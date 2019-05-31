package kr.co.retailtech.mybeacon.network;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import kr.co.retailtech.mybeacon.data.database.entity.Beacon;
import kr.co.retailtech.mybeacon.data.database.entity.Line;
import kr.co.retailtech.mybeacon.data.database.entity.Map;
import kr.co.retailtech.mybeacon.data.database.entity.Zone;
import kr.co.retailtech.mybeacon.network.model.ServiceBeaconResponse;
import kr.co.retailtech.mybeacon.network.model.ServiceLineResponse;
import kr.co.retailtech.mybeacon.network.model.ServiceMapResponse;
import kr.co.retailtech.mybeacon.network.model.ServiceZoneResponse;
import kr.co.retailtech.mybeacon.network.pojo.BeaConInfo;
import kr.co.retailtech.mybeacon.network.pojo.LineInfo;
import kr.co.retailtech.mybeacon.network.pojo.MapInfo;
import kr.co.retailtech.mybeacon.network.pojo.ZoneInfo;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final String BASE_URL = "http://192.168.100.53:3000/";

    private static Retrofit getRetrofit() {
        return new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory
                .create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }
    // Beacon Info
    public static Disposable getBeaconService(DisposableObserver<ServiceBeaconResponse> observer) {
        Log.d(LOG_TAG, "Getting data from the server");
        try {
            RestApi service = getRetrofit().create(RestApi.class);

            Observable<ServiceBeaconResponse> observable = service.getBeaconJson();
            return observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(observer);

        } catch (Exception e) {
            Log.d(LOG_TAG, "Getting data process has been failed. ", e);
        }
        return null;
    }

    public static List<Beacon> convertToBeaConInfoList(ServiceBeaconResponse serviceResponse) {
        List<Beacon> beaconInfo = new ArrayList<>();

        Log.d(LOG_TAG, "Converting the response.");
        try {
            for (BeaConInfo data : serviceResponse.data) {
                Beacon beacon = new Beacon();
                beacon.beaconId = data.beaConId;
                beacon.floorId = data.floorId;
                beacon.beaconUuid = data.beaconUuid;
                beacon.beaconX = data.beaconX;
                beacon.beaconY = data.beaconY;
                beacon.beaconType = data.beaconType;
                beacon.beaconTxType = data.beaconTxType;
                beacon.beaconRadius = data.beaconRadius;

                // add
                beaconInfo.add(beacon);
            }
        } catch (Exception e) {
            Log.d(LOG_TAG, "Converting the response process has been failed. ", e);
        }
        return beaconInfo;
    }

    // Line Info
    public static Disposable getLineService(DisposableObserver<ServiceLineResponse> observer) {
        Log.d(LOG_TAG, "Getting data from the server");
        try {
            RestApi service = getRetrofit().create(RestApi.class);

            Observable<ServiceLineResponse> observable = service.getLineJson();
            return observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(observer);

        } catch (Exception e) {
            Log.d(LOG_TAG, "Getting data process has been failed. ", e);
        }
        return null;
    }

    public static List<Line> convertToLineInfoList(ServiceLineResponse serviceResponse) {
        List<Line> Info = new ArrayList<>();

        Log.d(LOG_TAG, "Converting the response.");
        try {
            for (LineInfo data : serviceResponse.data) {
                Line line = new Line();
                line.lineId = data.lineId;
                line.floorId = data.floorId;
                line.lineType = data.lineType;
                line.lineX = data.lineX;
                line.lineY = data.lineY;
                line.lineW = data.lineW;
                line.lineH = data.lineH;

                // add
                Info.add(line);
            }
        } catch (Exception e) {
            Log.d(LOG_TAG, "Converting the response process has been failed. ", e);
        }
        return Info;
    }

    // Map Info
    public static Disposable getMapService(DisposableObserver<ServiceMapResponse> observer) {
        Log.d(LOG_TAG, "Getting data from the server");
        try {
            RestApi service = getRetrofit().create(RestApi.class);

            Observable<ServiceMapResponse> observable = service.getMapJson();
            return observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(observer);

        } catch (Exception e) {
            Log.d(LOG_TAG, "Getting data process has been failed. ", e);
        }
        return null;
    }

    public static List<Map> convertToMapInfoList(ServiceMapResponse serviceResponse) {
        List<Map> Info = new ArrayList<>();

        Log.d(LOG_TAG, "Converting the response.");
        try {
            for (MapInfo data : serviceResponse.data) {
                Map map = new Map();
                map.floorId = data.floorId;
                map.venueId = data.venueId;
                map.floorName = data.floorName;
                map.mapFile = data.mapFile;
                map.mapX = data.mapX;
                map.mapY = data.mapY;
                map.mapW = data.mapW;
                map.mapH = data.mapH;
                map.pixelW = data.pixelW;
                map.pixelH2 = data.pixelH2;
                map.useYn = data.useYn;

                // add
                Info.add(map);
            }
        } catch (Exception e) {
            Log.d(LOG_TAG, "Converting the response process has been failed. ", e);
        }
        return Info;
    }

    // Zone Info
    public static Disposable getZoneService(DisposableObserver<ServiceZoneResponse> observer) {
        Log.d(LOG_TAG, "Getting data from the server");
        try {
            RestApi service = getRetrofit().create(RestApi.class);

            Observable<ServiceZoneResponse> observable = service.getZoneJson();
            return observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(observer);

        } catch (Exception e) {
            Log.d(LOG_TAG, "Getting data process has been failed. ", e);
        }
        return null;
    }

    public static List<Zone> convertToZoneInfoList(ServiceZoneResponse serviceResponse) {
        List<Zone> Info = new ArrayList<>();

        Log.d(LOG_TAG, "Converting the response.");
        try {
            for (ZoneInfo data : serviceResponse.data) {
                Zone zone = new Zone();
                zone.zoneId = data.zoneId;
                zone.floorId = data.floorId;
                zone.zoneName = data.zoneName;
                zone.divCd = data.divCd;
                zone.zoneX = data.zoneX;
                zone.zoneY = data.zoneY;
                zone.zoneW = data.zoneW;
                zone.zoneH = data.zoneH;

                // add
                Info.add(zone);
            }
        } catch (Exception e) {
            Log.d(LOG_TAG, "Converting the response process has been failed. ", e);
        }
        return Info;
    }

}
