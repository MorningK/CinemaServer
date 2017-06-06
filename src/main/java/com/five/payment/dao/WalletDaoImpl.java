package com.five.payment.dao;

import com.five.order.model.Reservation;
import com.five.order.repository.OrderRepository;
import com.five.payment.model.Wallet;
import com.five.payment.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by msi on 2017/6/6.
 */
@Repository
public class WalletDaoImpl implements WalletDao {

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public List<Wallet> findWalletByUserId(int id) {
        return walletRepository.findByUserId(id);
    }

    @Override
    public Wallet UpdateWalletBalanceById(int id, double newBalance) {
        Wallet wallet = walletRepository.findOne(id);
        wallet.setBalance(newBalance);
        return walletRepository.save(wallet);
    }


}
