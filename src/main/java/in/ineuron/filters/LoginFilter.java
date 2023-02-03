package in.ineuron.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.wrappers.CustomRequest;

@WebFilter("*")
public class LoginFilter extends HttpFilter
{
	private static final long serialVersionUID = -3942536478244436604L;

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException
	{
		// Creating CustomRequest Object with original request object 
		CustomRequest customRequest = new CustomRequest(req);
		
		chain.doFilter(customRequest, res);
	}

}
