<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="lunchVote" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Lunch Vote App</title>
    <jsp:include page="fragments/headTag.jsp"/>
</head>
<body>
<c:set var="isNew" value="${empty userTo.id}"/>
<jsp:include page="fragments/navbar.jsp"/>

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
                            <form:form commandName="userTo" action="${isNew ? 'register' : 'profile'}" method="post"
                                       role="form"
                                       style="display: block;">
                                <lunchVote:inputField name="id" inputType="hidden"/>

                                <lunchVote:inputField name="name" placeholder="Username"/>

                                <lunchVote:inputField name="email" placeholder="Email"/>

                                <lunchVote:inputField name="password" inputType="password" placeholder="Password"/>

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