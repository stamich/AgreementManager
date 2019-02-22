package pl.codecity.helper;

import org.springframework.util.StringUtils;

@SuppressWarnings("serial")
public class SystemSearchHelper {

    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public boolean isEmpty(){
        if(StringUtils.hasText(getKeyword())){
            return false;
        }
        return true;
    }

    public static class Builder{

        private String keyword;

        public Builder keyword(String keyword) {
            this.keyword = keyword;
            return this;
        }

        public SystemSearchHelper build(){
            SystemSearchHelper helper = new SystemSearchHelper();
            helper.keyword = keyword;
            return helper;
        }
    }
}
