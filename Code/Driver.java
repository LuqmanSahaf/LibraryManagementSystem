import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;

public class Driver{

//Driver drives the Library System. It gives IO interface to use and control Library!

	Scanner sc;					// 	to scan user input initialized in main function.
	Library myLibrary;		//	Library instant initialized in main function.

	
		//Constructor for Driver
	Driver(String libName){
		sc = new Scanner(System.in);
		myLibrary = Library.getInstance(libName);
	}
	/*****************************************/
	/******** USER Login IO Interface ********/
	/*****************************************/
	void loginIO(int type){

		String name,pass;
		// template is the option list for Admin interface!
		String template = "\n\nChoose from the Following options:\n\n" +
				"- For adding an administrator account to the system, press 1\n" + "- For adding a faculty account to the system, press 2\n" +
				"- For adding a student account to the system, press 3\n" + "- For adding a book to the system, press 4\n" +
				"- For adding a course pack to the system, press 5\n" + "- For adding a magazine to the system, press 6\n" +
				"- For removing a user account from the system, press 7\n" + "- For removing a resource from the system, press 8\n" +
				"- For logging out of the system, press 9";


		//User is asked for the username and password!
		System.out.println("Enter the username:");
		name = sc.nextLine();
		System.out.println("Enter the password:");
		pass = sc.nextLine();
		LibraryUser user = myLibrary.findUser(name);

		//If user is not found, RETURN with message!
		if(user == null){
			System.out.println("The username or password was not correct!\n");
			return;
		}
		else if(!user.login(name,pass)){			//if user login fails
			return;
		}

		//If some Faculty or Student logs in with correct username and password, and is not Admin,
		//Then, login should not be attempted!
		//Or everyone should adhere to their interface... No Sniffing :P

		if(user.type != type){
			System.out.println("The username or password was not correct!");
			return;
		}
		
		
		
		/********** The User Has Logged In ****************/
		boolean done = false;
		int input;
		String userInput;

		if(type == Constants.ADMIN){

		//For Admin Login IO Interface!

			System.out.println("\n***************************************\n\nWelcome "+ name+ "!" +template);

			while(!done){

				if(!sc.hasNextInt()){
					userInput = sc.nextLine();		
					continue;
				}
				input = sc.nextInt();
				
				//hasNextInt() takes int only but not \n character!
				userInput = sc.nextLine();

				switch(input){
					case 1:
						addUserIO((Admin)user,Constants.ADMIN);		//transfers control to add user IO.
						System.out.println(template);
						break;
					case 2:
						addUserIO((Admin)user,Constants.FACULTY);	//transfers control to add user IO.
						System.out.println(template);
						break;
					case 3:
						addUserIO((Admin)user,Constants.STUDENT);	//transfers control to add user IO.
						System.out.println(template);
						break;
					case 4:
						addResourceIO((Admin)user,Constants.BOOK);	//transfers control to add resource Interface
						System.out.println(template);
						break;
					case 5:
						addResourceIO((Admin)user,Constants.COURSE_PACK);	//transfers control to add resource Interface
						System.out.println(template);
						break;
					case 6:
						addResourceIO((Admin)user,Constants.MAGAZINE);	//transfers control to add resource Interface
						System.out.println(template);
						break;
					case 7:
						removeUserIO((Admin)user);		// transfers control to remove user Interface
						System.out.println(template);
						break;
					case 8:
						removeResourceIO((Admin)user);	// transfers control to remove resource Interface
						System.out.println(template);
						break;
					case 9:
						System.out.println("Do you really want to log out? y/n");		// asks user if wants to log out
						userInput = sc.nextLine();
						if((userInput.equals("y") || userInput.equals("Y"))){
							System.out.println("Thanks... logging out!");
							user.logout();
							done = true;
						}
						else{
							System.out.println(user.userName + " still Logged in...");
						}
					break;
					default:
						System.out.println("Give the correct input");
						break;
				}
			}
		}
		// For Faculty and Student Login IO Interface.
		else{
			String template_2 = "\n\nChoose from the following options:\n\n"+
				"- For borrowing a resource, press 1\n"+
				"- For returning a resource, press 2\n"+
				"- For deleting a request, press 3\n"+
				"- For viewing issued books, press 4\n"+
				"- For viewing pending requests, press 5\n"+
				"- For viewing you fines, press 6\n"+
				"- For logging out of the system, press 7\n"+
				"- For renewing a resource, press 8\n";
			System.out.println("\n***************************************\n\nWelcome "+ name+ "!" +template_2);

			while(!done){

				if(!sc.hasNextInt()){
					System.out.println("Give the correct input");
					userInput = sc.nextLine();		//to ignore the wrong input
					continue;
				}
				input = sc.nextInt();
				
				userInput = sc.nextLine();
				switch(input){

					case 1:
						borrowIO((Borrower)user,type);		// transfers control to borrow Interface
						System.out.println(template_2);
						break;
					case 2:
						returnResourceIO((Borrower)user);		// transfers control to return resource Interface
						System.out.println(template_2);
						break;
					case 3:
						deleteRequestIO((Borrower)user);		// transfers control to delete request Interface
						System.out.println(template_2);
						break;
					case 4:
						((Borrower)user).viewIssued();			// views issued Resources by calling viewIssued function
						System.out.println(template_2);
						break;
					case 5:
						((Borrower)user).viewRequests();		// views requests of user
						System.out.println(template_2);
						break;
					case 6:
						((Borrower)user).viewFines();				// views fines to the user
						System.out.println(template_2);
						break;
					case 8:
						renewResourceIO((Borrower)user);		// transfers control to renew Resource IO Interface
						System.out.println(template_2);
						break;
					case 7:
						System.out.println("Do you really want to log out? y/n");		//asks user if it wants to log out
						userInput = sc.nextLine();
						if((userInput.equals("y") || userInput.equals("Y"))){
							System.out.println("Thanks... logging out!");
							user.logout();
							done = true;
						}
						else{
							System.out.println(user.userName + " Still Logged in...");
						}
						break;
					default:
						System.out.println("Give the correct input");
						break;
				}
			}
		}
	}
	/*****************************************/


	/*****************************************/
	/********* Add User IO Interface *********/
	/*****************************************/
	void addUserIO(Admin admin,int type){		//Only Admin is allowed to enter this method!
		
		//This function takes the userName and password of the new user and calls addUser function of Admin
		
		String name,pass;
		
		System.out.println("Enter the new username:");
		name = sc.nextLine();
		System.out.println("Enter the password:");
		pass = sc.nextLine();
		
		int id = admin.addUser(name,pass,type);
		if(id >= 0){
			System.out.println("The new userID is: " + id + "\n");
		}
		else{
			System.out.println("New user could not be created. Please try again with different username.\n");
		}
	}
	/*****************************************/


	/*****************************************/
	/******* Remove User IO Interface ********/
	/*****************************************/
	void removeUserIO(Admin admin){
	
		// This function asks for the ID or userName of user to be deleted and calls removeUser function of Admin...
	
		int id;
		String userInput;
		System.out.println("Enter the userID:");

		while(!sc.hasNextInt()){
			System.out.println("Give the correct integer input...");
			System.out.println("Enter the userID:");
			userInput = sc.nextLine();
			continue;
		}
		id = sc.nextInt();
		userInput = sc.nextLine();
		if(admin.removeUser(id))
		{
			System.out.println("User " + id + " has been successfully removed!");
		}
		else{
			System.out.println("User " + id + "was not removed!");
		}
	}
	/*****************************************/


	/*****************************************/
	/******** IO For Adding Resource *********/
	/*****************************************/

	void addResourceIO(Admin admin, int type){

		//This function asks for the Name of the Resource to be Added and calls addResource function of Admin.Java...
	
		String name;
		System.out.println("Enter the name of Resource:");
		name = sc.nextLine();
		if(admin.addResource(name,type) > -1){
			System.out.println("The resource has been added successfully");
		}
		else{
			System.out.println("The resource could not be added.\nPlease try with another name!");
		}
	}
	/*****************************************/


	/*****************************************/
	/******* IO For Removing Resource ********/
	/*****************************************/
	void removeResourceIO(Admin admin){
	
		// This function assks for the Name or ID of the Resource to be deleted and calls removeResource function of ADMIN...
		ArrayList<LibraryResource> resources = new ArrayList<LibraryResource>();
		String name,userInput;
		int id = -1;
		System.out.println("Enter the Resource Name or ID:");
		if(sc.hasNextInt()){
			id = sc.nextInt();
			userInput = sc.nextLine();
		}
		else{
			name = sc.nextLine();
			if(myLibrary.findResource(name)!=null){
				resources = myLibrary.findResource(name);
			}
		}
		for(int i =0;i<resources.size();i++){
			if(admin.removeResource(resources.get(i).getResourceID())){
				System.out.println("The resource is successfully removed!");
			}
			else{
				System.out.println("The resource is not found/removed");
			}
		}
	}
	/*****************************************/


	/*****************************************/
	/************** Borrow IO ****************/
	/*****************************************/
	void borrowIO(Borrower borrower, int type){
	
		// This function asks for the name or ID of the resource to be borrowed and calls tryIssue function of the Borrower.java
		ArrayList<LibraryResource> resources = new ArrayList<LibraryResource>();
		String name;
		int id = -1;
		System.out.println("Enter the name or ID of resource:");
		if(sc.hasNextInt()){
			id = sc.nextInt();
			name =  sc.nextLine();
		}
		else{
			name = sc.nextLine();
			if(myLibrary.findResource(name)!=null){
				resources = myLibrary.findResource(name);
			}
		}
		borrower.tryIssue(resources);
	}
	/*****************************************/


	/*****************************************/
	/******** IO for Resource Return *********/
	/*****************************************/

	void returnResourceIO(Borrower borrower){
	
		// This function asks for the name or ID of the resource to be returned and calls tryReturn function of the Borrower.java 
		ArrayList<LibraryResource> resources = new ArrayList<LibraryResource>();
		String name;
		int id = -1;
		System.out.println("Enter the name or ID of resource:");
		if(sc.hasNextInt()){
			id = sc.nextInt();
			name = sc.nextLine();
		}
		else{
			name = sc.nextLine();
			if(myLibrary.findResource(name)!=null){
				resources = myLibrary.findResource(name);
			}
		}
		
		for(int i =0;i<resources.size();i++){
			id=resources.get(i).getResourceID();
			if(borrower.findIssued(resources.get(i).getResourceID())){
				if(borrower.tryReturn(resources.get(i).getResourceID())){
					System.out.println("The requested resource has been successfully returned!");
					return;
				}
				
			}
		}
		if(borrower.findIssued(id)){
			if(borrower.tryReturn(id)){
				System.out.println("The requested resource has been successfully returned!");
			}
		}
		
		
		
		System.out.println("The resource was not returned! or it was not found!");
		
	}
	/*****************************************/


	/*****************************************/
	/******** IO for Request Deletion ********/
	/*****************************************/
	void deleteRequestIO(Borrower borrower){
	
		// This function asks for the name or ID of resource of which request is to be deleted and calls withdrawRequest of Borrower.Java...
		ArrayList<LibraryResource> resources = new ArrayList<LibraryResource>();
		int id = -1;;
		String name;
		borrower.viewRequests();
		System.out.println("\nEnter the name or ID of the Resource:");
		if(sc.hasNextInt()){
			id = sc.nextInt();
			name = sc.nextLine();
		}
		else{
			name = sc.nextLine();
			if(myLibrary.findResource(name)!=null){
				resources = myLibrary.findResource(name);
				for(int i =0;i<resources.size(); i++){
					if(borrower.withdrawRequest(resources.get(i).getResourceID())){
						System.out.println("The request was withdrawn successfully!");
						return;
					}
				}
			}
			else if(borrower.withdrawRequest(id)){
				System.out.println("The request was withdrawn successfully!");
				return;
			}
		}
		
		System.out.println("The transaction was not completed!");
	}

	/*****************************************/

	/*****************************************/
	/********* IO for Renew Resource *********/
	/*****************************************/	
	
	void renewResourceIO(Borrower borrower){
	
		// This function asks for the name or ID of resource to be renewed and calls tryRenew of Borrower.Java...
		ArrayList<LibraryResource> resources = new ArrayList<LibraryResource>();
		int id = -1;;
		String name;
		System.out.println("\nEnter the name or ID of the Resource to renew: ");
		if(sc.hasNextInt()){
			id = sc.nextInt();
			name = sc.nextLine();
		}
		else{
			name = sc.nextLine();
			for(int i =0;i<resources.size(); i++){
				if(borrower.tryRenew(resources.get(i).getResourceID())){
					System.out.println("The request was withdrawn successfully!");
					return;
				}
			}
		}
		
		if(borrower.tryRenew(id)){
			System.out.println("The resource was renewed successfully!");
		}
		else{
			System.out.println("The resource was not renewed!");
		}
		
		
	}
	
	/*****************************************/

	
	

	
	
	/*****************************************/
	/************* MAIN FUNCTION *************/
	/*****************************************/

	public static void main(String[] argv){

		String libName = "LUMS Library";
		
		Driver driver = new Driver(libName);
		
		System.out.println("\n\n***Welcome to Library Management System***\n\nSelect from the following options:\n\n" +
			"-  For viewing system stats press 1\n" + "-  For logging into the system as administrator, press 2\n" +
			"-  For faculty login press 3\n-  For student login press 4\n-  For exit, press 5\n");

		String userInput;
		int input;
		boolean done = false;
		String template = "\n\n***Welcome to Library Management System***\n\nSelect from the following options:\n\n" +
											"-  For viewing system stats, press 1\n" + "-  For logging into the system as administrator, press 2\n" +
											"-  For faculty login, press 3\n-  For student login, press 4\n-  For exit, press 5\n";
		
		// Taking the user input and appropriately calling functions... Main IO interface for Library
		while(!done){

			if(!driver.sc.hasNextInt()){
				System.out.println("Give the correct input");
				userInput = driver.sc.nextLine();
				continue;
			}
			input = driver.sc.nextInt();
			userInput = driver.sc.nextLine();
			switch(input){
				case 1:
					driver.myLibrary.getLibraryStats();
					System.out.println(template);
					break;
				case 2:
					driver.loginIO(Constants.ADMIN);
					System.out.println(template);
					break;
				case 3:
					driver.loginIO(Constants.FACULTY);
					System.out.println(template);
					break;
				case 4:
					driver.loginIO(Constants.STUDENT);
					System.out.println(template);
					break;
				case 5:
					System.out.println("Do you really want to lexit? y/n");
						userInput = driver.sc.nextLine();
						if((userInput.equals("y") || userInput.equals("Y"))){
							System.out.println("Thanks... exiting!");
							done = true;
						}
						else{
							System.out.println(template);
						}
					break;
				default:
					System.out.println("Give the correct input");
					break;
				}
			driver.myLibrary.updateFines();
		}
		
		driver.sc.close();		//closing the Scanner
		//Printing date...
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Library.calendar;
		System.out.println(dateFormat.format(cal.getTime()));
	}
}