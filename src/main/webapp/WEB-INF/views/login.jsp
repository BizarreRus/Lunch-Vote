<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome To Lunch Vote App</title>

    <jsp:include page="fragments/headTag.jsp"/>
</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top custom-shadow">
    <div class="container">
        <a href="${pageContext.request.contextPath}" class="navbar-brand">Lunch Vote App</a>
    </div>
</nav>

<div class="container">

    <p class="title-font">Welcome To Lunch Vote App</p>

    <%--//todo little validation from spring security--%>
    <p class="lead text-center">
        <c:if test="${not empty error}">
            ${error}
        </c:if>
    </p>

    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-6">
                            <a href="#" class="active" id="login-form-link">Login</a>
                        </div>
                        <div class="col-xs-6">
                            <a href="#" id="register-form-link">Register</a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="login-form" name="form_login" action="login" method="post" role="form"
                                  style="display: block;">
                                <div class="form-group">
                                    <input type="text" name="user_login" tabindex="1" class="form-control"
                                           placeholder="Username" value="">
                                </div>
                                <div class="form-group">
                                    <input type="password" name="user_password" tabindex="2"
                                           class="form-control" placeholder="Password">
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="submit" tabindex="4"
                                                   class="form-control btn btn-login" value="submit">
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <form id="register-form" action="register" method="post"
                                  role="form" style="display: none;">
                                <div class="form-group">
                                    <input type="text" name="username" tabindex="1" class="form-control"
                                           placeholder="Username" value="">
                                </div>
                                <div class="form-group">
                                    <input type="email" name="email" tabindex="1" class="form-control"
                                           placeholder="Email Address" value="">
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" tabindex="2"
                                           class="form-control" placeholder="Password">
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="register-submit"
                                                   tabindex="4" class="form-control btn btn-register"
                                                   value="Register Now">
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br/>
    <br/>
    <br/>
    <p class="lead">
            Проект является системой голосования для принятия решения, где пообедать.
        С регистрацией/авторизацией и интерфейсом на основе ролей (USER, ADMIN),
        Администратор может создавать/редактировать/удалять рестораны/меню/блюда,
        а пользователь - управлять своим профилем и голосовать за заведение,
        которое ему понравилось(Засчитывается один голос на день, причём сделать/изменить выбор будет возможно только до 11:00AM).
        Список ресторанов обновляется каждый день.
        Администратор так же имеет возможность просматривать историю.

    </p>
    <p class="lead">
        Стек технологий: Spring MVC, Spring Security, Spring Test, Hibernate ORM, Hibernate Validator, Logback, Apache
        Tomcat, HSQLDB, JUnit, jQuery.
    </p>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
