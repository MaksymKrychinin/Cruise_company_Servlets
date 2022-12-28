<%@ taglib prefix="fml" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<header>
    <script src="../js/slide.js"></script>
    <script src="../js/formOpenClose.js"></script>
    <script src="../js/RegistrationForm.js"></script>
    <link rel="stylesheet" href="../css/stylesheet.css">
    <h4 style="cursor:pointer"><a onclick="openSlideBar()"><fml:message key="text.menu"></fml:message></a></h4>
    <img class="logo_small" src="../img/logo.jpg" alt="logo">
    <c:if test="${user==null}">
        <h4 style="cursor:pointer"><a onclick="showForm('signIn')"><fml:message key="text.signIn"></fml:message></a>
        </h4>
        <h4 style="cursor:pointer"><a onclick="showForm('register')"><fml:message key="text.register"></fml:message></a>
        </h4>
        <div class="hidden">
            <a class="closeButtonForm" onclick="closeForm('signIn')">X</a>
            <form action="/AuthServlet" method="post" class="openForm" id="signIn">
                <div class="formElement">
                    <label><fml:message key="text.email"></fml:message></label>
                    <input type="text" name="email" required>
                    <small></small>
                </div>
                <div class="formElement">
                    <label><fml:message key="text.password"></fml:message></label>
                    <input type="password" name="password" minlength="8" required>
                    <small></small>
                </div>
                <button type="button" onclick="submitLoginForm()">Sign In</button>
            </form>
        </div>
        <div class="hidden">
            <a class="closeButtonForm" onclick="closeForm('register')">X</a>
            <form action="/RegisterServlet" method="post" class="openForm" id="register">
                <div class="formElement">
                    <label><fml:message key="text.email"></fml:message></label>
                    <input type="text" name="email" minlength="5" required>
                    <small></small>
                </div>
                <div class="formElement">
                    <label><fml:message key="text.password"></fml:message></label>
                    <input type="password" name="password" minlength="8" required>
                    <small></small>
                </div>
                <div class="formElement">
                    <label><fml:message key="text.surname"></fml:message></label>
                    <input type="text" name="surname" required>
                    <small></small></div>
                <div class="formElement">
                    <label><fml:message key="text.name"></fml:message></label>
                    <input type="text" name="name" required>
                    <small></small>
                </div>
                <div class="formElement">
                    <label><fml:message key="text.date"></fml:message></label>
                    <input type="date" name="date" min="1900-01-01" max="2023-01-01" required>
                    <small></small>
                </div>
                <div class="formElement">
                    <label><fml:message key="text.yourNumber"></fml:message></label>
                    <input type="tel" id="phone" onkeyup="phoneMask()" name="phoneNumber"
                           pattern="\+[0-9]{2}\([0-9]{3}\)-[0-9]{3}-[0-9]{2}-[0-9]{2}"
                           placeholder="+38(099)-453-22-78" required>
                    <small></small>
                </div>
                <div class="gender"><p><fml:message key="text.yourGender"></fml:message>:</p>
                    <input type="radio" name="gender" value="Male" required>
                    <label><fml:message key="input.gender.male"></fml:message></label>
                    <input type="radio" name="gender" value="Female">
                    <label><fml:message key="input.gender.famale"></fml:message></label>
                    <input type="radio" name="gender" value="Other">
                    <label><fml:message key="input.gender.other"></fml:message></label>
                    <small></small>
                </div>

                <button type="button" onclick="submitRegisterForm()">
                    <fml:message key="button.register"></fml:message></button>
            </form>
        </div>
    </c:if>
    <c:if test="${user!=null}">
        <h4 style="cursor:pointer"><a href="/UserOrders"><fml:message key="text.myOrders"></fml:message></a></h4>
        <h4 style="cursor: pointer"><a href="/Logout"><fml:message key="text.logout"></fml:message></a></h4>
    </c:if>
    <a href="/Lang?lang=en">EN</a>
    <a href="/Lang?lang=ua">UA</a>
    <c:if test="${user.getRole()=='admin'}">
        <h4 style="cursor:pointer"><a href="../adminPanel.jsp"><fml:message key="text.adminPanel"></fml:message></a></h4>
    </c:if>
    <div id="slideBarMenu" class="slideBar">
        <a href="javascript:void(0)" class="closeButton" onclick="closeSlideBar()">X</a>
        <a href="../index.jsp"><fml:message key="header.home"></fml:message></a>
    </div>
</header>