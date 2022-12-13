package org.joonzis.mapper;

import java.util.List;

import org.joonzis.domain.BoardVO;

public interface BoardMapper {
	// 전체 목록 가져오기
	public List<BoardVO> getList();
	
	//데이터 삽입
	public void insert(BoardVO board);
	
	// 한 개 데이터 가져오기 read(long bno)
	public BoardVO read(long bno);
	
	// 데이터 수정 update(BoardVO vo)
	public int update(BoardVO vo);
	
	// 데이터 삭제 delete(long bno)
	public int delete(long bno);
}
