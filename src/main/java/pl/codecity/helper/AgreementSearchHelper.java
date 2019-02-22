package pl.codecity.helper;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("serial")
public class AgreementSearchHelper implements Serializable {

    private String keyword;
    private LocalDate validFrom;
    private LocalDate validTo;
    private Double amount;
    private boolean active;
    private Collection<Long> systemIds;

    public AgreementSearchHelper(){
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public AgreementSearchHelper withKeyword(String keyword){
        this.keyword = keyword;
        return this;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public AgreementSearchHelper withValidFrom(LocalDate validFrom){
        this.validFrom = validFrom;
        return this;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

    public AgreementSearchHelper withValidTo(LocalDate validTo){
        this.validTo = validTo;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public AgreementSearchHelper withAmount(Double amount){
        this.amount = amount;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Collection<Long> getSystemIds() {
        return systemIds;
    }

    public void setSystemIds(Collection<Long> systemIds) {
        this.systemIds = systemIds;
    }

    public AgreementSearchHelper withSystemIds(Long... systems){
        if(getSystemIds() == null){
            setSystemIds(new ArrayList<>(systems.length));
        }
        for(Long value: systems){
            getSystemIds().add(value);
        }
        return this;
    }

    public AgreementSearchHelper withSystemIds(Collection<Long> systems){
        if(systems == null){
            this.systemIds = null;
        } else {
            this.systemIds = new ArrayList<>(systems);
        }
        return this;
    }

    public boolean isEmpty(){
        if(StringUtils.hasText(getKeyword())){
            return false;
        }
        if(getValidFrom() != null){
            return false;
        }
        if(getValidTo() != null){
            return false;
        }
        if(getAmount() != null){
            return false;
        }
        if(!CollectionUtils.isEmpty(getSystemIds())){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
