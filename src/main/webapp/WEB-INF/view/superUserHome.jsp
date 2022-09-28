<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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


