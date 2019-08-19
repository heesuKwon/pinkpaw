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
	
	String str = "한쌍";
	if(p.getParceloutGender().equals("m")){
		str = "수컷";
		}else if(p.getParceloutGender().equals("f")){
		str = "암컷";
		}
	
	int count = 0;
	if (p.getParceloutRenamedImg() != null) {
		String[]	imgList = p.getParceloutRenamedImg().split("§");
		count = imgList.length; 
	}

%>

<%@ include file="/WEB-INF/views/common/header.jsp"%><link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/slick.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/slick-theme.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/slick.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/view.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap" rel="stylesheet">

<script>
var content = <%=p.getParceloutContent()%>;
content = content.replace(/(?:\r\n|\r|\n)/g, '<br />');
document.getElementById("content").value = content;


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
			var tr = $("<tr></tr>");
			var html = "<td style='display:none; text-align:left;' colspan='2'>";
			html += "<form action='<%=request.getContextPath()%>/board/parceloutboard/boardCommentInsert' method='post'>";
			html += "<input type='hidden' name='boardRef' value='<%=p.getParceloutNo()%>'/>";
			html += "<input type='hidden' name='boardCommentWriter' value='<%=memberLoggedIn.getMemberId()%>'/>";
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
	var option = "top=200, left=270, width=450, height=300";
	
	window.open(url,target,option);
}

</script>
</head>
<body>
<div id="img-div">
	<img id="header-img" src="<%=request.getContextPath() %>/images/1.jpg" alt="헤더 - 후기게시판 사진" />
	<div id="blackbg"></div>
	<span class="header-title">분양 게시판 상세보기</span>
</div>


<section class="board-container">

<% if(memberLoggedIn!=null && 
 			(p.getParceloutWriter().equals(memberLoggedIn.getMemberId()) 
 			|| "admin".equals(memberLoggedIn.getMemberId())) ){ %>	 
 				<div style='height:50px; padding:5px;'>
 			
				<input type="button"  value="수정"  
						class="btn btn-pink"
						id="modify"
						onclick="updateBoard();" />
				<input type="button" value="삭제"   
						class="btn btn-gray"
						id="modify"
						onclick="deleteBoard();" />
		</div>
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



<table class="tg" style="table-layout: fixed;   width: 800px;">
		<colgroup>
			<col style="width: 35px">
			<col style="width: 100px">
			<col style="width: 35px">
			<col style="width: 100px">
		</colgroup>
		<tr>
			<th class="tg-th">작성자</th>
			<th class="tg" colspan="3"><%=p.getParceloutWriter() %> </th>
		</tr>
		<tr>
			<td class="tg-th">제목</td>
			<td class="tg" colspan="3"><%=p.getParceloutTitle() %></td>
		</tr>
		<tr>
			<td class="tg-th">글번호</td>
			<td class="tg-ml2k"><%=p.getParceloutNo() %></td>
			<td class="tg-th">조회수</td>
			<td class="tg-yc5w"><%=p.getParceloutCount() %> </td>
		</tr>
		<tr>
			<td class="tg-th">신고수</td>
			<td class="tg-yc5w"><%=p.getParceloutReportCount() %></td>
			<td class="tg-th">게시일</td>
			<td class="tg-yc5w"><%=p.getParceloutEnrolldate() %></td>
		</tr>
		<tr>
			<td class="tg-th">성별</td>
			<td class="tg-yc5w"><%=str %></td>
			<td class="tg-th">동물종류</td>
			<% if(p.getParceloutKind() != null){
				String[] arr = p.getParceloutKind().split("_");%>
				<td class="tg-yc5w"><%=arr.length == 1?p.getParceloutKind():arr[1]%></td>
				<%} %>
		</tr>
		<tr>
			<td class="tg-th">분양지역</td>
			<td class="tg-yc5w"><%=p.getParceloutPlace()%></td>
			<td class="tg-th">책임비</td>
			<td class="tg-yc5w"><%=p.getParceloutMoney()%>원</td>
		</tr>
	<tr>
			<td class="tg-th">사진</td>
			<td class="tg-img"  colspan="3">
				<!-- 첨부파일이 있는 경우만 보임 처리 -->
				<%if(count > 0) {%>
				<div class="slider slider-for" style='width: 700px; height: 450px;'>

					<%
						if (p.getParceloutRenamedImg() != null) {
							String[] renamedImgList = p.getParceloutRenamedImg().split("§");
							for (int i = 0; i < renamedImgList.length; i++) {
					%>
					<img
						src="<%=request.getContextPath()%>/upload/board/parcelout/<%=renamedImgList[i]%>"
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
						if (p.getParceloutRenamedImg()!= null) {
							String[] renamedImgList = p.getParceloutRenamedImg().split("§");
							for (int i = 0; i < renamedImgList.length; i++) {
					%>
					<img
							src="<%=request.getContextPath()%>/upload/board/parcelout/<%=renamedImgList[i]%>"
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
			<td class="tg-kw6a" colspan="3"><%=p.getParceloutContent() %>
			</td>
		</tr>

	</table>





	
	
	<div style='padding: 10px'>
	<%if(memberLoggedIn!=null){ %>
	<input type="button" value="신고하기" id="menu"   class="btn btn-pink" 
				onclick="goParceloutViewReportOpen();;" />

	<%} %>
<input type="button" value="목록으로 " id="menu"   class="btn btn-gray"
				onclick="goBoardList();"  /> 
	</div>
	
	
	
	
	
<!--댓글 부분 -->
<hr style="margin-top: 30px;"/>
<form action="<%=request.getContextPath()%>/board/parceloutboard/boardCommentInsert"
				name="boardCommentFrm" method="post">
<div class="input-group mb-3">
				<input type="hidden" name="boardRef" 
					   value="<%=p.getParceloutNo()%>" />
				<input type="hidden" name="boardCommentWriter" 
					   value="<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():""%>" />
				<input type="hidden" name="boardCommentLevel" 
					   value="1" />
				<input type="hidden" name="boardCommentRef" 
					   value="0" /> <!-- 댓글인 경우 참조댓글이 없으므로 0으로 초기화 -->
				<textarea name="boardCommentContent" class="form-control"
						  id="boardCommentContent" 
						  cols="60" rows="1"></textarea>
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


</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>