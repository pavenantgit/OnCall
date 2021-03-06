<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="apple-mobile-web-app-capable" content="yes">

	<title>OnCall</title>

	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/bootstrap.min.css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/bootstrap-grid.min.css">
	<!-- link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/bootstrap-reboot.min.css" -->
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/bootstrap-multiselect.css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/font-awesome.min.css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/style.css">

</head>
<body>

	<c:set var="activeMenu" scope="session" value="dashboard"></c:set>
	<%@include file="/header.jsp" %>

	<div class="container-fluid">

		<div class="row">

			<div class="col-md-4">
				<div class="panel panel-primary">
					<div class="panel-heading">Department Name</div>
					<div class="panel-body">
						<h5>Resource Name</h5>
						<p class="panel-text">
							082 123 1234<br />
							someone@somewhere.com
						</p>
					</div>
				</div>
			</div>
	
			<div class="col-md-4">
				<div class="panel panel-success">
					<div class="panel-heading">Department Name</div>
					<div class="panel-body panel-success">
						<h5>Resource Name</h5>
						<p class="panel-text">
							082 123 1234<br />
							someone@somewhere.com
						</p>
					</div>
				</div>
			</div>
	
			<div class="col-md-4">
				<div class="panel panel-info">
					<div class="panel-heading">Department Name</div>
					<div class="panel-body">
						<h5>Resource Name</h5>
						<p class="panel-text">
							082 123 1234<br />
							someone@somewhere.com
						</p>
					</div>
				</div>
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
