package kr.co.retailtech.mybeacon.data.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "LINE")
public class Line {
    @PrimaryKey
    public int lineId;
    public String floorId;
    public String lineType;
    public String lineX;
    public String lineY;
    public String lineW;
    public String lineH;
}
