package pl.codecity.helper;

import pl.codecity.model.Agreement;
import pl.codecity.model.Client;
import pl.codecity.model.SystemDictionary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("serial")
public class SystemCreateHelper implements Serializable {

    private String description;
    private String technology;
    private String comments;
    private Set<Agreement> agreements = new HashSet<>();
    private Set<Client> clients = new HashSet<>();
    List<SystemDictionary> systemDictionaries = new ArrayList<>();

    public String getDescription() {
        return description;
    }

    public String getTechnology() {
        return technology;
    }

    public String getComments() {
        return comments;
    }

    public Set<Agreement> getAgreements() {
        return agreements;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public List<SystemDictionary> getSystemDictionaries() {
        return systemDictionaries;
    }

    public static class Builder{

        private String description;
        private String technology;
        private String comments;
        private Set<Agreement> agreements = new HashSet<>();
        private Set<Client> clients = new HashSet<>();
        List<SystemDictionary> systemDictionaries = new ArrayList<>();

        public Builder(){
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder technology(String technology) {
            this.technology = technology;
            return this;
        }

        public Builder comments(String comments) {
            this.comments = comments;
            return this;
        }

        public Builder agreements(Set<Agreement> agreements) {
            this.agreements = agreements;
            return this;
        }

        public Builder clients(Set<Client> clients) {
            this.clients = clients;
            return this;
        }

        public Builder systemDictionaries(List<SystemDictionary> systemDictionaries) {
            this.systemDictionaries = systemDictionaries;
            return this;
        }

        public SystemCreateHelper build(){
            SystemCreateHelper helper = new SystemCreateHelper();
            helper.description = description;
            helper.technology = technology;
            helper.comments = comments;
            helper.agreements = agreements;
            helper.clients = clients;
            helper.systemDictionaries = systemDictionaries;
            return helper;
        }
    }
}
