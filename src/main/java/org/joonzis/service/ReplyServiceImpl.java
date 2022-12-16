package org.joonzis.service;

import java.util.List;

import org.joonzis.domain.ReplyVO;
import org.joonzis.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService{
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Override
	public List<ReplyVO> getList(long bno) {
		return mapper.getList(bno);
	}

	@Override
	public ReplyVO get(long rno) {
		log.info("get...." + rno);
		return mapper.read(rno);
	}
	
	@Override
	public int register(ReplyVO vo) {
		log.info("register" + vo);
		return mapper.insert(vo);
	}
	
	@Override
	public int modify(ReplyVO vo) {
		return mapper.update(vo);
	}
	
	@Override
	public int remove(long rno) {
		log.info("remove...." + rno);
		return mapper.remove(rno);
	}
}
