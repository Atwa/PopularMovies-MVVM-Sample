package ahmed.atwa.popularmovies.movies.domain

import ahmed.atwa.popularmovies.movies.data.Movie
import ahmed.atwa.popularmovies.movies.data.MovieFilterSource
import ahmed.atwa.popularmovies.movies.data.MoviePagingSource
import ahmed.atwa.popularmovies.movies.data.MovieRepo
import ahmed.atwa.popularmovies.movies.presentation.MovieAdapter
import androidx.paging.PagingSource
import javax.inject.Inject

class MovieSourceFactory @Inject constructor() {

    @Inject
    lateinit var movieFilterSource: MovieFilterSource

    @Inject
    lateinit var moviePagingSource: MoviePagingSource

    fun getSource( filterText: String): PagingSource<Int, Movie> {
        return if (filterText.isBlank() || filterText.isEmpty()) moviePagingSource
        else movieFilterSource.apply { this.filterText = filterText }
    }
}
