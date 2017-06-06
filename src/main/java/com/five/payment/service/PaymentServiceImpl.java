package com.five.payment.service;

import com.five.order.dao.OrderDao;
import com.five.order.model.Reservation;
import com.five.order.service.OrderService;
import com.five.order.utils.ClockThread;
import com.five.order.utils.ThreadPool;
import com.five.payment.dao.WalletDao;
import com.five.payment.model.Wallet;
import com.five.user.model.MyMessage;
import org.aspectj.weaver.ast.Or;
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
    public MyMessage payOrder(int orderId) {
        ClockThread clockThread = ThreadPool.getInstance().get(orderId);
        if (clockThread == null) return new MyMessage(0, "订单已过期");
        Reservation reservation = orderService.findById(orderId);
        List<Wallet> wallets = walletDao.findWalletByUserId(reservation.getUserId());
        if (wallets == null) {
            return new MyMessage(0, "支付失败，数据库无记录");
        }
        if (wallets.size() != 1) {
            return new MyMessage(0,"钱包异常，同时被多人拥有");
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
}
