/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.UserDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import utils.DBConnection;

/**
 *
 * @author Bilal Sayed
 */
public class AppointmentDao {
    //method to add an appointment to the database

    /**
     *
     * @param appointment
     */
    public static void addAppointment(Appointment appointment) {
        String query = "INSERT INTO appointment (customerId, userId, title, description, location, contact, type, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW(), ?)";
        try {
            PreparedStatement statment = DBConnection.startConnection().prepareStatement(query);

            statment.setInt(1, appointment.getCustomerId());
            statment.setInt(2, UserDao.getLoggedInUser().getUserId());
            statment.setString(3, appointment.getTitle());
            statment.setString(4, appointment.getDescription());
            statment.setString(5, appointment.getLocation());
            statment.setString(6, appointment.getContact());
            statment.setString(7, appointment.getType());
            statment.setString(8, "");

            LocalDateTime startSysDefault = appointment.getStart().toLocalDateTime();
            LocalDateTime startUTC = startSysDefault.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
            Timestamp startUTCTimestamp = Timestamp.valueOf(startUTC);

            statment.setTimestamp(9, startUTCTimestamp);

            LocalDateTime endSysDefault = appointment.getEnd().toLocalDateTime();
            LocalDateTime endUTC = endSysDefault.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
            Timestamp endUTCTimestamp = Timestamp.valueOf(endUTC);

            statment.setTimestamp(10, endUTCTimestamp);

            statment.setInt(11, UserDao.getLoggedInUser().getUserId());
            statment.setInt(12, UserDao.getLoggedInUser().getUserId());

            statment.executeUpdate();

            System.out.println("Appointment added successfully");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //method to get all appointments from the database

    /**
     *
     * @return
     */
    public static ObservableList<Appointment> getAllAppointments() {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        String query = "SELECT appointment.appointmentId, appointment.customerId, customer.customerName, appointment.userId, appointment.title, appointment.description, appointment.location, appointment.contact, appointment.type, appointment.url, appointment.start, appointment.end FROM customer JOIN appointment ON customer.customerId = appointment.customerId";
        try {
            PreparedStatement statment = DBConnection.startConnection().prepareStatement(query);
            ResultSet rs = statment.executeQuery();

            while (rs.next()) {
                Appointment returnedAppointment = new Appointment();
                returnedAppointment.setAppointmentId(rs.getInt("appointmentId"));
                returnedAppointment.setCustomerId(rs.getInt("customerId"));
                returnedAppointment.setCustomerName(rs.getString("customerName"));
                returnedAppointment.setUserId(rs.getInt("userId"));
                returnedAppointment.setTitle(rs.getString("title"));
                returnedAppointment.setDescription(rs.getString("description"));
                returnedAppointment.setLocation(rs.getString("location"));
                returnedAppointment.setContact(rs.getString("contact"));
                returnedAppointment.setType(rs.getString("type"));
                returnedAppointment.setUrl(rs.getString("url"));

                LocalDateTime startUTC = rs.getTimestamp("start").toLocalDateTime();
                LocalDateTime startSysDefault = startUTC.atZone(java.time.ZoneOffset.UTC).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                Timestamp startSysDefaultTimestamp = Timestamp.valueOf(startSysDefault);

                returnedAppointment.setStart(startSysDefaultTimestamp);

                LocalDateTime endUTC = rs.getTimestamp("end").toLocalDateTime();
                LocalDateTime endSysDefault = endUTC.atZone(java.time.ZoneOffset.UTC).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                Timestamp EndSysDefaultTimestamp = Timestamp.valueOf(endSysDefault);

                returnedAppointment.setEnd(EndSysDefaultTimestamp);

                appointments.add(returnedAppointment);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return appointments;
    }
    //method to update an appointmnet in the database

    /**
     *
     * @param appointment
     */
    public static void updateAppointment(Appointment appointment) {
        String query = "UPDATE appointment SET description = ?, location = ?, contact = ?, type = ?, start = ?, end = ?,  lastUpdate = NOW(), lastUpdateBy = ? WHERE appointmentId = ?";

        try {
            PreparedStatement statment = DBConnection.startConnection().prepareStatement(query);

            statment.setString(1, appointment.getDescription());
            statment.setString(2, appointment.getLocation());
            statment.setString(3, appointment.getContact());
            statment.setString(4, appointment.getType());

            LocalDateTime startSysDefault = appointment.getStart().toLocalDateTime();
            LocalDateTime startUTC = startSysDefault.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
            Timestamp startUTCTimestamp = Timestamp.valueOf(startUTC);

            statment.setTimestamp(5, startUTCTimestamp);

            LocalDateTime endSysDefault = appointment.getEnd().toLocalDateTime();
            LocalDateTime endUTC = endSysDefault.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
            Timestamp endUTCTimestamp = Timestamp.valueOf(endUTC);

            statment.setTimestamp(6, endUTCTimestamp);

            statment.setString(7, UserDao.getLoggedInUser().getUserName());
            statment.setInt(8, appointment.getAppointmentId());

            System.out.println(statment);

            statment.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //Mmethod to delete an appointment in the database

    /**
     *
     * @param appointment
     */
    public static void deleteAppointment(Appointment appointment) {
        String query = "DELETE FROM appointment WHERE appointmentId = ?";

        try {
            PreparedStatement statment = DBConnection.startConnection().prepareStatement(query);

            statment.setInt(1, appointment.getAppointmentId());
            statment.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
