package org.exercise;

import org.exercise.spreadsheet.core.SpreadsheetImpl;

/**
 * Utility class for creating new instances for office purposes, such as:
 * <li>{@link SpreadsheetImpl}</li>
 */
public final class Office {

    /**
     * Private constructor to prevent instantiation of the utility class
     */
    private Office (){
    }

    /**
     * Creates and returns a new instance of {@link SpreadsheetImpl} with the specified number of rows and columns
     *
     * @param rows    the number of rows in the new spreadsheet
     * @param columns the number of columns in the new spreadsheet
     * @return a new {@link SpreadsheetImpl} instance with the specified dimensions
     */
    public static SpreadsheetImpl newSpreadsheet(int rows, int columns){
        return new SpreadsheetImpl(rows, columns);
    }
}
