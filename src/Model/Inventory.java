package Model;

/**
 * Supplied class Inventory.java
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;


public class Inventory {


    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int partID = 0;

    /**
     * adds parts to the allParts list.
     */

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * adds product to the allProducts list.
     */


    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * removes Parts from allParts and returns a boolean value..
     */
    public static boolean deletePart(Part selectPart){
        if (allParts.contains(selectPart)){
            allParts.remove(selectPart);

            return true;
        }
        else {
            return false;
        }
    }

    /**
     * removes Products from allProducts and returns a boolean value.
     */
    public static boolean deleteProduct(Product selectProduct){
        if (allProducts.contains(selectProduct)){
            allProducts.remove(selectProduct);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * retrieves all the parts.
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * retrieves all the products.
     */

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * used in the modify parts page to find the part being updated and updating the part.
     */
    public static void updatePart(int index, Part selectedPart){
        for (int i = 0; i < allParts.size(); i++) {

            if (allParts.get(i).getId() == selectedPart.getId()) {

                allParts.set(i, selectedPart);

                break;

            }
        }
    }
    /**
     * used in the modify products page to find the product being updated and updating the product.
     */
    public static void updateProduct(int index, Product selectedProduct){
        for (int i = 0; i < allParts.size(); i++) {

            if (allProducts.get(i).getId() == selectedProduct.getId()) {

                allProducts.set(i, selectedProduct);

                break;

            }
        }
    }
    /**
     * can be used to lookup product id in the future.
     */

    public int lookupProduct(int productid){
        if (allParts.contains(productid)){
            return productid;

        }
        else {
            return 0;
        }
    }

    /**
     * used to lookup products based on search queries.
     */
    public static ObservableList<Product> lookupProduct(String searchItem){
        ObservableList<Product> temp = FXCollections.observableArrayList();
        searchItem = searchItem.trim().toLowerCase();

        for (Product product : getAllProducts()) {
            if (Integer.toString(product.getId()).contains(searchItem)|| product.getName().toLowerCase().contains(searchItem)) {
                temp.add(product);
            }
        }
        if (temp.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("No product found with this ID/Name.");
            alert.setHeaderText("Search failure.");
            alert.showAndWait();
            return getAllProducts();
        } else {
            return temp;
        }
    }
    /**
     * used to lookup Parts based on search queries.
     */
    public static ObservableList<Part> lookupPart(String searchItem) {
        ObservableList<Part> temp = FXCollections.observableArrayList();
        searchItem = searchItem.trim().toLowerCase();

        for (Part part : getAllParts()) {
            if (Integer.toString(part.getId()).contains(searchItem)|| part.getName().toLowerCase().contains(searchItem)) {
                temp.add(part);
            }
        }


        if (temp.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("No part found with this ID/Name.");
            alert.setHeaderText("Search failure.");
            alert.showAndWait();
            return getAllParts();
        } else {
            return temp;
        }
    }

    /**
     * used to give both products and parts a unique ID.
     */
    public static int partidAdd() {

        return ++partID;
    }



}