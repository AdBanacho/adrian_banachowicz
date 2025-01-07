package org.exercise.spreadsheet.exporters;

import org.exercise.spreadsheet.core.SpreadsheetImpl;

import java.util.stream.IntStream;

abstract class AbstractSpreadsheetExporter {
    private final SpreadsheetImpl sheet;

    public AbstractSpreadsheetExporter(SpreadsheetImpl sheet) {
        this.sheet = sheet;
    }

    abstract String getExportOption();

    public String export() {
        int rows = sheet.getNumberOfRows();
        int columns = sheet.getNumberOfColumns();
        String exportOption = getExportOption();
        StringBuilder stringToExport = new StringBuilder();
        
        stringToExport.append(rows).append(",").append(columns).append("#");

        IntStream.range(0, rows).forEach(row -> {
            IntStream.range(0, columns).forEach(col -> {
                String cellValue = sheet.get(row, col);
                String exportValue = cellValue == null || cellValue.isEmpty() ? "" : cellValue;
                stringToExport.append(exportValue).append(exportOption);
            });
        });

        return stringToExport.toString();
    }
}
