package in.ineuron.wrappers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CustomRequest extends HttpServletRequestWrapper
{
	private HttpServletRequest orig_request;

	public CustomRequest(HttpServletRequest orig_request)
	{
		super(orig_request);

		this.orig_request = orig_request;
	}
	
	// to generate Custom request Object - add @ineuron.ai 
	// if it is not present in email field of login form
	@Override
	public String getParameter(String paramName)
	{
		/// finding paramValue using paramName
		String paramValue = orig_request.getParameter(paramName);
		
		String modifiedMail=null;
		
		if (paramName.equals("email"))
		{
			if (paramValue.toLowerCase().endsWith("@ineuron.ai"))
				modifiedMail= paramValue;
			else
				modifiedMail= paramName + "@ineuron.ai";
		}
		return modifiedMail;
	}
}
