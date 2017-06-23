package com.five.payment.service;

import com.five.order.model.Reservation;
import com.five.order.service.OrderService;
import com.five.order.utils.ClockThread;
import com.five.order.utils.ClockThreadPool;
import com.five.payment.dao.WalletDao;
import com.five.payment.model.Wallet;
import com.five.user.model.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Object payOrder(int userId, int orderId) {
        ClockThread clockThread = ClockThreadPool.getInstance().get(orderId);
        if (clockThread == null) return new MyMessage(0, "订单已过期");
        Reservation reservation = orderService.findById(orderId);
        if (reservation.getUserId() != userId) {
            return new MyMessage(0,"你不是此订单持有者");
        }
        Wallet wallet = walletDao.findWalletByUserId(reservation.getUserId());
        if (wallet == null) {
            return new MyMessage(0, "支付失败，数据库无记录");
        }
        if (wallet.getId() == -1) {
            return new MyMessage(0,"钱包异常，此用户拥有多个钱包");
        }
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
        walletDao.refreshOne(userId);
        Wallet wallet = findByUserId(userId);
        if (wallet != null) {
            if (wallet.getUserId() != -1) {
                return new MyMessage(0, "钱包已存在");
            } else {
                return new MyMessage(0, "钱包异常，此用户拥有多个钱包");
            }
        } else {
            Wallet wallet1 = walletDao.addWalletForNewUserById(userId, balance);
            if (wallet1 != null)
                return new MyMessage(1, "钱包创建成功");
            else
                return new MyMessage(0, "钱包创建失败");
        }
    }

    @Override
    public Wallet findByUserId(int userId) {
        Object obj = walletDao.findWalletByUserId(userId);
        Wallet wallet = walletDao.findWalletByUserId(userId);
        if (wallet == null) return null;
        else if (wallet.getId() == -1) {
            //Todo
            // if wallet duplicate?
            return new Wallet(-1, 0);

        } else return wallet;
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
//        List<Wallet> wallets = walletDao.findWalletByUserId(userId);
        Wallet wallet = findByUserId(userId);
        if (wallet == null) {
            return new MyMessage(0, "更新失败，钱包不存在");
        }
        if (wallet.getId() == -1) {
            return new MyMessage(0,"钱包异常，此用户拥有多个钱包");
        }
        double oldBalance = wallet.getBalance();
        Wallet result = walletDao.UpdateWalletBalanceById(wallet.getId(), wallet.getBalance() + balance);
        if (result.getBalance() - balance == oldBalance) {
            return new MyMessage(1, "钱包金额更新成功");
        } else {
            return new MyMessage(0, "钱包金额更新出错");
        }
    }

    @Override
    public void reload() {
        walletDao.reload();
    }
}
