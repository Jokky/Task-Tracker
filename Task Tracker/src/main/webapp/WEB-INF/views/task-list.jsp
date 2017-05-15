<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<spring:url value="/task/" var="taskUrl"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h2>
    </c:if>

    <h1>List of all tasks:</h1>


    <spring:url value="new-task" var="newTaskUrl"/>
    <a href="${newTaskUrl}" class="btn btn-danger " role="button">Add new task</a>

    <c:choose>
        <c:when test="${not empty taskList}">

            <table class="table table-striped">
                <tr>
                    <th>Type</th>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Date added</th>
                    <th>Date finished</th>
                </tr>
                <c:forEach items="${taskList}" var="task">
                    <tr>
                        <td>${task.type}</td>

                        <td>${task.title}</td>

                        <td>${task.description}</td>

                        <td>${task.createdDate}</td>

                        <td>

                            <c:choose>

                                <c:when test="${task.finishedDate ne null}">
                                    ${task.finishedDate}
                                </c:when>

                                <c:otherwise>
                                    <p>Not finished</p>
                                </c:otherwise>

                            </c:choose>

                        </td>

                        <td>
                            <spring:url value="task/${task.id}" var="viewUrl"/>
                            <a href="${viewUrl}" class="btn btn-primary " role="button">View</a>
                        </td>

                        <td>



                            <spring:url value="finish-task/${task.id}" var="finishUrl"/>

                            <c:choose>

                                <c:when test="${task.finishedDate ne null}">
                                    <a href="${finishUrl}" class="btn btn-success disabled" role="button">Finished</a>
                                </c:when>

                                <c:otherwise>
                                    <a href="${finishUrl}" class="btn btn-warning " role="button">Finish</a>
                                </c:otherwise>

                            </c:choose>


                        </td>

                        <td>

                            <spring:url value="delete-task/${task.id}" var="deleteUrl"/>

                            <a href="${deleteUrl}" class="btn btn-danger " role="button">Delete</a>

                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>

        <c:otherwise>
            <h1 class="text-info">YOU HAVE NO TASKS</h1>
        </c:otherwise>

    </c:choose>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>