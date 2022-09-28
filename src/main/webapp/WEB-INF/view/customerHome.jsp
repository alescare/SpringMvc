<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<form action="UtenteServlet" method="get">
    <input type="submit" name="azione" value="Visualizza profilo">
    <br/><br/>
</form>
<form action="AutoServlet" method="get">
    <input type="hidden" name="azione" value="pagina di prenotazione auto">
    <input type="submit" value="Prenota auto">
</form>
<h4>${prenotazioneInCorso}</h4>

