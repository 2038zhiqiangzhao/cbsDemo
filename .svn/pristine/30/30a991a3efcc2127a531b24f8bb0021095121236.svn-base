package com.people2000.user.web.action;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.people2000.user.business.utils.SessionContainer;
import com.people2000.user.business.write.manage.CertificationWriteManage;
import com.people2000.user.model.po.ibatis.UserIdentityCard;
import com.people2000.user.model.vo.UserIdentityCardVO;
import com.people2000.user.web.BaseAction;

/**
 * Created by ody on 2015/9/24.
 */

@Controller
@RequestMapping(value = "/identityCard")
public class CertificationAction extends BaseAction {
	@Resource(name = "certificationWriteManage")
	private CertificationWriteManage certificationWriteManage;

	/**
	 * 新增实名认证信息
	 *
	 * @param userIdentityCard
	 * @return
	 */
	@RequestMapping(value = "/addIdentityCard", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object addIdentityCard(
			@RequestBody UserIdentityCardVO userIdentityCard) {
		userIdentityCard.setUserId(SessionContainer.getUserId());
		HashMap<String, Object> resultMap = certificationWriteManage
				.addUserIdentityCardWithTx(userIdentityCard);
		return resultMap;
	}

	@RequestMapping(value = "/addIdentityCardForm", method = RequestMethod.POST)
	@ResponseBody
	public Object addIdentityCardForm(UserIdentityCardVO userIdentityCard) {
		return this.addIdentityCard(userIdentityCard);
	}

	/**
	 * 根据uid获取实名认证信息
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getIdentityCard", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object getIdentityCard() {
		UserIdentityCardVO userIdentityCardVO = new UserIdentityCardVO();
		userIdentityCardVO.setUserId(SessionContainer.getUserId());
		UserIdentityCardVO userIdentityCard1 = certificationWriteManage
				.getUserIdentityCard(userIdentityCardVO);
		if (userIdentityCard1 == null) {
			return fail(null);
		}
		return success(userIdentityCard1);
	}

	/**
	 * 查询用户身份证信息
	 *
	 * @param userIdentityCard
	 * @return
	 */
	@RequestMapping(value = "/getIdentityCardForm", method = RequestMethod.POST)
	@ResponseBody
	public Object getIdentityCardForm(UserIdentityCard userIdentityCard) {
		return this.getIdentityCard();
	}

	/**
	 * 修改用户实名认证信息
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/updateIdentityCard", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object updateIdentityCard(
			@RequestBody UserIdentityCardVO userIdentityCard) {
		userIdentityCard.setUserId(SessionContainer.getUserId());
		final HashMap<String, Object> hashMap = certificationWriteManage
				.updateUserIdentityCardWithTx(userIdentityCard);
		return hashMap;

	}

	/**
	 * 修改用户实名认证信息
	 *
	 * @param userIdentityCard
	 * @return
	 */
	@RequestMapping(value = "/updateIdentityCardForm", method = RequestMethod.POST)
	@ResponseBody
	public Object updateIdentityCardForm(UserIdentityCardVO userIdentityCard) {
		return this.updateIdentityCard(userIdentityCard);
	}

}
