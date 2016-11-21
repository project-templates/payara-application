<%@ taglib prefix="c" url="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Database Tables</title>
    </head>
    <body>
        <c:forEach items="${requestScope.model.tableNames}" var="tableName">
            <h1>${tableName}</h1>
        </c:forEach>
    </body>
</html>
