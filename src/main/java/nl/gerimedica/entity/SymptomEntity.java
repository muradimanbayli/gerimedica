package nl.gerimedica.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Builder
@Table(name = "symptom")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SymptomEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String source;
    private String codeListCode;
    private String displayValue;
    private String code;
    private String longDescription;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer sortingPriority;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
