<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ page
	import="com.pinkpaw.board.missingboard.model.vo.MissingBoard"%>
	
<%
   MissingBoard b = (MissingBoard)request.getAttribute("board");
	


%>
<link rel="stylesheet" 
	  href="<%=request.getContextPath()%>/css/board.css" />
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

<section class="board-container">
	<form action="<%=request.getContextPath() %>/board/missingUpdateEnd"
		method="post" enctype="multipart/form-data">
		<table id="tbl-board-view">
			<tr>
				<th>글번호</th>
				<td><input type="text" name="boardNo" value="<%=b.getMissingNo()%>"
					 readonly /><br /></td>
			</tr>
			<tr>
				<th>등록인</th>
				<td><input type="text" name="boardWriter" value="<%=b.getMissingWriter()%>"
					required readonly /><br /></td>
			</tr>

			<tr>
				<th>글제목</th>
				<td><input type="text" name="boardTitle" value="<%=b.getMissingTitle()%>" required /></td>
			</tr>
			<tr>
				<th>분류</th>
				<td><input type="radio" id="animal-dog" name="kind" value="개" />개
					<input type="radio" id="animal-cat" name="kind" value="고양이" />고양이
					<input type="radio" id="animal" name="kind" value="animal" />기타&nbsp;&nbsp;
					<div id='show-me' style='display: none;'>
						<input type="text" id="animal-and" name="others"
							placeholder="기타 분류 입력해주세요">
					</div></td>
			</tr>
			<tr>
				<th>잃어버린 장소</th>
				<td>
				 <input type="text"
					name="detailPlace" maxlength="30" size="30"
					style="border-width: 1px;"
					value="<%=b.getMissingHpPlace() %>"/> 
				</td>
			</tr>

			<tr>
				<th>잃어버린 날짜</th>
				<td><input type="text" name="lostDate" value="<%=b.getMissingHpDate()%>" required /></td>
			</tr>
			<tr>
				<th>사례금</th>

				<td><input type="radio" id="reward-1" name="reward" value="-1"
					onclick="reward_chk(this.value);">있음(찾은 후 협의) <input
					type="radio" id="reward0"name="reward" value="0"
					onclick="reward_chk(this.value);">없음 <br> <input
					type="radio" id="reward1"name="reward" value="1"
					onclick="reward_chk(this.value);">있음 
					<div id='show-me2' style='display: none;'>
					<input type="number"
					name="rewardText">만원
					</div>
				</td>
			</tr>
			<tr>
				<th>연락처</th>
				<td><input type="text" name="phone" placeholder="-없이 입력해주세요."
				 value="<%=b.getMissingPhone() %>"	required /></td>
			</tr>

			<tr>
				<th>상세설명</th>
				<td><textarea name="boardContent" cols="40" rows="5"
						placeholder="내용을 입력해주세요."><%=b.getMissingContent() %></textarea></td>
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
				<th colspan="2"><input type="submit" value="수정" /> 
				<input type="button" value="취소" 
							onclick="return cancel();"/></th>
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