/**
 * This interface must be implemented by the Library class
 * 
 */
interface LibraryInfo {

    /**
     * To get the name of the library
     * @return the library name defined in the Library class
     */
    String getLibraryName();

    void getLibraryStats(); //should output the stats of the library system
}
