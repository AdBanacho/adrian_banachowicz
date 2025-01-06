package org.exercise.spreadsheet.core;

import org.exercise.spreadsheet.data.Cell;
import org.exercise.spreadsheet.data.ValueType;

public class SpreadsheetImpl {
    private final Cell[][] cells;

    public SpreadsheetImpl(int rows, int columns) {
        this.cells = new Cell[rows][columns];
    }

    public String get(int row, int column) {
        return this.cells[row][column].getValue();
    }

    public void put(int row, int column, String value){
        this.cells[row][column].setValue(value);
    }

    public ValueType getValueType(int row, int column) {
        return this.cells[row][column].getValueType();
    }
}
