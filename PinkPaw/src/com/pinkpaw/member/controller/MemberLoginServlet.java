package com.pinkpaw.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pinkpaw.board.dmboard.model.service.DMService;
import com.pinkpaw.member.model.service.MemberService;
import com.pinkpaw.member.model.vo.Member;




/**
 * servlet&jsp 객체별 scope
 * 
 * type										jsp			servlet
 * -------------------------------------------------------------
 * javax.servlet.jsp.PageContext			pageContext
 * javax.servlet.http.HttpServletRequest	request
 * javax.servlet.http.HttpSession			session
 * javax.servlet.ServletContext				application
 *
 */
@WebServlet(urlPatterns="/member/login",
			name="MemberLoginServlet")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		//서블릿 생성시 최초1회만 호출된다.
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.인코딩
		request.setCharacterEncoding("utf-8");
		//1.파라미터핸들링
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		//checked=on, unchecked=null
		String saveId = request.getParameter("saveId");
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setPassword(password);
		
		System.out.println("로그인 서블릿 @@@@@@@@@@@@@@@@@@@@@ ="+member);
		
		//2.업무로직
		// 	-> 1:로그인성공
		// 	-> 0:로그인실패. 비밀번호 틀림
		// 	-> -1:로그인실패. 존재하지 않는 아이디
		int result = new MemberService().loginCheck(member);
		System.out.println("result@LoginServlet="+result);
		
		//3.view단 선정처리
		String view = "";
		String loc = "/";
		
		String referer = request.getHeader("Referer");
		String origin = request.getHeader("Origin");
		String url = request.getRequestURL().toString(); // 리턴타입이 String 이라 toString 붙여줘야함
		String uri = request.getRequestURI();
		
//		System.out.println("refere = "+referer);
//		System.out.println("origin = "+origin);
//		System.out.println("url = "+url);
//		System.out.println("uri = "+uri);
//		
//		refere = http://localhost:9090/mvc/board/boardList
//		origin = http://localhost:9090
//		url = http://localhost:9090/mvc/member/login
//		uri = /mvc/member/login
		
		int idx = referer.indexOf(request.getContextPath())+request.getContextPath().length();
		loc = referer.substring(idx);
		System.out.println("loc === "+loc);
		// ** 로그인성공시뿐만 아니라 비밀번호가 틀렸을때도 게시판에 있었다면 게시판에 계속 머물기 가능
		// ** 이 처리를 안하면 index로 돌아감
	
		
		if(result == MemberService.LOGIN_OK) {
			view = "/index.jsp";
			//로그인한 사용자정보를 가져오기
			Member memberLoggedIn
				= new MemberService().selectOne(memberId);
			
//			System.out.println("로그인성공시 처리확인 @@@@@@@@@@"+memberLoggedIn);
			
			//클라이언트(브라우져)에서 최초요청시,
			//tomcat 유일한 session id값을 발행함.
			//response에 sesssion id값을 리턴함.
			//이후 session id는 쿠키(클라이언트)로 보관.
			//매요청이 session id값 함께 전송
			
			//세션에 로그인한 사용자 정보를 담는다.
			//create여부 파라미터로 전달(기본값 true)
			//세션객체가 있으면, 해당 객체를 리턴
			//없으면, 세션객체 생성후, 해당 객체를 리턴(브라우져최초접속시)
			HttpSession session = request.getSession(true);
			//web.xml보다 우선순위가 높은 세션유효기간 설정
			session.setMaxInactiveInterval(10*60);
			//System.out.println("sessionId="+session.getId());
			session.setAttribute("memberLoggedIn", memberLoggedIn);
			
			int recvCount = new DMService().ReceiveTotalContents(memberLoggedIn.getMemberId());
			System.out.println("recvCount@servlet="+recvCount);
			session.setAttribute("recvCount", recvCount);
			
			
			
			//아이디저장관련 쿠키처리
			if(saveId != null) {
				Cookie c = new Cookie("saveId", memberId);
				c.setMaxAge(7*24*60*60);//쿠키유효기간 7일(초단위로설정)
				c.setPath("/");//쿠키사용디렉토리. 지정디렉토리의 url요청시 해당쿠키사용
				response.addCookie(c);
			}
			else {
				//쿠키삭제
				Cookie c = new Cookie("saveId", memberId);
				c.setMaxAge(0);//쿠키 유효기간 0으로 삭제
				c.setPath("/");
				response.addCookie(c);
			}
			
			
			
			
			
			
			//로그인성공후에는 view단 없이 리다이렉트시킴.
			// http://localhost:9090/mvc
//			response.sendRedirect(request.getContextPath());
			
			response.sendRedirect(referer);
			//** ex) 게시판에서 로그인시 index로 안돌아가고 그대로 게시판에 남아있음
			
			
		}
		else {
			view = "/WEB-INF/views/common/msg.jsp";
			String msg = "";
			if(result == MemberService.WRONG_PASSWORD) {
				msg = "비밀번호가 틀렸습니다.";
			}
			else if(result == MemberService.ID_NOT_EXIST){
				msg = "아이디가 존재하지 않습니다.";
			}
			
			//처리할 정보를 request속성으로 저장
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
		
			//로그인 실패시만, msg.jsp를 뷰단으로 사용
			RequestDispatcher reqDispatcher
			= request.getRequestDispatcher(view);
			reqDispatcher.forward(request, response);
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
