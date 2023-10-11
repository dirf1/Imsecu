package com.test.secu.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.secu.board.service.BoardInfoService;
import com.test.secu.board.vo.BoardInfoVO;
import com.test.secu.board.vo.MsgVO;
import com.test.secu.vo.UserInfoVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BoardInfoController {
	@Value("${upload.file-path}")
	private String filePath;
	
	@Autowired
    private BoardInfoService boardService;
	
	@PostMapping("/login")
    public MsgVO login(@RequestBody BoardInfoVO user, MsgVO msg, HttpSession session) {
        BoardInfoVO loginUser = boardService.login(user);
        msg.setMsg("아이디나 비밀번호를 확인하세요");
        if (loginUser != null) {
            session.setAttribute("user", loginUser);
            msg.setMsg("로그인이 성공하였습니다.");
            msg.setUrl("/");
            msg.setSuccess(true);
        }
        return msg;
    }
	@PostMapping("/board-infos")
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
		}

		return 1;
	}
}
