<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "com.productManagement.model.Product"%>
    
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/product.js"></script>
		
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="utf-8">
<title>Product</title>
</head>

<body>
	<div class="container"><div class="row"><div class="col-6">
	
		<h1>Product Management</h1>
	
		
	<form id = "formProduct" name = "formProduct" >
		<div class="form-group">
			<label for="code">Product Code </label>
			<input id = "productCode" name = "productCode" type = "text" class = "form-control form-control-sm"  placeholder = "Enter Product Code..">
		</div>
		
		<div class="form-group">
			<label for="name">Product Name </label>
			<input id = "productName" name = "productName" type = "text" class = "form-control form-control-sm"  placeholder = "Product Name..">
		</div>
		
		<div class="form-group">
			<label for="price">Product Price </label>
			<input id = "productPrice" name = "productPrice" type = "text" class = "form-control form-control-sm"  placeholder = "Enter Product Price..">
		</div>
		
		<div class="form-group">
			<label for="quan">Quantity </label>
			<input id = "quanti" name = "quanti" type = "text" class = "form-control form-control-sm"  placeholder = "Enter Quantity..">
		</div>
		
		<div class="form-group">
			<label for="desc">Description </label>
			<input id = "des" name = "des" type = "text" class = "form-control form-control-sm"  placeholder = " Description..">
		</div>
		
		<div class="form-group">
			<label for="researcherName">Researcher Name </label>
			<input id = "researcherName" name = "researcherName" type = "text" class = "form-control form-control-sm"  placeholder = " Researcher Name..">
		</div>
		
		<input id = "btnSave" name="btnSave" type="button" value="Save" class = "btn btn-primary">
		<input type = "hidden" id = "hidProductIDSave" name = "hidProductIDSave" value = "">
	</form>
	
	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	
	<br>
	
	<br>
	<div id="divProductsGrid">
	<%
		Product product = new Product();
		out.print(product.readProducts());
	%>
	
	</div>
</body>
</html>