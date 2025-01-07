package org.exercise.spreadsheet.exporters;

import org.exercise.spreadsheet.core.SpreadsheetImpl;

public class DashSpreadsheetExporter extends AbstractSpreadsheetExporter {

    public DashSpreadsheetExporter(SpreadsheetImpl sheet) {
        super(sheet);
    }

    @Override
    String getExportOption(){
        return "-";
    }

}
