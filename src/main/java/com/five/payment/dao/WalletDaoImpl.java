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
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(keyGenerator = "wiselyKeyGenerator")
    public Wallet findWalletByUserId(int userId) {
        List<Wallet> wallets = walletRepository.findByUserId(userId);
        if (wallets.size() == 0) return null;
        else if (wallets.size() != 1) return new Wallet(-1, 0);
        else return wallets.get(0);
    }

    @Override
    @CachePut(key = "'wallet.findWalletByUserId'+#result.getUserId()", condition = "#result != null")
    public Wallet UpdateWalletBalanceById(int id, double newBalance) {
        Wallet wallet = walletRepository.findOne(id);
        wallet.setBalance(newBalance);
        return walletRepository.save(wallet);
    }

    @Override
    @CachePut(key = "'wallet.findWalletByUserId'+#result.getUserId()", condition = "#result != null")
    public Wallet addWalletForNewUserById(int userId, int balance) {
        Wallet wallet = new Wallet(userId, balance);
        return walletRepository.save(wallet);
    }

    @Override
//    @CacheEvict(cacheNames = "wallet", allEntries = true)
    public void reload() {

    }

    @Override
    @CacheEvict(key = "'wallet.findWalletByUserId'+#p0", condition = "#p0 != null")
    public void refreshOne(int userId) {

    }


}
