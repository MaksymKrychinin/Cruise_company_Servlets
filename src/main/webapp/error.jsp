<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="css/stylesheet.css">
</head>
<body>
<jsp:include page="jspDinamic/header.jsp"></jsp:include>
<div class="container">
    <p>${requestScope['javax.servlet.error.message']}</p>
    <c:if test="${requestScope['javax.servlet.error.message']==''}">
        <p><fmt:message key="error.unknown"/></p>
    </c:if>
</div>
<jsp:include page="jspDinamic/footer.jsp"></jsp:include>
</body>
</html>
