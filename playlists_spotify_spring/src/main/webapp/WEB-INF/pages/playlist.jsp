<%-- 
    Document   : playlist
    Created on : 01-mar-2017, 14:27:15
    Author     : amacdong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <h1>Nombre de la Playlist: ${name}</h1>
        <p>Id: ${id}</p>
        <p>Descripción: ${description}</p>
        <a href="${href}">Enlace</a> 
        <p>Playlist colaborativa: ${collaborative}</p>
        
        <img src="${url}" style="width:250px;height:250px;">
        
        
    </body>
</html>

