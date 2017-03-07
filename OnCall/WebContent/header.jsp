
	<nav class="navbar navbar-default navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
					<span class="sr-only">Toggle Navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="">OnCall</a>
			</div>
			<div class="navbar-collapse collapse" id="navbarCollapse">
				<ul class="nav navbar-nav">
					<li class="<c:if test="${activeMenu eq 'dashboard'}">active</c:if>"><a href="<%=request.getContextPath() %>/dashboard"><i class="fa fa-dashboard"></i> Dashboard</a></li>
					<li class="<c:if test="${activeMenu eq 'departments'}">active</c:if>"><a href="<%=request.getContextPath() %>/department"><i class="fa fa-university"></i> Departments</a></li>
					<li class="<c:if test="${activeMenu eq 'resources'}">active</c:if>"><a href="<%=request.getContextPath() %>/resource"><i class="fa fa-users"></i> Resources</a></li>
					<li class="<c:if test="${activeMenu eq 'schedules'}">active</c:if>"><a href="<%=request.getContextPath() %>/schedule"><i class="fa fa-calendar"></i> Schedules</a></li>
				</ul>
			</div>
		</div>
	</nav>
