<%-- 
    Document   : AddIngredientView
    Created on : Oct 20, 2025, 12:38:24 AM
    Author     : Asus
--%>


<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
</head>
<body>
<div class="page-container">
  <div class="card narrow">
    <h2 class="function-title">Add New Ingredient</h2>
    <form action="<%= request.getContextPath() %>/IngredientCtrl" method="post" class="form-grid">
        <input type="hidden" name="action" value="addIngredient">

        <label class="label" for="name">Ingredient Name<span class="required">*</span></label>
        <input class="input-text" type="text" id="name" name="name" required>

        <label class="label" for="price">Price<span class="required">*</span></label>
        <input class="input-number" type="number" step="0.1" id="price" name="price" required min="0.1">

        <div class="btn-row">
          <button class="btn btn-primary" type="submit">Add Ingredient</button>
    
    </form>      
          <form action="<%= request.getContextPath() %>/IngredientCtrl" method="get">
            <input type="hidden" name="action" value="">
            <button class="btn btn-outline" type="submit">Back</button>
          </form>
        </div>
  </div>
</div>
<script src="<%= request.getContextPath() %>/js/ui.js"></script>
</body>
</html>
