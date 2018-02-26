package com.ppt.wsinventory.wsdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.ppt.wsinventory.inventory.model.InventoryAllProducts;
import com.ppt.wsinventory.inventory.model.Inventory_SmithJob;
import com.ppt.wsinventory.inventory.model.Inventory_production_receiving;
import com.ppt.wsinventory.inventory.model.ProductReceiving;
import com.ppt.wsinventory.model.AdministrationLocations;
import com.ppt.wsinventory.model.AdministrationRole;
import com.ppt.wsinventory.model.AdministrationSettings;
import com.ppt.wsinventory.model.AdministrationSolutions;
import com.ppt.wsinventory.model.AdministrationStaff;
import com.ppt.wsinventory.model.AdministrationStaffRole;
import com.ppt.wsinventory.model.AdministrationWsdashboard;
import com.ppt.wsinventory.model.AdministratoryNoSerie;
import com.ppt.wsinventory.model.CodeValue;
import com.ppt.wsinventory.model.GoodsInventory;
import com.ppt.wsinventory.model.InventoryBIN;
import com.ppt.wsinventory.model.InventoryGold;
import com.ppt.wsinventory.model.InventoryGoldUOM;
import com.ppt.wsinventory.model.InventoryGoodInventory;
import com.ppt.wsinventory.model.InventoryPallet;
import com.ppt.wsinventory.model.InventoryProductGroup;
import com.ppt.wsinventory.model.InventoryProductSubgroups;
import com.ppt.wsinventory.model.InventoryProductlength;
import com.ppt.wsinventory.model.InventoryProductreduce;
import com.ppt.wsinventory.model.InventoryUOM;
import com.ppt.wsinventory.model.Inventory_proddetail;
import com.ppt.wsinventory.model.Inventory_prodhdr;
import com.ppt.wsinventory.model.Inventory_products;
import com.ppt.wsinventory.model.Inventory_productserial;
import com.ppt.wsinventory.model.Inventory_serialgolds;
import com.ppt.wsinventory.model.Item;
import com.ppt.wsinventory.model.ManufacturingSmith;
import com.ppt.wsinventory.model.Manufacturing_Smith_Jobgold;
import com.ppt.wsinventory.model.Manufacturing_jobstatus;
import com.ppt.wsinventory.model.Manufacturing_smith_joborder;
import com.ppt.wsinventory.model.Manufacturing_smith_jobproduct;
import com.ppt.wsinventory.model.Manufacturing_smith_jobtype;
import com.ppt.wsinventory.model.Manufacturing_smithmembers;
import com.ppt.wsinventory.model.NoSeries;
import com.ppt.wsinventory.model.WsDashboardModel;
import com.ppt.wsinventory.model.administration.design.AdministrationWsimages;
import com.ppt.wsinventory.model.administration.design.AdministrationWsimagestype;
import com.ppt.wsinventory.model.inventory_jewellery_model.Inventory_jewelinventory;
import com.ppt.wsinventory.model.inventory_jewellery_model.Inventory_jewellength;
import com.ppt.wsinventory.model.inventory_jewellery_model.Inventory_jewelpurchase;
import com.ppt.wsinventory.model.inventory_jewellery_model.Inventory_jewelpurchaseitems;
import com.ppt.wsinventory.model.inventory_jewellery_model.Inventory_jewelshape;
import com.ppt.wsinventory.model.inventory_jewellery_model.Inventory_jeweltype;
import com.ppt.wsinventory.model.inventory_jewellery_model.Inventory_supplier;
import com.ppt.wsinventory.model.inventory_jewellery_model.Inventory_suppliergroup;
import com.ppt.wsinventory.model.inventory_receive_model.Inventory_receiveddetail;
import com.ppt.wsinventory.model.inventory_receive_model.Inventory_receivedhdr;
import com.ppt.wsinventory.model.inventory_receive_model.Inventory_receiveserial;
import com.ppt.wsinventory.util.Utility;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 28/12/2017.
 */

public class DbAccess {

    SQLiteOpenHelper dbhelper;
    SQLiteDatabase database;
    private File dbpath;
    String dashboard;

    public File getDbFile() {
        return dbpath;
    }

    public File getDbbackupDirectory() {
        return new File(Environment.getExternalStorageDirectory(), "/database/");
//        return new File("/storage/sdcard1/","/database/" );
    }

    public String getDbName() {
        return dbhelper.getDatabaseName();
    }

    private static final String TAG = "Truckkit-DbAccess";
    private static DbAccess m_dbaccess;

    public DbAccess(Context context) {
        dbhelper = new DbHelper(context);
        dbpath = context.getDatabasePath(dbhelper.getDatabaseName());
        Log.i(TAG, "DbAccess: ");
        m_dbaccess = this;
    }

    public static DbAccess getInstance() {

        return m_dbaccess;
    }

    public boolean isOpen() {
        return database.isOpen();
    }

    public void open() {
        Log.i(TAG, "open: ");
        database = dbhelper.getWritableDatabase();
    }

    public void execSQL(String sql) {
        database.execSQL(sql);
    }

    public void execSQL(String sql, Object[] bindArgs) {
        database.execSQL(sql, bindArgs);
    }

    public boolean updateData(String table, ContentValues values, String whereClause, String[] whereArgs) {
        int result = database.update(table, values, whereClause, whereArgs);
        return (result >= 1);
    }

//    public boolean updateLatLng(Double lat ,Double lng,String CName){
//        SQLiteDatabase db = dbhelper.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("Latitude", lat);
//        contentValues.put("Longitude", lng);
//        db.update("Customers", contentValues, "CustName = ?",new String[] { CName });
//        return true;
//    }

    public boolean deleteData(String table) {
        Log.i(TAG, "deleteData: " + table);
        return deleteData(table, null, null);
    }

    public boolean deleteData(String table, String whereClause) {
        return deleteData(table, whereClause, null);
    }


    public boolean deleteData(String table, String whereClause, String[] whereArgs) {
        int result = database.delete(table, whereClause, whereArgs);
        return (result == 1);
    }

    public boolean checkData(String sql, String[] selectionArgs) {
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public Cursor readData(String tableName, String[] columnAll, String[] strings, Object o1, Object o, String sql) {
        return readData(sql, null);
    }

    public Cursor readData(String sql, String[] selectionArgs) {
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        return cursor;
    }

    public Cursor readData(String table, String[] columns
            , String selection
            , String[] selectionArgs
            , String groupBy
            , String having
            , String orderBy) {
        Cursor cursor = database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
        return cursor;
    }

    public Cursor readData(String table
            , String[] columns
            , String orderBy) {
        Cursor cursor = database.query(table, columns, null, null, null, null, orderBy);
        return cursor;
    }

    public void close() {
        Log.i(TAG, "close: ");
        if (database != null && database.isOpen()) {
            database.close();
        }
//        dbhelper.close();
    }

//    public List<Item> getAllConfirmedQueue() {
//        List<Item> queues = new ArrayList<>();
//        Cursor cursor = readData(QueueTable.TABLE_NAME
//                , QueueTable.COLUMN_ALL
//                , QueueTable.COLUMN_IS_SUBMIT + " = ?"
//                , new String[]{"1"}, null, null, null
//        );


    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        Cursor cursor = readData(Item.TABLE_NAME
                , Item.COLUMN_ALL
                , null, null, null, null, null
//                , new String[]{"1"}, null, null, null
        );

        while (cursor.moveToNext()) {
            Item item = new Item();
            item.setItemName(cursor.getString(cursor.getColumnIndex(item.COLUMN_ITEMNAME)));
            item.setItemType(cursor.getString(cursor.getColumnIndex(item.COLUMN_ITEMTYPE)));

            items.add(item);
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return items;


    }

    public List<WsDashboardModel> getAllDashboardItems() {
        List<WsDashboardModel> dashboarditems = new ArrayList<>();
        String sql = "select id, title, image, is_folder, actionname from administration_wsdashboard where parent_id = 0 order by displayno";
        Cursor cursor = readData(sql,null);

        while (cursor.moveToNext()) {
            WsDashboardModel item = new WsDashboardModel();
            item.setTitle(cursor.getString(cursor.getColumnIndex(item.COLUMN_TITLE)));
            item.setImage(cursor.getString(cursor.getColumnIndex(item.COLUMN_IMAGE)));
            item.setActionname(cursor.getString(cursor.getColumnIndex(item.COLUMN_ACTION_NAME)));
            item.setFolder(cursor.getInt(cursor.getColumnIndex(item.COLUMN_IS_FOLDRE)) > 0);
            item.setId(cursor.getInt(cursor.getColumnIndex(item.COLUMN_ID)));
            dashboarditems.add(item);
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return dashboarditems;


    }

    public List<Manufacturing_smith_joborder> getAllSmithJoborder() {
        List<Manufacturing_smith_joborder> smithorder = new ArrayList<>();
        Cursor cursor = readData(Manufacturing_smith_joborder.TABLE_MANUFACTURING_SMITH_JOBORDER
                , Manufacturing_smith_joborder.COLUMN_ALL
                , null, null, null, null, null
//                , new String[]{"1"}, null, null, null
        );

        while (cursor.moveToNext()) {
            Manufacturing_smith_joborder item = new Manufacturing_smith_joborder();
            item.setJoborder_no(cursor.getString(cursor.getColumnIndex(item.COLUMN_JOBORDER_NO)));
            try {
                item.setJoborder_date(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(item.COLUMN_JOBORDER_DATE))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            item.setPrejewelout(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(item.COLUMN_PREJEWELOUT))));
            try {
                item.setPrejewelout_date(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(item.COLUMN_PREJEWELOUT_DATE))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                item.setDate_target(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(item.COLUMN_DATE_TARGET))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                item.setDate_start(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(item.COLUMN_DATE_START))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                item.setDate_end(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(item.COLUMN_DATE_END))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            item.setRemarks(cursor.getString(cursor.getColumnIndex(item.COLUMN_REMARKS)));
            item.setActive(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(item.COLUMN_ACTIVE))));
            item.setIs_delete(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(item.COLUMN_IS_DELETE))));
            try {
                item.setTs(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(item.COLUMN_TS))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            item.setJoborder_type_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(item.COLUMN_JOBORDER_TYPE_ID))));
            item.setJobstatus_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(item.COLUMN_JOBSTATUS_ID))));
            item.setSmith_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(item.COLUMN_SMITH_ID))));
            item.setDensity(Double.parseDouble(cursor.getString(cursor.getColumnIndex(item.COLUMN_DENSITY))));
            item.setDiff_k(Integer.parseInt(cursor.getString(cursor.getColumnIndex(item.COLUMN_DIFF_K))));
            item.setDiff_p(Integer.parseInt(cursor.getString(cursor.getColumnIndex(item.COLUMN_DIFF_P))));
            item.setDiff_weight(Double.parseDouble(String.valueOf(cursor.getColumnIndex(item.COLUMN_DIFF_WEIGHT))));
            item.setDiff_y(Double.parseDouble(cursor.getString(cursor.getColumnIndex(item.COLUMN_DIFF_Y))));
            item.setPrint_count(Integer.parseInt(cursor.getString(cursor.getColumnIndex(item.COLUMN_PRINT_COUNT))));
            item.setProduct_weight(Double.parseDouble(cursor.getString(cursor.getColumnIndex(item.COLUMN_PRODUCT_WEIGHT))));
            item.setRemain_gold(Double.parseDouble(cursor.getString(cursor.getColumnIndex(item.COLUMN_REMAIN_GOLD))));
            item.setRemain_jewel(Double.parseDouble(cursor.getString(cursor.getColumnIndex(item.COLUMN_REMAIN_JEWEL))));
            item.setSave_count(Integer.parseInt(cursor.getString(cursor.getColumnIndex(item.COLUMN_SAVE_COUNT))));

            smithorder.add(item);
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return smithorder;


    }

    public List<Inventory_SmithJob> getAllInventorySmithJob() {
        List<Inventory_SmithJob> inventory_smithJobs = new ArrayList<>();
        String sql = "select s.name as smith_name, s.nickname as smith_nickname, sjob.joborder_no, sjob.smith_id, sjob.remarks , sjob.date_start , sjob.date_end , jt.description , jt.name as Jobtype_name\n" +
                "from manufacturing_smith_joborder sjob \n" +
                "inner join manufacturing_smith s on sjob.smith_id = s.id\n" +
                "inner join manufacturing_smith_jobtype jt on sjob.joborder_type_id = jt.id";
        Cursor cursor = readData(sql, null);

        while (cursor.moveToNext()) {
            Inventory_SmithJob inventory_smithJob = new Inventory_SmithJob();
            inventory_smithJob.setName(cursor.getString(cursor.getColumnIndex(inventory_smithJob.COLUMN_SMITH_NAME)));
            inventory_smithJob.setNickname(cursor.getString(cursor.getColumnIndex(inventory_smithJob.COLUMN_SMITH_NICKNAME)));
            inventory_smithJob.setSmith_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(inventory_smithJob.COLUMN_SMITH_ID))));
            inventory_smithJob.setJoborder_no(cursor.getString(cursor.getColumnIndex(inventory_smithJob.COLUMN_JOBORDER_NO)));
            try {
                inventory_smithJob.setDate_start(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(inventory_smithJob.COLUMN_DATE_START))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            inventory_smithJob.setDescription(cursor.getString(cursor.getColumnIndex(inventory_smithJob.COLUMN_DESCRIPTION)));
            inventory_smithJob.setJobtype_name(cursor.getString(cursor.getColumnIndex(inventory_smithJob.COLUMN_JOBTYPE_NAME)));
            try {
                inventory_smithJob.setDate_end(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(inventory_smithJob.COLUMN_DATE_END))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            inventory_smithJobs.add(inventory_smithJob);
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return inventory_smithJobs;
    }


    public List<Inventory_production_receiving> getAllInventoryProdhdr()
    {
        List<Inventory_production_receiving> inventory_prodhdr = new ArrayList<>();
        String sql = "select invP.* , manS.name as smith_name\n" +
                "from  inventory_prodhdr as invP\n" +
                "inner join manufacturing_smith as manS on invP.smit_id = manS.id\n";
        Cursor cursor = readData(sql, null);

        while (cursor.moveToNext()) {
            Inventory_production_receiving inventory_production_receiving = new Inventory_production_receiving();
            inventory_production_receiving.setProd_no(cursor.getString(cursor.getColumnIndex(inventory_production_receiving.COLUMN_PROD_NO)));
            try {
                inventory_production_receiving.setProd_date(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(inventory_production_receiving.COLUMN_PROD_DATE))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            inventory_production_receiving.setVoucher_no(cursor.getString(cursor.getColumnIndex(inventory_production_receiving.COLUMN_VOUCHER_NO)));
            inventory_production_receiving.setIs_delivered(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(inventory_production_receiving.COLUMN_IS_DELIVERED))));
            inventory_production_receiving.setIs_confirmed(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(inventory_production_receiving.COLUMN_IS_CONFIRMED))));
            inventory_production_receiving.setConfirmedby(cursor.getString(cursor.getColumnIndex(inventory_production_receiving.COLUMN_CONFIRMED_BY)));
            inventory_production_receiving.setIs_void(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(inventory_production_receiving.COLUMN_IS_VOID))));
            try {
                inventory_production_receiving.setVoid_date(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(inventory_production_receiving.COLUMN_VOID_DATE))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                inventory_production_receiving.setTs(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(inventory_production_receiving.COLUMN_TS))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            inventory_production_receiving.setLocation_id(cursor.getString(cursor.getColumnIndex(inventory_production_receiving.COLUMN_LOCATION_ID)));
            inventory_production_receiving.setSmit_id(cursor.getString(cursor.getColumnIndex(inventory_production_receiving.COLUMN_SMIT_ID)));
            inventory_production_receiving.setStaff_id(cursor.getString(cursor.getColumnIndex(inventory_production_receiving.COLUMN_STAFF_ID)));
            inventory_production_receiving.setSave_count(Integer.parseInt(cursor.getString(cursor.getColumnIndex(inventory_production_receiving.COLUMN_SAVE_COUNT))));
            inventory_production_receiving.setSmith_name(cursor.getString(cursor.getColumnIndex(inventory_production_receiving.COLUMN_SMITH_NAME)));
            inventory_prodhdr.add(inventory_production_receiving);

        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return inventory_prodhdr;
    }
    public List<ProductReceiving> getAllProductReceiving(String prod_no)
    {
        List<ProductReceiving> productpeceivings = new ArrayList<>();
        String sql = "select *, invD.designname  \n" +
                "from inventory_prodhdr as invP\n" +
                "inner join inventory_proddetail as invPD on invP.prod_no = invPD.prodhdr_id\n" +
                "inner join inventory_products as invD on invPD.product_id = invD.id \n" +
                "where invP.prod_no = ?";

        Cursor cursor = readData(sql, new String[]{prod_no});

        while (cursor.moveToNext()) {
            ProductReceiving productpeceiving = new ProductReceiving();
            productpeceiving.setDesignname(cursor.getString(cursor.getColumnIndex(productpeceiving.COLUMN_DESIGN_NAME)));
            productpeceiving.setQty(Double.parseDouble(cursor.getString(cursor.getColumnIndex(ProductReceiving.COLUMN_QTY))));
            productpeceiving.setWeight(Double.parseDouble(cursor.getString(cursor.getColumnIndex(ProductReceiving.COLUMN_WEIGHT))));

            productpeceivings.add(productpeceiving);
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return productpeceivings;
    }

    public List<InventoryAllProducts> getInventoryAllProducts(String productname,String groupname,String subgroupname){
        List<InventoryAllProducts> inventoryAllProducts = new ArrayList<>();
        String sql ="select invP.id as product_id , invP.name as product_name , invP.designname as design_name,invP.minqty as minqty,invP.maxqty as maxqty , invP.photo ,\n" +
                " invPG.id as productgroups_id,invPG.name as group_name ,invPR.id as productreduce_id,invPR.reduce_g ,invPR.reduce_k,invPR.reduce_p,invPR.reduce_y ,\n" +
                " invPR.production_fee ,invPR.cost_reduce_k,invPR.cost_reduce_p,invPR.cost_reduce_y,invPR.cost_production_fee,invPR.remarks,\n" +
                " invPL.id as productlength_id , invPL.plength ,invPSG.id as productsubgroups_id , invPSG.name as subgroup_name,aws.id as photo_id ," +
                " aws.type_id as photo_type,aws.name as photo_name\n" +
                "from inventory_products invP\n" +
                "inner join inventory_productgroups invPG on invP.pgroup_id = invPG.id\n" +
                "inner join inventory_productreduce invPR on invP.preduce_id = invPR.id\n" +
                "inner join inventory_productlength invPL on invP.plength_id = invPL.id\n" +
                "inner join administration_wsimages aws on invP.photo = aws.id\n" +
                "inner join inventory_productsubgroups invPSG on invP.psubgroup_id = invPSG.id\n";



        if(!productname.isEmpty() && productname != null){

            sql = sql + "where invP.name = '" + productname + "'\n";

            if(!groupname.isEmpty() && !groupname.equals("All Group")){

                sql = sql + "and invPG.name = '" + groupname + "'\n";
            }

            if(!subgroupname.isEmpty() && !subgroupname.equals("All SubGroup")){

                    sql = sql + "and invPSG.name = '" + subgroupname + "'\n";
            }


        }else {

            if (!groupname.isEmpty() &&  !groupname.equals("All Group")) {

                sql = sql + "where invPG.name = '" + groupname + "'\n";

                if (!subgroupname.isEmpty() && !subgroupname.equals("All SubGroup")) {

                    sql = sql + "and invPSG.name = '" + subgroupname + "'\n";
                }
            } else {

                if (!subgroupname.isEmpty() && !subgroupname.equals("All SubGroup")) {

                    sql = sql + "where invPSG.name = '" + subgroupname + "'\n";
                }

            }

        }


        Cursor cursor = readData(sql,null);

        while (cursor.moveToNext()) {
            InventoryAllProducts inventoryAllProduct = new InventoryAllProducts();
            inventoryAllProduct.setProduct_id(cursor.getString(cursor.getColumnIndex(inventoryAllProduct.COLUMN_PRODUCT_ID)));
            inventoryAllProduct.setProductlength_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(inventoryAllProduct.COLUMN_PRODUCTLENGTH_ID))));
            inventoryAllProduct.setProductsubgroups_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(inventoryAllProduct.COLUMN_PRODUCTSUBGROUPS_ID))));
            inventoryAllProduct.setProductgroups_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(inventoryAllProduct.COLUMN_PRODUCTGROUPS_ID))));
            inventoryAllProduct.setProduct_name(cursor.getString(cursor.getColumnIndex(inventoryAllProduct.COLUMN_PRODUCT_NAME)));
            inventoryAllProduct.setDesign_name(cursor.getString(cursor.getColumnIndex(inventoryAllProduct.COLUMN_DESIGNNAME)));
            inventoryAllProduct.setGroup_name(cursor.getString(cursor.getColumnIndex(inventoryAllProduct.COLUMN_GNAME)));
            inventoryAllProduct.setSubgroup_name(cursor.getString(cursor.getColumnIndex(inventoryAllProduct.COLUMN_SUBGNAME)));
            inventoryAllProduct.setPlength(cursor.getString(cursor.getColumnIndex(inventoryAllProduct.COLUMN_PLENGTH)));
            inventoryAllProduct.setReduce_g(Integer.parseInt(cursor.getString(cursor.getColumnIndex(inventoryAllProduct.COLUMN_REDUCE_G))));
            inventoryAllProduct.setReduce_k(Integer.parseInt(cursor.getString(cursor.getColumnIndex(inventoryAllProduct.COLUMN_REDUCE_K))));
            inventoryAllProduct.setReduce_p(Integer.parseInt(cursor.getString(cursor.getColumnIndex(inventoryAllProduct.COLUMN_REDUCE_P))));
            inventoryAllProduct.setReduce_y(Integer.parseInt(cursor.getString(cursor.getColumnIndex(inventoryAllProduct.COLUMN_REDUCE_Y))));
            inventoryAllProduct.setCost_reduce_k(Integer.parseInt(cursor.getString(cursor.getColumnIndex(inventoryAllProduct.COLUMN_COST_REDUCE_K))));
            inventoryAllProduct.setCost_reduce_p(Integer.parseInt(cursor.getString(cursor.getColumnIndex(inventoryAllProduct.COLUMN_COST_REDUCE_P))));
            inventoryAllProduct.setCost_reduce_y(Integer.parseInt(cursor.getString(cursor.getColumnIndex(inventoryAllProduct.COLUMN_COST_REDUCE_Y))));
            inventoryAllProduct.setMinqty(Integer.parseInt(cursor.getString(cursor.getColumnIndex(inventoryAllProduct.COLUMN_MINQTY))));
            inventoryAllProduct.setMaxqty(Integer.parseInt(cursor.getString(cursor.getColumnIndex(inventoryAllProduct.COLUMN_MAXQTY))));
            inventoryAllProduct.setPhoto(cursor.getString(cursor.getColumnIndex(inventoryAllProduct.COLUMN_PHOTO_ID)));
            inventoryAllProduct.setPhoto_name(cursor.getString(cursor.getColumnIndex(inventoryAllProduct.COLUMN_PHOTO_NAME)));
            inventoryAllProduct.setPhoto_type(cursor.getString(cursor.getColumnIndex(inventoryAllProduct.COLUMN_PHOTO_TYPE)));

            inventoryAllProducts.add(inventoryAllProduct);

        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return inventoryAllProducts;
    }

    public AdministrationSettings getAdministrationSettings() {
//        AdministrationSettings administrationSettings = new AdministrationSettings();
        AdministrationSettings administrationSetting = new AdministrationSettings();
        Cursor cursor = readData(AdministrationSettings.TABLE_ADMINISTRATION_SETTINGS
                , AdministrationSettings.COLUMN_ALL
                , null, null, null, null, null
//                , new String[]{"1"}, null, null, null
        );

        while (cursor.moveToNext()) {
            administrationSetting = new AdministrationSettings();
            administrationSetting.setId(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_ID)));
            administrationSetting.setH1(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_H1)));
            administrationSetting.setH2(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_H2)));
            administrationSetting.setH3(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_H3)));
            administrationSetting.setH4(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_H4)));
            administrationSetting.setT1(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_T1)));
            administrationSetting.setT2(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_T2)));
            administrationSetting.setT3(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_T3)));
            administrationSetting.setT4(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_T4)));
            administrationSetting.setDateformat(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_DATEFORMAT)));
            administrationSetting.setTimeformat(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_TIMEFORMAT)));
            administrationSetting.setDatetimeformat(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_DATETIMEFORMAT)));
            try {
                administrationSetting.setSystem_date(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_SYSTTEM_DATE))));
            } catch (ParseException e) {
                administrationSetting.setSystem_date(new Date(System.currentTimeMillis()));
            }
            administrationSetting.setActive(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_ACTIVE))));
            administrationSetting.setLocation_id(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_LOCATION_ID)));
            administrationSetting.setSolution_id(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_SOLUTION_ID)));
            administrationSetting.setStaff_id(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_STAFF_ID)));
            administrationSetting.setWeighscales_id(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_WEIGHSCALES_ID)));
            administrationSetting.setDashboarditemlwith(Integer.parseInt(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_DASHBOARDITEMLWITH))));
            administrationSetting.setDashboarditempwith(Integer.parseInt(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_DASHBOARDITEMPWITH))));
            administrationSetting.setDashboardicon(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_DASHBOARDICON)));
            administrationSetting.setDevicetype_id(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_DEVICETYPE_ID)));
            administrationSetting.setDevice_id(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_DEVICE_ID)));

//            administrationSettings.add(administrationSetting);
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return administrationSetting;
    }

    public List<CodeValue> getProductGroupList (){

        List<CodeValue>codeValues =new ArrayList<>();
        Cursor cursor = readData(InventoryProductGroup.TABLE_PRODUCTGROUP
                                ,new String[]{InventoryProductGroup.COLUMN_ID,
                                                InventoryProductGroup.COLUMN_NAME},
                            null);
        while (cursor.moveToNext()){
            CodeValue codeValue = new CodeValue();
            codeValue.setCode(cursor.getString(cursor.getColumnIndex(InventoryProductGroup.COLUMN_ID)));
            codeValue.setValue(cursor.getString(cursor.getColumnIndex(InventoryProductGroup.COLUMN_NAME)));
            codeValues.add(codeValue);
        }

        if(cursor !=null &&  !cursor.isClosed()){
            cursor.close();
        }
        return codeValues;
    }

    public List<CodeValue> getProductSubGroupList (String groupname){

        List<CodeValue>codeValues =new ArrayList<>();
        String sql = "select psg.id ,psg.name\n" +
                "from inventory_productsubgroups as psg\n" +
                "inner join inventory_productgroups as pg on psg.pgroup_id = pg.id\n";

        if(!groupname.isEmpty() && !groupname.equals("All Group") && groupname != null){

            sql = sql + "where pg.name = '" + groupname + "'";
        }
//        Cursor cursor = readData(InventoryProductSubgroups.TABLE_INVENTORY_PRODUCTSUBGROUPS
//                ,new String[]{InventoryProductSubgroups.COLUMN_ID,
//                        InventoryProductSubgroups.COLUMN_NAME},
//                null);

        Cursor cursor = readData(sql,null);
        while (cursor.moveToNext()){
            CodeValue codeValue = new CodeValue();
            codeValue.setCode(cursor.getString(cursor.getColumnIndex(InventoryProductSubgroups.COLUMN_ID)));
            codeValue.setValue(cursor.getString(cursor.getColumnIndex(InventoryProductSubgroups.COLUMN_NAME)));
            codeValues.add(codeValue);
        }

        if(cursor !=null &&  !cursor.isClosed()){
            cursor.close();
        }
        return codeValues;
    }

    public Item insertItems(Item item) {

        ContentValues values = new ContentValues();
        values.put(Item.COLUMN_ITEMNAME, item.getItemName());
        values.put(Item.COLUMN_ITEMTYPE, item.getItemType());

        long resultid = database.insert(Item.TABLE_NAME, null, values);
        item.setId(resultid);

        return item;
    }


    public AdministrationSettings insertSettings(AdministrationSettings administrationSettings) {
        ContentValues values = new ContentValues();
        values.put(AdministrationSettings.COLUMN_ID, administrationSettings.getId());
        values.put(AdministrationSettings.COLUMN_H1, administrationSettings.getH1());
        values.put(AdministrationSettings.COLUMN_H2, administrationSettings.getH2());
        values.put(AdministrationSettings.COLUMN_H3, administrationSettings.getH3());
        values.put(AdministrationSettings.COLUMN_H4, administrationSettings.getH4());
        values.put(AdministrationSettings.COLUMN_T1, administrationSettings.getT1());
        values.put(AdministrationSettings.COLUMN_T2, administrationSettings.getT2());
        values.put(AdministrationSettings.COLUMN_T3, administrationSettings.getT3());
        values.put(AdministrationSettings.COLUMN_T4, administrationSettings.getT4());
        values.put(AdministrationSettings.COLUMN_DATEFORMAT, administrationSettings.getDateformat());
        values.put(AdministrationSettings.COLUMN_TIMEFORMAT, administrationSettings.getTimeformat());
        values.put(AdministrationSettings.COLUMN_DATETIMEFORMAT, administrationSettings.getDatetimeformat());
        values.put(AdministrationSettings.COLUMN_SYSTTEM_DATE, Utility.dateFormat.format(administrationSettings.getSystem_date()));
        values.put(AdministrationSettings.COLUMN_ACTIVE, administrationSettings.getActive());
        values.put(AdministrationSettings.COLUMN_LOCATION_ID, administrationSettings.getLocation_id());
        values.put(AdministrationSettings.COLUMN_SOLUTION_ID, administrationSettings.getSolution_id());
        values.put(AdministrationSettings.COLUMN_STAFF_ID, administrationSettings.getStaff_id());
        values.put(AdministrationSettings.COLUMN_WEIGHSCALES_ID, administrationSettings.getWeighscales_id());
        values.put(AdministrationSettings.COLUMN_DASHBOARDITEMLWITH, administrationSettings.getDashboarditemlwith());
        values.put(AdministrationSettings.COLUMN_DASHBOARDITEMPWITH, administrationSettings.getDashboarditempwith());
        values.put(AdministrationSettings.COLUMN_DASHBOARDICON, administrationSettings.getDashboardicon());
        values.put(AdministrationSettings.COLUMN_DEVICE_ID, administrationSettings.getDevice_id());
        values.put(AdministrationSettings.COLUMN_DEVICETYPE_ID, administrationSettings.getDevicetype_id());
        long resultid = database.insert(administrationSettings.TABLE_ADMINISTRATION_SETTINGS, null, values);
//        administrationSettings.setId(resultid);
        return administrationSettings;
    }

    public AdministrationStaff insertAdministrationStaff(AdministrationStaff administrationStaff) {
        ContentValues values = new ContentValues();
        values.put(AdministrationStaff.COLUMN_ID, administrationStaff.getId());
        values.put(AdministrationStaff.COLUMN_STAFF_ID, administrationStaff.getStaff_id());
        values.put(AdministrationStaff.COLUMN_STAFF_NAME, administrationStaff.getStaff_name());
        values.put(AdministrationStaff.COLUMN_FATHER_NAME, administrationStaff.getFather_name());
        values.put(AdministrationStaff.COLUMN_NICK_NAME, administrationStaff.getNick_name());
        values.put(AdministrationStaff.COLUMN_NRC_NO, administrationStaff.getNrc_no());
        values.put(AdministrationStaff.COLUMN_ADDRESS, administrationStaff.getAddress());
        values.put(AdministrationStaff.COLUMN_PHONE_NO, administrationStaff.getPhone_no());
        values.put(AdministrationStaff.COLUMN_HOME_PHONE_NO, administrationStaff.getHome_phone_no());
        values.put(AdministrationStaff.COLUMN_STAFF_PHOTO, administrationStaff.getStaff_photo());
        values.put(AdministrationStaff.COLUMN_DATE_JOINED, Utility.dateFormat.format(administrationStaff.getDate_joined()));
        values.put(AdministrationStaff.COLUMN_DATE_LEFT, Utility.dateFormat.format(administrationStaff.getDate_left()));
        values.put(AdministrationStaff.COLUMN_USER_ID, administrationStaff.getUser_id());
        values.put(AdministrationStaff.COLUMN_ACTIVE, administrationStaff.getActive());
        values.put(AdministrationStaff.COLUMN_ROLE, administrationStaff.getRole());

        long resultid = database.insert(administrationStaff.TABLE_ADMINISTRATION_STAFF, null, values);
//        administrationStaff.setId(resultid);
        return administrationStaff;
    }

    public AdministrationLocations insertAdministrationLocation(AdministrationLocations administrationLocations) {
        ContentValues values = new ContentValues();
        values.put(AdministrationLocations.COLUMN_ID, administrationLocations.getId());
        values.put(AdministrationLocations.COLUMN_LOC_NAME, administrationLocations.getLoc_name());
        values.put(AdministrationLocations.COLUMN_LOC_ADDR, administrationLocations.getLoc_addr());
        values.put(AdministrationLocations.COLUMN_LATITUDE, administrationLocations.getLatitude());
        values.put(AdministrationLocations.COLUMN_LONGITUDE, administrationLocations.getLongitude());
        values.put(AdministrationLocations.COLUMN_RECEIVING_BIN, administrationLocations.getReceiving_bin());
        values.put(AdministrationLocations.COLUMN_ACTIVE, administrationLocations.isActive());
        long resultid = database.insert(administrationLocations.TABLE_ADMINISTRATION_LOCATIONS, null, values);
//        administrationStaff.setId(resultid);
        return administrationLocations;
    }

    public AdministrationRole insertAdministrationRole(AdministrationRole administrationRole) {
        ContentValues values = new ContentValues();
        values.put(AdministrationRole.COLUMN_ID, administrationRole.getId());
        values.put(AdministrationRole.COLUMN_ROLE_NAME, administrationRole.getRole_name());
        values.put(AdministrationRole.COLUMN_ACTIVE, administrationRole.isActive());

        long resultid = database.insert(administrationRole.TABLE_ADMINISTRATION_ROLE, null, values);
//        administrationStaff.setId(resultid);
        return administrationRole;
    }

    public InventoryGold insertInventoryGold(InventoryGold inventoryGold) {
        ContentValues values = new ContentValues();

        values.put(InventoryGold.COLUMN_ID, inventoryGold.getId());
        values.put(InventoryGold.COLUMN_NAME, inventoryGold.getName());
        values.put(InventoryGold.COLUMN_GOLDTYPE, inventoryGold.getGoldtype());
        values.put(InventoryGold.COLUMN_SALEPRICE, inventoryGold.getSaleprice());
        values.put(InventoryGold.COLUMN_PURCHASEPRICE, inventoryGold.getPurchaseprice());
        values.put(InventoryGold.COLUMN_COST, inventoryGold.getCost());
        values.put(InventoryGold.COLUMN_TAX1, inventoryGold.getTax1());
        values.put(InventoryGold.COLUMN_TAX2, inventoryGold.getTax2());
        values.put(InventoryGold.COLUMN_ACTIVE, inventoryGold.getActive());
        values.put(InventoryGold.COLUMN_CODE, inventoryGold.getCode());

        long resultid = database.insert(inventoryGold.TABLE_INVENTORY_GOLD, null, values);
        inventoryGold.setId(resultid);
        return inventoryGold;
    }

    public InventoryProductGroup insertInventoryProductGroup(InventoryProductGroup inventoryProductGroup) {
        ContentValues values = new ContentValues();

        values.put(InventoryProductGroup.COLUMN_ID, inventoryProductGroup.getId());
        values.put(InventoryProductGroup.COLUMN_NAME, inventoryProductGroup.getName());
        values.put(InventoryProductGroup.COLUMN_ACTIVE, inventoryProductGroup.isActive());

        long resultid = database.insert(inventoryProductGroup.TABLE_PRODUCTGROUP, null, values);
        inventoryProductGroup.setId(resultid);
        return inventoryProductGroup;
    }

    public long insertInventory_products(Inventory_products inventory_products) {
        ContentValues values = new ContentValues();

        values.put(Inventory_products.COLUMN_ID, inventory_products.getId());
        values.put(Inventory_products.COLUMN_NAME, inventory_products.getName());
        values.put(Inventory_products.COLUMN_DESIGNNAME, inventory_products.getDesignname());
        values.put(Inventory_products.COLUMN_PHOTO, inventory_products.getPhoto());
        values.put(Inventory_products.COLUMN_BARCODE, inventory_products.getBarcode());
        values.put(Inventory_products.COLUMN_TAG, inventory_products.getTag());
        values.put(Inventory_products.COLUMN_BASEUOM, inventory_products.getBaseuom());
        values.put(Inventory_products.COLUMN_MINQTY, inventory_products.getMinqty());
        values.put(Inventory_products.COLUMN_MAXQTY, inventory_products.getMaxqty());
        values.put(Inventory_products.COLUMN_ACTIVE, inventory_products.isActive());
        values.put(Inventory_products.COLUMN_IS_DELETE, inventory_products.isIs_delete());
        values.put(Inventory_products.COLUMN_PGROUP_ID, inventory_products.getPgroup_id());
        values.put(Inventory_products.COLUMN_ROW_NO, inventory_products.getRow_no());
        values.put(Inventory_products.COLUMN_PLENGTH_ID, inventory_products.getPlength_id());
        values.put(Inventory_products.COLUMN_PREDUCE_ID, inventory_products.getPreduce_id());
        values.put(Inventory_products.COLUMN_PSUBGROUP_ID, inventory_products.getPsubgroup_id());
        values.put(Inventory_products.COLUMN_TS,Utility.dateFormat.format(inventory_products.getTs()));

        values.put(Inventory_products.COLUMN_DIAMOND, inventory_products.getDiamond());
        values.put(Inventory_products.COLUMN_DIAMONDCARATWEIGHT, inventory_products.getDiamondcaratweight());
        values.put(Inventory_products.COLUMN_DIAMONDCLARITY, inventory_products.getDiamondclarity());
        values.put(Inventory_products.COLUMN_DIAMONDMEASUREMENT, inventory_products.getDiamondmeasurement());
        values.put(Inventory_products.COLUMN_DIAMONDPRICE, inventory_products.getDiamondprice());
        values.put(Inventory_products.COLUMN_JEWELCOST, inventory_products.getJewelcost());
        values.put(Inventory_products.COLUMN_JEWELLENGTH_ID, inventory_products.getJewellength_id());
        values.put(Inventory_products.COLUMN_JEWELPRICE, inventory_products.getJewelprice());
        values.put(Inventory_products.COLUMN_JEWELSHAPE_ID, inventory_products.getJewelshape_id());
        values.put(Inventory_products.COLUMN_JEWELTYPE_ID, inventory_products.getJeweltype_id());
        values.put(Inventory_products.COLUMN_LABOURCHARE, inventory_products.getLabourcharge());
        values.put(Inventory_products.COLUMN_REMARKS, inventory_products.getRemarks());
        values.put(Inventory_products.COLUMN_SELLINGLABOURCHARGE, inventory_products.getSellinglabourcharge());
        values.put(Inventory_products.COLUMN_DIAMONDCOLOR_ID, inventory_products.getDiamondcolor_id());
        values.put(Inventory_products.COLUMN_DIAMONDSHAPE_ID, inventory_products.getDiamondshape_id());
        values.put(Inventory_products.COLUMN_DIAMONDTYPE_ID, inventory_products.getDiamondtype_id());

        long resultid = database.insert(inventory_products.TABLE_INVENTORY_PRODUCTS, null, values);
//        inventory_products.setId(resultid);
        return resultid;
    }

    public AdministrationSolutions insertAdministrationSolutions(AdministrationSolutions administrationSolutions) {
        ContentValues values = new ContentValues();
        values.put(AdministrationSolutions.COLUMN_SOLUTION_NAME, administrationSolutions.getSolution_name());
        values.put(AdministrationSolutions.COLUMN_ACTIVE, administrationSolutions.getActive());

        long resultid = database.insert(administrationSolutions.TABLE_ADMINISTRATION_SOLUTIONS, null, values);
        administrationSolutions.setId(resultid);
        return administrationSolutions;
    }

    public AdministrationWsdashboard insertAdministrationWsdashboard(AdministrationWsdashboard administrationWsdashboard) {
        ContentValues values = new ContentValues();
        values.put(AdministrationWsdashboard.COLUMN_ID, administrationWsdashboard.getId());
        values.put(AdministrationWsdashboard.COLUMN_TITLE, administrationWsdashboard.getTitle());
        values.put(AdministrationWsdashboard.COLUMN_IS_FOLDER, administrationWsdashboard.isIs_folder());
        values.put(AdministrationWsdashboard.COLUMN_PARENT_ID, administrationWsdashboard.getParent_id());
        values.put(AdministrationWsdashboard.COLUMN_ACTIONNAME, administrationWsdashboard.getActionname());
        values.put(AdministrationWsdashboard.COLUMN_GROUPNAME, administrationWsdashboard.getGroupname());
        values.put(AdministrationWsdashboard.COLUMN_IMAGE, administrationWsdashboard.getImage());
        values.put(AdministrationWsdashboard.COLUMN_TIMESTAMP, administrationWsdashboard.getTimestamp());
        values.put(AdministrationWsdashboard.COLUMN_DELETE, administrationWsdashboard.getIs_delete());
        values.put(AdministrationWsdashboard.COLUMN_DISPLAYNO, administrationWsdashboard.getDisplayno());
        values.put(AdministrationWsdashboard.COLUMN_SCREEN_ID, administrationWsdashboard.getScreen_id());

        long resultid = database.insert(administrationWsdashboard.TABLE_ADMINISTRATION_WSDASHBOARD, null, values);
//        administrationSolutions.setId(resultid);
        return administrationWsdashboard;
    }

    public InventoryUOM insertInventoryUOM(InventoryUOM inventoryUOM) {
        ContentValues values = new ContentValues();
        values.put(InventoryUOM.COLUMN_UOM, inventoryUOM.getUom());
        values.put(InventoryUOM.COLUMN_BASEQUANLITY, inventoryUOM.getBaseqty());
        values.put(InventoryUOM.COLUMN_PRODUCT_ID, inventoryUOM.getProduct_id());
        values.put(InventoryUOM.COLUMN_ACTIVE, inventoryUOM.isActive());

        long resultid = database.insert(inventoryUOM.TABLE_INVENTROY_UOM, null, values);
//        administrationStaff.setId(resultid);
        return inventoryUOM;
    }

    public InventoryGoldUOM insertInventoryGoldUOM(InventoryGoldUOM inventoryGoldUOM) {
        ContentValues values = new ContentValues();
        values.put(InventoryGoldUOM.COLUMN_UOM, inventoryGoldUOM.getUom());
        values.put(InventoryGoldUOM.COLUMN_BASEQUANLITY, inventoryGoldUOM.getBaseqty());
        values.put(InventoryGoldUOM.COLUMN_GOLD_ID, inventoryGoldUOM.getGold_id());
        values.put(InventoryGoldUOM.COLUMN_ACTIVE, inventoryGoldUOM.isActive());

        long resultid = database.insert(inventoryGoldUOM.TABLE_INVENTROY_GOLDUOM, null, values);
//        administrationStaff.setId(resultid);
        return inventoryGoldUOM;
    }

    public long insertInventoryBIN(InventoryBIN inventoryBIN) {
        ContentValues values = new ContentValues();
        values.put(InventoryBIN.COLUMN_ID, inventoryBIN.getId());
        values.put(InventoryBIN.COLUMN_BIN_NAME, inventoryBIN.getBin_name());
        values.put(InventoryBIN.COLUMN_BIN_DESCRIPTION, inventoryBIN.getBin_description());
        values.put(InventoryBIN.COLUMN_BIN_TYPE, inventoryBIN.getBin_type());
        values.put(InventoryBIN.COLUMN_BARCODE, inventoryBIN.getBarcode());
        values.put(InventoryBIN.COLUMN_TAG, inventoryBIN.getTag());
        values.put(InventoryBIN.COLUMN_LOCATION_ID, inventoryBIN.getLocation_id());
        values.put(InventoryBIN.COLUMN_ACTIVE, inventoryBIN.isActive());
        values.put(InventoryBIN.COLUMN_TS, Utility.dateFormat.format(inventoryBIN.getTs()));

        long resultid = database.insert(inventoryBIN.TABLE_INVENTORY_BIN, null, values);
//        administrationStaff.setId(resultid);
        return resultid;
    }

    public long insertGoodsInventory(InventoryGoodInventory goodsinventory) {
        ContentValues values = new ContentValues();
        values.put(InventoryGoodInventory.COLUMN_ID, goodsinventory.getId());
        values.put(InventoryGoodInventory.COLUMN_QTY, goodsinventory.getQty());
        values.put(InventoryGoodInventory.COLUMN_WEIGHT, goodsinventory.getWeight());
        values.put(InventoryGoodInventory.COLUMN_K, goodsinventory.getK());
        values.put(InventoryGoodInventory.COLUMN_P, goodsinventory.getP());
        values.put(InventoryGoodInventory.COLUMN_Y, goodsinventory.getY());
        values.put(InventoryGoodInventory.COLUMN_IS_DELETE, goodsinventory.isIs_delete());
        values.put(InventoryGoodInventory.COLUMN_TS, Utility.dateFormat.format(goodsinventory.getTs()));
        values.put(InventoryGoodInventory.COLUMN_LOCATION_ID, goodsinventory.getLocation_id());
        values.put(InventoryGoodInventory.COLUMN_PRODUCT_ID, goodsinventory.getProduct_id());
        values.put(InventoryGoodInventory.COLUMN_UOM_ID, goodsinventory.getUom_id());

        long resultid = database.insert(goodsinventory.TABLE_INVENTORY_GOODSINVENTORY, null, values);
//        goodsinventory.setId(resultid);
        return resultid;
    }

    public long insertInventoryPallet(InventoryPallet inventoryPallet) {
        ContentValues values = new ContentValues();
        values.put(InventoryPallet.COLUMN_ID, inventoryPallet.getId());
        values.put(InventoryPallet.COLUMN_PALLET_NAME, inventoryPallet.getPallet_name());
        values.put(InventoryPallet.COLUMN_PALLET_DESCRIPTION, inventoryPallet.getPallet_description());
        values.put(InventoryPallet.COLUMN_PALLET_TYPE, inventoryPallet.getPallet_type());
        values.put(InventoryPallet.COLUMN_BARCODE, inventoryPallet.getBarcode());
        values.put(InventoryPallet.COLUMN_TAG, inventoryPallet.getTag());
        values.put(InventoryPallet.COLUMN_LOCATION_ID, inventoryPallet.getLocation_id());
        values.put(InventoryPallet.COLUMN_WEIGHT, inventoryPallet.getWeight());
        values.put(InventoryPallet.COLUMN_IS_USED, inventoryPallet.isIs_used());
        values.put(InventoryPallet.COLUMN_ACTIVE, inventoryPallet.isActive());
        values.put(InventoryPallet.COLUMN_TS, Utility.dateFormat.format(inventoryPallet.getTs()));

        long resultid = database.insert(inventoryPallet.TABLE_INVENTORY_PALLET, null, values);
//        administrationStaff.setId(resultid);
        return resultid;
    }

    public long insertInventory_productserial(Inventory_productserial inventory_productserial) {
        ContentValues values = new ContentValues();
        values.put(Inventory_productserial.COLUMN_SERIAL_NO, inventory_productserial.getSerial_no());
        values.put(Inventory_productserial.COLUMN_NAME, inventory_productserial.getName());
        values.put(Inventory_productserial.COLUMN_PLENGTH, inventory_productserial.getPlength());
        values.put(Inventory_productserial.COLUMN_PHOTO, inventory_productserial.getPhoto());
        values.put(Inventory_productserial.COLUMN_BARCODE, inventory_productserial.getBarcode());
        values.put(Inventory_productserial.COLUMN_TAG, inventory_productserial.getTag());
        values.put(Inventory_productserial.COLUMN_WEIGHT, inventory_productserial.getWeight());
        values.put(Inventory_productserial.COLUMN_K, inventory_productserial.getK());
        values.put(Inventory_productserial.COLUMN_P, inventory_productserial.getP());
        values.put(Inventory_productserial.COLUMN_Y, inventory_productserial.getY());
        values.put(Inventory_productserial.COLUMN_REDUCE_WEIGHT, inventory_productserial.getReduce_weight());
        values.put(Inventory_productserial.COLUMN_REDUCE_K, inventory_productserial.getReduce_k());
        values.put(Inventory_productserial.COLUMN_REDUCE_P, inventory_productserial.getReduce_p());
        values.put(Inventory_productserial.COLUMN_REDUCE_Y, inventory_productserial.getReduce_y());
        values.put(Inventory_productserial.COLUMN_JEWEL_WEIGHT, inventory_productserial.getJewel_weight());
        values.put(Inventory_productserial.COLUMN_JEWEL_K, inventory_productserial.getJewel_k());
        values.put(Inventory_productserial.COLUMN_JEWEL_P, inventory_productserial.getJewel_p());
        values.put(Inventory_productserial.COLUMN_JEWEL_Y, inventory_productserial.getJewel_y());
        values.put(Inventory_productserial.COLUMN_JEWEL_FEE, inventory_productserial.getJewel_fee());
        values.put(Inventory_productserial.COLUMN_PRODUCTION_FEE, inventory_productserial.getProduction_fee());
        values.put(Inventory_productserial.COLUMN_JEWEL_NAME1, inventory_productserial.getJewel_name1());
        values.put(Inventory_productserial.COLUMN_JEWEL_NAME2, inventory_productserial.getJewel_name2());
        values.put(Inventory_productserial.COLUMN_JEWEL_NAME3, inventory_productserial.getJewel_name3());
        values.put(Inventory_productserial.COLUMN_JEWEL_NAME4, inventory_productserial.getJewel_name4());
        values.put(Inventory_productserial.COLUMN_JEWEL_NAME5, inventory_productserial.getJewel_name5());
        values.put(Inventory_productserial.COLUMN_JEWEL_WEIGHT1, inventory_productserial.getJewel_weight1());
        values.put(Inventory_productserial.COLUMN_JEWEL_WEIGHT2, inventory_productserial.getJewel_weight2());
        values.put(Inventory_productserial.COLUMN_JEWEL_WEIGHT3, inventory_productserial.getJewel_weight3());
        values.put(Inventory_productserial.COLUMN_JEWEL_WEIGHT4, inventory_productserial.getJewel_weight4());
        values.put(Inventory_productserial.COLUMN_JEWEL_WEIGHT5, inventory_productserial.getJewel_weight5());
        values.put(Inventory_productserial.COLUMN_REMARKS, inventory_productserial.getRemarks());
        values.put(Inventory_productserial.COLUMN_IS_DELETE, inventory_productserial.isIs_delete());
        values.put(Inventory_productserial.COLUMN_TS, Utility.dateFormat.format(inventory_productserial.getTs()));
        values.put(Inventory_productserial.COLUMN_BIN_ID, inventory_productserial.getBin_id());
        values.put(Inventory_productserial.COLUMN_GOLD_ID, inventory_productserial.getGold_id());
        values.put(Inventory_productserial.COLUMN_LOCATION_ID, inventory_productserial.getLocation_id());
        values.put(Inventory_productserial.COLUMN_PALLET_ID, inventory_productserial.getPallet_id());
        values.put(Inventory_productserial.COLUMN_PRODUCT_ID, inventory_productserial.getProduct_id());
        values.put(Inventory_productserial.COLUMN_SMITH_ID, inventory_productserial.getSmith_id());
        values.put(Inventory_productserial.COLUMN_STAFF_ID, inventory_productserial.getStaff_id());
        values.put(Inventory_productserial.COLUMN_UOM_ID, inventory_productserial.getUom_id());
        values.put(Inventory_productserial.COLUMN_DELIVERED, inventory_productserial.isDelivered());
        values.put(Inventory_productserial.COLUMN_GOODSID, inventory_productserial.getGoodsid());
        long resultid = database.insert(inventory_productserial.TABLE_INVENTORY_PRODUCTSERIAL, null, values);
//        Inventory_productserial.set(resultid);
        return resultid;
    }

    public long insertInventory_serialgolds(Inventory_serialgolds inventory_serialgolds) {
        ContentValues values = new ContentValues();
        values.put(Inventory_serialgolds.COLUMN_SERIAL_ID, inventory_serialgolds.getSerial_id());
        values.put(Inventory_serialgolds.COLUMN_REDUCE_WEIGHT, inventory_serialgolds.getReduce_weight());
        values.put(Inventory_serialgolds.COLUMN_REDUCE_K, inventory_serialgolds.getReduce_k());
        values.put(Inventory_serialgolds.COLUMN_REDUCE_P, inventory_serialgolds.getReduce_p());
        values.put(Inventory_serialgolds.COLUMN_REDUCE_Y, inventory_serialgolds.getReduce_y());
        values.put(Inventory_serialgolds.COLUMN_PRODUCTION_FEE, inventory_serialgolds.getProduction_fee());
        values.put(Inventory_serialgolds.COLUMN_COST_REDUCE_WEIGHT, inventory_serialgolds.getCost_reduce_weight());
        values.put(Inventory_serialgolds.COLUMN_COST_REDUCE_K, inventory_serialgolds.getCost_reduce_k());
        values.put(Inventory_serialgolds.COLUMN_REDUCE_P, inventory_serialgolds.getCost_reduce_p());
        values.put(Inventory_serialgolds.COLUMN_COST_REDUCE_Y, inventory_serialgolds.getCost_reduce_y());
        values.put(Inventory_serialgolds.COLUMN_COST_PRODUCTIONFEE, inventory_serialgolds.getCost_productionfee());
        values.put(Inventory_serialgolds.COLUMN_IS_DELETE, inventory_serialgolds.isIs_delete());
        values.put(Inventory_serialgolds.COLUMN_GOLD_ID, inventory_serialgolds.getGold_id());
        values.put(Inventory_serialgolds.COLUMN_TS, Utility.dateFormat.format(inventory_serialgolds.getTs()));

        long resultid = database.insert(inventory_serialgolds.TABLE_INVENTORY_SERIALGOLDS, null, values);
//        Inventory_productserial.set(resultid);
        return resultid;
    }

    public long insertManufacturingSmith(ManufacturingSmith manufacturingsmith) {
        ContentValues values = new ContentValues();
        values.put(ManufacturingSmith.COLUMN_ID, manufacturingsmith.getId());
        values.put(ManufacturingSmith.COLUMN_NAME, manufacturingsmith.getName());
        values.put(ManufacturingSmith.COLUMN_NICKNAME, manufacturingsmith.getNickname());
        values.put(ManufacturingSmith.COLUMN_NRC, manufacturingsmith.getNrc());
        values.put(ManufacturingSmith.COLUMN_ADDRESS, manufacturingsmith.getAddress());
        values.put(ManufacturingSmith.COLUMN_PHONE, manufacturingsmith.getPhone());
        values.put(ManufacturingSmith.COLUMN_REFNAME, manufacturingsmith.getRefname());
        values.put(ManufacturingSmith.COLUMN_MIXJOB, manufacturingsmith.getMixjob());
        values.put(ManufacturingSmith.COLUMN_K, manufacturingsmith.getK());
        values.put(ManufacturingSmith.COLUMN_P, manufacturingsmith.getP());
        values.put(ManufacturingSmith.COLUMN_Y, manufacturingsmith.getY());
        values.put(ManufacturingSmith.COLUMN_G, manufacturingsmith.getG());
        values.put(ManufacturingSmith.COLUMN_COST, manufacturingsmith.getCost());
        values.put(ManufacturingSmith.COLUMN_DATE_JOINED, Utility.dateFormat.format(manufacturingsmith.getDate_joined()));
        values.put(ManufacturingSmith.COLUMN_DATE_END, Utility.dateFormat.format(manufacturingsmith.getDate_end()));
        values.put(ManufacturingSmith.COLUMN_IS_SMITH2, manufacturingsmith.isIs_smith2());
        values.put(ManufacturingSmith.COLUMN_ACTIVE, manufacturingsmith.isActive());
        values.put(ManufacturingSmith.COLUMN_GOLDSAVING, manufacturingsmith.getGoldsaving());
        values.put(ManufacturingSmith.COLUMN_INHANDJOB, manufacturingsmith.getInhandjob());
        values.put(ManufacturingSmith.COLUMN_PHOTO, manufacturingsmith.getPhoto());
        long resultid = database.insert(manufacturingsmith.TABLE_MANUFACTURING_SMITH, null, values);
//        Inventory_productserial.set(resultid);
        return resultid;
    }

    public long insertManufacturing_smithmembers(Manufacturing_smithmembers manufacturing_smithmembers) {
        ContentValues values = new ContentValues();
        values.put(Manufacturing_smithmembers.COLUMN_ID, manufacturing_smithmembers.getId());
        values.put(Manufacturing_smithmembers.COLUMN_NAME, manufacturing_smithmembers.getName());
        values.put(Manufacturing_smithmembers.COLUMN_NICKNAME, manufacturing_smithmembers.getNickname());
        values.put(Manufacturing_smithmembers.COLUMN_NRC, manufacturing_smithmembers.getNrc());
        values.put(Manufacturing_smithmembers.COLUMN_ADDRESS, manufacturing_smithmembers.getAddress());
        values.put(Manufacturing_smithmembers.COLUMN_PHONE, manufacturing_smithmembers.getPhone());
        values.put(Manufacturing_smithmembers.COLUMN_ACTIVE, manufacturing_smithmembers.isActive());
        values.put(Manufacturing_smithmembers.COLUMN_SMITH_ID, manufacturing_smithmembers.getSmith_id());
        values.put(Manufacturing_smithmembers.COLUMN_PHOTO, manufacturing_smithmembers.getPhoto());
        long resultid = database.insert(manufacturing_smithmembers.TABLE_MANUFACTURING_SMITHMEMBERS, null, values);
//        Inventory_productserial.set(resultid);
        return resultid;
    }

    public long insertManufacturing_jobstatus(Manufacturing_jobstatus manufacturing_jobstatus) {
        ContentValues values = new ContentValues();
        values.put(Manufacturing_jobstatus.COLUMN_ID, manufacturing_jobstatus.getId());
        values.put(Manufacturing_jobstatus.COLUMN_NAME, manufacturing_jobstatus.getName());
        values.put(Manufacturing_jobstatus.COLUMN_DESCRIPTION, manufacturing_jobstatus.getDescription());
        values.put(Manufacturing_jobstatus.COLUMN_STATUS_TYPE, manufacturing_jobstatus.getStatus_type());
        values.put(Manufacturing_jobstatus.COLUMN_ACTIVE, manufacturing_jobstatus.isActive());
        long resultid = database.insert(manufacturing_jobstatus.TABLE_MANUFACTURING_JOBSTATUS, null, values);
//        Inventory_productserial.set(resultid);
        return resultid;
    }

    public long insertManufacturing_Smith_Jobgold(Manufacturing_Smith_Jobgold manufacturing_smith_jobgold) {
        ContentValues values = new ContentValues();
        values.put(Manufacturing_Smith_Jobgold.COLUMN_SMITH_JOBORDER_ID, manufacturing_smith_jobgold.getSmith_joborder_id());
        values.put(Manufacturing_Smith_Jobgold.COLUMN_WEIGHT, manufacturing_smith_jobgold.getWeight());
        values.put(Manufacturing_Smith_Jobgold.COLUMN_QTY, manufacturing_smith_jobgold.getQty());
        values.put(Manufacturing_Smith_Jobgold.COLUMN_GOLDSAVING, manufacturing_smith_jobgold.getGoldsaving());
        values.put(Manufacturing_Smith_Jobgold.COLUMN_REMARKS, manufacturing_smith_jobgold.getRemarks());
        values.put(Manufacturing_Smith_Jobgold.COLUMN_ROW_NO, manufacturing_smith_jobgold.getRow_no());
        values.put(Manufacturing_Smith_Jobgold.COLUMN_IS_DELETE, manufacturing_smith_jobgold.getIs_delete());
        values.put(Manufacturing_Smith_Jobgold.COLUMN_GOLD_ID, manufacturing_smith_jobgold.getGold_id());
        values.put(Manufacturing_Smith_Jobgold.COLUMN_UOM_ID, manufacturing_smith_jobgold.getUom_id());
        long resultid = database.insert(manufacturing_smith_jobgold.TABLE_MANUFACTURING_SMITH_JOBGOLD, null, values);
//        Inventory_productserial.set(resultid);
        return resultid;
    }

    public long insertManufacturing_smith_joborder(Manufacturing_smith_joborder manufacturing_smith_joborder) {
        ContentValues values = new ContentValues();
        values.put(Manufacturing_smith_joborder.COLUMN_ID, manufacturing_smith_joborder.getId());
        values.put(Manufacturing_smith_joborder.COLUMN_JOBORDER_NO, manufacturing_smith_joborder.getJoborder_no());
        values.put(Manufacturing_smith_joborder.COLUMN_JOBORDER_DATE, Utility.dateFormat.format(manufacturing_smith_joborder.getJoborder_date()));
        values.put(Manufacturing_smith_joborder.COLUMN_PREJEWELOUT, manufacturing_smith_joborder.isPrejewelout());
        values.put(Manufacturing_smith_joborder.COLUMN_PREJEWELOUT_DATE, Utility.dateFormat.format(manufacturing_smith_joborder.getPrejewelout_date()));
        values.put(Manufacturing_smith_joborder.COLUMN_DATE_TARGET, Utility.dateFormat.format(manufacturing_smith_joborder.getDate_target()));
        values.put(Manufacturing_smith_joborder.COLUMN_DATE_START, Utility.dateFormat.format(manufacturing_smith_joborder.getDate_start()));
        values.put(Manufacturing_smith_joborder.COLUMN_DATE_END, Utility.dateFormat.format(manufacturing_smith_joborder.getDate_end()));
        values.put(Manufacturing_smith_joborder.COLUMN_REMARKS, manufacturing_smith_joborder.getRemarks());
        values.put(Manufacturing_smith_joborder.COLUMN_ACTIVE, manufacturing_smith_joborder.isActive());
        values.put(Manufacturing_smith_joborder.COLUMN_IS_DELETE, manufacturing_smith_joborder.isIs_delete());
        values.put(Manufacturing_smith_joborder.COLUMN_TS, Utility.dateFormat.format(manufacturing_smith_joborder.getTs()));
        values.put(Manufacturing_smith_joborder.COLUMN_JOBORDER_TYPE_ID, manufacturing_smith_joborder.getJoborder_type_id());
        values.put(Manufacturing_smith_joborder.COLUMN_JOBSTATUS_ID, manufacturing_smith_joborder.getJobstatus_id());
        values.put(Manufacturing_smith_joborder.COLUMN_SMITH_ID, manufacturing_smith_joborder.getSmith_id());
        values.put(Manufacturing_smith_joborder.COLUMN_DENSITY, manufacturing_smith_joborder.getDensity());
        values.put(Manufacturing_smith_joborder.COLUMN_DIFF_K, manufacturing_smith_joborder.getDiff_k());
        values.put(Manufacturing_smith_joborder.COLUMN_DIFF_P, manufacturing_smith_joborder.getDiff_p());
        values.put(Manufacturing_smith_joborder.COLUMN_PRODUCT_WEIGHT, manufacturing_smith_joborder.getDiff_weight());
        values.put(Manufacturing_smith_joborder.COLUMN_DIFF_Y, manufacturing_smith_joborder.getDiff_y());
        values.put(Manufacturing_smith_joborder.COLUMN_PRINT_COUNT, manufacturing_smith_joborder.getPrint_count());
        values.put(Manufacturing_smith_joborder.COLUMN_PRODUCT_WEIGHT, manufacturing_smith_joborder.getProduct_weight());
        values.put(Manufacturing_smith_joborder.COLUMN_REMAIN_GOLD, manufacturing_smith_joborder.getRemain_gold());
        values.put(Manufacturing_smith_joborder.COLUMN_REMAIN_JEWEL, manufacturing_smith_joborder.getRemain_jewel());
        values.put(Manufacturing_smith_joborder.COLUMN_SAVE_COUNT, manufacturing_smith_joborder.getSave_count());
        long resultid = database.insert(manufacturing_smith_joborder.TABLE_MANUFACTURING_SMITH_JOBORDER, null, values);
//        Inventory_productserial.set(resultid);
        return resultid;
    }

    public long insertManufacturing_smith_jobproduct(Manufacturing_smith_jobproduct manufacturing_smith_jobproduct) {
        ContentValues values = new ContentValues();
        values.put(Manufacturing_smith_jobproduct.COLUMN_PLENGTH, manufacturing_smith_jobproduct.getPlength());
        values.put(Manufacturing_smith_jobproduct.COLUMN_WEIGHT, manufacturing_smith_jobproduct.getWeight());
        values.put(Manufacturing_smith_jobproduct.COLUMN_K, manufacturing_smith_jobproduct.getK());
        values.put(Manufacturing_smith_jobproduct.COLUMN_P, manufacturing_smith_jobproduct.getP());
        values.put(Manufacturing_smith_jobproduct.COLUMN_Y, manufacturing_smith_jobproduct.getY());
        values.put(Manufacturing_smith_jobproduct.COLUMN_QTY, manufacturing_smith_jobproduct.getQty());
        values.put(Manufacturing_smith_jobproduct.COLUMN_REDUCE_WEIGHT, manufacturing_smith_jobproduct.getReduce_weight());
        values.put(Manufacturing_smith_jobproduct.COLUMN_REDUCE_K, manufacturing_smith_jobproduct.getReduce_k());
        values.put(Manufacturing_smith_jobproduct.COLUMN_REDUCE_P, manufacturing_smith_jobproduct.getReduce_p());
        values.put(Manufacturing_smith_jobproduct.COLUMN_REDUCE_Y, manufacturing_smith_jobproduct.getReduce_y());
        values.put(Manufacturing_smith_jobproduct.COLUMN_TARGET_DATE, Utility.dateFormat.format(manufacturing_smith_jobproduct.getTarget_date()));
        values.put(Manufacturing_smith_jobproduct.COLUMN_START_DATE, Utility.dateFormat.format(manufacturing_smith_jobproduct.getStart_date()));
        values.put(Manufacturing_smith_jobproduct.COLUMN_END_DATE, Utility.dateFormat.format(manufacturing_smith_jobproduct.getEnd_date()));
        values.put(Manufacturing_smith_jobproduct.COLUMN_WAGES, manufacturing_smith_jobproduct.getWages());
        values.put(Manufacturing_smith_jobproduct.COLUMN_REMARKS, manufacturing_smith_jobproduct.getRemarks());
        values.put(Manufacturing_smith_jobproduct.COLUMN_PRODUCTS_ID, manufacturing_smith_jobproduct.getProducts_id());
        values.put(Manufacturing_smith_jobproduct.COLUMN_SMITH_JOBORDER_ID, manufacturing_smith_jobproduct.getSmith_joborder_id());
        values.put(Manufacturing_smith_jobproduct.COLUMN_IS_DELETE, manufacturing_smith_jobproduct.isIs_delete());
        values.put(Manufacturing_smith_jobproduct.COLUMN_ROW_NO, manufacturing_smith_jobproduct.getRow_no());
        values.put(Manufacturing_smith_jobproduct.COLUMN_TO_LOCATION_ID, manufacturing_smith_jobproduct.getTo_location_id());
        values.put(Manufacturing_smith_jobproduct.COLUMN_UOM_ID, manufacturing_smith_jobproduct.getUom_id());
        long resultid = database.insert(manufacturing_smith_jobproduct.TABLE_MANUFACTURING_SMITH_JOBPRODUCT, null, values);
//        Inventory_productserial.set(resultid);
        return resultid;
    }

    public long insertManufacturing_smith_jobtype(Manufacturing_smith_jobtype manufacturing_smith_jobtype) {
        ContentValues values = new ContentValues();
        values.put(Manufacturing_smith_jobtype.COLUMN_ID, manufacturing_smith_jobtype.getId());
        values.put(Manufacturing_smith_jobtype.COLUMN_NAME, manufacturing_smith_jobtype.getName());
        values.put(Manufacturing_smith_jobtype.COLUMN_DESCRIPTION, manufacturing_smith_jobtype.getDescription());
        values.put(Manufacturing_smith_jobtype.COLUMN_JOBTYPE_GROUP, manufacturing_smith_jobtype.getJobtype_group());
        values.put(Manufacturing_smith_jobtype.COLUMN_ACTIVE, manufacturing_smith_jobtype.isActive());
        long resultid = database.insert(manufacturing_smith_jobtype.TABLE_MANUFACTURING_SMITH_JOBTYPE, null, values);
//        Inventory_productserial.set(resultid);
        return resultid;
    }

    public long insertAdministration_wsimages(AdministrationWsimages administrationWsimages){

        ContentValues values = new ContentValues();
        values.put(AdministrationWsimages.COLUMN_ID, administrationWsimages.getId());
        values.put(AdministrationWsimages.COLUMN_NAME, administrationWsimages.getName());
        values.put(AdministrationWsimages.COLUMN_PATH, administrationWsimages.getPath());
        values.put(AdministrationWsimages.COLUMN_TIMESTAMP, Utility.dateFormat.format(administrationWsimages.getTimestamp()));
        values.put(AdministrationWsimages.COLUMN_IS_DELETE, administrationWsimages.getIs_delete());
        values.put(AdministrationWsimages.COLUMN_SOLUTION_ID, administrationWsimages.getSolution_id());
        values.put(AdministrationWsimages.COLUMN_TYPE_ID, administrationWsimages.getType_id());
        Long resultid = database.insert(administrationWsimages.TABLE_ADMINISTRATION_WSIMAGES,null,values);
        return resultid;

    }

    public long insertAdministration_wsimagestype(AdministrationWsimagestype administrationWsimagestype){

        ContentValues values = new ContentValues();
        values.put(AdministrationWsimagestype.COLUMN_NAME, administrationWsimagestype.getName());
        Long resultid = database.insert(administrationWsimagestype.TABLE_ADMINISTRATION_WSIMAGESTYPE,null,values);
        return resultid;

    }

    public long insertProduct_Reduce(InventoryProductreduce inventoryProductreduce){

        ContentValues values = new ContentValues();
        values.put(InventoryProductreduce.COLUMN_ID, inventoryProductreduce.getId());
        values.put(InventoryProductreduce.COLUMN_REDUCE_G, inventoryProductreduce.getReduce_g());
        values.put(InventoryProductreduce.COLUMN_REDUCE_K, inventoryProductreduce.getReduce_k());
        values.put(InventoryProductreduce.COLUMN_REDUCE_P, inventoryProductreduce.getReduce_p());
        values.put(InventoryProductreduce.COLUMN_REDUCE_Y, inventoryProductreduce.getReduce_y());
        values.put(InventoryProductreduce.COLUMN_PRODUCTION_FEE, inventoryProductreduce.getProduction_fee());
        values.put(InventoryProductreduce.COLUMN_COST_REDUCE_K, inventoryProductreduce.getCost_reduce_k());
        values.put(InventoryProductreduce.COLUMN_COST_REDUCE_P, inventoryProductreduce.getCost_reduce_p());
        values.put(InventoryProductreduce.COLUMN_COST_REDUCE_Y, inventoryProductreduce.getCost_reduce_y());
        values.put(InventoryProductreduce.COLUMN_COST_PRODUCTION_FEE, inventoryProductreduce.getCost_production_fee());
        values.put(InventoryProductreduce.COLUMN_REMARKS, inventoryProductreduce.getRemarks());
        values.put(InventoryProductreduce.COLUMN_ACTIVE, inventoryProductreduce.getActive());
        values.put(InventoryProductreduce.COLUMN_IS_DELETE, inventoryProductreduce.getIs_delete());
        values.put(InventoryProductreduce.COLUMN_GOLD_ID, inventoryProductreduce.getGold_id());
        values.put(InventoryProductreduce.COLUMN_PLENGTH_ID, inventoryProductreduce.getPlength_id());
        Long resultid = database.insert(inventoryProductreduce.TABLE_INVENTORY_PRODUCTREDUCE,null,values);
        return resultid;

    }
    public long insertProductSubGroup(InventoryProductSubgroups inventoryProductSubgroups) {
        ContentValues values = new ContentValues();
        values.put(InventoryProductSubgroups.COLUMN_ID,inventoryProductSubgroups.getId());
        values.put(InventoryProductSubgroups.COLUMN_NAME,inventoryProductSubgroups.getName());
        values.put(InventoryProductSubgroups.COLUMN_ACTIVE,inventoryProductSubgroups.getActive());
        values.put(InventoryProductSubgroups.COLUMN_IS_DELETE,inventoryProductSubgroups.getIs_delete());
        values.put(InventoryProductSubgroups.COLUMN_PGROUP_ID,inventoryProductSubgroups.getPgroup_id());
        Long resultid = database.insert(inventoryProductSubgroups.TABLE_INVENTORY_PRODUCTSUBGROUPS,null,values);
        return resultid;
    }

    public long insertProductLength(InventoryProductlength inventoryProductlength) {
        ContentValues values = new ContentValues();
        values.put(InventoryProductlength.COLUMN_ID,inventoryProductlength.getId());
        values.put(InventoryProductlength.COLUMN_PLENGTH,inventoryProductlength.getPlength());
        values.put(InventoryProductlength.COLUMN_ACTIVE,inventoryProductlength.getActive());
        values.put(InventoryProductlength.COLUMN_IS_DELETE,inventoryProductlength.getIs_delete());
        values.put(InventoryProductlength.COLUMN_PSGROUP_ID,inventoryProductlength.getPsgroup_id());
        long resultid = database.insert(inventoryProductlength.TABLE_INVENTORY_PRODUCTLENGTH,null,values);
        return resultid;
    }

    public long insertInventory_jewelinventory(Inventory_jewelinventory inventory_jewelinventory) {
        ContentValues values = new ContentValues();
        values.put(Inventory_jewelinventory.COLUMN_QTY,inventory_jewelinventory.getQty());
        values.put(Inventory_jewelinventory.COLUMN_TS,Utility.dateFormat.format(inventory_jewelinventory.getTs()));
        values.put(Inventory_jewelinventory.COLUMN_JEWELLENGTH_ID,inventory_jewelinventory.getJewellength_id());
        values.put(Inventory_jewelinventory.COLUMN_JEWELSHAPE_ID,inventory_jewelinventory.getJewelshape_id());
        values.put(Inventory_jewelinventory.COLUMN_JEWELTYPE_ID,inventory_jewelinventory.getJeweltype_id());
        long resultid = database.insert(inventory_jewelinventory.TABLE_INVENTORY_JEWELINVENTORY,null,values);
        return resultid;
    }

    public long insertInventory_jewellength(Inventory_jewellength inventory_jewellength){
        ContentValues values = new ContentValues();
        values.put(Inventory_jewellength.COLUMN_ID,inventory_jewellength.getId());
        values.put(Inventory_jewellength.COLUMN_NAME,inventory_jewellength.getName());
        values.put(Inventory_jewellength.COLUMN_ACTIVE,inventory_jewellength.getActive());
        values.put(Inventory_jewellength.COLUMN_JEWELSHAPE_ID,inventory_jewellength.getJewelshape_id());
        long resultid = database.insert(inventory_jewellength.TABLE_INVENTORY_JEWELLENGTH,null,values);
        return resultid;
    }

    public long insertInventory_jewelpurchase(Inventory_jewelpurchase inventory_jewelpurchase){
        ContentValues values = new ContentValues();
        values.put(Inventory_jewelpurchase.COLUMN_PURCHASE_NO,inventory_jewelpurchase.getPurchase_no());
        values.put(Inventory_jewelpurchase.COLUMN_PURCHASE_DATE,Utility.dateFormat.format(inventory_jewelpurchase.getPurchase_date()));
        values.put(Inventory_jewelpurchase.COLUMN_REFERENCE_NO,inventory_jewelpurchase.getReference_no());
        values.put(Inventory_jewelpurchase.COLUMN_AMOUNT,inventory_jewelpurchase.getAmount());
        values.put(Inventory_jewelpurchase.COLUMN_PAID_AMOUNT,inventory_jewelpurchase.getPaid_amount());
        values.put(Inventory_jewelpurchase.COLUMN_DEDUCTION,inventory_jewelpurchase.getDeduction());
        values.put(Inventory_jewelpurchase.COLUMN_REMARKS,inventory_jewelpurchase.getRemarks());
        values.put(Inventory_jewelpurchase.COLUMN_IS_DELETE,inventory_jewelpurchase.getIs_delete());
        values.put(Inventory_jewelpurchase.COLUMN_TS,Utility.dateFormat.format(inventory_jewelpurchase.getTs()));
        values.put(Inventory_jewelpurchase.COLUMN_SUPPLIER_ID,inventory_jewelpurchase.getSupplier_id());
        long resultid = database.insert(inventory_jewelpurchase.TABLE_INVENTORY_JEWELPURCHASE,null,values);
        return resultid;
    }

    public long insertInventory_jewelpurchaseitems(Inventory_jewelpurchaseitems inventory_jewelpurchaseitems){
        ContentValues values = new ContentValues();
        values.put(Inventory_jewelpurchaseitems.COLUMN_ID,inventory_jewelpurchaseitems.getId());
        values.put(Inventory_jewelpurchaseitems.COLUMN_QTY,Utility.dateFormat.format(inventory_jewelpurchaseitems.getQty()));
        values.put(Inventory_jewelpurchaseitems.COLUMN_PRICE,inventory_jewelpurchaseitems.getPrice());
        values.put(Inventory_jewelpurchaseitems.COLUMN_AMOUNT,inventory_jewelpurchaseitems.getAmount());
        values.put(Inventory_jewelpurchaseitems.COLUMN_REMARKS,inventory_jewelpurchaseitems.getRemarks());
        values.put(Inventory_jewelpurchaseitems.COLUMN_ROW_NO,inventory_jewelpurchaseitems.getRow_no());
        values.put(Inventory_jewelpurchaseitems.COLUMN_IS_DELETE,inventory_jewelpurchaseitems.getIs_delete());
        values.put(Inventory_jewelpurchaseitems.COLUMN_TS,Utility.dateFormat.format(inventory_jewelpurchaseitems.getTs()));
        values.put(Inventory_jewelpurchaseitems.COLUMN_JEWEL_PURCHASE_ID,inventory_jewelpurchaseitems.getJewel_purchase_id());
        values.put(Inventory_jewelpurchaseitems.COLUMN_JEWELLENGTH_ID,inventory_jewelpurchaseitems.getJewel_purchase_id());
        values.put(Inventory_jewelpurchaseitems.COLUMN_JEWELSHAPE_ID,inventory_jewelpurchaseitems.getJewelshape_id());
        values.put(Inventory_jewelpurchaseitems.COLUMN_JEWELTYPE_ID,inventory_jewelpurchaseitems.getJeweltype_id());
        long resultid = database.insert(inventory_jewelpurchaseitems.TABLE_INVENTORY_JEWELPURCHASEITEMS,null,values);
        return resultid;
    }

    public long insertInventory_jewelshape(Inventory_jewelshape inventory_jewelshape){
        ContentValues values = new ContentValues();
        values.put(Inventory_jewelshape.COLUMN_ID,inventory_jewelshape.getId());
        values.put(Inventory_jewelshape.COLUMN_NAME,inventory_jewelshape.getName());
        values.put(Inventory_jewelshape.COLUMN_ACTIVE,inventory_jewelshape.getActive());
        values.put(Inventory_jewelshape.COLUMN_JEWELTYPE_ID,inventory_jewelshape.getJeweltype_id());

        long resultid = database.insert(inventory_jewelshape.TABLE_INVENTORY_JEWELSHAPE,null,values);
        return resultid;
    }

    public long insertInventory_jeweltype(Inventory_jeweltype inventory_jeweltype){
        ContentValues values = new ContentValues();
        values.put(Inventory_jeweltype.COLUMN_ID,inventory_jeweltype.getId());
        values.put(Inventory_jeweltype.COLUMN_NAME,inventory_jeweltype.getName());
        values.put(Inventory_jeweltype.COLUMN_UNITTYPE,inventory_jeweltype.getUnittype());
        values.put(Inventory_jeweltype.COLUMN_PURCHASE_UNITTYPE,inventory_jeweltype.getPurchase_unittype());
        values.put(Inventory_jeweltype.COLUMN_ACTIVE,inventory_jeweltype.getActive());

        long resultid = database.insert(inventory_jeweltype.TABLE_INVENTORY_JEWELTYPE,null,values);
        return resultid;
    }

    public long insertInventory_supplier(Inventory_supplier inventory_supplier){
        ContentValues values = new ContentValues();
        values.put(Inventory_supplier.COLUMN_ID,inventory_supplier.getId());
        values.put(Inventory_supplier.COLUMN_NAME,inventory_supplier.getName());
        values.put(Inventory_supplier.COLUMN_ADDRESS,inventory_supplier.getAddress());
        values.put(Inventory_supplier.COLUMN_PHONE,inventory_supplier.getPhone());
        values.put(Inventory_supplier.COLUMN_ACTIVE,inventory_supplier.getActive());
        values.put(Inventory_supplier.COLUMN_SUPPLIER_GROUP_ID,inventory_supplier.getSupplier_group_id());

        long resultid = database.insert(inventory_supplier.TABLE_INVENTORY_SUPPLIER,null,values);
        return resultid;
    }

    public long insertInventory_suppliergroup(Inventory_suppliergroup inventory_suppliergroup){
        ContentValues values = new ContentValues();
        values.put(Inventory_suppliergroup.COLUMN_ID,inventory_suppliergroup.getId());
        values.put(Inventory_suppliergroup.COLUMN_NAME,inventory_suppliergroup.getName());
        values.put(Inventory_suppliergroup.COLUMN_ACTIVE,inventory_suppliergroup.getActive());

        long resultid = database.insert(inventory_suppliergroup.TABLE_INVENTORY_SUPPLIERGROUP,null,values);
        return resultid;
    }

    public long insertInventory_receiveserial(Inventory_receiveserial inventory_receiveserial){
        ContentValues values = new ContentValues();
        values.put(Inventory_receiveserial.COLUMN_PRODUCTSERIAL_ID,inventory_receiveserial.getProductserial_id());
        values.put(Inventory_receiveserial.COLUMN_LINENO,inventory_receiveserial.getLineno());
        values.put(Inventory_receiveserial.COLUMN_IS_DELETE,inventory_receiveserial.getIs_delete());
        values.put(Inventory_receiveserial.COLUMN_TS,Utility.dateFormat.format(inventory_receiveserial.getTs()));

        long resultid = database.insert(inventory_receiveserial.TABLE_INVENTORY_RECEIVESERIAL,null,values);
        return resultid;
    }

    public long insertInventory_receiveddetail(Inventory_receiveddetail inventory_receiveddetail){
        ContentValues values = new ContentValues();
        values.put(Inventory_receiveddetail.COLUMN_LINENO,inventory_receiveddetail.getLineno());
        values.put(Inventory_receiveddetail.COLUMN_QTY,inventory_receiveddetail.getQty());
        values.put(Inventory_receiveddetail.COLUMN_WEIGHT,inventory_receiveddetail.getWeight());
        values.put(Inventory_receiveddetail.COLUMN_K,inventory_receiveddetail.getK());
        values.put(Inventory_receiveddetail.COLUMN_P,inventory_receiveddetail.getP());
        values.put(Inventory_receiveddetail.COLUMN_Y,inventory_receiveddetail.getY());
        values.put(Inventory_receiveddetail.COLUMN_DENSITY,inventory_receiveddetail.getDensity());
        values.put(Inventory_receiveddetail.COLUMN_REMARKS,inventory_receiveddetail.getRemarks());
        values.put(Inventory_receiveddetail.COLUMN_IS_DELETE,inventory_receiveddetail.isIs_delete());
        values.put(Inventory_receiveddetail.COLUMN_TS,Utility.dateFormat.format(inventory_receiveddetail.getTs()));
        values.put(Inventory_receiveddetail.COLUMN_BIN_ID,inventory_receiveddetail.getBin_id());
        values.put(Inventory_receiveddetail.COLUMN_PALLET_ID,inventory_receiveddetail.getPallet_id());
        values.put(Inventory_receiveddetail.COLUMN_PRODUCT_ID,inventory_receiveddetail.getProduct_id());
        values.put(Inventory_receiveddetail.COLUMN_RECEIVEDHDR_ID,inventory_receiveddetail.getReceivedhdr_id());
        values.put(Inventory_receiveddetail.COLUMN_TOLOCATION_ID,inventory_receiveddetail.getTolocation_id());
        values.put(Inventory_receiveddetail.COLUMN_UOM_ID,inventory_receiveddetail.getUom_id());

        long resultid = database.insert(inventory_receiveddetail.TABLE_INVENTORY_RECEIVEDDETAIL,null,values);
        return resultid;
    }

    public long insertInventory_receivedhdr(Inventory_receivedhdr inventory_receivedhdr){
        ContentValues values = new ContentValues();
        values.put(Inventory_receivedhdr.COLUMN_RECEIVE_NO,inventory_receivedhdr.getReceive_no());
        values.put(Inventory_receivedhdr.COLUMN_RECEIVE_DATE,Utility.dateFormat.format(inventory_receivedhdr.getReceive_date()));
        values.put(Inventory_receivedhdr.COLUMN_IS_COMPLETED,inventory_receivedhdr.getIs_completed());
        values.put(Inventory_receivedhdr.COLUMN_IS_APPROVED,inventory_receivedhdr.getIs_completed());
        values.put(Inventory_receivedhdr.COLUMN_IS_VOID,inventory_receivedhdr.getIs_void());
        values.put(Inventory_receivedhdr.COLUMN_VOID_DATE,Utility.dateFormat.format(inventory_receivedhdr.getVoid_date()));
        values.put(Inventory_receivedhdr.COLUMN_IS_DELETE,inventory_receivedhdr.getIs_delete());
        values.put(Inventory_receivedhdr.COLUMN_TS,Utility.dateFormat.format(inventory_receivedhdr.getTs()));
        values.put(Inventory_receivedhdr.COLUMN_DOCTYPE_ID,inventory_receivedhdr.getDoctype_id());
        values.put(Inventory_receivedhdr.COLUMN_LOCATION_ID,inventory_receivedhdr.getLocation_id());
        values.put(Inventory_receivedhdr.COLUMN_SMITH_ID,inventory_receivedhdr.getSmith_id());
        values.put(Inventory_receivedhdr.COLUMN_STAFF_ID,inventory_receivedhdr.getStaff_id());


        long resultid = database.insert(inventory_receivedhdr.TABLE_INVENTORY_RECEIVEDHDR,null,values);
        return resultid;
    }

    public long insertInventory_prodhdr(Inventory_prodhdr inventory_prodhdr){
        ContentValues values = new ContentValues();
        values.put(Inventory_prodhdr.COLUMN_PROD_NO,inventory_prodhdr.getProd_no());
        values.put(Inventory_prodhdr.COLUMN_PROD_DATE,Utility.dateFormat.format(inventory_prodhdr.getProd_date()));
        values.put(Inventory_prodhdr.COLUMN_VOUCHER_NO,inventory_prodhdr.getVoucher_no());
        values.put(Inventory_prodhdr.COLUMN_IS_DELIVERED,inventory_prodhdr.getIs_delivered());
        values.put(Inventory_prodhdr.COLUMN_IS_CONFIRMED,inventory_prodhdr.getIs_confirmed());
        values.put(Inventory_prodhdr.COLUMN_CONFIRMED_BY,inventory_prodhdr.getConfirmedby());
        values.put(Inventory_prodhdr.COLUMN_SAVE_COUNT,inventory_prodhdr.getSave_count());
        values.put(Inventory_prodhdr.COLUMN_IS_VOID,inventory_prodhdr.getIs_void());
        values.put(Inventory_prodhdr.COLUMN_VOID_DATE,Utility.dateFormat.format(inventory_prodhdr.getVoid_date()));
        values.put(Inventory_prodhdr.COLUMN_TS,Utility.dateFormat.format(inventory_prodhdr.getTs()));
        values.put(Inventory_prodhdr.COLUMN_SMIT_ID,inventory_prodhdr.getSmit_id());
        values.put(Inventory_prodhdr.COLUMN_LOCATION_ID,inventory_prodhdr.getLocation_id());
        values.put(Inventory_prodhdr.COLUMN_STAFF_ID,inventory_prodhdr.getStaff_id());

        long resultid = database.insert(inventory_prodhdr.TABLE_INVENTORY_PRODHDR,null,values);
        return resultid;
    }

    public long insertInventory_proddetail(Inventory_proddetail inventory_proddetail){
        ContentValues values = new ContentValues();

        values.put(inventory_proddetail.COLUMN_ID,inventory_proddetail.getId());
        values.put(inventory_proddetail.COLUMN_QTY,inventory_proddetail.getQty());
        values.put(inventory_proddetail.COLUMN_RECEIVED_QTY,inventory_proddetail.getReceived_qty());
        values.put(inventory_proddetail.COLUMN_WEIGHT,inventory_proddetail.getWeight());
        values.put(inventory_proddetail.COLUMN_K,inventory_proddetail.getK());
        values.put(inventory_proddetail.COLUMN_P,inventory_proddetail.getP());
        values.put(inventory_proddetail.COLUMN_Y,inventory_proddetail.getY());
        values.put(inventory_proddetail.COLUMN_DENSITY,inventory_proddetail.getDensity());
        values.put(inventory_proddetail.COLUMN_REMARKS,inventory_proddetail.getRemarks());
        values.put(inventory_proddetail.COLUMN_IS_DELETE,inventory_proddetail.getIs_delete());
        values.put(inventory_proddetail.COLUMN_PRODHDR_ID,inventory_proddetail.getProdhdr_id());
        values.put(inventory_proddetail.COLUMN_PRODUCT_ID,inventory_proddetail.getProduct_id());
        values.put(inventory_proddetail.COLUMN_TOLOCATION_ID,inventory_proddetail.getTolocation_id());
        values.put(inventory_proddetail.COLUMN_UOM_ID,inventory_proddetail.getUon_id());
        values.put(inventory_proddetail.COLUMN_TS,Utility.dateFormat.format(inventory_proddetail.getTs()));

        long resultid = database.insert(inventory_proddetail.TABLE_INVENTORY_PRODDETAIL,null,values);
        return resultid;
    }

    public boolean deleteAdministrationWsdashboard(String table, String whereArgs) {
        database.delete(table, "id = ?", new String[]{whereArgs});
        return true;
    }

    public List<String> getALLProductList()
    {
        List<String> list = new ArrayList<>();
        String sql = "select designname from inventory_products";
        Cursor cursor = readData(sql, null);


        while (cursor.moveToNext()) {
            list.add(cursor.getString( cursor.getColumnIndex("designname")));
        }

        return list;
    }

    public List<String> getALLGoldtList() {

        List<String> list = new ArrayList<>();
        String sql = "select name from inventory_gold";
        Cursor cursor = readData(sql, null);


        while (cursor.moveToNext()) {
            list.add(cursor.getString( cursor.getColumnIndex("name")));
        }

        return list;
    }

    public List<String> getALLSmithList() {
        List<String> list = new ArrayList<>();

        return list;
    }

    public List<String> getALLLocationList() {
        List<String> list = new ArrayList<>();
        String sql = "select loc_name from administration_locations";
        Cursor cursor = readData(sql, null);


        while (cursor.moveToNext())
        {
            list.add(cursor.getString( cursor.getColumnIndex("loc_name")));
        }
        return list;
    }

    public List<String> getALLContainerList(String location_id) {

        List<String> list = new ArrayList<>();
        String sql = "select bin_name from inventory_bin where location_id = ?";
        Cursor cursor = readData(sql, new String[]{location_id});


        while (cursor.moveToNext())
        {
            list.add(cursor.getString( cursor.getColumnIndex("bin_name")));
        }
        return list;
    }

    public List<String> getALLTrayList() {
        List<String> list = new ArrayList<>();

        return list;
    }

    public String getLocaionId(String locationname) {
        String sql = "select id from administration_locations where loc_name = ?";
        Cursor cursor = readData(sql, new String[]{locationname});

        cursor.moveToNext();
        return cursor.getString( cursor.getColumnIndex("id"));

    }

    public List<String> getALLTrayList(String location_id) {

        List<String> list = new ArrayList<>();
        String sql = "select pallet_name from inventory_pallet where location_id = ?";
        Cursor cursor = readData(sql, new String[]{location_id});


        while (cursor.moveToNext())
        {
            list.add(cursor.getString( cursor.getColumnIndex("pallet_name")));
        }
        return list;
    }

    public Long administration_no_series(AdministratoryNoSerie noSerie) {

        ContentValues values = new ContentValues();
        values.put(AdministratoryNoSerie.COLUMN_ID,noSerie.getId());
        values.put(AdministratoryNoSerie.COLUMN_CLIENT_ID,noSerie.getClient_id());
        values.put(AdministratoryNoSerie.COLUMN_MODULE_NAME,noSerie.getModule_name());
        values.put(AdministratoryNoSerie.COLUMN_PREFIX,noSerie.getPrefix());
        values.put(AdministratoryNoSerie.COLUMN_DIY,noSerie.isDiy());
        values.put(AdministratoryNoSerie.COLUMN_LENGTH,noSerie.getLength());
        values.put(AdministratoryNoSerie.COLUMN_LAST,noSerie.getLast());
        values.put(AdministratoryNoSerie.COLUMN_LAST_DIY,noSerie.getLastDiy());
        values.put(AdministratoryNoSerie.COLUMN_DATE_START,noSerie.getDateStart());
        values.put(AdministratoryNoSerie.COLUMN_DATE_END,noSerie.getDateend());
        values.put(AdministratoryNoSerie.COLUMN_ACTIVE,noSerie.isActive());
        values.put(AdministratoryNoSerie.COLUMN_SOLUTION,noSerie.getSolution());


        return database.insert(noSerie.TABLE_ADMINISTRATORY_NOSERIES,null,values);
    }

    public void insertAdministrationStaffRole(AdministrationStaffRole administrationStaffRole)

    {
        ContentValues values = new ContentValues();
        values.put(AdministrationStaffRole.COLUMN_ID,administrationStaffRole.getId());
        values.put(AdministrationStaffRole.COLUMN_ACTIVE,administrationStaffRole.isActive());
        values.put(AdministrationStaffRole.COLUMN_ROLE,administrationStaffRole.getRole());
        values.put(AdministrationStaffRole.COLUMN_STAFF,administrationStaffRole.getStaff());

         database.insert(AdministrationStaffRole.TABLE_ADMINISTRATION_STAFF_ROLE,null,values);


    }

    public List<WsDashboardModel> getAllChild(int id)
    {
        List<WsDashboardModel> dashboarditems = new ArrayList<>();
        String sql = "select id, title, image, is_folder, actionname, parent_id from administration_wsdashboard where parent_id = "+ id+" order by displayno";

        Cursor cursor = readData(sql,null);

        while (cursor.moveToNext()) {
            WsDashboardModel item = new WsDashboardModel();
            item.setTitle(cursor.getString(cursor.getColumnIndex(item.COLUMN_TITLE)));
            item.setImage(cursor.getString(cursor.getColumnIndex(item.COLUMN_IMAGE)));
            item.setActionname(cursor.getString(cursor.getColumnIndex(item.COLUMN_ACTION_NAME)));
            item.setFolder(cursor.getInt(cursor.getColumnIndex(item.COLUMN_IS_FOLDRE)) > 0);
            item.setId(cursor.getInt(cursor.getColumnIndex(item.COLUMN_ID)));
            item.setParent_id(cursor.getInt(cursor.getColumnIndex(item.COLUMN_PARENT_ID)));
            dashboarditems.add(item);
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return dashboarditems;
    }

    public List<WsDashboardModel> getparient(int latest_parient_id)
    {
        List<WsDashboardModel> dashboarditems = new ArrayList<>();
//        String sql = "select id, title, image, is_folder, actionname from administration_wsdashboard where id = "+ latest_parient_id +" order by displayno";
        String sql = "select t1.* \n" +
                "from administration_wsdashboard t1 inner join administration_wsdashboard t2 \n" +
                "on t2.parent_id = t1.parent_id\n" +
                "where t2.id = " + latest_parient_id;

        Cursor cursor = readData(sql,null);

        while (cursor.moveToNext()) {
            WsDashboardModel item = new WsDashboardModel();
            item.setTitle(cursor.getString(cursor.getColumnIndex(item.COLUMN_TITLE)));
            item.setImage(cursor.getString(cursor.getColumnIndex(item.COLUMN_IMAGE)));
            item.setActionname(cursor.getString(cursor.getColumnIndex(item.COLUMN_ACTION_NAME)));
            item.setFolder(cursor.getInt(cursor.getColumnIndex(item.COLUMN_IS_FOLDRE)) > 0);
            item.setId(cursor.getInt(cursor.getColumnIndex(item.COLUMN_ID)));
            dashboarditems.add(item);
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return dashboarditems;

    }
}
