<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile page</title>
    <jsp:include page="fragments/headTag.jsp"/>
</head>
<body>
<jsp:include page="fragments/navbar.jsp"/>

<div class="container">
    <p class="title-font">Profile page</p>

    <jsp:useBean id="user" type="net.bizare.lunchvoteapp.model.User" scope="request"/>

    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-12">
                            <span class="active"><c:out
                                    value="Hello, ${user.name}!"/></span>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form:form name="form_login" action="profile" method="post" role="form"
                                       style="display: block;">
                                <input type="hidden" name="id" value="${user.id}">

                                <div class="form-group">
                                    <input type="text" name="username" tabindex="1" class="form-control"
                                           placeholder="Username" value="${user.name}">
                                </div>

                                <div class="form-group">
                                    <input type="password" name="password" tabindex="2" class="form-control"
                                           placeholder="Password" value="${user.password}">
                                </div>

                                <div class="form-group">
                                    <input type="email" name="email" tabindex="3" class="form-control"
                                           placeholder="text" value="${user.email}">
                                </div>


                                <div class="form-group">
                                    <div class="row">
                                        <div class=col-xs-6>
                                            <input type="submit" name="submit" tabindex="4"
                                                   class="form-control btn btn-register" value="edit">
                                        </div>
                                        <div class="col-xs-6">
                                            <a href="restaurants" name="cancel" tabindex="5"
                                               class="form-control btn btn-login">cancel</a>
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>