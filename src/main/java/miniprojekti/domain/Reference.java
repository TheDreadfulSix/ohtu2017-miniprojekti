package miniprojekti.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Reference {
    protected String citationKey;
    
    protected static Set<FieldName> requiredFields;
    protected static Set<FieldName> optionalFields;
    protected static Set<FieldName> alternativeFields;
    
    protected Map<FieldName, Field> fields;
    
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
    public Reference(String citationKey, Collection<Field> fields) {
        this(citationKey, createFieldMap(fields));
    }
    
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
     * @throws IllegalArgumentException on missing alternative fields
     */
    public Reference(String citationKey, Map<FieldName, Field> fields) throws IllegalArgumentException {
        if (!fields.keySet().containsAll(requiredFields)) {
            throw new IllegalArgumentException("Required fields are missing");
        }
        
        if (!this.getAllFieldNames().containsAll(fields.keySet())) {
            throw new IllegalArgumentException("Invalid optional fields");
        }
        
        // TODO: Use retain all to make alternative fields exclusive
        if (getAlternativeFields() != null && 
            Collections.disjoint(fields.keySet(), getAlternativeFields())) {
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
    
    public static Set<FieldName> getRequiredFields() {
        return requiredFields;
    }
    
    public static Set<FieldName> getOptionalFields() {
        return optionalFields;
    }
    
    public static Set<FieldName> getAlternativeFields() {
        return alternativeFields;
    }

    protected Set<FieldName> getAllFieldNames() {
        return Stream.concat(getOptionalFields().stream(), getRequiredFields().stream())
               .collect(Collectors.toSet());
    }

    protected static Map<FieldName, Field> createFieldMap(Collection<Field> fields) {
        Map<FieldName, Field> fieldMap = new HashMap<>();
        
        for (Field field: fields) {
            fieldMap.put(field.getName(), field);
        }
        
        return fieldMap;
    }
    
    @Override
    public String toString() {
        String s = "source: Article\ncitation key: " + citationKey + "\n";
        for(FieldName fn : fields.keySet()){
            s += fn.name().toLowerCase() + ": " + fields.get(fn).getValue() + "\n";
        }
        s += "\n\n";
        return s;
    }
}
