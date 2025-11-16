<%-- 
    Document   : addDishView
    Created on : Oct 19, 2025, 11:07:12 PM
    Author     : Asus
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Dish</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
</head>
<body>
<div class="page-container">
  <div class="card narrow">
    <h2 class="function-title">Add New Dish</h2>
    <form action="<%= request.getContextPath() %>/DishCtrl" method="post" class="form-grid">
        <input type="hidden" name="action" value="addDish">

        <label class="label" for="name">Dish Name<span class="required">*</span></label>
        <input class="input-text" type="text" id="name" name="name" required>

        <label class="label" for="price">Price<span class="required">*</span></label>
        <input class="input-number" type="number" step="0.1" id="price" name="price" required min="0.1">

        <label class="label" for="description">Description</label>
        <textarea class="textarea" id="description" name="description" rows="3"></textarea>

        <div class="btn-row">
          <button class="btn btn-primary" type="submit">Add Dish</button>
      
    </form>    
            <form action="<%= request.getContextPath() %>/DishCtrl" method="get">
            <input type="hidden" name="action" value="viewDish">
            <button class="btn btn-outline" type="submit">Back</button>
          </form>
        </div>
  </div>
</div>            
            
<script src="<%= request.getContextPath() %>/js/ui.js"></script>
</body>
</html>

