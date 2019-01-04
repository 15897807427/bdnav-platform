package com.bdxh.common.base.constant;

/**
 * @description: 微信支付常量
 * @author: xuyuan
 * @create: 2019-01-02 17:57
 **/
public class WechatPayConstants {

    public interface JS {
        /**
         * 商户号
         */
        String mch_id="14067323432";

        /**
         * 秘钥
         */
        String app_key="8HVBmi7D6sadadaYT4aCz4Oc1";

        /**
         * 应用id
         */
        String app_id="wxsadad1684f864e4";

        /**
         * 回调地址
         */
        String notice_url="http://wxy.bdxht.com/wechatJsPay/notice/";

        /**
         * 支付类型
         */
         String trade_type="JSAPI";

        /**
         * 统一下单接口url
         */
        String order_url="";

    }

    public interface APP {

        /**
         * 商户号
         */
        String mch_id="1515043424243731";

        /**
         * 秘钥
         */
        String app_key="sB5ms2Y54543mNs5QsahJ54xdsk58Isdf61Dq2Ax12sU";

        /**
         * 应用id
         */
        String app_id="wxefsdf99d4bdsffsfdsfsf57fsdf4ce53f";

        /**
         * 回调地址
         */
        String notice_url="http://localhost:9011/wechatAppPay/notice";

        /**
         * 支付类型
         */
        String trade_type="APP";

        /**
         * 统一下单接口url
         */
        String order_url="https://api.mch.weixin.qq.com/pay/unifiedorder\n";

    }
}
