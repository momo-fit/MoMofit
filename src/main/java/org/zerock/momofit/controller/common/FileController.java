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
import org.zerock.momofit.common.SharedScopeKeys;


@Controller
public class FileController {

	@GetMapping("/display")
	public ResponseEntity<byte[]> getImage(String fileName){
		File file = new File(SharedScopeKeys.UPLOAD_PATH + fileName);

		ResponseEntity<byte[]> result = null;

		try {

			HttpHeaders header = new HttpHeaders();

			header.add("Content-type", Files.probeContentType(file.toPath()));

			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);

		}catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

} //end class