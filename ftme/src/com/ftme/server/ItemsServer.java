package com.ftme.server;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
/**
 * 检测项目业务层
 * @author wyl
 *
 */
public class ItemsServer {
	/**
	 * 获取对应状态的的基因检测项目
	 * @param itemsState 基因检测项目状态:0可用，1不可以用
	 * @return
	 */
	public List<Record> itemsFind(Integer itemsState,Record fy) {
//		//System.out.println("itemsState   "+itemsState);
		if(fy!=null){
			return Db.find("select * from items where itemsState=?  order by id desc limit  " + fy.getInt("begins") + "," + fy.getInt("amount"),itemsState);
		}else{
			return Db.find("select * from items where itemsState=? order by id desc ",itemsState);
		}
	}
	
	/**
	 * 获取查询的基因检测项目
	 * @param items
	 * @param fy
	 * @return
	 */
	public List<Record> itemsFindSel(String condition, Record fy) {
		if (fy != null) {
			return Db.find("select * from items where itemsState=0  "+condition+" order by id desc limit " + fy.getInt("begins") + "," + fy.getInt("amount"));
		} else {
			return Db.find("select * from items where itemsState=0  "+condition+" order by id desc ");
		}
	}
	
	/**
	 *  获取一个基因检测项目的详细信息
	 * @param itemsNo基因检测项目编号   
	 * @return
	 */
	public Record itemsById(String itemsNo) {
		List<Record> list = Db.find("select * from items where itemsNo=? ",
				itemsNo);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 添加基因检测项目
	 * @param items
	 *            添加的内容
	 * @return
	 */
	public boolean itemsSave(Record items) {
		return Db.save("items", items);
	}
	
	/**
	 * 修改基因检测项目
	 * @param items	
	 * @return
	 */
	public boolean itemsUpdate(Record items) {
		return Db.update("items", items);
	}

	/**
	 * 删除基因检测项目（0可用，1不可以用）
	 * @param items
	 * @return
	 */
	public boolean itemsDelete(Record items) {
		return Db.update("items", items);
	}
	/**
	 * 验证基因检测项目名称是否唯一
	 * 
	 * @param itemsNo
	 * @return
	 */
	public List<Record>  ajaxitemsname(String itemsname) {
		List<Record> list = Db.find("select * from items where itemsname=?",
				itemsname);
//		if (list.size() > 0) {
//			return list.get(0);
//		} else {
//			return null;
//		}
		return list;
	}
}
