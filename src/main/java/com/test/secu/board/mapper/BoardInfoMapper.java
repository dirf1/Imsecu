package com.test.secu.board.mapper;

import java.util.List;

import com.test.secu.board.vo.BoardInfoVO;

public interface BoardInfoMapper {	
	int insertBoardInfo(BoardInfoVO board);
	
	BoardInfoVO selectBoardInfo(int biNum);
	
	List<BoardInfoVO> selectBoardInfos(BoardInfoVO board);
	
	int selectBoardInfoCnt(BoardInfoVO board);
}
