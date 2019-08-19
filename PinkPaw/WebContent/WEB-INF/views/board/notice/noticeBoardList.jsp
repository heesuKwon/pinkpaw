<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.pinkpaw.board.noticeboard.model.vo.*, java.util.*" %>
<%
	List<NoticeBoard> list = (List<NoticeBoard>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />


<script>

$(document).ready(function(){
	
	$("td").click((e)=>{		
		var notice = $("#noticeNo").text();

		location.href = "<%=request.getContextPath()%>/board/notice/noticeBoardView?noticeNo="+notice;
	});
	
});

</script>


<section id="board-container">
	<h2>공지사항</h2>
	

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

<section class="board-container">	

		<%-- 관리자만 글쓰기가능 --%>
	<% if(memberLoggedIn!=null && "admin".equals(memberLoggedIn.getMemberId())) {%>
	<input type="button" value="글쓰기" id="btn-add"
		   class="btn btn-secondary"
					   style="background-color: #c54b54;"
		   onclick="goBoardForm();" />
	<script>
	function goBoardForm(){
		location.href 
			= "<%=request.getContextPath()%>/board/notice/noticeBoardForm";	
	}
	</script>
	<% } %>
	

	<table class="table table-gray table-hover" id="noticeList">

	<table id="tbl-board" class="table table-hover">

		<tr>
			<th scope="col">No</th>
			<th scope="col">제목</th>
			<th scope="col">작성자</th>
			<th scope="col">등록일</th>
			<th scope="col">조회수</th>
		</tr>
		<%if(list==null || list.isEmpty()){ %>
		<tr>
			<td colspan="6" align="center">게시글이 없습니다.</td>
		</tr>
		<%}
		else{
			for(NoticeBoard b : list){%>
		<tr>
			<th scope="row" id="noticeNo"><%=b.getNoticeNo() %></th>
			<td><%= b.getNoticeTitle() %></td>
			<td><%= b.getNoticeWriter() %></td>
			<td><%= b.getNoticeEnrollDate() %></td>
			<td><%=b.getNoticeCount() %></td>
		</tr>
		<% }} %>
	</table>

	<div id='pageBar'>
		<%=pageBar %>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>