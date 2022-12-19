package org.joonzis.service;

import java.util.List;

import org.joonzis.domain.ReplyVO;
import org.joonzis.mapper.BoardMapper;
import org.joonzis.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService{
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;
	
	@Override
	public List<ReplyVO> getList(long bno) {
		return mapper.getList(bno);
	}

	@Override
	public ReplyVO get(long rno) {
		log.info("get...." + rno);
		return mapper.read(rno);
	}
	
	@Transactional
	@Override
	public int register(ReplyVO vo) {
		log.info("register" + vo);
		
		// replycnt 컬럼 1증가
		boardMapper.updateReplyCnt(vo.getBno(), 1);
		return mapper.insert(vo);
	}
	
	@Override
	public int modify(ReplyVO vo) {
		return mapper.update(vo);
	}
	
	@Transactional
	@Override
	public int remove(long rno) {
		log.info("remove...." + rno);
		ReplyVO vo = mapper.read(rno);
		boardMapper.updateReplyCnt(vo.getBno(), -1);
		return mapper.remove(rno);
	}
}
