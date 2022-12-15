package org.joonzis.mapper;

import java.util.List;

import org.joonzis.domain.ReplyVO;

public interface ReplyMapper {
	//댓글 삽입
	public int insert(ReplyVO vo);
	// 댓글 목록
	public List<ReplyVO> getList(long bno);
	// 댓글 읽기
	public ReplyVO read(long rno);
	// 댓길 삭제
	public int remove(long rno);
	// 댓글 수정
	public int update(ReplyVO vo);
}
