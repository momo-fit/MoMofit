package org.zerock.momofit.controller.group;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zerock.momofit.domain.group.Criteria;
import org.zerock.momofit.domain.group.ReplyDTO;
import org.zerock.momofit.service.group.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@Controller
@RequestMapping("/group/replies/")
public class ReplyController {
	
	private ReplyService service;
	
	
	// 댓글 등록
	     /* 브라우저에서는 JSON 타입의 댓글 데이터 전송 / 서버에서는 댓글 처리 결과를 문자열로 알려줌 */
	@PostMapping(value= "/new",                    
	             consumes= "application/json", 
	             produces= { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> create(@RequestBody ReplyDTO rdto) {
	    
	    log.info("ReplyDTO: " + rdto);
	    
	    int insertCount = service.register(rdto);
	    
	    log.info("Reply INSERT COUNT: " + insertCount);
	    
        return insertCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK) 
                                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    // 성공여부 반환 - 삼항연산자 처리
        
	} // create
	
	
	// 특정 게시물의 댓글 목록 조회
	@GetMapping(value = "/pages/{group_no}/{page}", 
	            produces = { MediaType.APPLICATION_XML_VALUE,
	                         MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ReplyDTO>> getList(
	        @PathVariable("page") int page,
	        @PathVariable("group_no") int group_no) {
	    
	        log.info("getList.......");
	        Criteria cri = new Criteria();
	        
	        log.info(cri);
	        
	        return new ResponseEntity<>(service.getList(cri, group_no), HttpStatus.OK);
	}
	
	// 댓글 조회
	@GetMapping(value = "/{group_comm_no}", 
                produces = { "application/json", "text/xml"} )
	    public ResponseEntity<ReplyDTO> get(@PathVariable("group_comm_no") Integer group_comm_no) {
    
        log.info("get: " + group_comm_no);
        
        return new ResponseEntity<>(service.get(group_comm_no), HttpStatus.OK);
	}
	
	// 댓글 삭제
    @DeleteMapping(value = "/{group_comm_no}", produces = { MediaType.TEXT_PLAIN_VALUE })
        public ResponseEntity<String> remove(@PathVariable("group_comm_no") Integer group_comm_no) {
    
        log.info("remove: " + group_comm_no);
        
        return service.remove(group_comm_no) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) 
                                                  : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        // 성공여부 반환 - 삼항연산자 처리
    }
    
    
	// 댓글 수정
        /* JSON 형태로 전달되는 데이터와, 파라미터로 전달되는 댓글 번호 처리 */
    @RequestMapping(method = { RequestMethod.PUT, RequestMethod.PATCH },
                    value = "/{group_comm_no}",
                    consumes= "application/json", 
                    produces= { MediaType.TEXT_PLAIN_VALUE })
    public ResponseEntity<String> modify(@RequestBody ReplyDTO rdto,
                                         @PathVariable("group_comm_no") Integer group_comm_no) {
        rdto.setGroup_comm_no(group_comm_no);
        log.info("test" + rdto.getGroup_comm_no());
        
        
        log.info("댓글번호: " + group_comm_no);
        log.info("modify: " + rdto);
        
        return service.modify(rdto) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) 
                                         : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
        
	

	
} // class
