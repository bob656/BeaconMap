package kr.co.retailtech.mybeacon.ui.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import kr.co.retailtech.mybeacon.data.CommonRepository;
import kr.co.retailtech.mybeacon.data.database.entity.*;
import kr.co.retailtech.mybeacon.network.model.ServiceRequest;

public class BeaConInfoViewModel extends ViewModel {
    private final CommonRepository commonRepository;

    private final LiveData<List<Beacon>> mBeaconData;
    private final LiveData<List<Line>> mLineData;
    private final LiveData<List<Zone>> mZoneData;
    private final LiveData<List<Map>> mMapData;

    public BeaConInfoViewModel(CommonRepository commonRepository) {
        this.commonRepository = commonRepository;
        this.mBeaconData = commonRepository.getBeaconList();
        this.mLineData = commonRepository.getLineList();
        this.mZoneData = commonRepository.getZoneList();
        this.mMapData = commonRepository.getMapList();
    }

    public LiveData<List<Beacon>> getBeaconList() {
        return mBeaconData;
    }

    public LiveData<List<Line>> getLineList() {
        return mLineData;
    }

    public LiveData<List<Zone>> getZoneList() {
        return mZoneData;
    }

    public LiveData<List<Map>> getMapList() {
        return mMapData;
    }

    public void beaConPostRequest(ServiceRequest serviceRequest) {
        commonRepository.beaConPostServiceRequest(serviceRequest);
    }
    public void linePostRequest(ServiceRequest serviceRequest) {
        commonRepository.linePostServiceRequest(serviceRequest);
    }
    public void zonePostRequest(ServiceRequest serviceRequest) {
        commonRepository.zonePostServiceRequest(serviceRequest);
    }
    public void mapPostRequest(ServiceRequest serviceRequest) {
        commonRepository.mapPostServiceRequest(serviceRequest);
    }
}
