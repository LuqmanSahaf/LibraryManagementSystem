
/**
 * The is the parent class of all Library Resources
 */
public class LibraryResource {

    /**
     * name of a library resource, must be unique
     */
    String resourceName;

    /**
     * unique id of a library resource
     */
    protected int resourceID;
		
		int type;
		
    /**
     *
     * @return gives the unique id of the resource
     */
    public int getResourceID() {
        return this.resourceID;
    }
}
