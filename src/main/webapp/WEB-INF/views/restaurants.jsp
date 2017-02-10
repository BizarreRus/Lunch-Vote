<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Restaurants</title>
    <jsp:include page="fragments/headTag.jsp"/>
</head>
<body>
<jsp:include page="fragments/navbar.jsp"/>

<div class="container">
    <p class="title-font">Restaurants Page</p>
    <br/>
    <c:if test="${empty restaurants}">
        <br/><br/>
        <h3>List of restaurants is empty for today.</h3>
    </c:if>
    <div class="panel-group">

        <c:forEach items="${restaurants}" var="restaurant">
            <jsp:useBean id="restaurant" scope="page" type="net.bizare.lunchvoteapp.model.Restaurant"/>
            <div class="panel-indentation">
                <div class="panel panel-info custom-shadow">
                    <div class="panel-heading">

                        <h4 class="panel-title">
                        <div class="dropdown dropdown-btn-right">
                            <button class="btn btn-info dropdown-toggle" type="button" id="dropdownMenu1"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <i class="caret"></i>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenu1">
                                <li><a href="restaurants/${restaurant.id}/vote"><i
                                        class="glyphicon glyphicon-thumbs-up button-color"></i><span
                                        class="button-color"> Vote</span></a></li>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <li role="separator" class="divider"></li>
                                    <li><a href="restaurants/${restaurant.id}/edit"><i
                                            class="glyphicon glyphicon-pencil button-color"></i><span
                                            class="button-color"> Edit</span></a>
                                    </li>
                                    <li><a href="restaurants/${restaurant.id}/delete"><i
                                            class="glyphicon glyphicon-remove button-color"></i><span
                                            class="button-color"> Delete</span></a>
                                    </li>
                                </sec:authorize>
                            </ul>
                        </div>
                            <a data-toggle="collapse" href="#collapse${restaurant.id}">${restaurant.name} <span
                                    class="badge">${restaurant.numOfVotes}</span></a>
                        </h4>
                    </div>
                    <div id="collapse${restaurant.id}" class="panel-collapse collapse custom-shadow">

                        <c:if test="${empty restaurant.menus}">
                            <div class="panel-body">List of menus is empty</div>
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

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
