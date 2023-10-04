<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="common/header.html" />
<title>Sign In Form</title>
<link rel="stylesheet" href="css/style.css">
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
	box-sizing: border-box;
	font-family: sans-serif;
}

span {
	font-size: 2rem;
	font-style: italic;
}

nav {
	background: blue;
	position: fixed;
	width: 100%;
	z-index: 999;
}

nav .ministore {
	background: blue;
	max-width: 1250px;
	padding: 15px 30px;
	margin: auto;
	display: flex;
	align-items: center;
	justify-content: space-between;
}

.ministore .logo a {
	color: #f2f2f2;
	text-decoration: none;
	font-size: 27px;
	font-weight: 600;
}

.ministore .nav-links {
	display: inline-flex;
}

.nav-links li {
	list-style: none;
}

.nav-links li a {
	color: #f2f2f2;
	text-decoration: none;
	font-size: 18px;
	font-weight: 500;
	padding: 9px 15px;
	border-radius: 5px;
	transition: all 0.3s ease;
}

.nav-links li a:hover {
	background: #3A3B3C;
}

.nav-links .drop-menu {
	background: #242526;
	width: 180px;
	top: 65px;
	line-height: 45px;
	position: absolute;
	opacity: 0;
	visibility: hidden;
}

.nav-links li:hover .drop-menu {
	top: 65px;
	opacity: 1;
	visibility: visible;
	transition: all 0.3s ease;
}

.drop-menu li a {
	width: 70%;
	display: block;
	padding: 0 30px 0 0;
	font-weight: 400;
	border-radius: 0px;
}
</style>
</head>
<body>
	<c:import url="common/nav.jsp" />
	<br>
	<br>
	<br>
	<!-- <div id="div">
			<h1>Sign in Form</h1>
			<br>
			<form action="login" method="post">
				<input type="hidden" name="mode" value="LOGIN">
				<div class="mb-3">
					<label for="email" class="form-label">Email address</label> <input
						type="email" class="form-control" id="email" name="email"
						aria-describedby="emailHelp">
					<div id="emailHelp" class="form-text">We'll never share your
						email with anyone else.</div>
				</div>
				<div class="mb-3">
					<label for="password" class="form-label">Password</label> <input
						type="password" class="form-control" id="password" name="password">
				</div>
				<button type="submit" class="btn btn-primary">Login</button>
			</form>
			<br> Not have an account?<a href="user">Sign up Here</a>
	</div> -->
	<section class="vh-100 gradient-custom">
		<div class="container py-5 h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-12 col-md-8 col-lg-6 col-xl-5">
					<div class="card bg-dark text-white" style="border-radius: 1rem;">
						<div class="card-body p-5 text-center">

							<div class="mb-md-5 mt-md-4 pb-5">

								<h2 class="fw-bold mb-2 text-uppercase">Login</h2>
								<p class="text-white-50 mb-5">Please enter your login and
									password!</p>
								<form action="login" method="post">
									<input type="hidden" name="mode" value="LOGIN">
									<div class="form-outline form-white mb-4">
										<input type="email" id="email" name="email"
											class="form-control form-control-lg" /> <label
											class="form-label" for="email">Email</label>
									</div>

									<div class="form-outline form-white mb-4">
										<input type="password" id="password" name="password"
											class="form-control form-control-lg" /> <label
											class="form-label" for="password">Password</label>
									</div>

									<button class="btn btn-outline-light btn-lg px-5" type="submit">Login</button>
								</form>

							</div>

							<div>
								<p class="mb-0">
									Don't have an account? <a href="user"
										class="text-white-50 fw-bold">Sign Up</a>
								</p>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<c:import url="common/footer.html" />
</body>
</html>