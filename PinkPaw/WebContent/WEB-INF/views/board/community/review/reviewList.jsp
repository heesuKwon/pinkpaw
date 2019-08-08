<%@page import="java.util.List"%>
<%@page import="com.pinkpaw.board.reviewboard.model.vo.ReviewBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	List<ReviewBoard> list = (List<ReviewBoard>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<script>
$(()=>{
	//테이블의 열을 클릭시 해당 게시물로 이동
	$("td").click((e)=>{		
		var reviewNo = $(e.target).parent().children("th").text();
		
		location.href = "<%=request.getContextPath()%>/board/review/reviewView?boardNo="+reviewNo; 
	});
});
			
</script>
<section class="board-container">
	<%-- 로그인한 경우 글쓰기 가능 --%>
	<%if(memberLoggedIn!=null){ %>
	<input type="button" value="글쓰기" id="btn-add"
			onclick="goReviewWrite();"/>
	<%-- 함수를 console에 직접 쳐서 이동할 수 있으므로 그것을 방지하기 위해 if문 안에 script사용--%>
	<script>
	function goReviewWrite(){
		location.href = "<%=request.getContextPath()%>/board/review/reviewWrite";
	}
	</script>		
	<%} %>
	<table id="tbl-board" class="table table-hover">
		<tr>
			<th scope="col">번호</th>
			<th scope="col">첨부파일</th>
			<th scope="col">종류</th>
			<th scope="col">제목</th>
			<th scope="col">작성자</th>
			<th scope="col">게시일</th>
		</tr>
		<%if(list==null || list.isEmpty()){ %>
		<tr>
			<td rowspan="6" align="center">게시글이 없습니다.</td>
		</tr>
		<%}
		else{
			for(ReviewBoard b: list){%>		
		<tr>
			<th scope="row"><%=b.getReviewNo() %></th>
			<td>
			<!-- 첨부파일이 있는 경우, 
			file.png이미지가 해당 td에 보이도록 하세요 -->
				<%if(b.getReviewOriginalImg()!=null){%>
					<%-- <img src="<%=request.getContextPath()%>/images/file.png" alt="첨부파일" width="3px"/> --%>
				<%}%>
			</td>
			<td><%=b.getReviewKind() %></td>
			<td><%-- <a href="<%=request.getContextPath()%>/board/ReviewboardView?boardNo=<%=b.getReviewNo()%>"> --%><%=b.getReviewTitle() %><!-- </a> --></td>
			<td><%=b.getReviewWriter() %></td>
			<td><%=b.getReviewEnrollDate() %></td>
			
		</tr>
		<%} 
		}%>
	</table>
	<div id="pageBar">
		<%=pageBar %>
	</div>
</section>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>