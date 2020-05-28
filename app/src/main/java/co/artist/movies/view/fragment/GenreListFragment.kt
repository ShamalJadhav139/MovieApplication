package co.artist.movies.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.artist.info.adapter.GenreListAdapter
import co.artist.info.constant.ApiConstants
import co.artist.info.network.MainContract
import co.artist.info.presenter.MainActivityPresenter
import co.artist.info.view.BaseFragment
import co.artist.info.viewmodel.ProjectsViewModel
import co.artist.movies.R
import co.artist.movies.databinding.FragmentMovieListBinding
import co.artist.movies.model.GenreListResponse
import com.google.gson.Gson

class GenreListFragment : BaseFragment(), MainContract.View {
    private var binding: FragmentMovieListBinding? = null
    private var presenter: MainContract.Presenter? = null
    private  var genreListAdapter: GenreListAdapter? = null
    private var mViewModel: ProjectsViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)
        presenter = MainActivityPresenter(this)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        presenter!!.onClick(
            ApiConstants.getGenreList,
            arrayOf("68e82445c8842ba8616d0f6bf0e97a41"),
            requireContext(),
            true
        )

        mViewModel = ViewModelProviders.of(this).get(ProjectsViewModel::class.java)
        binding!!.genreList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        genreListAdapter = GenreListAdapter()
        binding!!.genreList.adapter = genreListAdapter
        mViewModel!!.allWords.observe(activity!!,
            Observer<List<GenreListResponse.Genre>> { words ->
                genreListAdapter!!.setData(words)

            })


    }





companion object {
    private val TAG = GenreListFragment::class.java.name
}

override fun initView() {

}

override fun setViewData(data: String, view: ApiConstants) {
    when (view) {
        ApiConstants.getGenreList -> {
            val response = Gson().fromJson(data, GenreListResponse::class.java)
            if (response != null) {
                mViewModel!!.deleteAll()
                genreListAdapter!!.setData(response.genres)
                mViewModel!!.insertAll(response.genres)
            } else {
                Toast.makeText(context, "Data not found", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
}

