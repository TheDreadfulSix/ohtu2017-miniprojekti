package miniprojekti.domain;

/**
 * Contains fieldname and value.
 */
public class Field {
    FieldName name;
    String value;
    
    /**
     * Initiates a field of given name and value.
     *
     * @param   name
     * @param   value
     * 
     * @see miniprojekti.domain.FieldName
     */
    public Field(FieldName name, String value) {
        this.name = name;
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public FieldName getName() {
        return name;
    }
}
