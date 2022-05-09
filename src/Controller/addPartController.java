package Controller;

/**
 * Supplied class addPartController.java
 */

import Model.Inventory;
import Model.InHouse;
import Model.Outsourced;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Model.Inventory.addPart;
import static Model.Inventory.partidAdd;

public class addPartController implements Initializable {

    @FXML
    private TextField inventoryField;

    @FXML
    private TextField machineidField;

    @FXML
    private TextField maxField;

    @FXML
    private TextField minField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private Button cancel;

    @FXML
    private Button save;

    @FXML
    private RadioButton inHouse;

    @FXML
    private RadioButton outsourced;

    @FXML
    private Label machineID;

    boolean isoutSourced = false;

    /**
     * changes text based on which radio button is selected.
     */

    @FXML
    void oninHouse(ActionEvent event) {
        machineID.setText("Machine ID");
        isoutSourced = false;


    }
    /**
     * changes text based on which radio button is selected.
     */
    @FXML
    void onOutsourced(ActionEvent event) {
        machineID.setText("Company Name");
        isoutSourced = true;

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
     *  After struggling with a number of
     * runtime errors I used a try catch block to catch errors on time and handle them with pop up messages before
     * saving entered data into new part objects.
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

            if (isoutSourced == true) {
                String companyName = machineidField.getText().trim();
                if (companyName.isEmpty()) {
                    throw new NumberFormatException();
                }
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


                addPart(new Outsourced(id, Name, price, inv, min, max, companyName));}
            else {
                int machine = Integer.parseInt(machineidField.getText().trim());
                if(inv < 0 || min < 0 || max < 0 || price < 0 || machine < 0)  {
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


                addPart(new InHouse(id, Name, price, inv, min, max, machine));  }





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
     * initializes add part page, sets inHouse radio button by default.
     */


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup group = new ToggleGroup();
        inHouse.setToggleGroup(group);
        outsourced.setToggleGroup(group);
        inHouse.setSelected(true);

    }
}
