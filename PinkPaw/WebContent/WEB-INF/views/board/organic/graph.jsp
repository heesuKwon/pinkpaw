<!-- 유기동물 통계/현황 -->
<%@page import="java.util.Locale"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
	
<!-- // jQuery UI CSS파일  -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<!-- // jQuery 기본 js파일 -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<!-- // jQuery UI 라이브러리 js파일 -->
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>	
	
<%
	Map<String, Integer> organicMap = (Map) request.getAttribute("organicMap");
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
	String today = sdf.format(date);
	Calendar cal = new GregorianCalendar(Locale.KOREA);
	cal.setTime(date);
	cal.add(Calendar.YEAR, -1); //1년을 뺀다.
	String AYearAgo = sdf.format(cal.getTime());
	
%>
<div class="header_background"></div>

<style>
.header_background{
	height: 84px;
	background: black;
    opacity: 0.5;
}

#title{
	font-size: 28px;
	font-weight: bold;
}
section{
	position: relative;
	top: 120px;
	margin: 0 auto;
	text-align: center;
	padding-bottom: 500px;
}
#dateSelect{
	position: relative;
	text-align: center;
	top: 20px;
}
#date1{
	position: relative;
	display: inline-block;
}
#date2{
	position: relative;
	display: inline-block;
}
#piegraph{
	position: relative;
	margin: 0 auto;
	top: 100px;
	padding-bottom: 80px;
}
 #bargraph{
 	position: relative;
 	margin: 0 auto;
 	top: 130px;
 }
</style>

<section class="container">
	<h1 id="title">전국 모든 지역 유기동물 현황</h1>

	<div id="dateSelect">
		<div id="date1">
			<input type="text" id="testDatepicker1" value="<%=AYearAgo%>">
		</div>
		~
		<div id="date2">
			<input type="text" id="testDatepicker2" value="<%=today%>">
		</div>
	</div>
	<br /><br />
	<hr />
	<div id="graph">
		<div style="width: 500px" id="piegraph">
			<canvas id="myPieChart"></canvas>
		</div>
		<div style="width: 500px" id="bargraph">
			<canvas id="myBarChart"></canvas>
		</div>
	</div>
</section>

<script>
//원형 그래프 부분
// 우선 컨텍스트를 가져옵니다. 
var ctx = document.getElementById("myPieChart").getContext('2d');
/*
 - Chart를 생성하면서, 
 - ctx를 첫번째 argument로 넘겨주고, 
 - 두번째 argument로 그림을 그릴때 필요한 요소들을 모두 넘겨줍니다. 
 */
 
var myPieChart = new Chart(
		ctx,
		{
			type : 'pie',
			data : {
				labels : [ "보호중인 동물", "입양된 동물", "반환된 동물", "자연사한 동물", "안락사된 동물", "방사된 동물", "기증된 동물", "미포획 동물" ],
				datasets : [ {
					label : '# of Votes',
					data : [<%=organicMap.get("보호중")%>	,<%=organicMap.get("종료(입양)")%>, <%=organicMap.get("종료(반환)")%>, <%=organicMap.get("종료(자연사)")%>,
						<%=organicMap.get("종료(안락사)")%>, <%=organicMap.get("종료(방사)")%>,<%=organicMap.get("종료(기증)")%>, <%=organicMap.get("종료(미포획)")%>],
					backgroundColor : [ 'rgba(33, 40, 94, 0.8)',
							'rgba(123, 181, 227, 0.8)',
							'rgba(248, 196, 219, 0.8)',
							'rgba(248, 152, 102, 0.8)',
							'rgba(231, 56, 37, 0.8)',
							'rgba(78, 180, 191, 0.8)',
							'rgba(75, 115, 185, 0.8)',
							'rgba(96, 98, 99, 0.8)'],
					borderColor : [ 'rgba(33, 40, 94, 1)',
						'rgba(123, 181, 227, 1)',
						'rgba(248, 196, 219, 1)',
						'rgba(248, 152, 102, 1)',
						'rgba(231, 56, 37, 1)',
						'rgba(78, 180, 191, 1)',
						'rgba(75, 115, 185, 1)',
						'rgba(96, 98, 99, 1)' ],
					borderWidth : 1
				} ]
			},

			options : {
				tooltips : {
					// Disable the on-canvas tooltip
					enabled : false,
					custom : function(tooltipModel) {
						// Tooltip Element
						var tooltipEl = document
								.getElementById('chartjs-tooltip');
						// Create element on first render
						if (!tooltipEl) {
							tooltipEl = document.createElement('div');
							tooltipEl.id = 'chartjs-tooltip';
							tooltipEl.innerHTML = '<table></table>';
							document.body.appendChild(tooltipEl);
						}
						// Hide if no tooltip
						if (tooltipModel.opacity === 0) {
							tooltipEl.style.opacity = 0;
							return;
						}
						// Set caret Position
						tooltipEl.classList.remove('above', 'below',
								'no-transform');
						if (tooltipModel.yAlign) {
							tooltipEl.classList.add(tooltipModel.yAlign);
						} else {
							tooltipEl.classList.add('no-transform');
						}
						function getBody(bodyItem) {
							return bodyItem.lines;
						}
						// Set Text
						if (tooltipModel.body) {
							var titleLines = tooltipModel.title || [];
							var bodyLines = tooltipModel.body.map(getBody);
							var innerHtml = '<thead>';
							titleLines.forEach(function(title) {
								innerHtml += '<tr><th>' + title
										+ '</th></tr>';
							});
							innerHtml += '</thead><tbody>';
							bodyLines
									.forEach(function(body, i) {
										var colors = tooltipModel.labelColors[i];
										var style = 'background:'
												+ colors.backgroundColor;
										style += '; border-color:'
												+ colors.borderColor;
										style += '; border-width: 2px';
										var span = '<span style="' + style + '"></span>';
										innerHtml += '<tr><td>' + span
												+ body + '</td></tr>';
									});
							innerHtml += '</tbody>';
							var tableRoot = tooltipEl
									.querySelector('table');
							tableRoot.innerHTML = innerHtml;
						}
						// this will be the overall tooltip
						var position = this._chart.canvas
								.getBoundingClientRect();
						// Display, position, and set styles for font
						tooltipEl.style.opacity = 1;
						tooltipEl.style.position = 'absolute';
						tooltipEl.style.left = position.left
								+ window.pageXOffset + tooltipModel.caretX
								+ 'px';
						tooltipEl.style.top = position.top
								+ window.pageYOffset + tooltipModel.caretY
								+ 'px';
						tooltipEl.style.fontFamily = tooltipModel._bodyFontFamily;
						tooltipEl.style.fontSize = tooltipModel.bodyFontSize
								+ 'px';
						tooltipEl.style.fontStyle = tooltipModel._bodyFontStyle;
						tooltipEl.style.padding = tooltipModel.yPadding
								+ 'px ' + tooltipModel.xPadding + 'px';
						tooltipEl.style.pointerEvents = 'none';
					}
				}
			}
		});

// 누운 막대 그래프 부분
// 우선 컨텍스트를 가져옵니다. 
var ctx = document.getElementById("myBarChart").getContext('2d');
/*
- Chart를 생성하면서, 
- ctx를 첫번째 argument로 넘겨주고, 
- 두번째 argument로 그림을 그릴때 필요한 요소들을 모두 넘겨줍니다. 
*/
var myBarChart = new Chart(ctx, {
    type: 'horizontalBar',
    data: {
        labels: [ "보호중인 동물", "입양된 동물", "반환된 동물", "자연사한 동물", "안락사된 동물", "방사된 동물", "기증된 동물", "미포획 동물" ],
        datasets: [{
            /* label: '전국 모든 지역 유기동물 현황',  */
            data: [<%=organicMap.get("보호중")%>	,<%=organicMap.get("종료(입양)")%>, <%=organicMap.get("종료(반환)")%>, <%=organicMap.get("종료(자연사)")%>,
				<%=organicMap.get("종료(안락사)")%>, <%=organicMap.get("종료(방사)")%>,<%=organicMap.get("종료(기증)")%>,<%=organicMap.get("종료(미포획)")%> ],
            backgroundColor: [
            	'rgba(33, 40, 94, 0.8)',
				'rgba(123, 181, 227, 0.8)',
				'rgba(248, 196, 219, 0.8)',
				'rgba(248, 152, 102, 0.8)',
				'rgba(231, 56, 37, 0.8)',
				'rgba(78, 180, 191, 0.8)',
				'rgba(75, 115, 185, 0.8)',
				'rgba(96, 98, 99, 0.8)'
            ],
            borderColor: [
            	'rgba(33, 40, 94, 1)',
				'rgba(123, 181, 227, 1)',
				'rgba(248, 196, 219, 1)',
				'rgba(248, 152, 102, 1)',
				'rgba(231, 56, 37, 1)',
				'rgba(78, 180, 191, 1)',
				'rgba(75, 115, 185, 1)',
				'rgba(96, 98, 99, 1)' 
            ],
            borderWidth: 1
        }]
    },
    options: {
    	legend:{
    		display:false
    	},
        maintainAspectRatio: true, // default value. false일 경우 포함된 div의 크기에 맞춰서 그려짐.
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        }
    }
});


 // 비동기 처리 부분
	var date1 = '';
	var date2 = '';
	
	$( "#testDatepicker1" ).datepicker({
		changeMonth: true, 
		changeYear: true,
		nextText: '다음 달',
		prevText: '이전 달',
		dayNamesMin : ['월','화','수','목','금','토','일'],
		monthNamesShort : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		showButtonPanel: true, 
		currentText: '오늘 날짜', 
		closeText: '닫기',
		dateFormat: "yymmdd",
		onClose: function(date){
			console.log(date);
			date1 = date;
			$("#testDatepicker2").datepicker( "option", "minDate", date);
			
			$.ajax({
				type:"POST",
				url:"<%=request.getContextPath()%>/board/organic/graph/OrganicGraphEnd",
				data: "date1=" + date1 + "&date2="+date2,
				success:function(data){
					
					console.log(data);
					console.log(data.보호중);
					console.log(data["종료(기증)"]);
					console.log(data["종료(반환)"]);
					console.log(data["종료(자연사)"]);
					console.log(data["종료(입양)"]);
					console.log(data["종료(미포획)"]);
					console.log(data["종료(안락사)"]);
					console.log(data["종료(방사)"]);
				
					myPieChart.data.datasets[0].data[0] = data["보호중"];
					myPieChart.data.datasets[0].data[1] = data["종료(입양)"];
					myPieChart.data.datasets[0].data[2] = data["종료(반환)"];
					myPieChart.data.datasets[0].data[3] = data["종료(자연사)"];
					myPieChart.data.datasets[0].data[4] = data["종료(안락사)"];
					myPieChart.data.datasets[0].data[5] = data["종료(방사)"];
					myPieChart.data.datasets[0].data[6] = data["종료(기증)"];
					myPieChart.data.datasets[0].data[7] =  data["종료(미포획)"];
					
					myBarChart.data.datasets[0].data[0] = data["보호중"];
					myBarChart.data.datasets[0].data[1] = data["종료(입양)"];
					myBarChart.data.datasets[0].data[2] = data["종료(반환)"];
					myBarChart.data.datasets[0].data[3] = data["종료(자연사)"];
					myBarChart.data.datasets[0].data[4] = data["종료(안락사)"];
					myBarChart.data.datasets[0].data[5] = data["종료(방사)"];
					myBarChart.data.datasets[0].data[6] = data["종료(기증)"];
					myBarChart.data.datasets[0].data[7] = data["종료(미포획)"];
					
					myPieChart.update();
					myBarChart.update();
					
					
				},
				error:function(jqxhr, textStatus, errorThrown){
					console.log("ajax 처리 실패! 그래프");
					console.log(jqxhr, textStatus, errorThrown);
				}					
			});	
		}
	
	});
	
	$( "#testDatepicker2" ).datepicker({
		changeMonth: true, 
		changeYear: true,
		nextText: '다음 달',
		prevText: '이전 달',
		dayNamesMin : ['월','화','수','목','금','토','일'],
		monthNamesShort : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		showButtonPanel: true, 
		currentText: '오늘 날짜', 
		closeText: '닫기', 
		dateFormat: "yymmdd",
		onClose: function(date){
			console.log(date);
			date2 = date;
			$("#testDatepicker1").datepicker( "option", "maxDate", date);
			
			$.ajax({
				type:"POST",
				url:"<%=request.getContextPath()%>/board/organic/graph/OrganicGraphEnd",
				data: "date1=" + date1 + "&date2="+date2,
				success:function(data){
					
					console.log(data);
					console.log(data.보호중);
					console.log(data["종료(기증)"]);
					console.log(data["종료(반환)"]);
					console.log(data["종료(자연사)"]);
					console.log(data["종료(입양)"]);
					console.log(data["종료(미포획)"]);
					console.log(data["종료(안락사)"]);
					console.log(data["종료(방사)"]);
				
					myPieChart.data.datasets[0].data[0] = data["보호중"];
					myPieChart.data.datasets[0].data[1] = data["종료(입양)"];
					myPieChart.data.datasets[0].data[2] = data["종료(반환)"];
					myPieChart.data.datasets[0].data[3] = data["종료(자연사)"];
					myPieChart.data.datasets[0].data[4] = data["종료(안락사)"];
					myPieChart.data.datasets[0].data[5] = data["종료(방사)"];
					myPieChart.data.datasets[0].data[6] = data["종료(기증)"];
					myPieChart.data.datasets[0].data[7] =  data["종료(미포획)"];
					
					myBarChart.data.datasets[0].data[0] = data["보호중"];
					myBarChart.data.datasets[0].data[1] = data["종료(입양)"];
					myBarChart.data.datasets[0].data[2] = data["종료(반환)"];
					myBarChart.data.datasets[0].data[3] = data["종료(자연사)"];
					myBarChart.data.datasets[0].data[4] = data["종료(안락사)"];
					myBarChart.data.datasets[0].data[5] = data["종료(방사)"];
					myBarChart.data.datasets[0].data[6] = data["종료(기증)"];
					myBarChart.data.datasets[0].data[7] = data["종료(미포획)"];
					
					myPieChart.update();
					myBarChart.update();
					
					
				},
				error:function(jqxhr, textStatus, errorThrown){
					console.log("ajax 처리 실패! 그래프");
					console.log(jqxhr, textStatus, errorThrown);
				}					
			});	
		}
	});
	
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>