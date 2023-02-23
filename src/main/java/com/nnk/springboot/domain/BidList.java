package com.nnk.springboot.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "bid_list")
public class BidList {

    public BidList(String account, String type, BigDecimal bidQuantity ){
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;
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

    Timestamp bidListDate;
    @Column(length = 250)
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
