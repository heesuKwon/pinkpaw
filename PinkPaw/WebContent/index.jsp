<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<!--nav-->
<nav>
	<ul>
		<li class="on"><a href="#sec01"></a></li>
		<li><a href="#sec02"></a></li>
		<li><a href="#sec03"></a></li>
		<li><a href="#sec04"></a></li>
	</ul>
</nav>

<!--section-->
<div id="wrap">
	<section id="sec01">
		<article>
			<h2>
				유기동물에서 반려동물로<br>
			</h2>
			<h1>
				사지말고<br> <strong>'입양'하세요!</strong>
				<p>
					PinkPaw의 또 다른 이름은 모두의 행복을 찾아주는 '해피파트너'입니다.<br>
					동물들의 가정을 찾아주고, 보다 많은 사람들의 유기동물에 대한 관심<br> 
					그리고 유기동물의 입양률을 높이기 위해서 최선을 다해 서비스를 제공합니다.<br>
				</p>
			</h1>
			<!--a href="../webzine/MagazineIndex.php" class="btn_magazine"><img src="images/main/btn_magazine.png" alt="매거진"></a-->
			<div class="scroll">
				<img src="images/main/icon_scroll.png" alt="스크롤">
			</div>
			<div class="dreamElement">
				<ul>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
				</ul>
			</div>
		</article>
	</section>

	<section id="sec02" style="position: relative;">
		<article>
			<h2>
				<span class="smallTitle">입양 & 분양 & 실종  & 봉사 후기</span>
				BEST 후기 :)
			</h2>
			<div class="brandWrap swiper-container">
				<ul class="brand swiper-wrapper">
					<li class="swiper-slide"><a
						href="../business/schoolteacher.php">
							<div class="brandImg"></div> <span class="brandNum">01</span>
							<div class="brandTxt">
								<h3>
									<span class="purple">별이 잘 지내고 있어요~</span>
								</h3>
								<p>
									안녕하세요~핑크포우 덕분에 천사를 알게되었습니다. <br>
									김살(별이) 는 처음 집올 때 무서워했지만
								</p>
							</div>
					</a></li>
					<li class="swiper-slide"><a href="../business/official.php">
							<div class="brandImg"></div> <span class="brandNum">02</span>
							<div class="brandTxt">
								<h3>
									<span class="purple">많은 관심 가져주셔서 감...</span>
								</h3>
								<p>
									좋으신분들 만나 덕분에 찾았습니다.<br>
									잃어버리지않게 잘 키울께요.<br>
									관심 감사드립니다.
								</p>
							</div>
					</a></li>
					<li class="swiper-slide"><a href="../business/cpta.php">
							<div class="brandImg"></div> <span class="brandNum">03</span>
							<div class="brandTxt">
								<h3>
									<span class="purple">순돌이를 입양해주신 분...</span>
								</h3>
								<p>
									본네트에서 구조해 2주정도 데리고 있던게 엊그제 같은데..<br>
									이것도 인연이겠다 싶어 같이 살고 싶었...
								</p>
							</div>
					</a></li>
					<li class="swiper-slide"><a href="../business/duty.php">
							<div class="brandImg"></div> <span class="brandNum">04</span>
							<div class="brandTxt">
								<h3>
									<span class="purple">너무 늦었네요...</span>
								</h3>
								<p>경산 홈플러스 앞 .. <br>교통사고로 무지개다리 건넜습니다...<br>많은관심가져주셔서 감사합니다 
								</p>
							</div>
					</a></li>
					<li class="swiper-slide"><a href="../business/cb.php">
							<div class="brandImg"></div> <span class="brandNum">05</span>
							<div class="brandTxt">
								<h3>
									<span class="purple">상어 찾았습니다</span>
								</h3>
								<p>
									젊은 남성분이 데리고 계셔서 다음날 바로 찾을 수 있었습니다 ! 도착할때까지 있어주셔서 정말 감사드립니다 ㅠㅠ
								</p>
							</div>
					</a></li>
					<li class="swiper-slide"><a href="../business/it.php">
							<div class="brandImg"></div> <span class="brandNum">06</span>
							<div class="brandTxt">
								<h3>
									
								</h3>
								<p>
								</p>
							</div>
					</a></li>
					<li class="swiper-slide"><a href="../business/interior.php">
							<div class="brandImg"></div> <span class="brandNum">07</span>
							<div class="brandTxt">
								<h3>
								</h3>
								<p>
								</p>
							</div>
					</a></li>
					<li class="swiper-slide"><a href="../business/coach.php">
							<div class="brandImg"></div> <span class="brandNum">08</span>
							<div class="brandTxt">
								<h3>
								</h3>
								<p>
								</p>
							</div>
					</a></li>
					<li class="swiper-slide"><a href="../business/job.php">
							<div class="brandImg"></div> <span class="brandNum">09</span>
							<div class="brandTxt">
								<h3>
								</h3>
								<p>
								</p>
							</div>
					</a></li>
				</ul>

			</div>
			<!-- Add Arrows -->
			<div class="swiper-button-next"></div>
			<div class="swiper-button-prev"></div>

			<!-- Initialize Swiper -->
			<script>
      var swiper = new Swiper('.swiper-container', {
        slidesPerView: 3,
      	spaceBetween: 25,
				//loop: true,
				//autoplay: {
				//	delay: 2500,
				//	disableOnInteraction: false,
				//2019-07-10},
        navigation: {
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev',
        },
				shortSwipes: false,
				longSwipes: false,
      });
    </script>
			​
			<div class="mbrandWrap ">
				<ul class="brand ">
					<li><a href="../business/schoolteacher.php">
							<div class="brandImg"></div> <span class="brandNum">01</span>
							<div class="brandTxt">
								<h3>
									교원임용 <span class="purple">희소/쌤플러스</span>
								</h3>
								<p>희소/쌤플러스는 1996년 개원한 최초의 교원임용 전문학원 입니다.</p>
							</div>
					</a></li>
					<li><a href="../business/official.php">
							<div class="brandImg"></div> <span class="brandNum">02</span>
							<div class="brandTxt">
								<h3>
									KG에듀원 <span class="purple">법원직·검찰직 &amp; 사서직</span>
								</h3>
								<p>22년 이상의 전문가 노하우로 법원직 &amp; 검찰직 &amp; 사서직 공무원 단기합격을 위한 전문
									서비스를 제공합니다.</p>
							</div>
					</a></li>
					<li><a href="../business/cpta.php">
							<div class="brandImg"></div> <span class="brandNum">03</span>
							<div class="brandTxt">
								<h3>
									KG에듀원 <span class="purple">회계사/세무사</span>
								</h3>
								<p>2006년 개원 이래 회계사, 세무사 전문 학원으로 최적화된 교육 서비스 제공합니다.</p>
							</div>
					</a></li>
					<li><a href="../business/duty.php">
							<div class="brandImg"></div> <span class="brandNum">04</span>
							<div class="brandTxt">
								<h3>
									KG에듀원 <span class="purple">직무교육</span>
								</h3>
								<p>개인과 기업의 성장을 돕는 HR파트너로서 'Total HRD Solution'을 제공합니다.</p>
							</div>
					</a></li>
					<li><a href="../business/cb.php">
							<div class="brandImg"></div> <span class="brandNum">05</span>
							<div class="brandTxt">
								<h3>
									KG에듀원 <span class="purple">학점은행</span>
								</h3>
								<p>교육부에서 평가 인정한 학점은행제 원격교육훈련기관으로 최단기간 학점 취득</p>
							</div>
					</a></li>
					<li><a href="../business/it.php">
							<div class="brandImg"></div> <span class="brandNum">06</span>
							<div class="brandTxt">
								<h3>
									IT 전문교육 <span class="purple">아이티뱅크</span>
								</h3>
								<p>KG에듀원 아이티뱅크는 고객이 신뢰하는 브랜드로서 독보적 1위 IT전문 교육기업입니다.</p>
							</div>
					</a></li>
					<li><a href="../business/interior.php">
							<div class="brandImg"></div> <span class="brandNum">07</span>
							<div class="brandTxt">
								<h3>
									KG에듀원 <span class="purple">인테리어뱅크</span>
								</h3>
								<p>국내유일의 실내디자인 법인학원으로서 건축인테리어 학원분야 1위 교육기관입니다.</p>
							</div>
					</a></li>
					<li><a href="../business/coach.php">
							<div class="brandImg"></div> <span class="brandNum">08</span>
							<div class="brandTxt">
								<h3>
									KG에듀원 <span class="purple">내일코칭</span>
								</h3>
								<p>대한민국 모든 직무/취업 준비생들이 하고 싶은 일을 찾아 갈 수 있도록 돕겠습니다.</p>
							</div>
					</a></li>
					<li><a href="../business/job.php">
							<div class="brandImg"></div> <span class="brandNum">09</span>
							<div class="brandTxt">
								<h3>
									KG에듀원 <span class="purple">취업아카데미</span>
								</h3>
								<p>취업에 필요한 각종 자격증을 준비 할 수 있는 전문 콘텐츠를 제공합니다.</p>
							</div>
					</a></li>
				</ul>

			</div>
			​
		</article>
	</section>

	<section id="sec03">
		<article>
			<h2>
				<span class="smallTitle">※ 보호중 입니다 ※</span>새로운 유기동물 공고
			</h2>
			<div class="swiper-container_pc">
				<ul>
					<li><a href="#none">
							<div class="news">
								<div class="newsImg">
									<img src="images/main/sec03-1.jpg" alt="">
								</div>
								<div class="newsTxt">
									<span>2019-08-15</span>
									<h3>고양이를 보호 중입니다.</h3>
									<p>만수3동 동부초등학교 부근 빌라단지 밤 11시쯤에 고양이를 발견하였습니...</p>
								</div>
							</div>
							<div class="newsBlind"></div> <span class="pBox">인천광역시</span>
					</a></li>
					<li><a href="../recruit/people.php">
							<div class="news">
								<div class="newsImg">
									<img src="images/main/sec03-2.jpeg" alt="">
								</div>
								<div class="newsTxt">
									<span>2019-08-16</span>
									<h3>흰색 말티즈 남아</h3>
									<p>경북 영주시 순흥면 소수서원 근처 도로변에서 발견 2019/08/18 오후3시경</p>
								</div>
							</div>
							<div class="newsBlind"></div> <span class="yBox">영주시</span>
					</a></li>
					<li><a href="#none">
							<div class="news">
								<div class="newsImg">
									<img src="images/main/sec03-3.jpeg" alt="">
								</div>
								<div class="newsTxt">
									<span>2019-08-17</span>
									<h3>[자양사거리]포메라니안</h3>
									<p>잘 정돈되어있는 포메라니안 남자 아이입니다. 
										중성화 수술이 안되어있으... </p>
								</div>
							</div>
							<div class="newsBlind"></div> <span class="pBox">광진구</span>
					</a></li>
					<li><a href="../recruit/people.php">
							<div class="news">
								<div class="newsImg">
									<img src="images/main/sec03-4.jpg" alt="">
								</div>
								<div class="newsTxt">
									<span>2019-08-17</span>
									<h3>목줄한 검정고양이</h3>
									<p>산책하다 마주쳤는데 먼저 와서 인사하는 걸 보니 사람을 잘 따릅니다.</p>
								</div>
							</div>
							<div class="newsBlind"></div> <span class="yBox">송파구</span>
					</a></li>
					<li><a href="#none">
							<div class="news">
								<div class="newsImg">
									<img src="images/main/sec03-5.jpg" alt="">
								</div>
								<div class="newsTxt">
									<span>2019-08-18</span>
									<h3>시베리안 허스키</h3>
									<p>눈옆과 발가락을 조금 다쳐 내일 약을 발라주고 모레 유기견보호소로 보낼...</p>
								</div>
							</div>
							<div class="newsBlind"></div> <span class="pBox">강서구</span>
					</a></li>
					<li><a href="../recruit/people.php">
							<div class="news">
								<div class="newsImg">
									<img src="images/main/sec03-6.jpg" alt="">
								</div>
								<div class="newsTxt">
									<span>2019-08-19</span>
									<h3>김해 내외동 소형 말티즈</h3>
									<p>김해사랑병원 근처에서 발견 계속 기다리다 임시 보호중</p>
								</div>
							</div>
							<div class="newsBlind"></div> <span class="yBox">김해시</span>
					</a></li>
				</ul>
			</div>

			<!-- Swiper -->
			<div class="swiper-container">
				<ul class="swiper-wrapper">
					<li class="swiper-slide"><a href="#none">
							<div class="news">
								<div class="newsImg">
									<img src="images/main/main_news_01.jpg" alt="">
								</div>
								<div class="newsTxt">
									<span>2019-08-</span>
									<h3>홀트아동복지타운 사랑의 봉사활동</h3>
									<p>일산에 위치한 홀트아동복지타운에 선생님들과 학생들이 함께하는 사랑의 봉사활동이 진행되었습니다.</p>
								</div>
							</div> <span class="pBox">BLOG 소식</span>
					</a></li>
					<li class="swiper-slide"><a href="../recruit/people.php">
							<div class="news">
								<div class="newsImg">
									<img src="images/main/main_news_190108-1.jpg" alt="">
								</div>
								<div class="newsTxt">
									<span>2019-01-16</span>
									<h3>교원기획팀 - 고정은</h3>
									<p>안녕하세요. 저는 교원사업본부에서 10년째 근무하고 있습니다. 그동안 R&amp;R은 많이 바뀌긴
										했지만, 교원 임용...</p>
								</div>
							</div> <span class="yBox">KG 피플</span>
					</a></li>
					<li class="swiper-slide"><a href="#none">
							<div class="news">
								<div class="newsImg">
									<img src="images/main/main_news_03.jpg" alt="">
								</div>
								<div class="newsTxt">
									<span>2018-10-21</span>
									<h3>그린리본캠페인</h3>
									<p>월드비전등과 같은 단체와의 지속적인 협력을 통해 방송프로그램 제작, 그린리본캠페인 등을 지원 하고
										있습니다.</p>
								</div>
							</div> <span class="pBox">BLOG 소식</span>
					</a></li>
					<li class="swiper-slide"><a href="../recruit/people.php">
							<div class="news">
								<div class="newsImg">
									<img src="images/main/main_news_190108-3.jpg" alt="">
								</div>
								<div class="newsTxt">
									<span>2019-01-16</span>
									<h3>지식콘텐츠사업팀 - 김상경</h3>
									<p>HRD교육과 관련된 일이라면 콘텐츠 개발부터 시스템 구축까지 가능한 모든 서비스를 제공하는 팀입니다.</p>
								</div>
							</div> <span class="yBox">KG 피플</span>
					</a></li>
					<li class="swiper-slide"><a href="#none">
							<div class="news">
								<div class="newsImg">
									<img src="images/main/main_news_05.jpg" alt="">
								</div>
								<div class="newsTxt">
									<span>2018-09-18</span>
									<h3>KG에듀원, IPO 절차 돌입</h3>
									<p>KG에듀원과 KB증권이 "코스닥상장을 위한 대표주관계약 체결 협약식"을 진행하였습니다. 2020년
										상반기 상장을 목표로 하...</p>
								</div>
							</div> <span class="pBox">BLOG 소식</span>
					</a></li>
					<li class="swiper-slide"><a href="#none">
							<div class="news">
								<div class="newsImg">
									<img src="images/main/main_news_06.jpg" alt="">
								</div>
								<div class="newsTxt">
									<span>2018-09-01</span>
									<h3>KG 15주년 합창대회 최우수상 수상</h3>
									<p>KG그룹 창립 15주년 기념 합창경연대회가 1일 서울 서초구 더케이아트홀에서 열렸다. 최우수상을
										수상한 KG에듀원의 괌...</p>
								</div>
							</div> <span class="pBox">BLOG 소식</span>
					</a></li>
				</ul>
				<!-- Add Pagination -->
				<div class="swiper-pagination"></div>
			</div>

			<!-- Initialize Swiper -->
			<script>
      var swiper = new Swiper('.swiper-container', {
        spaceBetween: 25,
        pagination: {
          el: '.swiper-pagination',
          clickable: true,
        },
      });
    </script>

		</article>
	</section>

	<section id="sec04">
		<article>
			<h2>
				<span class="smallTitle">반려동물 어떻게 돌보면 좋을까?</span>
					반려동물을 위해 알아두면 좋은 정보!
			</h2>
			<ul>
				<li>
					<div class="career">
						<div class="careerImg">
							<img src="images/main/sec04-1.jpg" alt="">
						</div>
						<div class="careerTxt">
							<span class="careerNum">01.</span>
							<h3>반려견 발톱 관리 이렇게 해보세요!</h3>
							<p>
								반려견들이 사용하는 강아지 전용 발톱깍이를 사용해야 하는데요.
								사람 발톱깍이를 사용해도 된다고 생각하실 수도 있지만 구조가 전혀 달라서 꼭 전용 도구를 사용해야 한답니다!
								강아지들의 발톱 관리는 대략 2주 간격으로 정리해주면 좋아요.
								
							</p>
							<a href="../recruit/talent.php"><img
								src="images/main/icon_narrow.png" alt=""></a>
						</div>
					</div>
				</li>
				<li class="rightBox">
					<div class="career">
						<div class="careerImg">
							<img src="images/main/sec04-2.jpg" alt="">
						</div>
						<div class="careerTxt">
							<span class="careerNum">02.</span>
							<h3>반려동물 분양시 준비해야 할 체크리스트</h3>
							<p>
								'키울 수 있는 환경인지'<br>
								'어떤 경우에도 책임질 수 있는지'<br> 
								'비용을 감당할 수 있는지'<br> 
								'가족의 동의를 구했는지'
							</p>
							<a href="../recruit/info.php"><img
								src="images/main/icon_narrow.png" alt=""></a>
						</div>
					</div>
				</li>
				<li>
					<div class="career">
						<div class="careerImg">
							<img src="images/main/sec04-3.jpg" alt="">
						</div>
						<div class="careerTxt">
							<span class="careerNum">03.</span>
							<h3>강아지가 먹으면 안되는 음식</h3>
							<p>
								1. 과일씨, 잎, 줄기 <br>
								2. 시고 매운 과일, 채소 금지<br>
								3. 자이리톨 성분 &nbsp;&nbsp;
								4. 카페인<br>
								5. 알콜 및 알콜 함유 제품 &nbsp;&nbsp;
								6. 포도 &nbsp;&nbsp;
								7. 우유
							</p>
							<a href="../recruit/institution.php"><img
								src="images/main/icon_narrow.png" alt=""></a>
						</div>
					</div>
				</li>
			</ul>
		</article>
	</section>

	<section id="sec05">
		<article>
			<div class="noticeWrap" style="position: relative;">
				<div class="dreamElement2">
					<ul>
						<li></li>
						<li></li>
						<li></li>
						<li></li>
						<li></li>
						<li></li>
						<li></li>
						<li></li>
						<li></li>
					</ul>
				</div>
				<!--<div class="notice">
      	<h1>공지사항<a href="../customer/notice.php">more +</a></h1>
      	<dl>
      		        	<dt><a href="../customer/notice_view.php?idx=1">2019 리뉴얼 홈페이지 관리 합니다.</a></dt><dd>2019-01-03</dd>
        	          
        </dl>
      </div>-->
				<div class="linkBnr">
					<ul>
						<li><a href="<%=request.getContextPath()%>/shelter/shelterList">
								<h1>
									<img src="images/main/icon_link02.png" alt="">
								</h1>
								<h2>
									보호소<br>찾기
								</h2>
								<p>+</p>
						</a></li>
						<li><a href="<%=request.getContextPath()%>/board/volunteer/volunteerList">
								<h1>
									<img src="images/main/icon_link03.png" alt="">
								</h1>
								<h2>
									보호소<br>봉사 요청
								</h2>
								<p>></p>
						</a></li>
					</ul>
				</div>
				<a href="#none"
					onClick="var scrollPosition = $('#sec01').offset().top; $('html,body').animate({scrollTop:scrollPosition},800,'swing');"
					class="topBtn"><img src="images/main/icon_top.png" alt="top버튼"></a>
			</div>
		</article>
	</section>
</div>

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
			$('header>h1>a>img').attr('src','images/main/logo_white.png');
			$('header>div>a>img').attr('src','images/main/icon_meun.png');
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
			$('header>h1>a>img').attr('src','images/main/logo_purple.png');
			$('header>div>a>img').attr('src','images/main/icon_meun_black.png');
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
		if(dWidth < 1160){
			$('header').css('width','1160px');
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
</script>
<%@ include file="/WEB-INF/views/common/mainFooter.jsp"%>