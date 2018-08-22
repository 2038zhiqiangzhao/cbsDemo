package com.people.cbsadmin.business.dao.ext;

import java.util.List;

import com.people.cbsadmin.model.po.BaseSequence;
import com.people.cbsadmin.model.vo.BaseSequenceQueryVo;



public interface BaseSequenceMapperExt {
	/**
	 * 
	 * <p>
	 * 根据条件查询列表
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年12月28日 上午10:20:31
	 * @param querys
	 * @return
	 * @see
	 */
	List<BaseSequence> queryBaseSequenceList(BaseSequenceQueryVo querys);

	/**
	 * 
	 * <p>
	 * 根据条件统计数量
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年12月28日 上午10:20:48
	 * @param querys
	 * @return
	 * @see
	 */
	int countBaseSequence(BaseSequenceQueryVo querys);

	/**
	 * 
	 * <p>
	 * 根据条件查询分页数据
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年12月28日 上午10:21:06
	 * @param querys
	 * @return
	 * @see
	 */
	List<BaseSequence> queryBaseSequencePage(BaseSequenceQueryVo querys);

	/**
	 * 根据序列名称获取下个值
	 * 
	 * @param seqName
	 * @return
	 */
	Integer getSequenceNextval(String seqName);
}