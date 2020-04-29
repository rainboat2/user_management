package com.neu.provider3.dao;

import com.neu.provider3.bean.TbUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbUser record);

    int insertSelective(TbUser record);

    TbUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbUser record);

    int updateByPrimaryKey(TbUser record);

    List<TbUser> selectBy(TbUser tbUser);
}