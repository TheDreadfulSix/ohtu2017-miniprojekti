package miniprojekti.domain;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

/**
 * Contains reference information of an article.
 */

public class Article extends Reference {
    
    public Article(){
        super();
    }
    
    /**
     * Calls {@link #Reference(java.lang.String, java.util.Collection)}.
     */
    public Article(String citationKey, Collection<Field> fields) {
        this(citationKey, createFieldMap(fields));
    }
    
    public Article(String citationKey, Collection<Field> fields, String tags) {
        this(citationKey, createFieldMap(fields));
        this.setTags(tags);
    }
    
    /**
     * Calls {@link #Reference(java.lang.String, java.util.Map)}.
     */
    public Article(String citationKey, Map<FieldName, Field> fields) throws IllegalArgumentException {
        this.setReference(citationKey, fields);
    }
    
    public void setFields() {
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
        alternativeFields = EnumSet.noneOf(FieldName.class);
    }
    
}
