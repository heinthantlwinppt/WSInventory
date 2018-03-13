package com.ppt.wsinventory.websocket;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ppt.wsinventory.GlobalVariables;
import com.ppt.wsinventory.common.BusinessLogic;
import com.ppt.wsinventory.common.GlobalBus;
import com.ppt.wsinventory.common.WsEvents;
import com.ppt.wsinventory.model.ActionList;
import com.ppt.wsinventory.model.AdministrationLocations;
import com.ppt.wsinventory.model.AdministrationRole;
import com.ppt.wsinventory.model.AdministrationSettings;
import com.ppt.wsinventory.model.AdministrationSolutions;
import com.ppt.wsinventory.model.AdministrationStaff;
import com.ppt.wsinventory.model.AdministrationStaffRole;
import com.ppt.wsinventory.model.AdministrationWsdashboard;
import com.ppt.wsinventory.model.AdministratoryNoSerie;
import com.ppt.wsinventory.model.ApiModel;
import com.ppt.wsinventory.model.ApiParam;
import com.ppt.wsinventory.model.BIN;
import com.ppt.wsinventory.model.Gold;
import com.ppt.wsinventory.model.GoldUOM;
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
import com.ppt.wsinventory.model.JewelInventory;
import com.ppt.wsinventory.model.JewelPurchase;
import com.ppt.wsinventory.model.JewelPurchaseItems;
import com.ppt.wsinventory.model.JewelShape;
import com.ppt.wsinventory.model.JewelType;
import com.ppt.wsinventory.model.Jewellength;
import com.ppt.wsinventory.model.JobStatus;
import com.ppt.wsinventory.model.Location;
import com.ppt.wsinventory.model.ManufacturingSmith;
import com.ppt.wsinventory.model.Manufacturing_Smith_Jobgold;
import com.ppt.wsinventory.model.Manufacturing_jobstatus;
import com.ppt.wsinventory.model.Manufacturing_smith_joborder;
import com.ppt.wsinventory.model.Manufacturing_smith_jobproduct;
import com.ppt.wsinventory.model.Manufacturing_smith_jobtype;
import com.ppt.wsinventory.model.Manufacturing_smithmembers;
import com.ppt.wsinventory.model.NoSeries;
import com.ppt.wsinventory.model.Pallet;
import com.ppt.wsinventory.model.ProdDetail;
import com.ppt.wsinventory.model.ProdHdr;
import com.ppt.wsinventory.model.Product;
import com.ppt.wsinventory.model.ProductGroup;
import com.ppt.wsinventory.model.ProductLength;
import com.ppt.wsinventory.model.ProductReduce;
import com.ppt.wsinventory.model.ProductSerial;
import com.ppt.wsinventory.model.ProductSubGroup;
import com.ppt.wsinventory.model.ReceiveSerial;
import com.ppt.wsinventory.model.ReceivedDetail;
import com.ppt.wsinventory.model.ReceivedHdr;
import com.ppt.wsinventory.model.Role;
import com.ppt.wsinventory.model.Serialgolds;
import com.ppt.wsinventory.model.Settings;
import com.ppt.wsinventory.model.Smith;
import com.ppt.wsinventory.model.Smith_JobGold;
import com.ppt.wsinventory.model.Smith_joborder;
import com.ppt.wsinventory.model.Smith_jobproduct;
import com.ppt.wsinventory.model.Smith_jobtype;
import com.ppt.wsinventory.model.Smithmembers;
import com.ppt.wsinventory.model.Solution;
import com.ppt.wsinventory.model.Staff;
import com.ppt.wsinventory.model.StaffRole;
import com.ppt.wsinventory.model.Supplier;
import com.ppt.wsinventory.model.SupplierGroup;
import com.ppt.wsinventory.model.TableToDelete;
import com.ppt.wsinventory.model.UOM;
import com.ppt.wsinventory.model.WsDashboard;
import com.ppt.wsinventory.model.WsImages;
import com.ppt.wsinventory.model.WsImagesType;
import com.ppt.wsinventory.model.WsapiSynchistory;
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
import com.ppt.wsinventory.services.WsService;
import com.ppt.wsinventory.services.WsSyncService;
import com.ppt.wsinventory.util.HexStringConverter;
import com.ppt.wsinventory.util.JsonHelper;
import com.ppt.wsinventory.util.Utility;
import com.ppt.wsinventory.wsdb.DbAccess;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 30/12/2017.
 */

public class WsApi {
    //    public final String WEBSOCKET_URL = "ws://52.230.10.246:9090/wsmessage";
    public final String WEBSOCKET_URL = "ws://192.168.1.8:8000/wsmessage";
    private static final String TAG = "Ws-WsApi";
    private Context mContext;
    DbAccess dbaccess;
    private GlobalVariables appContext;
    public static final String SYNCHRONIZATION_COMPLETED = "SYNCHRONIZATION_COMPLETED";
    public static final String SENDDATA_COMPLETED = "SENDDATA_COMPLETED";

    public WsApi(Context mContext) {
        this.mContext = mContext;
        appContext = (GlobalVariables) mContext.getApplicationContext();
    }

    public void setAppContext(GlobalVariables appContext) {
        this.appContext = appContext;
    }

    public WsApi() {
    }

    public Context getmContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public void TestWebSocket() {

        Intent intent = new Intent(mContext, WsSyncService.class);
        intent.putExtra(WsSyncService.SERVICE_TYPE, WsSyncService.SERVICE_LOGIN);
        mContext.startService(intent);
    }

    public void getGoodsID() {

        Intent intent = new Intent(mContext, WsService.class);
        intent.putExtra(WsService.SERVICE_TYPE, WsSyncService.SERVICE_GOODSID);
        mContext.startService(intent);
    }


    public void getActionList() {
        Intent intent = new Intent(mContext, WsSyncService.class);
        intent.putExtra(WsSyncService.SERVICE_TYPE, WsSyncService.SERVICE_REQUEST);
        mContext.startService(intent);
    }
    public void doSendData() {
        Gson gson = JsonHelper.getGson();
        String jsonString = "";
        String response = appContext.getResponseMessage();
        response = HexStringConverter.getHexStringConverterInstance().hexToString(response);
        ApiModel apiModel = gson.fromJson(response, ApiModel.class);
        boolean hasmore = false;
        if (apiModel.getMessage().equalsIgnoreCase("[]"))
            apiModel.setMessage("");
        if (apiModel.getName().equalsIgnoreCase(ApiModel.GETTABLESTODELETE)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<TableToDelete>>() {
                }.getType();
                List<TableToDelete> tableToDeletes = gson.fromJson(jsonString, listType);
                for (TableToDelete toDelete : tableToDeletes) {

//                dbaccess.deleteData(toDelete.getTablename());
                    importTableToDelete(toDelete);
                    Log.i(TAG, "Delete Table: " + toDelete.getTablename());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETTABLESTOCLEAN)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<TableToDelete>>() {
                }.getType();
                List<TableToDelete> tableToDeletes = gson.fromJson(jsonString, listType);
                for (TableToDelete toDelete : tableToDeletes) {

//                dbaccess.deleteData(toDelete.getTablename());
                    importTableToDelete(toDelete);
                    Log.i(TAG, "Delete Table: " + toDelete.getTablename());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        }else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETSYNCHISTORY)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<WsapiSynchistory>>() {
                }.getType();
                List<WsapiSynchistory> solutionList = gson.fromJson(jsonString, listType);
                for (WsapiSynchistory solution : solutionList) {
                    updateTimeStamp(solution.getTablename(), solution.getTimestamp());
                    Log.i(TAG, "TableName : " + solution.getTablename());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.UPDATESYNCHISTORY)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        }else if (apiModel.getName().equalsIgnoreCase(ApiModel.SAVEBINLIST)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<BIN>>() {
                }.getType();
                BusinessLogic businesslogic = new BusinessLogic(appContext);
                List<BIN> binList = gson.fromJson(jsonString, listType);
                for (BIN bin : binList) {
                    Date ts = bin.getTs();

                    updateTimeStamp(InventoryBIN.TABLE_INVENTORY_BIN, ts);
                    appContext.setTs(ts);
                }
            } else {

                RemoveActionList(apiModel.getName());
            }
        }

        if (appContext.getActionLists().size() > 0) {
            ActionList actionList = appContext.getActionLists().get(0);

            ApiModel apimodel;
            if (actionList.getActionname().equalsIgnoreCase(ApiModel.UPDATESYNCHISTORY)) {
                BusinessLogic bl = new BusinessLogic(appContext);
                List<WsapiSynchistory> wsapisynchistorys = bl.getSynchistory();
                jsonString = gson.toJson(wsapisynchistorys);
                apimodel = new ApiModel(1, actionList.getActionname(), ApiModel.TYPE_SAVE, jsonString);
            }else if (actionList.getActionname().equalsIgnoreCase(ApiModel.SAVEBINLIST)) {
                BusinessLogic bl = new BusinessLogic(appContext);
                List<InventoryBIN> inventorybins = bl.getAllBinToSend(false);
                jsonString = gson.toJson(inventorybins);
                apimodel = new ApiModel(1, actionList.getActionname(), ApiModel.TYPE_SAVE, jsonString);
            }
            else {
                List<ApiParam> params = new ArrayList<>();

                params.add(
                        new ApiParam("actionname", actionList.getActionname())
                );
                Date ts = appContext.getTs(); // new GregorianCalendar(2001, 1, 1, 0, 0, 0).getTime();
                appContext.setTs(ts);
                params.add(
                        new ApiParam("solutionname", appContext.getSolutionname())
                );
                params.add(
                        new ApiParam("ts", Utility.dateFormat.format(ts))
                );
                params.add(
                        new ApiParam("deviceid", appContext.getDeviceid())
                );
                if (appContext.isNewUser()) {
                    params.add(
                            new ApiParam("newuser", "True")
                    );

                } else {
                    params.add(
                            new ApiParam("newuser", "False")
                    );
                }

                jsonString = gson.toJson(params);
                Log.i(TAG, "dosync: " + jsonString);
                apimodel = new ApiModel(1, actionList.getActionname(), ApiModel.TYPE_GET, jsonString);
            }
            jsonString = gson.toJson(apimodel);
            String req = "";
            try {
                req = HexStringConverter.getHexStringConverterInstance().stringToHex(jsonString);
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            Log.i(TAG, "dosync: " + req);

//            String req = "7b226964223a312c226e616d65223a22676574416374696f6e4c697374222c2274797065223a22676574222c226d657373616765223a225b7b5c226e616d655c223a5c226e6577757365725c222c5c2276616c75655c223a5c22547275655c227d2c7b5c226e616d655c223a5c22736f6c7574696f6e6e616d655c222c5c2276616c75655c223a5c22574d535c227d5d227d";

            appContext.setRequestMessage(req);
            Intent intent = new Intent(mContext, WsSyncService.class);
            intent.putExtra(WsSyncService.SERVICE_TYPE, WsSyncService.SERVICE_REQUEST);
            mContext.startService(intent);
        } else {
            appContext.setNewUser(false);
            GlobalBus.getBus().post(
                    new WsEvents.EventShowMessage("Send Data",
                            "Send Data Completed!",
                            SENDDATA_COMPLETED,
                            null,
                            "OK"
                    )
            );
        }
    }
    public void doSync() {
        Gson gson = JsonHelper.getGson();
        String jsonString = "";
        String response = appContext.getResponseMessage();
        response = HexStringConverter.getHexStringConverterInstance().hexToString(response);
        ApiModel apiModel = gson.fromJson(response, ApiModel.class);
        boolean hasmore = false;
        if (apiModel.getMessage().equalsIgnoreCase("[]"))
            apiModel.setMessage("");
        if (apiModel.getName().equalsIgnoreCase(ApiModel.GETTABLESTODELETE)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<TableToDelete>>() {
                }.getType();
                List<TableToDelete> tableToDeletes = gson.fromJson(jsonString, listType);
                for (TableToDelete toDelete : tableToDeletes) {

//                dbaccess.deleteData(toDelete.getTablename());
                    importTableToDelete(toDelete);
                    Log.i(TAG, "Delete Table: " + toDelete.getTablename());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETTABLESTOCLEAN)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<TableToDelete>>() {
                }.getType();
                List<TableToDelete> tableToDeletes = gson.fromJson(jsonString, listType);
                for (TableToDelete toDelete : tableToDeletes) {

//                dbaccess.deleteData(toDelete.getTablename());
                    importTableToDelete(toDelete);
                    Log.i(TAG, "Delete Table: " + toDelete.getTablename());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        }else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETSOLUTIONS)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<Solution>>() {
                }.getType();
                List<Solution> solutionList = gson.fromJson(jsonString, listType);
                for (Solution solution : solutionList) {
                    importSolutions(solution);
                    Log.i(TAG, "Solution Name : " + solution.getSolutionName());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETSYNCHISTORY)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<WsapiSynchistory>>() {
                }.getType();
                List<WsapiSynchistory> solutionList = gson.fromJson(jsonString, listType);
                for (WsapiSynchistory solution : solutionList) {
                    updateTimeStamp(solution.getTablename(), solution.getTimestamp());
                    Log.i(TAG, "TableName : " + solution.getTablename());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.UPDATESYNCHISTORY)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETSETTINGS)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<Settings>>() {
                }.getType();
                List<Settings> settingsList = gson.fromJson(jsonString, listType);
                for (Settings settings : settingsList) {
                    importSettings(settings);
                    Log.i(TAG, "setting id : " + settings.getDeviceId());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETWSDASHBOARD)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<WsDashboard>>() {
                }.getType();
                List<WsDashboard> dashboardsList = gson.fromJson(jsonString, listType);
                for (WsDashboard dashboard : dashboardsList) {
                    importWsDashboard(dashboard);
                    Log.i(TAG, "dashboard Name : " + dashboard.getActionname());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETSTAFFLIST)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<Staff>>() {
                }.getType();
                List<Staff> staffsList = gson.fromJson(jsonString, listType);
                for (Staff staff : staffsList) {
                    importStaff(staff);
                    Log.i(TAG, "Staff Name : " + staff.getStaffName());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETROLELIST)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<Role>>() {
                }.getType();
                List<Role> roleList = gson.fromJson(jsonString, listType);
                for (Role role : roleList) {
                    importRole(role);
                    Log.i(TAG, "Role Name : " + role.getRoleName());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETLOCATIONSLIST)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<Location>>() {
                }.getType();
                List<Location> roleList = gson.fromJson(jsonString, listType);
                for (Location location : roleList) {
                    importLocations(location);
                    Log.i(TAG, "Location Name : " + location.getLocName());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETUOMLIST)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<UOM>>() {
                }.getType();
                List<UOM> uomList = gson.fromJson(jsonString, listType);
                for (UOM uom : uomList) {
                    importUOM(uom);
                    Log.i(TAG, "UOM ID : " + uom.getUom());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETWSGOLDUOM)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<GoldUOM>>() {
                }.getType();
                List<GoldUOM> golduomList = gson.fromJson(jsonString, listType);
                for (GoldUOM golduom : golduomList) {
                    importGoldUOM(golduom);
                    Log.i(TAG, "GOLDUOM ID : " + golduom.getUom());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETWSGOLD)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<Gold>>() {
                }.getType();
                List<Gold> goldList = gson.fromJson(jsonString, listType);
                for (Gold gold : goldList) {
                    importGold(gold);
                    Log.i(TAG, "Gold Name : " + gold.getName());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETWSPRODUCT)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<Product>>() {
                }.getType();
                List<Product> productList = gson.fromJson(jsonString, listType);
                for (Product product : productList) {
                    if (importProduct(product)) {
//                        appContext.setTs(product.getTs());
                    } else {
                        break;
                    }
                    Log.i(TAG, "Product Name : " + product.getName());
                }

            } else {

                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETWSSMITHLIST)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<Smith>>() {
                }.getType();
                List<Smith> smithList = gson.fromJson(jsonString, listType);
                for (Smith smith : smithList) {
                    importSmith(smith);
                    Log.i(TAG, "Smith Name : " + smith.getName());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETWSPRODUCTGROUPLIST)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<ProductGroup>>() {
                }.getType();
                List<ProductGroup> productGroupList = gson.fromJson(jsonString, listType);
                for (ProductGroup productGroup : productGroupList) {
                    importProductGroup(productGroup);
                    Log.i(TAG, "Product Name : " + productGroup.getName());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETWSSMITHMEMBERSLIST)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<Smithmembers>>() {
                }.getType();
                List<Smithmembers> smithmembersList = gson.fromJson(jsonString, listType);
                for (Smithmembers smithmembers : smithmembersList) {
                    importSmithMemberList(smithmembers);
                    Log.i(TAG, "Smith Name : " + smithmembers.getName());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETWSSMITHJOBSTATUSLIST)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<JobStatus>>() {
                }.getType();
                List<JobStatus> jobstatusList = gson.fromJson(jsonString, listType);
                for (JobStatus jobstatus : jobstatusList) {
                    importJobStatusList(jobstatus);
                    Log.i(TAG, "JOB Name : " + jobstatus.getName());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETWSSMITHJOBGOLDLIST)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<Smith_JobGold>>() {
                }.getType();
                List<Smith_JobGold> jobstatusList = gson.fromJson(jsonString, listType);
                for (Smith_JobGold smith_jobgold : jobstatusList) {
                    importSmithJobGoldList(smith_jobgold);
                    Log.i(TAG, "Gold ID : " + smith_jobgold.getGold());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETWSSMITHJOBPRODUCTLIST)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<Smith_jobproduct>>() {
                }.getType();
                List<Smith_jobproduct> jobstatusList = gson.fromJson(jsonString, listType);
                for (Smith_jobproduct smith_jobproduct : jobstatusList) {
                    importSmithJobProductList(smith_jobproduct);
                    Log.i(TAG, "Product ID : " + smith_jobproduct.getProducts());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETWSSMITHJOBORDERLIST)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<Smith_joborder>>() {
                }.getType();
                List<Smith_joborder> smith_joborderList = gson.fromJson(jsonString, listType);
                for (Smith_joborder smith_joborder : smith_joborderList) {
                    if (importSmithJobOrderList(smith_joborder)) {
//                        appContext.setTs(smith_joborder.getTs());
                    } else {
                        break;
                    }
                }
            } else {

                RemoveActionList(apiModel.getName());
            }

        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETWSSMITHJOBTYPELIST)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<Smith_jobtype>>() {
                }.getType();
                List<Smith_jobtype> smith_jobtypeList = gson.fromJson(jsonString, listType);
                for (Smith_jobtype smith_jobtype : smith_jobtypeList) {
                    importSmithJobTypeList(smith_jobtype);
                    Log.i(TAG, "Smith Name : " + smith_jobtype.getName());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETBINLIST)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<BIN>>() {
                }.getType();
                List<BIN> binList = gson.fromJson(jsonString, listType);
                for (BIN bin : binList) {
                    if (importBIN(bin)) {
//                        appContext.setTs(bin.getTs());
                    } else {
                        break;
                    }
                }
            } else {

                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETWSGOODSINVENTORYLIST)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<GoodsInventory>>() {
                }.getType();
                List<GoodsInventory> goodsinventoryList = gson.fromJson(jsonString, listType);
                for (GoodsInventory goodsinventory : goodsinventoryList) {
                    if (importGoodsInventory(goodsinventory)) {
//                        appContext.setTs(goodsinventory.getTs());
                    } else {
                        break;
                    }
                }
            } else {

                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETWSPRODUCTSERIALLIST)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<ProductSerial>>() {
                }.getType();
                List<ProductSerial> productserialList = gson.fromJson(jsonString, listType);
                for (ProductSerial productserial : productserialList) {
                    if (importProductSerial(productserial)) {
//                        appContext.setTs(productserial.getTs());
                    } else {
                        break;
                    }
                }
            } else {

                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETWSSERIALGOLDSLISTLIST)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<Serialgolds>>() {
                }.getType();
                List<Serialgolds> serialgoldsList = gson.fromJson(jsonString, listType);
                for (Serialgolds serialgolds : serialgoldsList) {
                    if (importInventory_serialgolds(serialgolds)) {
//                        appContext.setTs(serialgolds.getTs());
                    } else {
                        break;
                    }
                }
            } else {

                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETWSPALLET)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<Pallet>>() {
                }.getType();
                List<Pallet> palletList = gson.fromJson(jsonString, listType);
                for (Pallet pallet : palletList) {
                    if (importPallet(pallet)) {
//                        appContext.setTs(pallet.getTs());
                    } else {
                        break;
                    }
                }
            } else {

                RemoveActionList(apiModel.getName());
            }

        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETWSIMAGESTYPE)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<WsImagesType>>() {
                }.getType();
                List<WsImagesType> wsImagesTypes = gson.fromJson(jsonString, listType);
                for (WsImagesType wsImagesType : wsImagesTypes) {
                    importWsImagesType(wsImagesType);

                    Utility.creatdesignfolder(wsImagesType.getName());

                    //If folder not exist --> Create folder for each type
                    Log.i(TAG, "Solution Name : " + wsImagesType.getName());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }

        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETPRODUCTREDUCE)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<ProductReduce>>() {
                }.getType();
                List<ProductReduce> productReducesList = gson.fromJson(jsonString, listType);
                for (ProductReduce productReduce : productReducesList) {
                    importProductReduce(productReduce);
                    Log.i(TAG, "Solution Name : " + productReduce.getId());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETPRODUCTLENGTH)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<ProductLength>>() {
                }.getType();
                List<ProductLength> productLengths = gson.fromJson(jsonString, listType);
                for (ProductLength productLength : productLengths) {
                    importProductLength(productLength);
                    Log.i(TAG, "Solution Name : " + productLength.getId());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETPRODUCTSUBGROUP)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<ProductSubGroup>>() {
                }.getType();
                List<ProductSubGroup> productSubGroups = gson.fromJson(jsonString, listType);
                for (ProductSubGroup productSubGroup : productSubGroups) {
                    importProductSubGroup(productSubGroup);
                    Log.i(TAG, "Solution Name : " + productSubGroup.getId());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETSTAFFROLE)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<StaffRole>>() {
                }.getType();
                List<StaffRole> staffRoles = gson.fromJson(jsonString, listType);
                for (StaffRole staffRole : staffRoles) {
                    importStaffRole(staffRole);
                    Log.i(TAG, "Solution Name : " + staffRole.getId());
                }
                RemoveActionList(apiModel.getName());
            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETWSIMAGES)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<WsImages>>() {
                }.getType();
                List<WsImages> images = gson.fromJson(jsonString, listType);
                for (WsImages wsImages : images) {

//                    File dir = Utility.creatdesignfolder(wsImages.getType());
//                    File my_file = new File(dir, wsImages.getName() + ".png");
//                    if(my_file.exists()){
//                        my_file.delete();
//                    }
//                    String url = "http://52.230.10.246:8080" + wsImages.getPath();
//                    Picasso.with(mContext)
//                            .load(url)
//                            .into(new PicassoImageTarget(wsImages.getName(), dir));

                    if (importWsImages(wsImages)) {
                        // if delete is true then delete the image file
                        // else save the image to storage depend on their type
                        // import data

                        String urls = "http://52.230.10.246:8080" + wsImages.getPath();

                        File dir = Utility.creatdesignfolder(wsImages.getType());
                        File my_file = new File(dir, wsImages.getId());
                        if (my_file.exists()) {
                            my_file.delete();
                        }

                        if (wsImages.getIsDelete() == false) {

                            try {

                                URL url = new URL(urls);

                                long startTime = System.currentTimeMillis();
                                Log.d("DownloadManager", "download begining");
                                Log.d("DownloadManager", "download url:" + url);
                                Log.d("DownloadManager", "downloaded file name:" + wsImages.getName());

                                try {
                                    URLConnection ucon = url.openConnection();
                                    android.os.StrictMode.ThreadPolicy policy = new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
                                    android.os.StrictMode.setThreadPolicy(policy);
                                    InputStream is = ucon.getInputStream();
//                                BufferedInputStream bis = new BufferedInputStream(is);

//                                ByteArrayBuffer baf = new ByteArrayBuffer(5000);
//                                int current = 0;
//                                while ((current = bis.read()) != -1) {
//                                    baf.append((byte) current);
//                                }

                                    BufferedInputStream bis = new BufferedInputStream(is);
                                    ByteArrayOutputStream buffer = new ByteArrayOutputStream();

                                    byte[] data = new byte[5000];
                                    int current = 0;

                                    while ((current = bis.read(data, 0, data.length)) != -1) {
                                        buffer.write(data, 0, current);
                                    }

                                    FileOutputStream fos = new FileOutputStream(my_file);
                                    fos.write(buffer.toByteArray());
                                    fos.close();
                                    Log.d("DownloadManager", "download ready in" + ((System.currentTimeMillis() - startTime) / 1000) + " sec");


                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                        }

                        appContext.setTs(wsImages.getTimestamp());

                    } else {
                        break;
                    }
                }
            } else {

                RemoveActionList(apiModel.getName());
            }

        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETJEWELINVENTORY)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<JewelInventory>>() {
                }.getType();
                List<JewelInventory> jewelInventories = gson.fromJson(jsonString, listType);
                for (JewelInventory jewelInventory : jewelInventories) {
                    if (importJewelinventory(jewelInventory)) {
//                        appContext.setTs(jewelInventory.getTs());
                    } else {
                        break;
                    }
                    Log.i(TAG, "Jewelly Type : " + jewelInventory.getJeweltype());
                }

            } else {

                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETJEWELLENGTH)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<Jewellength>>() {
                }.getType();
                List<Jewellength> jewellengths = gson.fromJson(jsonString, listType);
                for (Jewellength jewellength : jewellengths) {
                    importJewellength(jewellength);
                    Log.i(TAG, "Jewelly Length Name : " + jewellength.getName());
                }
                RemoveActionList(apiModel.getName());

            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETJEWELPURCHASE)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<JewelPurchase>>() {
                }.getType();
                List<JewelPurchase> jewelPurchases = gson.fromJson(jsonString, listType);
                for (JewelPurchase jewelPurchase : jewelPurchases) {
                    if (importJewelpurchase(jewelPurchase)) {
//                        appContext.setTs(jewelPurchase.getTs());
                    } else {
                        break;
                    }
                    Log.i(TAG, "Purchases Class : " + jewelPurchase.getClass());
                }
                RemoveActionList(apiModel.getName());

            } else {

                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETJEWELPURCHASEITEMS)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<JewelPurchaseItems>>() {
                }.getType();
                List<JewelPurchaseItems> jewelPurchaseItems = gson.fromJson(jsonString, listType);
                for (JewelPurchaseItems purchaseItems : jewelPurchaseItems) {
                    if (importJewelpurchaseitems(purchaseItems)) {
//                        appContext.setTs(purchaseItems.getTs());
                    } else {
                        break;
                    }
                    Log.i(TAG, "JewelPurchaseitem : " + purchaseItems.getJewelPurchase());
                }
                RemoveActionList(apiModel.getName());

            } else {

                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETJEWELSHAPE)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<JewelShape>>() {
                }.getType();
                List<JewelShape> jewelShapes = gson.fromJson(jsonString, listType);
                for (JewelShape shapes : jewelShapes) {
                    importJewelshape(shapes);
                    Log.i(TAG, "JewelShape : " + shapes.getName());
                }
                RemoveActionList(apiModel.getName());

            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETJEWELTYPE)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<JewelType>>() {
                }.getType();
                List<JewelType> jewelTypes = gson.fromJson(jsonString, listType);
                for (JewelType jewelType : jewelTypes) {
                    importJeweltype(jewelType);
                    Log.i(TAG, "JewelType : " + jewelType.getName());
                }
                RemoveActionList(apiModel.getName());

            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETSUPPLIER)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<Supplier>>() {
                }.getType();
                List<Supplier> suppliers = gson.fromJson(jsonString, listType);
                for (Supplier supplier : suppliers) {
                    importSupplier(supplier);
                    Log.i(TAG, "Supplier Name : " + supplier.getName());
                }
                RemoveActionList(apiModel.getName());

            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETSUPPLIERGROUP)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<SupplierGroup>>() {
                }.getType();
                List<SupplierGroup> supplierGroups = gson.fromJson(jsonString, listType);
                for (SupplierGroup supplierGroup : supplierGroups) {
                    importSuppliergroup(supplierGroup);
                    Log.i(TAG, "SupplierGroup Name : " + supplierGroup.getName());
                }
                RemoveActionList(apiModel.getName());

            } else {
                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETRECEIVESERIAL)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<ReceiveSerial>>() {
                }.getType();
                List<ReceiveSerial> receiveSerials = gson.fromJson(jsonString, listType);
                for (ReceiveSerial receiveSerial : receiveSerials) {
                    if (importInventory_receiveserial(receiveSerial)) {
//                        appContext.setTs(receiveSerial.getTs());
                    } else {
                        break;
                    }
                    Log.i(TAG, "Receiveserialno : " + receiveSerial.getProductserial());
                }
                RemoveActionList(apiModel.getName());

            } else {

                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETRECEIVEDDETAIL)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<ReceivedDetail>>() {
                }.getType();
                List<ReceivedDetail> receivedDetails = gson.fromJson(jsonString, listType);
                for (ReceivedDetail receivedDetail : receivedDetails) {
                    if (importInventory_receiveddetail(receivedDetail)) {
//                        appContext.setTs(receivedDetail.getTs());
                    } else {
                        break;
                    }
//                    Log.i(TAG, "Receiveserialno : " + receivedDetail.getProductserial());
                }
                RemoveActionList(apiModel.getName());

            } else {

                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETRECEIVEDHDR)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<ReceivedHdr>>() {
                }.getType();
                List<ReceivedHdr> receivedHdrs = gson.fromJson(jsonString, listType);
                for (ReceivedHdr receivedHdr : receivedHdrs) {
                    if (importInventory_receivedhdr(receivedHdr)) {
//                        appContext.setTs(receivedHdr.getTs());
                    } else {
                        break;
                    }
//                    Log.i(TAG, "Receiveserialno : " + receivedDetail.getProductserial());
                }
                RemoveActionList(apiModel.getName());

            } else {

                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.ADMINISORY_NOSERIES)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<NoSeries>>() {
                }.getType();
                List<NoSeries> noseries = gson.fromJson(jsonString, listType);
                Log.i(TAG, "NO SERIES: " + noseries.size());
//
                for (NoSeries noSeries : noseries) {
                    importInventory_noseries(noSeries);

//                    Log.i(TAG, "Receiveserialno : " + receivedDetail.getProductserial());
                }
                RemoveActionList(apiModel.getName());

            } else {

                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETPRODHDR)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<ProdHdr>>() {
                }.getType();
                List<ProdHdr> prodHdrs = gson.fromJson(jsonString, listType);
                for (ProdHdr prodHdr : prodHdrs) {
                    if (importInventory_prodHdr(prodHdr)) {
//                        appContext.setTs(prodHdr.getTs());
                    } else {
                        break;
                    }
//                    Log.i(TAG, "Receiveserialno : " + receivedDetail.getProductserial());
                }
                RemoveActionList(apiModel.getName());

            } else {

                RemoveActionList(apiModel.getName());
            }
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETPRODDETAIL)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<ProdDetail>>() {
                }.getType();
                List<ProdDetail> prodDetails = gson.fromJson(jsonString, listType);
                for (ProdDetail details : prodDetails) {
                    if (importInventory_proddetail(details)) {
//                        appContext.setTs(details.getTs());
                    } else {
                        break;
                    }
//                    Log.i(TAG, "Receiveserialno : " + receivedDetail.getProductserial());
                }
                RemoveActionList(apiModel.getName());

            } else {

                RemoveActionList(apiModel.getName());
            }
        } else {
            RemoveActionList(apiModel.getName());
            Log.i(TAG, "doSync: arkar");
        }

        if (appContext.getActionLists().size() > 0) {
            ActionList actionList = appContext.getActionLists().get(0);

            ApiModel apimodel;
            if (actionList.getActionname().equalsIgnoreCase(ApiModel.UPDATESYNCHISTORY)) {
                BusinessLogic bl = new BusinessLogic(appContext);
                List<WsapiSynchistory> wsapisynchistorys = bl.getSynchistory();
                jsonString = gson.toJson(wsapisynchistorys);
                apimodel = new ApiModel(1, actionList.getActionname(), ApiModel.TYPE_SAVE, jsonString);
            } else {
                List<ApiParam> params = new ArrayList<>();

                params.add(
                        new ApiParam("actionname", actionList.getActionname())
                );
                Date ts = appContext.getTs(); // new GregorianCalendar(2001, 1, 1, 0, 0, 0).getTime();
                appContext.setTs(ts);
                params.add(
                        new ApiParam("solutionname", appContext.getSolutionname())
                );
                params.add(
                        new ApiParam("ts", Utility.dateFormat.format(ts))
                );
                params.add(
                        new ApiParam("deviceid", appContext.getDeviceid())
                );
                if (appContext.isNewUser()) {
                    params.add(
                            new ApiParam("newuser", "True")
                    );

                } else {
                    params.add(
                            new ApiParam("newuser", "False")
                    );
                }

                jsonString = gson.toJson(params);
                Log.i(TAG, "dosync: " + jsonString);
                apimodel = new ApiModel(1, actionList.getActionname(), ApiModel.TYPE_GET, jsonString);
            }
            jsonString = gson.toJson(apimodel);
            String req = "";
            try {
                req = HexStringConverter.getHexStringConverterInstance().stringToHex(jsonString);
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            Log.i(TAG, "dosync: " + req);

//            String req = "7b226964223a312c226e616d65223a22676574416374696f6e4c697374222c2274797065223a22676574222c226d657373616765223a225b7b5c226e616d655c223a5c226e6577757365725c222c5c2276616c75655c223a5c22547275655c227d2c7b5c226e616d655c223a5c22736f6c7574696f6e6e616d655c222c5c2276616c75655c223a5c22574d535c227d5d227d";

            appContext.setRequestMessage(req);
            Intent intent = new Intent(mContext, WsSyncService.class);
            intent.putExtra(WsSyncService.SERVICE_TYPE, WsSyncService.SERVICE_REQUEST);
            mContext.startService(intent);
        } else {
            appContext.setNewUser(false);
            GlobalBus.getBus().post(
                    new WsEvents.EventShowMessage("Synchronization",
                            "Synchronization Completed!",
                            SYNCHRONIZATION_COMPLETED,
                            null,
                            "OK"
                    )
            );
        }

    }

    private void importStaffRole(StaffRole staffRole) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        if (!appContext.isNewUser()) {
            dbaccess.deleteData(AdministrationStaffRole.TABLE_ADMINISTRATION_STAFF_ROLE,
                    AdministrationStaffRole.COLUMN_ID + " = ? ", new String[]{staffRole.getId()}
            );
        }
        AdministrationStaffRole administrationStaffRole = new AdministrationStaffRole();
        administrationStaffRole.setId(staffRole.getId());
        administrationStaffRole.setActive(staffRole.getActive());
        administrationStaffRole.setRole(staffRole.getRole());
        dbaccess.insertAdministrationStaffRole(administrationStaffRole);
        Date ts = new Date(System.currentTimeMillis());
        updateTimeStamp(AdministrationStaffRole.TABLE_ADMINISTRATION_STAFF_ROLE, ts);
        appContext.setTs(ts);
    }

    private boolean updateTimeStamp(String tablename, Date ts) {
        BusinessLogic businesslogic = new BusinessLogic(appContext);
        WsapiSynchistory wsapisynchistory = new WsapiSynchistory();
        wsapisynchistory.setTablename(tablename);
        wsapisynchistory.setTimestamp(ts);
        wsapisynchistory.setDevice_id(appContext.getDeviceid());
        businesslogic.deleteSyncHistory(wsapisynchistory);
        return businesslogic.insertSyncHistory(wsapisynchistory);
    }

    private boolean importInventory_noseries(NoSeries noSeries) {


        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        if (!appContext.isNewUser()) {
            dbaccess.deleteData(AdministratoryNoSerie.TABLE_ADMINISTRATORY_NOSERIES,
                    AdministratoryNoSerie.COLUMN_ID + " = ? And " + AdministratoryNoSerie.COLUMN_CLIENT_ID + " = ?", new String[]{noSeries.getId(), noSeries.getClientId()}
            );
        }

        AdministratoryNoSerie noSerie = new AdministratoryNoSerie();
        noSerie.setId(noSeries.getId());
        noSerie.setClient_id(noSeries.getClientId());
        noSerie.setModule_name(noSeries.getModuleName());
        noSerie.setPrefix(noSeries.getPrefix());
        noSerie.setDiy(noSeries.getDiy());
        noSerie.setLength(noSeries.getLength());
        noSerie.setLast(noSeries.getLast());
        noSerie.setLastDiy(noSeries.getLastDiy());
        noSerie.setDateStart(noSeries.getDateStart());
        noSerie.setDateend(noSeries.getDateEnd());
        noSerie.setActive(noSeries.getActive());
        noSerie.setSolution(noSeries.getSolution());

        Long l = dbaccess.administration_no_series(noSerie);

        if (l > 0) {
            Date ts = new Date(System.currentTimeMillis());
            updateTimeStamp(noSerie.TABLE_ADMINISTRATORY_NOSERIES, ts);
            appContext.setTs(ts);
        }
        return (l > 0);


    }


    public void RemoveActionList(String actionname) {
        for (ActionList actionList : appContext.getActionLists()) {
            if (actionList.getActionname().equalsIgnoreCase(actionname)) {
                appContext.getActionLists().remove(actionList);
                break;
            }
        }
        if (appContext.isNewUser()) {
            appContext.setTs(Utility.getDateBegin());
        } else {
            if (appContext.getActionLists().size() > 0) {
                ActionList actionList = appContext.getActionLists().get(0);
                BusinessLogic businesslogic = new BusinessLogic(appContext);
                WsapiSynchistory wsapisynchistory = businesslogic.getSynchistoryByTableName(actionList.getTablename());
                Date ts = Utility.getDateBegin();
//                Date ts =  new Date(System.currentTimeMillis());
                if (wsapisynchistory != null) {
                    ts = wsapisynchistory.getTimestamp();
                } else {
                    wsapisynchistory = new WsapiSynchistory();
                    wsapisynchistory.setDevice_id(appContext.getWssetting().getDevice_id());
                    wsapisynchistory.setTimestamp(ts);
                    wsapisynchistory.setTablename(actionList.getTablename());
                    businesslogic.insertSyncHistory(wsapisynchistory);
                }
                appContext.setTs(ts);

            }
        }


    }

    private void importTableToDelete(TableToDelete tableList) {

        dbaccess = DbAccess.getInstance();
        dbaccess.open();
        try {
            dbaccess.deleteData(tableList.getTablename());
        } catch (Exception e) {
            Log.i(TAG, "importTableToDelete: " + tableList.getTablename());
        }
    }

    private void importSolutions(Solution solution) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        if (!appContext.isNewUser()) {
            dbaccess.deleteData(AdministrationSolutions.TABLE_ADMINISTRATION_SOLUTIONS,
                    AdministrationSolutions.COLUMN_SOLUTION_NAME + " = ? ", new String[]{solution.getSolutionName()}
            );
        }
        AdministrationSolutions administrationSolutions = new AdministrationSolutions();
        administrationSolutions.setSolution_name(solution.getSolutionName());
        administrationSolutions.setActive(solution.getActive());
        dbaccess.insertAdministrationSolutions(administrationSolutions);
        Date ts = new Date(System.currentTimeMillis());
        updateTimeStamp(AdministrationSolutions.TABLE_ADMINISTRATION_SOLUTIONS, ts);
        appContext.setTs(ts);
//        dbaccess.close();

    }

    private void importSettings(Settings settings) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        if (!appContext.isNewUser()) {
            dbaccess.deleteData(AdministrationSettings.TABLE_ADMINISTRATION_SETTINGS,
                    AdministrationSettings.COLUMN_ID + " = ? ", new String[]{settings.getId()}
            );
        }
        AdministrationSettings administrationSettings = new AdministrationSettings();
        administrationSettings.setId(settings.getId());
        administrationSettings.setDevice_id(settings.getDeviceId());
        administrationSettings.setH1(String.valueOf(settings.getH1()));
        administrationSettings.setH2(String.valueOf(settings.getH2()));
        administrationSettings.setH3(String.valueOf(settings.getH3()));
        administrationSettings.setH4(String.valueOf(settings.getH4()));
        administrationSettings.setT1(String.valueOf(settings.getT1()));
        administrationSettings.setT2(String.valueOf(settings.getT2()));
        administrationSettings.setT3(String.valueOf(settings.getT3()));
        administrationSettings.setT4(String.valueOf(settings.getT4()));
        administrationSettings.setDateformat(String.valueOf(settings.getDateformat()));
        administrationSettings.setTimeformat(String.valueOf(settings.getTimeformat()));
        administrationSettings.setDatetimeformat(String.valueOf(settings.getDatetimeformat()));
        administrationSettings.setSystem_date(settings.getSystemDate());
        administrationSettings.setDashboarditempwith((Integer) settings.getDashboarditempwith());
        administrationSettings.setDashboarditemlwith((Integer) settings.getDashboarditemlwith());
        administrationSettings.setDashboardicon(String.valueOf(settings.getDashboardicon()));
        administrationSettings.setActive(settings.getActive());
        administrationSettings.setSolution_id(settings.getSolution());
        administrationSettings.setLocation_id(settings.getLocation());
        administrationSettings.setDevicetype_id(settings.getDevicetype());
        administrationSettings.setRfidreader_mac(settings.getRfidreader_mac());
        administrationSettings.setRfidreader_name(settings.getRfidreader_name());
        dbaccess.insertSettings(administrationSettings);

        Date ts = new Date(System.currentTimeMillis());
        updateTimeStamp(AdministrationStaffRole.TABLE_ADMINISTRATION_STAFF_ROLE, ts);
        appContext.setTs(ts);

    }

    private void importWsDashboard(WsDashboard wsDashboard) {
        dbaccess = DbAccess.getInstance();
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        if (!appContext.isNewUser()) {
            dbaccess.deleteData(AdministrationWsdashboard.TABLE_ADMINISTRATION_WSDASHBOARD,
                    AdministrationWsdashboard.COLUMN_ID + " = ? ", new String[]{wsDashboard.getId()}
            );
        }
        AdministrationWsdashboard administrationWsdashboard = new AdministrationWsdashboard();
        administrationWsdashboard.setId(wsDashboard.getId());
        administrationWsdashboard.setTitle(wsDashboard.getTitle());
        administrationWsdashboard.setIs_folder(wsDashboard.getIsFolder());
        administrationWsdashboard.setParent_id(wsDashboard.getParentId());
        administrationWsdashboard.setActionname(wsDashboard.getActionname());
        administrationWsdashboard.setGroupname(wsDashboard.getGroupname());
        administrationWsdashboard.setImage(wsDashboard.getImage());
        administrationWsdashboard.setTimestamp(wsDashboard.getTimestamp());
        administrationWsdashboard.setDisplayno(wsDashboard.getDisplayno());
        administrationWsdashboard.setIs_delete(wsDashboard.getIsDelete());
        administrationWsdashboard.setScreen_id(wsDashboard.getScreen());

        if (wsDashboard.getIsDelete() == true) {
            dbaccess.deleteAdministrationWsdashboard(AdministrationWsdashboard.TABLE_ADMINISTRATION_WSDASHBOARD, wsDashboard.getId());
        } else {
            dbaccess.insertAdministrationWsdashboard(administrationWsdashboard);
        }
        Date ts = wsDashboard.getTimestamp();
        updateTimeStamp(AdministrationWsdashboard.TABLE_ADMINISTRATION_WSDASHBOARD, ts);
        appContext.setTs(ts);
    }

    private void importStaff(Staff wsStaff) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        if (!appContext.isNewUser()) {
            dbaccess.deleteData(AdministrationStaff.TABLE_ADMINISTRATION_STAFF,
                    AdministrationStaff.COLUMN_ID + " = ? ", new String[]{wsStaff.getId()}
            );
        }
        AdministrationStaff administrationStaff = new AdministrationStaff();
        administrationStaff.setId(wsStaff.getId());
        administrationStaff.setStaff_name(wsStaff.getStaffName());
        administrationStaff.setFather_name(wsStaff.getFatherName());
        administrationStaff.setNick_name(wsStaff.getNickName());
        administrationStaff.setNrc_no(wsStaff.getNrcNo());
        administrationStaff.setAddress(wsStaff.getAddress());
        administrationStaff.setPhone_no(wsStaff.getPhoneNo());
        administrationStaff.setHome_phone_no(wsStaff.getHomePhoneNo());
        administrationStaff.setStaff_photo(wsStaff.getStaffPhoto());
        administrationStaff.setDate_joined(wsStaff.getDateJoined());
        administrationStaff.setDate_left(wsStaff.getDateLeft());
        administrationStaff.setUser_id(wsStaff.getUserId());
        administrationStaff.setPassword(wsStaff.getPassword());
        administrationStaff.setActive(wsStaff.getActive());
        administrationStaff.setRole(wsStaff.getRole());
        dbaccess.insertAdministrationStaff(administrationStaff);

        Date ts = new Date(System.currentTimeMillis());
        updateTimeStamp(AdministrationStaff.TABLE_ADMINISTRATION_STAFF, ts);
        appContext.setTs(ts);

    }

    private void importLocations(Location wsLocation) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        if (!appContext.isNewUser()) {
            dbaccess.deleteData(AdministrationLocations.TABLE_ADMINISTRATION_LOCATIONS,
                    AdministrationLocations.COLUMN_ID + " = ? ", new String[]{wsLocation.getId()}
            );
        }
        AdministrationLocations administrationLocations = new AdministrationLocations();
        administrationLocations.setId(wsLocation.getId());
        administrationLocations.setLoc_name(wsLocation.getLocName());
        administrationLocations.setLoc_addr(wsLocation.getLocAddr());
        administrationLocations.setLatitude(wsLocation.getLatitude());
        administrationLocations.setLongitude(wsLocation.getLongitude());
        administrationLocations.setReceiving_bin(wsLocation.getReceivingBin());
        administrationLocations.setActive(wsLocation.getActive());
        dbaccess.insertAdministrationLocation(administrationLocations);
        Date ts = new Date(System.currentTimeMillis());
        updateTimeStamp(AdministrationLocations.TABLE_ADMINISTRATION_LOCATIONS, ts);
        appContext.setTs(ts);

    }

    private void importRole(Role wsRole) {
        dbaccess = DbAccess.getInstance();
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        if (!appContext.isNewUser()) {
            dbaccess.deleteData(AdministrationStaffRole.TABLE_ADMINISTRATION_STAFF_ROLE,
                    AdministrationStaffRole.COLUMN_ID + " = ? ", new String[]{wsRole.getId()}
            );
        }
        AdministrationRole administrationRole = new AdministrationRole();
        administrationRole.setId(wsRole.getId());
        administrationRole.setRole_name(wsRole.getRoleName());
        administrationRole.setActive(wsRole.getActive());
        dbaccess.insertAdministrationRole(administrationRole);
        Date ts = new Date(System.currentTimeMillis());
        updateTimeStamp(AdministrationStaffRole.TABLE_ADMINISTRATION_STAFF_ROLE, ts);
        appContext.setTs(ts);

    }

    private void importUOM(UOM wsUOM) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        if (!appContext.isNewUser()) {
            dbaccess.deleteData(InventoryUOM.TABLE_INVENTROY_UOM,
                    InventoryUOM.COLUMN_PRODUCT_ID + " = ? and " + InventoryUOM.COLUMN_UOM + " = ?", new String[]{wsUOM.getProduct(), wsUOM.getUom()}
            );
        }
        InventoryUOM inventoryUOM = new InventoryUOM();
        inventoryUOM.setUom(wsUOM.getUom());
        inventoryUOM.setBaseqty(Double.parseDouble(wsUOM.getBaseqty()));
        inventoryUOM.setProduct_id(wsUOM.getProduct());
        inventoryUOM.setActive(wsUOM.getActive());
        dbaccess.insertInventoryUOM(inventoryUOM);
        Date ts = new Date(System.currentTimeMillis());
        updateTimeStamp(InventoryUOM.TABLE_INVENTROY_UOM, ts);
        appContext.setTs(ts);
    }

    private void importGoldUOM(GoldUOM wsGoldUOM) {
        dbaccess = DbAccess.getInstance();
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        if (!appContext.isNewUser()) {
            dbaccess.deleteData(InventoryGoldUOM.TABLE_INVENTROY_GOLDUOM,
                    InventoryGoldUOM.COLUMN_GOLD_ID + " = ? and " + InventoryGoldUOM.COLUMN_UOM + " = ?", new String[]{String.valueOf(wsGoldUOM.getGold()), wsGoldUOM.getUom()}
            );
        }
        InventoryGoldUOM inventoryGoldUOM = new InventoryGoldUOM();
        inventoryGoldUOM.setUom(wsGoldUOM.getUom());
        inventoryGoldUOM.setBaseqty(Double.parseDouble(wsGoldUOM.getBaseqty()));
        inventoryGoldUOM.setGold_id(wsGoldUOM.getGold());
        inventoryGoldUOM.setActive(wsGoldUOM.getActive());
        dbaccess.insertInventoryGoldUOM(inventoryGoldUOM);
        Date ts = new Date(System.currentTimeMillis());
        updateTimeStamp(inventoryGoldUOM.TABLE_INVENTROY_GOLDUOM, ts);
        appContext.setTs(ts);

    }

    private void importGold(Gold wsGold) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        if (!appContext.isNewUser()) {
            dbaccess.deleteData(InventoryGold.TABLE_INVENTORY_GOLD,
                    InventoryGold.COLUMN_ID + " = ? ", new String[]{String.valueOf(wsGold.getId())}
            );
        }
        InventoryGold inventoryGold = new InventoryGold();
        inventoryGold.setId(wsGold.getId());
        inventoryGold.setCode(wsGold.getCode());
        inventoryGold.setName(wsGold.getName());
        inventoryGold.setGoldtype(wsGold.getGoldtype());
        inventoryGold.setSaleprice(Double.parseDouble(wsGold.getSaleprice()));
        inventoryGold.setPurchaseprice(Double.parseDouble(wsGold.getPurchaseprice()));
        inventoryGold.setCost(Double.parseDouble(wsGold.getCost()));
        inventoryGold.setTax1(Double.parseDouble(wsGold.getTax1() == null ? "0.00" : wsGold.getTax1()));
        inventoryGold.setTax2(Double.parseDouble(wsGold.getTax2() == null ? "0.00" : wsGold.getTax2()));
        inventoryGold.setActive(wsGold.getActive());
        dbaccess.insertInventoryGold(inventoryGold);
        Date ts = new Date(System.currentTimeMillis());
        updateTimeStamp(InventoryGold.TABLE_INVENTORY_GOLD, ts);
        appContext.setTs(ts);
    }

    private boolean importProduct(Product wsProduct) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        if (!appContext.isNewUser()) {
            dbaccess.deleteData(Inventory_products.TABLE_INVENTORY_PRODUCTS,
                    Inventory_products.COLUMN_ID + " = ? ", new String[]{wsProduct.getId()}
            );
        }
        Inventory_products inventory_products = new Inventory_products();
        inventory_products.setId(wsProduct.getId());
        inventory_products.setName(wsProduct.getName());
        inventory_products.setDesignname(wsProduct.getDesignname());
        inventory_products.setPhoto(wsProduct.getPhoto());
        inventory_products.setBarcode(wsProduct.getBarcode());
        inventory_products.setBaseuom(wsProduct.getBaseuom());
        inventory_products.setMinqty(wsProduct.getMinqty());
        inventory_products.setMaxqty(wsProduct.getMaxqty());
        inventory_products.setTag(wsProduct.getTag());
        inventory_products.setIs_delete(wsProduct.getIsDelete());
        inventory_products.setActive(wsProduct.getActive());
        inventory_products.setPgroup_id((wsProduct.getPgroup() == null) ? 0 : wsProduct.getPgroup());
        inventory_products.setRow_no(wsProduct.getRowNo());
        inventory_products.setPlength_id((wsProduct.getPlength() == null) ? 0 : wsProduct.getPlength());
//        inventory_products.setPreduce_id(wsProduct.getPreduce());
        inventory_products.setPsubgroup_id((wsProduct.getPsubgroup() == null) ? 0 : wsProduct.getPsubgroup());
        inventory_products.setTs(wsProduct.getTs());
        inventory_products.setDiamond(wsProduct.getDiamond());
        inventory_products.setDiamondcaratweight((wsProduct.getDiamondcaratweight() == null) ? 0.00 : wsProduct.getDiamondcaratweight());
        inventory_products.setDiamondclarity((wsProduct.getDiamondclarity() == null) ? 0.00 : wsProduct.getDiamondclarity());
        inventory_products.setDiamondmeasurement((wsProduct.getDiamondmeasurement() == null) ? 0.00 : wsProduct.getDiamondmeasurement());
        inventory_products.setDiamondprice((wsProduct.getDiamondprice() == null) ? 0.00 : wsProduct.getDiamondprice());
        inventory_products.setJewelcost((wsProduct.getJewelcost() == null) ? 0.00 : wsProduct.getJewelcost());
        inventory_products.setJewellength_id((wsProduct.getJewellength() == null) ? 0 : wsProduct.getJewellength());
        inventory_products.setJewelprice((wsProduct.getJewelprice() == null) ? 0.00 : wsProduct.getJewelprice());
        inventory_products.setJewelshape_id((wsProduct.getJewelshape() == null) ? 0 : wsProduct.getJewelshape());
        inventory_products.setJeweltype_id((wsProduct.getJeweltype() == null) ? 0 : wsProduct.getJeweltype());
        inventory_products.setLabourcharge((wsProduct.getLabourcharge() == null) ? 0.00 : wsProduct.getLabourcharge());
        inventory_products.setRemarks(wsProduct.getRemarks());
        inventory_products.setSellinglabourcharge((wsProduct.getSellinglabourcharge() == null) ? 0.00 : wsProduct.getSellinglabourcharge());
        inventory_products.setDiamondcolor_id((wsProduct.getDiamondcolor() == null) ? 0 : wsProduct.getDiamondcolor());
        inventory_products.setDiamondshape_id((wsProduct.getDiamondshape() == null) ? 0 : wsProduct.getDiamondshape());
        inventory_products.setDiamondtype_id((wsProduct.getDiamondtype() == null) ? 0 : wsProduct.getDiamondtype());
        boolean b = false;
        if (wsProduct.getIsDelete() == true) {
            b = dbaccess.deleteData(Inventory_products.TABLE_INVENTORY_PRODUCTS, Inventory_products.COLUMN_ID + "=?", new String[]{wsProduct.getId()});

        } else {
            long l = dbaccess.insertInventory_products(inventory_products);
            b = (l > 0);
        }
        Date ts = wsProduct.getTs();
        updateTimeStamp(Inventory_products.TABLE_INVENTORY_PRODUCTS, ts);
        appContext.setTs(ts);
        return b;
//        long l = dbaccess.insertInventory_products(inventory_products);
//        return (l > 0);

    }

    private void importProductGroup(ProductGroup wsProductGroup) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        if (!appContext.isNewUser()) {
            dbaccess.deleteData(InventoryProductGroup.TABLE_PRODUCTGROUP,
                    InventoryProductGroup.COLUMN_ID + " = ? ", new String[]{String.valueOf(wsProductGroup.getId())}
            );
        }
        InventoryProductGroup inventoryProductGroup = new InventoryProductGroup();
        inventoryProductGroup.setId(wsProductGroup.getId());
        inventoryProductGroup.setName(wsProductGroup.getName());
        inventoryProductGroup.setActive(wsProductGroup.getActive());
        dbaccess.insertInventoryProductGroup(inventoryProductGroup);
        Date ts = new Date(System.currentTimeMillis());
        updateTimeStamp(InventoryProductGroup.TABLE_PRODUCTGROUP, ts);
        appContext.setTs(ts);
    }

    private boolean importBIN(BIN wsBIN) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        if (!appContext.isNewUser()) {
            dbaccess.deleteData(InventoryBIN.TABLE_INVENTORY_BIN,
                    InventoryBIN.COLUMN_ID + " = ? ", new String[]{wsBIN.getId()}
            );
        }
        InventoryBIN inventoryBIN = new InventoryBIN();
        inventoryBIN.setId(wsBIN.getId());
        inventoryBIN.setBin_name(wsBIN.getBinName());
        inventoryBIN.setBin_description(wsBIN.getBinDescription());
        inventoryBIN.setBin_type(wsBIN.getBinType());
        inventoryBIN.setBarcode(wsBIN.getBarcode());
        inventoryBIN.setTag(wsBIN.getTag());
        inventoryBIN.setLocation_id(wsBIN.getLocation());
        inventoryBIN.setActive(wsBIN.getActive());
        inventoryBIN.setTs(wsBIN.getTs());
        inventoryBIN.setUploaded(true);
        inventoryBIN.setNo_of_pallets(wsBIN.getNo_of_pallets());

        long l = dbaccess.insertInventoryBIN(inventoryBIN);
        Date ts = wsBIN.getTs();
        updateTimeStamp(InventoryBIN.TABLE_INVENTORY_BIN, ts);
        appContext.setTs(ts);

        return (l > 0);

    }

    private boolean importWsSyncHistory(WsapiSynchistory wsBIN) {
        dbaccess = DbAccess.getInstance();
        WsapiSynchistory inventoryBIN = new WsapiSynchistory();
        inventoryBIN.setDevice_id(wsBIN.getDevice_id());
        inventoryBIN.setTimestamp(wsBIN.getTimestamp());

        long l = dbaccess.insertWsapiSynchistory(inventoryBIN);
        return (l > 0);

    }

    private boolean importProductSerial(ProductSerial wsProductSerial) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        if (!appContext.isNewUser()) {
            dbaccess.deleteData(Inventory_productserial.TABLE_INVENTORY_PRODUCTSERIAL,
                    Inventory_productserial.COLUMN_SERIAL_NO + " = ? ", new String[]{wsProductSerial.getSerialNo()}
            );
        }
        Inventory_productserial inventory_productserial = new Inventory_productserial();
        inventory_productserial.setSerial_no(wsProductSerial.getSerialNo());
        inventory_productserial.setName(wsProductSerial.getName());
        inventory_productserial.setSerial_date(wsProductSerial.getSerialDate());
        inventory_productserial.setPlength(wsProductSerial.getPlength());
        inventory_productserial.setPhoto(wsProductSerial.getPhoto());
        inventory_productserial.setBarcode(wsProductSerial.getBarcode());
        inventory_productserial.setTag(wsProductSerial.getTag());
        inventory_productserial.setWeight(Double.parseDouble(wsProductSerial.getWeight()));
        inventory_productserial.setK(wsProductSerial.getK());
        inventory_productserial.setP(wsProductSerial.getP());
        inventory_productserial.setY(Double.parseDouble(wsProductSerial.getY()));
        inventory_productserial.setReduce_weight(Double.parseDouble(wsProductSerial.getReduceWeight()));
        inventory_productserial.setReduce_k(wsProductSerial.getReduceK());
        inventory_productserial.setReduce_p(wsProductSerial.getReduceP());
        inventory_productserial.setReduce_y(Double.parseDouble(wsProductSerial.getReduceY()));
        inventory_productserial.setJewel_weight(Double.parseDouble(wsProductSerial.getJewelWeight()));
        inventory_productserial.setJewel_k(wsProductSerial.getJewelK());
        inventory_productserial.setJewel_p(wsProductSerial.getJewelP());
        inventory_productserial.setJewel_y(Double.parseDouble(wsProductSerial.getJewelY()));
        inventory_productserial.setJewel_fee(Double.parseDouble(wsProductSerial.getJewelFee()));
        inventory_productserial.setProduction_fee(Double.parseDouble(wsProductSerial.getProductionFee()));
        inventory_productserial.setJewel_name1(wsProductSerial.getJewelName1());
        inventory_productserial.setJewel_name2(wsProductSerial.getJewelName2());
        inventory_productserial.setJewel_name3(wsProductSerial.getJewelName3());
        inventory_productserial.setJewel_name4(wsProductSerial.getJewelName4());
        inventory_productserial.setJewel_name5(wsProductSerial.getJewelName5());
        inventory_productserial.setJewel_weight1(Double.parseDouble(wsProductSerial.getJewelWeight1()));
        inventory_productserial.setJewel_weight2(Double.parseDouble(wsProductSerial.getJewelWeight2()));
        inventory_productserial.setJewel_weight3(Double.parseDouble(wsProductSerial.getJewelWeight3()));
        inventory_productserial.setJewel_weight4(Double.parseDouble(wsProductSerial.getJewelWeight4()));
        inventory_productserial.setJewel_weight5(Double.parseDouble(wsProductSerial.getJewelWeight5()));
        inventory_productserial.setRemarks(wsProductSerial.getRemarks());
        inventory_productserial.setIs_delete(wsProductSerial.getIsDelete());
        inventory_productserial.setTs(wsProductSerial.getTs());
        inventory_productserial.setBin_id(wsProductSerial.getBin());
        inventory_productserial.setGold_id(wsProductSerial.getGold());
        inventory_productserial.setLocation_id(wsProductSerial.getLocation());
        inventory_productserial.setPallet_id(wsProductSerial.getPallet());
        inventory_productserial.setProduct_id(wsProductSerial.getProduct());
        inventory_productserial.setSmith_id(wsProductSerial.getSmith());
        inventory_productserial.setStaff_id(wsProductSerial.getStaff());
        inventory_productserial.setUom_id(wsProductSerial.getUom());
        inventory_productserial.setDelivered(wsProductSerial.getDelivered());
        inventory_productserial.setGoodsid(wsProductSerial.getGoodsid());
        boolean b = false;
        if (wsProductSerial.getIsDelete() == true) {
            b = dbaccess.deleteData(Inventory_productserial.TABLE_INVENTORY_PRODUCTSERIAL,
                    Inventory_productserial.COLUMN_SERIAL_NO + "=?",
                    new String[]{wsProductSerial.getSerialNo()});

        } else {
            long l = dbaccess.insertInventory_productserial(inventory_productserial);
            b = (l > 0);
        }
        Date ts = wsProductSerial.getTs();
        updateTimeStamp(Inventory_productserial.TABLE_INVENTORY_PRODUCTSERIAL, ts);
        appContext.setTs(ts);
        return b;
//        long l = dbaccess.insertInventory_productserial(inventory_productserial);
//        return (l > 0);

    }

    private boolean importInventory_serialgolds(Serialgolds wsSerialgolds) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        if (!appContext.isNewUser()) {
            dbaccess.deleteData(Inventory_serialgolds.TABLE_INVENTORY_SERIALGOLDS,
                    Inventory_serialgolds.COLUMN_SERIAL_ID + " = ? and " + Inventory_serialgolds.COLUMN_GOLD_ID + "=?", new String[]{wsSerialgolds.getSerial(), String.valueOf(wsSerialgolds.getGold())}
            );
        }
        Inventory_serialgolds inventory_serialgolds = new Inventory_serialgolds();
        inventory_serialgolds.setSerial_id(wsSerialgolds.getSerial());
        inventory_serialgolds.setReduce_weight(Double.parseDouble(wsSerialgolds.getReduceWeight()));
        inventory_serialgolds.setReduce_k(wsSerialgolds.getReduceK());
        inventory_serialgolds.setReduce_p(wsSerialgolds.getReduceP());
        inventory_serialgolds.setReduce_y(Double.parseDouble(wsSerialgolds.getReduceY()));
        inventory_serialgolds.setProduction_fee(Double.parseDouble(wsSerialgolds.getProductionFee()));
        inventory_serialgolds.setCost_reduce_weight(Double.parseDouble(wsSerialgolds.getReduceWeight()));
        inventory_serialgolds.setCost_reduce_k(wsSerialgolds.getReduceK());
        inventory_serialgolds.setCost_reduce_p(wsSerialgolds.getReduceP());
        inventory_serialgolds.setCost_reduce_y(Double.parseDouble(wsSerialgolds.getReduceY()));
        inventory_serialgolds.setCost_productionfee(Double.parseDouble(wsSerialgolds.getProductionFee()));
        inventory_serialgolds.setIs_delete(wsSerialgolds.getIsDelete());
        inventory_serialgolds.setGold_id(wsSerialgolds.getGold());
        inventory_serialgolds.setTs(wsSerialgolds.getTs());
        boolean b = false;
        if (wsSerialgolds.getIsDelete() == true) {
            b = dbaccess.deleteData(Inventory_serialgolds.TABLE_INVENTORY_SERIALGOLDS,
                    Inventory_serialgolds.COLUMN_SERIAL_ID + " = ? and " + Inventory_serialgolds.COLUMN_GOLD_ID + "=?", new String[]{wsSerialgolds.getSerial(), String.valueOf(wsSerialgolds.getGold())}
            );

        } else {
            long l = dbaccess.insertInventory_serialgolds(inventory_serialgolds);
            b = (l > 0);
        }
        Date ts = wsSerialgolds.getTs();
        updateTimeStamp(AdministrationStaffRole.TABLE_ADMINISTRATION_STAFF_ROLE, ts);
        appContext.setTs(ts);
        return b;
//        long l = dbaccess.insertInventory_serialgolds(inventory_serialgolds);
//        return (l > 0);

    }

    private boolean importSmith(Smith wsSmith) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        if (!appContext.isNewUser()) {
            dbaccess.deleteData(ManufacturingSmith.TABLE_MANUFACTURING_SMITH,
                    ManufacturingSmith.COLUMN_ID + " = ? ", new String[]{String.valueOf(wsSmith.getId())}
            );
        }
        ManufacturingSmith manufacturingsmith = new ManufacturingSmith();
        manufacturingsmith.setId(wsSmith.getId());
        manufacturingsmith.setName(wsSmith.getName());
        manufacturingsmith.setNickname(wsSmith.getNickname());
        manufacturingsmith.setNrc(wsSmith.getNrc());
        manufacturingsmith.setAddress(wsSmith.getAddress());
        manufacturingsmith.setPhone(wsSmith.getPhone());
        manufacturingsmith.setRefname(wsSmith.getRefname());
        manufacturingsmith.setMixjob(wsSmith.getMixjob());
        manufacturingsmith.setK(wsSmith.getK());
        manufacturingsmith.setP(wsSmith.getP());
        manufacturingsmith.setY(Double.parseDouble(wsSmith.getY()));
        manufacturingsmith.setG(Double.parseDouble(wsSmith.getG()));
        manufacturingsmith.setCost(Double.parseDouble(wsSmith.getCost()));
        manufacturingsmith.setDate_joined(wsSmith.getDateJoined());
        if (wsSmith.getDateEnd() != null) {
            manufacturingsmith.setDate_end(wsSmith.getDateEnd());
        }
        manufacturingsmith.setIs_smith2(wsSmith.getIsSmith2());
        manufacturingsmith.setInhandjob(wsSmith.getInhandjob());
        manufacturingsmith.setActive(wsSmith.getActive());
        manufacturingsmith.setGoldsaving(Double.parseDouble(wsSmith.getGoldsaving()));
        manufacturingsmith.setInhandjob(wsSmith.getInhandjob());
        manufacturingsmith.setPhoto(wsSmith.getPhoto());

        long l = dbaccess.insertManufacturingSmith(manufacturingsmith);
        Date ts = new Date(System.currentTimeMillis());
        updateTimeStamp(ManufacturingSmith.TABLE_MANUFACTURING_SMITH, ts);
        appContext.setTs(ts);

        return (l > 0);

    }


    private boolean importPallet(Pallet wsPallet) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        if (!appContext.isNewUser()) {
            dbaccess.deleteData(InventoryPallet.TABLE_INVENTORY_PALLET,
                    InventoryPallet.COLUMN_ID + " = ? ", new String[]{wsPallet.getId()}
            );
        }
        InventoryPallet inventoryPallet = new InventoryPallet();
        inventoryPallet.setId(wsPallet.getId());
        inventoryPallet.setPallet_name(wsPallet.getPalletName());
        inventoryPallet.setPallet_description(wsPallet.getPalletDescription());
        inventoryPallet.setPallet_type(wsPallet.getPalletType());
        inventoryPallet.setBarcode(wsPallet.getBarcode());
        inventoryPallet.setTag(wsPallet.getTag());
        inventoryPallet.setLocation_id(wsPallet.getLocation());
        inventoryPallet.setWeight(Double.parseDouble(wsPallet.getWeight()));
        inventoryPallet.setIs_used(wsPallet.getIsUsed());
        inventoryPallet.setActive(wsPallet.getActive());
        inventoryPallet.setTs(wsPallet.getTs());

        long l = dbaccess.insertInventoryPallet(inventoryPallet);
        Date ts = new Date(System.currentTimeMillis());
        updateTimeStamp(InventoryPallet.TABLE_INVENTORY_PALLET, ts);
        appContext.setTs(ts);
        return (l > 0);

    }

    private boolean importGoodsInventory(GoodsInventory wsgoodsInventory) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        if (!appContext.isNewUser()) {
            dbaccess.deleteData(InventoryGoodInventory.TABLE_INVENTORY_GOODSINVENTORY,
                    InventoryGoodInventory.COLUMN_ID + " = ? ", new String[]{wsgoodsInventory.getId()}
            );
        }
        InventoryGoodInventory goodsinventory = new InventoryGoodInventory();
        goodsinventory.setId(wsgoodsInventory.getId());
        goodsinventory.setQty(Double.parseDouble(wsgoodsInventory.getQty()));
        goodsinventory.setWeight(Double.parseDouble(wsgoodsInventory.getWeight()));
        goodsinventory.setK(wsgoodsInventory.getK());
        goodsinventory.setP(wsgoodsInventory.getP());
        goodsinventory.setY(Double.parseDouble(wsgoodsInventory.getY()));
        goodsinventory.setIs_delete(wsgoodsInventory.getIsDelete());
        goodsinventory.setTs(wsgoodsInventory.getTs());
        goodsinventory.setLocation_id(wsgoodsInventory.getLocation());
        goodsinventory.setProduct_id(wsgoodsInventory.getProduct());
        goodsinventory.setUom_id(wsgoodsInventory.getUom());
        boolean b = false;
        if (wsgoodsInventory.getIsDelete() == true) {
            b = dbaccess.deleteData(InventoryGoodInventory.TABLE_INVENTORY_GOODSINVENTORY,
                    InventoryGoodInventory.COLUMN_ID + "=?",
                    new String[]{wsgoodsInventory.getId()});

        } else {
            long l = dbaccess.insertGoodsInventory(goodsinventory);
            b = (l > 0);
        }
        Date ts = new Date(System.currentTimeMillis());
        updateTimeStamp(InventoryGoodInventory.TABLE_INVENTORY_GOODSINVENTORY, ts);
        appContext.setTs(ts);
        return b;
//        long l = dbaccess.insertGoodsInventory(goodsinventory);
//        return (l > 0);
    }

    private boolean importSmithMemberList(Smithmembers wsSmithmembers) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        if (!appContext.isNewUser()) {
            dbaccess.deleteData(Manufacturing_smithmembers.TABLE_MANUFACTURING_SMITHMEMBERS,
                    Manufacturing_smithmembers.COLUMN_ID + " = ? ", new String[]{String.valueOf(wsSmithmembers.getId())}
            );
        }
        Manufacturing_smithmembers manufacturing_smithmembers = new Manufacturing_smithmembers();
        manufacturing_smithmembers.setId(wsSmithmembers.getId());
        manufacturing_smithmembers.setName(wsSmithmembers.getName());
        manufacturing_smithmembers.setNickname(wsSmithmembers.getNickname());
        manufacturing_smithmembers.setNrc(wsSmithmembers.getNrc());
        manufacturing_smithmembers.setAddress(wsSmithmembers.getAddress());
        manufacturing_smithmembers.setPhone(wsSmithmembers.getPhone());
        manufacturing_smithmembers.setActive(wsSmithmembers.getActive());
        manufacturing_smithmembers.setSmith_id(String.valueOf(wsSmithmembers.getSmith()));
        manufacturing_smithmembers.setPhoto(wsSmithmembers.getPhoto());
        long l = dbaccess.insertManufacturing_smithmembers(manufacturing_smithmembers);
        Date ts = new Date(System.currentTimeMillis());
        updateTimeStamp(Manufacturing_smithmembers.TABLE_MANUFACTURING_SMITHMEMBERS, ts);
        appContext.setTs(ts);
        return (l > 0);
    }

    private boolean importJobStatusList(JobStatus wsJobStatus) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        if (!appContext.isNewUser()) {
            dbaccess.deleteData(Manufacturing_jobstatus.TABLE_MANUFACTURING_JOBSTATUS,
                    Manufacturing_jobstatus.COLUMN_ID + " = ? ", new String[]{String.valueOf(wsJobStatus.getId())}
            );
        }
        Manufacturing_jobstatus manufacturing_jobstatus = new Manufacturing_jobstatus();
        manufacturing_jobstatus.setId(wsJobStatus.getId());
        manufacturing_jobstatus.setName(wsJobStatus.getName());
        manufacturing_jobstatus.setDescription(wsJobStatus.getDescription());
        manufacturing_jobstatus.setStatus_type(wsJobStatus.getStatusType());
        manufacturing_jobstatus.setActive(wsJobStatus.getActive());
        long l = dbaccess.insertManufacturing_jobstatus(manufacturing_jobstatus);
        Date ts = new Date(System.currentTimeMillis());
        updateTimeStamp(Manufacturing_jobstatus.TABLE_MANUFACTURING_JOBSTATUS, ts);
        appContext.setTs(ts);
        return (l > 0);
    }

    private boolean importSmithJobGoldList(Smith_JobGold wsSmith_JobGold) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
//        if(!appContext.isNewUser()){
//            dbaccess.deleteData(Manufacturing_Smith_Jobgold.TABLE_MANUFACTURING_SMITH_JOBGOLD,
//                    Manufacturing_Smith_Jobgold.COLUMN_ID + " = ? ",new String[]{staffRole.getId()}
//            );
//        }
        Manufacturing_Smith_Jobgold manufacturing_smith_jobgold = new Manufacturing_Smith_Jobgold();
        manufacturing_smith_jobgold.setSmith_joborder_id(wsSmith_JobGold.getSmithJoborder());
        manufacturing_smith_jobgold.setWeight(Double.valueOf(wsSmith_JobGold.getWeight()));
        manufacturing_smith_jobgold.setQty(Double.valueOf(wsSmith_JobGold.getQty()));
        manufacturing_smith_jobgold.setGoldsaving(Double.valueOf(wsSmith_JobGold.getGoldsaving()));
        manufacturing_smith_jobgold.setRemarks(wsSmith_JobGold.getRemarks());
        manufacturing_smith_jobgold.setRow_no(wsSmith_JobGold.getRowNo());
        manufacturing_smith_jobgold.setIs_delete(wsSmith_JobGold.getIsDelete());
        manufacturing_smith_jobgold.setGold_id(wsSmith_JobGold.getGold());
        manufacturing_smith_jobgold.setUom_id(wsSmith_JobGold.getUom());
        boolean b = false;
        if (wsSmith_JobGold.getIsDelete() == true) {
            b = dbaccess.deleteData(Manufacturing_Smith_Jobgold.TABLE_MANUFACTURING_SMITH_JOBGOLD,
                    Manufacturing_Smith_Jobgold.COLUMN_SMITH_JOBORDER_ID + "=?",
                    new String[]{wsSmith_JobGold.getSmithJoborder()});
        } else {
            long l = dbaccess.insertManufacturing_Smith_Jobgold(manufacturing_smith_jobgold);
            b = (l > 0);
        }

        return b;
//        long l = dbaccess.insertManufacturing_Smith_Jobgold(manufacturing_smith_jobgold);
//        return (l > 0);
    }

    private boolean importSmithJobOrderList(Smith_joborder wsSmith_joborder) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        //TODO
        Manufacturing_smith_joborder manufacturing_smith_joborder = new Manufacturing_smith_joborder();
        manufacturing_smith_joborder.setId(wsSmith_joborder.getId());
        manufacturing_smith_joborder.setJoborder_no(wsSmith_joborder.getJoborderNo());
        manufacturing_smith_joborder.setJoborder_date(wsSmith_joborder.getJoborderDate());
        manufacturing_smith_joborder.setPrejewelout(wsSmith_joborder.getPrejewelout());
        manufacturing_smith_joborder.setPrejewelout_date(wsSmith_joborder.getPrejeweloutDate());
        manufacturing_smith_joborder.setDate_target(wsSmith_joborder.getDateTarget());
        manufacturing_smith_joborder.setDate_start(wsSmith_joborder.getDateStart());
        manufacturing_smith_joborder.setDate_end(wsSmith_joborder.getDateEnd());
        manufacturing_smith_joborder.setActive(wsSmith_joborder.getActive());
        manufacturing_smith_joborder.setIs_delete(wsSmith_joborder.getIsDelete());
        manufacturing_smith_joborder.setTs(wsSmith_joborder.getTs());
        manufacturing_smith_joborder.setJoborder_type_id(wsSmith_joborder.getJoborderType());
        manufacturing_smith_joborder.setJobstatus_id(wsSmith_joborder.getJobstatus());
        manufacturing_smith_joborder.setSmith_id(wsSmith_joborder.getSmith());
        manufacturing_smith_joborder.setDensity(wsSmith_joborder.getDiffK());
        manufacturing_smith_joborder.setDiff_k(wsSmith_joborder.getDiffK());
        manufacturing_smith_joborder.setDiff_p(wsSmith_joborder.getDiffP());
        manufacturing_smith_joborder.setDiff_weight(Double.parseDouble(wsSmith_joborder.getDiffWeight()));
        manufacturing_smith_joborder.setDiff_y(Double.parseDouble(wsSmith_joborder.getDiffY()));
        manufacturing_smith_joborder.setPrint_count(wsSmith_joborder.getPrintCount());
        manufacturing_smith_joborder.setProduct_weight(Double.parseDouble(wsSmith_joborder.getProductWeight()));
        manufacturing_smith_joborder.setRemain_gold(Double.parseDouble(wsSmith_joborder.getRemainGold()));
        manufacturing_smith_joborder.setRemain_jewel(Double.parseDouble(wsSmith_joborder.getRemainJewel()));
        manufacturing_smith_joborder.setSave_count(wsSmith_joborder.getSaveCount());

        if (wsSmith_joborder.getIsDelete()) {
            boolean b = dbaccess.deleteData(Manufacturing_smith_joborder.TABLE_MANUFACTURING_SMITH_JOBORDER,
                    Manufacturing_smith_joborder.COLUMN_ID + "=?",
                    new String[]{String.valueOf(wsSmith_joborder.getId())});
            return b;
        } else {
            long l = dbaccess.insertManufacturing_smith_joborder(manufacturing_smith_joborder);
            return (l > 0);
        }

    }

    //
    private boolean importSmithJobProductList(Smith_jobproduct wsSmith_jobproduct) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        //TODO
        Manufacturing_smith_jobproduct manufacturing_smith_jobproduct = new Manufacturing_smith_jobproduct();
        manufacturing_smith_jobproduct.setPlength(wsSmith_jobproduct.getPlength());
        manufacturing_smith_jobproduct.setWeight(wsSmith_jobproduct.getWeight());
        manufacturing_smith_jobproduct.setK(wsSmith_jobproduct.getK());
        manufacturing_smith_jobproduct.setP(wsSmith_jobproduct.getP());
        manufacturing_smith_jobproduct.setY(wsSmith_jobproduct.getY());
        manufacturing_smith_jobproduct.setQty(wsSmith_jobproduct.getQty());
        manufacturing_smith_jobproduct.setReduce_weight(wsSmith_jobproduct.getReduceWeight());
        manufacturing_smith_jobproduct.setReduce_k(wsSmith_jobproduct.getReduceK());
        manufacturing_smith_jobproduct.setReduce_p(wsSmith_jobproduct.getReduceP());
        manufacturing_smith_jobproduct.setReduce_y(wsSmith_jobproduct.getReduceY());
        manufacturing_smith_jobproduct.setTarget_date(wsSmith_jobproduct.getTargetDate());
        manufacturing_smith_jobproduct.setStart_date(wsSmith_jobproduct.getStartDate());
        manufacturing_smith_jobproduct.setEnd_date(wsSmith_jobproduct.getEndDate());
        manufacturing_smith_jobproduct.setWages(wsSmith_jobproduct.getWages());
        manufacturing_smith_jobproduct.setRemarks(wsSmith_jobproduct.getRemarks());
        manufacturing_smith_jobproduct.setProducts_id(wsSmith_jobproduct.getProducts());
        manufacturing_smith_jobproduct.setSmith_joborder_id(wsSmith_jobproduct.getSmithJoborder());
        manufacturing_smith_jobproduct.setIs_delete(wsSmith_jobproduct.getIsDelete());
        manufacturing_smith_jobproduct.setRow_no(wsSmith_jobproduct.getRowNo());
        manufacturing_smith_jobproduct.setTo_location_id(wsSmith_jobproduct.getToLocation());
        manufacturing_smith_jobproduct.setUom_id(wsSmith_jobproduct.getUom());

        if (wsSmith_jobproduct.getIsDelete()) {
            boolean b = dbaccess.deleteData(Manufacturing_smith_jobproduct.TABLE_MANUFACTURING_SMITH_JOBPRODUCT,
                    Manufacturing_smith_jobproduct.COLUMN_SMITH_JOBORDER_ID + "=?",
                    new String[]{wsSmith_jobproduct.getSmithJoborder()});
            return b;
        } else {
            long l = dbaccess.insertManufacturing_smith_jobproduct(manufacturing_smith_jobproduct);
            return (l > 0);
        }

    }

    private boolean importSmithJobTypeList(Smith_jobtype wsSmith_jobtype) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        //TODO
        Manufacturing_smith_jobtype manufacturing_smith_jobtype = new Manufacturing_smith_jobtype();
        manufacturing_smith_jobtype.setId(wsSmith_jobtype.getId());
        manufacturing_smith_jobtype.setName(wsSmith_jobtype.getName());
        manufacturing_smith_jobtype.setDescription(wsSmith_jobtype.getDescription());
        manufacturing_smith_jobtype.setActive(wsSmith_jobtype.getActive());
        manufacturing_smith_jobtype.setJobtype_group(wsSmith_jobtype.getJobtypeGroup());
        long l = dbaccess.insertManufacturing_smith_jobtype(manufacturing_smith_jobtype);
        return (l > 0);
    }

    private boolean importWsImagesType(WsImagesType wsImagesType) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        //TODO
        AdministrationWsimagestype administrationWsimagestype = new AdministrationWsimagestype();
        administrationWsimagestype.setName(wsImagesType.getName());
        Log.i(TAG, "ImgesTypes" + administrationWsimagestype.getName());
        long l = dbaccess.insertAdministration_wsimagestype(administrationWsimagestype);
        return (l > 0);
    }

    private boolean importWsImages(WsImages wsImages) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        if (!appContext.isNewUser()) {
            dbaccess.deleteData(AdministrationWsimages.TABLE_ADMINISTRATION_WSIMAGES,
                    AdministrationWsimages.COLUMN_ID + " = ? ", new String[]{wsImages.getId()}
            );
        }
        AdministrationWsimages administrationWsimages = new AdministrationWsimages();
        administrationWsimages.setName(wsImages.getName());
        administrationWsimages.setId(wsImages.getId());
        administrationWsimages.setPath(wsImages.getPath());
        administrationWsimages.setTimestamp(wsImages.getTimestamp());
        administrationWsimages.setIs_delete(wsImages.getIsDelete());
        administrationWsimages.setSolution_id(wsImages.getSolution());
        administrationWsimages.setType_id(wsImages.getType());
        boolean b = false;
        if (wsImages.getIsDelete() == true) {
            b = dbaccess.deleteData(AdministrationWsimages.TABLE_ADMINISTRATION_WSIMAGES,
                    AdministrationWsimages.COLUMN_ID + "=?",
                    new String[]{String.valueOf(wsImages.getId())});


        } else {
            long l = dbaccess.insertAdministration_wsimages(administrationWsimages);
            b = (l > 0);
        }
        Date ts = wsImages.getTimestamp();
        updateTimeStamp(AdministrationWsimages.TABLE_ADMINISTRATION_WSIMAGES, ts);
        appContext.setTs(ts);
        return b;

    }

    private boolean importProductReduce(ProductReduce productReduce) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        if (!appContext.isNewUser()) {
            dbaccess.deleteData(InventoryProductreduce.TABLE_INVENTORY_PRODUCTREDUCE,
                    InventoryProductreduce.COLUMN_ID + " = ? ", new String[]{productReduce.getProduct()}
            );
        }
        InventoryProductreduce inventoryProductreduce = new InventoryProductreduce();
        inventoryProductreduce.setId(productReduce.getId());
        inventoryProductreduce.setReduce_g((productReduce.getReduceG() == null) ? 0.00 : productReduce.getReduceG());
        inventoryProductreduce.setReduce_k((productReduce.getReduceK() == null) ? 0 : productReduce.getReduceK());
        inventoryProductreduce.setReduce_p((productReduce.getReduceP() == null) ? 0 : productReduce.getReduceP());
        inventoryProductreduce.setReduce_y((productReduce.getReduceY() == null) ? 0 : productReduce.getReduceY());
        inventoryProductreduce.setProduction_fee((productReduce.getProductionFee() == null) ? 0.00 : productReduce.getProductionFee());
        inventoryProductreduce.setCost_reduce_k((productReduce.getCostReduceK() == null) ? 0 : productReduce.getCostReduceK());
        inventoryProductreduce.setCost_reduce_p((productReduce.getCostReduceP() == null) ? 0 : productReduce.getCostReduceP());
        inventoryProductreduce.setCost_reduce_y((productReduce.getCostReduceY() == null) ? 0.00 : productReduce.getCostReduceY());
        inventoryProductreduce.setCost_production_fee((productReduce.getCostProductionFee() == null) ? 0.00 : productReduce.getCostProductionFee());
        inventoryProductreduce.setRemarks(productReduce.getRemarks());
        inventoryProductreduce.setActive(productReduce.getActive());
        inventoryProductreduce.setIs_delete(productReduce.getIsDelete());
        inventoryProductreduce.setGold_id((productReduce.getGold() == null) ? 0 : productReduce.getGold());
        inventoryProductreduce.setPlength_id((productReduce.getPlength() == null) ? 0 : productReduce.getPlength());
        inventoryProductreduce.setProduct_id(productReduce.getProduct());
        inventoryProductreduce.setWs_reduce_g((productReduce.getWsReduceG() == null) ? 0.00 : productReduce.getWsReduceG());
        inventoryProductreduce.setWs_reduce_k((productReduce.getWsReduceK() == null) ? 0 : productReduce.getWsReduceK());
        inventoryProductreduce.setWs_reduce_p((productReduce.getWsReduceP() == null) ? 0 : productReduce.getWsReduceP());
        inventoryProductreduce.setWs_reduce_y((productReduce.getWsReduceY() == null) ? 0.00 : productReduce.getWsReduceY());
        inventoryProductreduce.setTs((productReduce.getTs() == null) ? new Date(System.currentTimeMillis()): productReduce.getTs());
        boolean b = false;
        if (productReduce.getIsDelete() == true) {
            b = dbaccess.deleteData(InventoryProductreduce.TABLE_INVENTORY_PRODUCTREDUCE,
                    InventoryProductreduce.COLUMN_ID + "=?",
                    new String[]{String.valueOf(productReduce.getId())});
        } else {
            long l = dbaccess.insertProduct_Reduce(inventoryProductreduce);
            b= (l > 0);
        }
        Date ts = inventoryProductreduce.getTs();
        updateTimeStamp(inventoryProductreduce.TABLE_INVENTORY_PRODUCTREDUCE, ts);
        appContext.setTs(ts);
        return b;

    }

    private boolean importProductSubGroup(ProductSubGroup productSubGroup) {
        dbaccess = DbAccess.getInstance();
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        //TODO
        InventoryProductSubgroups inventoryProductSubgroups = new InventoryProductSubgroups();
        inventoryProductSubgroups.setId(productSubGroup.getId());
        inventoryProductSubgroups.setName(productSubGroup.getName());
        inventoryProductSubgroups.setActive(productSubGroup.getActive());
        inventoryProductSubgroups.setIs_delete(productSubGroup.getIsDelete());
        inventoryProductSubgroups.setPgroup_id(productSubGroup.getPgroup());

        if (productSubGroup.getIsDelete() == true) {
            boolean b = dbaccess.deleteData(InventoryProductSubgroups.TABLE_INVENTORY_PRODUCTSUBGROUPS,
                    InventoryProductSubgroups.COLUMN_ID + "=?",
                    new String[]{String.valueOf(productSubGroup.getId())});
            return b;
        } else {
            long l = dbaccess.insertProductSubGroup(inventoryProductSubgroups);
            return (l > 0);
        }

    }

    private boolean importProductLength(ProductLength productLength) {
        dbaccess = DbAccess.getInstance();
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        //TODO
        InventoryProductlength inventoryProductlength = new InventoryProductlength();
        inventoryProductlength.setId(productLength.getId());
        inventoryProductlength.setPlength(productLength.getPlength());
        inventoryProductlength.setActive(productLength.getActive());
        inventoryProductlength.setIs_delete(productLength.getIsDelete());
        inventoryProductlength.setPsgroup_id(productLength.getPsgroup());

        if (productLength.getIsDelete() == true) {
            boolean b = dbaccess.deleteData(InventoryProductlength.TABLE_INVENTORY_PRODUCTLENGTH,
                    inventoryProductlength.COLUMN_ID + "=?",
                    new String[]{String.valueOf(productLength.getId())});
            return b;
        } else {
            Long l = dbaccess.insertProductLength(inventoryProductlength);
            return (l > 0);
        }

    }

    private boolean importJewelinventory(JewelInventory jewelInventory) {
        dbaccess = DbAccess.getInstance();
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        //TODO
        Inventory_jewelinventory inventory_jewelinventory = new Inventory_jewelinventory();
        inventory_jewelinventory.setQty(jewelInventory.getQty());
        inventory_jewelinventory.setTs(jewelInventory.getTs());
        inventory_jewelinventory.setJewellength_id(jewelInventory.getJewellength());
        inventory_jewelinventory.setJewelshape_id(jewelInventory.getJewelshape());
        inventory_jewelinventory.setJeweltype_id(jewelInventory.getJeweltype());

        Long l = dbaccess.insertInventory_jewelinventory(inventory_jewelinventory);
        return (l > 0);


    }

    private boolean importJewellength(Jewellength jewellength) {
        dbaccess = DbAccess.getInstance();
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        //TODO
        Inventory_jewellength inventory_jewellength = new Inventory_jewellength();
        inventory_jewellength.setId(jewellength.getId());
        inventory_jewellength.setName(jewellength.getName());
        inventory_jewellength.setActive(jewellength.getActive());
        inventory_jewellength.setJewelshape_id(jewellength.getJewelshape());

        Long l = dbaccess.insertInventory_jewellength(inventory_jewellength);
        return (l > 0);


    }

    private boolean importJewelpurchase(JewelPurchase jewelPurchase) {
        dbaccess = DbAccess.getInstance();
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        //TODO
        Inventory_jewelpurchase inventory_jewelpurchase = new Inventory_jewelpurchase();
        inventory_jewelpurchase.setPurchase_no(jewelPurchase.getPurchaseNo());
        inventory_jewelpurchase.setPurchase_date(jewelPurchase.getPurchaseDate());
        inventory_jewelpurchase.setReference_no(jewelPurchase.getReferenceNo());
        inventory_jewelpurchase.setAmount(jewelPurchase.getAmount());
        inventory_jewelpurchase.setPaid_amount(jewelPurchase.getPaidAmount());
        inventory_jewelpurchase.setDeduction(jewelPurchase.getDeduction());
        inventory_jewelpurchase.setRemarks(jewelPurchase.getRemarks());
        inventory_jewelpurchase.setIs_delete(jewelPurchase.getIsDelete());
        inventory_jewelpurchase.setTs(jewelPurchase.getTs());
        inventory_jewelpurchase.setSupplier_id(jewelPurchase.getSupplier());

        if (jewelPurchase.getIsDelete() == true) {
            boolean b = dbaccess.deleteData(Inventory_jewelpurchase.TABLE_INVENTORY_JEWELPURCHASE,
                    inventory_jewelpurchase.COLUMN_PURCHASE_NO + "=?",
                    new String[]{String.valueOf(jewelPurchase.getPurchaseNo())});
            return b;
        } else {
            Long l = dbaccess.insertInventory_jewelpurchase(inventory_jewelpurchase);
            return (l > 0);
        }


    }

    private boolean importJewelpurchaseitems(JewelPurchaseItems jewelPurchaseItems) {
        dbaccess = DbAccess.getInstance();
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        //TODO
        Inventory_jewelpurchaseitems inventory_jewelpurchaseitems = new Inventory_jewelpurchaseitems();
        inventory_jewelpurchaseitems.setId(jewelPurchaseItems.getId());
        inventory_jewelpurchaseitems.setQty(jewelPurchaseItems.getQty());
        inventory_jewelpurchaseitems.setPrice(jewelPurchaseItems.getPrice());
        inventory_jewelpurchaseitems.setAmount(jewelPurchaseItems.getAmount());
        inventory_jewelpurchaseitems.setRemarks(jewelPurchaseItems.getRemarks());
        inventory_jewelpurchaseitems.setRow_no(jewelPurchaseItems.getRowNo());
        inventory_jewelpurchaseitems.setIs_delete(jewelPurchaseItems.getIsDelete());
        inventory_jewelpurchaseitems.setTs(jewelPurchaseItems.getTs());
        inventory_jewelpurchaseitems.setJewel_purchase_id(jewelPurchaseItems.getJewelPurchase());
        inventory_jewelpurchaseitems.setJewellength_id(jewelPurchaseItems.getJewellength());
        inventory_jewelpurchaseitems.setJewelshape_id(jewelPurchaseItems.getJewelshape());
        inventory_jewelpurchaseitems.setJeweltype_id(jewelPurchaseItems.getJeweltype());

        if (jewelPurchaseItems.getIsDelete() == true) {
            boolean b = dbaccess.deleteData(Inventory_jewelpurchaseitems.TABLE_INVENTORY_JEWELPURCHASEITEMS,
                    inventory_jewelpurchaseitems.COLUMN_ID + "=?",
                    new String[]{String.valueOf(jewelPurchaseItems.getId())});
            return b;
        } else {
            Long l = dbaccess.insertInventory_jewelpurchaseitems(inventory_jewelpurchaseitems);
            return (l > 0);
        }


    }

    private boolean importJewelshape(JewelShape jewelShape) {
        dbaccess = DbAccess.getInstance();
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        //TODO
        Inventory_jewelshape inventory_jewelshape = new Inventory_jewelshape();
        inventory_jewelshape.setId(jewelShape.getId());
        inventory_jewelshape.setName(jewelShape.getName());
        inventory_jewelshape.setActive(jewelShape.getActive());
        inventory_jewelshape.setJeweltype_id(jewelShape.getJeweltype());

        Long l = dbaccess.insertInventory_jewelshape(inventory_jewelshape);
        return (l > 0);


    }

    private boolean importJeweltype(JewelType jewelType) {
        dbaccess = DbAccess.getInstance();
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        //TODO
        Inventory_jeweltype inventoryJeweltype = new Inventory_jeweltype();
        inventoryJeweltype.setId(jewelType.getId());
        inventoryJeweltype.setName(jewelType.getName());
        inventoryJeweltype.setUnittype(jewelType.getUnittype());
        inventoryJeweltype.setPurchase_unittype(jewelType.getPurchaseUnittype());
        inventoryJeweltype.setActive(jewelType.getActive());

        Long l = dbaccess.insertInventory_jeweltype(inventoryJeweltype);
        return (l > 0);


    }

    private boolean importSupplier(Supplier supplier) {
        dbaccess = DbAccess.getInstance();
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        //TODO
        Inventory_supplier inventory_supplier = new Inventory_supplier();
        inventory_supplier.setId(supplier.getId());
        inventory_supplier.setName(supplier.getName());
        inventory_supplier.setAddress(supplier.getAddress());
        inventory_supplier.setPhone(supplier.getPhone());
        inventory_supplier.setActive(supplier.getActive());
        inventory_supplier.setSupplier_group_id(supplier.getSupplierGroup());

        Long l = dbaccess.insertInventory_supplier(inventory_supplier);
        return (l > 0);


    }

    private boolean importSuppliergroup(SupplierGroup supplierGroup) {
        dbaccess = DbAccess.getInstance();
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        //TODO
        Inventory_suppliergroup inventory_suppliergroup = new Inventory_suppliergroup();
        inventory_suppliergroup.setId(supplierGroup.getId());
        inventory_suppliergroup.setName(supplierGroup.getName());
        inventory_suppliergroup.setActive(supplierGroup.getActive());

        Long l = dbaccess.insertInventory_suppliergroup(inventory_suppliergroup);
        return (l > 0);


    }

    private boolean importInventory_receiveserial(ReceiveSerial receiveSerial) {
        dbaccess = DbAccess.getInstance();
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        //TODO
        Inventory_receiveserial inventory_receiveserial = new Inventory_receiveserial();
        inventory_receiveserial.setProductserial_id(receiveSerial.getProductserial());
        inventory_receiveserial.setLineno(receiveSerial.getLineno());
        inventory_receiveserial.setIs_delete(receiveSerial.getIsDelete());
        inventory_receiveserial.setTs(receiveSerial.getTs());

        if (receiveSerial.getIsDelete() == true) {
            boolean b = dbaccess.deleteData(Inventory_receiveserial.TABLE_INVENTORY_RECEIVESERIAL,
                    inventory_receiveserial.COLUMN_PRODUCTSERIAL_ID + "=?",
                    new String[]{String.valueOf(receiveSerial.getProductserial())});
            return b;
        } else {
            Long l = dbaccess.insertInventory_receiveserial(inventory_receiveserial);
            return (l > 0);
        }

    }

    private boolean importInventory_receiveddetail(ReceivedDetail receivedDetail) {
        dbaccess = DbAccess.getInstance();
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        //TODO
        Inventory_receiveddetail inventory_receiveddetail = new Inventory_receiveddetail();
        inventory_receiveddetail.setLineno(receivedDetail.getLineno());
        inventory_receiveddetail.setQty(receivedDetail.getQty());
        inventory_receiveddetail.setWeight(receivedDetail.getWeight());
        inventory_receiveddetail.setK(receivedDetail.getK());
        inventory_receiveddetail.setP(receivedDetail.getP());
        inventory_receiveddetail.setY(receivedDetail.getY());
        inventory_receiveddetail.setDensity(receivedDetail.getDensity());
        inventory_receiveddetail.setRemarks(receivedDetail.getRemarks());
        inventory_receiveddetail.setIs_delete(receivedDetail.getIsDelete());
        inventory_receiveddetail.setTs(receivedDetail.getTs());
        inventory_receiveddetail.setBin_id(receivedDetail.getBin());
        inventory_receiveddetail.setPallet_id(receivedDetail.getPallet());
        inventory_receiveddetail.setProduct_id(receivedDetail.getProduct());
        inventory_receiveddetail.setReceivedhdr_id(receivedDetail.getReceivedhdr());
        inventory_receiveddetail.setTolocation_id(receivedDetail.getTolocation());
        inventory_receiveddetail.setUom_id(receivedDetail.getUom());


        if (receivedDetail.getIsDelete() == true) {
            boolean b = dbaccess.deleteData(Inventory_receiveddetail.TABLE_INVENTORY_RECEIVEDDETAIL,
                    inventory_receiveddetail.COLUMN_LINENO + "=?",
                    new String[]{String.valueOf(receivedDetail.getLineno())});
            return b;
        } else {
            Long l = dbaccess.insertInventory_receiveddetail(inventory_receiveddetail);
            return (l > 0);
        }

    }

    private boolean importInventory_receivedhdr(ReceivedHdr receivedHdr) {
        dbaccess = DbAccess.getInstance();
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        //TODO
        Inventory_receivedhdr inventory_receivedhdr = new Inventory_receivedhdr();
        inventory_receivedhdr.setReceive_no(receivedHdr.getReceiveNo());
        inventory_receivedhdr.setReceive_date(receivedHdr.getReceiveDate());
        inventory_receivedhdr.setIs_completed(receivedHdr.getIsCompleted());
        inventory_receivedhdr.setIs_approved(receivedHdr.getIsApproved());
        inventory_receivedhdr.setVoid_date(receivedHdr.getVoidDate());
        inventory_receivedhdr.setIs_delete(receivedHdr.getIsDelete());
        inventory_receivedhdr.setTs(receivedHdr.getTs());
        inventory_receivedhdr.setDoctype_id(receivedHdr.getDoctype());
        inventory_receivedhdr.setLocation_id(receivedHdr.getLocation());
        inventory_receivedhdr.setSmith_id(receivedHdr.getSmith());
        inventory_receivedhdr.setStaff_id(receivedHdr.getStaff());

        if (receivedHdr.getIsDelete() == true) {
            boolean b = dbaccess.deleteData(Inventory_receivedhdr.TABLE_INVENTORY_RECEIVEDHDR,
                    inventory_receivedhdr.COLUMN_RECEIVE_NO + "=?",
                    new String[]{String.valueOf(receivedHdr.getReceiveNo())});
            return b;
        } else {
            Long l = dbaccess.insertInventory_receivedhdr(inventory_receivedhdr);
            return (l > 0);
        }

    }

    private boolean importInventory_prodHdr(ProdHdr prodHdr) {
        dbaccess = DbAccess.getInstance();
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        //TODO
        Inventory_prodhdr inventory_prodhdr = new Inventory_prodhdr();
        inventory_prodhdr.setProd_no(prodHdr.getProdNo());
        inventory_prodhdr.setProd_date(prodHdr.getProdDate());
        inventory_prodhdr.setVoucher_no(prodHdr.getVoucherNo());
        inventory_prodhdr.setSave_count(prodHdr.getSaveCount());
        inventory_prodhdr.setIs_delivered(prodHdr.getIsDelivered());
        inventory_prodhdr.setIs_confirmed(prodHdr.getIsConfirmed());
        inventory_prodhdr.setConfirmedby(prodHdr.getConfirmedby());
        inventory_prodhdr.setIs_void(prodHdr.getIsVoid());
        inventory_prodhdr.setVoid_date(prodHdr.getVoidDate());
        inventory_prodhdr.setTs(prodHdr.getTs());
        inventory_prodhdr.setLocation_id(prodHdr.getLocation());
        inventory_prodhdr.setSmit_id(prodHdr.getSmith());
        inventory_prodhdr.setStaff_id(prodHdr.getStaff());


        Long l = dbaccess.insertInventory_prodhdr(inventory_prodhdr);
        return (l > 0);


    }

    private boolean importInventory_proddetail(ProdDetail prodDetail) {
        dbaccess = DbAccess.getInstance();
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        //TODO
        Inventory_proddetail inventory_proddetail = new Inventory_proddetail();
        inventory_proddetail.setId(prodDetail.getId());
        inventory_proddetail.setQty(prodDetail.getQty());
        inventory_proddetail.setReceived_qty(prodDetail.getReceivedQty());
        inventory_proddetail.setWeight(prodDetail.getWeight());
        inventory_proddetail.setK(prodDetail.getK());
        inventory_proddetail.setP(prodDetail.getP());
        inventory_proddetail.setY(prodDetail.getY());
        inventory_proddetail.setDensity(prodDetail.getDensity());
        inventory_proddetail.setRemarks(prodDetail.getRemarks());
        inventory_proddetail.setIs_delete(prodDetail.getIsDelete());
        inventory_proddetail.setProdhdr_id(prodDetail.getProdhdr());
        inventory_proddetail.setProduct_id(prodDetail.getProduct());
        inventory_proddetail.setTolocation_id(prodDetail.getTolocation());
        inventory_proddetail.setUon_id(prodDetail.getUom());
        inventory_proddetail.setTs(prodDetail.getTs());


        if (prodDetail.getIsDelete() == true) {
            boolean b = dbaccess.deleteData(Inventory_proddetail.TABLE_INVENTORY_PRODDETAIL,
                    inventory_proddetail.COLUMN_ID + "=?",
                    new String[]{String.valueOf(prodDetail.getId())});
            return b;
        } else {
            Long l = dbaccess.insertInventory_proddetail(inventory_proddetail);
            return (l > 0);
        }


    }
}

