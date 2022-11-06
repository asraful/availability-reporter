package com.fsecure.monitor.service;

import com.fsecure.monitor.util.Constants;
import com.fsecure.monitor.util.MatrixHeader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class ReportServiceImpl implements ReportService {

    private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    @Override
    public List<Matrix> getMatrixData() {

        List<Matrix> matrixList = new ArrayList<>();
        try {
            Reader in = new FileReader(Constants.LOG_FILE_NAME);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(MatrixHeader.class)
                    .withFirstRecordAsHeader()
                    .withRecordSeparator(",")
                    .parse(in);

            for (CSVRecord r : records) {
                Matrix matrix = new Matrix(r.get("LOG_TIME")
                        , r.get("URL"), r.get("CONTENT_MATCHED"),
                        Double.parseDouble(r.get("RESPONSE_TIME").trim()),
                        Integer.parseInt(r.get("HTTP_RESPONSE_CODE").trim()),
                        r.get("URL"));
                matrixList.add(matrix);
            }
        } catch (Exception e) {
            logger.error("Report error {}", e.getMessage());
        }

        matrixList.stream().sorted(Comparator.comparing(Matrix::getUrl));

        return matrixList;
    }
}
