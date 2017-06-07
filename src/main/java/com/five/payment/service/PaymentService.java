package com.five.payment.service;

import com.five.payment.model.Wallet;
import com.five.user.model.MyMessage;

/**
 * Created by msi on 2017/6/6.
 */
public interface PaymentService {
    MyMessage payOrder(int orderId);

    //Param:
    // balance : money
    MyMessage addWalletForNewUserById(int userId, int balance);
    Wallet findByUserId(int userId);

    MyMessage queryWallet(int userId);

    MyMessage updateWallet(int userId, double balance);

}
