package com.ppt.wsinventory.ws_rfidreader;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import cipherlab.rfid.device1800.config.BluetoothSocketConnector;
import cipherlab.rfid.device1800.config.Configurator;
import cipherlab.rfid.device1800.config.ConfiguratorException;
import cipherlab.rfid.device1800.config.DeviceResponse;
import cipherlab.rfid.device1800.config.InventoryType;
import cipherlab.rfid.device1800.config.OutputDataFormat;
import cipherlab.rfid.device1800.config.RFIDMemoryBank;
import cipherlab.rfid.device1800.config.RFIDMode;
import cipherlab.rfid.device1800.config.TagOperator;
/**
 * Created by arkar on 2/3/2018.
 */

public class AppHelper {
    protected static final int MESSAGE_SUCCESS = 1;
    protected static final int MESSAGE_FAIL = 2;
    protected static final int MESSAGE_TIMEOUT = 3;
    protected static final int MESSAGE_BUSY = 4;
    protected static final int MESSAGE_LOCK = 5;
    protected static final int MESSAGE_WRITE = 6;
    protected static final int MESSAGE_Finish = 7;

    public static Configurator mConfig;
    public static BluetoothSocketConnector mConnector;
    public static TagOperator mOperator;

    public static boolean SwitchStatus;
    private static boolean record = true;
    private static boolean running = false;
    private static final String TAG = "WS-AppHelper";
    private static Handler mThreadHandler;
    private static HandlerThread mWorker;

    public static void InitialObject() {
        if (mWorker == null) {
            mWorker = new HandlerThread("Demo");
            mWorker.start();
        }

        if (mThreadHandler == null) {
            mThreadHandler = new Handler(mWorker.getLooper());
        }
    }

    public static void ReleaseObject() {
        if (mWorker != null) {
            mWorker.quit();
            mWorker = null;
            mThreadHandler = null;
        }
        mConfig = null;
    }

    public static void GetHandle() {
        if (AppHelper.mConfig == null) {
            try {
                AppHelper.mConfig = new Configurator(AppHelper.mConnector);
                AppHelper.mOperator = new TagOperator(AppHelper.mConnector);
                // AppHelper.mConfig.SetLogPath("test.log");
            } catch (ConfiguratorException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void SwitchCommandMode() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    mConfig.EnableDeviceTrigger(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    mConfig.SetOutputDataFormat(OutputDataFormat.PacketData);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    mConfig.SetRFIDMode(RFIDMode.Inventory);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.i(TAG, "SwitchCommandMode End");
            }
        };
        mThreadHandler.post(r);
    }

    public static void ChangeDirectControl(final boolean flag) {

        if (record != flag) {
            record = flag;
            Log.i("AppHelper", "Change SwitchStatus to " + flag);
            new Thread(new Runnable() {
                public void run() {
                    try {
                        SwitchStatus = mConfig.GetRFIDSwitchStatus();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                    try {
                        mConfig.EnableDeviceTrigger(flag);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, "ChangeDirectControl End");
                }
            }, "ChangeDirectControl").start();
        }
    }

    public static void Inventory() {
        Runnable r = new Runnable() {
            public void run() {
                try {
                    mOperator.RFIDDirectStartInventoryRound(InventoryType.EPC, 1);
                } catch (ConfiguratorException e) {
                    e.printStackTrace();
                }
                Log.i(TAG, "Inventory End");
            }
        };
        mThreadHandler.post(r);
    }

    public static void InventoryCancel() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    mOperator.RFIDDirectCancelInventoryRound();
                } catch (ConfiguratorException e) {
                    e.printStackTrace();
                }
                Log.i(TAG, "InventoryCancel End");
            }
        }, "InventoryCancel").start();
    }

    public static byte[] StringToByteArray(String hex) throws Exception {
        if (hex == null) {
            throw new Exception("The binary key is empty");
        }
        int len = hex.length();
        if (len % 2 == 1) {
            throw new Exception(
                    "The binary key cannot have an odd number of digits");
        }

        byte[] ba = new byte[len / 2];
        for (int i = 0; i < ba.length; i++)
            ba[i] = (byte) Integer
                    .parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        return ba;
    }

    public static void ReadData(final String epc, final RFIDMemoryBank bank) {

        if (running == true)
            return;

        running = true;

        Runnable r = new Runnable() {
            public void run() {
                byte[] EPCByteArray = null;
                try {
                    EPCByteArray = StringToByteArray(epc);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                try {
                    if (EPCByteArray != null)
                        mOperator.RFIDDirectReadTagByEPC(null, EPCByteArray,
                                bank, 0, 0, 3);
                } catch (ConfiguratorException e) {
                    e.printStackTrace();
                }

                running = false;
                Log.i(TAG, "UI ReadData End");
            }
        };
        mThreadHandler.post(r);
    }

    public static void WriteData(final String epc, final RFIDMemoryBank bank,
                                 final String writeData, final Handler h) {

        if (running == true)
            return;

        running = true;
        Runnable r = new Runnable() {
            public void run() {
                byte[] EPCByteArray = null;
                byte[] DataArray = null;
                try {
                    EPCByteArray = StringToByteArray(epc);
                } catch (Exception e1) {
                    EPCByteArray = null;
                    e1.printStackTrace();
                }

                try {
                    DataArray = StringToByteArray(writeData);
                } catch (Exception e1) {
                    DataArray = null;
                    e1.printStackTrace();
                }

                try {
                    if (EPCByteArray != null && DataArray != null) {
                        DeviceResponse response = mOperator
                                .RFIDDirectWriteTagByEPC(null, EPCByteArray,
                                        bank, 2, 0, DataArray);
                        switch (response) {
                            case OperationSuccess:
                                h.sendMessage(h
                                        .obtainMessage(MESSAGE_SUCCESS));
                                break;
                            case OperationFinish:
                                h.sendMessage(h
                                        .obtainMessage(MESSAGE_Finish));
                                break;
                            case OperationFail:
                                h.sendMessage(h
                                        .obtainMessage(MESSAGE_FAIL));
                                break;
                            case DeviceTimeOut:
                                h.sendMessage(h
                                        .obtainMessage(MESSAGE_TIMEOUT));
                                break;
                            case DeviceBusy:
                                h.sendMessage(h
                                        .obtainMessage(MESSAGE_BUSY));
                                break;
                            case TagLock:
                                h.sendMessage(h
                                        .obtainMessage(MESSAGE_LOCK));
                                break;
                        }

                    } else {
                        h.sendMessage(h
                                .obtainMessage(MESSAGE_FAIL));
                    }
                } catch (ConfiguratorException e) {
                    e.printStackTrace();
                    h.sendMessage(h.obtainMessage(MESSAGE_FAIL));
                }
                running = false;
                Log.i(TAG, "UI WriteData End");
            }
        };
        mThreadHandler.post(r);
    }
}