package com.ftme.server;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
/**
 * 行业动态业务层
 * @author wyl
 *
 */
public class NewsServer {
	/**
	 * 获取对应状态的的行业动态信息
	 * @return
	 */
	public List<Record> newsFind(Record fy) {
		if(fy!=null){
			return Db.find("select id,newsNo,title from news order by id desc limit " + fy.getInt("begins") + "," + fy.getInt("amount"));
		}else{
			return Db.find("select id,newsNo,title from news order by id desc ");
		}
	}
	
	/**
	 *  获取一个行业动态的详细信息
	 * @param newsNo行业动态编号   
	 * @return
	 */
	public Record newsById(String newsNo) {
		List<Record> list = Db.find("select * from news where newsNo=? ",
				newsNo);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 添加行业动态信息
	 * @param news
	 *            添加的内容
	 * @return
	 */
	public boolean newsSave(Record news) {
		return Db.save("news", news);
	}
	
	/**
	 * 修改行业动态信息
	 * @param news	
	 * @return
	 */
	public boolean newsUpdate(Record news) {
		return Db.update("news", news); 
	}

	/**
	 * 删除行业动态信息（0可用，1不可以用）
	 * @param news
	 * @return
	 */
	public boolean newsDelete(Record news) {
		return Db.delete("news", news);
	}
	
}
