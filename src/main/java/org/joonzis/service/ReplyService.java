package org.joonzis.service;

import java.util.List;

import org.joonzis.domain.ReplyVO;

public interface ReplyService {
	public int register(ReplyVO vo);
	public List<ReplyVO> getList(long bno);
	public ReplyVO get(long rno);
	public int remove(long rno);
	public int modify(ReplyVO vo);
}
