package domain;

public class Field {
    FieldType type;
    String value;
    
    public Field(FieldType type, String value) {
        this.type = type;
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public FieldType getType() {
        return type;
    }
}
