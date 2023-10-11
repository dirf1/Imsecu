package com.test.secu.board.mapper;

import java.util.List;

import com.test.secu.board.vo.BoardInfoVO;

public interface BoardInfoMapper {
	BoardInfoVO selectBoardInfoByBiNum(BoardInfoVO board);
	
	List<BoardInfoVO> selectBoardInfos(BoardInfoVO user);
	
	int insertBoardInfo(BoardInfoVO board);
}
