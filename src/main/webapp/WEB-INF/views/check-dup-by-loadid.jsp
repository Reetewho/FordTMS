<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>AP Transport Center | Check Duplicate SystemLoad by SystemLoad </title>
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
	    
	    	<div class="row">
				<div class="col-md-12">
					<c:if test="${errorMsg!=null}">
						<div class="alert alert-danger alert-dismissible">
							<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
								<h4>
									<i class="icon fa fa-ban"></i>Error!
								</h4>
							<c:out value="${errorMsg}"></c:out>
						</div>
					</c:if>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-12">
					<c:if test="${Warning!=null || Success!=null }">
						<div class='alert ${Warning!=null?"alert-warning":"alert-success"}  alert-dismissible'>
							<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
								<h4>
									<i class="icon fa  ${Warning!=null?'fa-ban':'fa-check'}"></i>${Warning!=null?'Warning!':'Success'}
								</h4>
							<c:out value="${Warning!=null?Warning:Success} "></c:out>
						</div>
					</c:if>
				</div>
			</div>
				
	      <h1>
	       Check Duplicate SystemLoad
	        <small></small>
	      </h1>
	      <ol class="breadcrumb">
	        <li><a href="#"><i class="fas fa-tachometer-alt"></i> &nbsp;&nbsp;Home</a></li>
	        <li><a href="<c:url value='/searchby-loadid' />">Search by Load ID</a></li>                 
	      </ol>
	    </section>
	    
	    
	
	    <!-- Main content -->
	    <section class="content">  
			<div class="row" style="margin-bottom:20px"> 
				<form method="POST" action="<c:url value='/check-dup-systemload' />" data-toggle="validator" role="form" >        
					<div class="col-md-3">
						<div class="form-group has-feedback">
							<input type="text" name="systemLoadID" id="systemLoadID" value="${systemLoadID}" class="form-control" placeholder="LoadID" data-minlength="6" data-error="Minimum of 6 characters" required="required"/>  
						</div>
				     </div>        
				     <div class="col-md-2" ><input type="submit" value="search"/></div>
				</form>
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
		                  	<th>SystemLoad ID</th>
							<th style="display:none;">load ID</th>
							<th>Load Date</th>
							<th>Load Status</th>
							<th>Count of LoadStop</th>
							<th>Stop Load</th>   
		                </tr>
		                </thead>
		                <tbody>
				            <c:if test = "${not empty loadDatas}">
								<c:forEach items="${loadDatas}" var="loadRow">
									<tr >
										<td>
											<a href="<c:url value='/searchby-systemloadid/${loadRow.systemLoadID}' />">
											${loadRow.systemLoadID}
											</a>
										</td>
										<td style="display:none;">${loadRow.loadID}</td>
										<td>${loadRow.loadDate}</td>
										<td>${loadRow.loadStatus}</td>				
										<td>${loadRow.countLoadStop}</td>
										<td align="center" >
											<a href="<c:url value='/stop-load-data/${loadRow.systemLoadID}-${loadRow.loadID}' />"><img src="<c:url value='/assets/dist/img/false.png' />" class="img-circle" alt="User Image" style="background-color:white" width= "20px" height="20px"></a> 
										</td>
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
	var today = new Date();
	var dd = today.getDate();

	var mm = today.getMonth()+1; 
	var yyyy = today.getFullYear();
	if(dd<10) 
	{
	    dd='0'+dd;
	} 

	if(mm<10) 
	{
	    mm='0'+mm;
	} 
	
	  $(function () {	  	 
		  
		  
		  	$("#reportTable").DataTable({
		  		dom: "<'row'<'col-sm-2'l><'col-sm-7'B><'col-sm-3'f>>" +
		        "<'row'<'col-sm-12'tr>>" +
		        "<'row'<'col-sm-5'i><'col-sm-7'p>>",
		        buttons: [{extend: 'excelHtml5',text: 'Export To Excel',filename: 'Report_' + yyyy + mm + dd}],
		        scrollX: true
		            });
		  	
		  	 
	  });
	  
	</script>
</body>
</html>