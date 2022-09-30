package org.zerock.momofit.controller.board;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.momofit.domain.freeboard.AttachImageVO;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;

@Log4j2

@Controller
public class FreeUploadController {
		
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("uploadAjax() invoked.");
		
	} // uploadAjax
	
		
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AttachImageVO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		log.info("uploadAjaxPost() invoked.");
		
		// 이미지 파일 체크
		for(MultipartFile multipartFile: uploadFile) {			
			File checkfile = new File(multipartFile.getOriginalFilename());
			String type = null;
			
			try {
				type = Files.probeContentType(checkfile.toPath());
				log.info("MIME TYPE : " + type);				
			} catch (IOException e) {
				e.printStackTrace();
			} // try-catch
			
			if(!type.startsWith("image")) {
				List<AttachImageVO> list = null;
				return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
			} // if
			
		} // for
		
		String uploadFolder = "C:\\project/uploadFile";
		
		// 해당 날짜로 폴더 구조 만들기		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String datePath = sdf.format(date);		
		
		File uploadPath = new File(uploadFolder, datePath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		} // if
		
		// 이미지 정보 담을 list 객체
		List<AttachImageVO> list = new ArrayList();
		
		for(MultipartFile multipartFile : uploadFile ) {
			
			// 이미지 정보 객체
			AttachImageVO vo = new AttachImageVO();

			// 파일 이름 = UUID + 원본 파일이름 
			String uploadFileName = multipartFile.getOriginalFilename();
			vo.setFileName(uploadFileName);
			vo.setUploadPath(datePath);
			
			String uuid = UUID.randomUUID().toString();
			vo.setUuid(uuid);
			uploadFileName = uuid + "_" + uploadFileName;
			
			// 파일 위치, 파일 이름을 합친 File 객체
			File saveFile = new File(uploadPath, uploadFileName);
			
			// 파일 저장
			try {
				multipartFile.transferTo(saveFile);
				
				// 썸네일도 저장
				File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);				
				
				Thumbnails.of(saveFile)
		        .size(160, 160)
		        .toFile(thumbnailFile);
				
			} catch (Exception e) {
				e.printStackTrace();
			} // try-catch
			
			list.add(vo);
		} // enhanced for
		ResponseEntity<List<AttachImageVO>> result = new ResponseEntity<List<AttachImageVO>>(list, HttpStatus.OK);
		
		return result;
		
	} // uploadAjaxPost
	
	/* 이미지 파일 삭제 */
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName){
		log.info("deleteFile() invoked.");
		
		File file = null;
		
		try {
			/* 썸네일 파일 삭제 */
			file = new File("c:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));			
			file.delete();
			
			/* 원본 파일 삭제 */
			String originFileName = file.getAbsolutePath().replace("s_", "");			
			log.info("originFileName : " + originFileName);			
			file = new File(originFileName);			
			file.delete();			
			
		} catch(Exception e) {			
			e.printStackTrace();			
			return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);			
		} // try-catch
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
		
	} // deleteFile
	

} // end class
