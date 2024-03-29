<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

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
<%
 


%>



<script>

function checkIdDuplicate(){
	var memberId = $("#memberId_").val().trim();
	
	if(memberId.length < 4){
		alert("아이디는 4글자 이상 입력하세요.");
		return;
	}
	
	console.log(memberId+"zzzzzzzzzz");
	
	var url = "<%=request.getContextPath()%>/member/checkIdDuplicate";
	var title = "checkIdDuplicatePopup";
	var status = "width=300px, height=200px, left=50px, top=50px";
	var popup = open(url, title, status);
	
	//폼이랑 팝업연결
	var frm = document.checkIdDuplicateFrm;
	frm.memberId.value = memberId;
	frm.action = url;
	frm.target = title;
	frm.method = "post";
	frm.submit();
	
}

</script>

<style>
.star{
color: red;
}

.enrollmain{
margin-left: 40%;
margin-bottom: 115px;
}

td{
padding: 10px;
}

</style>

<script>
$(function(){
    $("#alert-success").hide();
    $("#alert-danger").hide();
    $("input").keyup(function(){
        var pwd1=$("#password_").val();
        var pwd2=$("#password-chk").val();
                       
        if(pwd1 != "" || pwd2 != ""){
            if(pwd1 == pwd2){
                $("#alert-success").show();
                $("#alert-danger").hide();
            }else{
                $("#alert-success").hide();
                $("#alert-danger").show();
            }    
        }
    });
});

function enrollValidate(){
	
	//아이디 중복검사 여부
	if($("#idValid").val() == 1){
		alert("아이디 중복검사를 실행해주세요");
		return false;
	}
	
	if($("#state").val() == "전국"){
	
		alert("주소를 선택해주세요");
		return false;
	}
	
	return true;
}


</script>

<div id="img-div">

	<img id="header-img" src="<%=request.getContextPath() %>/images/board/12.jpg" alt="헤더 - 후기게시판 사진" />
	<div id="blackbg"></div>
	<span class="header-title">회원가입</span>
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


	<div class="enrollmain" >
	
<form action="" name="checkIdDuplicateFrm">
	<input type="hidden" name="memberId" />
</form>
	  <form action="<%=request.getContextPath()%>/member/memberEnrollEnd" 
    	  name="memberEnrollFrm" 
    	  method="post"
    	  style="position: relative;
    padding-top: 360px;
    margin: 0 auto;
    padding-bottom: 100px;"
          onsubmit="return enrollValidate();">

        <table id="enrolltable">
            <tr>
                <th>아이디<span class="star">*</span></th>
                <td>
                    <input type="text" name="memberId" id="memberId_" placeholder="아이디" required />
                    <input type="button" value="중복검사"
                    	   class="btn btn-pink"
                    	   style="height: 35px;" 
						   onclick="checkIdDuplicate();"/>
                    <div class="idCheck" id="idCheck"></div>
                    <input type="hidden" id="idValid" value="1" />
                </td>
            </tr>
            <tr>
                <th>비밀번호<span class="star">*</span></th>
                <td>
                    <input type="password" name="password" id="password_" required />
                </td>
            </tr>
            <tr>
                <th>비밀번호 확인</th>
                <td>
                    <input type="password" id="password-chk" required />
                    <div class="alert-success" id="alert-success">비밀번호가 일치합니다.</div>
					<div class="alert-danger" id="alert-danger">비밀번호가 일치하지 않습니다.</div>
                </td>
            </tr>
            <tr>
                <th>이름<span class="star">*</span></th>
                <td>
                    <input type="text" name="memberName" id="memberName" placeholder="이름" required />
                </td>
            </tr>

            <tr>
                <th>이메일<span class="star">*</span></th>
                <td>
                    <input type="email" name="email" id="email" placeholder="temp@naver.com" />
                </td>
            </tr>

            <tr>
                <th>연락처<span class="star">*</span></th>
                <td>
                    <input type="text" name="phone" id="phone" placeholder="(-없이) 01012341234" required />
                </td>
            </tr>
            <tr>
                <th>주소<span class="star">*</span></th>
                <td>
                    <select name="state" id="state" onchange="show(this.options[this.selectedIndex].value);">
                       <option value="전국">전국</option>
						<option value="서울특별시">서울특별시</option>
						<option value="부산광역시">부산광역시</option>
						<option value="대구광역시">대구광역시</option>
						<option value="인천광역시">인천광역시</option>
						<option value="광주광역시">광주광역시</option>
						<option value="세종특별자치시">세종특별자치시</option>
						<option value="대전광역시">대전광역시</option>
						<option value="울산광역시">울산광역시</option>
						<option value="경기도">경기도</option>
						<option value="강원도">강원도</option>
						<option value="충청북도">충청북도</option>
						<option value="충청남도">충청남도</option>
						<option value="전라북도">전라북도</option>
						<option value="전라남도">전라남도</option>
						<option value="경상북도">경상북도</option>
						<option value="경상남도">경상남도</option>
						<option value="제주특별자치도">제주특별자치도</option>
				</select> <select name="temp" style="" id="temp">
						<option value="">지역선택</option>
				</select> <select name="city" style="display: none" id="city1">
						<option value="강남구">강남구</option>
						<option value="강동구">강동구</option>
						<option value="강북구">강북구</option>
						<option value="강서구">강서구</option>
						<option value="관악구">관악구</option>
						<option value="광진구">광진구</option>
						<option value="구로구">구로구</option>
						<option value="금천구">금천구</option>
						<option value="노원구">노원구</option>
						<option value="도봉구">도봉구</option>
						<option value="동대문구">동대문구</option>
						<option value="동작구">동작구</option>
						<option value="마포구">마포구</option>
						<option value="서대문구">서대문구</option>
						<option value="서초구">서초구</option>
						<option value="성동구">성동구</option>
						<option value="성북구">성북구</option>
						<option value="송파구">송파구</option>
						<option value="양천구">양천구</option>
						<option value="영등포구">영등포구</option>
						<option value="용산구">용산구</option>
						<option value="은평구">은평구</option>
						<option value="종로구">종로구</option>
						<option value="중구">중구</option>
						<option value="중랑구">중랑구</option>
				</select> <select name="city" style="display: none" id="city2">
						<option value="강서구">강서구</option>
						<option value="금정구">금정구</option>
						<option value="기장군">기장군</option>
						<option value="남구">남구</option>
						<option value="동구">동구</option>
						<option value="동래구">동래구</option>
						<option value="부산진구">부산진구</option>
						<option value="북구">북구</option>
						<option value="사상구">사상구</option>
						<option value="사하구">사하구</option>
						<option value="서구">서구</option>
						<option value="수영구">수영구</option>
						<option value="연제구">연제구</option>
						<option value="영도구">영도구</option>
						<option value="중구">중구</option>
						<option value="해운대구">해운대구</option>
				</select> <select name="city" style="display: none" id="city3">
						<option value="남구">남구</option>
						<option value="달서구">달서구</option>
						<option value="달성군">달성군</option>
						<option value="동구">동구</option>
						<option value="북구">북구</option>
						<option value="서구">서구</option>
						<option value="수성구">수성구</option>
						<option value="중구">중구</option>
				</select> <select name="city" style="display: none" id="city4">
						<option value="강화군">강화군</option>
						<option value="계양구">계양구</option>
						<option value="남동구">남동구</option>
						<option value="동구">동구</option>
						<option value="미추홀구">미추홀구</option>
						<option value="부평구">부평구</option>
						<option value="서구">서구</option>
						<option value="연수구">연수구</option>
						<option value="옹진군">옹진군</option>
						<option value="중구">중구</option>
				</select> <select name="city" style="display: none" id="city5">
						<option value="광산구">광산구</option>
						<option value="남구">남구</option>
						<option value="동구">동구</option>
						<option value="북구">북구</option>
						<option value="서구">서구</option>
				</select> <select name="city" style="display: none" id="city6">
						<option value="세종특별자치시">세종특별자치시</option>

				</select> <select name="city" style="display: none" id="city7">
						<option value="대덕구">대덕구</option>
						<option value="동구">동구</option>
						<option value="서구">서구</option>
						<option value="유성구">유성구</option>
						<option value="중구">중구</option>

				</select> <select name="city" style="display: none" id="city8">
						<option value="남구">남구</option>
						<option value="동구">동구</option>
						<option value="북구">북구</option>
						<option value="울주군">울주군</option>
						<option value="중구">중구</option>

				</select> <select name="city" style="display: none" id="city9">
						<option value="가평군">가평군</option>
						<option value="고양시">고양시</option>
						<option value="과천시">과천시</option>
						<option value="광명시">광명시</option>
						<option value="광주시">광주시</option>
						<option value="구리시">구리시</option>
						<option value="군포시">군포시</option>
						<option value="김포시">김포시</option>
						<option value="남양주시">남양주시</option>
						<option value="동두천시">동두천시</option>
						<option value="부천시">부천시</option>
						<option value="성남시">성남시</option>
						<option value="수원시">수원시</option>
						<option value="시흥시">시흥시</option>
						<option value="안산시">안산시</option>
						<option value="안성시">안성시</option>
						<option value="안양시">안양시</option>
						<option value="양주시">양주시</option>
						<option value="양평군">양평군</option>
						<option value="여주시">여주시</option>
						<option value="연천군">연천군</option>
						<option value="오산시">오산시</option>
						<option value="용인시">용인시</option>
						<option value="의왕시">의왕시</option>
						<option value="의정부시">의정부시</option>
						<option value="이천시">이천시</option>
						<option value="파주시">파주시</option>
						<option value="평택시">평택시</option>
						<option value="포천시">포천시</option>
						<option value="하남시">하남시</option>
						<option value="화성시">화성시</option>

				</select> <select name="city" style="display: none" id="city10">
						<option value="강릉시">강릉시</option>
						<option value="고성군">고성군</option>
						<option value="동해시">동해시</option>
						<option value="삼척시">삼척시</option>
						<option value="속초시">속초시</option>
						<option value="양구군">양구군</option>
						<option value="양양군">양양군</option>
						<option value="영월군">영월군</option>
						<option value="원주시">원주시</option>
						<option value="인제군">인제군</option>
						<option value="정선군">정선군</option>
						<option value="철원군">철원군</option>
						<option value="춘천시">춘천시</option>
						<option value="태백시">태백시</option>
						<option value="평창군">평창군</option>
						<option value="홍천군">홍천군</option>
						<option value="화천군">화천군</option>
						<option value="횡성군">횡성군</option>

				</select> <select name="city" style="display: none" id="city11">
						<option value="괴산군">괴산군</option>
						<option value="단양군">단양군</option>
						<option value="보은군">보은군</option>
						<option value="영동군">영동군</option>
						<option value="옥천군">옥천군</option>
						<option value="음성군">음성군</option>
						<option value="제천시">제천시</option>
						<option value="증평군">증평군</option>
						<option value="진천군">진천군</option>
						<option value="청주시">청주시</option>
						<option value="충주시">충주시</option>

				</select> <select name="city" style="display: none" id="city12">
						<option value="괴산군">괴산군</option>
						<option value="계룡시">계룡시</option>
						<option value="공주시">공주시</option>
						<option value="금산군">금산군</option>
						<option value="논산시">논산시</option>
						<option value="당진시">당진시</option>
						<option value="보령시">보령시</option>
						<option value="부여군">부여군</option>
						<option value="서산시">서산시</option>
						<option value="서천군">서천군</option>
						<option value="아산시">아산시</option>
						<option value="예산군">예산군</option>
						<option value="천안시">천안시</option>
						<option value="청양군">청양군</option>
						<option value="태안군">태안군</option>
						<option value="홍성군">홍성군</option>

				</select> <select name="city" style="display: none" id="city13">
						<option value="고창군">고창군</option>
						<option value="군산시">군산시</option>
						<option value="김제시">김제시</option>
						<option value="남원시">남원시</option>
						<option value="무주군">무주군</option>
						<option value="부안군">부안군</option>
						<option value="순창군">순창군</option>
						<option value="완주군">완주군</option>
						<option value="익산시">익산시</option>
						<option value="임실군">임실군</option>
						<option value="장수군">장수군</option>
						<option value="전주시">전주시</option>
						<option value="정읍시">정읍시</option>
						<option value="진안군">진안군</option>

				</select> <select name="city" style="display: none" id="city14">
						<option value="강진군">강진군</option>
						<option value="고흥군">고흥군</option>
						<option value="곡성군">곡성군</option>
						<option value="광양시">광양시</option>
						<option value="구례군">구례군</option>
						<option value="나주시">나주시</option>
						<option value="담양군">담양군</option>
						<option value="목포시">목포시</option>
						<option value="무안군">무안군</option>
						<option value="보성군">보성군</option>
						<option value="순천시">순천시</option>
						<option value="신안군">신안군</option>
						<option value="여수시">여수시</option>
						<option value="영광군">영광군</option>
						<option value="영암군">영암군</option>
						<option value="완도군">완도군</option>
						<option value="장성군">장성군</option>
						<option value="장흥군">장흥군</option>
						<option value="진도군">진도군</option>
						<option value="함평군">함평군</option>
						<option value="해남군">해남군</option>
						<option value="화순군">화순군</option>

				</select> <select name="city" style="display: none" id="city15">
						<option value="강진군">강진군</option>
						<option value="고흥군">고흥군</option>
						<option value="곡성군">곡성군</option>
						<option value="광양시">광양시</option>
						<option value="구례군">구례군</option>
						<option value="나주시">나주시</option>
						<option value="담양군">담양군</option>
						<option value="목포시">목포시</option>
						<option value="무안군">무안군</option>
						<option value="보성군">보성군</option>
						<option value="순천시">순천시</option>
						<option value="신안군">신안군</option>
						<option value="여수시">여수시</option>
						<option value="영광군">영광군</option>
						<option value="영암군">영암군</option>
						<option value="완도군">완도군</option>
						<option value="장성군">장성군</option>
						<option value="장흥군">장흥군</option>
						<option value="진도군">진도군</option>
						<option value="함평군">함평군</option>
						<option value="해남군">해남군</option>
						<option value="화순군">화순군</option>

				</select> <select name="city" style="display: none" id="city16">
						<option value="경산시">경산시</option>
						<option value="경주시">경주시</option>
						<option value="고령군">고령군</option>
						<option value="구미시">구미시</option>
						<option value="군위군">군위군</option>
						<option value="김천시">김천시</option>
						<option value="문경시">문경시</option>
						<option value="봉화군">봉화군</option>
						<option value="상주시">상주시</option>
						<option value="성주군">성주군</option>
						<option value="안동시">안동시</option>
						<option value="영덕군">영덕군</option>
						<option value="영양군">영양군</option>
						<option value="영주시">영주시</option>
						<option value="영천시">영천시</option>
						<option value="예천군">예천군</option>
						<option value="울릉군">울릉군</option>
						<option value="울진군">울진군</option>
						<option value="의성군">의성군</option>
						<option value="청도군">청도군</option>
						<option value="청송군">청송군</option>
						<option value="칠곡군">칠곡군</option>
						<option value="포항시">포항시</option>

				</select> <select name="city" style="display: none" id="city17">
						<option value="거제시">거제시</option>
						<option value="거창군">거창군</option>
						<option value="고성군">고성군</option>
						<option value="김해시">김해시</option>
						<option value="남해군">남해군</option>
						<option value="밀양시">밀양시</option>
						<option value="사천시">사천시</option>
						<option value="산청군">산청군</option>
						<option value="양산시">양산시</option>
						<option value="의령군">의령군</option>
						<option value="진주시">진주시</option>
						<option value="창녕군">창녕군</option>
						<option value="창원시">창원시</option>
						<option value="통영시">통영시</option>
						<option value="하동군">하동군</option>
						<option value="함안군">함안군</option>
						<option value="함양군">함양군</option>
						<option value="합천군">합천군</option>

				</select> <select name="city" style="display: none" id="city18">

						<option value="서귀포시">서귀포시</option>
						<option value="제주시 ">제주시</option>

				</select>

                    <input type="hidden" name="address" id="address" value=""/>

            </tr>
        
        </table>
        <input type="submit" value="회원가입" id="btn" class="btn btn-pink" style="height: 35px; margin-top: 35px; margin-left: 55px;" />
        <button onclick="cancle()" class="btn btn-gray" style="height: 35px ; margin-top: 35px; margin-left: 95px;" >취소</button>
 
        
        
                     

    </form>
    
      </div>
       
        <script>
        
        function cancle(){
        	location.href = "<%=request.getContextPath()%>/#none";
         }
        
        </script>

 <script>

 var name;


 function show(obj) {

     if (obj == "서울특별시") {
         name = "city1";

         for (var i = 1; i < 19; i++) { 
             $("#city" + i).css("display", "none");

         }

         $("#"+name).css("display", "");
         $("#temp").css("display", "none");
     }

     if (obj == "부산광역시") {
         name = "city2";

         for (var i = 1; i < 19; i++) {
             $("#city" + i).css("display", "none");

         }

         $("#"+name).css("display", "");
         $("#temp").css("display", "none");
     }

     if (obj == "대구광역시") {
         name = "city3";

         for (var i = 1; i < 19; i++) {
             $("#city" + i).css("display", "none");

         }

         $("#"+name).css("display", "");
         $("#temp").css("display", "none");
     }

     if (obj == "인천광역시") {
         name = "city4";

         for (var i = 1; i < 19; i++) {
             $("#city" + i).css("display", "none");

         }

         $("#"+name).css("display", "");
         $("#temp").css("display", "none");
     }

     if (obj == "광주광역시") {
         name = "city5";

         for (var i = 1; i < 19; i++) {
             $("#city" + i).css("display", "none");

         }

         $("#"+name).css("display", "");
         $("#temp").css("display", "none");
     }

     if (obj == "세종특별자치시") {
         name = "city6";

         for (var i = 1; i < 19; i++) {
             $("#city" + i).css("display", "none");

         }

         $("#"+name).css("display", "");
         $("#temp").css("display", "none");
     }

     if (obj == "대전광역시") {
         name = "city7";

         for (var i = 1; i < 19; i++) {
             $("#city" + i).css("display", "none");

         }

         $("#"+name).css("display", "");
         $("#temp").css("display", "none");
     }

     if (obj == "울산광역시") {
         name = "city8";

         for (var i = 1; i < 19; i++) {
             $("#city" + i).css("display", "none");

         }

         $("#"+name).css("display", "");
         $("#temp").css("display", "none");
     }

     if (obj == "경기도") {
         name = "city9";

         for (var i = 1; i < 19; i++) {
             $("#city" + i).css("display", "none");

         }

         $("#"+name).css("display", "");
         $("#temp").css("display", "none");
     }

     if (obj == "강원도") {
         name = "city10";

         for (var i = 1; i < 19; i++) {
             $("#city" + i).css("display", "none");

         }

         $("#"+name).css("display", "");
         $("#temp").css("display", "none");
     }

     if (obj == "충청북도") {
         name = "city11";

         for (var i = 1; i < 19; i++) {
             $("#city" + i).css("display", "none");

         }

         $("#"+name).css("display", "");
         $("#temp").css("display", "none");
     }

     if (obj == "충청남도") {
         name = "city12";

         for (var i = 1; i < 19; i++) {
             $("#city" + i).css("display", "none");

         }

         $("#"+name).css("display", "");
         $("#temp").css("display", "none");
     }

     if (obj == "전라북도") {
         name = "city13";

         for (var i = 1; i < 19; i++) {
             $("#city" + i).css("display", "none");

         }

         $("#"+name).css("display", "");
         $("#temp").css("display", "none");
     }

     if (obj == "전라남도") {
         name = "city14";

         for (var i = 1; i < 19; i++) {
             $("#city" + i).css("display", "none");

         }

         $("#"+name).css("display", "");
         $("#temp").css("display", "none");
     }

     if (obj == "경상북도") {
         name = "city15";

         for (var i = 1; i < 19; i++) {
             $("#city" + i).css("display", "none");

         }

         $("#"+name).css("display", "");
         $("#temp").css("display", "none");
     }

     if (obj == "경상남도") {
         name = "city16";

         for (var i = 1; i < 19; i++) {
             $("#city" + i).css("display", "none");

         }

         $("#"+name).css("display", "");
         $("#temp").css("display", "none");
     }

     if (obj == "제주특별자치도") {
         name = "city17";

         for (var i = 1; i < 19; i++) {
             $("#city" + i).css("display", "none");

         }

         $("#"+name).css("display", "");
         $("#temp").css("display", "none");
     }

 }

 $("#btn").click(function () {

	 var state = $("#state").val();
     var target = document.getElementById(name);

     var s = target.options[target.selectedIndex].text.trim();
     
     
     $("#address").val(state+" "+s);

     console.log(s);
     console.log($("#address").val());

 });
 
 
 
    </script>
    
  
    
       
   
    
    </body>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
                        
                        
                       
