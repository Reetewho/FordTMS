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
                  <th>Assign Date Time</th>                  
                  <th>Status</th>                  
                </tr>
                </thead>
                <tbody>
                <c:set var="naStatus" value="${0}"/>
                <c:set var="loadStatus" value="${0}"/>
                <c:set var="inTransit" value="${0}"/>
                <c:set var="completed" value="${0}"/>
                <c:forEach items="${Loadlistd}" var="Loadlistds">		     
					<tr>	   				    		    										    
						<td>${i=i+1}</td>
						<td>
							<a class="ClickLoadListStop" href="<c:url value='/loadStop-list/${Loadlistds.assign}/${Loadlistds.systemLoadID}-${Loadlistds.loadID}' />" >
							${Loadlistds.systemLoadID}
							</a>
		
						</td>
						<td>${Loadlistds.alertTypeCode}</td>
						<td>${Loadlistds.loadDescription}</td>
						<td>${Loadlistds.loadStartDateTime}</td>
						<td>${Loadlistds.loadEndDateTime}</td>
						<td>${Loadlistds.dateassign}</td>
						<td>${Loadlistds.status}</td>
						<c:if test = "${Loadlistds.status=='N/A'}"> <c:set var="naStatus" value="${naStatus+1}"/>  </c:if>
						<c:if test = "${Loadlistds.status=='Load'}"> <c:set var="loadStatus" value="${loadStatus+1}"/>  </c:if>
						<c:if test = "${Loadlistds.status=='In transit'}"> <c:set var="inTransit" value="${inTransit+1}"/>  </c:if>
						<c:if test = "${Loadlistds.status=='Completed'}"> <c:set var="completed" value="${completed+1}"/>  </c:if>
					</tr>
				</c:forEach>               
                </tbody> 
              </table>	              
                                   
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

var strDate='${loadDate}';
var d = strDate.split("-");

  $(function () {
	 
	  	$("#DriverTable").DataTable({
			dom: "<'row'<'col-sm-2'l><'col-sm-7'B><'col-sm-3'f>>" +
	        "<'row'<'col-sm-12'tr>>" +
	        "<'row'<'col-sm-5'i><'col-sm-7'p>>",
	                buttons: [{extend: 'excelHtml5',text: 'Export To Excel',filename: 'ExportLoad_'+d[2]+d[1]+d[0]}] ,
	                scrollX: true,
	                'order': [[6, 'desc']]
	            }); 
	    $("#totalNA").html(' <c:out value = "${naStatus}"/> ');
	    $("#totalLoad").html(' <c:out value = "${loadStatus}"/> ');
	    $("#totalIntransit").html(' <c:out value = "${inTransit}"/> ');
	    $("#completed").html(' <c:out value = "${completed}"/> ');
  });
 	
	/*
	* This method will : after click link provide delete attribute href (Origin).
	$(".ClickLoadRetrieve").on("click", function(){ 
		$(this).attr("onClick", false); 
	});
	*/
	
	/*
	* This method will : after click link provide delete attribute href.
	*/
	$(".ClickLoadListStop").on("click", function(){ 
		var strLocation = $(this).attr("href");
		  if(strLocation != null){
			  window.location=strLocation;
			  $(this).removeAttr( "href" );
			  $(this).attr("onClick", false);
		  }else{
			  $(this).attr("onClick", false); 
		  }   
	});
</script>
</body>
</html>
