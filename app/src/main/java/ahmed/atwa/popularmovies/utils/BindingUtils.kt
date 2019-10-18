package ahmed.atwa.popularmovies.utils

import ahmed.atwa.popularmovies.data.model.Movie
import ahmed.atwa.popularmovies.data.model.Trailer
import ahmed.atwa.popularmovies.ui.detail.TrailerAdapter
import ahmed.atwa.popularmovies.ui.movies.MovieAdapter
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@BindingAdapter("adapter")
fun addMovieItems(recyclerView: RecyclerView, movies: List<Movie>?) {
    val adapter = recyclerView.adapter as? MovieAdapter
    adapter?.clearItems()
    adapter?.addItems(movies!!)
}

@BindingAdapter("adapter")
fun addTrailerItems(recyclerView: RecyclerView, trailers: MutableLiveData<List<Trailer>>) {
    val adapter = recyclerView.adapter as? TrailerAdapter
    adapter?.clearItems()
    adapter?.addItems(trailers.value!!)
}

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    val context = imageView.context
    if (url != null)
        Glide.with(context).load("http://image.tmdb.org/t/p/w185$url").into(imageView)
}

