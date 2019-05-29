package kr.co.retailtech.mybeacon.data.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "MAP")
public class Map {
    @PrimaryKey
    public int floorId;
    public String venueId;
    public String floorName;
    public String mapFile;

    public String mapX;
    public String mapY;
    public String mapW;
    public String mapH;
    public String pixelW;
    public String pixelH2;
    public String useYn;
}
