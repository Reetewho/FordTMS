<%@page import="org.w3c.dom.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AP Transport Center | GSDB Detail</title>
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
       GSDB Detail
        <small></small>
      </h1>
       <ol class="breadcrumb">
        <li><a href="#"><i class="fas fa-tachometer-alt"></i> &nbsp;&nbsp;Home</a></li>
        <li><a href="<c:url value='/userList'/>">GSDB</a></li>
        <li><a href="#">GSDB Detail</a></li>         
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
        
         <form:form method="POST" id="regForm" modelAttribute="GSDB" onsubmit="return myFunction()" data-toggle="validator" role="form" >
            
          <div class="box box-primary">
          	 <div class="box-header with-border">
              <h3 class="box-title">GSDB Edit</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body form-horizontal">                    
               
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">GSDB Code :</label>
	                  <div class="col-sm-8">
	                   <form:input path="GSDBCODE" id="GSDBCODE" name="GSDBCODE" class="form-control" maxlength="200" data-error="Maximum of 30 characters" placeholder="Insert Your GSDB Code" required="required" />                    
	                  </div>
                </div>   
                <div class="form-group">
                  <label  class="col-sm-4 control-label">GSDB Name :</label>
	                  <div class="col-sm-8">
	                   <form:input path="GSDBNAME" id="GSDBNAME" name="GSDBNAME" class="form-control" maxlength="200" data-error="Maximum of 200 characters" placeholder="Insert Your GSDB Name" required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Radius :</label>
	                  <div class="col-sm-8">
	                   <form:input path="GSDBRADIUS" id="GSDBRADIUS" name="GSDBRADIUS" class="form-control" maxlength="30" data-error="Maximum of 30 characters" placeholder="Insert Your Radius" required="required" />                    
	                  </div>
                </div>  
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Latitude :</label>
	                  <div class="col-sm-8">
	                   <form:input path="GSDBLATITUDE" id="GSDBLATITUDE" name="GSDBLATITUDE" class="form-control" maxlength="30" data-error="Maximum of 30 characters" placeholder="Insert Your Latitude" required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Longitude :</label>
	                  <div class="col-sm-8">
	                   <form:input path="GSDBLONGITUDE" id="GSDBLONGITUDE" name="GSDBLONGITUDE" class="form-control" maxlength="30" data-error="Maximum of 30 characters" placeholder="Insert Your Longitude" required="required" />                    
	                  </div>
                </div>    
                
				<div class="form-group">
                  <label  class="col-sm-4 control-label">จังหวัด :</label>
	                  <div class="col-sm-8">	                   
	                    <select id="provinceId" name="provinceId" class="form-control selectpicker" data-live-search="true">
				          <c:if test = "${not empty provinces}">
				          <c:forEach items="${provinces}" var="findProvince">
				           <option value="${findProvince.id}"  ${findProvince.id==GSDB.provinceId?'selected':''} >
				            ${findProvince.nameTh}
				           </option>
				          </c:forEach>
				          </c:if>  
				       </select>   
	                   
	                  </div>
                </div>
                
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Area Zone :</label>
	                  <div class="col-sm-8">
	                   <form:input path="areaZone" id="areaZone" name="areaZone" class="form-control" maxlength="30" data-error="Maximum of 30 characters" placeholder="Insert Your areaZone" required="required" />                    
	                  </div>
                </div>
                
               <%--  <div class="form-group">
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
                 </div> --%>                      
                 <%-- <div class="form-group">
                  <label  class="col-sm-4 control-label">GPS Truck :</label>
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
                 </div> --%>   
                 <form:input type="hidden" path="GSDBCODE" id="GSDBCODE"/>
                <form:input type="hidden" path="GSDBDELIVERYTYPE" id="GSDBDELIVERYTYPE"/>   
                <form:input type="hidden" path="GSDBCREATEBY" id="GSDBCREATEBY"/>
                <form:input type="hidden" path="GSDBCREATERDATE" id="GSDBCREATERDATE"/>
                <form:input type="hidden" path="GSDBSTARUS" id="GSDBSTARUS"/>                        
            </div>
            <!-- /.box-body -->
            <div class="box-footer">
                <a href="<c:url value='/gsdblist/' />"><button type="button" class="btn btn-default">Cancel</button></a>
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
			  document.getElementById("GSDBCODE").disabled = true;		  

			  </script>

</body>
</html>
