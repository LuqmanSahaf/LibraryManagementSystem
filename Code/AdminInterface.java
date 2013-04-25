
/**
 * This interface must be implemented by the Admin class
 * 
 */
public interface AdminInterface {

    /**
     * Will create a user with the given username and password. 
     * @param userName the username of the new user
     * @param password the password of the new user
     * @param type create a user of type admin in type == 1, else if type is 2 then create a user with type faculty, if type is 3 create a student type user
     * @return when the user is created, also generate a random, unique +ve integer id for it and return the number, if cannot generate the user with the given credentials, return -1
     */
    int addUser(String userName, String password, int type);


    /**
     * remove the user from the system, 
     * @param userID id of the user who needs to be removed
     * @return return false if user does not exist, else return true
     */
    boolean removeUser(int userID);

    /**
     * adds the resource with the given name.
     * @param name name of the resource which needs to be added. must be unique
     * @param type if the type is 1, create a book type object, if the type is 2, create a course pack type object, if the type is 3, create a magazine
     * @return Generate a unique integer identifier (resourceID) for the newly added resource and return it. if the resource cannot be added, return -1
     */

    int addResource(String name, int type);
    

    /**
     * remove the resource from the system
     * @param resourceID the unique integer id of the resource
     * @return return false if resource does not exist. Also if the resource is issued, return false
     */
    boolean removeResource(int resourceID);
    
}
