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
				<fmt:formatDate value="${joiningDate}" pattern="yyyy-MM-dd'T'HH:mm:ss"  var="setDateTimeNow" />
				<form:input type="hidden" path="joiningDate" id="joiningDates" value="${setDateTimeNow}"/>
				<fmt:formatDate value="${logoutDate}" pattern="yyyy-MM-dd'T'HH:mm:ss"  var="setDateTimeNows" />
				<form:input type="hidden" path="logoutDate" id="logoutDates" value="${setDateTimeNows}"/>       
				    
				
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">First Name :</label>
	                  <div class="col-sm-8">
	                   <form:input path="name" id="Names" name="Names" class="form-control" maxlength="30" data-error="Maximum of 30 characters" placeholder="Insert Your Name" required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Last Name :</label>
	                  <div class="col-sm-8">
	                   <form:input path="lastname" id="Lastnames" name="Lastnames" class="form-control" maxlength="30" data-error="Maximum of 30 characters" placeholder="Insert Your Name" required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Confirm Password :</label>
	                  <div class="col-sm-8" >
	                    <input name="Oldpasswords" id="Oldpasswords" type="password" placeholder="Insert  Password" class="form-control"  required="required"  />                  
	                  </div>
	             </div>
                <!-- <div class="form-group">
                  <label  class="col-sm-4 control-label">New Password :</label>
	                  <div class="col-sm-8" >
	                    <input  placeholder="Insert New password" type="password" name="Newpasswords" id="Newpasswords" class="form-control"  maxlength="16" data-error="Maximum of 16 characters" required="required"  />                  
	                  </div>
                </div> 
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Confirm New Password :</label>
	                  <div class="col-sm-8" >
	                    <input placeholder="Confirm New password" type="password" name="Conpasswords" id="Conpasswords" class="form-control" maxlength="16" data-error="Maximum of 16 characters" required="required"  />                  
	                  </div>
                </div> -->
                
                <div class="form-group">
                  <label  class="col-sm-4 control-label">E-Mail :</label>
	                  <div class="col-sm-8">
	                   <form:input path="email" id="Emails" name="Emails" class="form-control" placeholder="xample@example.com" data-error="Bruh, that email address is invalid" required="required" />                    
	                  </div>
                </div>
                
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Contact Number :</label>
	                  <div class="col-sm-8">
	                   <input  id="Contactnumbers" name="Contactnumbers" class="form-control" data-minlength="10" data-error="Minimum of 10 characters" maxlength="10" data-error="Maximum of 10 characters" placeholder="Insert Your Contact Number"   required="required" />                    
	                  </div>
                </div>
                              
               <div class="form-group">
                  <label  class="col-sm-4 control-label">Role :</label>
		          <div class="col-sm-8">  
		         <select  id="ListRolests" name="ListRolests" class="form-control">
		          <c:if test = "${not empty ListRolest}">
		          <c:forEach items="${ListRolest}" var="ListRoles">
		           <option value="${ListRoles.roleId}" >
		            		 ${ListRoles.role_n}
		           </option>
		          </c:forEach>
		          </c:if>  
		         </select>               
		        </div>
                 </div>
                
                
               <div class="form-group">
                  <label  class="col-sm-4 control-label">Department :</label>
		          <div class="col-sm-8">  
		         <select  id="ListDepartments" name="ListDepartments" class="form-control">
		          <c:if test = "${not empty ListDepartments}">
		          <c:forEach items="${ListDepartments}" var="ListDepartment">
		           <option value="${ListDepartment.departmentId}" >
		            		 ${ListDepartment.department_n}
		           </option>
		          </c:forEach>
		          </c:if>  
		         </select>               
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

var mailformat = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
var passwordformat =/^\w*(?=\w*\d)(?=\w*[a-z])(?=\w*[A-Z])/;
var usernameformat= /^([a-zA-Z0-9])+$/i
var Contactnumberformat= /^([0-9])+$/i
var nameformat = /^([a-zA-Z])+$/i;


function myFunction() 
{
	if(document.getElementById("Names").value.match(nameformat))
	{
		if(document.getElementById("Lastnames").value.match(nameformat))
		{
			if(document.getElementById("Oldpasswords").value.match(passwordformat))
			{
				if(document.getElementById("Emails").value.match(mailformat))
				{
					if(document.getElementById("Contactnumbers").value.match(Contactnumberformat))
					{
							if (document.getElementById("Passwordn").value != document.getElementById("Oldpasswords").value) 
						    {
								alert("Passwords Not Match!!!")
						        document.getElementById("Oldpasswords").focus();
						        return false;
						    }
						   /*  else if(document.getElementById("Newpasswords").value != document.getElementById("Conpasswords").value)
						    {
						        alert("New Passwords Not Match!!!")
								return false;
						    } 
						    else if(document.getElementById("Passwordn").value == document.getElementById("Conpasswords").value)
						    {         
						        alert("new password can't be old password!!!")
						        document.getElementById("Newpasswords").focus();
								return false;
						    } */
						    else
						    {            
						        document.getElementById("regForm").submit();
						        return true;
						    }
					}else{
						
						alert("Please fallow Contactnumber policy");
						document.getElementById("Contactnumbers").focus();
						return false;
						
					}
										    
				}else{
					
					alert("Please fallow Email policy");
					document.getElementById("Emails").focus();
					return false;
					
				}
										    
			}else{
				
				alert("Please fallow Current passwords policy");
				document.getElementById("Oldpasswords").focus();
				return false;
				
			}
										    
		}else{
			
			alert("Please fallow lastname policy");
			document.getElementById("Lastnames").focus();
			return false;
			
		}
										    
	}else{
		
		alert("Please fallow name policy");
		document.getElementById("Names").focus();
		return false;
	}
}
</script>
</body>
</html>
