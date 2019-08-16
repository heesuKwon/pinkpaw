<%@page import="java.util.List"%>
<%@page import="com.pinkpaw.board.reviewboard.model.vo.ReviewBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	List<ReviewBoard> list = (List<ReviewBoard>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<script>
$(()=>{
	//테이블의 열을 클릭시 해당 게시물로 이동
	$("td").click((e)=>{		
		var reviewNo = $(e.target).parents("tr").children("th").text();
		
		location.href = "<%=request.getContextPath()%>/board/review/reviewView?reviewNo="+reviewNo; 
	});
});
			
</script>

<div id="img">
	<img id="review_header" src="<%=request.getContextPath() %>/images/1.jpg" alt="헤더 - 후기게시판 사진" />
</div>
<style>
	img#review_header{
		width: 1024px;
		height: 300px;
	}
	#img{
		text-align: center;
	}
</style>

<section class="board-container">
	
	<div class="input-group mb-3" style="width: 600px">
		<div class="input-group-prepend">
    		<select class="btn btn-outline-secondary dropdown-toggle" data-toggle="dropdown" name="kind" style="border-radius: 0" onchange="kindchange();">
				<option value="" selected hidden>글종류</option>
				<option value="">전체</option>
				<option value="입양후기">입양후기</option>
				<option value="분양후기">분양후기</option>
				<option value="찾은후기">찾은후기</option>
				<option value="봉사후기">봉사후기</option>
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
	function kindchange() {
		var kind = $("[name=kind]").val().trim();
		$.ajax({
			url: "<%=request.getContextPath()%>/board/review/reviewKindChange",
			data: "kind="+kind,
			type: "get",
			success: function(data){
				console.log(data);
				var html = "<tr><th scope='col'>번호</th><th scope='col'>첨부파일</th><th scope='col'>종류</th><th scope='col'>제목</th><th scope='col'>작성자</th><th scope='col'>게시일</th></tr>";
				var num = 1;
				$(data).each((i,b)=>{
					num = num + 1;
					html += "<tr onclick='view(this);'>";
					html += "<th scope='row'>"+b.reviewNo+"</th>";
					html += "<td></td>";
					html += "<td>"+b.reviewKind+"</td>";
					html += "<td>"+b.reviewTitle+"</td>";
					html += "<td>"+b.reviewWriter+"</td>";
					var d = b.reviewEnrollDate;
                    var d_ = d.split(" ");
                    var yyyy = parseInt(d_[2]);
                    var mm = parseInt(d_[0]);
                    var dd = parseInt(d_[1]);
                    if(mm > 9){html += "<td>"+yyyy+"-"+mm+"-"+dd+"</td>";}
                    else{html += "<td>"+yyyy+"-0"+mm+"-"+dd+"</td>";}
					html += "</tr>";
				});
				$("#tbl-board").html(html);
				if(num < 10){
					$("#pageBar").html('<span>[이전]</span> 1 <span>[다음]</span>');
				}
				else{
					$("#pageBar").html("<%=pageBar %>");
				}
			},
			error: function(jqxhr, textStatus, errorThrown){
				console.log("ajax 처리 실패");
				console.log(jqxhr, textStatus, errorThrown);
			}
		});
	}
	
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
				
				var html = "<tr><th scope='col'>번호</th><th scope='col'>첨부파일</th><th scope='col'>종류</th><th scope='col'>제목</th><th scope='col'>작성자</th><th scope='col'>게시일</th></tr>";
				var num = 1;
				$(data).each((i,b)=>{
					num = num + 1;
					html += "<tr onclick='view(this);'>";
					html += "<th scope='row'>"+b.reviewNo+"</th>";
					html += "<td></td>";
					html += "<td>"+b.reviewKind+"</td>";
					html += "<td>"+b.reviewTitle+"</td>";
					html += "<td>"+b.reviewWriter+"</td>";
					var d = b.reviewEnrollDate;
                    var d_ = d.split(" ");
                    var yyyy = parseInt(d_[2]);
                    var mm = parseInt(d_[0]);
                    var dd = parseInt(d_[1]);
                    if(mm > 9){html += "<td>"+yyyy+"-"+mm+"-"+dd+"</td>";}
                    else{html += "<td>"+yyyy+"-0"+mm+"-"+dd+"</td>";}
					html += "</tr>";
				});
				$("#tbl-board").html(html);
				if(num < 10){
					$("#pageBar").html('<span>[이전]</span> 1 <span>[다음]</span>');
				}
				else{
					$("#pageBar").html("<%=pageBar %>");
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
	<%if(memberLoggedIn!=null){ %>
	<input type="button" value="글쓰기" id="btn-add"
			onclick="goReviewWrite();"/>
	<%-- 함수를 console에 직접 쳐서 이동할 수 있으므로 그것을 방지하기 위해 if문 안에 script사용--%>
	<script>
	function goReviewWrite(){
		location.href = "<%=request.getContextPath()%>/board/review/reviewWrite";
	}
	</script>		
	<%} %>
	<table id="tbl-board" class="table table-hover">
		<tr>
			<th scope="col">번호</th>
			<th scope="col">첨부파일</th>
			<th scope="col">종류</th>
			<th scope="col">제목</th>
			<th scope="col">작성자</th>
			<th scope="col">게시일</th>
		</tr>
		<%if(list==null || list.isEmpty()){ %>
		<tr>
			<td colspan="6" align="center">게시글이 없습니다.</td>
		</tr>
		<%}
		else{
			for(ReviewBoard b: list){%>		
		<tr>
			<th scope="row"><%=b.getReviewNo() %></th>
			<td>
			<!-- 첨부파일이 있는 경우, 
			file.png이미지가 해당 td에 보이도록 하세요 -->
				<%if(b.getReviewOriginalImg()!=null){
					String[] renamedImgList = b.getReviewRenamedImg().split("§");%>
					<img src="<%=request.getContextPath()%>/upload/board/review/<%=renamedImgList[0]%>" alt="첨부파일" style='width:80px;'/>
					
				<%}%>
			</td>
			<td><%=b.getReviewKind() %></td>
			<td><%-- <a href="<%=request.getContextPath()%>/board/ReviewboardView?boardNo=<%=b.getReviewNo()%>"> --%><%=b.getReviewTitle() %><!-- </a> --></td>
			<td><%=b.getReviewWriter() %></td>
			<td><%=b.getReviewEnrollDate() %></td>
			
		</tr>
		<%} 
		}%>
	</table>
	<div id="pageBar">
		<%=pageBar %>
	</div>
</section>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>