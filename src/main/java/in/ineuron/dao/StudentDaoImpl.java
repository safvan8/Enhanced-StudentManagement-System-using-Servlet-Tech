package in.ineuron.dao;

import in.ineuron.dao.dboperations.InsertStudent;
import in.ineuron.dto.Student;

public class StudentDaoImpl implements IStudentDao
{

	// Object to insert student details
	private InsertStudent insertStudent;

	@Override
	public String save(Student student)
	{
		System.out.println("StudentDaoImpl.save().............\n");
		
		insertStudent = InsertStudent.getInsertStudent();
		
		return insertStudent.save(student);
	}

	@Override
	public Student findById(Integer sid)
	{
		// TODO Auto-generated method stub
		return null;
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
