package org.joonzis.controller;

import java.util.List;

import org.joonzis.domain.BoardAttachVO;
import org.joonzis.domain.BoardVO;
import org.joonzis.domain.Criteria;
import org.joonzis.domain.PageDTO;
import org.joonzis.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
		
		int total = service.getTotalCount();
		log.info("total..." + total);
		model.addAttribute("list", service.getListWithPaging(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, total));
		return "board/list";
	}
	
	@PostMapping("/register")
	public String register(BoardVO vo, RedirectAttributes rttr, Criteria cri) {
		log.info("register....." + vo);
		service.register(vo);
//		List<BoardAttachVO> list = vo.getAttachList();
//		if(list.size()>0) {
//			for(BoardAttachVO vo2 : list) {
//				log.info("------filename	 : " + vo2.getFileName());
//				log.info("------uploadPath	 : " + vo2.getUploadPath());
//				log.info("------getUuid		 : " + vo2.getUuid());
//			}
//		}
		
		rttr.addFlashAttribute("result", "ok");
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/register")
	public String register(Model model, Criteria cri) {
		model.addAttribute("cri", cri);
		return "board/register";
	}
	
	@GetMapping("/get")
	public String get(@RequestParam("bno") long bno, Criteria cri, Model model) {
		log.info("get...");
		model.addAttribute("vo", service.get(bno));
		model.addAttribute("cri", cri);
		return "/board/get";
	}
	
	@GetMapping("/modify")
	public String modify(@RequestParam("bno") long bno,
						 @ModelAttribute("cri") Criteria cri , Model model) {
		log.info("/modify...");
		
		model.addAttribute("vo", service.get(bno));
		return "/board/modify";
	}
	
	// list에 가기 전에 result값을 success를 넣어주도록 하자
	// @GetMapping 의 리턴값은 jsp이다.
	// @PostMapping의 리턴은 url이다.
	@PostMapping("/modify")
	public String modify(BoardVO vo, Criteria cri, RedirectAttributes rttr) {
		log.info("/modify...." + vo);
		
		boolean result = service.modify(vo);
		if (result) {
			rttr.addFlashAttribute("result", "success");
		} else {
			rttr.addFlashAttribute("result", "");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String remove(long bno, Criteria cri, RedirectAttributes rttr) {
		log.info("remove...");
		
		boolean result = service.remove(bno);
		if (result) {
			rttr.addFlashAttribute("result", "success");
		} else {
			rttr.addFlashAttribute("result", "");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		return "redirect:/board/list";
	}
	
	@GetMapping(value="/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> getAttachList(long bno) {
		return new ResponseEntity<List<BoardAttachVO>>(service.getAttachList(bno), HttpStatus.OK);
	}
	
}
