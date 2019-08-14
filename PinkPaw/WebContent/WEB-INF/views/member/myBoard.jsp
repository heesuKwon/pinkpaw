<%@page import="com.pinkpaw.member.model.service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.pinkpaw.admin.model.vo.*, java.util.*" %>
<%@ page import="com.pinkpaw.member.model.vo.MyBoard" %>
<%@ include file="/WEB-INF/views/common/mainHeader.jsp" %>



<%
	

List<MyBoard> list = (List<MyBoard>)request.getAttribute("list");
String pageBar = (String)request.getAttribute("pageBar");

List<MyBoard> list1 = new MemberService().selectMyBoard(1, 10, memberLoggedIn.getMemberId());

	
%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />

<section id="board-container">
	<h2>내가 쓴 글 리스트</h2>
	
		
	<table id="tbl-board">
		<tr>
			<th>테이블명</th>
			<th>번호</th>
			<th>제목</th>
			<th>작성일</th>
		</tr>

				<% for(MyBoard m : list1){ %>
		<tr>
			<td><%=m.getMyTable() %></td>
			<td><%=m.getMyNo() %></td>
			<td>
				<a href="<%=request.getContextPath()%>/board/dm/reportDMView?dmNo=<%=m.getMyNo()%>">
					<%=m.getMyTitle() %>
				</a>
			</td>
			<td><%=m.getMyDate() %></td>
		</tr>
		<% } %>
	</table>

	<div id='pageBar'>
		<%=pageBar %>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>