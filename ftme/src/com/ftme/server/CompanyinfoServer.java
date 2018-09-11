package com.ftme.server;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
/**
 * 公司简介业务层
 * @author wyl
 *
 */
public class CompanyinfoServer {
	/**
	 * 获取公司简介
	 * @return
	 */
	public Record companyinfoFindFirst() {
			return Db.findFirst("select * from companyinfo order by id desc");
	}
	
	/**
	 * 添加公司简介
	 * @param companyinfo  添加的内容
	 * @return
	 */
	public boolean companyinfoSave(Record companyinfo) {
		return Db.save("companyinfo", companyinfo);
	}
	
	/**
	 * 修改公司简介
	 * @param companyinfo 内容
	 * @return
	 */
	public boolean companyinfoUpdate(Record companyinfo) {
		return Db.update("companyinfo", companyinfo);
	}

	
}
