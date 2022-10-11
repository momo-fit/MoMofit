package org.zerock.momofit.service.mypage;

import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.momofit.common.SharedScopeKeys;
import org.zerock.momofit.domain.signIn.LoginVO;
import org.zerock.momofit.domain.signUp.UserDTO;
import org.zerock.momofit.exception.ServiceException;
import org.zerock.momofit.mapper.mypage.MyInfoModifyMapper;
import org.zerock.momofit.util.FileUploadUtil;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@AllArgsConstructor

@Service
public class MyInfoModifyServiceImpl implements MyInfoModifyService {
	
	private MyInfoModifyMapper myInfoModifyMapper;
	
	/*
	 * 1. 패스워드 유효성 검사
	 * @Param : Client로부터 전송 된 패스워드
	 * @Param : 고객 user_no
	 * 
	 * @return : 유효성 검사 참/거짓
	 */
	@Override
	public boolean checkValidPass(String pass, int user_no) throws ServiceException {
		log.trace("checkValidPass() invoked.");
		
		try {			
			// password 복호화
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			String salt = "_SALT"; 
			
			String password = this.myInfoModifyMapper.selectUserPass(user_no);
			
			return encoder.matches(salt + pass, password);
					
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // checkValidPass	

	/*
	 * 2. 유저 업데이트
	 * @param(dto) : UserDTO (업데이트 유저정보)
	 * @param(vo) : LoginVO (기존 파일정보 얻는 용도)
	 * @param(file) : 이미지 업로드 파일
	 */
	@Override
	public boolean modifyUserInfo(UserDTO dto, LoginVO vo, MultipartFile file) throws ServiceException {
		log.trace("modifyUserInfo() invoked.");
		
		try {
			//-------------------------
			//1. BCrypt PASS 암호화
			//-------------------------
			if(dto.getPass() != null && !dto.getPass().isBlank()) {
				log.trace("password BCrypt invoked.");
				
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				String salt = "_SALT";
				String Pass = encoder.encode(salt+dto.getPass());
				dto.setPass(Pass);
			}
				
			//-------------------------
			//2. 파일저장 (업로드 파일 존재 시)
			//-------------------------
			Map<String, String> saveFileInfo = null;
		
			// 파일이 존재할 경우, saveFile 실행
			if(file != null && !file.isEmpty()) {
				saveFileInfo = FileUploadUtil.saveFile(file);
				
				dto.setProfile_name(saveFileInfo.get(SharedScopeKeys.FILE_NAME));
				dto.setProfile_path(saveFileInfo.get(SharedScopeKeys.FILE_PATH));
				dto.setProfile_temp(saveFileInfo.get(SharedScopeKeys.FILE_TEMP));
				
				// 업데이트 실행
				int updateResult = this.myInfoModifyMapper.updateUserInfo(dto);
				
				
				if(updateResult == 1) {		// update success -> delete old upload file
					FileUploadUtil.deleteFile(vo.getProfile_path(), vo.getProfile_name(), vo.getProfile_temp());
				} else {	// update fail -> delete upload file
					FileUploadUtil.deleteFile(saveFileInfo.get(SharedScopeKeys.FILE_PATH), 
							saveFileInfo.get(SharedScopeKeys.FILE_NAME), 
							saveFileInfo.get(SharedScopeKeys.FILE_TEMP));
				} // if-else
	
				return updateResult == 1;				
				
			} // if
			
			int updateResult = this.myInfoModifyMapper.updateUserInfo(dto);
			log.info("\t+ dto.getProfile_name :{}", dto.getProfile_name());
			
			// Default 이미지 시, 기본 물리적 이미지 삭제 / 단 DB 업데이트는 성공해야함
			if(dto.getProfile_name() == null || dto.getProfile_name().isBlank() && updateResult == 1) {
				log.info("default Img selected. And Delete File....");
				FileUploadUtil.deleteFile(vo.getProfile_path(), vo.getProfile_name(), vo.getProfile_temp());
			}
			 
			return updateResult == 1;
			
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // modifyUserInfo

	@Override
	public LoginVO getUserInfo(int user_no) throws ServiceException {
		log.trace("getUserInfo() invoked.");
		
		try {
			
			return this.myInfoModifyMapper.selectUserInfo(user_no);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	} // getUserInfo
	
	
	
} // end class
