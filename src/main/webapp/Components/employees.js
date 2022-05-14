
$(document).ready(function() 
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateItemForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hididSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "EmployeesAPI", 
 type : type, 
 data : $("#formItem").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemSaveComplete(response.responseText, status); 
 } 
 }); 
});

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
$("#hididSave").val($(this).data("id")); 
 $("#name").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#address").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#telNum").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#otHours").val($(this).closest("tr").find('td:eq(3)').text()); 
 $("#salary").val($(this).closest("tr").find('td:eq(4)').text()); 
});

$(document).on("click", ".btnRemove", function(event) 
{ 
 $.ajax( 
 { 
 url : "EmployeesAPI", 
 type : "DELETE", 
 data : "id=" + $(this).data("id"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemDeleteComplete(response.responseText, status); 
 } 
 }); 
});
// CLIENT-MODEL================================================================
function validateItemForm() 
{ 
// NAME
if ($("#name").val().trim() == "") 
 { 
 return "Insert Name."; 
 } 
// ADDRESS
if ($("#address").val().trim() == "") 
 { 
 return "Insert address."; 
 } 
// TELNUMBER-------------------------------
if ($("#telNum").val().trim() == "") 
 { 
 return "Insert Tel Number."; 
 } 
// OTHOURS-------------------------------
if ($("#otHours").val().trim() == "") 
 { 
 return "Insert OT Hours."; 
 } 
// SALARY------------------------
if ($("#salary").val().trim() == "") 
 { 
 return "Insert salary."; 
 } 
return true; 
}

function onItemSaveComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
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
 $("#hidItemIDSave").val(""); 
 $("#formItem")[0].reset(); 
}


function onItemDeleteComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}




