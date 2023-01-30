package in.ineuron.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
				System.out.println("record inserted successfully...");
			else if (status.equals("failed"))
				System.out.println("Record insertion failed........");
			else
				System.out.println("Something went wrong.....");
		}

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
			}
			else
			{
				System.out.println("record not avaialble......SELECT..");
			}
		}
	}

}
