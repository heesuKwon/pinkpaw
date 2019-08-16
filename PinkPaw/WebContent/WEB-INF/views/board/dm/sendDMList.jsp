<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ page import="com.pinkpaw.board.dmboard.model.vo.DM" %>
<%@page import="java.util.List"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />

<%
List<DM> list = (List<DM>)request.getAttribute("list");
String pageBar = (String)request.getAttribute("pageBar");

%>

<script>
$(()=>{
	//테이블의 열을 클릭시 해당 게시물로 이동
	$("td").click((e)=>{		
		var dmNo = $(e.target).parents("tr").children("th").children("input").val();
		console.log(dmNo);
		
		var url = "<%=request.getContextPath()%>/board/dm/DMView?dmNo="+dmNo;
	    var title = "DMWrite";
	    var status =  "left=500px, top=200px, width=400px, height=500px";
	    
		var popup = window.open(url,title,status);
	
	});
});
			
</script>


<div id="img">
	<img id="sendDM_header" src="<%=request.getContextPath() %>/images/1.jpg" alt="헤더 - 수신쪽지 사진" />
</div>
<style>
	img#sendDM_header{
		width: 1024px;
		height: 300px;
	}
	#img{
		text-align: center;
	}
</style>



<input type="button" value="수신 쪽지함"
		onclick="DMRecive();"/>
<input type="button" value="발신 쪽지함"
		onclick="DMSend();"/>
<input type="button" value="신고 쪽지함"
		onclick="DMReport();"/>
		
		
		
	

	<%-- 함수를 console에 직접 쳐서 이동할 수 있으므로 그것을 방지하기 위해 if문 안에 script사용--%>
<section class="board-container">
	<table id="tbl-board" class="table table-hover">
		<tr>
			<th scope="col">보낸사람</th>
			<th scope="col">쪽지제목</th>
			<th scope="col">날짜</th>
		</tr>
		<%if(list==null || list.isEmpty()){ %>
		<tr>
			<td colspan="3" align="center">쪽지가 없습니다.</td>
		</tr>
		<%}
		else{
			for(DM d: list){%>		
		<tr>
			
			<th scope="row">
			<%=d.getDmSend() %>
			<input type="hidden" name="dmNo" value="<%=d.getDmNo() %>" />			
			</th>
			<td>
			<%=d.getDmTitle() %>
			</td>
			<td><%=d.getDmDate() %></td>
<<<<<<< HEAD
			
			
			
=======
>>>>>>> branch 'master' of https://github.com/heesuKwon/pinkpaw.git
		</tr>
		<%} 
		}%>
	</table>
	<div id="pageBar">
		<%=pageBar %>
	</div>
</section>

<<<<<<< HEAD

<input type="button" value="쪽지쓰기"
		onclick="DMWrite();"/>

=======
>>>>>>> branch 'master' of https://github.com/heesuKwon/pinkpaw.git
<script>
//쪽지쓰기
function DMWrite(){
	var url = "<%=request.getContextPath()%>/dmWrite?memberId=<%=memberLoggedIn.getMemberId()%>";
    var title = "DMWrite";
    var status =  "left=500px, top=200px, width=400px, height=500px";
    
	var popup = window.open(url,title,status);
}

//쪽지읽기
<%-- function DMView(){
	var url = "<%=request.getContextPath()%>/board/dm/dmView?dmNo=<%=b.getMissingNo()%>";
    var title = "DMView";
    var status =  "left=500px, top=200px, width=400px, height=500px";
    
	var popup = window.open(url,title,status);
	
}
 --%>
// /jquery/json/member/insert.do
//객체단위로 요청파라미터에 추가할 것.
</script>





<%@ include file="/WEB-INF/views/common/footer.jsp"%>
