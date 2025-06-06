<%--
  Created by IntelliJ IDEA.
  User: llere
  Date: 25/03/2025
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
    </style>
</head>
<body>
    <div class="container">
        <h2><c:out value="${labels['titolfavoritos']}"/></h2>
        <form action="favourites-lang-support" method="post">
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
                    <td><h3>${albums.title}</h3></td>
                    <td><h4>${albums.artist.name}</h4></td>
                    <td><button class="delete-btn" onclick="deleteFavorite(${albums.albumId})">Eliminar</button></td>
                </tr>
            </table>
        </c:forEach>
    </div>

    <script>
        const userId = ${sessionScope.userId}; // Evalúa en servidor, visible en cliente

        function deleteFavorite(albumID) {
            if (confirm("¿Estás seguro de que quieres eliminar este álbum de favoritos?")) {
                fetch(`DeleteFavoriteServlet?albumId=` + albumID + `&userId=${userId}`, {
                    method: 'GET'
                }).then(response => {
                    if (response.ok) {
                        location.reload();
                    } else {
                        alert(response.url);
                    }
                }).catch(error => console.error("Error:", error));
            }
        }
    </script>
</body>
</html>
