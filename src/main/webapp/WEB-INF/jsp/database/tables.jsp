<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Database Tables</title>
    </head>
    <body>
        <h1>Tables</h1>

        <%@include file="/WEB-INF/jsp/database/menu.jsp" %>

        <ul>
            <c:forEach items="${requestScope.model.tables}" var="table">
                <li><a href="tables/${table.name}">${table.schema}.${table.name}</a></li>
            </c:forEach>
        </ul>
    </body>
</html>
