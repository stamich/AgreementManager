package pl.codecity.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "SYSTEM")
@SuppressWarnings("serial")
public class System extends AbstractDomainObject<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @Column
    private String technology;

    @Column
    private String comments;

    @OneToMany(mappedBy = "system", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    private Set<Agreement> agreements = new HashSet<>();

    @OneToMany(mappedBy = "system", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    private Set<Client> clients = new HashSet<>();

    @OneToMany(mappedBy = "system", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    private List<SystemDictionary> systemDictionaries = new ArrayList<>();

    // Getter and setter

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Set<Agreement> getAgreements() {
        return agreements;
    }

    public void setAgreements(Set<Agreement> agreements) {
        this.agreements = agreements;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public List<SystemDictionary> getSystemDictionaries() {
        return systemDictionaries;
    }

    public void setSystemDictionaries(List<SystemDictionary> systemDictionaries) {
        this.systemDictionaries = systemDictionaries;
    }

    //

    @Override
    public String print() {
        long primitive = getId();
        int number = (int) primitive;
        return "System name: " + systemDictionaries.get(number);
    }
}
