package com.five.payment.service;

import com.five.order.model.Reservation;
import com.five.order.service.OrderService;
import com.five.order.utils.ClockThread;
import com.five.order.utils.ThreadPool;
import com.five.payment.dao.WalletDao;
import com.five.payment.model.Wallet;
import com.five.user.model.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by msi on 2017/6/6.
 */
@Transactional
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private WalletDao walletDao;

    @Autowired
    private OrderService orderService;

    @Override
    public Object payOrder(int orderId) {
        ClockThread clockThread = ThreadPool.getInstance().get(orderId);
        if (clockThread == null) return new MyMessage(0, "订单已过期");
        Reservation reservation = orderService.findById(orderId);
        List<Wallet> wallets = walletDao.findWalletByUserId(reservation.getUserId());
        if (wallets == null) {
            return new MyMessage(0, "支付失败，数据库无记录");
        }
        if (wallets.size() != 1) {
            return new MyMessage(0,"钱包异常，此用户拥有多个钱包");
        }
        Wallet wallet = wallets.get(0);
        if (wallet.getBalance() > reservation.getPrice()) {
            double oldBalance = wallet.getBalance();
            Wallet payResult = walletDao.UpdateWalletBalanceById(wallet.getId(), wallet.getBalance()-reservation.getPrice());
            if (payResult.getBalance() == oldBalance - reservation.getPrice()) {
                int result = orderService.UpdateStatusById(orderId, Reservation.PAID);
                System.out.println(result);
                if (result > 0) {
                    clockThread.paid();
                    return new MyMessage(1, "支付成功");
                } else {
                    return new MyMessage(0, "订单状态修改失败");
                }
            } else {

                return new MyMessage(0, "扣款失败");
            }

        } else {
            return new MyMessage(0, "余额不足");
        }

    }

    @Override
    public Object addWalletForNewUserById(int userId, int balance) {
        Wallet wallet = findByUserId(userId);
        if (wallet == null) {
            if (wallet.getUserId() != -1) {
                Wallet wallet1 = walletDao.addWalletForNewUserById(userId, balance);
                if (wallet1 != null)
                    return new MyMessage(1, "钱包创建成功");
                else
                    return new MyMessage(0, "钱包创建失败");
            } else {
                return new MyMessage(0, "钱包异常，此用户拥有多个钱包");
            }
        }
        return new MyMessage(0, "钱包已存在");
    }

    @Override
    public Wallet findByUserId(int userId) {
        List<Wallet> wallets = walletDao.findWalletByUserId(userId);
        if (wallets == null) return null;
        else if (wallets.size() != 1) {
            //Todo
            // if wallet duplicate?
            return new Wallet(-1, 0);

        } else return wallets.get(0);
    }

    @Override
    public Object queryWallet(int userId) {
        Wallet wallet = findByUserId(userId);
        if (wallet != null) {
            if (wallet.getUserId() != -1) {
                return new MyMessage(1, wallet);
            } else {
                return new MyMessage(0, "钱包异常，此用户拥有多个钱包");
            }
        }
        return new MyMessage(0, "钱包不存在");
    }

    @Override
    public Object updateWallet(int userId, double balance) {
        List<Wallet> wallets = walletDao.findWalletByUserId(userId);
        if (wallets == null) {
            return new MyMessage(0, "更新失败，钱包不存在");
        }
        if (wallets.size() != 1) {
            return new MyMessage(0,"钱包异常，此用户拥有多个钱包");
        }
        Wallet wallet = wallets.get(0);
        double oldBalance = wallet.getBalance();
        Wallet result = walletDao.UpdateWalletBalanceById(wallet.getId(), wallet.getBalance() + balance);
        if (result.getBalance() - balance == oldBalance) {
            return new MyMessage(1, "钱包金额更新成功");
        } else {
            return new MyMessage(0, "钱包金额更新出错");
        }
    }
}
