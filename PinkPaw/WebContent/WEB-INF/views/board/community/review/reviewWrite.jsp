<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/write.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css" />
<link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap" rel="stylesheet">

<div id="img-div">
	<img id="review_header" src="<%=request.getContextPath() %>/images/1.jpg" alt="헤더 - 후기게시판 사진" />
	<div id="blackbg"></div>
	<span class="header-title">후기 게시판 글쓰기</span>
</div>

<section class="board-container">
	<form action="<%=request.getContextPath()%>/board/review/reviewWriteEnd"
		method="post"
		enctype="multipart/form-data">
		<!-- 파일을 업로드하려면 enctype속성이 꼭 있어야 함. -->
		<table id="tbl-write" class="table">
			<tr>
				<th class="text-left">제목<i class="ico-star">*</i>
				</th>
				<td>
					<input type="text" name="reviewTitle" class="form-control title" required placeholder="한글 20자까지 작성가능"></input>
				</td>
			</tr>
			<tr>
				<th class="text-left">작성자<i class="ico-star">*</i></th>
				<td>
					<input type="text" name="reviewWriter" value="<%=memberLoggedIn.getMemberId()%>"
					class="form-control writer" required readonly></input>
				</td>
			</tr>
			<tr>
				<th class="text-left">게시물 종류<i class="ico-star">*</i></th>
				<td>
					<div class="custom-radio">
						<input type="radio" name="reviewKind" id="organic" value="입양후기" checked required/>
						<label for="organic">입양후기</label>
					</div>
					<div class="custom-radio">
					<input type="radio" name="reviewKind" id="parcelout" value="분양후기" required/>
					<label for="parcelout">분양후기</label>
					</div>
					<div class="custom-radio">
					<input type="radio" name="reviewKind" id="missing" value="찾은후기" required/>
					<label for="missing">찾은후기</label>
					</div>
					<div class="custom-radio">
					<input type="radio" name="reviewKind" id="volunteer" value="봉사후기" required/>
					<label for="volunteer">봉사후기</label>
					</div>
				</td>
			</tr>
			<tr>
				<th class="text-left">사진첨부</th>
				<td>
					<span class="small-text">※ 사진첨부시 주의사항 </span>
					<input type="button" value="추가" class="btn btn-small btn-pink" onclick="attachFile.add()"><br/>
					<span class="small-text">1. 등록가능 한 확장자는 jp(e)g, png입니다.</span> <br>
					<span class="small-text">2. 첫번째로 첨부된 사진이 메인사진으로 등록됩니다.</span> <br>
					
					<div class="left">
						<div class="filebox" id="mainImg">
							<label for="upFile" class="btn btn-small btn-pink">이미지선택</label> 
							<input class="upload-name" value="선택된 파일 없음" disabled="disabled">
							<input type="file" name="upFile" id="upFile" class="upload-hidden" onchange="previewImage(this,'view_area')"/>
							<input type="button" value="삭제" class="btn btn-small btn-gray" onclick="delFile()">
						</div>
						<div id='view_area' style='position:relative; width: 500px; display: none; '></div>
						<br>
						<div id="attachFileDiv">
						</div>
					</div>
				</td>
			<tr>
			<tr>
				<th class="text-left">내용<i class="ico-star">*</i></th>
				<td>
					<textarea name="reviewContent" 
							cols="40" rows="5" class="form-control" required></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="등록" class="btn btn-pink"
						onclick="return boardValidate();"/>
					<input type="button" value="취소" class="btn btn-gray"
						onclick="goReviewList();"/>
				</td>
			</tr>
		</table>	
	</form>
</section>
<script>
function boardValidate() {
	var content = $("[name=boardContent]").val();
	//좌우 공백을 제거하고 길이가 0이면(내용이 비어 있는 경우)
	if(content.trim().length == 0){
		alert("내용을 입력하세요.");
		return false;
	}
	
	return true;
}
function goReviewList(){
	location.href = "<%=request.getContextPath()%>/board/review/reviewList";
}
$(document).ready(function(){ 
	var fileTarget = $('.filebox .upload-hidden'); 
	fileTarget.on('change', function(){ // 값이 변경되면 
		if(window.FileReader){ // modern browser 
			var filename = $(this)[0].files[0].name; 
		}
		else { 
			// old IE 
			var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출 
		} 
		// 추출한 파일명 삽입 
		$(this).siblings('.upload-name').val(filename); 
	}); 
});
function delFile() {
	 if(document.getElementById('upFile').value != '' && !confirm('삭제 하시겠습니까?')){
         return;
     }
     document.getElementById('upFile').remove();
     document.getElementById('view_area').removeChild(document.querySelector("#view_area img"));
     
     var file = document.createElement('input');
     file.type = 'file';
     file.name = 'upFile';
     file.size = '40';
     file.id = 'upFile';
     file.className = 'upload-hidden';
     file.setAttribute('onchange', 'previewImage(this,"view_area")');
     
     document.getElementById('mainImg').appendChild(file);
     $("#mainImg").children('.upload-name').val("선택된 파일 없음");
}


 function previewImage(targetObj, View_area) {
	var preview = document.getElementById(View_area); //div id
	var ua = window.navigator.userAgent;
	
	preview.style.position = 'relative'; 
	preview.style.width = '100px'; 
	/* preview.style.height = '100px'; */
	preview.style.color = 'black'; 
	preview.style.border = '0px solid black'; 
	preview.style.display = 'inline';

 
	var files = targetObj.files;
		for ( var i = 0; i < files.length; i++) {
			var file = files[i];
			var imageType = /image.*/; //이미지 파일일경우만.. 뿌려준다.
			var filename = file.name;
			$('#'+View_area).siblings('.filebox').children('.upload-name').val(filename); 
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
			/* img.style.height = '100px'; */
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

var cnt = 0;
attachFile = {
        idx:0,
        add:function(){ // 파일필드 추가
        	if(cnt>1){
        		alert("사진은 세장까지만 추가가 가능합니다.")
        		return;
        	}
        	cnt = cnt+1;
            var o = this;
            var idx = o.idx;

            var div = document.createElement('div');
            div.style.marginTop = '3px';
            div.id = 'file' + o.idx;
            div.className = "filebox";

            var dv = document.createElement('dv');
            dv.style.marginTop = '3px';
            dv.id = 'dv' + o.idx;
            
            var uploadName = document.createElement('input');
            uploadName.value = '선택된 파일 없음';
            uploadName.disabled = 'disabled';
            uploadName.className = 'upload-name';
            
            var label = document.createElement('label');
            label.htmlFor = 'fileField' + o.idx;
            label.className = 'btn btn-small btn-pink';
            label.innerHTML = "이미지선택";

            var file = document.all ? document.createElement('<input name="upFile"+cnt>') : document.createElement('input');
            file.type = 'file';
            file.name = 'upFile'+cnt;
            file.size = '40';
            file.id = 'fileField' + o.idx;
            file.className = 'upload-hidden';
            file.onchange = function(){o.prev(this,'dv'+idx)};

            var btn = document.createElement('input');
            btn.type = 'button';
            btn.value = '삭제';
            btn.onclick = function(){o.del(idx)};
            btn.style.marginLeft = '5px';
            btn.className = 'btn btn-small btn-gray';


			div.appendChild(label);
			div.appendChild(uploadName);
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
            cnt--;
            document.getElementById('attachFileDiv').removeChild(document.getElementById('file' + idx));
            document.getElementById('attachFileDiv').removeChild(document.getElementById('dv' + idx));
        },
        prev:function(targetObj,View_area){ // 이미지 미리보기
            var preview = document.getElementById(View_area); //div id
            var ua = window.navigator.userAgent;
            
	        var files = targetObj.files;
	        for ( var i = 0; i < files.length; i++) {
	            var file = files[i];
	            var imageType = /image.*/; //이미지 파일일경우만.. 뿌려준다.
	            var prevImg = document.getElementById("prev_" + View_area);
	            var filename = file.name; //filename 가져오기
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
	            /* img.style.height = '100px'; */
	            
	            $('#'+View_area).prev('.filebox').children('.upload-name').val(filename); 
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

<%@ include file="/WEB-INF/views/common/footer.jsp" %>