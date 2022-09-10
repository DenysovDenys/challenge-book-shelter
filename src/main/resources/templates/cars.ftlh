<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<#import "parts/companies.ftlh" as comp>

<@c.page>
    <div>
        <@l.logout />
    </div>
    ${message!}
    <div>
        <form action="/companies/cars" method="post">
            <input type="hidden" name="companyName" value="${companyName}">
            <input type="text" name="modelOfCar" placeholder="Enter model" />
            <input type="datetime-local" name="manufacture" placeholder="Enter date of manufacture" />
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Add new car</button>
        </form>
    </div>
    <div>List of cars</div>
    <@comp.find "${filterByModel}", "/companies/cars/${companyName}", "filterByModel"/>
    <table>
        <thead>
        <tr>
            <th>Company</th>
            <th>Model</th>
            <th>Manufacture</th>
        </tr>
        </thead>
        <tbody>
        <#list cars as car>
            <tr>
                <td>${car.company.companyName}</td>
                <td>${car.model}</td>
                <td>${car.manufacture}</td>
            </tr>
        <#else>
            No cars
        </#list>
        </tbody>
    </table>
    <@l.back "/companies" />
</@c.page>