package jsp_employee;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class DeleteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int id = Integer.parseInt(req.getParameter("id"));
		EmployeeCRUD crud = new EmployeeCRUD();
		try {
			int result = crud.delete(id);
			if(result != 0) {
				req.setAttribute("list", crud.displayEmployees());
				req.getRequestDispatcher("success.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
				
				
	}
	
}
