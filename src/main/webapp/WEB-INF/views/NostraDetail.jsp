<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AP Transport Center | Operation Report </title>
  <%@ include file="/WEB-INF/include/cssInclude.jsp" %>
</head>
<style>
#events {
        margin-bottom: 1em;
        padding: 1em;
        background-color: #f6f6f6;
        border: 1px solid #999;
        border-radius: 3px;
        height: 100px;
        overflow: auto;
    }
    </style>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <%@ include file="/WEB-INF/include/header.jsp" %>
  <%@ include file="/WEB-INF/include/rightMenu.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
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
      <h1>
       Operation Report
        <small></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fas fa-tachometer-alt"></i> &nbsp;&nbsp;Home</a></li>
        <li><a href="#' />">Operation Report</a></li>                 
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">  
     	<div class="row" style="margin-bottom:20px"> 
	     	<form method="POST" action="<c:url value='/Nostra' />" data-toggle="validator" role="form" >        
	        <%-- <div class="col-md-2" ></div>
	        <div class="col-md-3">
     			<div class="form-group  input-group date">
	                <div class="input-group-addon">
	                	<i class="fa fa-calendar"></i> Start Date : 
	                </div>
                	<input type="text" class="form-control pull-right datepicker" name="startDate" id="startDate" value="${startDate}" data-error="Please Select Start Date" required="required"/>  
				</div>
	        </div>  --%>       
	        <%-- <div class="col-md-1" ></div>
	        <div class="col-md-3">
   				<div class="form-group  input-group date">
                	<div class="input-group-addon">
                		<i class="fa fa-calendar"></i> End Date : 
                	</div>
                	<input type="text" class="form-control pull-right datepicker" name="endDate" id="endDate" value="${endDate}" data-error="Please Select End Date" required="required"/>
				</div>
	        </div> --%>
	        <!-- <div class="col-md-1" style="padding-top:3px"><input type="submit" value="search"/></div>	        
	       <div class="col-md-2"></div>
	       </form>
	     </div> -->
	     <%-- 
	      <div class="row" style="margin-bottom:20px"> 
	     	<form method="POST" id="frm-payment" action="<c:url value='/updateNostraAPI' />" >      
	        <div class="col-md-2" ></div>
	        <div class="col-md-8">
	        	<input type = "hidden" name="startDate" id="startDate" value="${startDate}" />
	        	<input type = "hidden" name="endDate" id="endDate" value="${endDate}" />
	        
	        	<input type = "hidden" name = "console-select-rows" id = "console-select-rows" value = "" />
				<button type = "submit" name="subbt" id="subbt" class = "btn btn-primary pull-right" >Call Nostra</button> 
	        </div>        
	       <div class="col-md-2"></div>
	       </form>
	     </div> --%>
        
        <!-- <div class="row">
	        <div class="col-md-3">
	          <div class="box box-default box-solid">
	            <div class="box-header ">
	              <h3 class="box-title">Assigned</h3>
	              <div class="box-tools pull-right">
	                <span  id="totalAssigned"  style="font-size:16px;font-weight: 600;">-</span>
	              </div>
	              /.box-tools
	            </div>
	            /.box-header           
	            /.box-body
	          </div>
	          /.box
	        </div>
	        /.col
	        <div class="col-md-3">
	          <div class="box box-warning box-solid">
	            <div class="box-header ">
	              <h3 class="box-title">Checked- In</h3>
	              <div class="box-tools pull-right">
	                <span  id="totalCheckedIn" class="badge bg-yellow" style="font-size:16px;font-weight: 600;">-</span>
	              </div>
	              /.box-tools
	            </div>
	            /.box-header           
	            /.box-body
	          </div>
	          /.box
	        </div>
	        /.col
	        
	        <div class="col-md-3">
	          <div class="box box-primary box-solid">
	            <div class="box-header ">
	              <h3 class="box-title">Ontime</h3>
	              <div class="box-tools pull-right">
	                <span  id="totalOntime"  style="font-size:16px;font-weight: 600;">-</span>
	              </div>
	              /.box-tools
	            </div>
	            /.box-header            
	            /.box-body
	          </div>
	          
	          /.box
	        </div>
	        /.col
	        
	        <div class="col-md-3">
	          <div class="box box-info box-solid">
	            <div class="box-header ">
	              <h3 class="box-title">Delayed</h3>
	              <div class="box-tools pull-right">
	                <span  id="totalDelayed" class="badge bg-aqua" style="font-size:16px;font-weight: 600;">-</span>
	              </div>
	              /.box-tools
	            </div>
	            /.box-header            
	            /.box-body
	          </div>
	          /.box
	        </div>       
	     	/.col
	     </div>
	     /.row
         -->
        
      <div class="row">
      <!-- /.col -->
        <div class="col-md-12">
          <div class="box box-primary">            	      	
            <div class="box-body ">
              <table id="reportTable" class="table table-bordered table-striped" style="width : 100% ">
                <thead>
                <tr>
                  	<th >Load ID</th>
                  	<th >Route No.</th>
                  	<th >Pickup GSDB</th>
                  	<th >Pickup Supplier Name</th>
                  	<th >Truck Number</th>	
                  	<th >Jobs Number</th>	
                  	<th >Yard</th>	
                  	<th >Arrive Plan</th> 
                  	<th >Arrive Actual</th>	
					<th >Departure Plan</th>
					<th >Departure Actual</th>
					<th >ETA</th>
					<th >Color</th>
                  	<th >Driver Name</th>
                  	<!-- <th >Last Update</th> -->                 	
					<th >Latitude</th>
					<th >Longitude</th>
				
					<th style="display:none;"></th>
					<th style="display:none;"></th>
					        	       
                </tr>
                </thead>
                <tbody>                
		                <c:set var="assigned" value="${0}"/>
		                <c:set var="CheckedIn" value="${0}"/>
		                <c:set var="Ontime" value="${0}"/>	
		                <c:set var="Delayed" value="${0}"/>	                
	               <c:if test = "${not empty lsLoadID}">
						<c:forEach items="${lsLoadID}" var="report">
							<tr >
							<td>${report.systemLoadID}</td>
							<td>${report.loadDescription}</td>
							<td>${report.stopShippingLocation}</td>
							<td>${report.stopShippingLocationName}</td>
							<td>${report.truckNumber}</td>
							<td>${report.waybillNumber}</td>
							<td>${report.loadstopYardCode}</td>	
							<td>${report.arriveTime}</td>
							<c:choose>
								<c:when test="${report.completedFlag=='update'}">
									<td>${report.arriveTime}</td>
								</c:when>										 
									<c:when test="${report.completedFlag=='setStop'}">
										<td>${report.movementDateTime}</td>
									</c:when> 									
											<c:otherwise>
												<td></td>
											</c:otherwise>
							</c:choose>													
							<td>${report.departureTime}</td>							
							<c:choose>
								<c:when test="${report.completedFlag=='update'}">
									<td>${report.departureTime}</td>
								</c:when>										 
									<c:when test="${report.completedFlag=='setStop'}">
										<td>${report.estimatedDateTime}</td>
									</c:when> 									
											<c:otherwise>
												<td></td>
											</c:otherwise>
							</c:choose>				
							<td></td>
							<td></td>
							
							<td>${report.driverId}</td>															
							<%-- <td>${report.lastUpdateDate}</td> --%>		
							
							<td>${report.latitude}</td>
							<td>${report.longitude}</td>		
							<td style="display:none;">${report.id}</td>
							<td style="display:none;">${report.loadID}</td>
							</tr>
						</c:forEach>
					</c:if>    
                </tbody>                
              </table>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /. box -->
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
var strStartDate='${startDate}';
var strEndDate='${endDate}';
var sd = strStartDate.split("-");
var ed=strEndDate.split("-");
var d = sd[2]+sd[1]+sd[0]+' - '+ed[2]+ed[1]+ed[0];

  $(function () {	  	 
	  
	  document.getElementById("subbt").disabled = true;
	  
	  $('#reportTable').Tabledit({
		    url: 'Nostra.jsp',
		    editButton: false,
		    deleteButton: false,
		    hideIdentifier: false,
		    columns: {
		        identifier: [0, 'ID'],
		        editable: [[5, 'truckNumber'], [6, 'WaybillNumber'], [7, 'Yard','{"1": "","2": "ABC", "3": "APC", "4": "ARC"}'], [8, 'DriverName','{"1": "","2": "Driver1", "3": "Driver2", "4": "Driver3"}']]
		    },
		    scrollX: true
		});
	  
	   var events = $('#events');
	   var table =	$("#reportTable").DataTable({
		   
	  		dom: "<'row'<'col-sm-2'l><'col-sm-7'B><'col-sm-3'f>>" +
	        "<'row'<'col-sm-12'tr>>" +
	        "<'row'<'col-sm-5'i><'col-sm-7'p>>",
	        buttons: [
	            {
	                text: 'Get selected data',
	                action: function () {
	                	 
	                    var dataJson = '';
	                    var jsonGroupStr = '{"theGroupData":[]}';
	                    var objGroupdata = JSON.parse(jsonGroupStr);
	                   
	                    $.each(table.rows('.selected').nodes(), function (i, item) {
	                        var id = item.id;
	                        
	                        var dataSystemLoadID = item.cells[1].innerText;
	                        var dataRouteNo = item.cells[2].innerText;
	                        var dataTruckNumber = item.cells[5].innerText;
	                        var dataWaybillNumber = item.cells[6].innerText;
	                        var dataYard = item.cells[7].innerText;
	                        var dataDriver = item.cells[8].innerText;
	                        var dataSystemID = item.cells[16].innerText;
	                        var dataLoadID = item.cells[17].innerText;
	                        var itemObjJson = {	                        	
	                            SystemLoadID: dataSystemLoadID,
	                            RouteNo: dataRouteNo,
	                            TruckNumber: dataTruckNumber,
	                            WaybillNumber: dataWaybillNumber,
	                            Yard: dataYard,
	                            Driverids: dataDriver,
	                            SystemID: dataSystemID, 
	                            LoadID: dataLoadID 
	                        };

	                        objGroupdata['theGroupData'].push(itemObjJson);
		                   

	                    });

	                    var myJSON = JSON.stringify(objGroupdata);
	                    alert("Please press the OK button to confirm." );
	                    document.getElementById("subbt").disabled = false;
	                    document.getElementById("console-select-rows").value = myJSON;
	                    
	                }
	            }
	        ],
		    'initComplete': function(settings){
		         var api = this.api();
		      },
	      'columnDefs': [
	         {
	            'targets': 0,
	            'checkboxes': {
	               'selectRow': true
	            }
	         }
	      ],
	      'select': {
	         'style': 'multi'
	      },
	      'order': [[0, 'asc']]
	      
	      
	      
	            }); 
	   
	   
		  	
	  	 //Date picker
	    $('.datepicker').datepicker({
	      autoclose: true,
	      format: 'yyyy-mm-dd'
	    });
	  	 
	    
	    $("#totalAssigned").html(' <c:out value = "${assigned}"/> ');
	    $("#totalCheckedIn").html(' <c:out value = "${CheckedIn}"/> ');
	    $("#totalOntime").html(' <c:out value = "${Ontime}"/> ');
	    $("#totalDelayed").html(' <c:out value = "${Delayed}"/> ');
	  	 
	  
  });
</script>
</body>
</html>