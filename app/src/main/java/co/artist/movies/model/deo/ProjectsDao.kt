package co.artist.info.model.deo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

import co.artist.movies.model.GenreListResponse


@Dao
interface ProjectsDao {
    @get:Query("SELECT * from project")
    val getAllProjects: LiveData<List<GenreListResponse.Genre>>

    @Insert
    fun insert(projects: GenreListResponse.Genre)

    @Insert
    fun insert(projects: List<GenreListResponse.Genre>)

    @Query("DELETE FROM project")
    fun deleteAll()


}
