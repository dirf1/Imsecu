package com.test.secu.board.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.secu.board.mapper.BoardInfoMapper;
import com.test.secu.board.vo.BoardInfoVO;
import com.test.secu.common.vo.ResponsePageVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardInfoService {
	@Value("${upload.file-path}")
	private String filePath;

	@Autowired
	private BoardInfoMapper boardMapper;

	public BoardInfoVO addBoard(BoardInfoVO board) throws IllegalStateException, IOException {
		String fileName = board.getFile().getOriginalFilename();
		if (!fileName.isEmpty()) {
			String uuid = UUID.randomUUID().toString();
			int idx = fileName.lastIndexOf(".");
			String extName = fileName.substring(idx);
			String saveFilePath = filePath + "/" + uuid + extName;
			File file = new File(saveFilePath);
			board.getFile().transferTo(file);
			log.info("fileName=>{}", fileName);
			board.setBiFileName(fileName);
			board.setBiFilePath(uuid + extName);
		}
		boardMapper.insertBoardInfo(board);
		if (board.getBiNum() != 0) {
			return boardMapper.selectBoardInfo(board.getBiNum());
		}
		return null;
	}

	public BoardInfoVO getBoardInfo(int biNum) {
		return boardMapper.selectBoardInfo(biNum);
	}

	public ResponsePageVO<BoardInfoVO> selectBoardInfos(BoardInfoVO board) {
		board.setEnd(board.getPageSize());
		int start = (board.getPage() - 1) * board.getPageSize();
		board.setStart(start);

		ResponsePageVO<BoardInfoVO> resVO = new ResponsePageVO<>();
		resVO.setList(boardMapper.selectBoardInfos(board));
		resVO.setTotalCnt(boardMapper.selectBoardInfoCnt(board));
		return resVO;
	}
	
	public PageInfo<BoardInfoVO> selectBoardInfosWithHelper(BoardInfoVO board) {
		PageHelper.startPage(board.getPage(), board.getPageSize());
		return new PageInfo<>(boardMapper.selectBoardInfosWithHelper(board));
	}
}
