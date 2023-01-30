package in.ineuron.factory;

import in.ineuron.dao.IStudentDao;
import in.ineuron.dto.StudentDaoImpl;

public class StudentDaoFactory
{
	private static IStudentDao studentDao;

	private StudentDaoFactory()
	{
	}

	private static IStudentDao getStudentDao()
	{
		if (studentDao == null)
			studentDao = new StudentDaoImpl();

		return studentDao;
	}
}
