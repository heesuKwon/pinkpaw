<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@page import=" com.pinkpaw.board.freeboard.model.vo.FreeBoard"%>
<%@page import="java.util.List"%>
<%--헤더는 나중에 우리 이미지로 수정하기 <%@ include file="/WEB-INF/views/common/header.jsp"%> --%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/board.css" />

<%
	List<FreeBoard> list = (List<FreeBoard>)request.getAttribute("list");
	System.out.println("list@왜 아니ㅉㄲ혀 : "+list);
	String pageBar = (String)request.getAttribute("pageBar");
%>

<script>
	function goFreeWrite(){
		location.href = "<%=request.getContextPath()%>/board/community/free/freeWrite";	
	}
	
	$(()=>{
		$("#freeSearchType").on("change", (e)=>{
			var type = $(e.target).val();
			
			$(".freeSearchFrm").hide();
			$("#freeListSearch-"+type).css("display","inline-block");
		});
		
		//테이블의 열을 클릭시 해당 게시물로 이동
		$("td").click((e)=>{		
			var freeNo = $(e.target).parents("tr").children("th").text();

			location.href = "<%=request.getContextPath()%>/board/community/free/freeView?freeNo="+freeNo; 
		});
		
	});
</script>


<div>
	<img id="free_header" src="<%=request.getContextPath() %>/images/1.jpg" alt="헤더 - 자유게시판 사진" />
</div>
<style>
	img#free_header{
		width: 1024px;
		height: 300px;
	}
</style>


<section class="board-container">
	<%-- 로그인한 경우 글쓰기 가능 --%>
	<%-- <% if(memberLoggedIn != null) {%> --%>
	<input type="button" value="글쓰기" id="btn-add" onclick="goFreeWrite();" />

	<%-- <% } %> --%>

	<table id="tbl-board" class="table table-hover">
		<!-- <thead> -->
			<tr>
				<th scope="col">No</th>
				<th scope="col">사진</th>
				<th scope="col">제목</th>
				<th scope="col">작성자</th>
				<th scope="col">등록일</th>
				<th scope="col">조회수</th>
			</tr>
		<!-- </thead> -->

		<!-- <tbody> -->
			<%if(list==null || list.isEmpty()) { %>
			<tr>
				<td colspan="6" align="center">조회 결과가 없습니다.</td>
			</tr>
			<% }else{
					for(FreeBoard f : list) { %>
			<tr>
			
				<th scope="row"><%=f.getFreeNo() %></th>
				<td>
					<%if(f.getFreeOriginalImg()!=null){
						 String[] renamedImgList = f.getFreeRenamedImg().split("§"); %>
						 <img src="<%=request.getContextPath() %>/upload/board/free/<%=renamedImgList[0] %>" alt="첨부파일" style="width: 80px;"/>
					<%}%>
				</td>
				<td><%-- <a
					href="<%=request.getContextPath()%>/board/community/free/freeView?freeNo=<%=f.getFreeNo()%>"> --%>
						<%=f.getFreeTitle() %><!-- </a> -->
				</td>
				<td><%=f.getFreeWriter() %></td>
				<td><%=f.getFreeEnrolldate() %></td>
				<td><%=f.getFreeCount() %></td>
			</tr>
			<%}} %>
		<!-- </tbody> -->
	</table>

	<div id="pageBar">
		<%=pageBar %>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>

