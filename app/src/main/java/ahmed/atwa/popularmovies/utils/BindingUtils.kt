

package ahmed.atwa.popularmovies.utils

import ahmed.atwa.popularmovies.data.api.Movie
import ahmed.atwa.popularmovies.data.api.Trailer
import ahmed.atwa.popularmovies.ui.main.detail.TrailerAdapter
import ahmed.atwa.popularmovies.ui.main.home.MovieAdapter
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@BindingAdapter("adapter")
fun addMovieItems(recyclerView: RecyclerView, movies: List<Movie>) {
    val adapter = recyclerView.adapter as? MovieAdapter
    adapter?.clearItems()
    adapter?.addItems(movies)
}

@BindingAdapter("adapter")
fun addTrailerItems(recyclerView: RecyclerView, trailers: List<Trailer>) {
    val adapter = recyclerView.adapter as? TrailerAdapter
    adapter?.clearItems()
    adapter?.addItems(trailers)
}

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    val context = imageView.context
    Glide.with(context).load("http://image.tmdb.org/t/p/w185$url").into(imageView)
}

