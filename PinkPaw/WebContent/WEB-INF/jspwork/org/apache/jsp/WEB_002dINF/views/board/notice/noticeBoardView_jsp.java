/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.42
 * Generated at: 2019-08-17 12:37:06 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.board.notice;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.pinkpaw.board.noticeboard.model.vo.*;
import com.pinkpaw.member.model.vo.Member;
import com.pinkpaw.board.noticeboard.model.vo.*;
import java.util.*;

public final class noticeBoardView_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/views/common/header.jsp", Long.valueOf(1566045381359L));
    _jspx_dependants.put("/WEB-INF/views/common/footer.jsp", Long.valueOf(1566045381375L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("com.pinkpaw.board.noticeboard.model.vo");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("com.pinkpaw.member.model.vo.Member");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
 
//로그인성공후 session객체에 저장된 memberLoggedIn가져오기
Member memberLoggedIn
	= (Member)session.getAttribute("memberLoggedIn");
System.out.println("memberLoggedIn@index.jsp="+memberLoggedIn);
/* memberLoggedIn = new Member();
memberLoggedIn.setMemberId("admin");
System.out.println("memberLoggedIn@index.jsp="+memberLoggedIn); */

//쿠키관련 처리
Cookie[] cookies = request.getCookies();
boolean saveId = false;
String memberId = "";

if(cookies != null){
	System.out.println("-------------------------");
	for(Cookie c: cookies){
		String key = c.getName();
		String value = c.getValue();
		System.out.println(key+" : "+value);
		
		//전송된 saveId쿠키가 있는 경우
		if("saveId".equals(key)){
			saveId = true;
			memberId = value;
		}
	}
	System.out.println("-------------------------");
}




	

      out.write("  \r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>PinkPaw</title>\r\n");
      out.write("<!-- bootstrap -->\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("\thref=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\"\r\n");
      out.write("\tintegrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\"\r\n");
      out.write("\tcrossorigin=\"anonymous\">\r\n");
      out.write("<script\r\n");
      out.write("\tsrc=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"\r\n");
      out.write("\tintegrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\"\r\n");
      out.write("\tcrossorigin=\"anonymous\"></script>\r\n");
      out.write("\r\n");
      out.write("<!--<link rel=\"shortcut icon\" href=\"images/favicon.ico\" type=\"image/x-icon\">\r\n");
      out.write("<link rel=\"icon\" href=\"images/favicon.ico\" type=\"image/x-icon\">-->\r\n");
      out.write("<meta name=\"viewport\"\r\n");
      out.write("\tcontent=\"width=device-width,user-scalable=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,target-densitydpi=medium-dpi\">\r\n");
      out.write("<link href=\"");
      out.print(request.getContextPath());
      out.write("/css/reset.css\"\r\n");
      out.write("\trel=\"stylesheet\" />\r\n");
      out.write("<link href=\"");
      out.print(request.getContextPath());
      out.write("/css/main.css\" rel=\"stylesheet\" />\r\n");
      out.write("<link href=\"");
      out.print(request.getContextPath());
      out.write("/css/Font.css\" rel=\"stylesheet\" />\r\n");
      out.write("<link href=\"");
      out.print(request.getContextPath());
      out.write("/css/Swiper.css\"\r\n");
      out.write("\trel=\"stylesheet\" />\r\n");
      out.write("\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath());
      out.write("/js/jquery-3.4.1.js\"></script>\r\n");
      out.write("<script\r\n");
      out.write("\tsrc=\"");
      out.print(request.getContextPath());
      out.write("/js/jquery-migrate-3.0.0.min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath());
      out.write("/js/jquery-ui.min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath());
      out.write("/js/swiper.min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath());
      out.write("/js/jquery.mousewheel.min.js\"></script>\r\n");
      out.write("<link\r\n");
      out.write("\thref=\"https://fonts.googleapis.com/css?family=Noto+Sans|Noto+Sans+KR:100,300,400,500,700,900\"\r\n");
      out.write("\trel=\"stylesheet\" />\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("\r\n");
      out.write("function register(){\r\n");
      out.write("\tlocation.href=\"");
      out.print(request.getContextPath());
      out.write("/member/termsOfService\";\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>\t\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("function validate(){\r\n");
      out.write("\tif($(\"#memberId\").val().trim().length == 0){\r\n");
      out.write("\t\talert(\"아이디를 입력하세요.\");\r\n");
      out.write("\t\t$(\"#memberId\").focus();\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\tif($(\"#password\").val().trim().length == 0){\r\n");
      out.write("\t\talert(\"비밀번호를 입력하세요.\");\r\n");
      out.write("\t\t$(\"#password\").focus();\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\treturn true;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("\t\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\t<!--header-->\r\n");
      out.write("\t<div class=\"headerWrap\">\r\n");
      out.write("\t<header>\r\n");
      out.write("\t\t<h1  style=\"width: 800px; margin-left: -20px;\">\r\n");
      out.write("\t\t\t<a href=\"");
      out.print(request.getContextPath());
      out.write("\"><img src=\"");
      out.print(request.getContextPath());
      out.write("/images/main/logo_color.png\" alt=\"logo\" style=\"margin-top: -9px;\"></a>\r\n");
      out.write("\t\t</h1>\r\n");
      out.write("\r\n");
      out.write("\t\t<script>\r\n");
      out.write("\t\t$(function(){\r\n");
      out.write("​\r\n");
      out.write("\t\t\t$('.headerWrap').mouseover(function(e){\r\n");
      out.write("\t\t\t\t$(this).css('background','rgba(34,34,34,0.7)');\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t$('.headerWrap').mouseout(function(e){\r\n");
      out.write("\t\t\t\t$(this).css('background','none');}\r\n");
      out.write("\t\t\t);\r\n");
      out.write("​\r\n");
      out.write("\t\t\t$('header>div>ul:first-child>li').mouseover(function(e){\r\n");
      out.write("\t\t\t\t$('.headerWrap').css('height','140px');\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t$('header>div>ul:first-child>li').mouseout(function(e){\r\n");
      out.write("\t\t\t\t$('.headerWrap').css('height','84px');\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t\t<div>\r\n");
      out.write("\t\t\t​ <a href=\"#none\" onClick=\"$('.menuWrap').fadeIn(300);\"><img\r\n");
      out.write("\t\t\t\tsrc=\"");
      out.print(request.getContextPath());
      out.write("/images/main/icon_meun.png\" alt=\"메뉴바\"></a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</header>\r\n");
      out.write("\t​ ​\r\n");
      out.write("\t<!--menu-->\r\n");
      out.write("\t<div class=\"menuWrap\">\r\n");
      out.write("\t\t<div class=\"menu\">\r\n");
      out.write("\t\t\t<a href=\"#none\" onClick=\"$('.menuWrap').fadeOut(300);\"\r\n");
      out.write("\t\t\t\tclass=\"menu_closeBtn\"><img src=\"images/main/modal_close.gif\"\r\n");
      out.write("\t\t\t\talt=\"닫기\"></a>\r\n");
      out.write("\t\t\t<dl>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t");
 if(memberLoggedIn == null){ 
      out.write("\r\n");
      out.write("\t\t\t\t<form action=\"");
      out.print(request.getContextPath() );
      out.write("/member/login\" \r\n");
      out.write("\t\t\t\t\t  id=\"loginFrm\"\r\n");
      out.write("\t\t\t\t\t  method=\"post\"\r\n");
      out.write("\t\t\t\t\t  onsubmit=\"return validate();\">\r\n");
      out.write("\t\t\t\t\t<table>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"text\" \r\n");
      out.write("\t\t\t\t\t\t\t\t\t   name=\"memberId\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t   id=\"memberId\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t   placeholder=\"아이디\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t   tabindex=\"1\" \r\n");
      out.write("\t\t\t\t\t\t\t\t\t   value=\"");
      out.print(saveId?memberId:"");
      out.write("\"/>\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"submit\" value=\"로그인\" class=\"btn btn-primary\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t   tabindex=\"3\" />\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"password\" \r\n");
      out.write("\t\t\t\t\t\t\t\t\t   name=\"password\" \r\n");
      out.write("\t\t\t\t\t\t\t\t\t   id=\"password\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t   placeholder=\"비밀번호\" \r\n");
      out.write("\t\t\t\t\t\t\t\t\t   tabindex=\"2\"/>\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"2\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"checkbox\" \r\n");
      out.write("\t\t\t\t\t\t\t\t\t   name=\"saveId\" \r\n");
      out.write("\t\t\t\t\t\t\t\t\t   id=\"saveId\" \r\n");
      out.write("\t\t\t\t\t\t\t\t\t   ");
      out.print(saveId?"checked":"");
      out.write("/>\r\n");
      out.write("\t\t\t\t\t\t\t\t<label for=\"saveId\">아이디저장</label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t<button\r\n");
      out.write("\t\t\t\t\t\t\t\tclass=\"btn btn-primary\" \r\n");
      out.write("\t\t\t\t\t\t\t\tvalue=\"회원가입\"\r\n");
      out.write("\t\t\t\t\t\t\t\tonclick=\"register();\" >회원가입</button>\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t</form>\t\r\n");
      out.write("\t\t\t");
 } 
			//로그인에 성공한 경우
			else {
      out.write("\t\r\n");
      out.write("\t\t\t\t<table id=\"logged-in\">\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t");
      out.print(memberLoggedIn.getMemberName() );
      out.write("님, 안녕하세요.\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"button\" \r\n");
      out.write("\t\t\t\t\t\t\t\t   value=\"쪽지\" \r\n");
      out.write("\t\t\t\t\t\t\t\t   onclick=\"location.href='");
      out.print(request.getContextPath());
      out.write("/board/dm/dmView\"/>\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t");
if(memberLoggedIn != null && "admin".equals(memberLoggedIn.getMemberId())){ 
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"button\" \r\n");
      out.write("\t\t\t\t\t\t\t\t   value=\"신고쪽지\" \r\n");
      out.write("\t\t\t\t\t\t\t\t   onclick=\"location.href='");
      out.print(request.getContextPath());
      out.write("/board/dm/reportDMList'\"/>\r\n");
      out.write("\t\t\t\t\t\t\t\t   <input type=\"button\" \r\n");
      out.write("\t\t\t\t\t\t\t\t   value=\"신고게시판\" \r\n");
      out.write("\t\t\t\t\t\t\t\t   onclick=\"location.href='");
      out.print(request.getContextPath());
      out.write("/admin/reportBoardList'\"/>\r\n");
      out.write("\t\t\t\t\t\t\t");
} 
      out.write("\t   \r\n");
      out.write("\t\t\t\t\t\t\t\t   \t   \r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"button\" \r\n");
      out.write("\t\t\t\t\t\t\t\t   value=\"마이페이지\" \r\n");
      out.write("\t\t\t\t\t\t\t\t   onclick=\"location.href='");
      out.print(request.getContextPath());
      out.write("/member/memberView?memberId=");
      out.print(memberLoggedIn.getMemberId());
      out.write("'\"/>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"button\" \r\n");
      out.write("\t\t\t\t\t\t\t\t   value=\"내가쓴글보기\" \r\n");
      out.write("\t\t\t\t\t\t\t\t   onclick=\"location.href='");
      out.print(request.getContextPath());
      out.write("/member/myBoard'\"/>\r\n");
      out.write("\t\t\t\t\t\t\t\t   <input type=\"button\" \r\n");
      out.write("\t\t\t\t\t\t\t\t   value=\"내가쓴댓글보기\" \r\n");
      out.write("\t\t\t\t\t\t\t\t   onclick=\"location.href='");
      out.print(request.getContextPath());
      out.write("/member/myComment'\"/>\t   \r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"button\" \r\n");
      out.write("\t\t\t\t\t\t\t\t   value=\"로그아웃\" \r\n");
      out.write("\t\t\t\t\t\t\t\t   onclick=\"location.href='");
      out.print(request.getContextPath());
      out.write("/member/logout'\"/>\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\t\t\t\t\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t");
 } 
      out.write("\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t</dl>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t<dl>\r\n");
      out.write("\t\t\t\t<dt>\r\n");
      out.write("\t\t\t\t\t<a href=\"");
      out.print(request.getContextPath());
      out.write("/board/notice/noticeBoardList\">공지사항</a>\r\n");
      out.write("\t\t\t\t</dt>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</dl>\r\n");
      out.write("\t\t\t<dl>\r\n");
      out.write("\t\t\t\t<dt class=\"sub_menu\">유기동물</dt>\r\n");
      out.write("\t\t\t\t<dd>\r\n");
      out.write("\t\t\t\t\t<a href=\"");
      out.print(request.getContextPath() );
      out.write("/board/organic/graph/OrganicGraph\">유기동물 통계</a>\r\n");
      out.write("\t\t\t\t</dd>\r\n");
      out.write("\t\t\t\t<dd>\r\n");
      out.write("\t\t\t\t\t<a href=\"");
      out.print(request.getContextPath());
      out.write("/animal/animalNotice\">유기동물 공고</a>\r\n");
      out.write("\t\t\t\t</dd>\r\n");
      out.write("\t\t\t</dl>\r\n");
      out.write("\t\t\t<dl class=\"ddSize\">\r\n");
      out.write("\t\t\t\t<dt class=\"sub_menu\">보호소</dt>\r\n");
      out.write("\t\t\t\t<dd>\r\n");
      out.write("\t\t\t\t\t<a href=\"");
      out.print(request.getContextPath());
      out.write("/shelter/shelterList\">보호소 찾기</a>\r\n");
      out.write("\t\t\t\t</dd>\r\n");
      out.write("\t\t\t\t<dd>\r\n");
      out.write("\t\t\t\t\t<a href=\"");
      out.print(request.getContextPath());
      out.write("/board/volunteer/volunteerList\">보호소 봉사요청</a>\r\n");
      out.write("\t\t\t\t</dd>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</dl>\r\n");
      out.write("\t\t\t<dl>\r\n");
      out.write("\t\t\t\t<dt class=\"sub_menu\">커뮤니티</dt>\r\n");
      out.write("\t\t\t\t<dd>\r\n");
      out.write("\t\t\t\t\t<a href=\"");
      out.print(request.getContextPath());
      out.write("/board/missingList\">실종동물</a>\r\n");
      out.write("\t\t\t\t</dd>\r\n");
      out.write("\t\t\t\t<dd>\r\n");
      out.write("\t\t\t\t\t<a href=\"");
      out.print(request.getContextPath());
      out.write("/board/parcelout/parceloutList\">분양동물</a>\r\n");
      out.write("\t\t\t\t</dd>\r\n");
      out.write("\t\t\t\t<dd>\r\n");
      out.write("\t\t\t\t\t<a href=\"");
      out.print(request.getContextPath());
      out.write("/board/review/reviewList\">후기</a>\r\n");
      out.write("\t\t\t\t</dd>\r\n");
      out.write("\t\t\t\t<dd>\r\n");
      out.write("\t\t\t\t\t<a href=\"");
      out.print(request.getContextPath());
      out.write("/board/community/free/freeList\">자유게시판</a>\r\n");
      out.write("\t\t\t\t</dd>\r\n");
      out.write("\t\t\t</dl>\r\n");
      out.write("\t\t\t<dl>\r\n");
      out.write("\t\t\t\t\t");
if(memberLoggedIn!=null && "admin".equals(memberLoggedIn.getMemberId())){ 
      out.write("\r\n");
      out.write("\t\t\t\t\t<a href=\"");
      out.print(request.getContextPath());
      out.write("/admin/memberList\">회원리스트</a>\r\n");
      out.write("\t\t\t\t\t");
} 
      out.write("\r\n");
      out.write("\t\t\t</dl>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<script>\r\n");
      out.write("\t\t$(()=>{\r\n");
      out.write("\t\t\t$(\".sub_menu\").on(\"click\", function(e){\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t$(this).parent().siblings().children(\"dd\").css('display','none');\r\n");
      out.write("\t\t\t\t$(this).siblings().css('display','inline-block');\r\n");
      out.write("\t\t    });\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t</script>​\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!--menu end-->\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!--header end-->\r\n");
      out.write("\r\n");
      out.write("\r\n");

	NoticeBoard b = (NoticeBoard)request.getAttribute("NoticeBoard");


      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" \r\n");
      out.write("\t  href=\"");
      out.print(request.getContextPath());
      out.write("/css/board.css\" />\r\n");
      out.write("  \r\n");
      out.write("<section id=\"board-container\">\r\n");
      out.write("\t<h2>공지사항</h2>\r\n");
      out.write("\t<table class=\"table table-gray table-hover\">\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>글번호</th>\r\n");
      out.write("\t\t\t\t<td>");
      out.print(b.getNoticeNo() );
      out.write("</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>제 목</th>\r\n");
      out.write("\t\t\t\t<td>");
      out.print(b.getNoticeTitle() );
      out.write("</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>작성자</th>\r\n");
      out.write("\t\t\t\t<td>");
      out.print(b.getNoticeWriter() );
      out.write("</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>조회수</th>\r\n");
      out.write("\t\t\t\t<td>");
      out.print(b.getNoticeCount() );
      out.write("</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>내용</th>\r\n");
      out.write("\t\t\t<td>");
      out.print(b.getNoticeContent());
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<!-- 관리자인 경우에만 수정/삭제버튼이 보이도록함. -->\t\r\n");
      out.write("\t\t");
 if(memberLoggedIn!=null && "admin".equals(memberLoggedIn.getMemberId())) {
      out.write("\t\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th colspan=\"2\">\r\n");
      out.write("\t\t\t\t<input type=\"button\" value=\"수정\" \r\n");
      out.write("\t\t\t\t\t   onclick=\"updateBoard();\" />\r\n");
      out.write("\t\t\t\t<input type=\"button\" value=\"삭제\" \r\n");
      out.write("\t\t\t\t\t   onclick=\"deleteBoard();\" />\r\n");
      out.write("\t\t\t</th>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<form action=\"");
      out.print(request.getContextPath());
      out.write("/board/notice/noticeBoardDelete\"\r\n");
      out.write("\t\t      name=\"boardDeleteFrm\"\r\n");
      out.write("\t\t      method=\"post\">\r\n");
      out.write("\t\t\t<input type=\"hidden\" name=\"noticeNo\"\r\n");
      out.write("\t\t\t\t   value=\"");
      out.print(b.getNoticeNo());
      out.write("\" />\r\n");
      out.write("\t\t\t      \r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t\t<script>\r\n");
      out.write("\t\tfunction updateBoard(){\r\n");
      out.write("\t\t\tlocation.href = \"");
      out.print(request.getContextPath());
      out.write("/board/notice/noticeBoardUpdateForm?noticeNo=");
      out.print(b.getNoticeNo());
      out.write("\"\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tfunction deleteBoard(){\r\n");
      out.write("\t\t\tif(!confirm(\"정말 삭제 하시겠습니까?\")){\r\n");
      out.write("\t\t\t\treturn;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t$(\"[name=boardDeleteFrm]\").submit();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t");
} 
      out.write("\r\n");
      out.write("\t</table>\r\n");
      out.write("\t\r\n");
      out.write("</section>\r\n");
      out.write("\r\n");
      out.write("<footer>\r\n");
      out.write("\t​\r\n");
      out.write("\t<article>\r\n");
      out.write("\t\t<div class=\"footerInfo\">\r\n");
      out.write("\t\t\t<h1>\r\n");
      out.write("\t\t\t\t<img src=\"");
      out.print(request.getContextPath());
      out.write("/images/common/footer/foot_logo.png\" alt=\"로고\">\r\n");
      out.write("\t\t\t</h1>\r\n");
      out.write("\t\t\t<div class=\"footer_txt\">\r\n");
      out.write("\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t<li>Team PINKPAW</li>\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t<li>         \r\n");
      out.write("         \t\t\t\t ⓒ핑크포우제공/데이터 출처 <img src=\"");
      out.print(request.getContextPath());
      out.write("/images/common/footer/maf.png\" /> 농림축산식품부 <br />\r\n");
      out.write("\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t\t핑크포우는 사람과 동물 모두가 행복한 세상을 만들어갑니다.\r\n");
      out.write("\t\t\t\t\t</li> \r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t\t핑크포우는 정부나 보호소와는 관련이 없습니다.\r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t<p>\r\n");
      out.write("\t\t\t\t\t<br>ⓒCOPY RIGHT©2019 PinkPawTeam. ALL RIGHTS RESERVED.\r\n");
      out.write("\t\t\t\t</p>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</article>\r\n");
      out.write("\r\n");
      out.write("</footer>\r\n");
      out.write("<!-- <style>\r\n");
      out.write("/* footer {\r\n");
      out.write("\twidth: 100%;\r\n");
      out.write("\theight: 300px;\r\n");
      out.write("\tpadding: 60px;\r\n");
      out.write("\tbackground-color: #394141;\r\n");
      out.write("\tcolor: #7c8c8c;\r\n");
      out.write("\tbottom: 0%\r\n");
      out.write("} */\r\n");
      out.write("\r\n");
      out.write("footer p:nth-child(2) {\r\n");
      out.write("\tfont-size: 14px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("footer p:nth-child(3) {\r\n");
      out.write("\tfont-size: 10px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".underline {\r\n");
      out.write("\ttext-decoration: underline;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("footer h4 {\r\n");
      out.write("\tfont-size: 18px;\r\n");
      out.write("\tfont-weight: bold;\r\n");
      out.write("\tfont-family: \"Arial\";\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</style> -->\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
