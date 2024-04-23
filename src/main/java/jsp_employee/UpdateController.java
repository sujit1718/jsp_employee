package jsp_employee;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/update")
public class UpdateController extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		EmployeeCRUD crud = new EmployeeCRUD();
		try {
			Employee employee = crud.findEmployee(id);
			if(employee!=null) {
				
				//Validating Session
				HttpSession httpSession = req.getSession();
				String value = (String)httpSession.getAttribute("session");
				if(value!=null) {
				req.setAttribute("emp", employee);
				req.getRequestDispatcher("edit.jsp").forward(req, resp);
				}else {
					req.setAttribute("message", "No session found!!! Please login!");
					req.getRequestDispatcher("login.jsp").forward(req, resp);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		long phone = Long.parseLong(req.getParameter("phone"));
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String designation = req.getParameter("designation");
		double salary = Double.parseDouble(req.getParameter("salary"));
		
       Employee emp = new Employee() ;
		
		emp.setId(id);
		emp.setName(name);
		emp.setPhone(phone);
		emp.setEmail(email);
		emp.setPassword(password);
		emp.setDesignation(designation);
		emp.setSalary(salary);
		EmployeeCRUD crud = new EmployeeCRUD();
	    try {
	    	
			int output = crud.updateEmployee(emp) ;
			if(output!=0) {
				//Using Cookies to do Session Tracking.
				
				Cookie[] cookies = req.getCookies();
				String value = null;
				for(Cookie cookie : cookies) {
					if(cookie.getName().equals("userId")) {
						value = cookie.getValue();
						break;
					}
				}
				req.setAttribute("cookie", value);

				req.setAttribute("list", crud.displayEmployees());
				
				req.getRequestDispatcher("success.jsp").forward(req, resp);
			}
		} 
	    catch (Exception e) {
			e.printStackTrace();
		}
	}
}
	
	

