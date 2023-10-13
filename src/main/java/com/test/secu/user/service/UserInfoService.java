package com.test.secu.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.secu.user.mapper.UserInfoMapper;
import com.test.secu.user.vo.UserInfoVO;

@Service
public class UserInfoService implements UserDetailsService{
	@Autowired
	private UserInfoMapper userMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public int join(UserInfoVO user) {
		user.setUiPwd(passwordEncoder.encode(user.getUiPwd()));
		return userMapper.insertUserInfo(user);
	}
	
	public int updateUserInfoUiPwd(UserInfoVO user) {
		user.setUiPwd(passwordEncoder.encode(user.getUiPwd()));
		return userMapper.updateUserInfoUiPwd(user);
	}
	
	@Override
	public UserInfoVO loadUserByUsername(String uiId) throws UsernameNotFoundException {
		UserInfoVO user= userMapper.selectUserInfoByUiId(uiId);
		if(user==null) {
			throw new UsernameNotFoundException("Error");
		}
		return user;
	}
	
	
}
