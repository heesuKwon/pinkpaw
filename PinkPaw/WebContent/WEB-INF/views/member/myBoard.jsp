<%@page import="com.pinkpaw.member.model.service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.pinkpaw.admin.model.vo.*, java.util.*" %>
<%@ page import="com.pinkpaw.member.model.vo.MyBoard" %>
<%@ include file="/WEB-INF/views/common/mainHeader.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />

<%
List<MyBoard> list = (List<MyBoard>)request.getAttribute("list");
String pageBar = (String)request.getAttribute("pageBar");

List<MyBoard> list1 = new MemberService().selectMyBoard(1, 10, memberLoggedIn.getMemberId());
%>

<div id="img">
	<img id="myBoard_header" src="<%=request.getContextPath() %>/images/1.jpg" alt="헤더 - 내가 쓴 글 사진" />
</div>
<style>
	img#myBoard_header{
		width: 1024px;
		height: 300px;
	}
	#img{
		text-align: center;
	}
</style>

<section class="board-container">
	<!--내가 쓴 글 리스트-->
	
	<table id="tbl-board" class="table table-hover">
		<tr>
			<th scope="col">게시판명</th>
			<th scope="col">글 번호</th>
			<th scope="col">제목</th>
			<th scope="col">등록일</th>
		</tr>
		<%if(list==null || list.isEmpty()){ %>
		<tr>
			<td colspan="6" align="center">게시글이 없습니다.</td>
		</tr>
		<%}
		else{
			for(MyBoard m : list1){ %>
		<tr>
			<td><%=m.getMyTable() %></td>
			<td><%=m.getMyNo() %></td>
			<td>
				<a href="<%=request.getContextPath()%>/board/dm/reportDMView?dmNo=<%=m.getMyNo()%>">
					<%=m.getMyTitle() %>
				</a>
			</td>
			<td><%=m.getMyDate() %></td>
		</tr>
		<% } }%>
	</table>

	<div id='pageBar'>
		<%=pageBar %>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>