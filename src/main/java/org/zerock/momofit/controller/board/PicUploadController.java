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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.momofit.common.SharedScopeKeys;
import org.zerock.momofit.domain.picboard.board_imgDTO;
import org.zerock.momofit.exception.DAOException;
import org.zerock.momofit.exception.ServiceException;
import org.zerock.momofit.service.picboard.PicBoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;

@Controller

@Log4j2
@AllArgsConstructor
@RequestMapping("/board/pic")
public class PicUploadController {
	
	
	private PicBoardService PicBoardService;

	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		
	}//get매핑
	
	@PostMapping(value="/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<board_imgDTO>> uploadAjaxActionPOST(MultipartFile[] uploadFile, Object console) {
		//MultipartFile란? 뷰에서 전송한 multipart타입의 파일을 다룰수 있게 해주는 인터페이스이며, 메소드들은 파일의 사이즈반환,이름반환,특정경로에 저장을 수행한다.
		
		log.info("uploadAjax invoked");
		
		/* 이미지 파일 체크  */ 
		for(MultipartFile multipartFile: uploadFile) {//전달받은 모든 파일(uploadfile)의 유형을 체크해야 하기 때문에 for문을 작성
			File checkfile = new File(multipartFile.getOriginalFilename());// 전달받은 파일(uploadfile)을 File 객체로 만들어주고 File 참조 변수에 대입
			String type = null; //MIME TYPE을 저장할 String 타입의 type 변수를 선언하고 null로 초기화
			
			try {
				type = Files.probeContentType(checkfile.toPath());
				log.info("MIME TYPE : " + type);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(!type.startsWith("image")) {
				
				List<board_imgDTO> list = null;
				return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
			}
		}
		
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd"); //저장되는경로를 날짜별로 폴더생성해주기위해 SimpleDateFormat를 사용.. 초기화해준다.
		Date date= new Date(); //오늘의 날짜데이터를 얻기위해서 Date클래스타입의 변수를 선언 및 초기화 해줍니다.
		String datePath= sdf.format(date); //오늘의 날짜 데이터 값을 가지고 있는 date변수를 yyyy-MM-dd형식의 문자열로 변환하기위해 SimpleDateFormat의 format메소드를 호출합니다.
		
		/*폴더 생성******* */
		File uploadPath = new File(SharedScopeKeys.UPLOAD_PATH, datePath);
		
			if(uploadPath.exists()==false) { //폴더가 이미 존재하는 상황에, 중복으로 생성되는걸 방지함 
				uploadPath.mkdirs(); //여러개의 폴더 생성 
			}
		
		
		//이미지 정보 담는 객체
		List<board_imgDTO> list = new ArrayList();
		
		//MultipartFiled의 메소드를 활용하여 전달받은 데이터에 대한 정보 출력해보기 
		for(MultipartFile multipartFile :uploadFile) {
			
			
			//뷰로부터 전달받은파일을 저장하는 transferTo메소드를 사용하기위해 저장된 파일이름과 위치 전체를 포함하는 File객체 만들어주기 
			//파일 이름 그대로 사용하기때문에 getOriginalFilename메소드 사용 
			//File객체를 만들어주기 전,  먼저 파일의 이름을 사용하기 위해 String타입의 변수를 선언하여 저장해주기

			
			//이미지 정보 객체
			board_imgDTO dto = new board_imgDTO();
			
			
			/* 파일이름 */
			String uploadFileName=multipartFile.getOriginalFilename();
			dto.setBoard_img_name(uploadFileName);
			dto.setPath(datePath);
			
			
			/* UUID 적용 파일 이름 */
			String uuid = UUID.randomUUID().toString();
			uploadFileName=uuid+"_"+uploadFileName;
			dto.setTemp(uuid);
			
			log.trace("*** dto : " + dto);
			
			/* 파일 위치, 파일이름을 합친 File 객체 (saveFile) */
			File saveFile = new File(uploadPath, uploadFileName);
			
			/* 파일 저장 */
			try { 
				multipartFile.transferTo(saveFile); //파일 저장 

				//thumbnailaotor 라이브러리 사용)
				File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);				
				
				Thumbnails.of(saveFile)
		        .size(160, 160)
		        .toFile(thumbnailFile);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			list.add(dto);// 이미지정보가 저장된 board_ingDTO의 객체를 List의 요소로 추가해준다

		}//for문
		ResponseEntity<List<board_imgDTO>> result = new ResponseEntity<List<board_imgDTO>>(list, HttpStatus.OK);
		
		return result;
		
	}
	
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName){
		
		File file = null;		// File 타입의 참조 변수를 선언하고 null로 초기화
		
		try {
			
			/* **썸네일 파일 삭제** */
			file = new File(SharedScopeKeys.UPLOAD_PATH + URLDecoder.decode(fileName, "UTF-8"));
			
			file.delete();
			
			
			/* **원본 파일 삭제** */
			
			String originFileName = file.getAbsolutePath().replace("s_", "");
			
			
			file = new File(originFileName);
			
			file.delete();
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);
		}
		return new ResponseEntity<String>("success",HttpStatus.OK); //성공시
		
	}// 파일 삭제 
	
	
	/* 이미지 정보 반환 - 상세 게시물 조회 할때 이미지 가져오려고 씀 */
	@GetMapping(value="/imageList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<board_imgDTO>> imageList(int board_no) throws DAOException, ServiceException{
		
		log.info(" *** selectInsert_img's board_no : ", board_no);
		return new ResponseEntity<List<board_imgDTO>>(this.PicBoardService.imageList(board_no),HttpStatus.OK);
		
	}
	
	
} //end class
