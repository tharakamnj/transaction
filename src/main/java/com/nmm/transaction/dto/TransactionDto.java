package com.nmm.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private int transactionId;
    private String type;
    private double transactionAmount;
    private double currentAmount;
    private String accountNo;
    private Date date;
    private String modifiedBy;
}
