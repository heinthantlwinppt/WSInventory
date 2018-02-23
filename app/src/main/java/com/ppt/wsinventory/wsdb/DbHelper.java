package com.ppt.wsinventory.wsdb;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by User on 28/12/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String TAG = "SW-DbHelper";
    private static final String DATABASE_NAME = "WS.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_ITEMS = "Items";
    private static final String TABLE_CREATE_ITEMS =
            "CREATE TABLE Items \n" +
                    "(\n" +
                    " ItemName VARCHAR COLLATE NOCASE, \n" +
                    " ItemType VARCHAR COLLATE NOCASE\n" +
                    ")";

    private static final String TABLE_ADMINISTRATION_SETTINGS = "administration_settings";
    private static final String TABLE_CREATE_ADMINISTRATION_SETTINGS =
            "CREATE TABLE administration_settings \n" +
                    "(\n" +
                    "id VARCHAR COLLATE NOCASE, \n" +
                    "h1 VARCHAR COLLATE NOCASE, \n" +
                    "h2 VARCHAR COLLATE NOCASE, \n" +
                    "h3 VARCHAR COLLATE NOCASE, \n" +
                    "h4 VARCHAR COLLATE NOCASE, \n" +
                    "t1 VARCHAR COLLATE NOCASE, \n" +
                    "t2 VARCHAR COLLATE NOCASE, \n" +
                    "t3 VARCHAR COLLATE NOCASE, \n" +
                    "t4 VARCHAR COLLATE NOCASE, \n" +
                    "dateformat VARCHAR COLLATE NOCASE, \n" +
                    "timeformat VARCHAR COLLATE NOCASE, \n" +
                    "datetimeformat VARCHAR COLLATE NOCASE, \n" +
                    "system_date DATETIME, \n" +
                    "dashboarditemlwith INTEGER, \n" +
                    "dashboarditempwith INTEGER, \n" +
                    "dashboardicon VARCHAR COLLATE NOCASE, \n" +
                    "active BOOL, \n" +
                    "location_id VARCHAR COLLATE NOCASE, \n" +
                    "solution_id VARCHAR COLLATE NOCASE, \n" +
                    "staff_id VARCHAR COLLATE NOCASE, \n" +
                    "weighscales_id VARCHAR COLLATE NOCASE, \n" +
                    "device_id VARCHAR COLLATE NOCASE, \n" +
                    "devicetype_id VARCHAR COLLATE NOCASE \n" +
                    ")";

    private static final String TABLE_ADMINISTRATION_STAFF = "administration_staff";
    private static final String TABLE_CREATE_ADMINISTRATION_STAFF =
            "CREATE TABLE administration_staff \n" +
                    "(\n" +
                    " id  VARCHAR COLLATE NOCASE, \n" +
                    " staff_id  VARCHAR COLLATE NOCASE, \n" +
                    " staff_name  VARCHAR COLLATE NOCASE, \n" +
                    " father_name  VARCHAR COLLATE NOCASE, \n" +
                    " nick_name  VARCHAR COLLATE NOCASE, \n" +
                    " nrc_no  VARCHAR COLLATE NOCASE, \n" +
                    " address  VARCHAR COLLATE NOCASE, \n" +
                    " phone_no  VARCHAR COLLATE NOCASE, \n" +
                    " home_phone_no   VARCHAR COLLATE NOCASE, \n" +
                    " staff_photo   VARCHAR COLLATE NOCASE, \n" +
                    " date_joined   DATETIME, \n" +
                    " date_left   DATETIME, \n" +
                    " user_id    INTEGER, \n" +
                    " password   VARCHAR COLLATE NOCASE, \n" +
                    " active    BOOL, \n " +
                    " role    VARCHAR COLLATE NOCASE \n" +
                    ")";

    private static final String TABLE_INVENTORY_GOLD= "inventory_gold";
    private static final String TABLE_CREATE_INVENTORY_GOLD =
            "CREATE TABLE inventory_gold \n" +
                    "(\n" +
                    " id  INTERGER, \n" +
                    " name   VARCHAR COLLATE NOCASE, \n" +
                    " goldtype   SMALLINT, \n" +
                    " saleprice   NUMERIC, \n" +
                    " purchaseprice   NUMERIC, \n" +
                    " cost   NUMERIC, \n" +
                    " tax1   NUMERIC, \n" +
                    " tax2    NUMERIC, \n" +
                    " active    BOOL, \n" +
                    " code    VARCHAR COLLATE NOCASE\n" +
                    ")";

    private static final String TABLE_ADMINISTRATION_WSDASHBOARD = "administration_wsdashboard";
    private static final String TABLE_CREATE_ADMINISTRATION_WSDASHBOARD =
            "CREATE TABLE administration_wsdashboard \n" +
                    "(\n" +
                    " id VARCHAR COLLATE NOCASE, \n" +
                    " title VARCHAR COLLATE NOCASE, \n" +
                    " is_folder VARCHAR BOOL NOCASE, \n" +
                    " parent_id INTEGER, \n" +
                    " actionname  VARCHAR COLLATE NOCASE, \n" +
                    " groupname  VARCHAR COLLATE NOCASE, \n" +
                    " image  VARCHAR COLLATE NOCASE, \n" +
                    " timestamp  DATETIME, \n" +
                    " is_delete  BOOL, \n" +
                    " displayno  INTEGER, \n" +
                    " screen_id  INTEGER \n" +
                    ")";

    private static final String TABLE_ADMINISTRATION_SOLUTIONS = "administration_solutions";
    private static final String TABLE_CREATE_ADMINISTRATION_SOLUTIONS=
            "CREATE TABLE administration_solutions \n" +
                    "(\n" +
                    " solution_name  VARCHAR COLLATE NOCASE, \n" +
                    " active   BOOL\n" +
                    ")";

    private static final String TABLE_ADMINISTRATION_ROLE = "administration_role";
    private static final String TABLE_CREATE_ADMINISTRATION_ROLE =
            "CREATE TABLE administration_role \n" +
                    "(\n" +
                    " id  VARCHAR COLLATE NOCASE, \n" +
                    " role_name  VARCHAR COLLATE NOCASE, \n" +
                    " active    BOOL \n " +
                    ")";
    private static final String TABLE_ADMINISTRATION_LOCATIONS= "administration_locations";
    private static final String TABLE_CREATE_ADMINISTRATION_LOCATIONS =
            "CREATE TABLE administration_locations \n" +
                    "(\n" +
                    " id  INTERGER, \n" +
                    " loc_name   VARCHAR COLLATE NOCASE, \n" +
                    " loc_addr   VARCHAR COLLATE NOCASE, \n" +
                    " longitude   NUMERIC, \n" +
                    " latitude   NUMERIC, \n" +
                    " receiving_bin   VARCHAR COLLATE NOCASE, \n" +
                    " active    BOOL \n" +
                    ")";

    private static final String TABLE_INVENTROY_UOM= "inventory_uom";
    private static final String TABLE_CREATE_INVENTROY_UOM =
            "CREATE TABLE inventory_uom \n" +
                    "(\n" +
                    " uom  VARCHAR COLLATE NOCASE, \n" +
                    " baseqty   NUMERIC, \n" +
                    " product_id   VARCHAR COLLATE NOCASE, \n" +
                    " active    BOOL \n" +
                    ")";

    private static final String TABLE_INVENTROY_BIN= "inventory_bin";
    private static final String TABLE_CREATE_INVENTROY_BIN =
            "CREATE TABLE inventory_bin \n" +
                    "(\n" +
                    " id  VARCHAR COLLATE NOCASE, \n" +
                    " bin_name  VARCHAR COLLATE NOCASE, \n" +
                    " bin_description   VARCHAR COLLATE NOCASE, \n" +
                    " bin_type   VARCHAR COLLATE NOCASE, \n" +
                    " barcode   VARCHAR COLLATE NOCASE, \n" +
                    " tag   VARCHAR COLLATE NOCASE, \n" +
                    " location_id   VARCHAR COLLATE NOCASE, \n" +
                    " active    BOOL, \n" +
                    " ts DATETIME \n" +
                    ")";
    private static final String TABLE_INVENTROY_PALLET= "inventory_pallet";
    private static final String TABLE_CREATE_INVENTROY_PALLET =
            "CREATE TABLE inventory_pallet \n" +
                    "(\n" +
                    " id  VARCHAR COLLATE NOCASE, \n" +
                    " pallet_name  VARCHAR COLLATE NOCASE, \n" +
                    " pallet_description   VARCHAR COLLATE NOCASE, \n" +
                    " pallet_type   VARCHAR COLLATE NOCASE, \n" +
                    " barcode   VARCHAR COLLATE NOCASE, \n" +
                    " tag   VARCHAR COLLATE NOCASE, \n" +
                    " location_id   VARCHAR COLLATE NOCASE, \n" +
                    " weight   NUMERIC, \n" +
                    " ts   DATETIME, \n" +
                    " active    BOOL, \n" +
                    " is_used    BOOL \n" +
                    ")";

    private static final String TABLE_INVENTROY_PRODUCTS= "inventory_products";
    private static final String TABLE_CREATE_INVENTROY_PRODUCTS =
            "CREATE TABLE inventory_products \n" +
                    "(\n" +
                    " id  VARCHAR COLLATE NOCASE, \n" +
                    " name  VARCHAR COLLATE NOCASE, \n" +
                    " designname   VARCHAR COLLATE NOCASE, \n" +
                    " photo   VARCHAR COLLATE NOCASE, \n" +
                    " barcode   VARCHAR COLLATE NOCASE, \n" +
                    " tag   VARCHAR COLLATE NOCASE, \n" +
                    " baseuom   VARCHAR COLLATE NOCASE, \n" +
                    " minqty   INTEGER, \n" +
                    " maxqty   INTEGER, \n" +
                    " active    BOOL, \n" +
                    " is_delete    BOOL, \n" +
                    " pgroup_id     INTEGER, \n" +
                    " row_no      INTEGER, \n" +
                    " plength_id     INTEGER, \n" +
                    " preduce_id     INTEGER, \n" +
                    " psubgroup_id     INTEGER, \n" +
                    " ts DATETIME, \n" +

                    " diamond     VARCHAR COLLATE NOCASE, \n" +
                    " diamondcaratweight     NUMERIC, \n" +
                    " diamonclarity     VARCHAR COLLATE NOCASE, \n" +
                    " diamondmeasurement     VARCHAR COLLATE NOCASE, \n" +
                    " diamondprice     NUMERIC, \n" +
                    " jewelcost     NUMERIC, \n" +
                    " jewellength_id     INTEGER, \n" +
                    " jewelprice     NUMERIC, \n" +
                    " jewelshape_id     INTEGER, \n" +
                    " jeweltype_id     INTEGER, \n" +
                    " laboutcharge     NUMERIC, \n" +
                    " remarks     VARCHAR COLLATE NOCASE, \n" +
                    " sellinglabourcharge     NUMERIC, \n" +
                    " diamondcolor_id     INTEGER, \n" +
                    " diamondshape_id     INTEGER, \n" +
                    " diamondtype_id     INTEGER \n" +
                    ")";

    private static final String TABLE_INVENTORY_PRODUCTGROUPS= "inventory_productgroups";
    private static final String TABLE_CREATE_INVENTORY_PRODUCTGROUPS =
            "CREATE TABLE inventory_productgroups \n" +
                    "(\n" +
                    " id  VARCHAR COLLATE NOCASE, \n" +
                    " name  VARCHAR COLLATE NOCASE, \n" +
                    " active    BOOL \n" +
                    ")";

    private static final String TABLE_INVENTROY_GOLDUOM = "inventory_golduom";
    private static final String TABLE_CREATE_INVENTROY_GOLDUOM =
            "CREATE TABLE inventory_golduom \n" +
                    "(\n" +
                    " uom  VARCHAR COLLATE NOCASE, \n" +
                    " baseqty  NUMERIC, \n" +
                    " gold_id  INTEGER, \n" +
                    " active    BOOL \n" +
                    ")";

    private static final String TABLE_INVENTORY_PRODUCTSERIAL = "inventory_productserial";
    private static final String TABLE_CREATE_INVENTORY_PRODUCTSERIAL =
            "CREATE TABLE inventory_productserial \n" +
                    "(\n" +
                    "serial_no VARCHAR COLLATE NOCASE, \n" +
                    "name VARCHAR COLLATE NOCASE, \n" +
                    "serial_date DATETIME, \n" +
                    "plength VARCHAR COLLATE NOCASE, \n" +
                    "photo VARCHAR COLLATE NOCASE, \n" +
                    "barcode VARCHAR COLLATE NOCASE, \n" +
                    "tag VARCHAR COLLATE NOCASE, \n" +
                    "weight NUMERIC, \n" +
                    "k INTEGER, \n" +
                    "p INTEGER, \n" +
                    "y NUMERIC, \n" +
                    "reduce_weight NUMERIC, \n" +
                    "reduce_k INTEGER, \n" +
                    "reduce_p INTEGER, \n" +
                    "reduce_y NUMERIC, \n" +
                    "jewel_weight NUMERIC, \n" +
                    "jewel_k INTEGER, \n" +
                    "jewel_p INTEGER, \n" +
                    "jewel_y NUMERIC, \n" +
                    "jewel_fee NUMERIC, \n" +
                    "production_fee NUMERIC, \n" +
                    "jewel_name1 VARCHAR COLLATE NOCASE, \n" +
                    "jewel_name2 VARCHAR COLLATE NOCASE, \n" +
                    "jewel_name3 VARCHAR COLLATE NOCASE, \n" +
                    "jewel_name4 VARCHAR COLLATE NOCASE, \n" +
                    "jewel_name5 VARCHAR COLLATE NOCASE, \n" +
                    "jewel_weight1 NUMERIC, \n" +
                    "jewel_weight2 NUMERIC, \n" +
                    "jewel_weight3 NUMERIC, \n" +
                    "jewel_weight4 NUMERIC, \n" +
                    "jewel_weight5 NUMERIC, \n" +
                    "remarks VARCHAR COLLATE NOCASE, \n" +
                    "ts DATETIME, \n" +
                    "delivered BOOL, \n" +
                    "goodsid VARCHAR COLLATE NOCASE, \n" +
                    "is_delete BOOL, \n" +
                    "bin_id VARCHAR COLLATE NOCASE, \n" +
                    "gold_id INTEGER, \n" +
                    "location_id VARCHAR COLLATE NOCASE, \n" +
                    "pallet_id VARCHAR COLLATE NOCASE, \n" +
                    "product_id VARCHAR COLLATE NOCASE, \n" +
                    "staff_id VARCHAR COLLATE NOCASE, \n" +
                    "smith_id INTEGER, \n" +
                    "uom_id VARCHAR COLLATE NOCASE \n" +
                    ")";

    private static final String TABLE_INVENTORY_SERIALGOLDS = "inventory_serialgolds";
    private static final String TABLE_CREATE_INVENTORY_SERIALGOLDS =
            "CREATE TABLE inventory_serialgolds \n" +
                    "(\n" +
                    "serial_id VARCHAR COLLATE NOCASE, \n" +
                    "reduce_weight NUMERIC, \n" +
                    "reduce_k INTEGER, \n" +
                    "reduce_p INTEGER, \n" +
                    "reduce_y NUMERIC, \n" +
                    "production_fee NUMERIC, \n" +
                    "cost_reduce_weight NUMERIC, \n" +
                    "cost_reduce_k INTEGER, \n" +
                    "cost_reduce_p INTEGER, \n" +
                    "cost_reduce_y NUMERIC, \n" +
                    "cost_productionfee NUMERIC, \n" +
                    "is_delete BOOL, \n" +
                    "gold_id INTEGER, \n" +
                    "ts DATETIME \n" +
                    ")";

    private static final String TABLE_MANUFACTURING_SMITH = "manufacturing_smith";
    private static final String TABLE_CREATE_MANUFACTURING_SMITH =
            "CREATE TABLE manufacturing_smith \n" +
                    "(\n" +
                    "id INTEGER, \n" +
                    "name VARCHAR COLLATE NOCASE, \n" +
                    "nickname VARCHAR COLLATE NOCASE, \n" +
                    "nrc VARCHAR COLLATE NOCASE, \n" +
                    "address VARCHAR COLLATE NOCASE, \n" +
                    "phone VARCHAR COLLATE NOCASE, \n" +
                    "refname VARCHAR COLLATE NOCASE, \n" +
                    "mixjob VARCHAR COLLATE NOCASE, \n" +
                    "k INTEGER, \n" +
                    "p INTEGER, \n" +
                    "y NUMERIC, \n" +
                    "g NUMERIC, \n" +
                    "cost NUMERIC, \n" +
                    "date_joined DATETIME, \n" +
                    "date_end DATETIME, \n" +
                    "is_smith2 BOOL, \n" +
                    "active BOOL, \n" +
                    "goldsaving NUMERIC, \n" +
                    "inhandjob INTEGER, \n" +
                    "photo VARCHAR COLLATE NOCASE \n" +
                    ")";
    private static final String TABLE_MANUFACTURING_SMITH_JOBGOLD = "manufacturing_smith_jobgold";
    private static final String TABLE_CREATE_MANUFACTURING_SMITH_JOBGOLD =
            "CREATE TABLE manufacturing_smith_jobgold \n" +
                    "(\n" +
                    "smith_joborder_id VARCHAR COLLATE NOCASE, \n" +
                    "weight NUMERIC, \n" +
                    "qty NUMERIC, \n" +
                    "goldsaving NUMERIC, \n" +
                    "remarks VARCHAR COLLATE NOCASE, \n" +
                    "row_no VARCHAR COLLATE NOCASE, \n" +
                    "is_delete BOOL, \n" +
                    "mixjob VARCHAR COLLATE NOCASE, \n" +
                    "gold_id INTEGER, \n" +
                    "uom_id VARCHAR COLLATE NOCASE \n" +
                    ")";

    private static final String TABLE_MANUFACTURING_SMITH_JOBORDER = "manufacturing_smith_joborder";
    private static final String TABLE_CREATE_MANUFACTURING_SMITH_JOBORDER =
            "CREATE TABLE manufacturing_smith_joborder \n" +
                    "(\n" +
                    "id VARCHAR COLLATE NOCASE, \n" +
                    "joborder_no VARCHAR COLLATE NOCASE, \n" +
                    "joborder_date DATETIME, \n" +
                    "prejewelout BOOL, \n" +
                    "prejewelout_date DATETIME, \n" +
                    "date_target DATETIME, \n" +
                    "remarks VARCHAR COLLATE NOCASE, \n" +
                    "date_start DATETIME, \n" +
                    "date_end DATETIME, \n" +
                    "active BOOL, \n" +
                    "is_delete BOOL, \n" +
                    "ts DATETIME, \n" +
                    "joborder_type_id INTEGER, \n" +
                    "jobstatus_id INTEGER, \n" +
                    "smith_id INTEGER, \n" +
                    "density NUMERIC, \n" +
                    "diff_k INTEGER, \n" +
                    "diff_p INTEGER, \n" +
                    "diff_weight NUMERIC, \n" +
                    "diff_y VARCHAR COLLATE NOCASE, \n" +
                    "print_count INTEGER, \n" +
                    "product_weight VARCHAR COLLATE NOCASE, \n" +
                    "remain_gold NUMERIC, \n" +
                    "remain_jewel NUMERIC, \n" +
                    "save_count INTEGER \n" +
                    ")";

    private static final String TABLE_MANUFACTURING_SMITH_JOBPRODUCT = "manufacturing_smith_jobproduct";
    private static final String TABLE_CREATE_MANUFACTURING_SMITH_JOBPRODUCT =
            "CREATE TABLE manufacturing_smith_jobproduct \n" +
                    "(\n" +
                    "plength VARCHAR COLLATE NOCASE, \n" +
                    "weight NUMERIC, \n" +
                    "k VARCHAR COLLATE NOCASE, \n" +
                    "p INTEGER, \n" +
                    "y INTEGER, \n" +
                    "qty NUMERIC, \n" +
                    "reduce_weight NUMERIC, \n" +
                    "reduce_k INTEGER, \n" +
                    "reduce_p INTEGER, \n" +
                    "reduce_y NUMERIC, \n" +
                    "target_date DATETIME, \n" +
                    "start_date DATETIME, \n" +
                    "end_date DATETIME, \n" +
                    "wages NUMERIC, \n" +
                    "remarks VARCHAR COLLATE NOCASE, \n" +
                    "products_id VARCHAR COLLATE NOCASE, \n" +
                    "smith_joborder_id VARCHAR COLLATE NOCASE, \n" +
                    "is_delete BOOL, \n" +
                    "row_no INTEGER, \n" +
                    "to_location_id VARCHAR COLLATE NOCASE, \n" +
                    "uom_id VARCHAR COLLATE NOCASE \n" +
                    ")";

    private static final String TABLE_MANUFACTURING_SMITH_JOBTYPE = "manufacturing_smith_jobtype";
    private static final String TABLE_CREATE_MANUFACTURING_SMITH_JOBTYPE =
            "CREATE TABLE manufacturing_smith_jobtype \n" +
                    "(\n" +
                    "id INTEGER, \n" +
                    "name VARCHAR COLLATE NOCASE, \n" +
                    "description VARCHAR COLLATE NOCASE, \n" +
                    "active BOOL, \n" +
                    "jobtype_group VARCHAR COLLATE NOCASE \n" +
                    ")";

    private static final String TABLE_MANUFACTURING_SMITHMEMBERS = "manufacturing_smithmembers";
    private static final String TABLE_CREATE_MANUFACTURING_SMITHMEMBERS =
            "CREATE TABLE manufacturing_smithmembers \n" +
                    "(\n" +
                    "id INTEGER, \n" +
                    "name VARCHAR COLLATE NOCASE, \n" +
                    "nickname VARCHAR COLLATE NOCASE, \n" +
                    "nrc VARCHAR COLLATE NOCASE, \n" +
                    "address VARCHAR COLLATE NOCASE, \n" +
                    "phone VARCHAR COLLATE NOCASE, \n" +
                    "photo VARCHAR COLLATE NOCASE, \n" +
                    "active BOOL, \n" +
                    "smith_id INTEGER \n" +
                    ")";

    private static final String TABLE_MANUFACTURING_JOBSTATUS = "manufacturing_jobstatus";
    private static final String TABLE_CREATE_MANUFACTURING_JOBSTATUS =
            "CREATE TABLE manufacturing_jobstatus \n" +
                    "(\n" +
                    "id INTEGER, \n" +
                    "name VARCHAR COLLATE NOCASE, \n" +
                    "description VARCHAR COLLATE NOCASE, \n" +
                    "status_type VARCHAR COLLATE NOCASE, \n" +
                    "active BOOL \n" +
                    ")";

    private static final String TABLE_INVENTORY_GOODSINVENTORY = "inventory_goodsinventory";
    private static final String TABLE_CREATE_INVENTORY_GOODSINVENTORY =
            "CREATE TABLE inventory_goodsinventory \n" +
                    "(\n" +
                    " id  VARCHAR COLLATE NOCASE, \n" +
                    " qty  NUMERIC, \n" +
                    " weight   NUMERIC, \n" +
                    " k   INTEGER, \n" +
                    " p  INTEGER, \n" +
                    " y   INTEGER, \n" +
                    " ts   DATETIME, \n" +
                    " location_id   VARCHAR COLLATE NOCASE, \n" +
                    " product_id   VARCHAR COLLATE NOCASE, \n" +
                    " uom_id   VARCHAR COLLATE NOCASE, \n" +
                    " is_delete    BOOL \n" +
                    ")";

    private static final String TABLE_ADMINISTRATION_WSIMAGESTYPE = "administration_wsimagestype";
    private static final String TABLE_CREATE_ADMINISTRATION_WSIMAGESTYPE =
            "CREATE TABLE administration_wsimagestype \n" +
                    "(\n" +
                    "name VARCHAR COLLATE NOCASE \n" +
                    ")";

    private static final String TABLE_ADMINISTRATION_WSIMAGES = "administration_wsimages";
    private static final String TABLE_CREATE_ADMINISTRATION_WSIMAGES =
            "CREATE TABLE administration_wsimages \n" +
                    "(\n" +
                    "id INTEGER, \n" +
                    "name VARCHAR COLLATE NOCASE, \n" +
                    "path VARCHAR COLLATE NOCASE, \n" +
                    "timestamp DATETIME, \n" +
                    "is_delete BOOL, \n" +
                    "solution_id VARCHAR COLLATE NOCASE, \n" +
                    "type_id VARCHAR COLLATE NOCASE \n" +
                    ")";

    private static final String TABLE_INVENTORY_PRODUCTREDUCE= "inventory_productreduce";
    private static final String TABLE_CREATE_INVENTORY_PRODUCTREDUCE =
            "CREATE TABLE inventory_productreduce \n" +
                    "(\n" +
                    "id INTEGER, \n" +
                    "reduce_g NUMERIC, \n" +
                    "reduce_k INTEGER, \n" +
                    "reduce_p INTEGER, \n" +
                    "reduce_y INTEGER, \n" +
                    "production_fee NUMERIC, \n" +
                    "cost_reduce_k NUMERIC, \n" +
                    "cost_reduce_p NUMERIC, \n" +
                    "cost_reduce_y NUMERIC, \n" +
                    "cost_production_fee NUMERIC, \n" +
                    "remarks VARCHAR COLLATE NOCASE, \n" +
                    "active BOOL, \n" +
                    "is_delete BOOL, \n" +
                    "gold_id INTEGER, \n" +
                    "plength_id INTEGER \n" +
                    ")";

    private static final String TABLE_INVENTORY_PRODUCTSUBGROUPS = "inventory_productsubgroups";
    private static final String TABLE_CREATE_INVENTORY_PRODUCTSUBGROUPS =
            "CREATE TABLE inventory_productsubgroups \n" +
                    "(\n" +
                    "id INTEGER, \n" +
                    "name VARCHAR COLLATE NOCASE, \n" +
                    "active BOOL, \n" +
                    "is_delete BOOL, \n" +
                    "pgroup_id INTEGER \n" +

                    ")";

    private static final String TABLE_INVENTORY_PRODUCTLENGTH =  "inventory_productlength";
    private static final String TABLE_CREATE_INVENTORY_PRODUCTLENGTH =
            "CREATE TABLE inventory_productlength \n" +
                    "(\n" +
                    "id INTEGER, \n" +
                    "plength VARCHAR COLLATE NOCASE, \n" +
                    "active BOOL, \n" +
                    "is_delete BOOL, \n" +
                    "psgroup_id INTEGER \n" +
                    ")";

    private static final String TABLE_INVENTORY_JEWELLENGTH = "inventory_jewellength";
    private static final String TABLE_CREATE_INVENTORY_JEWELLENGTH =
            "CREATE TABLE inventory_jewellength \n" +
                    "(\n" +
                    "id INTEGER, \n" +
                    "name VARCHAR COLLATE NOCASE, \n" +
                    "active BOOL, \n" +
                    "jewelshape_id INTEGER \n" +
                    ")";

    private static final String TABLE_INVENTORY_JEWELINVENTORY = "inventory_jewelinventory";
    private static final String TABLE_CREATE_INVENTORY_JEWELINVENTORY =
            "CREATE TABLE inventory_jewelinventory \n" +
                    "(\n" +
                    "qty NUMERIC, \n" +
                    "ts DATETIME, \n" +
                    "jewellength_id INTEGER, \n" +
                    "jewelshape_id INTEGER, \n" +
                    "jeweltype_id INTEGER \n" +
                    ")";

    private static final String TABLE_INVENTORY_JEWELPURCHASE = "inventory_jewelpurchase";
    private static final String TABLE_CREATE_INVENTORY_JEWELPURCHASE =
            "CREATE TABLE inventory_jewelpurchase \n" +
                    "(\n" +
                    "purchase_no VARCHAR COLLATE NOCASE, \n" +
                    "purchase_date DATETIME, \n" +
                    "reference_no VARCHAR COLLATE NOCASE, \n" +
                    "amount NUMERIC, \n" +
                    "paid_amount NUMERIC, \n" +
                    "deduction NUMERIC, \n" +
                    "remarks VARCHAR COLLATE NOCASE, \n" +
                    "is_delete BOOL, \n" +
                    "ts DATETIME, \n" +
                    "supplier_id INTEGER \n" +
                    ")";

    private static final String TABLE_INVENTORY_JEWELPURCHASEITEMS = "inventory_jewelpurchaseitems";
    private static final String TABLE_CREATE_INVENTORY_JEWELPURCHASEITEMS =
            "CREATE TABLE inventory_jewelpurchaseitems \n" +
                    "(\n" +
                    "id INTEGER, \n" +
                    "qty NUMERIC, \n" +
                    "price NUMERIC, \n" +
                    "amount NUMERIC, \n" +
                    "remarks VARCHAR COLLATE NOCASE, \n" +
                    "row_no INTEGER, \n" +
                    "is_delete BOOL, \n" +
                    "ts DATETIME, \n" +
                    "jewel_purchase_id VARCHAR COLLATE NOCASE, \n" +
                    "jewellength_id INTEGER, \n" +
                    "jewelshape_id INTEGER, \n" +
                    "jeweltype_id INTEGER \n" +
                    ")";

    private static final String TABLE_INVENTORY_JEWELSHAPE = "inventory_jewelshape";
    private static final String TABLE_CREATE_INVENTORY_JEWELSHAPE =
            "CREATE TABLE inventory_jewelshape \n" +
                    "(\n" +
                    "id INTEGER, \n" +
                    "name VARCHAR COLLATE NOCASE, \n" +
                    "active BOOL, \n" +
                    "ts DATETIME, \n" +
                    "jeweltype_id INTEGER \n" +
                    ")";

    private static final String TABLE_INVENTORY_JEWELTYPE = "inventory_jeweltype";
    private static final String TABLE_CREATE_INVENTORY_JEWELTYPE =
            "CREATE TABLE inventory_jeweltype \n" +
                    "(\n" +
                    "id INTEGER, \n" +
                    "name VARCHAR COLLATE NOCASE, \n" +
                    "unittype VARCHAR COLLATE NOCASE, \n" +
                    "purchase_unittype VARCHAR COLLATE NOCASE, \n" +
                    "active BOOL \n" +
                    ")";

    private static final String TABLE_INVENTORY_SUPPLIER = "inventory_supplier";
    private static final String TABLE_CREATE_INVENTORY_SUPPLIER =
            "CREATE TABLE inventory_supplier \n" +
                    "(\n" +
                    "id INTEGER, \n" +
                    "name VARCHAR COLLATE NOCASE, \n" +
                    "address VARCHAR COLLATE NOCASE, \n" +
                    "phone VARCHAR COLLATE NOCASE, \n" +
                    "active BOOL, \n" +
                    "supplier_group_id INTEGER \n" +
                    ")";

    private static final String TABLE_INVENTORY_SUPPLIERGROUP = "inventory_suppliergroup";
    private static final String TABLE_CREATE_INVENTORY_SUPPLIERGROUP =
            "CREATE TABLE inventory_suppliergroup \n" +
                    "(\n" +
                    "id INTEGER, \n" +
                    "name VARCHAR COLLATE NOCASE, \n" +
                    "active BOOL \n" +
                    ")";

    private static final String TABLE_INVENTORY_RECEIVEDDETAIL = "Inventory_receiveddetail";
    private static final String TABLE_CREATE_INVENTORY_RECEIVEDDETAIL =
            "CREATE TABLE inventory_receiveddetail \n" +
                    "(\n" +
                    "lineno INTEGER, \n" +
                    "qty NUMERIC, \n" +
                    "weight NUMERIC, \n" +
                    "k INTEGER, \n" +
                    "p INTEGER, \n" +
                    "y NUMERIC, \n" +
                    "density NUMERIC, \n" +
                    "remarks VARCHAR COLLATE NOCASE, \n" +
                    "is_delete BOOL, \n" +
                    "ts DATETIME, \n" +
                    "bin_id VARCHAR COLLATE NOCASE, \n" +
                    "pallet_id VARCHAR COLLATE NOCASE, \n" +
                    "product_id VARCHAR COLLATE NOCASE, \n" +
                    "receivedhdr_id VARCHAR COLLATE NOCASE, \n" +
                    "tolocation_id VARCHAR COLLATE NOCASE, \n" +
                    "uom_id VARCHAR COLLATE NOCASE \n" +
                    ")";

    private static final String TABLE_INVENTORY_RECEIVESERIAL = "inventory_receiveserial";
    private static final String TABLE_CREATE_INVENTORY_RECEIVESERIAL =
            "CREATE TABLE inventory_receiveserial \n" +
                    "(\n" +
                    "productserial_id VARCHAR COLLATE NOCASE, \n" +
                    "lineno INTEGER, \n" +
                    "is_delete BOOL, \n" +
                    "ts DATETIME \n" +
                    ")";

    private static final String TABLE_INVENTORY_RECEIVEDHDR = "inventory_receivedhdr";
    private static final String TABLE_CREATE_INVENTORY_RECEIVEDHDR =
            "CREATE TABLE inventory_receivedhdr \n" +
                    "(\n" +
                    "receive_no VARCHAR COLLATE NOCASE, \n" +
                    "receive_date DATETIME, \n" +
                    "is_completed BOOL, \n" +
                    "is_approved BOOL, \n" +
                    "is_void BOOL, \n" +
                    "void_date DATETIME, \n" +
                    "is_delete BOOL, \n" +
                    "ts DATETIME, \n" +
                    "doctype_id INTEGER, \n" +
                    "location_id VARCHAR COLLATE NOCASE, \n" +
                    "smith_id INTEGER, \n" +
                    "staff_id VARCHAR COLLATE NOCASE \n" +
                    ")";

    private static final String TABLE_INVENTORY_PRODDETAI = "inventory_proddetail";
    private static final String TABLE_CREATE_INVENTORY_PRODDETAIL =
            "CREATE TABLE inventory_proddetail \n" +
                    "(\n" +
                    "id INTEGER , \n" +
                    "qty NUMERIC, \n" +
                    "received_qty NUMERIC, \n" +
                    "weight NUMERIC, \n" +
                    "k INTEGER, \n" +
                    "p INTEGER, \n" +
                    "y INTEGER, \n" +
                    "density NUMERIC, \n" +
                    "remarks VARCHAR COLLATE NOCASE, \n" +
                    "is_delete BOOL, \n" +
                    "prodhdr_id VARCHAR COLLATE NOCASE, \n" +
                    "product_id VARCHAR COLLATE NOCASE, \n" +
                    "tolocation_id VARCHAR COLLATE NOCASE, \n" +
                    "uom_id VARCHAR COLLATE NOCASE, \n" +
                    "ts DATETIME \n" +
                    ")";
    private static final String TABLE_INVENTORY_PRODHDR = "inventory_prodhdr";
    private static final String TABLE_CREATE_INVENTORY_PRODHDR =
            "CREATE TABLE inventory_prodhdr \n" +
                    "(\n" +
                    "prod_no VARCHAR COLLATE NOCASE, \n" +
                    "prod_date DATETIME, \n" +
                    "voucher_no VARCHAR COLLATE NOCASE, \n" +
                    "is_delivered BOOL, \n" +
                    "is_confirmed BOOL, \n" +
                    "save_count INTEGER, \n" +
                    "confirmed_by VARCHAR COLLATE NOCASE, \n" +
                    "is_void BOOL, \n" +
                    "void_date DATETIME, \n" +
                    "ts DATETIME, \n" +
                    "location_id VARCHAR COLLATE NOCASE, \n" +
                    "smit_id VARCHAR COLLATE NOCASE, \n" +
                    "staff_id VARCHAR COLLATE NOCASE \n" +
                    ")";



    private static final String TABLE_ADMINISTION_NO_SERIE = "administration_noseries";
    private static final String TABLE_CREATE_ADMINISTRATION_NO_SERIES=
            "CREATE TABLE administration_noseries \n" +
                    "(\n" +
                    "id VARCHAR COLLATE NOCASE, \n" +
                    "client_id DATETIME, \n" +
                    "module_name VARCHAR COLLATE NOCASE, \n" +
                    "prefix VARCHAR COLLATE NOCASE, \n" +
                    "diy BOOL, \n" +
                    "length INTEGER, \n" +
                    "last INTEGER, \n" +
                    "last_diy INTEGER, \n" +
                    "date_start INTEGER, \n" +
                    "date_end INTEGER, \n" +
                    "active BOOL, \n" +
                    "solution VARCHAR COLLATE NOCASE \n" +
                    ")";

    private static final String TABLE_ADMINISTRATION_STAFF_ROLE = "administration_staffrole";
    private static final String TABLE_CREATE_ADMINISTRATION_STAFFROLE=
            "CREATE TABLE administration_staffrole \n" +
                    "(\n" +
                    " id  INTEGER, \n" +
                    " active   BOOL,\n" +
                    " role   VARCHAR COLLATE NOCASE, \n" +
                    " staff   VARCHAR COLLATE NOCASE \n" +
                    ")";




    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_ITEMS);
        Log.i(TAG, "onCreate: Table " + TABLE_ITEMS + " has been created");
        db.execSQL(TABLE_CREATE_ADMINISTRATION_SETTINGS);
        Log.i(TAG, "onCreate: Table " + TABLE_ADMINISTRATION_SETTINGS + " has been created");
        db.execSQL(TABLE_CREATE_ADMINISTRATION_STAFF);
        Log.i(TAG, "onCreate: Table " + TABLE_ADMINISTRATION_STAFF + " has been created");
        db.execSQL(TABLE_CREATE_INVENTORY_GOLD);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTORY_GOLD + " has been created");
        db.execSQL(TABLE_CREATE_ADMINISTRATION_WSDASHBOARD);
        Log.i(TAG, "onCreate: Table " + TABLE_ADMINISTRATION_WSDASHBOARD + " has been created");
        db.execSQL(TABLE_CREATE_ADMINISTRATION_SOLUTIONS);
        Log.i(TAG, "onCreate: Table " + TABLE_ADMINISTRATION_SOLUTIONS + " has been created");
        db.execSQL(TABLE_CREATE_ADMINISTRATION_ROLE);
        Log.i(TAG, "onCreate: Table " + TABLE_ADMINISTRATION_ROLE + " has been created");
        db.execSQL(TABLE_CREATE_ADMINISTRATION_LOCATIONS);
        Log.i(TAG, "onCreate: Table " + TABLE_ADMINISTRATION_LOCATIONS + " has been created");
        db.execSQL(TABLE_CREATE_INVENTROY_UOM);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTROY_UOM + " has been created");
        db.execSQL(TABLE_CREATE_INVENTROY_BIN);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTROY_BIN + " has been created");
        db.execSQL(TABLE_CREATE_INVENTORY_GOODSINVENTORY);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTORY_GOODSINVENTORY + " has been created");
        db.execSQL(TABLE_CREATE_INVENTROY_PALLET);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTROY_PALLET+ " has been created");
        db.execSQL(TABLE_CREATE_INVENTROY_PRODUCTS);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTROY_PRODUCTS+ " has been created");
        db.execSQL(TABLE_CREATE_INVENTORY_PRODUCTGROUPS);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTORY_PRODUCTGROUPS+ " has been created");
        db.execSQL(TABLE_CREATE_INVENTROY_GOLDUOM);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTROY_GOLDUOM+ " has been created");
        db.execSQL(TABLE_CREATE_INVENTORY_PRODUCTSERIAL);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTORY_PRODUCTSERIAL+ " has been created");
        db.execSQL(TABLE_CREATE_INVENTORY_SERIALGOLDS);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTORY_SERIALGOLDS+ " has been created");
        db.execSQL(TABLE_CREATE_MANUFACTURING_SMITH);
        Log.i(TAG, "onCreate: Table " + TABLE_MANUFACTURING_SMITH+ " has been created");
        db.execSQL(TABLE_CREATE_MANUFACTURING_SMITH_JOBGOLD);
        Log.i(TAG, "onCreate: Table " + TABLE_MANUFACTURING_SMITH_JOBGOLD+ " has been created");
        db.execSQL(TABLE_CREATE_MANUFACTURING_SMITH_JOBORDER);
        Log.i(TAG, "onCreate: Table " + TABLE_MANUFACTURING_SMITH_JOBORDER+ " has been created");
        db.execSQL(TABLE_CREATE_MANUFACTURING_SMITH_JOBPRODUCT);
        Log.i(TAG, "onCreate: Table " + TABLE_MANUFACTURING_SMITH_JOBPRODUCT+ " has been created");
        db.execSQL(TABLE_CREATE_MANUFACTURING_SMITH_JOBTYPE);
        Log.i(TAG, "onCreate: Table " + TABLE_MANUFACTURING_SMITH_JOBTYPE+ " has been created");
        db.execSQL(TABLE_CREATE_MANUFACTURING_SMITHMEMBERS);
        Log.i(TAG, "onCreate: Table " + TABLE_MANUFACTURING_SMITHMEMBERS+ " has been created");
        db.execSQL(TABLE_CREATE_MANUFACTURING_JOBSTATUS);
        Log.i(TAG, "onCreate: Table " + TABLE_MANUFACTURING_JOBSTATUS+ " has been created");
        db.execSQL(TABLE_CREATE_ADMINISTRATION_WSIMAGES);
        Log.i(TAG, "onCreate: Table " + TABLE_ADMINISTRATION_WSIMAGES+ " has been created");
        db.execSQL(TABLE_CREATE_ADMINISTRATION_WSIMAGESTYPE);
        Log.i(TAG, "onCreate: Table " + TABLE_ADMINISTRATION_WSIMAGESTYPE+ " has been created");
        db.execSQL(TABLE_CREATE_INVENTORY_PRODUCTREDUCE);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTORY_PRODUCTREDUCE+ " has been created");
        db.execSQL(TABLE_CREATE_INVENTORY_PRODUCTSUBGROUPS);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTORY_PRODUCTSUBGROUPS+ " has been created");
        db.execSQL(TABLE_CREATE_INVENTORY_PRODUCTLENGTH);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTORY_PRODUCTLENGTH+ " has been created");
        db.execSQL(TABLE_CREATE_INVENTORY_JEWELINVENTORY);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTORY_JEWELINVENTORY+ " has been created");
        db.execSQL(TABLE_CREATE_INVENTORY_JEWELLENGTH);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTORY_JEWELLENGTH+ " has been created");
        db.execSQL(TABLE_CREATE_INVENTORY_JEWELPURCHASE);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTORY_JEWELPURCHASE+ " has been created");
        db.execSQL(TABLE_CREATE_INVENTORY_JEWELPURCHASEITEMS);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTORY_JEWELPURCHASEITEMS+ " has been created");
        db.execSQL(TABLE_CREATE_INVENTORY_JEWELSHAPE);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTORY_JEWELSHAPE+ " has been created");
        db.execSQL(TABLE_CREATE_INVENTORY_JEWELTYPE);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTORY_JEWELTYPE+ " has been created");
        db.execSQL(TABLE_CREATE_INVENTORY_SUPPLIER);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTORY_SUPPLIER+ " has been created");
        db.execSQL(TABLE_CREATE_INVENTORY_SUPPLIERGROUP);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTORY_SUPPLIERGROUP+ " has been created");
        db.execSQL(TABLE_CREATE_INVENTORY_RECEIVESERIAL);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTORY_RECEIVESERIAL+ " has been created");
        db.execSQL(TABLE_CREATE_INVENTORY_RECEIVEDDETAIL);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTORY_RECEIVEDDETAIL+ " has been created");
        db.execSQL(TABLE_CREATE_INVENTORY_RECEIVEDHDR);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTORY_RECEIVEDHDR+ " has been created");
        db.execSQL(TABLE_CREATE_INVENTORY_PRODDETAIL);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTORY_PRODDETAI+ " has been created");
        db.execSQL(TABLE_CREATE_INVENTORY_PRODHDR);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTORY_PRODHDR+ " has been created");
        db.execSQL(TABLE_CREATE_ADMINISTRATION_NO_SERIES);
        Log.i(TAG, "onCreate: Table " + TABLE_ADMINISTION_NO_SERIE+ " has been created");
        db.execSQL(TABLE_CREATE_ADMINISTRATION_STAFFROLE);
        Log.i(TAG, "onCreate: Table " + TABLE_ADMINISTRATION_STAFF_ROLE+ " has been created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINISTRATION_SETTINGS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINISTRATION_STAFF);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_GOLD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINISTRATION_WSDASHBOARD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINISTRATION_SOLUTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINISTRATION_ROLE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINISTRATION_LOCATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTROY_BIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTROY_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_PRODUCTGROUPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTROY_PALLET);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTROY_GOLDUOM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_PRODUCTSERIAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_SERIALGOLDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MANUFACTURING_SMITH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MANUFACTURING_SMITH_JOBGOLD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MANUFACTURING_SMITH_JOBORDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MANUFACTURING_SMITH_JOBPRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MANUFACTURING_SMITH_JOBTYPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MANUFACTURING_SMITHMEMBERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_GOODSINVENTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MANUFACTURING_JOBSTATUS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINISTRATION_WSIMAGES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINISTRATION_WSIMAGESTYPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_PRODUCTREDUCE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_PRODUCTSUBGROUPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_PRODUCTLENGTH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_JEWELINVENTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_JEWELLENGTH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_JEWELPURCHASE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_JEWELSHAPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_JEWELTYPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_SUPPLIER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_SUPPLIERGROUP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_RECEIVESERIAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_RECEIVEDDETAIL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_RECEIVEDHDR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_PRODDETAI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_PRODHDR);
        onCreate(db);
        Log.i(TAG, "Database has been upgraded from " +
                oldVersion + " to " + newVersion);

    }

}
