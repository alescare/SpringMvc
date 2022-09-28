<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="text-center pt-5">

    <main class="form-signin w-25 m-auto">

        <form:form action="utente/home" method="POST" modelAttribute="utenteLogin" enctype="multipart/form-data">

            <div class="form-floating">
                <form:input id="email" path="email" type="email" class="form-control" placeholder="name@example.com"/>
                <label for="email">Indirizzo email</label>
            </div>
            <div class="form-floating">
                <form:input path="password" type="password" class="form-control" id="password" placeholder="Password"/>
                <label for="password">Password</label>
            </div>
            <div class="pt-3">
                <input type="submit" id="accedi" class="btn btn-primary form-buttons" value = "Accedi"/>
            </div>
        </form:form>
    </main>
</div>



