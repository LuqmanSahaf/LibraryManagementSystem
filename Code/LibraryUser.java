
/**
 * This is the parent class of all the library users
 */
public class LibraryUser{

    /**
     * The name of the user, must be unique
     */
    String userName;
    /**
     * password
     */
    String password;
    /**
     * The unique id of the user
     */
    int userID;
		
		int type;
    /**
     * get the id of the current user
     * @return return the userID variable of this class
     */
    int getUserID(){
        return userID;
    }

    /**
     *
     * @param userName the string username
     * @param password the string password
     * @return returns true if the login is successful else returns false
     */
    boolean login(String userName, String password){
      /**
      * write your code here
      */
			if((userName.equals(this.userName) && password.equals(this.password))){
				return true;
			}
			else{
				System.out.println("The username or password entered was not correct!");
				return false;
			}
		}

    /**
     * logs out the currently logged in user. Note that the system cannot have multiple users logged in at the same time
     * @return return true if logout successful else return false
     */
    boolean logout() {

        /**
         * Write your code here
         */
        return true;
    }
}