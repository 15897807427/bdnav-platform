package com.bdxh.wallet.service;

import com.bdxh.common.web.support.IService;
import com.bdxh.wallet.entity.WalletAccountRecharge;
import org.apache.ibatis.annotations.Param;

/**
 * @description:
 * @author: xuyuan
 * @create: 2018-12-30 19:18
 **/
public interface WalletAccountRechargeService extends IService<WalletAccountRecharge> {
    /**
     * 根据订单号查询充值记录
     * @param orderNo
     * @return
     */
    WalletAccountRecharge getByOrderNO(Long orderNo);
    /**
     * 根据订单号更改充值记录
     * @return
     */
    int  getChangeRechargeecords(WalletAccountRecharge walletAccountRecharge);
}
