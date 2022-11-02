package com.fsecure.monitor.util;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fsecure.monitor.service.Matrix;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class LogWriter {

    public void write(Matrix matrix) throws IOException {

        final CsvMapper mapper = new CsvMapper();
        final CsvSchema schema = mapper.schemaFor(Matrix.class);
        final String csv = mapper.writer(schema.withUseHeader(false)).writeValueAsString(matrix);


        boolean isFile = new File(Constants.LOG_FILE_NAME).isFile();

        FileWriter fileWriter;
        CSVPrinter printer;

        final CSVFormat format = CSVFormat.DEFAULT.withEscape(' ')
                .withRecordSeparator('\n')
                .withTrim()
                .withQuoteMode(QuoteMode.NONE);

        if (isFile) {
            fileWriter = new FileWriter(Constants.LOG_FILE_NAME, true);
            printer = new CSVPrinter(fileWriter, format.withSkipHeaderRecord());
        } else {
            fileWriter = new FileWriter(Constants.LOG_FILE_NAME);
            printer = new CSVPrinter(fileWriter, format.withHeader(MatrixHeader.class));
        }
        printer.print(csv);
        printer.println();
        printer.close();
        fileWriter.close();
    }
}

