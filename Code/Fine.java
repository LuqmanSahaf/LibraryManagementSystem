import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Fine{
	
	int fineID;
	int resourceID;
	int userID;
	int fine;
	
	//Constructor for Fine class...
	Fine(int _resID,int userID,int _fine){
		fineID = Library.nextFineID;
		Library.nextFineID++;
		this.resourceID = _resID;
		this.fine = _fine;
		this.userID = userID;
	}
	
	//to update fine.
	void updateFine(int fine){
		this.fine = fine;
	}
	
}