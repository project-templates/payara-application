<%@include file="/WEB-INF/jsp/taglib.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Database Migration</title>
    </head>
    <body>
        <h1>Database Migration</h1>

        <%@include file="/WEB-INF/jsp/database/menu.jsp" %>

        <form action="migration" method="POST">
          <input type="submit" id="migrate" name="migrate" value="Migrate" />
          <input type="submit" id="clean" name="clean" value="Clean" />
          <input type="submit" id="cleanMigrate" name="cleanMigrate" value="Clean-Migrate" />
        </form>

        <script>
          var buttons = document.getElementsByTagName('input');

          for (var i=0; i<buttons.length; i++) {
              buttons[i].addEventListener('click', function(e) {
                  if (!confirm('実行しますか？')) {
                      e.preventDefault();
                  }
              });
          }
        </script>
    </body>
</html>
