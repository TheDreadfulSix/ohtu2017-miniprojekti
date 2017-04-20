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
    public Set<FieldName> requiredFields;
    public Set<FieldName> optionalFields;
    
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
    
    /**
     * Calls {@link #Reference(java.lang.String, java.util.Map)}.
     */
    public Inproceedings(String citationKey, Map<FieldName, Field> fields) throws IllegalArgumentException {
        super();
        this.setFields();
    }
  
    public Set<FieldName> getRequiredFields() {
        return requiredFields;
    }
    
    public Set<FieldName> getOptionalFields() {
        return optionalFields;
    }

    public void setFields() {
        requiredFields = EnumSet.of(
            FieldName.AUTHOR, 
            FieldName.BOOOKTITLE, 
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
    }
}
