package kr.co.retailtech.mybeacon.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

import io.reactivex.observers.DisposableObserver;
import kr.co.retailtech.mybeacon.data.database.entity.*;
import kr.co.retailtech.mybeacon.network.model.ServiceBeaconResponse;
import kr.co.retailtech.mybeacon.network.model.ServiceLineResponse;
import kr.co.retailtech.mybeacon.network.model.ServiceMapResponse;
import kr.co.retailtech.mybeacon.network.model.ServiceRequest;
import kr.co.retailtech.mybeacon.network.model.ServiceZoneResponse;
import kr.co.retailtech.mybeacon.utility.AppExecutors;

public class CommonNetworkDataSource {
    private static final String LOG_TAG = CommonNetworkDataSource.class.getSimpleName();

    // For Singleton instantiation
    private static CommonNetworkDataSource sInstance;
    private static final Object LOCK = new Object();

    private AppExecutors mAppExecutors;

    private final MutableLiveData<List<Beacon>> mBeaconDownloadedData;
    private final MutableLiveData<List<Line>> mLineDownloadedData;
    private final MutableLiveData<List<Zone>> mZoneDownloadedData;
    private final MutableLiveData<List<Map>> mMapDownloadedData;


    public CommonNetworkDataSource(AppExecutors mAppExecutors) {
        this.mAppExecutors = mAppExecutors;
        this.mBeaconDownloadedData = new MutableLiveData<>();
        this.mLineDownloadedData = new MutableLiveData<>();
        this.mZoneDownloadedData = new MutableLiveData<>();
        this.mMapDownloadedData = new MutableLiveData<>();
    }

    public static CommonNetworkDataSource getInstance(AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the network data source");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new CommonNetworkDataSource(executors);
                Log.d(LOG_TAG, "Made new network data source");
            }
        }
        return sInstance;
    }

    public void beaConFetchData(ServiceRequest serviceRequest) {
        mAppExecutors.networkIO().execute(() -> {
            try {
                NetworkUtils.getBeaconService(new
                  DisposableObserver<ServiceBeaconResponse>() {
                      @Override
                      public void onNext(ServiceBeaconResponse serviceResponse) {
                          setBeaconList(NetworkUtils.convertToBeaConInfoList(serviceResponse));
                      }
                      @Override
                      public void onError(Throwable e) {
                          Log.e(LOG_TAG, "Getting data process has been failed1.", e);
                      }

                      @Override
                      public void onComplete() {
                          Log.e(LOG_TAG, "Getting data process has been complete.");
                      }
                  });

            } catch (Exception ex) {
                Log.e(LOG_TAG, "Getting data process has been failed2.", ex);
            }
        });
    }

    //Beacon
    private void setBeaconList(List<Beacon> list){
        mBeaconDownloadedData.postValue(list);
    }
    public LiveData<List<Beacon>> getBeaconList(){
        return mBeaconDownloadedData;
    }

    public void lineFetchData(ServiceRequest serviceRequest) {
        mAppExecutors.networkIO().execute(() -> {
            try {
                NetworkUtils.getLineService(new
                  DisposableObserver<ServiceLineResponse>() {
                      @Override
                      public void onNext(ServiceLineResponse serviceResponse) {
                          setLineList(NetworkUtils.convertToLineInfoList(serviceResponse));
                      }
                      @Override
                      public void onError(Throwable e) {
                          Log.e(LOG_TAG, "Getting data process has been failed1.", e);
                      }

                      @Override
                      public void onComplete() {
                          Log.e(LOG_TAG, "Getting data process has been complete.");
                      }
                  });

            } catch (Exception ex) {
                Log.e(LOG_TAG, "Getting data process has been failed2.", ex);
            }
        });
    }
    //Line
    private void setLineList(List<Line> list){
        mLineDownloadedData.postValue(list);
    }
    public LiveData<List<Line>> getLineList(){
        return mLineDownloadedData;
    }


    public void mapFetchData(ServiceRequest serviceRequest) {
        mAppExecutors.networkIO().execute(() -> {
            try {
                NetworkUtils.getMapService(new
                    DisposableObserver<ServiceMapResponse>() {
                        @Override
                        public void onNext(ServiceMapResponse serviceResponse) {
                            setMapList(NetworkUtils.convertToMapInfoList(serviceResponse));
                        }
                        @Override
                        public void onError(Throwable e) {
                            Log.e(LOG_TAG, "Getting data process has been failed1.", e);
                        }

                        @Override
                        public void onComplete() {
                            Log.e(LOG_TAG, "Getting data process has been complete.");
                        }
                    });

            } catch (Exception ex) {
                Log.e(LOG_TAG, "Getting data process has been failed2.", ex);
            }
        });
    }

    //Map
    private void setMapList(List<Map> list){
        mMapDownloadedData.postValue(list);
    }
    public LiveData<List<Map>> getMapList(){
        return mMapDownloadedData;
    }


    public void zoneFetchData(ServiceRequest serviceRequest) {
        mAppExecutors.networkIO().execute(() -> {
            try {
                NetworkUtils.getZoneService(new
                   DisposableObserver<ServiceZoneResponse>() {
                       @Override
                       public void onNext(ServiceZoneResponse serviceResponse) {
                           setZoneList(NetworkUtils.convertToZoneInfoList(serviceResponse));
                       }
                       @Override
                       public void onError(Throwable e) {
                           Log.e(LOG_TAG, "Getting data process has been failed1.", e);
                       }

                       @Override
                       public void onComplete() {
                           Log.e(LOG_TAG, "Getting data process has been complete.");
                       }
                   });

            } catch (Exception ex) {
                Log.e(LOG_TAG, "Getting data process has been failed2.", ex);
            }
        });
    }
    //Zone
    private void setZoneList(List<Zone> list){
        mZoneDownloadedData.postValue(list);
    }
    public LiveData<List<Zone>> getZoneList(){
        return mZoneDownloadedData;
    }

}
