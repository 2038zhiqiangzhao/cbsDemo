package com.people2000.user.business.write.manage;

import java.util.List;

import com.people2000.user.model.dto.UserAPIDTO;
import com.people2000.user.model.exception.OuserMangeException;

/**
 * Created by xiaole on 2015/11/26.
 */
public interface UserAPIWriteManage {
	/**
	 * 根据用个别户信息获取用户所有信息
	 *
	 * @param commonInputDTO
	 * @return
	 */
	UserAPIDTO getUserInfoByUserAPI(UserAPIDTO commonInputDTO)
			throws OuserMangeException;

	/**
	 * 根据个别信息修改用户信息
	 *
	 * @param commonInputDTO
	 * @return
	 */
	Long updateByUserAPIWithTx(UserAPIDTO commonInputDTO)
			throws OuserMangeException;

	/**
	 * 根据用户信息获取用户所有信息列表
	 *
	 * @param commonInputDTO
	 * @return
	 */
	List<UserAPIDTO> getUserInfoListByUserAPI(UserAPIDTO commonInputDTO)
			throws OuserMangeException;
}
