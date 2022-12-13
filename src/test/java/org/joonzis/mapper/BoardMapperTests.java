package org.joonzis.mapper;

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
//ContextConfiguration : 스프링 컨테이너를 가져오는 역할. 의존성을 주입하고 Bean을 가져올수 있다.
//루트 컨텍스트를 활용하기 위함
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
// 로그 목적
@Log4j
public class BoardMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	/*
	 * Container Bean으로 객체 생성을 할때 리소스 낭비가 발생할 수 있기 때믄에
	 * VO와 같은 단순 데이터를 담기 위해 객체를 생성하고 지우는 경우는 컨테이너에서
	 * 생성해서 사용하지 않는 것이 좋다. 
	 *
	 * 그러므로 VO를 자주 사용할 것같은 경우를 제외하고 Service나 Dao와 같은
	 * 객체들만 conainer로 생성하는 것이 좋다.
	*/
	@Setter(onMethod_ = @Autowired)
	private BoardVO vo;
	
	/*
	@Test
	public void testGetList() {
		List<BoardVO> list = mapper.getList();
		for(BoardVO vo : list) {
			log.info(vo);
		}
	}
	*/
	/*
	@Test
	public void testInsert() {
		//임시 데이터로 삽입 테스트 후 vo 출력
		vo.setTitle("테스트용 제목");
		vo.setContent("테스트용 내용");
		vo.setWriter("테스트 작성자");
		
		mapper.insert(vo);
		log.info(vo);
		
	}
	*/
	/*
	@Test
	public void testRead() {
		long TestBno = 1;
		vo = mapper.read(TestBno);
		log.info(vo);
	}
	*/
	/*
	@Test
	public void testUpdate() {
		vo.setBno(1);
		vo.setTitle("변경한 제목");
		vo.setContent("변경한 내용");
		vo.setWriter("변경한 작성자");
		
		int result = mapper.update(vo);
		log.info(result);
	}
	*/
	/*
	@Test
	public void testDelete() {
		int result = mapper.delete(1);
		log.info(result);
	}
	*/
}