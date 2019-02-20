package pl.codecity.helper;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ClientDeleteHelper implements Serializable {

    private Long id;

    public static class Builder{

        private Long id;

        public Builder(){
        }

        public Builder(Long id) {
            this.id = id;
        }

        public ClientDeleteHelper build(){
            ClientDeleteHelper helper = new ClientDeleteHelper();
            helper.id = id;
            return helper;
        }
    }
}
