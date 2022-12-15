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
		return null;
	}
	
	@Override
	public int register(ReplyVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int modify(ReplyVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int remove(long rno) {
		// TODO Auto-generated method stub
		return 0;
	}
}
