<%@ page import="java.util.ArrayList" %>
<%@ page import="com.model.Contact" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: DevLe
  Date: 09.06.2016
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ContactList</title>
</head>
<body>
<center>
    <h3>Contacts</h3>
    <%
        ArrayList<Contact> contacts;
        contacts = (ArrayList<Contact>) request.getAttribute("contactList");

        if(contacts.size() <=0 ){
    %>
    <h3>Contact List is empty. Please add a new contact in DB.</h3>
    <%
        } else {
    %>
    <table>
        <tr style="background-color: #13c012">
            <td>Name:</td>
            <td>Email:</td>
            <td>Phone:</td>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
        <%
            Iterator iter = contacts.iterator();
            while(iter.hasNext()) {
                Contact contact = (Contact) iter.next();
        %>
        <tr>
            <td><%=contact.getName()%></td>
            <td><%=contact.getEmail()%></td>
            <td><%=contact.getPhone()%></td>
            <td><a href="ContactController?id=<%=contact.getId()%>&action=E">edit</a> </td>
            <td><a href="ContactController?id=<%=contact.getId()%>&action=D">delete</a> </td>
            <% } %>
        </tr>
    </table>
    <% } %>
    <br />
    <table>
        <tr>
            <td><a href="index.jsp">New Contact |</a></td>
            <td><a href="PhoneBookController">PhoneBook</a></td>
        </tr>
    </table>
</center>
</body>
</html>
