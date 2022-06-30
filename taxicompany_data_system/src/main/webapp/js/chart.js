$(function(){	//문서 시작
	 
	 //구글 차트 시각화
     google.charts.load('current', {'packages':['corechart']});
	
	 // 구글 시각화 API가 로딩이 완료되면,
	 // 인자로 전달된 콜백함수를 내부적으로 호출하여 차트를 그리는 메소드
     google.charts.setOnLoadCallback(weekChart);
 	 google.charts.setOnLoadCallback(monthChart);
 	 google.charts.setOnLoadCallback(yearChart);
    
     
     //weekchart (pieChart)
     function weekChart() {
		
	     // Create the data table.
	     var data = new google.visualization.DataTable();
	     data.addColumn('string', 'Topping');
	     data.addColumn('number', 'Slices');
	     data.addRows([
	        ['일요일', 3],
	        ['월요일', 1],
	        ['화요일', 1],
	        ['수요일', 1],
	        ['목요일', 2],
	        ['금요일', 2],
	        ['토요일', 2]
	      ]);
	
	      // Set chart options
	      var options = {'title':'weekchart',
	                       'width':1800,
	                       'height':900};
	
	      // Instantiate and draw our chart, passing in some options.
	      var chart = new google.visualization.PieChart(document.getElementById('pie_chart'));
	      chart.draw(data, options);
	
	}
     
     //monthchart (column)
	 function monthChart() {

	     // Create the data table.
	     var data = new google.visualization.DataTable();
	    
				

	     data.addColumn('string', 'month');
	     data.addColumn('number', 'sale');
	    
	 	 let dateRow=[];
	   	
	    
	    for(let i=0; i<32; i++){
		
			let daySale=$("#sale"+i).val();
			let sale =parseInt(daySale);
			let dayDate=$("#date"+i).val();
			dateRow=[dayDate,sale];
			data.addRow(dateRow);
			
		}
		
	      // Set chart options
	      var options = {'title':'monthchart',
	                       'width':1900,
	                       'height':900};
	
	      // Instantiate and draw our chart, passing in some options.
	      var chart = new google.visualization.ColumnChart(document.getElementById('bar_chart'));
	      chart.draw(data, options);
	
	}
	
	
	//yearchart (line)
	function yearChart() {

	     // Create the data table.
	     var data = new google.visualization.DataTable();
	    
	     data.addColumn('string', 'Topping');
	     data.addColumn('number', '2021');
	   	 data.addColumn('number' , '2022');
	     data.addRows([
	        ['1월', 3,3],
	        ['2월', 1,6],
	        ['3월', 1,7]
	      
	      ]);
	
	      // Set chart options
	      var options = {'title':'yearchart',
	                       'width':1900,
	                       'height':900};
	
	      // Instantiate and draw our chart, passing in some options.
	      var chart = new google.visualization.LineChart(document.getElementById('line_chart'));
	      chart.draw(data, options);
		
	}
	
	
	
	
	
});	//문서 끝