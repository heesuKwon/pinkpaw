<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.pinkpaw.member.model.vo.Member"%>
<script src="<%= request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" /> --%>

<%
	int freeNo = Integer.parseInt(request.getParameter("freeNo"));
	Member memberLoggedIn
	= (Member)session.getAttribute("memberLoggedIn");
	System.out.println("memberLoggedIn@index.jsp="+memberLoggedIn);
%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/board.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/write.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/header.css" />
<link
	href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap"
	rel="stylesheet">
<title>신고하기</title>

<div class="report-container" style="padding-left: 15px;">
		<form name="reportFrm" action="<%=request.getContextPath()%>/board/community/free/freeBoardReportEnd" method="post" >
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
						<select name="freeReportContent" id="freeReportContent" onchange="change()">
							<option value="">신고 사유 선택</option>
							<option value="광고글">광고글</option>
							<option value="언어폭력(욕설,비방,명예훼손 등)">언어폭력(욕설,비방,명예훼손 등)</option>
							<option value="부적절한 이미지">부적절한 이미지</option>
							<option value="부적절한 내용">부적절한 내용</option>
							<option value="기타">기타(직접 입력)</option>
						</select>
						<div id="freeOtherReason">
							<br />
							<textarea name="freeOtherReason" cols="25" rows="5" class="form-control"  placeholder="내용을 입력해주세요." ></textarea>
						</div>				
						<script>
							$("#freeOtherReason").hide();
							function change() {
								var state = $('#freeReportContent option:selected').val();
								
								if(state == "기타"){
									$("#freeOtherReason").show();									
								}
								else{
									$("#freeOtherReason").hide();							
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
			<input type="hidden" name="freeNo" value="<%=freeNo%>" />
		</form>
	</div>

<script>
function reportValidate() {
	var content = $("[name=freeReportContent]").val();
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