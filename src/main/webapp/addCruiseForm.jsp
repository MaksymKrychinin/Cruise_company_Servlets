<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddCruiseForm</title>
    <script src="js/validation.js"></script>
</head>
<body onload="dateMinSetter()">
<jsp:include page="jspDinamic/header.jsp"></jsp:include>
<div class="addCruiseForm">
    <form method="post" action="/AddCruise" class="addCruiseForm">
        <p>capacity</p>
        <input type="number" name="capacity" min="1" step="1" required>
        <p>routeFrom</p>
        <input type="text" name="routeFrom" minlength="1" required>
        <p>routeTo</p>
        <input type="text" name="routeTo" minlength="1" required>
        <p>numberOfVisitedPorts</p>
        <input type="number" name="numberOfVisitedPorts" min="0" step="1" required>
        <p>startDate</p>
        <input type="date" name="startDate" class="date" required>
        <p>endDate</p>
        <input type="date" name="endDate" class="date" required>
        <button type="submit">ADD CRUISE</button>
    </form>
</div>
<jsp:include page="jspDinamic/footer.jsp"></jsp:include>
</body>
</html>
