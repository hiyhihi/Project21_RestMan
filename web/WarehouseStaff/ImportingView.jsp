<%-- 
    Document   : ImportingView
    Created on : Oct 20, 2025, 12:37:55 AM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Ingredient" %>
<!DOCTYPE html>
<html>
<head>
    <title>Import Ingredient</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
</head>
<body>
<div class="page-container">
    <div class="card narrow">
        <h2 class="function-title">Importing Ingredient/Enter Quantity</h2>
        <%
            Ingredient ing = (Ingredient) session.getAttribute("selectedIngredient");
            if (ing != null) { %>
            <div class="stack">
                <div><b><%= ing.getName() %></b></div>
                <div class="small">Current Price (DB): <b><%= ing.getPrice() %></b> &nbsp; | &nbsp; Stock: <b><%= ing.getStock() %></b></div>
            </div>
            
            <form action="ImportedIngredientCtrl" method="post" class="form-grid spaced">
                <input type="hidden" name="action" value="addTemp"/>
                
                <input type="hidden" name="id" value="<%= ing.getId() %>"/>
                
                <label class="label">Quantity</label>
                <input class="input-number" type="number" name="quantity" min="1" required />
                
                <label class="label">Price</label>
                <input class="input-number" type="number" step="0.01" name="price" value="<%= ing.getPrice() %>" min="0.01"/>
                
                <div class="btn-row">
                    <button class="btn btn-primary" type="submit">Add to List</button>               
                </div>
            </form>
                        
            <form action="<%= request.getContextPath() %>/IngredientCtrl">
                <button class="btn btn-outline" type="submit">Back</button>
            </form>            
        <% } else { %>
            <p class="small" style="color: var(--error);">No ingredient selected!</p>
        <% } %>
    </div>
</div>
<script src="<%= request.getContextPath() %>/js/ui.js"></script>
</body>
</html>


