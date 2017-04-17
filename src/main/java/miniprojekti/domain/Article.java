package miniprojekti.domain;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;

/**
 * Contains reference information of an article.
 */
public class Article extends Reference {
    static {
        requiredFields = EnumSet.of(
            FieldName.AUTHOR, 
            FieldName.JOURNAL, 
            FieldName.TITLE, 
            FieldName.YEAR
        );
        optionalFields = EnumSet.of(
            FieldName.MONTH, 
            FieldName.NOTE, 
            FieldName.NUMBER, 
            FieldName.PAGES,
            FieldName.VOLUME
        );
    }
    
    /**
     * Calls {@link #Reference(java.lang.String, java.util.Collection)}.
     */
    public Article(String citationKey, Collection<Field> fields) {
        this(citationKey, createFieldMap(fields));
    }
    
    /**
     * Calls {@link #Reference(java.lang.String, java.util.Map)}.
     */
    public Article(String citationKey, Map<FieldName, Field> fields) throws IllegalArgumentException {
        super(citationKey, fields);
    }
    
}
