<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.pinkpaw.board.missingboard.model.vo.MissingBoard, com.pinkpaw.board.common.model.vo.BoardComment, java.util.*"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" 
	  href="<%=request.getContextPath()%>/css/board.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/slick.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/slick-theme.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/header.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/slick.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/view.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/write.css" />  
<link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap" rel="stylesheet">

<!DOCTYPE html>

<% 
	List<BoardComment> commentList = (List<BoardComment>)request.getAttribute("commentList");
	MissingBoard b = (MissingBoard)request.getAttribute("board");
	int count = 0;
	if (b.getMissingRenamedImg() != null) {
		String[]	imgList = b.getMissingRenamedImg().split("§");
		count = imgList.length; 
	}
	
	
	%>

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

function loginAlert(){
	alert("로그인 후 이용하세요.");	
	$("#memberId").focus();
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
			var html = "<form action='<%=request.getContextPath()%>/board/missingCommentInsert' method='post'>";
			html += "<div class='input-group'>";
			html += "<input type='hidden' name='boardRef' value='<%=b.getMissingNo()%>'/>";
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
        location.href="<%=request.getContextPath()%>/board/missingCommentDelete?missingNo=<%=b.getMissingNo() %>&del="+$(this).val();
    });
	
});
</script>


<div id="img-div">
	<img id="header-img" src="<%=request.getContextPath() %>/images/1.jpg"  alt="헤더 - 후기게시판 사진" />
	<div id="blackbg"></div>
	<span class="header-title" >실종게시판 상세보기</span>
</div>

<section class="board-container">

	<%if(memberLoggedIn != null&&
			(memberLoggedIn.getMemberId().equals(b.getMissingWriter()) ||
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

		<!--삭제 부분  -->
		<form action="<%=request.getContextPath()%>/board/missingDelete"
		      name="boardDeleteFrm"
		      method="post">
			<input type="hidden" name="boardNo"
				   value="<%=b.getMissingNo()%>" />
			<input type="hidden" name="renamedFileName" 
				   value="<%=b.getMissingRenamedImg()!=null?
						   		b.getMissingRenamedImg():""%>"/>      
		</form>
	<script>
		function updateBoard(){
			location.href = "<%=request.getContextPath()%>/board/missingUpdateForm?missingNo=<%=b.getMissingNo()%>"
		}
		function deleteBoard(){
			if(!confirm("정말 삭제 하시겠습니까?")){
				return;
			}
			$("[name=boardDeleteFrm]").submit();
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
			<th class="tg" colspan="3"><%=b.getMissingWriter() %> </th>
		</tr>
		<tr>
			<td class="tg-th">제목</td>
			<td class="tg" colspan="3"><%=b.getMissingTitle() %></td>
		</tr>
		<tr>
			<td class="tg-th">글번호</td>
			<td class="tg-ml2k"><%=b.getMissingNo() %></td>
			<td class="tg-th">조회수</td>
			<td class="tg-yc5w"><%=b.getMissingCount() %> </td>
		</tr>
		<tr>
			<td class="tg-th">신고수</td>
			<td class="tg-yc5w"><%=b.getMissingReportCount() %></td>
			<td class="tg-th">게시일</td>
			<td class="tg-yc5w"><%=b.getMissingEnrollDate() %></td>
		</tr>
		<tr>
			<td class="tg-th">잃어버린장소</td>
			<td class="tg-yc5w"><%=b.getMissingHpPlace() %></td>
			<td class="tg-th">잃어버린날짜</td>
			<td class="tg-yc5w"><%=b.getMissingHpDate() %></td>
		</tr>
		<tr>
			<td class="tg-th">동물종류</td>
			<td class="tg-yc5w"><%=b.getMissingKind() %></td>
			<td class="tg-th">사례금</td>
			<td class="tg-yc5w"><%=b.getMissingMoney() ==-1?"사례금협의":b.getMissingMoney() == 0?"사례금없음":b.getMissingMoney()+"만원"%> </td>
		</tr>
	
		<tr>
			<td class="tg-th">사진</td>
			<td class="tg-img"  colspan="3">
				<!-- 첨부파일이 있는 경우만 보임 처리 -->
				<%if(count > 0) {%>
				<div class="slider slider-for" style='width: 700px; height: 450px;'>

					<%
						if (b.getMissingRenamedImg() != null) {
							String[] renamedImgList = b.getMissingRenamedImg().split("§");
							for (int i = 0; i < renamedImgList.length; i++) {
					%>
					<img
						src="<%=request.getContextPath()%>/upload/board/missing/<%=renamedImgList[i]%>"
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
						if (b.getMissingRenamedImg()!= null) {
							String[] renamedImgList = b.getMissingRenamedImg().split("§");
							for (int i = 0; i < renamedImgList.length; i++) {
					%>
					<img
							src="<%=request.getContextPath()%>/upload/board/missing/<%=renamedImgList[i]%>"
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
			<td class="tg-kw6a" colspan="3"><%=b.getMissingContent() %></td>
		</tr>

	</table>


<div style='padding: 10px'>
	<%if(memberLoggedIn!=null){ %>
	<input type="button" value="신고하기" id="menu"  class="btn btn-pink" 
				onclick="goMissingViewReportOpen();;" />
	<%} %>
<input type="button" value="목록으로"   id="menu" class="btn btn-gray"
				onclick="goMissingList();"  />
</div>	



<!-- 수정전  -->
	
	
<!--댓글 부분 -->
<hr style="margin-top: 30px;"/>
<form action="<%=request.getContextPath()%>/board/missingCommentInsert"
				name="boardCommentFrm" method="post">
<div class="input-group mb-3">
				<input type="hidden" name="boardRef" 
					   value="<%=b.getMissingNo()%>" />
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

<!-- 신고하기 부분 -->
<script>
function goMissingList(){
	location.href = "<%=request.getContextPath()%>/board/missingList";
}

function goMissingViewReportOpen(){
	
	var url = "<%=request.getContextPath()%>/board/missing/missingReport?missingNo=<%=b.getMissingNo()%>";
    var target = "new";
    var option = "top=200, left=270, width=450, height=300";
   /*  var status =  "left=500px, top=200px, width=400px, height=500px"; */
    
    window.open(url,target,option);
}
</script>




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