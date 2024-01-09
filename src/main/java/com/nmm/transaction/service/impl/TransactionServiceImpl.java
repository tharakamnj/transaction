package com.nmm.transaction.service.impl;


import com.nmm.transaction.dto.TransactionDto;
import com.nmm.transaction.entity.Account;
import com.nmm.transaction.entity.Transaction;
import com.nmm.transaction.repository.AccountRepository;
import com.nmm.transaction.repository.TransactionRepository;
import com.nmm.transaction.service.TransactionService;
import com.nmm.transaction.util.CommonConst;
import com.nmm.transaction.util.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {


    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<CommonResponse> makeTransaction(TransactionDto dto) {
        log.info("Start makeTransaction method with AccountDto: " + dto);
        CommonResponse commonResponse = new CommonResponse();

        Account account = accountRepository.findById(dto.getAccount()).get();

        Transaction existingTransaction = Collections.max(transactionRepository.findByAccountId(account.getAccountId()),
                Comparator.comparing(Transaction::getDate));

        Transaction transaction = transactionRepository.save(new Transaction(
                dto.getTransactionId(),
                dto.getType(),
                dto.getTransactionAmount(),
                dto.getType() == "CR" ? existingTransaction.getCurrentAmount() + dto.getTransactionAmount() : existingTransaction.getCurrentAmount() - dto.getTransactionAmount(),
                account.getAccountId(),
                new Date(),
                dto.getModifiedBy()
        ));

        account.setAvailableBalance(transaction.getCurrentAmount());
        accountRepository.save(account);


        commonResponse.setPayload(Collections.singletonList(transaction));
        commonResponse.setStatus(CommonConst.CREATED);
        log.info("End makeTransaction method");
        return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CommonResponse> findAllTransactionByAccount(String accountNo) {
        log.info("Start findAllTransactionByAccount method ");
        CommonResponse commonResponse = new CommonResponse();

        Account account = accountRepository.findByAccountNo(accountNo);

        List<Transaction> existingTransactions = transactionRepository.findByAccountId(account.getAccountId());
        Collections.sort(existingTransactions, Comparator.comparing(Transaction::getDate));

        commonResponse.setPayload(Collections.singletonList(existingTransactions));
        commonResponse.setStatus(CommonConst.SUCCESS_CODE);
        log.info("End findAllTransactionByAccount method");
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }
}
