package pl.codecity.helper;

public class UserCreateHelper {

    private String firstName;
    private String lastName;
    private String nickName;
    private String email;
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static class Builder{

        private String firstName;
        private String lastName;
        private String nickName;
        private String email;
        private String password;

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder nickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public UserCreateHelper build(){
            UserCreateHelper helper = new UserCreateHelper();
            helper.firstName = firstName;
            helper.lastName = lastName;
            helper.nickName = nickName;
            helper.email = email;
            helper.password = password;
            return helper;
        }
    }
}
