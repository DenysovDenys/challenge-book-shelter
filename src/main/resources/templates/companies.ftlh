<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<#import "parts/companies.ftlh" as comp>

<@c.page>
    <div>
        <@l.logout />
        <span><a href="/user">User list</a></span>
    </div>
    ${message!}
    <div>
        <form action="/companies" method="post">
            <input type="text" name="companyName" placeholder="Enter company"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Add new company</button>
        </form>
    </div>
    <div>List of companies</div>
    <@comp.find "${filterByName}", "/companies", "filterByName"/>
    <table>
        <thead>
        <tr>
            <th>Name</th>
        </tr>
        </thead>
        <tbody>
        <#list companies as company>
            <tr>
                <td><a href="/companies/cars/${company.companyName}">${company.companyName}</a></td>
            </tr>
        <#else>
            No companies
        </#list>
        </tbody>
    </table>
    <@l.back "/" />
</@c.page>