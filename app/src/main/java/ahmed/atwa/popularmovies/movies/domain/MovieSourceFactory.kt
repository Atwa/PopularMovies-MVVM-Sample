package ahmed.atwa.popularmovies.movies.domain

import ahmed.atwa.popularmovies.movies.data.Movie
import ahmed.atwa.popularmovies.movies.data.MovieFilterSource
import ahmed.atwa.popularmovies.movies.data.MoviePagingSource
import ahmed.atwa.popularmovies.movies.data.MovieRepo
import androidx.paging.PagingSource
import javax.inject.Inject

class MovieSourceFactory @Inject constructor(private val movieRepo: MovieRepo) {
    fun getSource( filterText: String): PagingSource<Int, Movie> {
        return if (filterText.isBlank() || filterText.isEmpty()) MoviePagingSource(movieRepo)
        else MovieFilterSource(movieRepo,filterText)
    }
}
