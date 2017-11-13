<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>Online Catalog</title>
<spring:url value="/resources/css/mystyle.css" var="mystyle" />
<spring:url value="/cartView?refereer=${cardBean.getId()}" var="ToShoppingCart" htmlEscape="true" />
<link href="${mystyle}" rel="stylesheet" />
</head>

<body>
	<table width="993" border="0">
		<tr>
			<td width="251" height="96"><img
				src="<spring:url value="/resources/images/aglogo.jpg"/>" width="251"
				height="88" alt="AG Logo" /></td>
			<td class="right"><a href="${ToShoppingCart}"><img
					src="<spring:url value="/resources/images/shopping-cart.png"/>"
					width="80" height="88" alt="Shopping Cart" /></a></td>
		</tr>
	</table>
	<hr />
	<table width="997" height="399" border="0">
		<tr>
			<td width="358" class="center1"><img
				src="<spring:url value="${cardBean.getImageUrl()}"/>" width="400" height="500" alt=hname /></td>
			<td width="623" class="center1">
				<table width="500" height="233" border="0">
					<tr>
						<td class="center1">Title:</td>
						<td>${cardBean.getTitle()}</td>
					</tr>
					<tr>
						<td class="center1">Description:</td>
						<td>${cardBean.getDescription()}</td>
					</tr>
					<tr>
						<td class="center1">Season:</td>
						<td>${cardBean.getSeason()}</td>
					</tr>
					<tr>
						<td class="center1">Price :</td>
						<td>${cardBean.getPrice()}</td>
					</tr>
					<tr>
						<td class="center1">Stock :</td>
						<td>${stockBean.getQuantity()}</td>
					</tr>
				</table>
				<p>
					<form:form method="POST" modelAttribute="formBean">
						<table>
							<tr>
								<td>Enter quantity:</td>
								<td><form:input path="quantity" /></td>
								<td><form:errors path="quantity" cssStyle="color: #ff0000;" /></td>
								<td><input type="submit" name="submit" value="Add to cart"></td>
							</tr>
							<tr>
								<td></td>
								<td><form:hidden path="cardId" value="${cardBean.getId()}" /></td>
							</tr>
						</table>
					</form:form>
				</p>
			</td>
		</tr>
	</table>
</body>
</html>