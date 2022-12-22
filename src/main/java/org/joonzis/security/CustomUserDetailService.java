package org.joonzis.security;

import org.joonzis.domain.MemberVO;
import org.joonzis.mapper.MemberMapper;
import org.joonzis.security.domain.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailService implements UserDetailsService {
//	security context에서 관리할 bean 객체 UserDetailsService를 상속한다.
	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.warn("load user by username: " + username);
		
		MemberVO member = mapper.read(username);
		
		log.warn("member mapper : " + member);
		
		return member == null? null : new CustomUser(member);
	}
}
