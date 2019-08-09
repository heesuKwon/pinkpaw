<!-- 유기동물 공고 리스트 -->
<%@page import="com.pinkpaw.animal.model.vo.ProtectedAnimal"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<ProtectedAnimal> list = (List<ProtectedAnimal>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<style>
#PA{width: 1000px; height: 900px; margin-top: 100px; margin-left: 50px;}
</style>
<div id="PA">
	<table id="tbl-board" class="table table-hover">
	<%for(ProtectedAnimal p : list) {%>
	<tr style="display: inline-block; padding: 15px">
		<td class="card" style="width: 300px; display: inline-block;" >
  				<img src="<%=p.getPopfile() %>" class="card-img-top" alt="..." style="height: 100px">
  				<div class="card-body">
    				<h5 class="card-title" style="width: 110%;">공고 번호 : <%=p.getNoticeNo() %></h5>
    				<p class="card-text">접수일 : <%=p.getHappenDt() %></p>
    				<p class="card-text">품종 : <%=p.getKindCd() %></p>
    				<p class="card-text">상태 : <%=p.getProcessState() %></p>
				</div>
		</td>
	</tr>
	<%} %>
	</table>
	<div id="pageBar">
		<%=pageBar %>
	</div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>