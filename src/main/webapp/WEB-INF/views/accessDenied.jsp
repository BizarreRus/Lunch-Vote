<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Access is denied</title>
    <jsp:include page="fragments/headTag.jsp"/>
</head>
<body>

<jsp:include page="fragments/simpleNavbar.jsp"/>

<div class="container">
    <p class="error-title">Access is denied!</p>
    <hr/>
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h3 class="error-message">${msg}</h3><br/><br/><br/><br/>
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