import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Book extends Borrowable{
	
	boolean issueResource(int userID){
			Library lib = Library.getInstance("LUMS Library");
			LibraryUser user = lib.findUser(userID);
			int daysToIssue;
			if(user.type == Constants.FACULTY){
				daysToIssue = 30;
			}
			else if(user.type == Constants.STUDENT){
				daysToIssue = 15;
			}
			else{
				return false;
			}
			issuedTo = userID;
			available = false;
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_YEAR,-1);
			dueDate = cal.getTime();
			cal.add(Calendar.DAY_OF_YEAR,-daysToIssue-1);
			issueDate = cal.getTime();
			System.out.println(issueDate);
			return true;
	}
	
	boolean renewResource(int days){
		
		if(requests.size() == 0){
			Calendar cal = Calendar.getInstance();
			
			cal.add(Calendar.DAY_OF_YEAR,days);
			dueDate = cal.getTime();
			System.out.println("The new due Date is: " + getReturnDate());
			return true;
		}
		else{
			return false;
		}
	}
	
	Book(String resName,int resID){
		this.resourceID = resID;
		this.resourceName = resName;
		this.available = true;
		this.issuedTo = -1;
		this.type = Constants.BOOK;
	}
}