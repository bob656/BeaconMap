package kr.co.retailtech.mybeacon.data.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "ZONE")
public class Zone {
    @PrimaryKey
    @NonNull
    public String zoneId;
    public String floorId;
    public String zoneName;
    public String divCd;
    public String zoneX;
    public String zoneY;
    public String zoneW;
    public String zoneH;
}
