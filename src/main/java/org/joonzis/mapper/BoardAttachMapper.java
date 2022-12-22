package org.joonzis.mapper;

import java.util.List;

import org.joonzis.domain.BoardAttachVO;

public interface BoardAttachMapper {
	public void insert(BoardAttachVO vo);
	public void delete(String uuid);
	public List<BoardAttachVO> findByBno(long bno);
	// 첨부 파일의 경우 수정 개념이 없기 때문에 삽입, 삭제만 처리한다.
}
