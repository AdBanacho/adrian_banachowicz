package org.exercise.spreadsheet.exporters;

import org.exercise.spreadsheet.core.SpreadsheetImpl;

public class StarSpreadsheetExporter extends AbstractSpreadsheetExporter {

    public StarSpreadsheetExporter(SpreadsheetImpl sheet) {
        super(sheet);
    }

    @Override
    String getExportOption(){
        return "*";
    }

}
