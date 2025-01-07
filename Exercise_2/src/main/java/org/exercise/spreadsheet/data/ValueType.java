package org.exercise.spreadsheet.data;

public enum ValueType {
    INTEGER,
    FORMULA,
    STRING;

    public static ValueType determineValueType(String value) {
        if (value.matches("-?\\d+(\\.\\d+)?")) {
            return ValueType.INTEGER;
        } else if (value.startsWith("=")){
            return ValueType.FORMULA;
        } else {
            return ValueType.STRING;
        }
    }

}
