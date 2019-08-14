<%@page import="com.pinkpaw.board.dmboard.model.vo.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ page import="com.pinkpaw.board.dmboard.model.vo.*, java.util.*" %>
<%
	DM d = (DM)request.getAttribute("DM");

%>
<link rel="stylesheet" 
	  href="<%=request.getContextPath()%>/css/board.css" />
  
<section id="board-container">
	<h2>신고쪽지 상세보기</h2>
	<table id="tbl-board-view">
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
				<td><%=d.getDmSend() %></td>
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
					   onclick="deleteBoard();" />
			</th>
		</tr>
		<form action="<%=request.getContextPath()%>/board/dm/reportDMDelete"
		      name="dmDeleteFrm"
		      method="post">
			<input type="hidden" name="DmNo"
				   value="<%=d.getDmNo()%>" />
			      
		</form>
		
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



