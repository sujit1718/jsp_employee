package jsp_employee;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet{
	
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		EmployeeCRUD crud = new EmployeeCRUD();
		try {
			String dbpassword = crud.loginEmployee(email);
			if(dbpassword != null) {
				if(dbpassword.equals(password)) {
	                //create cookie
					Cookie cookie = new Cookie("userId", email);
					resp.addCookie(cookie);
					
					Cookie cookie2 = new Cookie("userId1", "sujit");
					resp.addCookie(cookie2);
					
					Cookie cookie3 = new Cookie("userId2", "8767393782");
					resp.addCookie(cookie3);
					
					Cookie cookie4 = new Cookie("userId3", "Sujitb");
					resp.addCookie(cookie4);
					
					// Create HttpSession
					HttpSession httpSession = req.getSession();
					httpSession.setAttribute("session", email);
					
					List<Employee> list = crud.displayEmployees();
				    req.setAttribute("list", list);
					RequestDispatcher dispatcher = req.getRequestDispatcher("success.jsp");
					dispatcher.include(req, resp);
					
				}
				else {
					req.setAttribute("message", "Invalid Password!!!");
					RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
					dispatcher.include(req, resp);
				}
			}else {
				req.setAttribute("message", "User Not Resgistered!! Please Register!!!");
				//resp.sendRedirect("signup.jsp");
				RequestDispatcher dispatcher = req.getRequestDispatcher("signup.jsp");
				dispatcher.include(req, resp);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
