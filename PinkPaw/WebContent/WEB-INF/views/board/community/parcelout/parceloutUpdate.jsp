<%@page import="com.pinkpaw.board.parceloutboard.model.vo.ParceloutBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ParceloutBoard p = (ParceloutBoard)request.getAttribute("p");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PinkPaw / 분양글 수정</title>
<script src="<%= request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<style>
#fileField {
position: relative;
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
</script>
</head>
<body>
	<h2>분양 글 수정</h2>
	<form action="<%=request.getContextPath()%>/board/parceloutboard/parceloutUpdateEnd"
	      method="post"
	      enctype="multipart/form-data">
	      <input type="hidden" name="parceloutNo" value="<%=p.getParceloutNo() %>" />
		<table id="tbl-board-view">
			<tr>
				<th>제목</th>
				<td><input type="text" name="parceloutTitle" value="<%=p.getParceloutTitle() %>" required/></td>
			</tr>		
			<tr>
				<th>작성자</th>
				<td><input type="text" 
						   name="parceloutWriter"
						   value="admin"
						   required readonly/></td>
			</tr>		
			<tr>
				<th>분양지역</th>
				<td>
					<select name="sido" id="sido" ></select>
					<select name="gugun" id="gugun"></select>			
				</td>
			</tr>
			<tr>
				<th>책임비</th>
				<td>
				<input type="radio" id="money" name="parceloutMoney" value="10000" <%=p.getParceloutMoney()%>=="10000"?checked:"">1만원
			    <input type="radio" id="money" name="parceloutMoney" value="20000" >2만원
    			<input type="radio" id="money" name="parceloutMoney" value="30000" >3만원
    			<input type="radio" id="money" name="parceloutMoney" value="40000" >4만원
    			<input type="radio" id="money" name="parceloutMoney" value="50000" >5만원
				</td>
			</tr>	
			
			<tr>
				<th>분류</th>
				<td>
				<input type="radio" name="parceloutKind" value="dog" />개
				<input type="radio" name="parceloutKind" value="cat" />고양이
				<input type="radio" name="parceloutKind" value="etc" />기타
				
<!-- 				<input type="radio" name="parceloutKind" value="dog" />개 -->
<!-- 				<input type="radio" name="parceloutKind" value="cat"/>고양이 -->
<!-- 				<input type="radio" id="animal" name="parceloutKind" value="animal"/>기타&nbsp;&nbsp; -->
<!-- 				<div id='show-me' style='display:none'> -->
<!-- 				<input type="text" id="etc" name="parceloutKind" placeholder="기타 분류 입력해주세요" > -->
<!-- 				</div> -->
				</td>
			</tr>	
			
			<tr>
				<th>암수구분</th>
				<td>
				<input type="radio" name="parceloutGender" value="m" >수컷
			    <input type="radio" name="parceloutGender" value="f">암컷
    			<input type="radio" name="parceloutGender" value="s" >한쌍
    			</td>
			</tr>	
				
			<tr>
				<th>내용</th>
				<td>
					<textarea name="parceloutContent" 
							  cols="40" rows="20" required ><%=p.getParceloutContent()%></textarea>
				</td>
			</tr>
					
			<tr>
				<th>첨부파일</th>
				<td>
				<div id="attachFileDiv" style="display: inline-block">
			    <input type="button" value="추가" onclick="attachFile.add()" style="margin-left:5px">
			    </div>
				<input type="file" name="upFile" style="display: block;"/>
				<span id="fname"><%=p.getParceloutOriginalImg()!=null?
										p.getParceloutRenamedImg():""%></span>
				<%if(p.getParceloutOriginalImg()!=null){ %>
					<br />
					<input type="checkbox" name="delFile" id="delFile" />
					<label for="delFile">첨부파일삭제</label>   
					<% } %>
				<input type="hidden" name="oldOName" value="<%=p.getParceloutOriginalImg()%>"/>
				<input type="hidden" name="oldRName" value="<%=p.getParceloutRenamedImg()%>"/>
				
				</td>		    
			</tr>	
			
				<tr>
					<th></th>
					<td>
					</td>
				</tr>
			    
			    
			<tr>
				<th colspan="2">
					<input type="submit" 
						   value="수정" 
						   onclick="return parceloutValidate();"/>
					
				</th>
			</tr>		
		
		</table>
	</form>
<script>
$("[name=upFile]").change(function(){
    console.log($(this).val());
    //사용자가 파일을 선택한 경우
    if($(this).val() != ""){
        $("#fname").hide();
        
        $("#delFile").hide().next().hide();
    }
    //사용자가 파일선택을 취소한 경우
    else{
        $("#fname").show();
        
        $("#delFile").show().next().show();
    }
});

function parceloutValidate(){
	var content = $("[name=parceloutContent]").val();
	if(content.trim().length == 0){
		alert("내용을 입력하세요.");
		return false;
	}
	
	return true;
}

</script>	
<script>
$("input:radio[name='parceloutMoney']:input[value='<%=p.getParceloutMoney()%>']").attr("checked", true);
$("input:radio[name='parceloutKind']:input[value='<%=p.getParceloutKind()%>']").attr("checked", true);
$("input:radio[name='parceloutGender']:input[value='<%=p.getParceloutGender()%>']").attr("checked", true);
</script>
 <script>
        attachFile = {
            idx:0,
            add:function(){ // 파일필드 추가
                var o = this;
                var idx = o.idx;
    
                var div = document.createElement('div');
                div.style.marginTop = '3px';
                div.id = 'file' + o.idx;
    
                var dv = document.createElement('dv');
                dv.style.marginTop = '3px';
                dv.id = 'dv' + o.idx;
    
                var file = document.all ? document.createElement('<input name="files">') : document.createElement('input');
                file.type = 'file';
                file.name = 'files';
                file.size = '40';
                file.id = 'fileField' + o.idx;
                file.onchange = function(){o.prev(this,'dv'+idx)};
    
                var btn = document.createElement('input');
                btn.type = 'button';
                btn.value = '삭제';
                btn.onclick = function(){o.del(idx)};
                btn.style.marginLeft = '5px';
    
    
    
                div.appendChild(file);
                div.appendChild(btn);
                document.getElementById('attachFileDiv').appendChild(div);
                            document.getElementById('attachFileDiv').appendChild(dv);
    
                o.idx++;
            },
            del:function(idx){ // 파일필드 삭제
                if(document.getElementById('fileField' + idx).value != '' && !confirm('삭제 하시겠습니까?')){
                    return;
                }
                document.getElementById('attachFileDiv').removeChild(document.getElementById('file' + idx));
                            document.getElementById('attachFileDiv').removeChild(document.getElementById('dv' + idx));
            },
            prev:function(targetObj,View_area){ // 이미지 미리보기
                var preview = document.getElementById(View_area); //div id
                alert(View_area);
               var ua = window.navigator.userAgent;
               
     
            var files = targetObj.files;
            for ( var i = 0; i < files.length; i++) {
                var file = files[i];
                var imageType = /image.*/; //이미지 파일일경우만.. 뿌려준다.
                var prevImg = document.getElementById("prev_" + View_area);
                if (!file.type.match(imageType)){
                    preview.removeChild(prevImg);
                    continue;
                    }
                 //이전에 미리보기가 있다면 삭제
                if (prevImg) {
                    preview.removeChild(prevImg);
                }
                var img = document.createElement("img"); 
                img.id = "prev_" + View_area;
                img.classList.add("obj");
                img.file = file;
                img.style.width = '100px'; 
                img.style.height = '100px';
                preview.appendChild(img);
                if (window.FileReader) { // FireFox, Chrome, Opera 확인.
                    var reader = new FileReader();
                    reader.onloadend = (function(aImg) {
                        return function(e) {
                            aImg.src = e.target.result;
                        };
                    })(img);
                    reader.readAsDataURL(file);
                } else { // safari is not supported FileReader
                    //alert('not supported FileReader');
                    if (!document.getElementById("sfr_preview_error_"
                            + View_area)) {
                        var info = document.createElement("p");
                        info.id = "sfr_preview_error_" + View_area;
                        info.innerHTML = "not supported FileReader";
                        preview.insertBefore(info, null);
                    }
                }
            }
            
    
            }
        }
   </script>	
	
	
	
	
	
	
	
</body>
</html>