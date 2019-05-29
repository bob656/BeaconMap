package kr.co.retailtech.mybeacon.data.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "ZONE")
public class Zone {
    @PrimaryKey
    public int zoneId;
    public String floorId;
    public String zoneName;
    public String divCd;
    public String zoneX;
    public String zoneY;
    public String zoneW;
    public String zoneH;
}
