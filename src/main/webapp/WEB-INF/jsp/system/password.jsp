<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Password Hash</title>
        <style>
          label {
            display: inline-block;
            width: 140px;
          }
          
          input[type="text"] {
            width: 800px;
          }
        </style>
    </head>
    <body>
        <h1>Password Hash</h1>
        
        <form method="GET" action="password">
            <div>
                <label for="password">Password (Plain) : </label>
                <input type="text" id="value" name="value" />
            </div>
            <div>
                <label for="hashed">Hashed Password :</label>
                <input type="text" id="hashed" value="${requestScope.hashed}" />
            </div>
            <input type="submit" value="Hash" />
        </form>
    </body>
</html>
