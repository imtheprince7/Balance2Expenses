package com.b2e.service;

import com.b2e.entity.Wallet;
import com.b2e.exception.WalletException;
import com.b2e.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public List<Wallet> getAll(){
        return  walletRepository.findAllByOrderByPriority();
    }
    public Wallet getById(Long id){
        Optional<Wallet> wallet = walletRepository.findById(id);
        if(wallet.isPresent()){
            return wallet.get();
        }
        throw new WalletException("Wallet with"+" "+id+" "+"does not exist!!    ");
    }
    public Wallet createOrUpdate(Wallet wallet){
        // we will check first whether coming req is Insert || Insert:          // If call is Insert return id is null Otherwise return the id
        if(wallet.getId() == null){
            walletRepository.save(wallet);
        }
        else{
            walletRepository.save(wallet);
        }
        return wallet;
    }
    public boolean delete(Long id){
        Optional<Wallet> wallet = walletRepository.findById(id);
        if(wallet.isPresent()){
            walletRepository.delete(wallet.get());
            return  true;
        }
        throw  new WalletException("Wallet with"+" "+id+" "+"does not exist:");
    }




}
