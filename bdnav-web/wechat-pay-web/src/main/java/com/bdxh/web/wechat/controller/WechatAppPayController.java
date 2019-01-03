package com.bdxh.web.wechat.controller;

import com.bdxh.common.base.constant.WechatPayConstants;
import com.bdxh.common.utils.ObjectUtil;
import com.bdxh.common.utils.wrapper.Wrapper;
import com.bdxh.common.wechatpay.app.domain.AppOrderRequest;
import com.bdxh.common.wechatpay.app.domain.AppOrderResponse;
import com.bdxh.wallet.feign.WalletControllerClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

/**
 * @description: 微信APP支付控制器
 * @author: xuyuan
 * @create: 2019-01-02 18:37
 **/
@Controller
@RequestMapping("/wechatAppPay")
@Slf4j
public class WechatAppPayController {

    @Autowired
    private WalletControllerClient walletControllerClient;

    @RequestMapping("/order")
    @ResponseBody
    public Object wechatAppPayOrder(){

        //获取参数

        //下单
        walletControllerClient.addRechargeLog(new Long(1537),new BigDecimal(123));

        AppOrderRequest appOrderRequest=new AppOrderRequest();
        //应用id
        appOrderRequest.setAppid(WechatPayConstants.APP.app_id);
        //商户号
        appOrderRequest.setMch_id(WechatPayConstants.APP.mch_id);
        //随机字符串，使用uuid
        appOrderRequest.setNonce_str(ObjectUtil.getUuid());
        //签名
        appOrderRequest.setSign("");
        //商品描述
        appOrderRequest.setBody("");
        //订单号
        appOrderRequest.setOut_trade_no("");
        //总金额
        appOrderRequest.setTotal_fee("");
        //终端ip
        appOrderRequest.setSpbill_create_ip("");
        //通知地址
        appOrderRequest.setNotify_url(WechatPayConstants.APP.notice_url);
        return null;
    }



    @RequestMapping("/notice")
    public Object wechatAppPayNotice(@RequestBody AppOrderResponse appOrderResponse){

        return null;
    }

    @RequestMapping("/noticet")
    @ResponseBody
    public Object wechatAppPayNoticet(@RequestBody AppOrderResponse appOrderResponse){
        walletControllerClient.addRechargeLog(new Long(1537),new BigDecimal(123));
        return "sssss";
    }

}
