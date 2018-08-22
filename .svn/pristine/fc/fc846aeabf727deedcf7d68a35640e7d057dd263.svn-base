package com.people.cbsadmin.business.dao.ext;
import java.util.List;
import com.people.cbsadmin.model.dto.UserDTO;
import com.people.cbsadmin.model.dto.UserQueryDTO;
import com.people.cbsadmin.model.po.User;
import com.people.cbsadmin.model.vo.UserQueryVo;
import com.people2000.user.model.po.ibatis.UserExample;

public interface UserMapperExt {
	/**
	 * 
	 * <p>根据条件查询列表</p> 
	 * @author dusai
	 * @date 2017年7月28日 上午10:20:31
	 * @param querys
	 * @return
	 * @see
	 */
	List<User> queryUserList(UserQueryVo querys);

	/**
	 * 
	 * <p>根据条件统计数量</p> 
	 * @author dusai
	 * @date 2017年7月28日 上午10:20:48
	 * @param querys
	 * @return
	 * @see
	 */
	int countUser(UserQueryVo querys);
	
	/**
	 * 
	 * <p>根据条件查询分页数据</p> 
	 * @author dusai
	 * @date 2017年7月28日 上午10:21:06
	 * @param querys
	 * @return
	 * @see
	 */
	List<User> queryUserPage(UserQueryVo querys);
	
	List<UserDTO> queryUserDetailPage(UserQueryVo querys);
	
	List<UserDTO> queryUserDetailList(UserQueryVo querys);
	
	UserDTO queryUserDetailById(Long userId);
	
	List<User> queryByCardNos(UserQueryDTO querys);
	
	User queryByCardNo(String cardNo);
	
	
	List<User> queryServerByproductId(Long productId);
	
	int clearUserGradeId(Long userId);

	List<com.people2000.user.model.po.ibatis.User> selectListUserByuser(
			com.people2000.user.model.po.ibatis.User user);

	int updateByExampleSelective(com.people2000.user.model.po.ibatis.User user,
			UserExample userExample);
	
	
}