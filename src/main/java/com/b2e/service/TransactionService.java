package com.b2e.service;

import com.b2e.entity.Transaction;
import com.b2e.entity.Wallet;
import com.b2e.exception.WalletException;
import com.b2e.repository.TransactionRepository;
import com.b2e.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    public  TransactionService( TransactionRepository transactionRepository, WalletRepository walletRepository ){
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
    }

    public List<Transaction> getAll(Long walletId){
        Optional<Wallet>wallet  = walletRepository.findById(walletId);
        return wallet.map(transactionRepository::findBywallet).orElse(null);
    }


    public Transaction getById(Long wallet_id, Long transaction_id){
        Optional<Wallet>wallet  = walletRepository.findById(wallet_id);
        if(wallet.isPresent()) {
            Optional<Transaction> transaction = transactionRepository.findById(transaction_id);
            if (transaction.isPresent()) return transaction.get();
        }
        throw new WalletException("Transaction with"+" "+transaction_id+" "+"does not exist!!    ");
    }

    public Transaction createOrUpdate(Long walletId, Transaction transaction){
        // we will check first whether coming req is Insert || Insert:          // If call is Insert return id is null Otherwise return the id
        Optional<Wallet>wallet  = walletRepository.findById(walletId);
        if(wallet.isPresent()) {
            transaction.setWallet(wallet.get());
            transactionRepository.save(transaction);
            return transaction;
        }
        return null;
    }

    public void delete(Long wallet_id, Long transaction_id){
        Optional<Wallet>wallet  = walletRepository.findById(wallet_id);
        if(wallet.isPresent()) {
            Optional<Transaction> transaction = transactionRepository.findById(transaction_id);
            if (transaction.isPresent()) {
                transactionRepository.delete(transaction.get());
                return;
            }
        }
        throw  new WalletException("Transaction with"+" "+transaction_id+" "+"does not exist:");
    }

}
