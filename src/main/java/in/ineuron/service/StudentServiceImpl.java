package in.ineuron.service;

import in.ineuron.dao.IStudentDao;
import in.ineuron.dto.Student;
import in.ineuron.factory.StudentDaoFactory;

public class StudentServiceImpl implements IStudentService
{

	private IStudentDao studentDao;

	@Override
	public String save(Student student)
	{
		System.out.println("StudentServiceImpl.save()............\n");

		// creating object of Dao and passing to Dao
		studentDao = StudentDaoFactory.getStudentDao();

		return studentDao.save(student);
	}

	@Override
	public Student findById(Integer sid)
	{
		System.out.println("StudentServiceImpl.findById()..................../n");

		// passing to Dao
		studentDao = StudentDaoFactory.getStudentDao();

		return studentDao.findById(sid);

	}

	@Override
	public String updateById(Student student)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteById(Integer sid)
	{
		// TODO Auto-generated method stub
		return null;
	}

}