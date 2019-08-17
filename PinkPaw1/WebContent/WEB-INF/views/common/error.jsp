<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%
	//error code로 넘어온 경우, exception내장객체는 null이다.
	int code = response.getStatus();//에러코드가 담김 ex)404

	String msg = exception!=null? 
					exception.getMessage():String.valueOf(code);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
<style>
body{text-align: center;}
#e-msg{color:red; font-size:2em;}
</style>
</head>
<body>
	<h2>재고관리 오류</h2>
	<p><span id="e-msg"><%=msg%></span></p>
	<a href="<%=request.getContextPath()%>">
		<img src="<%=request.getContextPath() %>/images/home.png" 
			width="30px"
			alt="메인페이지이동" />
	</a>
</body>
</html>