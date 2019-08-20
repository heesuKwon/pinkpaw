<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.pinkpaw.board.noticeboard.model.vo.*, java.util.*" %>
<%
	List<NoticeBoard> list = (List<NoticeBoard>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/board.css" />
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap" rel="stylesheet">



<script>

$(document).ready(function(){
	
	$("td").click((e)=>{		
		var notice = $(e.target).parents("tr").children("th").eq(0).text();
		
		location.href = "<%=request.getContextPath()%>/board/notice/noticeBoardView?noticeNo="+notice;
	});
	
});

</script>


<div id="img-div">

	<img id="header-img" src="<%=request.getContextPath() %>/images/board/6.jpg" alt="헤더 - 후기게시판 사진" />
	<div id="blackbg"></div>
	<span class="header-title">공지사항</span>
</div>

<section class="board-container">	

		<%-- 관리자만 글쓰기가능 --%>
	<% if(memberLoggedIn!=null && "admin".equals(memberLoggedIn.getMemberId())) {%>
	<input type="button" value="글쓰기" id="btn-add"
		   class="btn btn-pink"
		   onclick="goBoardForm();" />
	<script>
	function goBoardForm(){
		location.href 
			= "<%=request.getContextPath()%>/board/notice/noticeBoardForm";	
	}
	</script>
	<% } %>
	

	<table class="table table-gray table-hover" id="noticeList">

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