package kr.co.retailtech.mybeacon.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import java.util.List;

import kr.co.retailtech.mybeacon.data.database.entity.Beacon;
import kr.co.retailtech.mybeacon.data.database.entity.Line;
import kr.co.retailtech.mybeacon.data.database.entity.Map;
import kr.co.retailtech.mybeacon.data.database.entity.Zone;

@Dao
public abstract class CommonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void bulkBeaConInsert(List<Beacon> beaconInfo);

    @Query("Delete from BEACON")
    abstract void deleteBeaConAll();

    @Query("Select * from BEACON order by beaconId")
    public abstract LiveData<List<Beacon>> getBeaConList();

    @Transaction
    public void updateBeaconAll(List<Beacon> info){
        deleteBeaConAll();
        bulkBeaConInsert(info);
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void bulkLineInsert(List<Line> beaconInfo);

    @Query("Delete from LINE")
    abstract void deleteLineAll();

    @Query("Select * from LINE order by lineId")
    public abstract LiveData<List<Line>> getLineList();

    @Transaction
    public void updateLineAll(List<Line> info){
        deleteLineAll();
        bulkLineInsert(info);
    }


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void bulkMapInsert(List<Map> beaconInfo);

    @Query("Delete from MAP")
    abstract void deleteMapAll();

    @Query("Select * from MAP")
    public abstract LiveData<List<Map>> getMapList();

    @Transaction
    public void updateMapAll(List<Map> info){
        deleteMapAll();
        bulkMapInsert(info);
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void bulkZoneInsert(List<Zone> beaconInfo);

    @Query("Delete from ZONE")
    abstract void deleteZoneAll();

    @Query("Select * from ZONE order by zoneId")
    public abstract LiveData<List<Zone>> getZoneList();

    @Transaction
    public void updateZoneAll(List<Zone> info){
        deleteZoneAll();
        bulkZoneInsert(info);
    }

}
