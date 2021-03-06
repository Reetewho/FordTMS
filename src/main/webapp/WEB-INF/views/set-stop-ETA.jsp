<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AP Transport Center | Set Stop ETA</title>
  <%@ include file="/WEB-INF/include/cssInclude.jsp" %>
</head>
<style>
/*Don't forget to add Font Awesome CSS : "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"*/
input[type="text"] {
  width: 100%;
  margin: 8px 0;
  outline: none;
  padding: 8px;
  box-sizing: border-box;
  transition: 0.3s;
}

/* .inputWithIcon input[type="text"] {
  padding-left: 40px;
} */

.inputWithIcon {
  position: relative;
}

.inputWithIcon i {
  position: absolute;
  left: 0;
  top: 18px;
  padding: 9px 8px;
  color: #aaa;
  transition: 0.3s;
}


.inputWithIcon.inputIconBg i {
  background-color: #aaa;
  color: #fff;
  padding: 9px 4px;
  border-radius: 4px 0 0 4px;
}

.inputWithIcon.inputIconBg input[type="text"]:focus + i {
  color: #fff;
  background-color: dodgerBlue;
}

.inputWithIcon label {
  position: absolute;
  left: 82%;
  top: 3px;
  padding: 0.5% 3%;
  color: #aaa;
  transition: 0.3s;
}


.inputWithIcon label {display:inline-flex; background:url(//dab1nmslvvntp.cloudfront.net/wp-content/uploads/2017/07/1499401426qr_icon.svg) 50% 50% no-repeat; height:28px;width: 20px;  cursor:pointer}

.inputWithIcon label > input[type=file] {position:absolute; overflow:hidden; width:1px; height:1px; opacity:0}
  
</style>
<body class="hold-transition skin-blue sidebar-mini"  >
<!-- <body class="hold-transition skin-blue sidebar-mini" onload="watchLocation()" > -->
<div class="wrapper">

  <%@ include file="/WEB-INF/include/header.jsp" %>
  <%@ include file="/WEB-INF/include/rightMenu.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Set Stop ETA
        <small></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fas fa-tachometer-alt"></i> &nbsp;&nbsp;Home</a></li>
        <li><a href="<c:url value='/calendar/${loadDate}' />">Calendar</a></li>
        <li><a href="<c:url value='/load-list/${loadDate}'/>">${loadDate}</a></li>
        <li><a href="<c:url value='/loadStop-list/${loadDate}/${loadStop.load.systemLoadID}-${loadStop.load.loadID}'/>">${loadStop.load.systemLoadID}</a></li> 
        <li><a href="#">Set Stop ETA</a></li>         
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
    
       <div class="row">
      	<div class="col-md-12">
      		<c:if test="${Warning!=null || Success!=null }">
              <div class='alert ${Warning!=null?"alert-warning":"alert-success"}  alert-dismissible'>
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4><i class="icon fa  ${Warning!=null?'fa-ban':'fa-check'}"></i>${Warning!=null?'Warning!':'Success'} </h4>
                <c:out value="${Warning!=null?Warning:Success} "></c:out>
              </div>
            </c:if>              
      	</div>
      </div>
      
      <div class="row">
      <!-- /.col -->
      
       <div class="col-md-5">
       	<div class="box box-warning">
            <div class="box-header with-border">
              <h3 class="box-title">Load Info</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->            
              <div class="box-body form-horizontal" style="min-height: 357px;">
                <div class="form-group">
                  <label for="" class="col-sm-5 control-label">Load ID : </label>
                  <div class="col-sm-7" style="padding-top:8px" >
                    ${loadStop.load.systemLoadID}
                  </div>
                </div>
                <div class="form-group">
                  <label for="" class="col-sm-5 control-label">Alert Type Code : </label>
                  <div class="col-sm-7" style="padding-top:8px" >
                    ${loadStop.load.alertTypeCode}
                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-5 control-label">Route No. : </label>
                  <div class="col-sm-7" style="padding-top:8px" >
                    ${loadStop.load.loadDescription}
                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-5 control-label">Load Start Date Time : </label>
                  <div class="col-sm-7" style="padding-top:8px" >
                    ${loadStop.load.loadStartDateTime}
                  </div>
                </div>
                <div class="form-group"  >
                  <label  class="col-sm-5 control-label">Load End Date Time : </label>
                  <div class="col-sm-7" style="padding-top:8px">
                    ${loadStop.load.loadEndDateTime}
                  </div>
                </div>
              </div>
              <!-- /.box-body -->  
          </div>
       </div>
        <div class="col-md-7">
         <form:form method="POST" modelAttribute="setStopETA" data-toggle="validator" role="form">    
          <div class="box box-primary">
          	 <div class="box-header with-border">
              <h3 class="box-title">Set Stop ETA</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body form-horizontal">                    
             	<form:input type="hidden" path="id" id="id"/>
				<form:input type="hidden" path="loadStopID" id="loadStopID"/>
				<form:input type="hidden" path="status" id="status"/>     
		        <div class="form-group">
                  <label  class="col-sm-4 control-label">Shipping Location :</label>
                  <div class="col-sm-8" style="padding-top:8px">
                    ${loadStop.stopShippingLocation}
                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Shipping Location Name : </label>
                  <div class="col-sm-8" style="padding-top:8px">
                    ${loadStop.stopShippingLocationName}
                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Movement Date Time :</label>
                  <div class="col-sm-8" >
                    <form:input path="movementDateTime" id="movementDateTime" class="form-control" required="required"/>                  
                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Estimated Date Time :</label>
                  <div class="col-sm-8">
                   <form:input path="estimatedDateTime" id="estimatedDateTime" class="form-control" required="required" />                    
                  </div> 
                </div>
           <!--  --------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
                <form:form method="POST" modelAttribute="loadStop" data-toggle="validator" role="form">  
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Truck Number :</label>
                  <div class="col-sm-4" >
                   <c:choose>
	                <c:when test="${S_FordUser.role=='3' && loadStop.statusLoad == 'Inactive' }">
	                    <form:input path="truckNumber" id="truckNumbera" class="form-control" required="required" />   
					</c:when>
       		 </c:choose>
       		 <c:choose>
	                <c:when test="${(loadStop.statusLoad == 'Active') || (loadStop.statusLoad == 'null') ||(loadStop.statusLoad == '')|| (S_FordUser.role=='1') || (S_FordUser.role=='2')}">               	
                	<div class="inputWithIcon">
						  <form:input path="truckNumber" id="truckNumberb" type="text" size="16" placeholder="Tracking Code" class="form-control" required="required"/>
						  	<label aria-hidden="true"><input type="file" id="qrscann" accept="image/*" capture="environment" onchange="openQRCamera(this);" tabindex=-1></label>
					</div>                	
                  </c:when>
       		 </c:choose>
                  </div>
                </div>
                </form:form> 
            <!--  --------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
                
                <div class="form-group">
                  <label  class="col-sm-4 control-label">Lat :</label>
                  <div class="col-sm-2">
                    <form:input path="latitude" id="latitude" class="form-control"  required="required" />                   
                  </div>
                                   
                  <label  class="col-sm-2 control-label">Long :</label>                 
                   <div class="col-sm-2">
                    	<form:input path="longitude" id="longitude" class="form-control"  required="required" /> 
                   </div>                   
                   <c:choose>
		            <c:when test="${setStopETA.statusSetStop == 'Active'|| setStopETA.statusSetStop == null}">
		                    <div class="col-sm-1">
		                   	<img src="<c:url value='/assets/dist/img/map.png' />"  alt="User Image"  width= "20px" height="20px" onclick="init()">
		                  </div>  
                    </c:when>
                 </c:choose>  
                 
                 <c:choose>
		            <c:when test="${setStopETA.statusSetStop == 'Inactive'}">
		                    <div class="col-sm-1">
		                   	<img src="<c:url value='/assets/dist/img/map.png' />"  alt="User Image"  width= "20px" height="20px" >
		                  </div>  
                    </c:when>
                 </c:choose> 
                              
               </div> 
           
                <div class="form-group">
                  <label  class="col-sm-4 control-label">City :</label>
		          <div class="col-sm-8">  
		         <select id="lstcity" name="lstcity" class="form-control">
		          <c:if test = "${not empty ListCitys}">
		          <c:forEach items="${ListCitys}" var="ListCity">
		           <option value="${ListCity.cityID}"  ${ListCity.cityID==setStopETA.city?'selected':''} >
		            ${i=i+1} : ${ListCity.cityNameTh}
		           </option>
		          </c:forEach>
		          </c:if>  
		         </select>               
		        </div>
                 </div>
                 <c:choose>
                <c:when test="${S_FordUser.role=='1' || S_FordUser.role=='2'}">
                 <div class="form-group">
                  <label  class="col-sm-4 control-label">Remark :</label>
                  <div class="col-sm-8">
                  <form:textarea path="setStopremark" id="setStopremarks" class="form-control" placeholder="ในกรณีของการเปลี่ยนคนขับรถหรือเปลี่ยนรถที่ใช้ในการขับและอื่นๆ" required="required" />                 
                  </div>
                </div>
                 </c:when>
                 </c:choose>
                 
                        <input  type="hidden" id="movementDateTimes" name="movementDateTimes" value="${movementDateTime}"  >
            </div>
            <!-- /.box-body -->
            <div class="box-footer">
                <a href="<c:url value='/loadStop-list/${loadDate}/${loadStop.load.systemLoadID}-${loadStop.load.loadID}' />"><button type="button" class="btn btn-default">Cancel</button></a>
                <button type="submit" id="submitbt" class="btn btn-primary pull-right">Submit</button>
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

var statusStopETA = '${setStopETA.statusSetStop}';
var statusLoadsroles = '${S_FordUser.role}';


function openQRCamera(node) {
	
	  var reader = new FileReader();      
	  reader.onload = function() {	     
	    node.value = "";
	    qrcode.callback = function(res) {
	      if(res instanceof Error) {
	        alert("No QR code found. Please make sure the QR code is within the camera's frame and try again.");
	      } else {
	        node.parentNode.previousElementSibling.value = res;
	      }
	    };
	    qrcode.decode(reader.result);
	  };
	  reader.readAsDataURL(node.files[0]);
	};

$(function () {
	  if(document.getElementById("movementDateTime").value == "" || document.getElementById("estimatedDateTime").value == "" ){
		  /* document.getElementById("movementDateTime").disabled = true;
		  document.getElementById("estimatedDateTime").disabled = true; */
		  document.getElementById("submitbt").disabled = true;
		 
	  }else{
		  
	  
	   
	   if(${S_FordUser.role=='3' }){
		  document.getElementById("movementDateTime").readOnly = true;
	  }
	   
	   if(statusStopETA == "Inactive" && statusLoadsroles== "3"){
	    	
		   document.getElementById("movementDateTime").disabled = true;		  
			  document.getElementById("estimatedDateTime").disabled = true;		  
			  document.getElementById("latitude").disabled = true;		  
			  document.getElementById("longitude").disabled = true;		  
			  document.getElementById("longitude").disabled = true;		  
			  document.getElementById("submitbt").style.visibility="hidden";
			  document.getElementById("lstcity").disabled = true;
			  document.getElementById("setStopremarks").disabled = true;
			  document.getElementById("truckNumbera").disabled = true;	
			  
			  if(statusLoads == "Inactive" && statusLoadsroles != "3"){
			    	 
				  document.getElementById("truckNumbera").disabled = false;	
				  document.getElementById("movementDateTime").disabled = false;		  
				  document.getElementById("estimatedDateTime").disabled = false;		  
				  document.getElementById("latitude").disabled = false;		  
				  document.getElementById("longitude").disabled = false;		  
				  document.getElementById("longitude").disabled = false;		  
				  document.getElementById("submitbt").disabled = false;
				  document.getElementById("lstcity").disabled = false;
				  document.getElementById("setStopremarks").disabled = true;
			   
		   }  
	   }  
	  }
}); 

function watchLocation(successCallback, errorCallback) {
   successCallback = successCallback || function(){};
   errorCallback = errorCallback || function(){};        

   
// Try HTML5-spec geolocation.
var geolocation = navigator.geolocation;

if (geolocation) {
    // We have a real geolocation service.
    try {
      function handleSuccess(position) {
        successCallback(position.coords);
      }

      geolocation.watchPosition(handleSuccess, errorCallback, {
        enableHighAccuracy: true,
        maximumAge: 5000 // 5 sec.
      });
    } catch (err) {
      errorCallback();
    }
  } else {
    errorCallback();
  }


}

	function init() {
	  watchLocation(function(coords) {
	    document.getElementById('latitude').value =  coords.latitude;               
	    document.getElementById('longitude').value = coords.longitude;
	  }, function() {
		  alert("Please Allow permission GPS");
		     
	  });
	}
	
</script>
</body>
</html>
