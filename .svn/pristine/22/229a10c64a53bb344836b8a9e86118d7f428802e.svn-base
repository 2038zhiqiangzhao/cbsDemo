/**
 *
 */
package com.people2000.user.business.write.manage.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.people2000.common.log.LogUtils;
import com.people2000.user.business.utils.UserUtil;
import com.people2000.user.business.write.dao.ext.FunctionDAOWrite2;
import com.people2000.user.business.write.manage.FunctionWriteManage;
import com.people2000.user.model.constant.ConstantUser;
import com.people2000.user.model.dto.FunctionDto;
import com.people2000.user.model.dto.FunctionTreeDTO;
import com.people2000.user.model.exception.OuserMangeException;
import com.people2000.user.model.po.ibatis.Function;
import com.people2000.user.model.po.ibatis.FunctionExample;
import com.people2000.user.model.po.ibatis.FunctionExample.Criteria;
import com.people2000.user.model.vo.FunctionVO;

/**
 * @author crc
 * @time 2015-5-14 下午6:25:33
 * @description <pre>
 * 权限管理
 * </pre>
 */
@Service("functionWriteManage")
public class FunctionWriteManageImpl implements FunctionWriteManage {

	@Resource(name = "functionDAOWrite")
	private FunctionDAOWrite2 functionDAOWrite;

	@Override
	public List<Function> getAllFunction(int type) throws Exception {
		FunctionExample example = new FunctionExample();
		example.createCriteria().andProductTypeEqualTo(type);
		return functionDAOWrite.selectByExample(example, false);
	}

	@Override
	public List<Function> getAllFunctionByPlatformId(Long platformId)
			throws Exception {
		FunctionExample example = new FunctionExample();
		example.createCriteria().andPlatformIdEqualTo(platformId);
		return functionDAOWrite.selectByExample(example, false);
	}

	@Override
	public List<Function> getAllUserFunctionByPlatformId(FunctionVO functionVO)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", functionVO.getUserId());
		map.put("platformId", functionVO.getPlatformId());

		return functionDAOWrite.getAllFunctionByUserIdAndPlatformId(map);
	}

	@Override
	public List<Function> getUserFunction(FunctionVO functionVO)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != functionVO.getUserId()) {
			map.put("userId", functionVO.getUserId());
		}
		if (null != functionVO.getPlatformId()) {
			map.put("platformId", functionVO.getPlatformId());
		}

		if (!map.isEmpty()) {
			List<Function> list = functionDAOWrite.getUserFunction(map);
			return list;
		}
		return null;
	}

	@Override
	public Function getFunctionById(Long id) throws Exception {
		return functionDAOWrite.selectByPrimaryKey(id, false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Function> getFunctionByQuery(Function function)
			throws Exception {
		FunctionExample example = new FunctionExample();
		Criteria criteria = example.createCriteria();
		if (null != function.getId()) {
			criteria.andIdEqualTo(function.getId());
		}
		if (function.getProductType() != null) {
			criteria.andProductTypeEqualTo(function.getProductType());
		}
		if (function.getLevel() != null) {
			criteria.andLevelEqualTo(function.getLevel());
		}
		if (function.getParentCode() != null
				&& !"".equals(function.getParentCode())) {
			criteria.andParentCodeEqualTo(function.getParentCode());
		}
		if (function.getCode() != null && !"".equals(function.getCode())) {
			criteria.andCodeLike(function.getCode() + "%");
		}

		if (null != function.getPlatformId()) {
			criteria.andPlatformIdEqualTo(function.getPlatformId());
		}
		if (null != function.getType()) {
			criteria.andTypeEqualTo(function.getType());
		}

		if (StringUtils.isNotEmpty(function.getDomain())) {
			criteria.andDomainLike(function.getDomain() + "%");
		}

		if (StringUtils.isNotEmpty(function.getName())) {
			criteria.andNameLike(function.getName() + "%");
		}

		if (StringUtils.isNotEmpty(function.getPath())) {
			criteria.andPathLike(function.getPath() + "%");
		}

		return functionDAOWrite.selectByExample(example, false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Function> getFunctionByExactQuery(Function function)
			throws Exception {
		FunctionExample example = new FunctionExample();
		Criteria criteria = example.createCriteria();
		if (function.getProductType() != null) {
			criteria.andProductTypeEqualTo(function.getProductType());
		}
		if (function.getLevel() != null) {
			criteria.andLevelEqualTo(function.getLevel());
		}
		if (function.getId() != null) {
			criteria.andIdNotEqualTo(function.getId());
		}
		if (function.getParentCode() != null
				&& !"".equals(function.getParentCode())) {
			criteria.andParentCodeEqualTo(function.getParentCode());
		}
		if (function.getCode() != null && !"".equals(function.getCode())) {
			criteria.andCodeEqualTo(function.getCode());
		}

		if (null != function.getPlatformId()) {
			criteria.andPlatformIdEqualTo(function.getPlatformId());
		}
		if (null != function.getType()) {
			criteria.andTypeEqualTo(function.getType());
		}

		if (StringUtils.isNotEmpty(function.getDomain())) {
			criteria.andDomainEqualTo(function.getDomain());
		}

		if (StringUtils.isNotEmpty(function.getName())) {
			criteria.andNameEqualTo(function.getName());
		}

		if (StringUtils.isNotEmpty(function.getPath())) {
			criteria.andPathEqualTo(function.getPath());
		}
		return functionDAOWrite.selectByExample(example, false);
	}

	@Override
	public Map<String, Object> getFunctionPageByQuery(FunctionDto functionDto)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		FunctionExample example = new FunctionExample();
		Criteria criteria = example.createCriteria();
		if (functionDto.getProductType() != null) {
			criteria.andProductTypeEqualTo(functionDto.getProductType());
		}
		if (functionDto.getLevel() != null
				&& !"".equals(functionDto.getLevel())) {
			criteria.andLevelEqualTo(functionDto.getLevel());
		}
		if (functionDto.getType() != null && !"".equals(functionDto.getType())) {
			criteria.andTypeEqualTo(functionDto.getType());
		}
		if (functionDto.getParentCode() != null
				&& !"".equals(functionDto.getParentCode())) {
			criteria.andParentCodeEqualTo(functionDto.getParentCode());
		}
		if (functionDto.getCode() != null && !"".equals(functionDto.getCode())) {
			criteria.andCodeLike(functionDto.getCode() + "%");
		}
		if (functionDto.getName() != null && !"".equals(functionDto.getName())) {
			criteria.andNameLike(functionDto.getName() + "%");
		}
		int total = functionDAOWrite.countByExample(example, false);
		List<Function> list = new ArrayList<Function>();
		if (total != 0) {
			// 分页
			Integer currentPage = functionDto.getCurrentPage();
			Integer itemsPerPage = functionDto.getItemsPerPage();
			if (currentPage != null && itemsPerPage != null) {
				example.setOrderByClause(" code "
						+ UserUtil.getLimit(currentPage, itemsPerPage));
			}
			list = functionDAOWrite.selectByExample(example, false);
		}
		map.put("total", total);
		map.put("list", list);
		return map;
	}

	@Override
	public void saveWithTx(Function function) throws Exception {
		functionDAOWrite.insert(function);
	}

	@Override
	public void updateFunctionWithTx(Function function) throws Exception {
		Function record = new Function();
		record.setId(function.getId());
		record.setName(function.getName());
		record.setCode(function.getCode());
		record.setParentCode(function.getParentCode());
		record.setLevel(function.getLevel());
		record.setType(function.getType());
		record.setDomain(function.getDomain());
		record.setPath(function.getPath());
		record.setDesc(function.getDesc());
		functionDAOWrite.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<FunctionTreeDTO> getFunctionTreeByProductId(Long id)
			throws OuserMangeException {
		Long start = System.currentTimeMillis();
		ArrayList<FunctionTreeDTO> result = new ArrayList<FunctionTreeDTO>();
		List<FunctionTreeDTO> functionTreeDTOs = new ArrayList<FunctionTreeDTO>();
		FunctionTreeDTO functionTreeDTO = new FunctionTreeDTO();
		functionTreeDTO.setCode(String.valueOf(id));
		functionTreeDTOs.add(functionTreeDTO);
		for (int i = 0; i < 3; i++) {
			List<FunctionTreeDTO> list = getByParentCod(functionTreeDTOs);
			functionTreeDTOs = list;
			result.addAll(list);
		}
		Long end = System.currentTimeMillis();

		System.out.println("时间消耗" + (end - start));
		return result;
	}

	@Override
	public List<FunctionTreeDTO> getFunctionTreeByProductId(String code)
			throws OuserMangeException {
		Long start = System.currentTimeMillis();
		ArrayList<FunctionTreeDTO> result = new ArrayList<FunctionTreeDTO>();
		List<FunctionTreeDTO> functionTreeDTOs = new ArrayList<FunctionTreeDTO>();
		FunctionTreeDTO functionTreeDTO = new FunctionTreeDTO();
		functionTreeDTO.setCode(code);
		functionTreeDTOs.add(functionTreeDTO);
		for (int i = 0; i < 3; i++) {
			List<FunctionTreeDTO> list = getByParentCod(functionTreeDTOs);
			functionTreeDTOs = list;
			result.addAll(list);
		}
		Long end = System.currentTimeMillis();

		System.out.println("时间消耗" + (end - start));
		return result;
	}

	@Override
	public List<FunctionTreeDTO> getFunctionTreeByPlatformIdAndCompanyId(
			Function function) throws Exception {
		Long start = System.currentTimeMillis();
		ArrayList<FunctionTreeDTO> result = new ArrayList<FunctionTreeDTO>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", function.getCompanyId());
		params.put("platformId", function.getPlatformId());
		List<FunctionTreeDTO> list = functionDAOWrite
				.getAllMenuFunctionByPlatform(params);
		result.addAll(list);
		Long end = System.currentTimeMillis();
		System.out.println("时间消耗" + (end - start));
		return result;
	}

	/**
	 * 根据list获取下一级权限
	 *
	 * @param list
	 * @return
	 * @throws OuserMangeException
	 */
	public List<FunctionTreeDTO> getByParentCod(List<FunctionTreeDTO> list)
			throws OuserMangeException {
		List<FunctionTreeDTO> result;
		try {
			result = functionDAOWrite.getAllMenuFunctionByParentCode(list);
		} catch (SQLException e) {
			LogUtils.getLogger(FunctionWriteManageImpl.class).error(
					e.getMessage(), e);
			throw new OuserMangeException("", "查询权限树失败");
		}
		if (result == null) {
			result = new ArrayList<FunctionTreeDTO>();
		}
		return result;
	}

	@Override
	public List<Function> getFunctionList(List<Long> functionIdList,
			Integer isAvailable, Long companyId) throws Exception {
		FunctionExample example = new FunctionExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(functionIdList)
				.andTypeEqualTo(ConstantUser.function_type.menu)
				.andIsAvailableEqualTo(isAvailable)
				.andCompanyIdEqualTo(companyId);
		example.setOrderByClause("code asc");
		return functionDAOWrite.selectByExample(example, false);
	}
}
