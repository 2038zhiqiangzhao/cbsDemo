package com.people2000.user.business.write.manage;

import java.util.List;

import com.people2000.user.model.dto.PushMessageInputDTO;
import com.people2000.user.model.dto.PushMessageOutputDTO;
import com.people2000.user.model.exception.OuserMangeException;

/**
 * Created by xiaole on 2016/3/4.
 */
public interface PushMessageWriteManage {
	/**
	 * 根据条件查找
	 *
	 * @param commonInputDTO
	 * @return
	 */
	List<PushMessageOutputDTO> findByConditions(
			PushMessageInputDTO commonInputDTO) throws OuserMangeException;

	/**
	 * 新增
	 *
	 * @param commonInputDTO
	 */
	Long addPushMessageWithTx(PushMessageInputDTO commonInputDTO)
			throws OuserMangeException;

	/**
	 * 更新
	 *
	 * @param commonInputDTO
	 */
	void updatePushMessageWithTx(PushMessageInputDTO commonInputDTO)
			throws OuserMangeException;

	/**
	 * 删除
	 *
	 * @param commonInputDTO
	 */
	void delPushMessageWithTx(PushMessageInputDTO commonInputDTO)
			throws OuserMangeException;

	void delAllPushMessageWithTx(String deviceId) throws OuserMangeException;
}
