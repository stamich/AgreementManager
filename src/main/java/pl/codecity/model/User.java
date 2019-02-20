package pl.codecity.model;

import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@Table(name = "`user`")
public class User extends AbstractDomainObject<Long> {

    public enum Role{
        ADMIN, DBA, EMPLOYEE, GUEST
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LOGIN_ID")
    private String loginId;

    @Column(name = "FIRST_NAME", length = 50)
    private String firstName;

    @Column(name = "LAST_NAME", length = 50)
    private String lastName;

    @Column(name = "NICK_NAME", length = 30)
    private String nickName;

    @Column(length = 100)
    private String email;

    @Column(length = 100)
    private String password;

    @Column
    private Boolean active;

    @SortNatural
    @ElementCollection
    @JoinTable(name = "user_role")
    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", length = 20, nullable = false)
    private SortedSet<Role> roles = new TreeSet<>();

    //

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public SortedSet<Role> getRoles() {
        return roles;
    }

    public void setRoles(SortedSet<Role> roles) {
        this.roles = roles;
    }

    //

    @Override
    public String print() {
        return "User: " + getNickName();
    }
}
