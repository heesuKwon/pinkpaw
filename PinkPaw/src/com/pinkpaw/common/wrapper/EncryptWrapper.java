package com.pinkpaw.common.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.pinkpaw.common.util.MVCUtils;

public class EncryptWrapper extends HttpServletRequestWrapper{
	
	/**
	 * 부모타입에 기본생성자 없고, 파라미터 생성자 반드시 구현할 것
	 * @param request
	 */
	public EncryptWrapper(HttpServletRequest request) {
		super(request);
	}

	/**
	 * password파라미터 요청시, 
	 * 암호화된 문자열을 리턴하도록 메소드 오버라이딩
	 * -> 동적 로딩에 의해 부모타입에서 메소드를 호출해도 
	 * 	자식 객체의 오버라이딩된 메소드가 호출된다.
	 */
	@Override
	public String getParameter(String name) {
		String value = "";
		if(name != null && (name.equals("password") || name.equals("oldPassword"))) {
			System.out.println("암호화 전: "+super.getParameter(name));
			//암호화처리
			value = MVCUtils.getSha512(super.getParameter(name));
			
			System.out.println("암호화 후: "+value);
		}
		else {
			//password가 아닌 사용자 입력값은 그대로 전달
			value = super.getParameter(name);
		}
		return value;
	}
}
