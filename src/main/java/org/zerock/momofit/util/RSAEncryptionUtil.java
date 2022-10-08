package org.zerock.momofit.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class RSAEncryptionUtil {

	private static final int KEY_SIZE = 2048;

	/*
	 * 1. RSA KEY-Pair 생성
	 * KEY Pair객체로 PrivateKey와 PublicKey를 얻을 수 있는 객체
	 * 
	 * @return : KeyPair
	 */
	public static KeyPair createRSAKeyPair() throws NoSuchAlgorithmException {
		log.trace("createRSAKeyPairToString() invoked.");
	
	
			// Step.1 : "RSA" Algorithm으로 KeyGenerator 생성
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(KEY_SIZE);		// key Size 2048bit
			
			// Step.2 : keyGenerator를 통한 (1) 공개 키, (2) 개인 키 생성
			KeyPair keyPair = keyPairGenerator.genKeyPair();
			
			return keyPair;
	
	} // createKeyPairToSring
	
	
	/*
	 * 2. Encrypted된 암호문을 복호화
	 * 
	 * @Param (encrypted) : 암호화된 문자열
	 * @Param (privateKey) : PrivateKey -> 복호화 Key
	 * 
	 * @return : 복호화가 완료 된 문자열
	 */
	public static String decrypt(String encrypted, PrivateKey privateKey) 
			throws NoSuchAlgorithmException, NoSuchPaddingException, 
			InvalidKeyException, IllegalBlockSizeException, 
			BadPaddingException, UnsupportedEncodingException {
		
		Cipher cipher = Cipher.getInstance("RSA");
		
		// Bas64기반 바이트 배열로
		byte[] byteEncrypted = Base64.getDecoder().decode(encrypted.getBytes());
		
		// Cipher 초기화 -> (복호화 모드 / PrivateKey 이용)
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		
		// PrivateKey를 이용한 복호화 작업
		byte[] byteDecrypted = cipher.doFinal(byteEncrypted);
		
		// byte배열 -> String변환
		String decrypted = new String(byteDecrypted, "UTF-8");
		
		return decrypted;
	} // decrypt
	
	/*
	 * 3. Key객체를 Base64방식으로 문자열 Encoding
	 * Client에서 Base64기반 PublicKey로 Encrypt 사용하므로, 인코딩한다.
	 * Client 사용 라이브러리 : jsencrypt
	 * 
	 * @Param (key) : RSA Key
	 * @Return : Base64기반 인코딩 된 문자열
	 */
	public static String base64EncoderToString(Key key) {
		
		return Base64.getEncoder().encodeToString(key.getEncoded());

	} // base64EncoderToString
	
	
	
} // end class
