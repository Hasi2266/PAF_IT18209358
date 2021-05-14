/**
 * 
 */
package com.productManagement.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import com.productManagement.util.*;

/**
 * @author HASINI
 *
 */

public class Product {
	
	public String insertProduct(String code, String name, String price, String quantity, String des, String resName)
	{
		String output = "";
		
		try
		{
			Connection connection;
			connection = DBConnection.connect();
			
			if(connection == null) {
				return "error while connecting to the database";
			}
			
			String query = "INSERT INTO product("
					+"product_Id,"
					+"productCode,"
					+"productName,"
					+"productPrice,"
					+"quantity,"
					+"productDes,"
					+"resName)"
					+"VALUES(?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, name);
			preparedStmt.setDouble(4, Double.parseDouble(price));
			preparedStmt.setInt(5, Integer.parseInt(quantity));
			preparedStmt.setString(6, des);
			preparedStmt.setString(7, resName);
			
			preparedStmt.execute();
			connection.close();
			
			String newProducts = readProducts();
			output = "{\"status\":\"success\", \"data\": \"" +
			newProducts + "\"}";
			
		}catch(Exception e){
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the products.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readProducts() {
		
		String output = "";
		
		try {
			Connection connection = DBConnection.connect();
			if(connection == null) {
				return "Error while connecting to db";
			}
			
			output = "<table border='1'> <tr><th>Product Code</th>"
					+ "<th>Product Name</th><th>Product Price</th>"
					+ "<th>Quantity</th><th>Description</th>"
					+ "<th>Research Name</th><th>Update</th><th>Delete</th></tr>";
			
			String query = "select * from product";
			
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next())
			{
				String productID = Integer.toString(rs.getInt("product_id"));
				String pCode = rs.getString("productCode");
				String pName = rs.getString("productName");
				String pPrice = rs.getString("productPrice");
				String quanti = rs.getString("quantity");
				String des = rs.getString("productDes");
				String res = rs.getString("resName");
				
				output += "<tr><td><input id='hidProductIDUpdate' name='hidProductUpdate' type='hidden' value = '" + productID
						+ "'>" + pCode + "</td>";
						
				output += "<td>" + pName + "</td>";
				output += "<td>" + pPrice + "</td>";
				output += "<td>" + quanti + "</td>";
				output += "<td>" + des + "</td>";
				output += "<td>" + res + "</td>";
				
				output += "<td><input name='btnUpdate' type = 'button' value = 'Update' class = 'btnUpdate btn btn-secondary'></td>"
						+ "<td><input name = 'btnRemove' type = 'button' value = 'Remove' "
						+ "class = 'btnRemove btn btn-danger' data-itemid='" + productID + "'></td></tr>";
						
						
			}
			
			connection.close();
			
			output += "</table>";
			
		}
		catch(Exception e)
		{
			output = "Error while reading the products";
			System.err.println(e.getMessage());
		}
		
		return output;
		
	}
	
	public String deleteProduct(String productID) {
		
		String output = "";
		
		try {
			
			Connection connection = DBConnection.connect();
			
			if(connection == null) {
				return "Error while connecting to db";
			}
			
			String query = "delete from product where product_id=?";
			
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			
			preparedStmt.setInt(1, Integer.parseInt(productID));
			
			preparedStmt.execute();
			connection.close();
			
			String newItems = readProducts();
			output = "{\"status\":\"success\", \"data\": \"" +
			newItems + "\"}";
	
		}catch(Exception e) {
			"{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
			System.out.println(e.getMessage());
		}
		
		return output;
	}
	
	public String updateProduct(String pid, String code, String name, String price, String quanti, String des, String resName ) {
		
		String output = "";
		
		try {
			Connection connection = DBConnection.connect();
			
			if(connection == null) {
				return "Error while connecting to db";
			}
			
			String query = "update product set productCode=?,productName=?,productPrice=?,quantity=?,productDes=?,resName=? where product_id=?";
			
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			
			preparedStmt.setString(1, code);
			preparedStmt.setString(2, name);
			preparedStmt.setDouble(3, Double.parseDouble(price));
			preparedStmt.setInt(4, Integer.parseInt(quanti));
			preparedStmt.setString(5, des);
			preparedStmt.setString(6, resName);
			preparedStmt.setInt(7, Integer.parseInt(pid));
			
			preparedStmt.execute();
			connection.close();
			
			String newItems = readProducts();
			output = "{\"status\":\"success\", \"data\": \"" +
			newItems + "\"}";
			
		}catch(Exception e){
			output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	

}
