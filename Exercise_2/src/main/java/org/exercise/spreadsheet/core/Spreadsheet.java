package org.exercise.spreadsheet.core;

import org.exercise.spreadsheet.data.ValueType;

public interface Spreadsheet {

    String get(int row, int column);

    void put(int row, int column, String value);

    ValueType getValueType(int row, int column);
}
