package com.ftme.server;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
/**
 * 短信服务业务层
 * @author wyl
 *
 */
public class SmsrecordServer {
	/**
	 * 添加短信服务记录，记录短信是否成功
	 * @param smsrecord 添加的内容
	 * @return
	 */
	public boolean smsrecordSave(Record smsrecord) {
		return Db.save("smsrecord", smsrecord);
	}
}
