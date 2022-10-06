package org.zerock.momofit.controller.center;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.momofit.domain.report.Criteria;
import org.zerock.momofit.domain.report.PageDTO;
import org.zerock.momofit.domain.report.reportDTO;
import org.zerock.momofit.domain.report.reportListVO;
import org.zerock.momofit.domain.report.reportViewVO;
import org.zerock.momofit.exception.ControllerException;
import org.zerock.momofit.service.report.reportService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RestController
@RequestMapping(value = "/center/report/")
public class RestReportController {
	
	@Setter(onMethod_ = {@Autowired})
	private reportService service;
	
	@GetMapping("/report-list")
	public ResponseEntity<Map<String, Object>> getReportList( int page) throws ControllerException {
		log.info("getReportList({})", page);
		
		Criteria cri = new Criteria();
		
		try {
			
			cri.setCurrPage(page);
			
			List<reportListVO> vo = this.service.getReportLists(cri);
			
			int totalAmount = this.service.getTotalCnt();
			
			PageDTO pageDTO = new PageDTO(cri, totalAmount);
			
			Map<String, Object> result = new HashMap<>();
			result.put("list", vo);
			result.put("pageMaker", pageDTO);
				
			return new ResponseEntity<>(result, HttpStatus.OK);

			
		} catch (Exception e) {
			throw new ControllerException(e);
		}
		
	} // getReportList
	
	@GetMapping("/report-view")
	public ResponseEntity<Map<String, Object>> getReportView(int reportNum) throws ControllerException {
			
		try {
			
			reportViewVO view = this.service.getReportView(reportNum);
			
			Map<String, Object> viewResult = new HashMap<>();
			
			viewResult.put("content", view);
			
			return new ResponseEntity<>(viewResult, HttpStatus.OK);
			
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch

	} // getReportView
	
	@PostMapping("/report-post")
	public void postContent(reportDTO dto, RedirectAttributes rttrs, HttpServletResponse res, @RequestParam("file")MultipartFile file) throws ControllerException {
		
		try {
			
			boolean PostRes = this.service.registerReport(dto, file);
			
			if(PostRes) {
				res.sendRedirect("/center/report/list");
			} else {
				rttrs.addFlashAttribute("failMessage", "작성에 실패했습니다.");
				res.sendRedirect("/center/report/register");
			}

		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
		
	}
	

}
