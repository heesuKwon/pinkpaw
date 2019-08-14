<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.pinkpaw.board.dmboard.model.vo.DM" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쪽지</title>
<%
	DM dm = (DM)request.getAttribute("dm");

%>

</head>
<body>
	<table>
	<tr>
		<th>보낸사람</th>
		<td><%=dm.getDmSend() %></td>
	</tr>
	<tr>
		<th>보낸날짜</th>
		<td><%=dm.getDmDate() %></td>
	</tr>
	<tr>
		<th>쪽지제목</th>
		<td><%=dm.getDmContent() %>
		</td>
	</tr>
	<tr>
		<th>쪽지내용</th>
		
		<td><textarea name="" id="" cols="30" rows="10" readonly><%=dm.getDmTitle() %></textarea></td>
	</tr>	
	<tr>
		<td>
		<input type="button" value="닫기" onclick="self.close();" />
		<input type="button" value="삭제" onclick="dmDelete();" />
		<input type="button" value="신고 " onclick="dmReport();" />
		</td>
	</tr>
	
	</table>
<script>
function dmReport(){
	
	
	location.href = "<%=request.getContextPath()%>/board/dm/dmReport?dmNo=<%=dm.getDmNo()%>"; 
}

</script>


</body>
</html>