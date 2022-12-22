package org.joonzis.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/sample/*")
public class SampleController {
	@GetMapping("/all")
	public String doAll() {
		log.info("do all can access everybody!!!");
		return "sample/all";
	}
	
	@GetMapping("/member")
	public String doMember() {
		log.info("logined member......");
		return "/sample/member";
	}
	
	@GetMapping("/admin")
	public String doAdmin() {
		log.info("admin only");
		return "/sample/admin";
	}
	

	/*
	 * @Secured - 스프링 시큐리티 초기부터 사용되었고, ()안에 'ROLE_ADMIN'과 같은 문자열 혹은 문자 배열을 이용
	 * @PreAuthrize, @PostAuthize - 3버전 부터 지원되며, ()안에 표현식을 사용할 수 있으므로 최근에 많이 사용
	 * 
	 * **** 주의 사항 - 컨트롤러에서 사용하는 시큐리티의 어노테이션을 활성화 하기 위해서는
	 * security-context.xml이 아닌 스프링 MVC의 설정을 담당하는 servlet-context.xml에 설정을 추가해야 함.
	*/	
	//	hasRole([role])				해당 권한이 있으면 true
	//	hasAuthority([authority])
	//	
	//	hasAnyRole([role, role2]) 	여러 권한들 중 하나라도 해당하는 권한이 있으면 true
	//	hasAnyAuthority([authority])
	//	
	//	principal 				현재 사용자 정보를 의미
	//	permitAll				모든 사용자에게 허용
	//	denyAll					모든 사용자에게 거부
	//	isAnonymous()			익명의 사용자의 경우(로그인을 하지 않은 경ㅇ우)
	//	isAuthenticated()		인증된 사용자라면 true
	//	isFullyAuthenticated()	Remember-me 로 인증된 것이 아닌 사용자의 경우 true
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	@GetMapping("/annoMember")
	public void domember2() {
		log.info("로그인 어노테이션 멤버");
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/annoAdmin")
	public void doAdmin2() {
		log.info("어드민 어노테이션");
	}
}
