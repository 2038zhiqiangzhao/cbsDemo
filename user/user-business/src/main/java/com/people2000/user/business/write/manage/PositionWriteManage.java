package com.people2000.user.business.write.manage;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.people2000.user.model.dto.PositionDTO;
import com.people2000.user.model.vo.PositionVO;

public interface PositionWriteManage {

	/**
	 * 查询岗位信息列表分页
	 * 
	 * @param PO
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> queryPositionListF(PositionVO vo)
			throws SQLException, Exception;

	/**
	 * 查询岗位信息列表
	 * 
	 * @param PO
	 * @return
	 */
	public List<PositionDTO> queryPositionByName(PositionVO vo)
			throws SQLException;

	/**
	 * 新增岗位信息
	 * 
	 * @param vo
	 * @throws SQLException
	 */
	public void savePositionWithTx(PositionVO vo) throws SQLException;

	/**
	 * 更新岗位信息
	 * 
	 * @param vo
	 * @throws SQLException
	 */
	public void updatePositionWithTx(PositionVO vo) throws SQLException;

	/**
	 * 删除岗位信息(实际做更新操作)
	 * 
	 * @param vo
	 * @throws SQLException
	 */
	public void deletePositionWithTx(PositionVO vo) throws SQLException;

}
