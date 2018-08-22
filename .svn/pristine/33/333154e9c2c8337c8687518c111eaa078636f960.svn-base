package com.people2000.user.business.write.manage.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.people2000.common.base.bean.BeanUtils;
import com.people2000.common.base.page.PageResult;
import com.people2000.common.log.LogUtils;
import com.people2000.user.business.write.dao.UserIdentityCardDAOWrite;
import com.people2000.user.business.write.dao.ext.UserIdentityCardDAOWrite2;
import com.people2000.user.business.write.manage.CertificationWriteManage;
import com.people2000.user.model.constant.ConstantUser;
import com.people2000.user.model.constant.ErrorCode;
import com.people2000.user.model.dto.CertificationDTO;
import com.people2000.user.model.exception.OuserMangeException;
import com.people2000.user.model.po.ibatis.UserIdentityCard;
import com.people2000.user.model.po.ibatis.UserIdentityCardExample;
import com.people2000.user.model.vo.UserIdentityCardVO;

@Service("certificationWriteManage")
public class CertificationWriteManageImpl implements CertificationWriteManage {
	@Resource(name = "userIdentityCardDAOWrite")
	private UserIdentityCardDAOWrite userIdentityCardDAOWrite;

	@Resource(name = "userIdentityCardDAOWrite")
	private UserIdentityCardDAOWrite2 userIdentityCardDAOWrite2;

	@Override
	public HashMap<String, Object> addUserIdentityCardWithTx(
			UserIdentityCardVO userIdentityCard) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		UserIdentityCardExample example = new UserIdentityCardExample();
		UserIdentityCardExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userIdentityCard.getUserId());
		try {
			List list = userIdentityCardDAOWrite
					.selectByExample(example, false);
			if (list != null && list.size() > 0) {
				resultMap.put("code", "5");
				resultMap.put("message", "不能重复认证!");
				return resultMap;
			}
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
		}
		String temp;
		{
			temp = userIdentityCard.getIdentityCardName();
			if (StringUtils.isBlank(temp)) {
				LogUtils.getLogger(this.getClass()).warn("姓名不能为空!");
				resultMap.put("code", "1");
				resultMap.put("message", "姓名不能为空!");
				return resultMap;
			}
			temp = temp.trim();
			temp = temp.toLowerCase();
			temp = temp.replaceAll(" ", "");
			temp = temp.replaceAll("\t", "");
			temp = temp.replaceAll("\r", "");
			temp = temp.replaceAll("\n", "");
			userIdentityCard.setIdentityCardName(temp);
		}
		{
			temp = userIdentityCard.getIdentityCardNumber();
			if (StringUtils.isBlank(temp)) {
				LogUtils.getLogger(this.getClass()).warn("号码不能为空!");
				resultMap.put("code", "2");
				resultMap.put("message", "身份证号码不能为空!");
				return resultMap;
			}
			temp = temp.trim();
			temp = temp.toLowerCase();
			temp = temp.replaceAll(" ", "");
			temp = temp.replaceAll("\t", "");
			temp = temp.replaceAll("\r", "");
			temp = temp.replaceAll("\n", "");
			userIdentityCard.setIdentityCardNumber(temp);
		}
		// {
		// temp = userIdentityCard.getIdentityCardFrontUrl();
		// if (StringUtils.isBlank(temp)) {
		// LogUtils.warn(this, "身份证正面不能为空!");
		// resultMap.put("code", "3");
		// resultMap.put("message", "身份证正面不能为空!");
		// return resultMap;
		// }
		// temp = temp.trim();
		// temp = temp.toLowerCase();
		// temp = temp.replaceAll(" ", "");
		// temp = temp.replaceAll("\t", "");
		// temp = temp.replaceAll("\r", "");
		// temp = temp.replaceAll("\n", "");
		// userIdentityCard.setIdentityCardFrontUrl(temp);
		// }
		// {
		// temp = userIdentityCard.getIdentityCardBackUrl();
		// if (StringUtils.isBlank(temp)) {
		// resultMap.put("code", "4");
		// resultMap.put("message", "身份证反面不能为空!");
		// return resultMap;
		// }
		// temp = temp.trim();
		// temp = temp.toLowerCase();
		// temp = temp.replaceAll(" ", "");
		// temp = temp.replaceAll("\t", "");
		// temp = temp.replaceAll("\r", "");
		// temp = temp.replaceAll("\n", "");
		// userIdentityCard.setIdentityCardBackUrl(temp);
		// }

		UserIdentityCard userIdentityCard1 = new UserIdentityCard();
		BeanUtils.copyProperties(userIdentityCard, userIdentityCard1);
		try {
			Long insert = userIdentityCardDAOWrite.insert(userIdentityCard1);
			resultMap.put("id", insert);
		} catch (SQLException e) {
			resultMap.put("code", "-1");
			resultMap.put("message", "失败!");
			return resultMap;
		}
		resultMap.put("code", "0");
		resultMap.put("message", "成功!");
		return resultMap;
	}

	@Override
	public UserIdentityCardVO getUserIdentityCard(
			UserIdentityCardVO userIdentityCard) {
		UserIdentityCardExample example = new UserIdentityCardExample();
		UserIdentityCardExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userIdentityCard.getUserId());
		try {
			List<UserIdentityCard> list = userIdentityCardDAOWrite
					.selectByExample(example, false);
			if (list != null && list.size() > 0) {
				BeanUtils.copyProperties(list.get(0), userIdentityCard);
				return userIdentityCard;
			}
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
		}
		return null;
	}

	@Override
	public UserIdentityCardVO getUserIdentityCard(String uid) {
		UserIdentityCardVO userIdentityCard = new UserIdentityCardVO();
		userIdentityCard.setUserId(Long.valueOf(uid));
		return this.getUserIdentityCard(userIdentityCard);
	}

	@Override
	public PageResult<CertificationDTO> getCertificationPageByQuery(
			CertificationDTO certificationDTO) throws OuserMangeException {
		try {
			List<CertificationDTO> list = userIdentityCardDAOWrite2
					.getCertificationPageByQuery(certificationDTO);
			if (list == null || list.isEmpty()) {
				return null;
			}
			int count = userIdentityCardDAOWrite2
					.getCountByCondition(certificationDTO);
			PageResult<CertificationDTO> pageResult = new PageResult<CertificationDTO>();
			pageResult.setListObj(list);
			pageResult.setTotal(count);
			return pageResult;
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			throw new OuserMangeException("", "查询实名认证列表失败");
		}
	}

	@Override
	public CertificationDTO getCertificationById(Long id)
			throws OuserMangeException {

		try {
			CertificationDTO certificationDTO = userIdentityCardDAOWrite2
					.getCertificationById(id);
			if (certificationDTO != null) {
				return certificationDTO;
			}
			return null;
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			throw new OuserMangeException("", e.getMessage());
		}
	}

	@Override
	public void passWithTx(Long id) throws OuserMangeException {
		UserIdentityCard userIdentityCard = new UserIdentityCard();
		userIdentityCard.setStatus(ConstantUser.user_status.success);
		userIdentityCard.setId(id);
		try {
			userIdentityCardDAOWrite
					.updateByPrimaryKeySelective(userIdentityCard);
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			throw new OuserMangeException("", "更改用户审核状态失败(通过)");
		}
	}

	@Override
	public void notPassWithTx(CertificationDTO inputDTO)
			throws OuserMangeException {
		UserIdentityCard userIdentityCard = new UserIdentityCard();
		userIdentityCard.setStatus(ConstantUser.user_status.fail);
		userIdentityCard.setId(inputDTO.getId());
		userIdentityCard.setNotpassCode(inputDTO.getNotpassCode());
		userIdentityCard.setNotpassMessage(inputDTO.getNotpassMessage());
		try {
			userIdentityCardDAOWrite
					.updateByPrimaryKeySelective(userIdentityCard);
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			throw new OuserMangeException("", "更改用户审核状态失败(不通过)");
		}
	}

	/**
	 * 更新用户信息
	 *
	 * @param userIdentityCard
	 * @return
	 */
	@Override
	public HashMap<String, Object> updateUserIdentityCardWithTx(
			UserIdentityCardVO userIdentityCard) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		final UserIdentityCardExample example = new UserIdentityCardExample();
		final UserIdentityCardExample.Criteria criteria = example
				.createCriteria();
		criteria.andUserIdEqualTo(userIdentityCard.getUserId());
		criteria.andIdEqualTo(userIdentityCard.getId());
		final UserIdentityCard record = new UserIdentityCard();
		BeanUtils.copyProperties(userIdentityCard, record);
		try {
			final int i = userIdentityCardDAOWrite.updateByExampleSelective(
					record, example);
			if (i < 1) {
				resultMap.put("code", "-2");
				resultMap.put("message", "失败!");
				return resultMap;
			}
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			resultMap.put("code", "-1");
			resultMap.put("message", "失败!");
			return resultMap;
		}
		resultMap.put("code", "0");
		resultMap.put("message", "成功!");
		return resultMap;
	}

	/**
	 * 新增用户实名认证信息
	 *
	 * @param commonInputDTO
	 */
	@Override
	public void addUserIdentityCard2WithTx(CertificationDTO commonInputDTO)
			throws OuserMangeException {
		UserIdentityCardExample example = new UserIdentityCardExample();
		UserIdentityCardExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(commonInputDTO.getUserId());
		try {
			List list = userIdentityCardDAOWrite
					.selectByExample(example, false);
			if (list != null && !list.isEmpty()) {
				throw new OuserMangeException(ErrorCode.bussiness_error,
						"已经存在实名认证信息");
			}
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			throw new OuserMangeException(ErrorCode.sql_error, "select error");
		}
		final UserIdentityCard userIdentityCard = new UserIdentityCard();
		BeanUtils.copyProperties(commonInputDTO, userIdentityCard);
		userIdentityCard.setCompanyId(commonInputDTO.getCompanyId());
		try {
			final Long insert = userIdentityCardDAOWrite
					.insert(userIdentityCard);
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			throw new OuserMangeException(ErrorCode.sql_error, "insert error");
		}
	}

	/**
	 * 更新用户实名认证信息
	 *
	 * @param commonInputDTO
	 */
	@Override
	public void updateUserIdentityCard2WithTx(CertificationDTO commonInputDTO)
			throws OuserMangeException {
		final UserIdentityCard userIdentityCard = new UserIdentityCard();
		BeanUtils.copyProperties(commonInputDTO, userIdentityCard);
		try {
			userIdentityCardDAOWrite
					.updateByPrimaryKeySelective(userIdentityCard);
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			throw new OuserMangeException(ErrorCode.sql_error, "update error");
		}

	}
}
