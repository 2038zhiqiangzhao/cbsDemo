package com.people.cbsadmin.business.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.people.cbsadmin.business.dao.BaseDicMapper;
import com.people.cbsadmin.business.dao.ext.BaseDicMapperExt;
import com.people.cbsadmin.business.read.BaseDicReadManage;
import com.people.cbsadmin.model.po.BaseDic;
import com.people.cbsadmin.model.vo.BaseDicQueryVo;
import com.people2000.common.base.page.PageResult;


@Service("baseDicReadManage")
public class BaseDicReadManageImpl implements BaseDicReadManage {

	@Autowired
	private BaseDicMapper baseDicMapper;
	@Autowired
	private BaseDicMapperExt baseDicMapperExt;
	
	/**
	 * 
	 * <p>根据条件查询列表</p> 
	 * @author dusai
	 * @date 2017年8月3日 下午3:14:43
	 * @param querys
	 * @return 
	 * @see com.people2000.mzadmin.business.read.BaseDicReadManage#queryBaseDicList(com.people2000.mzadmin.model.vo.BaseDicQueryVo)
	 */
	@Override
	public List<BaseDic> queryBaseDicList(
			BaseDicQueryVo querys) {
		return baseDicMapperExt.queryBaseDicList(querys);
	}

	/**
	 * 
	 * <p>根据条件查询分页</p> 
	 * @author dusai
	 * @date 2017年8月3日 下午3:14:56
	 * @param querys
	 * @return 
	 * @see com.people2000.mzadmin.business.read.BaseDicReadManage#queryBaseDicPage(com.people2000.mzadmin.model.vo.BaseDicQueryVo)
	 */
	@Override
	public PageResult<BaseDic> queryBaseDicPage(
			BaseDicQueryVo querys) {
		PageResult<BaseDic> page = new PageResult<BaseDic>();
		int totalSize = baseDicMapperExt.countBaseDic(querys);
		if (totalSize > 0) {
			page.setTotal(totalSize);
			List<BaseDic> dataList = baseDicMapperExt
					.queryBaseDicPage(querys);
			page.setListObj(dataList);
		}
		return page;
	}

	/**
	 * 
	 * <p>根据主键id查询</p> 
	 * @author dusai
	 * @date 2017年8月3日 下午3:15:09
	 * @param id
	 * @return 
	 * @see com.people2000.mzadmin.business.read.BaseDicReadManage#queryBaseDicById(java.lang.Integer)
	 */
	@Override
	public BaseDic queryBaseDicById(Integer id) {
		return baseDicMapper.selectByPrimaryKey(id);
	}

	/**
	 * 
	 * <p>根据字典key查询</p> 
	 * @author dusai
	 * @date 2017年8月3日 下午3:15:21
	 * @param dicKey
	 * @return 
	 * @see com.people2000.mzadmin.business.read.BaseDicReadManage#queryBaseDicByDicKey(java.lang.Integer)
	 */
	@Override
	public BaseDic queryBaseDicByDicKey(Integer dicKey) {
		BaseDicQueryVo querys = new BaseDicQueryVo();
		querys.setDicKey(dicKey);
		List<BaseDic> dics = queryBaseDicList(querys);
		if (dics != null && !dics.isEmpty()) {
			return dics.get(0);
		}
		return null;
	}

	/**
	 * 
	 * <p>更新父级key查询子级列表</p> 
	 * @author dusai
	 * @date 2017年8月3日 下午3:15:36
	 * @param dicPKey
	 * @return 
	 * @see com.people2000.mzadmin.business.read.BaseDicReadManage#queryBaseDicByDicPKey(java.lang.Integer)
	 */
	@Override
	public List<BaseDic> queryBaseDicByDicPKey(Integer dicPKey) {
		BaseDicQueryVo querys = new BaseDicQueryVo();
		querys.setDicPkey(dicPKey);
		List<BaseDic> dics = queryBaseDicList(querys);
		return dics;
	}

}
