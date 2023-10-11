package com.test.secu.board.mapper;

import com.test.secu.board.vo.BoardInfoVO;

public interface BoardInfoMapper {
	BoardInfoVO selectBoardInfoByBiNum(String biNum);
}
