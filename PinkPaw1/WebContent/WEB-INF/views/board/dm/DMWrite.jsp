<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.pinkpaw.member.model.vo.Member" %>
<!DOCTYPE html>
<html>
<%
Member memberLoggedIn = new Member();
memberLoggedIn.setMemberId("admin");

String memberId = (String)request.getAttribute("memberId");

%>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="reportMissing-container">
		<form name="dmWriteFrm"
			action="<%=request.getContextPath()%>/dmWriteEnd"
			method="post">
			<table>
				<tr>
					<th>보낼 사람</th>
					<td>
					
					<input type="text" name="dmSender" id="dmSender"
						required>
					<input type="hidden" name="dmWriter" id="dmWriter" value="<%=memberLoggedIn.getMemberId() %>" />
					</td>
				</tr>
				<tr>
					<th>쪽지 제목 </th>
					<td><input type="text" name="dmTitle" id="dmTitle"
						value="" readonly required>
					</td>
				</tr>
				<tr>
					<th>쪽지 내용</th>
					<td><textarea name="dmContent" cols="40" rows="5"
							placeholder="내용을 입력해주세요."></textarea></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="보내기"
						onclick="return reportValidate();" />&nbsp; <input type="button"
						onclick="self.close();" value="닫기" /></td>
				</tr>
			</table>
		</form>
	</div>
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