package org.joonzis.controller;

import java.util.List;

import javax.print.DocFlavor.STRING;

import org.joonzis.domain.ReplyVO;
import org.joonzis.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/replies/")
@RestController
public class ReplyController {
	/*
	 * 동작에 따른 rul 방법 (http 전송 방식)
	 * 1. 등록 - /replies/new -- POST
	 * 2. 조회 - /replies/:rno -- GET
	 * 3. 삭제 - /replies/:rno -- DELETE
	 * 4. 수정 - /replies/:rno -- PUT or PATCH
	 * 5. 페이지 - /replies/pages/:bno/:page --GET
	 * 
	 * == REST 형식으로 설계할 땐 PK 기준으로 작성하는 것이 좋다. PK 만으로 CRUD가 가능하기 때문이다
	 * == 다만 댓글 목록은 PK 만으론 안되고 bno와 페이지 번호 정보가 필요
	*/
	
	
	// @Getter를 잘못써도 오류를 잡아주지 않음
	// @Setter를 쓰지 않아서 service를 받아오지 못하여, service가 null값이 되는것.
	// NullPointerException인경우에 확인해보자

	@Setter(onMethod_ = @Autowired)
	private ReplyService service;
	
	
	// 1. 등록
	// consumes = 수신할 대이터 포맷
	// produces = 출력할 데이터 포맷
	// ResponseEntity -> Http 상태를 포함해서 전달
	@PostMapping(value =  "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
		int insertCount = service.register(vo);
		log.info("Reply Insert Count : " + insertCount);
		
		return insertCount == 1?
				new ResponseEntity<String>("success", HttpStatus.OK) :		// 200번 코드
					new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 2. 목록 (책 395페이지)
	@GetMapping(value = "/pages/{bno}/{page}",
				produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<ReplyVO>> getList(@PathVariable("bno") long bno,
												@PathVariable("page") int page) {
		log.info("getList.....");
		return new ResponseEntity<List<ReplyVO>>(service.getList(bno), HttpStatus.OK);
	}
	
	// 3. 조회
	@GetMapping(value = "/{rno}", 
				produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") long rno) {
		
		log.info("get....." + rno);
		return new ResponseEntity<ReplyVO>(service.get(rno), HttpStatus.OK);
	}
	
	// 4. 삭제
	@DeleteMapping(value = "/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") long rno) {
		log.info("remove...." + rno);
		
		return service.remove(rno) == 1 ?
				new ResponseEntity<String>("success", HttpStatus.OK) :
					new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	// 5. 수정
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH},
			value = "/{rno}", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable("rno") long rno) {
		log.info("rno..." + rno);
		log.info("modify..." + vo);
		
		int modifyCount = service.modify(vo);
		
		log.info("Reply Modify Count: " + modifyCount);
		
		return modifyCount == 1?
				new ResponseEntity<String>("success", HttpStatus.OK) :
					new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
