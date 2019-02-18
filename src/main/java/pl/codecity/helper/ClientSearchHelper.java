package pl.codecity.helper;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import pl.codecity.model.Client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("serial")
public class ClientSearchHelper implements Serializable {

    private String keyword;
    private List<Client.Type> types;

    //

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public ClientSearchHelper withKeyword(String keyword){
        this.keyword = keyword;
        return this;
    }

    public List<Client.Type> getTypes() {
        return types;
    }

    public void setTypes(List<Client.Type> types) {
        this.types = types;
    }

    public ClientSearchHelper withType(Client.Type... types){
        if(getTypes() == null){
            setTypes(new ArrayList<>(types.length));
        }
        for (Client.Type value : types){
            getTypes().add(value);
        }
        return this;
    }

    public ClientSearchHelper withType(Collection<Client.Type> types){
        if(types == null){
            this.types = null;
        } else {
            this.types = new ArrayList<>(types);
        }
        return this;
    }

    public boolean isEmpty(){
        if(StringUtils.hasText(getKeyword())){
            return false;
        }
        if(!CollectionUtils.isEmpty(getTypes())){
            return false;
        }
        return true;
    }
}
