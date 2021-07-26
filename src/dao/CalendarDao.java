/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Calendar;
import utils.DBConnection;

/**
 *
 * @author Bilal Sayed
 */
public class CalendarDao {
    //method for getting the weekly appointments calendar view from the database

    /**
     *
     * @return
     */
    public static ObservableList<Calendar> getWeeklyAppointments() {
        ObservableList<Calendar> calendar = FXCollections.observableArrayList();

        LocalDate now = LocalDate.now();
        LocalDate weekLater = now.plusDays(7);
        String nowString = now.toString();
        String weekLaterString = weekLater.toString();

        String query = "SELECT appointment.appointmentId, appointment.customerId, customerName, appointment.userId, appointment.title, appointment.description, appointment.location, appointment.contact, appointment.type, appointment.start, appointment.end FROM appointment JOIN customer ON customer.customerId = appointment.customerId WHERE appointment.start BETWEEN ? AND ?";

        try {
            PreparedStatement statment = DBConnection.startConnection().prepareStatement(query);
            statment.setString(1, nowString);
            statment.setString(2, weekLaterString);

            ResultSet rs = statment.executeQuery();

            while (rs.next()) {
                Calendar returnedCalendar = new Calendar();
                returnedCalendar.setAppointmentId(rs.getInt("appointmentId"));
                returnedCalendar.setCustomerId(rs.getInt("customerId"));
                returnedCalendar.setCustomerName(rs.getString("customerName"));
                returnedCalendar.setUserId(rs.getInt("userId"));
                returnedCalendar.setTitle(rs.getString("title"));
                returnedCalendar.setDescription(rs.getString("description"));
                returnedCalendar.setLocation(rs.getString("location"));
                returnedCalendar.setContact(rs.getString("contact"));
                returnedCalendar.setType(rs.getString("type"));

                LocalDateTime startUTC = rs.getTimestamp("start").toLocalDateTime();
                LocalDateTime startSysDefault = startUTC.atZone(java.time.ZoneOffset.UTC).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                Timestamp startSysDefaultTimestamp = Timestamp.valueOf(startSysDefault);

                returnedCalendar.setStart(startSysDefaultTimestamp);

                LocalDateTime endUTC = rs.getTimestamp("end").toLocalDateTime();
                LocalDateTime endSysDefault = endUTC.atZone(java.time.ZoneOffset.UTC).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                Timestamp EndSysDefaultTimestamp = Timestamp.valueOf(endSysDefault);

                returnedCalendar.setEnd(EndSysDefaultTimestamp);

                calendar.add(returnedCalendar);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return calendar;
    }
    //method for getting the monthly appointments calendar view from the database

    /**
     *
     * @return
     */
    public static ObservableList<Calendar> getMonthlyAppointments() {
        ObservableList<Calendar> calendar = FXCollections.observableArrayList();

        LocalDate now = LocalDate.now();
        LocalDate weekLater = now.plusDays(30);
        String nowString = now.toString();
        String weekLaterString = weekLater.toString();

        String query = "SELECT appointment.appointmentId, appointment.customerId, customerName, appointment.userId, appointment.title, appointment.description, appointment.location, appointment.contact, appointment.type, appointment.start, appointment.end FROM appointment JOIN customer ON customer.customerId = appointment.customerId WHERE appointment.start BETWEEN ? AND ?";

        try {
            PreparedStatement statment = DBConnection.startConnection().prepareStatement(query);
            statment.setString(1, nowString);
            statment.setString(2, weekLaterString);

            ResultSet rs = statment.executeQuery();

            while (rs.next()) {
                Calendar returnedCalendar = new Calendar();
                returnedCalendar.setAppointmentId(rs.getInt("appointmentId"));
                returnedCalendar.setCustomerId(rs.getInt("customerId"));
                returnedCalendar.setCustomerName(rs.getString("customerName"));
                returnedCalendar.setUserId(rs.getInt("userId"));
                returnedCalendar.setTitle(rs.getString("title"));
                returnedCalendar.setDescription(rs.getString("description"));
                returnedCalendar.setLocation(rs.getString("location"));
                returnedCalendar.setContact(rs.getString("contact"));
                returnedCalendar.setType(rs.getString("type"));

                LocalDateTime startUTC = rs.getTimestamp("start").toLocalDateTime();
                LocalDateTime startSysDefault = startUTC.atZone(java.time.ZoneOffset.UTC).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                Timestamp startSysDefaultTimestamp = Timestamp.valueOf(startSysDefault);

                returnedCalendar.setStart(startSysDefaultTimestamp);

                LocalDateTime endUTC = rs.getTimestamp("end").toLocalDateTime();
                LocalDateTime endSysDefault = endUTC.atZone(java.time.ZoneOffset.UTC).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                Timestamp EndSysDefaultTimestamp = Timestamp.valueOf(endSysDefault);

                returnedCalendar.setEnd(EndSysDefaultTimestamp);

                calendar.add(returnedCalendar);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return calendar;
    }
    //method for getting all appointments from the database

    /**
     *
     * @return
     */
    public static ObservableList<Calendar> getAllAppointments() {
        ObservableList<Calendar> calendar = FXCollections.observableArrayList();

        String query = "SELECT appointment.appointmentId, appointment.customerId, customerName, appointment.userId, appointment.title, appointment.description, appointment.location, appointment.contact, appointment.type, appointment.start, appointment.end FROM appointment JOIN customer ON customer.customerId = appointment.customerId";

        try {
            PreparedStatement statment = DBConnection.startConnection().prepareStatement(query);

            ResultSet rs = statment.executeQuery();

            while (rs.next()) {
                Calendar returnedCalendar = new Calendar();
                returnedCalendar.setAppointmentId(rs.getInt("appointmentId"));
                returnedCalendar.setCustomerId(rs.getInt("customerId"));
                returnedCalendar.setCustomerName(rs.getString("customerName"));
                returnedCalendar.setUserId(rs.getInt("userId"));
                returnedCalendar.setTitle(rs.getString("title"));
                returnedCalendar.setDescription(rs.getString("description"));
                returnedCalendar.setLocation(rs.getString("location"));
                returnedCalendar.setContact(rs.getString("contact"));
                returnedCalendar.setType(rs.getString("type"));

                LocalDateTime startUTC = rs.getTimestamp("start").toLocalDateTime();
                LocalDateTime startSysDefault = startUTC.atZone(java.time.ZoneOffset.UTC).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                Timestamp startSysDefaultTimestamp = Timestamp.valueOf(startSysDefault);

                returnedCalendar.setStart(startSysDefaultTimestamp);

                LocalDateTime endUTC = rs.getTimestamp("end").toLocalDateTime();
                LocalDateTime endSysDefault = endUTC.atZone(java.time.ZoneOffset.UTC).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                Timestamp EndSysDefaultTimestamp = Timestamp.valueOf(endSysDefault);

                returnedCalendar.setEnd(EndSysDefaultTimestamp);

                calendar.add(returnedCalendar);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return calendar;
    }

}
