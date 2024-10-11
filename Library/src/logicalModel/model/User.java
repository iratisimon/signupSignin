/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicalModel.model;

import java.io.Serializable;

/**
 *
 * @author 2dam
 */
public class User implements Serializable {
    private String email;
    private String passwd;
    private String name;
    private String street;
    private int mobile;
    private String city;
    private int zip;
    private boolean active;

    public User() {
    }

    public User(String email, String passwd, String name, String street, int mobile, String city, int zip, boolean active) {
        this.email = email;
        this.passwd = passwd;
        this.name = name;
        this.street = street;
        this.mobile = mobile;
        this.city = city;
        this.zip = zip;
        this.active = active;
    }

    public User(String email, String passwd) {
        this.email = email;
        this.passwd = passwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String nick) {
        this.street = nick;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
