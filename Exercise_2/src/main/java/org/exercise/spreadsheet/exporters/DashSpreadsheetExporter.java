package org.exercise.spreadsheet.exporters;

import org.exercise.spreadsheet.core.SpreadsheetImpl;

/**
 * A concrete implementation of {@link AbstractSpreadsheetExporter} that exports the spreadsheet data with a <b>dash ('-')</b>
 */
public class DashSpreadsheetExporter extends AbstractSpreadsheetExporter {

    /**
     * Constructor of DashSpreadsheetExporter
     *
     * @param sheet the {@link SpreadsheetImpl} instance to be exported
     */
    public DashSpreadsheetExporter(SpreadsheetImpl sheet) {
        super(sheet);
    }

    /**
     * Provides the export option for the DashSpreadsheetExporter
     * which is a <b>dash ('-')</b>
     * This export option will be used to separate the cell values during export
     *
     * @return the export option, which is the <b>dash ('-')</b> character
     */
    @Override
    String getExportOption(){
        return "-";
    }

}
