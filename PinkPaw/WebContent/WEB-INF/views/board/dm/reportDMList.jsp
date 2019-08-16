<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.pinkpaw.board.dmboard.model.vo.reportDM, java.util.*" %>
<%
	List<reportDM> list = (List<reportDM>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />

<div id="img">
	<img id="reportDM_header" src="<%=request.getContextPath() %>/images/1.jpg" alt="헤더 - 쪽지 신고게시판 사진" />
</div>
<style>
	img#reportDM_header{
		width: 1024px;
		height: 300px;
	}
	#img{
		text-align: center;
	}
</style>

<section class="board-container">
	<table id="tbl-board" class="table table-hover">
		<tr>
			<th scope="col">보낸사람</th>
			<th scope="col">받은사람</th>
			<th scope="col">제목</th>
			<th scope="col">날짜</th>
		</tr>
		<%if(list==null || list.isEmpty()){ %>
		<tr>
			<td colspan="6" align="center">게시글이 없습니다.</td>
		</tr>
		<%}
		else{
			for(reportDM d : list){ %>
		<tr>
			<th scope="row"><%=d.getDmNo() %></th>
			<td><%=d.getDmSend() %></td>
			<td><%=d.getDmRecive() %></td>
			<td>
				<a href="<%=request.getContextPath()%>/board/dm/reportDMView?dmNo=<%=d.getDmNo() %>">
					<%=d.getDmTitle() %>
				</a>
			</td>
			<td><%= d.getDmDate() %></td>
		</tr>
		<% } }%>
	</table>

	<div id='pageBar'>
		<%=pageBar %>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>