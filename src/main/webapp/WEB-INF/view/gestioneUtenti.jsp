<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container text-center  pt-5">
    <h3>Aggiunta nuovo utente</h3>

    <br/>

    <form:form action="aggiungi_utente" method="post" modelAttribute="nuovoUtente">
        Nome:<br/>
        <form:input path="nome" type="text" name="nome"></form:input>
        <br/>
        <form:errors path="nome" cssClass="text-danger"></form:errors>
        <br/><br/>
        Cognome:<br/>
        <form:input path="cognome" type="text" name="cognome"></form:input>
        <br/>
        <form:errors path="cognome" cssClass="text-danger"></form:errors>
        <br/><br/>
        Email:<br/>
        <form:input path="email" type="text" name="email"></form:input>
        <br/>
        <form:errors path="email" cssClass="text-danger"></form:errors>
        <br/><br/>
        Username:<br/>
        <form:input path="username" type="text" name="username"></form:input>
        <br/>
        <form:errors path="username" cssClass="text-danger"></form:errors>
        <br/><br/>
        Password:<br/>
        <form:input path="password" type="password" name="password"></form:input>
        <br/>
        <form:errors path="password" cssClass="text-danger"></form:errors>
        <br/><br/>
        Data di nascita:<br/>
        <form:input path="dataNascita" type="date" name="dataNascita"></form:input>
        <br/>
        <form:errors path="dataNascita" cssClass="text-danger"></form:errors>
        <br/><br/>
        <button type="submit">Aggiungi</button>

    </form:form>
</div>

<hr>

<div class="d-flex align-content-center justify-content-center">
    <table class="table table-bordered text-center w-75">

        <tr>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Data di nascita</th>
            <th>Username</th>
        </tr>

        <c:forEach var="utente" items="${listaUtenti}">

            <tr>
                <td>${utente.nome}</td>
                <td>${utente.cognome}</td>
                <td>
                    <fmt:parseDate pattern="yyyy-MM-dd" value="${utente.dataNascita}" var="dataNascitaform"/>
                    <fmt:formatDate pattern="dd/MM/yyyy" value="${dataNascitaform}"/>
                </td>
                <td>${utente.username}</td>
                <td>
                    <form:form action="cancella_utente/${utente.id}" method="post">
                        <button type="submit">Cancella</button>
                    </form:form>
                </td>
            </tr>

        </c:forEach>

    </table>

</div>


