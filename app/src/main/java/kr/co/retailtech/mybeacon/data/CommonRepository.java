package kr.co.retailtech.mybeacon.data;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;

import kr.co.retailtech.mybeacon.data.database.CommonDao;
import kr.co.retailtech.mybeacon.data.database.entity.Beacon;
import kr.co.retailtech.mybeacon.data.database.entity.Line;
import kr.co.retailtech.mybeacon.data.database.entity.Map;
import kr.co.retailtech.mybeacon.data.database.entity.Zone;
import kr.co.retailtech.mybeacon.network.CommonNetworkDataSource;
import kr.co.retailtech.mybeacon.network.model.ServiceRequest;
import kr.co.retailtech.mybeacon.utility.AppExecutors;

public class CommonRepository {
    private static final String LOG_TAG = CommonRepository.class.getSimpleName();

    private CommonDao commonDao;
    private CommonNetworkDataSource networkDataSource;

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static CommonRepository sInstance;

    public CommonRepository(CommonDao commonDao, CommonNetworkDataSource networkDataSource, AppExecutors executors) {
        this.commonDao = commonDao;
        this.networkDataSource = networkDataSource;

        // As long as the repository exists, observe the network LiveData.
        // If that LiveData changes, update the database.
        networkDataSource.getBeaconList().observeForever(info -> {
            executors.diskIO().execute(() -> {
                Log.d(LOG_TAG, "BeaCon table is updating");
                commonDao.updateBeaconAll(info);
            });
        });

        // As long as the repository exists, observe the network LiveData.
        // If that LiveData changes, update the database.
        networkDataSource.getLineList().observeForever(info -> {
            executors.diskIO().execute(() -> {
                Log.d(LOG_TAG, "Line table is updating");
                commonDao.updateLineAll(info);
            });
        });

        // As long as the repository exists, observe the network LiveData.
        // If that LiveData changes, update the database.
        networkDataSource.getZoneList().observeForever(info -> {
            executors.diskIO().execute(() -> {
                Log.d(LOG_TAG, "Zone table is updating");
                commonDao.updateZoneAll(info);
            });
        });

        // As long as the repository exists, observe the network LiveData.
        // If that LiveData changes, update the database.
        networkDataSource.getMapList().observeForever(info -> {
            executors.diskIO().execute(() -> {
                Log.d(LOG_TAG, "Map table is updating");
                commonDao.updateMapAll(info);
            });
        });



    }

    public static CommonRepository getInstance(CommonDao commonDao, CommonNetworkDataSource networkDataSource, AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new CommonRepository(commonDao, networkDataSource, executors);
                Log.d(LOG_TAG, "Made new repository");
            }
        }
        return sInstance;
    }

    public LiveData<List<Beacon>> getBeaconList() {
        return commonDao.getBeaConList();
    }

    public LiveData<List<Line>> getLineList() {
        return commonDao.getLineList();
    }

    public LiveData<List<Map>> getMapList() {
        return commonDao.getMapList();
    }

    public LiveData<List<Zone>> getZoneList() {
        return commonDao.getZoneList();
    }

    public void beaConPostServiceRequest(ServiceRequest serviceRequest) {
        networkDataSource.beaConFetchData(serviceRequest);
    }

    public void linePostServiceRequest(ServiceRequest serviceRequest) {
        networkDataSource.lineFetchData(serviceRequest);
    }

    public void mapPostServiceRequest(ServiceRequest serviceRequest) {
        networkDataSource.mapFetchData(serviceRequest);
    }

    public void zonePostServiceRequest(ServiceRequest serviceRequest) {
        networkDataSource.zoneFetchData(serviceRequest);
    }




}
