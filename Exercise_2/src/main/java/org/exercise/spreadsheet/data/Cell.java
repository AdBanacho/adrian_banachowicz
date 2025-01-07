package org.exercise.spreadsheet.data;

/**
 * Represents a cell in the spreadsheet that holds a value and its corresponding value type
 * It allows updating the value and determining the type of the value it holds
 */
public class Cell {
    private ValueType valueType;
    private String value;

    /**
     * No args constructor of Cell
     */
    public Cell() {
    }

    /**
     * ValueType Getter
     *
     * @return the ValueType of the cell
     */
    public ValueType getValueType() {
        return valueType;
    }

    /**
     * Value Getter
     *
     * @return the value of the cell
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets default values:
     * <li>value - empty string ("")</li>
     * <li>valueType - {@link ValueType}.EMPTY</li>
     *
     * @return the current Cell object with default values set
     */
    public Cell setDefaultValues() {
        updateCell(null);
        return this;
    }

    /**
     * Updates the value stored in the cell and determines value type
     *
     * @param value the new value to store in the cell
     */
    public void updateCell(String value) {
        setValueType(value);
        setValue(value);
    }

    /**
     * ValueType Setter
     *
     * @param value the value used to determine the type
     * @see ValueType
     */
    private void setValueType(String value) {
        this.valueType = ValueType.determineValueType(value);
    }

    /**
     * Value Setter
     * The method handles different types of values such as empty, integer, and other types
     *
     * <li>For an empty and null value, it sets the cell's value as an empty string ("")</li>
     * <li>For an integer value, it trims the input string and stores it</li>
     * <li>For other types, it stores the value as-is</li>
     *
     * @param value the value to store in the cell
     */
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
