package org.joonzis.service;

import java.util.List;

import org.joonzis.domain.BoardAttachVO;
import org.joonzis.domain.BoardVO;
import org.joonzis.domain.Criteria;
import org.joonzis.mapper.BoardAttachMapper;
import org.joonzis.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Setter(onMethod_ = @Autowired)
	private BoardAttachMapper attachMapper;
	
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
	public int getTotalCount() {
		log.info("getTotalCount.....");
		return mapper.getTotalCount();
	}
	
	@Transactional
	@Override
	public void register(BoardVO vo) {
		log.info("register....." + vo);
		
		// 1. 게시글 등록
		mapper.insert(vo);
		// 2. 등록된 게시글의 bno 값을 가져온다.
		int bno = mapper.getBno();
		log.info(bno);
		
		// 3. tbl_attach 테이블에 데이터를 삽입한다.(반복문)
		if(vo.getAttachList() != null && vo.getAttachList().size() > 0) {
			for(BoardAttachVO vo2: vo.getAttachList()) {
				vo2.setBno(bno);
				attachMapper.insert(vo2);
			}
		}
	}  
	
	@Override
	public BoardVO get(long bno) {
		log.info("get....." + bno);
		
		BoardVO vo = mapper.read(bno); 
		vo.setAttachList(attachMapper.findByBno(bno));
		log.info("get.....attachListSize : " + vo.getAttachList().size());
		for(BoardAttachVO vo2 : vo.getAttachList()) {
			log.info("-------------fileName :" + vo2.getFileName());
		}
		
		return vo;
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
	
	@Override
	public List<BoardAttachVO> getAttachList(long bno) {
		return attachMapper.findByBno(bno);
	}
}
