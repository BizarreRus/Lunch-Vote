<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error page</title>
    <jsp:include page="fragments/headTag.jsp"/>
</head>
<body>

<jsp:include page="fragments/navbar.jsp"/>

<div class="container">
    <p class="error-title">Oops, error!</p>
    <hr/>
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h3 class="error-message">${errorMessage}</h3><br/><br/><br/><br/>
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
