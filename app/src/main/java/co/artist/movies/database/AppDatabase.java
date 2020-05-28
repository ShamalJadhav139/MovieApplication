package co.artist.movies.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import co.artist.info.model.deo.ProjectsDao;
import co.artist.movies.model.GenreListResponse;

@Database(entities = {GenreListResponse.Genre.class}, version = 1, exportSchema = false)


public abstract class AppDatabase extends RoomDatabase {

    public abstract ProjectsDao projectsDao();



    public static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "frogmen_database")
                            .addCallback(sRoomDatabaseCallback).allowMainThreadQueries()
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback =
            new Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);

                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {


        private final ProjectsDao mProjectsDao;



        PopulateDbAsync(AppDatabase db) {
            mProjectsDao = db.projectsDao();

        }

        @Override
        protected Void doInBackground(final Void... params) {
            mProjectsDao.deleteAll();
            return null;
        }
    }
}
