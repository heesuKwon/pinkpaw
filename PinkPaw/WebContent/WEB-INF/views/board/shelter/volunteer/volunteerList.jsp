<%@page import="com.pinkpaw.board.volunteer.model.vo.VolunteerBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	List<VolunteerBoard> list = (List<VolunteerBoard>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<script>
$(()=>{
	//테이블의 열을 클릭시 해당 게시물로 이동
	$("td").click((e)=>{		
		var volunteerNo = $(e.target).parents("tr").children("th").text();
		
		location.href = "<%=request.getContextPath()%>/board/volunteer/volunteerView?volunteerNo="+volunteerNo; 
	});
});
			
</script>
<section class="board-container">

	<div class="input-group mb-3" style="width: 600px">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		<div class="input-group-prepend">
    		<select class="btn btn-outline-secondary dropdown-toggle" data-toggle="dropdown" name="key">
				<option value="" selected hidden>키워드</option>
				<option value="">전체</option>
				<option value=volunteer_title>제목</option>
				<option value="volunteer_writer">작성자</option>
				<option value="volunteer_content">내용</option>
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
			url: "<%=request.getContextPath()%>/board/volunteer/volunteerKindChange",
			data: "kind="+kind,
			type: "get",
			success: function(data){
				console.log(data);
				var html = "<tr><th scope='col'>번호</th><th scope='col'>첨부파일</th><th scope='col'>종류</th><th scope='col'>제목</th><th scope='col'>작성자</th><th scope='col'>게시일</th></tr>";
				var num = 1;
				$(data).each((i,b)=>{
					num = num + 1;
					html += "<tr>";
					html += "<th scope='row'>"+b.volunteerNo+"</th>";
					html += "<td></td>";
					html += "<td>"+b.volunteerKind+"</td>";
					html += "<td>"+b.volunteerTitle+"</td>";
					html += "<td>"+b.volunteerWriter+"</td>";
					html += "<td>"+b.volunteerEnrollDate+"</td>";
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
			url: "<%=request.getContextPath()%>/board/volunteer/volunteerSearch",
			data: "kind="+kind+"&key="+key+"&keyword="+keyword,
			type: "get",
			success: function(data){
				console.log(data);
				
				var html = "<tr><th scope='col'>번호</th><th scope='col'>첨부파일</th><th scope='col'>종류</th><th scope='col'>제목</th><th scope='col'>작성자</th><th scope='col'>게시일</th></tr>";
				var num = 1;
				$(data).each((i,b)=>{
					num = num + 1;
					html += "<tr>";
					html += "<th scope='row'>"+b.volunteerNo+"</th>";
					html += "<td></td>";
					html += "<td>"+b.volunteerKind+"</td>";
					html += "<td>"+b.volunteerTitle+"</td>";
					html += "<td>"+b.volunteerWriter+"</td>";
					html += "<td>"+b.volunteerEnrollDate+"</td>";
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
	<%if(memberLoggedIn!=null){ %>
	<input type="button" value="글쓰기" id="btn-add"
			onclick="govolunteerWrite();"/>
	<%-- 함수를 console에 직접 쳐서 이동할 수 있으므로 그것을 방지하기 위해 if문 안에 script사용--%>
	<script>
	function govolunteerWrite(){
		location.href = "<%=request.getContextPath()%>/board/volunteer/volunteerWrite";
	}
	</script>		
	<%} %>
	<table id="tbl-board" class="table table-hover">
		<tr>
			<th scope="col">번호</th>
			<th scope="col">첨부파일</th>
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
			for(VolunteerBoard vb: list){%>		
		<tr>
			<th scope="row"><%=vb.getVolunteerNo() %></th>
			<td>
			<!-- 첨부파일이 있는 경우, 
			file.png이미지가 해당 td에 보이도록 하세요 -->
				<%if(vb.getVolunteerOriginalImg()!=null){
					String[] renamedImgList = vb.getVolunteerRenamedImg().split("§");%>
					<img src="<%=request.getContextPath()%>/upload/board/volunteer/<%=renamedImgList[0]%>" alt="첨부파일" style='width:80px;'/>
					
				<%}%>
			</td>
			<td><%=vb.getVolunteerTitle() %></td>
			<td><%=vb.getVolunteerWriter() %></td>
			<td><%=vb.getVolunteerEnrolldate() %></td>
			
		</tr>
		<%} 
		}%>
	</table>
	<div id="pageBar">
		<%=pageBar %>
	</div>
</section>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>