package org.zerock.momofit.controller.group;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.momofit.domain.group.GroupDTO;
import org.zerock.momofit.mapper.group.GroupMapper;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;


@Log4j2
@RequestMapping("/group/")
@Controller
public class UploadController {

	@Autowired
	private GroupMapper mapper;

//	------------------------------------------------------------------------------------------------------------

	/* 이미지 정보 반환 */
	@GetMapping(value="/getAttach",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GroupDTO> getAttach(int group_no){

		log.info("getAttach.........." + group_no);

		return new ResponseEntity<GroupDTO>(mapper.getAttach(group_no), HttpStatus.OK);

	}

//	------------------------------------------------------------------------------------------------------------	
	@PostMapping(value="/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GroupDTO> uploadAjaxAction (MultipartFile uploadFile) throws Exception {
		log.info("\t+ uploadFile.fileName : {}", uploadFile.getOriginalFilename());
		
		log.info("upload ajax post ...");

		/* 이미지 파일 체크 */
		File checkfile = new File(uploadFile.getOriginalFilename());
		String type = null;

		try {
			type = Files.probeContentType(checkfile.toPath());
			log.info("MIME TYPE : " + type);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(!type.startsWith("image")) {

			GroupDTO dto = null;
			return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);

		}

		String uploadFolder = "c://project/uploadFile/";

		// 날짜 폴더 경로
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		Date date = new Date();

		String str = sdf.format(date);

//		String datePath = str.replace("-", File.separator);

		/* 폴더 생성 -> 폴더가 없을 때만 생성하도록 if문 사용 */
		File uploadPath = new File(uploadFolder, str);

		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
//		------------------------------------------------------------------------------------------------------------

		/* 이미저 정보 담는 객체 */
		GroupDTO dto = new GroupDTO();

		/* 파일 이름 */
		String uploadFileName = uploadFile.getOriginalFilename();	
		dto.setGroup_img(uploadFileName);
		dto.setPath(str);

		/* uuid 적용 파일 이름 */
		String uuid = UUID.randomUUID().toString();
		dto.setTemp(uuid);

		uploadFileName = uuid + "_" + uploadFileName;

		/* 파일 위치, 파일 이름을 합친 File 객체 */
		File saveFile = new File(uploadPath, uploadFileName);

		/* 파일 저장 
		 	-> transferTo() 경우 IOException와 IllegalStateException을 일으킬 가능성이 있으므로 try catch문 사용 */
		try {

			uploadFile.transferTo(saveFile);

			/* 썸네일 생성 - ( pom.xml에 thumbnailator 의존성 추가 ) */
			File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);				

			Thumbnails.of(saveFile)
	        .size(160, 160)
	        .toFile(thumbnailFile);

		} catch (Exception e) {
			e.printStackTrace();
		} 

		// 클라이언트의 파일명 출력
		log.info("Upload file name : " + uploadFile.getOriginalFilename());

		// 작업하고 있는 파일의 용량
		log.info("Upload file size : " + uploadFile.getSize());
		log.info("-------------------------------------------");

		ResponseEntity<GroupDTO> result = new ResponseEntity<GroupDTO>(dto, HttpStatus.OK);

		return result;
	}


	/* 이미지 파일 삭제 */
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName){

		log.info("deleteFile........" + fileName);

		File file = null;

		try {

			/* 썸네일 파일 삭제 */
			file = new File("c://project/uploadFile/" + URLDecoder.decode(fileName, "UTF-8"));

			file.delete();

			/* 원본 파일 삭제 */
			String originFileName = file.getAbsolutePath().replace("s_", "");

			log.info("originFileName : " + originFileName);

			file = new File(originFileName);

			file.delete();

		} catch(Exception e) {

			e.printStackTrace();

			return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);

		}

		return new ResponseEntity<String>("success", HttpStatus.OK);

	}


} //end class