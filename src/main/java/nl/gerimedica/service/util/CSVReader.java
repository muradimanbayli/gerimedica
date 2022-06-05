package nl.gerimedica.service.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import nl.gerimedica.entity.SymptomEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Log4j2
public class CSVReader {
    public List<SymptomEntity> tryParseCSV(InputStream inputStream) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<SymptomEntity> result = new ArrayList<>();
            Iterable<CSVRecord> rows = csvParser.getRecords();
            for (CSVRecord row : rows) {
                try {
                    result.add(readRow(row));
                } catch (Exception e) {
                    log.warn("skipped row, code: {}, exception: {}", row.get("code"), e.getMessage());
                }
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    private SymptomEntity readRow(CSVRecord row) {
        return SymptomEntity.builder()
                .source(row.get("source"))
                .codeListCode(row.get("codeListCode"))
                .code(row.get("code"))
                .displayValue(row.get("displayValue"))
                .longDescription(row.get("longDescription"))
                .fromDate(parseDate(row.get("fromDate")))
                .toDate(parseDate(row.get("toDate")))
                .sortingPriority(parseInteger(row.get("sortingPriority")))
                .build();
    }

    private LocalDate parseDate(String text) {
        if(text == null || StringUtils.isEmpty(text)) {
            return null;
        }
        return LocalDate.parse(text, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    private Integer parseInteger(String text) {
        if(text == null || StringUtils.isEmpty(text)) {
            return null;
        }
        return Integer.parseInt(text);
    }
}
