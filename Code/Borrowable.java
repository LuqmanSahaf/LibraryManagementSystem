import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * This interface should be implemented by the Book and CoursePack classes
 * 
 */
public class Borrowable extends LibraryResource{

		Vector<Integer> requests = new Vector<Integer>();
		Date issueDate;
		Date dueDate;
		int issuedTo;
		int relatedFineID;
		
		boolean available;
		
		
    /**
     * should print the list of pending requests that have been made for the given resource
     */
    void viewRequests(){
			if(requests.size() == 0){
				System.out.println("There are no pending requests!");
			}
			else{
				String sp = "\t\t\t";
				System.out.println("Following are the pending requests for this resource:\n\nNo."+sp+"userID");
				for(int i=0;i<requests.size();i++){
					System.out.println((i+1)+"."+sp+ (int)requests.elementAt(i));
				}
			}
		}

    /**
     * check if the given item is availab;e in the library
     * @return should return 0 if the resource is available, return 1 if issued (not available)
     */
    boolean checkStatus(){
			return available;
		}

    /**
     * will issue the resource with the given ID to the user with the given ID
     * @param userID unique id of the user to whom we want to issue the resource
     * @return return true if successful else return false
     *
     */
    boolean issueResource(int userID){
			return true;
		}

    /**
     * return the resource to the library
     * NOTE: if there are pending requests for this resource, automatically issue it to the first requester
     * @return return true if successful else return false
     */
    boolean returnResource(){
			
			
			if(requests.size() > 0){
				Library lib = Library.getInstance("LUMS Library");
				Borrower borrower =  (Borrower)(lib.findUser((int)requests.elementAt(0)) );
				requests.remove(0);
				borrower.issuedResources.addElement(this.resourceID);
				this.issueResource(borrower.userID);
				borrower.withdrawRequest(this.resourceID);
			}
			else{
				issueDate = null;
				dueDate = null;
				available = true;
				issuedTo = -1;
			}
			return true;
		}

    /**
     * set the issue date of the resource to the given date the date format is mm/dd/yyyy
     * @param date date in the mm/dd/yyyy format
     */
		 
		 void setIssueDate(String date){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			try{
				issueDate = dateFormat.parse(date);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		 
    
    /**
     *
     * @return returns the issue date of the resource. returns empty string is not issued
     */
    
		String getIssueDate(){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			return dateFormat.format(issueDate);
		}

    /**
     *
     * @return returns the return date of the resource. returns empty string is not issued
     */
    
		String getReturnDate(){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			return dateFormat.format(dueDate);
		}
		
		//*****************************//
		
		Date getReturnDate1(){
			if(dueDate == null)
				return null;
			else
				return dueDate;
		}
		
		Date getIssueDate1(){
			if(issueDate == null)
				return null;
			else
				return issueDate;
		}
		
		void setIssueDate(Date date){
			issueDate = date;
		}
		
		void setRelatedFine(int fineID){
			this.relatedFineID = fineID;
		}
		
		int getRelatedFine(){
			return relatedFineID;
		}
		
		boolean removeRequest(int userID){
			for(int i=0;i<requests.size();i++){
				if(requests.get(i) == userID){
					requests.remove(i);
					return true;
				}
			}
			return false;
		}
}
