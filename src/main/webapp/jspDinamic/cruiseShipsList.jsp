<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<c:choose>
    <c:when test="${cruiseShipsList!=null}">
        <div class="cruiseShipsList">
            <c:forEach items="${cruiseShipsList}" var="cruiseShip">
                <div class="cruiseShip">
                    <div>
                        <p>RouteFrom</p>
                        <p>${cruiseShip.getRouteFrom()}</p>
                        <p>RouteTo</p>
                        <p>${cruiseShip.getRouteTo()}</p>
                    </div>
                    <div>
                        <p>Start Date</p>
                        <p>${cruiseShip.getStartDate()}</p>
                        <p>End Date</p>
                        <p>${cruiseShip.getEndDate()}</p>
                    </div>
                    <div>
                        <p>Capacity</p>
                        <p>${cruiseShip.getCapacity()}</p>
                        <p>Number of vizited Ports</p>
                        <p>${cruiseShip.getNumberOfVisitedPorts()}</p>
                    </div>
                    <div>
                        <p>Ordered Seats</p>
                        <p>${cruiseShip.getOrderedSeats()}</p>
                        <a href="/CruiseById?id=${cruiseShip.getId()}">Buy</a>
                    </div>
                </div>
            </c:forEach>
        </div>
        <table class="pagination-table">
            <tr>
                <c:if test="${page != 1}">
                    <td><a href="/${cruiseType}?page=1"><-</a></td>
                </c:if>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${page eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="/${cruiseType}?page=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${page lt noOfPages}">
                    <td><a href="/${cruiseType}?page=${noOfPages}">-></a></td>
                </c:if>
            </tr>
        </table>
    </c:when>
</c:choose>
