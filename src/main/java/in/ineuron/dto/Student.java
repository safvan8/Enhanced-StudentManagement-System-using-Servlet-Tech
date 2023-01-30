package in.ineuron.dto;

public class Student
{
	private Integer sid;
	private String sname;
	private Integer sage;
	private String saddr;

	// getters
	public Integer getSid()
	{
		return sid;
	}

	public String getSname()
	{
		return sname;
	}

	public Integer getSage()
	{
		return sage;
	}

	public String getSaddr()
	{
		return saddr;
	}

	// setters
	public void setSid(Integer sid)
	{
		this.sid = sid;
	}

	public void setSname(String sname)
	{
		this.sname = sname;
	}

	public void setSage(Integer sage)
	{
		this.sage = sage;
	}

	public void setSaddr(String saddr)
	{
		this.saddr = saddr;
	}

}
