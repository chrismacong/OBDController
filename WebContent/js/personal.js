$(document).ready(function(){
		
		// initiate page scroller plugin
		$('#logout_a').click(function() {
			$.cookie('email', null, {
				path : "/"
			});
			$.cookie('password', null,
			{
				path : "/"
			});
			window.location.href = "${pageContext.request.contextPath}/login.html";
		});
		$('body').pageScroller({
			navigation: '#nav'
		});
		$('#container1').highcharts({
	
	    	chart: {
	       		type: 'gauge',
	        	plotBackgroundColor: null,
		        plotBackgroundImage: null,
		        backgroundColor: 'rgba(0,0,0,0)',
		        plotBorderWidth: 0,
	        	plotShadow: false
	    	},
	    	exporting: {
	    		enabled: false
	    	},
	    	credits: {
	            enabled: false
	        },
        
		    title: {	
		        text: '速度',
		        style: {color: 'white', fontSize: '16px'}
		    },
	    
		    pane: {
		        startAngle: -150,
		        endAngle: 150,
		        background: [{
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#FFF'],
		                    [1, '#333']
		                ]
		            },
		            borderWidth: 0,
		            outerRadius: '109%'
		        }, {
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#333'],
		                    [1, '#FFF']
		                ]
		            },
		            borderWidth: 1,
		            outerRadius: '107%'
		        }, {
		            // default background
		        }, {
		            backgroundColor: '#DDD',
		            borderWidth: 0,
		            outerRadius: '105%',
		            innerRadius: '103%'
		        }]
		    },
	       
		    // the value axis
		    yAxis: {
		        min: 0,
		        max: 200,
		        
		        minorTickInterval: 'auto',
		        minorTickWidth: 1,
		        minorTickLength: 10,
		        minorTickPosition: 'inside',
		        minorTickColor: '#666',
		
		        tickPixelInterval: 30,
		        tickWidth: 2,
		        tickPosition: 'inside',
		        tickLength: 10,
		        tickColor: '#666',
		        labels: {
		            step: 2,
		            rotation: 'auto'
		        },
		        title: {
		            text: 'km/h'
		        },
		        plotBands: [{
		            from: 0,
		            to: 120,
		            color: '#55BF3B' // green
		        }, {
		            from: 120,
		            to: 160,
		            color: '#DDDF0D' // yellow
		        }, {
		            from: 160,
		            to: 200,
		            color: '#DF5353' // red
		        }]        
		    },
	
		    series: [{
		        name: 'Speed',
		        data: [0],
		        tooltip: {
		            valueSuffix: ' km/h'
		        }
		    }]
	
		}, 
		
		// Add some life
		function (chart) {
			if (!chart.renderer.forExport) {
			    setInterval(function () {
			        var point1 = chart.series[0].points[0];
			    	var $params="useremail=" + useremail + "&parameter_name=Obd速度";
					$.ajax({
						type:'GET',
						contentType: 'application/json',  
						url:"personal/refreshParameter.html",
						data: $params,
						dataType: "json",
						success:function(data){
							if (data && data.success == "true") {
						        point1.update(data.answer);
							}
							else{
								point1.update(0);
							}
						},
						error:function(){
							point1.update(0);
						}
					});
			        
			        
			    }, 3000);
			}
		});
		$('#container2').highcharts({
	
	    	chart: {
	       		type: 'gauge',
	        	plotBackgroundColor: null,
		        plotBackgroundImage: null,
		        backgroundColor: 'rgba(0,0,0,0)',
		        plotBorderWidth: 0,
	        	plotShadow: false
	    	},
	    	
	    	exporting: {
	    		enabled: false
	    	},
	    	
	    	credits: {
	            enabled: false
	        },
        
		    title: {	
		        text: '发动机转速',
		        style: {color: 'white', fontSize: '16px'}
		    },
	    
		    pane: {
		        startAngle: -150,
		        endAngle: 150,
		        background: [{
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#FFF'],
		                    [1, '#333']
		                ]
		            },
		            borderWidth: 0,
		            outerRadius: '109%'
		        }, {
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#333'],
		                    [1, '#FFF']
		                ]
		            },
		            borderWidth: 1,
		            outerRadius: '107%'
		        }, {
		            // default background
		        }, {
		            backgroundColor: '#DDD',
		            borderWidth: 0,
		            outerRadius: '105%',
		            innerRadius: '103%'
		        }]
		    },
	       
		    // the value axis
		    yAxis: {
		        min: 0,
		        max: 10,
		        
		        minorTickInterval: 'auto',
		        minorTickWidth: 1,
		        minorTickLength: 10,
		        minorTickPosition: 'inside',
		        minorTickColor: '#666',
		
		        tickPixelInterval: 30,
		        tickWidth: 2,
		        tickPosition: 'inside',
		        tickLength: 10,
		        tickColor: '#666',
		        labels: {
		            step: 2,
		            rotation: 'auto'
		        },
		        title: {
		            text: '*1000r/min'
		        },
		        plotBands: [{
		            from: 0,
		            to: 3,
		            color: '#55BF3B' // green
		        }, {
		            from: 3,
		            to: 5,
		            color: '#DDDF0D' // yellow
		        }, {
		            from: 5,
		            to: 10,
		            color: '#DF5353' // red
		        }]        
		    },
	
		    series: [{
		        name: 'Revolution',
		        data: [0.00],
		        tooltip: {
		            valueSuffix: ' *1000r/min'
		        }
		    }]
	
		}, 
		
		// Add some life
		function (chart) {
			if (!chart.renderer.forExport) {
				 setInterval(function () {
				        var point1 = chart.series[0].points[0];
				    	var $params="useremail=" + useremail + "&parameter_name=发动机转速";
						$.ajax({
							type:'GET',
							contentType: 'application/json',  
							url:"personal/refreshParameter.html",
							data: $params,
							dataType: "json",
							success:function(data){
								if (data && data.success == "true") {
							        point1.update(data.answer);
								}
								else{
									point1.update(0);
								}
							},
							error:function(){
								point1.update(0);
							}
						});
				        
				        
				    }, 3000);
			}
		});		
		$('#container3').highcharts({
	
	    	chart: {
	       		type: 'gauge',
	        	plotBackgroundColor: null,
		        plotBackgroundImage: null,
		        backgroundColor: 'rgba(0,0,0,0)',
		        plotBorderWidth: 0,
	        	plotShadow: false
	    	},
	    	
	    	exporting: {
	    		enabled: false
	    	},
	    	
	    	credits: {
	            enabled: false
	        },
        
		    title: {	
		        text: '电压',
		        style: {color: 'white', fontSize: '16px'}
		    },
	    
		    pane: {
		        startAngle: -150,
		        endAngle: 150,
		        background: [{
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#FFF'],
		                    [1, '#333']
		                ]
		            },
		            borderWidth: 0,
		            outerRadius: '109%'
		        }, {
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#333'],
		                    [1, '#FFF']
		                ]
		            },
		            borderWidth: 1,
		            outerRadius: '107%'
		        }, {
		            // default background
		        }, {
		            backgroundColor: '#DDD',
		            borderWidth: 0,
		            outerRadius: '105%',
		            innerRadius: '103%'
		        }]
		    },
	       
		    // the value axis
		    yAxis: {
		        min: 0,
		        max: 20,
		        
		        minorTickInterval: 'auto',
		        minorTickWidth: 1,
		        minorTickLength: 10,
		        minorTickPosition: 'inside',
		        minorTickColor: '#666',
		
		        tickPixelInterval: 30,
		        tickWidth: 2,
		        tickPosition: 'inside',
		        tickLength: 10,
		        tickColor: '#666',
		        labels: {
		            step: 2,
		            rotation: 'auto'
		        },
		        title: {
		            text: 'V'
		        },
		        plotBands: [{
		            from: 0,
		            to: 11,
		            color: '#DF5353' // red
		        }, {
		            from: 11,
		            to: 16,
		            color: '#55BF3B' // green
		        }, {
		            from: 16,
		            to: 20,
		            color: '#DF5353' // red
		        }]        
		    },
	
		    series: [{
		        name: 'Voltage',
		        data: [0.0],
		        tooltip: {
		            valueSuffix: ' V'
		        }
		    }]
	
		}, 
		
		// Add some life
		function (chart) {
			if (!chart.renderer.forExport) {
				 setInterval(function () {
				        var point1 = chart.series[0].points[0];
				    	var $params="useremail=" + useremail + "&parameter_name=电池电压";
						$.ajax({
							type:'GET',
							contentType: 'application/json',  
							url:"personal/refreshParameter.html",
							data: $params,
							dataType: "json",
							success:function(data){
								if (data && data.success == "true") {
							        point1.update(data.answer);
								}
								else{
									point1.update(0);
								}
							},
							error:function(){
								point1.update(0);
							}
						});
				        
				        
				    }, 3000);
			}
		});		
		$('#container4').highcharts({
	
	    	chart: {
	       		type: 'gauge',
	        	plotBackgroundColor: null,
		        plotBackgroundImage: null,
		        backgroundColor: 'rgba(0,0,0,0)',
		        plotBorderWidth: 0,
	        	plotShadow: false
	    	},
	    	
	    	exporting: {
	    		enabled: false
	    	},
	    	
	    	credits: {
	            enabled: false
	        },
        
		    title: {	
		        text: '发动机冷却液温度',
		        style: {color: 'white', fontSize: '16px'}
		    },
	    
		    pane: {
		        startAngle: -150,
		        endAngle: 150,
		        background: [{
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#FFF'],
		                    [1, '#333']
		                ]
		            },
		            borderWidth: 0,
		            outerRadius: '109%'
		        }, {
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#333'],
		                    [1, '#FFF']
		                ]
		            },
		            borderWidth: 1,
		            outerRadius: '107%'
		        }, {
		            // default background
		        }, {
		            backgroundColor: '#DDD',
		            borderWidth: 0,
		            outerRadius: '105%',
		            innerRadius: '103%'
		        }]
		    },
	       
		    // the value axis
		    yAxis: {
		        min: 0,
		        max: 120,
		        
		        minorTickInterval: 'auto',
		        minorTickWidth: 1,
		        minorTickLength: 10,
		        minorTickPosition: 'inside',
		        minorTickColor: '#666',
		
		        tickPixelInterval: 30,
		        tickWidth: 2,
		        tickPosition: 'inside',
		        tickLength: 10,
		        tickColor: '#666',
		        labels: {
		            step: 2,
		            rotation: 'auto'
		        },
		        title: {
		            text: '°C'
		        },
		        plotBands: [{
		            from: 0,
		            to: 70,
		            color: '#DF5353' // red
		        }, {
		        	from: 70,
		            to: 80,
		            color: '#DDDF0D' // yellow
		        }, {
		            from: 80,
		            to: 95,
		            color: '#55BF3B' // green
		        }, {
		        	from: 95,
		            to: 105,
		            color: '#DDDF0D' // yellow
		        }, {
		            from: 105,
		            to: 120,
		            color: '#DF5353' // red
		        }]        
		    },
	
		    series: [{
		        name: 'Temperature',
		        data: [0],
		        tooltip: {
		            valueSuffix: ' °C'
		        }
		    }]
	
		}, 
		
		// Add some life
		function (chart) {
			if (!chart.renderer.forExport) {
				 setInterval(function () {
				        var point1 = chart.series[0].points[0];
				    	var $params="useremail=" + useremail + "&parameter_name=发动机冷却液温度";
						$.ajax({
							type:'GET',
							contentType: 'application/json',  
							url:"personal/refreshParameter.html",
							data: $params,
							dataType: "json",
							success:function(data){
								if (data && data.success == "true") {
							        point1.update(data.answer);
								}
								else{
									point1.update(0);
								}
							},
							error:function(){
								point1.update(0);
							}
						});
				        
				        
				    }, 3000);
			}
		});		
		$('#container5').highcharts({
	
	    	chart: {
	       		type: 'gauge',
	        	plotBackgroundColor: null,
		        plotBackgroundImage: null,
		        backgroundColor: 'rgba(0,0,0,0)',
		        plotBorderWidth: 0,
	        	plotShadow: false
	    	},
	    	
	    	exporting: {
	    		enabled: false
	    	},
	    	
	    	credits: {
	            enabled: false
	        },
        
		    title: {	
		        text: '发动机进气温度',
		        style: {color: 'white', fontSize: '16px'}
		    },
	    
		    pane: {
		        startAngle: -90,
		        endAngle: 180,
		        background: [{
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#FFF'],
		                    [1, '#333']
		                ]
		            },
		            borderWidth: 0,
		            outerRadius: '109%'
		        }, {
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#333'],
		                    [1, '#FFF']
		                ]
		            },
		            borderWidth: 1,
		            outerRadius: '107%'
		        }, {
		            // default background
		        }, {
		            backgroundColor: '#DDD',
		            borderWidth: 0,
		            outerRadius: '105%',
		            innerRadius: '103%'
		        }]
		    },
	       
		    // the value axis
		    yAxis: {
		        min: 0,
		        max: 90,
		        
		        minorTickInterval: 'auto',
		        minorTickWidth: 1,
		        minorTickLength: 10,
		        minorTickPosition: 'inside',
		        minorTickColor: '#666',
		
		        tickPixelInterval: 30,
		        tickWidth: 2,
		        tickPosition: 'inside',
		        tickLength: 10,
		        tickColor: '#666',
		        labels: {
		            step: 2,
		            rotation: 'auto'
		        },
		        title: {
		            text: '°C'
		        },
		        plotBands: [{
		            from: 0,
		            to: 30,
		            color: '#DF5353' // red
		        }, {	        	
		            from: 30,
		            to: 60,
		            color: '#55BF3B' // green
		        }, {
		            from: 60,
		            to: 90,
		            color: '#DF5353' // red
		        }]        
		    },
	
		    series: [{
		        name: 'AirTemperature',
		        data: [0],
		        tooltip: {
		            valueSuffix: ' °C'
		        }
		    }]
	
		}, 
		
		// Add some life
		function (chart) {
			if (!chart.renderer.forExport) {
				 setInterval(function () {
				        var point1 = chart.series[0].points[0];
				    	var $params="useremail=" + useremail + "&parameter_name=进气温度";
						$.ajax({
							type:'GET',
							contentType: 'application/json',  
							url:"personal/refreshParameter.html",
							data: $params,
							dataType: "json",
							success:function(data){
								if (data && data.success == "true") {
							        point1.update(data.answer);
								}
								else{
									point1.update(0);
								}
							},
							error:function(){
								point1.update(0);
							}
						});
				        
				        
				    }, 3000);
			}
		});		
		$('#container6').highcharts({
	
	    	chart: {
	       		type: 'gauge',
	        	plotBackgroundColor: null,
		        plotBackgroundImage: null,
		        backgroundColor: 'rgba(0,0,0,0)',
		        plotBorderWidth: 0,
	        	plotShadow: false
	    	},
	    	
	    	exporting: {
	    		enabled: false
	    	},
	    	
	    	credits: {
	            enabled: false
	        },
        
		    title: {	
		        text: '燃油液位输入',
		        style: {color: 'white', fontSize: '16px'}
		    },
	    
		    pane: {
		        startAngle: -150,
		        endAngle: 150,
		        background: [{
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#FFF'],
		                    [1, '#333']
		                ]
		            },
		            borderWidth: 0,
		            outerRadius: '109%'
		        }, {
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#333'],
		                    [1, '#FFF']
		                ]
		            },
		            borderWidth: 1,
		            outerRadius: '107%'
		        }, {
		            // default background
		        }, {
		            backgroundColor: '#DDD',
		            borderWidth: 0,
		            outerRadius: '105%',
		            innerRadius: '103%'
		        }]
		    },
	       
		    // the value axis
		    yAxis: {
		        min: 0,
		        max: 100,
		        
		        minorTickInterval: 'auto',
		        minorTickWidth: 1,
		        minorTickLength: 10,
		        minorTickPosition: 'inside',
		        minorTickColor: '#666',
		
		        tickPixelInterval: 30,
		        tickWidth: 2,
		        tickPosition: 'inside',
		        tickLength: 10,
		        tickColor: '#666',
		        labels: {
		            step: 2,
		            rotation: 'auto'
		        },
		        title: {
		            text: '%'
		        },
		        plotBands: [{
		            from: 0,
		            to: 15,
		            color: '#DF5353' // red
		        }, {	        	
		            from: 15,
		            to: 90,
		            color: '#DDDF0D' // yellow
		        }, {
		            from: 90,
		            to: 100,
		            color: '#55BF3B' // green
		        }]        
		    },
	
		    series: [{
		        name: 'Oil',
		        data: [0.00],
		        tooltip: {
		            valueSuffix: ' %'
		        }
		    }]
	
		}, 
		
		// Add some life
		function (chart) {
			if (!chart.renderer.forExport) {
				 setInterval(function () {
				        var point1 = chart.series[0].points[0];
				    	var $params="useremail=" + useremail + "&parameter_name=燃油液位输入";
						$.ajax({
							type:'GET',
							contentType: 'application/json',  
							url:"personal/refreshParameter.html",
							data: $params,
							dataType: "json",
							success:function(data){
								if (data && data.success == "true") {
							        point1.update(data.answer);
								}
								else{
									point1.update(0);
								}
							},
							error:function(){
								point1.update(0);
							}
						});
				        
				        
				    }, 3000);
			}
		});		
	});