package org.joonzis.service;

import java.util.List;

import org.joonzis.domain.BoardVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	//service 의존성 주입
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	/*
	@Test
	public void testGetList() {
		List<BoardVO> list = service.getList();
		for( BoardVO vo : list) {
			log.info(vo);
		}
	}
	*/
	
	/*
	@Test
	public void testRegister() {
		BoardVO vo = new BoardVO();
		vo.setTitle("serviceTestTitle");
		vo.setContent("serviceTestContent");
		vo.setWriter("serviceTestWriter");
		service.register(vo);
		
		log.info(vo);
	}
	*/
	/*
	@Test
	public void testGet() {
		BoardVO vo = service.get(1);
		log.info(vo);
	}
	*/
	
	/*
	@Test
	public void testModify() {
		BoardVO vo = new BoardVO();
		vo.setBno(3);
		vo.setTitle("test_service_modify_title");
		vo.setContent("test_service_modify_content");
		vo.setWriter("test_service_modify_writer");
		
		boolean result = service.modify(vo);
		log.info(result);
	}
	*/
	
	/*
	@Test
	public void testRemove() {
		boolean result = service.remove(4);
		log.info(result);
	}
	*/
}

