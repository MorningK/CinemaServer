package com.five.payment.dao;

import com.five.order.model.Reservation;
import com.five.payment.model.Wallet;

import java.util.List;

/**
 * Created by msi on 2017/6/6.
 */
public interface WalletDao {
    Wallet findWalletByUserId(int userId);
    Wallet UpdateWalletBalanceById(int id, double newBalance);
    Wallet addWalletForNewUserById(int userId, int balance);
    void reload();
}
