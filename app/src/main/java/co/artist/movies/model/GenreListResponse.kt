package co.artist.movies.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class GenreListResponse(
    val genres: List<Genre>
) {
    @Entity(tableName = "project")
    data class Genre(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int = 0,
        @ColumnInfo(name = "name")
        val name: String?
    )
}