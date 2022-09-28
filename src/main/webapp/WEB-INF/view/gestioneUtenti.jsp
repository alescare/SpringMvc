<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container text-center  pt-5">
    <h3>Aggiunta nuovo utente</h3>

    <br/>
    <c:if test="${utente} == null">
        Utente non inserito
    </c:if>
    <form:form action="aggiungi_utente" method="post" modelAttribute="nuovoUtente">
        Nome:<br/>
        <form:input type="text" path="nome"></form:input><br/><br/>
        Cognome:<br/>
        <form:input type="text" path="cognome"></form:input><br/><br/>
        Username:<br/>
        <form:input type="text" path="username"></form:input><br/><br/>
        Password:<br/>
        <form:input type="password" path="password"></form:input><br/><br/>
        Data di nascita:<br/>
        <form:input type="date" path="dataNascita"></form:input><br/><br/>
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
                    <form:form action="cancella_utente_${utente.id}" method="post">
                        <button type="submit">Cancella</button>
                    </form:form>
                </td>
            </tr>

        </c:forEach>

    </table>

</div>


