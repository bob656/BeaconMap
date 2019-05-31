package kr.co.retailtech.mybeacon.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ZoneInfo {
    @SerializedName("ZONE_ID")
    @Expose
    public String zoneId;

    @SerializedName("FLOOR_ID")
    @Expose
    public String floorId;

    @SerializedName("ZONE_NAME")
    @Expose
    public String zoneName;

    @SerializedName("DIV_CD")
    @Expose
    public String divCd;

    @SerializedName("ZONE_X")
    @Expose
    public String zoneX;

    @SerializedName("ZONE_Y")
    @Expose
    public String zoneY;

    @SerializedName("ZONE_W")
    @Expose
    public String zoneW;

    @SerializedName("ZONE_H")
    @Expose
    public String zoneH;

}
