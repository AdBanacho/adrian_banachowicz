package org.exercise.spreadsheet.data;

public enum ValueType {
    INTEGER,
    FORMULA,
    STRING,
    EMPTY;

    public static ValueType determineValueType(String value) {
        if (value == null || value.isEmpty()){
            return ValueType.EMPTY;
        } else if (value.trim().matches("-?\\d+")) {
            return ValueType.INTEGER;
        } else if (value.startsWith("=")){
            return ValueType.FORMULA;
        } else {
            return ValueType.STRING;
        }
    }

}
