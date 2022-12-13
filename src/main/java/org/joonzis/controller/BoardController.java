package org.joonzis.controller;

import org.joonzis.domain.BoardVO;
import org.joonzis.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor // 생성자 방식으로 service 객체를 생성한다.
public class BoardController {
	
	private BoardService service;
	
	/*
	 * spring에서는 GetMapping을 사용하는 경우, 
	 * 메소드의 return 값이 void인경우에 알아서 같은 경로의 jsp를 찾아간다.
	 * 
	*/
	@GetMapping("/list")
	public String list(Model model) {
		log.info("list");
		model.addAttribute("list", service.getList());
		
		return "board/list";
	}
	
	@PostMapping("/register")
	public String register(BoardVO vo, RedirectAttributes rttr) {
		log.info("register....." + vo);
		service.register(vo);
		
		rttr.addFlashAttribute("result", vo);
		return "redirect:/board/list";
	}
	
	
}
