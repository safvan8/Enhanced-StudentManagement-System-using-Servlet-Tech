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
	private PreparedStatement preparedStatement;
	private String sqlSelectQuery;
	private ResultSet resultSet;

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
		String queryExecutionStatus = "";

		connection = JdbcUtil.getConnection();

		// to store result feched from select query
		Student resultant_student_obj = new Student();
		resultant_student_obj.setSid(sid);

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
					while (resultSet.next())
					{
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
