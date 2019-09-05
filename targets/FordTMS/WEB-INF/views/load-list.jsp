<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AP Transport Center | Load List</title>
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
       Load List
        <small></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fas fa-tachometer-alt"></i> &nbsp;&nbsp;Home</a></li>
        <li><a href="<c:url value='/calendar/${loadDate}' />">Calendar</a></li>
        <li><a href="#">${loadDate}</a></li>
        <li><a href="#">Load list</a></li>         
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
    <!-- =========================================================== -->

      <div class="row">
        <div class="col-md-3">
          <div class="box box-danger box-solid">
            <div class="box-header">
              <h3 class="box-title">N/A Status</h3>
              <div class="box-tools pull-right">
                <span  id="totalNA" class="badge bg-red" style="font-size:16px;font-weight: 600;">-</span>
              </div>
              <!-- /.box-tools -->
            </div>
            <!-- /.box-header -->           
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
        <div class="col-md-3">
          <div class="box box-warning box-solid">
            <div class="box-header">
              <h3 class="box-title">Load Status</h3>
              <div class="box-tools pull-right">
                <span  id="totalLoad" class="badge bg-yellow" style="font-size:16px;font-weight: 600;">-</span>
              </div>
              <!-- /.box-tools -->
            </div>
            <!-- /.box-header -->           
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
        <div class="col-md-3">
          <div class="box box-info box-solid">
            <div class="box-header">
              <h3 class="box-title">In Transit</h3>
              <div class="box-tools pull-right">
                <span  id="totalIntransit" class="badge bg-aqua" style="font-size:16px;font-weight: 600;">-</span>
              </div>
              <!-- /.box-tools -->
            </div>
            <!-- /.box-header -->            
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
        <div class="col-md-3">
          <div class="box box-success box-solid">
            <div class="box-header">
              <h3 class="box-title">Completed</h3>
			  <div class="box-tools pull-right">
                <span  id="completed" class="badge bg-green" style="font-size:16px;font-weight: 600;">-</span>
              </div>
            </div>
            <!-- /.box-body -->            
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->

      <!-- =========================================================== -->
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
            
            <div class="box-header">
            <c:if test="${setAlertDeleteLoadSuccess != null}">
              <div class="alert alert-success alert-dismissible">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4><i class="icon fa fa-check"></i> Success</h4>
                <c:out value="${setAlertDeleteLoadSuccess}"></c:out>
              </div>
            </c:if>
            </div>
            <!-- /.box-header -->
            
            <!-- /.box-header -->
            <div class="box-body ">
             <form method="POST" id="frm-example"  action="<c:url value='/drivers' />"  >            
              <table  id="example" class="table table-bordered table-striped" style="width : 100% ">
                <thead>
                <tr>
                  <th></th>
                  <th>NO.</th>
                  <th>Load ID</th>
                  <th>Alert Type Code</th>
                  <th>Route No.</th>
                  <th>Load Start Date Time</th>
                  <th>Load End Date Time</th>   
                  <th>Accept Date Time</th> 
                  <th>Assign To</th>                
                  <th>Status</th>                  
                </tr>
                </thead>
                <tbody>
                <c:set var="naStatus" value="${0}"/>
                <c:set var="loadStatus" value="${0}"/>
                <c:set var="inTransit" value="${0}"/>
                <c:set var="completed" value="${0}"/>
                <c:forEach items="${loads}" var="load">		     
					<tr>	   				    		    									
					    <th align="center" >				    
						${load.loadID}
						</th>	    
						<td>${i=i+1}</td>
						<td>
							<a class="ClickLoadListStop" href="<c:url value='/loadStop-list/${loadDate}/${load.systemLoadID}-${load.loadID}' />" >
							${load.systemLoadID}
							</a>
							
							<!-- This is test onclick : location.
								<a class='ClickLoadRetrieve' onclick="location='<c:url value='/loadStop-list/${loadDate}/${load.systemLoadID}-${load.loadID}' />'"> 
								${load.systemLoadID}
								</a>
							-->
							
						</td>
						<td>${load.alertTypeCode}</td>
						<td>${load.loadDescription}</td>
						<td>${load.loadStartDateTime}</td>
						<td>${load.loadEndDateTime}</td>
						<td>${load.dateaccept}</td>
						<td>${load.driverid}</td>
						<td>${load.status}</td>
						<c:if test = "${load.status=='N/A'}"> <c:set var="naStatus" value="${naStatus+1}"/>  </c:if>
						<c:if test = "${load.status=='Load'}"> <c:set var="loadStatus" value="${loadStatus+1}"/>  </c:if>
						<c:if test = "${load.status=='In transit'}"> <c:set var="inTransit" value="${inTransit+1}"/>  </c:if>
						<c:if test = "${load.status=='Completed'}"> <c:set var="completed" value="${completed+1}"/>  </c:if>
					</tr>
				</c:forEach>               
                </tbody> 
              </table>		
                <button type="submit" class="btn btn-primary pull-right">Assign</button>     
                <input type="hidden" name="loaddates" id="loaddates" value="${loadDate}"/>           
              <input type="hidden" name="console-select-rows" id="console-select-rows" value=""/>               
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
	 
	    $("#totalNA").html(' <c:out value = "${naStatus}"/> ');
	    $("#totalLoad").html(' <c:out value = "${loadStatus}"/> ');
	    $("#totalIntransit").html(' <c:out value = "${inTransit}"/> ');
	    $("#completed").html(' <c:out value = "${completed}"/> ');
  });

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
  


	 $(document).ready(function() {
		   var table = $('#example').DataTable({ 
			dom: "<'row'<'col-sm-2'l><'col-sm-7'B><'col-sm-3'f>>" +
			        "<'row'<'col-sm-12'tr>>" +
			        "<'row'<'col-sm-2'i><'col-sm-10'p>>",
			        buttons: [{extend: 'excelHtml5',text: 'Export To Excel',filename: 'ExportLoad_'+d[2]+d[1]+d[0]}],
			        'autoWidth':true,
					   'scrollX': true,

			   'initComplete': function(settings){
			         var api = this.api();

			         api.cells(
			            api.rows(function(idx, data, node){
			               return (data[9] === 'Completed') ? true : false;
			            }).indexes(),
			            0
			         ).checkboxes.disable();
			      },
		      'columnDefs': [
		         {
		            'targets': 0,
		            'checkboxes': {
		               'selectRow': true
		            }
		         }
		      ],
		      'select': {
		         'style': 'multi'
		      },
		      'order': [[0, 'asc']]
		   });
		   
		   // Handle form submission event 
		   $('#frm-example').on('submit', function(e){
		      var form = this;
		      
		      var rows_selected = table.column(0).checkboxes.selected();

		      // Iterate over all selected checkboxes
		      $.each(rows_selected, function(index, rowId){
		         // Create a hidden element 
		         $(form).append(
		             $('<input>')
		                .attr('type', 'hidden')
		                .attr('name', 'id[]')
		                .val(rowId)
		         );
		      });

		      // FOR DEMONSTRATION ONLY
		      // The code below is not needed in production
		      
		      // Output form data to a console     
		      $('#example-console-rows').text(rows_selected.join(","));
		      document.getElementById("console-select-rows").value = rows_selected.join(",");
		      
		      // Output form data to a console     
		      $('#example-console-form').text($(form).serialize());
		       
		      // Remove added elements
		      $('input[name="id\[\]"]', form).remove();
		       
		      // Prevent actual form submission
		      //e.preventDefault();
		   });   
		});
		
	 
	
</script>
</body>
</html>
