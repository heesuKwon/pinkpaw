<%@page import="com.pinkpaw.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int parceloutNo = Integer.parseInt(request.getParameter("parceloutNo"));
	/* Member memberLoggedIn
	= (Member)session.getAttribute("memberLoggedIn"); */
	Member memberLoggedIn = new Member();
	memberLoggedIn.setMemberId("admin");
	System.out.println("memberLoggedIn@index.jsp="+memberLoggedIn);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고하기</title>
<link rel="stylesheet" 
	  href="<%=request.getContextPath()%>/css/board.css" />
<script src="<%= request.getContextPath()%>/js/jquery-3.4.1.js"></script>
</head>
<body>

<section class="board-container">
<div id="reportParcelout-container">
		<form name="reportFrm" action="<%=request.getContextPath()%>/board/community/free/freeBoardReportEnd" method="post" >
			<table>
				<tr>
					<th>신고 사유</th>
					<td><input type="text" name="reportTitle" id="reportTitle" required></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>
						<input type="text" name="reportWriter" id="reportWriter" value="<%=memberLoggedIn.getMemberId()%>" readonly required>
					</td>
				</tr>
				<tr>
					<th>신고 내용</th>
					<td>	
						<textarea name="reportContent" cols="40" rows="5"
						placeholder="내용을 입력해주세요."></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit"  value="신고보내기" onclick="return reportValidate();"/>&nbsp;
						<input type="button" value="취소" onclick="self.close();"/>						
					</td>
				</tr>
			</table>
			<input type="hidden" name="parceloutNo" value="<%=parceloutNo%>" />
		</form>
	</div>
</section>
<script>
function reportValidate() {
	var content = $("[name=reportContent]").val();
	//좌우 공백을 제거하고 길이가 0이면(내용이 비어 있는 경우)
	if(content.trim().length == 0){
		alert("내용을 입력하세요.");
		return false;
	}

	
	return true;
}
$(()=>{
	
	var close = <%=request.getParameter("close")%>;
	if(close==true){
		self.close();
	}
})();


</script>
</body>
</html>