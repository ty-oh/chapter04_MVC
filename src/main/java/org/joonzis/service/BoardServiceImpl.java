package org.joonzis.service;

import java.util.List;

import org.joonzis.domain.BoardVO;
import org.joonzis.domain.Criteria;
import org.joonzis.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
// 해당 계층이 service라는 것을 알려주기 위한 annotation
@Log4j
// 로그 출력할때 사용하는 annotation
public class BoardServiceImpl implements BoardService {
	
	//mapper 객체 의존성 주입
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Override
	public List<BoardVO> getList() {
		log.info("getList....."); // 로그를 찍어두는 습관을 가지자.
		return mapper.getList();
	}
	
	@Override
	public List<BoardVO> getListWithPaging(Criteria cri) {
		log.info("getListWithPaging....");
		return mapper.getListWithPaging(cri);
	}
	
	@Override
	public void register(BoardVO vo) {
		log.info("register....." + vo);
		mapper.insert(vo);
	}
	
	@Override
	public BoardVO get(long bno) {
		log.info("get....." + bno);
		return mapper.read(bno);
	}
	
	@Override
	public boolean modify(BoardVO vo) {
		log.info("modify....." + vo);
		
		int result = mapper.update(vo);
		return result == 1;
	}
	
	@Override
	public boolean remove(long bno) {
		log.info("remove....." + bno);
		
		return mapper.delete(bno) == 1;
	}
}
