<%@page import="com.pinkpaw.board.common.model.vo.BoardComment"%>
<%@page import="java.util.List"%>
<%@page import="com.pinkpaw.board.parceloutboard.model.vo.ParceloutBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ParceloutBoard p = (ParceloutBoard)request.getAttribute("p");
	System.out.println("p@view="+p);
	List<BoardComment> commentList = (List<BoardComment>)request.getAttribute("commentList");
	System.out.println("commentList@view="+commentList);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>분양동물 게시판 상세보기</title>
<script src="<%= request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<style>
div.comment-editor button#btn-insert{
	width: 60px;
	height: 50px;
	color: white;
	background-color: #30f;
	position: relative;
	top: -20px;
}

/*댓글테이블*/
table#tbl-comment{width:580px; margin:0 auto; border-collapse:collapse; clear:both;} 
table#tbl-comment tr td{border-bottom:1px solid; border-top:1px solid; padding:5px; text-align:left; line-height:120%;}
table#tbl-comment tr td:first-of-type {padding: 5px 5px 5px 50px;}
table#tbl-comment tr td:last-of-type {text-align:right; width: 100px;}
table#tbl-comment button.btn-reply{display:none;}
table#tbl-comment tr:hover {background:lightgray;}
table#tbl-comment tr:hover button.btn-reply{display:inline;}
table#tbl-comment sub.comment-writer {color:navy; font-size:14px}
table#tbl-comment sub.comment-date {color:tomato; font-size:10px}

table#tbl-comment tr.level2 {color:gray; font-size: 14px;}
table#tbl-comment tr.level2 td:first-of-type{padding-left:100px;}
table#tbl-comment tr.level2 sub.comment-writer {color:#8e8eff; font-size:14px}
table#tbl-comment tr.level2 sub.comment-date {color:#ff9c8a; font-size:10px}

/* 답글관련 */
table#tbl-comment textarea{
	margin: 4px 0 0;
}
table#tbl-comment button.btn-insert2{
	width: 60px;
	height: 23px;
	color: white;
	background: #30f;
	position: relative;
	top: -5px;
	left: 10px;
}
table#tbl-comment button.btn-delete{
	background: red;
	color: white;
	display: none;
}
table#tbl-comment tr:hover button.btn-delete{
	display: inline;
}
</style>
<script>
function goBoardList(){
	location.href 
		= "<%=request.getContextPath()%>/board/parcelout/parceloutList";	
}

$(()=>{
	
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
			var tr = $("<tr></tr>");
			var html = "<td style='display:none; text-align:left;' colspan='2'>";
			html += "<form action='<%=request.getContextPath()%>/board/parceloutboard/boardCommentInsert' method='post'>";
			html += "<input type= 'hidden' name='boardRef' value='<%=p.getParceloutNo()%>'/>";
			html += "<input type= 'hidden' name='boardCommentWriter' value='admin'/>";
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

	});
	
	//댓글/답글 삭제버튼 클릭시
	$(".btn-delete").click(function(){
    if(!confirm("정말 삭제하시겠습니까?")) return;
    //삭제처리후 돌아올 현재게시판번호도 함께 전송함.
    location.href="<%=request.getContextPath()%>/board/parceloutBoard/ParceloutCommentDelete?parceloutNo=<%=p.getParceloutNo()%>&del="+$(this).val();

	});
	
});
			
function goParceloutViewReportOpen(){
	var url = "<%=request.getContextPath()%>/board/parcelout/parceloutReport?parceloutNo=<%=p.getParceloutNo()%>";
	var target = "new";
	var option = "top=200, left=450, width=450, height=300";
	
	window.open(url,target,option);
}
</script>
</head>
<body>
	<h2>분양동물</h2>
<section id="board-container">
<h2>게시판 상세보기</h2>
<input type="button" value="목록" onclick="goBoardList();"/>
<input type="button" value="신고하기" onclick="goParceloutViewReportOpen();"	 />
<table id="tbl-parcelout-view">
<tr>
	<th>
		<td>
	<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
  <div class="carousel-inner">
		<% if(p.getParceloutOriginalImg() != null){
				String[] renamedImgList = p.getParceloutRenamedImg().split("§");
	if(renamedImgList.length == 1){ %>
    <div class="carousel-item active">
	<img src="<%=request.getContextPath()%>/upload/board/parcelout/<%=renamedImgList[0]%>" class="d-block w-100" width="500" height="300"/>	
    </div>
    <% 
	}else if(renamedImgList.length == 2){
    %>
    <div class="carousel-item active">
	<img src="<%=request.getContextPath()%>/upload/board/parcelout/<%=renamedImgList[0]%>" class="d-block w-100" width="500" height="300"/>	
    </div>
    <div class="carousel-item">
	<img src="<%=request.getContextPath()%>/upload/board/parcelout/<%=renamedImgList[1]%>" class="d-block w-100" width="500" height="300"/>	
    </div>
    <% 
	}else if(renamedImgList.length == 3){
    %>
    <div class="carousel-item active">
	<img src="<%=request.getContextPath()%>/upload/board/parcelout/<%=renamedImgList[0]%>" class="d-block w-100" width="500" height="300"/>	
    </div>
    <div class="carousel-item">
	<img src="<%=request.getContextPath()%>/upload/board/parcelout/<%=renamedImgList[1]%>" class="d-block w-100" width="500" height="300"/>	
    </div>
    <div class="carousel-item">
	<img src="<%=request.getContextPath()%>/upload/board/parcelout/<%=renamedImgList[2]%>" class="d-block w-100" width="500" height="300"/>	
    </div>
  </div>
	<% }
		}%>
  <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
		
		
		</td>
	</th>
</tr>
			<tr>
				<th>글번호</th>
				<td><%=p.getParceloutNo() %></td>
			</tr>
			<tr>
				<th>제 목</th>
				<td><%=p.getParceloutTitle() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=p.getParceloutWriter() %></td>
			</tr>
			<tr>
				<th>조회수</th>
				<td><%=p.getParceloutCount() %></td>
			</tr>
			
			<tr>
				<th>신고수</th>
				<td><%=p.getParceloutReportCount() %></td>
			</tr>

			<tr>
				<th>신고 사유</th>
				<td><%=p.getParceloutReportReason() %></td>
			</tr>
			
		<tr>
			<th>첨부파일</th>
			<td>
				<!-- 첨부파일이 있는 경우만 보임 처리 -->
				<% if(p.getParceloutOriginalImg() != null){
				String[] renamedImgList = p.getParceloutRenamedImg().split("§");
					for(int i=0;i<renamedImgList.length;i++){%>
				<img src="<%=request.getContextPath()%>/upload/board/parcelout/<%=renamedImgList[i]%>" alt="첨부파일"  style='width:200px;' />	
				</a>
				<% }
				}%>
			</td>
		</tr>		
		<tr>
			<th>내용</th>
			<td><%=p.getParceloutContent()%></td>
		</tr>
		<!-- 글작성자/관리자인 경우에만 수정/삭제버튼이 보이도록함. -->	
<%-- 		<% if(memberLoggedIn!=null &&  --%>
<!-- 			(b.getBoardWriter().equals(memberLoggedIn.getMemberId()) -->
<!-- 			|| "admin".equals(memberLoggedIn.getMemberId())) ){ %>	 -->
		<tr>
			<th colspan="2">
				<input type="button" value="수정" 
					   onclick="updateBoard();" />
				<input type="button" value="삭제" 
					   onclick="deleteBoard();" />
			</th>
		</tr>
		<form action="<%=request.getContextPath()%>/board/parceloutboard/parceloutDelete"
		      name="parceloutDeleteFrm"
		      method="post">
			<input type="hidden" name="parceloutNo"
				   value="<%=p.getParceloutNo()%>" />
			<input type="hidden" name="parceloutRenamedImg" 
				   value="<%=p.getParceloutRenamedImg()!=null?
						   		p.getParceloutRenamedImg():""%>"/>      
		</form>
		<script>
		function updateBoard(){
			location.href = "<%=request.getContextPath()%>/board/community/parcelout/parceloutUpdate?parceloutNo=<%=p.getParceloutNo()%>";
		}
		function deleteBoard(){
			if(!confirm("정말 삭제 하시겠습니까?")){
				return;
			}
			$("[name=parceloutDeleteFrm]").submit();
		}
		</script>
			
<%-- 		<%} %> --%>
	</table>
	
	<hr style="margin-top: 30px;"/>
	<div id="comment-container">
		<div class="comment-editor">
			<form action="<%=request.getContextPath()%>/board/parceloutboard/boardCommentInsert"
				  name="boardCommentFrm"
				  method="post">
				<input type="hidden" name="boardRef" 
					   value="<%=p.getParceloutNo()%>" />
				<input type="hidden" name="boardCommentWriter" value="admin">
<%-- 					   value="<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():""%>" /> --%>
				<input type="hidden" name="boardCommentLevel" 
					   value="1" />
				<input type="hidden" name="boardCommentRef" 
					   value="0" /> <!-- 댓글인 경우 참조댓글이 없으므로 0으로 초기화 -->
				<textarea name="boardCommentContent" 
						  id="boardCommentContent" 
						  cols="60" rows="3"></textarea>
				<button type="submit"
					    id="btn-insert">등록</button>			
			</form>
		</div>
		<!-- 댓글목록테이블 -->
		<table id="tbl-comment">
			<%
 			if(commentList != null){
			for(BoardComment bc : commentList){
 			if(bc.getBoardCommentLevel()==1){%>
					<tr class=level1>
						<td>
							<sub class=comment-writer><%=bc.getBoardCommentWriter() %></sub>
							<sub class=comment-date><%=bc.getBoardCommentDate()%></sub>
							<br />
							<%=bc.getBoardCommentContent() %>
						</td>
						<td>
							<button class="btn-reply" 
									value="<%=bc.getBoardCommentNo()%>">답글</button>
							<button class="btn-delete" value="<%=bc.getBoardCommentNo()%>">삭제</button>									
						</td>
					</tr>
					<%} 
				else{%>
					<tr class=level2>
						<td>
							<sub class=comment-writer><%=bc.getBoardCommentWriter() %></sub>
							<sub class=comment-date><%=bc.getBoardCommentDate()%></sub>
							<br />
							<%=bc.getBoardCommentContent() %>
						</td>
						<td>
							<button class="btn-delete" value="<%=bc.getBoardCommentNo()%>">삭제</button>
						</td>
					</tr>
					<%
				}
					}
				} 
			%>
		</table>
	</div>	
</section>
</body>
</html>