
	<nav class="navbar navbar-toggleable-md navbar-inverse fixed-top bg-inverse">
		<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
		<a class="navbar-brand" href="#">OnCall</a>
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item <c:if test="${activeMenu eq 'dashboard'}">active</c:if>"><a class="nav-link" href="<%=request.getContextPath() %>/dashboard"><i class="fa fa-dashboard"></i> Dashboard <span class="sr-only">(current)</span></a></li>
				<li class="nav-item <c:if test="${activeMenu eq 'departments'}">active</c:if>"><a class="nav-link" href="<%=request.getContextPath() %>/department"><i class="fa fa-university"></i> Departments</a></li>
				<li class="nav-item <c:if test="${activeMenu eq 'resources'}">active</c:if>"><a class="nav-link" href="<%=request.getContextPath() %>/resource"><i class="fa fa-users"></i> Resources</a></li>
				<li class="nav-item <c:if test="${activeMenu eq 'schedules'}">active</c:if>"><a class="nav-link" href="<%=request.getContextPath() %>/schedule"><i class="fa fa-calendar"></i> Schedules</a></li>
			</ul>
<!-- 
			<form class="form-inline mt-2 mt-md-0">
				<input class="form-control mr-sm-2" type="text" placeholder="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
-->
		</div>
	</nav>
