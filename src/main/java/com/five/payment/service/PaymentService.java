package com.five.payment.service;

import com.five.payment.model.Wallet;
import com.five.user.model.MyMessage;

/**
 * Created by msi on 2017/6/6.
 */
public interface PaymentService {
    Object payOrder(int orderId);

    //Param:
    // balance : money
    Object addWalletForNewUserById(int userId, int balance);
    Wallet findByUserId(int userId);

    Object queryWallet(int userId);

    Object updateWallet(int userId, double balance);

    void reload();

}
