package miniprojekti.domain;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

/**
 * Contains reference information of an article.
 */

public class Article extends Reference {
    public Set<FieldName> requiredFields;
    public Set<FieldName> optionalFields;
    
    public Article(){
        super();
        this.setFields();
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
        super();
        this.setFields();
    }
    
    public Set<FieldName> getRequiredFields() {
        if (requiredFields == null) {
            setFields();
        }
        return this.requiredFields;
    }
    
    public Set<FieldName> getOptionalFields() {
        if (optionalFields == null) {
            setFields();
        }
        return this.optionalFields;
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
    }
    
}
