<%@page import="org.w3c.dom.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AP Transport Center | Add User </title>
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
       Add New User
        <small></small>
      </h1>
       <ol class="breadcrumb">
        <li><a href="#"><i class="fas fa-tachometer-alt"></i> &nbsp;&nbsp;Home</a></li>
        <li><a href="<c:url value='/userList'/>">User</a></li>
        <li><a href="#">Add User</a></li>         
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
        
         <form method="POST" name="addForm" id="addForm" onsubmit="return myFunction()"  data-toggle="validator" role="form" >
            
          <div class="box box-primary">
          	 <div class="box-header with-border">
              <h3 class="box-title">Add User</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body form-horizontal">                    
               
				
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">User Name :</label>
	                  <div class="col-sm-7">
	                   <input  id="Usernamea" name="Usernamea" class="form-control" placeholder="Insert Your User Name" data-minlength="5" data-error="Minimum of 5 characters" maxlength="16" data-error="Maximum of 16 characters" required="required" />                    
	                  </div>
                </div>
               
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Password :</label>
	                  <div class="col-sm-7">
	                   <input  id="Passworda" type = "password" name="Passworda" class="form-control" placeholder="Insert Your Password" data-minlength="5" data-error="Minimum of 5 characters" maxlength="16" data-error="Maximum of 16 characters" required="required" />	                   
	                  </div>
	                  <div class="col-sm-1">
	                  <img src="<c:url value='/assets/dist/img/eyehidden.png' />"  alt="User Image" style="background-color:white" width= "25px" height="25px" onclick="hiddeneye()">
              		  </div>
			    </div>
                
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Confirm Password :</label>
	                  <div class="col-sm-7">
	                   <input  id="inputPasswordConfirm" type = "password" name="inputPasswordConfirm" class="form-control" placeholder="Insert Your Password" data-match="#Passworda" data-match-error="Whoops, these don't match" data-minlength="5" data-error="Minimum of 5 characters" maxlength="16" data-error="Maximum of 16 characters" placeholder="Confirm" required="required" />                    
	                  </div>
                </div>
                
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Name :</label>
	                  <div class="col-sm-7">
	                   <input  id="namesa" name="namesa" class="form-control" placeholder="Insert Your Name" maxlength="30" data-error="Maximum of 30 characters" required="required" />                    
	                  </div>
                </div>
                              
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">Last Name :</label>
	                  <div class="col-sm-7">
	                   <input  id="LastNamea" name="LastNamea" class="form-control" placeholder="Insert Your Last Name" maxlength="30" data-error="Maximum of 30 characters" required="required" />                    
	                  </div>
                </div>   
                
                <div class="form-group">
                  <label  class="col-sm-4 control-label">E-Mail :</label>
	                  <div class="col-sm-7">
	                   <input  id="Email" name="Email" class="form-control" placeholder="xample@example.com" data-error="Bruh, that email address is invalid" required="required" />                    
	                  </div>
                </div>
                
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Contact Number :</label>
	                  <div class="col-sm-7">
	                   <input  id="Contactnumber" name="Contactnumber" class="form-control" placeholder="Insert Your Contact Number" data-minlength="10" data-error="Minimum of 10 characters" maxlength="10" data-error="Maximum of 10 characters"  required="required" />                    
	                  </div>
                </div>          
                              
               <div class="form-group">
                  <label  class="col-sm-4 control-label">Role :</label>
		          <div class="col-sm-7">  
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
		          <div class="col-sm-7">  
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
           </form> 
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


function hiddeneye() {
	  var x = document.getElementById("Passworda");
	  if (x.type === "password") {
	    x.type = "text";
	  } else {
	    x.type = "password";
	  }
	}


function myFunction() 
	{
	
	
	var pwdPolicy =/^\w*(?=\w*\d)(?=\w*[a-z])(?=\w*[A-Z])/;
	var mailformat = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	var nameformat = /^([a-zA-Z])+$/i;
	var usernameformat= /^([a-zA-Z0-9])+$/i
	var Contactnumberformat= /^([0-9])+$/i
	
	var Contactnumbers=document.getElementById("Contactnumber").value;
	var usernameas=document.getElementById("Usernamea").value;
	var pass=document.getElementById("Passworda").value;
	var namese =document.getElementById("namesa").value;
	var LastNamees=document.getElementById("LastNamea").value;
	var passEmail=document.getElementById("Email").value;
	

	if(usernameas.match(usernameformat))
	{		
	if(pass.match(pwdPolicy))
	{
		if(namese.match(nameformat))
		{
			if(LastNamees.match(nameformat))	
				{
				if(Contactnumbers.match(Contactnumberformat))
				{		
				if(passEmail.match(mailformat))
					{
						return true;
						
					}else{
							alert("You have entered an invalid email address!");
							document.getElementById("Email").focus();
							return false;
						 }
				}else{
					alert("Please fallow LastName policy");
					return false;
				}
				}else{
					alert("Please fallow LastName policy");
					return false;
				}
		}else{
			alert("Please fallow names policy");
			return false;
		}
	
		
		/* alert("Add User success");
		return true; */
	}else{
			alert("Please fallow password policy");
			document.getElementById("Passworda").focus();
			return false;
		}
	}else{
		alert("Please fallow Username policy");
		return false;
		}
	}
	
	
	
	
	

   
    
</script>
</body>
</html>
