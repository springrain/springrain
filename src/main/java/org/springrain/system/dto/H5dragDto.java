package org.springrain.system.dto;

import java.io.Serializable;

public class H5dragDto implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String imgVal;
    private String urlVal;
    private String titleVal;
    private String subTitleVal;
    private String priceVal;
    private String salePriceVal;
    public String getImgVal() {
        return imgVal;
    }
    public void setImgVal(String imgVal) {
        this.imgVal = imgVal;
    }
    public String getUrlVal() {
        return urlVal;
    }
    public void setUrlVal(String urlVal) {
        this.urlVal = urlVal;
    }
    public String getTitleVal() {
        return titleVal;
    }
    public void setTitleVal(String titleVal) {
        this.titleVal = titleVal;
    }
    public String getSubTitleVal() {
        return subTitleVal;
    }
    public void setSubTitleVal(String subTitleVal) {
        this.subTitleVal = subTitleVal;
    }
    public String getPriceVal() {
        return priceVal;
    }
    public void setPriceVal(String priceVal) {
        this.priceVal = priceVal;
    }
    public String getSalePriceVal() {
        return salePriceVal;
    }
    public void setSalePriceVal(String salePriceVal) {
        this.salePriceVal = salePriceVal;
    }

}
