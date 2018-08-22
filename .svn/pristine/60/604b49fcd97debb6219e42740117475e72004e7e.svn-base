/**
 *
 */
package com.people2000.user.business.write.manage;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.people2000.common.base.page.PageResult;
import com.people2000.user.model.dto.UserAreaDTO;
import com.people2000.user.model.exception.OuserMangeException;
import com.people2000.user.model.vo.UserAddressVO;

/**
 * @author crc
 * @time 2015-5-14 下午6:25:01
 * @description <pre>
 * <p/>
 * </pre>
 */
public interface ShippingAddressWriteManage {

	/**
	 * 新增收货地址
	 *
	 * @param userAddress
	 * @return
	 */
	HashMap<String, Object> addAddressWithTx(UserAddressVO userAddress);

	/**
	 * 更新收货地址
	 *
	 * @param userAddress
	 * @return
	 */
	HashMap<String, Object> updateAddressWithTx(UserAddressVO userAddress);

	/**
	 * 获取默认收货地址
	 *
	 * @param userAddress
	 * @return
	 */
	UserAddressVO getDefaultAddress(UserAddressVO userAddress);

	/**
	 * 查询收货地址
	 *
	 * @param userAddress
	 * @return
	 */
	UserAddressVO getAddress(UserAddressVO userAddress);

	/**
	 * 获取所有收货地址
	 *
	 * @param userAddress
	 * @return
	 */
	List<UserAddressVO> getAllAdress(UserAddressVO userAddress);

	/**
	 * 删除收货地址
	 *
	 * @param userAddress
	 * @return
	 */

	int deleteAddressWithTx(UserAddressVO userAddress);

	/**
	 * 根据省份获取用户收货地址
	 *
	 * @param provinceId
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	List<UserAddressVO> getUserAddressByProvinced(String provinceId, String uid)
			throws SQLException;

	/**
	 * 更新收货地址的使用时间
	 *
	 * @param commonInputDTO
	 */
	void updateUseTimeWithTx(Long id) throws OuserMangeException;

	/**
	 * 根据用户id获取收货地址
	 *
	 * @param commonInputDTO
	 * @return
	 */
	List<UserAddressVO> getUserAddressByUserIdBatch(Long userId);

	/**
	 * 根据用户id获取默认收货地址
	 *
	 * @param commonInputDTO
	 * @return
	 */
	UserAddressVO getDefaultByUserId(Long userId);

	/**
	 * 根据用户和区域id查询排序收货地址
	 *
	 * @param commonInputDTO
	 * @return
	 */
	List<UserAddressVO> getAddressByUserIdAreaIdBatch(UserAreaDTO commonInputDTO)
			throws OuserMangeException;

	/**
	 * 根据分页条件查询默认收货地址
	 *
	 * @param commonInputDTO
	 * @return
	 */
	PageResult<UserAddressVO> getDefaultAddressByPage(UserAreaDTO commonInputDTO)
			throws OuserMangeException;
}
