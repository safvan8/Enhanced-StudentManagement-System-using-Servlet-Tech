package in.ineuron.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/controller/*")
public class ControllerServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	

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
		if(uri.endsWith("addform"))
		{
			System.out.println("done");
		}
	}

}
