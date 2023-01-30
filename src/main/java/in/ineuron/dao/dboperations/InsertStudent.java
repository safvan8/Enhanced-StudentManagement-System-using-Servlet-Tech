package in.ineuron.dao.dboperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.ineuron.dto.Student;
import in.ineuron.util.JdbcUtil;

// class to perform insert operations
public class InsertStudent
{
	private static InsertStudent insertStudent = null;

	private Connection connection = null;
	private PreparedStatement preparedStatement;

	private String sqlInsertQuery = null;

	// making class singleton
	private InsertStudent()
	{
	}

	public static InsertStudent getInsertStudent()
	{
		System.out.println("InsertStudent.getInsertStudent()...........\n");

		if (insertStudent == null)
			insertStudent = new InsertStudent();

		return insertStudent;
	}

	// to insert student record in to DB
	public String save(Student student)
	{
		System.out.println("InsertStudent.save()................\n");

		String queryExecutionStatus = "";

		sqlInsertQuery = "INSERT INTO schooldbo.student_tab3 (`sname`,`sage`,`saddress`)  VALUES (?,?,?)";

		// getting connection and preparing statement
		connection = JdbcUtil.getConnection();

		if (connection != null)
		{
			int rowsAffected = 0;

			try
			{
				preparedStatement = connection.prepareStatement(sqlInsertQuery);

				if (preparedStatement != null)
				{
					// setting values to preparedStatement
					preparedStatement.setString(1, student.getSname());
					preparedStatement.setInt(2, student.getSage());
					preparedStatement.setString(3, student.getSaddr());

					// executing query
					rowsAffected = preparedStatement.executeUpdate();
				}

			} catch (SQLException e)
			{
				e.printStackTrace();
			}

			if (rowsAffected == 1)
				queryExecutionStatus = "success";
			else
				queryExecutionStatus = "failed";

		}
		return queryExecutionStatus;
	}
}
