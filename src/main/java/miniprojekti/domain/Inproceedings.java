/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojekti.domain;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Joonas
 */
public class Inproceedings extends Reference {
    
    public Inproceedings(){
        super();
        this.setFields();
    }
    
     /**
     * Calls {@link #Reference(java.lang.String, java.util.Collection)}.
     */
    public Inproceedings(String citationKey, Collection<Field> fields) {
        this(citationKey, createFieldMap(fields));
    }
    
    public Inproceedings(String citationKey, Collection<Field> fields, String tags) {
        this(citationKey, createFieldMap(fields));
        this.setTags(tags);
    }
    
    /**
     * Calls {@link #Reference(java.lang.String, java.util.Map)}.
     */
    public Inproceedings(String citationKey, Map<FieldName, Field> fields) throws IllegalArgumentException {
        super();
        this.setFields();
        this.setReference(citationKey, fields);
    }

    public void setFields() {
        requiredFields = EnumSet.of(
            FieldName.AUTHOR, 
            FieldName.BOOKTITLE, 
            FieldName.TITLE, 
            FieldName.YEAR
        );
        optionalFields = EnumSet.of(
            FieldName.ADDRESS, 
            FieldName.EDITOR, 
            FieldName.MONTH, 
            FieldName.NOTE,
            FieldName.NUMBER,
            FieldName.ORGANIZATION,
            FieldName.PAGES,
            FieldName.PUBLISHER,
            FieldName.SERIES,
            FieldName.VOLUME
        );
        alternativeFields = EnumSet.noneOf(FieldName.class);
    }
}
