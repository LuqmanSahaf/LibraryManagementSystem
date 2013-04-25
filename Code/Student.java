import java.util.*;

public class Student extends Borrower{
	
	Student(String name,String pass){
		this.userName = name;
		this.password = pass;
		this.type = Constants.STUDENT;
		this.maxResources = 3;
		this.fines = new HashSet<Fine>();
	}
	
}