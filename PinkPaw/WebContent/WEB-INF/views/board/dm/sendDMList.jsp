<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ page import="com.pinkpaw.board.dmboard.model.vo.DM" %>
<%@page import="java.util.List"%>

<%
List<DM> list = (List<DM>)request.getAttribute("list");
String pageBar = (String)request.getAttribute("pageBar");

%>

<script>



$(()=>{
	//테이블의 열을 클릭시 해당 게시물로 이동
	$("td").click((e)=>{		
		var dmNo = $(e.target).parents("tr").children("td").children("input[name=dmNo]").val();
		console.log(dmNo);
		var dmRead = $(e.target).parents("tr").children("td").children("input[name=dmRead]").val();
		console.log(dmRead);
		var url = "<%=request.getContextPath()%>/board/dm/DMView?dmNo="+dmNo+"&dmRead="+dmRead;
		
	    var title = "DMWrite";
	    var status =  "left=500px, top=200px, width=400px, height=500px";
	    
		var popup = window.open(url,title,status);
	
	});
	
	
});
			
</script>
<style>
#mar{ margin-top: 100px;
}
#tbl-board{
	bacground: yellow;
}
</style>
<div id="mar">

<input type="button" value="쪽지쓰기"
		onclick="DMWrite();"/>

<input type="button" value="수신 쪽지함"
		onclick="DMSend();"/>
<input type="button" value="발신 쪽지함"
		onclick="DMRecieve();"/>

		
		
	
	<%-- 함수를 console에 직접 쳐서 이동할 수 있으므로 그것을 방지하기 위해 if문 안에 script사용--%>
	
	<table id="tbl-board" class="table table-hover">
		<tr>
			<th scope="col">보낸사람</th>
			<th scope="col">쪽지제목</th>
			<th scope="col">읽음여부</th>
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
			
			<td scope="row">
			<%=d.getDmSend() %>
			<input type="hidden" name="dmNo" value="<%=d.getDmNo() %>" />			
			<input type="hidden" name="dmRead" value="<%=d.getDmRecvRead() %>" />			
			</td>
			<td>
			<%=d.getDmTitle() %>
			</td>
			<td><%=d.getDmRecvRead()==0?"안읽음":"읽음" %></td>			
			<td><%=d.getDmDate() %></td>
			
			
		</tr>
		<%} 
		}%>
	</table>
	<div id="pageBar">
		<%=pageBar %>
	</div>
		


</div>
<script>
//쪽지쓰기

	
function DMWrite(){
	var url = "<%=request.getContextPath()%>/dmWrite?memberId=<%=memberLoggedIn.getMemberId()%>";
    var title = "DMWrite";
    var status =  "left=500px, top=200px, width=400px, height=500px";
    
	var popup = window.open(url,title,status);
}

//쪽지읽기
function DMSend(){
	location.href = "<%=request.getContextPath()%>/board/dm/dmList?memberId=<%=memberLoggedIn.getMemberId()%>";
}
function DMRecieve(){
	location.href = "<%=request.getContextPath()%>/board/dm/dmSendList?memberId=<%=memberLoggedIn.getMemberId()%>";
}

// /jquery/json/member/insert.do
//객체단위로 요청파라미터에 추가할 것.
</script>





<%@ include file="/WEB-INF/views/common/footer.jsp"%>
