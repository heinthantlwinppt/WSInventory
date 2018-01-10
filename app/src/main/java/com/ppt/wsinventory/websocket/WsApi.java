package com.ppt.wsinventory.websocket;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.ppt.wsinventory.GlobalVariables;
import com.ppt.wsinventory.model.ActionList;
import com.ppt.wsinventory.model.AdministrationRole;
import com.ppt.wsinventory.model.AdministrationSettings;
import com.ppt.wsinventory.model.AdministrationSolutions;
import com.ppt.wsinventory.model.AdministrationStaff;
import com.ppt.wsinventory.model.AdministrationWsdashboard;
import com.ppt.wsinventory.model.ApiModel;
import com.ppt.wsinventory.model.ApiParam;
import com.ppt.wsinventory.model.Role;
import com.ppt.wsinventory.model.Settings;
import com.ppt.wsinventory.model.Solution;
import com.ppt.wsinventory.model.Staff;
import com.ppt.wsinventory.model.TableToDelete;
import com.ppt.wsinventory.model.WsDashboard;
import com.ppt.wsinventory.services.WsSyncService;
import com.ppt.wsinventory.util.HexStringConverter;
import com.ppt.wsinventory.util.JsonHelper;
import com.ppt.wsinventory.util.Utility;
import com.ppt.wsinventory.wsdb.DbAccess;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by User on 30/12/2017.
 */

public class WsApi  {
    public final String WEBSOCKET_URL = "ws://52.230.10.246:9090/wsmessage";
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

    public void TestWebSocket(){

            Intent intent = new Intent(mContext, WsSyncService.class);
            intent.putExtra(WsSyncService.SERVICE_TYPE, WsSyncService.SERVICE_LOGIN);
            mContext.startService(intent);
    }
    public void getActionList(){
        Intent intent = new Intent(mContext, WsSyncService.class);
        intent.putExtra(WsSyncService.SERVICE_TYPE, WsSyncService.SERVICE_REQUEST);
        mContext.startService(intent);
    }
    public void doSync(){
        Gson gson = JsonHelper.getGson();
        String jsonString = "";
        String response = appContext.getResponseMessage();
        response = HexStringConverter.getHexStringConverterInstance().hexToString(response);
        ApiModel apiModel = gson.fromJson(response,ApiModel.class);
        boolean hasmore = false;
        if(apiModel.getName().equalsIgnoreCase(ApiModel.GETTABLESTODELETE)){
            jsonString = apiModel.getMessage();
            Type listType = new TypeToken<ArrayList<TableToDelete>>(){}.getType();
            List<TableToDelete> tableToDeletes = gson.fromJson(jsonString, listType);
            for (TableToDelete toDelete: tableToDeletes){

//                dbaccess.deleteData(toDelete.getTablename());
                importTableToDelete(toDelete);
                Log.i(TAG, "Delete Table: " + toDelete.getTablename());
            }
            RemoveActionList(apiModel.getName());

        }else if(apiModel.getName().equalsIgnoreCase(ApiModel.GETSOLUTIONS)){
            jsonString = apiModel.getMessage();
            Type listType = new TypeToken<ArrayList<Solution>>(){}.getType();
            List<Solution> solutionList = gson.fromJson(jsonString, listType);
            for (Solution solution: solutionList){
                importSolutions(solution);
                Log.i(TAG, "Solution Name : " + solution.getSolutionName());
            }
            RemoveActionList(apiModel.getName());
        }else if(apiModel.getName().equalsIgnoreCase(ApiModel.GETSETTINGS)){
            jsonString = apiModel.getMessage();
            Type listType = new TypeToken<ArrayList<Settings>>(){}.getType();
            List<Settings> settingsList = gson.fromJson(jsonString, listType);
            for (Settings settings: settingsList){
                importSettings(settings);
                Log.i(TAG, "setting id : " + settings.getDeviceId());
            }
            RemoveActionList(apiModel.getName());
        }else if(apiModel.getName().equalsIgnoreCase(ApiModel.GETWSDASHBOARD)){
            jsonString = apiModel.getMessage();
            Type listType = new TypeToken<ArrayList<WsDashboard>>(){}.getType();
            List<WsDashboard> dashboardsList = gson.fromJson(jsonString, listType);
            for (WsDashboard dashboard: dashboardsList){
                importWsDashboard(dashboard);
                Log.i(TAG, "dashboard Name : " + dashboard.getActionname());
            }
            RemoveActionList(apiModel.getName());
        }
        else if(apiModel.getName().equalsIgnoreCase(ApiModel.GETWSSTAFF)) {
            jsonString = apiModel.getMessage();
            Type listType = new TypeToken<ArrayList<Staff>>() {}.getType();
            List<Staff> staffsList = gson.fromJson(jsonString, listType);
            for (Staff staff : staffsList) {
                importStaff(staff);
                Log.i(TAG, "Staff Name : " + staff.getStaffName());
            }
            RemoveActionList(apiModel.getName());
        }
        else if(apiModel.getName().equalsIgnoreCase(ApiModel.GETWSROLE)) {
            jsonString = apiModel.getMessage();
            Type listType = new TypeToken<ArrayList<Role>>() {}.getType();
            List<Role> roleList = gson.fromJson(jsonString, listType);
            for (Role role : roleList) {
                importRole(role);
                Log.i(TAG, "Role Name : " + role.getRoleName());
            }
            RemoveActionList(apiModel.getName());
        }

        if(appContext.getActionLists().size() > 0) {
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
                    new ApiParam("deviceid",appContext.getDeviceid())
            );

            jsonString = gson.toJson(params);
            Log.i(TAG, "dosync: " + jsonString);
            ApiModel apimodel= new ApiModel(1,actionList.getActionname(), ApiModel.TYPE_GET, jsonString );
            jsonString = gson.toJson(apimodel);
            String req = "";
            try {
                req =  HexStringConverter.getHexStringConverterInstance().stringToHex(jsonString);
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
    public void RemoveActionList(String actionname){
        for(ActionList actionList: appContext.getActionLists()){
            if(actionList.getActionname().equalsIgnoreCase(actionname)){
                appContext.getActionLists().remove(actionList);
                break;
            }
        }
    }

    private void importTableToDelete(TableToDelete tableList) {

        dbaccess = DbAccess.getInstance();
        dbaccess.deleteData(tableList.getTablename());
        }

    private void importSolutions(Solution solution) {
        dbaccess = DbAccess.getInstance();
        AdministrationSolutions administrationSolutions= new AdministrationSolutions();
        administrationSolutions.setSolution_name(solution.getSolutionName());
        administrationSolutions.setActive(solution.getActive());
        dbaccess.insertAdministrationSolutions(administrationSolutions);

    }

    private void importSettings(Settings settings) {
        dbaccess = DbAccess.getInstance();
        AdministrationSettings administrationSettings= new AdministrationSettings();
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
        AdministrationWsdashboard administrationWsdashboard= new AdministrationWsdashboard();
        administrationWsdashboard.setId(wsDashboard.getId());
        administrationWsdashboard.setTitle(wsDashboard.getTitle());
        administrationWsdashboard.setActionname(wsDashboard.getActionname());
        administrationWsdashboard.setGroupname(wsDashboard.getGroupname());
        administrationWsdashboard.setImage(wsDashboard.getImage());
        administrationWsdashboard.setTimestamp(wsDashboard.getTimestamp());
        administrationWsdashboard.setDisplayno(wsDashboard.getDisplayno());
        administrationWsdashboard.setIs_delete(wsDashboard.getIsDelete());
        administrationWsdashboard.setScreen_id(wsDashboard.getScreen());

        if(wsDashboard.getIsDelete() == true){
            dbaccess.deleteAdministrationWsdashboard(AdministrationWsdashboard.TABLE_ADMINISTRATION_WSDASHBOARD,wsDashboard.getId());
        }else {
            dbaccess.insertAdministrationWsdashboard(administrationWsdashboard);
        }

    }

    private void importStaff(Staff wsStaff) {
        dbaccess = DbAccess.getInstance();
        AdministrationStaff administrationStaff= new AdministrationStaff();
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

    private void importRole(Role wsRole) {
        dbaccess = DbAccess.getInstance();
        AdministrationRole administrationRole= new AdministrationRole();
        administrationRole.setId(wsRole.getId());
        administrationRole.setRole_name(wsRole.getRoleName());
        administrationRole.setActive(wsRole.getActive());
        dbaccess.insertAdministrationRole(administrationRole);

    }



}
