/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import utils.DBConnection;

/**
 *
 * @author Bilal Sayed
 */
public class CustomerDao {
    //method for getting the next customer id from the databse

    /**
     *
     * @return
     */
    public static int nextId() {
        int nextId = 0;

        String query = "SELECT MAX(customerId) FROM customer WHERE customerId";
        try {
            PreparedStatement statment = DBConnection.startConnection().prepareStatement(query);
            ResultSet rs = statment.executeQuery();

            while (rs.next()) {
                nextId = rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nextId + 1;
    }
    //method for adding a customer to the databse

    /**
     *
     * @param customer
     */
    public static void addCustomer(Customer customer) {
        String query1 = "INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES (?, '', ?, ?,  ?, NOW(), ?, NOW(), ?)";
        String query2 = "SELECT addressId FROM address WHERE phone = ?";
        String query3 = "INSERT INTO customer(customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES (?, ?, 1, NOW(), ?, NOW(), ?)";
        try {
            PreparedStatement statment1 = DBConnection.startConnection().prepareStatement(query1);
            PreparedStatement statment2 = DBConnection.startConnection().prepareStatement(query2);
            PreparedStatement statment3 = DBConnection.startConnection().prepareStatement(query3);

            statment1.setString(1, customer.getAddress());
            statment1.setInt(2, customer.getCityId());
            statment1.setString(3, customer.getPostalCode());
            statment1.setString(4, customer.getPhone());
            statment1.setString(5, UserDao.getLoggedInUser().getUserName());
            statment1.setString(6, UserDao.getLoggedInUser().getUserName());

            statment1.executeUpdate();

            statment2.setString(1, customer.getPhone());

            ResultSet rs = statment2.executeQuery();
            while (rs.next()) {
                customer.setAddressId(rs.getInt("addressId"));
            }

            statment3.setString(1, customer.getCustomerName());
            statment3.setInt(2, customer.getAddressId());
            statment3.setString(3, UserDao.getLoggedInUser().getUserName());
            statment3.setString(4, UserDao.getLoggedInUser().getUserName());

            statment3.executeUpdate();

            System.out.println("Customer added successfully");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //method for getting a specific customer from the databse

    /**
     *
     * @param customerId
     * @return
     */
    public static Customer getCustomer(int customerId) {
        Customer returnedCustomer = new Customer();

        String query = "SELECT DISTINCT customer.customerId, customer.customerName, address.address, address.postalCode, address.phone, address.addressId, city.cityId, country.countryId FROM customer JOIN address ON customer.addressId = address.addressId JOIN city ON address.cityId = city.cityId JOIN country ON city.countryId = country.countryId WHERE customerId = ?";
        try {
            PreparedStatement statment = DBConnection.startConnection().prepareStatement(query);
            statment.setInt(1, customerId);
            ResultSet rs = statment.executeQuery();

            while (rs.next()) {
                returnedCustomer.setCustomerId(rs.getInt("customerId"));
                returnedCustomer.setCustomerName(rs.getString("customerName"));
                returnedCustomer.setAddress(rs.getString("address"));
                returnedCustomer.setPostalCode(rs.getString("postalCode"));
                returnedCustomer.setPhone(rs.getString("phone"));
                returnedCustomer.setAddressId(rs.getInt("addressId"));
                returnedCustomer.setCityId(rs.getInt("cityId"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return returnedCustomer;
    }
    //method for getting all customers from the database 

    /**
     *
     * @return
     */
    public static ObservableList<Customer> getAllCustomers() {
        ObservableList<Customer> customers = FXCollections.observableArrayList();

        String query = "SELECT DISTINCT customer.customerId, customer.customerName, address.address, address.postalCode, address.phone, address.addressId, city.cityId, country.countryId FROM customer JOIN address ON customer.addressId = address.addressId JOIN city ON address.cityId = city.cityId JOIN country ON city.countryId = country.countryId";
        try {
            PreparedStatement statment = DBConnection.startConnection().prepareStatement(query);
            ResultSet rs = statment.executeQuery();

            while (rs.next()) {
                Customer returnedCustomer = new Customer();
                returnedCustomer.setCustomerId(rs.getInt("customerId"));
                returnedCustomer.setCustomerName(rs.getString("customerName"));
                returnedCustomer.setAddress(rs.getString("address"));
                returnedCustomer.setPostalCode(rs.getString("postalCode"));
                returnedCustomer.setPhone(rs.getString("phone"));
                returnedCustomer.setAddressId(rs.getInt("addressId"));
                returnedCustomer.setCityId(rs.getInt("cityId"));

                customers.add(returnedCustomer);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return customers;
    }
    //method for updating a customer in the databse

    /**
     *
     * @param customer
     */
    public static void updateCustomer(Customer customer) {
        String query1 = "UPDATE address SET address = ?, cityId = ?, postalCode = ?, phone = ?,  lastUpdate = NOW(), lastUpdateBy = ? WHERE addressId = ?";
        String query2 = "UPDATE customer SET customerName = ?, lastUpdate = NOW(), lastUpdateBy = ? WHERE customerId = ?";
        try {
            PreparedStatement statment1 = DBConnection.startConnection().prepareStatement(query1);
            PreparedStatement statment2 = DBConnection.startConnection().prepareStatement(query2);

            statment1.setString(1, customer.getAddress());
            statment1.setInt(2, customer.getCityId());
            statment1.setString(3, customer.getPostalCode());
            statment1.setString(4, customer.getPhone());
            statment1.setString(5, UserDao.getLoggedInUser().getUserName());
            statment1.setInt(6, customer.getAddressId());

            statment1.executeUpdate();

            statment2.setString(1, customer.getCustomerName());
            statment2.setString(2, UserDao.getLoggedInUser().getUserName());
            statment2.setInt(3, customer.getCustomerId());

            statment2.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //method for deleting a customer from the databse

    /**
     *
     * @param customer
     */
    public static void deleteCustomer(Customer customer) {
        String query1 = "DELETE FROM appointment WHERE customerId = ?";
        String query2 = "DELETE FROM customer WHERE customerId = ?";

        try {
            PreparedStatement statment1 = DBConnection.startConnection().prepareStatement(query1);
            PreparedStatement statment2 = DBConnection.startConnection().prepareStatement(query2);

            statment1.setInt(1, customer.getCustomerId());
            statment1.executeUpdate();

            statment2.setInt(1, customer.getCustomerId());
            statment2.executeUpdate();

            System.out.println(statment2.getUpdateCount() + "row(s) affected.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
