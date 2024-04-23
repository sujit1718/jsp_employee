<%@page import="jsp_employee.Employee"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee List</title>
</head>
<body>
     <%String value = (String)request.getAttribute("cookie");
     if(value!=null){
     %> 
      <h2>Changed By <%=value %></h2>
     <% } %> 
     <%--Cookies end--%>
    <% String message = (String)request.getAttribute("message");
    if(message != null) {
        out.print("<h1>" + message + "</h1>");
    }
    %>
    
    <% List<Employee> list = (List<Employee>) request.getAttribute("list"); %>
     
    <table border="2px solid" cellpadding="20px" cellspacing="5px">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Phone</th>
        <th>Email</th>
        <th>Password</th>
        <th>Designation</th>
        <th>Salary</th>
    </tr>
    <% for (Employee employee : list) { %>
        <tr>
            <td><%= employee.getId() %></td>
            <td><%= employee.getName() %></td>
            <td><%= employee.getPhone() %></td>
            <td><%= employee.getEmail()%></td>
            <td><%= employee.getPassword()%></td>
            <td><%= employee.getDesignation() %></td>
            <td><%= employee.getSalary()%></td>
            <td><a href="delete?id=<%= employee.getId() %>"><button title="delete">DELETE</button> </a></td>
            <td><a href="update?id=<%= employee.getId() %>"><button title="update">UPDATE</button> </a></td>
        </tr>
    <% } %>
    </table>
       <a href="login.jsp"><button title="logout">Logout</button></a>
    <script type="text/javascript">
       alert("login Successful!!")
    </script>
</body>
</html>