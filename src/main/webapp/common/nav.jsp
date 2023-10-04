<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<!-- <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container">
		<a class="navbar-brand" href="inventory"><img id="logo"
			src="assets/fblogo.jpg" alt="logo" /> HMI Mini Store</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="inventory">Home</a></li>
				<c:choose>
					<c:when test="${fn:contains(user.role, 'ROLE_ADMIN') }">
						<li class="nav-item"><a class="nav-link"
							href="inventory?mode=SHOW_FORM">Add Material</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link"
							href="inventory?mode=SHOW_CART">Cart</a></li>
					</c:otherwise>
				</c:choose>
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="inventory?mode=HISTORY">History</a></li>
				<c:choose>
					<c:when test="${user == null }">
						<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="login">Login</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
							role="button" data-bs-toggle="dropdown" aria-expanded="false">${user.firstname }</a>
							<ul class="dropdown-menu dropdown-menu-end"
								aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" href="login?mode=LOGOUT" onclick="return confirm('Are you sure you want to logout?');">Logout</a></li>
							</ul>
							</li>
					</c:otherwise>
				</c:choose>

			</ul>
		</div>
	</div>
</nav> -->
 <!-- <nav>
    <input type="checkbox" id="check">
    <label for="check" class="check-btn">
    <i class="fa fas fa-bars" ></i>
    </label>
    <label class="logo">MyMiniStore</label>
    <ul>
        <li><a href="inventory" >Home</a></li>
        <c:choose>
					<c:when test="${user == null }">
						<li><a href="login">Login</a></li>
					</c:when>
					<c:otherwise>
						<li><a>${user.firstname }</a>
							<ul class="dropdown">
								<li><a href="login?mode=LOGOUT" onclick="return confirm('Are you sure you want to logout?');">Logout</a></li>
							</ul>
							</li>
					</c:otherwise>
				</c:choose>
        <c:choose>
					<c:when test="${fn:contains(user.role, 'ROLE_ADMIN') }">
						<li><a 
							href="inventory?mode=SHOW_FORM">Add Material</a></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="inventory?mode=SHOW_CART">Cart</a></li>
					</c:otherwise>
				</c:choose>
        <li><a href="inventory?mode=HISTORY" >History</a></li>
        
    </ul>
   </nav> -->
   
   <nav>
        <div class="ministore">
            <div class="logo"><a href="#">Mini<span>X</span></a> </div>

            <ul class="nav-links">
                <li><a href="#">Home</a></li>
                <c:choose>
					<c:when test="${fn:contains(user.role, 'ROLE_ADMIN') }">
						<li><a 
							href="inventory?mode=SHOW_FORM">Add Material</a></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="inventory?mode=SHOW_CART">Cart</a></li>
					</c:otherwise>
				</c:choose>
                <li><a href="inventory?mode=HISTORY" >History</a></li>
                <c:choose>
					<c:when test="${user == null }">
						<li><a href="login">Login</a></li>
					</c:when>
					<c:otherwise>
						<li><a>${user.firstname }</a>
							<ul class="drop-menu">
								<li><a href="login?mode=LOGOUT" onclick="return confirm('Are you sure you want to logout?');">Logout</a></li>
							</ul>
							</li>
					</c:otherwise>
				</c:choose>
            </ul>
        </div>
    </nav>