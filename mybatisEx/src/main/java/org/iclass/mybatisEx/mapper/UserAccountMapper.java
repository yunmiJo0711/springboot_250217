package org.iclass.mybatisEx.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.mybatisEx.dto.UserAccount;

@Mapper
public interface UserAccountMapper {
	UserAccount selectForLogin(Map<String,String> map);
	int insert(UserAccount dto);
}
