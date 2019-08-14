<%@page import="com.pinkpaw.member.model.service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.pinkpaw.admin.model.vo.*, java.util.*" %>
<%@ page import="com.pinkpaw.member.model.vo.MyComment" %>
<%@ include file="/WEB-INF/views/common/mainHeader.jsp" %>


<%
	

String pageBar = (String)request.getAttribute("pageBar");

List<MyComment> list1 = new MemberService().selectMyComment(1, 10, memberLoggedIn.getMemberId());

	
%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />

<section id="board-container">
	<h2>내가 쓴 댓글 리스트</h2>
	
		
	<table id="tbl-board">
		<tr>
			<th>테이블명</th>
			<th>댓글내용</th>
			<th>작성일</th>
		</tr>

				<% for(MyComment m : list1){ %>
		<tr>
			<td><%=m.getMyCoTable() %></td>
			<td>
				<a href="<%=request.getContextPath()%>/board/dm/reportDMView?dmNo=<%=m.getMyCoREF()%>">
					<%=m.getMyCoContent() %>
				</a>
			</td>
			<td><%=m.getMyCoDate() %></td>
		</tr>
		<% } %>
	</table>

	<div id='pageBar'>
		<%=pageBar %>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>