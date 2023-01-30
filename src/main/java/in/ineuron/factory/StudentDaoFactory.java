package in.ineuron.factory;

import in.ineuron.dao.IStudentDao;

public class StudentDaoFactory
{
	private static IStudentDao studentDao;

	private StudentDaoFactory()
	{
	}

	public static IStudentDao getStudentDao()
	{
		if (studentDao == null)
			studentDao = new in.ineuron.dao.StudentDaoImpl();

		return studentDao;
	}
}
