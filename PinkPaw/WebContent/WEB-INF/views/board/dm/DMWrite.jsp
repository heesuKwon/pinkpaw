<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.pinkpaw.member.model.vo.Member" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="<%= request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<%
Member memberLoggedIn = 
		(Member)session.getAttribute("memberLoggedIn");
// new Member();
// memberLoggedIn.setMemberId("admin");
String memberId = (String)request.getAttribute("memberId");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/write.css" />
	
<title>쪽지 작성하기</title>
<script>
function validate(){
	var content = $("[name=dmContent]").val();
	if(content.trim().length == 0){
		alert("내용을 입력하세요.");
		return false;
	}
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
				var bool = data;
				if(bool =="false"){
					alert("해당회원 아이디가 없습니다.");
					
				}
				else if(bool =="true"){
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
	<div class="dm-container">
		
			<table class="table table-bordered" style="width:500px;">
				<tr>
					<th>보낼 사람</th>
					<td>
					
					<input type="text" name="dmSender" id="dmSender" class="form-control writer"
						required>
					<input type="hidden" name="dmWriter" id="dmWriter" value="<%=memberLoggedIn.getMemberId() %>" />
					</td>
				</tr>
				<tr>
					<th>쪽지 제목 </th>
					<td><input type="text" name="dmTitle" id="dmTitle" class="form-control title" required>
					</td>
				</tr>
				<tr>
					<th>쪽지 내용</th>
					<td><textarea name="dmContent" cols="40" rows="5" class="form-control"
							placeholder="내용을 입력해주세요."></textarea></td>
				</tr>
			</table>
			<div style="width:500px; text-align:center;">
					<input type="submit" value="보내기" class="btn btn-lightpink"
						onclick="return validate();" />&nbsp; 
						<input type="button" class="btn btn-gray"
						onclick="self.close();" value="닫기" />
			</div>
	</div>
	

</body>
</html>