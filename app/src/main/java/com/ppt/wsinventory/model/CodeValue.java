package com.ppt.wsinventory.model;

/**
 * Created by User on 29/01/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CodeValue {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("value")
    @Expose
    private String value;

    /**
     *
     * @return
     * The code
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * @param code
     * The code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     *
     * @return
     * The value
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value
     * The value
     */
    public void setValue(String value) {
        this.value = value;
    }

    public CodeValue() {
    }

    public CodeValue(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
