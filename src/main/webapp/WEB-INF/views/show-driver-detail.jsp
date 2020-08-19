<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AP Transport Center | Driver Management</title>
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
       Driver management
        <small></small>       
      </h1>
								
      <ol class="breadcrumb">
        <li><a href="#"><i class="fas fa-tachometer-alt"></i> &nbsp;&nbsp;Home</a></li>
        <li><a href="#">load list</a></li> 
        <li><a href="#">Driver list</a></li>     
        <li><a href="#">Show Detail </a></li>      
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
      		<c:if test="${Warning!=null || Success!=null  }">
              <div class='alert ${Warning!=null?"alert-warning":"alert-success"}  alert-dismissible'>
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4><i class="icon fa  ${Warning!=null?'fa-exclamation-triangle':'fa-check'}"></i>${Warning!=null?'Warning':'Success'} </h4>
                <c:out value="${Warning!=null?Warning:Success} "></c:out>
              </div>
            </c:if>           
      	</div>
      </div>
            <!-- /.box-header -->
            <div class="box-body ">
            <table id="DriverTable"  class="table table-bordered table-striped" style="width : 100% ">
            <thead>
                <tr> 
            	  <th>Name</th>
                  <th>Lastname</th>
                  <th>E-Mail</th>                 
                  <th>Contact Number</th>
                  <th>Joining date</th>                  
                  <th>Last login</th>                  
                </tr>
                </thead>
                <tbody>	                
						<tr >
						<td>${user_r.name}</td>
						<td>${user_r.lastname}</td>
						<td>${user_r.email}</td>
						<td>${user_r.contactnumber}</td>
						<td>${user_r.joiningDate}</td>
						<td>${user_r.logoutDate}</td>
						</tr>					
             </table>
             </div>
             <div class="box-body ">
               <form method="POST" id="frmDrivere" >                                    
              <table id="DriverTable"  class="table table-bordered table-striped" style="width : 100% ">
               <thead>
                <tr>                  
                  <th>NO.</th>
                  <th>Load ID</th>
                  <th>Alert Type Code</th>
                  <th>Route No.</th>
                  <th>Load Start Date Time</th>
                  <th>Load End Date Time</th>                  
                  <th>Status</th>    
                </tr>
                </thead>
                <tbody>
                <c:set var="naStatus" value="${0}"/>
                <c:set var="loadStatus" value="${0}"/>
                <c:set var="inTransit" value="${0}"/>
                <c:set var="completed" value="${0}"/>
                <c:forEach items="${allListLoads}" var="allListLoad">		     
					<tr>	   				    		    										    
						<td>${i=i+1}</td>
						<td>
							<a class="ClickLoadListStop" href="<c:url value='/loadStop-list/${loaddates}/${allListLoad.systemLoadID}-${allListLoad.loadID}' />" >
							${allListLoad.systemLoadID}
							</a>
		
						</td>
						<td>${allListLoad.alertTypeCode}</td>
						<td>${allListLoad.loadDescription}</td>
						<td>${allListLoad.loadStartDateTime}</td>
						<td>${allListLoad.loadEndDateTime}</td>
						<td>${allListLoad.status}</td>
						<c:if test = "${allListLoad.status=='N/A'}"> <c:set var="naStatus" value="${naStatus+1}"/>  </c:if>
						<c:if test = "${allListLoad.status=='Load'}"> <c:set var="loadStatus" value="${loadStatus+1}"/>  </c:if>
						<c:if test = "${allListLoad.status=='In transit'}"> <c:set var="inTransit" value="${inTransit+1}"/>  </c:if>
						<c:if test = "${allListLoad.status=='Completed'}"> <c:set var="completed" value="${completed+1}"/>  </c:if>
					</tr>				
				</c:forEach>  				           
                </tbody> 
              </table>	 
              	<!-- -------------------------------------------------------------------------------------------------------------------- -->
				<input type="hidden" name="assignId" id="assignId" value="${S_FordUser.username}">
				<!-- -------------------------------------------------------------------------------------------------------------------- -->             
              <a href="<c:url value='/load-list/${loaddates}' />"><button type="button" class="btn btn-default">Back</button></a>
              <a href="<c:url value='/driverconf/${S_FordUser.username}/${user_r.username}/${loaddates}' />"><button type="button" class="btn btn-primary pull-right">Submit</button></a>	                     
             </form>
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
	  	$("#DriverTable").DataTable({	        scrollX: true	            }); 
	  	
	  /* 	$("#DriverTables").DataTable({	        scrollX: true	            });  */
	  
	  	
  });
</script>
</body>
</html>
