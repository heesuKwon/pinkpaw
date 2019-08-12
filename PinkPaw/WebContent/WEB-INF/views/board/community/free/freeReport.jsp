<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.pinkpaw.board.freeboard.model.vo.FreeBoard"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />

<%
	int freeNo = Integer.parseInt(request.getParameter("freeNo"));
%>

<section class="board-container">
	<form action="<%=request.getContextPath() %>/board/community/free/freeBoardReportEnd" name="freeReportFrm" method="post">
		<table id="tbl-board-view">
		
			<tr>
				<th>작성자</th>
				<td>
					<input type="text" name="freeReportWriter" value="<%=memberLoggedIn.getMemberId()%>" readonly required>
				</td>
			</tr>
			
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="freeReportTitle" required/>
				</td>
			</tr>
			
			<tr>
				<th>신고 사유</th>
				<td>
					<textarea name="freeReportContent" cols="40" rows="10" required></textarea>
				</td>
			</tr>
		
			<tr>
				<th colspan="2">
					<input type="submit" value="보내기" onclick="return freeBoardReportValidate();" />
					<input type="button" value="취소" onclick="self.close();" />
				</th>
			</tr>		
		</table>
			<input type="hidden" name="freeNo" value="<%=freeNo%>"/>
		
	</form>
</section>

<script>
	function freeBoardReportValidate(){
		var content = $("[name=freeReportContent]").val();
		if(content.trim().length == 0){
			alert("내용을 입력하세요.");
			return false;
		}
		
		return true;
	}
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>