<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.pinkpaw.admin.model.vo.*, java.util.*" %>
<%
	List<ReportBoard> list = (List<ReportBoard>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
	
	
	
%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />

<div id="img">
	<img id="reportBoard_header" src="<%=request.getContextPath() %>/images/1.jpg" alt="헤더 - 신고쪽지 게시판 사진" />
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

<section class="board-container">
	<table id="tbl-board" class="table table-hover">
		<tr>
			<th scope="col">게시판명</th>
			<th scope="col">글 번호</th>
			<th scope="col">제목</th>
			<th scope="col">신고사유</th>		
		</tr>
		<%if(list==null || list.isEmpty()){ %>
		<tr>
			<td colspan="6" align="center">게시글이 없습니다.</td>
		</tr>
		<%}
		else{
			for(ReportBoard r : list){ %>
		<tr>
			<td><%=r.getReportTableName() %></td>
			<td><%=r.getReportNo() %></td>
			<td><%=r.getReportTitle() %></td>
			<td><%=r.getReportReason() %></td>
		</tr>
		<% } }%>
	</table>

	<div id='pageBar'>
		<%=pageBar %>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>