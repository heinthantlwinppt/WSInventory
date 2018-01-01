package com.ppt.wsinventory.model;

/**
 * Created by arkar on 2/1/2018.
 */

public class ApiModel {
    int id;
    String name;
    String type;
    String message;

    public ApiModel() {
    }

    public ApiModel(int id, String name, String type, String message) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ApiModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
