package org.exercise.spreadsheet.exporters;

import org.exercise.spreadsheet.core.SpreadsheetImpl;

import java.util.stream.IntStream;

/**
 * Abstract class responsible for exporting a spreadsheet to a specific format
 * This class provides a template for exporting the spreadsheet data
 * and subclasses must define the specific export option
 */
abstract class AbstractSpreadsheetExporter {
    private final SpreadsheetImpl sheet;

    /**
     * Constructor of AbstractSpreadsheetExporter
     *
     * @param sheet the {@link SpreadsheetImpl} instance to be exported
     */
    public AbstractSpreadsheetExporter(SpreadsheetImpl sheet) {
        this.sheet = sheet;
    }

    /**
     * Defines the export option for the specific export format
     * Subclasses must provide the specific export format option (e.g., CSV delimiter)
     *
     * @return the export option as a String
     */
    abstract String getExportOption();

    /**
     * Exports the spreadsheet data as a string based on the specified export format
     *
     * @return the exported spreadsheet data as a string
     */
    public String export() {
        int rows = sheet.getNumberOfRows();
        int columns = sheet.getNumberOfColumns();
        String exportOption = getExportOption();
        StringBuilder stringToExport = new StringBuilder();

        // Header
        stringToExport.append(rows).append(",").append(columns).append("#");

        // Cells
        IntStream.range(0, rows).forEach(row -> {
            IntStream.range(0, columns).forEach(col -> {
                String cellValue = sheet.get(row, col);
                if (!cellValue.isEmpty()) {
                    stringToExport.append(cellValue);
                }
                stringToExport.append(exportOption);
            });
        });

        return stringToExport.toString();
    }
}