package org.exercise.spreadsheet.exporters;

import org.exercise.spreadsheet.core.SpreadsheetImpl;

/**
 * A concrete implementation of {@link AbstractSpreadsheetExporter} that exports the spreadsheet data with a <b>star ('*')</b>
 */
public class StarSpreadsheetExporter extends AbstractSpreadsheetExporter {

    /**
     * Constructor of StarSpreadsheetExporter
     *
     * @param sheet the {@link SpreadsheetImpl} instance to be exported
     */
    public StarSpreadsheetExporter(SpreadsheetImpl sheet) {
        super(sheet);
    }

    /**
     * Provides the export option for the StarSpreadsheetExporter
     * which is a <b>star ('*')</b>
     * This export option will be used to separate the cell values during export
     *
     * @return the export option, which is the <b>star ('*')</b> character
     */
    @Override
    String getExportOption(){
        return "*";
    }

}
