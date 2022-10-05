<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container text-center  pt-5">
    <h3>Aggiunta nuovo utente</h3>

    <br/>

    <form action="aggiungi_utente" method="post">
        <input type="hidden" name="idNuovoUtente" path="id"></input>
        Nome:<br/>
        <input type="text" name="nome"><br/><br/>
        Cognome:<br/>
        <input type="text" name="cognome"><br/><br/>
        Email:<br/>
        <input type="text" name="email"><br/><br/>
        Username:<br/>
        <input type="text" name="username"><br/><br/>
        Password:<br/>
        <input type="password" name="password"><br/><br/>
        Data di nascita:<br/>
        <input type="date" name="dataNascita"><br/><br/>
        <button type="submit">Aggiungi</button>
    </form>
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


