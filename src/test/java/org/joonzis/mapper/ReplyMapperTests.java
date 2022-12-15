package org.joonzis.mapper;

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
public class ReplyMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
//	@Test
//	public void testInsert() {
//		//임시 데이터로 삽입 테스트 후 vo 출력
//		ReplyVO rvo = new ReplyVO();
//		rvo.setBno(11254);
//		rvo.setReply("좋은아침");
//		rvo.setReplyer("메시");
//		
//		mapper.insert(rvo);
//		log.info(rvo);
//	}
	
//	@Test
//	public void testGetList() {
//		List<ReplyVO> list = mapper.getList(11254);
//		log.info(list);
//	}
	
//	@Test
//	public void testRead() {
//		ReplyVO rvo = mapper.read(1);
//		log.info(rvo);
//	}
	
//	@Test
//	public void testRemove() {
//		int result = mapper.remove(2);
//		log.info(result==1);
//	}
	
//	@Test
//	public void testUpdate() {
//		ReplyVO rvo = new ReplyVO();
//		rvo.setRno(1);
//		rvo.setReply("안녕하세요 반갑습니다.");
//		
//		int result = mapper.update(rvo);
//		log.info(result==1);
//	}
}