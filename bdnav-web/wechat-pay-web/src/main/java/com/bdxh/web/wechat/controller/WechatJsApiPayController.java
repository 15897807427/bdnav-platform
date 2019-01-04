package com.bdxh.web.wechat.controller;

import com.bdxh.common.base.constant.WechatPayConstants;
import com.bdxh.common.utils.ObjectUtil;
import com.bdxh.common.utils.WxPayUtil;
import com.bdxh.common.wechatpay.app.domain.AppOrderResponse;
import com.bdxh.wallet.feign.WalletControllerClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 微信JSAPI支付控制器
 * @author: LiTao
 * @create: 2019-01-02 18:37
 **/
@Controller
@RequestMapping("/wechatJsPay")
@Slf4j
public class WechatJsApiPayController {

    @Autowired
    private WalletControllerClient walletControllerClient;

    @RequestMapping("/order")
    @ResponseBody
    public Object wechatJsPayOrder(HttpServletRequest request) throws Exception {

        Map<String, String> paraMap = new HashMap<String, String>();
        //获取参数
        String ip = request.getParameter("ip");
        String openid = request.getParameter("openid");
        String body = request.getParameter("body");
        String amount = request.getParameter("amount");
        //下单
        //walletControllerClient.addRechargeLog(new Long(1537), new BigDecimal(123));
        try {
            //公众账号id
            paraMap.put("appid", WechatPayConstants.JS.app_id);
            //商品描述
            paraMap.put("body", body);
            //商户号
            paraMap.put("mch_id", WechatPayConstants.JS.mch_id);
            //32位随机字符串
            paraMap.put("nonce_str", ObjectUtil.generateNonceStr());
            // 此路径是微信服务器调用支付结果通知路
            paraMap.put("notify_url", WechatPayConstants.JS.notice_url);
            // 订单号
            paraMap.put("out_trade_no", WxPayUtil.getOrderNo());
            //终端id
            paraMap.put("spbill_create_ip", ip);
            String sumfigure = WxPayUtil.getMoney(amount);
            //金额
            paraMap.put("total_fee", sumfigure);
            //支付场景
            paraMap.put("trade_type", WechatPayConstants.JS.trade_type);
            //微信用户唯一标识
            paraMap.put("openid", openid);
            //
            String sign = WxPayUtil.generateSignature(paraMap, WechatPayConstants.JS.app_key);
            paraMap.put("sign", sign);
//            String xml = mapToXml(paraMap);// 将所有参数(map)转xml格式 // 统一下单
            String unifiedorder_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }



    @RequestMapping("/notice")
    public Object wechatAppPayNotice(@RequestBody AppOrderResponse appOrderResponse){

        return null;
    }

    @RequestMapping("/noticet")
    @ResponseBody
    public Object wechatAppPayNoticet(@RequestBody AppOrderResponse appOrderResponse){
        //walletControllerClient.addRechargeLog(new Long(1537),new BigDecimal(123));
        return "sssss";
    }

}
