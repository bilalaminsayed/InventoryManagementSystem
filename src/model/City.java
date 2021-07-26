/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Bilal Sayed
 */
public class City {

    private int cityId;
    private String city;

    /**
     *
     * @param cityId
     * @param city
     */
    public City(int cityId, String city) {
        this.cityId = cityId;
        this.city = city;
    }

    /**
     *
     */
    public City() {
    }

    @Override
    public String toString() {
        return (city);
    }

    /**
     *
     * @return
     */
    public int getCityId() {
        return cityId;
    }

    /**
     *
     * @param cityId
     */
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    /**
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }
}
