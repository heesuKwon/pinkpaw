<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="com.pinkpaw.board.common.model.vo.BoardComment"%>
<%@page import="com.pinkpaw.board.freeboard.model.vo.FreeBoard"%>
<%@page import="java.util.List"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%--헤더는 나중에 우리 이미지로 수정하기 <%@ include file="/WEB-INF/views/common/header.jsp"%> --%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/board.css" />

<%
	FreeBoard f = (FreeBoard)request.getAttribute("freeBoard");
	List<BoardComment> commentList = (List<BoardComment>)request.getAttribute("commentList");
    
	String pageBar = (String)request.getAttribute("pageBar");
	
%>

<section class="board-container">
	<!-- <h2>게시판 상세보기</h2> -->
	<table id="tbl-board-view">
		<tr>
			<th>No</th>
			<td><%=f.getFreeNo() %></td>
		</tr>

		<tr>
			<th>제목</th>
			<td><%=f.getFreeTitle() %></td>
		</tr>

		<tr>
			<th>작성자</th>
			<td><%=f.getFreeWriter() %></td>
		</tr>

		<tr>
			<th>조회수</th>
			<td><%=f.getFreeCount() %></td>
		</tr>

		<tr>
			<th>신고수</th>
			<td><%=f.getFreeReportCount() %></td>
		</tr>

		<tr>
			<th>신고 사유</th>
			<td><%=f.getFreeReportReason() %></td>
		</tr>
		
		<tr>
			<th>게시일</th>
			<td><%=f.getFreeEnrolldate() %></td>
		</tr>

		<tr>
			<th>첨부파일</th>
			<td>
			<!-- 첨부파일이 있는 경우만 보임 처리 -->
			<%if(f.getFreeOriginalImg()!=null) {
				String[] renamedImgList = f.getFreeRenamedImg().split("§");
				for(int i=0;i<renamedImgList.length;i++){%>
				<img src="<%=request.getContextPath()%>/upload/board/free/<%=renamedImgList[i]%>" alt="첨부파일"  style='width:200px;' />
			<%} }%>
			</td>
		</tr>

		<tr>
			<th>내용</th>
			<td><%=f.getFreeContent() %></td>
		</tr>


		<!-- 글작성자/관리자인 경우에만 수정/삭제버튼이 보이도록함. -->
		<%-- <% if(memberLoggedIn!=null && 
			(f.getFreeWriter().equals(memberLoggedIn.getMemberId())
			|| "admin".equals(memberLoggedIn.getMemberId())) ){ %> --%>
		<tr>
			<th colspan="2">
			<input type="button" value="수정" onclick="updateFreeBoard();" />
			<input type="button" value="삭제" onclick="deleteFreeBoard();" />
			<input type="button" value="목록으로" onclick="goFreeViewList();" />
			<input type="button" value="신고하기" onclick="goFreeViewReportOpen();" />
			</th>
		</tr>

		<!--삭제 부분  -->
		<form
			action="<%=request.getContextPath()%>/board/community/free/FreeBoardDelete"
			name="freeBoardDeleteFrm" method="post">
			<input type="hidden" name="freeNo" value="<%=f.getFreeNo()%>" />
			<input type="hidden" name="reviewRenamedImg"
				   value="<%=f.getFreeRenamedImg()!=null?f.getFreeRenamedImg():""%>"/>
		</form>
				
		<!--게시글 수정/삭제 부분  -->
		<script>
				function updateFreeBoard(){
					location.href = "<%=request.getContextPath()%>/board/community/free/FreeUpdateForm?freeNo=<%=f.getFreeNo()%>"
				}
				
				function deleteFreeBoard(){
					if(!confirm("정말 삭제 하시겠습니까?")){
						return;
					}
					$("[name=freeBoardDeleteFrm]").submit();
				}
				
				function goFreeViewList(){
					location.href = "<%=request.getContextPath()%>/board/community/free/freeList"
				}
				
				function goFreeViewReportOpen(){
		
					var url = "<%=request.getContextPath()%>/board/community/free/freeBoardReport?freeNo=<%=f.getFreeNo()%>";
					var target = "new";
					var option = "top=200, left=450, width=450, height=300";
					
					window.open(url,target,option);
				}
			</script>

		<%--  <%} %> --%>
	</table>

	<!--댓글 부분  -->
	<%-- <input type="hidden" name="freeNo" value="<%=f.getFreeNo() %>"/>	 --%>
	<hr style="margin-top: 30px;" />
	<div id="comment-container">
		<div class="comment-editor">
			<form
				action="<%=request.getContextPath()%>/board/community/free/freeBoardCommentInsert"
				name="freeBoardCommentFrm" method="post">
				<input type="hidden" name="boardRef" value="<%=f.getFreeNo()%>" />
				<input type="hidden" name="boardCommentWriter" <%-- value="<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():""%>" --%> />
				<input type="hidden" name="boardCommentLevel" value="1" /> <input
					type="hidden" name="boardCommentRef" value="0" />
				<!-- 댓글인 경우 참조댓글이 없으므로 0으로 초기화 -->
				<textarea name="boardCommentContent" id="boardCommentContent"
					cols="60" rows="3"></textarea>
				<button type="submit" id="btn-insert">등록</button>
			</form>
		</div>

		<!-- 댓글 목록 테이블 -->
		<table id="tbl-comment">
			<%
					if(commentList != null){
						for(BoardComment bc : commentList){
							if(bc.getBoardCommentLevel()==1){
					%>
			<tr class=level1>
				<td><sub class=comment-writer><%=bc.getBoardCommentWriter() %></sub>
					<sub class=comment-date><%=bc.getBoardCommentDate()%></sub> <br />
					<%=bc.getBoardCommentContent() %></td>
				<td>
					<button class="btn-reply" value="<%=bc.getBoardCommentNo()%>">답글</button>
					<!-- @실습문제:
										 관리자/댓글작성자에 한해 이버튼을 노출시키고,
										 댓글 삭제 기능추가. 
										 댓글삭제후에는 현재페이지로 다시 이동함. --> <%-- <%if(memberLoggedIn!=null 
										&& ("admin".equals(memberLoggedIn.getMemberId()) 
												|| bc.getBoardCommentWriter().equals(memberLoggedIn.getMemberId()) )){%> --%>
					<button class="btn-delete" value="<%=bc.getBoardCommentNo()%>">삭제</button>
					<%-- <%} %> --%>
				</td>
			</tr>
			<% 		} else { %>
			<tr class=level2>
				<td><sub class=comment-writer><%=bc.getBoardCommentWriter() %></sub>
					<sub class=comment-date><%=bc.getBoardCommentDate()%></sub> <br />
					<%=bc.getBoardCommentContent() %></td>
				<td>
					<!--삭제버튼 추가 --> <%-- <%if(memberLoggedIn!=null 
										&& ("admin".equals(memberLoggedIn.getMemberId()) 
										|| bc.getBoardCommentWriter().equals(memberLoggedIn.getMemberId()) )){%> --%>
					<button class="btn-delete" value="<%=bc.getBoardCommentNo()%>">삭제</button>
					<%-- <%} %> --%>
				</td>
			</tr>

			<%
							}//end of if : level1, level2
				
						}//end of for	
					} 
					%>
		</table>
	</div>
</section>

<script>
 	function fileDownload(oName, rName){
		//ie에서 요청한 한글파일명은 오류를 유발하므로,
		//유니코드 문자로 직접변환함.
		oName = encodeURIComponent(oName);
		console.log(oName);
		
		location.href = "<%=request.getContextPath()%>/board/boardFileDownload"
					  + "?oName=" + oName
					  + "&rName=" + rName;
		
	}
	
	/* function loginAlert(){
		alert("로그인 후 이용하세요.");	
		$("#memberId").focus();
	}
 */

$(()=>{
	//로그인하지 않고 댓글쓰기 방지
	<%-- $("[name=boardCommentContent]").click(()=>{
		if(<%=memberLoggedIn==null%>){
			loginAlert();
		}
	}); --%>
	
	//boardCommentFrm 유효성 검사: jquery.submit이벤트핸들러 이용
	$("[name=boardCommentFrm]").submit((e)=>{
		
		//댓글 유효성검사
		var len = $("#boardCommentContent").val()
										   .trim()
										   .length;
		if(len == 0){
			e.preventDefault();
		}
	});
	
	//답글(대댓글) 작성
	$(".btn-reply").on("click", (e)=>{
		/* 로그인여부에 따라 분기 */
		<%-- <% if(memberLoggedIn != null){%> --%>
			//로그인한 경우
			var tr = $("<tr></tr>");
			var html = "<td style='display:none; text-align:left;' colspan='2'>";
			html += "<form action='<%=request.getContextPath()%>/board/community/free/freeBoardCommentInsert' method='post'>";
			html += "<input type='hidden' name='boardRef' value='<%=f.getFreeNo()%>'/>";
			html += "<input type='hidden' name='boardCommentWriter'/>";  <%-- value='<%=memberLoggedIn.getMemberId()%>' --%>
			html += "<input type='hidden' name='boardCommentLevel' value='2'/>";
			html += "<input type='hidden' name='boardCommentRef' value='"+e.target.value+"'/>";
			html += "<textarea name='boardCommentContent' cols='60' rows='1'></textarea>";
			html += "<button type='submit' class='btn-insert2'>등록</button>";
			html += "</form>";
			html +="</td>";
			tr.html(html);
			
			//클릭한 버튼이 속한 tr 다음에 tr을 추가
			tr.insertAfter($(e.target).parent().parent())
			  .children("td")
			  .slideDown(800)
			  .children("form")
			  .submit((e)=>{
				 console.log($(e.target));
				 var len = $(e.target).children("textarea")
				 					  .val()
				 					  .trim()
				 					  .length;
				 if(len == 0)
					 e.preventDefault();
				 
			  })
			  .find("textarea").focus();
			
			//한번 댓글폼 생성후 이벤트핸들러 제거
			$(e.target).off('click');
			
			
		<%-- <% } else { %>
			//로그인하지 않은 경우
			loginAlert();
		<% } %> --%>
	});
	
	 //삭제버튼 클릭시
    $(".btn-delete").click(function(){
        if(!confirm("정말 삭제하시겠습니까?")) return;
        //삭제처리후 돌아올 현재게시판번호도 함께 전송함.
        location.href="<%=request.getContextPath()%>/board/community/free/freeBoardCommentDelete?freeNo=<%=f.getFreeNo() %>&del="+$(this).val();
    });
	
});
</script>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>