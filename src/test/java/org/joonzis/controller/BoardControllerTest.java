package org.joonzis.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

// Controller 테스트 코드
// 웹을 개발 하면서 매번 URL을 테스트 하기 위해
// Tomcat과 같은 WAS를 실행하는 불편한 단계를 생략할 수 있도록 코드를 작성 

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Log4j
// 테스트에 필요한 controller 객체를 가지고있는 servlet-context 컨테이너와 service 객체를 가지고 있는 root-context 컨테이너  
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
					   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class BoardControllerTest {
	//
	//뷰를 테스트 하기 위한 셋업 
	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext ctx;
	private MockMvc mockMvc; // 가짜 MVC를 이용할 수 있게끔 한다.
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testList() throws Exception{
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
				.andReturn()
				.getModelAndView()
				.getModelMap()
				);
	}
	
	@Test
	public void testRegister() throws Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
				.param("title", "테스트 새글 제목")
				.param("content", "테스트 새글 내용")
				.param("writer", "user00"))
				.andReturn()
				.getModelAndView()
				.getViewName();
		log.info(result);
	}
	
	
}
