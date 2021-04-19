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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.8.0/jszip.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.8.0/xlsx.js"></script>
</head>
<body class="hold-transition skin-blue sidebar-mini" id="allBody">
<div class="wrapper">

  <%@ include file="/WEB-INF/include/header.jsp" %>
  <%@ include file="/WEB-INF/include/rightMenu.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
       Truck Management
        <small></small>     
 			<a href="<c:url value='/addtruck' />"><button type="button" class="btn btn-default">Add</button></a>
 			<a ><button onclick="clickimport()" type="button" class="btn btn-default">Import</button></a>
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
      <form method="POST" enctype="multipart/form-data" id="frm-payment" action="<c:url value='/UpdateTruckExcel' />" >
		    <input style="display:none;" id="upload" type=file  name="files[]">		    
		</form>

            <!-- /.box-header -->
            <div class="box-body ">
              <table id="TruckTable"  class="table table-bordered table-striped" style="width : 100% ">
                 <thead>
                <tr>              		
                	<th >No.</th>
					<th >Truck Number</th>
					<th >Head Or Tail</th>
					<th >Truck Type</th>
					<th >GPS Truck</th>
					<th >Create Date</th>
					<th >Create By</th>
					<th >Update Date</th>
					<th >Update By</th>
					<th >Status</th>
					<th ></th>
					<th ></th>
                </tr>
                </thead>
                <tbody>
	                <c:forEach items="${trucks}" var="truck">
						<tr >
						<td>${i=i+1}</td>
						<td>${truck.TRUCK_NUMBER}</td>
						<td>${truck.PLATE_TYPE}</td>
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
					<div id="result"></div>                
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
$("#TruckTable").DataTable({	        scrollX: true	            }); 



function clickimport() {   document.getElementById("upload").click();	}

 var ExcelToJSON = function() {

    this.parseExcel = function(file) {
      var reader = new FileReader();

      reader.onload = function(e) {
        var data = e.target.result;
        var workbook = XLSX.read(data, {
          type: 'binary'
        });
        workbook.SheetNames.forEach(function(sheetName) {
          // Here is your object
          var XL_row_object = XLSX.utils.sheet_to_row_object_array(workbook.Sheets[sheetName]);        
          //var json_object = JSON.stringify(XL_row_object);        
          var jsonGroupStr = '{"theGroupData":[]}';
          var objGroupdata = JSON.parse(jsonGroupStr);

          //console.log(JSON.parse(json_object));
          objGroupdata['theGroupData'].push(XL_row_object);
     	 var myJSON = JSON.stringify(objGroupdata);


          $.ajax({
      		type : "POST",
      		contentType : "application/json",
      		url : "UpdateTruckExcel",
      		data : myJSON,
      		dataType : 'json',				
      		success : function(data) {
      			 alert('Code to display the response.');
      			// Code to display the response.
      		},
      		error: function(jqXHR, textStatus, errorThrown) {
                //alert('An error occurred... Look at the console (F12 or Ctrl+Shift+I, Console tab) for more information!');  
                 $('#allBody').html(jqXHR.responseText);
               
               setTimeout(function() { window.location.reload();},10000);
                
            }
      	}); 
          
          
        })
      };

      reader.onerror = function(ex) {
        console.log(ex);
      };

      reader.readAsBinaryString(file);
    };
};

function handleFileSelect(evt) {
  
  var files = evt.target.files; // FileList object
  var xl2json = new ExcelToJSON();
  xl2json.parseExcel(files[0]);
}
document.getElementById('upload').addEventListener('change', handleFileSelect, false); 


  
</script>
</body>
</html>
