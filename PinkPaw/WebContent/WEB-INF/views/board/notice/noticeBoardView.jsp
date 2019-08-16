<%@page import="com.pinkpaw.board.noticeboard.model.vo.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ page import="com.pinkpaw.board.noticeboard.model.vo.*, java.util.*" %>
<%
	NoticeBoard b = (NoticeBoard)request.getAttribute("NoticeBoard");

%>
<link rel="stylesheet" 
	  href="<%=request.getContextPath()%>/css/board.css" />
  
<section id="board-container">
	<h2>공지사항</h2>
	<table class="table table-gray table-hover">
			<tr>
				<th>글번호</th>
				<td><%=b.getNoticeNo() %></td>
			</tr>
			<tr>
				<th>제 목</th>
				<td><%=b.getNoticeTitle() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=b.getNoticeWriter() %></td>
			</tr>
			<tr>
				<th>조회수</th>
				<td><%=b.getNoticeCount() %></td>
			</tr>
		
		<tr>
			<th>내용</th>
			<td><%=b.getNoticeContent()%></td>
		</tr>
		<!-- 관리자인 경우에만 수정/삭제버튼이 보이도록함. -->	
		<% if(memberLoggedIn!=null && "admin".equals(memberLoggedIn.getMemberId())) {%>	
		<tr>
			<th colspan="2">
				<input type="button" value="수정" 
					   onclick="updateBoard();" />
				<input type="button" value="삭제" 
					   onclick="deleteBoard();" />
			</th>
		</tr>
		<form action="<%=request.getContextPath()%>/board/notice/noticeBoardDelete"
		      name="boardDeleteFrm"
		      method="post">
			<input type="hidden" name="noticeNo"
				   value="<%=b.getNoticeNo()%>" />
			      
		</form>
		<script>
		function updateBoard(){
			location.href = "<%=request.getContextPath()%>/board/notice/noticeBoardUpdateForm?noticeNo=<%=b.getNoticeNo()%>"
		}
		function deleteBoard(){
			if(!confirm("정말 삭제 하시겠습니까?")){
				return;
			}
			$("[name=boardDeleteFrm]").submit();
		}
		</script>
			
		<%} %>
	</table>
	
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>



