<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">

	<title>OnCall</title>

	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/bootstrap.min.css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/bootstrap-grid.min.css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/bootstrap-multiselect.css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/font-awesome.min.css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/style.css">
</head>
<body>

	<c:set var="activeMenu" scope="session" value="schedules"></c:set>
	<%@include file="/header.jsp" %>

	<div class="container-fluid">

		<ol class="breadcrumb">
			<li><i class="fa fa-dashboard"></i> <a href="<%=request.getContextPath()%>/">Dashboard</a></li>
			<li class="active"><i class="fa fa-calendar"></i> Schedules</li> 
		</ol>

		<div class="content">
			<div class="panel panel-default">
				<div class="panel-heading">
					Schedules
					<a class="btn btn-default btn-xs pull-right" href="<%=request.getContextPath()%>/schedule?action=insert" type="button"><i class="fa fa-plus"></i> Add New Schedule</a>
				</div>
				<table class="table table-hover table-condensed">
					<thead>
						<tr>
							<th>Start Date</th>
							<th>End Date</th>
							<th>Department</th>
							<th>Resource</th>
							<th>Active</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${schedules}" var="schedule" varStatus="loop">
							<tr>
								<td><c:out value="${schedule.startDate}" /></td>
								<td><c:out value="${schedule.endDate}" /></td>
								<td>
									<c:forEach items="${departments}" var="department" varStatus="loop">
										<c:choose>
											<c:when test="${schedule.department eq department.sequence}">
												<c:out value="${department.name}" />
											</c:when>
										</c:choose>
									</c:forEach>
								</td>
								<td>
									<c:forEach items="${resources}" var="resource" varStatus="loop">
										<c:choose>
											<c:when test="${schedule.resource eq resource.sequence}">
												<c:out value="${resource.firstName}" />&nbsp;<c:out value="${resource.lastName}" />
											</c:when>
										</c:choose>
									</c:forEach>
								</td>
								<td><c:out value="${schedule.active ? 'Yes' : 'No'}" /></td>
								<td>
									<a class="btn btn-xs btn-primary" href="schedule?action=edit&sequence=<c:out value="${schedule.sequence}"/>"><i class="fa fa-pencil"></i> Edit</a>
									<a class="btn btn-xs btn-danger" href="schedule?action=delete&sequence=<c:out value="${schedule.sequence}"/>"><i class="fa fa-trash"></i> Delete</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<nav class="navbar navbar-default navbar-fixed-bottom">
		<div class="container-fluid">
			<div class="panel-body">
				<strong>Copyright &copy; 2017 <a href="http://www.altron.com">Altron</a>.</strong> All rights reserved.
				<div class="pull-right"><b>Version</b> 1.0.0</div>
			</div>
		</div>
	</nav>

	<!--
		Delete Confirmation Modal form 
	 -->
	<div class="delete-confirmation-modal">
		<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Confirm Delete</h4>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					</div>
					<div class="modal-body">
						<p>You are about to delete a schedule.</p>
						<p>Do you want to proceed?</p>
					</div>
					<div class="modal-footer">
						<button class="btn btn-default" data-dismiss="modal">Cancel</button>
						<button class="btn btn-danger btn-ok">Delete</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/jquery-3.1.1.slim.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/tether.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/bootstrap-multiselect.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/ie10-viewport-bug-workaround.js"></script>

	<script>

		$(document).ready(function() {



		});
	</script>

</body>
</html>
