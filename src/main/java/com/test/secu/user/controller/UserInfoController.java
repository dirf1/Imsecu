package com.test.secu.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.secu.user.service.UserInfoService;
import com.test.secu.user.vo.UserInfoVO;

@RestController
public class UserInfoController {
	@Autowired
	private UserInfoService userService;
	@PostMapping("/join")
	public UserInfoVO join(@RequestBody UserInfoVO user) {
		if(userService.join(user)==1) {
			user = userService.loadUserByUsername(user.getUiId());
		}
		return user;
	}
}
