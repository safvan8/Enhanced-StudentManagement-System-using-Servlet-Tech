package in.ineuron.dao.dboperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.ineuron.dto.Student;
import in.ineuron.util.JdbcUtil;

public class SelectStudent
{
	private static SelectStudent selectStudent = null;
	private Connection connection;
	private String sqlSelectQuery=null;
	

	// restrict Object creation outside class
	private SelectStudent()
	{
	}

	// factory method- singleton
	public static SelectStudent getSelectStudent()
	{
		if (selectStudent == null)
			selectStudent = new SelectStudent();

		return selectStudent;
	}

	public Student findById(Integer sid)
	{
		System.out.println("SelectStudent.findById()...................\n");
		
		sqlSelectQuery = "SELECT * FROM schooldbo.student_tab3 where sid=?";
		connection = JdbcUtil.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;;

		// to store result feched from select query
		Student resultant_student_obj =null;

		if (connection != null)
		{
			try
			{ // creating preparedStatement and setting placeholder
				preparedStatement = connection.prepareStatement(sqlSelectQuery);
				preparedStatement.setInt(1, sid);
				
				// executing query;
				if (preparedStatement != null)
				{
					
					resultSet = preparedStatement.executeQuery();
					
					// storing the result to new Student Object
					if (resultSet.next())
					{
						// only create stdent object if value is found
						resultant_student_obj=new Student();
					
						resultant_student_obj.setSid(resultSet.getInt(1));
						resultant_student_obj.setSname(resultSet.getString(2));
						resultant_student_obj.setSage(resultSet.getInt(3));
						resultant_student_obj.setSaddress(resultSet.getString(4));

					}
				}
				
			} catch (SQLException e)
			{
				e.printStackTrace();
			}

		}
		System.out.println("SelectStudent.findById().................end");
		return resultant_student_obj;
	}
}
