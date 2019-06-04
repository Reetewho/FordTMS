<%@page import="org.w3c.dom.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AP Transport Center | User Detail</title>
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
       User Detail
        <small></small>
      </h1>
       <ol class="breadcrumb">
        <li><a href="#"><i class="fas fa-tachometer-alt"></i> &nbsp;&nbsp;Home</a></li>
        <li><a href="<c:url value='/userList'/>">User</a></li>
        <li><a href="#">User Detail</a></li>         
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
        
         <form:form method="POST" id="regForm" modelAttribute="user" onsubmit="return myFunction()" data-toggle="validator" role="form" >
            
          <div class="box box-primary">
          	 <div class="box-header with-border">
              <h3 class="box-title">User Edit</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body form-horizontal">                    
               <form:input type="hidden" path="username" id="username"/>
				<form:input type="hidden" path="status" id="status"/>
				<form:input type="hidden" path="password" id="password"/>
				<jsp:useBean id="now" class="java.util.Date"/>       
				<fmt:formatDate value="${now}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="setDateTimeNow" />
				<form:input type="hidden" path="joiningDate" id="joiningDate" value="${setDateTimeNow}"/>    
				
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">Name :</label>
	                  <div class="col-sm-8">
	                   <form:input path="name" id="names" class="form-control" placeholder="Insert Your Name" required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Old Password :</label>
	                  <div class="col-sm-8" >
	                    <input name="oldpassword" id="oldpassword" type="password" placeholder="Insert Old Password" class="form-control"  required="required"  />                  
	                  </div>
	             </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">New Password :</label>
	                  <div class="col-sm-8" >
	                    <input  placeholder="Insert New password" type="password" name="newpassword" id="newpassword" class="form-control" data-minlength="5" data-error="Minimum of 15 characters" required="required"  />                  
	                  </div>
                </div> 
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Confirm New Password :</label>
	                  <div class="col-sm-8" >
	                    <input placeholder="Confirm New password" type="password" name="conpassword" id="conpassword" class="form-control" data-minlength="5" data-error="Minimum of 15 characters" required="required"  />                  
	                  </div>
                </div>
                
               
                
               <div class="form-group">
                  <label  class="col-sm-4 control-label">Role :</label>
	                  <div class="col-sm-8" >
				         <form:select path="role" id="role" class="form-control">
				           <form:option value="ADMIN">ADMIN</form:option>
				           <form:option value="USER">USER</form:option>
				           <form:option value="CONTAINER">CONTAINER</form:option>
				           <form:option value="DRIVER">DRIVER</form:option>
				         </form:select>                   
			        	</div>
		       </div>	
                
               <div class="form-group">
                  <label  class="col-sm-4 control-label">Department :</label>
	                  <div class="col-sm-8" >
				         <form:select path="department" id="department" class="form-control">
				           <form:option value="IT NETWORK">IT NETWORK</form:option>
				           <form:option value="SOFTE WARE DEV">SOFTE WARE DEV</form:option>
				           <form:option value="ACCOUNT">ACCOUNT</form:option>				           
				         </form:select>                   
			        	</div>
		       </div>
                          
     
            </div>
            <!-- /.box-body -->
            <div class="box-footer">
                <a href="<c:url value='/user-list/' />"><button type="button" class="btn btn-default">Cancel</button></a>
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


<script>

    function myFunction() 
    {

        if (document.getElementById("password").value != document.getElementById("oldpassword").value) 
        {
            //document.getElementById("password");
           // document.getElementById("oldpassword");
            alert("Passwords Not Match!!!")
            return false;
        }
        else if(document.getElementById("newpassword").value != document.getElementById("conpassword").value)
        {
        	//document.getElementById("newpassword");
            //document.getElementById("conpassword");
            
            alert("New Passwords Not Match!!!")
			return false;
        }
        else
        {            
        	document.getElementById("password").value = document.getElementById("newpassword").value;
            document.getElementById("regForm").submit();
            return true;
        }
    }
    
</script>
</body>
</html>
