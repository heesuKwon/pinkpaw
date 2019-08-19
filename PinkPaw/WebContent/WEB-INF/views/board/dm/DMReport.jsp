<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.pinkpaw.board.dmboard.model.vo.DM" %>
<%@page import="com.pinkpaw.member.model.vo.Member"%>

    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css" />
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
<link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/write.css" />  
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/view.css">
<script src="<%= request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<meta charset="UTF-8">
<%
	DM dm = (DM)request.getAttribute("dm");
	Member memberLoggedIn = new Member();
	memberLoggedIn.setMemberId("admin");


%>
<title>Insert title here</title>
</head>
<body>

<div id="reportReview-container" id="reportReview-container" 
	style="padding-top: 165px;
		   text-align: center;
		   padding-left: 91px;">
<form action="<%=request.getContextPath()%>/board/dm/dmReportEnd" method="post">
			<table class="tg">
				<tr>
					<th class="tg-th" style='width:100px' >작성자</th>
					<td>
						<%=memberLoggedIn.getMemberId()%>
					</td>
				</tr>
				<tr>
					<th class="tg-th">신고 내용</th>
					<td>	
						<!-- <textarea name="reportContent" cols="40" rows="5"
						placeholder="내용을 입력해주세요."></textarea> -->
						<select name="dmReportContent">
							<option value="">신고 사유 선택</option>
							<option value="광고글">광고글</option>
							<option value="언어폭력(욕설,비방,명예훼손 등)">언어폭력(욕설,비방,명예훼손 등)</option>
							<option value="부적절한 이미지">부적절한 이미지</option>
							<option value="부적절한 내용">부적절한 내용</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<br /><br /><br />
						<input type="submit" class='btn btn-pink'  value="신고" onclick="return reportValidate();"/>&nbsp;
						<input type="button" class='btn btn-gray' value="취소" onclick="self.close();"/>						
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