<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Restaurant page</title>
    <jsp:include page="fragments/headTag.jsp"/>
</head>
<body>
<jsp:include page="fragments/navbar.jsp"/>

<div class="container">
    <c:set var="createOrUpdate" value="${empty restaurant.id ? 'Create' : 'Edit'}"/>
    <p class="title-font"><c:out value="${createOrUpdate}"/> Restaurant</p>

    <jsp:useBean id="restaurant" type="net.bizare.lunchvoteapp.model.Restaurant" scope="request"/>

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
                            <form:form id="login-form" name="form_login" action="restaurants" method="post" role="form"
                                       style="display: block;" commandName="restaurant">
                                <input type="hidden" name="id" value="${restaurant.id}">
                                <input type="hidden" name="numOfVotes" value="${restaurant.numOfVotes}">

                                <div class="form-group">
                                    <input type="text" name="name" tabindex="1" class="form-control"
                                           placeholder="Restaurant name" value="${restaurant.name}">
                                </div>

                                <div class="form-group">
                                    <input type="date" name="visitDate" tabindex="2"
                                           class="form-control" placeholder="Visit date"
                                           value="${restaurant.visitDate}">
                                </div>

                                <div class="form-group">
                                    <div class="row">
                                        <div class=col-xs-6>
                                            <input type="submit" name="submit" tabindex="4"
                                                   class="form-control btn btn-register" value="${createOrUpdate}">
                                        </div>
                                        <div class="col-xs-6">
                                            <a href="restaurants" name="submit" tabindex="5"
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

    <c:if test="${!empty restaurant.id}">
        <div class="row">
            <div class="col-md-6 col-md-offset-3 panel-group ">

                <div class="panel custom-shadow panel-info">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" href="#collapse1">Menus</a>

                            <div class="dropdown dropdown-btn-right">
                                <button class="btn btn-info dropdown-toggle" type="button" id="dropdownMenu1"
                                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                    <i class="caret"></i>
                                </button>
                                <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenu1">
                                    <li><a href="restaurants/${restaurant.id}/menus/new"><i
                                            class="glyphicon glyphicon-plus button-color"></i><span
                                            class="button-color"> Add</span></a>
                                    </li>

                                    <c:if test="${!empty restaurant.menus}">
                                        <li><a href="restaurants/${restaurant.id}/menus/deleteAll"><i
                                                class="glyphicon glyphicon-remove button-color"></i><span
                                                class="button-color"> Delete all</span></a>
                                        </li>
                                    </c:if>
                                </ul>
                            </div>

                        </h4>
                    </div>

                    <div id="collapse1" class="panel-collapse collapse">

                        <c:if test="${empty restaurant.menus}">
                            <div class="panel-body">List of menus is empty</div>
                        </c:if>

                        <c:forEach items="${restaurant.menus}" var="menu">
                            <div class="panel-body">
                                    ${menu.name}


                                <div class="dropdown dropdown-btn-right">
                                    <button class="btn btn-info dropdown-toggle" type="button" id="dropdownMenu2"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                        <i class="caret"></i>
                                    </button>
                                    <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenu2">
                                        <li><a href="restaurants/${restaurant.id}/menus/${menu.id}/edit"><i
                                                class="glyphicon glyphicon-pencil button-color"></i><span
                                                class="button-color"> Edit</span></a>
                                        </li>
                                        <li><a href="restaurants/${restaurant.id}/menus/${menu.id}/delete"><i
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
</body>
</html>
