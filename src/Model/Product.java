package Model;

/**
 * Supplied class Product.java
 */


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {

    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private ObservableList<Part> associatedPart = FXCollections.observableArrayList();

    public Product(){}

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }


/**
 * This method can be used in the future to delete a products associated Parts outside of the modify product page.
  */

    public boolean deleteAssociatedPart(Part selectAssociatedPart){
        if (associatedPart.contains(selectAssociatedPart)){
            associatedPart.remove(selectAssociatedPart);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * adds Associated parts to the product.
     * @param newPart the obervable list to be added to associatedParts.
     */

    public void addAssociatedPart(ObservableList <Part> newPart) {

        this.associatedPart.addAll(newPart);
    }

    /**
     * retrieves the products associatedPart observable list.
     * @return the associated parts.
     */
    public ObservableList<Part> getAssociatedPart() {

        return associatedPart;
    }


}

