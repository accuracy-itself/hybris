<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
    <title>News List</title>
    <body>
        <h1>News List</h1>
     <ul>
     <c:forEach var="newsItem" items="${news}">
        <li>${newsItem.date}:${newsItem.headline}.${newsItem.content}</a></li>
      </c:forEach>
      </ul>
    </body>
</html>