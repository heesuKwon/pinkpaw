<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@page import="com.pinkpaw.board.freeboard.model.vo.FreeBoard" %>
<%@page import="java.util.List"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />

<%
	FreeBoard f = (FreeBoard)request.getAttribute("freeBoard");
%>

<section class="board-container">
	<form action="<%=request.getContextPath() %>/board/community/free/freeUpdateEnd"
	      method="post" enctype="multipart/form-data">
	      
		<input type="hidden" name="freeNo" value="<%=f.getFreeNo()%>" />
		
		<table id="tbl-board-view">
			<tr>
				<th>제목</th>
				<td><input type="text" name="freeTitle" 
						   value="<%=f.getFreeTitle() %>" required/>
				</td>
			</tr>	
				
			<tr>
				<th>작성자</th>
				<td><input type="text" name="freeWriter"
						   value="<%=f.getFreeWriter() %>" required readonly/></td>
			</tr>		
			
			<tr>
				<th>내용</th>
				<td>
					<textarea name="freeContent" cols="40" rows="5" required><%=f.getFreeContent() %></textarea>
				</td>
			</tr>	
			
			<tr>
				<th>첨부파일</th>
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
					
					<%if(f.getFreeOriginalImg() !=null) {
						String[] renamedImgList = f.getFreeRenamedImg().split("§");
						String[] originalImgList = f.getFreeOriginalImg().split("§");
						for(int i=0;i<renamedImgList.length;i++){%>
						<div>
							<span id="originalImg"><%=originalImgList[i]%></span>
							<span id="renamedImg" style="display:none;"><%=renamedImgList[i]%></span>
							<input type="button" value="삭제" class="btn-delete"><br/>
							<img src="<%=request.getContextPath()%>/upload/board/free/<%=renamedImgList[i]%>" alt="첨부파일"  style='width:100px;' />												
						</div>
					<%}
					}%>
						<input type="button" value="추가" onclick="attachFile.add()"><br/>
						<div id="attachFileDiv">
					<%-- <input type="file" name="upFile"/>
					<span id="fname"><%=rb.getReviewOriginalImg()!=null?b.getOriginalFileName():""%></span>
					첨부파일이 있는 경우 기존파일 삭제용
					<%if(b.getOriginalFileName()!=null){ %>
					<br />
					<input type="checkbox" name="delFile" id="delFile" />
					<label for="delFile">첨부파일삭제</label>
					<%} %> --%>
					<input type="hidden" name="oldOName" value="<%=f.getFreeOriginalImg()!=null?f.getFreeOriginalImg():""%>"/>
					<input type="hidden" name="oldRName" value="<%=f.getFreeRenamedImg()!=null?f.getFreeRenamedImg():""%>"/>
				</td>
			</tr>
											
			<tr>
				<th colspan="2">
					<input type="submit" value="수정하기" onclick="return freeBoardValidate();"/>
					<input type="button" value="취소" onclick="return freeBoardUpdateCancel();"/>
				</th>
			</tr>		
		
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

<%@ include file="/WEB-INF/views/common/footer.jsp" %>