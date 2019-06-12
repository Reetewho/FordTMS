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
        <li><a href="#">User list</a></li>         
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
					<th >Role</th>
					<th >Department</th>
					<th >Joining date</th>
					<th >Last login</th>
					<th >Status</th>
					<th></th>
					<th></th>         
					         
                </tr>
                </thead>
               <tbody>
	                <c:forEach items="${users}" var="user">
						<tr >
						<td>${user.name}</td>
						<td>${user.role}</td>
						<td>${user.department}</td>
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
					<td><a href="<c:url value='/userDetail/${user.username}' />"><img  src="<c:url value='/assets/dist/img/edit.png' />" class="img-circle" alt="User Image" style="background-color:white" width= "20px" height="20px" ></a></td>																									
					<td>
					<c:choose>
							<c:when test="${user.status=='0'}">
					<a href="<c:url value='/UserStatus/${user.status}-${user.username}-${user.name}' />"><img  src="<c:url value='/assets/dist/img/True.png' />" class="img-circle" alt="User Image" style="background-color:white" width= "20px" height="20px" ></a>																				
					</c:when>
							<c:otherwise>
					<a href="<c:url value='/UserStatus/${user.status}-${user.username}-${user.name}' />"><img src="<c:url value='/assets/dist/img/false.png' />" class="img-circle" alt="User Image" style="background-color:white" width= "20px" height="20px"></a>
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
	  	$("#UserTable").DataTable( ); 
	   
  });
</script>
</body>
</html>
