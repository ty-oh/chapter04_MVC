package org.joonzis.service;

import java.util.List;

import org.joonzis.domain.BoardVO;
import org.joonzis.domain.Criteria;

public interface BoardService {
	
	public List<BoardVO> getList();
	public List<BoardVO> getListWithPaging(Criteria cri);
	public int getTotalCount();
	public void register(BoardVO vo);
	public BoardVO get(long bno);
	public boolean modify(BoardVO vo);
	public boolean remove(long bno);
}
