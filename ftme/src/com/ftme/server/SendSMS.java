package com.ftme.server;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ftme.common.model.Smsrecord;
import com.ftme.util.DictSMS;
import com.ftme.util.ISendSMS;
import com.ftme.util.LoadProperties;
import com.jfinal.kit.HttpKit;
import com.jfinal.plugin.activerecord.Record;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @title SendSMS
 * @description 短信发送实现类，完成短信发送动作
 * @author RenYY
 */
public class SendSMS implements ISendSMS {

    private static String aliyun_sms_region_id = "";    //阿里云regionId
    private static String aliyun_sms_access_key_id = "";    //阿里云 accessKeyId
    private static String aliyun_sms_access_key_secret = "";    //阿里云 accessKeySecret
    private static String aliyun_sms_end_point_name = "";   //阿里云end_point_name
    private static String aliyun_sms_product = "";  //阿里云产品
    private static String aliyun_sms_domain = "";   //阿里云调用短信服务平台接口域名

    private String signName;    //阿里云短信签名
    private String templateCode;    //阿里云短信模板Code
    private String paramString; //阿里云短信模板参数字符串
    private String recNum;  //发送短信手机号


    static{
        aliyun_sms_region_id = LoadProperties.readValue("aliyun_sms_region_id");
        aliyun_sms_access_key_id = LoadProperties.readValue("aliyun_sms_access_key_id");
        aliyun_sms_access_key_secret = LoadProperties.readValue("aliyun_sms_access_key_secret");
        aliyun_sms_end_point_name = LoadProperties.readValue("aliyun_sms_end_point_name");
        aliyun_sms_product = LoadProperties.readValue("aliyun_sms_product");
        aliyun_sms_domain = LoadProperties.readValue("aliyun_sms_domain");
    }
    /**
     * @description 方法描述
     * @param signName  如果signName为null或者空串，则取集团短信签名; 否则取signName值
     * @param templateId    1:找回密码CODE, 2:对患者进行通知CODE, 3:检测报告通知CODE
     * @param paramMap  短信模板中参数名为key,参数值为value
     * @param phoneNum  手机号数组,例：{"18888888888","19999999999"}
     * @author RenYY
     * @date 2016年12月29日
     */
    public SendSMS(String signName, int templateId,Record param, String[] phoneNum) {
        super();
        //短信签名: 如果signName为null或者空串，则取集团短信签名; 否则去signName值
        this.signName = (null == signName || "".equals(signName)) ? DictSMS.SIGN_NAME_PUXIN : signName;
        //短信模板
        switch (templateId) {
            case 1: this.templateCode = DictSMS.TEMPLATE_CODE_OF_INFORM; break;//找回密码CODE
            case 2: this.templateCode = DictSMS.TEMPLATE_CODE_OF_INFORM2; break;//对患者进行通知CODE
            case 3: this.templateCode = DictSMS.TEMPLATE_CODE_OF_INFORM3; break;//检测报告通知CODE
            default:this.templateCode = DictSMS.TEMPLATE_CODE_OF_PROMOTION; break;//以上三种均不匹配时，默认为  推广短信模板
        }
        //模板参数
        this.paramString = param.toJson();
        ////System.out.println(" this.paramString =="+this.paramString );
        ////System.out.println(" aliyun_sms_region_id =="+this.aliyun_sms_region_id );
        ////System.out.println(" aliyun_sms_end_point_name =="+this.aliyun_sms_end_point_name );
        ////System.out.println(" aliyun_sms_product =="+this.paramString );
        ////System.out.println(" aliyun_sms_domain =="+this.aliyun_sms_domain );
        //构造手机号字符串，多个手机号之间逗号分隔
        StringBuffer recNumSB = new StringBuffer();
        for(int i = 0; i < phoneNum.length; i++){
            if(i == phoneNum.length -1){
                recNumSB.append(phoneNum[i]);
            }else{
                recNumSB.append(phoneNum[i] + ",");
            }
        }
        this.recNum = recNumSB.toString();
    }
    /**
     * @description 发送短信
     * @return 发送成功返回 true,否则返回 false
     * @author RenYY 
     * @date 2016年12月29日
     */
    @Override
    public boolean sendSMS() {
        boolean isSendSuccess = false;
        try {
            IClientProfile profile = DefaultProfile.getProfile(aliyun_sms_region_id, aliyun_sms_access_key_id, aliyun_sms_access_key_secret);
            DefaultProfile.addEndpoint(aliyun_sms_end_point_name, aliyun_sms_region_id, aliyun_sms_product,aliyun_sms_domain);
            IAcsClient client = new DefaultAcsClient(profile);
            SingleSendSmsRequest request = new SingleSendSmsRequest();
            request.setSignName(signName);
            request.setTemplateCode(templateCode);
            request.setParamString(paramString);
            request.setRecNum(recNum);
            SingleSendSmsResponse response = client.getAcsResponse(request);//短信发送
            isSendSuccess = true;
        } catch (Exception e) {
//            log.error("*aliyun_sms_begin*");
//            log.error(e.getMessage());
//            log.error("*aliyun_sms_end*");
            e.printStackTrace();
        }finally{
            return isSendSuccess;
        }
    }
    
    public static void main(String[] args) {
//    	signName 如果signName为null或者空串，则取集团短信签名; 否则取signName值
//    	templateId  1:找回密码CODE, 2:对患者进行通知CODE, 3:检测报告通知CODE
//    	paramMap 短信模板中参数名为key,参数值为value
//    	phoneNum 手机号数组,例：{"18888888888","19999999999"}
//    	Map<String,String> map = new HashMap<String,String>();
//    	map.put("name", "王义龙");
//    	map.put("password", "a123456");
//    	尊敬的${name},您好！您的登录账号为：${username},初始密码为：${password},详细信息请关注www.chinagene.cc
//    	尊敬的${name},您好！您的基因检测报告已经完成,您的快递单号：${courierInfo}，请使用您的账号：${username},登录www.chinagene.cc查看报告的详细信息
    	Record re=new Record();
    	re.set("name", "王义龙");
    	re.set("username", "15039939677");
    	re.set("courierInfo", "天天快递：5243531513543");
    	re.set("password", "a123456");
    	String [] str={"15039939677"};
    	SendSMS s=new SendSMS("中因科技", 3, re, str);
//    	//System.out.println(s.sendSMS());
    	
	}
   
}