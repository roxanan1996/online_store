<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Online Catalog</title>
<spring:url value="/resources/css/mystyle.css" var="mystyle" />
<spring:url value="/cardView/card/${refereerId}" var="back"
	htmlEscape="true" />
<link href="${mystyle}" rel="stylesheet" />
</head>

<body>
	<table width="993" border="0">
		<tr>
			<td width="251" height="96"><img
				src="<spring:url value="/resources/images/aglogo.jpg"/>" width="251"
				height="88" alt="AG Logo" /></td>
			<td class="right"><a href="${back}"><img
					src="<spring:url value="/resources/images/back.png"/>" width="80"
					height="88" alt="Shopping Cart" /></a></td>
		</tr>
	</table>
	<hr />
	<p>
		<p class="gnrl">You have these items in your cart:</p>
	</p>
	<c:if test="${!empty cartItems}">
		<table width="992" border="1" cellspacing="0">
			<tr>
				<td class="center1" width="66" align="center"><strong>Card
						ID</strong></td>
				<td class="center1" width="354" align="center"><strong>Title</strong></td>
				<td class="center1" width="153" align="center"><strong>Quantity</strong></td>
				<td class="center1" width="237" align="center"><strong>Total
						Cost</strong></td>
			</tr>
			<c:forEach items="${cartItems}" var="cartItem">
				<tr>
					<td class="center1" height="75" align="center">${cartItem.getCard().id}</td>
					<td class="center1" align="center">${cartItem.getCard().title}</td>
					<td class="center1" align="center">${cartItem.getQuantity()}</td>
					<td class="center1" align="center"><fmt:formatNumber
							type="number" maxFractionDigits="2"
							value="${cartItem.getCard().price * cartItem.getQuantity()}" /></td>
				</tr>
				<br />
			</c:forEach>
		</table>
		<br />
		<form:form action="/cartView/Checkout" method="POST" cssStyle="float:left;">
			<input type="image"
				src="<spring:url value="/resources/images/button_checkout.png"/>"
				width="150" height="40" alt="Checkout" />
			<input type="hidden" name="refereer" value="${refereerId}">
		</form:form>
		<form:form action="/cartView/EmptyCart" method="POST"
			cssStyle="float:left;position:relative;margin-left:20px;">
			<input type="image"
				src="<spring:url value="/resources/images/button_empty-cart.png"/>"
				width="150" height="40" alt="Checkout" />
			<input type="hidden" name="refereer" value="${refereerId}">
		</form:form>
	</c:if>

</body>
</html>