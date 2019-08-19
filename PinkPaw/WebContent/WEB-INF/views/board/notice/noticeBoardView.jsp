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

<div id="img">
	<img id="notice_header" src="<%=request.getContextPath() %>/images/1.jpg" alt="헤더 - 공지사항 사진" />
</div>
<style>
	img#notice_header{
		width: 1024px;
		height: 300px;
	}
	#img{
		text-align: center;
	}
</style>
<br />
<br />
<br />
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
			
	</table>
	
	
	<div style="margin-top: 50px; margin-left: 40%;">
			
				<input type="button" value="수정"
					   class="btn btn-secondary"
					   style="background-color: #c54b54;"
					   onclick="updateBoard();" />
				<input type="button" value="삭제"
					   class="btn btn-secondary"
					   style="background-color: #c54b54; margin-left: 150px;" 
					   onclick="deleteBoard();" />
			
		</div>
		<%} %>
	
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>



