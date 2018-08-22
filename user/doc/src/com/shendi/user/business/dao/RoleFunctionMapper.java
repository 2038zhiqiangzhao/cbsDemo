package com.people2000.user.business.dao;

import com.people2000.user.model.po.RoleFunction;

public interface RoleFunctionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoleFunction record);

    int insertSelective(RoleFunction record);

    RoleFunction selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleFunction record);

    int updateByPrimaryKey(RoleFunction record);
}