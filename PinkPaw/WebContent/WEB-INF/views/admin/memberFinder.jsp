<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Member> list = (List<Member>)request.getAttribute("list");
	
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");

	String pageBar = (String)request.getAttribute("pageBar");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css" />
<style>
div#search-container{
	margin: 0 0 10px;
	padding: 3px;
}
div#search-memberId{display: <%="memberId".equals(searchType)?"inline-block":"none"%>;}
div#search-memberName{display: <%="memberName".equals(searchType)?"inline-block":"none"%>;}
div#search-gender{display: <%="gender".equals(searchType)?"inline-block":"none"%>;}
</style>
<script>
$(()=>{
	$("#searchType").on("change", (e)=>{
		var type = $(e.target).val();
		
		$(".searchFrm").hide();
		$("#search-"+type).css('display','inline-block');
		
	});
	
	$("td").click((e)=>{		
		var member = $("#memberId").text();

		location.href = "<%=request.getContextPath()%>/member/memberView?memberId="+member;
	});
	
	
});
</script>

<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />

<section id="memberList-container">
	<h2 style="margin: 15px; text-align: center; font-size: 30px; font-weight: bold; color: #c54b54;">회원관리</h2>
	<br /><br />
	
	<div id="search-container">
		
		<div id="search-form" style="margin-left: 70%;">
			<select id="searchType" style="margin: 30px;">
			<option value="memberId" <%="memberId".equals(searchType)?"selected":"" %>>아이디</option>
			<option value="memberName" <%="memberName".equals(searchType)?"selected":"" %>>회원명</option>
		</select>	
		<div id="search-memberId" class="searchFrm">
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden" 
					   name="searchType" 
					   value="memberId" />
				<input type="search"
					   name="searchKeyword"
					   size="25"
					   value="<%="memberId".equals(searchType)?searchKeyword:"" %>"
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
					   value="<%="memberName".equals(searchType)?searchKeyword:"" %>"
					   placeholder="검색할 회원명을 입력하세요." />
				<input type="submit" 
					   value="검색" 
					   class="btn btn-pink" 
					   style="height: 35px;"
					   />
			</form>
		</div>
	</div>
	
	</div>
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
		<%=pageBar%>
	</div>

</section>



<%@ include file="/WEB-INF/views/common/footer.jsp" %>