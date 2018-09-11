package com.ftme.server;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
/**
 * 医院业务层
 * @author wyl
 *
 */
public class HospitalServer {
	/**
	 * 获取对应状态的的医院信息
	 * @param hospitalState 医院状态:0可用，1不可以用
	 * @return
	 */
	public List<Record> hospitalFind(Integer hospitalState,Record fy) {
//		//System.out.println("hospitalState   "+hospitalState);
		if(fy!=null){
			return Db.find("select * from hospital where hospitalState=?  order by id desc limit  " + fy.getInt("begins") + "," + fy.getInt("amount"),hospitalState);
		}else{
			return Db.find("select * from hospital where hospitalState=? order by id desc ",hospitalState);
		}
	}
	
	/**
	 * 获取查询的医院信息
	 * @param hospital
	 * @param fy
	 * @return
	 */
	public List<Record> hospitalFindSel(String condition, Record fy) {
		if (fy != null) {
			return Db.find("select * from hospital where hospitalState=0  "+condition+" order by id desc limit " + fy.getInt("begins") + "," + fy.getInt("amount"));
		} else {
			return Db.find("select * from hospital where hospitalState=0  "+condition+" order by id desc ");
		}
	}
	
	/**
	 *  获取一个医院的详细信息
	 * @param hospitalNo医院编号   
	 * @return
	 */
	public Record hospitalById(String hospitalNo) {
		List<Record> list = Db.find("select * from hospital where hospitalNo=? ",
				hospitalNo);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 添加医院信息
	 * @param hospital
	 *            添加的内容
	 * @return
	 */
	public boolean hospitalSave(Record hospital) {
		return Db.save("hospital", hospital);
	}
	
	/**
	 * 修改医院信息
	 * @param hospital	
	 * @return
	 */
	public boolean hospitalUpdate(Record hospital) {
		return Db.update("hospital", hospital);
	}

	/**
	 * 删除医院信息（0可用，1不可以用）
	 * @param hospital
	 * @return
	 */
	public boolean hospitalDelete(Record hospital) {
		return Db.update("hospital", hospital);
	}
	/**
	 * 验证医院编号是否唯一
	 * 
	 * @param hospitalNo
	 * @return
	 */
	public List<Record> ajaxhospitalNo(String hospitalNo) {
		List<Record> list = Db.find("select * from hospital where hospitalNo=?",
				hospitalNo);
		return list;
	}
	/**
	 * 验证医院名称是否唯一
	 * 
	 * @param hospitalNo
	 * @return
	 */
	public List<Record>  ajaxhospitalname(String hospitalname) {
		List<Record> list = Db.find("select * from hospital where hospitalname=?",
				hospitalname);
//		if (list.size() > 0) {
//			return list.get(0);
//		} else {
//			return null;
//		}
		return list;
	}
}
