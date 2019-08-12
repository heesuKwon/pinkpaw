<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="com.pinkpaw.board.common.model.vo.BoardComment"%>
<%@page import="com.pinkpaw.board.freeboard.model.vo.FreeBoard"%>
<%@page import="java.util.List"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%--헤더는 나중에 우리 이미지로 수정하기 <%@ include file="/WEB-INF/views/common/header.jsp"%> --%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/board.css" />

<%
	FreeBoard f = (FreeBoard)request.getAttribute("freeBoard");
    
	String pageBar = (String)request.getAttribute("pageBar");
	
%>

<section class="board-container">
	<!-- <h2>게시판 상세보기</h2> -->
	<table id="tbl-board-view">
		<tr>
			<th>No</th>
			<td><%=f.getFreeNo() %></td>
		</tr>
		
		<tr>
			<th>제목</th>
			<td><%=f.getFreeTitle() %></td>
		</tr>

		<tr>
			<th>신고자</th>
			<td><%=f.getFreeWriter() %></td>
		</tr>

		<!--조회수 대신에 신고된 횟수를 받아오도록 만들어보기  -->
		<%-- <tr>
			<th>조회수</th>
			<td><%=f.getFreeCount() %></td>
		</tr> --%>

		<!--신고 날짜로..?  -->
		<tr>
			<th>게시일</th>
			<td><%=f.getFreeEnrolldate() %></td>
		</tr>

		<tr>
			<th>내용</th>
			<td><%=f.getFreeContent() %></td>
		</tr>


		<!-- 글작성자/관리자인 경우에만 수정/삭제버튼이 보이도록함. -->
		<%-- <% if(memberLoggedIn!=null && 
			(b.getBoardWriter().equals(memberLoggedIn.getMemberId())
			|| "admin".equals(memberLoggedIn.getMemberId())) ){ %>	 --%>
		<!-- <tr>
			<th colspan="2">
			<input type="button" value="수정" onclick="updateFreeBoard();" />
			<input type="button" value="삭제" onclick="deleteFreeBoard();" />
			<input type="button" value="목록으로" onclick="goFreeViewList();" />
			<input type="button" value="신고하기" onclick="goFreeViewReportOpen();" />
			</th>
		</tr> -->

		<!--삭제 부분  -->
	<%-- 	<form
			action="<%=request.getContextPath()%>/board/community/free/FreeBoardDelete"
			name="freeBoardDeleteFrm" method="post">
			<input type="hidden" name="freeNo" value="<%=f.getFreeNo()%>" />
			<input type="hidden" name="renamedFileName" 
					   value="<%=f.getRenamedFileName()!=null?b.getRenamedFileName():""%>"/>   
		</form> --%>
		

		<!--게시글 수정/삭제 부분  -->
		<%-- <script>
				function updateFreeBoard(){
					location.href = "<%=request.getContextPath()%>/board/community/free/FreeUpdateForm?freeNo=<%=f.getFreeNo()%>"
				}
				
				function deleteFreeBoard(){
					if(!confirm("정말 삭제 하시겠습니까?")){
						return;
					}
					$("[name=freeBoardDeleteFrm]").submit();
				}
				
				function goFreeViewList(){
					location.href = "<%=request.getContextPath()%>/board/community/free/freeList"
				}
				
				function goFreeViewReportOpen(){
				window.open("<%=request.getContextPath()%>/board/community/free/freeBoardReport","new","top=200, left=450, width=450, height=300");
				 
					var url = "<%=request.getContextPath()%>/board/community/free/freeBoardReport";
					var target = "new";
					var option = "top=200, left=450, width=450, height=300";
					
					window.open(url,target,option);
				}
			</script> --%>

		<%-- <%} %> --%>
	</table>

</section>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>