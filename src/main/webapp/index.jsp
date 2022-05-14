<%@page import="model.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
if (request.getParameter("itemCode") != null) 
{ 
 Employee employeeObj = new Employee(); 
 String stsMsg = ""; 
//Insert--------------------------
if (request.getParameter("hididSave") == "") 
 { 
 stsMsg = employeeObj.insertEmployee(request.getParameter("id"),
 request.getParameter("name"),
 request.getParameter("address"), 
 request.getParameter("telNum"),
 request.getParameter("otHours"),
 request.getParameter("salary")); 
 } 
else//Update----------------------
 { 
 stsMsg = employeeObj.updateEmployee(request.getParameter("hididSave"), 
 request.getParameter("name"), 
 request.getParameter("address"), 
 request.getParameter("telNum"), 
 request.getParameter("otHours"), 
 request.getParameter("salary")); 
 } 
 session.setAttribute("statusMsg", stsMsg); 
} 
//Delete-----------------------------
if (request.getParameter("hididDelete") != null) 
{ 
 Employee itemObj = new Employee(); 
 String stsMsg = 
 itemObj.deleteEmployee(request.getParameter("hididDelete")); 
 session.setAttribute("statusMsg", stsMsg); 
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Implementation Service</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/items.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Employee Management </h1>
<form id="formEmployee" name="formEmployee">
 Name: 
 <input id="name" name="name" type="text" 
 class="form-control form-control-sm">
 <br> Address: 
 <input id="address" name="address" type="text" 
 class="form-control form-control-sm">
 <br> Telephone Number: 
 <input id="telNum" name="telNum" type="text" 
 class="form-control form-control-sm">
 <br> OT Hours: 
 <input id="otHours" name="otHours" type="text" 
 class="form-control form-control-sm">
 <br> Salary: 
 <input id="salary" name="salary" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave" 
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Employee employeeObj = new Employee(); 
 out.print(employeeObj.readEmployees()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>
