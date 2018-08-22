package com.people2000.user.business.write.manage.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.people2000.common.log.LogUtils;
import com.people2000.user.business.write.dao.UPositionDAOWrite;
import com.people2000.user.business.write.manage.PositionWriteManage;
import com.people2000.user.model.dto.PositionDTO;
import com.people2000.user.model.po.ibatis.UPositionPO;
import com.people2000.user.model.po.ibatis.UPositionPOExample;
import com.people2000.user.model.vo.PositionVO;

@Service("positionWriteManage")
public class PositionWriteManageImpl implements PositionWriteManage {

	@Resource(name = "positionDAOWrite")
	private UPositionDAOWrite positionDAOWrite;

	private static final Logger logger = LogUtils
			.getLogger(PositionWriteManageImpl.class);

	/**
	 * 根据岗位名称查询结果
	 */
	@Override
	public List<PositionDTO> queryPositionByName(PositionVO vo)
			throws SQLException {
		// 创建查询对象
		UPositionPOExample example = new UPositionPOExample();
		UPositionPOExample.Criteria criteria = example.createCriteria();
		// 创建结果集列表
		List<PositionDTO> positions = new ArrayList<PositionDTO>();
		criteria.andPositionNameEqualTo(vo.getPositionName());
		try {
			positions = positionDAOWrite.selectByExample(example, false);
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error("查询Position异常", e);
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
		}
		return positions;
	}

	/**
	 * 查询position列表具体实现(分页)
	 * 
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> queryPositionListF(PositionVO vo)
			throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		if (null != vo.getCurrentPage() && null != vo.getItemsPerPage()
				&& vo.getCurrentPage() > 0 && vo.getItemsPerPage() >= 1) {
			vo.setStartItems((vo.getCurrentPage() - 1) * vo.getItemsPerPage());
		} else {
			logger.info("CurrentPage： " + vo.getCurrentPage()
					+ " ItemsPerPage: " + vo.getItemsPerPage());
			throw new Exception("查询分页参数错误!");
		}
		// 创建查询对象
		UPositionPOExample example = new UPositionPOExample();
		UPositionPOExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("create_time desc");// 降序排列
		// 查询结果总条数初始化定义
		int total = 0;
		// 创建结果集列表
		List<PositionDTO> positions = new ArrayList<PositionDTO>();
		// 设置查询条件
		if (null != vo.getPositionName()) {
			criteria.andPositionNameLike(vo.getPositionName() + "%");
		}
		if (null != vo.getCreateTimeStart() && null != vo.getCreateTimeEnd()) {
			criteria.andCreateTimeBetween(vo.getCreateTimeStart(),
					vo.getCreateTimeEnd());
		}
		if (null != vo.getCreateTimeStart()) {
			criteria.andCreateTimeGreaterThanOrEqualTo(vo.getCreateTimeStart());
		}
		if (null != vo.getCreateTimeEnd()) {
			criteria.andCreateTimeLessThanOrEqualTo(vo.getCreateTimeEnd());
		}

		try {
			total = positionDAOWrite.countByExample(example, false);
			// 不为0就继续查询
			if (total != 0) {
			}
			// 设置分页查询条件
			example.setLimitClauseStart((long) ((vo.getCurrentPage() - 1) * vo
					.getItemsPerPage()));
			example.setLimitClauseCount(vo.getItemsPerPage().longValue());
			positions = positionDAOWrite.selectByExample(example, false);
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error("查询Position列表异常", e);
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
		}
		map.put("total", total);
		map.put("list", positions);
		return map;
	}

	/**
	 * 新增岗位具体实现
	 */
	@Override
	public void savePositionWithTx(PositionVO vo) throws SQLException {
		// 创建查询对象
		UPositionPOExample example = new UPositionPOExample();
		UPositionPOExample.Criteria criteria = example.createCriteria();
		// 判断是否存在该岗位
		criteria.andPositionNameEqualTo(vo.getPositionName());
		int count = positionDAOWrite.countByExample(example, false);
		// 结果不存在,再新增
		if (count == 0) {
			UPositionPO record = new UPositionPO();
			record.setId(null);
			record.setPositionName(vo.getPositionName());
			record.setPositionDesc(vo.getPositionDesc());
			record.setCompanyId(vo.getCompanyId());
			// record.setCreateTime(new Date(System.currentTimeMillis()));
			try {
				positionDAOWrite.insert(record);
			} catch (SQLException e) {
				LogUtils.getLogger(getClass()).error("新增Position异常", e);
				LogUtils.getLogger(getClass()).error(e.getMessage(), e);
				;
			}
		}
	}

	/**
	 * 修改岗位信息具体实现
	 */
	@Override
	public void updatePositionWithTx(PositionVO vo) throws SQLException {
		System.out.println("更新执行");
		UPositionPO record = new UPositionPO();
		record.setId(vo.getId());
		record.setPositionName(vo.getPositionName());
		record.setPositionDesc(vo.getPositionDesc());
		// 设置更新时间
		record.setUpdateTime(new Date(System.currentTimeMillis()));
		try {
			positionDAOWrite.updateByPrimaryKeySelective(record);
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error("修改Position异常", e);
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
		}

	}

	/**
	 * 删除岗位信息具体实现
	 */
	@Override
	public void deletePositionWithTx(PositionVO vo) throws SQLException {
		System.out.println("删除执行");
		UPositionPO record = new UPositionPO();
		record.setId(vo.getId());
		record.setIsDeleted(1);
		try {
			positionDAOWrite.updateByPrimaryKeySelective(record);
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error("删除Position异常", e);
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
		}

	}

}
