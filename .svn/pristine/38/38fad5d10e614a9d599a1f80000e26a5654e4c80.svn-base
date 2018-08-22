package com.people2000.user.business.write.dao.ext;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.odianyun.common.ocache.CacheProxy;
import com.people2000.user.business.write.dao.UserIdentityCardDAOWriteImpl;
import com.people2000.user.model.dto.CertificationDTO;

@Repository("userIdentityCardDAOWrite")
public class UserIdentityCardDAOWriteImpl2 extends UserIdentityCardDAOWriteImpl
		implements UserIdentityCardDAOWrite2 {

	@Autowired
	public UserIdentityCardDAOWriteImpl2(SqlMapClient sqlMapClientWrite) {
		super(sqlMapClientWrite);
	}

	// @Autowired
	public void setCache(CacheProxy cache) {
		this.cache = cache;
	}

	@Override
	public CertificationDTO getCertificationById(Long id) throws SQLException {
		return (CertificationDTO) sqlMapClient.queryForObject(
				"u_user_identity_card2.getCertificationById", id);
	}

	@Override
	public List<CertificationDTO> getCertificationPageByQuery(
			CertificationDTO certificationDTO) throws SQLException {
		return sqlMapClient.queryForList(
				"u_user_identity_card2.getCertificationPageByQuery",
				certificationDTO);
	}

	@Override
	public int getCountByCondition(CertificationDTO certificationDTO)
			throws SQLException {
		Integer count = (Integer) sqlMapClient
				.queryForObject("u_user_identity_card2.getCertificationCount",
						certificationDTO);
		return count.intValue();
	}
}
