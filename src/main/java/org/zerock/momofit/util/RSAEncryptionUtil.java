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

	//----------------------
	// RSA KEY-Pair 생성
	//----------------------
	public static KeyPair createRSAKeyPair() throws NoSuchAlgorithmException {
		log.trace("createRSAKeyPairToString() invoked.");
	
	
			// Step.1 : "RSA" Algorithm으로 KeyGenerator 생성
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(KEY_SIZE);		// key Size 2048bit
			
			// Step.2 : keyGenerator를 통한 (1) 공개 키, (2) 개인 키 생성
			KeyPair keyPair = keyPairGenerator.genKeyPair();
			
			return keyPair;
	
	} // createKeyPairToSring
	
	//-----------------------
	// PrivateKey로 복호화
	//-----------------------
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
	
	
	//-----------------------
	// Base64로 Encoder
	//-----------------------
	public static String base64EncoderToString(Key key) {
		
		return Base64.getEncoder().encodeToString(key.getEncoded());

	} // base64EncoderToString
	
	
	
} // end class
