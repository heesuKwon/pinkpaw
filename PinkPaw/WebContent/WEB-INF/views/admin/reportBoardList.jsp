<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.pinkpaw.admin.model.vo.*, java.util.*" %>
<%
	List<ReportBoard> list = (List<ReportBoard>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
	String[] arr = new String[list.size()];
	
	
	
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
			for(int i = 0; i<list.size(); i++){ %>
		<tr>
			<td><%=list.get(i).getReportTableName() %></td>
			<td><%=list.get(i).getReportNo() %></td>
			<td>
				<%if(list.get(i).getReportTableName().equals("리뷰게시판")){ %>
				
				<a href="<%=request.getContextPath()%>/board/review/reviewView?reviewNo=<%=list.get(i).getReportNo()%>">
					<%=list.get(i).getReportTitle() %>
				</a>
				<%} %>
				<%if(list.get(i).getReportTableName().equals("자유게시판")){ %>
				
				<a href="<%=request.getContextPath()%>/board/community/free/freeView?freeNo=<%=list.get(i).getReportNo()%>">
					<%=list.get(i).getReportTitle() %>
				</a>
				<%} %>
				
				<%if(list.get(i).getReportTableName().equals("봉사게시판")){ %>
				
				<a href="<%=request.getContextPath()%>/board/volunteer/volunteerView?volunteerNo=<%=list.get(i).getReportNo()%>">
					<%=list.get(i).getReportTitle() %>
				</a>
				<%} %>
				
				<%if(list.get(i).getReportTableName().equals("분양게시판")){ %>
				
				<a href="<%=request.getContextPath()%>/board/parcelout/parceloutView?parceloutNo=<%=list.get(i).getReportNo()%>">
					<%=list.get(i).getReportTitle() %>
				</a>
				<%} %>
				<%if(list.get(i).getReportTableName().equals("실종게시판")){ %>
				
				<a href="<%=request.getContextPath()%>/board/parcelout/missingView?missingNo=<%=list.get(i).getReportNo()%>">
					<%=list.get(i).getReportTitle() %>
				</a>
				<%} %>
				
				
			</td>
			<td><%= list.get(i).getReportReason() %></td>
		</tr>
		<% } }%>
	</table>

	<div id='pageBar'>
		<%=pageBar %>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>