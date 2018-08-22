/**
 *
 */
package com.people2000.user.business.write.manage;

import java.util.HashMap;

import com.people2000.common.base.page.PageResult;
import com.people2000.user.model.dto.CertificationDTO;
import com.people2000.user.model.exception.OuserMangeException;
import com.people2000.user.model.vo.UserIdentityCardVO;

/**
 * @author crc
 * @time 2015-5-14 下午6:25:01
 * @description <pre>
 * <p/>
 * </pre>
 */
public interface CertificationWriteManage {

	/**
	 * 新增用户身份证信息
	 *
	 * @param userIdentityCard
	 * @return
	 */
	HashMap<String, Object> addUserIdentityCardWithTx(
			UserIdentityCardVO userIdentityCard);

	/**
	 * 查询用户身份证信息
	 *
	 * @param userIdentityCard
	 * @return
	 */
	UserIdentityCardVO getUserIdentityCard(UserIdentityCardVO userIdentityCard);

	/**
	 * 根据用户id获取用户的身份证信息
	 *
	 * @param uid
	 * @return
	 */
	UserIdentityCardVO getUserIdentityCard(String uid);

	/**
	 * 根据查询条件查询分页信息
	 *
	 * @param certificationDTO
	 * @return
	 */
	PageResult<CertificationDTO> getCertificationPageByQuery(
			CertificationDTO certificationDTO) throws OuserMangeException;

	/**
	 * 根据id获取实名信息详情
	 *
	 * @param id
	 * @return
	 */
	CertificationDTO getCertificationById(Long id) throws OuserMangeException;

	/**
	 * 审核通过
	 *
	 * @param id
	 * @return
	 */
	void passWithTx(Long id) throws OuserMangeException;

	/**
	 * 审核不通过
	 *
	 * @param inputDTO
	 */
	void notPassWithTx(CertificationDTO inputDTO) throws OuserMangeException;

	/**
	 * 更新用户信息
	 *
	 * @param userIdentityCard
	 * @return
	 */
	HashMap<String, Object> updateUserIdentityCardWithTx(
			UserIdentityCardVO userIdentityCard);

	/**
	 * 新增用户实名认证信息
	 *
	 * @param commonInputDTO
	 */
	void addUserIdentityCard2WithTx(CertificationDTO commonInputDTO)
			throws OuserMangeException;

	/**
	 * 更新用户实名认证信息
	 *
	 * @param commonInputDTO
	 */
	void updateUserIdentityCard2WithTx(CertificationDTO commonInputDTO)
			throws OuserMangeException;
}
