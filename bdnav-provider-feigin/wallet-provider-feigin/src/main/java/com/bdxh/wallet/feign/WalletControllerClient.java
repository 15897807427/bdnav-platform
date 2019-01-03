package com.bdxh.wallet.feign;

import com.bdxh.common.utils.wrapper.Wrapper;
import com.bdxh.wallet.fallback.WalletControllerFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

/**
 * @description: feign客户端
 * @author: xuyuan
 * @create: 2018-12-27 17:18
 **/
@Service
@FeignClient(value = "wallet-provider-cluster",fallback= WalletControllerFallback.class)
@RequestMapping("/changeRechargeLog")
public interface WalletControllerClient {

    /**
     * 添加用户充值记录
     *
     * @param userId
     * @param amount
     * @return
     */
    @RequestMapping("/addRechargeLog")
    @ResponseBody
    Wrapper addRechargeLog(@RequestParam(name = "userId") Long userId, @RequestParam(name = "amount") BigDecimal amount);

    /**
     * 更改用户充值记录状态
     *
     * @param orderNo
     * @return
     */
    @RequestMapping("/changeRechargeLog")
    @ResponseBody
    Wrapper changeRechargeLog(@RequestParam(name = "orderNo") Long orderNo, @RequestParam(name = "status") Byte status);

}