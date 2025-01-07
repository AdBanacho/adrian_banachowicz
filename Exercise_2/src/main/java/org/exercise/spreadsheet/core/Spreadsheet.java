package org.exercise.spreadsheet.core;

import org.exercise.spreadsheet.data.ValueType;


public interface Spreadsheet {

    /**
     * Return the value stored in the cell at the specified row and column

     * @param row    the row index of the cell
     * @param column the column index of the cell
     * @return the value of the cell as a String
     * @throws IndexOutOfBoundsException if the row or column indices are out of bounds
     */
    String get(int row, int column);

    /**
     * Updates the value of the cell at the specified row and column
     *
     * @param row    the row index of the cell
     * @param column the column index of the cell
     * @param value  the new value to store in the cell
     * @throws IndexOutOfBoundsException if the row or column indices are out of bounds
     */
    void put(int row, int column, String value);

    /**
     * Return the value type of the cell at the specified row and column
     *
     * @param row    the row index of the cell
     * @param column the column index of the cell
     * @return the ValueType of the cell
     * @throws IndexOutOfBoundsException if the row or column indices are out of bounds
     */
    ValueType getValueType(int row, int column);
}
