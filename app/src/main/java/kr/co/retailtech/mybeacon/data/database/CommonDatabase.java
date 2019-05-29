package kr.co.retailtech.mybeacon.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

import kr.co.retailtech.mybeacon.data.database.entity.*;

@Database(entities = {Beacon.class, Line.class,Map.class,Zone.class}, version = 2, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class CommonDatabase extends RoomDatabase {
    private static final String LOG_TAG = CommonDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "MAP_INFO.db";

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static CommonDatabase mInstance;

    public static CommonDatabase getInstance(Context context){
        Log.d(LOG_TAG, "Getting " + DATABASE_NAME + " database");

        if (mInstance == null) {
            synchronized (LOCK) {
                mInstance = Room.databaseBuilder(context, CommonDatabase.class, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
                Log.d(LOG_TAG, DATABASE_NAME + " database has been created.");
            }
        }
        return mInstance;
    }

    public abstract CommonDao commonDao();
}
