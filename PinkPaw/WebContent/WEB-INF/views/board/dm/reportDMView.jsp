<%@page import="com.pinkpaw.board.dmboard.model.vo.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ page import="com.pinkpaw.board.dmboard.model.vo.*, java.util.*" %>
<%
	DM d = (DM)request.getAttribute("DM");

%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
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

<div id="img">
	<img id="reportBoard_header" src="<%=request.getContextPath() %>/images/board/20.jpg" alt="헤더 - 신고쪽지 게시판 사진" />
	<div id="blackbg"></div>
	<span class="header-title">신고 쪽지</span>
</div>
<style>
		
	.con{
	
	margin: 15px;
	padding: 35px;
	
	}
	
	img#reportBoard_header{
		width: 1024px;
		height: 300px;
	}
	#img{
		text-align: center;
	}
	
</style>
	  
 <div class="con"> 
<section id="board-container">
	<table id="tbl-board-view" class="table table-hover">
			<tr>
				<th>쪽지번호</th>
				<td><%=d.getDmNo() %></td>
			</tr>
			<tr>
				<th>제 목</th>
				<td><%=d.getDmTitle() %></td>
			</tr>
			<tr>
				<th>보낸사람</th>
				<td><a href="<%=request.getContextPath()%>/member/memberView?memberId=<%=d.getDmSend() %>"><%=d.getDmSend() %></a>
				</td>
			</tr>
			<tr>
				<th>받은사람</th>
				<td><%=d.getDmRecive() %></td>
			</tr>
		<tr>
			<th>내용</th>
			<td><%=d.getDmContent()%></td>
		</tr>
		<tr>
			<th>신고이유</th>
			<td><%=d.getDmReportReason()%></td>
		</tr>
		
		<tr>
			<th colspan="2">
				<input type="button" value="삭제" 
					   class="btn btn-pink" 
					   onclick="deleteBoard();" />
			</th>
		</tr>
		<form action="<%=request.getContextPath()%>/board/dm/reportDMDelete"
		      name="dmDeleteFrm"
		      method="post">
			<input type="hidden" name="DmNo"
				   value="<%=d.getDmNo()%>" />
			      
		</form>
		</div>
		<script>
		
		function deleteBoard(){
			if(!confirm("정말 삭제 하시겠습니까?")){
				return;
			}
			$("[name=dmDeleteFrm]").submit();
		}
		</script>
			
		
	</table>
	
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>



