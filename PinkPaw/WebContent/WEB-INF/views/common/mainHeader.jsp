<%@page import="com.pinkpaw.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
	//로그인성공후 session객체에 저장된 memberLoggedIn가져오기
	/* Member memberLoggedIn
		= (Member)session.getAttribute("memberLoggedIn"); */
	Member memberLoggedIn = new Member();
	memberLoggedIn.setMemberId("admin");
	System.out.println("memberLoggedIn@index.jsp="+memberLoggedIn);
	
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
				<dt>
					<a href="#">공지사항</a>
				</dt>

			</dl>
			<dl>
				<dt class="sub_menu">유기동물</dt>
				<dd>
					<a href="../investment/ir_info.php">유기동물 통계</a>
				</dd>
				<dd>
					<a href="../investment/ir_notice.php">유기동물 공고</a>
				</dd>
			</dl>
			<dl class="ddSize">
				<dt class="sub_menu">보호소</dt>
				<dd>
					<a href="../business/schoolteacher.php">보호소 찾기</a>
				</dd>
				<dd>
					<a href="../business/official.php">보호소 봉사요청</a>
				</dd>

			</dl>
			<dl>
				<dt class="sub_menu">커뮤니티</dt>
				<dd>
					<a href="../pr_center/culture.php">실종동물</a>
				</dd>
				<dd>
					<a href="../pr_center/culture.php">분양동물</a>
				</dd>
				<dd>
					<a href="<%=request.getContextPath()%>/board/review/reviewList">후기</a>
				</dd>
				<dd>
					<a href="../pr_center/culture.php">자유게시판</a>
				</dd>
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
