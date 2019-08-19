<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String memberId = request.getParameter("memberId");
	boolean isUsable = (boolean)request.getAttribute("isUsable");
	
	System.out.println("!!!!!!!!!!!!!!!!!!!"+memberId);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 검사</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<style>
#checkId-container{
	text-align: center;
	padding-top: 50px;
}
span#duplicated{
	color: red;
	font-weight: bold;
}
</style>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>


</head>
<body>
	<div id="checkId-container">
	<% if(isUsable == true){ %>	
		[<span><%=memberId %></span>]는 사용가능합니다.
		<br /><br />
		<button type="button" onclick="setMemberId();">닫기</button>
	
	<% } else { %>
		[<span id="duplicated"><%=memberId %></span>]는 
		이미 사용중입니다.
		<br /><br />
		<!-- 아이디중복체크폼 -->
		<form action="<%=request.getContextPath()%>/member/checkIdDuplicate"
			  name="checkIdDuplicateFrm"
			  method="post">
			<input type="text" name="memberId"
				   id="memberId"
				   placeholder="아이디를 입력하세요." />
			<button type="button" 
					class="btn btn-secondary"
					style="background-color: #c54b54; height: 35px;"
				    onclick="checkIdDuplicate();">
				중복검사
			</button>
		</form>
	
	<% } %>
	</div>
<script>
function checkIdDuplicate(){
	var memberId = $("#memberId").val().trim();
	
	if(memberId.length < 4){
		alert("아이디는 4글자 이상 입력하세요.");
		return;
	}
	
	var frm = document.checkIdDuplicateFrm;
	frm.memberId.value = memberId;
	frm.submit();
	
}

function setMemberId(){
	//opener: 팝업창을 연 부모창의 window객체
	var frm = opener.document.memberEnrollFrm;

	frm.memberId.value = '<%=memberId%>';
	frm.idValid.value = 0;
	frm.password.focus();
	self.close();
}
</script>
</body>
</html>





