package com.ppt.wsinventory.model;

/**
 * Created by User on 08/02/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ReceiveSerial {

    @SerializedName("productserial")
    @Expose
    private String productserial;
    @SerializedName("lineno")
    @Expose
    private Integer lineno;
    @SerializedName("is_delete")
    @Expose
    private Boolean isDelete;
    @SerializedName("ts")
    @Expose
    private Date ts;

    public String getProductserial() {
        return productserial;
    }

    public void setProductserial(String productserial) {
        this.productserial = productserial;
    }

    public Integer getLineno() {
        return lineno;
    }

    public void setLineno(Integer lineno) {
        this.lineno = lineno;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

}
