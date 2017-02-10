<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error page</title>

    <jsp:include page="fragments/headTag.jsp"/>
</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top custom-shadow">
    <div class="container">
        <a href="${pageContext.request.contextPath}" class="navbar-brand">Voting App Project</a>
    </div>
</nav>

<div class="container">
    <p class="title-font error-color">Oops, error!</p>


    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h3 class="error-message">${exception.message}</h3><br/><br/><br/><br/>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3">
                    <a href="restaurants" name="submit" class="form-control btn btn-login">Go to main page</a>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>

</body>
</html>
