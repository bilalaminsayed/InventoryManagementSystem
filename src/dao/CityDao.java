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
import model.City;
import utils.DBConnection;

/**
 *
 * @author Bilal Sayed
 */
public class CityDao {
    //method for getting all cities from the databse

    /**
     *
     * @return
     */
    public static ObservableList<City> getAllCities() {
        ObservableList<City> cities = FXCollections.observableArrayList();

        String query = "SELECT cityId, city FROM city";
        try {
            PreparedStatement statment = DBConnection.startConnection().prepareStatement(query);
            ResultSet rs = statment.executeQuery();

            while (rs.next()) {
                City returnedCity = new City();
                returnedCity.setCityId(rs.getInt("cityId"));
                returnedCity.setCity(rs.getString("city"));

                cities.add(returnedCity);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return cities;
    }
    //method for getting specific city from the databse

    /**
     *
     * @param cityId
     * @return
     */
    public static City getCity(int cityId) {
        City returnedCity = new City();

        String query = "SELECT cityId, city FROM city WHERE cityId = ?";
        try {
            PreparedStatement statment = DBConnection.startConnection().prepareStatement(query);
            statment.setInt(1, cityId);
            ResultSet rs = statment.executeQuery();

            while (rs.next()) {
                returnedCity.setCityId(rs.getInt("cityId"));
                returnedCity.setCity(rs.getString("city"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return returnedCity;
    }
}
