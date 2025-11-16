<%-- 
    Document   : warehouseStaffHomeView
    Created on : Oct 19, 2025, 9:59:07 PM
    Author     : Asus
--%>

<%@page import="java.util.Enumeration"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <h2 class="function-title">Warehouse Staff Home</h2>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
</head>
<body>  
    
<div class="page-container">
  <div class="card narrow">
    <!--<h2 class="title">Warehouse Staff Home</h2>-->
    <div class="actions">
      <form action="<%= request.getContextPath() %>/SupplierCtrl" method="get">
          <input type="hidden" name="action" value="">
          <button class="btn btn-primary" type="submit">Import Ingredient</button>
      </form>
      <form action="<%= request.getContextPath() %>/UserCtrl" method="post">
          <input type="hidden" name="action" value="logout">
          <button class="btn btn-outline" type="submit">Logout</button>
      </form>
    </div>
  </div>
</div>
<script src="<%= request.getContextPath() %>/js/ui.js"></script>
<%
    String actionMessage = (String) request.getAttribute("message");
    if (actionMessage != null) { %>
        <script>
            showToast(<%= '"' + actionMessage.replace("\"","\\\"") + '"' %>, 'success');
        </script>
    <% } 
%>
</body>
</html>

