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
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/bootstrap-reboot.min.css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/bootstrap-multiselect.css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/font-awesome.min.css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/style.css">
</head>
<body>

	<c:set var="activeMenu" scope="session" value="resources"></c:set>
	<%@include file="/header.jsp" %>

	<div class="container-fluid">

		<ol class="breadcrumb">
			<li class="breadcrumb-item"><i class="fa fa-dashboard"></i> <a href="">Dashboard</a></li>
			<li class="breadcrumb-item active"><i class="fa fa-users"></i> Resources</li>
		</ol>

		<div class="content">
			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="card-header">
							<h5 class="card-title">
								Resources
								<a class="btn btn-default btn-sm pull-right" href="<%=request.getContextPath()%>/resource?action=insert" type="button"><i class="fa fa-plus"></i> Add New Resource</a>
								<div class="clearfix"></div>
							</h5>
						</div>
						<div class="card-block">
							<table class="table table-hover table-sm">
								<thead>
									<tr>
										<th>First Name</th>
										<th>Last Name</th>
										<th>Contact Number</th>
										<th>E-mail</th>
										<th>Active</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${resources}" var="resource" varStatus="loop">
										<tr>
											<td><c:out value="${resource.firstName}" /></td>
											<td><c:out value="${resource.lastName}" /></td>
											<td><c:out value="${resource.contactNumber}" /></td>
											<td><c:out value="${resource.email}" /></td>
											<td><c:out value="${resource.active ? 'Yes' : 'No'}" /></td>
											<td>
												<a class="btn btn-sm btn-primary" href="resource?action=edit&sequence=<c:out value="${resource.sequence}"/>"><i class="fa fa-pencil"></i> Edit</a>
												<a class="btn btn-sm btn-danger" href="resource?action=delete&sequence=<c:out value="${resource.sequence}"/>"><i class="fa fa-trash"></i> Delete</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="fixed-bottom">
		<div class="container-fluid">
			<div class="pull-right hidden-xs"><b>Version</b> 1.0.0</div>
			<strong>Copyright &copy; 2017 <a href="http://www.altron.com">Altron</a>.</strong> All rights reserved.
		</div>
	</div>

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
						<p>You are about to delete a department.</p>
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
