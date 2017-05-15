<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="container">

    <h1>Please, fill in</h1>
    <spring:url value="new-task" var="url"/>
    <div class="form-group">
    <form:form action="${url}" method="post" modelAttribute="taskDto">
            <table>
                <tr>
                    <td>Type:</td>
                    <td>
                        <form:select path="type" cssClass="form-control">

                            <form:options items="${options}"/>

                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td>Title:</td>
                    <td><form:input path="title" class="form-control"/></td>
                </tr>

                <tr>
                    <td>Description:</td>
                    <td><form:textarea path="description" cols="100" rows="20" cssClass="form-control" id="description"/></td>
                </tr>

                <tr>
                    <td><form:button class="btn btn-primary">Submit</form:button></td>
                </tr>
            </table>


        </form:form>
    </div>

