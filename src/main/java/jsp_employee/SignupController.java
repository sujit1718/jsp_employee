package jsp_employee;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignupController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		long phone = Long.parseLong(req.getParameter("phone"));
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String designation = req.getParameter("designation");
		double salary = Double.parseDouble(req.getParameter("salary"));

		Employee emp = new Employee();
		emp.setId(id);
		emp.setName(name);
		emp.setPhone(phone);
		emp.setEmail(email);
		emp.setPassword(password);
		emp.setDesignation(designation);
		emp.setSalary(salary);

		EmployeeCRUD crud = new EmployeeCRUD();
		try {
			int result = crud.signupEmployee(emp);
			if (result != 0) {
				req.setAttribute("message", "SignUP Successful !!!!");

				RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
				dispatcher.forward(req, resp);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}


