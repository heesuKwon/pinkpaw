package com.pinkpaw.common.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MVCUtils {
	
	/**
	 * 사용자 비밀번호를 Sha512 알고리즘을 이용하여 해쉬(단방향 암호화)처리함.
	 * 
	 * 1. 암호화(MessageDigest)
	 * 2. 인코딩(Base64)
	 * @param password
	 * @return
	 */
	public static String getSha512(String password) {
		String encPwd = null;
		//1.암호화
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		//바이트배열로 변환
		byte[] bytes = password.getBytes(Charset.forName("utf-8"));
		//md객체 업데이트
		md.update(bytes);
		//해싱처리
		byte[] encryptedBytes = md.digest();
		
		System.out.println(new String(encryptedBytes));
		
		//2.인코딩
		//영문자+숫자+특수문자 몇개 = 총 64개의 문자로 사람이 읽을 수 있도록 변환
		encPwd = Base64.getEncoder().encodeToString(encryptedBytes);
		
		return encPwd;
	}
}
