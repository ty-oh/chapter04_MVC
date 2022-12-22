package org.joonzis.mapper;

import org.joonzis.domain.AuthVO;
import org.joonzis.domain.MemberVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
//ContextConfiguration : 스프링 컨테이너를 가져오는 역할. 의존성을 주입하고 Bean을 가져올수 있다.
//루트 컨텍스트를 활용하기 위함
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//로그 목적
@Log4j
public class MemberMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	MemberMapper mapper;
	
	@Test
	public void MemberReadTests() {
		MemberVO member = mapper.read("admin90");
		log.info(member);
		
		for(AuthVO auth: member.getAuthList()) {
			log.info(auth);
		}
	}
	
}
