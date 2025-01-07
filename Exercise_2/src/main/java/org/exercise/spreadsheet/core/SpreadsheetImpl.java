package org.exercise.spreadsheet.core;

import org.exercise.spreadsheet.data.Cell;
import org.exercise.spreadsheet.data.ValueType;

import java.util.stream.IntStream;

public class SpreadsheetImpl {
    private final Integer numberOfRows;
    private final Integer numberOfColumns;
    private final Cell[][] cells;

    public SpreadsheetImpl(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.cells = new Cell[numberOfRows][numberOfColumns];
        initializeCells(numberOfRows, numberOfColumns);
    }

    private void initializeCells(int rows, int columns) {
        IntStream.range(0, rows).forEach(row ->
                IntStream.range(0, columns).forEach(col ->
                        this.cells[row][col] = new Cell(ValueType.STRING, "")
                )
        );
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

    public Integer getNumberOfRows() {
        return numberOfRows;
    }

    public Integer getNumberOfColumns() {
        return numberOfColumns;
    }
}
