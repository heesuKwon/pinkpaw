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
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/slick.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/slick-theme.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/header.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/slick.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/view.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/write.css" />  
<link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<script>

 function goFreeViewList(){
				location.href = "<%=request.getContextPath()%>/board/notice/noticeBoardList";
		 }

</script>

<div id="img-div">
	<img id="header-img" src="<%=request.getContextPath() %>/images/1.jpg"  alt="헤더 - 후기게시판 사진" />
	<div id="blackbg"></div>
	<span class="header-title" >공지사항 상세보기</span>
</div>

<section class="board-container" >
			<div style='height:50px; padding:5px;'>
				<input type="button" value="수정"  
						class="btn btn-pink"
						id="modify"
				onclick="updateBoard();" />
				<input type="button" value="삭제"  
						class="btn btn-gray"
						id="modify"
				onclick="deleteBoard();" />
			</div>
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
		<%} %>	
	
<table class="tg" style="table-layout: fixed;   width: 800px;">
		<colgroup>
			<col style="width: 35px">
			<col style="width: 100px">
			<col style="width: 35px">
			<col style="width: 100px">
		</colgroup>
		<tr>
			<th class="tg-th">작성자</th>
			<th class="tg" colspan="3"><%=b.getNoticeWriter()%></th>
		</tr>
		<tr>
			<td class="tg-th">제목</td>
			<td class="tg" colspan="3"><%=b.getNoticeTitle() %></td>
		</tr>
		<tr>
			<td class="tg-th">글번호</td>
			<td class="tg-ml2k"><%=b.getNoticeNo() %></td>
			<td class="tg-th">조회수</td>
			<td class="tg-yc5w"><%=b.getNoticeCount() %></td>
		</tr>
		<tr>
			<td class="tg-th">게시일</td>
			<td class="tg-yc5w"><%=b.getNoticeEnrollDate() %></td>
		</tr>
	
		<tr>
			<td class="tg-th">내용</td>
			<td class="tg-kw6a" colspan="3"><%=b.getNoticeContent() %></td>
		</tr>
	</table>
	
	<div style='padding:10px;'>
	
	<input type="button" value="목록으로" id="menu"  class="btn btn-gray"
				onclick="goFreeViewList();"  />
	</div>
	
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>



