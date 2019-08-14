<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.pinkpaw.board.dmboard.model.vo.reportDM, java.util.*" %>
<%
	List<reportDM> list = (List<reportDM>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />

<section id="board-container">
	<h2>신고쪽지</h2>
	
		
	<table id="tbl-board">
		<tr>
			<th>보낸사람</th>
			<th>받은사람</th>
			<th>제목</th>
			<th>날짜</th>
		</tr>

				<% for(reportDM d : list){ %>
		<tr>
			<td><%=d.getDmSend() %></td>
			<td><%=d.getDmRecive() %></td>
			<td>
				<a href="<%=request.getContextPath()%>/board/dm/reportDMView?dmNo=<%=d.getDmNo() %>">
					<%=d.getDmTitle() %>
				</a>
			</td>
			<td><%= d.getDmDate() %></td>
		</tr>
		<% } %>
	</table>

	<div id='pageBar'>
		<%=pageBar %>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>