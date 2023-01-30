package in.ineuron.service;

import in.ineuron.dto.Student;

public interface IStudentService
{
	public abstract String save(); // INSERT new student record

	public abstract Student findById(); // select one student details and display to end user

	public abstract String updateById(); // Update existing students details
	
	public abstract String deleteById(); // delete existing student details
}
