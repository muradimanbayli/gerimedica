package nl.gerimedica.model;

import java.time.LocalDate;
import lombok.Data;

@Data
public class SymptomDto {
    private String source;
    private String codeListCode;
    private String displayValue;
    private String code;
    private String longDescription;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer sortingPriority;
}
