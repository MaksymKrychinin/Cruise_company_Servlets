<%@ taglib prefix="fml" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fml:message key="title.userOrders"></fml:message></title>
    <link rel="stylesheet" href="css/stylesheet.css">
</head>
<body>
<jsp:include page="jspDinamic/header.jsp"></jsp:include>
<c:choose>
    <c:when test="${usersOrderList!=null}">
        <div class="usersOrderList">
            <c:forEach items="${usersOrderList}" var="userOrder">
                <div class="usersOrder">
                    <p>${userOrder.getCruiseShip().getRouteFrom()}</p>
                    <p>${userOrder.getCruiseShip().getRouteTo()}</p>
                    <p>${userOrder.getCruiseShip().getStartDate()}</p>
                    <p>${userOrder.getCruiseShip().getEndDate()}</p>
                    <p>${userOrder.getStatus()}</p>
                </div>
            </c:forEach>
        </div>
        <table class="pagination-table">
            <tr>
                <c:if test="${page != 1}">
                    <td><a href="/UserOrders?page=${page - 1}"><-</a></td>
                </c:if>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${page eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="/UserOrders?page=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${page lt noOfPages}">
                    <td><a href="/UserOrders?page=${page + 1}">-></a></td>
                </c:if>
            </tr>
        </table>
    </c:when>
</c:choose>
<jsp:include page="jspDinamic/footer.jsp"></jsp:include>
</body>
</html>
