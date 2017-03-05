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
			<li class="breadcrumb-item"><i class="fa fa-users"></i> <a href="<%=request.getContextPath() %>/resource">Resources</a></li>
			<li class="breadcrumb-item active"><i class="fa fa-pencil"></i> Edit Resource</li>
		</ol>

		<div class="content">
			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="card-header">
							<h5 class="card-title">Resource</h5>
						</div>
						<form class="form-horizontal" action="ResourceController" method="POST">
							<div class="card-block">
								<fieldset>
									<div class="form-group row">
										<label class="col-md-2 control-label" for="sequence">Sequence</label>
										<div class="col-md-10">
											<input class="form-control" type="text" name="sequence" value="<c:out value="${resource.sequence}" />" readonly="readonly" placeholder="Sequence" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-md-2 control-label" for="firstname">First Name</label>
										<div class="col-md-10">
											<input class="form-control" type="text" name="firstname" value="<c:out value="${resource.firstName}" />" placeholder="First Name" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-md-2 control-label" for="lastname">Last Name</label>
										<div class="col-md-10">
											<input class="form-control" type="text" name="lastname" value="<c:out value="${resource.lastName}" />" placeholder="Last Name" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-md-2 control-label" for="contactnumber">Contact Number</label>
										<div class="col-md-10">
											<input class="form-control" type="text" name="contactnumber" value="<c:out value="${resource.contactNumber}" />" placeholder="Contact Number" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-md-2 control-label" for="email">E-mail</label>
										<div class="col-md-10">
											<input class="form-control" type="text" name="email" value="<c:out value="${resource.email}" />" placeholder="E-mail" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-md-2 control-label" for="active">Active</label>
										<div class="col-md-10">
											<select class="form-control" name="active">
												<option value="Y" <c:out value="${resource.active ? 'selected' : ''}" />>Yes</option>
												<option value="N" <c:out value="${resource.active ? '' : 'selected'}" />>No</option>
											</select>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-md-2 control-label" for="departments">Department</label>
										<div class="col-md-10">
											<select class="form-control" name="departments" id="departments" multiple="multiple">
												<c:forEach items="${departments}" var="department" varStatus="loop">
													<c:choose>
														<c:when test="${empty resource.departments}">
															<option value="${department.sequence}">${department.name}</option>
														</c:when>
														<c:otherwise>
															<c:forEach items="${resource.departments}" var="rd">
																<c:choose>
																	<c:when test="${rd eq department.sequence}">
																		<option value="${department.sequence}" selected="selected">${department.name}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${department.sequence}">${department.name}</option>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
														</c:otherwise>
													</c:choose>
												</c:forEach>
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

			$('#departments').multiselect({
				buttonWidth: '100%'
			});
			$(".caret").css('float', 'right');
		});
	</script>

</body>
</html>
