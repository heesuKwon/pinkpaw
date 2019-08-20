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

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css" />
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/w3.css">
 <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
<link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap" rel="stylesheet">

<style>
#btn-add{
display: block
}
table#parcel-board{width:100%; margin:0 auto; border:0px solid black; border-collapse:collapse; clear:both; border-radius: 5px; }
table#parcel-board th, table#tbl-board td {border:0px solid; padding: 5px 0; text-align:center;} 
.card:hover{
box-shadow: 10px 10px 8px rgba(135, 139, 133, 0.4); 
cursor: pointer;
}
div#pageBar{margin-top:10px; text-align:center;}
div#pageBar span.cPage{color: gray; margin-right: 5px;}
div#pageBar a{margin-right: 5px;}

.input-group mb-3{ 
width: 600px;  
text-align: center; 
</style>

<script>
function goBoardForm(){
	location.href 
		= "<%=request.getContextPath()%>/board/parcelout/parceloutWrite";	
}
</script>

<div id="img-div">
	<img id="header-img" src="<%=request.getContextPath() %>/images/board/11.jpg" alt="헤더 - 후기게시판 사진" />
	<div id="blackbg"></div>
	<span class="header-title">분양동물 게시판</span>
</div>

<section class="card-container">
<table id="parcel-board" style="border:0px; border-collapse: collapse;">
<%if(memberLoggedIn!=null){ %>
<input type="button" value="글쓰기" id="btn-add" onclick="goBoardForm();" class="btn btn-pink" />
<%} %>

<% for(ParceloutBoard p : list){ %>
	<div class="card" style="width: 18rem; height: 310px; display: inline-block; margin-top:20px; margin-left: 20px;">
	<a style="text-decoration: none; color: gray" href="<%=request.getContextPath()%>/board/parcelout/parceloutView?parceloutNo=<%=p.getParceloutNo()%>">
 	 <% if(p.getParceloutOriginalImg() != null){
				String[] renamedImgList = p.getParceloutRenamedImg().split("§");%>
		<img class="d-block w-100" src="<%=request.getContextPath()%>/upload/board/parcelout/<%=renamedImgList[0]%>" alt="첨부파일"  style='width:200px; height: 150px;' />	
	<% }
		else { %>
  <img src="<%=request.getContextPath()%>/images/bg.png" class="card-img-top" style="height: 150px;" >
 	<%} %>
  <div class="card-body">
    <p class="card-title">
    <span style="font-size: 12px;">#<% if(p.getParceloutKind() != null){
	String[] arr = p.getParceloutKind().split("_");%>
	<%=arr.length == 1?p.getParceloutKind():arr[1]%>
	<%} %>
	</span>
    <br />
    <span style="font-weight:bold; font-size: 18px; color: dark-gray;"><%= p.getParceloutTitle()%></span>
    <br />
    <span style="font-size: 13px;"><%=p.getParceloutPlace()%></span>
    </p>
    <p style="font-size: 11px; color: gray; text-align: right;"><%=p.getParceloutEnrolldate() %>
    <br />
    <%=p.getParceloutWriter() %>
    </p>
  </div>
  </a>
</div>
		<% } %>
	</table>
	<div id='pageBar'>
		<%=pageBar %>
	</div>
</section>	
<%@ include file="/WEB-INF/views/common/footer.jsp"%>