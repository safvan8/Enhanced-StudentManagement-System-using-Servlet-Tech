package in.ineuron.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ineuron.wrappers.CustomRequest;

public class LoginFilter extends HttpFilter
{
	private static final long serialVersionUID = -3942536478244436604L;

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException
	{
		// Creating CustomRequest Object with original request object
		CustomRequest customRequest = new CustomRequest(req);

System.out.println("LoginFilter.doFilter()1");
		// checking whether the request is associated with any session or not
		HttpSession httpSession = req.getSession(false);

		// forward the request to servlet if session is avaialble
		if (httpSession != null)
		{
			System.out.println("LoginFilter.doFilter()2");
			System.out.println("hi ");
			chain.doFilter(customRequest, res);
			System.out.println("donee");
		}

		// authenticating to verify user creadentails
		else
		{System.out.println("LoginFilter.doFilter()3");
			String email = customRequest.getParameter("email");
			String password = customRequest.getParameter("password");

			// TODO: Check if the email and password are valid

			// write code for connecting with authentication service and verify credentials
			if ("a@ineuron.ai".equals(customRequest.getParameter("email")))
			{
				// If the email and password are valid, create a new session object
				HttpSession session = req.getSession();
				session.setAttribute("email", email);

				System.out.println("LoginFilter.doFilter()4");
				System.out.println("login success");

				// Forward the request to the servlet
				chain.doFilter(customRequest, res);
			} else
			{
				System.out.println("Login failed: ");
				
				 // If the email and password are invalid, send an error message
//	            customRequest.setAttribute("error", "Invalid email or password");
//	            req.getRequestDispatcher("/login.jsp").forward(customRequest, res);
	        }
	    }
	}

//	In this
//
//	modified doFilter() method, the email and password parameters are retrieved from the CustomRequest object using
//
//	the getParameter() method. The email and password are then checked to see if they are valid. If they are valid, a new session object is created using the getSession() method, and the email attribute is stored in the session object using the setAttribute() method. Finally, the request is forwarded to the servlet using the chain.doFilter() method.
//
//	If the email and password are invalid, an error message is stored in the CustomRequest object using the setAttribute() method, and the request is forwarded to the login.jsp page using the getRequestDispatcher()
//
//	method. The getRequestDispatcher() method is used to obtain a reference to the RequestDispatcher object for the login.jsp page, which can then be used to forward the request to that page.


		
	}


