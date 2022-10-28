package org.zerock.momofit.controller.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.zerock.momofit.aws.AwsS3FileUploadService;

import com.amazonaws.util.IOUtils;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class FileController {

	private final AwsS3FileUploadService awsS3FileUploadService;
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> getImage(String fileName){
//		File file = new File(SharedScopeKeys.UPLOAD_PATH + fileName);

		ResponseEntity<byte[]> result = null;

		try {

			HttpHeaders header = new HttpHeaders();
			
			// 이미지 불러오기 (from AWS S3)
			URL url = new URL(awsS3FileUploadService.getFileURL(fileName));
			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
			InputStream fileIS = urlConn.getInputStream();
			
			header.add("Content-type", URLConnection.guessContentTypeFromStream(fileIS));

			result = new ResponseEntity<>(IOUtils.toByteArray(fileIS), header, HttpStatus.OK);
			
		}catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

} //end class