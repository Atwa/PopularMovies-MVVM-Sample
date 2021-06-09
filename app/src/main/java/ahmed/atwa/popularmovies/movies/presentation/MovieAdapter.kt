package ahmed.atwa.popularmovies.movies.presentation

import ahmed.atwa.popularmovies.R
import ahmed.atwa.popularmovies.movies.domain.MovieEntity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import javax.inject.Inject


/**
 * Created by Ahmed Atwa on 10/19/18.
 */

class MovieAdapter @Inject constructor(private val mMoviesList: MutableList<MovieEntity>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    private val movieThumbBaseUrl = "http://image.tmdb.org/t/p/w185"
    private lateinit var listener: OnItemClick

    override fun getItemCount(): Int {
        return if (mMoviesList.size > 0) mMoviesList.size else 0
    }

    fun setListener(mOnItemClick: OnItemClick) {
        listener = mOnItemClick
    }

    fun addItems(mList: List<MovieEntity>?) {
        if (mList != null) {
            clearItems()
            mMoviesList.addAll(mList)
            notifyDataSetChanged()
        }
    }


    private fun clearItems() {
        mMoviesList.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_view, parent, false)
        return MovieViewHolder(view)
    }

    inner class MovieViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var myImgView: ImageView = itemView.findViewById(R.id.movieImg) as ImageView

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val moviePoster = mMoviesList[position].poster_path
        moviePoster?.let {
            holder.myImgView.apply {
                Glide.with(context)
                        .load(Uri.parse("$movieThumbBaseUrl$moviePoster"))
                        .into(this)
                setOnClickListener { listener.onMovieClicked(mMoviesList[position]) }
            }
        }
    }

    interface OnItemClick {
        fun onMovieClicked(movieEntity: MovieEntity)
    }

}
