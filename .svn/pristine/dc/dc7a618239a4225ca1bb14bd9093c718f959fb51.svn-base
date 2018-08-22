/**
 * 
 */
package com.people2000.user.business.write.manage;

import java.util.List;

/**
 * @author crc
 * @time 2015-8-11 下午4:04:01
 * @description <pre>
 * 
 * </pre>
 * 
 */
public interface UserShopWriteManage {

	/**
	 * 
	 * @description <pre>
	 * 保存用户与店铺的关联关系
	 * </pre>
	 * @param shopIds
	 * @param userId
	 * @throws Exception
	 */
	int saveUserManageShopWithTx(List<Long> shopIds, Long userId)
			throws Exception;

	/**
	 * 
	 * @description <pre>
	 * 根据用户id获取用户管理的店铺
	 * </pre>
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<Long> getUserMangeShopList(Long userId) throws Exception;

	/**
	 * 
	 * @description <pre>
	 * 更新用户管理店铺
	 * </pre>
	 * @param shopIds
	 * @param userId
	 * @return
	 */
	int updateUserManageShopWithTx(List<Long> shopIds, Long userId)
			throws Exception;

}
