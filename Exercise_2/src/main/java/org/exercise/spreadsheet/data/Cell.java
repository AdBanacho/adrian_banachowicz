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

    private void setValueType(ValueType valueType) {
        this.valueType = valueType;
    }

    public void setValue(String value) {
        if (value == null) {
            this.value = "";
            setValueType(ValueType.EMPTY);
            return;
        }

        ValueType valueType = ValueType.determineValueType(value);
        setValueType(valueType);

        this.value = (ValueType.isInteger(valueType)) ? value.trim() : value;
    }
}
