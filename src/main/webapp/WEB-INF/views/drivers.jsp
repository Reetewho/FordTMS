<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AP Transport Center | User Management</title>
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
       User management
        <small></small>
 	  </h1>								
      <ol class="breadcrumb">
        <li><a href="#"><i class="fas fa-tachometer-alt"></i> &nbsp;&nbsp;Home</a></li>
        <li><a href="#">load list</a></li> 
        <li><a href="#">Driver list</a></li>         
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
              <table id="UserTable"  class="table table-bordered table-striped">
                <thead>
                <tr>
					<th >Name</th>
					<th >Last Name</th>
					<th >Role</th>
					<th >Department</th>
					<th >E-mail</th>
					<th >Contact Number</th>
					<th >Joining date</th>
					<th >Last login</th>
					<th >Status</th>
					
					        
					         
                </tr>
                </thead>
               <tbody>
	                <c:forEach items="${users}" var="user">
						<tr >
						<td>${user.name}</td>
						<td>${user.lastname}</td>
						<td>
						 <c:forEach items="${ListRolest}" var="ListRoles">
						 <c:if test="${user.role eq ListRoles.roleId}">
						 	<c:set var="ShowRoleName" value="${ListRoles.role_n}" /> 
             			 	<c:out value="${ShowRoleName}" />
						 </c:if>
						 </c:forEach>
						
						</td> 
						<td>
						 <c:forEach  items="${ListDepartments}" var="ListDepartment">
						 <c:if test="${user.department eq ListDepartment.departmentId}">
						 	<c:set var="ShowRoleName" value="${ListDepartment.department_n}" /> 
             			 	<c:out value="${ShowRoleName}" />
						 </c:if>
						 </c:forEach>							
						</td> 
						<td>${user.email}</td>
						<td>${user.contactnumber}</td>
						<td>${user.joiningDate}</td>
						<td>${user.logoutDate}</td>
						<c:choose>
							<c:when test="${user.status=='1'}">
								<td>Active</td>
							</c:when>
							<c:otherwise>
								<td>Not Active</td>
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
	  	$("#UserTable").DataTable({	        scrollX: true	            }); 
	   
  });
</script>
</body>
</html>
