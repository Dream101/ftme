package com.ftme.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.ftme.common.model.Smsrecord;
import com.ftme.server.SendSMS;
import com.ftme.server.SmsrecordServer;
import com.jfinal.plugin.activerecord.Record;


public class WylUtil {
	
	public Integer up=null;//上一页
	public Integer current=null;//当前页
	public Integer next=null;//下一页
	public Integer allpages=null;//总页数
	public Integer count=null;//总页数
	public Integer begins=null;//开始行数
	public Integer amount=15;//每页的行数
	/**
	 * 计算分页信息
	 * @param count	信息总条数
	 * @param curr	当前页
	 * @return
	 */
	public Record fenyi(Integer count,Integer curr){
		////System.out.println(count % this.amount == 0);
		this.allpages=(count % this.amount == 0 ? count / this.amount : count / this.amount + 1);
		this.up=(curr <= 1 ? 1 : curr - 1);
		this.next=(curr >= this.allpages ? this.allpages  : curr + 1);
		this.current=(curr);
		this.count=count;
		this.begins=((curr-1)*this.amount);
		
		Record fy=new Record();
		fy.set("up",this.up );
		fy.set("current",this.current );
		fy.set("next",this.next );
		fy.set("allpages",this.allpages );
		fy.set("count",this.count );
		fy.set("begins",this.begins );
		fy.set("amount",this.amount );
		return fy;
	}
	
	/**
	 * 行业动态分页信息
	 * @param count	信息总条数
	 * @param curr	当前页
	 * @return
	 */
	public Record blogFY(Integer count,String begin){
		Integer curr=1;
		if(begin!=null){
			curr=new Integer(begin);
		}
		int a=3;
		////System.out.println(count % a == 0);
		this.allpages=(count % a == 0 ? count / a : count / a + 1);
		this.up=(curr <= 1 ? 1 : curr - 1);
		this.next=(curr >= this.allpages ? this.allpages  : curr + 1);
		this.current=(curr);
		this.count=count;
		this.begins=((curr-1)*a);
		
		Record fy=new Record();
		fy.set("up",this.up );
		fy.set("current",this.current );
		fy.set("next",this.next );
		fy.set("allpages",this.allpages );
		fy.set("count",this.count );
		fy.set("begins",this.begins );
		fy.set("amount",a );
		return fy;
	}
	
//	/**
//	 * 分页
//	 * @param List	数据
//	 * @return
//	 */
//	public List zengpin(List list){
//		List ar=new ArrayList();
//		
//		for (; begin < pages; begin++) {
//			Object pr=(Object) list.get(begin);
//			ar.add(pr);
//		}
//		return ar;
//	}
	/**
	 * 为图片赋予新的名字
	 */
	public String getPath(String name) {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		Random ran = new Random();
		String str = df.format(date) + ran.nextInt(10000)
				+ name.substring(name.lastIndexOf("."));
		return str;
	}
	
	/**
	 * 公司人员编号
	 */
	public String getNO() {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		Random ran = new Random();
		String str = df.format(date) + ran.nextInt(1000);
		return str;
	}
	/**
	 * 医生患者人员编号
	 * @param hospitalNo 医院编号
	 * @return
	 */
	public String getPatientNOOrDoctorNO(String hospitalNo) {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		String str =hospitalNo+"_"+ df.format(date);
		return str;
	}
	
//	public static void main(String[] args) {
//		////System.out.println(new WylUtil().getPatientNOOrDoctorNO("tr"));
//	}
	
	/**
	 * 验证修改的时候是否是自己在进行修改
	 */
	public static boolean ck(List<Record> list,Integer id){
		boolean falg=false;
		if (list.size() > 1) {
			falg= false;
		} else if(list.size()==1){
			Record u=(Record) list.get(0);
			if(id!=-1&&u!=null){//修改时判断，（当账号存在是判断是否是其本身的）
				if(u.get("id").equals(id)){
					falg= true;
				}
			}else{
				falg=false;
			}
//			if(id==-1&&u==null){//添加时判断
//				falg= true;
//			}else if(id!=-1&&u!=null){//修改时判断，（当账号存在是判断是否是其本身的）
//				if(u.get("id").equals(id)){
//					falg= true;
//				}
//			}else if(id!=-1&&u==null){//修改时判断，当账号不存在通过
//				falg=true;
//			}
		}else{
			falg=true;
		}
		return falg;
	}
	/**
	 * 发送通知短信的公共方法
	 * @param user			需要的数据
	 * @param templateId	短信模板 1:找回密码CODE, 2:对患者进行通知CODE, 3:检测报告通知CODE
	 */
	 public Record sendSMS(Record user,int templateId) {
//	    	signName 如果signName为null或者空串，则取集团短信签名; 否则取signName值
//	    	templateId  1:找回密码CODE, 2:对患者进行通知CODE, 3:检测报告通知CODE
//	    	paramMap 短信模板中参数名为key,参数值为value
//	    	phoneNum 手机号数组,例：{"18888888888","19999999999"}
//	    	Map<String,String> map = new HashMap<String,String>();
//	    	map.put("name", "王义龙");
//	    	map.put("password", "a123456");
//	    	尊敬的${name},您好！您的登录账号为：${username},初始密码为：${password},详细信息请关注www.chinagene.cc
//	    	尊敬的${name},您好！您的基因检测报告已经完成,您的快递单号：${courierInfo}，请使用您的账号：${username},登录www.chinagene.cc查看报告的详细信息
	    	Record re=new Record();
	    	re.set("name", user.getStr("name"));
	    	re.set("username", user.getStr("username"));
	    	re.set("courierInfo", user.getStr("courierInfo")==null?"":user.getStr("courierInfo"));
	    	re.set("password", user.getStr("password"));
	    	String [] str={user.getStr("phone")};
	    	////System.out.println(re.toJson());
	    	////System.out.println(str);
	    	SendSMS s=new SendSMS("中因科技", templateId, re, str);
	    	
	    	boolean falg=s.sendSMS();
//	      id       | INT(10)     | NO   | PRI |         | ID      
//	      smsNo    | VARCHAR(30) | YES  |     |         | 短信状态编号  
//	      userNo   | VARCHAR(30) | YES  |     |         | 发送对象的编号 
//	      name     | VARCHAR(30) | YES  |     |         | 用户名称    
//	      phone    | VARCHAR(30) | YES  |     |         | 手机号     
//	      smsType  | VARCHAR(2)  | YES  |     |         | 1:找回密码CODE, 2:对患者进行通知CODE, 3:检测报告通知CODE
//	      smsState | VARCHAR(2)  | YES  |     |         | 短信发送状态  
//	      state    | VARCHAR(30) | YES  |     |         | 备用字段 
	    	
	    	////System.out.println("发送通知短信的公共方法"+falg);
	    	Smsrecord sms=new Smsrecord();
	    	sms.setSmsNo(new WylUtil().getNO());
	    	sms.setUserNo(user.getStr("userNo"));
	    	sms.setName(user.getStr("name"));
	    	sms.setPhone(user.getStr("phone"));
	    	sms.setSmsType(""+templateId);
	    	sms.setSmsState(falg+"");
	    	////System.out.println("发送通知短信的公共方法"+sms.toJson());
	    	
	    	return sms.toRecord();
		}
	
}
