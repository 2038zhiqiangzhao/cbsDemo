package com.people2000.user.business.write.dao.ext;

import java.sql.SQLException;
import java.util.List;

import com.people2000.user.business.write.dao.UserIdentityCardDAOWrite;
import com.people2000.user.model.dto.CertificationDTO;

/**
 * Created by xiaole on 2015/11/16.
 */
public interface UserIdentityCardDAOWrite2 extends UserIdentityCardDAOWrite {

	public CertificationDTO getCertificationById(Long id) throws SQLException;

	public List<CertificationDTO> getCertificationPageByQuery(
			CertificationDTO certificationDTO) throws SQLException;

	public int getCountByCondition(CertificationDTO certificationDTO)
			throws SQLException;
}
