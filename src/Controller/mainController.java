package Controller;

/**
 * Supplied class mainController.java
 */

import Model.Part;
import Model.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Model.Inventory.*;

public class mainController implements Initializable {
    @FXML
    private Button deleteProduct;

    @FXML
    private TableColumn<?, ?> productCost;

    @FXML
    private TableColumn<?, ?> productID;

    @FXML
    private TableColumn<?, ?> productInventory;

    @FXML
    private TableColumn<?, ?> productName;

    @FXML
    private TextField productSearchField;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private Button exitButton;
    @FXML
    private Button deletePart;
    @FXML
    private TextField searchField;

    @FXML
    private Button addPart;
    @FXML
    private Button modifyPart;
    @FXML
    private Button addProduct;
    @FXML
    private Button modifyProduct;
    @FXML
    private TableColumn<Part, Integer> partId;

    @FXML
    private TableColumn<Part, Integer> partInventory;

    @FXML
    private TableColumn<Part, String> partName;

    @FXML
    private TableColumn<Part, Double> partPrice;

    @FXML
    private TableView<Part> partTable;
    private static Part selectedPart;
    private static Product selectedProduct;

    /**
     * Removes selected product.
     */

    @FXML
    void onDeleteProduct(ActionEvent event) {
        Product removeProduct = productTable.getSelectionModel().getSelectedItem();






        if (removeProduct != null) {

            if (removeProduct.getAssociatedPart().size() > 0) {Alert alert = new Alert(Alert.AlertType.INFORMATION, "Products with associated parts can't be deleted.");
                alert.showAndWait();}

            else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete the selected product?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                deleteProduct(removeProduct);
                productTable.setItems(getAllProducts());}
            }}
         else {Alert alert = new Alert(Alert.AlertType.INFORMATION, "Select product you want delete.");
            alert.showAndWait(); }



    }

    /**
     * searches products based on entered Id or name
     */

    @FXML
    void onProductSearch(ActionEvent event) {
        String searchproduct = productSearchField.getText();
        productTable.setItems(lookupProduct(searchproduct));

    }
    /**
     * After getting errors I decided
     * to use getters and setters to send data to the modify pages ,in the future the setters and getter can be placed
     * in a model class too clean up the code as it scales.
     */
    public static Part getselectedPart()
    {
        return selectedPart;
    }

    /**
     * sets selected Part
     * @param selectedPart sets selected part
     */

    public void setselectedPart(Part selectedPart)
    {
        mainController.selectedPart = selectedPart;
    }

    /**
     * returns selected
     * @return returns selected product
     */

    public static Product getselectedProduct()
    {
        return selectedProduct;
    }

    /**
     * sets selected product.
     * @param selectedProduct sets selected product
     */

    public void setselectedProduct(Product selectedProduct)
    {
        mainController.selectedProduct = selectedProduct;
    }


    /**
     * deletes selected part and removes from table.
     */


    @FXML
    void ondeletePart(ActionEvent event) {

        Part remove = partTable.getSelectionModel().getSelectedItem();



        if (remove != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete the selected part?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                deletePart(remove);
                partTable.setItems(getAllParts());
            }
        } else {Alert alert = new Alert(Alert.AlertType.INFORMATION, "Select part you want delete.");
            alert.showAndWait(); }




    }

    /**
     * takes you to the add product page
     */
    @FXML
    void onaddProduct(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/addProduct.fxml"));
        Stage Window = (Stage) addProduct.getScene().getWindow();
        Window.setScene(new Scene(root));

    }

    /**
     * Takes you to the modify product page and sets the selected product to be retrieved.
     */
    @FXML
    void onmodifyProduct(ActionEvent event) throws IOException {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        setselectedProduct(selectedProduct);

        if (selectedProduct != null) {





        Parent root = FXMLLoader.load(getClass().getResource("../View/modifyProduct.fxml"));
        Stage Window = (Stage) modifyProduct.getScene().getWindow();
        Window.setScene(new Scene(root));} else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Select product you want to modify.");
            alert.showAndWait();

        }

    }

    /**
     * Takes you to the add part page.
     */

    @FXML
    void onaddPart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/addPart.fxml"));
        Stage Window = (Stage) addPart.getScene().getWindow();
        Window.setScene(new Scene(root));
    }


    /**
     * exits the application.
     */

    @FXML
    void onexitButton(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Takes you to the modify part page and sets the selected part to be retrieved.
     */
    @FXML
    void onmodifyPart(ActionEvent event) throws IOException {
        Part selectedPart = partTable.getSelectionModel().getSelectedItem();
        setselectedPart(selectedPart);
        if (selectedPart != null) {





            Parent root = FXMLLoader.load(getClass().getResource("../View/modifyProduct.fxml"));
            Stage Window = (Stage) modifyProduct.getScene().getWindow();
            Window.setScene(new Scene(root));} else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Select part you want to modify.");
            alert.showAndWait();

        }
    }
    /**
     * searches parts based on entered id or name. returns results or gives errors message.
     */
    @FXML
    void onsearchField(ActionEvent event) {
        String searchItem = searchField.getText();
        partTable.setItems(lookupPart(searchItem));

    }
    /**
     * initializes main page and populates part and product tables.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partTable.setItems(getAllParts());

        partId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        partInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));

        productTable.setItems(getAllProducts());

        productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productCost.setCellValueFactory(new PropertyValueFactory<>("price"));
        productInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));

    }

}
