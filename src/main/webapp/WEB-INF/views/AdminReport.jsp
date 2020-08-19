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
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <%@ include file="/WEB-INF/include/header.jsp" %>
  <%@ include file="/WEB-INF/include/rightMenu.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
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
	     	<form method="POST" action="<c:url value='/AdminReport' />" data-toggle="validator" role="form" >        
	        <div class="col-md-2" ></div>
	        <div class="col-md-3">
     			<div class="form-group  input-group date">
	                <div class="input-group-addon">
	                	<i class="fa fa-calendar"></i> Start Date : 
	                </div>
                	<input type="text" class="form-control pull-right datepicker" name="startDate" id="startDate" value="${startDate}" data-error="Please Select Start Date" required="required"/>  
				</div>
	        </div>        
	        <div class="col-md-1" ></div>
	        <div class="col-md-3">
   				<div class="form-group  input-group date">
                	<div class="input-group-addon">
                		<i class="fa fa-calendar"></i> End Date : 
                	</div>
                	<input type="text" class="form-control pull-right datepicker" name="endDate" id="endDate" value="${endDate}" data-error="Please Select End Date" required="required"/>
				</div>
	        </div>
	        <div class="col-md-1" style="padding-top:3px"><input type="submit" value="search"/></div>
	       <div class="col-md-2"></div>
	       </form>
	     </div>
        
        <div class="row">
	        <div class="col-md-3">
	          <div class="box box-default box-solid">
	            <div class="box-header ">
	              <h3 class="box-title">Assigned</h3>
	              <div class="box-tools pull-right">
	                <span  id="totalAssigned"  style="font-size:16px;font-weight: 600;">-</span>
	              </div>
	              <!-- /.box-tools -->
	            </div>
	            <!-- /.box-header -->           
	            <!-- /.box-body -->
	          </div>
	          <!-- /.box -->
	        </div>
	        <!-- /.col -->
	        <div class="col-md-3">
	          <div class="box box-warning box-solid">
	            <div class="box-header ">
	              <h3 class="box-title">Checked- In</h3>
	              <div class="box-tools pull-right">
	                <span  id="totalCheckedIn" class="badge bg-yellow" style="font-size:16px;font-weight: 600;">-</span>
	              </div>
	              <!-- /.box-tools -->
	            </div>
	            <!-- /.box-header -->           
	            <!-- /.box-body -->
	          </div>
	          <!-- /.box -->
	        </div>
	        <!-- /.col -->
	        
	        <div class="col-md-3">
	          <div class="box box-primary box-solid">
	            <div class="box-header ">
	              <h3 class="box-title">Ontime</h3>
	              <div class="box-tools pull-right">
	                <span  id="totalOntime"  style="font-size:16px;font-weight: 600;">-</span>
	              </div>
	              <!-- /.box-tools -->
	            </div>
	            <!-- /.box-header -->            
	            <!-- /.box-body -->
	          </div>
	          <!-- /.box -->
	        </div>
	        <!-- /.col -->
	        
	        <div class="col-md-3">
	          <div class="box box-info box-solid">
	            <div class="box-header ">
	              <h3 class="box-title">Delayed</h3>
	              <div class="box-tools pull-right">
	                <span  id="totalDelayed" class="badge bg-aqua" style="font-size:16px;font-weight: 600;">-</span>
	              </div>
	              <!-- /.box-tools -->
	            </div>
	            <!-- /.box-header -->            
	            <!-- /.box-body -->
	          </div>
	          <!-- /.box -->
	        </div>       
	     	<!-- /.col -->
	     </div>
	     <!-- /.row -->
        
        
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
                  	<th >Arrive Plan</th> 
                  	<th >Arrive Actual</th>	
                  	<th >Arrive Status</th> 
					<th >Departure Plan</th>
					<th >Departure Actual</th>
					<th >Departure Status</th>
					<th >Last Update</th>
					<th >Truck Number</th>	
					<th >Driver Name</th>	
					<th >Tel</th>
					<th >Check In Status</th>
					<th >Latitude</th>
					<th >Longitude</th>  
					<th >Estimated Date Time At Stop</th>
					<th >Movement Date Time</th>
					<th >Confrimed</th>
					<th >User Assigned</th>
					<th >User Re-Assigned</th>
					<th >User Updated</th>
					<th >Remark</th>
					
					
					
					
					<!-- <th >Load Start Date Time</th>
					<th >Load End Date Time</th> -->
					               
                </tr>
                </thead>
                <tbody>                
		                <c:set var="assigned" value="${0}"/>
		                <c:set var="CheckedIn" value="${0}"/>
		                <c:set var="Ontime" value="${0}"/>	
		                <c:set var="Delayed" value="${0}"/>	                
	               <c:if test = "${not empty report}">
						<c:forEach items="${report}" var="report">
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
												<td></td>
											</c:otherwise>
							</c:choose>			
							
							
							<c:choose>
								<c:when test="${report.completedFlag=='update'}">
									<td>Ontime</td>
								</c:when>										 
									<c:when test="${report.completedFlag=='setStop'}">
										<td>Delay</td>
									</c:when> 									
											<c:otherwise>
												<td></td>
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
												<td></td>
											</c:otherwise>
							</c:choose>	
							
							<c:choose>
								<c:when test="${report.completedFlag=='update'}">
									<td>Ontime</td>
								</c:when>										 
									<c:when test="${report.completedFlag=='setStop'}">
										<td>Delay</td>
									</c:when> 									
											<c:otherwise>
												<td></td>
											</c:otherwise>
							</c:choose>
							<td>${report.lastUpdateDate}</td>	
							<td>${report.truckNumber}</td>	
							<td>${report.driverId}</td>	
							<td>${report.contactnumber}</td>				
							<td>${report.completedFlag}</td>		
							<td>${report.latitude}</td>
							<td>${report.longitude}</td>
							<td>${report.estimatedDateTime}</td>
							<td>${report.movementDateTime}</td>	
							<td>${report.completedFlag}</td>
							<td>${report.assignname}</td>
							<td>${report.assignname}</td>
							<td>${report.lastUpdateUser}</td>
							<td>${report.loadstopremark}</td>
												
													
							<%-- <td>${report.loadStartDateTime}</td>				
							<td>${report.loadEndDateTime}</td> --%>

								<c:if test = "${report.driverId!=''}"> <c:set var="assigned" value="${assigned+1}"/>  </c:if>							
								<c:if test = "${report.arriveTime!=''}"> <c:set var="CheckedIn" value="${CheckedIn+1}"/>  </c:if>
								<c:if test = "${report.completedFlag=='update'}"> <c:set var="Ontime" value="${Ontime+1}"/>  </c:if>
								<c:if test = "${report.completedFlag=='setStop'}"> <c:set var="Delayed" value="${Delayed+1}"/>  </c:if>
								
							</tr>
						</c:forEach>
					</c:if>    
                </tbody>                
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
	  
	  
	  	$("#reportTable").DataTable({
	  		dom: "<'row'<'col-sm-2'l><'col-sm-7'B><'col-sm-3'f>>" +
	        "<'row'<'col-sm-12'tr>>" +
	        "<'row'<'col-sm-5'i><'col-sm-7'p>>",
	        buttons: [{extend: 'excelHtml5',text: 'Export To Excel',filename: 'Report_'+d}],
	        scrollX: true
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
