package com.test.secu.user.vo;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class UserInfoVO implements UserDetails{
	private int uiNum;
    private String uiName;
    private String uiId;
    private String uiPwd;
    private String uiMobile;
    private String uiEmail;
    private String uiBirth;
    private String credat;
    private String lmodat;
    private String active;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {	
		return Collections.singletonList(new SimpleGrantedAuthority("USER_ROLE"));
	}
	@Override
	public String getPassword() {		
		return uiPwd;
	}
	@Override
	public String getUsername() {		
		return uiId;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {	
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {	
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
}
