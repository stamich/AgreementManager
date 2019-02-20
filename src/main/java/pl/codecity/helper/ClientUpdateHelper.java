package pl.codecity.helper;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ClientUpdateHelper implements Serializable {

    private Long id;
    private String name;
    private String country;
    private String city;
    private String street;
    private String home;
    private String email;
    private String phone;
    private boolean active;
    private String comments;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHome() {
        return home;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isActive() {
        return active;
    }

    public String getComments() {
        return comments;
    }

    public static class Builder{

        private Long id;
        private String name;
        private String country;
        private String city;
        private String street;
        private String home;
        private String email;
        private String phone;
        private boolean active;
        private String comments;

        public Builder(){
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public Builder home(String home) {
            this.home = home;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder active(boolean active) {
            this.active = active;
            return this;
        }

        public Builder comments(String comments) {
            this.comments = comments;
            return this;
        }

        public ClientUpdateHelper build(){
            ClientUpdateHelper helper = new ClientUpdateHelper();
            helper.id = id;
            helper.name = name;
            helper.country = country;
            helper.city = city;
            helper.street = street;
            helper.home = home;
            helper.phone = phone;
            helper.email = email;
            helper.active = active;
            helper.comments = comments;
            return helper;
        }
    }
}
