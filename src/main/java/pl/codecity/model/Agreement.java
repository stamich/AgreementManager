package pl.codecity.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "AGREEMENT")
@SuppressWarnings("serial")
public class Agreement extends AbstractDomainObject<Long> {

    public enum Period{
        WEEK, MONTH, QUARTER, SEMESTER, YEAR
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AGREEMENT_NUMBER", nullable = false, unique = true, length = 50)
    private String agreementNumber;

    @Column(name = "VALID_FROM", nullable = false)
    private LocalDate validFrom;

    @Column(name = "VALID_TO", nullable = false)
    private LocalDate validTo;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private boolean active;

    @Column
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID", nullable = false)
    private System system;

    // Getter and setter

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgreementNumber() {
        return agreementNumber;
    }

    public void setAgreementNumber(String agreementNumber) {
        this.agreementNumber = agreementNumber;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
        return "Agreement: " + getAgreementNumber();
    }
}
