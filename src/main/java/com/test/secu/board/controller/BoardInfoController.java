package com.test.secu.board.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.test.secu.board.service.BoardInfoService;
import com.test.secu.board.vo.BoardInfoVO;
import com.test.secu.common.vo.ResponsePageVO;
import com.test.secu.vo.UserInfoVO;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class BoardInfoController {
	@Value("${upload.file-path}")
	private String filePath;

	@Autowired
	private BoardInfoService boardService;


	@PostMapping("/board-infos")
	public BoardInfoVO addBoard(BoardInfoVO board, @AuthenticationPrincipal UserInfoVO user) throws IllegalStateException, IOException {
		board.setUiNum(user.getUiNum());
		return boardService.addBoard(board);
	}
	
	@GetMapping("/board-infos")
	public ResponsePageVO<BoardInfoVO> getBoardInfos(BoardInfoVO board){
		board.setEnd(board.getPageSize());
		int start = (board.getPage()-1) * board.getPageSize();
		board.setStart(start);
		return boardService.selectBoardInfos(board);
	}
}
