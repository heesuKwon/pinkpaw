<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.pinkpaw.board.dmboard.model.vo.reportDM, java.util.*" %>
<%
	List<reportDM> list = (List<reportDM>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
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

<script>

$(()=>{
	$("td").click((e)=>{		
		var no = $(e.target).parents("tr").children("td").eq(0).text();
		
		alert(no);
		
			location.href = "<%=request.getContextPath()%>/board/dm/reportDMView?dmNo="+no;
				
		});
	});

</script>
<div id="img">
	<img id="reportBoard_header" src="<%=request.getContextPath() %>/images/board/20.jpg" alt="헤더 - 신고쪽지 게시판 사진" />
	<div id="blackbg"></div>
	<span class="header-title">신고 쪽지</span>
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

<section  style="position: relative;
    padding-top: 170px;
    margin: 0 auto;
    text-align: center;
    padding-bottom: 100px;">
	
		
	<table id="tbl-board" class="table table-hover">
		<tr>
			<th>번호</th>
			<th>보낸사람</th>
			<th>받은사람</th>
			<th>제목</th>
			<th>날짜</th>
		</tr>


	<% for(reportDM d : list){ %>
		<tr>
			<td><%=d.getDmNo() %></td>
			<td><%=d.getDmSend() %></td>
			<td><%=d.getDmRecive() %></td>
			<td><%=d.getDmTitle() %></td>
			<td><%= d.getDmDate() %></td>
		</tr>
		<% } %>
	</table>

	<div id='pageBar'>
		<%=pageBar %>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>