import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CoursePack extends Borrowable{
	
	boolean issueResource(int userID){
			issuedTo = userID;
			available = false;
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_YEAR,-1);
			dueDate = cal.getTime();
			cal.add(Calendar.DAY_OF_YEAR,-2);
			issueDate = cal.getTime();
			System.out.println(issueDate);
			return true;
	}
	
	CoursePack(String resName,int resID){
		this.resourceName = resName;
		this.resourceID = resID;
		this.available = true;
		this.issuedTo = -1;
		this.type = Constants.COURSE_PACK;
	}
}