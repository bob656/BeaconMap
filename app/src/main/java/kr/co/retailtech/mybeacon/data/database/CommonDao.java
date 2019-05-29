package kr.co.retailtech.mybeacon.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import java.util.List;

import kr.co.retailtech.mybeacon.data.database.entity.Beacon;

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



}
