package kr.co.retailtech.mybeacon.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MapInfo {
    @SerializedName("FLOOR_ID")
    @Expose
    public String floorId;

    @SerializedName("VENUE_ID")
    @Expose
    public String venueId;

    @SerializedName("FLOOR_NAME")
    @Expose
    public String floorName;

    @SerializedName("MAP_FILE")
    @Expose
    public String mapFile;

    @SerializedName("MAP_X")
    @Expose
    public String mapX;

    @SerializedName("MAP_Y")
    @Expose
    public String mapY;

    @SerializedName("MAP_W")
    @Expose
    public String mapW;

    @SerializedName("MAP_H")
    @Expose
    public String mapH;

    @SerializedName("PIXEL_W")
    @Expose
    public String pixelW;

    @SerializedName("PIXEL_H2")
    @Expose
    public String pixelH2;

    @SerializedName("USE_YN")
    @Expose
    public String useYn;
}
