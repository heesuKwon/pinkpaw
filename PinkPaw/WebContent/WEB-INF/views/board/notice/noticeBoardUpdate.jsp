<%@page import="com.pinkpaw.board.noticeboard.model.vo.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
NoticeBoard b = (NoticeBoard)request.getAttribute("NoticeBoard");

System.out.println("노티스넘버확인 @@@@@@@"+b.getNoticeNo());
System.out.println("노티스타이틀확인 @@@@@@@"+b.getNoticeTitle());

%>
<link rel="stylesheet" 
	  href="<%=request.getContextPath()%>/css/board.css" />
<section id="board-container">
	<h2>공지사항 수정</h2>
	<form action="<%=request.getContextPath() %>/board/notice/noticeBoardUpdateEnd"
	      method="post"
	     >
		<input type="hidden" name="noticeNo" value="<%=b.getNoticeNo()%>"/>
		<table id="tbl-board-view">
			
			<tr>
				<th>제목</th>
				<td><input type="text" 
						   name="noticeTitle" 
						   value="<%=b.getNoticeTitle() %>"
						   required/></td>
			</tr>		
			<tr>
				<th>작성자</th>
				<td><input type="text" 
						   name="noticeWriter"
						   value="<%=b.getNoticeWriter() %>"
						   required readonly/></td>
			</tr>		
			<tr>
				<th>내용</th>
				<td>
					<textarea name="noticeContent" 
							  cols="40" rows="5" required><%=b.getNoticeContent() %></textarea>
				</td>
			</tr>		
			<tr>
				<th colspan="2">
					<input type="submit" 
						   value="수정" 
						   onclick="return boardValidate();"/>
				</th>
			</tr>		
		
		</table>
	</form>

</section>
<script>

function boardValidate(){
	var content = $("[name=noticeContent]").val();
	if(content.trim().length == 0){
		alert("내용을 입력하세요.");
		return false;
	}
	
	return true;
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>