package Controller;

/**
 * Supplied class modifyPartController.java
 */

import Model.InHouse;
import Model.Outsourced;
import Model.Part;
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

import static Controller.mainController.getselectedPart;
import static Model.Inventory.*;

public class modifyPartController implements Initializable {

    @FXML
    private Button cancel;
    @FXML
    private TextField idField;
    @FXML
    private TextField inventoryField;
    @FXML
    private TextField machineIDField;

    private  Part selectedPart;


    @FXML
    private TextField maxField;

    @FXML
    private TextField minfield;

    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;

    @FXML
    private Button save;

    @FXML
    private RadioButton inHouse;

    @FXML
    private RadioButton outsourced;

    @FXML
    private Label machineID;

    private boolean isoutSourced;

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
     *Takes all the inputs and validates them before updating the selected part.
     */
    @FXML
    void onSave(ActionEvent event) throws IOException {
        String ErrorMessage = "All fields must be filled with correct value types.";
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String Name = nameField.getText().trim();
            if (Name.isEmpty()) {
                throw new NumberFormatException();
            }
            int inv = Integer.parseInt(inventoryField.getText().trim());
            int min = Integer.parseInt(minfield.getText().trim());
            int max = Integer.parseInt(maxField.getText().trim());
            double price = Double.parseDouble(priceField.getText());

            if (isoutSourced == true) {
                String companyName = machineIDField.getText().trim();
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


                updatePart(id, new Outsourced(id, Name, price, inv, min, max, companyName));}
            else {
                int machine = Integer.parseInt(machineIDField.getText().trim());
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


                updatePart(id, new InHouse(id, Name, price, inv, min, max, machine));  }





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
     * initializes modify part page. It gets selectedPart from the mainController and places that data into the
     * appropriate fields. Also checks if the selected part is outsourced or in house and selects correct radio button
     * and sets the correct text.
     */


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        this.selectedPart = getselectedPart();


        idField.setText(Integer.toString(selectedPart.getId()));
        nameField.setText(selectedPart.getName());
        inventoryField.setText(Integer.toString(selectedPart.getStock()));
        priceField.setText(Double.toString(selectedPart.getPrice()));
        minfield.setText(Integer.toString(selectedPart.getMin()));
        maxField.setText(Integer.toString(selectedPart.getMax()));




        ToggleGroup group = new ToggleGroup();
        inHouse.setToggleGroup(group);
        outsourced.setToggleGroup(group);
        if(selectedPart instanceof Outsourced){
            outsourced.setSelected(true);
            Outsourced osselectedPart = (Outsourced) selectedPart;
            machineID.setText("Company Name");
            machineIDField.setText(osselectedPart.getCompanyName());
            isoutSourced = true;
        }
        else {
            inHouse.setSelected(true);
            InHouse ihselectedPart = (InHouse) selectedPart;
            machineIDField.setText(Integer.toString(ihselectedPart.getMachineId()));
            isoutSourced = false;
        }


    }


}

