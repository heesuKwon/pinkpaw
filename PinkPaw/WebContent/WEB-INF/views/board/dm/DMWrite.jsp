<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.pinkpaw.member.model.vo.Member" %>
<!DOCTYPE html>
<html>
<%
Member memberLoggedIn = new Member();
memberLoggedIn.setMemberId("admin");

String memberId = (String)request.getAttribute("memberId");

%>
<head>

<meta charset="UTF-8">
<script src="<%= request.getContextPath()%>/js/jquery-3.4.1.js"></script>

<script>
function validate(){
	var content = $("[name=dmContent]").val();
	var title = $("[name=dmTitle]").val();
	var dmWriter = $("[name=dmWriter]").val();
	var dmSender = $("[name=dmSender]").val();
	if(content.trim().length == 0 || title.trim().length == 0){
		alert("제목과 내용을 입력하세요.");
		return false;
	}
	
	var dm = {
			type: "dm",
			msg: content,
			sender: dmWriter,
			title: title,
			/* receiver: $("#dm-client option:selected").val(), */
			receiver: dmSender
		}

		var param = {
				dm: JSON.stringify(dm)
		}
		
		$.ajax({
			url:"<%=request.getContextPath()%>/DMWriteEnd",
			data: param,
			dataType: "json",
			success: data => {
				var html = "";
				
				if(data == false){
					alert("해당회원 아이디가 없습니다.");
				}
				else{
					alert("메세지를 보냈습니다.")
							self.close();
				}
			
			}, 
			error: (jqxhr, textStatus, err)=>{
				console.log("ajax처리실패!");
				console.log(jqxhr, textStatus, err);
			}
			
		});
	return true;
}

</script>

</head>
<body>
	<div id="reportMissing-container">
		
			<table>
				<tr>
					<th>보낼 사람</th>
					<td>
					
					<input type="text" name="dmSender" id="dmSender"
						required>
					<input type="hidden" name="dmWriter" id="dmWriter" value="<%=memberLoggedIn.getMemberId() %>" />
					</td>
				</tr>
				<tr>
					<th>쪽지 제목 </th>
					<td><input type="text" name="dmTitle" id="dmTitle"
						value=""  required>
					</td>
				</tr>
				<tr>
					<th>쪽지 내용</th>
					<td><textarea name="dmContent" cols="40" rows="5"
							placeholder="내용을 입력해주세요."></textarea></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" value="보내기"
						onclick="return validate();" />&nbsp; <input type="button"
						onclick="self.close();" value="닫기" /></td>
				</tr>
			</table>
		
	</div>
	<script>
function reportValidate() {
	var content = $("[name=reportContent]").val();
	//좌우 공백을 제거하고 길이가 0이면(내용이 비어 있는 경우)
	if(content.trim().length == 0){
		alert("내용을 입력하세요.");
		return false;
	}

	
	return true;
}



</script>


</body>
</html>