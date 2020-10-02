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
       Add New Truck
        <small></small>
      </h1>
       <ol class="breadcrumb">
        <li><a href="#"><i class="fas fa-tachometer-alt"></i> &nbsp;&nbsp;Home</a></li>
        <li><a href="<c:url value='/userList'/>">Truck</a></li>
        <li><a href="#">Add Truck</a></li>         
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
              <h3 class="box-title">Add Truck</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body form-horizontal">                    
               
				
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">Truck Number :</label>
	                  <div class="col-sm-7">
	                   <input  id="TruckNumber" name="TruckNumber" class="form-control" placeholder="Insert Your Truck Number" data-minlength="2" data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div>                                                                          
               <div class="form-group">
                  <label  class="col-sm-4 control-label">Truck Type :</label>
		          <div class="col-sm-7">  
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
		          <div class="col-sm-7">  
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
            </div>
            <!-- /.box-body -->
            <div class="box-footer">
                <a href="<c:url value='/trucklist/' />"><button type="button" class="btn btn-default">Cancel</button></a>
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
	 /*  var x = document.getElementById("Passworda");
	  if (x.type === "password") {
	    x.type = "text";
	  } else {
	    x.type = "password";
	  }
	} */


function myFunction() 
	{
	
	
	var passwordformat =/^\w*(?=\w*\d)(?=\w*[a-z])(?=\w*[A-Z])/;
	var mailformat = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	var nameformat = /^([a-zA-Z])+$/i;
	var usernameformat= /^([a-zA-Z0-9])+$/i
	var Contactnumberformat= /^([0-9])+$/i	

/* if(document.getElementById("Usernamea").value.match(usernameformat))
{		
	if(document.getElementById("Passworda").value.match(passwordformat))
	{
		if(document.getElementById("namesa").value.match(nameformat))
		{
			if(document.getElementById("LastNamea").value.match(nameformat))	
				{
				if(document.getElementById("Contactnumber").value.match(Contactnumberformat))
				{		
				if(document.getElementById("Email").value.match(mailformat))
					{
						return true;
						
					}else{
							alert("You have entered an invalid email address!");
							document.getElementById("Email").focus();
							return false;
						 }
				}else{
					alert("Please follow Contactnumber policy");
					document.getElementById("Contactnumber").focus();
					return false;
				}
				
				}else{
					alert("Please follow LastName policy");
					document.getElementById("LastNamea").focus();
					return false;
				}
		}else{
			alert("Please follow names policy");
			document.getElementById("namesa").focus();
			return false;
		}

	}else{
			alert("Please follow password policy");
			document.getElementById("Passworda").focus();
			return false;
		}
	
	}else{
		alert("Please follow Username policy");
		document.getElementById("Usernamea").focus();
		return false;
		} */
	}
	
	
	
	
	

   
    
</script>
</body>
</html>
