<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<c:import url="common/header.html" />
<title>History Form</title>
<!-- CDN-->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script
	src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/school.ico" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
<link href="css/custom.css" rel="stylesheet" />
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
	<!-- Responsive navbar-->
	<c:import url="common/nav.jsp" />
	<!-- Page content-->
	<div class="container">
		<br>
		<br>
		<br>
		<br>
		<h1>History</h1>
		<br>
		<table id="example" class="table table-striped" style="width: 100%">
			<thead>
				<tr>

					<c:if test="${fn:contains(user.role, 'ROLE_ADMIN') }">
						<th>Firstname</th>
						<th>Lastname</th>
					</c:if>
					<th>List</th>
					<th>Date</th>
					<th>Total Amount</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="historyList" items="${historyList }">
					<tr>

						<c:if test="${fn:contains(user.role, 'ROLE_ADMIN') }">
							<td>${historyList.firstname }</td>
							<td>${historyList.lastname }</td>
						</c:if>
						<td>${historyList.list }</td>
						<td>${historyList.date }</td>
						<td>${historyList.total }MMK</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div align="right">
			<br> <a class="btn btn-secondary" href="inventory">Back</a>
		</div>

	</div>


	<c:import url="common/footer.html" />

	<script>
		$(document).ready(function() {
			$('#example').DataTable();
		});
	</script>
</body>
</html>
