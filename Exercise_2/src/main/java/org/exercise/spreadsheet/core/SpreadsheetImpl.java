package org.exercise.spreadsheet.core;

import org.exercise.spreadsheet.data.Cell;
import org.exercise.spreadsheet.data.ValueType;

import java.util.stream.IntStream;

public class SpreadsheetImpl implements Spreadsheet {
    private final Integer numberOfRows;
    private final Integer numberOfColumns;
    private final Cell[][] cells;

    public SpreadsheetImpl(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.cells = new Cell[numberOfRows][numberOfColumns];
        initializeCells(numberOfRows, numberOfColumns);
    }

    private void initializeCells(int numberOfRows, int numberOfColumns) {
        IntStream.range(0, numberOfRows).forEach(row ->
                IntStream.range(0, numberOfColumns).forEach(col ->
                        cells[row][col] = new Cell(ValueType.STRING, "")
                )
        );
    }

    @Override
    public String get(int row, int column) {
        validateIndices(row, column);
        return getCell(row, column).getValue();
    }

    @Override
    public void put(int row, int column, String value) {
        validateIndices(row, column);
        getCell(row, column).setValue(value);
    }

    @Override
    public ValueType getValueType(int row, int column) {
        validateIndices(row, column);
        return getCell(row, column).getValueType();
    }

    private void validateIndices(int row, int column) {
        if (row < 0 || row >= getNumberOfRows()) {
            throw new IndexOutOfBoundsException("Invalid row index: " + row);
        }
        if (column < 0 || column >= getNumberOfColumns()) {
            throw new IndexOutOfBoundsException("Invalid column index: " + column);
        }
    }

    public Integer getNumberOfRows() {
        return numberOfRows;
    }

    public Integer getNumberOfColumns() {
        return numberOfColumns;
    }

    private Cell getCell(int row, int column) {
        return cells[row][column];
    }
}
