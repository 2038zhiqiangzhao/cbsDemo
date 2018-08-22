/**
 *
 */
package com.people2000.user.business.write.manage;

import java.util.List;
import java.util.Map;

import com.people2000.user.model.dto.FunctionDto;
import com.people2000.user.model.dto.FunctionTreeDTO;
import com.people2000.user.model.exception.OuserMangeException;
import com.people2000.user.model.po.ibatis.Function;
import com.people2000.user.model.vo.FunctionVO;

/**
 * @author crc
 * @time 2015-5-14 下午6:25:01
 * @description <pre>
 * <p/>
 * </pre>
 */
public interface FunctionWriteManage {

	/**
	 * @return
	 * @throws Exception
	 * @description <pre>
	 * 获取所有的权限
	 * </pre>
	 */
	List<Function> getAllFunction(int type) throws Exception;

	/**
	 * @return
	 * @throws Exception
	 * @description <pre>
	 * 根据平台获取所有的权限
	 * </pre>
	 */
	List<Function> getAllFunctionByPlatformId(Long platformId) throws Exception;

	/**
	 * @return
	 * @throws Exception
	 * @description <pre>
	 * 根据平台获取所有的权限
	 * </pre>
	 */
	List<Function> getAllUserFunctionByPlatformId(FunctionVO functionVO)
			throws Exception;

	/**
	 *
	 * @param functionVO
	 * @return
	 * @throws Exception
	 *             查询用户的权限
	 */
	List<Function> getUserFunction(FunctionVO functionVO) throws Exception;

	/**
	 * @return
	 * @throws Exception
	 * @description <pre>
	 * 根据id查询对应权限
	 * </pre>
	 */
	Function getFunctionById(Long id) throws Exception;

	/**
	 * @param function
	 * @return
	 * @description <pre>
	 * 根据条件查询权限
	 * </pre>
	 */
	List<Function> getFunctionByQuery(Function function) throws Exception;

	/**
	 * @param function
	 * @return
	 * @description <pre>
	 * 根据条件查询权限（非模糊匹配）
	 * </pre>
	 */
	List<Function> getFunctionByExactQuery(Function function) throws Exception;

	/**
	 * @param functionDto
	 * @return
	 * @description <pre>
	 * 根据条件查询权限(分页)
	 * </pre>
	 */
	Map<String, Object> getFunctionPageByQuery(FunctionDto functionDto)
			throws Exception;

	void saveWithTx(Function function) throws Exception;

	void updateFunctionWithTx(Function function) throws Exception;

	public List<FunctionTreeDTO> getFunctionTreeByProductId(Long id)
			throws OuserMangeException;

	public List<FunctionTreeDTO> getFunctionTreeByProductId(String code)
			throws OuserMangeException;

	public List<FunctionTreeDTO> getFunctionTreeByPlatformIdAndCompanyId(
			Function function) throws Exception;

	/**
	 * 根据id列表查询权限
	 *
	 * @param functionIdList
	 * @param isAvailable
	 * @param companyId
	 * @return
	 * @throws Exception
	 */
	List<Function> getFunctionList(List<Long> functionIdList,
			Integer isAvailable, Long companyId) throws Exception;

}
