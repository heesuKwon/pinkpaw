<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" 
	  href="<%=request.getContextPath()%>/css/board.css" />
	  <div id="img">
	<img id="notice_header" src="<%=request.getContextPath() %>/images/1.jpg" alt="헤더 - 공지사항 사진" />
</div>
<style>
	img#notice_header{
		width: 1024px;
		height: 300px;
	}
	#img{
		text-align: center;
	}
	.noticeBoardFrm{
	
	margin-top: 70px;
	}
	td{
	padding: 15px;
	}
	th{
	text-align: center;
	}
</style>
	  
 <div class="noticeBoardFrm">
<section id="board-container">
	<form action="<%=request.getContextPath() %>/board/notice/noticeBoardFormEnd"
	      method="post"
	      >
		<table id="tbl-board-view" class="table table-gray table-hover">
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
							  cols="40" rows="10" required></textarea>
				</td>
			</tr>		
			<tr>
				<th colspan="2" style="">
					<input type="submit" 
						   value="등록" 
						   class="btn btn-secondary"
						   style="background-color: #c54b54;"
						   onclick="return boardValidate();"/>
				</th>
			</tr>		
		
		</table>
		
	</form>

</section>
</div>
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




