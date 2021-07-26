/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewController;

import dao.AppointmentDao;
import dao.CalendarDao;
import dao.CustomerDao;
import dao.ReportDao;
import dao.UserDao;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Appointment;
import model.Calendar;
import model.Customer;
import model.Report;
import model.User;

/**
 * FXML Controller class
 *
 * @author Bilal Sayed
 */
public class MainScreenController implements Initializable {

    @FXML
    private Color x1;
    @FXML
    private Insets x3;
    @FXML
    private Font x2;
    @FXML
    private Label loggedInUserLabel;
    @FXML
    private TableView<Customer> customersTableView;
    @FXML
    private TableColumn<Customer, Integer> customersCustomerIDColumn;
    @FXML
    private TableColumn<Customer, String> customersNameColumn;
    @FXML
    private TableColumn<Customer, String> customersAddressColumn;
    @FXML
    private TableColumn<Customer, String> customersContactColumn;
    @FXML
    private TableView<Appointment> appointmentsTableView;
    @FXML
    private TableColumn<Appointment, Integer> appointmentsCustomerIDColumn;
    @FXML
    private TableColumn<Appointment, String> appointmentsNameColumn;
    @FXML
    private TableColumn<Appointment, Integer> appointmentsUserIDColumn;
    @FXML
    private TableColumn<Appointment, String> appointmentsTypeColumn;
    @FXML
    private TableColumn<Appointment, LocalDateTime> appointmentsStartTimeColumn;
    @FXML
    private TableColumn<Appointment, LocalDateTime> appointmentsEndTimeColumn;
    @FXML
    private TableView<Calendar> weeklyTableView;
    @FXML
    private TableColumn<Calendar, Integer> weeklyAppointmentIDColumn;
    @FXML
    private TableColumn<Calendar, Integer> weeklyCustomerIDColumn;
    @FXML
    private TableColumn<Calendar, String> weeklyNameColumn;
    @FXML
    private TableColumn<Calendar, Integer> weeklyUserIDColumn;
    @FXML
    private TableColumn<Calendar, String> weeklyTitleColumn;
    @FXML
    private TableColumn<Calendar, String> weeklyDescriptionColumn;
    @FXML
    private TableColumn<Calendar, String> weeklyLocationColumn;
    @FXML
    private TableColumn<Calendar, String> weeklyContactCoulmn;
    @FXML
    private TableColumn<Calendar, String> weeklyTypeColumn;
    @FXML
    private TableColumn<Calendar, Timestamp> weeklyStartTimeColumn;
    @FXML
    private TableColumn<Calendar, Timestamp> weeklyEndTimeColumn;
    @FXML
    private TableView<Calendar> monthlyTableView;
    @FXML
    private TableColumn<Calendar, Integer> monthlyAppointmentIDColumn;
    @FXML
    private TableColumn<Calendar, Integer> monthlyCustomerIDColumn;
    @FXML
    private TableColumn<Calendar, String> monthlyNameColumn;
    @FXML
    private TableColumn<Calendar, Integer> monthlyUserIDColumn;
    @FXML
    private TableColumn<Calendar, String> monthlyTitleColumn;
    @FXML
    private TableColumn<Calendar, String> monthlyDescriptionColumn;
    @FXML
    private TableColumn<Calendar, String> monthlyLocationColumn;
    @FXML
    private TableColumn<Calendar, String> monthlyContactColumn;
    @FXML
    private TableColumn<Calendar, String> monthlyTypeColumn;
    @FXML
    private TableColumn<Calendar, Timestamp> monthlyStartTimeColumn;
    @FXML
    private TableColumn<Calendar, Timestamp> monthlyEndTimeColumn;
    @FXML
    private TableView<Calendar> allTableView;
    @FXML
    private TableColumn<Calendar, Integer> allAppointmentIDColumn;
    @FXML
    private TableColumn<Calendar, Integer> allCustomerIDColumn;
    @FXML
    private TableColumn<Calendar, String> allNameColumn;
    @FXML
    private TableColumn<Calendar, Integer> allUserIDColumn;
    @FXML
    private TableColumn<Calendar, String> allTitleColumn;
    @FXML
    private TableColumn<Calendar, String> allDecscriptionColumn;
    @FXML
    private TableColumn<Calendar, String> allLocationColumn;
    @FXML
    private TableColumn<Calendar, String> allContactColumn;
    @FXML
    private TableColumn<Calendar, String> allTypeCoulumn;
    @FXML
    private TableColumn<Calendar, Timestamp> allStartTimeColumn;
    @FXML
    private TableColumn<Calendar, Timestamp> allEndTimeColumn;
    @FXML
    private TableView<Report> typeReportTableView;
    @FXML
    private TableColumn<Report, String> typeReportTypeColum;
    @FXML
    private TableColumn<Report, Integer> typeReportCountColumn;
    @FXML
    private ComboBox<User> userReportComboBox;
    @FXML
    private TableView<Report> userReportTableView;
    @FXML
    private TableColumn<Report, Integer> userReportAppointmentIDColumn;
    @FXML
    private TableColumn<Report, Integer> userReportCustomerIDColumn;
    @FXML
    private TableColumn<Report, String> userReportNameColumn;
    @FXML
    private TableColumn<Report, String> userReportTitleColumn;
    @FXML
    private TableColumn<Report, String> userReportDescriptionColumn;
    @FXML
    private TableColumn<Report, String> userReportLocationColumn;
    @FXML
    private TableColumn<Report, String> userReportContactColumn;
    @FXML
    private TableColumn<Report, String> userReportTypeColumn;
    @FXML
    private TableColumn<Report, Timestamp> userReportStartTimeColumn;
    @FXML
    private TableColumn<Report, Timestamp> userReportEndTimeColumn;
    @FXML
    private ComboBox<Customer> customReportComboBox;
    @FXML
    private TableView<Report> customReportTableView;
    @FXML
    private TableColumn<Report, Integer> customReportAppointmentIDColumn;
    @FXML
    private TableColumn<Report, Integer> customReportCustomerIDColumn;
    @FXML
    private TableColumn<Report, String> customReportTitleColumn;
    @FXML
    private TableColumn<Report, String> customReportDescriptionColumn;
    @FXML
    private TableColumn<Report, String> customReportLocationColumn;
    @FXML
    private TableColumn<Report, String> customReportContactColumn;
    @FXML
    private TableColumn<Report, String> customReportTypeColumn;
    @FXML
    private TableColumn<Report, Timestamp> customReportStartTimeColumn;
    @FXML
    private TableColumn<Report, Timestamp> customReportEndTimeColumn;

    /**
     *
     */
    public static Customer selectedCustomer;

    /**
     *
     */
    public static Appointment selectedAppointment;

    /**
     *
     */
    public static MainScreenController mainScreen;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mainScreen = this;
        //set logged in user text
        loggedInUserLabel.setText("Welcome User " + UserDao.getLoggedInUser().getUserName());
        appointmentAlert();
        setTables();
        populateTables();
    }

    @FXML
    private void createCustomerButtonHandler(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().
                    getResource("/viewController/addCustomerScreen.fxml"));
            //create a new stage and scene
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            //set the title and modality and set the scene and show it
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.getIcons().add(new Image("/img/appIcon.png"));
            stage.setTitle("Scheduling App - Add Customer");
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void updateCustomerButtonHandler(ActionEvent event) {
        try {
            //check to see if an item is selected
            if (customersTableView.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Error");
                alert.setHeaderText("Please select a customer.");
                alert.showAndWait();
            } else {
                selectedCustomer = null;
                selectedCustomer = customersTableView.getSelectionModel().getSelectedItem();

                Parent root = FXMLLoader.load(getClass().
                        getResource("/viewController/updateCustomerScreen.fxml"));
                //create a new stage and scene
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                //set the title and modality and set the scene and show it
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.getIcons().add(new Image("/img/appIcon.png"));
                stage.setTitle("Scheduling App - Update Customer");
                stage.show();

            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void deleteCustomerButtonHandler(ActionEvent event) {
        if (customersTableView.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Error");
            alert.setHeaderText("Please select a customer to delete.");
            alert.showAndWait();
        } else {
            //confirmation to delete a part
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Delete?");
            alert.setHeaderText("Please confirm you want delete.");
            alert.setContentText("This cannot be undone.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                CustomerDao.deleteCustomer(customersTableView.getSelectionModel().getSelectedItem());
                refreshData();
            }
        }
    }

    @FXML
    private void createAppointmentButtonHandler(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().
                    getResource("/viewController/addAppointmentScreen.fxml"));
            //create a new stage and scene
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            //set the title and modality and set the scene and show it
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.getIcons().add(new Image("/img/appIcon.png"));
            stage.setTitle("Scheduling App - Add Appointment");
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void updateAppointmentButtonHandler(ActionEvent event) {
        try {
            //check to see if an item is selected
            if (appointmentsTableView.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Error");
                alert.setHeaderText("Please select an appointment.");
                alert.showAndWait();
            } else {
                selectedAppointment = null;
                selectedAppointment = appointmentsTableView.getSelectionModel().getSelectedItem();

                Parent root = FXMLLoader.load(getClass().
                        getResource("/viewController/updateAppointmentScreen.fxml"));
                //create a new stage and scene
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                //set the title and modality and set the scene and show it
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.getIcons().add(new Image("/img/appIcon.png"));
                stage.setTitle("Scheduling App - Update Appointment");
                stage.show();

            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void deleteAppointmentButtonHandler(ActionEvent event) {
        if (appointmentsTableView.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Error");
            alert.setHeaderText("Please select an appointment to delete.");
            alert.showAndWait();
        } else {
            //confirmation to delete a part
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Delete?");
            alert.setHeaderText("Please confirm you want delete.");
            alert.setContentText("This cannot be undone.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                AppointmentDao.deleteAppointment(appointmentsTableView.getSelectionModel().getSelectedItem());
                refreshData();
            }
        }
    }

    @FXML
    private void userReportComboHandler(ActionEvent event) {
        //set user report table base on selection
        userReportTableView.setItems(ReportDao.getUserAppointmentReport(userReportComboBox.getValue()));
    }

    @FXML
    private void customReportComboHandler(ActionEvent event) {
        //set custome report table based on selection
        customReportTableView.setItems(ReportDao.getCustomerAppointmentReport(customReportComboBox.getValue()));
    }
    //populate table method

    /**
     *
     */
    public void populateTables() {
        customersTableView.setItems(CustomerDao.getAllCustomers());
        appointmentsTableView.setItems(AppointmentDao.getAllAppointments());
        weeklyTableView.setItems(CalendarDao.getWeeklyAppointments());
        monthlyTableView.setItems(CalendarDao.getMonthlyAppointments());
        allTableView.setItems(CalendarDao.getAllAppointments());
        typeReportTableView.setItems(ReportDao.getTypeReport());
        userReportComboBox.setItems(UserDao.getAllUsers());
        customReportComboBox.setItems(CustomerDao.getAllCustomers());
    }
    //set table method

    /**
     *
     */
    public void setTables() {
        customersCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customersNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customersAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        customersContactColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        appointmentsCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appointmentsNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        appointmentsUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        appointmentsTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        appointmentsStartTimeColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        appointmentsEndTimeColumn.setCellValueFactory(new PropertyValueFactory<>("end"));

        weeklyAppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        weeklyCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        weeklyNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        weeklyUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        weeklyTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        weeklyDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        weeklyLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        weeklyContactCoulmn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        weeklyTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        weeklyStartTimeColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        weeklyEndTimeColumn.setCellValueFactory(new PropertyValueFactory<>("end"));

        monthlyAppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        monthlyCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        monthlyNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        monthlyUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        monthlyTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        monthlyDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        monthlyLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        monthlyContactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        monthlyTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        monthlyStartTimeColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        monthlyEndTimeColumn.setCellValueFactory(new PropertyValueFactory<>("end"));

        allAppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        allCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        allNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        allUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        allTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        allDecscriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        allLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        allContactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        allTypeCoulumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        allStartTimeColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        allEndTimeColumn.setCellValueFactory(new PropertyValueFactory<>("end"));

        typeReportTypeColum.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeReportCountColumn.setCellValueFactory(new PropertyValueFactory<>("count"));

        userReportAppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        userReportCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userReportNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        userReportTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        userReportDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        userReportLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        userReportContactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        userReportTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        userReportStartTimeColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        userReportEndTimeColumn.setCellValueFactory(new PropertyValueFactory<>("end"));

        customReportAppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        customReportCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customReportTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        customReportDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        customReportLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        customReportContactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        customReportTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        customReportStartTimeColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        customReportEndTimeColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
    }
    //refresh data method

    /**
     *
     */
    public void refreshData() {
        customersTableView.getItems().setAll(CustomerDao.getAllCustomers());
        appointmentsTableView.getItems().setAll(AppointmentDao.getAllAppointments());
        weeklyTableView.getItems().setAll(CalendarDao.getWeeklyAppointments());
        monthlyTableView.getItems().setAll(CalendarDao.getMonthlyAppointments());
        allTableView.getItems().setAll(CalendarDao.getAllAppointments());
        typeReportTableView.getItems().setAll(ReportDao.getTypeReport());
    }
    //alert if there is appointment within 15 mins of logging in

    /**
     *
     */
    public void appointmentAlert() {
        for (Appointment appointment : AppointmentDao.getAllAppointments()) {

            if (appointment.getStart().toLocalDateTime().isAfter(LocalDateTime.now()) & appointment.getStart().toLocalDateTime().isBefore(LocalDateTime.now().plusMinutes(15).plusSeconds(1))) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Alert");
                alert.setHeaderText("There is an appointment in 15 minutes or sooner");
                alert.showAndWait();
            }
        }
    }

}
