<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.pinkpaw.board.dmboard.model.vo.DM" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css" />
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
<link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/write.css" />  
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/view.css">

<meta charset="UTF-8">
<title>쪽지</title>
<%
	DM dm = (DM)request.getAttribute("dm");

%>
<!-- 수신함일때  -->
</head>
<body>

	
	<table class="tg" style="table-layout: fixed;   width: 400px;">
		<tr>
			<th class="tg-th">보낸사람</th>
			<th class="tg" colspan="3"><%=dm.getDmSend() %></th>
		</tr>
		<tr>
			<td class="tg-th">보낸날짜</td>
			<td class="tg" colspan="3"><%=dm.getDmDate()%></td>
		</tr>
		<tr>
			<td class="tg-th">쪽지제목</td>
			<td class="tg-ml2k"><%=dm.getDmTitle()%></td>
		</tr>
		
		<tr>
			<td class="tg-th">내용</td>
			<td class="tg-kw6a" colspan="3"><textarea name="" id="" cols="30" rows="10" readonly><%=dm.getDmContent() %></textarea></td>
		</tr>
		<tr>
			<td>
		</td>
		</tr>
	</table>
	<div style='left:50px; text-align: center;'>
	
		<input type="button" class="btn btn-pink" style='display: inline-block;' value="닫기" onclick="self.close();" />
		<input type="button" class="btn btn-pink"  style='display: inline-block;' value="삭제" onclick="dmDelete();" />
		<input type="button" class="btn btn-gray" style='display: inline-block;' value="신고 " onclick="dmReport();" />
	
	</div>
	
<script>

$(()=>{
	
	var close = <%=request.getParameter("close")%>;
	if(close==true){
		self.close();
	}
})();




function dmReport(){
	
	
	location.href = "<%=request.getContextPath()%>/board/dm/dmReport?dmNo=<%=dm.getDmNo()%>"; 
}
function dmDelete(){
	if(!confirm("정말 삭제하시겠습니까?")){
		return;
	}		
	location.href = "<%=request.getContextPath()%>/board/dm/dmDelete?dmNo=<%=dm.getDmNo()%>&dmReceive=<%=dm.getDmRecive()%>"; 
}


</script>


</body>
</html>