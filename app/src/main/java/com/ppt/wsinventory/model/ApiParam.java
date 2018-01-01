package com.ppt.wsinventory.model;

/**
 * Created by arkar on 2/1/2018.
 */

public class ApiParam {
    String name;
    String value;

    public ApiParam() {
    }

    public ApiParam(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ApiParam{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
