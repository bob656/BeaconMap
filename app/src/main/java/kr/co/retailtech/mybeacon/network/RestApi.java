package kr.co.retailtech.mybeacon.network;

import io.reactivex.Observable;
import kr.co.retailtech.mybeacon.network.model.ServiceBeaconResponse;
import kr.co.retailtech.mybeacon.network.model.ServiceLineResponse;
import kr.co.retailtech.mybeacon.network.model.ServiceMapResponse;
import kr.co.retailtech.mybeacon.network.model.ServiceZoneResponse;
import retrofit2.http.GET;

public interface RestApi {
    @GET("beacon.json")
    Observable<ServiceBeaconResponse> getBeaconJson();

    @GET("line.json")
    Observable<ServiceLineResponse> getLineJson();

    @GET("map.json")
    Observable<ServiceMapResponse> getMapJson();

    @GET("zone.json")
    Observable<ServiceZoneResponse> getZoneJson();


}
