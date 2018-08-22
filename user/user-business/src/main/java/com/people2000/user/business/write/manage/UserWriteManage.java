package com.people2000.user.business.write.manage;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.people2000.common.base.page.PageResult;
import com.people2000.user.model.dto.DefaultUserDTO;
import com.people2000.user.model.dto.User2;
import com.people2000.user.model.dto.User3;
import com.people2000.user.model.dto.UserAccountDTO;
import com.people2000.user.model.dto.UserCache;
import com.people2000.user.model.dto.UserDto;
import com.people2000.user.model.dto.UserExtInDTO;
import com.people2000.user.model.dto.UserExtOutDTO;
import com.people2000.user.model.dto.UserGradeDTO;
import com.people2000.user.model.dto.UserInDTO;
import com.people2000.user.model.dto.UserInputDTO;
import com.people2000.user.model.dto.UserNameOutputDTO;
import com.people2000.user.model.dto.UserOutDTO;
import com.people2000.user.model.exception.OuserMangeException;
import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.vo.MobileUserVO;
import com.people2000.user.model.vo.UserVO;

public interface UserWriteManage {

	/**
	 * 根据用户id获取用户基本信息
	 * 
	 * @param userId
	 * @return
	 * @description <pre>
	 * 
	 * </pre>
	 */
	User getUserById(Long userId) throws Exception;

	User3 getUserDetailById(Long userId) throws Exception;

	UserAccountDTO getUserAccount(Long userId) throws Exception;

	UserGradeDTO getUserGrade(Long gradeId) throws Exception;

	void resetPasswordByBackendWithTx(UserDto userDto) throws Exception;

	/**
	 * 修改用户信息
	 *
	 * @param userDto
	 */
	Integer updateUserDetailWithTx(UserDto userDto) throws Exception;

	User getUserByIdAndCompanyId(Long userId, Long companyId) throws Exception;

	/**
	 * 商家平台用户修改自己的信息
	 * 
	 * @param uuser
	 * @param userId
	 * @description <pre>
	 * 
	 * </pre>
	 */
	void saveUserWithTx(User uuser, Long userId) throws Exception;

	/**
	 * 提审自己的数据
	 * 
	 * @param uuser
	 * @param userId
	 * @description <pre>
	 * 
	 * </pre>
	 */
	void submitUserWithTx(User uuser, Long userId) throws Exception;

	/**
	 * 根据用户id获取用户的子账号
	 * 
	 * @param userId
	 * @return
	 * @description <pre>
	 * 
	 * </pre>
	 */
	List<User> getUserChildAccountByUserId(Long userId) throws Exception;

	/**
	 * 创建子账号
	 * 
	 * @param userDto
	 * @description <pre>
	 * 
	 * </pre>
	 */
	Long saveUserChildAccountWithTx(UserDto userDto) throws Exception;

	/**
	 * 根据查询条件查询子账户
	 * 
	 * @param userDto
	 * @return
	 * @description <pre>
	 * 
	 * </pre>
	 */
	List<User2> getUserChildAccountByQuery(UserDto userDto) throws Exception;

	/**
	 * 根据id删除子账户
	 * 
	 * @param id
	 *            需要删除的用户id
	 * @param userId
	 *            操作人的用户id
	 * @description <pre>
	 * 
	 * </pre>
	 */
	void deleteChildAccountWithTx(Long id, Long userId) throws Exception;

	/**
	 * 用户修改密码
	 * 
	 * @param userDto
	 * @return
	 * @throws Exception
	 * @description <pre>
	 * 
	 * </pre>
	 */
	int updatePassWordWithTx(UserDto userDto) throws Exception;

	/**
	 * 注册
	 * 
	 * @param userDto
	 * @return
	 * @throws Exception
	 * @description <pre>
	 *
	 * </pre>
	 */
	int registerWithTx(UserDto userDto) throws Exception;

	/**
	 * 根据查询条件，获取管理员用户
	 * 
	 * @param userDto
	 * @return
	 * @throws Exception
	 * @description <pre>
	 *
	 * </pre>
	 */
	List<User> getManageUserByQuery(UserDto userDto) throws Exception;

	/**
	 * 根据查询条件，分页获取所有用户
	 * 
	 * @param userDto
	 * @return
	 * @throws Exception
	 * @description <pre>
	 *
	 * </pre>
	 */
	Map<String, Object> getUserByQueryPage(UserDto userDto) throws Exception;

	/**
	 * 新增后台运营账户
	 * 
	 * @param userDto
	 * @description <pre>
	 * 
	 * </pre>
	 */
	int saveManageUserWithTx(UserDto userDto) throws Exception;

	/**
	 * 获取主账号信息
	 * 
	 * @param user
	 * @return
	 * @description <pre>
	 * 
	 * </pre>
	 */
	List<User> getUserMainList(User user) throws Exception;

	void checkUserWithTx(Long id, Integer status) throws Exception;

	/**
	 * 获取所有用户
	 * 
	 * @param
	 * @return
	 * @description <pre>
	 * 
	 * </pre>
	 */
	List<User> getUsers() throws Exception;

	/**
	 * @param userId
	 * @return
	 * @description <pre>
	 * <p/>
	 * </pre>
	 */
	UserVO getChildUser(Long userId) throws Exception;

	/**
	 * 修改 子账户信息
	 * 
	 * @param userDto
	 * @throws Exception
	 * @description <pre>
	 * 
	 * </pre>
	 */
	void UpdateUserChildAccountWithTx(UserDto userDto, Long userId)
			throws Exception;

	/**
	 * 获取用户身份证信息
	 *
	 * @param userId
	 * @return
	 */
	MobileUserVO getUserMessageByUserId(Long userId);

	public boolean isRepeatName(UserDto userDto) throws SQLException;

	public boolean isRepeatMobile(UserDto userDto) throws Exception;

	/**
	 * 根据用户名获取用户信息
	 *
	 * @param userDto
	 * @return
	 * @throws SQLException
	 */
	User getUserInfoByUserName(UserDto userDto) throws SQLException;

	/**
	 * 根据条件获取用户信息
	 *
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> getUsersByExamplePG(UserDto user) throws Exception;

	/**
	 * 查询列表以外的用户
	 *
	 * @param user
	 * @param userNameList
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> getOtherUsers(UserDto user, List<String> userNameList)
			throws Exception;

	/**
	 * 根据用户id获取用户姓名
	 *
	 * @param commonInputDTO
	 * @return
	 */
	UserNameOutputDTO getUserNameById(Long id) throws OuserMangeException;

	/**
	 * 添加默认商家用户并返回id
	 *
	 * @param commonInputDTO
	 * @return
	 */
	Long addDefaultMerchantUserWithTx(UserDto commonInputDTO)
			throws OuserMangeException;

	/**
	 * 根据分页条件查询用户信息
	 *
	 * @param commonInputDTO
	 * @return
	 * @throws SQLException
	 */
	List<UserNameOutputDTO> getUserBatchNew(UserInputDTO commonInputDTO)
			throws OuserMangeException;

	/**
	 * 根据分页条件查询总条数
	 *
	 * @param commonInputDTO
	 * @return
	 */
	int getTotalNew(UserInputDTO commonInputDTO) throws OuserMangeException;

	/**
	 * 根据分页条件查询用户信息
	 *
	 * @param commonInputDTO
	 * @return
	 */
	List<UserNameOutputDTO> getUserBatch(UserInputDTO commonInputDTO)
			throws OuserMangeException;

	/**
	 * 根据分页条件查询总条数
	 *
	 * @param commonInputDTO
	 * @return
	 */
	int getTotal(UserInputDTO commonInputDTO) throws OuserMangeException;

	/**
	 * 修改用户信息
	 */
	void updateUserWithTx(UserDto userDto) throws SQLException;

	/**
	 * 新增默认用户
	 *
	 * @param commonInputDTO
	 * @return
	 */
	Long addDefaultUserWithTx(DefaultUserDTO commonInputDTO)
			throws OuserMangeException;

	/**
	 * 根据条件查询用户列表
	 *
	 * @param commonInputDTO
	 * @return
	 */
	List<UserOutDTO> getUserByConditions(UserInDTO commonInputDTO)
			throws OuserMangeException;

	/**
	 * 根据条件查询用户列表并分页
	 *
	 * @param commonInputDTO
	 * @return
	 */
	PageResult<UserOutDTO> getUserByConditionsWithPage(UserInDTO commonInputDTO)
			throws OuserMangeException;

	/**
	 * 根据email查询用户记录
	 * 
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	List<User> getUserByEmail(User user) throws SQLException;

	UserCache getUserByOrginalId(String orginalId);

	Long addOneUserWithTx(UserDto userDto) throws SQLException;

	Long addOneUserAndReturnIdWithTx(UserDto userDto) throws SQLException;

	Long addMerchantUserWithTx(UserDto userDto) throws SQLException;

	Long addMerchantUserAndReturnIdWithTx(UserDto userDto) throws SQLException;

	Long activeOrInactiveUserWithTx(UserDto userDto) throws SQLException;

	/**
	 * 获取用户额外信息
	 * 
	 * @param userExtInDTO
	 * @return
	 * @throws OuserMangeException
	 */
	UserExtOutDTO getUserExtByConditions(UserExtInDTO userExtInDTO)
			throws OuserMangeException, SQLException;

	/**
	 * 获取用户额外信息
	 * 
	 * @param userExtInDTO
	 * @throws OuserMangeException
	 * @throws SQLException
	 */
	void updateUserExtByConditionsWithTx(UserExtInDTO userExtInDTO)
			throws OuserMangeException, SQLException;
}
