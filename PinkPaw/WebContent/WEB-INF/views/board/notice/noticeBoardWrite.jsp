<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/write.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css" />
<link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap" rel="stylesheet">

<div id="img-div">
	<img id="header-img" src="<%=request.getContextPath() %>/images/1.jpg" alt="헤더 - 후기게시판 사진" />
	<img id="header-img" src="<%=request.getContextPath() %>/images/board/6.jpg" alt="헤더 - 후기게시판 사진" />
	<div id="blackbg"></div>
	<span class="header-title">공지사항 작성</span>
</div>

<section class="board-container">
	<form action="<%=request.getContextPath() %>/board/notice/noticeBoardFormEnd"
	      method="post"
	      >
		<table id="tbl-write" class="table">
			<tr>
				<th class="text-left">제목<i class="ico-star">*</i></th>
				<td><input type="text" name="noticeTitle" class="form-control title" required placeholder="한글 20자까지 작성가능"/></td>
			</tr>		
			<tr>
				<th class="text-left">작성자<i class="ico-star">*</i></th>
				<td><input type="text" 
						   name="noticeWriter"
						   class="form-control writer"
						   value="<%=memberLoggedIn.getMemberId()%>"
						   required readonly/></td>
			</tr>		
			<tr>
				<th class="text-left">내용<i class="ico-star">*</i></th>
			<td>
					<textarea name="noticeContent" 
							  cols="40" rows="5" class="form-control" required></textarea>
				</td>
			</tr>		
			<tr>
				<td colspan="2">
					<input type="submit" 
						   value="등록" 
						   class="btn btn-pink"
						   onclick="return boardValidate();"/>
					<input type="button" value="취소" class="btn btn-gray"
						onclick="goNoticeList();"/>
				</td>
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
function goNoticeList(){
	location.href = "<%=request.getContextPath()%>/board/notice/noticeBoardList";
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>




