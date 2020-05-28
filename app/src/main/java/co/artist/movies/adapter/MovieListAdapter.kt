package co.artist.info.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.artist.info.utilites.AppDetails.Companion.activity
import co.artist.movies.MainActivity
import co.artist.movies.R
import co.artist.movies.databinding.ItemViewGenreListBinding
import co.artist.movies.databinding.ItemViewMovieListBinding
import co.artist.movies.model.GenreListResponse
import co.artist.movies.model.MovieListResponse

import com.squareup.picasso.Picasso


class MovieListAdapter(val list: List<MovieListResponse.Result>) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    var mContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val inflater = LayoutInflater.from(mContext)
        val binding = ItemViewMovieListBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.movieName.text = list[position].original_title
        if(list[position].poster_path.isNotEmpty()){
            Picasso.get().load(list[position].poster_path).fit().centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.binding.roundedBg)
        }
        holder.itemView.setOnClickListener {
            //(mContext as MainActivity).launchDetailsFragment(artistList[position])
        }
    }



    inner class ViewHolder(itemView: ItemViewMovieListBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var binding = itemView
    }

    
}