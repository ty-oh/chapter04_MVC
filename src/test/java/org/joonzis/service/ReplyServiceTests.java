package org.joonzis.service;

import java.util.List;

import org.joonzis.domain.ReplyVO;
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
public class ReplyServiceTests {
	//service 의존성 주입
	@Setter(onMethod_ = @Autowired)
	private ReplyService service;
	
//	@Test
//	public void testGetList() {
//		List<ReplyVO> list = service.getList(11254);
//		for( ReplyVO vo : list) {
//			log.info(vo);
//		}
//	}
	
//	@Test
//	public void testGet() {
//		ReplyVO vo = service.get(1);
//		log.info(vo);
//	}
	
//	@Test
//	public void testRegister() {
//		ReplyVO rvo = new ReplyVO();
//		rvo.setBno(11254);
//		rvo.setReply("우승은 아르헨이다");
//		rvo.setReplyer("호날두");
//		
//		int result = service.register(rvo);
//		log.info(result==1);
//	}
	
//	@Test
//	public void testRemove() {
//		int result = service.remove(4);
//		log.info(result==1);
//	}
	
//	@Test
//	public void testModify() {
//		ReplyVO rvo = new ReplyVO();
//		rvo.setRno(5);
//		rvo.setReply("우승은 아르헨이다");
//		
//		int result = service.modify(rvo);
//		log.info(result==1);
//	}
	
}

