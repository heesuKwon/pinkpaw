<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PinkPaw</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
</head>
<body>
	<div id="container">
		<header>
			
			<!-- 로그인메뉴 끝 -->
			<!-- 메인메뉴 시작 -->
			<nav>
				<ul class="main-nav">
					<li><a href="<%=request.getContextPath()%>">Home</a></li>
					<li><a href="#">공지사항</a></li>
					<li><a href="<%=request.getContextPath()%>/board/boardList">게시판</a></li>
					<li><a href="<%=request.getContextPath()%>/photo/photoList">사진게시판</a></li>
				</ul>
			</nav>
			<!-- 메인메뉴 끝 -->
						
		</header>
		
		<section id="content">