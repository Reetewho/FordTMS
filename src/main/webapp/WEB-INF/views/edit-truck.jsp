<%@page import="org.w3c.dom.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AP Transport Center | Truck Detail</title>
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
       Truck Detail
        <small></small>
      </h1>
       <ol class="breadcrumb">
        <li><a href="#"><i class="fas fa-tachometer-alt"></i> &nbsp;&nbsp;Home</a></li>
        <li><a href="<c:url value='/userList'/>">User</a></li>
        <li><a href="#">Truck Detail</a></li>         
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
    
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
      
      <div class="row">
      <!-- /.col -->     
        <div class="col-md-7">
        
         <form:form method="POST" id="regForm" modelAttribute="trucks" onsubmit="return myFunction()" data-toggle="validator" role="form" >
            
          <div class="box box-primary">
          	 <div class="box-header with-border">
              <h3 class="box-title">Truck Edit</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body form-horizontal">                    
               
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">TRUCK NUMBER :</label>
	                  <div class="col-sm-8">
	                   <form:input path="TRUCK_NUMBER" id="TRUCKNUMBER" name="TRUCKNUMBER" class="form-control" maxlength="30" data-error="Maximum of 30 characters" placeholder="Insert Your TRUCK NUMBER" required="required" />                    
	                  </div>
                </div>         
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Truck Type :</label>
		          <div class="col-sm-8">  
		         <select  id="TruckType" name="TruckType" class="form-control">
		          <c:if test = "${not empty ListType}">
		          <c:forEach items="${ListType}" var="ListType">
		           <option value="${ListType.TRUCKTYPE_id}" >
		            		 ${ListType.TRUCKTYPE_TYPE}
		           </option>
		          </c:forEach>
		          </c:if>  
		         </select>               
		        </div>
                 </div>                      
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">GPS TRUCK :</label>
		          <div class="col-sm-8">  
		         <select  id="GPSTRUCK" name="GPSTRUCK" class="form-control">
		          <c:if test = "${not empty TruckGps}">
		          <c:forEach items="${TruckGps}" var="GpsType">
		           <option value="${GpsType.GPSId}" >
		            		  ${GpsType.GPSTYPE}
		           </option>
		          </c:forEach>
		          </c:if>  
		         </select>               
		        </div>
                 </div>   
                <%-- <form:input type="hidden" path="TRUCK_NUMBER" id="TRUCKNUMBERS"/>    --%>    
                <form:input type="hidden" path="CREATE_BY" id="CREATEBY"/>
                <form:input type="hidden" path="CREATE_DATE" id="CREATEDATE"/>
                <form:input type="hidden" path="STATUS" id="STATUS"/>                        
            </div>
            <!-- /.box-body -->
            <div class="box-footer">
                <a href="<c:url value='/trucklist/' />"><button type="button" class="btn btn-default">Cancel</button></a>
                <button type="submit" class="btn btn-primary pull-right">Submit</button>
              </div>
              <!-- /.box-footer -->
          </div>
          <!-- /. box -->
           </form:form> 
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
<script type="text/javascript">
			  document.getElementById("TRUCKNUMBER").disabled = true;		  

			  </script>

</body>
</html>
