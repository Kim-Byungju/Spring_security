package com.cos.security1.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.security1.model.User;

//시큐리티가 /login 주소 요청이 오면 낚ㅅ아채서 로그인을 진행시킨다
//로그인을 진행이 완료가되면 시큐리티 session을 만들어준다.(Security ContextHolder)
//오브젝트 =>Authentication타입 객체
//Authentication안에 User정보가 있어야됨
//User 오브젝트 타입 ->UserDetails 타입 객체

//Security Session -> Authentication -> UserDetails(PrincipalDetails)

public class PrincipalDetails implements UserDetails {
	
	private User user; //콤포지션
	
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	
	//해당유저에 권한을 리턴하는 곳!!
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
			
				return user.getRole();
			}
		});
		return collect;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
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
		
		//우리사이트!! 1년동안 회원이 로그인을 안하면 휴먼계정을 하기로함.
		//현재시간 - 로그인시간 => 1년을 초과하면 return false;
		
		return true;
	}
	
	

}
