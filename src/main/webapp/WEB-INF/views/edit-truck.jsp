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
        <li><a href="<c:url value='/userList'/>">Truck</a></li>
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
        
         <form:form method="POST" id="regForm" modelAttribute="TruckTruckEdits" onsubmit="return myFunction()" data-toggle="validator" role="form" >
            
          <div class="box box-primary">
          	 <div class="box-header with-border">
              <h3 class="box-title">Truck Edit</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body form-horizontal">                    
               
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">ทะเบียน ( Truck Number ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="TRUCK_NUMBER" id="TRUCKNUMBER" name="TRUCKNUMBER" class="form-control" readonly="true" required="required" />                    
	                  </div>
                </div> 
               <%--  <div class="form-group">
                  <label  class="col-sm-4 control-label">Province :</label>
	                  <div class="col-sm-8">
	                   <form:input path="PROVINCE" id="PROVINCE" name="PROVINCE" class="form-control"    required="required" />                    
	                  </div>
                </div> --%>  
                <div class="form-group">
                  <label  class="col-sm-4 control-label">จังหวัด ( Province ) :</label>
		          <div class="col-sm-8">  
		         <select id="lstcity" name="lstcity" class="form-control selectpicker" data-live-search="true">
		          <c:if test = "${not empty ListCitys}">
		          <c:forEach items="${ListCitys}" var="ListCity">
		           <option value="${ListCity.cityNameTh}"  ${ListCity.cityNameTh==TruckTruckEdits.PROVINCE?'selected':''} >
		            ${i=i+1} : ${ListCity.cityNameTh}
		           </option>
		          </c:forEach>
		          </c:if>  
		         </select>               
		        </div>
                 </div>
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">จีพีเอส ( GPS Truck ) :</label>
		          <div class="col-sm-8">  
		         <select id="GPSTRUCK" name="GPSTRUCK" class="form-control selectpicker"  data-live-search="true">
		          <c:if test = "${not empty TruckGps}">
		          <c:forEach items="${TruckGps}" var="GpsType">
		           <option value="${GpsType.GPSId}"  ${GpsType.GPSId==TruckTruckEdits.GPS_TRUCK?'selected':''} >
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
                  <%-- <div class="form-group">
                  <label  class="col-sm-4 control-label">Yard :</label>
	                  <div class="col-sm-8">
	                   <form:input path="YARDS" id="YARD" name="YARD" class="form-control"    required="required" />                 
	                       <form:select class="form-control" path="YARDS" items="${yardstatus}"/>    
 				</div>
                </div>   --%>
                
                <div class="form-group">
                  <label  class="col-sm-4 control-label">ลานจอด ( Yard ) :</label>
	                  <div class="col-sm-8">	                   
	                    <select id="Yard" name="Yard" class="form-control selectpicker" data-live-search="true">
				          <c:if test = "${not empty FindAllYards}">
				          <c:forEach items="${FindAllYards}" var="FindAllYard">
				           <option value="${FindAllYard.yard_id}"  ${FindAllYard.yard_id==TruckTruckEdits.YARDS?'selected':''} >
				            ${FindAllYard.yard_code}
				           </option>
				          </c:forEach>
				          </c:if>  
				       </select>   
	                   
	                  </div>
                </div>
                
                <div class="form-group">
                  <label  class="col-sm-4 control-label">สังกัด ( Subsidairy ) : </label>
	                  <div class="col-sm-8">
	                  	<form:select class="form-control"  path="SUBSIDAIRY" >
	                  		<c:if test = "${not empty subsidairyData}">
	                   		<form:options items="${subsidairyData}" />
	                   		</c:if>
	                   	</form:select>
	                  </div>
                </div> 
                
                <%-- <div class="form-group">
                  <label  class="col-sm-4 control-label">Company :</label>
		          <div class="col-sm-8">  
		         <select  id="Company" name="Company" class="form-control">
		          <c:if test = "${not empty ListStakeHolder}">
		          <c:forEach items="${ListStakeHolder}" var="ListStakeHolder">
		           <option value="${ListStakeHolder.STAKE_HOLDER_UN_ID}" >
		            		 ${ListStakeHolder.STAKE_HOLDER_NAME}
		           </option>
		          </c:forEach>
		          </c:if>  
		         </select>               
		        </div>
                 </div> --%>
                 
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">เจ้าของ ( Company ) :</label>
		          <div class="col-sm-8">  
		         <select id="Company" name="Company" class="form-control selectpicker" data-live-search="true">
		          <c:if test = "${not empty ListStakeHolder}">
		          <c:forEach items="${ListStakeHolder}" var="ListStakeHolder_Company">
		           <option value="${ListStakeHolder_Company.STAKE_HOLDER_UN_ID}"  ${ListStakeHolder_Company.STAKE_HOLDER_UN_ID==TruckTruckEdits.COMPANY?'selected':''} >
		            			${ListStakeHolder_Company.STAKE_HOLDER_ID}-${ListStakeHolder_Company.STAKE_HOLDER_NAME}
		           </option>
		          </c:forEach>
		          </c:if>  
		         </select>               
		        </div>
                 </div>

                  <div class="form-group">
                  <label  class="col-sm-4 control-label">ทะเบียนหัว / ทะเบียนหาง ( Head Or Tail ) :</label>
	                  <div class="col-sm-8">
	                  	<select id="HeadOrTail" name="HeadOrTail" class="form-control">       
					  	<option value="ทะเบียนหัว"  ${TruckTruckEdits.PLATE_TYPE=='ทะเบียนหัว'?'selected':''} >ทะเบียนหัว</option>       
						<option value="ทะเบียนหาง"  ${TruckTruckEdits.PLATE_TYPE=='ทะเบียนหาง'?'selected':''} >ทะเบียนหาง</option>          
						</select>
	                  </div>
                </div> 

                 <div class="form-group">
                  <label  class="col-sm-4 control-label">ประเภทรถ ( Truck Type ) :</label>
		          <div class="col-sm-8">  
		         <select id="TruckType" name="TruckType" class="form-control selectpicker" data-live-search="true">
		          <c:if test = "${not empty ListType}">
		          <c:forEach items="${ListType}" var="ListType">
		           <option value="${ListType.TRUCKTYPE_id}"  ${ListType.TRUCKTYPE_id==TruckTruckEdits.TRUCK_TYPE?'selected':''} >
		            			 ${ListType.TRUCKTYPE_TYPE}
		           </option>
		          </c:forEach>
		          </c:if>  
		         </select>               
		        </div>
                 </div>
                 
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">ยี่ห้อ ( Brand ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="BRAND" id="BRAND" name="BRAND" class="form-control"    required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">ประเภทน้ำมัน ( Fuel Type ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="FUEL_TYPE" id="FUELTYPE" name="FUELTYPE" class="form-control"    required="required" />                    
	                  </div>
                </div>
                 
                 
                  <div class="form-group">
                	<H3 class="col-sm-9 control-label"></H3>
                </div>
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">ขนาดตู้วัดขอบชนขอบ ( Container Size ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="CONTAINER_SIZE" id="CONTAINERSIZE" name="CONTAINERSIZE" class="form-control"    required="required" />                    
	                  </div>
                </div> 
                <div class="form-group">
                  <label  class="col-sm-4 control-label">ขนาดตู้บรรทุกภายในตู้ ( Container Border Size ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="CONTAINER_BORDER_SIZE" id="CONTAINERBORDERSIZE" name="CONTAINERBORDERSIZE" class="form-control"    required="required" />                    
	                  </div>
                </div> 
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">อัตราเฉลี่ยเชื้อเพลิง ( Average Fuel ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="AVAERAGE_FUEL" Type="number" onkeypress='return isNumberKey(event)' id="AVAERAGEFUEL" name="AVAERAGEFUEL" class="form-control"    required="required" />                    
	                  </div>
                </div> 
               	<div class="form-group">
                	<label  class="col-sm-4 control-label">โปรเจค ( Project ) :</label>
	              	<div class="col-sm-8">
	                  <%--  <form:input path="PROJECT" id="PROJECT" name="PROJECT" class="form-control"    required="required" /> --%>    
	                	<select id="lstProject" name="lstProject" class="form-control selectpicker" data-live-search="true">
				        	<c:if test = "${not empty FindProject}">
				          	<c:forEach items="${FindProject}" var="FindProject">
				           	<option value="${FindProject.projectnameid}"  ${FindProject.projectnameid==TruckTruckEdits.PROJECT?'selected':''} >
				            ${FindProject.projectnamename}
				           	</option>
				          	</c:forEach>
				        	</c:if>  
				    	</select>                  
	            	</div>
                </div>
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">เลขไมล์ล่าสุด ( Last Mileage ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="LATEST_MILAGE" Type="number" onkeypress='return isNumberKey(event)' id="LATESTMILAGE" name="LATESTMILAGE" class="form-control"    required="required" />                    
	                  </div>
                </div>
     
                <div class="form-group">
                  <label  class="col-sm-4 control-label">สถานะรถ ( Truck Status ) : </label>
	                  <div class="col-sm-8">
	                  	<form:select class="form-control"  path="TRUCK_STATUS">
	                  		<c:if test = "${not empty truckstatus}">
	                   		<form:options items="${truckstatus}" itemValue="truckStatusNo" itemLabel="truckStatusName" />
	                   		</c:if>
	                   	</form:select>
	                   
	                   <%-- <select id="lstTruckStatus" name="lstTruckStatus" class="form-control">
				        	<c:if test = "${not empty truckstatus}">
				          	<c:forEach items="${truckstatus}" var="truckstatus">
				           	<option value="${truckstatus.truckStatusNo}"  ${truckstatus.truckStatusNo==TruckTruckEdits.TRUCK_STATUS?'selected':''} >
				            ${truckstatus.truckStatusName}
				           	</option>
				          	</c:forEach>
				        	</c:if>  
				    	</select> --%>
	                  </div>
                </div> 
                <div class="form-group">
                  <label  class="col-sm-4 control-label">วันที่จดทะเบียนครั้งแรก ( First Register Date ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="FIRST_REGIST_DATE" type="date" id="FIRSTREGISTDATE" name="FIRSTREGISTDATE" class="form-control"    required="required" />                    
	                  </div>
                </div>
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">วันที่หมดอายุทะเบียน ( Expire Date ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="EXPIRE_DATE" type="date" id="EXPIREDATE" name="EXPIREDATE" class="form-control"    required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">รุ่น ( Model ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="MODEL" id="MODEL" name="MODEL" class="form-control"    required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">เลขที่ตัวถัง ( Chassis No ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="CHASSIS_NO" id="CHASSISNO" name="CHASSISNO" class="form-control"    required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">เลขที่เครื่องยนต์  ( Engine No ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="ENGINE_NO" id="ENGINENO" name="ENGINENO" class="form-control"    required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">อยู่ที่1 ( Posotion1 ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="POSITION1" id="POSITION1" name="POSITION1" class="form-control"    required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">อยู่ที่2( Posotion2 ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="POSITION2" id="POSITION2" name="POSITION2" class="form-control"    required="required" />                    
	                  </div>
                </div>
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">อายุรถปี ( Truck Age Year ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="TRUCK_AGE_YEAR" Type="number" onkeypress='return isNumberKey(event)' id="TRUCKAGEYEAR" name="TRUCKAGEYEAR" class="form-control"    required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">อายุรถ เดือน ( Truck Age Month ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="TRUCK_AGE_MONTH" Type="number" onkeypress='return isNumberKey(event)' id="TRUCKAGEMONTH" name="TRUCKAGEMONTH" class="form-control"    required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">จำนวนเครื่องยนต์ ( CC ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="PUMP_NO" Type="number" onkeypress='return isNumberKey(event)' id="PUMPNO" name="PUMPNO" class="form-control"    required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">แรงม้า ( HPS ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="HPS" Type="number" onkeypress='return isNumberKey(event)' id="HPS" name="HPS" class="form-control"    required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">น้ำหนัก ( Weight ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="WEIGHT" id="WEIGHT" Type="number" onkeypress='return isNumberKey(event)' name="WEIGHT" class="form-control"    required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">น้ำนักบรรทุก ( Carry Weight ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="WEIGHT_CARRY" Type="number" onkeypress='return isNumberKey(event)' id="WEIGHTCARRY" name="WEIGHTCARRY" class="form-control"    required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">น้ำหนักรวม ( Total Weight ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="WEIGHT_TOTAL" Type="number" onkeypress='return isNumberKey(event)' id="WEIGHTTOTAL" name="WEIGHTTOTAL" class="form-control"    required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">พรบ.เลขที่ ( ACT No ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="ACT_CAR_NUM" id="ACTCARNUM" name="ACTCARNUM" class="form-control"    required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">วันที่หมดอายุพรบ. ( ACT Expire ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="ACT_CAR_EXPIRE" type="date" id="ACTCAREXPIRE" name="ACTCAREXPIRE" class="form-control" required="required" />                    
	                  </div>
                </div>
                
				<div class="form-group">
					<label  class="col-sm-4 control-label">พรบ. บริษัท ( ACT Car Company ) :</label>
					<div class="col-sm-8">  
						<select id="lstActCarCom" name="lstActCarCom" class="form-control selectpicker" data-live-search="true">
						<c:if test = "${not empty ListStakeHolder}">
			          	<c:forEach items="${ListStakeHolder}" var="ListSH_ActCarCom">
			           	<option value="${ListSH_ActCarCom.STAKE_HOLDER_UN_ID}"  ${ListSH_ActCarCom.STAKE_HOLDER_UN_ID==TruckTruckEdits.ACT_CAR_COMPANY?'selected':''} >
			            			 ${ListSH_ActCarCom.STAKE_HOLDER_ID}-${ListSH_ActCarCom.STAKE_HOLDER_NAME}
			           	</option>
			          	</c:forEach>
			          </c:if>  
						</select>               
			        </div>
				</div>
                
                 <div class="form-group">
                	<H3 class="col-sm-9 control-label">InsuranceMotor</H3>
                </div>                
<%--                  <div class="form-group">
                  <label  class="col-sm-4 control-label">InsuranceMotor Company :</label>
	                  <div class="col-sm-8">
	                   <form:input path="ACT_INSURANCE_MOTOR" id="ACTINSURANCEMOTOR" name="ACTINSURANCEMOTOR" class="form-control"    required="required" />                    
	                  </div>
                </div> --%>
                
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">ประกันภัยรถบริษัท ( InsuranceMotor Company ) :</label>
		          <div class="col-sm-8">  
		         <select id="lstStakeHolder" name="lstStakeHolder" class="form-control selectpicker" data-live-search="true">
		          <c:if test = "${not empty ListStakeHolder}">
		          <c:forEach items="${ListStakeHolder}" var="ListStakeHolder">
		           <option value="${ListStakeHolder.STAKE_HOLDER_NAME}"  ${ListStakeHolder.STAKE_HOLDER_NAME==TruckTruckEdits.ACT_INSURANCE_MOTOR?'selected':''} >
		            			 ${ListStakeHolder.STAKE_HOLDER_ID}-${ListStakeHolder.STAKE_HOLDER_NAME}
		           </option>
		          </c:forEach>
		          </c:if>  
		         </select>               
		        </div>
                 </div>
                
					<div class="form-group">
                  <label  class="col-sm-4 control-label">ผู้ดูแล ( InsuranceMotor Agent ) :</label>
		          <div class="col-sm-8">  
		         <select id="ListStakeHolderInsuranceMotorAgent" name="ListStakeHolderInsuranceMotorAgent" class="form-control selectpicker" data-live-search="true">
		          <c:if test = "${not empty ListStakeHolder}">
		          <c:forEach items="${ListStakeHolder}" var="ListSH_MotorAgent">
		           <option value="${ListSH_MotorAgent.STAKE_HOLDER_UN_ID}"  ${ListSH_MotorAgent.STAKE_HOLDER_UN_ID==TruckTruckEdits.ACT_RESPONSE_BY?'selected':''} >
		            			 ${ListSH_MotorAgent.STAKE_HOLDER_ID}-${ListSH_MotorAgent.STAKE_HOLDER_NAME}
		           </option>
		          </c:forEach>
		          </c:if>  
		         </select>               
		        </div>
                 </div>

                <div class="form-group">
                  <label  class="col-sm-4 control-label">เลขที่กรมธรรม์ ( InsuranceMotor No ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="ACT_NO" id="InsuranceMotorActNo" name="InsuranceMotorActNo" class="form-control"    required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">วงเงินประกัน ( InsuranceMotor Price ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="ACT_INSURANCE_LIMIT" Type="number" onkeypress='return isNumberKey(event)' id="InsuranceMotorActLimit" name="InsuranceMotorActLimit" class="form-control"    required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">วันที่สิ้นสุดคุ้มครอง ( InsuranceMotor Expire Date ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="ACT_EXPIRE_DATE" type="date" id="InsuranceMotorActExpiredate" name="InsuranceMotorActExpiredate" class="form-control"    required="required" />                    
	                  </div>
                </div>
                
                <div class="form-group">
                	<H3 class="col-sm-9 control-label">InsuranceCoverProduct</H3>
                </div>
                
                
                <div class="form-group">
                  <label  class="col-sm-4 control-label">ประกันภัยสินค้าบริษัท ( InsuranceMotor Company ) :</label>
		          <div class="col-sm-8">  
		         <select id="ListStakeHolderInsuranceCargoCompany" name="ListStakeHolderInsuranceCargoCompany" class="form-control selectpicker" data-live-search="true">
		          <c:if test = "${not empty ListStakeHolder}">
		          <c:forEach items="${ListStakeHolder}" var="ListSH_InsCarco">
		           <option value="${ListSH_InsCarco.STAKE_HOLDER_NAME}"  ${ListSH_InsCarco.STAKE_HOLDER_NAME==TruckTruckEdits.INSURANCE_COVER_PRODUCT?'selected':''} >
		            			 ${ListSH_InsCarco.STAKE_HOLDER_ID}-${ListSH_InsCarco.STAKE_HOLDER_NAME}
		           </option>
		          </c:forEach>
		          </c:if>  
		         </select>               
		        </div>
                 </div>
                
					<div class="form-group">
                  <label  class="col-sm-4 control-label">ผู้ดูแล ( InsuranceCargo Agent ) :</label>
		          <div class="col-sm-8">  
		         <select id="ListStakeHolderInsuranceCargoAgent" name="ListStakeHolderInsuranceCargoAgent" class="form-control selectpicker" data-live-search="true">
		          <c:if test = "${not empty ListStakeHolder}">
		          <c:forEach items="${ListStakeHolder}" var="ListSH_InsCarcoAgent">
		           <option value="${ListSH_InsCarcoAgent.STAKE_HOLDER_UN_ID}"  ${ListSH_InsCarcoAgent.STAKE_HOLDER_UN_ID==TruckTruckEdits.INSURANCE_RESPONSE_BY?'selected':''} >
		            			 ${ListSH_InsCarcoAgent.STAKE_HOLDER_ID}-${ListSH_InsCarcoAgent.STAKE_HOLDER_NAME}
		           </option>
		          </c:forEach>
		          </c:if>  
		         </select>               
		        </div>
                 </div>
                             
                <div class="form-group">
                  <label  class="col-sm-4 control-label">เลขที่กรมธรรม์ ( InsuranceCargo No ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="POLICY_NO" id="InsuranceCoverNo" name="InsuranceCoverNo" class="form-control"    required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">วงเงินประกัน ( InsuranceCargo Price ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="INSURANCE_LIMIT" Type="number" onkeypress='return isNumberKey(event)' id="InsuranceCoverLimit" name="InsuranceCoverLimit" class="form-control"    required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">วันที่สิ้นสุดคุ้มครอง ( InsuranceCargo Expire Date ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="POLICY_EXPIRY_DATE" type="date" id="InsuranceCoverdate" name="InsuranceCoverdate" class="form-control"    required="required" />                    
	                  </div>
                </div>
                
                <div class="form-group">
                	<H3 class="col-sm-9 control-label">Fleet Card</H3>
                </div>
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">บริษัทบัตรน้ำมัน( Fleet Card Brand ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="FLEETCARD_BRAND" id="FLEETCARDBRAND" name="FLEETCARDBRAND" class="form-control"    required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">เลขที่บัตรน้ำมัน( Fleet Card No ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="FLEETCARD_NO" id="FLEETCARDNO" name="FLEETCARDNO" class="form-control"    required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">วงเงินบัตรน้ำมัน( Fleet Card Credit Limit ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="FLEETCARD_CREDIT_LIMIT" Type="number" onkeypress='return isNumberKey(event)' id="FLEETCARDCREDITLIMIT" name="FLEETCARDCREDITLIMIT" class="form-control"    required="required" />                    
	                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">วันที่ทะเบียนบัตร ( Fleet Card Receive Date ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="FLEETCARD_RECIEVE_DATE" type="date" id="FLEETCARDRECIEVEDATE" name="FLEETCARDRECIEVEDATE" class="form-control"    required="required" />                    
	                  </div>
                </div>
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">วันหมดอายุบัตร ( Fleet Card Expire Date ) :</label>
	                  <div class="col-sm-8">
	                   <form:input path="FLEET_EXPIRY_DATE" type="date" id="FLEETEXPIRYDATE" name="FLEETEXPIRYDATE" class="form-control"    required="required" />                    
	                  </div>
                </div>
          
         		 <form:input type="hidden" path="SUPPLIER_SUM" id="SUPPLIERSUM"/> 
                 <form:input type="hidden" path="TRUCK_AGE" id="TRUCKAGE"/>  
                 <form:input type="hidden" path="FLEETCARD_ID" id="FLEETCARDID"/>                  
                 <form:input type="hidden" path="TYPE_ID" id="TYPEID"/> 
                <form:input type="hidden" path="STAKE_HOLDER_UN_ID" id="STAKEHOLDERUNID"/>
                <form:input type="hidden" path="POLICY_ID" id="POLICYID"/>   
                 <form:input type="hidden" path="ACT_ID" id="ACTID"/>                
                                
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
	/* document.getElementById("TRUCKNUMBER").disabled = true;	 */
			  		  
	function isNumberKey(evt){
		var charCode = (evt.which) ? evt.which : evt.keyCode;
			if (charCode > 31 && (charCode < 48 || charCode > 57))
				 return false;
				    return true;
	}
			  
</script>

</body>
</html>
