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
import model.User;
import utils.DBConnection;

/**
 *
 * @author Bilal Sayed
 */
public class UserDao {

    //variable to store logged in user
    private static User loggedInUser;
    //method to verify userename and password from database

    /**
     *
     * @param userName
     * @param password
     * @return
     */
    public static User login(String userName, String password) {
        User returnedUser = new User();

        String query = "SELECT userId, username, password from user where userName = ? and password = ?";
        try {
            PreparedStatement statment = DBConnection.startConnection().prepareStatement(query);
            statment.setString(1, userName);
            statment.setString(2, password);
            ResultSet rs = statment.executeQuery();

            if (rs.next()) {
                returnedUser.setUserId(rs.getInt("userId"));
                returnedUser.setUserName(rs.getString("username"));
                returnedUser.setPassword(rs.getString("password"));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return returnedUser;
    }
    //method to get logged in user

    /**
     *
     * @return
     */
    public static User getLoggedInUser() {
        return loggedInUser;
    }
    //set logged in user

    /**
     *
     * @param loggedInUser
     */
    public static void setLoggedInUser(User loggedInUser) {
        UserDao.loggedInUser = loggedInUser;
    }
    //method to get all users from the database

    /**
     *
     * @return
     */
    public static ObservableList<User> getAllUsers() {
        ObservableList<User> user = FXCollections.observableArrayList();

        String query = "SELECT userId, username from user";
        try {
            PreparedStatement statment = DBConnection.startConnection().prepareStatement(query);
            ResultSet rs = statment.executeQuery();

            while (rs.next()) {
                User returnedUser = new User();
                returnedUser.setUserId(rs.getInt("userId"));
                returnedUser.setUserName(rs.getString("username"));

                user.add(returnedUser);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }
}
