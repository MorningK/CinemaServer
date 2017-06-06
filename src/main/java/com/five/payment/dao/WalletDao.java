package com.five.payment.dao;

import com.five.order.model.Reservation;
import com.five.payment.model.Wallet;

import java.util.List;

/**
 * Created by msi on 2017/6/6.
 */
public interface WalletDao {
    List<Wallet> findWalletByUserId(int id);
    Wallet UpdateWalletBalanceById(int id, double newBalance);
}
