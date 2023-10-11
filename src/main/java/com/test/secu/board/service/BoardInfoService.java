package com.test.secu.board.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.test.secu.board.vo.BoardInfoVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardInfoService {
			@Value("${upload.file-path}")
			private String filePath;
			
			public int addBoard(BoardInfoVO board) throws IllegalStateException, IOException {
				log.info("filePath=>{}", filePath);
				log.info("board=>{}", board);
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
					board.setBiFilePath(saveFilePath);
				}
				return 1;
			}
}
