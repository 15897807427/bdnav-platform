package com.bdxh.wallet.persistence;

import com.bdxh.wallet.entity.WalletAccountRecharge;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface WalletAccountRechargeMapper extends Mapper<WalletAccountRecharge> {
    /**
     * 根据订单号查询充值记录
     * @param orderNo
     * @return
     */
    WalletAccountRecharge getByOrderNo(@Param("orderNo") Long orderNo);
    /**
     * 根据订单号更改充值记录
     * @return
     */
    int  getChangeRechargeecords(WalletAccountRecharge walletAccountRecharge);
}