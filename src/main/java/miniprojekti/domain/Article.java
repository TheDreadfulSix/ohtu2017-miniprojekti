package miniprojekti.domain;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import static miniprojekti.domain.Reference.requiredFields;

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
        super(citationKey, fields);
    }
    
    /**
     * Calls {@link #Reference(java.lang.String, java.util.Map)}.
     */
    public Article(String citationKey, Map<FieldName, Field> fields) throws IllegalArgumentException {
        super(citationKey, fields);
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
}
