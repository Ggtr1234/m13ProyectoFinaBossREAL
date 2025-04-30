<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><c:out value="${title}"/></title>
</head>
<body>
<h1><c:out value="${labels['header']}"/></h1>

<table border="1">
    <tr>
        <th><c:out value="${labels['column.id']}"/></th>
        <th><c:out value="${labels['column.name']}"/></th>
        <th><c:out value="${labels['column.price']}"/></th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td><c:out value="${product.id}"/></td>
            <td><c:out value="${product.name}"/></td>
            <td><c:out value="${product.price}"/></td>
        </tr>
    </c:forEach>
</table>

<br>
<br>
<form action="products-lang-support" method="post">
    <label for="lang">Selecciona idioma:</label>
    <select name="lang" id="lang" onchange="this.form.submit()">
        <option value="ca">Català</option>
        <option value="es">Español</option>
        <option value="en">English</option>
        <option value="fr">Français</option>
    </select>
</form>
</body>
</html>