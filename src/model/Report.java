/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author Bilal Sayed
 */
public class Report {

    private String type;
    private int count;

    private int appointmentId;
    private int customerId;
    private int userId;
    private String customerName;
    private String title;
    private String description;
    private String location;
    private String contact;
    private Timestamp start;
    private Timestamp end;

    /**
     *
     * @param type
     * @param count
     * @param appointmentId
     * @param customerId
     * @param customerName
     * @param title
     * @param description
     * @param location
     * @param contact
     * @param start
     * @param end
     */
    public Report(String type, int count, int appointmentId, int customerId, String customerName, String title, String description, String location, String contact, Timestamp start, Timestamp end) {
        this.type = type;
        this.count = count;
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.start = start;
        this.end = end;
    }

    /**
     *
     */
    public Report() {
    }

    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    public int getCount() {
        return count;
    }

    /**
     *
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     *
     * @return
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     *
     * @param appointmentId
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     *
     * @return
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     *
     * @param customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     *
     * @return
     */
    public int getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     *
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     *
     * @return
     */
    public String getContact() {
        return contact;
    }

    /**
     *
     * @param contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     *
     * @return
     */
    public Timestamp getStart() {
        return start;
    }

    /**
     *
     * @param start
     */
    public void setStart(Timestamp start) {
        this.start = start;
    }

    /**
     *
     * @return
     */
    public Timestamp getEnd() {
        return end;
    }

    /**
     *
     * @param end
     */
    public void setEnd(Timestamp end) {
        this.end = end;
    }
}
