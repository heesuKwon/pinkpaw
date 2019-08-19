<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.pinkpaw.board.dmboard.model.vo.*, java.util.*" %>
<%
	List<DM> list = (List<DM>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />

<script>

$(()=>{
	$("td").click((e)=>{		
		var no = $(e.target).parents("tr").children("td").eq(1).val();
		
			alert(no);
					
			location.href = "<%=request.getContextPath()%>/board/dm/reportDMView?dmNo="+no;
				
		});
	});

</script>

<section class="board-container">
<!--신고쪽지  -->
	<table id="tbl-board" class="table table-hover">
		<tr>
			<th scope="col">보낸사람</th>
			<th scope="col">받은사람</th>
			<th scope="col">제목</th>
			<th scope="col">날짜</th>
		</tr>
		<%if(list==null || list.isEmpty()){ %>
		<tr>
			<td colspan="6" align="center">게시글이 없습니다.</td>
		</tr>
		<%}
		else{
			for(DM d : list){ %>
		<tr>
			<td><input type="hidden" value="<%=d.getDmNo()%>" /></td>
			<td><%=d.getDmSend() %></td>
			<td><%=d.getDmRecive() %></td>
			<td><%=d.getDmTitle() %></td>
			<td><%= d.getDmDate() %></td>
		</tr>
		<% } }%>
	</table>

	<div id='pageBar'>
		<%=pageBar %>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>