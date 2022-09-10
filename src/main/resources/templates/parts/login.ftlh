<#macro login path>
    <form action="${path}" method="post">
        <div>
            User Name :
            <input type="text" name="username"/>
        </div>
        <div>
            Password:
            <input type="password" name="password"/>

        </div>
        <div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" value="Sign In"/>
        </div>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Sign Out"/>
    </form>
</#macro>

<#macro back action><! -- When we go back and login successfully, we stay on the login page with the link 'login?' This BUG appears on all Back buttons-->
    <form action="${action}">
        <div>
            <input type="submit" value="Back"/>
        </div>
    </form>
</#macro>