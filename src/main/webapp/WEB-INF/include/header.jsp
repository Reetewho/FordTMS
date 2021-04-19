<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <header class="main-header">
    <!-- Logo -->
    <c:choose>
    <c:when test="${S_FordUser.role=='1'}">
    <a href="<c:url value='/calendar' />" class="logo">   
		
      	<c:choose>
		<c:when test="${S_profile=='prod'}">
			<!-- mini logo for sidebar mini 50x50 pixels -->
      		<span class="logo-mini"><b>AP</b></span>
      		<!-- logo for regular state and mobile devices -->
      		<span class="logo-lg"><b>AP</b>Transport Center</span>
		</c:when> 									
		<c:otherwise>
			<!-- mini logo for sidebar mini 50x50 pixels -->
      		<span class="logo-mini"><b>AP-<c:out value="${S_profile}"></c:out></b></span>
      		<!-- logo for regular state and mobile devices -->
      		<span class="logo-lg"><b>AP</b>Transport-<c:out value="${S_profile}"></c:out></span>
		</c:otherwise>
		</c:choose>			
    </a>
     </c:when>
     		 <c:when test="${S_FordUser.role=='2'}">
    				  <a href="<c:url value='/calendar' />" class="logo">   
				      <!-- mini logo for sidebar mini 50x50 pixels -->
				      <span class="logo-mini"><b>AP</b></span>
				      <!-- logo for regular state and mobile devices -->
				      <span class="logo-lg"><b>AP</b>  Transport Center</span>
				    </a>
			</c:when>
				<c:when test="${S_FordUser.role=='3'}">
				    <a href="<c:url value='/load-list-drivers/${S_FordUser.username}' />" class="logo">   
				      <!-- mini logo for sidebar mini 50x50 pixels -->
				      <span class="logo-mini"><b>AP</b></span>
				      <!-- logo for regular state and mobile devices -->
				      <span class="logo-lg"><b>AP</b>  Transport Center</span>
				    </a>
			</c:when>
     </c:choose>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">          
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="<c:url value='/assets/dist/svg/004-chick.svg' />" class="user-image" alt="User Image" style="background-color:white">
              <span class="hidden-xs">${S_FordUser.name}</span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="<c:url value='/assets/dist/svg/004-chick.svg' />" class="img-circle" alt="User Image" style="background-color:white">
				<fmt:parseDate value="${S_FordUser.joiningDate}" pattern="yyyy-MM-dd" var="joiningDate" type="both" />
			    
                <p>
                  ${S_FordUser.name} - ${S_FordUser.department}
                  <small>Member since  <fmt:formatDate pattern="MMM. yyyy" value="${ joiningDate }" /></small>
                </p>
              </li>              
              <!-- Menu Footer-->
              <li class="user-footer">
                <!--  div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat">Profile</a>
                </div-->
                <div class="pull-right">
                  <a href="<c:url value='/logout/' />" class="btn btn-default btn-flat">Sign out</a>
                </div>
              </li>
            </ul>
          </li>          
        </ul>
      </div>
    </nav>
  </header>