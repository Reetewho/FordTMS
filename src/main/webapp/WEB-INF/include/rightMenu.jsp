<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
         <img src="<c:url value='/assets/dist/svg/004-chick.svg' />" class="img-circle" alt="User Image" style="background-color:white">
        </div>
        <div class="pull-left info">
          <%-- <p>${S_FordUser.name}</p> --%>
          <a href="#"><i class="fas fa-circle text-success"></i> Online</a>
        </div>
      </div>
      
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
        <li class="header">MAIN NAVIGATION</li>
        <c:forEach items="${P_FordUser}" var="permissionMenu">
	        <c:choose>
			<c:when test="${permissionMenu.id_menu=='1'}">
		         <li>
			          <a href="<c:url value='/calendar' />">
			            <i class="fas fa-calendar-alt"></i> &nbsp;&nbsp;<span>Calendar</span>            
			          </a>
		        </li>
	        </c:when>
	        </c:choose>
	        <c:choose>
		        <c:when test="${permissionMenu.id_menu=='2'}">
		         <li>
					  <a href="<c:url value='/load-list-drivers/${S_FordUser.username}' />">
					       <i class="fas fa-list"></i> &nbsp;&nbsp;<span>Inbox Load List</span>            
					  </a>
				  </li>
				</c:when>     
			</c:choose> 
        <!-- 
        <li>
          <a href="<c:url value='/searchby-loadid-processloadRetrieve' />">
            <i class="fas fa-file-alt"></i> &nbsp;&nbsp;<span>Search by Load ID (LoadRetrieve)</span>            
          </a>
        </li>
        -->
	        <c:choose>
	        <c:when test="${permissionMenu.id_menu=='3'}">
		       <li>
		          <a href="<c:url value='/searchby-loadid' />">
		            <i class="fas fa-search"></i> &nbsp;&nbsp;<span>Search by Load ID</span>            
		          </a>
		        </li>
	        </c:when>
	        </c:choose>
	        <c:choose>
	        <c:when test="${permissionMenu.id_menu=='4'}">
		        <li>
		          <a href="<c:url value='/report' />">
		            <i class="fas fa-file-alt"></i> &nbsp;&nbsp;<span>Operation Report</span>            
		          </a>
		        </li>
	        </c:when>
	        </c:choose>
	        <c:choose>
	        <c:when test="${permissionMenu.id_menu=='5'}">
		        <li>
		          <a href="<c:url value='/paymentreport' />">
		            <i class="fas fa-receipt"></i> &nbsp;&nbsp;<span>Summary Report</span>            
		          </a>
		        </li>
	        </c:when>
	        </c:choose>
	        <c:choose>
	        <c:when test="${permissionMenu.id_menu=='6'}">
		        <li>
		          <a href="<c:url value='/userList' />">
		            <i class="fa fa-user"></i> &nbsp;<span>User Management</span>            
		          </a>
		        </li>
	        </c:when>
	         </c:choose>	
	         <c:choose>
		     <c:when test="${permissionMenu.id_menu=='7'}">
		        <li>
		          <a href="<c:url value='/manual-add-load' />">
		            <i class="fas fa-plus-square"></i> &nbsp;<span>Manual Add Load</span>            
		          </a>
		        </li>
		      </c:when>
	          </c:choose>
         
       <%--  <c:choose>
        <c:when test="${permissionMenu.id_menu=='8'}">
        <li>
          <a href="<c:url value='/Monitor' />">
            <i class="fas fa-desktop"></i> &nbsp;<span>Monitor</span>            
          </a>
        </li>
        </c:when>
         </c:choose>  --%>
         
         <c:choose>
	     <c:when test="${permissionMenu.id_menu=='8'}">
	        <li>
	          <a href="<c:url value='/adminreport' />">
	            <i class="fas fa-receipt"></i> &nbsp;<span>Operation Report(Admin)</span>            
	          </a>
	        </li>
	     </c:when>
         </c:choose>
         
         <c:choose>
         <c:when test="${permissionMenu.id_menu=='8'}">
	         <li>
	          <a href="<c:url value='/nostra' />">
	            <i class="fas fa-receipt"></i> &nbsp;<span>Nostra Report(Admin)</span>            
	          </a>
	         </li>
         </c:when>
         </c:choose>
         
        <c:choose>
        <c:when test="${permissionMenu.id_menu=='8'}">
        <li>
          <a href="<c:url value='/trucklist' />">
            <i class="fas fa-receipt"></i> &nbsp;<span>Truck Management(Admin)</span>            
          </a>
        </li>
        </c:when>
        </c:choose>
         
        <c:choose>
        <c:when test="${permissionMenu.id_menu=='8'}">
	        <li>
	          <a href="<c:url value='/gsdblist' />">
	            <i class="fas fa-receipt"></i> &nbsp;<span>GSDB Management(Admin)</span>            
	          </a>
	        </li>
        </c:when>
        </c:choose>
        
       
         <%--  <c:choose>
        <c:when test="${permissionMenu.id_menu=='8'}">
        <li>
          <a href="<c:url value='/testRestAPI' />">
            <i class="fas fa-receipt"></i> &nbsp;<span>summarylist (Admin)</span>            
          </a>
        </li>
        </c:when>
         </c:choose> --%>
         
        </c:forEach>
        
         <c:choose>
	        <c:when test="${fordPermissionDev=='On'}">
	         	<li>
			    	<a href="<c:url value='/create-cron-job' />">
			    		<i class="fas fa-receipt"></i> &nbsp;&nbsp;<span>Create-CronJob</span>            
			    	</a>
		        </li>
		        <li>
			    	<a href="<c:url value='/show-all-job' />">
			    		<i class="fas fa-receipt"></i> &nbsp;&nbsp;<span>Task Scheduler</span>            
			    	</a>
		        </li>
		     </c:when>
         </c:choose>
        
        <!-- 
         <li>
          <a href="<c:url value='/showtest' />">
            <i class="fas fa-file-alt"></i> &nbsp;&nbsp;<span>Test</span>            
          </a>
        </li>
        -->
         <!-- li>
          <a href="<c:url value='/userList' />">
            <i class="fa fa-user"></i> &nbsp;&nbsp;<span>User</span>            
          </a>
        </li-->        
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>