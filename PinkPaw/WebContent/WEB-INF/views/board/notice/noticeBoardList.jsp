<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.pinkpaw.board.noticeboard.model.vo.*, java.util.*" %>
<%
	List<NoticeBoard> list = (List<NoticeBoard>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />

<section id="board-container">
	<h2>게시판</h2>
	
		<%-- 관리자만 글쓰기가능 --%>
	<% if(memberLoggedIn!=null && "admin".equals(memberLoggedIn.getMemberId())) {%>
	<input type="button" value="글쓰기" id="btn-add"
		   onclick="goBoardForm();" />
	<script>
	function goBoardForm(){
		location.href 
			= "<%=request.getContextPath()%>/board/notice/noticeBoardForm";	
	}
	</script>
	<% } %>
	
	<table id="tbl-board">
		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>

				<% for(NoticeBoard b : list){ %>
		<tr>
			<td><%=b.getNoticeNo() %></td>
			<td><%= b.getNoticeWriter() %></td>
			<td>
				<a href="<%=request.getContextPath()%>/board/notice/noticeBoardView?noticeNo=<%=b.getNoticeNo() %>">
					<%= b.getNoticeTitle() %>
				</a>
			</td>
			<td><%= b.getNoticeEnrollDate() %></td>
			<td><%=b.getNoticeCount() %></td>
		</tr>
		<% } %>
	</table>

	<div id='pageBar'>
		<%=pageBar %>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>