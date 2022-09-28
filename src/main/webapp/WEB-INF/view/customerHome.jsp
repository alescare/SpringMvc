<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form action="profilo" method="get">
    <button type="submit">Visualizza profilo</button>>
    <br/><br/>
</form:form>
<form:form action="prenota_auto" method="get">
    <button type="submit">Prenota auto</button>>
</form:form>
<h4>${prenotazioneInCorso}</h4>

