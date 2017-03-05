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

	<c:set var="activeMenu" scope="session" value="departments"></c:set>
	<%@include file="/header.jsp" %>

	<div class="container-fluid">

		<ol class="breadcrumb">
			<li class="breadcrumb-item"><i class="fa fa-dashboard"></i> <a href="">Dashboard</a></li>
			<li class="breadcrumb-item"><i class="fa fa-university"></i> <a href="<%=request.getContextPath() %>/department">Departments</a></li>
			<li class="breadcrumb-item active"><i class="fa fa-pencil"></i> Edit Department</li>
		</ol>

		<div class="content">
			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">Department</h3>
						</div>
						<form class="form-horizontal" action="DepartmentController" method="POST">
							<div class="card-block">
								<fieldset>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="sequence">Sequence</label>
										<div class="col-sm-10">
											<input class="form-control" type="text" name="sequence" value="<c:out value="${department.sequence}" />" readonly="readonly" placeholder="Sequence" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="name">Department</label>
										<div class="col-sm-10">
											<input class="form-control" type="text" name="name" value="<c:out value="${department.name}" />" placeholder="Department Name" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="description">Description</label>
										<div class="col-sm-10">
											<input class="form-control" type="text" name="description" value="<c:out value="${department.description}" />" placeholder="Description" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="active">Active</label>
										<div class="col-sm-10">
											<select class="form-control" name="active">
												<option value="Y" <c:if test="${department.active eq true}">selected="selected"</c:if>>Yes</option>
												<option value="N" <c:if test="${department.active eq false}">selected="selected"</c:if>>No</option>
											</select>
										</div>
									</div>
								</fieldset>
							</div>
							<div class="card-footer">
								<div>
									<input class="btn btn-default" type="submit" value="Submit" />
								</div>
							</div>
						</form>
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

	<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/jquery-3.1.1.slim.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/tether.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/bootstrap-multiselect.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/ie10-viewport-bug-workaround.js"></script>

	<script>

		$(document).ready(function() {

			var date = new Date();

		});
	</script>

</body>
</html>
