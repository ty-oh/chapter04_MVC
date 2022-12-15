package org.joonzis.controller;

import org.joonzis.domain.BoardVO;
import org.joonzis.domain.Criteria;
import org.joonzis.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	 */
	@GetMapping("/list")
	public String list(Criteria cri , Model model) {
		log.info("list..." + cri);
		model.addAttribute("list", service.getListWithPaging(cri));
		
		return "board/list";
	}
	
	@PostMapping("/register")
	public String register(BoardVO vo, RedirectAttributes rttr) {
		log.info("register....." + vo);
		service.register(vo);
		
		rttr.addFlashAttribute("result", "ok");
		return "redirect:/board/list";
	}
	
	@GetMapping("/register")
	public String register() {
		return "board/register";
	}
	
	@GetMapping("/get")
	public String get(@RequestParam("bno") long bno, Model model) {
		log.info("get...");
		
		model.addAttribute("vo", service.get(bno));
		return "/board/get";
	}
	
	@GetMapping("/modify")
	public String modify(@RequestParam("bno") long bno, Model model) {
		log.info("/modify...");
		
		model.addAttribute("vo", service.get(bno));
		return "/board/modify";
	}
	
	//수정 // 삭제 기능 필요
	//메소드 2개 작성
	// 수정 or 삭제 후에 list로 이동
	// list에 가기 전에 result값을 success를 넣어주도록 하자
	// @GetMapping 의 리턴값은 jsp이다.
	// @PostMapping의 리턴은 url이다.
	@PostMapping("/modify")
	public String modify(BoardVO vo, RedirectAttributes rttr) {
		log.info("/modify...." + vo);
		
		boolean result = service.modify(vo);
		if (result) {
			rttr.addFlashAttribute("result", "success");
		} else {
			rttr.addFlashAttribute("result", "");
		}
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") long bno, RedirectAttributes rttr) {
		log.info("remove...");
		
		boolean result = service.remove(bno);
		if (result) {
			rttr.addFlashAttribute("result", "success");
		} else {
			rttr.addFlashAttribute("result", "");
		}
		return "redirect:/board/list";
	}
}
