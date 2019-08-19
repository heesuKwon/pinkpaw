<%@page import="com.pinkpaw.board.common.model.vo.BoardComment"%>
<%@page import="java.util.List"%>
<%@page import="com.pinkpaw.board.reviewboard.model.vo.ReviewBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/slick.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/slick-theme.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/slick.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/view.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/write.css" />

<%
	ReviewBoard reviewBoard = (ReviewBoard)request.getAttribute("reviewBoard");
	List<BoardComment> boardCommentList = (List<BoardComment>)request.getAttribute("boardCommentList");
	int count = 0;
	if (reviewBoard.getReviewOriginalImg() != null) {
		String[]	imgList = reviewBoard.getReviewRenamedImg().split("§");
		count = imgList.length; 
	}
	
	
%>

<script>
function fileDownload(oName, rName){
	//크롬은 한글 파라미터값을 자동으로 바꿔주지만
	//ie에서 요청한 한글파일명은 오류를 유발하므로,
	//유니코드 문자로 직접 변환함.
	oName = encodeURIComponent(oName);
	console.log(oName);
	location.href = "<%=request.getContextPath()%>/board/boardFileDownload"
					+"?oName="+oName+"&rName="+rName;
}

function loginAlert() {
	alert("로그인 후 이용가능 합니다.");
	$("#memberId").focus();
}


//jQuery코드는 클라이언트단에서 실행
$(()=>{
	//로그인하지 않고 댓글 쓰기 방지
	$("[name=boardCommentContent]").click(()=>{
		//로그인 하지 않으면
		//java코드는 서버에서 먼저 실행되서 결과값을 돌려줌.
		if(<%=memberLoggedIn==null%>){
			loginAlert();
		}
	});
	
	//boardCommentFrm 유효성 검사: jquery.submit이벤트 핸들러
	$("[name=boardCommentFrm]").submit((e)=>{
		//댓글 유효성 검사
		var len = $("#boardCommentContent").val()
										.trim()
										.length;
		//작성된 글이 없는 경우
		if(len == 0){
			e.preventDefault();//제출을 못하도록 함.
			
		}
	});
	//답글(대댓글) 작성
	$(".btn-reply").on("click",(e)=>{
		//로그인 여부에 따라 분기
		<% if(memberLoggedIn != null){%>
			//로그인한 경우	
			var div = $("<div></div>");
			var html = "<form action='<%=request.getContextPath()%>/board/review/reviewBoardCommentInsert' method='post'>";
			html += "<div class='input-group'>";
			html += "<input type= 'hidden' name='boardRef' value='<%=reviewBoard.getReviewNo()%>'/>";
			html += "<input type= 'hidden' name='boardCommentWriter' value='<%=memberLoggedIn.getMemberId()%>'/>";
			html += "<input type= 'hidden' name='boardCommentLevel' value='2'/>";//답글(대댓글)이기 때문에 2로 작성
			html += "<input type= 'hidden' name='boardCommentRef' value='"+e.target.value+"'/>";
			html += "<textarea name='boardCommentContent' cols='60' rows='1'></textarea>";
			html += "<div class='input-group-append' style='background-color: #da7f84; border-radius: 0.2em;'>";
			html += "<button type='submit' class='btn btn-outline-secondary' style='color: white; border:0px solid transparent;'>등록</button>";
			html += "</div>";
			html += "</div>";
			html += "</form>";
			
			div.html(html);
			
			//클릭한 버튼이 속한 tr 다음에 tr을 추가
			div.insertAfter($(e.target).parent().parent())
				.children("form").slideDown(800)//td가 0.8초동안 슬라이드가 밑으로 내려옴.
				.children("form").submit((e)=>{//form이 제출 될때
									//여기서 e는 form을 가리킴
									console.log($(e.target));
									var len = $(e.target).children("textarea")
														.val()
														.trim()
														.length;
									if(len == 0){
										e.preventDefault();
									}
								});
			//한번 댓글폼 생성 후 이벤트 핸들러 제거
			
		<% } 
		 else{%>
			//로그인하지 않은 경우
			loginAlert();
		<% }%>

	});
	
	//댓글/답글 삭제버튼 클릭시
	$(".btn-delete").on("click",(e)=>{
		if(!confirm("정말 삭제하시겠습니까?")){
			return;
		}		
		location.href = "<%=request.getContextPath()%>/board/review/reviewBoardCommentDelete"
			+"?boardCommentNo="+e.target.value+"&boardNo="+<%=reviewBoard.getReviewNo()%>;
		
	});
	
});

	/*신고하기 부분  */
	function goReviewViewReportOpen(){
		
		var url = "<%=request.getContextPath()%>/board/review/reviewBoardReport?reviewNo=<%=reviewBoard.getReviewNo()%>";
		var target = "new";
		var option = "top=200, left=450, width=450, height=300";
		
		window.open(url,target,option);
	}
	


</script>

<div id="img">
	<img id="review_header" src="<%=request.getContextPath() %>/images/1.jpg" alt="헤더 - 후기게시판 사진" />
</div>
<section class="board-container">
			<%if(memberLoggedIn != null&&
			(memberLoggedIn.getMemberId().equals(reviewBoard.getReviewWriter()) ||
			"admin".equals(memberLoggedIn.getMemberId()))){%>
			<div style='height:50px; padding:5px;'>
				<input type="button" value="삭제"  
						class="btn btn-gray"
						style='position: absolute; right: 0.5em;'
				onclick="deleteBoard();" />
				<input type="button" value="수정"  
						class="btn btn-pink"
						style='position: absolute; right: 7em;'
				onclick="updateBoard();" />
			</div>
				
		<form action="<%=request.getContextPath()%>/board/review/reviewDelete"
			name="reviewDeleteFrm"
			method="post">
			<input type="hidden" name="reviewNo" 
				value="<%=reviewBoard.getReviewNo()%>"/>
			<input type="hidden" name="reviewRenamedImg"
				value="<%=reviewBoard.getReviewRenamedImg()!=null?reviewBoard.getReviewRenamedImg():""%>"/>
		</form>
		<script>
		function updateBoard(){
			location.href = "<%=request.getContextPath()%>/board/review/reviewUpdate?reviewNo=<%=reviewBoard.getReviewNo()%>";
		}
		function deleteBoard(){
			//확인: true, 취소: false
			if(!confirm("정말 삭제하시겠습니까?")){
				return;
			}
			$("[name=reviewDeleteFrm]").submit();
		}
		function goReviewList(){
			location.href = "<%=request.getContextPath()%>/board/review/reviewList";
		}
		</script>
		<%} %>
	 <table class="tg" style="table-layout: fixed;   width: 1024px;">
		<colgroup>
			<col style="width: 35px">
			<col style="width: 100px">
			<col style="width: 35px">
			<col style="width: 100px">
		</colgroup>
		<tr>
			<th class="tg-th">작성자</th>
			<th class="tg" colspan="3"><%=reviewBoard.getReviewWriter()%></th>
		</tr>
		<tr>
			<td class="tg-th">제목</td>
			<td class="tg" colspan="3"><%=reviewBoard.getReviewTitle()%></td>
		</tr>
		<tr>
			<td class="tg-th">글번호</td>
			<td class="tg-ml2k"><%=reviewBoard.getReviewNo()%></td>
			<td class="tg-th">조회수</td>
			<td class="tg-yc5w"><%=reviewBoard.getReviewCount()%></td>
		</tr>
		<tr>
			<td class="tg-th">신고수</td>
			<td class="tg-yc5w"><%=reviewBoard.getReviewReportCount()%></td>
			<td class="tg-th">게시일</td>
			<td class="tg-yc5w"><%=reviewBoard.getReviewEnrollDate()%></td>
		</tr>
		<tr>
			<td class="tg-th">게시물 종류</td>
			<td class="tg-kw6a" colspan="3"><%=reviewBoard.getReviewKind()%></td>
		</tr>
		<tr>
			<td class="tg-th">사진</td>
			<td class="tg-img"  colspan="3">
				<!-- 첨부파일이 있는 경우만 보임 처리 -->
				<%if(count > 0) {%>
				<div class="slider slider-for" style='width: 700px; height: 450px;'>

					<%
						if (reviewBoard.getReviewOriginalImg() != null) {
							String[] renamedImgList = reviewBoard.getReviewRenamedImg().split("§");
							for (int i = 0; i < renamedImgList.length; i++) {
					%>
					<img
						src="<%=request.getContextPath()%>/upload/board/review/<%=renamedImgList[i]%>"
						alt="첨부파일" style='max-width: 700px; max-height: 450px;' />
					<%
						}
						}
					%>
				</div>
				<%} %>
					<br />

					<% if ( count == 2 || count == 3) {%>
				<div class="slider slider-nav" align="center" style='width: 700px; height: 150px;'>
						<% 
						if (reviewBoard.getReviewOriginalImg() != null) {
							String[] renamedImgList = reviewBoard.getReviewRenamedImg().split("§");
							for (int i = 0; i < renamedImgList.length; i++) {
					%>
					<img
							src="<%=request.getContextPath()%>/upload/board/review/<%=renamedImgList[i]%>"
							alt="첨부파일" style='height: 150px; width: 200px;' />
				
					<%
						}
						}
					}
					%>
				</div>
				
			</td>
			
		</tr>
		<tr>
			<td class="tg-th">내용</td>
			<td class="tg-kw6a" colspan="3"><%=reviewBoard.getReviewContent()%></td>
		</tr>

	</table>
		<!-- 글작성자/관리자인 경우에만 수정/삭제 버튼이 보이도록 함. -->

	<div style='padding:10px;'>
	<%if(memberLoggedIn!=null){ %>
	<input type="button" value="신고하기" id="menu"  class="btn btn-pink" 
				onclick="goReviewViewReportOpen();" />
	<%} %>
	<input type="button" value="목록으로" id="menu"   class="btn btn-gray"
				onclick="goReviewList();"  /> 
	</div>
<%-- 	<hr style="margin-top: 30px;"/>
	<div id="comment-container">
		<div class="comment-editor">
			<form action="<%=request.getContextPath()%>/board/review/reviewBoardCommentInsert"
				name="boardCommentFrm"
				method="post">
=======
 --%>
<!--댓글 부분  -->
<hr style="margin-top: 30px;"/>
<form action="<%=request.getContextPath()%>/board/review/reviewBoardCommentInsert"
				name="boardCommentFrm" method="post">
<div class="input-group mb-3">
			<input type="hidden" name="boardRef" 
				value="<%=reviewBoard.getReviewNo()%>"/>
			<input type="hidden" name="boardCommentWriter" 
				value="<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():""%>"/>
			<input type="hidden" name="boardCommentLevel" 
				value="1"/> <!-- 댓글인 경우 1 -->
			<input type="hidden" name="boardCommentRef" 
				value="0"/> <!-- 댓글인 경우 참조댓글이 없으므로 0으로 초기화 -->
			<textarea name="boardCommentContent" class="form-control" cols="60" rows="1"></textarea>
			<div class="input-group-append" style="background-color: #da7f84; border-radius: 0.2em;" >
			<button type="submit" class="btn btn-outline-secondary" style="color: white; border:0px solid transparent;">등록</button>
			</div>
	</div>
</form>

		<!-- 댓글 목록 테이블 -->
		<table id="tbl-comment" class="list-group">
		<%if(boardCommentList != null){
			for(BoardComment bc:boardCommentList) {		
				if(bc.getBoardCommentLevel()==1){%>
				
				<tr class="level1, list-group-item">
					<td> 
						<sub class="comment-writer"><%=bc.getBoardCommentWriter() %></sub> 
						<sub class="comment-date"><%=bc.getBoardCommentDate() %></sub><br /><br />
						<%=bc.getBoardCommentContent() %>
					</td>
					<td>
						<button class="btn-reply btn btn-small btn-pink" value="<%=bc.getBoardCommentNo()%>">답글</button>
						<%--@실습문제: 관리자/댓글작성자에 한하여 이 버튼을 노출시키고,
							댓글 삭제 기능 추가.
							댓글 삭제 후에는 현재 페이지로 다시 이동함.	
						 --%>
						 <%if(memberLoggedIn!=null&&
						 ("admin".equals(memberLoggedIn.getMemberId())||
								 memberLoggedIn.getMemberId().equals(bc.getBoardCommentWriter()))) {%>
						<button class="btn-delete btn btn-small btn-gray" value="<%=bc.getBoardCommentNo()%>">삭제</button>
						<%} %>
					</td>
				</tr>
				<%} 
				else{%>
				<tr class="level2, list-group-item">
					<td style="padding-left: 20px">
						<sub class="comment-writer">ㄴ&nbsp;<%=bc.getBoardCommentWriter() %></sub> 
						<sub class="comment-date"><%=bc.getBoardCommentDate() %></sub> <br /><br />
						<%=bc.getBoardCommentContent() %>
					</td>
					<td>
						<%if(memberLoggedIn!=null&&
						 ("admin".equals(memberLoggedIn.getMemberId())||
								 memberLoggedIn.getMemberId().equals(bc.getBoardCommentWriter()))) {%>
						<button class="btn-delete btn btn-small btn-gray" value="<%=bc.getBoardCommentNo()%>">삭제</button>
						<%} %>
					</td>
				</tr>
			<% 	} 
			}
		}%>
		</table>
</section>

<script>
//3개일때
$('.slider-for').slick({
	  slidesToShow: 1,
	  slidesToScroll: 1,
	  arrows: false,
	  fade: true,
	  asNavFor: '.slider-nav'
	});
$('.slider-nav').slick({
	  slidesToShow: 3,
	  slidesToScroll: 1,
	  asNavFor: '.slider-for',
	  arrows: true,
	  dots: false,
	  centerMode: true,
	  focusOnSelect: true
	});

//두개일때
	/*  $('.slider-for').slick({
		  slidesToShow: 1,
		  slidesToScroll: 1,
		  arrows: false,
		  fade: true,
		  asNavFor: '.slider-nav'
		});
		$('.slider-nav').slick({
		  slidesToShow: 1,
		  slidesToScroll: 1,
		  asNavFor: '.slider-for',
		  dots: true,
		  centerMode: true,
		  focusOnSelect: true
		}); */



</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>

