package com.ftme.util;
/**
 * 应用策略模式，方便以后扩展短信发送功能
 * @title SendSMSUtil
 * @description 阿里云短信发送util
 * @author RenYY
 */
public class SendSMSUtil {
    /**
     * @description 短信发送接口
     * @param sendSMS
     * @return
     * @author RenYY
     * @date 2016年12月29日
     */
    public static boolean sendSMS(ISendSMS sendSMS){
        return sendSMS.sendSMS();
    }

}