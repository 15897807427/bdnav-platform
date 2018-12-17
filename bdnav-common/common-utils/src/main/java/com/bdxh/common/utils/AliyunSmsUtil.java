package com.bdxh.common.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.bdxh.common.base.constant.AliyunSmsConstants;
import com.google.common.base.Preconditions;
import com.xiaoleilu.hutool.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import java.util.Map;

/**
 * @description: 阿里短信发送工具类
 * @author: xuyuan
 * @create: 2018-12-17 14:57
 **/
public class AliyunSmsUtil {

    public static void sendAliyun(String busType, Map<String, String> param) throws ClientException {
        Preconditions.checkArgument(StringUtils.isNotEmpty(busType), "短信类型不能为空");
        AliyunSmsConstants.SmsTempletEnum smsTempletEnum = AliyunSmsConstants.SmsTempletEnum.getEnumByBusType(busType);
        Preconditions.checkNotNull(smsTempletEnum,"短信类型未配置");
        System.setProperty("sun.net.client.defaultConnectTimeout", AliyunSmsConstants.aliyunSms.connectTimeout);
        System.setProperty("sun.net.client.defaultReadTimeout", AliyunSmsConstants.aliyunSms.readTimeout);
        IClientProfile profile = DefaultProfile.getProfile(AliyunSmsConstants.aliyunSms.region, AliyunSmsConstants.aliyunSms.accessKeyId, AliyunSmsConstants.aliyunSms.accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", AliyunSmsConstants.aliyunSms.region, AliyunSmsConstants.aliyunSms.product, AliyunSmsConstants.aliyunSms.domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();
        String outId = param.get("outId");
        if (StringUtils.isNotEmpty(outId)) {
            request.setOutId(outId);
        }
        String smsUpExtendCode	 = param.get("smsUpExtendCode");
        if (StringUtils.isNotEmpty(smsUpExtendCode)) {
            request.setSmsUpExtendCode(smsUpExtendCode);
        }
        //组装参数
        JSONObject jsonObject=new JSONObject();
        String smsParamName = smsTempletEnum.getSmsParamName();
        if (StringUtils.isNotEmpty(smsParamName)){
            String[] smsParamNameArray = smsParamName.split(",");
            for(int i=0;i<smsParamNameArray.length;i++){
                jsonObject.put(smsParamNameArray[i],param.get(smsParamNameArray[i]));
            }
        }
        String smsParamString=jsonObject.toString();
        //设置短信内容
        request.setTemplateParam(smsParamString);
        //设置模板code
        request.setTemplateCode(smsTempletEnum.getTempletCode());
        //设置签名
        request.setSignName(smsTempletEnum.getSignName());
        //设置手机号
        request.setPhoneNumbers(param.get("phone"));
        //发送短信
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        //获取返回结果
        Preconditions.checkArgument(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK"));
    }

/*    public static void sendTaobao(String busType, Map<String, String> param) throws ApiException {
        Preconditions.checkArgument(StringUtils.isNotEmpty(busType), "短信类型不能为空");
        AliyunSmsConstants.SmsTempletEnum smsTempletEnum = AliyunSmsConstants.SmsTempletEnum.getEnumByBusType(busType);
        Preconditions.checkNotNull(smsTempletEnum,"短信类型未配置");
        TaobaoClient client = new DefaultTaobaoClient(AliyunSmsConstants.taobaoSms.domain, AliyunSmsConstants.taobaoSms.accessKeyId,
                AliyunSmsConstants.taobaoSms.accessKeySecret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        String extend = param.get("extend");
        if (StringUtils.isNotEmpty(extend)) {
            req.setExtend(extend);
        } else {
            req.setExtend("123456");
        }
        String smsType = param.get("smsType");
        if (StringUtils.isNotEmpty(smsType)) {
            req.setSmsType(smsType);
        } else {
            req.setSmsType("normal");
        }
        req.setRecNum(param.get("phone"));
        //组装参数
        JSONObject jsonObject=new JSONObject();
        String smsParamName = smsTempletEnum.getSmsParamName();
        if (StringUtils.isNotEmpty(smsParamName)){
            String[] smsParamNameArray = smsParamName.split(",");
            for(int i=0;i<smsParamNameArray.length;i++){
                jsonObject.put(smsParamNameArray[i],param.get(smsParamNameArray[i]));
            }
        }
        String smsParamString=jsonObject.toString();
        //设置短信内容
        req.setSmsParamString(smsParamString);
        //设置模板code
        req.setSmsTemplateCode(smsTempletEnum.getTempletCode());
        //设置签名
        req.setSmsFreeSignName(smsTempletEnum.getSignName());
        //发送短信
        client.execute(req);
    }

    public static void main1(String[] args) throws ApiException {
        Map<String,String> param=new HashMap<>();
        param.put("phone","17688937892");
        param.put("code","123456");
        AliyunSmsUtil.sendTaobao(AliyunSmsConstants.SmsTempletEnum.TEMPLATE_博学派.getBusType(),param);
    }

    public static void main(String[] args) throws ApiException {
        Map<String,String> param=new HashMap<>();
        param.put("phone","17688937892");
        param.put("code","123456");
        param.put("product","微校钱包");
        AliyunSmsUtil.sendTaobao(AliyunSmsConstants.SmsTempletEnum.TEMPLATE_身份验证.getBusType(),param);
    }*/

}
