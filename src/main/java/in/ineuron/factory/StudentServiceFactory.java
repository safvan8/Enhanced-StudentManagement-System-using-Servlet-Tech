package in.ineuron.factory;

import in.ineuron.service.IStudentService;
import in.ineuron.service.StudentServiceImpl;

public class StudentServiceFactory
{
	
	private static IStudentService studentService;
	
	// to avoid object creation outside class
	 private StudentServiceFactory()
	 {}
	 
	 public static IStudentService getStudentService()
	 {
		 System.out.println("StudentServiceFactory.getStudentService()...\n");
		 if (studentService==null)
		 {
			 studentService = new StudentServiceImpl();
		 }
		 return studentService;
	 }
	 
}
