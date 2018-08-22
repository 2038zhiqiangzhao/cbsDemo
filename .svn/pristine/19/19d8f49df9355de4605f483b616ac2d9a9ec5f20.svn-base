package com.people2000.user.business.write.manage.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.people2000.user.business.read.dao.UPlatformGroupDAORead;
import com.people2000.user.business.read.dao.UPlatformGroupRelationDAORead;
import com.people2000.user.business.write.dao.UPlatformGroupDAOWrite;
import com.people2000.user.business.write.dao.UPlatformGroupRelationDAOWrite;
import com.people2000.user.business.write.dao.ext.UPlatformGroupRelationDAOWrite2;
import com.people2000.user.business.write.manage.PlatformGroupWriteManage;
import com.people2000.user.model.dto.PlatformGroupDTO;
import com.people2000.user.model.po.ibatis.UPlatformGroupPO;
import com.people2000.user.model.po.ibatis.UPlatformGroupPOExample;
import com.people2000.user.model.po.ibatis.UPlatformGroupPOExample.Criteria;
import com.people2000.user.model.po.ibatis.UPlatformGroupRelationPO;
import com.people2000.user.model.po.ibatis.UPlatformGroupRelationPOExample;

@Service("platformGroupWriteManage")
public class PlatformGroupWriteManageImpl implements PlatformGroupWriteManage {

	@Autowired
	private UPlatformGroupDAORead uPlatformGroupDAORead;

	@Autowired
	private UPlatformGroupDAOWrite uPlatformGroupDAOWrite;

	@Autowired
	private UPlatformGroupRelationDAORead uPlatformGroupRelationDAORead;

	@Autowired
	private UPlatformGroupRelationDAOWrite uPlatformGroupRelationDAOWrite;

	@Autowired
	private UPlatformGroupRelationDAOWrite2 uPlatformGroupRelationDAOWrite2;

	@Override
	public int addPlatformGroupWithTx(PlatformGroupDTO dto) throws SQLException {
		UPlatformGroupPOExample example = new UPlatformGroupPOExample();
		Criteria c = example.createCriteria();
		c.andPlatformGroupNameEqualTo(dto.getPlatformGroupName());
		List<UPlatformGroupPO> list = uPlatformGroupDAORead.selectByExample(
				example, false);
		if (list != null && list.size() > 0) {
			return -1;
		}
		UPlatformGroupRelationPOExample example1 = new UPlatformGroupRelationPOExample();
		UPlatformGroupRelationPOExample.Criteria c1 = example1.createCriteria();
		c1.andPlatformIdIn(dto.getPlatformIds());
		List<UPlatformGroupRelationPO> list1 = uPlatformGroupRelationDAORead
				.selectByExample(example1, false);
		if (list1 != null && list1.size() > 0) {
			return -2;
		}
		UPlatformGroupPO platformGroupPO = new UPlatformGroupPO();
		platformGroupPO.setPlatformGroupName(dto.getPlatformGroupName());
		platformGroupPO.setIsAvailable(1);
		Long platformGroupId = uPlatformGroupDAOWrite.insert(platformGroupPO);
		List<Long> platformIdList = dto.getPlatformIds();
		for (Long long1 : platformIdList) {
			UPlatformGroupRelationPO po = new UPlatformGroupRelationPO();
			po.setPlatformGroupId(platformGroupId);
			po.setPlatformId(long1);
			po.setIsAvailable(1);
			uPlatformGroupRelationDAOWrite.insert(po);
		}
		return 0;
	}

	@Override
	public int modifyPlatformGroupWithTx(PlatformGroupDTO dto)
			throws SQLException {
		UPlatformGroupPOExample example = new UPlatformGroupPOExample();
		Criteria c = example.createCriteria();
		c.andPlatformGroupNameEqualTo(dto.getPlatformGroupName());
		c.andIdNotEqualTo(dto.getPlatformGroupId());
		List<UPlatformGroupPO> list = uPlatformGroupDAORead.selectByExample(
				example, false);
		if (list != null && list.size() > 0) {
			return -1;
		}
		UPlatformGroupRelationPOExample example1 = new UPlatformGroupRelationPOExample();
		UPlatformGroupRelationPOExample.Criteria c1 = example1.createCriteria();
		c1.andPlatformIdIn(dto.getPlatformIds());
		c1.andPlatformGroupIdNotEqualTo(dto.getPlatformGroupId());
		List<UPlatformGroupRelationPO> list1 = uPlatformGroupRelationDAORead
				.selectByExample(example1, false);
		if (list1 != null && list1.size() > 0) {
			return -2;
		}
		UPlatformGroupPO platformGroupPO = new UPlatformGroupPO();
		platformGroupPO.setId(dto.getPlatformGroupId());
		platformGroupPO.setPlatformGroupName(dto.getPlatformGroupName());
		platformGroupPO.setIsAvailable(1);
		uPlatformGroupDAOWrite.updateByPrimaryKeySelective(platformGroupPO);
		UPlatformGroupRelationPO delPO = new UPlatformGroupRelationPO();
		delPO.setPlatformGroupId(dto.getPlatformGroupId());
		uPlatformGroupRelationDAOWrite2.delete(delPO);
		List<Long> platformIdList = dto.getPlatformIds();
		for (Long long1 : platformIdList) {
			UPlatformGroupRelationPO po = new UPlatformGroupRelationPO();
			po.setPlatformGroupId(dto.getPlatformGroupId());
			po.setPlatformId(long1);
			po.setIsAvailable(1);
			uPlatformGroupRelationDAOWrite.insert(po);
		}
		return 0;
	}

}
