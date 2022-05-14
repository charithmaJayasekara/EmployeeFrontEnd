package model; 
import java.sql.*; 


public class Employee 
{ 
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 con = 
 DriverManager.getConnection( 
 "jdbc:mysql://127.0.0.1:3306/electrogriddb", "root", "root"); 
 } 
 catch (Exception e) 
 { 
 e.printStackTrace(); 
 } 
 return con; 
 } 





public String readEmployees() 
{ 
 String output = ""; 
try
 { 
 Connection con = connect(); 
 if (con == null) 
 { 
 return "Error while connecting to the database for reading."; 
 } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Employee ID</th>" 
		 +"<th>Name</th>"
		 +"<th>Address</th>"
		 +"<th>Tel number</th>"
		 +"<th>OT Hours</th>"
		 +"<th>Salary</th>"
 + "<th>Update</th><th>Remove</th></tr>"; 
 String query = "select * from employees"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String id = Integer.toString(rs.getInt("id")); 
 String name = rs.getString("name"); 
 String address = rs.getString("adress"); 
 String telNum = Integer.toString(rs.getInt("telNum")); 
 String otHours = Integer.toString(rs.getInt("otHours")); 
 String salary = Integer.toString(rs.getInt("salary")); 
 
 // Add into the html table
 output += "<tr><td>" + id + "</td>"; 
 output += "<td>" + name + "</td>"; 
 output += "<td>" + address + "</td>"; 
 output += "<td>" + telNum + "</td>"; 
 output += "<td>" + otHours + "</td>"; 
 output += "<td>" + salary + "</td>"; 
// buttons
output += "<td><input name='btnUpdate' type='button' value='Update' "
+ "class='btnUpdate btn btn-secondary' data-id='" + id + "'></td>"
+ "<td><input name='btnRemove' type='button' value='Remove' "
+ "class='btnRemove btn btn-danger' data-id='" + id + "'></td></tr>"; 
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
catch (Exception e) 
 { 
 output = "Error while reading the employees."; 
 System.err.println(e.getMessage()); 
 } 
return output; 
}






public String insertEmployee(String id, String name, 
		 String address, String telNum, String otHours, String salary) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for inserting."; 
		 } 
		 // create a prepared statement
		 String query = " insert into employees (`id`,`name`,`address`,`telNum`,`otHours`,`salary`)"
		 + " values (?, ?, ?, ?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, name); 
		 preparedStmt.setString(3, address); 
		 preparedStmt.setString(4, telNum); 
		 preparedStmt.setString(5, otHours); 
		 preparedStmt.setString(6, salary); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newEmployees = readEmployees(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newEmployees + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the employee.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		





public String updateEmployee(String id, String name, String address, String telNum,
		 String otHours, String salary) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for updating."; 
		 } 
		 // create a prepared statement
		 String query = "UPDATE employees SET id=?,name=?,address=?,telNum=?,otHours=?,salary=? WHERE id=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, id); 
		 preparedStmt.setString(2, name);
		 preparedStmt.setString(3, address);
		 preparedStmt.setString(4, telNum);
		 preparedStmt.setString(5, otHours);
		 preparedStmt.setString(6, salary);
		 preparedStmt.setInt(7, Integer.parseInt(id)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newEmployees = readEmployees(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newEmployees + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while updating the employee.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		
		
		
		
		
		public String deleteEmployee(String id) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for deleting."; 
		 } 
		 // create a prepared statement
		 String query = "delete from employees where id=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(id)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newEmployees = readEmployees(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newEmployees + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the employee.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		}


