<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
#PA{width: 1000px; height: 1000px; margin-top: 100px; margin-left: 50px;}


 #layout {
	display: inline-block;
	margin: 1em;
	width: 17rem;
	height: 18rem;
} 
</style>

<div id="PA">


	<h2>실종게시판</h2>
	
	<div class="input-group mb-3" style="width: 600px">
		<div class="input-group-prepend">
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
	<br/>
	<%if(list==null || list.isEmpty()){ %>
	<div class="card" id="layout">게시글이없습니다.</div>
	<%} else{ %>
	
			<% for(MissingBoard b : list){ %>
	<a href="<%=request.getContextPath()%>/board/missingView?missingNo=<%=b.getMissingNo() %>">
	<div class="card" id="layout">
	<%if(b.getMissingOriginalImg()!=null) {
				String[] renamedImgList = b.getMissingRenamedImg().split("§");
				%>
				<img src="<%=request.getContextPath()%>/upload/board/missing/<%=renamedImgList[0]%>" class="card-img-top" alt="logo"  style='width:200px; height:200px;' />					
			<% 
			}%>
		<div class="card-body">

			<h2>"잃어버린 <%=b.getMissingKind() %> 를 찾습니다"</h2>
			<p class="card-text">
				지역: <%=b.getMissingHpPlace().substring(0, b.getMissingHpPlace().indexOf(" ", 6)) %>
			</p>
			<p class="card-text">
				사례금:<%=b.getMissingMoney() ==-1?"사례금협의":b.getMissingMoney() == 0?"사례금없음":b.getMissingMoney()+"만원" %>
			</p>

		</div>
	</div>
	</a>
				
		<%}
	}%>




	<div id='pageBar'><%=pageBar %></div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</div>


