<%@page import="org.w3c.dom.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AP Transport Center | StakeHolder Detail</title>
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
       StakeHolder Detail
        <small></small>
      </h1>
       <ol class="breadcrumb">
        <li><a href="#"><i class="fas fa-tachometer-alt"></i> &nbsp;&nbsp;Home</a></li>
        <li><a href="<c:url value='/StakeHolder'/>">StakeHolder</a></li>
        <li><a href="#">StakeHolder Detail</a></li>         
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
        
         <form:form method="POST" id="regForm" modelAttribute="FindByStakeHolder" data-toggle="validator" role="form" >
            
          <div class="box box-primary">
          	 <div class="box-header with-border">
              <h3 class="box-title">StakeHolder Edit</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body form-horizontal">                    
               
                 <div class="form-group">
                  <label  class="col-sm-5 control-label">รหัส ( Stake Holder Id ) :</label>
	                  <div class="col-sm-7">
	                   <form:input path="STAKE_HOLDER_ID" id="STAKE_HOLDER_ID" class="form-control" readonly="true" maxlength="30" data-error="Maximum of 30 characters" placeholder="Insert Your STAKE HOLDER ID" required="required" />                    
	                  </div>
                </div> 
                <div class="form-group">
                  <label  class="col-sm-5 control-label">ชื่อ ( Stake Holder Name ) :</label>
	                  <div class="col-sm-7">
	                   <form:input path="STAKE_HOLDER_NAME" id="STAKE_HOLDER_NAME"  class="form-control" maxlength="150" data-error="Maximum of 150 characters" required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-5 control-label">ที่อยู่ ( Stake Holder Address ) :</label>
	                  <div class="col-sm-7">
	                   <form:input path="STAKE_HOLDER_ADDRESS" id="STAKE_HOLDER_ADDRESS" class="form-control" maxlength="100" data-error="Maximum of 100 characters"  required="required" />                    
	                  </div>
                </div>              
                <div class="form-group">
                  <label  class="col-sm-5 control-label">ตำบล /แขวง ( Stake Holder District ) :</label>
	                  <div class="col-sm-7">
	                   <form:input  path="STAKE_HOLDER_SUBDISTRICT" id="STAKE_HOLDER_SUBDISTRICT" class="form-control" maxlength="100" data-error="Maximum of 100 characters"  required="required" />                    
	                  </div>
                </div>  
                <div class="form-group">
                  <label  class="col-sm-5 control-label">อำเภอเ / เขต( Stake Holder Sub District ) :</label>
	                  <div class="col-sm-7">
	                   <form:input path="STAKE_HOLDER_DISTRICT" id="STAKE_HOLDER_DISTRICT" class="form-control" maxlength="100" data-error="Maximum of 100 characters"  required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-5 control-label">จังหวัด ( Stake Holder Province ) :</label>
	                  <div class="col-sm-7">
	                   <form:input path="STAKE_HOLDER_PROVINCE" id="STAKE_HOLDER_PROVINCE"  class="form-control" maxlength="100" data-error="Maximum of 100 characters"  required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-5 control-label">รหัสไปรษณีย์ ( Stake Holder Post Code ) :</label>
	                  <div class="col-sm-7">
	                   <form:input path="STAKE_HOLDER_POST_CODE" id="STAKE_HOLDER_POST_CODE"  class="form-control" maxlength="100" data-error="Maximum of 100 characters"  required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-5 control-label">ไทรศัพท์ ( Stake Holder Tel No ) :</label>
	                  <div class="col-sm-7">
	                   <form:input path="STAKE_HOLDER_TEL_NO" id="STAKE_HOLDER_TEL_NO"  class="form-control" maxlength="100" data-error="Maximum of 100 characters"  required="required" />                    
	                  </div>
                </div>
                 <div class="form-group">
                  <label  class="col-sm-5 control-label">แฟกซ์ ( Stake Holder Fax No ) :</label>
	                  <div class="col-sm-7">
	                   <form:input path="STAKE_HOLDER_FAX_NO" id="STAKE_HOLDER_FAX_NO" class="form-control" maxlength="100" data-error="Maximum of 100 characters"  required="required" />                    
	                  </div>
                </div> 
                <div class="form-group">
                  <label  class="col-sm-5 control-label">โทรศัพท์มือถือ ( Stake Holder Mobile No ) :</label>
	                  <div class="col-sm-7">
	                   <form:input path="STAKE_HOLDER_MOBILE_NO" id="STAKE_HOLDER_MOBILE_NO"  class="form-control" maxlength="100" data-error="Maximum of 100 characters"  required="required" />                    
	                  </div>
                </div> 
                <div class="form-group">
                  <label  class="col-sm-5 control-label">อีเมล์ ( Stake Holder E-mail ) :</label>
	                  <div class="col-sm-7">
	                   <form:input path="STAKE_HOLDER_EMAIL" id="STAKE_HOLDER_EMAIL"  class="form-control" maxlength="100" data-error="Maximum of 100 characters"  required="required" />                    
	                  </div>
                </div> 
                 <div class="form-group">
                  <label  class="col-sm-5 control-label">ชื่อผู้ติดต่อ ( Stake Holder Contact Name ) :</label>
	                  <div class="col-sm-7">
	                   <form:input path="STAKE_HOLDER_CONTACT_NAME" id="STAKE_HOLDER_CONTACT_NAME" class="form-control" maxlength="100" data-error="Maximum of 100 characters"  required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-5 control-label">เลขประจำตัวผู้เสียถาษี ( Stake Holder Tax No ) :</label>
	                  <div class="col-sm-7">
	                   <form:input path="STAKE_HOLDER_TAX_NO" id="STAKE_HOLDER_TAX_NO" class="form-control" maxlength="100" data-error="Maximum of 100 characters"  required="required" />                    
	                  </div>
                </div>
<%--                 <div class="form-group">
                  <label  class="col-sm-5 control-label">Stake Holder Sale Type :</label>
	                  <div class="col-sm-7">
	                   <form:input path="STAKE_HOLDER_SALE_TYPE" id="STAKE_HOLDER_SALE_TYPE" class="form-control" maxlength="100" data-error="Maximum of 100 characters"  required="required" />                    
	                  </div>
                </div>    --%>             
                 <div class="form-group">
                  <label  class="col-sm-5 control-label">ประเภทผุ้ขาย ( Stake Holder Sale Type ) :</label>
	                  <div class="col-sm-7">	                   
	                    <select id="STAKEHOLDERSALETYPE" name="STAKEHOLDERSALETYPE" class="form-control">
				          <c:if test = "${not empty FindSaleType}">
				          <c:forEach items="${FindSaleType}" var="FindSaleType">
				           <option value="${FindSaleType.saletypeid}"  ${FindSaleType.saletypeid==FindByStakeHolder.STAKE_HOLDER_SALE_TYPE?'selected':''} >
				            ${FindSaleType.saletypename}
				           </option>
				          </c:forEach>
				          </c:if>  
				       </select>   
	                   
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-5 control-label">( Stake Holder Payment Condition ) :</label>
	                  <div class="col-sm-7">
	                   <form:input path="STAKE_HOLDER_PAYMENT_CONDITION" id="STAKE_HOLDER_PAYMENT_CONDITION"  class="form-control" maxlength="100" data-error="Maximum of 100 characters"  required="required" />                    
	                  </div>
                </div>
<%--                 <div class="form-group">
                  <label  class="col-sm-5 control-label">การจ่ายเงิน ( Stake Holder Payment Method ) :</label>
	                  <div class="col-sm-7">
	                   <form:input path="STAKE_HOLDER_PAYMENT_METHOD" id="STAKE_HOLDER_PAYMENT_METHOD" class="form-control" maxlength="100" data-error="Maximum of 100 characters"  required="required" />                    
	                  </div>
                </div> --%>
                
                 <div class="form-group">
                  <label  class="col-sm-5 control-label">การจ่ายเงิน ( Stake Holder Payment Method ) :</label>
	                  <div class="col-sm-7">	                   
	                    <select id="STAKEHOLDERPAYMENTMETHOD" name="STAKEHOLDERPAYMENTMETHOD" class="form-control">
				          <c:if test = "${not empty FindPaymentMethod}">
				          <c:forEach items="${FindPaymentMethod}" var="FindPaymentMethod">
				           <option value="${FindPaymentMethod.paymentmethodid}"  ${FindPaymentMethod.paymentmethodid==FindByStakeHolder.STAKE_HOLDER_PAYMENT_METHOD?'selected':''} >
				            ${FindPaymentMethod.paymentmethodname}
				           </option>
				          </c:forEach>
				          </c:if>  
				       </select>   
	                   
	                  </div>
                </div>
                
                
                <div class="form-group">
                  <label  class="col-sm-5 control-label">ธนาคาร ( Stake Holder Ref Bank ) :</label>
	                  <div class="col-sm-7">
	                   <form:input path="STAKE_HOLDER_REF_BANK" id="STAKE_HOLDER_REF_BANK" class="form-control" maxlength="100" data-error="Maximum of 100 characters"  required="required" />                    
	                  </div>
                </div>
                 <div class="form-group">
                  <label  class="col-sm-5 control-label">สาขา ( Stake Holder Ref Bank Branch ) :</label>
	                  <div class="col-sm-7">
	                   <form:input path="STAKE_HOLDER_REF_BANK_BRANCH" id="STAKE_HOLDER_REF_BANK_BRANCH" class="form-control" maxlength="100" data-error="Maximum of 100 characters"  required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-5 control-label">เลขที่บัญชี ( Stake Holder Account No ) :</label>
	                  <div class="col-sm-7">
	                   <form:input path="STAKE_HOLDER_REF_ACCOUNT_NO" id="STAKE_HOLDER_REF_ACCOUNT_NO"  class="form-control" maxlength="100" data-error="Maximum of 100 characters"  required="required" />                    
	                  </div>
                </div>
                 <div class="form-group">
                  <label  class="col-sm-5 control-label">ชื่อบัญชีธนาคาร ( Stake Holder Account Name ) :</label>
	                  <div class="col-sm-7">
	                   <form:input path="STAKE_HOLDER_REF_ACCOUNT_NAME" id="STAKE_HOLDER_REF_ACCOUNT_NAME" class="form-control" maxlength="100" data-error="Maximum of 100 characters"  required="required" />                    
	                  </div>
                </div>
               <%--  <div class="form-group">
                  <label  class="col-sm-5 control-label">หน่วยงานโปรเจค ( Stake Holder Project Name ) :</label>
	                  <div class="col-sm-7">
	                   <form:input path="STAKE_HOLDER_PROJECT_NAME" id="STAKE_HOLDER_PROJECT_NAME"  class="form-control" maxlength="100" data-error="Maximum of 100 characters"  required="required" />                    
	                  </div>
                </div>  --%>
                
                 <div class="form-group">
                  <label  class="col-sm-5 control-label">หน่วยงานโปรเจค ( Stake Holder Project Name ) :</label>
	                  <div class="col-sm-7">	                   
	                    <select id="STAKEHOLDERPROJECT" name="STAKEHOLDERPROJECT" class="form-control">
				          <c:if test = "${not empty FindProject}">
				          <c:forEach items="${FindProject}" var="FindProject">
				           <option value="${FindProject.projectnameid}"  ${FindProject.projectnameid==FindByStakeHolder.STAKE_HOLDER_PROJECT?'selected':''} >
				            ${FindProject.projectnamename}
				           </option>
				          </c:forEach>
				          </c:if>  
				       </select>   
	                   
	                  </div>
                </div>
                
                
                
                <%--  <div class="form-group">
                  <label  class="col-sm-5 control-label">เงื่อนไขการชำระเงิน ( Stake Holder Credit Term ) :</label>
	                  <div class="col-sm-7">
	                   <form:input path="STAKE_HOLDER_CREDIT_TERM" id="STAKE_HOLDER_CREDIT_TERM"  class="form-control" maxlength="100" data-error="Maximum of 100 characters"  required="required" />                    
	                  </div>
                </div>   --%>
                
                 <div class="form-group">
                  <label  class="col-sm-5 control-label">เงื่อนไขการชำระเงิน ( Stake Holder Credit Term ) :</label>
	                  <div class="col-sm-7">	                   
	                    <select id="STAKEHOLDERCREDITTERM" name="STAKEHOLDERCREDITTERM" class="form-control">
				          <c:if test = "${not empty FindCreditTerm}">
				          <c:forEach items="${FindCreditTerm}" var="FindCreditTerm">
				           <option value="${FindCreditTerm.credittermid}"  ${FindCreditTerm.credittermid==FindByStakeHolder.STAKE_HOLDER_CREDIT_TERM?'selected':''} >
				            ${FindCreditTerm.credittermname}
				           </option>
				          </c:forEach>
				          </c:if>  
				       </select>   
	                   
	                  </div>
                </div>
                
                <div class="form-group">
                	<label  class="col-sm-5 control-label">ประเภท ( Stake Holder Type ) :</label>
		        	<div class="col-sm-7">  
		        		<form:select class="form-control" path="STAKE_HOLDER_TYPE" items="${StakeHoldertype}"/>            
		        	</div>
                 </div>
                                                 
                <form:input type="hidden" path="STAKE_HOLDER_UPDATE_DATE" id="STAKE_HOLDER_UPDATE_DATE"/>  
                 <form:input type="hidden" path="STAKE_HOLDER_UPDATE_BY" id="STAKE_HOLDER_UPDATE_BY"/>
                <!-- <form:input type="hidden" path="STAKE_HOLDER_TYPE" id="STAKE_HOLDER_TYPE"/>  --> 
                 <form:input type="hidden" path="STAKE_HOLDER_CREATE_BY" id="STAKE_HOLDER_CREATE_BY"/>
                <form:input type="hidden" path="STAKE_HOLDER_CREATE_DATE" id="STAKE_HOLDER_CREATE_DATE"/>
                <!-- <form:input type="hidden" path="STAKE_HOLDER_ID" id="STAKEHOLDERID"/> -->
                <form:input type="hidden" path="STAKE_HOLDER_STATUS" id="STAKE_HOLDER_STATUS"/>     
                <form:input type="hidden" path="STAKE_HOLDER_UN_ID" id="STAKE_HOLDER_UN_ID" />                       
            </div>
            
            <!-- /.box-body -->
            <div class="box-footer">
                <a href="<c:url value='/stakeholderlist/' />"><button type="button" class="btn btn-default">Cancel</button></a>
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
	//A $( document ).ready() block.
	$( document ).ready(function() {
	    console.log( "Ready Edit-Stake-Holder page." );
	    //document.getElementById("STAKE_HOLDER_ID").disabled = true;	
	});
			  	  

</script>

</body>
</html>
