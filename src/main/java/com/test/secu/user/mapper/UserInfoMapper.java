package com.test.secu.user.mapper;


import com.test.secu.user.vo.UserInfoVO;

public interface UserInfoMapper {
	UserInfoVO selectUserInfoByUiId(String uiId);
	
	int insertUserInfo(UserInfoVO user);
	int updateUserInfoUiPwd(UserInfoVO user);
}
