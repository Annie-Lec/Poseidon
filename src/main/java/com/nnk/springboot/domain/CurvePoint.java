package com.nnk.springboot.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "curvepoint")
public class CurvePoint {

    public CurvePoint(int curveId, BigDecimal term, BigDecimal value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NotNull(message="must not be null")
    int curveId;
    @NotNull(message="must not be null - asOfDate is mandatory")
    Timestamp asOfDate;
    @DecimalMin(message = "must be greater than 0.0", value = "0.0", inclusive = false)
    @Column(columnDefinition = "DECIMAL(8,1)")
    BigDecimal term;
    @DecimalMin(message = "must be greater than 0.0", value = "0.0", inclusive = false)
    @Column(columnDefinition = "DECIMAL(8,1)")
    BigDecimal value;
    @CreationTimestamp
    Timestamp creationDate;
}
