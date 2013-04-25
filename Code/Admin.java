import java.util.ArrayList;

public class Admin extends LibraryUser implements AdminInterface{
	
	public int addUser(String userName, String password, int type){
		Library lib = Library.getInstance("LUMS");
		
		if(lib.findUser(userName) != null){
			System.out.println("This username already exists! Choose Another one.");
			return -1;
		}
		
		int result = -1;
		if(type == Constants.ADMIN){
			Admin admin = new Admin(userName,password,type,false);
			result = admin.userID;
			lib.users.add(admin);
		}
		else if(type == Constants.STUDENT){
			Student student = new Student(userName,password);
			student.userID = Library.nextUserID;
			Library.nextUserID++;
			result = student.userID;
			lib.users.add(student);
		}
		else if(type == Constants.FACULTY){
			Faculty faculty = new Faculty(userName,password);
			faculty.userID = Library.nextUserID;
			Library.nextUserID++;
			result = faculty.userID;
			lib.users.add(faculty);
		}
		
		
		
		return result;
	}
 
	public boolean removeUser(int userID){
		
		Library lib = Library.getInstance("LUMS Library");
		LibraryUser user = lib.findUser(userID);
		if(user == null){
			return false;
		}
		
		// Remove the user!
		return lib.removeUser(userID);
	}
 
	public int addResource(String name, int type){
		Library lib = Library.getInstance("LUMS Library");
		int result = -1;
		if(type == Constants.BOOK){
			
			Book book = new Book(name,Library.nextResID);
			lib.resources.add(book);
			result = book.resourceID;
		}
		else if(type == Constants.COURSE_PACK){
			CoursePack pack = new CoursePack(name,Library.nextResID);
			lib.resources.add(pack);
			result = pack.resourceID;
		}
		else if(type == Constants.MAGAZINE){
			Magazine mag = new Magazine(name,Library.nextResID);
			lib.resources.add(mag);
			result = mag.resourceID;
		}
		
		Library.nextResID++;		
		return result;
	}
 
	public boolean removeResource(int resourceID){
		Library lib = Library.getInstance("LUMS Library");
		return lib.removeResource(resourceID);
			
	}
	
	Admin(String user,String pass, int typ,boolean firstInstance){
		
		this.userName = user;
		this.password = pass;
		this.type = typ;		
		this.userID = Library.nextUserID;
		Library.nextUserID++;
	}
}