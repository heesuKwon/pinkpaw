<%@page import="com.pinkpaw.member.model.service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.pinkpaw.admin.model.vo.*, java.util.*" %>
<%@ page import="com.pinkpaw.member.model.vo.MyComment" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/w3.css">
<link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/header.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/slick.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/view.css">
	<link href="<%=request.getContextPath()%>/css/write.css" rel="stylesheet" />

<%
	

String pageBar = (String)request.getAttribute("pageBar");

List<MyComment> list1 = new MemberService().selectMyComment(1, 10, memberLoggedIn.getMemberId());

	
%>
<div id="img-div">

	<img id="header-img" src="<%=request.getContextPath() %>/images/board/6.jpg" alt="헤더 - 후기게시판 사진" />
	<div id="blackbg"></div>
	<span class="header-title">작성댓글보기</span>
</div>

<style>
	img#reportBoard_header{
		width: 1024px;
		height: 300px;
	}
	#img{
		text-align: center;
	}
</style>

<script>

$(()=>{
	$("td").click((e)=>{		
		var no = $(e.target).parents("tr").children("td").eq(1).text();
		
		var table = $(e.target).parents("tr").children("td").eq(0).text();
		

		
		
		if(table == "리뷰게시판"){
			
		location.href = "<%=request.getContextPath()%>/board/review/reviewView?reviewNo="+no;
		}
		if(table == "자유게시판"){
			
			location.href = "<%=request.getContextPath()%>/board/community/free/freeView?freeNo="+no;
		}
		if(table == "봉사게시판"){
			
			location.href = "<%=request.getContextPath()%>/board/volunteer/volunteerView?volunteerNo="+no;
		}
		if(table == "분양게시판"){
			
			location.href = "<%=request.getContextPath()%>/board/parcelout/parceloutView?parceloutNo="+no;
		}
		if(table == "실종게시판"){
			
			location.href = "<%=request.getContextPath()%>/board/parcelout/missingView?missingNo="+no;
		}
		
		});
	});

</script>


<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />

<section  style="position: relative;
    padding-top: 387px;
    margin: 0 auto;
    text-align: center;
    padding-bottom: 100px;">
	
		
	<table id="tbl-board"  class="table table-hover">
		<tr>
			<th scope="col">테이블명</th>
			<th scope="col">댓글번호</th>
			<th scope="col">댓글내용</th>
			<th scope="col">작성일</th>
		</tr>
		<%if(list1==null || list1.isEmpty()){ %>
		<tr>
			<td colspan="6" align="center">게시글이 없습니다.</td>
		</tr>
		<%} 
		else{
		%>
		
				<% for(MyComment m : list1){ %>
		<tr id="temp">
			<td><%=m.getMyCoTable() %></td>
			<td><%=m.getMyCoREF() %></td>
			<td><%=m.getMyCoContent() %></td>
			<td><%=m.getMyCoDate() %></td>
		</tr>
		<% } %>
<%} %>
	</table>
	<div id='pageBar' style="">
		<%=pageBar %>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>