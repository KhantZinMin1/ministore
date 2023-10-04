<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="common/header.html" />
<title>Add Material</title>
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

	<div class="container">
		<h2>Add Material Form</h2>
		<form action="inventory" method="post" enctype="multipart/form-data">

			<input type="hidden" name="mode" value="CREATE">

			<div class="mb-3">
				<label for="image" class="form-label">Image</label> <input
					type="file" name="image" class="form-control" id="image"
					required="required">
			</div>

			<div class="mb-3">
				<label for="name" class="form-label">Material Name</label> <input
					type="text" name="name" class="form-control" id="name"
					required="required">
			</div>

			<div class="mb-3">
				<label for="price" class="form-label">Price</label> <input
					type="number" name="price" class="form-control" id="price"
					required="required">
			</div>

			<div class="mb-3">
				<label for="description" class="form-label">Description</label> <input
					type="text" name="description" class="form-control"
					id="description" required="required">
			</div>

			<div class="mb-3">
				<label for="category" class="form-label">Category </label> <select
					name="category" id="category" required="required"
					class="form-control">
					<option value="Food">Food</option>
					<option value="Drink">Drink</option>
					<option value="Fruit">Fruit</option>
					<option value="Cosmetic">Cosmetic</option>
				</select>
			</div>

			<div class="mb-3">
				<label for="instock" class="form-label">Instock</label> <input
					type="number" name="instock" class="form-control" id="instock"
					required="required">
			</div>

			<button type="submit" class="btn btn-primary">Add</button>
		</form>

	</div>

	<c:import url="common/footer.html" />

</body>
</html>