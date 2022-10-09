package org.zerock.momofit.controller.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class FileController {
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> getImage(String fileName){ //ResponseEntity 객체를 통해 body에 byte [] 데이터를 보내야 하기 대문에 ResponseEntity<byte[]>를 반환 타입으로 작성
		log.trace("*** getImgage() : " + fileName);
		File file = new File("c:/project/uploadFile/" + fileName);
		
		ResponseEntity<byte[]> result = null; //뷰로 반환할 ResponseEntity 객체의 주소를 저장할 참조 변수를 선언하고 null로 초기화
		
		try {
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-type", Files.probeContentType(file.toPath()));//header의 'Content Type' 속성 값에 이미지 파일 MIME TYPE을 추가해주기 위해서 HttpHeader 클래스에 있는 add() 메서드를 사용
			// add() 메서드의 첫 번째 파라미터에는 Response header의 '속성명'을, 두 번째 파라미터에는 해당 '속성명'에 부여할 값(value)을 삽입하면 됩니다.

			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			

		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}

} //end class