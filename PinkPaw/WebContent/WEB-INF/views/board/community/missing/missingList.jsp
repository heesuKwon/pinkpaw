<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<%@ page
	import="com.pinkpaw.board.missingboard.model.vo.MissingBoard, java.util.*"%>
<%
	
	List<MissingBoard> list = (List<MissingBoard>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
	/* String str = "서울특별시 강서구 어딘가";
	
	System.out.println(str.substring(0, str.indexOf(" ", 6)));
 */
	%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%-- <link rel="stylesheet" 
	  href="<%=request.getContextPath()%>/css/board.css" /> --%>

<style>
#btn-add{
display: block
}

table#missing-board{width:100%; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; border-radius: 5px; }
table#missing-board th, table#tbl-board td {border:1px solid; padding: 5px 0; text-align:center;} 
.card:hover{
box-shadow: 10px 10px 8px rgba(135, 139, 133, 0.4); 
cursor: pointer;

div#pageBar{margin-top:10px; text-align:center;}
div#pageBar span.cPage{color: gray; margin-right: 5px;}
div#pageBar a{margin-right: 5px;}
}

.input-group mb-3{
width: 600px; 
text-align: center;
}
</style>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<div id="img">
	<img id="missing_header" src="<%=request.getContextPath() %>/images/1.jpg" alt="헤더 - 실종게시판 사진" />
</div>
<style>
	img#missing_header{
		width: 1024px;
		height: 300px;
	}
	#img{
		text-align: center;
	}
</style>

<section class="board-container">
<table id="missing-board" style="border:0px; border-collapse: collapse;" >

	<div class="input-group mb-3" >
		<div class="input-group-prepend" >
    		<select class="btn btn-outline-secondary dropdown-toggle" data-toggle="dropdown" name="kind" style="border-radius: 0" onchange="kindchange();">
				<option value="" selected hidden>동물종류</option>
				<option value="">전체</option>
				<option value="강아지">강아지</option>
				<option value="고양이">고양이</option>
				<option value="기타">기타동물</option>
			</select>
 		</div> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		<div class="input-group-prepend">
    		<select class="btn btn-outline-secondary dropdown-toggle" data-toggle="dropdown" name="key">
				<option value="" selected hidden>키워드</option>
				<option value="">전체</option>
				<option value=review_title>제목</option>
				<option value="review_writer">작성자</option>
				<option value="review_content">내용</option>
			</select>
 		 </div>
  		<input type="text" class="form-control" aria-label="Text input with dropdown button" style="width: 200px" id="keyword">
  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		<button class="btn btn-outline-secondary" style="border-radius: 0" onclick="srch();">검색하기</button>
	</div>
	<script>
	function srch() {
		var kind = $("[name=kind]").val().trim();
		var key = $("[name=key]").val().trim();
		var keyword = $("#keyword").val().trim();
		$.ajax({
			url: "<%=request.getContextPath()%>/board/review/reviewSearch",
			data: "kind="+kind+"&key="+key+"&keyword="+keyword,
			type: "get",
			success: function(data){
				console.log(data);
				
				/* var html = "<tr><th scope='col'>번호</th><th scope='col'>첨부파일</th><th scope='col'>종류</th><th scope='col'>제목</th><th scope='col'>작성자</th><th scope='col'>게시일</th></tr>";
				var num = 1;
				$(data).each((i,b)=>{
					num = num + 1;
					html += "<tr onclick='view(this);'>";
					html += "<th scope='row'>"+b.reviewNo+"</th>";
					html += "<td></td>";
					html += "<td>"+b.reviewKind+"</td>";
					html += "<td>"+b.reviewTitle+"</td>";
					html += "<td>"+b.reviewWriter+"</td>";
					html += "<td>"+b.reviewEnrollDate+"</td>";
					html += "</tr>"; */
				});
				$("#tbl-board").html(html);
				if(num < 10){
					$("#pageBar").html('<span>[이전]</span> 1 <span>[다음]</span>');
				}
				else{
					$("#pageBar").html("<%=pageBar%>");
				}
				
			},
			error: function(jqxhr, textStatus, errorThrown){
				console.log("ajax 처리 실패");
				console.log(jqxhr, textStatus, errorThrown);
			}
		});
	}
	function view(tr) {		
		var reviewNo = $(tr).children("th").text();
		location.href = "<%=request.getContextPath()%>/board/review/reviewView?reviewNo="+reviewNo;
	}
	</script>
	
	<%-- 로그인한 경우 글쓰기 가능 --%>
	<%if(memberLoggedIn!=null  ){ %>
	<input type="button" value="글쓰기" id="btn-add"
			onclick="goReviewWrite();"/>
	<%-- 함수를 console에 직접 쳐서 이동할 수 있으므로 그것을 방지하기 위해 if문 안에 script사용--%>
	<script>
	function goReviewWrite(){
		location.href = "<%=request.getContextPath()%>/board/missingWrite";
	}
	</script>		
	<%} %>
	<% for(MissingBoard b : list){ %>
	<div class="card" style="width: 18rem; height: 310px; display: inline-block; margin-top:20px; margin-left: 20px;">
	<a style="text-decoration: none; color: gray" href="<%=request.getContextPath()%>/board/missingView?missingNo=<%=b.getMissingNo() %>">
	<%if(b.getMissingOriginalImg()!=null) {
				String[] renamedImgList = b.getMissingRenamedImg().split("§");%>
	<img class="d-block w-100" src="<%=request.getContextPath()%>/upload/board/missing/<%=renamedImgList[0]%>" style='width:200px; height:150px;' />					
	<% }
		else { %>
 	 <img src="<%=request.getContextPath()%>/images/bg.png"  class="card-img-top" style="height: 150px;">
		<%} %>
	<div class="card-body">
	<p class="card-title">
	<span style="font-size: 12px;">#<% if(b.getMissingKind() != null){
 			String[] arr = b.getMissingKind().split("_");%> 
			<%=arr.length == 1?b.getMissingKind():arr[1]%>
			<%} %>
	</span>
	<br />
	<span style="font-weight:bold; font-size: 18px; color: dark-gray;"><%= b.getMissingTitle()%></span>		
	<br />
	<span style="font-size: 13px;"><%=b.getMissingHpPlace().substring(0, b.getMissingHpPlace().indexOf(" ", 6)) %></span>
	</p>
	<p style="font-size: 11px; color: gray; text-align: right;"><%=b.getMissingEnrollDate() %>
	<br />
	<%=b.getMissingWriter()%>
	</p>
	</div>
  </a>
</div>
		<% } %>
</table>
<div id='pageBar'>
<%=pageBar%>
</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>


