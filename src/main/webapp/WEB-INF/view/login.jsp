<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="text-center pt-5">

    <main class="form-signin w-25 m-auto">

        <form action="performLogin" method="post" >

            <div class="form-floating">
                <input name="username" type="text" class="form-control" placeholder="Username" id="username"/>
                <label for="username">Username</label>
            </div>
            <div class="form-floating">
                <input  type="password" class="form-control" name="password" placeholder="Password" id="password"/>
                <label for="password">Password</label>
            </div>
            <div class="pt-3">
                <input type="submit" id="accedi" class="btn btn-primary form-buttons" value = "Accedi"/>
            </div>
        </form>
    </main>
</div>



