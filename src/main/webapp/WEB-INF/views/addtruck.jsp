<%@page import="org.w3c.dom.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AP Transport Center | Add Truck </title>
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
	                   <input  id="TruckNumber" name="TruckNumber" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div>                                                                          
      		  
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">Province :</label>
		          <div class="col-sm-7">  
		         <select id="Province" name="Province" class="form-control">
		          <c:if test = "${not empty ListCitys}">
		          <c:forEach items="${ListCitys}" var="ListCity">
		           <option value="${ListCity.cityNameTh}"  ${ListCity.cityID==setStopETA.city?'selected':''} >
		            ${i=i+1} : ${ListCity.cityNameTh}
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
                  <div class="form-group">
                	<H3 class="col-sm-9 control-label"></H3>
               </div>  
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">Yard :</label>
		          <div class="col-sm-7">  
		         <select  id="Yard" name="Yard" class="form-control">
		          <c:if test = "${not empty FindAllYards}">
		           <c:forEach items="${FindAllYards}" var="FindAllYards">
		           <option value="${FindAllYards.yard_id}" >
		            		 ${FindAllYards.yard_code}
		           </option>
		          </c:forEach>
		          </c:if>  
		         </select>               
		        </div>
                 </div>
              
                <!-- <div class="form-group">
                  <label  class="col-sm-4 control-label">Yard :</label>
	                  <div class="col-sm-7">
	                   <select name="Yard" class="form-control">       
							<option value="1">ABC</option>       
							<option value="2">APC</option>
							<option value="3">ARC<option>       
							<option value="4">APT<option> 
							<option value="5">SUB<option>       
							<option value="6">OTHER<option>     
						</select>
	                  </div>
                </div>  -->

                <div class="form-group">
                  <label  class="col-sm-4 control-label">Company :</label>
		          <div class="col-sm-7">  
		         <select  id="Company" name="Company" class="form-control">
		          <c:if test = "${not empty ListStakeHolder}">
		          <c:forEach items="${ListStakeHolder}" var="ListStakeHolder">
		           <option value="${ListStakeHolder.STAKE_HOLDER_ID}" >
		            		 ${ListStakeHolder.STAKE_HOLDER_NAME}
		           </option>
		          </c:forEach>
		          </c:if>  
		         </select>               
		        </div>
                 </div>
                 
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">Head Or Tail :</label>
	                  <div class="col-sm-7">
	                   <select name="HeadOrTail" class="form-control">       
							<option value="ทะเบียนหัว">ทะเบียนหัว</option>       
							<option value="ทะเบียนหาง">ทะเบียนหาง</option>       
						</select>
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
                  <label  class="col-sm-4 control-label">Brand :</label>
	                  <div class="col-sm-7">
	                   <input  id="Brand" name="Brand"type="text" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div> 
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Fuel Type :</label>
	                  <div class="col-sm-7">
	                   <input  id="FuelType" name="FuelType"type="text" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div> 
                 <div class="form-group">
                	<H3 class="col-sm-9 control-label"></H3>
                </div>  
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Container Border Size :</label>
	                  <div class="col-sm-7">
	                   <input  id="ContainerBorderSize"type="text" name="ContainerBorderSize" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div> 
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Container Size :</label>
	                  <div class="col-sm-7">
	                   <input  id="ContainerSize"type="text" name="ContainerSize" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div> 
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Average Fuel  :</label>
	                  <div class="col-sm-7">
	                   <input  id="AverageFuel" Type="number" name="AverageFuel" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div> 
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Project :</label>
	                  <div class="col-sm-7">
	                   <input  id="Project" name="Project" type="text" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div> 
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Last Mileage :</label>
	                  <div class="col-sm-7">
	                   <input  id="LastMileage" type="number" name="LastMileage" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div> 
                <!-- <div class="form-group">
                  <label  class="col-sm-4 control-label">Truck Status :</label>
	                  <div class="col-sm-7">
	                   <input  id="TruckStatus" name="TruckStatus" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div>  -->
                
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Truck Status :</label>
	                  <div class="col-sm-7">
	                   <select name="TruckStatus" class="form-control">       
							<option value="1">อนุญาติให้ใช้งานปกติ</option>       
							<option value="2">ไม่อนุญาติให้ใช้งาน</option>       
						</select>
	                  </div>
                </div>
                
                <div class="form-group">
                  <label  class="col-sm-4 control-label">First Register Date :</label>
	                  <div class="col-sm-7">
	                   <input  id="FirstRegisterDate" type="date" name="FirstRegisterDate" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Expire Date :</label>
	                  <div class="col-sm-7">
	                   <input  id="ExpireDate" type="date" name="ExpireDate" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div> 
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Truck Age Year :</label>
	                  <div class="col-sm-7">
	                   <input  id="TruckAgeYear"  name="TruckAgeYear" type="number" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div> 
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Truck Age Month :</label>
	                  <div class="col-sm-7">
	                   <input  id="TruckAgeMonth" name="TruckAgeMonth" type="number" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div> 
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">Model :</label>
	                  <div class="col-sm-7">
	                   <input  id="Model" name="Model" type="text" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div> 
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Chassis No :</label>
	                  <div class="col-sm-7">
	                   <input  id="ChassisNo" name="ChassisNo" type="text" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div> 
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">Engine No :</label>
	                  <div class="col-sm-7">
	                   <input  id="EngineNo" name="EngineNo" type="text" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div> 
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">Position1 :</label>
	                  <div class="col-sm-7">
	                   <input  id="Position1" type="text" name="Position1" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div> 
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">Position2 :</label>
	                  <div class="col-sm-7">
	                   <input  id="Position2" type="text" name="Position2" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div> 
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">piston :</label>
	                  <div class="col-sm-7">
	                   <input  id="piston" name="piston" type="number" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div> 
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">HPS :</label>
	                  <div class="col-sm-7">
	                   <input  id="HPS" name="HPS" type="number" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div> 
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">Weight :</label>
	                  <div class="col-sm-7">
	                   <input  id="Weight" name="Weight" type="number" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div> 
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">Carry Weight :</label>
	                  <div class="col-sm-7">
	                   <input  id="CarryWeight" name="CarryWeight" type="number" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div> 
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Total Weight :</label>
	                  <div class="col-sm-7">
	                   <input  id="TotalWeight" name="TotalWeight" type="number" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div> 
                <div class="form-group">
                  <label  class="col-sm-4 control-label">ACT No :</label>
	                  <div class="col-sm-7">
	                   <input  id="ACTCARNUM" name="ACTCARNUM" type="text" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div> 
                <div class="form-group">
                  <label  class="col-sm-4 control-label">ACT Expire :</label>
	                  <div class="col-sm-7">
	                   <input  id="ACTCAREXPIRE" Type="date" name="ACTCAREXPIRE" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div> 
                 <div class="form-group">
                	<H3 class="col-sm-9 control-label">InsuranceMotor</H3>
                </div>
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">InsuranceMotor Company :</label>
		          <div class="col-sm-7">  
		         <select  id="InsuranceMotorCompany" name="InsuranceMotorCompany" class="form-control">
		          <c:if test = "${not empty ListStakeHolder}">
		          <c:forEach items="${ListStakeHolder}" var="ListStakeHolder">
		           <option value="${ListStakeHolder.STAKE_HOLDER_NAME}" >
		            		 ${ListStakeHolder.STAKE_HOLDER_NAME}
		           </option>
		          </c:forEach>
		          </c:if>  
		         </select>               
		        </div>
                 </div>
                  <div class="form-group">
                  <label  class="col-sm-4 control-label">InsuranceMotor Agent :</label>
		          <div class="col-sm-7">  
		         <select  id="InsuranceMotorAgent" name="InsuranceMotorAgent" class="form-control">
		          <c:if test = "${not empty ListStakeHolder}">
		          <c:forEach items="${ListStakeHolder}" var="ListStakeHolder">
		           <option value="${ListStakeHolder.STAKE_HOLDER_UN_ID}" >
		            		 ${ListStakeHolder.STAKE_HOLDER_NAME}
		           </option>
		          </c:forEach>
		          </c:if>  
		         </select>               
		        </div>
                 </div>
  				<div class="form-group">
                  <label  class="col-sm-4 control-label">InsuranceMotor No :</label>
	                  <div class="col-sm-7">
	                   <input  id="InsuranceMotorNo" type="text" name="InsuranceMotorNo" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div> 
                <div class="form-group">
                  <label  class="col-sm-4 control-label">InsuranceMotor Price :</label>
	                  <div class="col-sm-7">
	                   <input  id="InsuranceMotorPrice" type="number" name="InsuranceMotorPrice" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">InsuranceMotor Expire Date :</label>
	                  <div class="col-sm-7">
	                   <input  id="InsuranceMotorExpireDate" type="date" name="InsuranceMotorExpireDate" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                	<H3 class="col-sm-9 control-label">InsuranceCoverProduct</H3>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">InsuranceCargo Company :</label>
		          <div class="col-sm-7">  
		         <select  id="InsuranceCargoCompany" name="InsuranceCargoCompany" class="form-control">
		          <c:if test = "${not empty ListStakeHolder}">
		          <c:forEach items="${ListStakeHolder}" var="ListStakeHolder">
		           <option value="${ListStakeHolder.STAKE_HOLDER_NAME}" >
		            		 ${ListStakeHolder.STAKE_HOLDER_NAME}
		           </option>
		          </c:forEach>
		          </c:if>  
		         </select>               
		        </div>
                 </div>
                  <div class="form-group">
                  <label  class="col-sm-4 control-label">InsuranceCargo Agent :</label>
		          <div class="col-sm-7">  
		         <select  id="InsuranceCargoAgent" name="InsuranceCargoAgent" class="form-control">
		          <c:if test = "${not empty ListStakeHolder}">
		          <c:forEach items="${ListStakeHolder}" var="ListStakeHolder">
		           <option value="${ListStakeHolder.STAKE_HOLDER_UN_ID}" >
		            		 ${ListStakeHolder.STAKE_HOLDER_NAME}
		           </option>
		          </c:forEach>
		          </c:if>  
		         </select>               
		        </div>
                 </div>
  				<div class="form-group">
                  <label  class="col-sm-4 control-label">InsuranceCargo No :</label>
	                  <div class="col-sm-7">
	                   <input  id="InsuranceCargoNo" name="InsuranceCargoNo" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div> 
                <div class="form-group">
                  <label  class="col-sm-4 control-label">InsuranceCargo Price :</label>
	                  <div class="col-sm-7">
	                   <input  id="InsuranceCargoPrice" type="number" name="InsuranceCargoPrice" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">InsuranceCargo Expire Date :</label>
	                  <div class="col-sm-7">
	                   <input  id="InsuranceCargoExpireDate" type="date" name="InsuranceCargoExpireDate" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                	<H3 class="col-sm-8 control-label">Fleet Card</H3>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Fleet Card Brand :</label>
	                  <div class="col-sm-7">
	                   <input  id="FleetCardBrand" type="text" name="FleetCardBrand" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div> 
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Fleet Card No :</label>
	                  <div class="col-sm-7">
	                   <input  id="FleetCardNo" type="text" name="FleetCardNo" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Fleet Card Credit Limit :</label>
	                  <div class="col-sm-7">
	                   <input  id="FleetCardCreditLimit" type="number" name="FleetCardCreditLimit" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Fleet Card Receive Date :</label>
	                  <div class="col-sm-7">
	                   <input  id="FleetCardReceiveDate" type="date" name="FleetCardReceiveDate" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Fleet Card Expire Date :</label>
	                  <div class="col-sm-7">
	                   <input  id="FleetCardExpireDate" type="date" name="FleetCardExpireDate" class="form-control"   data-error="Minimum of 8 characters" maxlength="20" data-error="Maximum of 20 characters" required="required" />                    
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
