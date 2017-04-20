package miniprojekti.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Reference {
    protected String citationKey;
    
    //Set<FieldName> requiredFields;
    //Set<FieldName> optionalFields;
    
    protected Map<FieldName, Field> fields;
    
    public Reference(){
        this.setFields();
    }
    
    /**
     * Initialization with a collection of fields instead of a map, calls
     * {@link #Reference(java.lang.String, java.util.Map)} after creating the
     * map.
     * 
     * @param   citationKey
     * @param   fields
     * 
     * @see #Article(java.lang.String, java.util.Map)
     */
    
    /**
     * Initializes the reference with given citation key and fields.
     * 
     * @param   citationKey
     * @param   fields
     * 
     * @see miniprojekti.domain.Field
     * @see miniprojekti.domain.FieldName
     * 
     * @throws IllegalArgumentException on missing required fields
     * @throws IllegalArgumentException on invalid optional fields
     */
    public void setReference(String citationKey, Map<FieldName, Field> fields) throws IllegalArgumentException {
        if (!fields.keySet().containsAll(getRequiredFields())) {
            throw new IllegalArgumentException("Required fields are missing");
        }
        
        if (!this.getAllFieldNames().containsAll(fields.keySet())) {
            throw new IllegalArgumentException("Invalid optional fields");
        }
        boolean alternativeFieldCheck = true;
        for (FieldName alternativeField : this.getAlternativeFields()) {
            alternativeFieldCheck = false;
            if (fields.keySet().contains(alternativeField)) {
                alternativeFieldCheck = true;
                break;
            }
        }
        if (!alternativeFieldCheck) {
            throw new IllegalArgumentException("Required fields are missing");
        }
        
        this.citationKey = citationKey;
        this.fields = fields;
    }
    
    /**
     * Gets the field of given fieldname.
     * 
     * @param   name
     * 
     * @see miniprojekti.domain.Field
     * @see miniprojekti.domain.FieldName
     * 
     * @return Fieldname if one exists, otherwise null.
     */
    public Field getField(FieldName name) {
        return fields.getOrDefault(name, null);
    }
    
    public String getCitationKey() {
        return citationKey;
    }
    
    public Map<FieldName, Field> getFieldMap() {
        return fields;
    }
       
    public abstract Set<FieldName> getRequiredFields();
    
    public abstract Set<FieldName> getOptionalFields();
    
    public abstract Set<FieldName> getAlternativeFields();
    
    protected Set<FieldName> getAllFieldNames() {
        
        return Stream.concat(Stream.concat(getOptionalFields().stream(), getRequiredFields().stream()), getAlternativeFields().stream())
               .collect(Collectors.toSet());
    }

    protected static Map<FieldName, Field> createFieldMap(Collection<Field> fields) {
        Map<FieldName, Field> fieldMap = new HashMap<>();
        
        for (Field field: fields) {
            fieldMap.put(field.getName(), field);
        }
        
        return fieldMap;
    }
    
    public abstract void setFields();
 
}
