<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.pinkpaw.board.dmboard.model.vo.DM" %>
<%@page import="com.pinkpaw.member.model.vo.Member"%>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
	DM dm = (DM)request.getAttribute("dm");
	Member memberLoggedIn = new Member();
	memberLoggedIn.setMemberId("admin");


%>
<title>Insert title here</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/board/dm/dmReportEnd" method="post">
	<table>
	<tr>
		<th>신고자</th>
		<td>
		<input type="text" name="reportWriter" value="<%=memberLoggedIn.getMemberId() %>" />
		</td>
	</tr>
	<tr>
		<th>신고사유</th>
		<td>
		<textarea name="dmReportContent" id="" cols="30" rows="10">
		</textarea>
		</td>
	</tr>
	<tr>
		<td>
		<input type="hidden" name="dmNo" value="<%=dm.getDmNo() %>" />
		<input type="submit" value="신고 " onclick="report();" />
		<input type="button" value="닫기" onclick="self.close();" />
		</td>
	</tr>
	
	</table>
	</form>

</body>
</html>