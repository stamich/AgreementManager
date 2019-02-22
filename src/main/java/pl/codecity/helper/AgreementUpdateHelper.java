package pl.codecity.helper;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class AgreementUpdateHelper implements Serializable {

    private Long id;
    private String number;
    private LocalDate validFrom;
    private LocalDate validTo;
    private Double amount;
    private boolean active;
    private String comments;
    private List<System> systems = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public Double getAmount() {
        return amount;
    }

    public boolean isActive() {
        return active;
    }

    public String getComments() {
        return comments;
    }

    public List<System> getSystems() {
        return systems;
    }

    public static class Builder{

        private Long id;
        private String number;
        private LocalDate validFrom;
        private LocalDate validTo;
        private Double amount;
        private boolean active;
        private String comments;
        private List<System> systems = new ArrayList<>();

        public Builder(){

        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder number(String number) {
            this.number = number;
            return this;
        }

        public Builder validFrom(LocalDate validFrom) {
            this.validFrom = validFrom;
            return this;
        }

        public Builder validTo(LocalDate validTo) {
            this.validTo = validTo;
            return this;
        }

        public Builder amount(Double amount) {
            this.amount = amount;
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

        public Builder systems(List<System> systems) {
            this.systems = systems;
            return this;
        }

        public AgreementUpdateHelper build(){
            AgreementUpdateHelper helper = new AgreementUpdateHelper();
            helper.id = id;
            helper.number = number;
            helper.validFrom = validFrom;
            helper.validTo = validTo;
            helper.amount = amount;
            helper.active = active;
            helper.comments = comments;
            helper.systems = systems;
            return helper;
        }
    }
}
