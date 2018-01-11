package com.ppt.wsinventory.model;

/**
 * Created by ThinkCentre on 1/10/2018.
 */

public class AdministrationLocations {
    public static final String TABLE_ADMINISTRATION_LOCATIONS = "administration_locations";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LOC_NAME  = "loc_name";
    public static final String COLUMN_LOC_ADDR  = "loc_addr";
    public static final String COLUMN_LONGITUDE  = "longitude";
    public static final String COLUMN_LATITUDE  = "latitude";
    public static final String COLUMN_RECEIVING_BIN  = "receiving_bin";
    public static final String COLUMN_ACTIVE  = "active";

    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_ID ,
            COLUMN_LOC_NAME,
            COLUMN_LOC_ADDR ,
            COLUMN_LONGITUDE ,
            COLUMN_LATITUDE ,
            COLUMN_RECEIVING_BIN ,
            COLUMN_ACTIVE
    };

    private String id ;
    private String loc_name ;
    private String loc_addr ;
    private Double longitude ;
    private Double latitude ;
    private String receiving_bin ;
    private boolean active ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoc_name() {
        return loc_name;
    }

    public void setLoc_name(String loc_name) {
        this.loc_name = loc_name;
    }

    public String getLoc_addr() {
        return loc_addr;
    }

    public void setLoc_addr(String loc_addr) {
        this.loc_addr = loc_addr;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getReceiving_bin() {
        return receiving_bin;
    }

    public void setReceiving_bin(String receiving_bin) {
        this.receiving_bin = receiving_bin;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "AdministrationLocations{" +
                "loc_name='" + loc_name + '\'' +
                ", loc_addr='" + loc_addr + '\'' +
                ", receiving_bin='" + receiving_bin + '\'' +
                '}';
    }
}
