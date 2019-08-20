<%@ page import="com.pinkpaw.board.dmboard.model.vo.DM" %>
<%@page import="com.pinkpaw.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="<%= request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<%
	DM dm = (DM)request.getAttribute("dm");
	Member memberLoggedIn
	= (Member)session.getAttribute("memberLoggedIn");


%><link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/board.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/write.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/header.css" />
<link
	href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap"
	rel="stylesheet">
<title>신고하기</title>

<div class="report-container" style="padding-left: 10px;">
<form action="<%=request.getContextPath()%>/board/dm/dmReportEnd" method="post">
			<table id="tbl-write" class="table">
				<tr>
					<th class="text-left">작성자<i class="ico-star"></i></th>
					<td>
					<input type="text" 
							name="reportWriter" 
							class="form-control writer" 
							value="<%=memberLoggedIn.getMemberId()%>" readonly required>
					</td>
				</tr>
				<tr>
					<th class="text-left">신고내용<i class="ico-star"></i></th>
					<td>	
						<select name="dmReportContent" id="dmReportContent" onchange="change()">
							<option value="">신고 사유 선택</option>
							<option value="광고글">광고글</option>
							<option value="언어폭력(욕설,비방,명예훼손 등)">언어폭력(욕설,비방,명예훼손 등)</option>
							<option value="부적절한 이미지">부적절한 이미지</option>
							<option value="부적절한 내용">부적절한 내용</option>
							<option value="기타">기타(직접 입력)</option>
						</select>
						<div id="dmOtherReason">
						<br />
							<textarea name="dmOtherReason" class="form-control"  cols="25" rows="5" placeholder="내용을 입력해주세요." ></textarea>
						</div>				
						<script>
							$("#dmOtherReason").hide();
							function change() {
								var state = $('#dmReportContent option:selected').val();
								
								if(state == "기타"){
									$("#dmOtherReason").show();									
								}
								else{
									$("#dmOtherReason").hide();							
								}
							}
						</script>
					</td>
				</tr>
				<tr>
				<td colspan="2" style="text-align:center;">
					<input type="submit" 
						   value="신고" 
						   class="btn btn-pink"
						   onclick="return reportValidate();"/>
					<input type="button" 
						   value="취소" 
						   class="btn btn-gray"
						   onclick="self.close();"/>
					
				</td>
				</tr>
			</table>
			<input type="hidden" name="dmNo" value="<%=dm.getDmNo()%>" />
		</form>
	</div>

<script>
function reportValidate() {
	var content = $("[name=reviewReportContent]").val();
	//좌우 공백을 제거하고 길이가 0이면(내용이 비어 있는 경우)
	if(content.trim().length == 0){
		alert("신고 사유를 선택하세요.");
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