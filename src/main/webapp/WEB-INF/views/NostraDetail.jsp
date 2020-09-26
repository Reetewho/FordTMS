<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AP Transport Center | Operation Report </title>
  <%@ include file="/WEB-INF/include/cssInclude.jsp" %>
</head>
<style>
#events {
        margin-bottom: 1em;
        padding: 1em;
        background-color: #f6f6f6;
        border: 1px solid #999;
        border-radius: 3px;
        height: 100px;
        overflow: auto;
    }
    </style>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <%@ include file="/WEB-INF/include/header.jsp" %>
  <%@ include file="/WEB-INF/include/rightMenu.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
     <div class="row">
      	<div class="col-md-12">
      		<c:if test="${Warning!=null || Success!=null }">
              <div class='alert ${Warning!=null?"alert-warning":"alert-success"}  alert-dismissible'>
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4><i class="icon fa  ${Warning!=null?'fa-ban':'fa-check'}"></i>${Warning!=null?'Warning!':'Success'} </h4>
                <c:out value="${Warning!=null?Warning:Success} "></c:out>
              </div>
            </c:if>              
      	</div>
      </div>
      <h1>
       Operation Report
        <small></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fas fa-tachometer-alt"></i> &nbsp;&nbsp;Home</a></li>
        <li><a href="#' />">Operation Report</a></li>                 
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">  
     	<div class="row" style="margin-bottom:20px"> 
	     	<form method="POST" action="<c:url value='/Nostra' />" data-toggle="validator" role="form" > 
	     	</form>       
      <div class="row">
      <!-- /.col -->
        <div class="col-md-12">
          <div class="box box-primary">            	      	
            <div class="box-body ">
              <table id="reportTable" class="table table-bordered table-striped" style="width : 100% ">
                <thead>
                <tr>
                  	<th >Load ID</th>
                  	<th >Route No.</th>
                  	<th >Pickup GSDB</th>
                  	<th >Pickup Supplier Name</th>
                  	<!-- <th >Truck Number</th> -->	
                  	<!-- <th >Jobs Number</th> -->	
                  	<!-- <th >Yard</th> -->	
                  	<th >Arrive Plan</th> 
                  	<th >Arrive Actual</th>	
					<th >Departure Plan</th>
					<th >Departure Actual</th>
					<th >ETA</th>
					<th >Color</th>
                  	<!-- <th >Driver Name</th> -->
                  	<!-- <th >Last Update</th> -->                 	
					<th >Latitude</th>
					<th >Longitude</th>
				
					<th style="display:none;"></th>
					<th style="display:none;"></th>
					        	       
                </tr>
                </thead>
                <tbody>                		                      
	               <c:if test = "${not empty lsLoadID}">
						<c:forEach items="${lsLoadID}" var="report">
							<tr >
							<td>${report.systemLoadID}</td> 
							<td>${report.loadDescription}</td> 
							<td>${report.stopShippingLocation}</td> 
							<td>${report.stopShippingLocationName}</td>
							<td>${report.arriveTime}</td> 
							<c:choose> 
								<c:when test="${report.completedFlag=='update'}">
									<td>${report.arriveTime}</td>
								</c:when>										 
								<c:when test="${report.completedFlag=='setStop'}">
									<td>${report.movementDateTime}</td>
								</c:when> 									
								<c:otherwise>
									<td>${report.actualStartDate}</td>
								</c:otherwise>
							</c:choose>													
							<td>${report.departureTime}</td> 						
							<c:choose> 
								<c:when test="${report.completedFlag=='update'}">
									<td>${report.departureTime}</td>
								</c:when>										 
								<c:when test="${report.completedFlag=='setStop'}">
									<td>${report.estimatedDateTime}</td>
								</c:when> 									
								<c:otherwise>
									<td>${report.actualEndDate}</td>
								</c:otherwise>
							</c:choose>				
							<td>${report.etaDate}</td>
							<td></td>							
							<td>${report.latitude}</td>
							<td>${report.longitude}</td>		
							<td style="display:none;">${report.id}</td>
							<td style="display:none;">${report.loadID}</td>
							</tr>
						</c:forEach>
					</c:if> 
                </tbody>          
                	<%-- <a href="<c:url value='/loadStop-list/${loadDate}/${load.systemLoadID}-${load.loadID}' />"><button type="button" class="btn btn-default">Cancel</button></a> --%>					                        
              </table>
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
var strStartDate='${startDate}';
var strEndDate='${endDate}';
var sd = strStartDate.split("-");
var ed=strEndDate.split("-");
var d = sd[2]+sd[1]+sd[0]+' - '+ed[2]+ed[1]+ed[0];

  $(function () {	  	 
	   var events = $('#events');
	   var table =	$("#reportTable").DataTable({
		   
	  		dom: "<'row'<'col-sm-2'l><'col-sm-7'B><'col-sm-3'f>>" +
	        "<'row'<'col-sm-12'tr>>" +
	        "<'row'<'col-sm-5'i><'col-sm-7'p>>",
	        buttons: [  ], scrollX: true          	            
	            }); 
	
	  	 //Date picker
	    $('.datepicker').datepicker({
	      autoclose: true,
	      format: 'yyyy-mm-dd'
	    });
	  	 
	    
	    $("#totalAssigned").html(' <c:out value = "${assigned}"/> ');
	    $("#totalCheckedIn").html(' <c:out value = "${CheckedIn}"/> ');
	    $("#totalOntime").html(' <c:out value = "${Ontime}"/> ');
	    $("#totalDelayed").html(' <c:out value = "${Delayed}"/> ');
	  	 
	  
  });
</script>
</body>
</html>
