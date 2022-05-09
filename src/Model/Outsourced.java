package Model;
/**
 * Supplied class Outsourced.java
 */


public class Outsourced extends Part {

     String companyName;



    public Outsourced(int id, String name, double price, int stock, int min, int max,String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param  companyName sets the company Name.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


}
