<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fml" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fml:message key="title.cruiseShipBuy"></fml:message></title>
    <link rel="stylesheet" href="css/stylesheet.css">
</head>
<body>
<jsp:include page="jspDinamic/header.jsp"></jsp:include>
<div class="cruiseShipBuy">
    <div class="userOrder"><p>Capacity</p>
        <p>${cruiseShip.getCapacity()}</p>
        <p>RouteFrom</p>
        <p>${cruiseShip.getRouteFrom()}</p>
        <p>RouteTo</p>
        <p>${cruiseShip.getRouteTo()}</p>
        <p>Number of vizited Ports</p>
        <p>${cruiseShip.getNumberOfVisitedPorts()}</p>
        <p>Start Date</p>
        <p>${cruiseShip.getStartDate()}</p>
        <p>End Date</p>
        <p>${cruiseShip.getEndDate()}</p>
        <p>Ordered Seats</p>
        <p>${cruiseShip.getOrderedSeats()}</p></div>

    <form action="/BuyCruise" enctype="multipart/form-data" method="POST">
        <input name="id" type="hidden" value="${cruiseShip.id}">
        <p><fml:message key="text.frontPassport"></fml:message></p>
        <input type="file" name="frontPassport" accept="image/*">
        <p><fml:message key="text.backPassport"></fml:message></p>
        <input type="file" name="backPassport" accept="image/*">
        <button type="submit">Buy</button>
    </form>
</div>
<jsp:include page="jspDinamic/footer.jsp"></jsp:include>
</body>
</html>
