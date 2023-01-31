package in.ineuron.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.dto.Student;
import in.ineuron.factory.StudentServiceFactory;
import in.ineuron.service.IStudentService;

@WebServlet(urlPatterns = "/controller/*", loadOnStartup = 1)
public class ControllerServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private IStudentService studentService = null;

	static
	{
		System.out.println("Servlet loading....");
	}

	public ControllerServlet()
	{
		System.out.println("Servlet instantation....");
	}

	@Override
	public void init() throws ServletException
	{
		System.out.println("Servlet instantiation.");
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doProcess(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		doProcess(req, resp);
	}

	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		System.out.println("ControllerServlet.doProcess()...........");

		// getting request uri
		String uri = request.getRequestURI();
		System.out.println("uri is... " + uri);

		// dispatiching to layout.html
		RequestDispatcher rd = null;
		if (uri.endsWith("layout"))
		{
			rd = request.getRequestDispatcher("../layout.html");
			rd.forward(request, response);
		}

		// to perform add operation [INSERT] :
		if (uri.endsWith("addform"))
		{
			System.out.println("insert new Student details............");

			// getting user input
			String sname = request.getParameter("sname");
			Integer sage = Integer.parseInt(request.getParameter("sage"));
			String saddress = request.getParameter("saddress");

			// creating object to pass to service layer
			Student student = new Student();
			student.setSname(sname);
			student.setSage(sage);
			student.setSaddress(saddress);

			// passing Student object to servcie layer
			studentService = StudentServiceFactory.getStudentService();

			String status = studentService.save(student);

			if (status.equals("success"))
			{
				System.out.println("record inserted successfully...");
				rd = request.getRequestDispatcher("htt/success.html");
				rd.forward(request, response);

			} else if (status.equals("failed"))
			{
				rd = request.getRequestDispatcher("../notfound.html");
				rd.forward(request, response);
				System.out.println("Record insertion failed........");
			} else
			{
				rd = request.getRequestDispatcher("../failed.html");
				rd.forward(request, response);
				System.out.println("Something went wrong.....");
			}
		}

		// to perform add operation [READ] :
		if (uri.endsWith("searchform"))
		{
			System.out.println("READ existing student details");

			Integer sid = Integer.parseInt(request.getParameter("sid"));

			// apssing sid to service layer
			studentService = StudentServiceFactory.getStudentService();

			Student resultant_student_obj = studentService.findById(sid);

			// checking value fetched and displying
			if (resultant_student_obj != null)
			{
				System.out.println("Student details fetached -Record Avaialable ");
				System.out.println(resultant_student_obj);

				PrintWriter out = response.getWriter();
				out.println("<html><head><title>STUDENT DATA</title></head>");
				out.println("<body bgcolor='lightblue'>");
				out.println("<br/><br/><br/>");
				out.println("<table align='center' border='1'>");
				out.println("<tr>");
				out.println("<th>SID</th>");
				out.println("<th>SNAME</th>");
				out.println("<th>SAGE</th>");
				out.println("<th>SADDRESS</th>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<td>" + resultant_student_obj.getSid() + "</td>");
				out.println("<td>" + resultant_student_obj.getSname() + "</td>");
				out.println("<td>" + resultant_student_obj.getSage() + "</td>");
				out.println("<td>" + resultant_student_obj.getSadress() + "</td>");
				out.println("</tr>");

				out.println("</table>");
				out.println("</body>");
				out.println("</html>");

				out.close();

			} else
			{
				System.out.println("record not avaialble......SELECT..");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("../notfound.html");
				requestDispatcher.forward(request, response);
			}
		}

		// to display Exising data in editform ,before update [UPDATE] :
		if (uri.endsWith("editform"))
		{
			System.out.println("to get Existing student details before updation...........");

			// getting id enterd by user
			Integer sid = Integer.parseInt(request.getParameter("sid"));

			studentService = StudentServiceFactory.getStudentService();

			// checking whether student is existing or not
			Student student_record_check = studentService.findById(sid);

			// checking whether the record existing in db or not
			if (student_record_check != null)
			{
				System.out.println("record found");

				response.setContentType("text/html");

				// display editpage using html
				PrintWriter out = response.getWriter();
				out.println("<html><head><title>OUTPUT</title></head>");
				out.println("<body bgcolor='lightblue'>");
				out.println("<br/><br/><br/>");
				// already inside controller , that's why ,mentioned on "./updateform"
				out.println("<form method='post' action='./updateform'>");
				out.println("<table align='center'>");
				out.println("<tr><th>ID</th><td>" + student_record_check.getSid() + "</td></tr>");
				out.println("<input type='hidden' name='sid' value='" + student_record_check.getSid() + "'/>");
				out.println("<tr><th>NAME</th><td><input type='text' name='sname' value='"
						+ student_record_check.getSname() + "'/></td></tr>");
				out.println("<tr><th>AGE</th><td><input type='text' name='sage' value='"
						+ student_record_check.getSage() + "'/></td></tr>");
				out.println("<tr><th>ADDRESS</th><td><input type='text' name='saddress' value='"
						+ student_record_check.getSadress() + "'/></td></tr>");
				out.println("<tr><td></td><td><input type='submit' value='update'/></td></tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				out.close();
			} else
			{
				System.out.println("no record found");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("../notfound.html");
				requestDispatcher.forward(request, response);
			}
		}

		// to perform update operation [UPDATE] :
		if (uri.endsWith("updateform"))
		{
			Integer sid = Integer.parseInt(request.getParameter("sid"));
			String sname = request.getParameter("sname");
			Integer sage = Integer.parseInt(request.getParameter("sage"));
			String saddress = request.getParameter("saddress");

			Student student_for_update = new Student();
			student_for_update.setSid(sid);
			student_for_update.setSname(sname);
			student_for_update.setSage(sage);
			student_for_update.setSaddress(saddress);

			// pasing object to service layer
			studentService = StudentServiceFactory.getStudentService();
			String status = studentService.updateById(student_for_update);

			if (status.equals("success"))
			{
				System.out.println("Student record updated.........");
				RequestDispatcher requestDispatcher =request.getRequestDispatcher("../success.html");
				requestDispatcher.forward(request, response);
			} else
			{
				RequestDispatcher requestDispatcher = request.getRequestDispatcher( "../failed.html");
				requestDispatcher.forward(request, response);
				System.out.println("student record update Failed.........");
			}
		}

	}

}
