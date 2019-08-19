<%@page import="com.pinkpaw.board.noticeboard.model.vo.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
NoticeBoard b = (NoticeBoard)request.getAttribute("NoticeBoard");

System.out.println("노티스넘버확인 @@@@@@@"+b.getNoticeNo());
System.out.println("노티스타이틀확인 @@@@@@@"+b.getNoticeTitle());

%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/write.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css" />
<link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap" rel="stylesheet">

<div id="img-div">
	<img id="header-img" src="<%=request.getContextPath() %>/images/board/6.jpg" alt="헤더 - 공지사항 사진" />
	<div id="blackbg"></div>
	<span class="header-title">공지사항 수정</span>
</div>
<section class="board-container">
	<form action="<%=request.getContextPath() %>/board/notice/noticeBoardUpdateEnd"
	      method="post"
	     >
			<table id="tbl-write" class="table">
			<tr>
				<th class="text-left">제목<i class="ico-star">*</i></th>
				<td><input type="text" name="noticeTitle" class="form-control title" required value="<%=b.getNoticeTitle() %>"/></td>
			</tr>	
			<tr>
				<th class="text-left">작성자<i class="ico-star">*</i></th>
				<td><input type="text" 
						   name="noticeWriter"
						   class="form-control writer"
						   value="<%=b.getNoticeWriter() %>"
						   required readonly/></td>
			</tr>
			<tr>
				<th class="text-left">내용<i class="ico-star">*</i></th>
			<td>
					<textarea name="noticeContent" 
							  cols="40" rows="5" class="form-control" required><%=b.getNoticeContent() %></textarea>
				</td>
			</tr>		
			<tr>
				<td colspan="2">
				<input type="hidden" name="noticeNo" value="<%=b.getNoticeNo()%>"/>
				<input type="submit" 
						value="등록" 
						class="btn btn-pink"
						onclick="return boardValidate();"/>
				<input type="button" value="취소" class="btn btn-gray"
						onclick="goNoticeView();"/>
				</td>
			</tr>		
		</table>
	</form>

</section>
<!-- </div> -->
<script>
function boardValidate(){
	var content = $("[name=noticeContent]").val();
	if(content.trim().length == 0){
		alert("내용을 입력하세요.");
		return false;
	}
	
	return true;
}
function goNoticeView() {
	location.href = "<%=request.getContextPath()%>/board/notice/noticeBoardView?noticeNo="+<%=b.getNoticeNo()%>;
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>