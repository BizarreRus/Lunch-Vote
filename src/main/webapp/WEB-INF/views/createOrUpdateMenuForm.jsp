<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="lunchVote" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Menu page</title>
    <jsp:include page="fragments/headTag.jsp"/>
</head>
<body>
<jsp:include page="fragments/navbar.jsp"/>

<div class="container">
    <c:set var="createOrUpdate" value="${empty menu.id ? 'Create' : 'Edit'}"/>
    <p class="title-font">Menu page</p>
    <hr/>

    <jsp:useBean id="menu" type="net.bizare.lunchvoteapp.model.Menu" scope="request"/>

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
                            <form:form id="login-form" name="form_login" action="restaurants/${restaurantId}/menus"
                                       method="post" role="form"
                                       style="display: block;" commandName="menu">

                                <lunchVote:inputField name="id" inputType="hidden"/>

                                <lunchVote:inputField name="name" placeholder="Menu name"/>

                                <div class="form-group">
                                    <div class="row">
                                        <div class=col-xs-6>
                                            <input type="submit" name="submit" tabindex="4"
                                                   class="form-control btn btn-register" value="${createOrUpdate}">
                                        </div>
                                        <div class="col-xs-6">
                                            <a href="restaurants/${restaurantId}/edit" name="submit"
                                               tabindex="5"
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

    <c:if test="${!empty menu.id}">
        <div class="row">
            <div class="col-md-6 col-md-offset-3 panel-group ">

                <div class="panel custom-shadow panel-info">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" href="#collapse1">Dishes</a>

                            <div class="dropdown dropdown-btn-right">
                                <button class="btn btn-info dropdown-toggle" type="button" id="dropdownMenu1"
                                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                    <i class="caret"></i>
                                </button>
                                <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenu1">
                                    <li><a href="restaurants/${restaurantId}/menus/${menu.id}/dishes/new"><i
                                            class="glyphicon glyphicon-plus button-color"></i><span
                                            class="button-color"> Add</span></a>
                                    </li>
                                    <c:if test="${!empty menu.dishes}">
                                        <li><a href="restaurants/${restaurantId}/menus/${menu.id}/dishes/deleteAll"><i
                                                class="glyphicon glyphicon-remove button-color"></i><span
                                                class="button-color"> Delete all</span></a>
                                        </li>
                                    </c:if>
                                </ul>
                            </div>

                        </h4>
                    </div>

                    <div id="collapse1" class="panel-collapse collapse">

                        <c:if test="${empty menu.dishes}">
                            <div class="panel-body">List is empty</div>
                        </c:if>

                        <c:forEach items="${menu.dishes}" var="dish">
                            <div id="${dish.id}" class="panel-body">
                                    ${dish.name}

                                <div class="dropdown dropdown-btn-right">
                                    <button class="btn btn-info dropdown-toggle" type="button" id="dropdownMenu2"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                        <i class="caret"></i>
                                    </button>
                                    <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenu2">
                                        <li>
                                            <a href="restaurants/${restaurantId}/menus/${menu.id}/dishes/${dish.id}/edit"><i
                                                    class="glyphicon glyphicon-pencil button-color"></i><span
                                                    class="button-color"> Edit</span></a>
                                        </li>
                                        <li>
                                            <a href="javascript:deleteDish(${menu.id}, ${dish.id});"><i
                                                    class="glyphicon glyphicon-remove button-color"></i><span
                                                    class="button-color"> Delete</span></a>
                                        </li>
                                    </ul>
                                </div>

                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

    </c:if>

</div>

<jsp:include page="fragments/footer.jsp"/>
<script src="${pageContext.request.contextPath}/resources/js/dishes.js"></script>
</body>
</html>
