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
        String notice_url="/wechatJsPay/notice";

    }

    public interface APP {

        /**
         * 商户号
         */
        String mch_id="151505453421";

        /**
         * 秘钥
         */
        String app_key="sB5ms2Y3mNserwdsfdgwer2Ax12sU";

        /**
         * 应用id
         */
        String app_id="wxe99d4bdaasdzf4ce53f";

        /**
         * 回调地址
         */
        String notice_url="/wechatAppPay/notice";

    }
}
