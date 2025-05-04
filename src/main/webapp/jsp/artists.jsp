<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><c:out value="${labels['title']}"/></title>
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
            table-layout: fixed;
        }
        td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
            vertical-align: middle;
        }
        td:nth-child(1) {
            width: 65%;
        }
        td:nth-child(2) {
            width: 20%;
            padding-left: 20px;
        }
        td:nth-child(3) {
            width: 15%;
            text-align: right;
        }
        h2, h3, h4 {
            margin: 0;
            color: #333;
        }
        h4 {
            font-weight: normal;
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
    <h2><c:out value="${labels['header.artists']}"/></h2>
    <form action="artists-lang-support" method="post">
        <label for="lang"><c:out value="${labels['label.language']}"/>:</label>
        <select name="lang" id="lang" onchange="this.form.submit()">
            <option value="ca" <c:if test="${sessionScope.lang == 'ca'}">selected</c:if>>Català</option>
            <option value="es" <c:if test="${sessionScope.lang == 'es'}">selected</c:if>>Español</option>
            <option value="en" <c:if test="${sessionScope.lang == 'en'}">selected</c:if>>English</option>
        </select>
    </form>
    <a href="loggedUser"><c:out value="${labels['link.back']}"/></a>

    <c:forEach var="artists" items="${artists}">
        <table>
            <tr>
                <td><h3><c:out value="${artists.name}"/></h3></td>
                <td><h4><c:out value="${artists.id}"/></h4></td>
                <td><a href="deleteartist?ArtistId=${artists.id}" class="delete-btn">
                    <c:out value="${labels['button.delete']}"/></a></td>
            </tr>
        </table>
    </c:forEach>

    <br><br>

</div>
</body>
</html>
