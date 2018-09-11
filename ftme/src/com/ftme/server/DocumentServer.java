package com.ftme.server;

import java.io.File;
import java.util.List;

import com.ftme.common.model.Documentinfo;
import com.ftme.common.model.Userinfo;
import com.ftme.util.WylUtil;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;
/**
 * 文档业务层
 * @author wyl
 *
 */
public class DocumentServer {
	
	public static Documentinfo documentinfo = null;
	public static WylUtil wyl = new WylUtil();
	
	
	
	/**
	 * 文件上传(案例，可以之后，将参数该为model类型的参数)
	 * 
	 * @return
	 */
	public boolean documentUpload(UploadFile file) {
		String filePath = file.getUploadPath() + "\\" + file.getFileName();
		String newName=wyl.getPath(file.getFileName());
//		////System.out.println(newName);
		file.getFile().renameTo(new File(newName));

		return true;
	}
	
	/**
	 * 分页查询
	 * @param 
	 * @return
	 */
	public List<Record> documentFindAll(Record fy,Userinfo userinfo) {
		////System.out.println("documentFindAll=    "+!userinfo.getRoleNo().equals("2"));
		if(fy!=null){
			if(!userinfo.getRoleNo().equals("2")){
				return Db.find("select * from documentinfo  order by id desc limit "+ fy.getInt("begins") + "," + fy.getInt("amount"));
			}else{
				return Db.find("select * from documentinfo where  admin=? order by id desc limit "+ fy.getInt("begins") + "," + fy.getInt("amount"),userinfo.getUserNo());
			}
		}else{
			if(!userinfo.getRoleNo().equals("2")){
				return Db.find("select * from documentinfo order by id desc ");
			}else{
				return Db.find("select * from documentinfo where  admin=? order by id desc ",userinfo.getUserNo());
			}
		}
		
	}
	
	/**
	 * 条件分页查询
	 * @param condition	查询条件
	 * @param fy 分页信息
	 * @return
	 */
	public List<Record> documentFindSel(String condition,Record fy,Userinfo userinfo) {
		
		if(fy!=null){
			if(!userinfo.getRoleNo().equals("2")){
				return Db.find("select * from documentinfo where 1=1 "+condition+" order by id desc limit  "+ fy.getInt("begins") + "," + fy.getInt("amount"));
			}else{
				return Db.find("select * from documentinfo where admin=? "+condition+" order by id desc limit  "+ fy.getInt("begins") + "," + fy.getInt("amount"),userinfo.getUserNo());
			}
		}else{
			if(!userinfo.getRoleNo().equals("2")){
				return Db.find("select * from documentinfo where 1=1 "+condition+" order by id desc ");
			}else{
				return Db.find("select * from documentinfo where admin=? "+condition+" order by id desc ",userinfo.getUserNo());
			}
			
		}
	}
	
	/**
	 * 获取单个文件信息
	 * @param 
	 * @return
	 */
	public Record documentFindByDocumentNo(String documentNo) {
		
		List<Record> list= Db.find("select * from documentinfo where documentNo= ?",documentNo);
		if(list.size()==0){
			return null;
		}else{
			return list.get(0);
		}
		
	}
	/**
	 *  id               主键      
	 *  documentNo  文档编号
 userNo            患者的id（用户表userinfo）
 hospitalNo        所属医院    
 name              患者的名称（管理员查询的检索字段）
 phone             手机号（管理员查询的检索字段）
 documentType      文档类别(遗传诊断报告,基因的测序结果,诊断病例分析)
 documentName       文档名称    
 documentUploadName 文档上传后的新文档名称
 documentPath       文档上传路径  
 admin             上传者(当前登录者)
 remark            备注      
 documentState     状态（本患者是否已经下载过遗传诊断报告：0可下载，1不可下载）
 datetime          上传时间    
 state             备用字段    
	 */
	/**
	 * 获取对应类型的文件信息
	 * @param 
	 * @return
	 */
	public List<Record> documentFindByDocumentType(String documentType) {
			return Db.find("select * from documentinfo where documentType= ? order by id desc",documentType);
	}
	
	/**
	 * 添加文件信息
	 * @param hospital
	 *            添加的内容
	 * @return
	 */
	public boolean documentinfoSave(Record documentinfo) {
		return Db.save("documentinfo", documentinfo);
	}
	

	/**
	 * 删除文件信息（0可用，1不可以用）
	 * @param hospital
	 * @return
	 */
	public boolean documentinfoDelete(Record documentinfo) {
		////System.out.println("server方法删除文件信息documentinfoDelete"+documentinfo.toString());
		return Db.delete("documentinfo", documentinfo);
	}
	
//	--------------------------------------------------------------------与之关联的数据查询----------------------------------------------------------------------------
	/**
	 * 获取对应状态的的医院信息
	 * @param hospitalState 医院状态:0可用，1不可以用
	 * @return
	 */
	public List<Record> hospitalFind(Integer hospitalState) {
			return Db.find("select * from hospital where hospitalState=? order by id desc",hospitalState);
	}
	
	/**
	 * 获取所有对应状态的患者信息
	 * @param hospitalNo	患者编号
	 * @return
	 */
	public List<Record> patientFineAll(String hospitalNo) {
		////System.out.println("患者信息patientFineAll="+hospitalNo);
		return Db.find("select * from userinfo where roleNo=4 and accountState=1 and hospitalNo = '"+hospitalNo+"' order by id desc");
	}
	
	/**
	 * 获取某个患者具体信息
	 * 
	 * @param userNo
	 *            用户id
	 * @return 返回具体的该id的具体内容
	 */
	public Record staffById(String userNo) {
		List<Record> list = Db.find("select * from userinfo where userNo=?",
				userNo);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}


	// /**
	// * 获取一个文件的详细信息
	// * @param documentNo文件编号
	// * @return
	// */
	// public Record hospitalById(String documentNo) {
	// List<Record> list =
	// Db.find("select * from documentinfo where documentNo=? ",
	// documentNo);
	// if (list.size() > 0) {
	// return list.get(0);
	// } else {
	// return null;
	// }
	// }
	// /**
	// * 修改文件信息
	// * @param hospital
	// * @return
	// */
	// public boolean hospitalUpdate(Record documentinfo) {
	// return Db.update("documentinfo", documentinfo);
	// }
}