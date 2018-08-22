package com.people2000.user.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.people2000.user.business.utils.SessionContainer;
import com.people2000.user.business.write.manage.ShippingAddressWriteManage;
import com.people2000.user.model.vo.UserAddressVO;
import com.people2000.user.web.BaseAction;

/**
 * Created by xxl on 2015/9/24.
 */

@Controller
@RequestMapping(value = "/address")
public class ShippingAddressAction extends BaseAction {
	@Resource(name = "shippingAddressWriteManage")
	private ShippingAddressWriteManage shippingAddressWriteManage;

	/**
	 * 新增地址
	 *
	 * @param userAddress
	 * @return
	 */
	@RequestMapping(value = "/addAddress", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object addAddress(@RequestBody UserAddressVO userAddress) {
		userAddress.setUserId(SessionContainer.getUserId());
		HashMap<String, Object> map = shippingAddressWriteManage
				.addAddressWithTx(userAddress);
		return map;
	}

	@RequestMapping(value = "/addAddressForm", method = RequestMethod.POST)
	@ResponseBody
	public Object addAddressForm(UserAddressVO userAddress) {
		return this.addAddress(userAddress);
	}

	/**
	 * 更新地址
	 *
	 * @param userAddress
	 * @return
	 */
	@RequestMapping(value = "/updateAddress", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object updateAddress(@RequestBody UserAddressVO userAddress) {
		userAddress.setUserId(SessionContainer.getUserId());
		HashMap<String, Object> resultMap = shippingAddressWriteManage
				.updateAddressWithTx(userAddress);
		return resultMap;
	}

	@RequestMapping(value = "/updateAddressForm", method = RequestMethod.POST)
	@ResponseBody
	public Object updateAddressForm(UserAddressVO userAddress) {
		return this.updateAddress(userAddress);
	}

	/**
	 * 删除地址
	 *
	 * @param userAddress
	 * @return
	 */
	@RequestMapping(value = "/deleteAddress", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteAddress(@RequestBody UserAddressVO userAddress) {
		userAddress.setUserId(SessionContainer.getUserId());
		int code = shippingAddressWriteManage.deleteAddressWithTx(userAddress);
		if (code == 0) {
			return success(null);
		}
		return fail(null);
	}

	@RequestMapping(value = "/deleteAddressForm", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteAddressForm(UserAddressVO userAddress) {
		return this.deleteAddress(userAddress);
	}

	/**
	 * 获取默认地址
	 *
	 * @param userAddress
	 * @return
	 */
	@RequestMapping(value = "/getDefaultAddress", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object getDefaultAddress(@RequestBody UserAddressVO userAddress) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		userAddress.setUserId(SessionContainer.getUserId());
		UserAddressVO defaultAddress = shippingAddressWriteManage
				.getDefaultAddress(userAddress);
		if (defaultAddress == null) {
			return success(null);
		}
		return success(defaultAddress);
	}

	@RequestMapping(value = "/getDefaultAddressForm", method = RequestMethod.POST)
	@ResponseBody
	public Object getDefaultAddressForm(UserAddressVO userAddress) {
		return this.getDefaultAddress(userAddress);
	}

	/**
	 * 根据id获取地址
	 *
	 * @param userAddress
	 * @return
	 */
	@RequestMapping(value = "/getAddress", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object getAddress(@RequestBody UserAddressVO userAddress) {
		userAddress.setUserId(SessionContainer.getUserId());
		UserAddressVO address = shippingAddressWriteManage
				.getAddress(userAddress);
		if (address == null) {
			return fail(null);
		}
		return success(address);
	}

	@RequestMapping(value = "/getAddressForm", method = RequestMethod.POST)
	@ResponseBody
	public Object getAddressForm(UserAddressVO userAddress) {
		return this.getAddress(userAddress);
	}

	/**
	 * 获取用户所有地址
	 *
	 * @param userAddress
	 * @return
	 */
	@RequestMapping(value = "/getAllAddress", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object getAllAddress(@RequestBody UserAddressVO userAddress) {
		userAddress.setUserId(SessionContainer.getUserId());
		List<UserAddressVO> list = shippingAddressWriteManage
				.getAllAdress(userAddress);
		// if (list == null) {
		// return fail(null);
		// }
		return success(list);
	}

	@RequestMapping(value = "/getAllAddressForm", method = RequestMethod.POST)
	@ResponseBody
	public Object getAllAddressForm(UserAddressVO userAddress) {
		return this.getAllAddress(userAddress);
	}

}
