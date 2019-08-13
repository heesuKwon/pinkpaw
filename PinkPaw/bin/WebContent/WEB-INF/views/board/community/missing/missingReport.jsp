<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.pinkpaw.board.missingboard.model.vo.MissingBoard, java.util.*"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	int missingNo = Integer.parseInt(request.getParameter("missingNo"));
	
	
	%>
<link rel="stylesheet" 
	  href="<%=request.getContextPath()%>/css/board.css" />

<section class="board-container">
<div id="reportMissing-container">
		<form name="reportFrm" action="<%=request.getContextPath()%>/board/missing/reportMissingEnd" method="post" >
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
						<input type="submit"  value="변경" onclick="return reportValidate();"/>&nbsp;
						<input type="button" onclick="self.close();" value="닫기" />						
					</td>
				</tr>
			</table>
			<input type="hidden" name="missingNo" value="<%=missingNo%>" />
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

	
<%@ include file="/WEB-INF/views/common/footer.jsp"%>