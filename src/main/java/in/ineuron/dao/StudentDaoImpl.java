package in.ineuron.dao;

import in.ineuron.dao.dboperations.InsertStudent;
import in.ineuron.dao.dboperations.SelectStudent;
import in.ineuron.dao.dboperations.UpdateStudent;
import in.ineuron.dto.Student;

public class StudentDaoImpl implements IStudentDao
{

	// Object to insert student details
	private InsertStudent insertStudent;

	// Object to select existing student details
	private SelectStudent selectStudent;
	
	
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
		System.out.println("StudentDaoImpl.findById().............\n");
		selectStudent = SelectStudent.getSelectStudent();
		return selectStudent.findById(sid);
	}

	@Override
	public String updateById(Student student)
	{
		System.out.println("StudentDaoImpl.updateById()................/n");
		
		// passing to UpdateStudent module
		UpdateStudent updateStudent = UpdateStudent.getUpdateStudent();
		return updateStudent.updateById(student);
	}

	@Override
	public String deleteById(Integer sid)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
