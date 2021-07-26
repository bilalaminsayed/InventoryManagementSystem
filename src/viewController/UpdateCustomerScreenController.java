/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewController;

import dao.CityDao;
import dao.CustomerDao;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.City;
import model.Customer;
import static viewController.MainScreenController.selectedCustomer;

/**
 * FXML Controller class
 *
 * @author Bilal Sayed
 */
public class UpdateCustomerScreenController implements Initializable {

    @FXML
    private TextField customerIDField;
    @FXML
    private TextField customerNameField;
    @FXML
    private TextField CustomerAddressField;
    @FXML
    private TextField customerContactField;
    @FXML
    private ComboBox<City> cityComboBox;
    @FXML
    private TextField postalCodeField;
    @FXML
    private Font x1;
    @FXML
    private Insets x2;
    @FXML
    private Insets x5;
    @FXML
    private Insets x3;
    @FXML
    private Font x4;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set cities combo box
        setCitiesComboBox();
        //populate fields based on customer selected
        customerIDField.setText(Integer.toString(MainScreenController.selectedCustomer.getCustomerId()));
        customerNameField.setText(MainScreenController.selectedCustomer.getCustomerName());
        CustomerAddressField.setText(MainScreenController.selectedCustomer.getAddress());
        cityComboBox.setValue(CityDao.getCity(selectedCustomer.getCityId()));
        postalCodeField.setText(MainScreenController.selectedCustomer.getPostalCode());
        customerContactField.setText(MainScreenController.selectedCustomer.getPhone());
    }

    @FXML
    private void saveButtonHandler(ActionEvent event) {
        //create new customer object
        if (customerNameField.getText().isEmpty()) {
            //check for empty fields
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter a name.");
            alert.showAndWait();
        }
        if (CustomerAddressField.getText().isEmpty()) {
            //check for empty fields
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter an address.");
            alert.showAndWait();
        }
        if (postalCodeField.getText().isEmpty()) {
            //check for empty fields
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter a postal code.");
            alert.showAndWait();
        }
        if (customerContactField.getText().isEmpty()) {
            //check for empty fields
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter a phone number.");
            alert.showAndWait();
        } else {
            //create new customer object
            Customer newCustomer = new Customer();
            newCustomer.setCustomerId(Integer.parseInt(customerIDField.getText()));
            newCustomer.setCustomerName(customerNameField.getText());
            newCustomer.setAddress(CustomerAddressField.getText());
            newCustomer.setPhone(customerContactField.getText());
            newCustomer.setPostalCode(postalCodeField.getText());
            newCustomer.setCityId(cityComboBox.getSelectionModel().getSelectedItem().getCityId());
            newCustomer.setAddressId(selectedCustomer.getAddressId());
            //add customer to database
            CustomerDao.updateCustomer(newCustomer);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();
            MainScreenController.mainScreen.refreshData();
        }
    }

    @FXML
    private void cancelButtonHandler(ActionEvent event) {
        //cancel button handler
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle("Cancel");
        alert.setHeaderText("Are you sure you want to Cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();
        }
    }

    //get all cities and set to combo box
    private void setCitiesComboBox() {
        cityComboBox.setItems(CityDao.getAllCities());
    }
}
