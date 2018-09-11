package com.ftme.server;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
/**
 * 检测结果业务层
 * @author wyl
 *
 */
public class ClinicalServer {
	/**
	 * 获取对应状态的的临床诊断
	 * @param clinicalState 临床诊断状态:0可用，1不可以用
	 * @return
	 */
	public List<Record> clinicalFind(Integer clinicalState,Record fy) {
//		//System.out.println("clinicalState   "+clinicalState);
		if(fy!=null){
			return Db.find("select * from clinical where clinicalState=?  order by id desc limit  " + fy.getInt("begins") + "," + fy.getInt("amount"),clinicalState);
		}else{
			return Db.find("select * from clinical where clinicalState=? order by id desc ",clinicalState);
		}
	}
	
	/**
	 * 获取查询的临床诊断
	 * @param clinical
	 * @param fy
	 * @return
	 */
	public List<Record> clinicalFindSel(String condition, Record fy) {
		if (fy != null) {
			return Db.find("select * from clinical where clinicalState=0  "+condition+" order by id desc limit " + fy.getInt("begins") + "," + fy.getInt("amount"));
		} else {
			return Db.find("select * from clinical where clinicalState=0  "+condition+" order by id desc ");
		}
	}
	
	/**
	 *  获取一个临床诊断的详细信息
	 * @param clinicalNo临床诊断编号   
	 * @return
	 */
	public Record clinicalById(String clinicalNo) {
		List<Record> list = Db.find("select * from clinical where clinicalNo=? ",
				clinicalNo);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 添加临床诊断
	 * @param clinical
	 *            添加的内容
	 * @return
	 */
	public boolean clinicalSave(Record clinical) {
		return Db.save("clinical", clinical);
	}
	
	/**
	 * 修改临床诊断
	 * @param clinical	
	 * @return
	 */
	public boolean clinicalUpdate(Record clinical) {
		return Db.update("clinical", clinical);
	}

	/**
	 * 删除临床诊断（0可用，1不可以用）
	 * @param clinical
	 * @return
	 */
	public boolean clinicalDelete(Record clinical) {
		return Db.update("clinical", clinical);
	}
	/**
	 * 验证临床诊断名称是否唯一
	 * 
	 * @param clinicalNo
	 * @return
	 */
	public List<Record>  ajaxclinicalname(String clinicalname) {
		List<Record> list = Db.find("select * from clinical where clinicalname=?",
				clinicalname);
//		if (list.size() > 0) {
//			return list.get(0);
//		} else {
//			return null;
//		}
		return list;
	}
}
