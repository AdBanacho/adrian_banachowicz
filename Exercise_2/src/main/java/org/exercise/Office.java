package org.exercise;

import org.exercise.spreadsheet.core.SpreadsheetImpl;

public class Office {

    public static SpreadsheetImpl newSpreadsheet(int rows, int columns){
        return new SpreadsheetImpl(rows, columns);
    }
}
