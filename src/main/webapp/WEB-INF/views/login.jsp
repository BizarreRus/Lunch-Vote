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
        <div class="navbar-header">
            <a href="${pageContext.request.contextPath}" class="navbar-brand">Lunch Vote App</a>
        </div>
        <ul class="nav navbar-nav navbar-right hidden-xs">
            <li>
                <a href="${pageContext.request.contextPath}/register" methods="post">
                    <span class="glyphicon glyphicon-user"></span> Sign Up
                </a>
            </li>
        </ul>
    </div>
</nav>

<div class="container">

    <p class="title-font">Welcome To Lunch Vote App</p>

    <hr/>
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-12">
                            <span class="active" id="login-form-link">Sign in</span>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="login-form" name="form_login" action="spring_security_check" method="post"
                                  role="form"
                                  style="display: block;">
                                <div class="form-group">
                                    <input type="text" name="username" class="form-control"
                                           placeholder="Email" value="">
                                </div>

                                <div class="form-group">
                                    <input type="password" name="password"
                                           class="form-control" placeholder="Password">
                                </div>

                                <div class="error-message">
                                    <c:if test="${error}">
                                        Invalid email or password
                                    </c:if>
                                </div>
                                <br/>

                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="submit"
                                                   class="form-control btn btn-login" value="submit">
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

    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="col-lg-12">
                <div class="form-group">
                    <div class="row">
                        <div class=col-xs-6>
                            <button type="submit" class="btn btn-lg btn-primary center-block"
                                    onclick="setCredentials('user@yandex.ru', 'password')">
                                Sign In like User
                            </button>
                        </div>
                        <div class="col-xs-6">
                            <button type="submit" class="btn btn-lg btn-success center-block"
                                    onclick="setCredentials('admin@gmail.com', 'admin')">
                                Sign In like Admin
                            </button>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<br/>
<div class="container">
    <div class="row">
        <div class="about-project">
            <p style="text-indent: 5em;">
                Проект является системой голосования для принятия решения, где пообедать.
                С регистрацией/авторизацией и интерфейсом на основе ролей (USER, ADMIN),
                Администратор может создавать/редактировать/удалять рестораны/меню/блюда,
                а пользователь - управлять своим профилем и голосовать за заведение,
                которое ему понравилось(засчитывается один голос за день, причём сделать/изменить выбор будет возможно
                только до 11:00AM). Список ресторанов обновляется каждый день администратором, который так же имеет
                возможность просматривать историю.
            </p>
        </div>
    </div>
    <hr/>
    <div class="row">
        <div class="about-project">
            <p>
                Стек технологий: Spring MVC, Spring Security, Hibernate ORM, Hibernate Validator, Logback, Json Simple,
                Apache Tomcat, HSQLDB, JUnit, jQuery, Bootstrap, DataTimePicker plugin.
            </p>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>

<script type="text/javascript">
    function setCredentials(username, password) {
        $('input[name="username"]').val(username);
        $('input[name="password"]').val(password);
    }
</script>
</body>
</html>
