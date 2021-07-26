/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewController;

import dao.AppointmentDao;
import dao.CityDao;
import dao.CustomerDao;
import static dao.CustomerDao.getAllCustomers;
import dao.ReportDao;
import dao.UserDao;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.MINUTES;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;
import model.Report;
import static viewController.MainScreenController.selectedAppointment;

/**
 * FXML Controller class
 *
 * @author Bilal Sayed
 */
public class UpdateAppointmentScreenController implements Initializable {

    @FXML
    private TextField appointmentUserIDField;
    @FXML
    private TextField appointmentTitleField;
    @FXML
    private TextArea appointmentDescriptionField;
    @FXML
    private TextField appointmentLocationField;
    @FXML
    private TextField appointmentContactField;
    @FXML
    private TextField appointmentTypeField;
    @FXML
    private ComboBox<Customer> appointmentCustomerComboBox;
    @FXML
    private DatePicker appointmentDatePicker;
    @FXML
    private ComboBox<LocalTime> appointmentStartTimeComboBox;
    @FXML
    private ComboBox<String> appointmentLengthComboBox;
    @FXML
    private Font x1;
    @FXML
    private Insets x2;
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

        //variables to set start combo box
        LocalTime nine = LocalTime.of(9, 0);
        LocalTime noon = LocalTime.of(12, 0);
        LocalTime one = LocalTime.of(1, 0);
        LocalTime five = LocalTime.of(5, 0);

        //set start and combo box
        appointmentStartTimeComboBox.getItems().clear();
        while (nine.isBefore(noon.plusSeconds(1))) {
            appointmentStartTimeComboBox.getItems().add(nine);
            nine = nine.plusMinutes(15);
        }
        while (one.isBefore(five.plusSeconds(1))) {
            appointmentStartTimeComboBox.getItems().add(one);
            one = one.plusMinutes(15);
        }

        appointmentStartTimeComboBox.setVisibleRowCount(7);
        //set length combo box
        appointmentLengthComboBox.getItems().clear();
        appointmentLengthComboBox.getItems().add("15 Minutes");
        appointmentLengthComboBox.getItems().add("30 Minutes");
        appointmentLengthComboBox.getItems().add("1 Hour");
        //set customer combo box              
        setAppointmentCustomerComboBox();
        //populate fields according to customer selected
        appointmentCustomerComboBox.setValue(CustomerDao.getCustomer(MainScreenController.selectedAppointment.getCustomerId()));
        appointmentUserIDField.setText(Integer.toString(UserDao.getLoggedInUser().getUserId()));
        appointmentTitleField.setText(MainScreenController.selectedAppointment.getTitle());
        appointmentDescriptionField.setText(MainScreenController.selectedAppointment.getDescription());
        appointmentLocationField.setText(MainScreenController.selectedAppointment.getLocation());
        appointmentContactField.setText(MainScreenController.selectedAppointment.getContact());
        appointmentTypeField.setText(MainScreenController.selectedAppointment.getType());
        //start timestamp to localdatetime
        Timestamp startTimestamp = selectedAppointment.getStart();
        LocalDate startDate = startTimestamp.toLocalDateTime().toLocalDate();
        LocalTime startTime = startTimestamp.toLocalDateTime().toLocalTime();
        //end timestamp to local datetime
        Timestamp endTimestamp = selectedAppointment.getEnd();
        LocalTime endTime = endTimestamp.toLocalDateTime().toLocalTime();

        long timeBetween = MINUTES.between(startTime, endTime);

        appointmentDatePicker.setValue(startDate);
        appointmentStartTimeComboBox.setValue(startTime);
        //code to set length selection
        if (timeBetween == 15) {
            appointmentLengthComboBox.setValue("15 Minutes");
        }
        if (timeBetween == 30) {
            appointmentLengthComboBox.setValue("30 Minutes");
        }
        if (timeBetween == 60) {
            appointmentLengthComboBox.setValue("1 Hour");
        }
    }

    @FXML
    private void saveButtonHandler(ActionEvent event) {
        //create new appointment object
        Appointment newAppointment = new Appointment();
        newAppointment.setAppointmentId(selectedAppointment.getAppointmentId());
        newAppointment.setDescription(appointmentDescriptionField.getText());
        newAppointment.setLocation(appointmentLocationField.getText());
        newAppointment.setContact(appointmentContactField.getText());
        newAppointment.setType(appointmentTypeField.getText());
        //convert to timestamp
        LocalDateTime startDateTime = LocalDateTime.of(appointmentDatePicker.getValue(), appointmentStartTimeComboBox.getValue());
        Timestamp startTimestamp = Timestamp.valueOf(startDateTime);
        //code to create end timestamp
        LocalTime end = null;
        if (appointmentLengthComboBox.getValue() == "15 Minutes") {
            end = appointmentStartTimeComboBox.getValue().plusMinutes(15);
        }
        if (appointmentLengthComboBox.getValue() == "30 Minutes") {
            end = appointmentStartTimeComboBox.getValue().plusMinutes(30);
        }
        if (appointmentLengthComboBox.getValue() == "1 Hour") {
            end = appointmentStartTimeComboBox.getValue().plusHours(1);
        }
        //get date picker value
        LocalDateTime endDateTime = LocalDateTime.of(appointmentDatePicker.getValue(), end);
        Timestamp endTimestamp = Timestamp.valueOf(endDateTime);
        //set the start and end values
        newAppointment.setStart(startTimestamp);
        newAppointment.setEnd(endTimestamp);
        //check if appointment overlaps
        if (checkUserSchedule(startDateTime) == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Error");
            alert.setHeaderText("This appointment time is already scheduled. Please choose a different time.");
            alert.showAndWait();
        } else {
            //add appointment to database
            AppointmentDao.updateAppointment(newAppointment);

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

    @FXML
    private void customerComboHandler(ActionEvent event) {
        //pre set fields based on customer info stored in database
        int city = appointmentCustomerComboBox.getSelectionModel().getSelectedItem().getCityId();
        String phone = appointmentCustomerComboBox.getSelectionModel().getSelectedItem().getPhone();
        appointmentLocationField.setText(CityDao.getCity(city).getCity());
        appointmentContactField.setText(phone);
    }

    //method to set customer combobox
    private void setAppointmentCustomerComboBox() {
        appointmentCustomerComboBox.setItems(getAllCustomers());
    }

    private boolean checkUserSchedule(LocalDateTime start) {
        ObservableList<Report> appointments = ReportDao.getUserAppointmentReport(UserDao.getLoggedInUser());

        ObservableList<Report> filteredAppointments = appointments.stream()
                .filter(t -> t.getAppointmentId() != selectedAppointment.getAppointmentId())
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        for (Report report : filteredAppointments) {
            if (start.isBefore(report.getEnd().toLocalDateTime()) && start.isAfter(report.getStart().toLocalDateTime().minusSeconds(1))) {
                return false;
            }
        }
        return true;
    }
}
