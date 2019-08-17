<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<section class="board-container">
	<form action="<%=request.getContextPath()%>/board/volunteer/volunteerWriteEnd"
		method="post"
		enctype="multipart/form-data">
		<!-- 파일을 업로드하려면 enctype속성이 꼭 있어야 함. -->
		<table id="tbl-board-view">
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="volunteerTitle" required></input>
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
					<input type="text" name="volunteerWriter" value="<%=memberLoggedIn.getMemberId()%>"
					required readonly></input>
				</td>
			</tr>
			<tr>
				<th>사진첨부</th>
				<td>
					첫번째로 첨부된 사진이 메인사진으로 등록됩니다.<br/>
					<input type="file" name="upFile" onchange="previewImage(this,'view_area')"/>
					<input type="button" value="추가" onclick="attachFile.add()"><br/>
					<div id='view_area' style='position:relative; width: 100px; height: 100px; display: none; '></div>
					<div id="attachFileDiv">
					</div>
				</td>
			<tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea name="volunteerContent" 
							cols="60" rows="12" required>단체 및 시설이름 : 

주소 : 

담당자 이름 :

단체,시설 연락처 : 

모집기간 : 

봉사활동기간 : </textarea>
				</td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="submit" value="등록" 
							onclick="return boardValidate();"/>
				</th>
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
            document.getElementById('attachFileDiv').removeChild(document.getElementById('file' + idx));
                        document.getElementById('attachFileDiv').removeChild(document.getElementById('dv' + idx));
        },
        prev:function(targetObj,View_area){ // 이미지 미리보기
            var pvolunteer = document.getElementById(View_area); //div id
            /* alert(View_area); */
           var ua = window.navigator.userAgent;
  //ie일때(IE8 이하에서만 작동)
    if (ua.indexOf("MSIE") > -1) {
        targetObj.select();
        try {
            var src = document.selection.createRange().text; // get file full path(IE9, IE10에서 사용 불가)
            var ie_pvolunteer_error = document.getElementById("ie_pvolunteer_error_" + View_area);


            if (ie_pvolunteer_error) {
                pvolunteer.removeChild(ie_pvolunteer_error); //error가 있으면 delete
            }

            var img = document.getElementById(View_area); //이미지가 뿌려질 곳

            //이미지 로딩, sizingMethod는 div에 맞춰서 사이즈를 자동조절 하는 역할
            img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+src+"', sizingMethod='scale')";
        } catch (e) {
            if (!document.getElementById("ie_pvolunteer_error_" + View_area)) {
                var info = document.createElement("<p>");
                info.id = "ie_pvolunteer_error_" + View_area;
                info.innerHTML = e.name;
                pvolunteer.insertBefore(info, null);
            }
        }
  //ie가 아닐때(크롬, 사파리, FF)
    } else {
        var files = targetObj.files;
        for ( var i = 0; i < files.length; i++) {
            var file = files[i];
            var imageType = /image.*/; //이미지 파일일경우만.. 뿌려준다.
            var prevImg = document.getElementById("prev_" + View_area);
            if (!file.type.match(imageType)){
                pvolunteer.removeChild(prevImg);
                continue;
                }
             //이전에 미리보기가 있다면 삭제
            if (prevImg) {
                pvolunteer.removeChild(prevImg);
            }
            var img = document.createElement("img"); 
            img.id = "prev_" + View_area;
            img.classList.add("obj");
            img.file = file;
            img.style.width = '100px'; 
            img.style.height = '100px';
            pvolunteer.appendChild(img);
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
                if (!document.getElementById("sfr_pvolunteer_error_"
                        + View_area)) {
                    var info = document.createElement("p");
                    info.id = "sfr_pvolunteer_error_" + View_area;
                    info.innerHTML = "not supported FileReader";
                    pvolunteer.insertBefore(info, null);
                }
            }
        }
    }

  }
}
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>