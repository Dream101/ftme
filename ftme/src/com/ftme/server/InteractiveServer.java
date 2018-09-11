package com.ftme.server;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
/**
 * 联系我们业务层
 * @author wyl
 *
 */
public class InteractiveServer {
	/**
	 * 获取对应状态的的联系我们信息
	 * @return
	 */
	public List<Record> interactiveFind(Record fy) {
		if(fy!=null){
			return Db.find("select * from interactive order by interactiveState,id desc limit " + fy.getInt("begins") + "," + fy.getInt("amount"));
		}else{
			return Db.find("select * from interactive order by interactiveState,id desc ");
		}
	}
	
	/**
	 *  获取一个联系我们的详细信息
	 * @param interactiveNo联系我们编号   
	 * @return
	 */
	public Record interactiveById(String interactiveNo) {
		List<Record> list = Db.find("select * from interactive where interactiveNo=? ",
				interactiveNo);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 添加联系我们
	 * @param interactive
	 *            内容
	 * @return
	 */
	public boolean interactiveSave(Record interactive) {
		return Db.save("interactive", interactive);
	}
	
	/**
	 * 更改状态查询数据的状态
	 * @param interactive	
	 * @return
	 */
	public boolean interactiveUpdate(Record interactive) {
		return Db.update("interactive", interactive); 
	}
}
