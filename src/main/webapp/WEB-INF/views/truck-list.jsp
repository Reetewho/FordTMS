<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AP Transport Center | Truck Management</title>
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
       Truck management
        <small></small>
        
<%-- 		<a href="<c:url value='/adduser' />"><img  src="<c:url value='/assets/dist/img/add.png' />" class="img-circle" alt="User Image" style="background-color:white" width= "20px" height="20px" ></a>
 --%>        
 			<a href="<c:url value='/addtruck' />"><button type="button" class="btn btn-default">Add</button></a>
        
      </h1>
								
      <ol class="breadcrumb">
        <li><a href="#"><i class="fas fa-tachometer-alt"></i> &nbsp;&nbsp;Home</a></li>
        <li><a href="#">Truck list</a></li>         
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">   
      <div class="row">
      <!-- /.col -->
        <div class="col-md-12">
          <div class="box box-primary">
          	<div class="row">
      	<div class="col-md-12">
      		<c:if test="${Error!=null || Success!=null }">
              <div class='alert ${Error!=null?"alert-danger":"alert-success"}  alert-dismissible'>
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4><i class="icon fa  ${Error!=null?'fa-ban':'fa-check'}"></i>${Error!=null?'Error!':'Success'} </h4>
                <c:out value="${Error!=null?Error:Success} "></c:out>
              </div>
            </c:if>           
      	</div>
      </div>
            <!-- /.box-header -->
            <div class="box-body ">
              <table id="TruckTable"  class="table table-bordered table-striped" style="width : 100% ">
                 <thead>
                <tr>
                	<th >No.</th>
					<th >TruckNumber</th>
					<th >Truck Type</th>
					<th >Gps Truck</th>
					<th >Create Date</th>
					<th >Create By</th>
					<th >Update Date</th>
					<th >Update By</th>
					<th >Status</th>
					<th ></th>
                </tr>
                </thead>
               <tbody>
	                <c:forEach items="${trucks}" var="truck">
						<tr >
						<td>${i=i+1}</td>
						<td>${truck.TRUCK_NUMBER}</td>
						<td>
						 <c:forEach items="${ListType}" var="ListType">
							 <c:if test="${truck.TRUCK_TYPE eq ListType.TRUCKTYPE_id}">
							 	<c:set var="ShowRoleName" value="${ListType.TRUCKTYPE_TYPE}" /> 
	             			 	<c:out value="${ShowRoleName}" />
							 </c:if>
						 </c:forEach>						
						</td> 
						<td>
						 <c:forEach items="${TruckGps}" var="GpsType">
							 <c:if test="${truck.GPS_TRUCK eq GpsType.GPSId}">
							 	<c:set var="ShowRoleName" value="${GpsType.GPSTYPE}" /> 
	             			 	<c:out value="${ShowRoleName}" />
							 </c:if>
						 </c:forEach>						
						</td>	
						<td>${truck.CREATE_DATE}</td>
						<td>${truck.CREATE_BY}</td>
						<td>${truck.UPDATE_DATE}</td>	
						<td>${truck.UPDATE_BY}</td>		
						<%-- <td>${truck.STATUS}</td> --%>
						<c:choose>
							<c:when test="${truck.STATUS=='1'}">
								<td>Activate</td>
							</c:when>
							<c:otherwise>
								<td>Deactivate</td>
							</c:otherwise>
						</c:choose>				
					<td align="center"><a href="<c:url value='/edit-truck/${truck.TRUCK_NUMBER}' />"><img  src="<c:url value='/assets/dist/img/edit.png' />" class="img-circle" alt="User Image" style="background-color:white" width= "20px" height="20px" ></a></td>					
					<td align="center" >
					<c:choose>
							<c:when test="${truck.STATUS=='0'}">
					<a href="<c:url value='/TruckName/${truck.TRUCK_NUMBER}' />"><img  src="<c:url value='/assets/dist/img/True.png' />" class="img-circle" alt="User Image" style="background-color:white" width= "20px" height="20px" ></a>																				
					</c:when>
					<c:otherwise>
					<a href="<c:url value='/TruckName/${truck.TRUCK_NUMBER}' />"><img src="<c:url value='/assets/dist/img/false.png' />" class="img-circle" alt="User Image" style="background-color:white" width= "20px" height="20px"></a> 
					</c:otherwise>
						</c:choose>
					</td>					
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
  $(function () {
	  	$("#TruckTable").DataTable({	        scrollX: true	            }); 
	   
  });
</script>
</body>
</html>
