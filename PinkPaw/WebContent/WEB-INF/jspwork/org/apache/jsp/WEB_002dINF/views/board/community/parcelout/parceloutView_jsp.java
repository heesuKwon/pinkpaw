/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.42
 * Generated at: 2019-08-14 17:04:08 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.board.community.parcelout;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.pinkpaw.board.common.model.vo.BoardComment;
import java.util.List;
import com.pinkpaw.board.parceloutboard.model.vo.ParceloutBoard;

public final class parceloutView_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("com.pinkpaw.board.common.model.vo.BoardComment");
    _jspx_imports_classes.add("com.pinkpaw.board.parceloutboard.model.vo.ParceloutBoard");
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

	ParceloutBoard p = (ParceloutBoard)request.getAttribute("p");
	System.out.println("p@view="+p);
	List<BoardComment> commentList = (List<BoardComment>)request.getAttribute("commentList");
	System.out.println("commentList@view="+commentList);
	

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>분양동물 게시판 상세보기</title>\r\n");
      out.write("<script src=\"");
      out.print( request.getContextPath());
      out.write("/js/jquery-3.4.1.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">\r\n");
      out.write("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\" integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("<style>\r\n");
      out.write("div.comment-editor button#btn-insert{\r\n");
      out.write("\twidth: 60px;\r\n");
      out.write("\theight: 50px;\r\n");
      out.write("\tcolor: white;\r\n");
      out.write("\tbackground-color: #30f;\r\n");
      out.write("\tposition: relative;\r\n");
      out.write("\ttop: -20px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/*댓글테이블*/\r\n");
      out.write("table#tbl-comment{width:580px; margin:0 auto; border-collapse:collapse; clear:both;} \r\n");
      out.write("table#tbl-comment tr td{border-bottom:1px solid; border-top:1px solid; padding:5px; text-align:left; line-height:120%;}\r\n");
      out.write("table#tbl-comment tr td:first-of-type {padding: 5px 5px 5px 50px;}\r\n");
      out.write("table#tbl-comment tr td:last-of-type {text-align:right; width: 100px;}\r\n");
      out.write("table#tbl-comment button.btn-reply{display:none;}\r\n");
      out.write("table#tbl-comment tr:hover {background:lightgray;}\r\n");
      out.write("table#tbl-comment tr:hover button.btn-reply{display:inline;}\r\n");
      out.write("table#tbl-comment sub.comment-writer {color:navy; font-size:14px}\r\n");
      out.write("table#tbl-comment sub.comment-date {color:tomato; font-size:10px}\r\n");
      out.write("\r\n");
      out.write("table#tbl-comment tr.level2 {color:gray; font-size: 14px;}\r\n");
      out.write("table#tbl-comment tr.level2 td:first-of-type{padding-left:100px;}\r\n");
      out.write("table#tbl-comment tr.level2 sub.comment-writer {color:#8e8eff; font-size:14px}\r\n");
      out.write("table#tbl-comment tr.level2 sub.comment-date {color:#ff9c8a; font-size:10px}\r\n");
      out.write("\r\n");
      out.write("/* 답글관련 */\r\n");
      out.write("table#tbl-comment textarea{\r\n");
      out.write("\tmargin: 4px 0 0;\r\n");
      out.write("}\r\n");
      out.write("table#tbl-comment button.btn-insert2{\r\n");
      out.write("\twidth: 60px;\r\n");
      out.write("\theight: 23px;\r\n");
      out.write("\tcolor: white;\r\n");
      out.write("\tbackground: #30f;\r\n");
      out.write("\tposition: relative;\r\n");
      out.write("\ttop: -5px;\r\n");
      out.write("\tleft: 10px;\r\n");
      out.write("}\r\n");
      out.write("table#tbl-comment button.btn-delete{\r\n");
      out.write("\tbackground: red;\r\n");
      out.write("\tcolor: white;\r\n");
      out.write("\tdisplay: none;\r\n");
      out.write("}\r\n");
      out.write("table#tbl-comment tr:hover button.btn-delete{\r\n");
      out.write("\tdisplay: inline;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("<script>\r\n");
      out.write("function goBoardList(){\r\n");
      out.write("\tlocation.href \r\n");
      out.write("\t\t= \"");
      out.print(request.getContextPath());
      out.write("/board/parcelout/parceloutList\";\t\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("$(()=>{\r\n");
      out.write("\t\r\n");
      out.write("\t//boardCommentFrm 유효성 검사: jquery.submit이벤트 핸들러\r\n");
      out.write("\t$(\"[name=boardCommentFrm]\").submit((e)=>{\r\n");
      out.write("\t\t//댓글 유효성 검사\r\n");
      out.write("\t\tvar len = $(\"#boardCommentContent\").val()\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t.trim()\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t.length;\r\n");
      out.write("\t\t//작성된 글이 없는 경우\r\n");
      out.write("\t\tif(len == 0){\r\n");
      out.write("\t\t\te.preventDefault();//제출을 못하도록 함.\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("\t//답글(대댓글) 작성\r\n");
      out.write("\t$(\".btn-reply\").on(\"click\",(e)=>{\r\n");
      out.write("\t\t\tvar tr = $(\"<tr></tr>\");\r\n");
      out.write("\t\t\tvar html = \"<td style='display:none; text-align:left;' colspan='2'>\";\r\n");
      out.write("\t\t\thtml += \"<form action='");
      out.print(request.getContextPath());
      out.write("/board/parceloutboard/boardCommentInsert' method='post'>\";\r\n");
      out.write("\t\t\thtml += \"<input type= 'hidden' name='boardRef' value='");
      out.print(p.getParceloutNo());
      out.write("'/>\";\r\n");
      out.write("\t\t\thtml += \"<input type= 'hidden' name='boardCommentWriter' value='admin'/>\";\r\n");
      out.write("\t\t\thtml += \"<input type= 'hidden' name='boardCommentLevel' value='2'/>\";//답글(대댓글)이기 때문에 2로 작성\r\n");
      out.write("\t\t\thtml += \"<input type= 'hidden' name='boardCommentRef' value='\"+e.target.value+\"'/>\";\r\n");
      out.write("\t\t\thtml += \"<textarea name='boardCommentContent' cols='60' rows='1'></textarea>\";\r\n");
      out.write("\t\t\thtml += \"<button type='submit' class='btn-insert2'>등록</button>\";\r\n");
      out.write("\t\t\thtml += \"</form>\";\r\n");
      out.write("\t\t\thtml += \"</td>\";\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\ttr.html(html);\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t//클릭한 버튼이 속한 tr 다음에 tr을 추가\r\n");
      out.write("\t\t\ttr.insertAfter($(e.target).parent().parent())\r\n");
      out.write("\t\t\t\t.children(\"td\").slideDown(800)//td가 0.8초동안 슬라이드가 밑으로 내려옴.\r\n");
      out.write("\t\t\t\t.children(\"form\").submit((e)=>{//form이 제출 될때\r\n");
      out.write("\t\t\t\t\t\t\t\t\t//여기서 e는 form을 가리킴\r\n");
      out.write("\t\t\t\t\t\t\t\t\tconsole.log($(e.target));\r\n");
      out.write("\t\t\t\t\t\t\t\t\tvar len = $(e.target).children(\"textarea\")\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t.val()\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t.trim()\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t.length;\r\n");
      out.write("\t\t\t\t\t\t\t\t\tif(len == 0){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\te.preventDefault();\r\n");
      out.write("\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t});\r\n");
      out.write("\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t//댓글/답글 삭제버튼 클릭시\r\n");
      out.write("\t$(\".btn-delete\").click(function(){\r\n");
      out.write("    if(!confirm(\"정말 삭제하시겠습니까?\")) return;\r\n");
      out.write("    //삭제처리후 돌아올 현재게시판번호도 함께 전송함.\r\n");
      out.write("    location.href=\"");
      out.print(request.getContextPath());
      out.write("/board/parceloutBoard/ParceloutCommentDelete?parceloutNo=");
      out.print(p.getParceloutNo());
      out.write("&del=\"+$(this).val();\r\n");
      out.write("\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("});\r\n");
      out.write("\t\t\t\r\n");
      out.write("function goParceloutViewReportOpen(){\r\n");
      out.write("\tvar url = \"");
      out.print(request.getContextPath());
      out.write("/board/parcelout/parceloutReport?parceloutNo=");
      out.print(p.getParceloutNo());
      out.write("\";\r\n");
      out.write("\tvar target = \"new\";\r\n");
      out.write("\tvar option = \"top=200, left=450, width=450, height=300\";\r\n");
      out.write("\t\r\n");
      out.write("\twindow.open(url,target,option);\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<h2>분양동물</h2>\r\n");
      out.write("<section id=\"board-container\">\r\n");
      out.write("<h2>게시판 상세보기</h2>\r\n");
      out.write("<input type=\"button\" value=\"목록\" onclick=\"goBoardList();\"/>\r\n");
      out.write("<input type=\"button\" value=\"신고하기\" onclick=\"goParceloutViewReportOpen();\"\t />\r\n");
      out.write("<table id=\"tbl-parcelout-view\">\r\n");
      out.write("<tr>\r\n");
      out.write("\t<th>\r\n");
      out.write("\t\t<td>\r\n");
      out.write("\t<div id=\"carouselExampleControls\" class=\"carousel slide\" data-ride=\"carousel\">\r\n");
      out.write("  <div class=\"carousel-inner\">\r\n");
      out.write("\t\t");
 if(p.getParceloutOriginalImg() != null){
				String[] renamedImgList = p.getParceloutRenamedImg().split("§");
	if(renamedImgList.length == 1){ 
      out.write("\r\n");
      out.write("    <div class=\"carousel-item active\">\r\n");
      out.write("\t<img src=\"");
      out.print(request.getContextPath());
      out.write("/upload/board/parcelout/");
      out.print(renamedImgList[0]);
      out.write("\" class=\"d-block w-100\" width=\"500\" height=\"300\"/>\t\r\n");
      out.write("    </div>\r\n");
      out.write("    ");
 
	}else if(renamedImgList.length == 2){
    
      out.write("\r\n");
      out.write("    <div class=\"carousel-item active\">\r\n");
      out.write("\t<img src=\"");
      out.print(request.getContextPath());
      out.write("/upload/board/parcelout/");
      out.print(renamedImgList[0]);
      out.write("\" class=\"d-block w-100\" width=\"500\" height=\"300\"/>\t\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"carousel-item\">\r\n");
      out.write("\t<img src=\"");
      out.print(request.getContextPath());
      out.write("/upload/board/parcelout/");
      out.print(renamedImgList[1]);
      out.write("\" class=\"d-block w-100\" width=\"500\" height=\"300\"/>\t\r\n");
      out.write("    </div>\r\n");
      out.write("    ");
 
	}else if(renamedImgList.length == 3){
    
      out.write("\r\n");
      out.write("    <div class=\"carousel-item active\">\r\n");
      out.write("\t<img src=\"");
      out.print(request.getContextPath());
      out.write("/upload/board/parcelout/");
      out.print(renamedImgList[0]);
      out.write("\" class=\"d-block w-100\" width=\"500\" height=\"300\"/>\t\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"carousel-item\">\r\n");
      out.write("\t<img src=\"");
      out.print(request.getContextPath());
      out.write("/upload/board/parcelout/");
      out.print(renamedImgList[1]);
      out.write("\" class=\"d-block w-100\" width=\"500\" height=\"300\"/>\t\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"carousel-item\">\r\n");
      out.write("\t<img src=\"");
      out.print(request.getContextPath());
      out.write("/upload/board/parcelout/");
      out.print(renamedImgList[2]);
      out.write("\" class=\"d-block w-100\" width=\"500\" height=\"300\"/>\t\r\n");
      out.write("    </div>\r\n");
      out.write("  </div>\r\n");
      out.write("\t");
 }
		}
      out.write("\r\n");
      out.write("  <a class=\"carousel-control-prev\" href=\"#carouselExampleControls\" role=\"button\" data-slide=\"prev\">\r\n");
      out.write("    <span class=\"carousel-control-prev-icon\" aria-hidden=\"true\"></span>\r\n");
      out.write("    <span class=\"sr-only\">Previous</span>\r\n");
      out.write("  </a>\r\n");
      out.write("  <a class=\"carousel-control-next\" href=\"#carouselExampleControls\" role=\"button\" data-slide=\"next\">\r\n");
      out.write("    <span class=\"carousel-control-next-icon\" aria-hidden=\"true\"></span>\r\n");
      out.write("    <span class=\"sr-only\">Next</span>\r\n");
      out.write("  </a>\r\n");
      out.write("</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t</td>\r\n");
      out.write("\t</th>\r\n");
      out.write("</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>글번호</th>\r\n");
      out.write("\t\t\t\t<td>");
      out.print(p.getParceloutNo() );
      out.write("</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>제 목</th>\r\n");
      out.write("\t\t\t\t<td>");
      out.print(p.getParceloutTitle() );
      out.write("</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>작성자</th>\r\n");
      out.write("\t\t\t\t<td>");
      out.print(p.getParceloutWriter() );
      out.write("</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>조회수</th>\r\n");
      out.write("\t\t\t\t<td>");
      out.print(p.getParceloutCount() );
      out.write("</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>신고수</th>\r\n");
      out.write("\t\t\t\t<td>");
      out.print(p.getParceloutReportCount() );
      out.write("</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>신고 사유</th>\r\n");
      out.write("\t\t\t\t<td>");
      out.print(p.getParceloutReportReason() );
      out.write("</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>첨부파일</th>\r\n");
      out.write("\t\t\t<td>\r\n");
      out.write("\t\t\t\t<!-- 첨부파일이 있는 경우만 보임 처리 -->\r\n");
      out.write("\t\t\t\t");
 if(p.getParceloutOriginalImg() != null){
				String[] renamedImgList = p.getParceloutRenamedImg().split("§");
					for(int i=0;i<renamedImgList.length;i++){
      out.write("\r\n");
      out.write("\t\t\t\t<img src=\"");
      out.print(request.getContextPath());
      out.write("/upload/board/parcelout/");
      out.print(renamedImgList[i]);
      out.write("\" alt=\"첨부파일\"  style='width:200px;' />\t\r\n");
      out.write("\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t");
 }
				}
      out.write("\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t</tr>\t\t\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>내용</th>\r\n");
      out.write("\t\t\t<td>");
      out.print(p.getParceloutContent());
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<!-- 글작성자/관리자인 경우에만 수정/삭제버튼이 보이도록함. -->\t\r\n");
      out.write("\r\n");
      out.write("<!-- \t\t\t(b.getBoardWriter().equals(memberLoggedIn.getMemberId()) -->\r\n");
      out.write("<!-- \t\t\t|| \"admin\".equals(memberLoggedIn.getMemberId())) ){ %>\t -->\r\n");
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
      out.write("/board/parceloutboard/parceloutDelete\"\r\n");
      out.write("\t\t      name=\"parceloutDeleteFrm\"\r\n");
      out.write("\t\t      method=\"post\">\r\n");
      out.write("\t\t\t<input type=\"hidden\" name=\"parceloutNo\"\r\n");
      out.write("\t\t\t\t   value=\"");
      out.print(p.getParceloutNo());
      out.write("\" />\r\n");
      out.write("\t\t\t<input type=\"hidden\" name=\"parceloutRenamedImg\" \r\n");
      out.write("\t\t\t\t   value=\"");
      out.print(p.getParceloutRenamedImg()!=null?
						   		p.getParceloutRenamedImg():"");
      out.write("\"/>      \r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t\t<script>\r\n");
      out.write("\t\tfunction updateBoard(){\r\n");
      out.write("\t\t\tlocation.href = \"");
      out.print(request.getContextPath());
      out.write("/board/community/parcelout/parceloutUpdate?parceloutNo=");
      out.print(p.getParceloutNo());
      out.write("\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tfunction deleteBoard(){\r\n");
      out.write("\t\t\tif(!confirm(\"정말 삭제 하시겠습니까?\")){\r\n");
      out.write("\t\t\t\treturn;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t$(\"[name=parceloutDeleteFrm]\").submit();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\r\n");
      out.write("\t</table>\r\n");
      out.write("\t\r\n");
      out.write("\t<hr style=\"margin-top: 30px;\"/>\r\n");
      out.write("\t<div id=\"comment-container\">\r\n");
      out.write("\t\t<div class=\"comment-editor\">\r\n");
      out.write("\t\t\t<form action=\"");
      out.print(request.getContextPath());
      out.write("/board/parceloutboard/boardCommentInsert\"\r\n");
      out.write("\t\t\t\t  name=\"boardCommentFrm\"\r\n");
      out.write("\t\t\t\t  method=\"post\">\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"boardRef\" \r\n");
      out.write("\t\t\t\t\t   value=\"");
      out.print(p.getParceloutNo());
      out.write("\" />\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"boardCommentWriter\" value=\"admin\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"boardCommentLevel\" \r\n");
      out.write("\t\t\t\t\t   value=\"1\" />\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"boardCommentRef\" \r\n");
      out.write("\t\t\t\t\t   value=\"0\" /> <!-- 댓글인 경우 참조댓글이 없으므로 0으로 초기화 -->\r\n");
      out.write("\t\t\t\t<textarea name=\"boardCommentContent\" \r\n");
      out.write("\t\t\t\t\t\t  id=\"boardCommentContent\" \r\n");
      out.write("\t\t\t\t\t\t  cols=\"60\" rows=\"3\"></textarea>\r\n");
      out.write("\t\t\t\t<button type=\"submit\"\r\n");
      out.write("\t\t\t\t\t    id=\"btn-insert\">등록</button>\t\t\t\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- 댓글목록테이블 -->\r\n");
      out.write("\t\t<table id=\"tbl-comment\">\r\n");
      out.write("\t\t\t");

 			if(commentList != null){
			for(BoardComment bc : commentList){
 			if(bc.getBoardCommentLevel()==1){
      out.write("\r\n");
      out.write("\t\t\t\t\t<tr class=level1>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<sub class=comment-writer>");
      out.print(bc.getBoardCommentWriter() );
      out.write("</sub>\r\n");
      out.write("\t\t\t\t\t\t\t<sub class=comment-date>");
      out.print(bc.getBoardCommentDate());
      out.write("</sub>\r\n");
      out.write("\t\t\t\t\t\t\t<br />\r\n");
      out.write("\t\t\t\t\t\t\t");
      out.print(bc.getBoardCommentContent() );
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<button class=\"btn-reply\" \r\n");
      out.write("\t\t\t\t\t\t\t\t\tvalue=\"");
      out.print(bc.getBoardCommentNo());
      out.write("\">답글</button>\r\n");
      out.write("\t\t\t\t\t\t\t<button class=\"btn-delete\" value=\"");
      out.print(bc.getBoardCommentNo());
      out.write("\">삭제</button>\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t");
} 
				else{
      out.write("\r\n");
      out.write("\t\t\t\t\t<tr class=level2>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<sub class=comment-writer>");
      out.print(bc.getBoardCommentWriter() );
      out.write("</sub>\r\n");
      out.write("\t\t\t\t\t\t\t<sub class=comment-date>");
      out.print(bc.getBoardCommentDate());
      out.write("</sub>\r\n");
      out.write("\t\t\t\t\t\t\t<br />\r\n");
      out.write("\t\t\t\t\t\t\t");
      out.print(bc.getBoardCommentContent() );
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<button class=\"btn-delete\" value=\"");
      out.print(bc.getBoardCommentNo());
      out.write("\">삭제</button>\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t");

				}
					}
				} 
			
      out.write("\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</div>\t\r\n");
      out.write("</section>\r\n");
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