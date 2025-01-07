package org.exercise.spreadsheet.exporters;

import org.exercise.spreadsheet.core.SpreadsheetImpl;

abstract class AbstractSpreadsheetExporter {
    protected final SpreadsheetImpl sheet;

    public AbstractSpreadsheetExporter(SpreadsheetImpl sheet) {
        this.sheet = sheet;
    }

    abstract String getExportOption();

    public String export() {
        return "";
    }
}
