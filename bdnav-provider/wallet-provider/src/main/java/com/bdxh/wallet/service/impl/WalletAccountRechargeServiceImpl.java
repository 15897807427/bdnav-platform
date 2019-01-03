package com.bdxh.wallet.service.impl;

import com.bdxh.common.web.support.BaseService;
import com.bdxh.wallet.entity.WalletAccountRecharge;
import com.bdxh.wallet.persistence.WalletAccountRechargeMapper;
import com.bdxh.wallet.service.WalletAccountRechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: xuyuan
 * @create: 2018-12-30 19:24
 **/
@Service
public class WalletAccountRechargeServiceImpl  extends BaseService<WalletAccountRecharge> implements WalletAccountRechargeService{

    @Autowired
    private WalletAccountRechargeMapper walletAccountRechargeMapper;


    @Override
    public WalletAccountRecharge getByOrderNO(Long orderNo) {
        WalletAccountRecharge walletAccountRecharge=walletAccountRechargeMapper.getByOrderNo(orderNo);
        return walletAccountRecharge;
    }

    @Override
    public int getChangeRechargeecords(WalletAccountRecharge walletAccountRecharge) {
        int flag= walletAccountRechargeMapper.getChangeRechargeecords(walletAccountRecharge);
        return flag;
    }

}
