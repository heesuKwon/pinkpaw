<%@page import="java.util.ArrayList"%>
<%@page import="com.pinkpaw.board.shelter.model.vo.Shelter"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Shelter> list = (ArrayList)request.getAttribute("list");
	if(list != null)
	System.out.println(list.size());
	int num = 0;
%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<script src="<%= request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<style>
#shelter{width: 900px; height: 760px; margin-top: 100px; margin-left: 100px}
/* table{
	width: 900px;
	margin: 0 auto;
	border: 1px solid white;
	border-collapse: collapse;
	position: absolute;
	top: 100px;
	left: 60px;
}
table td {
	border: 1px solid;
	padding: 5px 0 5px 15px;
	text-align: center;
} */
table td.shelname{width: 330px;}
.more{
	display: none;
}
</style>
<script>
$('document').ready(function() {
   var area0 = ["시/도 선택","서울특별시","인천광역시","대전광역시","광주광역시","대구광역시","울산광역시","부산광역시","경기도","강원도","충청북도","충청남도","전라북도","전라남도","경상북도","경상남도","제주도","세종특별자치시"];
   var area1 = ["강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중랑구"];
   var area2 = ["계양구","남동구","동구","미추홀구","부평구","서구","연수구","중구","강화군","옹진군"];
   var area3 = ["대덕구","동구","서구","유성구","중구"];
   var area4 = ["광산구","남구","동구","북구","서구"];
   var area5 = ["남구","달서구","동구","북구","서구","수성구","중구","달성군"];
   var area6 = ["남구","동구","북구","중구","울주군"];
   var area7 = ["강서구","금정구","남구","동구","동래구","부산진구","북구","사상구","사하구","서구","수영구","연제구","영도구","중구","해운대구","기장군"];
   var area8 = ["고양시","과천시","광명시","광주시","구리시","군포시","김포시","남양주시","동두천시","부천시","성남시","수원시","시흥시","안산시","안성시","안양시","양주시","여주시","오산시","용인시","의왕시","의정부시","이천시","파주시","평택시","포천시","하남시","화성시","가평군","양평군","연천군"];
   var area9 = ["강릉시","동해시","삼척시","속초시","원주시","춘천시","태백시","고성군","양구군","양양군","영월군","인제군","정선군","철원군","평창군","홍천군","화천군","횡성군"];
   var area10 = ["제천시","청주시","충주시","괴산군","단양군","보은군","영동군","옥천군","음성군","증평군","진천군"];
   var area11 = ["계룡시","공주시","논산시","당진시","보령시","서산시","아산시","천안시","금산군","부여군","서천군","연기군","예산군","청양군","태안군","홍성군"];
   var area12 = ["군산시","김제시","남원시","익산시","전주시","정읍시","고창군","무주군","부안군","순창군","완주군","임실군","장수군","진안군"];
   var area13 = ["광양시","나주시","목포시","순천시","여수시","강진군","고흥군","곡성군","구례군","담양군","무안군","보성군","신안군","영광군","영암군","완도군","장성군","장흥군","진도군","함평군","해남군","화순군"];
   var area14 = ["경산시","경주시","구미시","김천시","문경시","상주시","안동시","영주시","영천시","포항시","고령군","군위군","봉화군","성주군","영덕군","영양군","예천군","울릉군","울진군","의성군","청도군","청송군","칠곡군"];
   var area15 = ["거제시","김해시","밀양시","사천시","양산시","진주시","창원시","통영시","거창군","고성군","남해군","산청군","의령군","창녕군","하동군","함안군","함양군","합천군"];
   var area16 = ["서귀포시","제주시"];
   var area16 = [""];

 // 시/도 선택 박스 초기화

 $("select[name^=sido]").each(function() {
  $selsido = $(this);
  $.each(eval(area0), function() {
   $selsido.append("<option value='"+this+"'>"+this+"</option>");
  });
  $selsido.next().append("<option value=''>구/군 선택</option>");
 });

 // 시/도 선택시 구/군 설정

 $("select[name^=sido]").change(function() {
  var area = "area"+$("option",$(this)).index($("option:selected",$(this))); // 선택지역의 구군 Array
  var $gugun = $(this).next(); // 선택영역 군구 객체
  $("option",$gugun).remove(); // 구군 초기화

  if(area == "area0")
   $gugun.append("<option value=''>시/군/구 선택</option>");
  else {
   $.each(eval(area), function() {
    $gugun.append("<option value='"+this+"'>"+this+"</option>");
   });
  }
 });
});
function detailView(no) {
	console.log(no.trim());
	var place = no.split("@");
	html = "https://www.google.com/maps/place/";
	html += place[0] + "°";
	html += place[1] + "\'";
	html += place[2] + "\"N+";
	html += place[3] + "°";
	html += place[4]+ "\'";
	html += place[5]+ "\"E";
	
	open(html, "길찾기", "width=800px, height=600px, top=30px, left=30px");
}
</script>
<div id="shelter">
	<table id="tbl-board" class="table table-hover">
	<tr>
		<td colspan="3">
			<form action="<%=request.getContextPath() %>/shelter/shelterListByCity">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<select class="btn btn-outline-secondary dropdown-toggle" data-toggle="dropdown" style="border-radius: 0" name="sido" id="sido"></select>
			<select class="btn btn-outline-secondary dropdown-toggle" data-toggle="dropdown" style="border-radius: 0" name="gugun" id="gugun"></select>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input class="btn btn-outline-secondary" style="border-radius: 0" type="submit" value="검색하기"/>
			</form>
		</td>
	</tr>
	<tr>
		<th scope="col">보호소 이름</th>
		<th scope="col">위치</th>
		<th scope="col">전화번호</th>
	</tr>
	<%for(Shelter s : list) {
		if(num<10){%>
	<tr class="shel" onclick="detailView('<%=s.getPlace()%>');">
		<td class="shelname"><%=s.getShelterCenterName() %></td>
		<td><%=!s.getShelterRdnmAddress().equals("-")?s.getShelterRdnmAddress():s.getShelterInmAddress() %></td>
		<td><%=s.getShelterPhone() %></td>
	</tr>
	<%} else{%>
	<tr class="shel more" onclick="detailView('<%=s.getPlace()%>');">
		<td class="shelname"><%=s.getShelterCenterName() %></td>
		<td><%=!s.getShelterRdnmAddress().equals("-")?s.getShelterRdnmAddress():s.getShelterInmAddress() %></td>
		<td><%=s.getShelterPhone() %></td>
	</tr>
	<%} num++; }%>
	<%if(list.size() == 0) {%>
	<tr><td colspan="3">검색한 데이터가 없습니다.</td></tr>
	<%} %>
	<%if(num>10) {%>
	<tr>
		<td colspan="2" onclick="more();">더보기</td>
		<td onclick="byebye();">접기</td>
	</tr>
	<%} %>
	</table>
</div>
<script>
var i = 0;
function more() {
	var num = i;
	for(var a = num; a<num+10; a++){
		i = i + 1;
		$(".more").eq(a).css("display", "table-row");
	}
	var size = parseInt($("#shelter").css("height"))+400;
	$("#shelter").css("height", size+"px");
}
function byebye() {
	$(".more").css("display", "none");
	$("#shelter").css("height", "760px");
}
</script>
	​
<%@ include file="/WEB-INF/views/common/footer.jsp"%>