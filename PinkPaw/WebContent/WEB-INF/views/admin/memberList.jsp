<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Member> list = (List<Member>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
	int numPerPage = (int)request.getAttribute("numPerPage");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css" />
<link href="<%=request.getContextPath()%>/css/write.css" rel="stylesheet" />

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/w3.css">
<link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/header.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/slick.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/view.css">
	<link href="<%=request.getContextPath()%>/css/write.css" rel="stylesheet" />

<style>
div#search-container{
	margin: 0 0 10px;
	padding: 3px;
}
div#search-memberId{display: inline-block;}
div#search-memberName{display: none;}
</style>
<script>
$(()=>{
	$("#searchType").on("change", (e)=>{
		var type = $(e.target).val();
		
		$(".searchFrm").hide();
		$("#search-"+type).css('display','inline-block');
		
	});
	
	$("#numPerPage").on("change",()=>{
		$("#numPerPageFrm").submit();
	});
	
	
	$("td").click((e)=>{		
		var member = $(e.target).parents("tr").children("td").eq(0).text();
		
		location.href = "<%=request.getContextPath()%>/member/memberView?memberId="+member;
	});
	
});




</script>

<div id="img-div">

	<img id="header-img" src="<%=request.getContextPath() %>/images/bg3.jpg" alt="헤더 - 후기게시판 사진" />
	<div id="blackbg"></div>
	<span class="header-title">회원리스트</span>
</div>

<style>
	img#reportBoard_header{
		width: 1024px;
		height: 300px;
	}
	#img{
		text-align: center;
	}
</style>

<section id="memberList-container"  style="position: relative;
    padding-top: 170px;
    margin: 0 auto;
    text-align: center;
    padding-bottom: 100px;">
    
    
    
	<h2 style="margin: 15px; text-align: center; font-size: 30px; font-weight: bold; color: white;"> </h2>
	
	
<br >
<br >
	
	<div id="head-wrapper">
		<div id="search-container">
		
			<div id="search-form" style="margin-left: 70%; margin-top: 55px;">
			<select id="searchType" style="margin: 30px;">
				<option value="memberId">아이디</option>
				<option value="memberName">회원명</option>
			</select>
			<div id="search-memberId" class="searchFrm">
				<form action="<%=request.getContextPath()%>/admin/memberFinder">
					<input type="hidden" 
						   name="searchType" 
						   value="memberId" />
					<input type="search"
						   name="searchKeyword"
						   size="25"
						   placeholder="검색할 아이디를 입력하세요." />
					<input type="submit" 
						   value="검색" 
						   class="btn btn-pink" 
						   style="height: 35px;"/>
				</form>
				</div>
				
			
			<div id="search-memberName" class="searchFrm">
				<form action="<%=request.getContextPath()%>/admin/memberFinder">
					<input type="hidden" 
						   name="searchType" 
						   value="memberName" />
					<input type="search"
						   name="searchKeyword"
						   size="25"
						   placeholder="검색할 회원명을 입력하세요." />
					<input type="submit" 
						   class="btn btn-pink" 
						   style="height: 35px;"	
						   value="검색" />
				</form>
			</div>
		</div>
		</div>
		<!-- end of div#search-container -->
		<div id="numPerPage-container" style="margin: 15px; float: left;">
			<form name="numPerPageFrm"
				  id="numPerPageFrm">
				<select name="numPerPage" 
						id="numPerPage">
					<option value="5" <%=numPerPage==5?"selected":"" %>>5개씩</option>
					<option value="10" <%=numPerPage==10?"selected":"" %>>10개씩</option>
					<option value="20" <%=numPerPage==20?"selected":"" %>>20개씩</option>
				</select>				  
			</form>
		</div>
	
	</div>
	<!-- end of div#head-wrapper -->
	
	<table class="table table-gray table-hover">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>주소</th>
				<th>가입일</th>
				<th>경고</th>
				<th>등급</th>
			</tr>
		</thead>
		<tbody>
	
		<% if(list==null || list.isEmpty()){ %>
            <tr>
                <td colspan="9" align="center"> 검색 결과가 없습니다. </td>
            </tr>
        <% 
            } 
            else {
                for(Member m : list){ 
        %>
            <tr>
                <td id="memberId"><%=m.getMemberId()%></td>
                <td><%=m.getMemberName()%></td>
                <td><%=m.getEmail()%></td>
                <td><%=m.getPhone()%></td>
                <td><%=m.getAddress()%></td>
                <td><%=m.getEnrolldate()%></td>		
                <td><%=m.getReportCount()%></td>		
                <td><%=m.getGrade()%></td>		
            </tr>		
        <%		} 
            }
        %>
		
		</tbody>
	</table>
	<div id="pageBar">
		<%=pageBar %>
	</div>

</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>