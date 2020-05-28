package co.artist.info.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.artist.info.utilites.AppDetails.Companion.activity
import co.artist.movies.MainActivity
import co.artist.movies.databinding.ItemViewGenreListBinding
import co.artist.movies.model.GenreListResponse
import co.artist.movies.model.MovieListResponse

import com.squareup.picasso.Picasso


class GenreListAdapter() : RecyclerView.Adapter<GenreListAdapter.ViewHolder>() {
    var mContext: Context? = null
    private  var movieListAdapter: MovieListAdapter? = null
    var artistList = emptyList<GenreListResponse.Genre>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val inflater = LayoutInflater.from(mContext)
        val binding = ItemViewGenreListBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return artistList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.genreName.text = artistList[position].name
       /* holder.binding.movieList.layoutManager = GridLayoutManager(activity,3)
        movieListAdapter = MovieListAdapter(artistList[position].id as List<MovieListResponse.Result>)
        holder.binding.movieList.adapter = movieListAdapter*/
        holder.itemView.setOnClickListener {
            //(mContext as MainActivity).launchDetailsFragment(artistList[position])
        }
    }

    fun setData(artistList: List<GenreListResponse.Genre>?) {
        this.artistList = artistList!!
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: ItemViewGenreListBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var binding = itemView
    }

    
}