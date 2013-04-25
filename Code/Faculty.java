import java.util.*;

class Faculty extends Borrower{
	
	Faculty(String name,String pass){
		this.userName = name;
		this.password = pass;
		this.type = Constants.FACULTY;
		this.maxResources = 6;
		this.fines = new HashSet<Fine>();
	}
	
}