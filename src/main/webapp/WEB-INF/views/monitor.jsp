<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>AP Transport Center | Assign Status </title>
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
	       Assign Status
	        <small></small>
	      </h1>
	      <ol class="breadcrumb">
	        <li><a href="#"><i class="fas fa-tachometer-alt"></i> &nbsp;&nbsp;Home</a></li>
	        <li><a href="#' />">Assign Status</a></li>                 
	      </ol>
	    </section>
	
	    <!-- Main content -->
	    <section class="content">  
	    <div class="row" style="margin-bottom:20px"> 
	     	<form method="POST" action="<c:url value='/monitor' />" id="FormFilter" data-toggle="validator" role="form" >        
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
	     
		  <div class="row" >
	        <div class="col-md-3">
	          <div class="box box-danger box-solid">
	            <div class="box-header ">
	              <h3 class="box-title">Pending</h3>
	              <div class="box-tools pull-right">
	                <span  id="totalPending"   class="badge bg-red" style="font-size:16px;font-weight: 600;">-</span>
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
	          <div class="box box-warning box-solid" >
	            <div class="box-header ">
	              <h3 class="box-title">Assigned</h3>
	              <div class="box-tools pull-right">
	                <span  id="totalAssigned" class="badge bg-yellow" style="font-size:16px;font-weight: 600;">-</span>
	              </div>
	              <!-- /.box-tools -->
	            </div>
	            <!-- /.box-header -->           
	            <!-- /.box-body -->
	          </div>
	          <!-- /.box -->
	        </div>	        
	        <div class="col-md-3">
	          <div class="box box-success box-solid">
	            <div class="box-header ">
	              <h3 class="box-title">Completed</h3>
				  <div class="box-tools pull-right">
	                <span  id="completed" class="badge bg-green" style="font-size:16px;font-weight: 600;">-</span>
	              </div>
	            </div>
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
		                  <th>NO.</th>
		                  <th>Load ID</th>
		                  <th>Alert Type Code</th>
		                  <th>Route No.</th>		                     
		                  <th>Assign Date Time</th> 
		                  <th>Assign To</th>   
		                  <th>Load Start Date Time</th>
		                  <th>Load End Date Time</th>             
		                  <th>Status</th>          
	                </tr>
	                </thead>
	                <tbody>
		                <c:set var="PendingStatus" value="${0}"/>
		                <c:set var="AssignedStatus" value="${0}"/>
		                <c:set var="completed" value="${0}"/>
			            <c:if test = "${not empty Monitorstatus}">
							<c:forEach items="${Monitorstatus}" var="LMonitorstatus">
								<tr >
								<td>${i=i+1}</td>
								<td>${LMonitorstatus.systemLoadID}</td>
								<td>${LMonitorstatus.alertTypeCode}</td>
								<td>${LMonitorstatus.loadDescription}</td>
								<td>${LMonitorstatus.dateassign}</td>
								<td>${LMonitorstatus.driverid}</td>
								<td>${LMonitorstatus.loadStartDateTime}</td>
								<td>${LMonitorstatus.loadEndDateTime}</td>								
								<td>${LMonitorstatus.status}</td>
								<c:if test = "${LMonitorstatus.status=='N/A' || LMonitorstatus.status=='Load'}"> <c:set var="PendingStatus" value="${PendingStatus+1}"/>  </c:if>
								<c:if test = "${LMonitorstatus.dateassign!=null}"> <c:set var="AssignedStatus" value="${AssignedStatus+1}"/>  </c:if>							
								<c:if test = "${LMonitorstatus.status=='Completed'}"> <c:set var="completed" value="${completed+1}"/>  </c:if>
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
		  		dom:  "<'row'<'col-sm-2'l><'col-sm-7'B><'col-sm-3'f>>" + 
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
		  	 
		    $("#totalPending").html(' <c:out value = "${PendingStatus}"/> ');
		    $("#totalAssigned").html(' <c:out value = "${AssignedStatus}"/> ');
		    $("#completed").html(' <c:out value = "${completed}"/> ');
	  });		  
	</script>
</body>
</html>