package org.zerock.momofit.controller.center;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.zerock.momofit.common.SharedScopeKeys;
import org.zerock.momofit.domain.picboard.Criteria;
import org.zerock.momofit.domain.picboard.PicBoardCommDTO;
import org.zerock.momofit.domain.qnaboard.QnaBoardCommDTO;
import org.zerock.momofit.domain.signIn.LoginVO;
import org.zerock.momofit.service.qnaboard.QnaBoardCommService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@RequestMapping("/center/qna/comm/")
@Controller
public class QnaCommController {
	
	@Setter(onMethod_= {@Autowired})
	private QnaBoardCommService QnaBoardCommservice;


	
	// JSON 방식의 데이터만 처리하도록하고, 문자열을 반환하도록 한다.
	@PostMapping(value= "/create",                    
	             consumes= "application/json", 
	             produces= { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> create(@RequestBody QnaBoardCommDTO Commdto, HttpSession session) {//@RequestBody를 적용해서 JSON데이터를 PicBoardCommDTO타입으로 변환
		log.info("*** Controller Commdto : ", Commdto);
		
		LoginVO loginVO = (LoginVO) session.getAttribute(SharedScopeKeys.USER_KEY);
        
        int user_no = loginVO.getUser_no();
		
	    int insertCount = QnaBoardCommservice.registerComm(Commdto);
	    

        return insertCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK) 
                                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	} // 댓글 등록
	
	
	
	@GetMapping(value = "/pages/{qna_no}/{page}", 
	            produces = { MediaType.APPLICATION_XML_VALUE,
	                         MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<QnaBoardCommDTO>> getCommList(
	        @PathVariable("page") int page,//@PathVariable를 이용해서 파라미터로 처리 
	        @PathVariable("qna_no") int qna_no) {

	        Criteria cri = new Criteria();
	        
	        log.info(cri);
	        
	        return new ResponseEntity<>(QnaBoardCommservice.getCommList(qna_no, cri), HttpStatus.OK);
	}// 댓글목록 조회
	
	
	
    
	@DeleteMapping(value = "/{qna_comm_no}", produces = { MediaType.TEXT_PLAIN_VALUE })
        public ResponseEntity<String> removeComm(@PathVariable("qna_comm_no") Integer qna_comm_no) {
    
        log.info("remove: " + qna_comm_no);
        
        return QnaBoardCommservice.removeComm(qna_comm_no) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) 
                                                  : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        
    }// 댓글 삭제
	
    
	
	    
	@RequestMapping(method = { RequestMethod.PUT, RequestMethod.PATCH },
	                value = "/{qna_comm_no}",
	                consumes= "application/json", 
	                produces= { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> modifyComm(@RequestBody QnaBoardCommDTO Commdto,
	                                     @PathVariable("qna_comm_no") Integer qna_comm_no) {//JSON 형태로 전달되는 데이터와 파라미터로 전달되는 댓글번호를 처리
		Commdto.setQna_comm_no(qna_comm_no);
	    
	    log.info("댓글번호: " + qna_comm_no);
	    log.info("modifyComm: " + Commdto);
	    
	    return QnaBoardCommservice.modifyComm(Commdto) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) 
	                                     : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}	// 댓글 수정
    

}