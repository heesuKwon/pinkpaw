/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.42
 * Generated at: 2019-08-16 06:45:48 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.board.dm;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.pinkpaw.member.model.vo.Member;

public final class DMWrite_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");

Member memberLoggedIn = new Member();
memberLoggedIn.setMemberId("admin");

String memberId = (String)request.getAttribute("memberId");


      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<script src=\"");
      out.print( request.getContextPath());
      out.write("/js/jquery-3.4.1.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("function validate(){\r\n");
      out.write("\tvar content = $(\"[name=dmContent]\").val();\r\n");
      out.write("\tvar title = $(\"[name=dmTitle]\").val();\r\n");
      out.write("\tvar dmWriter = $(\"[name=dmWriter]\").val();\r\n");
      out.write("\tvar dmSender = $(\"[name=dmSender]\").val();\r\n");
      out.write("\tif(content.trim().length == 0 || title.trim().length == 0){\r\n");
      out.write("\t\talert(\"제목과 내용을 입력하세요.\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tvar dm = {\r\n");
      out.write("\t\t\ttype: \"dm\",\r\n");
      out.write("\t\t\tmsg: content,\r\n");
      out.write("\t\t\tsender: dmWriter,\r\n");
      out.write("\t\t\ttitle: title,\r\n");
      out.write("\t\t\t/* receiver: $(\"#dm-client option:selected\").val(), */\r\n");
      out.write("\t\t\treceiver: dmSender\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\tvar param = {\r\n");
      out.write("\t\t\t\tdm: JSON.stringify(dm)\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\turl:\"");
      out.print(request.getContextPath());
      out.write("/DMWriteEnd\",\r\n");
      out.write("\t\t\tdata: param,\r\n");
      out.write("\t\t\tdataType: \"json\",\r\n");
      out.write("\t\t\tsuccess: data => {\r\n");
      out.write("\t\t\t\tvar html = \"\";\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tif(data == false){\r\n");
      out.write("\t\t\t\t\talert(\"해당회원 아이디가 없습니다.\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\telse{\r\n");
      out.write("\t\t\t\t\talert(\"메세지를 보냈습니다.\")\r\n");
      out.write("\t\t\t\t\t\t\tself.close();\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t}, \r\n");
      out.write("\t\t\terror: (jqxhr, textStatus, err)=>{\r\n");
      out.write("\t\t\t\tconsole.log(\"ajax처리실패!\");\r\n");
      out.write("\t\t\t\tconsole.log(jqxhr, textStatus, err);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t});\r\n");
      out.write("\treturn true;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div id=\"reportMissing-container\">\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\t<table>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th>보낼 사람</th>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" name=\"dmSender\" id=\"dmSender\"\r\n");
      out.write("\t\t\t\t\t\trequired>\r\n");
      out.write("\t\t\t\t\t<input type=\"hidden\" name=\"dmWriter\" id=\"dmWriter\" value=\"");
      out.print(memberLoggedIn.getMemberId() );
      out.write("\" />\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th>쪽지 제목 </th>\r\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" name=\"dmTitle\" id=\"dmTitle\"\r\n");
      out.write("\t\t\t\t\t\tvalue=\"\"  required>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th>쪽지 내용</th>\r\n");
      out.write("\t\t\t\t\t<td><textarea name=\"dmContent\" cols=\"40\" rows=\"5\"\r\n");
      out.write("\t\t\t\t\t\t\tplaceholder=\"내용을 입력해주세요.\"></textarea></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"2\"><input type=\"button\" value=\"보내기\"\r\n");
      out.write("\t\t\t\t\t\tonclick=\"return validate();\" />&nbsp; <input type=\"button\"\r\n");
      out.write("\t\t\t\t\t\tonclick=\"self.close();\" value=\"닫기\" /></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<script>\r\n");
      out.write("function reportValidate() {\r\n");
      out.write("\tvar content = $(\"[name=reportContent]\").val();\r\n");
      out.write("\t//좌우 공백을 제거하고 길이가 0이면(내용이 비어 있는 경우)\r\n");
      out.write("\tif(content.trim().length == 0){\r\n");
      out.write("\t\talert(\"내용을 입력하세요.\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\treturn true;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
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
