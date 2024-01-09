package com.nmm.transaction.controller;


import com.nmm.transaction.dto.TransactionDto;
import com.nmm.transaction.service.TransactionService;
import com.nmm.transaction.util.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transaction")
    public ResponseEntity<CommonResponse> makeTransaction(@RequestBody TransactionDto dto){
        log.info("Start makeTransaction method with TransactionDto: " + dto);
        ResponseEntity<CommonResponse> responseEntity = null;
        CommonResponse commonResponse = new CommonResponse();
        try {
            responseEntity = transactionService.makeTransaction(dto);
        } catch (Exception ex) {
            commonResponse.setStatus(HttpStatus.EXPECTATION_FAILED.value());
            commonResponse.setErrorMessages(Collections.singletonList(ex.getMessage()));
            log.error(ex.getMessage());
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        log.info("End makeTransaction method");
        return responseEntity;
    }

    @GetMapping("/allTransactionByAccount/{accountNo}")
    public ResponseEntity<CommonResponse> findAllTransactionByAccount(@PathVariable("accountId") String accountNo){
        log.info("Start findAllTransactionByAccount method");
        ResponseEntity<CommonResponse> responseEntity = null;
        CommonResponse commonResponse = new CommonResponse();
        try {
            responseEntity = transactionService.findAllTransactionByAccount(accountNo);
        } catch (Exception ex) {
            commonResponse.setStatus(HttpStatus.EXPECTATION_FAILED.value());
            commonResponse.setErrorMessages(Collections.singletonList(ex.getMessage()));
            log.error(ex.getMessage());
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        log.info("End findAllTransactionByAccount method");
        return responseEntity;
    }
}
