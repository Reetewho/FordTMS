<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AP Transport Center | Calendar</title>
  <%@ include file="/WEB-INF/include/cssInclude.jsp" %>
  <style>
  .fc-title{
	  position: absolute;
	  padding-top:0px;
	  left: 40%;
	  font-size:1.50em;
  }
  </style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <%@ include file="/WEB-INF/include/header.jsp" %>
  <%@ include file="/WEB-INF/include/rightMenu.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
       Calendar
        <small></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fas fa-tachometer-alt"></i> &nbsp;&nbsp;Home</a></li>
        <li><a href="#">Calendar</a></li>        
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
     <div class="row" style="margin-bottom:20px"> 
          	<div class="col-md-2">
          	</div>
			<label  class="col-md-1 control-label">Month : </label>
				<div class="col-md-2">  
					<select id="lstMonth" name="lstMonth" class="form-control">
						<c:if test = "${not empty monthLst}">
						<c:forEach items="${monthLst}" var="monthValue">
						<option value="${monthValue}" ${chooseMonth==monthValue?'selected':''} >
						${monthValue}
						</option>
						</c:forEach>
						</c:if>  
					</select>               
				</div>        
			<label  class="col-md-1 control-label">Year : </label>
				<div class="col-md-2">  
					<select id="lstYear" name="lstYear" class="form-control">
						<c:if test = "${not empty yearLst}">
						<c:forEach items="${yearLst}" var="yearValue">
						<option value="${yearValue}" ${chooseYear==yearValue?'selected':''} >
						${yearValue}
						</option>
						</c:forEach>
						</c:if>  
					</select>               
				</div>
			<div class="col-md-2">
				<button type="button" id="findLoadData">LoadData</button>
			</div>
	        <div class="col-md-2">
          	</div>
      </div>
      <div class="row">
      <!-- /.col -->
        <div class="col-md-12">
          <div class="box box-primary">
          	
            <div class="box-body no-padding">
              <!-- THE CALENDAR -->
              <div id="calendar"></div>
            </div>
           
            <!-- /.box-body -->
          </div>
          <!-- /. box -->
        </div>        
        <!-- /.col -->
      </div>      
      <!-- /.row -->
    </section>
    <!-- /.content -->
    
  </div>
  <!-- /.content-wrapper -->  
  
  <%@ include file="/WEB-INF/include/footer.jsp" %>
  
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/include/jsInclude.jsp" %>

<!-- page script -->
<script>
var _y=${_year};
var _m=${_month};
var _d=${_date};
var dayclick=false;
var options={
		 customButtons: { 
	    	  myCustomButton: { 
	    		  text: 'Custom', 
	    		  click: function() { 
	    			  alert('Clicked the custom button'); 
	    		  } 
	      	   } 
	      },
		 header: {
		        left: 'myCustomButton prev,next',
		        center: 'title',
		        right: ''
		  },   	     
	      timeFormat: ' ',
	      dayClick: function(date, jsEvent, view) {
			  if(!dayclick){
			    window.location.href = "<c:url value='/load-list/"+date.format()+"' />";
			    dayclick=true;
			  }
				//alert('Clicked on: ' + date.format());
		   },
		   events: [
			   <c:if test = "${not empty carriers}">
			  	<c:forEach items="${carriers}" var="carriers">
			  	<fmt:parseDate value="${carriers.loadDate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="loadDate" type="both" />
			  	{
			  		title: '${carriers.numOfLoad}',
		           start: new Date(<fmt:formatDate pattern="yyyy" value="${ loadDate }" />, <fmt:formatDate pattern="MM" value="${ loadDate }" />-1,<fmt:formatDate pattern="dd" value="${ loadDate }" />)
			  	},
			  	</c:forEach>
			  </c:if> 
			   ],
		  eventBackgroundColor :"#ffffff",
		  eventBorderColor :"#ffffff",
		  eventTextColor :"#f56954",
	      editable: false,
	      droppable: false,
		  defaultDate: _y+'-'+_m+'-'+(_d+14)
	    };


 function myFunction() {
	 var getDatePicker = $("#date_picker").datepicker("getDate");
     console.log( 'Date Picker : ' + getDatePicker);
     var cdate1 = new Date(getDatePicker.getFullYear(), getDatePicker.getMonth()+1, getDatePicker.getDate());
 	 cdate1.setDate(0);
 	 console.log("End : " + cdate1);
 	 var endDate=getDateString(cdate1);
 	 cdate1.setDate(1);
 	 console.log("Start : " + cdate1);
 	 var startDate=getDateString(cdate1);

     //$('#calendar').fullCalendar('gotoDate', d);
     
     window.location.href = "<c:url value='/calendar/"+startDate+"/"+endDate+"' />";
	}
 
 

 var dataListMoth = [
   "January",
   "February",
   "March",
   "April",
   "May",
   "June",
   "July",
   "August",
   "September",
   "October",
   "November",
   "December"
 ];
 
 function getDateString(date){
	  return  date.getFullYear()+"-"+((date.getMonth()+1)<10?"0"+(date.getMonth()+1):(date.getMonth()+1))+"-"+(date.getDate()<10?"0"+date.getDate():date.getDate());
 }
 
  $(function () {
	  
	  //For Click ListMonth and Year
	  $( '#findLoadData' ).on( 'click', function() {
		 	var selectedMonth = $("#lstMonth option:selected").text();
		    var posMonth = dataListMoth.indexOf(selectedMonth.trim());
		    posMonth = posMonth+1;
		    var selectedYear = $("#lstYear option:selected").text();
		    //alert("Finb Year : " + sltYear); 
		     var cdate1 = new Date(selectedYear.trim(), posMonth, 1);
		
		 	 cdate1.setDate(0);
		 	 //console.log("End : " + cdate1);
		 	 var endDate=getDateString(cdate1);
		 	 cdate1.setDate(1);
		 	 //console.log("Start : " + cdate1);
		 	 var startDate=getDateString(cdate1);
		 	 
		 	 
		 	//console.log("StartDate" + startDate + " AND EndDate" + endDate);
		 	window.location.href = "<c:url value='/calendar/"+startDate+"/"+endDate+"' />";
		 });
	  
	  
	// for the date picker 
		var today = new Date();
		var day = today.getDate();
		var month = today.getMonth() + 1; //As January is 0.
		var year = today.getFullYear();
		if(day<10) day='0'+day;
		if(month<10) month='0'+month;
		//var formated = year + "-" + month + "-" + day;
		
		//console.log("Test Today : " + year + "-" + month + "-" + day);
	  $("#date_picker").datepicker({
			 autoclose: true,
			 format: 'yyyy-mm-dd',
			 date: year + "-" + month + "-" + day,
		     current: year + "-" + month + "-" + day,
	         changeMonth: true,
	         changeYear: true,
	         onSelect: function(dateText, inst) {
	             var d = $("#date_picker").datepicker("getDate");
	             console.log( 'Date Picker : ' + d);
	             $('#calendar').fullCalendar('gotoDate', d);
	             $(this).change();
	         }
	     }).on("change", function() {
	    	 myFunction();
	     });
	  
	
	  
	  
	  $( '#setDate' ).on( 'click', function() {
		    console.log( 'Going to ' + $(this).data( 'date' ) );
		    $( '#calendar' ).fullCalendar( 'gotoDate', $(this).data( 'date' ) );
		    console.log( 'Now at ' + $( '#calendar' ).fullCalendar( 'getDate' ).format( "YYYY-MM-DD") );
		    // Or
		    var newDate = new moment( $(this).data( 'date' ) );
		      console.log( 'Trying with moment.js to get to ' + $(this).data( 'date' ) );
		      $( '#calendar' ).fullCalendar( 'goToDate', newDate );
		      console.log( 'And we\'re at ' + $( '#calendar' ).fullCalendar( 'getDate' ).format( "YYYY-MM-DD" ) );
		      
		      console.log( 'v1 style' );
		  $( '#calendar' ).fullCalendar( 'goToDate', 2016, 0, 1 );
		      console.log( 'And we\'re at ' + $( '#calendar' ).fullCalendar( 'getDate' ).format( "YYYY-MM-DD" ) );
		    });   
		    

	
	  /* initialize the calendar
	     -----------------------------------------------------------------*/
	    //Date for the calendar events (dummy data)
	    
	    $('#calendar').fullCalendar(options);
	   
	    $('.fc-prev-button').click(function(){			  
	    	var cdate1 = new Date(_y,_m-1,_d);
			   cdate1.setDate(0);			   			  
			   var endDate=getDateString(cdate1);			   
			   cdate1.setDate(1);
			   var startDate=getDateString(cdate1);			   	
			   window.location.href = "<c:url value='/calendar/"+startDate+"/"+endDate+"' />";
		});

		$('.fc-next-button').click(function(){
			 var cdate2 = new Date(_y,_m-1,_d);			   
			   cdate2.setMonth(cdate2.getMonth()+2);
			   cdate2.setDate(0);
			   var endDate=getDateString(cdate2);
			   cdate2.setDate(1);
			   var startDate=getDateString(cdate2);
			   window.location.href = "<c:url value='/calendar/"+startDate+"/"+endDate+"' />";
		});
  });
  
  function getDateString(date){
	  return  date.getFullYear()+"-"+((date.getMonth()+1)<10?"0"+(date.getMonth()+1):(date.getMonth()+1))+"-"+(date.getDate()<10?"0"+date.getDate():date.getDate());
  }
</script>
</body>
</html>
