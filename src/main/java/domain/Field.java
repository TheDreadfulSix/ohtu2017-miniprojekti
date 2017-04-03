package domain;

public class Field {
    FieldName type;
    String value;
    
    public Field(FieldName type, String value) {
        this.type = type;
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public FieldName getType() {
        return type;
    }
}
