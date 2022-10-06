package org.zerock.momofit.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;
import org.zerock.momofit.common.SharedScopeKeys;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
public class FileUploadUtil {

	/*
	 * 1. 파일 저장
	 * @param : MultipartFile
	 * @return : Map (파일 저장 후, 원본파일명/UUID/폴더명) 
	 */
	public static Map<String, String> saveFile(MultipartFile file){
		log.trace("saveFile() invoked.");
		
		Map<String, String> result = new HashMap<>();
		
		//Step.1 : 파일 저장 되는 폴더 생성
		String path = makeFolder();
		
		
		//Step.2 : 원본파일명 
		String fileName = file.getOriginalFilename();
		log.info("\t+ fileName : {}", fileName);
		
		//Step.3 : 임시파일명얻기
		String fileTemp = UUID.randomUUID().toString();
		log.info("\t+ fileTemp : {}", fileTemp);
		
		//-- 파일 저장형식 : "임시파일명_원본파일명"
		String saveFileName = fileTemp + "_" + fileName;
		
		
		
		//Step.4 : 파일업로드
		File saveFile = new File(SharedScopeKeys.UPLOAD_PATH + path, saveFileName);
		log.info("\t+ saveFile : {}", saveFile);
		
		try {
			file.transferTo(saveFile);
		} catch (IllegalStateException | IOException e) {
			result.put(SharedScopeKeys.ERROR_MESSAGE, SharedScopeKeys.ERROR_FILE_UPLOAD);		
			return result;
		} // try-catch
		
		result.put(SharedScopeKeys.FILE_PATH, path);
		result.put(SharedScopeKeys.FILE_NAME, fileName);
		result.put(SharedScopeKeys.FILE_TEMP, fileTemp);
		
		return result;
		
	} // saveFile
	
	/*
	 * 2. 폴더생성
	 * @return : 생성 된 폴더명 (오늘날짜 "yyyyMMdd")
	 */
	public static String makeFolder() {
		log.trace("makeFolder() invoked.");
		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String todayDir = sdf.format(today);
		
		String folderDir = SharedScopeKeys.UPLOAD_PATH + todayDir;
		
		File uploadFolder = new File(folderDir);
		
		if(!uploadFolder.exists()) {
			uploadFolder.mkdir();
		} // if
		
		return todayDir;
		
	} // makeFolder
	

	/*
	 * 3. 파일삭제
	 * @param : 파일경로, 원본파일명, 임시파일명
	 */
	public static void deleteFile(String path, String name, String temp) {
		
		String targetDeleteFile = SharedScopeKeys.UPLOAD_PATH + path + "/" + temp + "_" + name;
		log.info("\t+ deleteFile : {}", targetDeleteFile);
		
		File deleteFile = new File(targetDeleteFile);
		deleteFile.delete();
		
	} // deleteFile
	
	
} // end class
