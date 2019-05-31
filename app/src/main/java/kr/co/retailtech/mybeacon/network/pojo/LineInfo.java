package kr.co.retailtech.mybeacon.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LineInfo {
    @SerializedName("LINE_ID")
    @Expose
    public String lineId;

    @SerializedName("FLOOR_ID")
    @Expose
    public String floorId;

    @SerializedName("LINE_TYPE")
    @Expose
    public String lineType;

    @SerializedName("LINE_X")
    @Expose
    public String lineX;

    @SerializedName("LINE_Y")
    @Expose
    public String lineY;

    @SerializedName("LINE_W")
    @Expose
    public String lineW;

    @SerializedName("LINE_H")
    @Expose
    public String lineH;

}
