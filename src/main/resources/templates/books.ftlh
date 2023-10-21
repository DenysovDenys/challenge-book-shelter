<#import "parts/common.ftlh" as c>
<#import "parts/books.ftlh" as comp>

<style>
    .button {
        background-color: #2cffb8;
        border: none;
        color: black;
        padding: 8px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 4px 2px;
        cursor: pointer;
        border-radius: 8px;
    }
</style>

<@c.page>
    <div>
        <span style="text-align: center;" onclick="sortTable()">Book Shelter</span>
    </div>
    <div>
        <span style="color:red;">${message!}</span>
    </div>
    <div>
        <form action="/books" method="post">
            <input type="text" name="bookName" maxlength="10" placeholder="Enter book name"/>
            <input type="date" name="dateOfBook" placeholder="Enter date"/>
            <button class="button" type="submit">Add new book</button>
        </form>
    </div>
    <div>List of books</div>
    <@comp.find "${filterByName}", "/books", "filterByName"/>
    <table id="tableOfBooks">
        <thead>
        <tr>
            <th>Name</th>
            <th>Date</th>
        </tr>
        </thead>
        <tbody>
        <#list books as book>
            <tr>
                <td>${book.bookName}</td>
                <td>${book.dateOfBook}</td>
            </tr>
        <#else>
            No books
        </#list>
        </tbody>
    </table>

    <script>
        function sortTable() {
            let table, rows, switching, i, x, y, shouldSwitch;
            table = document.getElementById("tableOfBooks");
            switching = true;
            while (switching) {
                switching = false;
                rows = table.rows;
                for (i = 1; i < (rows.length - 1); i++) {
                    shouldSwitch = false;
                    x = rows[i].getElementsByTagName("TD")[0];
                    y = rows[i + 1].getElementsByTagName("TD")[0];
                    if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                        shouldSwitch = true;
                        break;
                    }
                }
                if (shouldSwitch) {
                    rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                    switching = true;
                }
            }
        }
    </script>
</@c.page>