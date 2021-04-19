<%@page import="org.w3c.dom.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>AP Transport Center | Manual Add Load</title>
<%@ include file="/WEB-INF/include/cssInclude.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">

	<div class="wrapper">

		<%@ include file="/WEB-INF/include/header.jsp"%>
		<%@ include file="/WEB-INF/include/rightMenu.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					Manual Add Load <small></small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="<c:url value='/calendar'/>"><i
							class="fas fa-tachometer-alt"></i> &nbsp;&nbsp;Home</a></li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">

				<!-- /.row -->
				<div class="row" style="margin-bottom: 20px">
					<c:url var="myCraeteCronJoburl" value="/create-cron-job"/>
					<c:set var="varCreateJobURL" value="<c:url value='/create-cron-job' />" />
					<div class="col-sm-2">
						<div class="form-group input-group">
							<div class="input-group-addon">
								<label class="control-label">URL :</label>
								<input type="text" name="txtUrl" id="txtUrl" class="form-control" value="<c:url value='/create-cron-job' />" />	
								<input type="hidden" name="txtCreateUrl" id="txtCreateUrl" value="<c:out value = '${myCraeteCronJoburl}'/>" />
							</div>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group">
							<div class="input-group-addon">
								<label class="control-label">Create job Name :</label>
								<input type="text" name="txtCreateCronJobName" id="txtCreateCronJobName" class="form-control" />
							</div>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group">
							<div class="input-group-addon">
								<label class="control-label">LoadID :</label>
								<input type="text" name="txtCreateLoadID" id="txtCreateLoadID" class="form-control" />
							</div>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group">
							<div class="input-group-addon">
								<label class="control-label">SystemLoadID :</label>
								<input type="text" name="txtCreateSystemLoadID" id="txtCreateSystemLoadID" class="form-control" />
							</div>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group">
							<div class="input-group-addon">
								<label class="control-label">set cron job :</label>
								<input type="text" name="txtCreateCronJobTime" id="txtCreateCronJobTime" class="form-control" />
							</div>
						</div>
					</div>

					<div class="col-sm-2">
						<button onclick="createJobFunction()">CreateJob</button>
						<a href="<c:url value='/calendar' />">
							<button type="button"class="btn btn-default">Cancel</button>
						</a>

					</div>
				</div>
				<!-- /.row -->
				
				
				<div class="row" style="margin-bottom: 20px">
					<c:url var="myCraeteCronJoburl_V2" value="/create-cron-job-v2"/>
					<c:set var="varCreateJobURL_V2" value="<c:url value='/create-cron-job-v2' />" />
					
					<div class="col-sm-2">
						<div class="form-group input-group">
							<div class="input-group-addon">
								<label class="control-label">URL :</label>
								<input type="text" name="txtUrl_V2" id="txtUrl_V2" class="form-control" value="<c:url value='/create-cron-job-v2' />" />	
								<input type="hidden" name="txtCreateUrl_V2" id="txtCreateUrl_V2" value="<c:out value = '${myCraeteCronJoburl_V2}'/>" />
							</div>
						</div>
					</div>
					
					<div class="col-sm-2">
						<div class="form-group input-group">
							<div class="input-group-addon">
								<label class="control-label">Create job Name :</label>
								<input type="text" name="txtCreateCronJobName_V2" id="txtCreateCronJobName_V2" class="form-control" />
							</div>
						</div>
					</div>
					
					<div class="col-sm-2">
						<div class="form-group input-group">
							<div class="input-group-addon">
								<label class="control-label">Create job Type :</label>
								<input type="text" name="txtCreateCronJobType_V2" id="txtCreateCronJobType_V2" class="form-control" />
							</div>
						</div>
					</div>

					<div class="col-sm-2">
						<button onclick="createJobFunction_V2()">CreateJobV2</button>
						<a href="<c:url value='/calendar' />">
							<button type="button"class="btn btn-default">Cancel</button>
						</a>

					</div>
				</div>
				<!-- /.row -->
				
				
				<div class="row" style="margin-bottom: 20px">
					<div class="col-sm-4">
					</div>
					<div class="col-sm-5">
						<label class="control-label">Example set cron job : 0 55 20 * * ? [SS MM  HH DD * ?]</label>
					</div>
					<div class="col-sm-3">
					</div>
				</div>
				<!-- /.row -->

				<!-- /.row -->
				<div class="row" style="margin-bottom: 20px">
					<c:url var="myStopCronJoburl" value="/stop-cron-job"/>
					<c:set var="varStopJobURL" value="<c:url value='/stop-cron-job' />" />
					<div class="col-sm-3"></div>
					<div class="col-sm-4">
						<div class="form-group input-group">
							<div class="input-group-addon">
								<label class="control-label">Name for stop job :</label>
							</div>
							<input type="text" name="txtStopCronJobName" id="txtStopCronJobName" class="form-control" />
							<input type="hidden" name="txtStopUrl" id="txtStopUrl" value="<c:out value = '${myStopCronJoburl}'/>" />
						</div>
					</div>

					<div class="col-sm-5">
						<button onclick="stopJobFunction()">CancelJob</button>
						<a href="<c:url value='/calendar' />"><button type="button"
								class="btn btn-default">Cancel</button></a>


					</div>
				</div>
				<!-- /.row -->
				
				<!-- /.row -->
				<div class="row" style="margin-bottom: 20px">
					<div class="col-sm-12">
					<span  id="showPostUrl"  style="font-size:16px;">-</span>
					</div>

				</div>
				<!-- /.row -->

			</section>
			<!-- /.content -->

		</div>
		<!-- /.content-wrapper -->

		<%@ include file="/WEB-INF/include/footer.jsp"%>

	</div>
	<!-- ./wrapper -->

	<%@ include file="/WEB-INF/include/jsInclude.jsp"%>

	<!-- page script -->
<script>

    var valPostCreatURL="${myCraeteCronJoburl}";
    var valPostStopURL="${myStopCronJoburl}";
    
    var valPostCreatURL_V2="${myCraeteCronJoburl_V2}";

</script>
	<script
		src="<c:url value='/assets/js/createJob.js' />"></script>

</body>
</html>
