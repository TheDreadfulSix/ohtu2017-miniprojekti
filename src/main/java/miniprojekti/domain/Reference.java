package miniprojekti.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Reference {
    protected String citationKey;
    protected String tags;
    
    //Set<FieldName> requiredFields;
    //Set<FieldName> optionalFields;
    
    protected Map<FieldName, Field> fields;
    
    protected Set<FieldName> requiredFields;
    protected Set<FieldName> optionalFields;
    protected Set<FieldName> alternativeFields;
    
    public Reference(){
        this.setFields();
        tags = "";
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
        this.setFields();
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
    
    public void setTags(String tagInput) {
        if (tags == null) tags = "";
        if (!tags.isEmpty() && !tagInput.isEmpty()) tags += ",";
        tags += tagInput.replace(' ', ',');
    }
    
    public String getTags() {
        return tags;
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
       
    public Set<FieldName> getRequiredFields() {
        return requiredFields;
    }
    
    public Set<FieldName> getOptionalFields() {
        return optionalFields;
    }
    
    public Set<FieldName> getAlternativeFields() {
        return alternativeFields;
    }
    
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
    
    @Override
    public String toString() {
        String all = "\n\t";
        all += this.getClass().getSimpleName().toUpperCase() + "\t\t\t\tCitation Key: " + this.citationKey;
        for (FieldName fld : this.fields.keySet()) {
            all += "\n\t\t\t" + fld.toString() + ": " + this.fields.get(fld).value;
        }
        all += "\n";
        if (tags != null && !tags.isEmpty()) {
            all += "\t\t\tTags: ";
            String formattedTags = tags.replace(",", ", ");
            all += formattedTags;
            all += "\n";
        } 
        return all;
    }
}
