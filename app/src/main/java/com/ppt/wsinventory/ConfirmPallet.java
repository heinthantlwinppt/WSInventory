package com.ppt.wsinventory;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ppt.wsinventory.common.GlobalBus;
import com.ppt.wsinventory.common.WsEvents;
import com.ppt.wsinventory.ws_rfidreader.AppHelper;

import org.greenrobot.eventbus.Subscribe;

import java.io.UnsupportedEncodingException;
import java.util.List;

import cipherlab.rfid.device1800.config.BluetoothSocketConnector;
import cipherlab.rfid.device1800.config.DeviceResponse;
import cipherlab.rfid.device1800.config.EPCData;
import cipherlab.rfid.device1800.config.IConfigEventListener;
import cipherlab.rfid.device1800.config.InventoryData;

public class ConfirmPallet extends AppCompatActivity {
    private GlobalVariables appContext;
    private IntentFilter filter;
    private List<String> list;
    private listener mylistener;
    private boolean mSwitch =false ;
    private String mBtn = "Action";
    private boolean[] mSelect;
    private boolean displayflag = false;
    private int OriginLength = 0;
    public static final String TAG = "Ws-ConfirmPallet";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_pallet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        StateManager.getInstance().setCurrent_activity(ConfirmPallet.class.getName());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        appContext = (GlobalVariables) getApplicationContext();
        AppHelper.mConnector = new BluetoothSocketConnector(this);
        mylistener = new listener();
        AppHelper.mConnector.addListener(mylistener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GlobalBus.getBus().register(this);
        initLogiCodeRFID();
    }

    @Override
    protected void onStop() {
        GlobalBus.getBus().unregister(this);
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_conform_bin, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    private void ReadEPC() {
        if (mBtn.contains("Cancel")) {
            displayflag = false;
            AppHelper.InventoryCancel();
        } else {
            ClearDisplayData();
            displayflag = true;
            AppHelper.Inventory();

//            if (mSwitch.isChecked() == false) {
            mBtn ="Cancel";
//            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_logicode_reader:
                if(mSwitch){
                    mSwitch=false;
                    ChangeControl();
                }else{
                    ReadEPC();
                }


        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    protected void onResume() {
        super.onResume();
        StateManager.getInstance().setCurrent_activity(ConfirmPallet.class.getName());
    }
    private void initLogiCodeRFID() {
        if (AppHelper.mConnector.IsBlueToothTurnOn() == true) {
            list = AppHelper.mConnector.GetPairedDevice();

            if (list != null) {
                for(String device : list){
                    if(TextUtils.equals(device,appContext.getWssetting().getRfidreader_name())){
                        StateManager.getInstance().setIslogicoderfid(true);
                        break;
                    }
                    Log.i(TAG, "onStart: " + device );
                }
                if(StateManager.getInstance().IsLogicoderfid()){
                    AppHelper.InitialObject();
                    AppHelper.mConnector.Device = appContext.getWssetting().getRfidreader_name();
                    boolean result = AppHelper.mConnector.Connect();
                    if (result == true) {
//                        AppHelper.SwitchCommandMode();
//
//                        mSelect = new boolean[4];
//                        mSelect[0] = true;
//                        mSelect[1] = false;
//                        mSelect[2] = false;
//                        mSelect[3] = true;
//
//                        ChangeControl();
                    }
                }
//                adapter = new ArrayAdapter<String>(this,
//                        R.layout.dropdown_item, list);
//                mSpinner = (Spinner) findViewById(R.id.spinner_encoding);
//                mSpinner.setAdapter(adapter);
//                mBtnConnect.setEnabled(true);
            } else {
//                mBtnConnect.setEnabled(false);
            }
        } else {
//            mBtnConnect.setEnabled(false);
//            String temp = getString(R.string.MSG_BtTurnOnError);
//            Toast t = Toast.makeText(DevicePickerActivity.this, temp,
//                    Toast.LENGTH_SHORT);
//            t.show();
        }
    }

    private void ChangeControl() {
        if (mSwitch == true) {
            if (mSelect[0]) {
                AppHelper.ChangeDirectControl(true);
            } else if (mSelect[1]) {
                AppHelper.ChangeDirectControl(false);
            } else if (mSelect[2]) {
                AppHelper.ChangeDirectControl(false);
            } else if (mSelect[3]) {
                AppHelper.ChangeDirectControl(false);
            }
        } else {
            AppHelper.ChangeDirectControl(false);
        }
    }
    private void onReadRFID(String data){
        GlobalBus.getBus().post(
                new WsEvents.EventReadRFID(data)
        );
    }
    public class listener implements IConfigEventListener {
        public void onConnected() {
            AppHelper.SwitchCommandMode();
            mSwitch = true;
            mSelect = new boolean[4];
            mSelect[0] = true;
            mSelect[1] = false;
            mSelect[2] = false;
            mSelect[3] = true;

            ChangeControl();

            AppHelper.GetHandle();
            DeviceConnected();
        }

        public void onDisconnected() {
            AppHelper.mConnector.removeListener(mylistener);
            AppHelper.ReleaseObject();
        }

        public void onDeviceEvent(int value) {
            if ((value & 0xFF20) == 0x0020) {
                AppHelper.SwitchStatus = !AppHelper.SwitchStatus;
            }
        }

        @Override
        public void onDataTransmission(EPCData data) {
            if (mSwitch == true) {
                if (mSelect[0]) {
                    ClearDisplayData();
                    onReadRFID(data.TagData);
                } else if (mSelect[1]) {
                    if (data.Original == null)
                        return;

//                    String temp = EncodingUtils.getString(data.Original, 0,
//                            data.Original.length, "ASCII");
//                    if (temp.contains("#@TRIGOFF\r")) {
//                        ReadTID();
//                    }
                } else if (mSelect[2]) {
                    if (data.Original == null)
                        return;

//                    String temp = EncodingUtils.getString(data.Original, 0,
//                            data.Original.length, "ASCII");
//                    if (temp.contains("#@TRIGOFF\r")) {
//                        ReadUSER();
//                    }
                } else if (mSelect[3]) {
                    if (data.Original == null)
                        return;

//                    String temp = EncodingUtils.getString(data.Original, 0,
//                            data.Original.length, "ASCII");
//                    if (temp.contains("#@TRIGOFF\r")) {
//                        WriteUSER();
//                    }
                }
            }
        }

        @Override
        public void onInventory(InventoryData data, DeviceResponse response) {
            switch (response) {
                case OperationSuccess:
                    if (data != null && mSelect[0]) {
                        onReadRFID(data.EPCData);
//                        mReceive[0].setText(data.EPCData);
                    }
                    break;
                case OperationFinish: {
                    Toast t = Toast.makeText(ConfirmPallet.this,
                            "STR_Success", Toast.LENGTH_SHORT);
                    t.show();
                    if (mSwitch == false) {
                        mBtn="Action";
//                        mBtn.setEnabled(true);
                    }
                    break;
                }
                case OperationFail:
                case DeviceTimeOut: {
                    if (displayflag == true) {
                        Toast t = Toast.makeText(ConfirmPallet.this,
                                "STR_Failed", Toast.LENGTH_SHORT);
                        t.show();
                    }
                    if (mSwitch == false) {
                        mBtn="Action";
//                        mBtn.setEnabled(true);
                    }
                    break;
                }
                case DeviceBusy:
                    Toast t = Toast.makeText(ConfirmPallet.this, "STR_Busy",
                            Toast.LENGTH_SHORT);
                    t.show();
                    if (mSwitch == false) {
                        mBtn="Action";
//                        mBtn.setEnabled(true);
                    }
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onReadTag(byte[] data, DeviceResponse response) {
            switch (response) {
                case OperationSuccess: {
                    if (mSelect[1]) {
                        if (data != null) {
                            String temp;
                            try {
                                temp = Hex2Binary(data);
                            } catch (UnsupportedEncodingException e) {
                                temp = null;
                                e.printStackTrace();
                            }
                            onReadRFID("Need to check -" + temp);
                        }
                    } else if (mSelect[2]) {
                        if (data != null) {
                            String temp;
                            try {
                                temp = Hex2Binary(data);
                                OriginLength = temp.length();
                            } catch (UnsupportedEncodingException e) {
                                temp = null;
                                e.printStackTrace();
                            }
                            onReadRFID("Need to check2 -" + temp);
                        }
                    } else if (mSelect[3]) {

                    }
                    break;
                }
                case OperationFinish: {
                    Toast t = Toast.makeText(ConfirmPallet.this,
                            "STR_Success", Toast.LENGTH_SHORT);
                    t.show();
                    if (mSwitch== false) {
                        mBtn="Action";
//                        mBtn.setEnabled(true);
                    }
                    break;
                }
                case OperationFail:
                case DeviceTimeOut: {
                    Toast t = Toast.makeText(ConfirmPallet.this,
                            "STR_Failed", Toast.LENGTH_SHORT);
                    t.show();
                    if (mSwitch == false) {
                        mBtn="Action";
//                        mBtn.setEnabled(true);
                    }
                    break;
                }
                case DeviceBusy:
                    Toast t = Toast.makeText(ConfirmPallet.this, "STR_Busy",
                            Toast.LENGTH_SHORT);
                    t.show();
                    if (mSwitch == false) {
                        mBtn="Action";
//                        mBtn.setEnabled(true);
                    }
                    break;
                default:
                    break;
            }
        }
    }
    final static byte[] HEX_CHAR_TABLE = { (byte) '0', (byte) '1', (byte) '2',
            (byte) '3', (byte) '4', (byte) '5', (byte) '6', (byte) '7',
            (byte) '8', (byte) '9', (byte) 'a', (byte) 'b', (byte) 'c',
            (byte) 'd', (byte) 'e', (byte) 'f' };

    public static String Hex2Binary(byte[] raw)
            throws UnsupportedEncodingException {
        byte[] hex = new byte[2 * raw.length];
        int index = 0;

        for (byte b : raw) {
            int v = b & 0xFF;
            hex[index++] = HEX_CHAR_TABLE[v >>> 4];
            hex[index++] = HEX_CHAR_TABLE[v & 0xF];
        }
        return new String(hex, "ASCII");
    }
    private void ClearDisplayData() {}
    private void DeviceConnected() {
        Toast.makeText(this, "RFID Reader is Connected.", Toast.LENGTH_SHORT).show();
//        if (mNormal.isChecked() == true) {
//            Intent intent = new Intent();
//            intent.setClass(DevicePickerActivity.this, MainActivity.class);
//            startActivity(intent);
//        } else {
//            Intent intent = new Intent();
//            intent.setClass(DevicePickerActivity.this, AutoTestActivity.class);
//            startActivity(intent);
//        }
    }
    @Subscribe
    public void CloseActivity(WsEvents.EventOpenScreen e){
        if(e.getActionname().equals("close_folder"))
            onBackPressed();
    }
    @Override
    public void onBackPressed() {
        AppHelper.mConnector.Disconnect();
        super.onBackPressed();
    }
}
