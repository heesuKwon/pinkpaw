<!-- 유기동물 공고 리스트 -->
<%@page import="com.pinkpaw.animal.model.vo.ProtectedAnimal"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<%
	List<ProtectedAnimal> list = (List<ProtectedAnimal>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
%>

<div class="header_background"></div>

<style>
.header_background{
	height: 84px;
	background: black;
    opacity: 0.5;
}

#PA{width: 1000px; height: 1450px; margin-top: 100px; margin-left: 20px;}
.sub{display: none; border-radius: 0;}
</style>
<!-- <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
 -->
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/w3.css">
 
 <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
<script>
function srch(val) {
	var num = val.value;
	$(".sub").css("display", "none");
	if(num != ""){
		document.getElementById(num).style.display="initial";
	} else {
		document.getElementById("all").style.display="initial";
	}
	
	if(num == "5690000") {
		$(".sub").css("display", "none");
	} 
}
function srchList() {
	var anikind = $("#anikind").val();
	var sido = $("#sido").val();
	var sigungu = document.getElementById(sido).value;
	if(anikind == "" && sido == "all" && sigungu == ""){
		location.href = "<%=request.getContextPath()%>/animal/animalNotice";
	} else{
		location.href = "<%=request.getContextPath()%>/animal/animalNoticeSrch?anikind="+anikind+"&sido="+sido+"&sigungu="+sigungu;	
	}
}
</script>

<div id="PA">

	<div class="input-group mb-3" style="width: 800px">
		<div class="input-group-prepend">
    		<select class="btn btn-outline-secondary dropdown-toggle" data-toggle="dropdown" name="anikind" id="anikind" style="border-radius: 0">
				<option value="" selected hidden>동물종류</option>
				<option value="">전체</option>
				<option value="417000">개</option>
				<option value="422400">고양이</option>
				<option value="429900">기타</option>
			</select>
 		</div> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		<div class="input-group-prepend">
			<select class="btn btn-outline-secondary dropdown-toggle"
				data-toggle="dropdown" name="sido" id="sido" onchange="srch(this);">
				<option value="all" selected hidden>시/도</option>
				<option value="all">전체</option>
				<option value="6110000">서울특별시</option>
				<option value="6260000">부산광역시</option>
				<option value="6270000">대구광역시</option>
				<option value="6280000">인천광역시</option>
				<option value="6290000">광주광역시</option>
				<option value="6300000">대전광역시</option>
				<option value="6310000">울산광역시</option>
				<option value="5690000">세종특별자치시</option>
				<option value="6410000">경기도</option>
				<option value="6420000">강원도</option>
				<option value="6430000">충청북도</option>
				<option value="6440000">충청남도</option>
				<option value="6450000">전라북도</option>
				<option value="6460000">전라남도</option>
				<option value="6470000">경상북도</option>
				<option value="6480000">경상남도</option>
				<option value="6500000">제주특별자치도</option>
			</select>
		</div>
  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		<select class="btn btn-outline-secondary dropdown-toggle sub"
				data-toggle="dropdown" name="" id="all" style="display: initial;">
			<option value="" selected hidden>시/군/구</option>
		</select>
		<select name="6110000" id="6110000" class="btn btn-outline-secondary dropdown-toggle sub"
				data-toggle="dropdown">
			<option value="" selected>전체</option>
			<option value="6119999">가정보호</option>
			<option value="6119998">개별사업</option>
			<option value="3220000">강남구</option>
			<option value="3240000">강동구</option>
			<option value="3080000">강북구</option>
			<option value="3150000">강서구</option>
			<option value="3200000">관악구</option>
			<option value="3040000">광진구</option>
			<option value="3160000">구로구</option>
			<option value="3170000">금천구</option>
			<option value="3100000">노원구</option>
			<option value="3090000">도봉구</option>
			<option value="3050000">동대문구</option>
			<option value="3190000">동작구</option>
			<option value="3130000">마포구</option>
			<option value="3120000">서대문구</option>
			<option value="3210000">서초구</option>
			<option value="3030000">성동구</option>
			<option value="3070000">성북구</option>
			<option value="3230000">송파구</option>
			<option value="3140000">양천구</option>
			<option value="3180000">영등포구</option>
			<option value="3020000">용산구</option>
			<option value="3110000">은평구</option>
			<option value="3000000">종로구</option>
			<option value="3010000">중구</option>
			<option value="3060000">중랑구</option>
		</select> 
		<select name="6260000" id="6260000" class="btn btn-outline-secondary dropdown-toggle sub"
				data-toggle="dropdown">
			<option value="" selected>전체</option>
			<option value="3360000">강서구</option>
			<option value="3350000">금정구</option>
			<option value="3400000">기장군</option>
			<option value="3310000">남구</option>
			<option value="3270000">동구</option>
			<option value="3300000">동래구</option>
			<option value="3290000">부산진구</option>
			<option value="3320000">북구</option>
			<option value="3390000">사상구</option>
			<option value="3340000">사하구</option>
			<option value="3260000">서구</option>
			<option value="3380000">수영구</option>
			<option value="3370000">연제구</option>
			<option value="3280000">영도구</option>
			<option value="3250000">중구</option>
			<option value="3330000">해운대구</option>
		</select>
		<select name="6270000" id="6270000" class="btn btn-outline-secondary dropdown-toggle sub"
				data-toggle="dropdown">
			<option value="" selected>전체</option>
			<option value="3440000">남구</option>
			<option value="3470000">달서구</option>
			<option value="3480000">달성군</option>
			<option value="3420000">동구</option>
			<option value="3450000">북구</option>
			<option value="3430000">서구</option>
			<option value="3460000">수성구</option>
			<option value="3410000">중구</option>
		</select>
		<select name="6280000" id="6280000" class="btn btn-outline-secondary dropdown-toggle sub"
				data-toggle="dropdown">
			<option value="" selected>전체</option>
			<option value="3570000">강화군</option>
			<option value="3550000">계양구</option>
			<option value="3530000">남동구</option>
			<option value="3500000">동구</option>
			<option value="3510500">미추홀구</option>
			<option value="3540000">부평구</option>
			<option value="3560000">서구</option>
			<option value="3520000">연수구</option>
			<option value="3580000">옹진군</option>
			<option value="3490000">중구</option>
		</select>
		<select name="6290000" id="6290000" class="btn btn-outline-secondary dropdown-toggle sub"
				data-toggle="dropdown">
			<option value="" selected>전체</option>
			<option value="3630000">광산구</option>
			<option value="3610000">남구</option>
			<option value="3590000">동구</option>
			<option value="3620000">북구</option>
			<option value="3600000">서구</option>
		</select>
		<select name="6300000" id="6300000" class="btn btn-outline-secondary dropdown-toggle sub"
				data-toggle="dropdown">
			<option value="" selected>전체</option>
			<option value="3680000">대덕구</option>
			<option value="3640000">동구</option>
			<option value="3660000">서구</option>
			<option value="3670000">유성구</option>
			<option value="3650000">중구</option>
		</select>
		<select name="6310000" id="6310000" class="btn btn-outline-secondary dropdown-toggle sub"
				data-toggle="dropdown">
			<option value="" selected>전체</option>
			<option value="3700000">남구</option>
			<option value="3710000">동구</option>
			<option value="3720000">북구</option>
			<option value="3730000">울주군</option>
			<option value="3690000">중구</option>
		</select>
		<select name="5690000" id="5690000" class="btn btn-outline-secondary dropdown-toggle sub"
				data-toggle="dropdown">
		
		</select>
		<select name="6410000" id="6410000" class="btn btn-outline-secondary dropdown-toggle sub"
				data-toggle="dropdown">
			<option value="" selected>전체</option>
			<option value="4160000">가평군</option>
			<option value="3940000">고양시</option>
			<option value="3970000">과천시</option>
			<option value="3900000">광명시</option>
			<option value="5540000">광주시</option>
			<option value="3980000">구리시</option>
			<option value="4020000">군포시</option>
			<option value="4090000">김포시</option>
			<option value="3990000">남양주시</option>
			<option value="3920000">동두천시</option>
			<option value="3860000">부천시</option>
			<option value="3780000">성남시</option>
			<option value="3740000">수원시</option>
			<option value="4010000">시흥시</option>
			<option value="3930000">안산시</option>
			<option value="4080000">안성시</option>
			<option value="3830000">안양시</option>
			<option value="5590000">양주시</option>
			<option value="4170000">양평군</option>
			<option value="5700000">여주시</option>
			<option value="4140000">연천군</option>
			<option value="4000000">오산시</option>
			<option value="4050000">용인시</option>
			<option value="5630000">용인시 기흥구</option>
			<option value="4030000">의왕시</option>
			<option value="3820000">의정부시</option>
			<option value="4070000">이천시</option>
			<option value="4060000">파주시</option>
			<option value="3910000">평택시</option>
			<option value="5600000">포천시</option>
			<option value="4040000">하남시</option>
			<option value="5530000">화성시</option>
		</select>
		<select name="6420000" id="6420000" class="btn btn-outline-secondary dropdown-toggle sub"
				data-toggle="dropdown">
			<option value="" selected>전체</option>
			<option value="4200000">강릉시</option>
			<option value="4340000">고성군</option>
			<option value="4210000">동해시</option>
			<option value="4240000">삼척시</option>
			<option value="4230000">속초시</option>
			<option value="4320000">양구군</option>
			<option value="4350000">양양군</option>
			<option value="4270000">영월군</option>
			<option value="4190000">원주시</option>
			<option value="4330000">인제군</option>
			<option value="4290000">정선군</option>
			<option value="4300000">철원군</option>
			<option value="4180000">춘천시</option>
			<option value="4220000">태백시</option>
			<option value="4280000">평창군</option>
			<option value="4250000">홍천군</option>
			<option value="4310000">화천군</option>
			<option value="4260000">횡성군</option>
		</select>
		<select name="6430000" id="6430000" class="btn btn-outline-secondary dropdown-toggle sub"
				data-toggle="dropdown">
			<option value="" selected>전체</option>
			<option value="4460000">괴산군</option>
			<option value="4480000">단양군</option>
			<option value="4420000">보은군</option>
			<option value="4440000">영동군</option>
			<option value="4430000">옥천군</option>
			<option value="4470000">음성군</option>
			<option value="4400000">제천시</option>
			<option value="5570000">증평군</option>
			<option value="4450000">진천군</option>
			<option value="5710000">청주시</option>
			<option value="4390000">충주시</option>
		</select>
		<select name="6440000" id="6440000" class="btn btn-outline-secondary dropdown-toggle sub"
				data-toggle="dropdown">
			<option value="" selected>전체</option>
			<option value="5580000">계룡시</option>
			<option value="4500000">공주시</option>
			<option value="4550000">금산군</option>
			<option value="4540000">논산시</option>
			<option value="5680000">당진시</option>
			<option value="4510000">보령시</option>
			<option value="4570000">부여군</option>
			<option value="4530000">서산시</option>
			<option value="4580000">서천군</option>
			<option value="4520000">아산시</option>
			<option value="4560000">연기군</option>
			<option value="4610000">예산군</option>
			<option value="4490000">천안시</option>
			<option value="4590000">청양군</option>
			<option value="4620000">태안군</option>
			<option value="4600000">홍성군</option>
		</select>
		<select name="6450000" id="6450000" class="btn btn-outline-secondary dropdown-toggle sub"
				data-toggle="dropdown">
			<option value="" selected>전체</option>
			<option value="4780000">고창군</option>
			<option value="4670000">군산시</option>
			<option value="4710000">김제시</option>
			<option value="4700000">남원시</option>
			<option value="4740000">무주군</option>
			<option value="4790000">부안군</option>
			<option value="4770000">순창군</option>
			<option value="4720000">완주군</option>
			<option value="4680000">익산시</option>
			<option value="4760000">임실군</option>
			<option value="4750000">장수군</option>
			<option value="4640000">전주시</option>
			<option value="4690000">정읍시</option>
			<option value="4730000">진안군</option>
		</select>
		<select name="6460000" id="6460000" class="btn btn-outline-secondary dropdown-toggle sub"
				data-toggle="dropdown">
			<option value="" selected>전체</option>
			<option value="4920000">강진군</option>
			<option value="4880000">고흥군</option>
			<option value="4860000">곡성군</option>
			<option value="4840000">광양시</option>
			<option value="4870000">구례군</option>
			<option value="4830000">나주시</option>
			<option value="4850000">담양군</option>
			<option value="4800000">목포시</option>
			<option value="4950000">무안군</option>
			<option value="4890000">보성군</option>
			<option value="4820000">순천시</option>
			<option value="5010000">신안군</option>
			<option value="4810000">여수시</option>
			<option value="4970000">영광군</option>
			<option value="4940000">영암군</option>
			<option value="4990000">완도군</option>
			<option value="4980000">장성군</option>
			<option value="4910000">장흥군</option>
			<option value="5000000">진도군</option>
			<option value="4960000">함평군</option>
			<option value="4930000">해남군</option>
			<option value="4900000">화순군</option>
		</select>
		<select name="6470000" id="6470000" class="btn btn-outline-secondary dropdown-toggle sub"
				data-toggle="dropdown">
			<option value="" selected>전체</option>
			<option value="5130000">경산시</option>
			<option value="5050000">경주시</option>
			<option value="5200000">고령군</option>
			<option value="5080000">구미시</option>
			<option value="5140000">군위군</option>
			<option value="5060000">김천시</option>
			<option value="5120000">문경시</option>
			<option value="5240000">봉화군</option>
			<option value="5110000">상주시</option>
			<option value="5210000">성주군</option>
			<option value="5070000">안동시</option>
			<option value="5180000">영덕군</option>
			<option value="5170000">영양군</option>
			<option value="5090000">영주시</option>
			<option value="5100000">영천시</option>
			<option value="5230000">예천군</option>
			<option value="5260000">울릉군</option>
			<option value="5250000">울진군</option>
			<option value="5150000">의성군</option>
			<option value="5190000">청도군</option>
			<option value="5160000">청송군</option>
			<option value="5220000">칠곡군</option>
			<option value="5020000">포항시</option>
		</select>
		<select name="6480000" id="6480000" class="btn btn-outline-secondary dropdown-toggle sub"
				data-toggle="dropdown">
			<option value="" selected>전체</option>
			<option value="5370000">거제시</option>
			<option value="5470000">거창군</option>
			<option value="5420000">고성군</option>
			<option value="5350000">김해시</option>
			<option value="5430000">남해군</option>
			<option value="5360000">밀양시</option>
			<option value="5340000">사천시</option>
			<option value="5450000">산청군</option>
			<option value="5380000">양산시</option>
			<option value="5390000">의령군</option>
			<option value="5310000">진주시</option>
			<option value="5410000">창녕군</option>
			<option value="5280000">창원 마산합포회원구</option>
			<option value="5670000">창원 의창성산구</option>
			<option value="5320000">창원 진해구</option>
			<option value="5330000">통영시</option>
			<option value="5440000">하동군</option>
			<option value="5400000">함안군</option>
			<option value="5460000">함양군</option>
			<option value="5480000">합천군</option>
		</select>
		<select name="6500000" id="6500000" class="btn btn-outline-secondary dropdown-toggle sub"
				data-toggle="dropdown">
			<option value="6520000">서귀포시</option>
			<option value="6510000">제주시</option>
			<option value="6500000">제주특별자치도</option>
		</select>
  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		<button class="btn btn-outline-secondary" style="border-radius: 0" onclick="srchList();">검색하기</button>
	</div>
	
	<table id="tbl-board" class="table table-hover">
	<%for(ProtectedAnimal p : list) {%>
	<tr style="display: inline-block; padding: 15px; cursor: pointer;" 
		onclick="open('<%=request.getContextPath() %>/animal/animalView?age='+encodeURI('<%=p.getAge() %>')+'&careAddr='+encodeURI('<%=p.getCareAddr() %>')+'&careNm='+encodeURI('<%=p.getCareNm() %>')+'&careTel='+encodeURI('<%=p.getCareTel() %>')+'&chargeNm='+encodeURI('<%=p.getChargeNm() %>')+'&colorCd='+encodeURI('<%=p.getColorCd() %>')+'&desertionNo='+encodeURI('<%=p.getDesertionNo() %>')+'&filename='+encodeURI('<%=p.getFilename() %>')+'&happenDt='+encodeURI('<%=p.getHappenDt() %>')+'&happenPlace='+encodeURI('<%=p.getHappenPlace() %>')+'&kindCd='+encodeURI('<%=p.getKindCd() %>')+'&neuterYn='+encodeURI('<%=p.getNeuterYn() %>')+'&noticeEdt='+encodeURI('<%=p.getNoticeEdt() %>')+'&noticeNo='+encodeURI('<%=p.getNoticeNo() %>')+'&noticeSdt='+encodeURI('<%=p.getNoticeSdt() %>')+'&officetel='+encodeURI('<%=p.getOfficetel() %>')+'&orgNm='+encodeURI('<%=p.getOrgNm() %>')+'&popfile='+encodeURI('<%=p.getPopfile() %>')+'&processState='+encodeURI('<%=p.getProcessState() %>')+'&sexCd='+encodeURI('<%=p.getSexCd() %>')+'&specialMark='+encodeURI('<%=p.getSpecialMark() %>')+'&weight='+encodeURI('<%=p.getWeight() %>')
		, '유기동물상세보기', 'width=900px, height=600px, top=30px, left=30px');" >
		<td class="card" style="width: 300px; display: inline-block;" >
  				<img src="<%=p.getPopfile() %>" class="card-img-top" alt="..." style="height: 200px">
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