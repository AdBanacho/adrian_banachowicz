package org.exercise.spreadsheet.data;

public class Cell {
    private ValueType valueType;
    private String value;

    public Cell() {
    }

    public ValueType getValueType() {
        return valueType;
    }

    public String getValue() {
        return value;
    }

    public Cell setDefaultValues(){
        updateCell(null);
        return this;
    }

    public void updateCell(String value) {
        setValueType(value);
        setValue(value);
    }

    private void setValueType(String value) {
        this.valueType = ValueType.determineValueType(value);
    }

    private void setValue(String value) {
        switch (valueType) {
            case ValueType.EMPTY:
                this.value = "";
                break;
            case ValueType.INTEGER:
                this.value = value.trim();
                break;
            default:
                this.value = value;
                break;
        }
    }
}
