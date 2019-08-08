<%@page import="com.pinkpaw.board.common.model.vo.BoardComment"%>
<%@page import="java.util.List"%>
<%@page import="com.pinkpaw.board.reviewboard.model.vo.ReviewBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<%
	ReviewBoard reviewBoard = (ReviewBoard)request.getAttribute("reviewBoard");
	List<BoardComment> boardCommentList = (List<BoardComment>)request.getAttribute("boardCommentList");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />

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
			var tr = $("<tr></tr>");
			var html = "<td style='display:none; text-align:left;' colspan='2'>";
			html += "<form action='<%=request.getContextPath()%>/board/boardCommentInsert' method='post'>";
			html += "<input type= 'hidden' name='boardRef' value='<%=reviewBoard.getReviewNo()%>'/>";
			html += "<input type= 'hidden' name='boardCommentWriter' value='<%=memberLoggedIn.getMemberId()%>'/>";
			html += "<input type= 'hidden' name='boardCommentLevel' value='2'/>";//답글(대댓글)이기 때문에 2로 작성
			html += "<input type= 'hidden' name='boardCommentRef' value='"+e.target.value+"'/>";
			html += "<textarea name='boardCommentContent' cols='60' rows='1'></textarea>";
			html += "<button type='submit' class='btn-insert2'>등록</button>";
			html += "</form>";
			html += "</td>";
			
			tr.html(html);
			
			//클릭한 버튼이 속한 tr 다음에 tr을 추가
			tr.insertAfter($(e.target).parent().parent())
				.children("td").slideDown(800)//td가 0.8초동안 슬라이드가 밑으로 내려옴.
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
		location.href = "<%=request.getContextPath()%>/board/boardCommentDelete"
			+"?boardCommentNo="+e.target.value+"&boardNo="+<%=reviewBoard.getReviewNo()%>;
		
	});
	
});
</script>
<section class="board-container">
	<table id="tbl-board-view">
		<tr>
			<th>글번호</th>
			<td><%=reviewBoard.getReviewNo() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%=reviewBoard.getReviewTitle() %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%=reviewBoard.getReviewWriter() %></td>
		</tr>
		<tr>
			<th>게시물 종류</th>
			<td><%=reviewBoard.getReviewKind() %></td>
		</tr>
		<tr>
			<th>조회수</th>
			<td><%=reviewBoard.getReviewCount() %></td>
		</tr>
		<tr>
			<th>좋아요</th>
			<td><%=reviewBoard.getReviewLike() %></td>
		</tr>
		<tr>
			<th>게시일</th>
			<td><%=reviewBoard.getReviewEnrollDate() %></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
			<!-- 첨부파일이 있는 경우만 보임 처리 -->
			<%if(reviewBoard.getReviewOriginalImg()!=null) {%>
				<a href="javascript:fileDownload('<%=reviewBoard.getReviewOriginalImg()%>','<%=reviewBoard.getReviewRenamedImg()%>');">
					<img src="<%=request.getContextPath()%>/images/file.png" alt="" />
					<!-- 사용자가 업로드한 파일명 -->
					<%=reviewBoard.getReviewOriginalImg() %>
				</a>
			<%} %>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<%=reviewBoard.getReviewContent() %>
			</td>
		</tr>
		<!-- 글작성자/관리자인 경우에만 수정/삭제 버튼이 보이도록 함. -->
		<%if(memberLoggedIn != null&&
			(memberLoggedIn.getMemberId().equals(reviewBoard.getReviewWriter()) ||
			"admin".equals(memberLoggedIn.getMemberId()))){%>
		<tr>
			<th colspan="2">
				<input type="button" value="수정"
				onclick="updateBoard();" />
				<input type="button" value="삭제"
				onclick="deleteBoard();" />
			</th>
		</tr>
		<form action="<%=request.getContextPath()%>/board/boardDelete"
			name="boardDeleteFrm"
			method="post">
			<input type="hidden" name="boardNo" 
				value="<%=reviewBoard.getReviewNo()%>"/>
			<input type="hidden" name="renamedFileName"
				value="<%=reviewBoard.getReviewRenamedImg()!=null?reviewBoard.getReviewRenamedImg():""%>"/>
		</form>
		<script>
		function updateBoard(){
			location.href = "<%=request.getContextPath()%>/board/boardUpdateForm?boardNo=<%=reviewBoard.getReviewNo()%>";
		}
		function deleteBoard(){
			//확인: true, 취소: false
			if(!confirm("정말 삭제하시겠습니까?")){
				return;
			}
			$("[name=boardDeleteFrm]").submit();
		}
		</script>
		<%} %>
	</table>
	
	<hr style="margin-top: 30px;"/>
	<div id="comment-container">
		<div class="comment-editor">
			<form action="<%=request.getContextPath()%>/board/boardCommentInsert"
				name="boardCommentFrm"
				method="post">
			<input type="hidden" name="boardRef" 
				value="<%=reviewBoard.getReviewNo()%>"/>
			<input type="hidden" name="boardCommentWriter" 
				value="<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():""%>"/>
			<input type="hidden" name="boardCommentLevel" 
				value="1"/> <!-- 댓글인 경우 1 -->
			<input type="hidden" name="boardCommentRef" 
				value="0"/> <!-- 댓글인 경우 참조댓글이 없으므로 0으로 초기화 -->
			<textarea name="boardCommentContent" id="boardCommentContent" cols="60" rows="3"></textarea>
			<button type="submit" id="btn-insert">등록</button>
			</form>
		</div>
		<!-- 댓글 목록 테이블 -->
		<table id="tbl-comment">
		<%if(boardCommentList != null){
			for(BoardComment bc:boardCommentList) {		
				if(bc.getBoardCommentLevel()==1){%>
				
				<tr class="level1">
					<td> 
						<sub class="comment-writer"><%=bc.getBoardCommentWriter() %></sub> 
						<sub class="comment-date"><%=bc.getBoardCommentDate() %></sub>
						<br />
						<%=bc.getBoardCommentContent() %>
					</td>
					<td>
						<button class="btn-reply" value="<%=bc.getBoardCommentNo()%>">답글</button>
						<%--@실습문제: 관리자/댓글작성자에 한하여 이 버튼을 노출시키고,
							댓글 삭제 기능 추가.
							댓글 삭제 후에는 현재 페이지로 다시 이동함.	
						 --%>
						 <%if(memberLoggedIn!=null&&
						 ("admin".equals(memberLoggedIn.getMemberId())||
								 memberLoggedIn.getMemberId().equals(bc.getBoardCommentWriter()))) {%>
						<button class="btn-delete" value="<%=bc.getBoardCommentNo()%>">삭제</button>
						<%} %>
					</td>
				</tr>
				<%} 
				else{%>
				<tr class="level2">

					<td> 
						<sub class="comment-writer"><%=bc.getBoardCommentWriter() %></sub> 
						<sub class="comment-date"><%=bc.getBoardCommentDate() %></sub>
						<br />
						<%=bc.getBoardCommentContent() %>
					</td>
					<td>
						<%if(memberLoggedIn!=null&&
						 ("admin".equals(memberLoggedIn.getMemberId())||
								 memberLoggedIn.getMemberId().equals(bc.getBoardCommentWriter()))) {%>
						<button class="btn-delete" value="<%=bc.getBoardCommentNo()%>">삭제</button>
						<%} %>
					</td>
				</tr>
			<% 	} 
			}
		}%>
		</table>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>