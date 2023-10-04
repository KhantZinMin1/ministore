<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<c:import url="common/header.html" />
<title>Mini Store</title>
<!-- CDN-->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script
	src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>

<style>
#div {
	height: 100px;
	font-family: Verdana, Geneva, Tahoma, sans-serif;
	font-size: 2rem;
	font-style: italic;
	font-weight: bolder;
	color: #2019B3;
	text-align: center;
	background-color: #9E9DB2;
	padding-top: 30px;
}

.div {
	background-color: #9E9DB2;
	height: 100% vh;
	display: inline-block;
}

.div1 {
	border: 1px solid black;
	border-radius: 20px;
	width: 190px;
	font-size: 1.2rem;
	margin: 7px;
	padding: 10px;
	display: inline-block;
	background-color: white;
}

img {
	width: 170px;
	height: 170px;
}

.a1 {
	text-decoration: none;
	background-color: #8EBCC9;
	border-radius: 10px;
	color: blue;
	font-family: Verdana, Geneva, Tahoma, sans-serif;
	padding: 5px;
	font-size: 1rem;
	display: inline-block;
}

.a2 {
	text-decoration: none;
	background-color: #8EBCC9;
	border-radius: 10px;
	color: blue;
	font-family: Verdana, Geneva, Tahoma, sans-serif;
	padding: 5px;
	font-size: 1rem;
	margin-left: 30px;
	display: inline-block;
}

.a3 {
	text-decoration: none;
	background-color: #8EBCC9;
	border-radius: 10px;
	color: blue;
	font-family: Verdana, Geneva, Tahoma, sans-serif;
	padding: 5px;
	font-size: 1rem;
	margin-left: 45px;
	display: inline-block;
}

#div1 {
	display: inline-block;
}

#div2 {
	display: inline-block;
}

#p1 {
	font-size: 0.7rem;
}

#p2 {
	font-size: 0.7rem;
	color: red;
}

h2 {
	margin: 50px 0px 30px 30px;
	font-family: sans-serif;
	font-size: 2rem;
	font-style: oblique;
	font-weight: bold;
}

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
}
</style>
</head>
<body>

	<c:import url="common/nav.jsp" />
	<div id="div">WELCOME TO HMI MINISTORE</div>

	<div class="div">
		<h2>Foods</h2>
		<c:forEach var="inventory" items="${inventoryList }">
			<c:url var="updateURL" value="inventory">
				<c:param name="mode" value="LOAD"></c:param>
				<c:param name="id" value="${inventory.id }"></c:param>
			</c:url>

			<c:url var="deleteURL" value="inventory">
				<c:param name="mode" value="DELETE"></c:param>
				<c:param name="id" value="${inventory.id }"></c:param>
			</c:url>

			<c:url var="addCartURL" value="inventory">
				<c:param name="mode" value="ADD_CART"></c:param>
				<c:param name="id" value="${inventory.id }"></c:param>
			</c:url>

			<c:if test="${fn:contains(inventory.category,'Food') }">
				<div class="div1">
					<img src="images/${inventory.image }" alt="blabla">
					<h4>${inventory.name }</h4>
					<h5>Price : ${inventory.price } MMK</h5>
					<p id="p1">${inventory.description }</p>
					<p id="p2">Instock : ${inventory.instock }</p>
					<c:choose>
						<c:when test="${fn:contains(user.role,'ROLE_ADMIN') }">
							<div id="div1" align="left">
								<a class="a1" href="${updateURL }">Update</a>
							</div>
							<div id="div2" align="right">
								<a class="a2" href="${deleteURL }" onclick="return myConfirm();">Delete</a>
							</div>
						</c:when>
						<c:otherwise>
							<a class="a3" href="${addCartURL }">Add Cart</a>
						</c:otherwise>
					</c:choose>

				</div>
			</c:if>
		</c:forEach>

		<h2>Energy Drink</h2>
		<c:forEach var="inventory" items="${inventoryList }">
			<c:url var="updateURL" value="inventory">
				<c:param name="mode" value="LOAD"></c:param>
				<c:param name="id" value="${inventory.id }"></c:param>
			</c:url>

			<c:url var="deleteURL" value="inventory">
				<c:param name="mode" value="DELETE"></c:param>
				<c:param name="id" value="${inventory.id }"></c:param>
			</c:url>

			<c:url var="addCartURL" value="inventory">
				<c:param name="mode" value="ADD_CART"></c:param>
				<c:param name="id" value="${inventory.id }"></c:param>
			</c:url>

			<c:if test="${fn:contains(inventory.category,'Drink') }">
				<div class="div1">
					<img src="images/${inventory.image }" alt="blabla">
					<h4>${inventory.name }</h4>
					<h5>Price : ${inventory.price } MMK</h5>
					<p id="p1">${inventory.description }</p>
					<p id="p2">Instock : ${inventory.instock }</p>
					<c:choose>
						<c:when test="${fn:contains(user.role,'ROLE_ADMIN') }">
							<div id="div1" align="left">
								<a class="a1" href="${updateURL }">Update</a>
							</div>
							<div id="div2" align="right">
								<a class="a2" href="${deleteURL }" onclick="return myConfirm();">Delete</a>
							</div>
						</c:when>
						<c:otherwise>
							<a class="a3" href="${addCartURL }">Add Cart</a>
						</c:otherwise>
					</c:choose>

				</div>
			</c:if>
		</c:forEach>

		<h2>Fruits</h2>
		<c:forEach var="inventory" items="${inventoryList }">
			<c:url var="updateURL" value="inventory">
				<c:param name="mode" value="LOAD"></c:param>
				<c:param name="id" value="${inventory.id }"></c:param>
			</c:url>

			<c:url var="deleteURL" value="inventory">
				<c:param name="mode" value="DELETE"></c:param>
				<c:param name="id" value="${inventory.id }"></c:param>
			</c:url>

			<c:url var="addCartURL" value="inventory">
				<c:param name="mode" value="ADD_CART"></c:param>
				<c:param name="id" value="${inventory.id }"></c:param>
			</c:url>

			<c:if test="${fn:contains(inventory.category,'Fruit') }">
				<div class="div1">
					<img src="images/${inventory.image }" alt="blabla">
					<h4>${inventory.name }</h4>
					<h5>Price : ${inventory.price } MMK</h5>
					<p id="p1">${inventory.description }</p>
					<p id="p2">Instock : ${inventory.instock }</p>
					<c:choose>
						<c:when test="${fn:contains(user.role,'ROLE_ADMIN') }">
							<div id="div1" align="left">
								<a class="a1" href="${updateURL }">Update</a>
							</div>
							<div id="div2" align="right">
								<a class="a2" href="${deleteURL }" onclick="return myConfirm();">Delete</a>
							</div>
						</c:when>
						<c:otherwise>
							<a class="a3" href="${addCartURL }">Add Cart</a>
						</c:otherwise>
					</c:choose>

				</div>
			</c:if>
		</c:forEach>

		<h2>Cosmetics</h2>
		<c:forEach var="inventory" items="${inventoryList }">
			<c:url var="updateURL" value="inventory">
				<c:param name="mode" value="LOAD"></c:param>
				<c:param name="id" value="${inventory.id }"></c:param>
			</c:url>

			<c:url var="deleteURL" value="inventory">
				<c:param name="mode" value="DELETE"></c:param>
				<c:param name="id" value="${inventory.id }"></c:param>
			</c:url>

			<c:url var="addCartURL" value="inventory">
				<c:param name="mode" value="ADD_CART"></c:param>
				<c:param name="id" value="${inventory.id }"></c:param>
			</c:url>

			<c:if test="${fn:contains(inventory.category,'Cosmetic') }">
				<div class="div1">
					<img src="images/${inventory.image }" alt="blabla">
					<h4>${inventory.name }</h4>
					<h5>Price : ${inventory.price } MMK</h5>
					<p id="p1">${inventory.description }</p>
					<p id="p2">Instock : ${inventory.instock }</p>
					<c:choose>
						<c:when test="${fn:contains(user.role,'ROLE_ADMIN') }">
							<div id="div1" align="left">
								<a class="a1" href="${updateURL }">Update</a>
							</div>
							<div id="div2" align="right">
								<a class="a2" href="${deleteURL }" onclick="return myConfirm();">Delete</a>
							</div>
						</c:when>
						<c:otherwise>
							<a class="a3" href="${addCartURL }">Add Cart</a>
						</c:otherwise>
					</c:choose>

				</div>
			</c:if>
		</c:forEach>

	</div>
	<c:import url="common/footer.html" />
	<script>
		function myConfirm() {
			var result = confirm("Are you sure you want to delete?");
			if (result == true) {
				return true;
			} else {
				return false;
			}
		}
	</script>

</body>
</html>
