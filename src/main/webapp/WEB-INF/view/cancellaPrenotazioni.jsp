<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<table border="3" style="text-align: center;">

    <tr>
        <th>Auto</th>
        <th>Data inizio</th>
        <th>Data fine</th>
        <th>Azioni</th>
    </tr>

    <c:forEach var="prenotazione" items="${listaPrenotazioni}">

        <tr>
            <td>${prenotazione.auto.targa}</td>
            <td>
                <fmt:parseDate pattern="yyyy-MM-dd" value="${prenotazione.dataInizio}" var="dataInizioForm"/>
                <fmt:formatDate pattern="dd/MM/yyyy" value="${dataInizioForm}"/>
            </td>
            <td>
                <fmt:parseDate pattern="yyyy-MM-dd" value="${prenotazione.dataFine}" var="dataFineform"/>
                <fmt:formatDate pattern="dd/MM/yyyy" value="${dataFineform}"/></td>
            <td>
                <form:form action="prenotazioni_da_cancellare/${prenotazione.id}" method="post">
                    <button type="submit">Cancella</button>
                </form:form>
            </td>
        </tr>

    </c:forEach>

</table>