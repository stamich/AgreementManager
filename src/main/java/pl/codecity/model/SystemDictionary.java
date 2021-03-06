package pl.codecity.model;

import javax.persistence.*;

@Entity
@Table(name = "SYSTEM_DICTIONARY")
@SuppressWarnings("serial")
public class SystemDictionary extends AbstractDomainObject<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SYSYEM_NAME", nullable = false, unique = true, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private System system;

    // Getter and setter

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }

    //

    @Override
    public String print() {
        return "System: " + getName();
    }
}
