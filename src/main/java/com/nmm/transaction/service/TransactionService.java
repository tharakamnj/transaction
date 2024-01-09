package com.nmm.transaction.service;

import com.nmm.transaction.dto.TransactionDto;
import com.nmm.transaction.util.CommonResponse;
import org.springframework.http.ResponseEntity;

public interface TransactionService {
    ResponseEntity<CommonResponse> makeTransaction(TransactionDto dto);

    ResponseEntity<CommonResponse> findAllTransactionByAccount(int accountId);
}
