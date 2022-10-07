<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:choose>
    <c:when test="${!(utenteLogin.admin)}">
        <form:form action="profilo" method="get">
            <button type="submit">Visualizza profilo</button>
            <br/><br/>
        </form:form>


        <form:form action="prenota_auto" method="get">
            <button type="submit">Prenota auto</button><br/>
            <font color="#dc143c">${prenotazioneInCorsoMsg}</font>
        </form:form>
    </c:when>

    <c:otherwise>
        <div class="text-center pt-5">
            <div class="d-grid gap-2 col-6 mx-auto">
                <form:form action="gestione_utenti" method="get">
                    <button type="submit" class="btn btn-dark w-25 pt-2">Gestisci utenti</button>
                </form:form>
                <form:form action="gestione_auto" method="get">
                    <button type="submit" class="btn btn-dark w-25 pt-2">Gestisci auto</button>
                </form:form>
                <form:form action="prenotazioni_da_approvare" method="get">
                    <button type="submit" class="btn btn-dark w-25 pt-2">Approva prenotazioni</button>
                </form:form>
                <form:form action="prenotazioni_da_cancellare" method="get">
                    <button type="submit" class="btn btn-dark w-25 pt-2">Cancella Prenotazioni</button>
                </form:form>
            </div>
        </div>
    </c:otherwise>
</c:choose>

