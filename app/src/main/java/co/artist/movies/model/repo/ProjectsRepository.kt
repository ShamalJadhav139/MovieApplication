package co.artist.info.model.repo

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import co.artist.info.model.deo.ProjectsDao
import co.artist.movies.database.AppDatabase
import co.artist.movies.model.GenreListResponse


class ProjectsRepository(application: Application) {
    private val projectsDao: ProjectsDao
    var listLiveData: LiveData<List<GenreListResponse.Genre>>? = null


    init {
        val database = AppDatabase.getDatabase(application)
        projectsDao = database.projectsDao()
        listLiveData = projectsDao.getAllProjects


    }

    fun insert(word: GenreListResponse.Genre) {
        InsertAsyncTask(projectsDao).execute(word)
    }

    fun insertAll(word: List<GenreListResponse.Genre>) {
        InsertAllAsyncTask(projectsDao).execute(word)
    }



    fun deleteAll() {
        DeleteAsyncTask(projectsDao).execute()
    }

    private class InsertAsyncTask internal constructor(private val mAsyncTaskDao: ProjectsDao) :
        AsyncTask<GenreListResponse.Genre, Void, Void>() {

        override fun doInBackground(vararg params: GenreListResponse.Genre): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }

    private class InsertAllAsyncTask internal constructor(private val mAsyncTaskDao: ProjectsDao) :
        AsyncTask<List<GenreListResponse.Genre>, Void, Void>() {

        override fun doInBackground(vararg params: List<GenreListResponse.Genre>): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }

    private class DeleteAsyncTask internal constructor(private val mAsyncTaskDao: ProjectsDao) :
        AsyncTask<GenreListResponse.Genre, Void, Void>() {

        override fun doInBackground(vararg params: GenreListResponse.Genre): Void? {
            mAsyncTaskDao.deleteAll()
            return null
        }
    }


}


