<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fml" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html>
<head>
    <title><fml:message key="title.home"></fml:message></title>
    <link rel="stylesheet" href="css/stylesheet.css">
    <script src="js/validation.js"></script>
</head>
<body onload="dateMinSetter()">
<jsp:include page="jspDinamic/header.jsp"></jsp:include>
<div class="block">
    <c:choose>
        <c:when test="${user!=null}">
            <div class="formsLine">
                <form action="/AllCruise" method="GET">
                    <p>Find all cruise</p>
                    <button type="submit"><fml:message key="text.allcruise"></fml:message></button>
                </form>
                <form action="/CruiseByDate" method="GET">
                    <p>Date filter: </p>
                    <input name="date" type="date" class="date"><br>
                    <button type="submit"><fml:message key="text.searchcruise"></fml:message></button>
                </form>
                <form action="/CruiseByDuration" method="GET">
                    <p>Duration filter:</p>
                    <input name="duration" type="number" min="1" step="1" value="1" required><br>
                    <button type="submit"><fml:message key="text.searchcruise"></fml:message></button>
                </form>
            </div>

            <jsp:include page="jspDinamic/cruiseShipsList.jsp"></jsp:include>
        </c:when>
        <c:otherwise>
            <h1><fml:message key="text.pleaseRegisterOrSignin"></fml:message></h1>
        </c:otherwise>
    </c:choose>
</div>
<jsp:include page="jspDinamic/footer.jsp"></jsp:include>
</body>
</html>