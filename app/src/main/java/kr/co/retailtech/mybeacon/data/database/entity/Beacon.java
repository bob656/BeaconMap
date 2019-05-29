package kr.co.retailtech.mybeacon.data.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "BEACON")
public class Beacon {
    @PrimaryKey
    @NonNull
    public String beaconId;
    public String floorId;
    public String beaconUuid;
    public String beaconX;
    public String beaconY;
    public String beaconType;
    public String beaconTxType;
    public String beaconRadius;
}
