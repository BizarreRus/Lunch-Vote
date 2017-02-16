<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lunch Vote App</title>
    <jsp:include page="fragments/headTag.jsp"/>
</head>
<body>
<c:set var="isNew" value="${empty userTo.id}"/>
<jsp:include page="fragments/${isNew ? 'simpleNavbar' : 'navbar'}.jsp"/>

<div class="container">
    <p class="title-font"><c:out value="${isNew ? 'Sign Up' : 'Profile'} page"/></p>
    <hr/>

    <jsp:useBean id="userTo" type="net.bizare.lunchvoteapp.to.UserTo" scope="request"/>

    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-12">
                            <span class="active">Hello<c:if test="${!empty userTo.name}">, ${userTo.name}</c:if>!</span>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form:form commandName="userTo" action="register" method="post" role="form"
                                       style="display: block;">
                                <form:input path="id" type="hidden" name="id" value="${userTo.id}"/>

                                <div class="form-group">
                                    <form:input path="name" type="text" cssClass="form-control"
                                                placeholder="Username" value="${userTo.name}"/>
                                    <form:errors path="name" cssClass="error-message"/>

                                </div>

                                <div class="form-group">
                                    <form:input path="email" type="text" cssClass="form-control"
                                                placeholder="Email" value="${userTo.email}"/>
                                    <form:errors path="email" cssClass="error-message"/>
                                </div>

                                <div class="form-group">
                                    <form:input path="password" type="password" cssClass="form-control"
                                                placeholder="Password" value="${userTo.password}"/>
                                    <form:errors path="password" cssClass="error-message"/>
                                </div>

                                <div class="form-group">
                                    <div class="row">
                                        <div class=col-xs-6>
                                            <input type="submit" name="submit" tabindex="4"
                                                   class="form-control btn btn-register"
                                                   value="<c:out value="${isNew ? 'Create' : 'Edit'}"/>">
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