<%-- 
    Document   : manageDishView
    Created on : Oct 19, 2025, 10:42:04 PM
    Author     : Asus
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, model.Dish" %>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.util.Locale"%>
<!DOCTYPE html>
<html>
<head>
    <h2 class="function-title">Manage Dishes</h2>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
</head>
<body>
<div class="page-container">
  <div class="page-cluster">
    <div class="card">
      <h2 class="title">Dish List</h2>
      <div class="table-card">
        <table class="table">
          <thead>
            <tr><th style="width:90px">ID</th><th>Name</th><th class="text-right" style="width:140px">Price</th><th>Description</th></tr>
          </thead>
          <tbody>
            <%
              ArrayList<Dish> dishList = (ArrayList<Dish>) request.getAttribute("dishList");
              if (dishList != null && !dishList.isEmpty()) {
                for (Dish d : dishList) {
            %>
            <% 
                DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("vi", "VN"));
                symbols.setGroupingSeparator('.');
                symbols.setDecimalSeparator(',');
                DecimalFormat formatter = new DecimalFormat("#,##0.0", symbols); 
            %>
            <tr>
              <td><%= d.getId() %></td>
              <td><%= d.getName() %></td>
              <td class="text-right"><%= formatter.format(d.getPrice()) %></td>
              <td><%= d.getDescription() %></td>
            </tr>
          <% }} else { %>
            <tr><td colspan="4" class="text-center">No dishes available.</td></tr>
          <% } %>
          </tbody>
        </table>
      </div>
      <div class="btn-row">
        <form action="<%= request.getContextPath() %>/ManagementStaff/ManagementStaffHomeView.jsp" method="get">
          <button class="btn btn-outline" type="submit">Back</button>
        </form>
        <form action="<%= request.getContextPath() %>/ManagementStaff/AddDishView.jsp" method="get">
          <button class="btn btn-primary" type="submit">Add New Dish</button>
        </form>
      </div>
    </div>
  </div>
</div>
               
<script src="<%= request.getContextPath() %>/js/ui.js"></script>
<%
    String message = (String) request.getAttribute("message");
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (message != null) {
%>
<script>showToast(<%= '"' + message.replace("\"","\\\"") + '"' %>, 'success');</script>
<% }
   if (errorMessage != null) { %>
<script>showPopup('Error', <%= '"' + errorMessage.replace("\"","\\\"") + '"' %>);</script>
<% } %>
</body>
</html>


