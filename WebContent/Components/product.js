$(document).ready(function(){
	
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
		$("#alertError").hide();
	
});

$(document).on("click", "#btnSave", function(event){
	
	// Clear alerts
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation
	var status = validateProductForm();
	if (status != true)
	{
	$("#alertError").text(status);
	$("#alertError").show();
	return;
	}
	// If valid
	var type = ($("#hidProductIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
	{
	url : "ProductAPI",
	type : type,
	data : $("#formProduct").serialize(),
	dataType : "text",
	complete : function(response, status)
	{
	onProductSaveComplete(response.responseText, status);
	}
	});
});

function onProductSaveComplete(response, status)
{
	if (status == "success")
	{
	var resultSet = JSON.parse(response);
	if (resultSet.status.trim() == "success")
	{
	$("#alertSuccess").text("Successfully saved.");
	$("#alertSuccess").show();
	$("#divProductsGrid").html(resultSet.data);
	} else if (resultSet.status.trim() == "error")
	{
	$("#alertError").text(resultSet.data);
	$("#alertError").show();
	}
	} else if (status == "error")
	{
	$("#alertError").text("Error while saving.");
	$("#alertError").show();
	} else
	{
	$("#alertError").text("Unknown error while saving..");
	$("#alertError").show();
	}
	
	$("#hidProductIDSave").val("");
	$("#formProduct")[0].reset();
}


$(document).on("click", ".btnUpdate", function(event)
		{
		$("#hidProductIDSave").val($(this).closest("tr").find('#hidProductIDUpdate').val());
		$("#productCode").val($(this).closest("tr").find('td:eq(0)').text());
		$("#productName").val($(this).closest("tr").find('td:eq(1)').text());
		$("#productPrice").val($(this).closest("tr").find('td:eq(2)').text());
		$("#quanti").val($(this).closest("tr").find('td:eq(3)').text());
		$("#des").val($(this).closest("tr").find('td:eq()').text());
		$("#researcherName").val($(this).closest("tr").find('td:eq(3)').text());
});

$(document).on("click", ".btnRemove", function(event)
		{
		$.ajax(
		{
		url : "ProductAPI",
		type : "DELETE",
		data : "productID=" + $(this).data("productid"),
		dataType : "text",
		complete : function(response, status)
		{
		onProductDeleteComplete(response.responseText, status);
		}
		});
		});

//CLIENT-MODEL================================================================
function validateProductForm()
{
		// Product CODE
		if ($("#productCode").val().trim() == "")
		{
			return "Insert Product Code.";
		}
		
		// Product NAME
		if ($("#productName").val().trim() == "")
		{
			return "Insert Product Name.";
		}

		// Product PRICE
		if ($("#productPrice").val().trim() == "")
		{
			return "Insert Product Price.";
		}
		// is numerical value
		var tmpPrice = $("#productPrice").val().trim();
		if (!$.isNumeric(tmpPrice))
		{
			return "Insert a numerical value for Product Price.";
		}
		// convert to decimal price
		$("#productPrice").val(parseFloat(tmpPrice).toFixed(2));
		
		// Quantity
		
		if ($("#quanti").val().trim() == "")
		{
			return "Insert Quantity.";
		}
		// is numerical value
		var tmpQunatity = $("#quanti").val().trim();
		if (!$.isNumeric(tmpQunatity))
		{
			return "Insert a numerical value for Quantity.";
		}
		
		// DESCRIPTION
		
		if ($("#des").val().trim() == "")
		{
			return "Insert Product Description.";
		}
		
		// Researcher Name
		if ($("#researcherName").val().trim() == "")
		{
			return "Insert Researcher Name.";
		}
return true;
}

