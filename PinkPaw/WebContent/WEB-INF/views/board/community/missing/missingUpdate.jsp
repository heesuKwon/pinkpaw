<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ page import="com.pinkpaw.board.missingboard.model.vo.MissingBoard"%>

<%
	MissingBoard b = (MissingBoard) request.getAttribute("board");
	String missingPlace = b.getMissingHpPlace();
	String[] places = missingPlace.split(" ");
%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/board.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/write.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/header.css" />
<link
	href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap"
	rel="stylesheet">
<style>
#fileField {
	position: relative;
}
</style>

<script>
function fileDownload(oName, rName){
	//ie에서 요청한 한글파일명은 오류를 유발하므로,
	//유니코드 문자로 직접변환함.
	oName = encodeURIComponent(oName);
	console.log(oName);
	
	location.href = "<%=request.getContextPath()%>/board/boardFileDownload"
				  + "?oName=" + oName
				  + "&rName=" + rName;
	
}

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

$(()=>{
	//종류
var kind = '<%=b.getMissingKind()%>';
if(kind=='고양이' || kind=='개')
	$('input:radio[name=kind]:input[value='+kind+ ']').attr('checked', 'checked');

else {
	$('input:radio[name=kind]:input[value=animal]').attr('checked', 'checked');
	$('#show-me').removeAttr("style");
	
	$('#animal-and').val(kind);
}
	//사례금
var money = <%=b.getMissingMoney()%>;
if(money==-1 || money==0 )
	$('input:radio[name=reward]:input[value='+money+ ']').attr('checked', 'checked');

else if(money> 0) {
	$('input:radio[name=reward]:input[value='+1+']').attr('checked', 'checked');
	$('#show-me2').removeAttr("style");
	$('input[name=rewardText]').val(money); 

}

});

</script>
<div id="img-div">
	<img id="header-img" src="<%=request.getContextPath()%>/images/board/bg5.jpg"
		alt="헤더 - 후기게시판 사진" />
	<div id="blackbg"></div>
	<span class="header-title">실종 게시글 수정</span>
</div>
<section class="board-container">
	<form action="<%=request.getContextPath()%>/board/missingUpdateEnd"
		method="post" enctype="multipart/form-data">
 <input type="hidden" name="boardNo" value="<%=b.getMissingNo()%>" />		
		<table id="tbl-write" class="table">
			<tr>
				<th class="text-left">제목<i class="ico-star">*</i></th>
				<td><input type="text" name="boardTitle"
					class="form-control title" value="<%=b.getMissingTitle()%>"
					required /></td>
			</tr>
			<tr>
				<th class="text-left">작성자<i class="ico-star">*</i></th>
				<td><input type="text" name="boardWriter"
					value="<%=b.getMissingWriter()%>" class="form-control writer"
					required readonly /></td>
			</tr>
			<tr>
				<th class="text-left">분류<i class="ico-star">*</i></th>
				<td>
					<div class="custom-radio">
						<input type="radio" id="animal-dog" name="kind" value="개" required />
						<label for="animal-dog">개</label>
					</div>
					<div class="custom-radio">
						<input type="radio" id="animal-cat" name="kind" value="고양이"
							required /> <label for="animal-cat">고양이</label>
					</div>
					<div class="custom-radio">
						<input type="radio" id="animal" name="kind" value="animal"
							required /> <label for="animal">기타</label>
					</div>
					<div id='show-me' style='display: none'>
						<input type="text" id="animal-and" name="others"
							class="form-control others" style="display: inline-block;"
							placeholder="기타 분류 입력해주세요">
					</div>
				</td>
			</tr>
			<tr>
				<th class="text-left">잃어버린 장소</th>
				<td class="left">
					<select name="sido" id="sido"
					class="btn btn-outline-gray dropdown-toggle" data-toggle="dropdown" ></select>
					<select name="gugun" id="gugun"
					class="btn btn-outline-gray dropdown-toggle" data-toggle="dropdown" ></select>
					<div class="center">
						<input type="text" name="detailPlace" maxlength="20" size="17"
							class="form-control others" value="<%=places[2] %>" placeholder="9글자까지 목록에 노출">
					</div></td>
			</tr>

			<tr>
				<th class="text-left">잃어버린 날짜</th>
				<td><input type="date" name="lostDate"
					class="form-control others" value="<%=b.getMissingHpDate() %>" required /></td>
			</tr>
			<tr>
				<th class="text-left">사례금</th>

				<td>
					<div class="custom-radio">
						<input type="radio" id="reward-1" name="reward" value="-1"
							onclick="reward_chk(this.value);"> <label for="reward-1">있음(찾은
							후 협의)</label>
					</div>
					<div class="custom-radio">
						<input type="radio" id="reward0" name="reward" value="0"
							onclick="reward_chk(this.value);"> <label for="reward0">없음</label>
					</div>
					<div class="custom-radio">
						<input type="radio" id="reward1" name="reward" value="1"
							onclick="reward_chk(this.value);"> <label for="reward1">있음</label>
					</div>
					<div id='show-me2' style='display: none'>
						<input type="number" name="rewardText" size="4" maxlength="4"
							class="form-control number center"> 만원
					</div>
				</td>
			</tr>
			<tr>
				<th class="text-left">연락처</th>
				<td><input type="text" name="phone" class="form-control others" value="<%=b.getMissingPhone() %>" required /></td>
			</tr>
			<tr>
				<th>사진추가</th>
				<td style="position: relative">
					<!-- 
					첨부파일 관련 사용자 시나리오:
					1. 첨부파일이 없는 경우 
					2. 첨부파일이 있는 경우
						- 파일 관련 수정을 하지 않는 경우:  upFile=null
						- 새로운 파일을 첨부한 경우: upFile있음. 기존파일은 삭제
						- 기존 파일을 삭제만 하는 경우(delFile): upFile=null.기존파일 삭제
					-->
					<!-- file태그의 value 속성은 보안상 이유로 임의 변경이 불가함. -->
					첫번째로 첨부된 사진이 메인사진으로 등록됩니다.<br/>
					<!-- 첨부파일이 있는 경우만 보임 처리 -->
				
					<%if(b.getMissingOriginalImg()!=null) {
				String[] renamedImgList = b.getMissingRenamedImg().split("§");
				String[] originalImgList = b.getMissingOriginalImg().split("§");

				for(int i=0;i<renamedImgList.length;i++){%>
				<div>
				<span id="originalImg"><%=originalImgList[i]%></span>
				<span id="renamedImg" style="display:none;"><%=renamedImgList[i]%></span>
				<input type="button" value="삭제" class="btn-delete"><br/>
				
				<img src="<%=request.getContextPath()%>/upload/board/missing/<%=renamedImgList[i]%>" alt="첨부파일"  style='width:200px;' />					
				</div>
			<%} 
			}%>
					
						<input type="button" value="추가" onclick="attachFile.add()"><br/>
					<div id="attachFileDiv"></div>
					<%-- <input type="file" name="upFile"/>
					<span id="fname"><%=rb.getReviewOriginalImg()!=null?b.getOriginalFileName():""%></span>
					첨부파일이 있는 경우 기존파일 삭제용
					<%if(b.getOriginalFileName()!=null){ %>
					<br />
					<input type="checkbox" name="delFile" id="delFile" />
					<label for="delFile">첨부파일삭제</label>
					<%} %> --%>
					<input type="hidden" name="oldOName" value="<%=b.getMissingOriginalImg()!=null?b.getMissingOriginalImg():""%>"/>
					<input type="hidden" name="oldRName" value="<%=b.getMissingRenamedImg()!=null?b.getMissingRenamedImg():""%>"/>
				</td>
			<tr>
<!-- 			<tr> -->
<!-- 				<th class="text-left">사진첨부</th> -->
<!-- 				<td><span class="small-text">※ 사진첨부시 주의사항 </span> <input -->
<!-- 					type="button" value="추가" class="btn btn-small btn-pink" -->
<!-- 					onclick="attachFile.add()"><br /> <span class="small-text">1. -->
<!-- 						등록가능 한 확장자는 jp(e)g, png입니다.</span> <br> <span class="small-text">2. -->
<!-- 						첫번째로 첨부된 사진이 메인사진으로 등록됩니다.</span> <br> -->

<!-- 					<div class="left"> -->
<!-- 						<div class="filebox" id="mainImg"> -->
<!-- 							<label for="upFile" class="btn btn-small btn-pink">이미지선택</label> -->
<!-- 							<input class="upload-name" value="선택된 파일 없음" disabled="disabled"> -->
<!-- 							<input type="file" name="upFile" id="upFile" -->
<!-- 								class="upload-hidden" onchange="previewImage(this,'view_area')" /> -->
<!-- 							<input type="button" value="삭제" class="btn btn-small btn-gray" -->
<%-- 								onclick="delFile()"> <br /> <span id="fname"><%=b.getMissingOriginalImg() != null ? b.getMissingRenamedImg() : ""%></span> --%>
<%-- 							<% --%>
<!-- // 								if (b.getMissingOriginalImg() != null) { -->
<%-- 							%> --%>
<!-- 							<input type="checkbox" name="delFile" id="delFile" /> <label -->
<!-- 								for="delFile">첨부파일삭제</label> -->
<%-- 							<% --%>
<!-- // 								} -->
<%-- 							%> --%>
<!-- 						</div> -->
<%-- 						<input type="hidden" name="oldOName" value="<%=b.getMissingOriginalImg()!=null?b.getMissingOriginalImg():""%>"/> --%>
<%-- 						<input type="hidden" name="oldRName" value="<%=b.getMissingRenamedImg()!=null?b.getMissingRenamedImg():""%>"/> --%>
<!-- 						<div id='view_area' -->
<!-- 							style='position: relative; width: 500px; display: none;'></div> -->
<!-- 						<br> -->
<!-- 						<div id="attachFileDiv"></div> -->
<!-- 					</div></td> -->
<!-- 			</tr> -->
			<tr>
				<th class="text-left">내용<i class="ico-star">*</i></th>
				<td><textarea name="boardContent" cols="40" rows="5"
						class="form-control" required><%=b.getMissingContent()%></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" 
						   value="수정" 
						   class="btn btn-pink"
						   onclick="return boardValidate();"/>
					<input type="button" 
						   value="취소" 
						   class="btn btn-gray"
						   onclick="cancel();"/>
					
				</td>
			</tr>		

		</table>
	</form>
</section>
<script>
//분류기타
$("input[id='animal']").click(function () {
    $('#show-me').css('display', ($(this).val() === 'animal') ? 'inline':'none');
});
$("input[id='animal-dog']").click(function () {
    $('#show-me').css('display', ($(this).val() === 'animal') ? 'inline':'none');
});
$("input[id='animal-cat']").click(function () {
    $('#show-me').css('display', ($(this).val() === 'animal') ? 'inline':'none');
});

//사례금
$("input[id='reward-1']").click(function () {
    $('#show-me2').css('display', ($(this).val() === '1') ? 'inline':'none');
});
$("input[id='reward0']").click(function () {
    $('#show-me2').css('display', ($(this).val() === '1') ? 'inline':'none');
});
$("input[id='reward1']").click(function () {
    $('#show-me2').css('display', ($(this).val() === '1') ? 'inline':'none');
});

//이미지 파일추가 

$("[name=upFile]").change(function(){
	console.log($(this).val());
	//사용자가 파일을 선택한 경우
	if($(this).val() != ""){
		$("#fname").hide();
		//delFile을 숨기고 다음(라벨)도 숨겨라
		$("#delFile").hide().next().hide();
	}
	//사용자가 파일 선택을 취소한 경우
	else{
		$("#fname").show();
		$("#delFile").show().next().show();
	}
});
function boardValidate() {
	var content = $("[name=boardContent]").val();
	//좌우 공백을 제거하고 길이가 0이면(내용이 비어 있는 경우)
	if(content.trim().length == 0){
		alert("내용을 입력하세요.");
		return false;
	}
	
	return true;
}
function cancel(){
	location.href="<%=request.getContextPath()%>/board/missingView?missingNo="+<%=b.getMissingNo()%>;
}

function previewImage(targetObj, View_area) {
	var preview = document.getElementById(View_area); //div id
	var ua = window.navigator.userAgent;
	
	preview.style.position = 'relative'; 
	preview.style.width = '100px'; 
	preview.style.height = '100px';
	preview.style.color = 'black'; 
	preview.style.border = '0px solid black'; 
	preview.style.display = 'inline';

  //ie일때(IE8 이하에서만 작동)
	if (ua.indexOf("MSIE") > -1) {
		targetObj.select();
		try {
			var src = document.selection.createRange().text; // get file full path(IE9, IE10에서 사용 불가)
			var ie_preview_error = document.getElementById("ie_preview_error_" + View_area);


			if (ie_preview_error) {
				preview.removeChild(ie_preview_error); //error가 있으면 delete
			}

			var img = document.getElementById(View_area); //이미지가 뿌려질 곳

			//이미지 로딩, sizingMethod는 div에 맞춰서 사이즈를 자동조절 하는 역할
			img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+src+"', sizingMethod='scale')";
		} catch (e) {
			if (!document.getElementById("ie_preview_error_" + View_area)) {
				var info = document.createElement("<p>");
				info.id = "ie_preview_error_" + View_area;
				info.innerHTML = e.name;
				preview.insertBefore(info, null);
			}
		}
  //ie가 아닐때(크롬, 사파리, FF)
	} else {
		var files = targetObj.files;
		for ( var i = 0; i < files.length; i++) {
			var file = files[i];
			var imageType = /image.*/; //이미지 파일일경우만.. 뿌려준다.
			if (!file.type.match(imageType))
				continue;
			var prevImg = document.getElementById("prev_" + View_area); //이전에 미리보기가 있다면 삭제
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




var cnt = 0;
<%
	if(b.getMissingOriginalImg()!=null){
		String[] renamedImgList = b.getMissingRenamedImg().split("§");%>
		cnt = <%=renamedImgList.length%>;
	
<%}
%>
$(".btn-delete").on("click",(e)=>{
	if(confirm('삭제 하시겠습니까?')){
		$(e.target).parent("div").hide();
		cnt = cnt-1;
		var delOriginalImg = $(e.target).siblings("#originalImg").text();
		var delRenamedImg = $(e.target).siblings("#renamedImg").text();
		console.log(delOriginalImg);
		console.log(delRenamedImg);
		$("table").before($("<input type='hidden' name='delOriginalImg' value="+delOriginalImg+">"));
		$("table").before($("<input type='hidden' name='delRenamedImg' value="+delRenamedImg+">"));
	}
});

attachFile = {
        idx:0,
        add:function(){ // 파일필드 추가
        	if(cnt>2){
        		alert("사진은 세장까지만 추가가 가능합니다.")
        		return;
        	}
        	cnt = cnt+1;
            var o = this;
            var idx = o.idx;

            var div = document.createElement('div');
            div.style.marginTop = '3px';       
            div.id = 'file' + o.idx;

            var dv = document.createElement('dv');
            dv.style.marginTop = '3px';
            dv.id = 'dv' + o.idx;

            var file = document.all ? document.createElement('<input name="upFile"+cnt>') : document.createElement('input');
            file.type = 'file';
            file.name = 'upFile'+cnt;
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
        	cnt = cnt-1;
            document.getElementById('attachFileDiv').removeChild(document.getElementById('file' + idx));
            document.getElementById('attachFileDiv').removeChild(document.getElementById('dv' + idx));
        },
        prev:function(targetObj,View_area){ // 이미지 미리보기
            var preview = document.getElementById(View_area); //div id
            /* alert(View_area); */
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
<%@ include file="/WEB-INF/views/common/footer.jsp"%>