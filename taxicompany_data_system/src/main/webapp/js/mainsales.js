
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
	     
	     let dataRow= [];
	     
	     for(let i=0; i<7; i++){
		
			let weekSale=$("#weekSale"+i).val();
			let sale=parseInt(weekSale);
			let weekDate=$("#weekDate"+i).val();
			dataRow=[weekDate,sale];
			data.addRow(dataRow);
		}
	
	      // Set chart options
	      var options = {'title':'',
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
	    
	 	 let dataRow=[];
	   	
	    
	    for(let i=0; i<32; i++){
		
			let daySale=$("#daySale"+i).val();
			let sale =parseInt(daySale);
			let dayDate=$("#dayDate"+i).val();
			dataRow=[dayDate,sale];
			data.addRow(dataRow);
			
		}
		
	      // Set chart options
	      var options = {'title':'',
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
	    
	     data.addColumn('string', 'yearDate');
	   	 data.addColumn('number' , '2022');
	   
	     let dataRow=[];
	     
	     for(let i=0; i<12; i++){
			
			let monthSale=$("#monthSale"+i).val();
			let sale=parseInt(monthSale);
			let monthDate=$("#monthDate"+i).val();
			
		
		
			
			dataRow=[monthDate,sale];
			data.addRow(dataRow);
		 }
	
	      // Set chart options
	      var options = {'title':'',
	                       'width':1900,
	                       'height':900};
	
	      // Instantiate and draw our chart, passing in some options.
	      var chart = new google.visualization.LineChart(document.getElementById('line_chart'));
	      chart.draw(data, options);
		
	}
	
	
	
	
	
});	//문서 끝