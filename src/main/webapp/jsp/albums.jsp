<%--
  Created by IntelliJ IDEA.
  User: llere
  Date: 25/03/2025
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Albums</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .container {
            width: 60%;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
            table-layout: fixed; /* Añadido para controlar el ancho de las columnas */
        }
        td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
            vertical-align: middle; /* Alinea verticalmente el contenido */
        }
        td:nth-child(1) {
            width: 65%; /* Ancho para la columna del título */
        }
        td:nth-child(2) {
            width: 20%; /* Ancho fijo para la columna del artista */
            padding-left: 20px; /* Espaciado consistente */
        }
        td:nth-child(3) {
            width: 15%; /* Ancho para la columna del botón */
            text-align: right;
        }
        h3, h4 {
            margin: 0;
            color: #333;
        }
        h4 {
            font-weight: normal; /* Opcional: para diferenciar del título */
        }
        .delete-btn {
            background-color: #ff4d4d;
            color: white;
            border: none;
            padding: 8px 15px;
            border-radius: 5px;
            cursor: pointer;
            transition: 0.3s;
        }
        .delete-btn:hover {
            background-color: #cc0000;
        }
        .favorite-btn {
            background-color: #afa24c;
            color: white;
            border: none;
            padding: 8px 15px;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            margin-right: 10px;
            transition: 0.3s;
        }
        .favorite-btn:hover {
            background-color: #8e8738;
        }
    </style>
</head>
<body>
<h1><c:out value="${labels['page.title']}"/></h1>

<div class="container">
    <h2><c:out value="${labels['product.list']}"/></h2>
    <form action="albums-lang-support" method="post">
        <label for="lang"><c:out value="${labels['label.language']}"/></label>
        <select name="lang" id="lang" onchange="this.form.submit()">
            <option value="en" <c:if test="${sessionScope.lang == 'en'}">selected</c:if>>English</option>
            <option value="es" <c:if test="${sessionScope.lang == 'es'}">selected</c:if>>Español</option>
            <option value="ca" <c:if test="${sessionScope.lang == 'ca'}">selected</c:if>>Català</option>

        </select>
    </form>
    <a href="loggedUser"><c:out value="${labels['back.home']}"/></a>
    <c:forEach var="albums" items="${albums}">
        <table>
            <tr>
                <td><h3><c:out value="${albums.title}"/></h3></td>
                <td><h4><c:out value="${albums.artist.name}"/></h4></td>
                <td>
                    <a href="addFavoriteAlbum?albumId=${albums.albumId}" class="favorite-btn">
                    <c:out value="${labels['button.favorite']}"/>
                    </a>
                    <a href="deletealbum?albumId=${albums.albumId}" class="delete-btn">
                        <c:out value="${labels['button.delete']}"/>
                    </a>
                </td>
            </tr>
        </table>
    </c:forEach>
</div>

<br><br>

</body>
</html>
