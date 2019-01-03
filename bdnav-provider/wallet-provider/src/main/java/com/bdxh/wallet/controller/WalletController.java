package com.bdxh.wallet.controller;

import com.bdxh.common.utils.SnowflakeIdWorker;
import com.bdxh.common.utils.wrapper.WrapMapper;
import com.bdxh.wallet.entity.WalletAccountRecharge;
import com.bdxh.wallet.service.WalletAccountRechargeService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.math.BigDecimal;

@Controller
@RequestMapping("/wallet")
@Slf4j
public class WalletController {

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    private WalletAccountRechargeService walletAccountRechargeService;

    /**
     * 添加用户充值记录
     * @param userId
     * @param amount
     * @return
     */
    @RequestMapping("/addRechargeLog")
    @ResponseBody
    public Object addRechargeLog(@RequestParam(name="userId") Long userId,@RequestParam(name="amount") BigDecimal amount,
                                @RequestParam("orderType") String orderType){
        try {
            Preconditions.checkArgument(userId!=null,"用户信息不能为空");
            Preconditions.checkArgument(amount!=null&&amount.doubleValue()>0,"金额输入不正确");
            Preconditions.checkArgument(StringUtils.isNotEmpty(orderType),"订单类型不能为空");
            WalletAccountRecharge recharge=new WalletAccountRecharge();
            recharge.setUserId(userId);
            Long orderNo = snowflakeIdWorker.nextId();
            recharge.setOrderNo(orderNo);
            recharge.setRechargeMoney(amount);
            recharge.setStatus(Byte.valueOf("0"));
            recharge.setOrderType(orderType);
            //调用service
            walletAccountRechargeService.save(recharge);
            return WrapMapper.ok(orderNo);
        }catch (Exception e){
            log.error(e.getMessage());
            return WrapMapper.error(e.getMessage());
        }

    }

    /**
     *更改用户充值记录状态
     * @param orderNo
     * @return
     */
    @RequestMapping("/changeRechargeLog")
    @ResponseBody
    public Object changeRechargeLog(@RequestParam(name="orderNo") Long orderNo,@RequestParam(name="status") Byte status){
        try {
            Preconditions.checkArgument(orderNo!=null,"订单号不能为空");
            Preconditions.checkArgument(status!=null,"充值状态不能为空");
            //调用service
            WalletAccountRecharge walletAccountRecharge=walletAccountRechargeService.getByOrderNO(orderNo);
            Preconditions.checkNotNull(walletAccountRecharge,"订单信息不存在");
            walletAccountRecharge.setStatus(Byte.valueOf("status"));
            walletAccountRechargeService.update(walletAccountRecharge);
            return WrapMapper.ok();
        }catch (Exception e){
            log.error(e.getMessage());
            return WrapMapper.error(e.getMessage());
        }
    }


    /**
     * 用户回调时更改用户充值记录状态及第三方支付号
     * @param orderNo
     * @return
     */
    @RequestMapping("/changeRechargeLog")
    @ResponseBody
    public Object changeRechargeLog(@RequestParam(name="orderNo") Long orderNo,@RequestParam(name="status") Byte status,
                                    @RequestParam(name="thirdOrderNo") String thirdOrderNo){
        try {
            Preconditions.checkArgument(orderNo!=null,"订单号不能为空");
            Preconditions.checkArgument(status!=null,"充值状态不能为空");
            Preconditions.checkArgument(StringUtils.isNoneEmpty(thirdOrderNo),"第三方支付账单号不能为空");
            //调用service
            WalletAccountRecharge walletAccountRecharge=walletAccountRechargeService.getByOrderNO(orderNo);
            Preconditions.checkNotNull(walletAccountRecharge,"订单信息不存在");
            walletAccountRecharge.setStatus(Byte.valueOf("status"));
            walletAccountRecharge.setThirdOrderNo(thirdOrderNo);
            walletAccountRechargeService.getChangeRechargeecords(walletAccountRecharge);
            return WrapMapper.ok();
        }catch (Exception e){
            log.error(e.getMessage());
            return WrapMapper.error(e.getMessage());
        }
    }

}

