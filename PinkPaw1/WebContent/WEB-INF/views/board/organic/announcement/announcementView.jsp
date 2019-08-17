<!-- 유기동물 공고 상세보기 -->
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.pinkpaw.animal.model.vo.ProtectedAnimal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ProtectedAnimal p = (ProtectedAnimal)request.getAttribute("Animal");

	SimpleDateFormat format1  = new SimpleDateFormat("yyyy년 MM월 dd일");
	SimpleDateFormat format2  = new SimpleDateFormat("yyyyMMdd");
	SimpleDateFormat format3  = new SimpleDateFormat("yyyy-MM-dd");
	
	Date d1 = format2.parse(p.getNoticeSdt());
	Date d2 = format2.parse(p.getNoticeEdt());

	Calendar cal = Calendar.getInstance();
	String date1 = format1.format(cal.getTime());
	String d_1 = format3.format(d1);
	String d_2 = format3.format(d2);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<!-- bootstrap -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" 
integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" 
crossorigin="anonymous"></script>
<link href="<%=request.getContextPath()%>/css/animal.css" rel="stylesheet" />
</head>
<body>
	<div class="article">
<article class="sub">
<p class="txt1">「동물보호법」 제17조, 같은 법 시행령 제7조 및 같은 법 
시행규칙 제20조에 따라 구조된 동물의 보호상황을 
아래와 같이 공고합니다. </p>
<div class="ani_photo">
<img src="<%=p.getPopfile() %>" class="photoimg" alt="">
</div>
<div class="roundbox">
	<table>
	<colgroup>
	 <col width="40%">
	 <col width="60%">
	</colgroup>
		<tr>
			<th>공고번호</th>
			<td class="lft"><%=p.getNoticeNo() %></td>
		</tr>
		<tr>
			<th>품 종</th>
			<td class="lft"><%=p.getKindCd() %></td>
		</tr>
		<tr>
			<th>색 상</th>
			<td class="lft"><%=p.getColorCd() %></td>
		</tr>
		<tr>
			<th>성 별</th>
			<td class="lft"><%=p.getSexCd() %></td>
		</tr>
		<tr>
			<th>나이/체중</th>
			<td class="lft"><%=p.getAge() %> / <%=p.getWeight() %> </td>
		</tr>
		<tr>
			<th>접수일시</th>
			<td class="lft"><%=p.getHappenDt() %></td>
		</tr>
		<tr>
			<th>발생장소</th>
			<td class="lft"><%=p.getHappenPlace() %></td>
		</tr>
		<tr>
			<th>특 징</th>
			<td class="lft"><%=p.getSpecialMark() %></td>
		</tr>
		<tr>
			<th class="last">공고기한</th>
			<td class="last lft"><%=d_1 %> ~ <%=d_2 %></td>
		</tr>                              
	</table>
</div>
<p class="txt1">유기동물문의는 보호소에 연락하시기 바랍니다. </p>
<div class="roundbox">
	<table>
	<colgroup>
	 <col width="40%">
	 <col width="60%">
	</colgroup>
		<tr>
			<th>보호소이름</th>
			<td class="lft"><%=p.getCareNm() %></td>
		</tr>
		<tr>
			<th>보호장소</th>
			<td class="lft"><%=p.getCareAddr() %></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td class="lft"><%=p.getCareTel() %></td>
		</tr>
		<tr>
			<th>관할기관</th>
			<td class="lft"><%=p.getOrgNm() %></td>
		</tr>
		<tr>
			<th>담당자</th>
			<td class="lft"><%=p.getChargeNm() %></td>
		</tr>
		<tr>
			<th>연락처</th>
			<td class="lft"><%=p.getOfficetel() %></td>
		</tr>		
		<tr>
			<th class="last">특이사항</th>
			<td class="last lft"> </td>
		</tr>                              
	</table>
</div>
<p class="txt_ge">상기 동물을 분실하신 소유주께서는 보호소로 문의
하시어 동물을 찾아가시기 바라며, 동물보호 법 
제17조의 규정에 따른 공고가 있는 날부터 10일이 
경과하여도 소유자 등을 알 수 없는 경우에는 유실물법 
제12조 및 민법 제253조의 규정에 불구하고 해당 
시,군,구자치구가 그 동물의 소유권을 취득하게 됩니다.</p>
<p class="txt_end"><%=date1 %></p>
</article>
</div>

</body>
</html>