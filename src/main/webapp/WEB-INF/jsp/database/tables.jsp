<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Database Tables</title>
    </head>
    <body>
        <h1>Tables</h1>
        <ul>
            <c:forEach items="${requestScope.model.tableNames}" var="tableName">
                <li><a href="tables/${tableName}">${tableName}</a></li>
            </c:forEach>
        </ul>
    </body>
</html>
