package com.people2000.user.business.write.manage.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.people2000.common.base.bean.BeanUtils;
import com.people2000.common.base.page.PageResult;
import com.people2000.common.log.LogUtils;
import com.people2000.user.business.utils.IDCardUtils;
import com.people2000.user.business.write.dao.UserAddressDAOWrite;
import com.people2000.user.business.write.manage.ShippingAddressWriteManage;
import com.people2000.user.model.constant.ConstantUser;
import com.people2000.user.model.constant.ErrorCode;
import com.people2000.user.model.dto.UserAreaDTO;
import com.people2000.user.model.exception.OuserMangeException;
import com.people2000.user.model.po.ibatis.UserAddress;
import com.people2000.user.model.po.ibatis.UserAddressExample;
import com.people2000.user.model.vo.UserAddressVO;

@Service("shippingAddressWriteManage")
public class ShippingAddressWriteManageImpl implements
		ShippingAddressWriteManage {
	@Resource(name = "userAddressDAOWrite")
	private UserAddressDAOWrite userAddressDAOWrite;

	@Override
	public HashMap<String, Object> addAddressWithTx(UserAddressVO userAddress) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String temp;
		{
			temp = userAddress.getUserName();
			if (StringUtils.isBlank(temp)) {
				LogUtils.getLogger(this.getClass()).warn("收货人不能为空!");
				resultMap.put("code", "1");
				resultMap.put("message", "收货人不能为空!");
				return resultMap;

			}
			temp = temp.trim();
			temp = temp.toLowerCase();
			temp = temp.replaceAll(" ", "");
			temp = temp.replaceAll("\t", "");
			temp = temp.replaceAll("\r", "");
			temp = temp.replaceAll("\n", "");
			userAddress.setUserName(temp);
		}
		{
			temp = userAddress.getMobile();
			if (StringUtils.isBlank(temp)) {
				resultMap.put("code", "2");
				resultMap.put("message", "联系电话不能为空!");
				return resultMap;
			}
			temp = temp.trim();
			temp = temp.replaceAll("\r", "");
			temp = temp.replaceAll("\n", "");
			userAddress.setMobile(temp);
		}
		// 身份证校验
		String identityCardNumber = userAddress.getIdentityCardNumber();
		if (StringUtils.isNotBlank(identityCardNumber)) {
			boolean isIDCard = IDCardUtils.isIDCard(identityCardNumber);
			if (!isIDCard) {
				resultMap.put("code", "4");
				resultMap.put("message", "身份证号码有误");
				return resultMap;
			}
		}
		// 如果没有地址则设为默认地址
		{
			UserAddressExample example = new UserAddressExample();
			UserAddressExample.Criteria criteria = example.createCriteria();
			criteria.andUserIdEqualTo(userAddress.getUserId());
			try {
				List list = userAddressDAOWrite.selectByExample(example, false);
				if (list == null || list.size() < 1) {
					userAddress.setDefaultIs(1);
				}
			} catch (SQLException e) {
				LogUtils.getLogger(getClass()).error(e.getMessage(), e);
				;
			}
		}
		// 如果为默认地址，将原默认地址置为非默认
		if (userAddress.getDefaultIs() != null
				&& ConstantUser.u_user_address_default_is_true
						.equals(userAddress.getDefaultIs())) {
			UserAddressExample example = new UserAddressExample();
			UserAddressExample.Criteria criteria = example.createCriteria();
			criteria.andUserIdEqualTo(userAddress.getUserId())
					.andDefaultIsEqualTo(
							ConstantUser.u_user_address_default_is_true);
			try {
				List<UserAddress> list = userAddressDAOWrite.selectByExample(
						example, false);
				if (list != null && list.size() > 0) {
					UserAddress address = list.get(0);
					address.setDefaultIs(0);
					userAddressDAOWrite.updateByPrimaryKeySelective(address);
				}
			} catch (SQLException e) {
				LogUtils.getLogger(getClass()).error(e.getMessage(), e);
				;
				resultMap.put("code", "-1");
				resultMap.put("message", "查询失败!");
				return resultMap;
			}
		}

		UserAddress userAddress1 = new UserAddress();
		BeanUtils.copyProperties(userAddress, userAddress1);
		// 如果城市名称不为空，城市id为空，查询城市id
		getCityIdByName(userAddress, userAddress1);
		userAddress1.setProvinceId(userAddress.getProvinceCode());
		userAddress1.setUpdateTime(new Date());
		Long insert;
		try {
			insert = userAddressDAOWrite.insert(userAddress1);
		} catch (SQLException e) {
			resultMap.put("code", "-1");
			resultMap.put("message", "新增失败!");
			return resultMap;
		}
		resultMap.put("code", "0");
		resultMap.put("id", insert);
		resultMap.put("message", "添加成功!");

		return resultMap;
	}

	private void getCityIdByName(UserAddressVO userAddress,
			UserAddress userAddress1) {
		if (userAddress.getCityName() != null
				&& null == userAddress.getCityId()) {

		}
	}

	@Override
	public HashMap<String, Object> updateAddressWithTx(UserAddressVO userAddress) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		{
			if (userAddress.getId() == null) {
				resultMap.put("code", "1");
				resultMap.put("message", "id不能为空!");
				return resultMap;
			}
		}
		// 查询原地址是否为默认地址
		UserAddress address = null;
		UserAddressExample example = new UserAddressExample();
		UserAddressExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(userAddress.getId()).andUserIdEqualTo(
				userAddress.getUserId());
		try {
			List<UserAddress> list = userAddressDAOWrite.selectByExample(
					example, false);
			if (list != null && list.size() > 0) {
				address = list.get(0);
			} else {
				resultMap.put("code", "2");
				resultMap.put("message", "地址不存在!");
				return resultMap;
			}
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
		}
		if (address != null
				&& address.getDefaultIs().equals(userAddress.getDefaultIs())) {
			return doUpdateAddress(userAddress, resultMap);
		}
		// 新地址为默认地址，修改原默认地址为非默认
		if (userAddress.getDefaultIs() != null
				&& ConstantUser.u_user_address_default_is_true
						.equals(userAddress.getDefaultIs())) {
			UserAddressExample example1 = new UserAddressExample();
			UserAddressExample.Criteria criteria1 = example1.createCriteria();
			criteria1.andUserIdEqualTo(userAddress.getUserId())
					.andDefaultIsEqualTo(
							ConstantUser.u_user_address_default_is_true);
			try {
				List<UserAddress> list = userAddressDAOWrite.selectByExample(
						example1, false);
				if (list != null && list.size() > 0) {
					UserAddress address1 = list.get(0);
					address1.setDefaultIs(0);
					userAddressDAOWrite.updateByPrimaryKeySelective(address1);
				}
			} catch (SQLException e) {
				LogUtils.getLogger(getClass()).error(e.getMessage(), e);
				;
				resultMap.put("code", "-1");
				resultMap.put("message", "失败!");
				return resultMap;
			}
		} else {
			// 新地址为非默认地址，修改原地址最新创建为默认地址
			setNewCreatDefault(userAddress);
		}
		return doUpdateAddress(userAddress, resultMap);

	}

	// 进行更新操作
	private HashMap<String, Object> doUpdateAddress(UserAddressVO userAddress,
			HashMap<String, Object> resultMap) {
		// 更新地址
		UserAddress userAddress1 = new UserAddress();
		BeanUtils.copyProperties(userAddress, userAddress1);
		getCityIdByName(userAddress, userAddress1);
		userAddress1.setProvinceId(userAddress.getProvinceCode());
		try {
			userAddressDAOWrite.updateByPrimaryKeySelective(userAddress1);
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			resultMap.put("code", "-1");
			resultMap.put("message", "失败!");
			return resultMap;
		}
		resultMap.put("code", "0");
		resultMap.put("message", "更新成功!");
		return resultMap;
	}

	@Override
	public UserAddressVO getDefaultAddress(UserAddressVO userAddress) {
		UserAddressExample example = new UserAddressExample();
		UserAddressExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userAddress.getUserId()).andDefaultIsEqualTo(
				1);
		try {
			List<UserAddress> list = userAddressDAOWrite.selectByExample(
					example, false);
			if (list.size() > 0) {
				UserAddress address = list.get(0);
				addressToVO(address, userAddress);
				return userAddress;
			}
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
		}
		return null;
	}

	@Override
	public UserAddressVO getAddress(UserAddressVO userAddress) {
		try {
			UserAddressExample example = new UserAddressExample();
			UserAddressExample.Criteria criteria = example.createCriteria();
			criteria.andIdEqualTo(userAddress.getId());
			List<UserAddress> list = userAddressDAOWrite.selectByExample(
					example, false);
			if (CollectionUtils.isEmpty(list)) {
				return null;
			}
			// 判断uid是否为该id的uid
			// if (address.getUserId()!=userAddress.getUserId())
			// {
			// return null;
			// }
			addressToVO(list.get(0), userAddress);
			return userAddress;
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			return null;
		}
	}

	@Override
	public List<UserAddressVO> getAllAdress(UserAddressVO userAddress) {
		UserAddressExample example = new UserAddressExample();
		UserAddressExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userAddress.getUserId());
		example.setOrderByClause("update_time desc,default_is desc");
		List<UserAddressVO> list1 = new ArrayList<UserAddressVO>();
		try {
			List<UserAddress> list = userAddressDAOWrite.selectByExample(
					example, false);

			if (list.size() > 0) {
				Iterator<UserAddress> it = list.iterator();
				while (it.hasNext()) {
					UserAddressVO userAddressVO = new UserAddressVO();
					UserAddress address = it.next();
					addressToVO(address, userAddressVO);
					list1.add(userAddressVO);
				}
				return list1;
			}
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
		}
		return list1;
	}

	@Override
	public int deleteAddressWithTx(UserAddressVO userAddress) {
		if (userAddress.getId() == null) {
			return -1;
		}
		try {
			UserAddress address = userAddressDAOWrite.selectByPrimaryKey(
					userAddress.getId(), false);
			if (address == null
					|| !address.getUserId().equals(userAddress.getUserId())) {
				return -1;
			}
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			return -1;
		}
		// 设置删除地址isdelete为1
		UserAddress userAddress1 = new UserAddress();
		BeanUtils.copyProperties(userAddress, userAddress1);
		userAddress1.setProvinceId(userAddress.getProvinceCode());
		try {
			userAddress1.setIsDeleted(1);
			userAddressDAOWrite.updateByPrimaryKeySelective(userAddress1);
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			return -1;
		}

		if (userAddress.getDefaultIs() != null
				&& ConstantUser.u_user_address_default_is_true
						.equals(userAddress.getDefaultIs()))
			if (setNewCreatDefault(userAddress))
				return -1;

		return 0;
	}

	// 如果是新地址为非默认地址，设置最新更新的地址为默认地址

	private boolean setNewCreatDefault(UserAddressVO userAddress) {
		UserAddressExample example = new UserAddressExample();
		example.setOrderByClause("update_time desc");
		UserAddressExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userAddress.getUserId()).andDefaultIsEqualTo(
				ConstantUser.u_user_address_default_is_false);
		try {
			List<UserAddress> list = userAddressDAOWrite.selectByExample(
					example, false);
			if (list != null && list.size() > 0) {
				UserAddress address = list.get(0);
				address.setDefaultIs(ConstantUser.u_user_address_default_is_true);
				userAddressDAOWrite.updateByPrimaryKeySelective(address);
			}
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			return true;
		}
		return false;
	}

	@Override
	public List<UserAddressVO> getUserAddressByProvinced(String provinceId,
			String uid) throws SQLException {
		if (provinceId != null) {
			UserAddressExample example = new UserAddressExample();
			UserAddressExample.Criteria criteria = example.createCriteria();
			criteria.andUserIdEqualTo(Long.valueOf(uid));
			criteria.andProvinceIdEqualTo(Long.valueOf(provinceId));
			example.setOrderByClause("update_time desc");
			List list = userAddressDAOWrite.selectByExample(example, false);
			if (list.size() > 0) {
				List<UserAddressVO> list1 = new ArrayList<UserAddressVO>();
				Iterator<UserAddress> it = list.iterator();
				while (it.hasNext()) {
					UserAddressVO userAddressVO = new UserAddressVO();
					UserAddress address = it.next();
					addressToVO(address, userAddressVO);
					list1.add(userAddressVO);
				}
				return list1;
			}
		} else {
			// 如果没有省份id返回默认收货地址
			final UserAddressVO userAddressVO = new UserAddressVO();
			userAddressVO.setUserId(Long.valueOf(uid));
			final UserAddressVO defaultAddress = getDefaultAddress(userAddressVO);
			final List arrayList = new ArrayList();
			arrayList.add(defaultAddress);
			return arrayList;
		}
		return null;
	}

	/**
	 * 更新收货地址的使用时间
	 *
	 * @param commonInputDTO
	 */
	@Override
	public void updateUseTimeWithTx(Long id) throws OuserMangeException {
		final UserAddress userAddress = new UserAddress();
		userAddress.setId(id);
		userAddress.setUseTime(new Date());
		try {
			userAddressDAOWrite.updateByPrimaryKeySelective(userAddress);
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			throw new OuserMangeException(ErrorCode.system__error, "sql异常，更新失败");
		}
	}

	/**
	 * 根据用户id获取收货地址
	 *
	 * @param commonInputDTO
	 * @return
	 */
	@Override
	public List<UserAddressVO> getUserAddressByUserIdBatch(Long userId) {
		UserAddressExample example = new UserAddressExample();
		UserAddressExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		example.setOrderByClause("use_time desc,update_time desc,default_is desc");
		List<UserAddressVO> list1 = new ArrayList<UserAddressVO>();
		try {
			List<UserAddress> list = userAddressDAOWrite.selectByExample(
					example, false);
			if (list.size() > 0) {
				Iterator<UserAddress> it = list.iterator();
				while (it.hasNext()) {
					UserAddressVO userAddressVO = new UserAddressVO();
					UserAddress address = it.next();
					addressToVO(address, userAddressVO);
					list1.add(userAddressVO);
				}
				return list1;
			}
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
		}
		return list1;
	}

	/**
	 * 根据用户id获取默认收货地址
	 *
	 * @param commonInputDTO
	 * @return
	 */
	@Override
	public UserAddressVO getDefaultByUserId(Long userId) {
		UserAddressExample example = new UserAddressExample();
		UserAddressExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId).andDefaultIsEqualTo(1);
		try {
			List<UserAddress> list = userAddressDAOWrite.selectByExample(
					example, false);
			if (list.size() > 0) {
				UserAddress address = list.get(0);
				UserAddressVO userAddress = new UserAddressVO();
				addressToVO(address, userAddress);
				return userAddress;
			}
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
		}
		return null;
	}

	/**
	 * 根据用户和区域id查询排序收货地址
	 *
	 * @param commonInputDTO
	 * @return
	 */
	@Override
	public List<UserAddressVO> getAddressByUserIdAreaIdBatch(UserAreaDTO data)
			throws OuserMangeException {
		if (data.getUserId() == null) {
			throw new OuserMangeException(ErrorCode.input_null, "用户id不能为空");
		}
		final UserAddressExample example = new UserAddressExample();
		final UserAddressExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(data.getUserId());
		example.setOrderByClause("use_time desc,update_time desc");
		try {
			final List<UserAddress> list = userAddressDAOWrite.selectByExample(
					example, false);
			final List<UserAddressVO> resultList = new ArrayList();
			if (CollectionUtils.isEmpty(list)) {
				return resultList;
			}
			// 将结果转为Dto
			final Iterator<UserAddress> it = list.iterator();
			while (it.hasNext()) {
				UserAddressVO userAddressVO = new UserAddressVO();
				UserAddress address = it.next();
				addressToVO(address, userAddressVO);
				resultList.add(userAddressVO);
			}
			// 根据排序参数排序
			final List sortList1 = new ArrayList();
			final List sortList2 = new ArrayList();
			for (int i = 0; i < resultList.size(); i++) {
				final UserAddressVO userAddressVO = resultList.get(i);
				// //如果省份id不为空
				// if (data.getProvinceId() != null) {
				// if
				// (userAddressVO.getProvinceCode().equals(data.getProvinceId()))
				// {
				// sortList1.add(userAddressVO);
				// } else {
				// sortList2.add(userAddressVO);
				// }
				// }
				// //如果市id不为空
				// if (data.getCityId() != null) {
				// if (userAddressVO.getCityId().equals(data.getCityId())) {
				// sortList1.add(userAddressVO);
				// } else {
				// sortList2.add(userAddressVO);
				// }
				// }
				// //如果区id不为空
				// if (data.getRegionId() != null) {
				// if (userAddressVO.getRegionId().equals(data.getRegionId())) {
				// sortList1.add(userAddressVO);
				// } else {
				// sortList2.add(userAddressVO);
				// }
				// }
				final Long unknowId = data.getUnknowId();
				final Long provinceId = userAddressVO.getProvinceCode();
				final Long cityId = userAddressVO.getCityId();
				final Long regionId = userAddressVO.getRegionId();
				if (unknowId != null
						&& (unknowId.equals(provinceId)
								|| unknowId.equals(cityId) || unknowId
									.equals(regionId))) {
					sortList1.add(userAddressVO);
				} else {
					sortList2.add(userAddressVO);
				}
			}
			sortList1.addAll(sortList2);
			return sortList1;

		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			throw new OuserMangeException(ErrorCode.system__error,
					"sql异常,查询收货地址列表出错");
		}
	}

	/**
	 * 根据分页条件查询默认收货地址
	 *
	 * @param commonInputDTO
	 * @return
	 */
	@Override
	public PageResult<UserAddressVO> getDefaultAddressByPage(UserAreaDTO data)
			throws OuserMangeException {
		final UserAddressExample example = new UserAddressExample();
		final UserAddressExample.Criteria criteria = example.createCriteria();
		if (data.getStartTime() != null) {
			criteria.andCreateTimeGreaterThanOrEqualTo(data.getStartTime());
		}
		if (data.getEndTime() != null) {
			criteria.andCreateTimeLessThanOrEqualTo(data.getEndTime());
		}
		if (data.getUserName() != null) {
			criteria.andUserNameLike("%" + data.getUserName() + "%");
		}
		if (data.getMobile() != null) {
			criteria.andMobileLike("%" + data.getMobile() + "%");
		}
		criteria.andDefaultIsEqualTo(1);
		example.setLimitClauseStart((long) data.getStartItem());
		example.setLimitClauseCount((long) data.getItemsPerPage());
		example.setOrderByClause("create_time desc");
		try {
			final List<UserAddress> list = userAddressDAOWrite.selectByExample(
					example, false);
			final List<UserAddressVO> result = new ArrayList();
			if (!CollectionUtils.isEmpty(list)) {
				for (int i = 0; i < list.size(); i++) {
					final UserAddressVO userAddressVO = new UserAddressVO();
					BeanUtils.copyProperties(list.get(i), userAddressVO);
					result.add(userAddressVO);
				}
			}
			final int i = userAddressDAOWrite.countByExample(example, false);
			final PageResult<UserAddressVO> pageResult = new PageResult();
			pageResult.setTotal(i);
			pageResult.setListObj(result);
			return pageResult;
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			throw new OuserMangeException(ErrorCode.sql_error, "sql_error");
		}
	}

	// 将区域id转为name，并存入表中
	// 入参 address为po，出参为DTO
	private void addressToVO(UserAddress address, UserAddressVO userAddress) {
		final UserAddress updateAddress = new UserAddress();
		boolean flag = false;
		if (address.getProvinceId() != null
				&& address.getProvinceName() == null) {
			final String provinceName = getNameById(address.getProvinceId());
			address.setProvinceName(provinceName);
			updateAddress.setProvinceName(provinceName);
			flag = true;
		}
		if (address.getCityId() != null && address.getCityName() == null) {
			final String cityName = getNameById(address.getCityId());
			address.setCityName(cityName);
			updateAddress.setCityName(cityName);
			flag = true;
		}
		if (address.getRegionId() != null && address.getRegionName() == null) {
			final String regionName = getNameById(address.getRegionId());
			address.setRegionName(regionName);
			updateAddress.setRegionName(regionName);
			flag = true;
		}
		if (flag) {
			updateAddress.setId(address.getId());
			try {
				userAddressDAOWrite.updateByPrimaryKeySelective(updateAddress);
			} catch (SQLException e) {
				LogUtils.getLogger(getClass()).error(e.getMessage(), e);
				;
			}
		}
		BeanUtils.copyProperties(address, userAddress);
		if (address.getProvinceId() != null) {
			userAddress.setProvinceCode(address.getProvinceId());
		}
	}

	// InputDTO inputDTO = new InputDTO();
	// SoaAreaReadClient instanst = SoaAreaReadClient.getInstanst();
	//
	// //根据地址code获取地址name
	// private String getNameByCode(Long code) {
	// if (code == null) {
	// return null;
	// }
	// inputDTO.setData(code);
	// try {
	// OutputDTO areaName = instanst.getAreaName(inputDTO);
	// return areaName.getData().toString();
	// } catch (SQLException e) {
	// LogUtils.getLogger(getClass()).error(e.getMessage(),e);;
	// }
	// return null;
	// }

	private String getNameById(Long code) {
		return null;
	}

}
