package com.people2000.user.business.read.manage.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.people2000.user.business.read.dao.ext.UPlatformGroupRelationDAORead2;
import com.people2000.user.business.read.manage.PlatformGroupReadManage;
import com.people2000.user.model.dto.PlatformGroupRelationDTO;

@Service("platformGroupReadManage")
public class PlatformGroupReadManageImpl implements PlatformGroupReadManage {

	@Autowired
	private UPlatformGroupRelationDAORead2 uPlatformGroupRelationDAORead;

	@Override
	public List<PlatformGroupRelationDTO> queryPlatformGroupList(
			PlatformGroupRelationDTO dto) throws SQLException {
		Map<Long, String> platformMap = new HashMap<Long, String>();
		List<PlatformGroupRelationDTO> list = uPlatformGroupRelationDAORead
				.queryPlatformGroupList(dto);
		if (list != null && list.size() > 0) {
			for (PlatformGroupRelationDTO platformGroupRelationDTO : list) {
				platformGroupRelationDTO.setPlatformName(platformMap
						.get(platformGroupRelationDTO.getPlatformId()));
			}
		}
		return list;
	}

	@Override
	public List<PlatformGroupRelationDTO> queryPlatformGroupListOnly(
			PlatformGroupRelationDTO dto) throws SQLException {
		Map<Long, String> platformMap = new HashMap<Long, String>();
		List<PlatformGroupRelationDTO> list = uPlatformGroupRelationDAORead
				.queryPlatformGroupListOnly(dto);
		if (list != null && list.size() > 0) {
			for (PlatformGroupRelationDTO platformGroupRelationDTO : list) {
				platformGroupRelationDTO.setPlatformName(platformMap
						.get(platformGroupRelationDTO.getPlatformId()));
			}
		}
		return list;
	}

}
