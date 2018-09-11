package com.ftme.server;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
/**
 * 检测结果业务层
 * @author wyl
 *
 */
public class TestresultsServer {
	/**
	 * 获取对应状态的的基因检测结果
	 * @param testresultsState 基因检测结果状态:0可用，1不可以用
	 * @return
	 */
	public List<Record> testresultsFind(Integer testresultsState,Record fy) {
//		//System.out.println("testresultsState   "+testresultsState);
		if(fy!=null){
			return Db.find("select * from testresults where testresultsState=?  order by id desc limit  " + fy.getInt("begins") + "," + fy.getInt("amount"),testresultsState);
		}else{
			return Db.find("select * from testresults where testresultsState=? order by id desc ",testresultsState);
		}
	}
	
	/**
	 * 获取查询的基因检测结果
	 * @param testresults
	 * @param fy
	 * @return
	 */
	public List<Record> testresultsFindSel(String condition, Record fy) {
		if (fy != null) {
			return Db.find("select * from testresults where testresultsState=0  "+condition+" order by id desc limit " + fy.getInt("begins") + "," + fy.getInt("amount"));
		} else {
			return Db.find("select * from testresults where testresultsState=0  "+condition+" order by id desc ");
		}
	}
	
	/**
	 *  获取一个基因检测结果的详细信息
	 * @param testresultsNo基因检测结果编号   
	 * @return
	 */
	public Record testresultsById(String testresultsNo) {
		List<Record> list = Db.find("select * from testresults where testresultsNo=? ",
				testresultsNo);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 添加基因检测结果
	 * @param testresults
	 *            添加的内容
	 * @return
	 */
	public boolean testresultsSave(Record testresults) {
		return Db.save("testresults", testresults);
	}
	
	/**
	 * 修改基因检测结果
	 * @param testresults	
	 * @return
	 */
	public boolean testresultsUpdate(Record testresults) {
		return Db.update("testresults", testresults);
	}

	/**
	 * 删除基因检测结果（0可用，1不可以用）
	 * @param testresults
	 * @return
	 */
	public boolean testresultsDelete(Record testresults) {
		return Db.update("testresults", testresults);
	}
	/**
	 * 验证基因检测结果名称是否唯一
	 * 
	 * @param testresultsNo
	 * @return
	 */
	public List<Record>  ajaxtestresultsname(String testresultsname) {
		List<Record> list = Db.find("select * from testresults where testresultsname=?",
				testresultsname);
//		if (list.size() > 0) {
//			return list.get(0);
//		} else {
//			return null;
//		}
		return list;
	}
}
