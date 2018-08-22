package com.people.cbsadmin.business.read;

import java.util.List;

import com.people.cbsadmin.model.po.BaseDic;
import com.people.cbsadmin.model.vo.BaseDicQueryVo;
import com.people2000.common.base.page.PageResult;


public interface BaseDicReadManage {
	
	List<BaseDic> queryBaseDicList(BaseDicQueryVo querys);
	
	PageResult<BaseDic> queryBaseDicPage(BaseDicQueryVo querys);
	
	BaseDic queryBaseDicById(Integer id);
	
	BaseDic queryBaseDicByDicKey(Integer dicKey);
	
	List<BaseDic> queryBaseDicByDicPKey(Integer dictPKey);
	
}
