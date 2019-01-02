package com.bdxh.web.wechat.controller;

import com.bdxh.common.base.constant.WechatPayConstants;
import com.bdxh.common.utils.ObjectUtil;
import com.bdxh.common.wechatpay.app.domain.AppOrderRequest;
import com.bdxh.common.wechatpay.app.domain.AppOrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 微信APP支付控制器
 * @author: xuyuan
 * @create: 2019-01-02 18:37
 **/
@Controller
@RequestMapping("/wechatAppPay")
@Slf4j
public class WechatAppPayController {

    @RequestMapping("/order")
    @ResponseBody
    public Object wechatAppPayOrder(){

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

}