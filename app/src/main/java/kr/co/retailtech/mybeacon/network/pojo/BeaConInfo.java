package kr.co.retailtech.mybeacon.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BeaConInfo {
    @SerializedName("BEACON_ID")
    @Expose
    public String beaConId;
    @SerializedName("FLOOR_ID")
    @Expose
    public String floorId;
    @SerializedName("BEACON_UUID")
    @Expose
    public String beaconUuid;
    @SerializedName("BEACON_X")
    @Expose
    public String beaconX;
    @SerializedName("BEACON_Y")
    @Expose
    public String beaconY;
    @SerializedName("BEACON_TYPE")
    @Expose
    public String beaconType;
    @SerializedName("BEACON_TX_TYPE")
    @Expose
    public String beaconTxType;
    @SerializedName("BEACON_RADIUS")
    @Expose
    public String beaconRadius;

}
