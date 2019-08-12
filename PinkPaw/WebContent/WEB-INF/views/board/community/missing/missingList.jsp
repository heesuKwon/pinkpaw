<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.pinkpaw.board.missingboard.model.vo.MissingBoard, java.util.*"%>
<%
	
	List<MissingBoard> list = (List<MissingBoard>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
	/* String str = "서울특별시 강서구 어딘가";
	
	System.out.println(str.substring(0, str.indexOf(" ", 6)));
 */
	
	
	%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" 
	  href="<%=request.getContextPath()%>/css/board.css" />

<section class="board-container">


	<h2>실종게시판</h2>
	<%-- 로그인한 경우 글쓰기 가능 --%>
	<%if(memberLoggedIn!=null){ %>
	<input type="button" value="글쓰기" id="btn-add"
			onclick="goReviewWrite();"/>
	<%-- 함수를 console에 직접 쳐서 이동할 수 있으므로 그것을 방지하기 위해 if문 안에 script사용--%>
	<script>
	function goReviewWrite(){
		location.href = "<%=request.getContextPath()%>/board/missingWrite";
	}
	</script>		
	<%} %>
			<% for(MissingBoard b : list){ %>
	<a href="<%=request.getContextPath()%>/board/missingView?missingNo=<%=b.getMissingNo() %>">
	<div class="card" id="layout">
	<%if(b.getMissingOriginalImg()!=null) {
				String[] renamedImgList = b.getMissingRenamedImg().split("§");
				%>
				<img src="<%=request.getContextPath()%>/upload/board/missing/<%=renamedImgList[0]%>" class="card-img-top" alt="logo"  style='width:200px; height:200px;' />					
			<% 
			}%>
		<div class="card-body">

			<h2>"잃어버린 <%=b.getMissingKind() %> 를 찾습니다"</h2>
			<p class="card-text">
				지역: <%=b.getMissingHpPlace().substring(0, b.getMissingHpPlace().indexOf(" ", 6)) %>
			</p>
			<p class="card-text">
				사례금:<%=b.getMissingMoney() ==-1?"사례금협의":b.getMissingMoney() == 0?"사례금없음":b.getMissingMoney()+"만원" %>
			</p>

		</div>
	</div>
	</a>
				
	<%} %>
	<style>
#layout {
	display: inline-block;
	margin: 1em;
	width: 17rem;
	height: 18rem;
}
</style>



	<div id='pageBar'><%=pageBar %></div>





</section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>