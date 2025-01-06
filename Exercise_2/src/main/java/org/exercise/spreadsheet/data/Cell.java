package org.exercise.spreadsheet.data;

public class Cell {
    private ValueType valueType;
    private String value;

    public Cell(ValueType valueType, String value) {
        this.valueType = valueType;
        this.value = value;
    }

    public ValueType getValueType() {
        return valueType;
    }

    public String getValue() {
        return value;
    }

    public void setValueType(ValueType valueType) {
        this.valueType = valueType;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
