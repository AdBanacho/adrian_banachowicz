package org.exercise.spreadsheet.core;

import org.exercise.spreadsheet.data.Cell;
import org.exercise.spreadsheet.data.ValueType;

import java.util.stream.IntStream;

/**
 * Represents a spreadsheet with a grid of cells
 * Each cell can hold a value and has an associated value type
 */
public class SpreadsheetImpl implements Spreadsheet {
    private final Integer numberOfRows;
    private final Integer numberOfColumns;
    private final Cell[][] cells;

    /**
     * Constructor of SpreadsheetImpl
     *
     * @param numberOfRows    the number of rows in the spreadsheet
     * @param numberOfColumns the number of columns in the spreadsheet
     */
    public SpreadsheetImpl(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.cells = new Cell[numberOfRows][numberOfColumns];
        initializeCells(numberOfRows, numberOfColumns);
    }

    /**
     * Initializes all cells in the spreadsheet to have a default value and type
     *
     * @param numberOfRows    the number of rows in the spreadsheet
     * @param numberOfColumns the number of columns in the spreadsheet
     */
    private void initializeCells(int numberOfRows, int numberOfColumns) {
        IntStream.range(0, numberOfRows).forEach(row ->
                IntStream.range(0, numberOfColumns).forEach(col ->
                        cells[row][col] = new Cell().setDefaultValues()
                )
        );
    }

    /**
     * @inheritDoc
     */
    @Override
    public String get(int row, int column) {
        validateIndices(row, column);
        return getCell(row, column).getValue();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void put(int row, int column, String value) {
        validateIndices(row, column);
        getCell(row, column).updateCell(value);
    }

    /**
     * @inheritDoc
     */
    @Override
    public ValueType getValueType(int row, int column) {
        validateIndices(row, column);
        return getCell(row, column).getValueType();
    }

    /**
     * Validates that the specified row and column indices are within the bounds of the spreadsheet
     *
     * @param row    the row index to validate
     * @param column the column index to validate
     * @throws IndexOutOfBoundsException if the row or column indices are out of bounds
     */
    private void validateIndices(int row, int column) {
        if (row < 0 || row >= getNumberOfRows()) {
            throw new IndexOutOfBoundsException("Invalid row index: " + row);
        }
        if (column < 0 || column >= getNumberOfColumns()) {
            throw new IndexOutOfBoundsException("Invalid column index: " + column);
        }
    }

    /**
     * Return the number of rows in the spreadsheet
     *
     * @return the number of rows
     */
    public Integer getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * Return the number of columns in the spreadsheet
     *
     * @return the number of columns
     */
    public Integer getNumberOfColumns() {
        return numberOfColumns;
    }

    /**
     * Return the Cell object at the specified row and column
     *
     * @param row    the row index of the cell
     * @param column the column index of the cell
     * @return the Cell object
     */
    private Cell getCell(int row, int column) {
        return cells[row][column];
    }
}
