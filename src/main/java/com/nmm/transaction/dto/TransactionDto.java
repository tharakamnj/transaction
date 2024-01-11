package com.nmm.transaction.dto;

import com.nmm.transaction.util.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private int transactionId;
    private TransactionType type;
    private double transactionAmount;
    private double currentAmount;
    private String accountNo;
    private String trAccountNo;
    private Date date;
    private String modifiedBy;
}
