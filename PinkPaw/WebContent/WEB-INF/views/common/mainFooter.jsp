<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<footer>
	​
	<article>
		<div class="footerInfo">
			<h1>
				<img src="<%=request.getContextPath()%>/images/common/footer/foot_logo.png" alt="로고">
			</h1>
			<div class="footer_txt">
				<ul>
					<li>Team PINKPAW</li>
				</ul>
				<ul>
					<li>         
         				 ⓒ핑크포우제공/데이터 출처 <img src="<%=request.getContextPath()%>/images/common/footer/maf.png" /> 농림축산식품부 <br />
						</li>
				</ul>
				<ul>
					<li>
						핑크포우는 사람과 동물 모두가 행복한 세상을 만들어갑니다.
					</li> 
				</ul>
					<li>
						핑크포우는 정부나 보호소와는 관련이 없습니다.
					</li>
				</ul>
				<p>
					<br>ⓒCOPY RIGHT©2019 PinkPawTeam. ALL RIGHTS RESERVED.
				</p>
			</div>
		<div id="dm-container" style='width:300px'class="input-group mb-3">
		
		<div class="input-group-prepend" style='background-color:pink; '>
		     <label class="input-group-text" 
		    	   for="dm-client" style='color: white; background-color: #da7f84; border: 1px solid #da7f84;'>회원</label>
		  </div>
		  <select class="custom-select" 
		  		  id="dm-client">
		    <option selected>접속자 목록</option>
		  </select>
		</div>
		</div>
	</article>

</footer>
<script>
	$(function(){
		var dWidth=$(window).width();
		var dHeight=$(window).height();
		$('section').css({height:dHeight+'px'});

		//선택되어져 있는 네비게이션 표시
		$(window).scroll(function(e){
		var ht=$(window).height();
		var dScroll=$(window).scrollTop();
		if(dScroll>=0 && dScroll<ht){
			$('nav>ul>li').removeClass('on');
			$('nav>ul>li:nth-child(1)').addClass('on');
			$('.headerWrap').css({'background':'none', 'border-bottom':'none'});
			$('header>h1>a>img').attr('src','<%=request.getContextPath()%>/images/main/logo_white.png');
			$('header>div>a>img').attr('src','<%=request.getContextPath()%>/images/main/icon_meun.png');
			$('header>div>ul>li>a').css('color','#fff');
			$('.headerWrap').mouseover(function(e){$(this).css('background','rgba(34,34,34,0.7)');});
			$('.headerWrap').mouseout(function(e){$(this).css('background','none');});
			$('header .asideNav').css('background','url("images/main/bg_gnbLine.png") no-repeat left center');
			}
		if(dScroll>=ht && dScroll<ht*2){
			$('nav>ul>li').removeClass('on');
			$('nav>ul>li:nth-child(2)').addClass('on');
			if(dWidth <= 450){
				$('.headerWrap').css({'background':'#fff', 'height':'7%'});
			} else {
				$('.headerWrap').css({'background':'#fff', 'height':'84px', 'border-bottom':'1px solid #eee', 'box-sizing':'border-box'});
			}
			$('header>h1>a>img').attr('src','<%=request.getContextPath()%>/images/main/logo_color.png');
			$('header>div>a>img').attr('src','<%=request.getContextPath()%>/images/main/icon_meun_black.png');
			$('header>div>ul>li>a').css('color','#222');
			$('.headerWrap').mouseover(function(e){$(this).css('background','#fff');});
			$('.headerWrap').mouseout(function(e){$(this).css('background','#fff');});
			$('header .asideNav').css('background','url("images/main/bg_gnbLine_100.png") no-repeat left center');
			}
		if(dScroll>=ht*2 && dScroll<ht*3){
			$('nav>ul>li').removeClass('on');
			$('nav>ul>li:nth-child(3)').addClass('on');
			}
		if(dScroll>=ht*3 && dScroll<ht*4){
			$('nav>ul>li').removeClass('on');
			$('nav>ul>li:nth-child(4)').addClass('on');
			}
		});

		//메뉴버튼 클릭시 페이지 이동 이벤트
		$('nav>ul>li>a').click(function(e){
			var secPos=$(this).attr('href'); //해당아이디
			var sec=$(secPos).position().top;  //top의 수치를 가져오기 위함
			$('body,html').stop().animate({scrollTop:sec},1200,'swing');
			return false;
			});

		//마우스 휠 이벤트
		$('section').on('mousewheel',function(event,delta){
			if(delta>0 && $('#sec02','#sec03','#sec04')){
				var prev=$(this).prev().offset().top;
				$('body,html').stop().animate({scrollTop:prev},1200,'swing');
				}
			if(delta<0 && $('#sec01','#sec02','#sec03')){
				var next=$(this).next().offset().top;
				$('body,html').stop().animate({scrollTop:next},1200,'swing');
				}
			});
			
			if(dWidth <= 767 && $(window).scrollTop() > 0){
				$('.headerWrap').css('background','#fff!important');
			}
		
		//header width값 고정
		if(dWidth < 1024){
			$('header').css('width','1024px');
		}		

		//반응형(모바일)
		if(dWidth <= 767){
			
			/*header*/
			$('.headerWrap').css({'position':'fixed', 'height':'7%!important'});
			$('.headerWrap>header').css('width','100%');
			/*$('header').css({'padding-top':dWidth/100*5, 'margin-left':'0', 'left':'5%', 'height':'auto'});*/
			$('header').css({'padding':'5%', 'margin-left':'0', 'left':'auto', 'height':'auto'});
			$('header>div>ul').hide();
			$('header>h1').css({'margin-top':'0', 'width':'19%'});
			$('header>div').css('width','5.85%');
			$('header>div>a').css({'margin-top':'0', 'margin-left':'0'});
			$('header>div>a').attr('onClick','$(".menuWrap_m").fadeIn(300);');
			
			/*menu*/
			$('.menuWrap_m>.menu>a').click($(function(){$('#div').hide()})); 
			$('.menuWrap_m .menu').css('height',dHeight);
			$('.menuWrap_m .menu>div').css({'padding-top':dWidth/100*5, 'margin-left':'0', 'left':'5%', 'height':'12%', 'box-sizing':'border-box'});
			//$('.menuWrap_m .menu>div').css('padding-bottom',dWidth/100*5);
			$('.menuWrap_m dl>dt').css('padding-top',dWidth/100*5);
			$('.menuWrap_m dl>dt').css('padding-bottom',dWidth/100*5);
			$('.menuWrap_m dl>dd>p').css('padding-top',dWidth/100*5*0.772);
			$('.menuWrap_m dl>dd>p').css('padding-bottom',dWidth/100*5*0.772);

			/*sec01*/
			$('#sec01 h1>p').html("KG에듀원의 또 다른 이름은<br> 꿈을 이루는 '드림파트너'입니다.");
			$('#sec01 .btn_magazine>img').attr('src','images/main/btn_magazine_m.png');

			/*sec02*/
			$('#sec02 h2').html('KG에듀원의<br>브랜드를 소개합니다.');
			$('#sec02 .brand>li').removeClass('mt50 mt-50');
			$('#sec02 .brand>li>a').height(dWidth/100*28.888);
			$('.brandWrap').removeClass('swiper-container');
			$('.brand').removeClass('swiper-wrapper');
			$('.brand>li').removeClass('swiper-slide');

			/*sec03*/
			$('#sec03 h2').html('KG에듀원의 새로운 소식');
			$('#sec03 .swiper-container>ul').addClass('swiper-wrapper');
			$('#sec03 .swiper-container>ul>li').addClass('swiper-slide');

			/*sec04*/
			$('#sec04 h2').html('KG에듀원과<br>함께 할 당신을 기다립니다.');
			$('#sec04 .career').height(dWidth/100*28.888);
			$('#sec04 ul>li').removeClass('rightBox');
			$('#sec04 .career>.careerTxt>p').html('자세히보기');

			/*sec05*/
			$('#sec05 .noticeWrap>.linkBnr>ul>li').height(dWidth/100*35.533);
		}
	});
	
	

	//DM 접속자목록 가져오기
	$("#dm-client").focus(()=>{
		$.ajax({
			url:"<%=request.getContextPath()%>/chat/userList.do",
			dataType: "json",
			success: function(data){
				//console.log(data);
				
				var html = "<option value='' disabled>접속자 목록</option>";
			
				$.each(data, (i,userId) => {
					html += "<option value='"+userId+"'>";
					html += userId;
					html += "</option>";
				});
				$("#dm-client").html(html);
				
			},
			error: function(jqxhr, textStatus, err){
				console.log("ajax처리실패!");
				console.log(jqxhr, textStatus, err);
			}
		});
	});
</script>
</body>
</html>