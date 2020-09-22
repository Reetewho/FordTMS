<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>AP Transport Center | Summary Report </title>
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
					Summary Report <small></small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fas fa-tachometer-alt"></i>
							&nbsp;&nbsp;Home</a></li>
					<li><a href="#' />">Summary Report</a></li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row" style="margin-bottom: 20px">
					<form method="POST" id="frm-example1" action="<c:url value='/testrestapi-login' />"
						data-toggle="validator" role="form">
						<div class="col-md-3"></div>
						<div class="col-md-3">
							<div class="form-group  input-group date">
								<div class="input-group-addon">
									<label class="col-sm-4 control-label">Test LogIN</label>
								</div>
							</div>
						</div>
						<div class="col-md-3" style="padding-top: 3px">
							<input type="submit" value="TestLogin" />
						</div>
						<div class="col-md-3"></div>
					</form>
				</div>

				<div class="row" style="margin-bottom: 20px">
					<form method="POST" id="frm-example2" action="<c:url value='/testrestapi-summary-list' />"
						data-toggle="validator" role="form">
						<div class="col-md-3"></div>
						<div class="col-md-3">
							<div class="form-group  input-group date">
								<div class="input-group-addon">
									<label class="col-sm-4 control-label">Test Summary-List</label>
								</div>
							</div>
						</div>
						<div class="col-md-3" style="padding-top: 3px">
							<input type="submit" value="TestSummaryList" />
						</div>
						<div class="col-md-3"></div>
					</form>
				</div>
				
			</section>
			<!-- /.content -->

		</div>
	  <!-- /.content-wrapper -->  
	  
	  <%@ include file="/WEB-INF/include/footer.jsp" %>
	  
	</div>
	<!-- ./wrapper -->

	<%@ include file="/WEB-INF/include/jsInclude.jsp" %>

	<!-- DataTables Edit -->
	<!-- <script language="javascript" src="<c:url value='/assets/js/paymentreport1.js' />"></script> -->
	<script language="javascript" src="<c:url value='/assets/js/paymentreport1.js' />" ></script>
</body>
</html>