package pl.codecity.helper;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AgreementDeleteHelper implements Serializable {

    private Long id;
    private String number;

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public static class Builder{

        private Long id;
        private String number;

        public Builder(){
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder number(String number){
            this.number = number;
            return this;
        }

        public AgreementDeleteHelper build(){
            AgreementDeleteHelper helper = new AgreementDeleteHelper();
            helper.id = id;
            helper.number = number;
            return helper;
        }
    }
}
