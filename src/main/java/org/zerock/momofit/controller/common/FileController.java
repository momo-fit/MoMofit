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


@Controller
public class FileController {
<<<<<<< Updated upstream
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> getImage(String fileName){
		File file = new File("c://project/uploadFile/" + fileName);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			

			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-type", Files.probeContentType(file.toPath()));
			

			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			
=======

	@GetMapping("/display")
	public ResponseEntity<byte[]> getImage(String fileName){
		File file = new File("c://project/uploadFile/" + fileName);

		ResponseEntity<byte[]> result = null;

		try {

			HttpHeaders header = new HttpHeaders();

			header.add("Content-type", Files.probeContentType(file.toPath()));

			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
>>>>>>> Stashed changes

		}catch (IOException e) {
			e.printStackTrace();
		}
<<<<<<< Updated upstream
		
		return result;
	}

} //end class

=======

		return result;
	}

} //end class
>>>>>>> Stashed changes
