package in.ineuron.service;

import in.ineuron.dao.IStudentDao;
import in.ineuron.dto.Student;
import in.ineuron.factory.StudentDaoFactory;
import in.ineuron.factory.StudentServiceFactory;

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
		System.out.println("StudentServiceImpl.updateById()................../n");

		// passing to dao layer
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.updateById(student);
	}

	@Override
	public String deleteById(Integer sid)
	{
		// TODO Auto-generated method stub
		return null;
	}

}