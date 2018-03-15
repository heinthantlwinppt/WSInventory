package com.ppt.wsinventory;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cipherlab.barcode.GeneralString;
import com.cipherlab.barcode.ReaderManager;
import com.cipherlab.barcode.decoder.BcReaderType;
import com.cipherlab.barcode.decoder.KeyboardEmulationType;
import com.cipherlab.barcode.decoderparams.ReaderOutputConfiguration;
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

public class ConfirmBin extends AppCompatActivity {
    // IntentFilter is used to get the intent we need.
    private GlobalVariables appContext;
    private IntentFilter filter;
    private List<String> list;
    private listener mylistener;
    private boolean mSwitch =true ;
    private boolean[] mSelect;
    private boolean displayflag = false;
    private int OriginLength = 0;
    // A very important class used to communicate with driver and  service.
    private com.cipherlab.barcode.ReaderManager mReaderManager;
    public static final String TAG = "WS-ConfirmCounter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conform_bin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        StateManager.getInstance().setCurrent_activity(ConfirmBin.class.getName());
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                onBackPressed();
//            }
//        });
        appContext = (GlobalVariables) getApplicationContext();
        // ***************************************************//
        // ***************************************************//
        mReaderManager = ReaderManager.InitInstance(this);

        // ***************************************************//
        // Register for the IntentFilter whose content is the
        // com.cipherlab.barcode.GeneralString.Intent_SOFTTRIGGER_DATA string
        // Later, when myDataReceiver, a BroadcastReceiver class, receives the intent coming from service, it will then be able to deal with something else.
        // ***************************************************//
        filter = new IntentFilter();
        filter.addAction(com.cipherlab.barcode.GeneralString.Intent_SOFTTRIGGER_DATA);
        filter.addAction(com.cipherlab.barcode.GeneralString.Intent_PASS_TO_APP);
        filter.addAction(com.cipherlab.barcode.GeneralString.Intent_READERSERVICE_CONNECTED);
        registerReceiver(myDataReceiver, filter);

        AppHelper.mConnector = new BluetoothSocketConnector(this);
        mylistener = new listener();
        AppHelper.mConnector.addListener(mylistener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_conform_bin, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_logicode_reader:
                initLogiCodeRFID();


        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // ***************************************************//
        // Unregister Broadcast Receiver before closing the APP.
        // ***************************************************//
        unregisterReceiver(myDataReceiver);

        // ***************************************************//
        // Remember to release before leaving.
        // ***************************************************//
        if (mReaderManager != null)
        {
            mReaderManager.Release();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        StateManager.getInstance().setCurrent_activity(ConfirmBin.class.getName());
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
    /// Here is the all API examples
    private void ExeSampleCode()
    {

        // ***************************************************//
        // 1. Get the barcode scanner type.
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{
				com.cipherlab.barcode.decoder.BcReaderType myReaderType =  mReaderManager.GetReaderType();
				e1.setText(myReaderType.toString());
			}
			*/
        }

        // ***************************************************//
        // 2. Disable/enable the trigger scan function.
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{
				com.cipherlab.barcode.decoder.ClResult clRet = mReaderManager.SetActive(false);
				boolean bRet = mReaderManager.GetActive();
				clRet = mReaderManager.SetActive(false);
				bRet = mReaderManager.GetActive();
			}
			*/
        }

        // ***************************************************//
        // 3. Get/set output data format (keystroke).
        // Determine whether to prefix/suffix string, carrier return, decoder type, etc. to the output data.
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{
				com.cipherlab.barcode.decoderparams.ReaderOutputConfiguration settings = new ReaderOutputConfiguration();

				// step2: Immediately set the new created object above means to set all values to default
				mReaderManager.Set_ReaderOutputConfiguration(settings);

				// step3: Read it again for verification.
				mReaderManager.Get_ReaderOutputConfiguration(settings);

				// step4:  Skip step 2 & 3. You can immediately change settings.
				settings.autoEnterWay = OutputEnterWay.SuffixData;
				settings.autoEnterChar = OutputEnterChar.Return;
				if (settings.enableKeyboardEmulation == Enable_State.FALSE)
					settings.enableKeyboardEmulation = Enable_State.TRUE;
				settings.showCodeLen = Enable_State.TRUE;
				settings.showCodeType= Enable_State.TRUE;
				settings.szPrefixCode=" Data->";
				settings.szSuffixCode="! ";
				settings.useDelim = ':';

				// If successful, it returns ClResult.S_ERR. Otherwise, return other values.
				ClResult clRet = mReaderManager.Set_ReaderOutputConfiguration(settings);
				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Set_ReaderOutputConfiguration was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Set_ReaderOutputConfiguration was InvalidParameter", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Set_ReaderOutputConfiguration was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Set_ReaderOutputConfiguration was Ok", Toast.LENGTH_SHORT).show();
				}
			*/
        }

        // ***************************************************//
        // 4. Get/set notification.
        // Determine whether to beep sound, flash LED, or vibrate when display barcode data.
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{
				com.cipherlab.barcode.decoderparams.NotificationParams settings = new NotificationParams();

				// step2: Get the current values by the newly created object.
				mReaderManager.Get_NotificationParams(settings);

				// step3: Change values.
				settings.ReaderBeep = BeepType.Hwandsw;
				settings.enableVibrator = Enable_State.FALSE;
				settings.ledDuration = 500; //ms
				settings.vibrationCounter = 1; //500ms * count

				// step4: Overwrite settings with modified values.
				ClResult clRet = mReaderManager.Set_NotificationParams(settings);

				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Set_NotificationParams was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Set_NotificationParams was InvalidParameter", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Set_NotificationParams was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Set_NotificationParams was Ok", Toast.LENGTH_SHORT).show();
			}
			*/
        }

        // ***************************************************//
        // 5. get/set UserPreference
        // e.g. scanning time, security level, redundancy level, etc.
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{
				com.cipherlab.barcode.decoder.BcReaderType myReaderType =  mReaderManager.GetReaderType();

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.UserPreference settings = new UserPreference();

				// step2: Get the current values by the newly created object.
				mReaderManager.Get_UserPreferences(settings);

				// step3: Read or change values
				if (Enable_State.NotSupport == settings.displayMode)
				{
					// Some settings are not necessarily supported by the 1D scan engine. Users can verify them respectively.
					//1D is not support
				}
				settings.addonSecurityLevel = 7;
				settings.laserOnTime = 3000;
				settings.negativeBarcodes = InverseType.AutoDetect;
				settings.scanAngle = ScanAngleType.Wide;
				settings.securityLevel=SecurityLevel.Two;
				settings.pickListMode=Enable_State.TRUE;
				settings.timeoutBetweenSameSymbol = 1000;
				settings.displayMode = Enable_State.TRUE;
				settings.redundancyLevel = RedundancyLevel.Four;
				settings.transmitCodeIdChar = TransmitCodeIDType.AimCodeId;
				settings.triggerMode = TriggerType.ContinuousMode;
				settings.interCharGapSize = InterCharacterGapSize.Normal;
				settings.decodingAimingPattern = Enable_State.TRUE;
				settings.decodingIllumination  = Enable_State.FALSE;


				//other settings are omitted.

				// Overwrite settings with modified values.
				ClResult clRet = mReaderManager.Set_UserPreferences(settings);
				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Get_UserPreferences was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Get_UserPreferences was InvalidParameter",	Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Get_UserPreferences was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Get_UserPreferences was successful", Toast.LENGTH_SHORT).show();
				}
			*/
        }


        // ***************************************************//
        // 6. Read all symbologies to check whether they are enabled.
        //  Enable/disable a specific, some or all symbologies.
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{
				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoder.Decoders settings = new Decoders();

				// step2: Get the status of all Decoders (Symbology).
				mReaderManager.Get_Decoders_Status(settings);

				// step3: Check whether the symbology is supported.
				if (Enable_State.NotSupport == settings.enableAustrailianPostal) {
					// 1D is not support
				}

				// e.g. disable all symbologies
				settings.enableAustrailianPostal = Enable_State.TRUE;
				settings.enableAztec = Enable_State.TRUE;
				settings.enableChinese2Of5 = Enable_State.TRUE;
				settings.enableCodabar = Enable_State.TRUE;
				settings.enableCode11 = Enable_State.TRUE;
				settings.enableCode128 = Enable_State.TRUE;
				settings.enableCode39 = Enable_State.TRUE;
				settings.enableCode93 = Enable_State.TRUE;
				settings.enableCompositeCC_AB = Enable_State.TRUE;
				settings.enableCompositeCC_C = Enable_State.TRUE;
				settings.enableCompositeTlc39 = Enable_State.TRUE;
				settings.enableDataMatrix = Enable_State.TRUE;
				settings.enableDutchPostal = Enable_State.TRUE;
				settings.enableEanJan13 = Enable_State.TRUE;
				settings.enableEanJan8 = Enable_State.TRUE;
				settings.enableGs1128 = Enable_State.TRUE;
				settings.enableGs1DataBar14 = Enable_State.TRUE;
				settings.enableGs1DataBarExpanded = Enable_State.TRUE;
				settings.enableGs1DataBarLimited = Enable_State.TRUE;
				settings.enableGs1DatabarToUpcEan = Enable_State.TRUE;

				settings.enableIndustrial2Of5 = Enable_State.TRUE;
				settings.enableInterleaved2Of5 = Enable_State.TRUE;
				settings.enableIsbt128 = Enable_State.TRUE;
				settings.enableJapanPostal = Enable_State.TRUE;
				settings.enableKorean3Of5 = Enable_State.TRUE;
				settings.enableMatrix2Of5 = Enable_State.TRUE;
				settings.enableMaxiCode = Enable_State.TRUE;
				settings.enableMicroPDF417 = Enable_State.TRUE;
				settings.enableMicroQR = Enable_State.TRUE;
				settings.enableMsi = Enable_State.TRUE;
				settings.enablePDF417 = Enable_State.TRUE;
				settings.enableQRcode = Enable_State.TRUE;
				settings.enableTriopticCode39 = Enable_State.TRUE;

				settings.enableUccCoupon = Enable_State.TRUE;
				settings.enableUKPostal = Enable_State.TRUE;
				settings.enableUpcA = Enable_State.TRUE;
				settings.enableUpcE = Enable_State.TRUE;
				settings.enableUpcE1 = Enable_State.TRUE;
				settings.enableUPUFICSPostal = Enable_State.TRUE;
				settings.enableUSPlanet = Enable_State.TRUE;
				settings.enableUSPostnet = Enable_State.TRUE;
				settings.enableUSPSPostal = Enable_State.TRUE;

				// Overwrite settings with modified values
				ClResult clRet = mReaderManager.Set_Decoders_Status(settings);
				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Set_Decoders_Status was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Set_Decoders_Status was InvalidParameter",	Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Set_Decoders_Status was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Set_Decoders_Status was successful", Toast.LENGTH_SHORT).show();

				// Remember to set decoder status to default, or the scanning function will be affected next time.
				//mReaderManager.Set_Decoders_Status(new Decoders());
			}
			*/
        }


        // ***************************************************//
        // 7-0. Get/set the CodaBar symbology.
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{
				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.Codabar settings = new Codabar();

				// step2:Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings))
				{
					// checking statements
				}

				// step3: If supported, users can change values.
				settings.clsiEditing = Enable_State.FALSE;
				settings.enable = Enable_State.TRUE;
				settings.length1 = 4;
				settings.length2 = 55;
				settings.notisEditing = Enable_State.FALSE;
                settings.transmitCheckDigit = Enable_State.TRUE;

				// Overwrite settings with modified values
				ClResult clRet = mReaderManager.Set_Symbology(settings);
				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();
			}
			*/
        }

        // ***************************************************//
        // 7-1. Get/set the Industrial 2 Of 5 symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{
				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.Industrial2Of5 settings = new Industrial2Of5();

				// step2:Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings))
				{
					// checking statements
				}

				// step3: If supported, users can change values.
				settings.enable = Enable_State.TRUE;
				settings.length1 = 5;
				settings.length2 = 30;

				// Overwrite settings with modified values.
				ClResult clRet = mReaderManager.Set_Symbology(settings);
				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();
			}
			*/

        }

        // ***************************************************//
        // 7-2. Get/set the Interleaved 2 Of 5 symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{
				com.cipherlab.barcode.decoder.BcReaderType myReaderType =  mReaderManager.GetReaderType();

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.Interleaved2Of5 settings = new Interleaved2Of5();

				// step2:Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings))
				{
					// checking statements
				}

				// step3: If supported, users can change values.
				settings.enable = Enable_State.TRUE;
				settings.length1 = 5;
				settings.length2 = 30;
				settings.convertToEan13 = Enable_State.FALSE;
				settings.transmitCheckDigit =Enable_State.FALSE;


				settings.checkDigitVerification = I20f5CheckDigitVerification.OPCC;


				// Overwrite settings with modified values.
				ClResult clRet = mReaderManager.Set_Symbology(settings);
				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();

			}
			*/

        }

        // ***************************************************//
        // 7-3. Get/set the 2D symbologies via the 1D device.
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{
				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.Composite settings = new Composite();

				// step2:Check whether the symbology is supported by the barcode scanner.
				ClResult clResult = mReaderManager.Get_Symbology(settings);
				if (ClResult.Err_NotSupport == clResult)
				{
					// checking statements
					Toast.makeText(this, "Composite is not supported",
							Toast.LENGTH_SHORT).show();
				}

				// step3: If supported, users can change values.
				settings.enableCc_AB = Enable_State.FALSE;
				settings.enableCc_C = Enable_State.FALSE;
				settings.enableEmulationMode = Enable_State.FALSE;
				settings.enableTlc39 = Enable_State.TRUE;
				settings.enableUpcMode = UpcMode.NeverLinksUPC;


				// Overwrite settings with modified values. The returned results can be observed indicating whether the setting is successful or supported.
				ClResult clRet = mReaderManager.Set_Symbology(settings);
				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();

			}
			*/

        }

        // ***************************************************//
        //  7-4. Get/set the Chinese 2 of 5 symbology (similar to the previous one).
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{
				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.Chinese2Of5 settings = new Chinese2Of5();

				// step2:Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings))
				{
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}

				// step3: If supported, users can change values.
				settings.enable = Enable_State.TRUE;


				// Overwrite settings with modified values.
				ClResult clRet = mReaderManager.Set_Symbology(settings);
				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();

			}
			*/

        }

        // ***************************************************//
        // 7-5. Get/set the Matrix 2 of 5 symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.Matrix2Of5 settings = new Matrix2Of5();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if(ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings))
				{
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							 Toast.LENGTH_SHORT).show();
				}

				// step3: If supported, users can change values.
				settings.enable =Enable_State.TRUE;
				settings.checkDigitVerification = Enable_State.FALSE;
				settings.transmitCheckDigit = Enable_State.FALSE;
				settings.redundancy = Enable_State.TRUE;
				settings.length1 = 5;

				// Overwrite settings with modified values.
				ClResult clRet = mReaderManager.Set_Symbology(settings);
				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();
			}
			*/

        }

        // ***************************************************//
        //  7-6. Get/set the Code 39 symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.Code39 settings = new Code39();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings))
				{
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}

				// step3: If supported, users can change values.
				settings.enable = Enable_State.TRUE;
				settings.fullASCII = Enable_State.TRUE;
				settings.checkDigitVerification = Enable_State.FALSE;
				settings.transmitCheckDigit = Enable_State.FALSE;
				settings.convertToCode32 = Enable_State.FALSE;
				settings.convertToCode32Prefix = Enable_State.FALSE;

				// Overwrite settings with modified values.
				ClResult clRet = mReaderManager.Set_Symbology(settings);
				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();
			}
			*/

        }

        // ***************************************************//
        //  7-7. Get/set the TriopticCode 39 symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.TriopticCode39 settings = new TriopticCode39();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings))
				{
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}

				// step3: If supported, users can change values.
				settings.enable = Enable_State.FALSE;


				// Overwrite settings with modified values.
				ClResult clRet = mReaderManager.Set_Symbology(settings);
				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();

			}
			*/

        }

        // ***************************************************//
        //  7-8. Get/set the Code 93 symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{
				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.Code93 settings = new Code93();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings))
				{
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}

				// step3: If supported, users can change values.
				settings.enable = Enable_State.TRUE;
				settings.length1 = 10;
				settings.length2 = 20;


				// Overwrite settings with modified values.
				ClResult clRet = mReaderManager.Set_Symbology(settings);
				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();
			}
			*/

        }

        // ***************************************************//
        // 7-9. Get/set the ISBT 12 symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{
				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.ISBT128 settings = new ISBT128();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings))
				{
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}

				// step3: If supported, users can change values.
				settings.enable = Enable_State.FALSE;
				settings.concatenation = ISBTConcatenationType.Enable;
				settings.concatenationRedundancy = 5;


				// Overwrite settings with modified values.
				ClResult clRet = mReaderManager.Set_Symbology(settings);
				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();
			}
			*/

        }

        // ***************************************************//
        //  7-10. Get/set the Code 128 symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{
				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.Code128 settings = new Code128();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings))
				{
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}

				// step3: If supported, users can change values.
				settings.enable = Enable_State.TRUE;


				// Overwrite settings with modified values.
				ClResult clRet = mReaderManager.Set_Symbology(settings);
				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();
			}
			*/

        }

        // ***************************************************//
        //  7-11. Get/set the GS1 128 symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{
				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.GS1128 settings = new GS1128();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings))
				{
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}

				// step3: If supported, users can change values.
				settings.enable = Enable_State.TRUE;
				settings.fieldSeparator = '%';

				// Overwrite settings with modified values.
				ClResult clRet = mReaderManager.Set_Symbology(settings);
				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();
			}
			*/

        }

        // ***************************************************//
        //  7-12. Get/set the Msi symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.Msi settings = new Msi();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings))
				{
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}

				// step3: If supported, users can change values.
				settings.enable = Enable_State.TRUE;
				settings.length1 = 4;
				settings.length2 = 55;
				settings.checkDigitAlgorithm = DigitAlgorithm.Modulo_10_11;
				settings.transmitCheckDigit = Enable_State.TRUE;
				settings.checkDigitOption = MsiDigitOption.OneDigit;


				// Overwrite settings with modified values.
				ClResult clRet = mReaderManager.Set_Symbology(settings);
				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();

			}
			*/

        }

        // ***************************************************//
        //  7-13. Get/set the Ean8 symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{
				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.Ean8 settings = new Ean8();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings))
				{
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}

				// step3: If supported, users can change values.
				settings.enable = Enable_State.TRUE;
				settings.addon2 = AddonsType.IgnoresAddons;
				settings.addon5 = AddonsType.AutoDiscriminate;
				settings.convertToEan13 = Enable_State.FALSE;
				settings.transmitCheckDigit = Enable_State.TRUE;


				// Overwrite settings with modified values.
				ClResult clRet = mReaderManager.Set_Symbology(settings);
				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();
			}
			*/


        }

        // ***************************************************//
        //  7-14. Get/set the Ean13 symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{
				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.Ean13 settings = new Ean13();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings))
				{
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}

				// step3: If supported, users can change values.
				settings.enable = Enable_State.TRUE;
				settings.addon2 = AddonsType.AutoDiscriminate;
				settings.addon5 = AddonsType.IgnoresAddons;
				settings.convertToISBN = Enable_State.TRUE;
				settings.transmitCheckDigit = Enable_State.TRUE;
				settings.booklandISBNFormat=ISBNFormat.ISBN_10;


				// Overwrite settings with modified values.
				ClResult clRet = mReaderManager.Set_Symbology(settings);
				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();
			}
			*/


        }

        // ***************************************************//
        //  7-15. Get/set the GS1 DataBar 14 symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{
				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.GS1DataBar14 settings = new GS1DataBar14();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings))
				{
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}

				// step3: If supported, users can change values.
				settings.enable = Enable_State.TRUE;
				settings.convertToUpcEan = Enable_State.FALSE;

				// Overwrite settings with modified values.
				ClResult clRet = mReaderManager.Set_Symbology(settings);
				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();
			}
			*/


        }


        // ***************************************************//
        //  7-16. Get/set the GS1 DataBar Expanded symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.GS1DataBarExpanded settings = new GS1DataBarExpanded();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings))
				{
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}

				// step3: If supported, users can change values.
				settings.enable = Enable_State.TRUE;
				settings.fieldSeparator = '"';

				// Overwrite settings with modified values.
				ClResult clRet = mReaderManager.Set_Symbology(settings);
				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();

			}
			*/


        }

        // ***************************************************//
        //  7-17. Get/set the GS1 DataBar Limited symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.GS1DataBarLimited settings = new GS1DataBarLimited();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings))
				{
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}

				// step3: If supported, users can change values.
				settings.enable = Enable_State.TRUE;
				settings.convertToUpcEan = Enable_State.FALSE;

				// Overwrite settings with modified values.
				ClResult clRet = mReaderManager.Set_Symbology(settings);
				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();
			}
			*/


        }

        // ***************************************************//
        //  7-18. Get/set the UccCoupon symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.UccCoupon settings = new UccCoupon();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings))
				{
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}

				// step3: If supported, users can change values.
				settings.enable = Enable_State.TRUE;

				// Overwrite settings with modified values.
				ClResult clRet = mReaderManager.Set_Symbology(settings);
				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();
			}
			*/

        }

        // ***************************************************//
        //  7-19. Get/set the UpcA symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.UpcA settings = new UpcA();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings))
				{
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}

				// step3: If supported, users can change values.
				settings.enable = Enable_State.TRUE;
				settings.addon2 = AddonsType.AutoDiscriminate;
				settings.addon5 = AddonsType.AutoDiscriminate;
				settings.convertToEan13 = Enable_State.FALSE;
				settings.transmitCheckDigit = Enable_State.TRUE;
				settings.transmitSystemNumber = Preamble.SysNumAndCtyCode;

				// Overwrite settings with modified values.
				ClResult clRet = mReaderManager.Set_Symbology(settings);
				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();
			}
			*/


        }

        // ***************************************************//
        //  7-20. Get/set the UpcE symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.UpcE settings = new UpcE();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings))
				{
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}

				// step3: If supported, users can change values.
				settings.enable = Enable_State.TRUE;
				settings.addon2 = AddonsType.AutoDiscriminate;
				settings.addon5 = AddonsType.AutoDiscriminate;
				settings.convertToUpcA = Enable_State.FALSE;
				settings.transmitCheckDigit = Enable_State.TRUE;
				settings.transmitSystemNumber = Preamble.SysNumAndCtyCode;

				// Overwrite settings with modified values.
				ClResult clRet = mReaderManager.Set_Symbology(settings);
				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();
			}
			*/


        }

        // ***************************************************//
        //  7-21. Get/set the UpcE1 symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.UpcE1 settings = new UpcE1();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings))
				{
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}

				// step3: If supported, users can change values.
				settings.enable = Enable_State.TRUE;
				settings.addon2 = AddonsType.AutoDiscriminate;
				settings.addon5 = AddonsType.AutoDiscriminate;
				settings.convertToUpcA = Enable_State.FALSE;
				settings.transmitCheckDigit = Enable_State.TRUE;
				settings.transmitSystemNumber = Preamble.None;

				// Overwrite settings with modified values.
				ClResult clRet = mReaderManager.Set_Symbology(settings);
				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();
			}
			*/


        }

        // ***************************************************//
        //  7-22. Get/set the Code11 symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.Code11 settings = new Code11();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings))
				{
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}

				// step3: If supported, users can change values.
				settings.enable = Enable_State.TRUE;
				settings.transmitCheckDigit = Enable_State.TRUE;
				settings.numberOfCheckDigits = NumberOfCheck.Two;
				settings.length1 = 4;
				settings.length2 = 55;

				// Overwrite settings with modified values.
				ClResult clRet = mReaderManager.Set_Symbology(settings);
				if (ClResult.S_ERR == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_InvalidParameter == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
				else if (ClResult.Err_NotSupport == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
				else if (ClResult.S_OK == clRet)
					Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();
			}
			*/


        }

        // ***************************************************//
        // 7-23. Get/set the USPostal symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.USPostal settings = new USPostal();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings)) {
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}
				else if (ClResult.S_OK == mReaderManager.Get_Symbology(settings)) {
					// step3: If supported, users can change values.
					settings.enablePlanet = Enable_State.TRUE;
					settings.enablePostnet = Enable_State.TRUE;
					settings.transmitCheckDigit = Enable_State.TRUE;

					// Overwrite settings with modified values.
					ClResult clRet = mReaderManager.Set_Symbology(settings);
					if (ClResult.S_ERR == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_InvalidParameter == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_NotSupport == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
					else if (ClResult.S_OK == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();

				}
			}
			*/
        }

        // ***************************************************//
        // 7-24. Get/set the UKPostal symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.UKPostal settings = new UKPostal();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings)) {
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}
				else if (ClResult.S_OK == mReaderManager.Get_Symbology(settings)) {
					// step3: If supported, users can change values.
					settings.enable = Enable_State.FALSE;
					settings.transmitCheckDigit = Enable_State.TRUE;

					// Overwrite settings with modified values.
					ClResult clRet = mReaderManager.Set_Symbology(settings);
					if (ClResult.S_ERR == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_InvalidParameter == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_NotSupport == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
					else if (ClResult.S_OK == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();

				}
			}
			*/
        }

        // ***************************************************//
        // 7-25. Get/set the JapanPostal symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.JapanPostal settings = new JapanPostal();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings)) {
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}
				else if (ClResult.S_OK == mReaderManager.Get_Symbology(settings)) {
					// step3: If supported, users can change values.
					settings.enable = Enable_State.FALSE;

					// Overwrite settings with modified values.
					ClResult clRet = mReaderManager.Set_Symbology(settings);
					if (ClResult.S_ERR == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_InvalidParameter == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_NotSupport == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
					else if (ClResult.S_OK == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();

				}
			}
			*/
        }

        // ***************************************************//
        // 7-26. Get/set the AustralianPostal symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.AustralianPostal settings = new AustralianPostal();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings)) {
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}
				else if (ClResult.S_OK == mReaderManager.Get_Symbology(settings)) {
					// step3: If supported, users can change values.
					settings.enable = Enable_State.TRUE;

					// Overwrite settings with modified values.
					ClResult clRet = mReaderManager.Set_Symbology(settings);
					if (ClResult.S_ERR == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_InvalidParameter == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_NotSupport == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
					else if (ClResult.S_OK == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();

				}
			}
			*/
        }

        // ***************************************************//
        // 7-27. Get/set the DutchPostal symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.DutchPostal settings = new DutchPostal();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings)) {
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}
				else if (ClResult.S_OK == mReaderManager.Get_Symbology(settings)) {
					// step3: If supported, users can change values.
					settings.enable = Enable_State.TRUE;

					// Overwrite settings with modified values.
					ClResult clRet = mReaderManager.Set_Symbology(settings);
					if (ClResult.S_ERR == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_InvalidParameter == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_NotSupport == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
					else if (ClResult.S_OK == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();

				}
			}
			*/
        }

        // ***************************************************//
        // 7-28. Get/set the USPSPostal symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.USPSPostal settings = new USPSPostal();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings)) {
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}
				else if (ClResult.S_OK == mReaderManager.Get_Symbology(settings)) {
					// step3: If supported, users can change values.
					settings.enable = Enable_State.TRUE;

					// Overwrite settings with modified values.
					ClResult clRet = mReaderManager.Set_Symbology(settings);
					if (ClResult.S_ERR == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_InvalidParameter == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_NotSupport == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
					else if (ClResult.S_OK == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();

				}
			}
			*/
        }

        // ***************************************************//
        // 7-29. Get/set the UPUFICSPostal symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.UPUFICSPostal settings = new UPUFICSPostal();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings)) {
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}
				else if (ClResult.S_OK == mReaderManager.Get_Symbology(settings)) {
					// step3: If supported, users can change values.
					settings.enable = Enable_State.TRUE;

					// Overwrite settings with modified values.
					ClResult clRet = mReaderManager.Set_Symbology(settings);
					if (ClResult.S_ERR == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_InvalidParameter == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_NotSupport == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
					else if (ClResult.S_OK == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();

				}
			}
			*/
        }


        // ***************************************************//
        // 7-30. Get/set the PDF417 symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.PDF417 settings = new PDF417();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings)) {
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}
				else if (ClResult.S_OK == mReaderManager.Get_Symbology(settings)) {
					// step3: If supported, users can change values.
					settings.enable = Enable_State.TRUE;
					settings.escapeCharacter = Enable_State.FALSE;
					settings.transmitControlHeader = Enable_State.FALSE;
					settings.transmitMode = TransmitMode.TransmitAnySymbolInSet;

					// Overwrite settings with modified values.
					ClResult clRet = mReaderManager.Set_Symbology(settings);
					if (ClResult.S_ERR == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_InvalidParameter == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_NotSupport == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
					else if (ClResult.S_OK == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();

				}
			}
			*/
        }

        // ***************************************************//
        // 7-31. Get/set the MicroPDF417 symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.MicroPDF417 settings = new MicroPDF417();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings)) {
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}
				else if (ClResult.S_OK == mReaderManager.Get_Symbology(settings)) {
					// step3: If supported, users can change values.
					settings.enable = Enable_State.TRUE;
					settings.code128Emulation = Enable_State.FALSE;

					// Overwrite settings with modified values.
					ClResult clRet = mReaderManager.Set_Symbology(settings);
					if (ClResult.S_ERR == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_InvalidParameter == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_NotSupport == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
					else if (ClResult.S_OK == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();

				}
			}
			*/
        }

        // ***************************************************//
        // 7-32. Get/set the DataMatrix symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.DataMatrix settings = new DataMatrix();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings)) {
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}
				else if (ClResult.S_OK == mReaderManager.Get_Symbology(settings)) {
					// step3: If supported, users can change values.
					settings.enable = Enable_State.TRUE;
					settings.mirrorImage = MatrixMirrorImage.Auto;
					settings.fieldSeparator = 'A';

					// Overwrite settings with modified values.
					ClResult clRet = mReaderManager.Set_Symbology(settings);
					if (ClResult.S_ERR == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_InvalidParameter == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_NotSupport == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
					else if (ClResult.S_OK == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();

				}
			}
			*/
        }


        // ***************************************************//
        // 7-33. Get/set the MaxiCode symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.MaxiCode settings = new MaxiCode();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings)) {
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}
				else if (ClResult.S_OK == mReaderManager.Get_Symbology(settings)) {
					// step3: If supported, users can change values.
					settings.enable = Enable_State.TRUE;

					// Overwrite settings with modified values.
					ClResult clRet = mReaderManager.Set_Symbology(settings);
					if (ClResult.S_ERR == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_InvalidParameter == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_NotSupport == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
					else if (ClResult.S_OK == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();

				}
			}
			*/
        }

        // ***************************************************//
        // 7-34. Get/set the QRCode symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.QRCode settings = new QRCode();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings)) {
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}
				else if (ClResult.S_OK == mReaderManager.Get_Symbology(settings)) {
					// step3: If supported, users can change values.
					settings.enable = Enable_State.TRUE;

					// Overwrite settings with modified values.
					ClResult clRet = mReaderManager.Set_Symbology(settings);
					if (ClResult.S_ERR == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_InvalidParameter == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_NotSupport == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
					else if (ClResult.S_OK == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();

				}
			}
			*/
        }

        // ***************************************************//
        // 7-35. Get/set the MicroQR symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.MicroQR settings = new MicroQR();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings)) {
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}
				else if (ClResult.S_OK == mReaderManager.Get_Symbology(settings)) {
					// step3: If supported, users can change values.
					settings.enable = Enable_State.TRUE;

					// Overwrite settings with modified values.
					ClResult clRet = mReaderManager.Set_Symbology(settings);
					if (ClResult.S_ERR == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_InvalidParameter == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_NotSupport == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
					else if (ClResult.S_OK == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();

				}
			}
			*/
        }

        // ***************************************************//
        // 7-36. Get/set the Aztec symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{

				// step1: When a new object is created, all the values are assigned by default. Users don��t have to assign them one by one.
				com.cipherlab.barcode.decoderparams.Aztec settings = new Aztec();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings)) {
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}
				else if (ClResult.S_OK == mReaderManager.Get_Symbology(settings)) {
					// step3: If supported, users can change values.
					settings.enable = Enable_State.TRUE;

					// Overwrite settings with modified values.
					ClResult clRet = mReaderManager.Set_Symbology(settings);
					if (ClResult.S_ERR == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_InvalidParameter == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_NotSupport == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
					else if (ClResult.S_OK == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();

				}
			}
			*/
        }

        // ***************************************************//
        // 7-37. Get/set the Korean3Of5 symbology (similar to the previous one)
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{

				com.cipherlab.barcode.decoderparams.Korean3Of5 settings = new Korean3Of5();

				// step2: Check whether the symbology is supported by the barcode scanner.
				if (ClResult.Err_NotSupport == mReaderManager.Get_Symbology(settings)) {
					// checking statements
					Toast.makeText(this, "This symbology is not supported",
							Toast.LENGTH_SHORT).show();
				}
				else if (ClResult.S_OK == mReaderManager.Get_Symbology(settings)) {
					// step3: If supported, users can change values.
					settings.enable = Enable_State.TRUE;

					// Overwrite settings with modified values.
					ClResult clRet = mReaderManager.Set_Symbology(settings);
					if (ClResult.S_ERR == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was failed", Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_InvalidParameter == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was InvalidParameter",	Toast.LENGTH_SHORT).show();
					else if (ClResult.Err_NotSupport == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was NotSupport", Toast.LENGTH_SHORT).show();
					else if (ClResult.S_OK == clRet)
						Toast.makeText(this, "Set_Symbology " + settings.getClass().getSimpleName() + " was successful", Toast.LENGTH_SHORT).show();

				}
			}
			*/
        }

        // ***************************************************//
        //  8. software trigger
        // ***************************************************//
        {

            // When a barcode is scanned, this function is used to receive the processed data (the data string format generated by Set_ReaderOutputConfiguration) via myDataReceiver.

            if (mReaderManager != null)
            {
                mReaderManager.SoftScanTrigger();
            }

        }

        // ***************************************************//
        //  9. Restore all settings to default.
        // ***************************************************//
        {
			/*
			// 1D scan engine supports 21 symbologies, while 2D scan engine supports 38 symbologies.
			// Besides all aymbologies, Notification, UserPreferences, and ReaderOutputConfiguration will be reset to default.
			if (mReaderManager != null)
			{
				if (ClResult.S_ERR == mReaderManager.ResetReaderToDefault())
				{
					Toast.makeText(this, "ResetReaderToDefault was failed",
							Toast.LENGTH_SHORT).show();
				}
				else {
					Toast.makeText(this, "ResetReaderToDefault was done!",
							Toast.LENGTH_SHORT).show();
				}
			}
			*/
        }



        // ***************************************************//
        //  10. Get the version information of Reader Service.
        // ***************************************************//
        {
			/*
			if (mReaderManager != null)
			{
				String ver = mReaderManager.Get_BarcodeServiceVer();
			}
			*/
        }

    }

    /// Create a broadcast object to receive the intent coming from service.
    private final BroadcastReceiver myDataReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // If intent of the Intent_SOFTTRIGGER_DATA string is received
            if (intent.getAction().equals(GeneralString.Intent_SOFTTRIGGER_DATA)) {

                // fetch the data within the intent
                String data = intent.getStringExtra(GeneralString.BcReaderData);
                int iCodeType = intent.getIntExtra(GeneralString.BcReaderCodeType, 0);

                // display the fetched data
//                Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
//                e1.setText(data);
                onReadBarcode(data);
            }else if (intent.getAction().equals(GeneralString.Intent_PASS_TO_APP)){

                // fetch the data within the intent
                String data = intent.getStringExtra(GeneralString.BcReaderData);
                int iCodeType = intent.getIntExtra(GeneralString.BcReaderCodeType, 0);

                // display the fetched data
//                Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
//                e1.setText(data);
                onReadBarcode(data);

            }else if(intent.getAction().equals(GeneralString.Intent_READERSERVICE_CONNECTED)){

                BcReaderType myReaderType =  mReaderManager.GetReaderType();
//                e1.setText(myReaderType.toString());
                Toast.makeText(getApplicationContext(), myReaderType.toString(), Toast.LENGTH_SHORT).show();
                ReaderOutputConfiguration settings = new ReaderOutputConfiguration();
                mReaderManager.Get_ReaderOutputConfiguration(settings);
                settings.enableKeyboardEmulation = KeyboardEmulationType.None;
                mReaderManager.Set_ReaderOutputConfiguration(settings);

				/*NotificationParams settings = new NotificationParams();
				mReaderManager.Get_NotificationParams(settings);

				ReaderOutputConfiguration settings2 = new ReaderOutputConfiguration();
				mReaderManager.Get_ReaderOutputConfiguration(settings2);
				*/
            }

        }
    };
    private void onReadBarcode(String data){
        GlobalBus.getBus().post(
                    new WsEvents.EventReadBarCode(data)
            );
    }
    private void onReadRFID(String data){
        GlobalBus.getBus().post(
                new WsEvents.EventReadRFID(data)
        );
    }
    public class listener implements IConfigEventListener {
        public void onConnected() {
            AppHelper.SwitchCommandMode();

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
                    Toast t = Toast.makeText(ConfirmBin.this,
                            "STR_Success", Toast.LENGTH_SHORT);
                    t.show();
                    if (mSwitch == false) {
//                        mBtn.setText("Action");
//                        mBtn.setEnabled(true);
                    }
                    break;
                }
                case OperationFail:
                case DeviceTimeOut: {
                    if (displayflag == true) {
                        Toast t = Toast.makeText(ConfirmBin.this,
                                "STR_Failed", Toast.LENGTH_SHORT);
                        t.show();
                    }
                    if (mSwitch == false) {
//                        mBtn.setText("Action");
//                        mBtn.setEnabled(true);
                    }
                    break;
                }
                case DeviceBusy:
                    Toast t = Toast.makeText(ConfirmBin.this, "STR_Busy",
                            Toast.LENGTH_SHORT);
                    t.show();
                    if (mSwitch == false) {
//                        mBtn.setText("Action");
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
                    Toast t = Toast.makeText(ConfirmBin.this,
                            "STR_Success", Toast.LENGTH_SHORT);
                    t.show();
                    if (mSwitch== false) {
//                        mBtn.setText("Action");
//                        mBtn.setEnabled(true);
                    }
                    break;
                }
                case OperationFail:
                case DeviceTimeOut: {
                    Toast t = Toast.makeText(ConfirmBin.this,
                            "STR_Failed", Toast.LENGTH_SHORT);
                    t.show();
                    if (mSwitch == false) {
//                        mBtn.setText("Action");
//                        mBtn.setEnabled(true);
                    }
                    break;
                }
                case DeviceBusy:
                    Toast t = Toast.makeText(ConfirmBin.this, "STR_Busy",
                            Toast.LENGTH_SHORT);
                    t.show();
                    if (mSwitch == false) {
//                        mBtn.setText("Action");
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
