<%@page contentType="text/html; ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP</title>
</head>
<body>
    <h2>Add new Contact! =)</h2>
    <form action="ContactController" method="post">
        <table>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name"/></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="text" name="email"/></td>
            </tr>
            <tr>
                <td>Phone:</td>
                <td><input type="text" name="phone"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="submit"></td>
            </tr>
        </table>
    </form>

    <br />
    <a href="PhoneBookController">table all contacts</a>

</body>
</html>
