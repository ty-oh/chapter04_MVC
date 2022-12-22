package org.joonzis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.joonzis.domain.BoardVO;
import org.joonzis.domain.Criteria;

public interface BoardMapper {
	// 전체 목록 가져오기
	public List<BoardVO> getList();
	
	// 전체 목록 가져오기 (페이징 처리)
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	
	// 테이블 내 전체 데이터 개수 구하기
	public int getTotalCount();
	
	// 방금 생성한 게시글 Bno값 가져오기
	public int getBno();
	
	//데이터 삽입
	public void insert(BoardVO board);
	
	// 한 개 데이터 가져오기 read(long bno)
	public BoardVO read(long bno);
	
	// 데이터 수정 update(BoardVO vo)
	public int update(BoardVO vo);
	
	// 데이터 삭제 delete(long bno)
	public int delete(long bno);
	
	//replycnt 값 변경
	// Mapper의 매개변수는 원칙적으로 하나만 받을수 있으나 spring에서 @Param으로 여러개의 매개변수를 지원함. 
	public void updateReplyCnt(@Param("bno") long bno, @Param("amount") int amount);
}
