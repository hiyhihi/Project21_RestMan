<%-- 
    Document   : SearchSupplierView
    Created on : Oct 20, 2025, 12:37:33 AM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Supplier" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Supplier</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
</head>
<body>
<div class="page-container">
  <div class="page-cluster">
    <div class="card">
        <h2 class="function-title">Search Supplier</h2>
        <div class="actions">

        <div class="actions-left">
            <form class="search-form" action="<%= request.getContextPath() %>/SupplierCtrl" method="get">
                <input type="hidden" name="action" value="searchSupplier"/>
                <input class="input-text" type="text" name="keyword" placeholder="Enter supplier name"/>
                <button class="btn btn-primary" type="submit">Search</button>
            </form>
        </div>

        <div class="actions-right">
            <form action="<%= request.getContextPath() %>/WarehouseStaff/AddSupplierView.jsp" method="get">
                <button class="btn btn-outline" type="submit">Add Supplier</button>
            </form>
            <form action="<%= request.getContextPath() %>/WarehouseStaff/WarehouseStaffHomeView.jsp" method="get">
                <button class="btn btn-outline" type="submit">Back</button>
            </form>
        </div>
    
        </div>

    </div>

    <div class="card">
      <h3 class="subtitle">Results</h3>
      <div class="table-card">
        <table class="table">
          <thead><tr><th>ID</th><th>Name</th><th>Phone</th><th>Address</th><th class="text-center" style="width:80px">Action</th></tr></thead>
          <tbody>
          <%
              ArrayList<Supplier> supplierList = (ArrayList<Supplier>) request.getAttribute("supplierList");
              if (supplierList != null && !supplierList.isEmpty()) {
                for (Supplier sup : supplierList) { %>
              <tr>
                  <td><%= sup.getId() %></td>
                  <td><%= sup.getCompanyName() %></td>
                  <td><%= sup.getPhone() %></td>
                  <td><%= sup.getAddress() %></td>
                  <td class="text-center">
                        <form action="<%= request.getContextPath() %>/SupplierCtrl" method="get">
                            <input type="hidden" name="action" value="selectSupplier"/>
                            <input type="hidden" name="id" value="<%= sup.getId() %>"/>
                            <button class="btn btn-primary" type="submit">Select</button>
                        </form>
                  </td>
              </tr>
          <% } } else { %>
              <tr><td colspan="4" class="text-center small">No data</td></tr>
          <% } %>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
<script src="<%= request.getContextPath() %>/js/ui.js"></script>
<%
    String actionMessage = (String) request.getAttribute("message");
    String actionError = (String) request.getAttribute("errorMessage");
    String searchError = (String) request.getAttribute("searchError");
    if (actionMessage != null) { %>
<script>showToast(<%= '"' + actionMessage.replace("\"","\\\"") + '"' %>, 'success');</script>
<% } if (actionError != null) { %>
<script>showPopup('Action Error', <%= '"' + actionError.replace("\"","\\\"") + '"' %>);</script>
<% } if (searchError != null) { %>
<script>showPopup('Search Error', <%= '"' + searchError.replace("\"","\\\"") + '"' %>);</script>
<% } %>
</body>
</html>


