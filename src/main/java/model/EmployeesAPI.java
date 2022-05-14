package model;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ItemsAPI
 */

@WebServlet("/EmployeesAPI")
public class EmployeesAPI extends HttpServlet {
	Employee employeeObj = new Employee(); 
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		String output = employeeObj.insertEmployee(request.getParameter("id"), 
				 request.getParameter("name"), 
				request.getParameter("address"), 
				request.getParameter("telNum"), 
				request.getParameter("otHours"), 
				request.getParameter("salary")); 
				response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException 
			{ 
			 Map paras = getParasMap(request); 
			 String output = employeeObj.updateEmployee(paras.get("hididSave").toString(), 
			 paras.get("name").toString(), 
			paras.get("address").toString(), 
			paras.get("telNum").toString(),
			paras.get("otHours").toString(),
			paras.get("salary").toString()); 
			response.getWriter().write(output); 
			} 
	
	
	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 { 
	 String[] p = param.split("="); 
	 map.put(p[0], p[1]); 
	 } 
	 } 
	catch (Exception e) 
	 { 
	 } 
	return map; 
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException 
			{ 
			 Map paras = getParasMap(request); 
			 String output = employeeObj.deleteEmployee(paras.get("id").toString()); 
			response.getWriter().write(output); 
			}


}
