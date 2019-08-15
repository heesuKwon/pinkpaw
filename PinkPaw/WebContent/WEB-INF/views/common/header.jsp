<%@page import="com.pinkpaw.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
//로그인성공후 session객체에 저장된 memberLoggedIn가져오기
Member memberLoggedIn
	= (Member)session.getAttribute("memberLoggedIn");
System.out.println("memberLoggedIn@index.jsp="+memberLoggedIn);
/* memberLoggedIn = new Member();
memberLoggedIn.setMemberId("admin");
<<<<<<< HEAD
System.out.println("memberLoggedIn@index.jsp="+memberLoggedIn);
int recvCount = 0;
if(session.getAttribute("recvCount")!=null){
		recvCount = (int)session.getAttribute("recvCount");
}
=======
System.out.println("memberLoggedIn@index.jsp="+memberLoggedIn); */

>>>>>>> branch 'master' of https://github.com/heesuKwon/pinkpaw.git
//쿠키관련 처리
Cookie[] cookies = request.getCookies();
boolean saveId = false;
String memberId = "";

if(cookies != null){
	System.out.println("-------------------------");
	for(Cookie c: cookies){
		String key = c.getName();
		String value = c.getValue();
		System.out.println(key+" : "+value);
		
		//전송된 saveId쿠키가 있는 경우
		if("saveId".equals(key)){
			saveId = true;
			memberId = value;
		}
	}
	System.out.println("-------------------------");
}




	
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PinkPaw</title>
<!-- bootstrap -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>

<!--<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<link rel="icon" href="images/favicon.ico" type="image/x-icon">-->
<meta name="viewport"
	content="width=device-width,user-scalable=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,target-densitydpi=medium-dpi">
<link href="<%=request.getContextPath()%>/css/reset.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/Font.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/Swiper.css"
	rel="stylesheet" />

<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<script
	src="<%=request.getContextPath()%>/js/jquery-migrate-3.0.0.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-ui.min.js"></script>
<script src="<%=request.getContextPath()%>/js/swiper.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.mousewheel.min.js"></script>
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans|Noto+Sans+KR:100,300,400,500,700,900"
	rel="stylesheet" />

<script>

function register(){
	location.href="<%=request.getContextPath()%>/member/termsOfService";
}


$(()=>{
	var host = location.host;
	var ws = new WebSocket('ws://'+host+"<%=request.getContextPath()%>/chat/helloWebSocket");
	
	//최초연결시 open이벤트 핸들러
	ws.onopen = e => {
		console.log("ws open!");
	};
	//서버로부터 message를 전달 받았을 때 핸들러
	ws.onmessage = e => {
		//console.log("ws message: "+e.data);
		var o = JSON.parse(e.data);
		
		//타입 메세지 처리
		if(o.type === 'message'){
			
			var html = "<li class='list-group-item'>";
			html += "<span class='badge badge-dark'>"+o.sender+"</span>";
			html += "&nbsp;&nbsp;"+o.msg;
			html += "</li>";
			
			$("#msg-container ul").append(html);
			
			//scroll처리
			var scrollHeight = $("#msg-container").prop("scrollHeight");
			//console.log(scrollHeight);
			$("#msg-container").scrollTop(scrollHeight);
		}
		else if(o.type === "dm"){
			alert("쪽지가 도착했습니다");
		}
	};
	//서버처리 도중 error발생시 핸들러
	ws.onerror = e =>{
		console.log("ws error!");		
	};
	//서버와 연결 종료시 핸들러
	ws.onclose = e =>{
		console.log("ws close!");			
	};
	
});
</script>

<script>
function validate(){
	if($("#memberId").val().trim().length == 0){
		alert("아이디를 입력하세요.");
		$("#memberId").focus();
		return false;
	}
	if($("#password").val().trim().length == 0){
		alert("비밀번호를 입력하세요.");
		$("#password").focus();
		return false;
	}
	
	
	return true;
}

</script>
	
</head>


<body>

	<!--header-->
	<div class="headerWrap">
	<header>
		<h1>
			<a href="<%=request.getContextPath()%>"><img src="<%=request.getContextPath()%>/images/main/logo_white.png" alt="logo"></a>
		</h1>

		<script>
		$(function(){
​
			$('.headerWrap').mouseover(function(e){
				$(this).css('background','rgba(34,34,34,0.7)');
			});
			$('.headerWrap').mouseout(function(e){
				$(this).css('background','none');}
			);
​
			$('header>div>ul:first-child>li').mouseover(function(e){
				$('.headerWrap').css('height','140px');
				});
			$('header>div>ul:first-child>li').mouseout(function(e){
				$('.headerWrap').css('height','84px');
				});
			});
		</script>
		<div>
			​ <a href="#none" onClick="$('.menuWrap').fadeIn(300);"><img
				src="<%=request.getContextPath()%>/images/main/icon_meun.png" alt="메뉴바"></a>
		</div>
	</header>
	​ ​
	<!--menu-->
	<div class="menuWrap">
		<div class="menu">
			<a href="#none" onClick="$('.menuWrap').fadeOut(300);"
				class="menu_closeBtn"><img src="images/main/modal_close.gif"
				alt="닫기"></a>
			<dl>
				
			<% if(memberLoggedIn == null){ %>
				<form action="<%=request.getContextPath() %>/member/login" 
					  id="loginFrm"
					  method="post"
					  onsubmit="return validate();">
					<table>
						<tr>
							<td>
								<input type="text" 
									   name="memberId"
									   id="memberId"
									   placeholder="아이디"
									   tabindex="1" 
									   value="<%=saveId?memberId:""%>"/>
							</td>
							<td>
								<input type="submit" value="로그인"
									   tabindex="3" />
							</td>
						</tr>
						<tr>
							<td>
								<input type="password" 
									   name="password" 
									   id="password"
									   placeholder="비밀번호" 
									   tabindex="2"/>
							</td>
							<td></td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="checkbox" 
									   name="saveId" 
									   id="saveId" 
									   <%=saveId?"checked":""%>/>
								<label for="saveId">아이디저장</label>
								<input type="button" 
									   value="회원가입"
									   onclick="register();" />
																
							</td>
						</tr>
					</table>
				</form>	
			<% } 
			//로그인에 성공한 경우
			else {%>	
				<table id="logged-in">
					<tr>
						<td>
							<%=memberLoggedIn.getMemberName() %>님, 안녕하세요.
						</td>
					</tr>
					<tr>
						<td>
							<input type="button" 
								   value="쪽지" 
								   onclick="location.href='<%=request.getContextPath()%>/board/dm/dmView"/>
							
							<%if(memberLoggedIn != null && "admin".equals(memberLoggedIn.getMemberId())){ %>
							<input type="button" 
								   value="신고쪽지" 
								   onclick="location.href='<%=request.getContextPath()%>/board/dm/reportDMList'"/>
								   <input type="button" 
								   value="신고게시판" 
								   onclick="location.href='<%=request.getContextPath()%>/admin/reportBoardList'"/>
							<%} %>	   
								   	   
							<input type="button" 
								   value="마이페이지" 
								   onclick="location.href='<%=request.getContextPath()%>/member/memberView?memberId=<%=memberLoggedIn.getMemberId()%>'"/>
							<input type="button" 
								   value="내가쓴글보기" 
								   onclick="location.href='<%=request.getContextPath()%>/member/myBoard'"/>
								   <input type="button" 
								   value="내가쓴댓글보기" 
								   onclick="location.href='<%=request.getContextPath()%>/member/myComment'"/>	   
							<input type="button" 
								   value="로그아웃" 
								   onclick="location.href='<%=request.getContextPath()%>/member/logout'"/>
						</td>
					</tr>				
				</table>
				
			<% } %>		
			
			</dl>
				
			<dl>
				<dt>
					<a href="<%=request.getContextPath()%>/board/notice/noticeBoardList">공지사항</a>
				</dt>

			</dl>
			<dl>
				<dt class="sub_menu">유기동물</dt>
				<dd>
					<a href="<%=request.getContextPath() %>/board/organic/graph/OrganicGraph">유기동물 통계</a>
				</dd>
				<dd>
					<a href="<%=request.getContextPath()%>/animal/animalNotice">유기동물 공고</a>
				</dd>
			</dl>
			<dl class="ddSize">
				<dt class="sub_menu">보호소</dt>
				<dd>
					<a href="<%=request.getContextPath()%>/shelter/shelterList">보호소 찾기</a>
				</dd>
				<dd>
					<a href="<%=request.getContextPath()%>/board/volunteer/volunteerList">보호소 봉사요청</a>
				</dd>

			</dl>
			<dl>
				<dt class="sub_menu">커뮤니티</dt>
				<dd>
					<a href="<%=request.getContextPath()%>/board/missingList">실종동물</a>
				</dd>
				<dd>
					<a href="<%=request.getContextPath()%>/board/parcelout/parceloutList">분양동물</a>
				</dd>
				<dd>
					<a href="<%=request.getContextPath()%>/board/review/reviewList">후기</a>
				</dd>
				<dd>
					<a href="<%=request.getContextPath()%>/board/community/free/freeList">자유게시판</a>
				</dd>
			</dl>
			<dl>
					<%if(memberLoggedIn!=null && "admin".equals(memberLoggedIn.getMemberId())){ %>
					<a href="<%=request.getContextPath()%>/admin/memberList">회원리스트</a>
					<%} %>
			</dl>
		</div>
		<script>
		$(()=>{
			$(".sub_menu").on("click", function(e){

				$(this).parent().siblings().children("dd").css('display','none');
				$(this).siblings().css('display','inline-block');
		    });
		});
		</script>​
	</div>
	<!--menu end-->
	</div>
	<!--header end-->
