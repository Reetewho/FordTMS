<%@page import="org.w3c.dom.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AP Transport Center | stakeholder Truck </title>
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
       Add New stakeholder
        <small></small>
      </h1>
       <ol class="breadcrumb">
        <li><a href="#"><i class="fas fa-tachometer-alt"></i> &nbsp;&nbsp;Home</a></li>
        <li><a href="<c:url value='/userList'/>">Truck</a></li>
        <li><a href="#">Add stakeholder</a></li>         
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
              <h3 class="box-title">Add StakeHolder</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body form-horizontal">                    
               
				
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">Stake Holder Id :</label>
	                  <div class="col-sm-7">
	                   <input  id="StakeId" name="StakeId" class="form-control" placeholder="Insert Your Stake Holder Id" data-minlength="2" data-error="Minimum of 2 characters" maxlength="30" data-error="Maximum of 30 characters" required="required" />                    
	                  </div>
                </div>                                                                                          
           
            <div class="form-group">
                  <label  class="col-sm-4 control-label">Stake Holder Name :</label>
	                  <div class="col-sm-7">
	                   <input  id="StakeName" name="StakeName" class="form-control" placeholder="Insert Your Stake Holder Name" data-minlength="2" data-error="Minimum of 2 characters" maxlength="150" data-error="Maximum of 150 characters" required="required" />                    
	                  </div>
                </div>                                                                                          
            
            <div class="form-group">
                  <label  class="col-sm-4 control-label">Stake Holder Address :</label>
	                  <div class="col-sm-7">
	                   <input  id="StakeAddress" name="StakeAddress" class="form-control" placeholder="Insert Your Stake Holder Name" data-minlength="2" data-error="Minimum of 2 characters" maxlength="150" data-error="Maximum of 150 characters" required="required" />                    
	                  </div>
                </div>                                                                                          
            
             <div class="form-group">
                  <label  class="col-sm-4 control-label">Stake Holder District :</label>
	                  <div class="col-sm-7">
	                   <input  id="StakeDistrict" name="StakeDistrict" class="form-control" placeholder="Insert Your Stake Holder District" data-minlength="2" data-error="Minimum of 2 characters" maxlength="150" data-error="Maximum of 150 characters" required="required" />                    
	                  </div>
                </div>                                                                                          
            
             <div class="form-group">
                  <label  class="col-sm-4 control-label">Stake Holder Sub District :</label>
	                  <div class="col-sm-7">
	                   <input  id="StakeSubdistrict" name="StakeSubdistrict" class="form-control" placeholder="Insert Your Stake Holder Sub District" data-minlength="2" data-error="Minimum of 2 characters" maxlength="150" data-error="Maximum of 150 characters" required="required" />                    
	                  </div>
                </div>                                                                                          
           
             <div class="form-group">
                  <label  class="col-sm-4 control-label">Stake Holder Province :</label>
	                  <div class="col-sm-7">
	                   <input  id="StakeProvince" name="StakeProvince" class="form-control" placeholder="Insert Your Stake Holder Province" data-minlength="2" data-error="Minimum of 2 characters" maxlength="150" data-error="Maximum of 150 characters" required="required" />                    
	                  </div>
                </div>                                                                                          
            
             <div class="form-group">
                  <label  class="col-sm-4 control-label">Stake Holder Post Code :</label>
	                  <div class="col-sm-7">
	                   <input  id="StakePostCode" name="StakePostCode" class="form-control" placeholder="Insert Your Stake Holder Post Code" data-minlength="2" data-error="Minimum of 2 characters" maxlength="150" data-error="Maximum of 150 characters" required="required" />                    
	                  </div>
                </div>                                                                                          
            
            <div class="form-group">
                  <label  class="col-sm-4 control-label">Stake Holder Tel No :</label>
	                  <div class="col-sm-7">
	                   <input  id="StakeTel" name="StakeTel" class="form-control" placeholder="Insert Your Stake Holder Tel No" data-minlength="2" data-error="Minimum of 2 characters" maxlength="150" data-error="Maximum of 150 characters" required="required" />                    
	                  </div>
                </div>  
                
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Stake Holder Fax No :</label>
	                  <div class="col-sm-7">
	                   <input  id="StakeFax" name="StakeFax" class="form-control" placeholder="Insert Your Stake Holder Fax No" data-minlength="2" data-error="Minimum of 2 characters" maxlength="150" data-error="Maximum of 150 characters" required="required" />                    
	                  </div>
                </div> 
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">Stake Holder Mobile No :</label>
	                  <div class="col-sm-7">
	                   <input  id="StakeMobile" name="StakeMobile" class="form-control" placeholder="Insert Your Stake Holder Mobile No" data-minlength="2" data-error="Minimum of 2 characters" maxlength="150" data-error="Maximum of 150 characters" required="required" />                    
	                  </div>
                </div>  
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">Stake Holder E-mail :</label>
	                  <div class="col-sm-7">
	                   <input  id="Stakemail" name="Stakemail" class="form-control" placeholder="Insert Your Stake Holder E-mail" data-minlength="2" data-error="Minimum of 2 characters" maxlength="150" data-error="Maximum of 150 characters" required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Stake Holder Contact Name :</label>
	                  <div class="col-sm-7">
	                   <input  id="StakeContactName" name="StakeContactName" class="form-control" placeholder="Insert Your Stake Holder Contact Name " data-error="Minimum of 2 characters" maxlength="150" data-error="Maximum of 150 characters" required="required" />                    
	                  </div>
                </div> 
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Stake Holder Tax No :</label>
	                  <div class="col-sm-7">
	                   <input  id="StakeTaxNo" name="StakeTaxNo" class="form-control" placeholder="Insert Your Stake Holder Tax No" data-minlength="2" data-error="Minimum of 2 characters" maxlength="150" data-error="Maximum of 150 characters" required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Stake Holder Sale Type :</label>
	                  <div class="col-sm-7">
	                   <input  id="StakeSaleType" name="StakeSaleType" class="form-control" placeholder="Insert Your Stake Holder Sale Type" data-minlength="2" data-error="Minimum of 2 characters" maxlength="150" data-error="Maximum of 150 characters" required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Stake Holder Payment Condition :</label>
	                  <div class="col-sm-7">
	                   <input  id="StakePaymentCondition" name="StakePaymentCondition" class="form-control" placeholder="Insert Your Stake Holder Payment Condition" data-minlength="2" data-error="Minimum of 2 characters" maxlength="150" data-error="Maximum of 150 characters" required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Stake Holder Payment Method :</label>
	                  <div class="col-sm-7">
	                   <input  id="StakePaymentMethod" name="StakePaymentMethod" class="form-control" placeholder="Insert Your Stake Holder Payment Method" data-minlength="2" data-error="Minimum of 2 characters" maxlength="150" data-error="Maximum of 150 characters" required="required" />                    
	                  </div>
                </div> 
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">Stake Holder Ref Bank :</label>
	                  <div class="col-sm-7">
	                   <input  id="StakeRefBank" name="StakeRefBank" class="form-control" placeholder="Insert Your Stake Holder Ref Bank" data-minlength="2" data-error="Minimum of 2 characters" maxlength="150" data-error="Maximum of 150 characters" required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Stake Holder Ref Bank Branch :</label>
	                  <div class="col-sm-7">
	                   <input  id="StakeRefBankBranch" name="StakeRefBankBranch" class="form-control" placeholder="Insert Your Stake Holder Ref Bank Branch" data-minlength="2" data-error="Minimum of 2 characters" maxlength="150" data-error="Maximum of 150 characters" required="required" />                    
	                  </div>
                </div> 
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">Stake Holder Account No :</label>
	                  <div class="col-sm-7">
	                   <input  id="StakeAccountNo" name="StakeAccountNo" class="form-control" placeholder="Insert Your Stake Holder Account No" data-minlength="2" data-error="Minimum of 2 characters" maxlength="150" data-error="Maximum of 150 characters" required="required" />                    
	                  </div>
                </div>  
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">Stake Holder Account Name :</label>
	                  <div class="col-sm-7">
	                   <input  id="StakeAccountName" name="StakeAccountName" class="form-control" placeholder="Insert Your Stake Holder Account Name" data-minlength="2" data-error="Minimum of 2 characters" maxlength="150" data-error="Maximum of 150 characters" required="required" />                    
	                  </div>
                </div> 
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Stake Holder Project Name :</label>
	                  <div class="col-sm-7">
	                   <input  id="StakeProject" name="StakeProject" class="form-control" placeholder="Insert Your Stake Holder Project Name" data-minlength="2" data-error="Minimum of 2 characters" maxlength="150" data-error="Maximum of 150 characters" required="required" />                    
	                  </div>
                </div>
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">Stake Holder Credit Term :</label>
	                  <div class="col-sm-7">
	                   <input  id="StakeCreditTerm" name="StakeCreditTerm" class="form-control" placeholder="Insert Your Stake Holder Credit Term" data-minlength="2" data-error="Minimum of 2 characters" maxlength="150" data-error="Maximum of 150 characters" required="required" />                    
	                  </div>
                </div> 
                  <div class="form-group">
                  <label  class="col-sm-4 control-label">Stake Holder Type :</label>
	                  <div class="col-sm-7">
	                   <select name="StakeType" class="form-control">       
							<option value="1">SUPPLIER</option>       
							<option value="2">CUSTOMER</option>       
							<option value="3">OTHER</option>   
						</select>
	                  </div>
                </div>                                                                                      
 
            <!-- /.box-body -->
            <div class="box-footer">
                <a href="<c:url value='/stakeholderlist/' />"><button type="button" class="btn btn-default">Cancel</button></a>
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
