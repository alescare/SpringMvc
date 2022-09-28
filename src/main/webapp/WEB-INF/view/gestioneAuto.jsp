<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<form:form action="gestione_modifiche_auto" method="post" modelAttribute="autoDaModificare">

    <form:input type="hidden" name="idAutoDaModificare" path="id"></form:input>
    Costruttore:<br/>
    <form:input type="text" name="costruttore" path="costruttore"></form:input>
    <br/><br/>
    Modello:<br/>
    <form:input type="text" name="modello" path="modello"></form:input>
    <br/><br/>
    Targa:<br/>
    <form:input type="text" name="targa" path="targa"></form:input>
    <br/><br/>
    Tipologia:<br/>
    <form:input type="text" name="tipologia" path="tipologia"></form:input>
    <br/><br/>
    Anno di immatricolazione:<br/>
    <form:input type="text" name="annoImmatricolazione" path="annoImmatricolazione"></form:input>
    <br/><br/>
    <button type="submit" name="azione" value="Salva"/>Salva</button>

</form:form>

<hr>

<table border="3" style="text-align: center">

    <tr>
        <th>Costruttore</th>
        <th>Modello</th>
        <th>Tipologia</th>
        <th>Targa</th>
        <th>Anno immatricolazione</th>
    </tr>

    <c:forEach var="auto" items="${listaAuto}">

    <tr>
        <td>${auto.costruttore}</td>
        <td>${auto.modello}</td>
        <td>${auto.tipologia}</td>
        <td>${auto.targa}</td>
        <td>${auto.annoImmatricolazione}</td>
        <td>
            <form:form action="modifica_auto_${auto.id}" method="get">
                <input type="submit" value="Modifica">
            </form:form>
        </td>
        <td>
            <form:form action="elimina_auto_${auto.id}" method="post">
                <input type="hidden" name="azione" value="elimina auto">
                <input type="submit" value="Elimina">
            </form:form>
        </td>
    </tr>


    </c:forEach>


