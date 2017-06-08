package com.five.payment.dao;

import com.five.order.model.Reservation;
import com.five.order.repository.OrderRepository;
import com.five.payment.model.Wallet;
import com.five.payment.repository.WalletRepository;
import org.hibernate.annotations.Cache;
import org.hibernate.persister.walking.spi.WalkingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by msi on 2017/6/6.
 */
@Repository
@CacheConfig(cacheNames = "wallet")
public class WalletDaoImpl implements WalletDao {

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public List<Wallet> findWalletByUserId(int userId) {
        return walletRepository.findByUserId(userId);
    }

    @Override
    @CachePut(cacheNames = "wallet")
    public Wallet UpdateWalletBalanceById(int id, double newBalance) {
        Wallet wallet = walletRepository.findOne(id);
        wallet.setBalance(newBalance);
        return walletRepository.save(wallet);
    }

    @Override
    @CachePut(cacheNames = "wallet")
    public Wallet addWalletForNewUserById(int userId, int balance) {
        Wallet wallet = new Wallet(userId, balance);
        return walletRepository.save(wallet);
    }

    @Override
    @CacheEvict(cacheNames = "wallet", allEntries = true)
    public void reload() {

    }


}
