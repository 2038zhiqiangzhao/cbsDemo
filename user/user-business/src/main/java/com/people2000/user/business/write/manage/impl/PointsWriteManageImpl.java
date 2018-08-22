package com.people2000.user.business.write.manage.impl;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.people2000.common.log.LogUtils;
import com.people2000.user.business.write.dao.ext.UserDAOWrite2;
import com.people2000.user.business.write.manage.PointsWriteManage;
import com.people2000.user.model.constant.ErrorCode;
import com.people2000.user.model.dto.PointsDTO;
import com.people2000.user.model.exception.OuserMangeException;
import com.people2000.user.model.po.ibatis.User;

/**
 * Created by xiaole on 2015/12/18.
 */
@Service("pointsWriteManage")
public class PointsWriteManageImpl implements PointsWriteManage {
	@Resource(name = "userDAOWrite")
	private UserDAOWrite2 userDAOWrite;

	/**
	 * 根据id查询用户积分
	 *
	 * @param commonInputDTO
	 * @return
	 */
	@Override
	public PointsDTO queryByUserId(Long userId) throws OuserMangeException {
		try {
			User user = userDAOWrite.selectByPrimaryKey(userId, false);
			if (null == user) {
				throw new OuserMangeException(ErrorCode.bussiness_error,
						"未查询到该用户");
			}
			PointsDTO pointsDTO = new PointsDTO();
			pointsDTO.setPoints(user.getPoints());
			return pointsDTO;
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			throw new OuserMangeException(ErrorCode.system__error, "查询该用户积分失败");
		}
	}

	/**
	 * 更新用户积分
	 *
	 * @param commonInputDTO
	 */
	@Override
	public void updatePointsWithTx(PointsDTO pointsDTO)
			throws OuserMangeException {
		try {
			User user = userDAOWrite.selectByPrimaryKey(pointsDTO.getId(),
					false);
			if (null == user) {
				throw new OuserMangeException(ErrorCode.bussiness_error,
						"没有查询到该用户");
			}
			BigDecimal points = user.getPoints();
			if (points == null) {
				points = BigDecimal.valueOf(0.00);
			}
			BigDecimal result = pointsDTO.getPoints().add(points);
			User user1 = new User();
			user1.setId(pointsDTO.getId());
			user1.setPoints(result);
			userDAOWrite.updateByPrimaryKeySelective(user1);
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			throw new OuserMangeException(ErrorCode.system__error, "更新用户积分失败");
		}
	}
}
