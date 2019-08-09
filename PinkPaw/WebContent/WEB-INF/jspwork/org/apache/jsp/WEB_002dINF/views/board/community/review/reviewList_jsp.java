/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.42
 * Generated at: 2019-08-09 07:49:48 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.board.community.review;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import com.pinkpaw.board.reviewboard.model.vo.ReviewBoard;
import com.pinkpaw.member.model.vo.Member;

public final class reviewList_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/views/common/header.jsp", Long.valueOf(1565335444956L));
    _jspx_dependants.put("/WEB-INF/views/common/footer.jsp", Long.valueOf(1565255707844L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("com.pinkpaw.board.reviewboard.model.vo.ReviewBoard");
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
 
	//로그인성공후 session객체에 저장된 memberLoggedIn가져오기
	/* Member memberLoggedIn
		= (Member)session.getAttribute("memberLoggedIn"); */
	Member memberLoggedIn = new Member();
	memberLoggedIn.setMemberId("admin");
	System.out.println("memberLoggedIn@index.jsp="+memberLoggedIn);
	
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
      out.write("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\" \r\n");
      out.write("integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\" \r\n");
      out.write("crossorigin=\"anonymous\"></script>\r\n");
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
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\t<!--header-->\r\n");
      out.write("\t<div class=\"headerWrap\">\r\n");
      out.write("\t<header>\r\n");
      out.write("\t\t<h1>\r\n");
      out.write("\t\t\t<a href=\"");
      out.print(request.getContextPath());
      out.write("\"><img src=\"");
      out.print(request.getContextPath());
      out.write("/images/main/logo_color.png\" alt=\"logo\"></a>\r\n");
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
      out.write("/images/main/icon_meun_black.png\" alt=\"메뉴바\"></a>\r\n");
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
      out.write("\t\t\t\t<dt>\r\n");
      out.write("\t\t\t\t\t<a href=\"#\">공지사항</a>\r\n");
      out.write("\t\t\t\t</dt>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</dl>\r\n");
      out.write("\t\t\t<dl>\r\n");
      out.write("\t\t\t\t<dt class=\"sub_menu\">유기동물</dt>\r\n");
      out.write("\t\t\t\t<dd>\r\n");
      out.write("\t\t\t\t\t<a href=\"../investment/ir_info.php\">유기동물 통계</a>\r\n");
      out.write("\t\t\t\t</dd>\r\n");
      out.write("\t\t\t\t<dd>\r\n");
      out.write("\t\t\t\t\t<a href=\"../investment/ir_notice.php\">유기동물 공고</a>\r\n");
      out.write("\t\t\t\t</dd>\r\n");
      out.write("\t\t\t</dl>\r\n");
      out.write("\t\t\t<dl class=\"ddSize\">\r\n");
      out.write("\t\t\t\t<dt class=\"sub_menu\">보호소</dt>\r\n");
      out.write("\t\t\t\t<dd>\r\n");
      out.write("\t\t\t\t\t<a href=\"");
      out.print(request.getContextPath() );
      out.write("/shelter/shelterList\">보호소 찾기</a>\r\n");
      out.write("\t\t\t\t</dd>\r\n");
      out.write("\t\t\t\t<dd>\r\n");
      out.write("\t\t\t\t\t<a href=\"../business/official.php\">보호소 봉사요청</a>\r\n");
      out.write("\t\t\t\t</dd>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</dl>\r\n");
      out.write("\t\t\t<dl>\r\n");
      out.write("\t\t\t\t<dt class=\"sub_menu\">커뮤니티</dt>\r\n");
      out.write("\t\t\t\t<dd>\r\n");
      out.write("\t\t\t\t\t<a href=\"../pr_center/culture.php\">실종동물</a>\r\n");
      out.write("\t\t\t\t</dd>\r\n");
      out.write("\t\t\t\t<dd>\r\n");
      out.write("\t\t\t\t\t<a href=\"../pr_center/culture.php\">분양동물</a>\r\n");
      out.write("\t\t\t\t</dd>\r\n");
      out.write("\t\t\t\t<dd>\r\n");
      out.write("\t\t\t\t\t<a href=\"");
      out.print(request.getContextPath());
      out.write("/board/review/reviewList\">후기</a>\r\n");
      out.write("\t\t\t\t</dd>\r\n");
      out.write("\t\t\t\t<dd>\r\n");
      out.write("\t\t\t\t\t<a href=\"../pr_center/culture.php\">자유게시판</a>\r\n");
      out.write("\t\t\t\t</dd>\r\n");
      out.write("\t\t\t</dl>\r\n");
      out.write("\r\n");
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
      out.write('\r');
      out.write('\n');

	List<ReviewBoard> list = (List<ReviewBoard>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");

      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath());
      out.write("/css/board.css\" />\r\n");
      out.write("<script>\r\n");
      out.write("$(()=>{\r\n");
      out.write("\t//테이블의 열을 클릭시 해당 게시물로 이동\r\n");
      out.write("\t$(\"td\").click((e)=>{\t\t\r\n");
      out.write("\t\tvar reviewNo = $(e.target).parent().children(\"th\").text();\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tlocation.href = \"");
      out.print(request.getContextPath());
      out.write("/board/review/reviewView?boardNo=\"+reviewNo; \r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("\t\t\t\r\n");
      out.write("</script>\r\n");
      out.write("<section class=\"board-container\">\r\n");
      out.write("  \t\t\r\n");
      out.write("\t<div class=\"input-group mb-3\" style=\"width: 600px\">\r\n");
      out.write("\t\t<div class=\"input-group-prepend\">\r\n");
      out.write("    \t\t<select class=\"btn btn-outline-secondary dropdown-toggle\" data-toggle=\"dropdown\" name=\"kind\" style=\"border-radius: 0\" onchange=\"kindchange();\">\r\n");
      out.write("\t\t\t\t<option value=\"\" selected hidden>글종류</option>\r\n");
      out.write("\t\t\t\t<option value=\"\">전체</option>\r\n");
      out.write("\t\t\t\t<option value=\"입양후기\">입양후기</option>\r\n");
      out.write("\t\t\t\t<option value=\"분양후기\">분양후기</option>\r\n");
      out.write("\t\t\t\t<option value=\"찾은후기\">찾은후기</option>\r\n");
      out.write("\t\t\t\t<option value=\"봉사후기\">봉사후기</option>\r\n");
      out.write("\t\t\t</select>\r\n");
      out.write(" \t\t</div> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
      out.write("  \t\t<div class=\"input-group-prepend\">\r\n");
      out.write("    \t\t<select class=\"btn btn-outline-secondary dropdown-toggle\" data-toggle=\"dropdown\" name=\"key\">\r\n");
      out.write("\t\t\t\t<option value=\"\" selected hidden>키워드</option>\r\n");
      out.write("\t\t\t\t<option value=\"\">전체</option>\r\n");
      out.write("\t\t\t\t<option value=review_title>제목</option>\r\n");
      out.write("\t\t\t\t<option value=\"review_writer\">작성자</option>\r\n");
      out.write("\t\t\t\t<option value=\"review_content\">내용</option>\r\n");
      out.write("\t\t\t</select>\r\n");
      out.write(" \t\t </div>\r\n");
      out.write("  \t\t<input type=\"text\" class=\"form-control\" aria-label=\"Text input with dropdown button\" style=\"width: 200px\" id=\"keyword\">\r\n");
      out.write("  \t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
      out.write("  \t\t<button class=\"btn btn-outline-secondary\" style=\"border-radius: 0\" onclick=\"srch();\">검색하기</button>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<script>\r\n");
      out.write("\tfunction kindchange() {\r\n");
      out.write("\t\tvar kind = $(\"[name=kind]\").val().trim();\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\turl: \"");
      out.print(request.getContextPath());
      out.write("/board/review/reviewKindChange\",\r\n");
      out.write("\t\t\tdata: \"kind=\"+kind,\r\n");
      out.write("\t\t\ttype: \"get\",\r\n");
      out.write("\t\t\tsuccess: function(data){\r\n");
      out.write("\t\t\t\tconsole.log(data);\r\n");
      out.write("\t\t\t\tvar html = \"<tr><th scope='col'>번호</th><th scope='col'>첨부파일</th><th scope='col'>종류</th><th scope='col'>제목</th><th scope='col'>작성자</th><th scope='col'>게시일</th></tr>\";\r\n");
      out.write("\t\t\t\tvar num = 1;\r\n");
      out.write("\t\t\t\t$(data).each((i,b)=>{\r\n");
      out.write("\t\t\t\t\tnum = num + 1;\r\n");
      out.write("\t\t\t\t\thtml += \"<tr>\";\r\n");
      out.write("\t\t\t\t\thtml += \"<th scope='row'>\"+b.reviewNo+\"</th>\";\r\n");
      out.write("\t\t\t\t\thtml += \"<td></td>\";\r\n");
      out.write("\t\t\t\t\thtml += \"<td>\"+b.reviewKind+\"</td>\";\r\n");
      out.write("\t\t\t\t\thtml += \"<td>\"+b.reviewTitle+\"</td>\";\r\n");
      out.write("\t\t\t\t\thtml += \"<td>\"+b.reviewWriter+\"</td>\";\r\n");
      out.write("\t\t\t\t\thtml += \"<td>\"+b.reviewEnrollDate+\"</td>\";\r\n");
      out.write("\t\t\t\t\thtml += \"</tr>\";\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t$(\"#tbl-board\").html(html);\r\n");
      out.write("\t\t\t\tif(num < 10){\r\n");
      out.write("\t\t\t\t\t$(\"#pageBar\").html('<span>[이전]</span> 1 <span>[다음]</span>');\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\telse{\r\n");
      out.write("\t\t\t\t\t$(\"#pageBar\").html(\"");
      out.print(pageBar );
      out.write("\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\terror: function(jqxhr, textStatus, errorThrown){\r\n");
      out.write("\t\t\t\tconsole.log(\"ajax 처리 실패\");\r\n");
      out.write("\t\t\t\tconsole.log(jqxhr, textStatus, errorThrown);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction srch() {\r\n");
      out.write("\t\tvar kind = $(\"[name=kind]\").val().trim();\r\n");
      out.write("\t\tvar key = $(\"[name=key]\").val().trim();\r\n");
      out.write("\t\tvar keyword = $(\"#keyword\").val().trim();\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\turl: \"");
      out.print(request.getContextPath());
      out.write("/board/review/reviewSearch\",\r\n");
      out.write("\t\t\tdata: \"kind=\"+kind+\"&key=\"+key+\"&keyword=\"+keyword,\r\n");
      out.write("\t\t\ttype: \"get\",\r\n");
      out.write("\t\t\tsuccess: function(data){\r\n");
      out.write("\t\t\t\tconsole.log(data);\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tvar html = \"<tr><th scope='col'>번호</th><th scope='col'>첨부파일</th><th scope='col'>종류</th><th scope='col'>제목</th><th scope='col'>작성자</th><th scope='col'>게시일</th></tr>\";\r\n");
      out.write("\t\t\t\tvar num = 1;\r\n");
      out.write("\t\t\t\t$(data).each((i,b)=>{\r\n");
      out.write("\t\t\t\t\tnum = num + 1;\r\n");
      out.write("\t\t\t\t\thtml += \"<tr>\";\r\n");
      out.write("\t\t\t\t\thtml += \"<th scope='row'>\"+b.reviewNo+\"</th>\";\r\n");
      out.write("\t\t\t\t\thtml += \"<td></td>\";\r\n");
      out.write("\t\t\t\t\thtml += \"<td>\"+b.reviewKind+\"</td>\";\r\n");
      out.write("\t\t\t\t\thtml += \"<td>\"+b.reviewTitle+\"</td>\";\r\n");
      out.write("\t\t\t\t\thtml += \"<td>\"+b.reviewWriter+\"</td>\";\r\n");
      out.write("\t\t\t\t\thtml += \"<td>\"+b.reviewEnrollDate+\"</td>\";\r\n");
      out.write("\t\t\t\t\thtml += \"</tr>\";\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t$(\"#tbl-board\").html(html);\r\n");
      out.write("\t\t\t\tif(num < 10){\r\n");
      out.write("\t\t\t\t\t$(\"#pageBar\").html('<span>[이전]</span> 1 <span>[다음]</span>');\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\telse{\r\n");
      out.write("\t\t\t\t\t$(\"#pageBar\").html(\"");
      out.print(pageBar );
      out.write("\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\terror: function(jqxhr, textStatus, errorThrown){\r\n");
      out.write("\t\t\t\tconsole.log(\"ajax 처리 실패\");\r\n");
      out.write("\t\t\t\tconsole.log(jqxhr, textStatus, errorThrown);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("\r\n");
      out.write("\t");
      out.write('\r');
      out.write('\n');
      out.write('	');
if(memberLoggedIn!=null){ 
      out.write("\r\n");
      out.write("\t<input type=\"button\" value=\"글쓰기\" id=\"btn-add\"\r\n");
      out.write("\t\t\tonclick=\"goReviewWrite();\"/>\r\n");
      out.write("\t");
      out.write("\r\n");
      out.write("\t<script>\r\n");
      out.write("\tfunction goReviewWrite(){\r\n");
      out.write("\t\tlocation.href = \"");
      out.print(request.getContextPath());
      out.write("/board/review/reviewWrite\";\r\n");
      out.write("\t}\r\n");
      out.write("\t</script>\t\t\r\n");
      out.write("\t");
} 
      out.write("\r\n");
      out.write("\t<table id=\"tbl-board\" class=\"table table-hover\">\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th scope=\"col\">번호</th>\r\n");
      out.write("\t\t\t<th scope=\"col\">첨부파일</th>\r\n");
      out.write("\t\t\t<th scope=\"col\">종류</th>\r\n");
      out.write("\t\t\t<th scope=\"col\">제목</th>\r\n");
      out.write("\t\t\t<th scope=\"col\">작성자</th>\r\n");
      out.write("\t\t\t<th scope=\"col\">게시일</th>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t");
if(list==null || list.isEmpty()){ 
      out.write("\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td colspan=\"6\" align=\"center\">게시글이 없습니다.</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t");
}
		else{
			for(ReviewBoard b: list){
      out.write("\t\t\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th scope=\"row\">");
      out.print(b.getReviewNo() );
      out.write("</th>\r\n");
      out.write("\t\t\t<td>\r\n");
      out.write("\t\t\t<!-- 첨부파일이 있는 경우, \r\n");
      out.write("\t\t\tfile.png이미지가 해당 td에 보이도록 하세요 -->\r\n");
      out.write("\t\t\t\t");
if(b.getReviewOriginalImg()!=null){
					String[] renamedImgList = b.getReviewRenamedImg().split("§");
      out.write("\r\n");
      out.write("\t\t\t\t\t<img src=\"");
      out.print(request.getContextPath());
      out.write("/upload/board/review/");
      out.print(renamedImgList[0]);
      out.write("\" alt=\"첨부파일\" style='width:80px;'/>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t");
}
      out.write("\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t<td>");
      out.print(b.getReviewKind() );
      out.write("</td>\r\n");
      out.write("\t\t\t<td>");
      out.print(b.getReviewTitle() );
      out.write("<!-- </a> --></td>\r\n");
      out.write("\t\t\t<td>");
      out.print(b.getReviewWriter() );
      out.write("</td>\r\n");
      out.write("\t\t\t<td>");
      out.print(b.getReviewEnrollDate() );
      out.write("</td>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t");
} 
		}
      out.write("\r\n");
      out.write("\t</table>\r\n");
      out.write("\t<div id=\"pageBar\">\r\n");
      out.write("\t\t");
      out.print(pageBar );
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("</section>\r\n");
      out.write("\r\n");
      out.write("\r\n");
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
      out.write("<script>\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\tvar dWidth=$(window).width();\r\n");
      out.write("\t\tvar dHeight=$(window).height();\r\n");
      out.write("\t\t$('section').css({height:dHeight+'px'});\r\n");
      out.write("\r\n");
      out.write("\t\t//선택되어져 있는 네비게이션 표시\r\n");
      out.write("\t\t$(window).scroll(function(e){\r\n");
      out.write("\t\tvar ht=$(window).height();\r\n");
      out.write("\t\tvar dScroll=$(window).scrollTop();\r\n");
      out.write("\t\tif(dScroll>=0 && dScroll<ht){\r\n");
      out.write("\t\t\t$('nav>ul>li').removeClass('on');\r\n");
      out.write("\t\t\t$('nav>ul>li:nth-child(1)').addClass('on');\r\n");
      out.write("\t\t\t$('.headerWrap').css({'background':'none', 'border-bottom':'none'});\r\n");
      out.write("\t\t\t$('header>h1>a>img').attr('src','images/main/logo_white.png');\r\n");
      out.write("\t\t\t$('header>div>a>img').attr('src','images/main/icon_meun.png');\r\n");
      out.write("\t\t\t$('header>div>ul>li>a').css('color','#fff');\r\n");
      out.write("\t\t\t$('.headerWrap').mouseover(function(e){$(this).css('background','rgba(34,34,34,0.7)');});\r\n");
      out.write("\t\t\t$('.headerWrap').mouseout(function(e){$(this).css('background','none');});\r\n");
      out.write("\t\t\t$('header .asideNav').css('background','url(\"images/main/bg_gnbLine.png\") no-repeat left center');\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\tif(dScroll>=ht && dScroll<ht*2){\r\n");
      out.write("\t\t\t$('nav>ul>li').removeClass('on');\r\n");
      out.write("\t\t\t$('nav>ul>li:nth-child(2)').addClass('on');\r\n");
      out.write("\t\t\tif(dWidth <= 450){\r\n");
      out.write("\t\t\t\t$('.headerWrap').css({'background':'#fff', 'height':'7%'});\r\n");
      out.write("\t\t\t} else {\r\n");
      out.write("\t\t\t\t$('.headerWrap').css({'background':'#fff', 'height':'84px', 'border-bottom':'1px solid #eee', 'box-sizing':'border-box'});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t$('header>h1>a>img').attr('src','images/main/logo_color.png');\r\n");
      out.write("\t\t\t$('header>div>a>img').attr('src','images/main/icon_meun_black.png');\r\n");
      out.write("\t\t\t$('header>div>ul>li>a').css('color','#222');\r\n");
      out.write("\t\t\t$('.headerWrap').mouseover(function(e){$(this).css('background','#fff');});\r\n");
      out.write("\t\t\t$('.headerWrap').mouseout(function(e){$(this).css('background','#fff');});\r\n");
      out.write("\t\t\t$('header .asideNav').css('background','url(\"images/main/bg_gnbLine_100.png\") no-repeat left center');\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\tif(dScroll>=ht*2 && dScroll<ht*3){\r\n");
      out.write("\t\t\t$('nav>ul>li').removeClass('on');\r\n");
      out.write("\t\t\t$('nav>ul>li:nth-child(3)').addClass('on');\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\tif(dScroll>=ht*3 && dScroll<ht*4){\r\n");
      out.write("\t\t\t$('nav>ul>li').removeClass('on');\r\n");
      out.write("\t\t\t$('nav>ul>li:nth-child(4)').addClass('on');\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t//메뉴버튼 클릭시 페이지 이동 이벤트\r\n");
      out.write("\t\t$('nav>ul>li>a').click(function(e){\r\n");
      out.write("\t\t\tvar secPos=$(this).attr('href'); //해당아이디\r\n");
      out.write("\t\t\tvar sec=$(secPos).position().top;  //top의 수치를 가져오기 위함\r\n");
      out.write("\t\t\t$('body,html').stop().animate({scrollTop:sec},1200,'swing');\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t//마우스 휠 이벤트\r\n");
      out.write("\t\t$('section').on('mousewheel',function(event,delta){\r\n");
      out.write("\t\t\tif(delta>0 && $('#sec02','#sec03','#sec04')){\r\n");
      out.write("\t\t\t\tvar prev=$(this).prev().offset().top;\r\n");
      out.write("\t\t\t\t$('body,html').stop().animate({scrollTop:prev},1200,'swing');\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\tif(delta<0 && $('#sec01','#sec02','#sec03')){\r\n");
      out.write("\t\t\t\tvar next=$(this).next().offset().top;\r\n");
      out.write("\t\t\t\t$('body,html').stop().animate({scrollTop:next},1200,'swing');\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tif(dWidth <= 767 && $(window).scrollTop() > 0){\r\n");
      out.write("\t\t\t\t$('.headerWrap').css('background','#fff!important');\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//header width값 고정\r\n");
      out.write("\t\tif(dWidth < 1024){\r\n");
      out.write("\t\t\t$('header').css('width','1024px');\r\n");
      out.write("\t\t}\t\t\r\n");
      out.write("\r\n");
      out.write("\t\t//반응형(모바일)\r\n");
      out.write("\t\tif(dWidth <= 767){\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t/*header*/\r\n");
      out.write("\t\t\t$('.headerWrap').css({'position':'fixed', 'height':'7%!important'});\r\n");
      out.write("\t\t\t$('.headerWrap>header').css('width','100%');\r\n");
      out.write("\t\t\t/*$('header').css({'padding-top':dWidth/100*5, 'margin-left':'0', 'left':'5%', 'height':'auto'});*/\r\n");
      out.write("\t\t\t$('header').css({'padding':'5%', 'margin-left':'0', 'left':'auto', 'height':'auto'});\r\n");
      out.write("\t\t\t$('header>div>ul').hide();\r\n");
      out.write("\t\t\t$('header>h1').css({'margin-top':'0', 'width':'19%'});\r\n");
      out.write("\t\t\t$('header>div').css('width','5.85%');\r\n");
      out.write("\t\t\t$('header>div>a').css({'margin-top':'0', 'margin-left':'0'});\r\n");
      out.write("\t\t\t$('header>div>a').attr('onClick','$(\".menuWrap_m\").fadeIn(300);');\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t/*menu*/\r\n");
      out.write("\t\t\t$('.menuWrap_m>.menu>a').click($(function(){$('#div').hide()})); \r\n");
      out.write("\t\t\t$('.menuWrap_m .menu').css('height',dHeight);\r\n");
      out.write("\t\t\t$('.menuWrap_m .menu>div').css({'padding-top':dWidth/100*5, 'margin-left':'0', 'left':'5%', 'height':'12%', 'box-sizing':'border-box'});\r\n");
      out.write("\t\t\t//$('.menuWrap_m .menu>div').css('padding-bottom',dWidth/100*5);\r\n");
      out.write("\t\t\t$('.menuWrap_m dl>dt').css('padding-top',dWidth/100*5);\r\n");
      out.write("\t\t\t$('.menuWrap_m dl>dt').css('padding-bottom',dWidth/100*5);\r\n");
      out.write("\t\t\t$('.menuWrap_m dl>dd>p').css('padding-top',dWidth/100*5*0.772);\r\n");
      out.write("\t\t\t$('.menuWrap_m dl>dd>p').css('padding-bottom',dWidth/100*5*0.772);\r\n");
      out.write("\r\n");
      out.write("\t\t\t/*sec01*/\r\n");
      out.write("\t\t\t$('#sec01 h1>p').html(\"KG에듀원의 또 다른 이름은<br> 꿈을 이루는 '드림파트너'입니다.\");\r\n");
      out.write("\t\t\t$('#sec01 .btn_magazine>img').attr('src','images/main/btn_magazine_m.png');\r\n");
      out.write("\r\n");
      out.write("\t\t\t/*sec02*/\r\n");
      out.write("\t\t\t$('#sec02 h2').html('KG에듀원의<br>브랜드를 소개합니다.');\r\n");
      out.write("\t\t\t$('#sec02 .brand>li').removeClass('mt50 mt-50');\r\n");
      out.write("\t\t\t$('#sec02 .brand>li>a').height(dWidth/100*28.888);\r\n");
      out.write("\t\t\t$('.brandWrap').removeClass('swiper-container');\r\n");
      out.write("\t\t\t$('.brand').removeClass('swiper-wrapper');\r\n");
      out.write("\t\t\t$('.brand>li').removeClass('swiper-slide');\r\n");
      out.write("\r\n");
      out.write("\t\t\t/*sec03*/\r\n");
      out.write("\t\t\t$('#sec03 h2').html('KG에듀원의 새로운 소식');\r\n");
      out.write("\t\t\t$('#sec03 .swiper-container>ul').addClass('swiper-wrapper');\r\n");
      out.write("\t\t\t$('#sec03 .swiper-container>ul>li').addClass('swiper-slide');\r\n");
      out.write("\r\n");
      out.write("\t\t\t/*sec04*/\r\n");
      out.write("\t\t\t$('#sec04 h2').html('KG에듀원과<br>함께 할 당신을 기다립니다.');\r\n");
      out.write("\t\t\t$('#sec04 .career').height(dWidth/100*28.888);\r\n");
      out.write("\t\t\t$('#sec04 ul>li').removeClass('rightBox');\r\n");
      out.write("\t\t\t$('#sec04 .career>.careerTxt>p').html('자세히보기');\r\n");
      out.write("\r\n");
      out.write("\t\t\t/*sec05*/\r\n");
      out.write("\t\t\t$('#sec05 .noticeWrap>.linkBnr>ul>li').height(dWidth/100*35.533);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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
