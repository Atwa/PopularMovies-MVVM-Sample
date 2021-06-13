package ahmed.atwa.popularmovies.movies.presentation

import ahmed.atwa.popularmovies.BuildConfig
import ahmed.atwa.popularmovies.R
import ahmed.atwa.popularmovies.movies.data.Movie
import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


/**
 * Created by Ahmed Atwa on 10/19/18.
 */

class MovieAdapter : PagingDataAdapter<MovieModel, RecyclerView.ViewHolder>(MovieModelComparator) {

    private lateinit var listener: OnItemClick

    fun setListener(mOnItemClick: OnItemClick) {
        listener = mOnItemClick
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is MovieModel.MovieItem -> R.layout.item_movie_view
            is MovieModel.SeparatorItem -> R.layout.item_movie_seperator
            null -> throw UnsupportedOperationException("Unknown view")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_movie_view -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_view, parent, false)
                MovieViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_seperator, parent, false)
                SeparatorViewHolder(view)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movieModel: MovieModel = getItem(position)!!
        movieModel.let {
            when (movieModel) {
                is MovieModel.MovieItem -> {
                    val viewHolder = holder as MovieViewHolder
                    movieModel.movie.poster_path?.let {
                        holder.iv_poster.apply {
                            Glide.with(context)
                                    .load(Uri.parse("${BuildConfig.IMAGE_URL}$it"))
                                    .into(this)
                            setOnClickListener { listener.onMovieClicked(movieModel.movie) }
                        }
                    }
                }
                is MovieModel.SeparatorItem -> {
                    val viewHolder = holder as SeparatorViewHolder
                    viewHolder.tv_separator.text = movieModel.description
                }
            }
        }
    }


    inner class MovieViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv_poster: ImageView = itemView.findViewById(R.id.movieImg) as ImageView
    }

    inner class SeparatorViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_separator: TextView = itemView.findViewById(R.id.separator_description) as TextView
    }

    interface OnItemClick {
        fun onMovieClicked(movieEntity: Movie)
    }


    companion object {
        private val MovieModelComparator = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return (oldItem is MovieModel.MovieItem && newItem is MovieModel.MovieItem &&
                        oldItem.movie.id == newItem.movie.id) ||
                        (oldItem is MovieModel.SeparatorItem && newItem is MovieModel.SeparatorItem &&
                                oldItem.description == newItem.description)
            }

            override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean =
                    oldItem == newItem
        }
    }


}
