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
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/bootstrap-datetimepicker.min.css">
		<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
		<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/font-awesome.min.css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/style.css">
</head>
<body>

	<c:set var="activeMenu" scope="session" value="schedules"></c:set>
	<%@include file="/header.jsp" %>

	<div class="container-fluid">

		<ol class="breadcrumb">
			<li><i class="fa fa-dashboard"></i> <a href="<%=request.getContextPath()%>/">Dashboard</a></li>
			<li><i class="fa fa-calendar"></i> <a href="<%=request.getContextPath() %>/schedule">Schedules</a></li>
			<li class="active"><i class="fa fa-pencil"></i> Edit Schedule</li>
		</ol>

		<div class="content">
			<div class="panel panel-default">
				<div class="panel-heading">
					Schedule
				</div>
				<form class="form-horizontal" action="ScheduleController" method="POST">
					<div class="panel-body">
						<fieldset>
							<div class="form-group">
								<label class="col-md-2 control-label" for="sequence">Sequence</label>
								<div class="col-md-10">
									<input class="form-control" type="text" name="sequence" value="<c:out value="${schedule.sequence}" />" readonly="readonly" placeholder="Sequence" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" for="startdate">Start Date</label>
								<div class="col-md-10">
									<div class="input-group input-append date" id="startdatepicker">
										<input class="form-control" type="text" name="startdate" readonly="readonly" value="<c:out value="${schedule.startDate}" />" placeholder="Start Date" />
										<span class="input-group-addon add-on"><i class="fa fa-calendar"></i></span>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" for="enddate">End Date</label>
								<div class="col-md-10">
									<div class="input-group input-append date" id="enddatepicker">
										<input class="form-control" type="text" name="enddate" readonly="readonly" value="<c:out value="${schedule.endDate}" />" placeholder="End Date" />
										<span class="input-group-addon add-on"><i class="fa fa-calendar"></i></span>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" for="department">Department</label>
								<div class="col-md-10">
									<select class="form-control" name="department" id="department">
										<c:forEach items="${departments}" var="department" varStatus="loop">
											<c:choose>
												<c:when test="${empty schedule.department}">
													<option value="${department.sequence}">${department.name}</option>
												</c:when>
												<c:otherwise>
													<c:choose>
														<c:when test="${schedule.department eq department.sequence}">
															<option value="${department.sequence}" selected="selected">${department.name}</option>
														</c:when>
														<c:otherwise>
															<option value="${department.sequence}">${department.name}</option>
														</c:otherwise>
													</c:choose>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" for="resource">Resource</label>
								<div class="col-md-10">
									<select class="form-control" name="resource" id="resource">
										<c:forEach items="${resources}" var="resource" varStatus="loop">
											<c:choose>
												<c:when test="${schedule.resource eq resource.sequence}">
													<option value="${resource.sequence}" selected="selected">${resource.firstName}&nbsp;${resource.lastName}</option>
												</c:when>
												<c:otherwise>
													<option value="${resource.sequence}">${resource.firstName}&nbsp;${resource.lastName}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" for="active">Active</label>
								<div class="col-md-10">
									<select class="form-control" name="active">
										<option value="Y" <c:out value="${schedule.active ? 'selected' : ''}" />>Yes</option>
										<option value="N" <c:out value="${schedule.active ? '' : 'selected'}" />>No</option>
									</select>
								</div>
							</div>
						</fieldset>
					</div>
					<div class="panel-footer">
						<div>
							<input class="btn btn-default offset-md-2" type="submit" value="Submit" />
						</div>
					</div>
				</form>
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
	<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/bootstrap-datetimepicker.min.js"></script>
		<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/ie10-viewport-bug-workaround.js"></script>

	<script type="text/javascript">

		$(document).ready(function() {
			$('#startdatepicker').datepicker({
				autoclose: true,
	            format: 'yyyy-mm-dd'
	        })
	        
	        $('#enddatepicker').datepicker({
	        	autoclose: true,
	            format: 'yyyy-mm-dd'
	        })
		});

	</script>

</body>
</html>
