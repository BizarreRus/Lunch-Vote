<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dish page</title>
    <jsp:include page="fragments/headTag.jsp"/>
</head>
<body>
<jsp:include page="fragments/navbar.jsp"/>

<div class="container">
    <c:set var="createOrUpdate" value="${empty dish.id ? 'Create' : 'Edit'}"/>
    <p class="title-font">Dish page</p>
    <hr/>

    <jsp:useBean id="dish" type="net.bizare.lunchvoteapp.model.Dish" scope="request"/>

    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-12">
                            <span class="active" id="login-form-link"><c:out
                                    value="${createOrUpdate}"/></span>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form:form id="login-form" name="form_login" action="restaurants/${restaurantId}/menus/${menuId}/dishes" method="post" role="form"
                                       style="display: block;" commandName="dish">
                                <input type="hidden" name="id" value="${dish.id}">

                                <div class="form-group">
                                    <form:input path="name" type="text" name="name" tabindex="1" class="form-control"
                                           placeholder="Dish name" value="${dish.name}"/>
                                   <form:errors path="name" cssClass="error-message"/>
                                </div>

                                <div class="form-group">
                                    <form:input path="price" type="number" step="0.01" name="price" tabindex="2" class="form-control"
                                           placeholder="Dish price" value="${dish.price}"/>
                                  <form:errors path="price" cssClass="error-message"/>
                                </div>

                                <div class="form-group">
                                    <div class="row">
                                        <div class=col-xs-6>
                                            <input type="submit" name="submit" tabindex="4"
                                                   class="form-control btn btn-register" value="${createOrUpdate}">
                                        </div>
                                        <div class="col-xs-6">
                                            <a href="restaurants/${restaurantId}/menus/${menuId}/edit" name="submit" tabindex="5"
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
