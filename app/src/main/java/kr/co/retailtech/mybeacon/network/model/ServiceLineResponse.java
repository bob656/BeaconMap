package kr.co.retailtech.mybeacon.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import kr.co.retailtech.mybeacon.network.pojo.LineInfo;

public class ServiceLineResponse {
    @SerializedName("data")
    @Expose
    public List<LineInfo> data ;

}
