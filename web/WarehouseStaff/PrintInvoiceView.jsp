<%-- 
    Document   : PrintInvoiceView
    Created on : Oct 20, 2025, 12:38:37 AM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="model.User,model.Supplier,model.ImportedIngredient" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.text.DecimalFormatSymbols" %>
<% 
    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    symbols.setGroupingSeparator('.');
    symbols.setDecimalSeparator(',');
    DecimalFormat formatter = new DecimalFormat("#,##0.0 VND", symbols); 
%>
<!DOCTYPE html>
<html>
<head>
    <title>Print Invoice</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
</head>
<body>
<div class="page-container">
    <div class="page-cluster">
    <div class="card">
        <h2 class="function-title">Importing Invoice</h2>
        <% 
          User staff = (User) session.getAttribute("user");
          Supplier sup = (Supplier) session.getAttribute("currentSupplier");
          ArrayList<ImportedIngredient> importList = (ArrayList<ImportedIngredient>) session.getAttribute("importList");
          String err = (String) request.getAttribute("errorMessage");
          if (err != null) {
        %>
        <script>showPopup('Error', <%= '"' + err.replace("\"","\\\"") + '"' %>);</script>
        <% } %>

        <div class="stack">
          <div><b>Supplier:</b> <%= sup != null ? sup.getCompanyName() : "(none)" %></div>
          <div><b>Phone:</b> <%= sup != null ? sup.getPhone() : "" %> &nbsp; | &nbsp; <b>Address:</b> <%= sup != null ? sup.getAddress() : "" %></div>
          <div><b>Staff:</b> <%= staff != null ? staff.getName() : "(unknown)" %></div>
          <div><b>Date:</b> <%= java.time.LocalDateTime.now().toString() %></div>
        </div>

        <div class="table-card spaced">
          <table class="table">
            <thead><tr><th>Name</th><th style="width:120px">Qty</th><th class="text-right" style="width:180px">Price</th><th class="text-right" style="width:180px">Total</th><th style="width:20px"></th></tr></thead>
            <tbody>
              <%
                float grand = 0f;
                if (importList != null) for (ImportedIngredient it : importList) {
                   String name = it.getIngredient() != null ? it.getIngredient().getName() : "(?)";
                   float qty = it.getQuantity();
                   float price = it.getPrice();
                   float total = qty * price; grand += total; %>
              <tr>
                <td><%= name %></td>
                <td><%= qty %></td>
                <td class="text-right"><%= formatter.format(price) %></td>
                <td class="text-right"><%= formatter.format(total) %></td>
              </tr>
              <% } %>
            </tbody>
            <tfoot>
              <tr>
                <td colspan="3" class="text-right"><b>Grand Total</b></td>
                <td class="text-right"><b><%= formatter.format(grand) %></b></td>
              </tr>
            </tfoot>
          </table>
        </div>

        <div class="btn-row">
            <form action="ImportingInvoiceCtrl" method="post">
                <input type="hidden" name="action" value="confirmInvoice"/>
                <button class="btn btn-primary" type="submit">Submit</button>
            </form>
            <form action="<%= request.getContextPath() %>/IngredientCtrl">
                <button class="btn btn-outline" type="submit">Back</button>
            </form>
        </div>
    </div>
  </div>
</div>
<script src="<%= request.getContextPath() %>/js/ui.js"></script>
</body>
</html>

