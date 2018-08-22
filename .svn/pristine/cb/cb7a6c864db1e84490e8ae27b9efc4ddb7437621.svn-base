package com.people2000.user.business.write.manage;

import com.people2000.user.model.dto.PointsDTO;
import com.people2000.user.model.exception.OuserMangeException;

/**
 * Created by xiaole on 2015/12/18.
 */
public interface PointsWriteManage {
	/**
	 * 根据id查询用户积分
	 *
	 * @param commonInputDTO
	 * @return
	 */
	PointsDTO queryByUserId(Long commonInputDTO) throws OuserMangeException;

	/**
	 * 更新用户积分
	 *
	 * @param commonInputDTO
	 */
	void updatePointsWithTx(PointsDTO commonInputDTO)
			throws OuserMangeException;
}
