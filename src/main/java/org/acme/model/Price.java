package org.acme.model;

public class Price {
    private CurrencyValue value;

    public CurrencyValue getValue() { return value; }
    public void setValue(CurrencyValue value) { this.value = value; }
}

class CurrencyValue {
    private Integer gp;
    private Integer sp;
    private Integer cp;
    
    public Integer getGp() { return gp; }
    public void setGp(Integer gp) { this.gp = gp; }
    
    public Integer getSp() { return sp; }
    public void setSp(Integer sp) { this.sp = sp; }
    
    public Integer getCp() { return cp; }
    public void setCp(Integer cp) { this.cp = cp; }
}
