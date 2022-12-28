<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fml" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fml:message key="title.adminPanel"></fml:message></title>
    <link rel="stylesheet" href="css/stylesheet.css">
</head>
<body>
<jsp:include page="jspDinamic/header.jsp"></jsp:include>
<div class="buttonsCenter">
    <form action="/AllUsersOrders" method="GET">
        <button type="submit">AllUsersOrders</button>
    </form>
    <a href="addCruiseForm.jsp">AddCruiseForm</a>
</div>

<c:if test="${user.getRole()=='admin'}">
    <c:if test="${usersOrdersList!=null}">
        <div style="height: 500px;overflow-y: scroll">
            <c:forEach var="userOrder" items="${usersOrdersList}">
                <div class="userOrder">
                    <p>${userOrder.getUser().getSurname()}</p>
                    <p>${userOrder.getUser().getName()}</p>
                    <p>${userOrder.getUser().getDateOfBirthday()}</p>
                    <p>${userOrder.getUser().getGender()}</p>
                    <p>${userOrder.getCruiseShip().getRouteFrom()}</p>
                    <p>${userOrder.getCruiseShip().getRouteTo()}</p>
                    <p>${userOrder.getCruiseShip().getStartDate()}</p>
                    <p>${userOrder.getCruiseShip().getEndDate()}</p>
                    <p>${userOrder.getStatus()}</p>
                    <img src="/passports/${userOrder.getFrontPassport()}">
                    <img src="/passports/${userOrder.getBackPassport()}">
                    <form action="/ChangeStatus" method="POST">
                        <input type="hidden" name="userOrderId" value="${userOrder.getIdUserOrder()}">
                        <button type="submit">ChangeStatusToPaid</button>
                    </form>
                </div>
            </c:forEach>
        </div>
        <div class="pagination-table">
        <table class="pagination-table">
            <tr>
                <c:if test="${page != 1}">
                    <td><a href="/AllUsersOrders?page=${page - 1}"><-</a></td>
                </c:if>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${page eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="/AllUsersOrders?page=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${page lt noOfPages}">
                    <td><a href="/AllUsersOrders?page=${page + 1}">-></a></td>
                </c:if>
            </tr>
        </table>
        </div>
    </c:if>
</c:if>
<jsp:include page="jspDinamic/footer.jsp"></jsp:include>
</body>
</html>
