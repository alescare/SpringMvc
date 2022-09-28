<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<form:form action="auto_disponibili" method="get">
    Data inizio: <input  type="date" name="dataInizioPeriodo" value="${dataInizioPeriodo}"><br/>
    Data fine: <input  type="date" name="dataFinePeriodo" value="${dataFinePeriodo}"><br/>
    <button type="submit">Cerca</button>
</form:form>

<hr>

<c:if test="${dataInizioPeriodo != null && dataFinePeriodo != null}">
<table >

    <tr>
        <th>Costruttore</th>
        <th>Modello</th>
        <th>Tipologia</th>
        <th>Anno immatricolazione</th>
        <th></th>
    </tr>

    <c:forEach var="auto" items="${listaAutoDisponibili}">

        <tr>
            <td>${auto.costruttore}</td>
            <td>${auto.modello}</td>
            <td>${auto.tipologia}</td>
            <td>${auto.annoImmatricolazione}</td>
            <td>
                <form:form action="prenota_auto/${auto.id}" method="post">
                    <input type="hidden" name="dataFinePeriodo" value="${dataFinePeriodo}">
                    <input type="hidden" name="dataInizioPeriodo" value="${dataInizioPeriodo}">
                    <button type="submit">Prenota</button>
                </form:form>
            </td>
        </tr>

    </c:forEach>
    </c:if>

</table>


