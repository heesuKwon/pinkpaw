<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@page import=" com.pinkpaw.board.freeboard.model.vo.FreeBoard"%>
<%@page import="java.util.List"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/board.css" />

<%
	List<FreeBoard> list = (List<FreeBoard>)request.getAttribute("list");
	System.out.println("list@왜 아니ㅉㄲ혀 : "+list);
	String pageBar = (String)request.getAttribute("pageBar");
%>

<script>
	function goFreeWrite(){
		location.href = "<%=request.getContextPath()%>/board/community/free/freeWrite";	
	}
	
	$(()=>{
		$("#freeSearchType").on("change", (e)=>{
			var type = $(e.target).val();
			
			$(".freeSearchFrm").hide();
			$("#freeListSearch-"+type).css("display","inline-block");
		});
		
		//테이블의 열을 클릭시 해당 게시물로 이동
		$("td").click((e)=>{		
			var freeNo = $(e.target).parents("tr").children("th").text();

			location.href = "<%=request.getContextPath()%>/board/community/free/freeView?freeNo="+freeNo; 
		});
		
	});
	
	function view(tr) {		
		var freeNo = $(tr).children("th").text();
		location.href = "<%=request.getContextPath()%>/board/community/free/freeView?freeNo="+freeNo;
	}
</script>

<div id="img">
	<img id="free_header" src="<%=request.getContextPath() %>/images/1.jpg" alt="헤더 - 자유게시판 사진" />
</div>
<style>
	img#free_header{
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
    		<select class="btn btn-outline-secondary dropdown-toggle" data-toggle="dropdown" name="key">
				<option value="" selected hidden>키워드</option>
				<option value="">전체</option>
				<option value=free_title>제목</option>
				<option value="free_writer">작성자</option>
				<option value="free_content">내용</option>
			</select>
 		 </div>
  		<input type="text" class="form-control" aria-label="Text input with dropdown button" style="width: 200px" id="keyword">
  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		<button class="btn btn-outline-secondary" style="border-radius: 0" onclick="srch();">검색하기</button>
	</div>
	<script>
	function srch() {
		var key = $("[name=key]").val().trim();
		var keyword = $("#keyword").val().trim();
		$.ajax({
			url: "<%=request.getContextPath()%>/board/community/free/freeSearch",
			data: "key="+key+"&keyword="+keyword,
			type: "get",
			success: function(data){
				console.log(data);
				
				var html = "<tr><th scope='col'>No</th><th scope='col'>사진</th><th scope='col'>제목</th><th scope='col'>작성자</th><th scope='col'>등록일</th><th scope='col'>조회수</th></tr>";
				var num = 1;
				$(data).each((i,b)=>{
					num = num + 1;
					html += "<tr onclick='view(this);'>";
					html += "<th scope='row'>"+b.freeNo+"</th>";
					html += "<td></td>";
					html += "<td>"+b.freeTitle+"</td>";
					html += "<td>"+b.freeWriter+"</td>";
					var d = b.freeEnrollDate;
                    var d_ = d.split(" ");
                    var yyyy = parseInt(d_[2]);
                    var mm = parseInt(d_[0]);
                    var dd = parseInt(d_[1]);
                    if(mm > 9){html += "<td>"+yyyy+"-"+mm+"-"+dd+"</td>";}
                    else{html += "<td>"+yyyy+"-0"+mm+"-"+dd+"</td>";}
					html += "<td>"+b.freeCount+"</td>";
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
	</script>
	
	<%-- 로그인한 경우 글쓰기 가능 --%>
	<% if(memberLoggedIn != null) {%>
	<input type="button" value="글쓰기" id="btn-add" onclick="goFreeWrite();" />

	<% } %>

	<table id="tbl-board" class="table table-hover">
			<tr>
				<th scope="col">No</th>
				<th scope="col">사진</th>
				<th scope="col">제목</th>
				<th scope="col">작성자</th>
				<th scope="col">등록일</th>
				<th scope="col">조회수</th>
			</tr>
	
			<%if(list==null || list.isEmpty()) { %>
			<tr>
				<td colspan="6" align="center">게시글이 없습니다.</td>
			</tr>
			<% }else{
					for(FreeBoard f : list) { %>
			<tr>
			
				<th scope="row"><%=f.getFreeNo() %></th>
				<td>
					<%if(f.getFreeOriginalImg()!=null){
						 String[] renamedImgList = f.getFreeRenamedImg().split("§"); %>
						 <img src="<%=request.getContextPath() %>/upload/board/free/<%=renamedImgList[0] %>" alt="첨부파일" style="width: 80px;"/>
					<%}%>
				</td>
				<td><%-- <a
					href="<%=request.getContextPath()%>/board/community/free/freeView?freeNo=<%=f.getFreeNo()%>"> --%>
						<%=f.getFreeTitle() %><!-- </a> -->
				</td>
				<td><%=f.getFreeWriter() %></td>
				<td><%=f.getFreeEnrolldate() %></td>
				<td><%=f.getFreeCount() %></td>
			</tr>
			<%}} %>

	</table>

	<div id="pageBar">
		<%=pageBar %>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>

