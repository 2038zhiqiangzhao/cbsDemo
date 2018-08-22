package com.people.cbsadmin.business.dao;

import com.people.cbsadmin.model.po.CommunityActivityAdmin;



public interface CommunityActivityAdminMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommunityActivityAdmin record);

    int insertSelective(CommunityActivityAdmin record);

    CommunityActivityAdmin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommunityActivityAdmin record);

    int updateByPrimaryKey(CommunityActivityAdmin record);
}