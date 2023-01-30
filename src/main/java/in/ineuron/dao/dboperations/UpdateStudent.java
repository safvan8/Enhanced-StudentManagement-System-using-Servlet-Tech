package in.ineuron.dao.dboperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import in.ineuron.dto.Student;
import in.ineuron.util.JdbcUtil;

// to update existing student details
public class UpdateStudent
{
	private static UpdateStudent updateStudent = null;
	private static Connection connection = null;
	private static PreparedStatement preparedStatement;

	// restrict student creation outside object
	private UpdateStudent()
	{
	}

	public static UpdateStudent getUpdateStudent()
	{
		if (updateStudent == null)
			updateStudent = new UpdateStudent();
		return updateStudent;
	}

	public String updateById(Student student)
	{
		System.out.println("UpdateStudent.updateById()......................");
		String sqlUpdateQuery = "UPDATE schooldbo.student_tab3 SET `sname`=?,`sage`=?,`saddress`=? WHERE `sid` =?";
		Integer rowsAffected = 0;
		String updateExecutionResult = "";
		// getting connection
		connection = JdbcUtil.getConnection();
		if (connection != null)
		{
			try
			{
				preparedStatement = connection.prepareStatement(sqlUpdateQuery);
				
				// setting placeholders for preparedStatement
				preparedStatement.setString(1, student.getSname());
				preparedStatement.setInt(2, student.getSage());
				preparedStatement.setString(3, student.getSadress());
				preparedStatement.setInt(4, student.getSid());
				
				System.out.println("prepared Statement Object is :: "+preparedStatement );
				
				if (preparedStatement != null)
				{
					// execute Query
					rowsAffected = preparedStatement.executeUpdate();
				}
			} catch (SQLException e)
			{

				e.printStackTrace();
			}
		}
		if (rowsAffected == 1)
			updateExecutionResult = "success";
		else
			updateExecutionResult = "failed";

		return updateExecutionResult;
	}
}
