package org.exercise.spreadsheet.data;

public enum ValueType {
    INTEGER,
    FORMULA,
    STRING,
    EMPTY;

    /**
     * Determines the value type of given string value
     * It checks the value and categorizes it as one of the defined value types:
     * <li>If the value is null or empty, it returns {@link ValueType#EMPTY}</li>
     * <li>If the value is an integer (a valid number), it returns {@link ValueType#INTEGER}.</li>
     * <li>If the value starts with an equal sign ("="), it returns {@link ValueType#FORMULA}.</li>
     * <li>Otherwise, it returns {@link ValueType#STRING}</li>
     *
     * @param value the string value to evaluate
     * @return the determined ValueType based on the value
     */
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
