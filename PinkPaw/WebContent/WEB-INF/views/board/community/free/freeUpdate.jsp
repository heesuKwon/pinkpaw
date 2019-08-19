<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@page import="com.pinkpaw.board.freeboard.model.vo.FreeBoard"%>
<%@page import="java.util.List"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/board.css" />

<%
	FreeBoard f = (FreeBoard)request.getAttribute("freeBoard");
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
<div id="img-div">
	<img id="header-img" src="<%=request.getContextPath() %>/images/1.jpg"
		alt="헤더 - 후기게시판 사진" />
	<div id="blackbg"></div>
	<span class="header-title">자유 게시글 수정</span>
</div>
<section class="board-container">
	<form
		action="<%=request.getContextPath() %>/board/community/free/freeUpdateEnd"
		method="post" enctype="multipart/form-data">

		<input type="hidden" name="freeNo" value="<%=f.getFreeNo()%>" />

		<table id="tbl-write" class="table">
			<tr>
				<th class="text-left">제목<i class="ico-star">*</i></th>
				<td><input type="text" name="freeTitle" class="form-control title" required value="<%=f.getFreeTitle()%>"/></td>
			</tr>
			<tr>
				<th class="text-left">작성자<i class="ico-star">*</i></th>
				<td>
					<input type="text" name="freeWriter" value="<%=f.getFreeWriter() %>" class="form-control writer" required readonly/>
				</td>
			</tr>
<!-- 			<tr> -->
<!-- 				<th>내용</th> -->
<%-- 				<td><textarea name="freeContent" cols="40" rows="5" required><%=f.getFreeContent() %></textarea> --%>
<!-- 				</td> -->
<!-- 			</tr> -->
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
							<br />
						<span id="fname"><%=f.getFreeOriginalImg() != null ? f.getFreeRenamedImg() : ""%></span>
							<%
								if (f.getFreeOriginalImg() != null) {
							%>
							<input type="checkbox" name="delFile" id="delFile" /> <label
								for="delFile">첨부파일삭제</label>
							<%
								}
							%>
						</div>
						<input type="hidden" name="oldOName"
							value="<%=f.getFreeOriginalImg()!=null?f.getFreeOriginalImg():""%>" />
						<input type="hidden" name="oldRName"
							value="<%=f.getFreeRenamedImg()!=null?f.getFreeRenamedImg():""%>" />
						<div id='view_area' style='position:relative; width: 500px; display: none; '></div>
						<br>
						<div id="attachFileDiv">
						</div>
					</div>
				</td>
			</tr>
			
			<tr>
				<th class="text-left">내용<i class="ico-star">*</i></th>
				<td><textarea name="freeContent" cols="40" rows="5"
						class="form-control" required><%=f.getFreeContent()%></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" 
						   value="수정" 
						   class="btn btn-pink"
						   onclick="return freeBoardValidate();"/>
					<input type="button" 
						   value="취소" 
						   class="btn btn-gray"
						   onclick="return freeBoardUpdateCancel();"/>
					
				</td>

		</table>
	</form>

</section>

<script>
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
	
	function freeBoardValidate(){
		var content = $("[name=freeContent]").val();
		if(content.trim().length == 0){
			alert("내용을 입력하세요.");
			return false;
		}
		
		return true;
	}
	
	function freeBoardUpdateCancel(){
		location.href = "<%=request.getContextPath()%>/board/community/free/freeView?freeNo=<%=f.getFreeNo()%>"
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
		if(f.getFreeOriginalImg()!=null){
			String[] renamedImgList = f.getFreeRenamedImg().split("§");%>
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

<%@ include file="/WEB-INF/views/common/footer.jsp"%>