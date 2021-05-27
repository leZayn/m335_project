package m335.penz.persistence;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import m335.penz.model.Pendency;

@Database(entities = {Pendency.class}, version = 3)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "PenzDb";
    private static AppDatabase appDb;

    /**
     *
     * @param context
     * @return
     */
    public static AppDatabase getAppDb(Context context){
        if(appDb == null){
            appDb = Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return appDb;
    }

    public abstract PendencyDao  getPendencyDao();
}
