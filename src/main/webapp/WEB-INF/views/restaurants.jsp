<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lunch Vote App</title>
    <jsp:include page="fragments/headTag.jsp"/>
</head>
<body>
<jsp:include page="fragments/navbar.jsp"/>

<div class="container">
    <p class="title-font">Restaurants Page</p>
    <hr/>

    <c:choose>
        <c:when test="${history}">
            <div class="description-message">
                Today is ${today}.
            </div>
            <div class="description-message">
                Here you can see the history.
            </div>
        </c:when>
        <c:when test="${empty restaurants}">
            <div class="description-message">
                List of restaurants is empty for today.
            </div>
        </c:when>
        <c:otherwise>
            <div id="chooseMsg" class="description-message">
                Please choose restaurant where you want to have lunch.
            </div>
        </c:otherwise>
    </c:choose>
    <br/>

    <div class="panel-group">

        <c:forEach items="${restaurants}" var="restaurant">
            <jsp:useBean id="restaurant" scope="page" type="net.bizare.lunchvoteapp.model.Restaurant"/>
            <div id="${restaurant.id}" class="panel-indentation">
                <div class="panel panel-info custom-shadow">
                    <div class="panel-heading">

                        <h4 class="panel-title">
                            <div class="dropdown dropdown-btn-right">
                                <button class="btn btn-info dropdown-toggle" type="button" id="dropdownMenu1"
                                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                    <i class="caret"></i>
                                </button>
                                <ul class="dropdown-menu dropdown-menu-right"
                                    aria-labelledby="dropdownMenu1">
                                    <c:if test="${!history}">
                                        <li><a href="javascript:vote(${restaurant.id});"><i
                                                class="glyphicon glyphicon-thumbs-up button-color"></i><span
                                                class="button-color"> Vote</span></a></li>
                                    </c:if>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <li><a href="restaurants/${restaurant.id}/edit"><i
                                                class="glyphicon glyphicon-pencil button-color"></i><span
                                                class="button-color"> Edit</span></a>
                                        </li>
                                        <li><a href="javascript:deleteRestaurant(${restaurant.id});"><i
                                                class="glyphicon glyphicon-remove button-color"></i><span
                                                class="button-color"> Delete</span></a>
                                        </li>
                                    </sec:authorize>
                                </ul>
                            </div>
                            <a data-toggle="collapse" href="#collapse${restaurant.id}">${restaurant.name}
                                <span class="badge">${restaurant.numOfVotes}</span>
                            </a>
                            <c:if test="${history}">
                                <span class="panel-element-right">Date: ${restaurant.visitDate}&nbsp;</span>
                            </c:if>
                        </h4>
                    </div>
                    <div id="collapse${restaurant.id}" class="panel-collapse collapse custom-shadow">

                        <c:if test="${empty restaurant.menus}">
                            <div class="panel-body">List is empty</div>
                        </c:if>
                        <c:forEach items="${restaurant.menus}" var="menu">
                            <jsp:useBean id="menu" scope="page" type="net.bizare.lunchvoteapp.model.Menu"/>

                            <c:set var="totalPrice" value="0"/>

                            <div class="panel-heading"><b>${menu.name}</b></div>

                            <c:forEach items="${menu.dishes}" var="dish">
                                <jsp:useBean id="dish" scope="page" type="net.bizare.lunchvoteapp.model.Dish"/>

                                <div class="panel-body">
                                        ${dish.name}
                                    <span class="panel-element-right">${dish.price}</span>
                                </div>

                                <c:set var="totalPrice" value="${totalPrice + dish.price}"/>
                            </c:forEach>
                            <div class="panel-footer">
                                Total price
                                <span class="panel-element-right">${totalPrice}</span>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>

        </c:forEach>

    </div>
</div>

<div class="modal fade" id="errorModal" role="dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header modal-error-title">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <p class="modal-title text-center">Oops, error!</p>
            </div>
            <div class="modal-body">
                <h4 class="text-center" id="message"></h4>
            </div>
            <div class="modal-footer modal-error-message">
                <div class="row">
                    <div class="text-center">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
<script src="${pageContext.request.contextPath}/resources/js/restaurants.js"></script>
</body>
</html>
