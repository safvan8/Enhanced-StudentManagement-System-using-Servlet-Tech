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

		// checking whether the request is associated with any session or not
		HttpSession httpSession = req.getSession(false);

		// forward the request to servlet if session is avaialble
		if (httpSession != null)
		{
			chain.doFilter(customRequest, res);
		}
		
		// authenticating to verify user creadentails
		else
		{
			// write code for connecting with authentication  service and verify credentials
			if (customRequest.getParameter("email").equals("safvan@ineuron.ai"))
			{
				System.out.println("login success");
				chain.doFilter(customRequest, res);
			}
			else
			{
				System.out.println("Login failed: ");
				
			}
		}
	}

}
