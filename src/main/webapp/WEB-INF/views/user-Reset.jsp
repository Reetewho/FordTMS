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

<input type="hidden" value="${passwordn}" id="Passwordn" name="Passwordn">

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
        
         <form:form method="POST" id="regForms"   onsubmit="return myFunction()" data-toggle="validator" role="form" >
 
          <div class="box box-primary">
          	 <div class="box-header with-border">
              <h3 class="box-title">Reset Password</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body form-horizontal">                    
           				
              <div class="form-group">
                  <label  class="col-sm-4 control-label">Current Password :</label>
	                  <div class="col-sm-8" >
	                    <input name="Oldpasswords" id="Currentpasswords" type="password" placeholder="Insert  Password" class="form-control"  required="required"  />                  
	                  </div>
	             </div>
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">New Password :</label>
	                  <div class="col-sm-8" >
	                    <input  placeholder="Insert New password" type="password" name="Newpasswordsc" id="Newpasswords" class="form-control" data-minlength="5" data-error="Minimum of 5 characters" maxlength="16" data-error="Maximum of 16 characters" required="required"  />                  
	                  </div>
                </div> 
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Confirm New Password :</label>
	                  <div class="col-sm-8" >
	                    <input placeholder="Confirm New password" type="password" name="Conpasswordsc" id="Conpasswords" class="form-control" data-minlength="5" data-error="Minimum of 5 characters" maxlength="16" data-error="Maximum of 16 characters" required="required"  />                  
	                  </div>
                </div> 
                
            </div>
            <!-- /.box-body -->
            <div class="box-footer">
                <a href="<c:url value='/userList/' />"><button type="button" class="btn btn-default">Cancel</button></a>
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

var passwordformat =/^\w*(?=\w*\d)(?=\w*[a-z])(?=\w*[A-Z])/;

function myFunction() 

{
			if(document.getElementById("Newpasswordsc").value.match(passwordformat))
			{
									
							if (document.getElementById("Passwordn").value != document.getElementById("Currentpasswords").value) 
						    {
								alert("Passwords Not Match!!!")
						        document.getElementById("Currentpasswords").focus();
						        return false;
						    }
						     else if(document.getElementById("Newpasswordsc").value != document.getElementById("Conpasswordsc").value)
						    {
						        alert("New Passwords Not Match!!!")
								return false;
						    } 
						    else if(document.getElementById("Passwordn").value == document.getElementById("Conpasswordsc").value)
						    {         
						        alert("new password can't be old password!!!")
						        document.getElementById("Newpasswordsc").focus();
								return false;
						    } 
						    else
						    {            
						        document.getElementById("regForms").submit();
						        return true;
						    }
							
			}else{
				alert("Please fallow New password policy");
				document.getElementById("Newpasswordsc").focus();
				return false;
			}
	
}
</script>
</body>
</html>
