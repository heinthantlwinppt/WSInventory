package com.ppt.wsinventory.websocket;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ppt.wsinventory.GlobalVariables;
import com.ppt.wsinventory.model.ActionList;
import com.ppt.wsinventory.model.AdministrationLocations;
import com.ppt.wsinventory.model.AdministrationRole;
import com.ppt.wsinventory.model.AdministrationSettings;
import com.ppt.wsinventory.model.AdministrationSolutions;
import com.ppt.wsinventory.model.AdministrationStaff;
import com.ppt.wsinventory.model.AdministrationWsdashboard;
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
import com.ppt.wsinventory.model.InventoryUOM;
import com.ppt.wsinventory.model.Inventory_products;
import com.ppt.wsinventory.model.Inventory_productserial;
import com.ppt.wsinventory.model.Location;
import com.ppt.wsinventory.model.ManufacturingSmith;
import com.ppt.wsinventory.model.Pallet;
import com.ppt.wsinventory.model.Product;
import com.ppt.wsinventory.model.ProductGroup;
import com.ppt.wsinventory.model.ProductSerial;
import com.ppt.wsinventory.model.Role;
import com.ppt.wsinventory.model.Settings;
import com.ppt.wsinventory.model.Smith;
import com.ppt.wsinventory.model.Solution;
import com.ppt.wsinventory.model.Staff;
import com.ppt.wsinventory.model.TableToDelete;
import com.ppt.wsinventory.model.UOM;
import com.ppt.wsinventory.model.WsDashboard;
import com.ppt.wsinventory.services.WsService;
import com.ppt.wsinventory.services.WsSyncService;
import com.ppt.wsinventory.util.HexStringConverter;
import com.ppt.wsinventory.util.JsonHelper;
import com.ppt.wsinventory.util.Utility;
import com.ppt.wsinventory.wsdb.DbAccess;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
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

    public void setmContext(Context mContext) {
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
            Type listType = new TypeToken<ArrayList<TableToDelete>>() {
            }.getType();
            List<TableToDelete> tableToDeletes = gson.fromJson(jsonString, listType);
            for (TableToDelete toDelete : tableToDeletes) {

//                dbaccess.deleteData(toDelete.getTablename());
                importTableToDelete(toDelete);
                Log.i(TAG, "Delete Table: " + toDelete.getTablename());
            }
            RemoveActionList(apiModel.getName());

        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETSOLUTIONS)) {
            jsonString = apiModel.getMessage();
            Type listType = new TypeToken<ArrayList<Solution>>() {
            }.getType();
            List<Solution> solutionList = gson.fromJson(jsonString, listType);
            for (Solution solution : solutionList) {
                importSolutions(solution);
                Log.i(TAG, "Solution Name : " + solution.getSolutionName());
            }
            RemoveActionList(apiModel.getName());
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETSETTINGS)) {
            jsonString = apiModel.getMessage();
            Type listType = new TypeToken<ArrayList<Settings>>() {
            }.getType();
            List<Settings> settingsList = gson.fromJson(jsonString, listType);
            for (Settings settings : settingsList) {
                importSettings(settings);
                Log.i(TAG, "setting id : " + settings.getDeviceId());
            }
            RemoveActionList(apiModel.getName());
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETWSDASHBOARD)) {
            jsonString = apiModel.getMessage();
            Type listType = new TypeToken<ArrayList<WsDashboard>>() {
            }.getType();
            List<WsDashboard> dashboardsList = gson.fromJson(jsonString, listType);
            for (WsDashboard dashboard : dashboardsList) {
                importWsDashboard(dashboard);
                Log.i(TAG, "dashboard Name : " + dashboard.getActionname());
            }
            RemoveActionList(apiModel.getName());
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETSTAFFLIST)) {
            jsonString = apiModel.getMessage();
            Type listType = new TypeToken<ArrayList<Staff>>() {
            }.getType();
            List<Staff> staffsList = gson.fromJson(jsonString, listType);
            for (Staff staff : staffsList) {
                importStaff(staff);
                Log.i(TAG, "Staff Name : " + staff.getStaffName());
            }
            RemoveActionList(apiModel.getName());
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETROLELIST)) {
            jsonString = apiModel.getMessage();
            Type listType = new TypeToken<ArrayList<Role>>() {
            }.getType();
            List<Role> roleList = gson.fromJson(jsonString, listType);
            for (Role role : roleList) {
                importRole(role);
                Log.i(TAG, "Role Name : " + role.getRoleName());
            }
            RemoveActionList(apiModel.getName());
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETLOCATIONSLIST)) {
            jsonString = apiModel.getMessage();
            Type listType = new TypeToken<ArrayList<Location>>() {
            }.getType();
            List<Location> roleList = gson.fromJson(jsonString, listType);
            for (Location location : roleList) {
                importLocations(location);
                Log.i(TAG, "Location Name : " + location.getLocName());
            }
            RemoveActionList(apiModel.getName());
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETUOMLIST)) {
            jsonString = apiModel.getMessage();
            Type listType = new TypeToken<ArrayList<UOM>>() {
            }.getType();
            List<UOM> uomList = gson.fromJson(jsonString, listType);
            for (UOM uom : uomList) {
                importUOM(uom);
                Log.i(TAG, "UOM ID : " + uom.getUom());
            }
            RemoveActionList(apiModel.getName());
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETWSGOLDUOM)) {
            jsonString = apiModel.getMessage();
            Type listType = new TypeToken<ArrayList<GoldUOM>>() {
            }.getType();
            List<GoldUOM> golduomList = gson.fromJson(jsonString, listType);
            for (GoldUOM golduom : golduomList) {
                importGoldUOM(golduom);
                Log.i(TAG, "UOM ID : " + golduom.getUom());
            }
            RemoveActionList(apiModel.getName());
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETWSGOLD)) {
            jsonString = apiModel.getMessage();
            Type listType = new TypeToken<ArrayList<Gold>>() {
            }.getType();
            List<Gold> goldList = gson.fromJson(jsonString, listType);
            for (Gold gold : goldList) {
                importGold(gold);
                Log.i(TAG, "Gold Name : " + gold.getName());
            }
            RemoveActionList(apiModel.getName());
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETWSPRODUCT)) {
            jsonString = apiModel.getMessage();
            Type listType = new TypeToken<ArrayList<Product>>() {
            }.getType();
            List<Product> productList = gson.fromJson(jsonString, listType);
            for (Product product : productList) {
                importProduct(product);
                Log.i(TAG, "Product Name : " + product.getName());
            }
            RemoveActionList(apiModel.getName());
        }
        else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETWSSMITHLIST)) {
            jsonString = apiModel.getMessage();
            Type listType = new TypeToken<ArrayList<Smith>>() {
            }.getType();
            List<Smith> smithList = gson.fromJson(jsonString, listType);
            for (Smith smith : smithList) {
                importSmith(smith);
                Log.i(TAG, "Smith Name : " + smith.getName());
            }
            RemoveActionList(apiModel.getName());
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETWSPRODUCTGROUPLIST)) {
            jsonString = apiModel.getMessage();
            Type listType = new TypeToken<ArrayList<ProductGroup>>() {
            }.getType();
            List<ProductGroup> productGroupList = gson.fromJson(jsonString, listType);
            for (ProductGroup productGroup : productGroupList) {
                importProductGroup(productGroup);
                Log.i(TAG, "Product Name : " + productGroup.getName());
            }
            RemoveActionList(apiModel.getName());
        } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETBINLIST)) {
            jsonString = apiModel.getMessage();
            if (!TextUtils.isEmpty(jsonString)) {
                Type listType = new TypeToken<ArrayList<BIN>>() {
                }.getType();
                List<BIN> binList = gson.fromJson(jsonString, listType);
                for (BIN bin : binList) {
                    if (importBIN(bin)) {
                        appContext.setTs(bin.getTs());
                    } else {
                        break;
                    }
                }
            } else if (apiModel.getName().equalsIgnoreCase(ApiModel.GETWSGOODSINVENTORYLIST)) {
                jsonString = apiModel.getMessage();
                if (!TextUtils.isEmpty(jsonString)) {
                    Type listType = new TypeToken<ArrayList<GoodsInventory>>() {
                    }.getType();
                    List<GoodsInventory>goodsinventoryList = gson.fromJson(jsonString, listType);
                    for (GoodsInventory goodsinventory : goodsinventoryList) {
                        if (importGoodsInventory(goodsinventory)) {
                            appContext.setTs(goodsinventory.getTs());
                        } else {
                            break;
                        }
                    }
                } else {
                    appContext.setTs(Utility.getDateBegin());
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
                            appContext.setTs(productserial.getTs());
                        } else {
                            break;
                        }
                    }
                } else {
                    appContext.setTs(Utility.getDateBegin());
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
                            appContext.setTs(pallet.getTs());
                        } else {
                            break;
                        }
                    }
                } else {
                    appContext.setTs(Utility.getDateBegin());
                    RemoveActionList(apiModel.getName());
                }

            }
//        else if(apiModel.getName().equalsIgnoreCase(ApiModel.GETWSGOLD)) {
//            jsonString = apiModel.getMessage();
//            Type listType = new TypeToken<ArrayList<BIN>>() {}.getType();
//            List<BIN> binList = gson.fromJson(jsonString, listType);
//            for (BIN bin : binList) {
//                importBIN(bin);
////                Log.i(TAG, "BIN Name : " + bin.get());
//            }
//            RemoveActionList(apiModel.getName());
//        }else if(apiModel.getName().equalsIgnoreCase(ApiModel.GETWSGOLDUOM)) {
//            jsonString = apiModel.getMessage();
//            Type listType = new TypeToken<ArrayList<BIN>>() {}.getType();
//            List<BIN> binList = gson.fromJson(jsonString, listType);
//            for (BIN bin : binList) {
//                importBIN(bin);
////                Log.i(TAG, "BIN Name : " + bin.get());
//            }
//            RemoveActionList(apiModel.getName());
//        }else if(apiModel.getName().equalsIgnoreCase(ApiModel.GETWSGOODSINVENTORYLIST)) {
//            jsonString = apiModel.getMessage();
//            Type listType = new TypeToken<ArrayList<GoodsInventory>>() {}.getType();
//            List<GoodsInventory> goodsInventoryList = gson.fromJson(jsonString, listType);
//            for (GoodsInventory goodsInventory : goodsInventoryList) {
//                importGoodsInventory(goodsInventory);
////                Log.i(TAG, "BIN Name : " + bin.get());
//            }
//            RemoveActionList(apiModel.getName());
//        }else if(apiModel.getName().equalsIgnoreCase(ApiModel.GETWSPRODUCTGROUPLIST)) {
//            jsonString = apiModel.getMessage();
//            Type listType = new TypeToken<ArrayList<BIN>>() {}.getType();
//            List<BIN> binList = gson.fromJson(jsonString, listType);
//            for (BIN bin : binList) {
//                importBIN(bin);
////                Log.i(TAG, "BIN Name : " + bin.get());
//            }
//            RemoveActionList(apiModel.getName());
//        }
            else {
                RemoveActionList(apiModel.getName());
            }

            if (appContext.getActionLists().size() > 0) {
                ActionList actionList = appContext.getActionLists().get(0);
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

                jsonString = gson.toJson(params);
                Log.i(TAG, "dosync: " + jsonString);
                ApiModel apimodel = new ApiModel(1, actionList.getActionname(), ApiModel.TYPE_GET, jsonString);
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
            }
        }
    }

    public void RemoveActionList(String actionname) {
        for (ActionList actionList : appContext.getActionLists()) {
            if (actionList.getActionname().equalsIgnoreCase(actionname)) {
                appContext.getActionLists().remove(actionList);
                break;
            }
        }
    }

    private void importTableToDelete(TableToDelete tableList) {

        dbaccess = DbAccess.getInstance();
        try {
            dbaccess.deleteData(tableList.getTablename());
        } catch (Exception e) {
            Log.i(TAG, "importTableToDelete: " + tableList.getTablename());
        }
    }

    private void importSolutions(Solution solution) {
        dbaccess = DbAccess.getInstance();
        AdministrationSolutions administrationSolutions = new AdministrationSolutions();
        administrationSolutions.setSolution_name(solution.getSolutionName());
        administrationSolutions.setActive(solution.getActive());
        dbaccess.insertAdministrationSolutions(administrationSolutions);

    }

    private void importSettings(Settings settings) {
        dbaccess = DbAccess.getInstance();
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
        dbaccess.insertSettings(administrationSettings);

    }

    private void importWsDashboard(WsDashboard wsDashboard) {
        dbaccess = DbAccess.getInstance();
        AdministrationWsdashboard administrationWsdashboard = new AdministrationWsdashboard();
        administrationWsdashboard.setId(wsDashboard.getId());
        administrationWsdashboard.setTitle(wsDashboard.getTitle());
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

    }

    private void importStaff(Staff wsStaff) {
        dbaccess = DbAccess.getInstance();
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

    }

    private void importLocations(Location wsLocation) {
        dbaccess = DbAccess.getInstance();
        AdministrationLocations administrationLocations = new AdministrationLocations();
        administrationLocations.setId(wsLocation.getId());
        administrationLocations.setLoc_name(wsLocation.getLocName());
        administrationLocations.setLoc_addr(wsLocation.getLocAddr());
        administrationLocations.setLatitude(wsLocation.getLatitude());
        administrationLocations.setLongitude(wsLocation.getLongitude());
        administrationLocations.setReceiving_bin(wsLocation.getReceivingBin());
        administrationLocations.setActive(wsLocation.getActive());
        dbaccess.insertAdministrationLocation(administrationLocations);

    }

    private void importRole(Role wsRole) {
        dbaccess = DbAccess.getInstance();
        AdministrationRole administrationRole = new AdministrationRole();
        administrationRole.setId(wsRole.getId());
        administrationRole.setRole_name(wsRole.getRoleName());
        administrationRole.setActive(wsRole.getActive());
        dbaccess.insertAdministrationRole(administrationRole);

    }

    private void importUOM(UOM wsUOM) {
        dbaccess = DbAccess.getInstance();
        InventoryUOM inventoryUOM = new InventoryUOM();
        inventoryUOM.setUom(wsUOM.getUom());
        inventoryUOM.setBaseqty(Double.parseDouble(wsUOM.getBaseqty()));
        inventoryUOM.setProduct_id(wsUOM.getProduct());
        inventoryUOM.setActive(wsUOM.getActive());
        dbaccess.insertInventoryUOM(inventoryUOM);

    }

    private void importGoldUOM(GoldUOM wsGoldUOM) {
        dbaccess = DbAccess.getInstance();
        InventoryGoldUOM inventoryGoldUOM = new InventoryGoldUOM();
        inventoryGoldUOM.setUom(wsGoldUOM.getUom());
        inventoryGoldUOM.setBaseqty(Double.parseDouble(wsGoldUOM.getBaseqty()));
        inventoryGoldUOM.setGold_id(wsGoldUOM.getGold());
        inventoryGoldUOM.setActive(wsGoldUOM.getActive());
        dbaccess.insertInventoryGoldUOM(inventoryGoldUOM);

    }

    private void importGold(Gold wsGold) {
        dbaccess = DbAccess.getInstance();
        InventoryGold inventoryGold = new InventoryGold();
        inventoryGold.setId(wsGold.getId());
        inventoryGold.setCode(wsGold.getCode());
        inventoryGold.setName(wsGold.getName());
        inventoryGold.setGoldtype(wsGold.getGoldtype());
        inventoryGold.setSaleprice(Double.parseDouble(wsGold.getSaleprice()));
        inventoryGold.setPurchaseprice(Double.parseDouble(wsGold.getPurchaseprice()));
        inventoryGold.setCost(Double.parseDouble(wsGold.getCost()));
        inventoryGold.setTax1(Double.parseDouble(wsGold.getTax1()));
        inventoryGold.setTax2(Double.parseDouble(wsGold.getTax2()));
        inventoryGold.setActive(wsGold.getActive());
        dbaccess.insertInventoryGold(inventoryGold);

    }

    private void importProduct(Product wsProduct) {
        dbaccess = DbAccess.getInstance();
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
        dbaccess.insertInventory_products(inventory_products);

    }

    private void importProductGroup(ProductGroup wsProductGroup) {
        dbaccess = DbAccess.getInstance();
        InventoryProductGroup inventoryProductGroup = new InventoryProductGroup();
        inventoryProductGroup.setId(Long.parseLong(wsProductGroup.getId()));
        inventoryProductGroup.setName(wsProductGroup.getName());
        inventoryProductGroup.setActive(wsProductGroup.getActive());
        dbaccess.insertInventoryProductGroup(inventoryProductGroup);

    }

    private boolean importBIN(BIN wsBIN) {
        dbaccess = DbAccess.getInstance();
        InventoryBIN inventoryBIN = new InventoryBIN();
        inventoryBIN.setId(wsBIN.getId());
        inventoryBIN.setBin_name(wsBIN.getBinName());
        inventoryBIN.setBin_description(wsBIN.getBinDescription());
        inventoryBIN.setBin_type(wsBIN.getBinType());
        inventoryBIN.setBarcode(wsBIN.getBarcode());
        inventoryBIN.setTag(wsBIN.getTag());
        inventoryBIN.setActive(wsBIN.getActive());
        inventoryBIN.setTs(wsBIN.getTs());

        long l = dbaccess.insertInventoryBIN(inventoryBIN);
        return (l > 0);

    }

    private boolean importProductSerial(ProductSerial wsProductSerial) {
        dbaccess = DbAccess.getInstance();
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
        inventory_productserial.setIs_delete(Boolean.parseBoolean(wsProductSerial.getIsDelete()));
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

        long l = dbaccess.insertInventory_productserial(inventory_productserial);
        return (l > 0);

    }

    private boolean importSmith(Smith wsSmith) {
        dbaccess = DbAccess.getInstance();
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
        manufacturingsmith.setDate_end(wsSmith.getDateEnd());
        manufacturingsmith.setIs_smith2(wsSmith.getIsSmith2());
        manufacturingsmith.setInhandjob(wsSmith.getInhandjob());
        manufacturingsmith.setActive(wsSmith.getActive());
        manufacturingsmith.setGoldsaving(Double.parseDouble(wsSmith.getGoldsaving()));
        manufacturingsmith.setInhandjob(wsSmith.getInhandjob());
        manufacturingsmith.setPhoto(wsSmith.getPhoto());

        long l = dbaccess.insertManufacturingSmith(manufacturingsmith);
        return (l > 0);

    }


    private boolean importPallet(Pallet wsPallet) {
        dbaccess = DbAccess.getInstance();
        InventoryPallet inventoryPallet = new InventoryPallet();
        inventoryPallet.setId(wsPallet.getId());
        inventoryPallet.setPallet_name(wsPallet.getPalletName());
        inventoryPallet.setPallet_description(wsPallet.getPalletDescription());
        inventoryPallet.setPallet_type(wsPallet.getPalletType());
        inventoryPallet.setBarcode(wsPallet.getBarcode());
        inventoryPallet.setTag(wsPallet.getTag());
        inventoryPallet.setLocation_id(wsPallet.getTag());
        inventoryPallet.setWeight(Double.parseDouble(wsPallet.getWeight()));
        inventoryPallet.setIs_used(wsPallet.getActive());
        inventoryPallet.setActive(wsPallet.getActive());
        inventoryPallet.setTs(wsPallet.getTs());

        long l = dbaccess.insertInventoryPallet(inventoryPallet);
        return (l > 0);

    }


    private boolean importGoodsInventory(GoodsInventory wsgoodsInventory) {
        dbaccess = DbAccess.getInstance();
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
        long l = dbaccess.insertGoodsInventory(goodsinventory);
        return (l > 0);
    }


}
