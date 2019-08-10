<%@page import="com.pinkpaw.board.reviewboard.model.vo.ReviewBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	ReviewBoard rb = (ReviewBoard)request.getAttribute("reviewBoard");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<section id="board-container">
	<form action="<%=request.getContextPath()%>/board/review/reviewUpdateEnd"
		method="post"
		enctype="multipart/form-data">
		<!-- 파일을 업로드하려면 enctype속성이 꼭 있어야 함. -->
		<input type="hidden" name="reviewNo" value="<%=rb.getReviewNo()%>" />
		<table id="tbl-board-view">
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="reviewTitle" value="<%=rb.getReviewTitle()%>" required></input>
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
					<input type="text" name="reviewWriter" value="<%=rb.getReviewWriter()%>"
					required readonly></input>
				</td>
			</tr>
			<tr>
				<th>게시물 종류</th>
				<td>
					<input type="radio" name="reviewKind" id="organic" value="입양후기" required/>
					<label for="organic">입양후기</label>
					<input type="radio" name="reviewKind" id="parcelout" value="분양후기" required/>
					<label for="parcelout">분양후기</label>
					<input type="radio" name="reviewKind" id="missing" value="찾은후기" required/>
					<label for="missing">찾은후기</label>
					<input type="radio" name="reviewKind" id="volunteer" value="봉사후기" required/>
					<label for="volunteer">봉사후기</label>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td style="position: relative">
					<!-- 
					첨부파일 관련 사용자 시나리오:
					1. 첨부파일이 없는 경우 
					2. 첨부파일이 있는 경우
						- 파일 관련 수정을 하지 않는 경우:  upFile=null
						- 새로운 파일을 첨부한 경우: upFile있음. 기존파일은 삭제
						- 기존 파일을 삭제만 하는 경우(delFile): upFile=null.기존파일 삭제
					-->
					<!-- file태그의 value 속성은 보안상 이유로 임의 변경이 불가함. -->
					첫번째로 첨부된 사진이 메인사진으로 등록됩니다.<br/>
					<input type="file" name="upFile" onchange="previewImage(this,'view_area')"/>
					<input type="button" value="추가" onclick="attachFile.add()"><br/>
					<div id='view_area' style='position:relative; width: 100px; height: 100px; display: none; '></div>
					<div id="attachFileDiv">
					</div>
					<%-- <input type="file" name="upFile"/>
					<span id="fname"><%=rb.getReviewOriginalImg()!=null?b.getOriginalFileName():""%></span>
					첨부파일이 있는 경우 기존파일 삭제용
					<%if(b.getOriginalFileName()!=null){ %>
					<br />
					<input type="checkbox" name="delFile" id="delFile" />
					<label for="delFile">첨부파일삭제</label>
					<%} %> --%>
					<input type="hidden" name="oldOName" value="<%=rb.getReviewOriginalImg()!=null?rb.getReviewOriginalImg():""%>"/>
					<input type="hidden" name="oldRName" value="<%=rb.getReviewRenamedImg()!=null?rb.getReviewRenamedImg():""%>"/>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea name="reviewContent" 
							cols="40" rows="5" required><%=rb.getReviewContent()%>
					</textarea>
				</td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="submit" value="수정" 
							onclick="return boardValidate();"/>
				</th>
			</tr>
		</table>	
	</form>
</section>
<script>
$("[name=upFile]").change(function(){
	console.log($(this).val());
	//사용자가 파일을 선택한 경우
	if($(this).val() != ""){
		$("#fname").hide();
		//delFile을 숨기고 다음(라벨)도 숨겨라
		$("#delFile").hide().next().hide();
	}
	//사용자가 파일 선택을 취소한 경우
	else{
		$("#fname").show();
		$("#delFile").show().next().show();
	}
});
function boardValidate() {
	var content = $("[name=boardContent]").val();
	//좌우 공백을 제거하고 길이가 0이면(내용이 비어 있는 경우)
	if(content.trim().length == 0){
		alert("내용을 입력하세요.");
		return false;
	}
	
	return true;
}
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>