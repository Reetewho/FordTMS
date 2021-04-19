<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<meta http-equiv="Content-Style-Type" content="text/css" />
	<title>Group Internet Claims - Error Page</title>
	<style type="text/css">
	<!--
	.style2 {
		color: #FFFFFF
	}
	
	.logo-lg {
    	display: block;
    	color: #fff;
  		font-weight:bold;
  		background-color: #367fa9;
	}
	.showText {
    	display: block;
    	color: #fff;
  		font-weight:bold;
  		background-color: #333;
	}

	-->
	</style>
</head>
<body onload="( function() { try { document.getElementById('btnOk').focus(); } catch (e) { } } )();">
	<table border="0" cellpadding="0" cellspacing="0" style="height: 512px; width: 100%;">
		<tr>
			<td align="center">
				<table border="0" cellpadding="5" cellspacing="0" style="width: 400px;">
					<tbody>
						<tr>
							<th colspan="2" scope="rowgroup">Error - 404&nbsp;&nbsp;</th>
						</tr>
						<tr>
							<td valign=top colspan="2" class="table-text" align="center">
								<span class="logo-lg">&nbsp;</span>
								<span class="logo-lg">
									<b>AP</b> Transport Center
								</span>
								<span class="logo-lg">&nbsp;</span>
								<span class="showText">
									&nbsp;
								</span>
								<span class="showText">
									Your URL Request Not Found
								</span>
								<span class="showText">
									&nbsp;
								</span>
							</td>
						</tr>
						<tr>
							<td align="center" width="150" colspan="2">
								<input type="button" id="btnOk" value="    OK    " onclick="document.location.href='<c:url value='/' />'">
							</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
