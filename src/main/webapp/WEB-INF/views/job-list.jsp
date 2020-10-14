<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AP Transport Center | GSDB Management</title>
  <%@ include file="/WEB-INF/include/cssInclude.jsp" %>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.8.0/jszip.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.8.0/xlsx.js"></script>
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
					Job Management
				</h1>

				<ol class="breadcrumb">
					<li><a href="#"><i class="fas fa-tachometer-alt"></i>
							&nbsp;&nbsp;Home</a></li>
					<li><a href="<c:url value='/show-all-job' />">Job list</a></li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<!-- /.col -->
					<div class="col-md-12">
						<div class="box box-primary">
							<!-- /.box-header -->
							<div class="box-header">
								<form method="GET" enctype="multipart/form-data" id="frm-job" action="<c:url value='/show-all-job' />">
							    	<input type="submit" value="Show All Job" />
								</form>
							</div>

							<div class="box-header">
								<div class="col-md-12">
									<c:if test="${Error!=null || Success!=null }">
										<div
											class='alert ${Error!=null?"alert-danger":"alert-success"}  alert-dismissible'>
											<button type="button" class="close" data-dismiss="alert"
												aria-hidden="true">×</button>
											<h4>
												<i class="icon fa  ${Error!=null?'fa-ban':'fa-check'}"></i>${Error!=null?'Error!':'Success'}
											</h4>
											<c:out value="${Error!=null?Error:Success} "></c:out>
										</div>
									</c:if>
								</div>
							</div>
							
							<!-- /.box-body -->
							<div class="box-body ">
								<table id="JobTable" class="table table-bordered table-striped"
									style="width: 100%">
									<thead>
										<tr>
											<th>No.</th>
											<th>Job Name</th>
											<th>Start Time</th>
											<th>Next Fire Time</th>
											<th>Last Fire Time</th>
											<th>Stop Job</th>
										</tr>
									</thead>
									<c:if test = "${not empty jobData}"> 
									<tbody>
										<c:forEach items="${jobData}" var="jobData">
											<tr>
												<td>${i=i+1}</td>
												<td>${jobData.jobName}</td>
												<td>${jobData.startTime}</td>
												<td>${jobData.nextFireTime}</td>
												<td>${jobData.lastFireTime}</td>
												<td align="center">
													<a href="<c:url value='/stop-job/${jobData.jobName}' />"><img
														src="<c:url value='/assets/dist/img/false.png' />"
														class="img-circle" alt="User Image"
														style="background-color: white" width="20px" height="20px"></a>
											    </td>
											</tr>
										</c:forEach>
									</tbody>
									</c:if>
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
$("#JobTable").DataTable({scrollX: true}); 
</script>
</body>
</html>
