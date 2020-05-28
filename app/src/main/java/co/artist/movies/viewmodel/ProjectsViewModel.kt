package co.artist.info.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import co.artist.info.model.repo.ProjectsRepository
import co.artist.movies.model.GenreListResponse


class ProjectsViewModel(application: Application) :
    AndroidViewModel(application) {
    private val mRepository: ProjectsRepository = ProjectsRepository(application)
    internal val allWords: LiveData<List<GenreListResponse.Genre>>

    init {
        allWords = mRepository.listLiveData!!
    }

    internal fun insert(word: GenreListResponse.Genre) {
        mRepository.insert(word)
    }

    internal fun insertAll(word: List<GenreListResponse.Genre>) {
        mRepository.insertAll(word)
    }

    internal fun deleteAll() {
        mRepository.deleteAll()
    }

}
