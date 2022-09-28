<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<h4>Dati anagrafici utente:</h4>
<h5>Nome: ${utenteLogin.nome}</h5>
<h5>Cognome: ${utenteLogin.cognome}</h5>
<h5>Data di nascita:
    <fmt:parseDate pattern="yyyy-MM-dd" value="${utenteLogin.dataNascita}" var="dataNascitaform"/>
    <fmt:formatDate pattern="dd/MM/yyyy" value="${dataNascitaform}"/>
</h5>

<hr>

<h4>Credenziali utente:</h4>

<form:form action="modifica_credenziali" method="post">

    <h5>Username: <input type="text" value="${utenteLogin.username}" name="username"></h5>
    <h5>Password: <input type="text" value="${utenteLogin.password}" name="password"></h5>
    <h5>Email: <input type="email" value="${utenteLogin.email}" name="email"></h5>
    <button type="submit">Applica modifiche</button>

</form:form>


<hr>

<h4>Storico noleggi:</h4>

<table border="3" style="text-align: center">

    <tr>
        <th>Costruttore</th>
        <th>Modello</th>
        <th>Targa</th>
        <th>Data inizio</th>
        <th>Data fine</th>
        <th>Approvata</th>
    </tr>

    <c:forEach var="prenotazione" items="${listaPrenotazioni}">

        <tr>
            <td>${prenotazione.auto.costruttore}</td>
            <td>${prenotazione.auto.modello}</td>
            <td>${prenotazione.auto.targa}</td>
            <td><fmt:parseDate pattern="yyyy-MM-dd" value="${prenotazione.dataInizio}" var="dataInizioForm"/>
                <fmt:formatDate pattern="dd/MM/yyyy" value="${dataInizioForm}"/>
            </td>
            <td>
                <fmt:parseDate pattern="yyyy-MM-dd" value="${prenotazione.dataFine}" var="dataFineform"/>
                <fmt:formatDate pattern="dd/MM/yyyy" value="${dataFineform}"/>
            </td>
            <td>
                <c:choose>
                    <c:when test="${prenotazione.approvata}">SI</c:when>
                    <c:otherwise>NO</c:otherwise>
                </c:choose>
            </td>
        </tr>

    </c:forEach>

</table>

