/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.42
 * Generated at: 2019-08-15 14:59:47 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.board.community.parcelout;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.pinkpaw.member.model.vo.Member;

public final class parceloutReport_jsp extends org.apache.jasper.runtime.HttpJspBase
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

	int parceloutNo = Integer.parseInt(request.getParameter("parceloutNo"));
	/* Member memberLoggedIn
	= (Member)session.getAttribute("memberLoggedIn"); */
	Member memberLoggedIn = new Member();
	memberLoggedIn.setMemberId("admin");
	System.out.println("memberLoggedIn@index.jsp="+memberLoggedIn);

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>신고하기</title>\r\n");
      out.write("<link rel=\"stylesheet\" \r\n");
      out.write("\t  href=\"");
      out.print(request.getContextPath());
      out.write("/css/board.css\" />\r\n");
      out.write("<script src=\"");
      out.print( request.getContextPath());
      out.write("/js/jquery-3.4.1.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<section class=\"board-container\">\r\n");
      out.write("<div id=\"reportParcelout-container\">\r\n");
      out.write("\t\t<form name=\"reportFrm\" action=\"");
      out.print(request.getContextPath());
      out.write("/board/parceloutBoard/parceloutReport\" method=\"post\" >\r\n");
      out.write("\t\t\t<table>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th>작성자</th>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input type=\"text\" name=\"reportWriter\" id=\"reportWriter\" value=\"");
      out.print(memberLoggedIn.getMemberId());
      out.write("\" readonly required>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th>신고 내용</th>\r\n");
      out.write("\t\t\t\t\t<td>\t\r\n");
      out.write("\t\t\t\t\t\t<!-- <textarea name=\"reportContent\" cols=\"40\" rows=\"5\"\r\n");
      out.write("\t\t\t\t\t\tplaceholder=\"내용을 입력해주세요.\"></textarea> -->\r\n");
      out.write("\t\t\t\t\t\t<select name=\"parceloutReportContent\" id=\"parceloutReportContent\" onchange=\"change()\">\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"\">신고 사유 선택</option>\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"광고글\">광고글</option>\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"언어폭력(욕설,비방,명예훼손 등)\">언어폭력(욕설,비방,명예훼손 등)</option>\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"부적절한 이미지\">부적절한 이미지</option>\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"부적절한 내용\">부적절한 내용</option>\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"기타\">기타(직접 입력)</option>\r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t<div id=\"parceloutOtherReason\">\r\n");
      out.write("\t\t\t\t\t\t\t<textarea name=\"parceloutOtherReason\" cols=\"40\" rows=\"5\" placeholder=\"내용을 입력해주세요.\" ></textarea>\r\n");
      out.write("\t\t\t\t\t\t</div>\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<script>\r\n");
      out.write("\t\t\t\t\t\t\t$(\"#parceloutOtherReason\").hide();\r\n");
      out.write("\t\t\t\t\t\t\tfunction change() {\r\n");
      out.write("\t\t\t\t\t\t\t\tvar state = $('#parceloutReportContent option:selected').val();\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\tif(state == \"기타\"){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$(\"#parceloutOtherReason\").show();\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\telse{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$(\"#parceloutOtherReason\").hide();\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t</script>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"2\">\r\n");
      out.write("\t\t\t\t\t\t<input type=\"submit\"  value=\"신고보내기\" onclick=\"return reportValidate();\"/>&nbsp;\r\n");
      out.write("\t\t\t\t\t\t<input type=\"button\" value=\"취소\" onclick=\"self.close();\"/>\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t\t<input type=\"hidden\" name=\"parceloutNo\" value=\"");
      out.print(parceloutNo);
      out.write("\" />\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("</section>\r\n");
      out.write("<script>\r\n");
      out.write("function reportValidate() {\r\n");
      out.write("\tvar content = $(\"[name=parceloutReportContent]\").val();\r\n");
      out.write("\t//좌우 공백을 제거하고 길이가 0이면(내용이 비어 있는 경우)\r\n");
      out.write("\tif(content.trim().length == 0){\r\n");
      out.write("\t\talert(\"신고 사유를 선택하세요.\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\treturn true;\r\n");
      out.write("}\r\n");
      out.write("$(()=>{\r\n");
      out.write("\t\r\n");
      out.write("\tvar close = ");
      out.print(request.getParameter("close"));
      out.write(";\r\n");
      out.write("\tif(close==true){\r\n");
      out.write("\t\tself.close();\r\n");
      out.write("\t}\r\n");
      out.write("})();\r\n");
      out.write("\r\n");
      out.write("\r\n");
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
