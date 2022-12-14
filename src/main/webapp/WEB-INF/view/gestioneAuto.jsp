<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<form:form action="gestione_modifiche_auto" method="post" modelAttribute="autoDaModificare">

    <form:input type="hidden" name="idAutoDaModificare" path="id"></form:input>
    Costruttore:<br/>
    <form:input type="text" name="costruttore" path="costruttore"></form:input>
    <br/>
    <form:errors path="costruttore" cssClass="text-danger"></form:errors>
    <br/><br/>
    Modello:<br/>
    <form:input type="text" name="modello" path="modello"></form:input>
    <br/>
    <form:errors path="modello" cssClass="text-danger"></form:errors>
    <br/><br/>
    Targa:<br/>
    <form:input type="text" name="targa" path="targa"></form:input>
    <br/>
    <form:errors path="targa" cssClass="text-danger"></form:errors>
    <br/><br/>
    Tipologia:<br/>
    <form:input type="text" name="tipologia" path="tipologia"></form:input>
    <br/>
    <form:errors path="tipologia" cssClass="text-danger"></form:errors>
    <br/><br/>
    Anno di immatricolazione:<br/>
    <form:input type="text" name="annoImmatricolazione" path="annoImmatricolazione" ></form:input>
    <br/>
    <form:errors path="annoImmatricolazione" cssClass="text-danger"></form:errors>
    <br/><br/>
    <button type="submit">Salva</button>

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
                <button type="submit">Modifica</button>
            </form:form>
        </td>
        <td>
            <form:form action="elimina_auto/${auto.id}" method="post">
                <button type="submit">Elimina</button>
            </form:form>
        </td>
    </tr>


    </c:forEach>


