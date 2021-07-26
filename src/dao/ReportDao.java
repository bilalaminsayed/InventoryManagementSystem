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
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.Report;
import model.User;
import utils.DBConnection;

/**
 *
 * @author Bilal Sayed
 */
public class ReportDao {
    //method for getting the type report from the database

    /**
     *
     * @return
     */
    public static ObservableList<Report> getTypeReport() {
        ObservableList<Report> report = FXCollections.observableArrayList();

        LocalDate now = LocalDate.now();
        LocalDate weekLater = now.plusDays(30);
        String nowString = now.toString();
        String weekLaterString = weekLater.toString();

        String query = "SELECT type, count(*) AS count FROM appointment WHERE start BETWEEN ? AND ? GROUP BY type";

        try {
            PreparedStatement statment = DBConnection.startConnection().prepareStatement(query);
            statment.setString(1, nowString);
            statment.setString(2, weekLaterString);

            ResultSet rs = statment.executeQuery();

            while (rs.next()) {
                Report returnedReport = new Report();
                returnedReport.setType(rs.getString("type"));
                returnedReport.setCount(rs.getInt("count"));

                report.add(returnedReport);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return report;
    }
    //method for getting the user appointment report from the database

    /**
     *
     * @param user
     * @return
     */
    public static ObservableList<Report> getUserAppointmentReport(User user) {
        ObservableList<Report> report = FXCollections.observableArrayList();
        ObservableList<Report> userAppointmentReport = FXCollections.observableArrayList();

        String query = "SELECT appointment.appointmentId, appointment.customerId, appointment.userId, customer.customerName, appointment.title, appointment.description, appointment.location, appointment.contact, appointment.type, appointment.start, appointment.end FROM customer JOIN appointment ON customer.customerId = appointment.customerId";
        try {
            PreparedStatement statment = DBConnection.startConnection().prepareStatement(query);

            ResultSet rs = statment.executeQuery();

            while (rs.next()) {
                Report returnedReport = new Report();
                returnedReport.setAppointmentId(rs.getInt("appointmentId"));
                returnedReport.setCustomerId(rs.getInt("customerId"));
                returnedReport.setUserId(rs.getInt("userId"));
                returnedReport.setCustomerName(rs.getString("customerName"));
                returnedReport.setTitle(rs.getString("title"));
                returnedReport.setDescription(rs.getString("description"));
                returnedReport.setLocation(rs.getString("location"));
                returnedReport.setContact(rs.getString("contact"));

                LocalDateTime startUTC = rs.getTimestamp("start").toLocalDateTime();
                LocalDateTime startSysDefault = startUTC.atZone(java.time.ZoneOffset.UTC).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                Timestamp startSysDefaultTimestamp = Timestamp.valueOf(startSysDefault);

                returnedReport.setStart(startSysDefaultTimestamp);

                LocalDateTime endUTC = rs.getTimestamp("end").toLocalDateTime();
                LocalDateTime endSysDefault = endUTC.atZone(java.time.ZoneOffset.UTC).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                Timestamp EndSysDefaultTimestamp = Timestamp.valueOf(endSysDefault);

                returnedReport.setEnd(EndSysDefaultTimestamp);

                report.add(returnedReport);
            }
            //lambda to filter report by userId for the schedule for each consultant
            userAppointmentReport
                    = report.stream()
                            .filter(t -> t.getUserId() == user.getUserId())
                            .collect(Collectors.toCollection(FXCollections::observableArrayList));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return userAppointmentReport;
    }
    //method for getting the custom report from the databse

    /**
     *
     * @param customer
     * @return
     */
    public static ObservableList<Report> getCustomerAppointmentReport(Customer customer) {
        ObservableList<Report> report = FXCollections.observableArrayList();
        ObservableList<Report> customerAppointmentReport = FXCollections.observableArrayList();

        String query = "SELECT appointment.appointmentId, appointment.customerId, appointment.userId, appointment.title, appointment.description, appointment.location, appointment.contact, appointment.type, appointment.start, appointment.end FROM customer JOIN appointment ON customer.customerId = appointment.customerId";
        try {
            PreparedStatement statment = DBConnection.startConnection().prepareStatement(query);

            ResultSet rs = statment.executeQuery();

            while (rs.next()) {
                Report returnedReport = new Report();
                returnedReport.setAppointmentId(rs.getInt("appointmentId"));
                returnedReport.setCustomerId(rs.getInt("customerId"));
                returnedReport.setUserId(rs.getInt("userId"));
                returnedReport.setTitle(rs.getString("title"));
                returnedReport.setDescription(rs.getString("description"));
                returnedReport.setLocation(rs.getString("location"));
                returnedReport.setContact(rs.getString("contact"));

                LocalDateTime startUTC = rs.getTimestamp("start").toLocalDateTime();
                LocalDateTime startSysDefault = startUTC.atZone(java.time.ZoneOffset.UTC).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                Timestamp startSysDefaultTimestamp = Timestamp.valueOf(startSysDefault);

                returnedReport.setStart(startSysDefaultTimestamp);

                LocalDateTime endUTC = rs.getTimestamp("end").toLocalDateTime();
                LocalDateTime endSysDefault = endUTC.atZone(java.time.ZoneOffset.UTC).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                Timestamp EndSysDefaultTimestamp = Timestamp.valueOf(endSysDefault);

                returnedReport.setEnd(EndSysDefaultTimestamp);

                report.add(returnedReport);
            }
            //lambda to filter report by customerId for the custom report
            customerAppointmentReport
                    = report.stream()
                            .filter(t -> t.getCustomerId() == customer.getCustomerId())
                            .collect(Collectors.toCollection(FXCollections::observableArrayList));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return customerAppointmentReport;
    }
}
