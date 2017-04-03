package domain;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Article {
    Set<FieldName> requiredFields = EnumSet.of(
        FieldName.AUTHOR, 
        FieldName.JOURNAL, 
        FieldName.TITLE, 
        FieldName.YEAR
    );
    
    Set<FieldName> optionalFields = EnumSet.of(
        FieldName.MONTH, 
        FieldName.NOTE, 
        FieldName.NUMBER, 
        FieldName.PAGES,
        FieldName.VOLUME
    );
    
    Map<FieldName, Field> fields = new HashMap<>();
    
    public Article(Collection<Field> fields) {
        this(mapFields(fields));
    }
    
    public Article(Map<FieldName, Field> fields) throws IllegalArgumentException {
        if (!fields.keySet().containsAll(requiredFields)) {
            throw new IllegalArgumentException("Required fields are missing");
        }
        
        if (!this.getAllFieldNames().containsAll(fields.keySet())) {
            throw new IllegalArgumentException("Invalid optional fields");
        }
        
        this.fields = fields;
    }
    
    public Field getField(FieldName name) {
        return fields.getOrDefault(name, null);
    }
    
    private static Map<FieldName, Field> mapFields(Collection<Field> fields) {
        Map<FieldName, Field> fieldMap = new HashMap<>();
        
        for (Field field: fields) {
            fieldMap.put(field.getType(), field);
        }
        
        return fieldMap;
    }
    
    private Set<FieldName> getAllFieldNames() {
        return Stream.concat(optionalFields.stream(), requiredFields.stream())
               .collect(Collectors.toSet());
    }
}
