<%@page import="com.pinkpaw.board.volunteer.model.vo.VolunteerBoard"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/write.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css" />
<link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<%
	VolunteerBoard vb = (VolunteerBoard)request.getAttribute("volunteerBoard");
%>

<div id="img-div">
	<img id="header-img" src="<%=request.getContextPath() %>/images/board/10.jpg"  alt="헤더 - 후기게시판 사진" />
	<div id="blackbg"></div>
	<span class="header-title" >봉사 게시글 수정</span>
</div>

<section class="board-container">
	<form action="<%=request.getContextPath()%>/board/volunteer/volunteerUpdateEnd"
		method="post"
		enctype="multipart/form-data">
		<!-- 파일을 업로드하려면 enctype속성이 꼭 있어야 함. -->
		<input type="hidden" name="volunteerNo" value="<%=vb.getVolunteerNo()%>" />
		<table id="tbl-write" class="table">
			<tr>
				<th class="text-left">제목<i class="ico-star">*</i></th>
				<td>
					<input type="text" name="volunteerTitle" class="form-control title" required value="<%=vb.getVolunteerTitle()%>"></input>
				</td>
			</tr>
			<tr>
				<th class="text-left">작성자<i class="ico-star">*</i></th>
				<td>
					<input type="text" name="volunteerWriter" value="<%=vb.getVolunteerWriter()%>"
					class="form-control writer" required readonly></input>
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
					<%if(vb.getVolunteerOriginalImg()!=null) {
						String[] renamedImgList = vb.getVolunteerRenamedImg().split("§");
						String[] originalImgList = vb.getVolunteerOriginalImg().split("§");
						for(int i=0;i<renamedImgList.length;i++){%>
						<div>
							<span id="originalImg"><%=originalImgList[i]%></span>
							<span id="renamedImg" style="display:none;"><%=renamedImgList[i]%></span>
							<input type="button" value="삭제" class="btn-delete"><br/>
							<img src="<%=request.getContextPath()%>/upload/board/volunteer/<%=renamedImgList[i]%>" alt="첨부파일"  style='width:100px;' />												
						</div>
					<%}
					}%>
					<input type="button" value="추가" onclick="attachFile.add()"><br/>
					<div id="attachFileDiv">
					<input type="hidden" name="oldOName" value="<%=vb.getVolunteerOriginalImg()!=null?vb.getVolunteerOriginalImg():""%>"/>
					<input type="hidden" name="oldRName" value="<%=vb.getVolunteerRenamedImg()!=null?vb.getVolunteerRenamedImg():""%>"/>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea name="volunteerContent" 
							cols="40" rows="5" class="form-control" required><%=vb.getVolunteerContent()%>
					</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="등록" class="btn btn-pink"
						onclick="return boardValidate();"/>
					<input type="button" value="취소" class="btn btn-gray"
						onclick="return cancel();"/>
				</td>
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
	location.href="<%=request.getContextPath()%>/board/volunteer/volunteerView?volunteerNo="+<%=vb.getVolunteerNo()%>;
}

var cnt = 0;
<%
	if(vb.getVolunteerOriginalImg()!=null){
		String[] renamedImgList = vb.getVolunteerRenamedImg().split("§");%>
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
            var pvolunteer = document.getElementById(View_area); //div id
            /* alert(View_area); */
           var ua = window.navigator.userAgent;

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
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>