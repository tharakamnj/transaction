package com.nmm.transaction.entity;

import com.nmm.transaction.util.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;
    private TransactionType type;
    private double transactionAmount;
    private double currentAmount;
    private int accountId;
    private String trAccountNo;
    private Date date;
    private String modifiedBy;
}
