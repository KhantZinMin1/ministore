<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<c:import url="common/header.html" />
<title>Cart Form</title>
<!-- CDN-->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script
	src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<!-- Favicon-->

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
	<div>
		<c:import url="common/nav.jsp" />
	</div>
	<div class="container">
		<br>
		<br>
		<br>
		<br>
		<h1>Your Choose List</h1>
		<br>
		<table id="example" class="table table-striped" style="width: 100%">
			<thead>
				<tr>
					<th>MaterialList</th>
					<th>Price</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="buyList" items="${buyList }">
					<c:url var="removeURL" value="inventory">
						<c:param name="mode" value="REMOVE"></c:param>
						<c:param name="id" value="${buyList.id }"></c:param>
					</c:url>
					<input type="hidden" name="id" value="${buyList.id }">
					<tr>
						<td>${buyList.name }</td>
						<td>${buyList.price }MMK</td>
						<td><a class="btn btn-secondary" href="${removeURL }"
							onclick="return myConfirm();">Remove</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div align="left">
			<br>
			<h4>Total Amount : ${total } MMK</h4>
		</div>
		<div align="right">
			<a class="btn btn-secondary" href="inventory">Back</a> <a
				class="btn btn-secondary" href="inventory?mode=BUY">Buy</a>
		</div>

	</div>
	<c:import url="common/footer.html" />

	<script>
		function myConfirm() {
			var result = confirm("Are you sure you want to remove?");
			if (result == true) {
				return true;
			} else {
				return false;
			}
		}
	</script>
</body>

</html>
