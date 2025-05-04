<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Music App - <c:out value="${labels['title']}"/></title>
    <style>
        :root {
            --primary-color: #4CAF50;
            --secondary-color: #2E7D32;
            --accent-blue: #2196F3;
            --accent-orange: #FF9800;
            --light-color: #f8f9fa;
            --dark-color: #343a40;
            --border-radius: 8px;
            --box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            color: #333;
        }

        .container {
            width: 90%;
            max-width: 600px;
            background: white;
            padding: 40px;
            border-radius: var(--border-radius);
            box-shadow: var(--box-shadow);
            text-align: center;
        }

        .welcome-message {
            margin-bottom: 30px;
        }

        .user-name {
            color: var(--primary-color);
            font-weight: bold;
            font-size: 1.5em;
        }

        .button-container {
            display: flex;
            justify-content: center;
            gap: 20px;
            margin-top: 40px;
            flex-wrap: wrap;
        }

        .btn {
            padding: 15px 30px;
            border: none;
            border-radius: var(--border-radius);
            font-size: 16px;
            font-weight: 500;
            cursor: pointer;
            transition: all 0.3s ease;
            text-decoration: none;
            display: inline-block;
            min-width: 180px;
        }

        .btn-albums {
            background-color: var(--accent-blue);
            color: white;
        }

        .btn-artists {
            background-color: var(--accent-orange);
            color: white;
        }

        .btn-logout {
            background-color: #FF5733; /* Color para el botón de logout */
            color: white;
        }

        .btn:hover {
            transform: translateY(-3px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
        }

        .btn-albums:hover {
            background-color: #0b7dda;
        }

        .btn-artists:hover {
            background-color: #e68a00;
        }

        .btn-logout:hover {
            background-color: #cc4e2d; /* Color hover para el logout */
        }

        .app-title {
            color: var(--primary-color);
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="app-title">Music App</h1>
    <form action="loggedUser" method="get">
        <label for="lang"><c:out value="${labels['label.language']}"/>:</label>
        <select name="lang" id="lang" onchange="this.form.submit()">
            <option value="ca" ${lang == 'ca' ? 'selected' : ''}>Català</option>
            <option value="es" ${lang == 'es' ? 'selected' : ''}>Español</option>
            <option value="en" ${lang == 'en' ? 'selected' : ''}>English</option>
        </select>
    </form>
    <div class="welcome-message">
        <h2><c:out value="${labels['welcome']}"/>, <span class="user-name"><%= session.getAttribute("user") %></span></h2>
        <p><c:out value="${labels['question']}"/></p>
    </div>

    <div class="button-container">
        <a href="albumlists?lang=${param.lang}" class="btn btn-albums"><c:out value="${labels['view.albums']}"/></a>
        <a href="artistlist?lang=${param.lang}" class="btn btn-artists"><c:out value="${labels['view.artists']}"/></a>
        <a href="favouritesAlbum?lang=${param.lang}" class="btn btn-artists"><c:out value="${labels['view.favourites']}"/></a>
    </div>

    <!-- Botón de Logout -->
    <div class="button-container">
        <a href="logout" class="btn btn-logout"><c:out value="${labels['logout']}"/></a>
    </div>

    <br><br>

</div>
</body>
</html>
