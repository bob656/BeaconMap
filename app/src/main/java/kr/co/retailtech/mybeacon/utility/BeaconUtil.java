package kr.co.retailtech.mybeacon.utility;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.minew.beacon.BeaconValueIndex;
import com.minew.beacon.BluetoothState;
import com.minew.beacon.MinewBeacon;
import com.minew.beacon.MinewBeaconManager;
import com.minew.beacon.MinewBeaconManagerListener;

import java.util.List;

public class BeaconUtil implements MinewBeaconManagerListener {
    private String LOG_TAG = BeaconUtil.class.getSimpleName();

    private enum CheckFlag {
        INSERT, UPDATE, DELETE
    }

    private final Context context ;
    // 1.get MinewBeaconManager instance
    MinewBeaconManager mMinewBeaconManager = null;

    public BeaconUtil(Context context){
        this.context = context;
        mMinewBeaconManager = MinewBeaconManager.getInstance(context);
        mMinewBeaconManager.setDeviceManagerDelegateListener(this);
    }

    public void startBeacon() {
        if(checkBluetooth()){
            mMinewBeaconManager.startScan();
        }
    }
    public void stopBeacon() {
        mMinewBeaconManager.stopScan();
    }

    public List<MinewBeacon> scannedBeacons(){
        return mMinewBeaconManager.scannedBeacons;
    }

    public List<MinewBeacon> inRangeBeacons(){
        return mMinewBeaconManager.inRangeBeacons;
    }

    @Override
    public void onAppearBeacons(List<MinewBeacon> list) {
        processBeaconInfo(list, CheckFlag.UPDATE);
    }

    @Override
    public void onDisappearBeacons(List<MinewBeacon> list) {
        processBeaconInfo(list, CheckFlag.DELETE);
    }

    @Override
    public void onRangeBeacons(List<MinewBeacon> list) {
        processBeaconInfo(list, CheckFlag.UPDATE);
    }

    private void processBeaconInfo (List<MinewBeacon> list, CheckFlag checkFlag)
    {
        for(MinewBeacon minewBeacon: list){
            String major = minewBeacon.getBeaconValue(BeaconValueIndex.MinewBeaconValueIndex_Major).getStringValue();
            String minor = minewBeacon.getBeaconValue(BeaconValueIndex.MinewBeaconValueIndex_Minor).getStringValue();
            if(major.equals("40101") && minor.equals("15073")) {
                Integer txPower = minewBeacon.getBeaconValue(BeaconValueIndex.MinewBeaconValueIndex_TxPower).getIntValue();
                Integer rssi = minewBeacon.getBeaconValue(BeaconValueIndex.MinewBeaconValueIndex_RSSI).getIntValue();
                double distance = calculateAccuracy(-59, rssi);

                double distance2 = calculateAccuracy(-65, rssi);

                StringBuffer logBuffer = new StringBuffer();
                logBuffer.append("STATUS==>" + checkFlag)
                        .append(" MAJOR==>" + major)
                        .append(" MINOR==>" + minor)
                        .append(" txPower==>" + txPower)
                        .append(" RSSI==>" + rssi)
                        .append(" DISTANCE==>" + distance)
                        .append(" DISTANCE2==>" + distance2)
                ;

                Log.d(LOG_TAG, logBuffer.toString());
                switch (checkFlag) {
                    case INSERT:
                        break;
                    case UPDATE:
                        break;
                    case DELETE:
                        break;

                }
            }
        }

    }

    protected double calculateAccuracy(int txPower, double rssi) {
        double POW_CONSTANT = 10;
        double BEST_SIGNAL_CONSTANT = 0.89976;
        double BEST_POW_CONSTANT = 7.7095;
        double BEST_ADDED_CONSTANT = 0.111;

        if (rssi == 0) {
            return -1.0; // if we cannot determine accuracy, return -1.
        }
        double ratio = rssi*1.0/txPower;
        if (ratio < 1.0) {
            return Math.pow(ratio,POW_CONSTANT);
        }
        else {
            double accuracy =  BEST_SIGNAL_CONSTANT * Math.pow(ratio,BEST_POW_CONSTANT) + BEST_ADDED_CONSTANT;
            return accuracy;
        }
    }

    @Override
    public void onUpdateState(BluetoothState bluetoothState) {
        switch (bluetoothState) {
            case BluetoothStateNotSupported:
                break;
            case BluetoothStatePowerOff:
                break;
            case BluetoothStatePowerOn:
                break;
        }
    }

    private boolean checkBluetooth() {
        BluetoothState bluetoothState = mMinewBeaconManager.checkBluetoothState();
        Log.d(LOG_TAG, "bluetoothState : " + bluetoothState);

        switch (bluetoothState) {
            case BluetoothStateNotSupported:
                Log.d(LOG_TAG, "BluetoothStateNotSupported");
                Toast.makeText(context, "Not Support BLE", Toast.LENGTH_SHORT).show();
                return false;
            case BluetoothStatePowerOff:
                Log.d(LOG_TAG, "BluetoothStatePowerOff");
                return false;
            case BluetoothStatePowerOn:
                return true;
            default:
                return false;
        }
    }

}
