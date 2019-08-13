<!-- 유기동물 통계/현황 -->
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
%>

<style>
#date1{
	position: absolute;
	top: 100px;
	left: 20px;
}
#date2{
	position: absolute;
	top: 100px;
	left: 300px;
}
#piegraph{
	position: absolute;
	top: 200px;
	left: 20px;
}
 #bargraph{
 	position: absolute;
 	top: 200px;
 	left: 600px;
 }
</style>

<section class="board-container">
	<div style="width: 500px" id="date1">
		<input type="text" id="testDatepicker1" >
	</div>
	<div style="width: 500px" id="date2">
		<input type="text" id="testDatepicker2" >
	</div>
	
	<div style="width: 500px" id="piegraph">
		<canvas id="myPieChart"></canvas>
	</div>
	<div style="width: 500px" id="bargraph">
		<canvas id="myBarChart"></canvas>
	</div>
</section>

<script>

	var date1 = '';
	var date2 = '';

	$( "#testDatepicker1" ).datepicker({
		showOn: "both",
		changeMonth: true, 
		changeYear: true,
		nextText: '다음 달',
		prevText: '이전 달',
		showButtonPanel: true, 
		currentText: '오늘 날짜', 
		closeText: '닫기', 
		dateFormat: "yymmdd",
		onClose: function(data, obj){
			console.log(data);
			date1 = data;
			/* console.log(obj); */
		}
	});
	
	
	$( "#testDatepicker2" ).datepicker({
		showOn: "both",
		changeMonth: true, 
		changeYear: true,
		nextText: '다음 달',
		prevText: '이전 달',
		showButtonPanel: true, 
		currentText: '오늘 날짜', 
		closeText: '닫기', 
		dateFormat: "yymmdd",
		onClose: function(date, obj){
			console.log(date);
			date2 = date;
			/* console.log(obj); */
			
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
					<%-- <%organicMap.put("종료(기증)",%>data["종료(기증)"]<%);%> --%>
					
					var co = data;
					console.log(co);

				},
				error:function(jqxhr, textStatus, errorThrown){
					console.log("ajax 처리 실패! 그래프");
					console.log(jqxhr, textStatus, errorThrown);
				}					
			});	
		}
	});
	
	/* $("#testDatepicker1").click(()=>{
		
		console.log(dateValue);
		
		if(dateValue.length == 0)
			return;
		
		var data ={
			dateValue: dateValue
		}
		
		$.ajax({
			type:"POST",
			url:"/WEB-INF/views/board/organic/graph.jsp",
			dataType:"JSON",
			data: data,
			success:function(data){
				console.log(data);
					
			},
			error:function(jqxhr, textStatus, errorThrown){
				console.log("ajax 처리 실패! 그래프");
				console.log(jqxhr, textStatus, errorThrown);
			}					
		});	
	}); */



	// 원형 그래프 부분
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
						backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
								'rgba(54, 162, 235, 0.2)',
								'rgba(255, 206, 86, 0.2)',
								'rgba(75, 192, 192, 0.2)',
								'rgba(153, 102, 255, 0.2)',
								'rgba(255, 159, 64, 0.2)' ],
						borderColor : [ 'rgba(255,99,132,1)',
								'rgba(54, 162, 235, 1)',
								'rgba(255, 206, 86, 1)',
								'rgba(75, 192, 192, 1)',
								'rgba(153, 102, 255, 1)',
								'rgba(255, 159, 64, 1)' ],
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
	            label: '전국 모든 지역 유기동물 현황',
	            data: [<%=organicMap.get("보호중")%>	,<%=organicMap.get("종료(입양)")%>, <%=organicMap.get("종료(반환)")%>, <%=organicMap.get("종료(자연사)")%>,
					<%=organicMap.get("종료(안락사)")%>, <%=organicMap.get("종료(방사)")%>,<%=organicMap.get("종료(기증)")%>,<%=organicMap.get("종료(미포획)")%> ],
	            backgroundColor: [
	                'rgba(255, 99, 132, 0.2)',
	                'rgba(54, 162, 235, 0.2)',
	                'rgba(255, 206, 86, 0.2)',
	                'rgba(75, 192, 192, 0.2)',
	                'rgba(153, 102, 255, 0.2)',
	                'rgba(255, 159, 64, 0.2)'
	            ],
	            borderColor: [
	                'rgba(255,99,132,1)',
	                'rgba(54, 162, 235, 1)',
	                'rgba(255, 206, 86, 1)',
	                'rgba(75, 192, 192, 1)',
	                'rgba(153, 102, 255, 1)',
	                'rgba(255, 159, 64, 1)'
	            ],
	            borderWidth: 1
	        }]
	    },
	    options: {
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
</script>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>
