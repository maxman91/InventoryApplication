package Controller;

/**
 * Supplied class addProductController.java
 */

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import static Model.Inventory.addPart;


public class addProductController implements Initializable {

    @FXML
    private Button cancel;
    @FXML
    private TextField priceField;
    @FXML
    private TextField inventoryField;

    @FXML
    private TextField maxField;

    @FXML
    private TextField minField;

    @FXML
    private TextField nameField;

    @FXML
    private Button Remove;
    @FXML
    private TableColumn<?, ?> asId;

    @FXML
    private TableColumn<?, ?> asInv;

    @FXML
    private TableColumn<?, ?> asName;

    @FXML
    private TableColumn<?, ?> ascost;

    @FXML
    private TableView<Part> aspartTable;

    @FXML
    private Button add;

    @FXML
    private TextField search;


    @FXML
    private TableView<Part> partTable;

    @FXML
    private Button save;
    @FXML
    private TableColumn<?, ?> partId;

    @FXML
    private TableColumn<?, ?> partInventory;

    @FXML
    private TableColumn<?, ?> partName;

    @FXML
    private TableColumn<?, ?> partPrice;



    private ObservableList<Part> tempasPart = FXCollections.observableArrayList();

    /**
     * removes associated part from table.
     */
    @FXML
    void onRemove(ActionEvent event) {


        Part selectedaPart = aspartTable.getSelectionModel().getSelectedItem();

        if (selectedaPart != null) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Remove  selected Part from associated parts?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            tempasPart.remove(selectedaPart);
            aspartTable.setItems(tempasPart);
        }
        } else {Alert alert = new Alert(Alert.AlertType.INFORMATION, "Select part you want to remove from associated parts list.");
            alert.showAndWait(); }


    }
    /**
     * searched through part table based on entered id or name.
     */
    @FXML
    void onSearch(ActionEvent event) {
        String searchItem = search.getText();
        partTable.setItems(lookupPart(searchItem));
    }
    /**
     * adds associated part to associated part table.
     */
    @FXML
    void onAdd(ActionEvent event) {
        Part selectedasPart = partTable.getSelectionModel().getSelectedItem();

        if (selectedasPart != null ){
            tempasPart.add(selectedasPart);
            aspartTable.setItems(tempasPart);

            asId.setCellValueFactory(new PropertyValueFactory<>("id"));
            asName.setCellValueFactory(new PropertyValueFactory<>("name"));
            ascost.setCellValueFactory(new PropertyValueFactory<>("price"));
            asInv.setCellValueFactory(new PropertyValueFactory<>("stock"));  }
            else {Alert alert = new Alert(Alert.AlertType.INFORMATION, "Select part you want to add to associated parts list.");
            alert.showAndWait(); }





    }
    /**
     * returns to main page.
     */
    @FXML
    void onCancel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/main.fxml"));
        Stage Window = (Stage) cancel.getScene().getWindow();
        Window.setScene(new Scene(root));

    }
    /**
     * validated all the entered data and saves it.
     */
    @FXML
    void onSave(ActionEvent event) throws IOException {
        String ErrorMessage = "All fields must be filled with correct value types.";
        try {
            int id = partidAdd();
            String Name = nameField.getText().trim();
            if (Name.isEmpty()) {
                throw new NumberFormatException();
            }
            int inv = Integer.parseInt(inventoryField.getText().trim());
            int min = Integer.parseInt(minField.getText().trim());
            int max = Integer.parseInt(maxField.getText().trim());
            double price = Double.parseDouble(priceField.getText());




                if(inv < 0 || min < 0 || max < 0 || price < 0)  {
                    ErrorMessage = "All numbers must be positive.";
                    throw new NumberFormatException();}
                if (min > max || min == max){
                    ErrorMessage = "Minimum input value must be less that maximum input value.";
                    throw new NumberFormatException();
                }
                if (inv > max || inv < min){
                    ErrorMessage = "Inventory value must be between Max and Min values.";
                    throw new NumberFormatException();
                }
                Product product = new Product();
                product.setId(id);
                product.setName(Name);
                product.setStock(inv);
                product.setMin(min);
                product.setMax(max);
                product.setPrice(price);
                product.addAssociatedPart(tempasPart);

                Inventory.addProduct(product);








            Parent root = FXMLLoader.load(getClass().getResource("../View/main.fxml"));
            Stage Window = (Stage) save.getScene().getWindow();
            Window.setScene(new Scene(root));

        } catch(NumberFormatException e)
        {

            System.out.println("please enter valid values");
            System.out.println(e.fillInStackTrace());
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("Input failed");
            alert.setContentText(ErrorMessage);
            alert.showAndWait();
        }

    }


    /**
     * initializes add product page and populates part table.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partTable.setItems(getAllParts());

        partId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        partInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));

    }
}
