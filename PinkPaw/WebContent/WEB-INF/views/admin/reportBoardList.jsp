<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.pinkpaw.admin.model.vo.*, java.util.*" %>
<%
	List<ReportBoard> list = (List<ReportBoard>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
	String[] arr = new String[list.size()];
	for(int i = 0; i<list.size(); i++ ){
		switch(list.get(i).getReportTableName()){
			case "review" : arr[i] = "후기게시판"; break;
			case "missing" : arr[i] = "실종게시판"; break;
			case "free" : arr[i] = "자유게시판"; break;
			case "parcelout" : arr[i] = "분양게시판"; break;
		}
	}
	
	
%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />

<section id="board-container">
	<h2>신고게시판</h2>
	
		
	<table id="tbl-board">
		<tr>
			<th>테이블명</th>
			<th>번호</th>
			<th>제목</th>
			<th>신고사유</th>
		</tr>

				<% for(int i = 0; i<list.size(); i++){ %>
		<tr>
			<td><%=list.get(i).getReportTableName() %></td>
			<td><%=list.get(i).getReportNo() %></td>
			<td>
				<a href="<%=request.getContextPath()%>/board/dm/reportDMView?dmNo=<%=list.get(i).getReportNo()%>">
					<%=list.get(i).getReportTitle() %>
				</a>
			</td>
			<td><%= list.get(i).getReportReason() %></td>
		</tr>
		<% } %>
	</table>

	<div id='pageBar'>
		<%=pageBar %>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>