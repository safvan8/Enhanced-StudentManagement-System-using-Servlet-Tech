package in.ineuron.dto;

public class Student
{
	private Integer sid;
	private String sname;
	private Integer sage;
	private String saddress;

	static
	{
		System.out.println("Student class loading.................");
	}

	public Student()
	{
		System.out.println("Student object creation.....");
	}

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
		return saddress;
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

	public void setSaddress(String saddress)
	{
		this.saddress = saddress;
	}

	@Override
	public String toString()
	{
		return "Student [sid=" + sid + ", sname=" + sname + ", sage=" + sage + ", saddress=" + saddress + "]";
	}

}
