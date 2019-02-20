package pl.codecity.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@SuppressWarnings("serial")
public class ClientDetails implements Serializable {

    private String country;
    private String city;
    private String street;
    private String home;
    private String email;
    private String phone;

    public ClientDetails(){
    }

    public ClientDetails(String country, String city, String street, String home, String email, String phone) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.home = home;
        this.email = email;
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return getCountry() + " " + getCity() + " " + getStreet() + " " + getHome() + " " + getEmail() + " " + getPhone();
    }
}
