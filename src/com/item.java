package com;
import java.sql.*;






public class item {

	/**************Testing the connection success*****************************************************/
	public Connection connect()
	{
			Connection con = null;
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				
				con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root", "1234");
				
	
				System.out.print("Successfully connected");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return con;
	
	}
	
	/************Insert item method***********************************************************/
	
	public String insertItem(String code, String name, String price, String desc)
	{
		
		String output = "";
		
		try
		{
			
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database";
			}
	
			String query = " insert into items(`itemID`,`itemCode`,`itemName`,`itemPrice`,`itemDesc`)"+ " values (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
	
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, name);
			preparedStmt.setDouble(4, Double.parseDouble(price));
			preparedStmt.setString(5, desc);
	
	
			preparedStmt.execute();
			
			con.close();
			
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
	
	public String readItems()
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
	
			
			output = "<table border=‘1’><tr><th>Item Code</th>" +"<th>Item Name</th><th>Item Price</th>"
					+ "<th>Item Description</th>"
					+ "<th>Update</th><th>Remove</th></tr>";
			
			
			String query = "select * from items";
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
	
			while (rs.next())
			{
				
				String itemID = Integer.toString(rs.getInt("itemID"));
				
				String itemCode = rs.getString("itemCode");
				
				String itemName = rs.getString("itemName");
				
				String itemPrice = Double.toString(rs.getDouble("itemPrice"));
				
				String itemDesc = rs.getString("itemDesc");
				
	
				output += "<tr><td>" + itemCode + "</td>";
				output += "<td>" + itemName + "</td>";
				output += "<td>" + itemPrice + "</td>";
				output += "<td>" + itemDesc + "</td>";
				
	
				output += "<td><form method='post' action='updateItem.jsp'>"
						+ "<input name='btnUpdate' "
						+ " type='submit' value='Update'>"
						+ "<input name='itemID' type='hidden' "
						+ " value=' " + itemID + "'>"
						+ "<input name='itemCode' type='hidden' "
						+ " value=' " + itemCode + "'>"
						+ "<input name='itemName' type='hidden' "
						+ " value=' " + itemName + "'>"
						+ "<input name='itemPrice' type='hidden' "
						+ " value=' " + itemPrice + "'>"
						+ "<input name='itemDesc' type='hidden' "
						+ " value=' " + itemDesc + "'>"
						+ "</form></td>"
						+ "<td><form method='post' action='deleteItem.jsp'>"
						+ "<input name='btnRemove' "
						+ " type='submit' value='Remove'>"
						+ "<input name='itemCode' type='hidden' "
						+ " value='" + itemCode + "'>" + "</form></td></tr>";
			}
			
			con.close();
			
	
			output += "</table>";
	}
	catch (Exception e)
	{
		output = "Error while reading the items.";
		System.err.println(e.getMessage());
	}
		
	return output;
	
	}
	
	
/************update item method***********************************************************/
	
	public String updateItem(String itemCode, String itemName, String itemPrice, String itemDesc)
	{
		
		String output = "";
		
		try {
			
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			
			// create a prepared statement
						String query = "update item set itemName=?, itemPrice=?, itemDesc=?" + "where itemCode=? ";
						
						PreparedStatement preparedStmt = con.prepareStatement(query);
						
						// binding values
						preparedStmt.setString(3, itemName);
						preparedStmt.setDouble(4, Double.parseDouble(itemPrice));
						preparedStmt.setString(5, itemDesc);
						
						//execute the statement
						preparedStmt.execute();
						con.close();
						
						output = "Updated successfully";
			
		}catch(Exception e) {
			output = "Error while updating";
			System.err.println(e.getMessage());
		}
		return output;
		
		
	}
	
	
	
	}

