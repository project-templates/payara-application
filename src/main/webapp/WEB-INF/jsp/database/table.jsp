<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Tables ${model.tableName}</title>
    </head>
    <body>
        <h1>${model.tableName}</h1>

        <%@include file="/WEB-INF/jsp/database/menu.jsp" %>

        <table border="1">
            <tr>
                <c:forEach items="${model.columnNames}" var="columnName">
                    <th>${columnName}</th>
                </c:forEach>
            </tr>
            <c:forEach items="${model.records}" var="record">
                <tr>
                    <c:forEach items="${model.columnNames}" var="columnName">
                        <td>${record[columnName]}</td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
