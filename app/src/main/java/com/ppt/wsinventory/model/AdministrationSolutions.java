package com.ppt.wsinventory.model;

/**
 * Created by User on 08/01/2018.
 */

public class AdministrationSolutions {

    public static final String TABLE_ADMINISTRATION_SOLUTIONS = "administration_solutions";
    public static final String COLUMN_SOLUTION_NAME = "solution_name";
    public static final String COLUMN_ACTIVE  = "active";

    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_SOLUTION_NAME ,
            COLUMN_ACTIVE


    };

    private long id ;
    private String solution_name ;
    private boolean active ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSolution_name() {
        return solution_name;
    }

    public void setSolution_name(String solution_name) {
        this.solution_name = solution_name;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "AdministrationSolutions{" +
                "solution_name='" + solution_name + '\'' +
                ", active='" + active + '\'' +
                '}';
    }
}
