<%-- 
    Document   : managementStaffHomeView
    Created on : Oct 19, 2025, 9:58:29 PM
    Author     : Asus
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <h2 class="function-title">Management Dashboard</h2>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
</head>
<body>
<div class="page-container">
  <div class="card narrow">
    <!--<h2 class="title">Management Dashboard</h2>-->
    <div class="actions">
      <form action="<%= request.getContextPath() %>/DishCtrl" method="get">
          <input type="hidden" name="action" value="viewDish">
          <button class="btn btn-primary" type="submit">Manage Dishes</button>
      </form>
      <form action="<%= request.getContextPath() %>/UserCtrl" method="post">
          <input type="hidden" name="action" value="logout">
          <button class="btn btn-outline" type="submit">Logout</button>
      </form>
    </div>
  </div>
</div>
<script src="<%= request.getContextPath() %>/js/ui.js"></script>
</body>
</html>

