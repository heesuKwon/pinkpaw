<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.pinkpaw.board.common.model.vo.BoardComment"%>
<%@page import="java.util.List"%>
<%@page import="com.pinkpaw.board.parceloutboard.model.vo.ParceloutBoard"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/write.css" />
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"> 

<%
	ParceloutBoard p = (ParceloutBoard)request.getAttribute("p");
	System.out.println("p@view="+p);
	List<BoardComment> commentList = (List<BoardComment>)request.getAttribute("commentList");
	System.out.println("commentList@view="+commentList);
	
	String str = "한쌍";
	if(p.getParceloutGender().equals("m")){
		str = "수컷";
		}else if(p.getParceloutGender().equals("f")){
		str = "암컷";
		}
%>

<!-- <style>
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
</style> -->

<script>

function loginAlert(){
	alert("로그인 후 이용하세요.");	
// 	$("#memberId").focus();
}


$(()=>{
	//로그인하지 않고 댓글쓰기 방지
	$("[name=boardCommentContent]").click(()=>{
		if(<%=memberLoggedIn==null%>){
			loginAlert();
		}
	});
	
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
		<% if(memberLoggedIn != null){%>
			//로그인한 경우
			var div = $("<div></div>");
			var html = "<form action='<%=request.getContextPath()%>/board/parceloutboard/boardCommentInsert' method='post'>";
			html += "<div class='input-group'>";
			html += "<input type='hidden' name='boardRef' value='<%=p.getParceloutNo()%>'/>";
			html += "<input type='hidden' name='boardCommentWriter' value='<%=memberLoggedIn.getMemberId()%>'/>";
			html += "<input type='hidden' name='boardCommentLevel' value='2'/>";
			html += "<input type='hidden' name='boardCommentRef' value='"+e.target.value+"'/>";
			html += "<textarea name='boardCommentContent' cols='60' rows='1'></textarea>";
			html += "<div class='input-group-append' style='background-color: #da7f84; border-radius: 0.2em;'>";
			html += "<button type='submit' class='btn btn-outline-secondary' style='color: white; border:0px solid transparent;'>등록</button>";
			html += "</div>";
			html += "</div>";
			html += "</form>";
			div.html(html);
			
			//클릭한 버튼이 속한 tr 다음에 tr을 추가
			div.insertAfter($(e.target).parent().parent())
			  .children("form")
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
			
			
		<% } else { %>
			//로그인하지 않은 경우
			loginAlert();
		<% } %>
	});
	
	 //삭제버튼 클릭시
    $(".btn-delete").click(function(){
        if(!confirm("정말 삭제하시겠습니까?")) return;
        //삭제처리후 돌아올 현재게시판번호도 함께 전송함.
        location.href="<%=request.getContextPath()%>/board/parceloutBoard/ParceloutCommentDelete?parceloutNo=<%=p.getParceloutNo() %>&del="+$(this).val();
    });
	
});

function goBoardList() {
	location.href = "<%=request.getContextPath()%>/board/parcelout/parceloutList";
}		

function goParceloutViewReportOpen(){
	var url = "<%=request.getContextPath()%>/board/parcelout/parceloutReport?parceloutNo=<%=p.getParceloutNo()%>";
	var target = "new";
	var option = "top=200, left=450, width=450, height=300";
	
	window.open(url,target,option);
}

</script>
<!-- </head>
<body> -->
<h2>분양동물</h2>
<h2>게시판 상세보기</h2>
<section class="board-container">
<input type="button" value="목록" onclick="goBoardList();"/>
<%if(memberLoggedIn!=null){ %>
<input type="button" value="신고" onclick="goParceloutViewReportOpen();"/>
<%} %>
<table id="tbl-parcelout-view" >
<tr>
	<th>
		<td>
	<div id="carouselExampleControls" class="carousel slide" data-ride="carousel" >
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
				<th>분양지역</th>
				<td><%=p.getParceloutPlace()%></td>
			</tr>
			<tr>
				<th>책임비</th>
				<td><%=p.getParceloutMoney()%>원</td>
			</tr>
			<tr>
				<th>동물종류</th>
				<% if(p.getParceloutKind() != null){
				String[] arr = p.getParceloutKind().split("_");%>
				<td><%=arr.length == 1?p.getParceloutKind():arr[1]%></td>
				<%} %>
			</tr>
			<tr>
				<th>성 별</th>
				<td><%=str %></td>
			</tr>
				<% if(p.getParceloutOriginalImg() != null){
				String[] renamedImgList = p.getParceloutRenamedImg().split("§");
					for(int i=0;i<renamedImgList.length;i++){%>
<!-- 		<tr> -->
<!-- 			<th>첨부파일</th> -->
<!-- 			<td> -->
<!-- 				첨부파일이 있는 경우만 보임 처리 -->
<%-- 				<img src="<%=request.getContextPath()%>/upload/board/parcelout/<%=renamedImgList[i]%>" alt="첨부파일"  style='width:200px;' />	 --%>
<!-- 			</td> -->
<!-- 		</tr>		 -->
				<% }
 				}%> 
		<tr>
			<th>내 용</th>
			<td><%=p.getParceloutContent()%></td>
		</tr>
		<!-- 글작성자/관리자인 경우에만 수정/삭제버튼이 보이도록함. -->	
		<% if(memberLoggedIn!=null && 
 			(p.getParceloutWriter().equals(memberLoggedIn.getMemberId()) 
 			|| "admin".equals(memberLoggedIn.getMemberId())) ){ %>	 
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
			location.href = "<%=request.getContextPath()%>/board/parcelout/parceloutUpdate?parceloutNo=<%=p.getParceloutNo()%>";
		}
		function deleteBoard(){
			if(!confirm("정말 삭제 하시겠습니까?")){
				return;
			}
			$("[name=parceloutDeleteFrm]").submit();
		}
		</script>
			
		<%} %>
	</table>

<!-- 댓글 부분 -->
<hr style="margin-top: 30px;"/>
<form action="<%=request.getContextPath()%>/board/parceloutboard/boardCommentInsert"
				  name="boardCommentFrm" method="post">
	<div class="input-group mb-3">
			<input type="hidden" name="boardRef" value="<%=p.getParceloutNo()%>" />
			<input type="hidden" name="boardCommentWriter" value="<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():""%>" />
			<input type="hidden" name="boardCommentLevel" value="1" />
			<input type="hidden" name="boardCommentRef" value="0" /> <!-- 댓글인 경우 참조댓글이 없으므로 0으로 초기화 -->
			<textarea name="boardCommentContent" class="form-control" cols="60" rows="1"></textarea>
			<div class="input-group-append" style="background-color: #da7f84; border-radius: 0.2em;" >
				<button type="submit" class="btn btn-outline-secondary" style="color: white; border:0px solid transparent;">등록</button>
			</div>
	</div>			
</form>

		<!-- 댓글목록테이블 -->
		<table id="tbl-comment" class="list-group">
			<%
			if(commentList != null){
				for(BoardComment bc : commentList){
					if(bc.getBoardCommentLevel()==1){
			%>
					<tr class="level1, list-group-item">
						<td>
							<sub class=comment-writer><%=bc.getBoardCommentWriter() %></sub>
							<sub class=comment-date><%=bc.getBoardCommentDate()%></sub> <br /><br />
							<%=bc.getBoardCommentContent() %>
						</td>
						<td>
							<button class="btn-reply btn btn-small btn-pink" value="<%=bc.getBoardCommentNo()%>">답글</button>
							<!-- @실습문제:
								 관리자/댓글작성자에 한해 이버튼을 노출시키고,
								 댓글 삭제 기능추가. 
								 댓글삭제후에는 현재페이지로 다시 이동함. -->
							 
							<%if(memberLoggedIn!=null 
								&& ("admin".equals(memberLoggedIn.getMemberId()) 
										|| bc.getBoardCommentWriter().equals(memberLoggedIn.getMemberId()) )){%>
							<button class="btn-delete btn btn-small btn-gray" value="<%=bc.getBoardCommentNo()%>">삭제</button>
							<%} %>
						</td>
					</tr>
			<% 		} else { %>
					<tr class="level2, list-group-item">
						<td style="padding-left: 20px">
							<sub class=comment-writer>ㄴ&nbsp;<%=bc.getBoardCommentWriter() %></sub>
							<sub class=comment-date><%=bc.getBoardCommentDate()%></sub><br /><br />
							<%=bc.getBoardCommentContent() %>
						</td>
						<td>
							<!-- 삭제버튼 추가 -->
							<%if(memberLoggedIn!=null 
								&& ("admin".equals(memberLoggedIn.getMemberId()) 
								|| bc.getBoardCommentWriter().equals(memberLoggedIn.getMemberId()) )){%>
							<button class="btn-delete btn btn-small btn-gray" value="<%=bc.getBoardCommentNo()%>">삭제</button>
							<%} %>
						</td>
					</tr>
		
			<%
					}//end of if : level1, level2
		
				}//end of for	
			} 
			%>
		</table>

</section>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>