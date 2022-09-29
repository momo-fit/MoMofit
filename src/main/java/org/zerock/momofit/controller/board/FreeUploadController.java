package org.zerock.momofit.controller.board;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.momofit.domain.freeboard.AttachImageVO;

import lombok.extern.log4j.Log4j2;

@Log4j2

@RequestMapping("/board/free/")
@Controller
public class FreeUploadController {
	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("uploadForm() invoked.");
		
	} // uploadForm
	
	
	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		log.info("uploadFormPost() invoked.");
		
		String uploadFolder = "C:\\upload";
		
		for(MultipartFile multipartFile : uploadFile ) {
			log.info("Upload File name : " + multipartFile.getOriginalFilename());
			log.info("Upload File size : " + multipartFile.getSize());
			
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage());
			} // try-catch			
		} // enhanced for		
		
	} // uploadFormPost
	
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("uploadAjax() invoked.");
		
	} // uploadAjax
	
	
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);		
	} // getFolder
	
	private boolean checkImageType(File file) {
		try {			
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
			
		} catch (IOException e){
			e.printStackTrace();
		}
		return false;
	} // checkImageType
	
	
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachImageVO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		log.info("uploadAjaxPost() invoked.");
		
		List<AttachImageVO> list = new ArrayList<>();
		
		String uploadFolder = "C:\\upload";
		String uploadFolderPath = getFolder();
		
		// 해당 날짜로 폴더 구조 만들기
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		log.info("upload path : " + uploadPath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		} // if
		
		for(MultipartFile multipartFile : uploadFile ) {
			log.info("Upload File name : " + multipartFile.getOriginalFilename());
			log.info("Upload File size : " + multipartFile.getSize());
			
			AttachImageVO attachDTO = new AttachImageVO();
			
			// UUID + 파일명으로 중복방지
			String uploadFileName = multipartFile.getOriginalFilename();
			attachDTO.setFileName(uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "-" + uploadFileName;
			
			try {
				File saveFile = new File (uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
				
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFolderPath);
				
				if(checkImageType(saveFile)) {
//					attachDTO.setImage(true);					
				}
				
				list.add(attachDTO);
				
			} catch (Exception e) {
				e.printStackTrace();
			} // try-catch			
		} // enhanced for
		
		return new ResponseEntity<>(list, HttpStatus.OK);
					
	} // uploadAjaxPost
	

} // end class
