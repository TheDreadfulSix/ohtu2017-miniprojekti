package miniprojekti.domain;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import static miniprojekti.domain.Reference.requiredFields;

/**
 * Contains reference information of a book.
 */

public class Book extends Reference {
    static {
        requiredFields = EnumSet.of(
            FieldName.TITLE, 
            FieldName.YEAR,
            FieldName.PUBLISHER
        );
        optionalFields = EnumSet.of(
            FieldName.MONTH, 
            FieldName.NOTE, 
            FieldName.ADDRESS,
            FieldName.VOLUME,
            FieldName.EDITION,
            FieldName.SERIES
        );

        alternativeFields = EnumSet.of(
            FieldName.AUTHOR, 
            FieldName.EDITOR
        );
    }
    
    /**
     * Calls {@link #Reference(java.lang.String, java.util.Collection)}.
     */
    public Book(String citationKey, Collection<Field> fields) {
        this(citationKey, createFieldMap(fields));
    }
    
    /**
     * Calls {@link #Reference(java.lang.String, java.util.Map)}.
     */
    public Book(String citationKey, Map<FieldName, Field> fields) throws IllegalArgumentException {
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

