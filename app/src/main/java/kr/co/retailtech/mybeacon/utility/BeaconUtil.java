package kr.co.retailtech.mybeacon.utility;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.minew.beacon.BeaconValueIndex;
import com.minew.beacon.BluetoothState;
import com.minew.beacon.MinewBeacon;
import com.minew.beacon.MinewBeaconManager;
import com.minew.beacon.MinewBeaconManagerListener;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import java.util.ArrayList;
import java.util.List;

import kr.co.retailtech.mybeacon.data.database.entity.Beacon;

public class BeaconUtil implements MinewBeaconManagerListener {
    private String LOG_TAG = BeaconUtil.class.getSimpleName();

    private enum CheckFlag {
        INSERT, UPDATE, DELETE
    }

    private final Context context ;
    // 1.get MinewBeaconManager instance
    MinewBeaconManager mMinewBeaconManager = null;

    private MutableLiveData<List<Beacon>> getTempBeaconList;
    private MutableLiveData<double[]> getPosition;

    public BeaconUtil(Context context){
        this.context = context;
        mMinewBeaconManager = MinewBeaconManager.getInstance(context);
        mMinewBeaconManager.setDeviceManagerDelegateListener(this);

        getTempBeaconList = new MutableLiveData<List<Beacon>>();
        getPosition = new MutableLiveData<double[]>();
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
        List<Beacon> benconInfo = new ArrayList<Beacon>();
        for(MinewBeacon minewBeacon: list){
            String major = minewBeacon.getBeaconValue(BeaconValueIndex.MinewBeaconValueIndex_Major).getStringValue();
            String minor = minewBeacon.getBeaconValue(BeaconValueIndex.MinewBeaconValueIndex_Minor).getStringValue();
            if( minor.equals("15136") || minor.equals("15073")  || minor.equals("15077")) {
                Integer txPower = minewBeacon.getBeaconValue(BeaconValueIndex.MinewBeaconValueIndex_TxPower).getIntValue();
                Integer rssi = minewBeacon.getBeaconValue(BeaconValueIndex.MinewBeaconValueIndex_RSSI).getIntValue();
                double distance = calculateAccuracy(-65, rssi);
                //double distance = calculateAccuracy(-65, rssi);

                Beacon bc = new Beacon();
                bc.beaconUuid = major.concat(minor);
                bc.distance = Double.toString(distance);
                benconInfo.add(bc);

                StringBuffer logBuffer = new StringBuffer();
                logBuffer.append(bc.beaconUuid)
                        .append(  "\t" + rssi  + "\t")
                        .append(distance)
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


        if(benconInfo.size() > 2){
            double[][] positions = new double[][] {{ 100.0, 100.0 }, { 100.0, 940 }, { 220, 320 }};
            double[] distances = new double[3];

            for(int i = 0 ; i < benconInfo.size() ; i++){
                distances[i] = Double.parseDouble(benconInfo.get(i).distance);
            }
            NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(new TrilaterationFunction(positions, distances), new LevenbergMarquardtOptimizer());
            Optimum optimum = solver.solve();

            // the answer
            double[] centroid = optimum.getPoint().toArray();

            // error and geometry information; may throw SingularMatrixException depending the threshold argument provided
            RealVector standardDeviation = optimum.getSigma(0);
            RealMatrix covarianceMatrix = optimum.getCovariances(0);
            getTempBeaconList.setValue(benconInfo);
            getPosition.setValue(centroid);

        }

    }

    public LiveData<List<Beacon>> getMutableLiveData(){
        return getTempBeaconList;
    }
    public LiveData<double[]> getPostionMutableLiveData(){
        return getPosition;
    }

    protected double calculateAccuracy(int txPower, double rssi) {
        double POW_CONSTANT = 10.0d;
//        double BEST_SIGNAL_CONSTANT = 0.89976;
//        double BEST_POW_CONSTANT = 7.7095;
//        double BEST_ADDED_CONSTANT = 0.111;

        double BEST_SIGNAL_CONSTANT = 0.42093;
        double BEST_POW_CONSTANT = 6.9476;
        double BEST_ADDED_CONSTANT =0.54992;

        /*
        double BEST_SIGNAL_CONSTANT = 0.9401940951;
        double BEST_POW_CONSTANT = 6.170094565;
        double BEST_ADDED_CONSTANT =0.0;
*/
        if (rssi == 0) {
            return -1.0d; // if we cannot determine accuracy, return -1.
        }

        double distance;
        double ratio = (1.0d * rssi) / ((double) txPower);

        if (ratio < 1.0d) {
            return Math.pow(ratio,POW_CONSTANT);
        }
        else {
            double accuracy =  (BEST_SIGNAL_CONSTANT * Math.pow(ratio,BEST_POW_CONSTANT)) + BEST_ADDED_CONSTANT;
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
