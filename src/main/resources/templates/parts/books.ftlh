<#macro find filterByName, action, filterName>
    <form action="${action}" method="get">
        <input type="text" name="${filterName}" value="${filterByName}">
        <button type="submit">Find</button>
    </form>
</#macro>