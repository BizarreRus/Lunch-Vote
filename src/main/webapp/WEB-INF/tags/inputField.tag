<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ attribute name="name" required="true" description="Name of corresponding property in bean object" %>
<%@ attribute name="inputType" required="false" description="Input type" %>
<%@ attribute name="placeholder" required="false" description="Placeholder of corresponding property in bean object" %>

<spring:bind path="${name}">
    <div class="form-group">
        <c:choose>
            <c:when test="${inputType == 'password'}">
                <form:input type="password" cssClass="form-control" path="${name}" class="form-control" placeholder="${placeholder}"/></c:when>

            <c:when test="${inputType == 'number'}">
                <form:input path="${name}" cssClass="form-control" type="number" class="form-control"  placeholder="${placeholder}"/></c:when>

            <c:when test="${inputType == 'float'}">
                <form:input path="${name}" cssClass="form-control" type="number" class="form-control"  placeholder="${placeholder}" step="0.01" /></c:when>

            <c:when test="${inputType == 'hidden'}">
                <form:input path="${name}" type="hidden"/></c:when>

            <c:when test="${inputType == 'date'}">
                <form:input path="${name}" cssClass="form-control" id="visitDate" placeholder="${placeholder}"/></c:when>

            <c:otherwise>
                <form:input path="${name}" class="form-control" placeholder="${placeholder}"/></c:otherwise>
        </c:choose>

        <form:errors path="${name}" class="error-message"/>

    </div>
</spring:bind>
