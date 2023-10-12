package com.test.secu.board.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.test.secu.board.service.BoardInfoService;
import com.test.secu.board.vo.BoardInfoVO;


@RestController
public class BoardInfoController {
	@Value("${upload.file-path}")
	private String filePath;

	@Autowired
	private BoardInfoService boardService;


	@PostMapping("/board-infos")
	public BoardInfoVO addBoard(BoardInfoVO board) throws IllegalStateException, IOException {
		return boardService.addBoard(board);
	}
	
	@GetMapping("/board-infos")
	public List<BoardInfoVO> getBoardInfos(BoardInfoVO board){
		return boardService.selectBoardInfos(board);
	}
}
