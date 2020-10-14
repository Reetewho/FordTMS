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
       GSDB Management
        <small></small>     
 			<a href="<c:url value='/addgsdb' />"><button type="button" class="btn btn-default">Add</button></a>
 			<a ><button onclick="clickimport()" type="button" class="btn btn-default">Import</button></a>
      </h1>
								
      <ol class="breadcrumb">
        <li><a href="#"><i class="fas fa-tachometer-alt"></i> &nbsp;&nbsp;Home</a></li>
        <li><a href="#">GSDB list</a></li>         
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
      <form method="POST" enctype="multipart/form-data" id="frm-payment" action="<c:url value='/UpdateGsdbExcel' />" >
      	    <input type = "hidden" name = "xlx_json" id = "xlx_json" value = "" />           	  
		    <input style="display:none;" id="upload" type=file  name="files[]">
		    <button style="display:none;" type = "submit" name="subbt" id="subbt" class = "btn btn-primary pull-right" ></button> 
		    
		</form>
		<!-- <textarea class="form-control" rows=35 cols=120 id="xlx_json"></textarea> -->

            <!-- /.box-header -->
            <div class="box-body ">
              <table id="GsdbTable"  class="table table-bordered table-striped" style="width : 100% ">
                 <thead>
                <tr>
                	<th >No.</th>
					<th >GSDB Code</th>
					<th >GSDB Name</th>
					<th >Radius</th>
					<th >Latitude</th>
					<th >Longitude</th>
					<!-- <th >Delivery</th> -->
					<th >Update Date</th>
					<th >Update By</th>
					<th >Status</th>
					<th ></th>
					<th ></th>
                </tr>
                </thead>
                <tbody>
	                <c:forEach items="${GSDB}" var="GSDB">
						<tr >
						<td>${i=i+1}</td>
						<td>${GSDB.GSDBCODE}</td>
						<td>${GSDB.GSDBNAME}</td>
						<td>${GSDB.GSDBRADIUS}</td>						
						<td>${GSDB.GSDBLATITUDE}</td>
						<td>${GSDB.GSDBLONGITUDE}</td>
						<%-- <td>${GSDB.GSDBDELIVERYTYPE}</td> --%>
						<td>${GSDB.GSDBUPDATEDATE}</td>
						<td>${GSDB.GSDBUPDATEBY}</td>
						<c:choose>
							<c:when test="${GSDB.GSDBSTARUS=='1'}">
								<td>Activate</td>
							</c:when>
							<c:otherwise>
								<td>Deactivate</td>
							</c:otherwise>
						</c:choose>				
						<td align="center"><a href="<c:url value='/edit-gsdb/${GSDB.GSDBCODE}' />"><img  src="<c:url value='/assets/dist/img/edit.png' />" class="img-circle" alt="User Image" style="background-color:white" width= "20px" height="20px" ></a></td>					
						<td align="center" >
						<c:choose>
							<c:when test="${GSDB.GSDBSTARUS=='0'}">
						<a href="<c:url value='/GSDBcode/${GSDB.GSDBCODE}' />"><img  src="<c:url value='/assets/dist/img/True.png' />" class="img-circle" alt="User Image" style="background-color:white" width= "20px" height="20px" ></a>																				
						</c:when>
						<c:otherwise>
						<a href="<c:url value='/GSDBcode/${GSDB.GSDBCODE}' />"><img src="<c:url value='/assets/dist/img/false.png' />" class="img-circle" alt="User Image" style="background-color:white" width= "20px" height="20px"></a> 
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
$("#GsdbTable").DataTable({	        scrollX: true	            }); 



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
          var json_object = JSON.stringify(XL_row_object);
          var jsonGroupStr = '{"theGroupData":[]}';
          var objGroupdata = JSON.parse(jsonGroupStr);

          console.log(JSON.parse(json_object));
          objGroupdata['theGroupData'].push(XL_row_object);
     	 var myJSON = JSON.stringify(objGroupdata);


          $.ajax({
      		type : "POST",
      		contentType : "application/json",
      		url : "UpdateGsdbExcel",
      		data : myJSON,
      		dataType : 'json',				
      		success : function(data) {
      			
      			// Code to display the response.
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
