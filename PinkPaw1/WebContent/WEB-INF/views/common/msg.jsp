<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	//Object -> String 형변환 필수
	String msg = (String)request.getAttribute("msg");
	String loc = (String)request.getAttribute("loc");
	
	System.out.println("msg@msg.jsp="+msg);
	System.out.println("loc@loc.jsp="+loc);
%>
<script>
alert("<%=msg%>");
//dml요청시 반드시 페이지 이동 필수! (새로고침시 같은 데이터를 다시 보내짐.)
location.href="<%=request.getContextPath() + loc%>";// /product+loc
</script>   