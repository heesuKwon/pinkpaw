<%@page import="com.pinkpaw.board.parceloutboard.model.vo.ParceloutBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	List<ParceloutBoard> list = (List<ParceloutBoard>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<style>
table#parcel-board{width:100%; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; border-radius: 5px; }
table#parcel-board th, table#tbl-board td {border:1px solid; padding: 5px 0; text-align:center;} 
.card:hover{
box-shadow: 10px 10px 8px rgba(135, 139, 133, 0.4); 
cursor: pointer;
}
</style>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script>
function goBoardForm(){
	location.href 
		= "<%=request.getContextPath()%>/board/community/parcelout/parceloutWrite";	
}
</script>
</head>
<body>

<section id="board-container">
<table id="parcel-board" style="border:0px; border-collapse: collapse;">
<tr><td >
<input type="button" value="글쓰기" id="btn-add" onclick="goBoardForm();" />
</th></td>
<tr>
<td>
<% for(ParceloutBoard p : list){ %>
	<div class="card" style="width: 18rem; height: 310px; display: inline-block; padding:20px, 20px;">
	<a style="text-decoration: none; color: gray" href="<%=request.getContextPath()%>/board/community/parcelout/parceloutView?parceloutNo=<%=p.getParceloutNo()%>"></a>
 	 <% if(p.getParceloutOriginalImg() != null){
				String[] renamedImgList = p.getParceloutRenamedImg().split("§");%>
		<img class="d-block w-100" src="<%=request.getContextPath()%>/upload/board/parcelout/<%=renamedImgList[0]%>" alt="첨부파일"  style='width:200px; height: 150px;' />	
	<% }
		else { %>
  <img src="<%=request.getContextPath()%>/images/bg.png" class="card-img-top" style="height: 150px;" >
 	<%} %>
  <div class="card-body">
    <p class="card-title"><%= p.getParceloutTitle()%>
    <br />
    #<%=p.getParceloutPlace()%>
    <br />
    #<%=p.getParceloutKind()%>
    </p>
    <p style="font-size: 11px; color: gray; text-align: right;"><%=p.getParceloutEnrolldate() %></p>
  </div>
</div>
		<% } %>
</td>
		</tr>		
	</table>
	<div id='pageBar'>
		<%=pageBar %>
	</div>
</section>	
<%@ include file="/WEB-INF/views/common/footer.jsp"%>