package miniprojekti.domain;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Contains reference information of an article.
 */
public class Article {
    private static final Set<FieldName> requiredFields = EnumSet.of(
        FieldName.AUTHOR, 
        FieldName.JOURNAL, 
        FieldName.TITLE, 
        FieldName.YEAR
    );
    
    private static final Set<FieldName> optionalFields = EnumSet.of(
        FieldName.MONTH, 
        FieldName.NOTE, 
        FieldName.NUMBER, 
        FieldName.PAGES,
        FieldName.VOLUME
    );
    
    private String citationKey;
    
    private Map<FieldName, Field> fields = new HashMap<>();
    
    /**
     * Initialization with a collection of fields instead of a map, calls
     * {@link #Article(java.lang.String, java.util.Map)}.
     * 
     * @see #Article(java.lang.String, java.util.Map)
     */
    public Article(String citationKey, Collection<Field> fields) {
        this(citationKey, fieldMap(fields));
    }
    
    /**
     * Initializes the article with given fields if they are valid. 
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
    public Article(String citationKey, Map<FieldName, Field> fields) throws IllegalArgumentException {
        if (!fields.keySet().containsAll(requiredFields)) {
            throw new IllegalArgumentException("Required fields are missing");
        }
        
        if (!this.getAllFieldNames().containsAll(fields.keySet())) {
            throw new IllegalArgumentException("Invalid optional fields");
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
    
    public Map<FieldName, Field> getFieldMap() {
        return fields;
    }
    
    public static Set<FieldName> getRequiredFields() {
        return requiredFields;
    }
    
    public static Set<FieldName> getOptionalFields() {
        return optionalFields;
    }
    
    private static Map<FieldName, Field> fieldMap(Collection<Field> fields) {
        Map<FieldName, Field> fieldMap = new HashMap<>();
        
        for (Field field: fields) {
            fieldMap.put(field.getName(), field);
        }
        
        return fieldMap;
    }
    
    private Set<FieldName> getAllFieldNames() {
        return Stream.concat(optionalFields.stream(), requiredFields.stream())
               .collect(Collectors.toSet());
    }
}
