<%-- 
    Document   : SearchIngredientView
    Created on : Oct 20, 2025, 12:37:46 AM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="model.Ingredient" %>
<%@ page import="model.ImportedIngredient" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.text.DecimalFormatSymbols" %>
<%@ page import="java.util.Locale" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Ingredient</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
</head>
<body>
<% 
    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    symbols.setGroupingSeparator('.');
    symbols.setDecimalSeparator(',');
    DecimalFormat formatter = new DecimalFormat("#,##0.0 VND", symbols); 
%>
<div class="page-container">
    <div class="page-cluster">
        <div class="card">
            <h2 class="function-title">Search Ingredient</h2>
            <div class="actions">
                <div class="actions-left">
                    <form class="search-form" action="<%= request.getContextPath() %>/IngredientCtrl" method="get">
                        <input type="hidden" name="action" value="searchIngredient"/>
                        <input class="input-text" type="text" name="keyword" placeholder="Enter ingredient name"/>
                        <button class="btn btn-primary" type="submit">Search</button>
                    </form>
                </div>

                <div class="actions-right">
                    <form action="<%= request.getContextPath() %>/WarehouseStaff/AddIngredientView.jsp" method="get">
                        <button class="btn btn-outline" type="submit">Add Ingredient</button>
                    </form>
                    <form action="<%= request.getContextPath() %>/WarehouseStaff/SearchSupplierView.jsp" >
                        <button class="btn btn-outline" type="submit">Back</button>
                    </form>
                </div>
            </div>

        </div>

        <div class="card">
            <h3 class="subtitle">Results</h3>
            <div class="table-card">
                <table class="table">
                    <thead><tr><th style="width:50px">ID</th><th>Name</th><th class="text-right" style="width:180px">Price</th><th style="width:120px">Stock</th><th class="text-center" style="width:100px">Action</th></tr></thead>
                    <tbody>
                    <%
                        ArrayList<Ingredient> ingList = (ArrayList<Ingredient>) request.getAttribute("ingredientList");
                        if ( ingList != null && !ingList.isEmpty()) {
                            for (Ingredient ing : ingList) { %>
                            <tr>
                                <td><%= ing.getId() %></td>
                                <td><%= ing.getName() %></td>
                                <td class="text-right"><%= formatter.format(ing.getPrice()) %></td>
                                <td><%= ing.getStock() %></td>
                                <td class="text-center">
                                    <form action="<%= request.getContextPath() %>/IngredientCtrl" method="get">
                                        <input type="hidden" name="action" value="selectIngredient"/>
                                        <input type="hidden" name="id" value="<%= ing.getId() %>"/>
                                        <button class="btn btn-primary" type="submit">Select</button>
                                    </form>
                                </td>
                            </tr>
                    <% } } else { %>
                        <tr><td colspan="5" class="text-center small">No data</td></tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="card">
            <h3 class="subtitle">Temp Imported Ingredients</h3>
            <%
                ArrayList<ImportedIngredient> importList = (ArrayList<ImportedIngredient>) session.getAttribute("importList");
                float grand = 0f;
            %>
            <div class="table-card">
                <table class="table">
                    <thead><tr><th style="width:50px">No</th><th>Name</th><th style="width:50px">Qty</th><th class="text-right" style="width:180px">Price</th><th class="text-right" style="width:180px">Total</th><th class="text-center" style="width:100px">Action</th></tr></thead>
                    <tbody>
                    <%
                        int n = 1;
                        if (importList != null && !importList.isEmpty()) {
                            for (ImportedIngredient it : importList) {
                                String name = it.getIngredient() != null ? it.getIngredient().getName() : "(?)";
                                float qty = it.getQuantity();
                                float price = it.getPrice();
                                float total = qty * price; grand += total; %>
                            <tr>
                                <td><%= n++ %></td>
                                <td><%= name %></td>
                                <td><%= qty %></td>
                                <td class="text-right"><%= formatter.format(price) %></td>
                                <td class="text-right"><%= formatter.format(total) %></td>
                                <td class="text-center">
                                    <form action="<%= request.getContextPath() %>/ImportedIngredientCtrl" method="get">
                                        <input type="hidden" name="action" value="removeTemp"/>
                                        <input type="hidden" name="ingredientId" value="<%= it.getIngredient() != null ? it.getIngredient().getId() : 0 %>"/>
                                        <button class="btn btn-danger" type="submit">Remove</button>
                                    </form>
                                </td>
                            </tr>
                    <% } } else { %>
                        <tr><td colspan="5" class="text-center small">(List is empty)</td></tr>
                    <% } %>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="4" class="text-right"><b>Grand Total</b></td>
                            <td class="text-right"><b><%= formatter.format(grand) %></b></td>
                            <td></td>
                        </tr>
                    </tfoot>
                </table>
            </div>

            <div class="btn-row">
                <form action="<%= request.getContextPath() %>/ImportingInvoiceCtrl" method="get">
                    <input type="hidden" name="action" value="printInvoice"/>
                    <button class="btn btn-primary" type="submit">Next</button>
                </form>
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


