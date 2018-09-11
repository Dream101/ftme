package com.ftme.server;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
/**
 * 前台logo图片管理业务层
 * @author wyl
 *
 */
public class PictureinfoServer {
	/**
	 * 获取前台logo图片管理
	 * @return
	 */
	public List<Record> pictureinfoFind() {
			return Db.find("select * from pictureinfo order by id asc");
	}
	
	/**
	 * 获取某个前台logo的具体信息
	 * @return
	 */
	public Record pictureinfoByFind(String  pictureNo) {
		return Db.findFirst("select * from pictureinfo where pictureNo=?",pictureNo);
	}
	
	/**
	 * 添加前台logo图片管理
	 * @param pictureinfo  添加的内容
	 * @return
	 */
	public boolean pictureinfoSave(Record pictureinfo) {
		return Db.save("pictureinfo", pictureinfo);
	}
	
	/**
	 * 修改前台logo图片管理
	 * @param pictureinfo 内容
	 * @return
	 */
	public boolean pictureinfoUpdate(Record pictureinfo) {
		return Db.update("pictureinfo", pictureinfo);
	}

	
}
