<%-- 
    Document   : login
    Created on : Oct 19, 2025, 9:56:48 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <h2 class="function-title">Restaurant Management Project 21</h2>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
</head>
<body>
    <div class="page-container">
    <div class="card narrow">
        <h2 class="function-title">Login</h2>
        <form action="UserCtrl" method="post" class="form-grid">
            <input type="hidden" name="action" value="login">
            <label class="label">Username</label>
            <input class="input-text" type="text" name="username" required>
            <label class="label">Password</label>
            <input class="input-text" type="password" name="password" required>
            <div class="btn-row">
                <button class="btn btn-primary" type="submit">Login</button>
            </div>
        </form>
    </div>
</div>


<script src="<%= request.getContextPath() %>/js/ui.js"></script>
<%
String error = (String) request.getAttribute("errorMessage");
String message = (String) request.getAttribute("message");
if (message != null) {
%>
<script>showToast(<%= '"' + message.replace("\"","\\\"") + '"' %>, 'success', 'Welcome');</script>
<% }
if (error != null) { %>
<script>showPopup('Login failed', <%= '"' + error.replace("\"","\\\"") + '"' %>);</script>
<% } %>
</body>
</html>
