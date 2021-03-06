<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AP Transport Center | Load Stop List</title>
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
       Load Stop List
        <small></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fas fa-tachometer-alt"></i> &nbsp;&nbsp;Home</a></li>
        <li>
        <c:choose>
        			<c:when test="${S_FordUser.role=='1'}">
						<a href="<c:url value='/calendar/${loadDate}' />">Calendar</a>
					</c:when>
						<c:when test="${S_FordUser.role=='2'}">
							<a href="<c:url value='/load-list-drivers/${S_FordUser.username}' />">Load List</a>
						</c:when>
        	</c:choose>
        	
        </li>
        <li>
         	<c:choose>
        			<c:when test="${S_FordUser.role=='1'}">
        				<a href="<c:url value='/load-list/${loadDate}'/>">${loadDate}</a>
        			</c:when>
        				<c:when test="${S_FordUser.role=='2'}"></c:when>
        	</c:choose>
        </li>        
        <li><a href="#">${load.systemLoadID}</a></li> 
        <li><a href="#">Load Stop list</a></li>         
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">   
      <div class="row">
      <!-- /.col -->
        <div class="col-md-12">
          <div class="box box-primary">
          	<div class="box-header">
	            <c:if test="${Error!=null}">
	              <div class="alert alert-danger alert-dismissible">
	                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
	                <h4><i class="icon fa fa-ban"></i> Error!</h4>
	                <c:out value="${Error}"></c:out>
	              </div>
	            </c:if>
            </div>
            <!-- /.box-header -->
            <div class="box-body ">
              <table id="loadStopTable" class="table table-bordered table-striped" style="width : 100% ">
                <thead>
                <tr>
                  	<th >Stop Sequence</th>
					<th >Route No.</th>
					<th >Pickup GSDB</th>
					<th >Pickup Supplier Name</th>
					<th >Truck Number</th>
					<th >Arrive Time</th>
					<th >Departure Time</th>
					<c:choose>
						<c:when test="${S_FordUser.role!='3'}">
							<th >ShipingOrder</th>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${S_FordUser.role!='3'}">
							<th >WaybillNumber</th>
						</c:when>
					</c:choose>
					<th >Status</th>
					<th></th>
					<th></th>                  
                </tr>
                </thead>
                <tbody>
	                <c:forEach items="${loadStops}" var="loadStop">
						<tr >
						<td>${loadStop.stopSequence}</td>
						<td>${load.loadDescription}</td>
						<td>${loadStop.stopShippingLocation}</td>
						<td>${loadStop.stopShippingLocationName}</td>
						<td>${loadStop.truckNumber}</td>
						
						
						<td>
						<fmt:parseDate value="${loadStop.arriveTime}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedArriveTime" type="both" />
						<fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm:ss" value="${ parsedArriveTime }" />
						</td>
						<td>
						<fmt:parseDate value="${loadStop.departureTime}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDepartureTime" type="both" />
						<fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm:ss" value="${ parsedDepartureTime }" />
						</td>
						<c:choose>
							<c:when test="${S_FordUser.role!='3'}">
								 <td>${loadStop.shipingOrder}</td> 
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${S_FordUser.role!='3'}">	
								<td>${loadStop.waybillNumber}</td>
							</c:when>
						</c:choose>
						
						<td>${loadStop.status}</td>
						<td><a href="<c:url value='/loadStatusUpdate/${loadDate}/${load.systemLoadID}-${loadStop.stopShippingLocation}-${loadStop.id}' />">On Times</a></td>
						<!-- td><a href="<c:url value='/setStopETA/${load.carrier.carrierCode}-${load.systemLoadID}-${loadStop.stopShippingLocation}-${loadStop.id}' />">Set Stop ETA</a></td-->
						 
						<c:choose>
							<c:when test="${loadStop.status=='update'}">
								<td></td>
							</c:when>
							<c:otherwise>
								<td><a href="<c:url value='/setStopETA/${loadDate}/${load.systemLoadID}-${loadStop.stopShippingLocation}-${loadStop.id}' />">Delay</a></td>
							</c:otherwise>
						</c:choose>	
								
						</tr>
					</c:forEach>
                              
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
      
      
      
            <!-- Modal -->
 		 	<div class="modal fade" id="myModalDeleteLoad" role="dialog">
    		<div class="modal-dialog">
				 <!-- Modal content-->
			      <div class="modal-content">
			      	<form method="POST" id="confirmDeleteLoadForm" data-toggle="validator" role="form" >
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Delete Load Data</h4>
				        </div>
				        <div class="modal-body">
				          <input type="hidden" id="hiddenDelloadID" name="hiddenDelloadID"/> 
				          <!--   <input type="text" id="txtDelloadID" name="txtDelloadID" readonly="readonly"/> -->
				          <p>Confirm delete Load ID : <span  id="showLoadID" class="badge bg-red" style="font-size:16px;font-weight: 600;">-</span>.</p>
				        </div>
				        <div class="modal-footer">
				          <button type="submit" class="btn btn-primary">Ok</button>
				          <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
			        </div>
			        </form>
			      </div>
		      </div>
		      </div>
		      
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
 // $(function () {
 //	  	$("#loadStopTable").DataTable({	        scrollX: true	            } ); 	   
 // });
  
	$(document).ready(function(){
		$("#loadStopTable").DataTable({	        scrollX: true	            } ); 
	  
		var setAlertMsg='${setAlertMsgDeleteLoad}';	
		if(setAlertMsg == 'true'){
			//var action = "<c:url value='/deleteLoadData' />";
			//document.getElementById("confirmDeleteLoadForm").action = action;
			document.getElementById("hiddenDelloadID").value = '${load.loadID}';
			//document.getElementById("txtDelloadID").value = '${load.systemLoadID}';
			document.getElementById("showLoadID").innerHTML = '${load.systemLoadID}';
			//$("#processDeleteAttorney").attr("href", "<c:url value='/headerform-list' />");
			//$("#processDeleteAttorney").attr("href", "<c:url value='/headerform-list' />");
			$("#myModalDeleteLoad").modal();	
		}
		
		
	});
	
</script>
</body>
</html>
