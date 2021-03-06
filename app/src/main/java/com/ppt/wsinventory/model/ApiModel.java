package com.ppt.wsinventory.model;

/**
 * Created by arkar on 2/1/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiModel {
    public static String GETACTIONLIST = "getActionList";
    public static String GETSENDDATALIST = "getSendDataList";
    public static String DOSYNC = "dosync";
    public static String SENDNEWACTION = "sendnewaction";
    public static String GETTABLESTODELETE = "getTablesToDelete";
    public static String GETTABLESTOCLEAN = "getTablesToClean";
    public static String GETSOLUTIONS = "getSolutions";
    public static String GETSETTINGS = "getSettings";
    public static String GETWSDASHBOARD = "getWsDashboard";
    public static String GETSTAFFLIST = "getStaffList";
    public static String GETROLELIST = "getRoleList";
    public static String GETLOCATIONSLIST = "getLocationsList";
    public static String GETUOMLIST = "getUOMList";
    public static String GETBINLIST = "getBinList";
    public static String SAVEBINLIST = "saveBinList";
    public static String SAVEPALLETLIST = "savePalletList";
    public static String GETWSPALLET = "getPalletList";
    public static String GETWSGOLD = "getGoldList";
    public static String GETGOODSLIST = "getGoodsList";
    public static String GETWSPRODUCT = "getProductsList";
    public static String GETWSGOLDUOM = "getGoldUOMList";
    public static String GETWSGOODSINVENTORYLIST = "getGoodsinventoryList";
    public static String GETWSPRODUCTGROUPLIST = "getProductgroupsList";
    public static String GETWSPRODUCTSERIALLIST = "getProductSerialList";
    public static String GETWSSERIALGOLDSLISTLIST = "getSerialGoldsList";
    public static String GETWSSMITHLIST = "getManufacturingSmithList";
    public static String GETWSSMITHJOBGOLDLIST = "getManufacturingSmithJobGold";
    public static String GETWSSMITHJOBORDERLIST = "getManufacturingSmithJobOrder";
    public static String GETWSSMITHJOBPRODUCTLIST = "getManufacturingSmithJobProduct";
    public static String GETWSSMITHJOBTYPELIST = "getManufacturingSmithJobType";
    public static String GETWSSMITHJOBSTATUSLIST = "getManufacturingJobStatus";
    public static String GETWSSMITHMEMBERSLIST = "getManufacturingSmithmembersList";
    public static String GETWSIMAGESTYPE = "getWsImagesType";
    public static String GETWSIMAGES = "getWsImages";
    public static String GETPRODUCTREDUCE = "getProductreduce";
    public static String GETPRODUCTLENGTH = "getProductLength";
    public static String GETPRODUCTSUBGROUP = "getProductSubGroup";
    public static String GETSTAFFROLE = "getStaffRole";
    public static String GETSYNCHISTORY = "getSynchistory";
    public static String UPDATESYNCHISTORY = "updateSynchistory";

    public static String GETJEWELINVENTORY = "getJewelInventory";
    public static String GETJEWELLENGTH = "getJewellength";
    public static String GETJEWELPURCHASE = "getJewelPurchase";
    public static String GETJEWELPURCHASEITEMS = "getJewelPurchaseItems";
    public static String GETJEWELSHAPE = "GETJEWELSHAPE";
    public static String GETJEWELTYPE = "getJewelType";
    public static String GETSUPPLIER = "getSupplier";
    public static String GETSUPPLIERGROUP = "getSupplierGroup";
    public static String GETRECEIVESERIAL = "getReceiveSerial";
    public static String GETRECEIVEDDETAIL = "getReceivedDetail";
    public static String GETRECEIVEDHDR = "getReceivedHdr";
    public static String GETPRODHDR = "getProdHdr";
    public static String GETPRODDETAIL = "getProdDetail";
    public static String TYPE_GET = "get";
    public static String TYPE_SAVE = "save";
    public static String ADMINISORY_NOSERIES = "getNoSeries";
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public ApiModel(Integer id, String name, String type, String message) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.message = message;
    }

}