package com.nnk.springboot.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bidlist")
public class BidList {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer BidListId;
    @NotBlank(message = "Account is mandatory")
    @Column(length = 32)
    String account;
    @NotBlank(message = "Type is mandatory")
    @Column(length = 32)
    String type;

    @Column(columnDefinition = "DECIMAL(8,1)")
    BigDecimal bidQuantity;
    @Column(columnDefinition = "DECIMAL(8,1)")
    BigDecimal askQuantity;
    @Column(columnDefinition = "DECIMAL(8,1)")
    BigDecimal bid;
    @Column(columnDefinition = "DECIMAL(8,1)")
    BigDecimal ask;

    @Column(length = 125)
    String benchmark;
    @NotNull(message="must not be null - bidListDate is mandatory")
    Timestamp bidListDate;
    @Column(length = 255)
    String commentary;
    @Column(length = 32)
    String security;
    @Column(length = 32)
    String status;
    @Column(length = 32)
    String trader;
    @Column(length = 32)
    String book;
    @Column(length = 32)
    String creationName;

    @CreationTimestamp
    Timestamp creationDate;
    @Column(length = 32)
    String revisionName;

    @UpdateTimestamp
    Timestamp revisionDate;
    @Column(length = 32)
    String dealName;
    @Column(length = 32)
    String dealType;
    @Column(length = 32)
    String sourceListId;
    @Column(length = 32)
    String side;
}
