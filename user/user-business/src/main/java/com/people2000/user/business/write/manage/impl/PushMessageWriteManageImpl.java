package com.people2000.user.business.write.manage.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.people2000.common.base.bean.BeanUtils;
import com.people2000.common.log.LogUtils;
import com.people2000.user.business.write.dao.PushMessageDAOWrite;
import com.people2000.user.business.write.manage.PushMessageWriteManage;
import com.people2000.user.model.constant.ErrorCode;
import com.people2000.user.model.dto.PushMessageInputDTO;
import com.people2000.user.model.dto.PushMessageOutputDTO;
import com.people2000.user.model.exception.OuserMangeException;
import com.people2000.user.model.po.ibatis.PushMessage;
import com.people2000.user.model.po.ibatis.PushMessageExample;

/**
 * Created by xiaole on 2016/3/4.
 */
@Service("pushMessageWriteManage")
public class PushMessageWriteManageImpl implements PushMessageWriteManage {

	@Resource(name = "pushMessageDAOWrite")
	private PushMessageDAOWrite pushMessageDAOWrite;

	/**
	 * 根据条件查找
	 *
	 * @param commonInputDTO
	 * @return
	 */
	@Override
	public List<PushMessageOutputDTO> findByConditions(
			PushMessageInputDTO pushMessageInputDTO) throws OuserMangeException {
		final Long userId = pushMessageInputDTO.getUserId();
		final Integer clientType = pushMessageInputDTO.getClientType();
		final String deviceId = pushMessageInputDTO.getDeviceId();
		final Integer appType = pushMessageInputDTO.getAppType();
		PushMessageExample example = new PushMessageExample();
		final PushMessageExample.Criteria criteria = example.createCriteria();
		if (userId != null) {
			criteria.andUserIdEqualTo(userId);
		}
		if (deviceId != null) {
			criteria.andDeviceIdEqualTo(deviceId);
		}
		if (appType != null) {
			criteria.andAppTypeEqualTo(appType);
		}
		if (clientType != null) {
			criteria.andClientTypeEqualTo(clientType);
		}
		try {
			final List<PushMessage> list = pushMessageDAOWrite.selectByExample(
					example, false);
			final List<PushMessageOutputDTO> result = new ArrayList();
			if (CollectionUtils.isEmpty(list)) {
				return result;
			}

			removeContain: for (int i = 0; i < list.size(); i++) {
				PushMessage pushMessage = list.get(i);
				// 去重复.

				for (PushMessageOutputDTO pushMessageOutputDTO : result) {

					if (pushMessageOutputDTO.getUserId() != null
							&& pushMessageOutputDTO.getUserId().equals(
									pushMessage.getUserId())
							&& pushMessageOutputDTO.getDeviceId() != null
							&& pushMessageOutputDTO.getDeviceId().equals(
									pushMessage.getDeviceId())
							&& pushMessageOutputDTO.getAppType() != null
							&& pushMessageOutputDTO.getAppType().equals(
									pushMessage.getAppType())) {
						continue removeContain;
					}
				}
				final PushMessageOutputDTO pushMessageOutputDTO = new PushMessageOutputDTO();
				BeanUtils.copyProperties(pushMessage, pushMessageOutputDTO);
				result.add(pushMessageOutputDTO);

			}
			return result;
		} catch (SQLException e) {
			LogUtils.getLogger(PushMessageWriteManage.class).error(
					e.getMessage(), e);
			throw new OuserMangeException(ErrorCode.sql_error, "sql_error");
		}

	}

	/**
	 * 新增
	 *
	 * @param commonInputDTO
	 */
	@Override
	public Long addPushMessageWithTx(PushMessageInputDTO pushMessageInputDTO)
			throws OuserMangeException {
		final PushMessage pushMessage = new PushMessage();
		BeanUtils.copyProperties(pushMessageInputDTO, pushMessage);

		if (pushMessage == null) {
			throw new OuserMangeException(ErrorCode.input_null, "input is null");
		}
		if (pushMessage.getAppType() == null) {
			throw new OuserMangeException(ErrorCode.input_null,
					"input appType is null");
		}
		if (pushMessage.getUserId() == null) {
			throw new OuserMangeException(ErrorCode.input_null,
					"userId cant be null");
		}
		if (pushMessage.getDeviceId() == null) {
			throw new OuserMangeException(ErrorCode.input_null,
					"deviceId cant be null");
		}
		try {
			final Long insert = pushMessageDAOWrite.insert(pushMessage);
			return insert;
		} catch (SQLException e) {
			LogUtils.getLogger(PushMessageWriteManage.class).error(
					e.getMessage(), e);
			throw new OuserMangeException(ErrorCode.sql_error, "sql_error");
		}
	}

	/**
	 * 更新
	 *
	 * @param commonInputDTO
	 */
	@Override
	public void updatePushMessageWithTx(PushMessageInputDTO pushMessageInputDTO)
			throws OuserMangeException {
		final Long userId = pushMessageInputDTO.getUserId();
		final String deviceId = pushMessageInputDTO.getDeviceId();
		final Integer appType = pushMessageInputDTO.getAppType();
		final Integer clientType = pushMessageInputDTO.getClientType();
		if (userId == null) {
			throw new OuserMangeException(ErrorCode.input_null,
					"userId cant be null");
		}
		if (appType == null) {
			throw new OuserMangeException(ErrorCode.input_null,
					"appType cant be null");
		}
		/*
		 * if (clientType == null) { throw new
		 * OuserMangeException(ErrorCode.input_null, "clientType cant be null");
		 * }
		 */
		if (deviceId == null) {
			throw new OuserMangeException(ErrorCode.input_null,
					"deviceId cant be null");
		}

		final PushMessageExample example = new PushMessageExample();
		final PushMessageExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andAppTypeEqualTo(appType);
		// criteria.andClientTypeEqualTo(clientType);
		final PushMessage pushMessage = new PushMessage();
		BeanUtils.copyProperties(pushMessageInputDTO, pushMessage);
		try {
			pushMessageDAOWrite.updateByExampleSelective(pushMessage, example);
		} catch (SQLException e) {
			LogUtils.getLogger(PushMessageWriteManage.class).error(
					e.getMessage(), e);
			throw new OuserMangeException(ErrorCode.sql_error, "sql_error");
		}

	}

	/**
	 * 删除
	 *
	 * @param commonInputDTO
	 */
	@Override
	public void delPushMessageWithTx(PushMessageInputDTO pushMessageInputDTO)
			throws OuserMangeException {
		final Long userId = pushMessageInputDTO.getUserId();
		final String deviceId = pushMessageInputDTO.getDeviceId();
		if (userId == null) {
			throw new OuserMangeException(ErrorCode.input_null,
					"userId cant be null");
		}
		if (deviceId == null) {
			throw new OuserMangeException(ErrorCode.input_null,
					"deviceId cant be null");
		}
		final PushMessageExample example = new PushMessageExample();
		final PushMessageExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andDeviceIdEqualTo(deviceId);
		final PushMessage pushMessage = new PushMessage();
		pushMessage.setIsDeleted(1);
		try {
			pushMessageDAOWrite.updateByExampleSelective(pushMessage, example);
		} catch (SQLException e) {
			LogUtils.getLogger(PushMessageWriteManage.class).error(
					e.getMessage(), e);
			throw new OuserMangeException(ErrorCode.sql_error, "sql_error");
		}
	}

	@Override
	public void delAllPushMessageWithTx(String deviceId)
			throws OuserMangeException {
		if (deviceId == null) {
			throw new OuserMangeException(ErrorCode.input_null,
					"deviceId cant be null");
		}
		PushMessageExample example = new PushMessageExample();
		PushMessageExample.Criteria criteria = example.createCriteria();
		criteria.andDeviceIdEqualTo(deviceId);
		PushMessage pushMessage = new PushMessage();
		pushMessage.setIsDeleted(1);
		try {
			pushMessageDAOWrite.updateByExampleSelective(pushMessage, example);
		} catch (SQLException e) {
			LogUtils.getLogger(PushMessageWriteManage.class).error(
					e.getMessage(), e);
			throw new OuserMangeException(ErrorCode.sql_error, "sql_error");
		}
	}
}
