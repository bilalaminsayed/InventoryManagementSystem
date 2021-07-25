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
public class Customer {

    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private int addressId;
    private int cityId;

    /**
     *
     * @param customerId
     * @param customerName
     * @param address
     * @param phone
     * @param addressId
     * @param cityId
     */
    public Customer(int customerId, String customerName, String address, String phone, int addressId, int cityId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.addressId = addressId;
        this.cityId = cityId;
    }

    /**
     *
     */
    public Customer() {
    }

    @Override
    public String toString() {
        return customerId + " " + customerName;
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
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     *
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     *
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     */
    public int getAddressId() {
        return addressId;
    }

    /**
     *
     * @param addressId
     */
    public void setAddressId(int addressId) {
        this.addressId = addressId;
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
}
