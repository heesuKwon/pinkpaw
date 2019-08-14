<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" 
	  href="<%=request.getContextPath()%>/css/board.css" />
<section id="board-container">
	<h2>공지사항 작성</h2>
	<form action="<%=request.getContextPath() %>/board/notice/noticeBoardFormEnd"
	      method="post"
	      >
		<table id="tbl-board-view">
			<tr>
				<th>제목</th>
				<td><input type="text" name="noticeTitle" required/></td>
			</tr>		
			<tr>
				<th>작성자</th>
				<td><input type="text" 
						   name="noticeWriter"
						   value="<%=memberLoggedIn.getMemberId()%>"
						   required readonly/></td>
			</tr>		
			<tr>
				<th>내용</th>
			<td>
					<textarea name="noticeContent" 
							  cols="40" rows="5" required></textarea>
				</td>
			</tr>		
			<tr>
				<th colspan="2">
					<input type="submit" 
						   value="등록" 
						   onclick="return boardValidate();"/>
				</th>
			</tr>		
		
		</table>
	</form>

</section>
<script>
function boardValidate(){
	var content = $("[name=boardContent]").val();
	if(content.trim().length == 0){
		alert("내용을 입력하세요.");
		return false;
	}
	
	return true;
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>




