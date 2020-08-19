<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AP Transport Center | Driver Management</title>
  <%@ include file="/WEB-INF/include/cssInclude.jsp" %>
   <script src="../node_modules/tesseract.js/dist/tesseract.min.js"></script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <%@ include file="/WEB-INF/include/header.jsp" %>
  <%@ include file="/WEB-INF/include/rightMenu.jsp" %>
<!-- -------------------------------------------------------------------------------------------------------------------- -->
<input type="hidden" name="Assignd" id="Assignd" value="sessionSelectItem">
<!-- -------------------------------------------------------------------------------------------------------------------- -->
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
            <form method="POST" id="frm-example"  action="<c:url value='/drivers' />"  >
              <table id="UserTable"  class="table table-bordered table-striped" style="width : 100% ">
                <thead>
                <tr>
               		 <th ></th>
					<th >Load ID</th>
					<th >Alert Type Code</th>
					<th >Route No.</th>
					<th >Load Start Date Time</th>
					<th >Load End Date Time</th>
					<th >Status</th>
					<th >Waybill Number	</th>
					<th ></th>					        
					         
                </tr>
                </thead>
               <tbody>	                
					<c:forEach items="${loadSessionItems}" var="load">		     
				<tr>	   		    
						<td>${i=i+1}</td>			    		    														    
						<%-- <td><a class="ClickLoadListStop" href="<c:url value='/loadStop-list/${loadDate}/${load.systemLoadID}-${load.loadID}' />" >${load.systemLoadID}</a></td> --%>
						<td>${load.systemLoadID}</td>
						<td>${load.alertTypeCode}</td>
						<td>${load.loadDescription}</td>
						<td>${load.loadStartDateTime}</td>
						<td>${load.loadEndDateTime}</td>
						<td>${load.status}</td>
						<c:if test = "${load.status=='N/A'}"> <c:set var="naStatus" value="${naStatus+1}"/>  </c:if>
						<c:if test = "${load.status=='Load'}"> <c:set var="loadStatus" value="${loadStatus+1}"/>  </c:if>
						<c:if test = "${load.status=='In transit'}"> <c:set var="inTransit" value="${inTransit+1}"/>  </c:if>
						<c:if test = "${load.status=='Completed'}"> <c:set var="completed" value="${completed+1}"/>  </c:if>						
						<td>
							<div class="inputWithIcon">						
							 	<input  id="WaybillNumber" name="WaybillNumber" type="text" size="16" placeholder="Waybill Number" class="form-control" required="required"/>								 	
							</div> 
						</td>			
						<td>						
						</td>			
						<c:forEach items="${sessionSelectItem}" var="sessionSelectItems"></c:forEach>	
 						<%-- <td align="center"><a href="<c:url value='/drivers/${user.username}/${loaddates}' />"><button type="button" class="btn btn-primary pull-right">Assign</button></a></td> --%>											 
					</tr>
				</c:forEach>				                         
                </tbody>                          
              </table>       
              <a href="<c:url value='/load-list/${loaddates}' />"><button type="button" class="btn btn-default">Back</button></a>      		     
              	<button type="submit" class="btn btn-primary pull-right">Assign</button>  
	                <input type="hidden" name="loaddates" id="loaddates" value="${loaddates}"/> 
	                <c:forEach items="${loadSessionItems}" var="loadsSessionItems">        
	              	<input type="hidden" name="console-select-rows" id="console-select-rows" value="${loadsSessionItems.loadID}"/> 	
	              	</c:forEach> 
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
  $(function () {
	  	$("#UserTable").DataTable({	        scrollX: true	            }); 
	   
  });
</script>
</body>
</html>
