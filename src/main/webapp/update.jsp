<%@ page import="com.model.Contact" %>
<%@page contentType="text/html; ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP</title>
</head>
<body>
    <h2>Page contact for update =)</h2>
    <%
        Contact contact = (Contact) request.getAttribute("contact");
    %>
    <form action="ContactController" method="get">
        <table>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" value="<%=contact.getName()%>"/></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="text" name="email" value="<%=contact.getEmail()%>"/></td>
            </tr>
            <tr>
                <td>Phone:</td>
                <td><input type="text" name="phone" value="<%=contact.getPhone()%>"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Update"></td>
            </tr>
        </table>
        <input hidden="true" type="text" name="id" value="<%=contact.getId()%>" />
        <input hidden="true" type="text" name="action" value='ABC' />
    </form>
    <br />
</body>
</html>
