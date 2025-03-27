
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Music App - Inicio</title>
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

        .app-title {
            color: var(--primary-color);
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="app-title">Music App</h1>
    <div class="welcome-message">
        <h2>Bienvenido, <span class="user-name"><%= session.getAttribute("user") %></span></h2>
        <p>¿Qué te gustaría explorar hoy?</p>
    </div>

    <div class="button-container">
        <a href="../albumlists" class="btn btn-albums">Ver Álbumes</a>
        <a href="../artistlist" class="btn btn-artists">Ver Artistas</a>
        <a href="../favouritesAlbum" class="btn btn-artists">Ver favoritos</a>
    </div>
</div>
</body>
</html>